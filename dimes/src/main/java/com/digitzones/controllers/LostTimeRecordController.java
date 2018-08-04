package com.digitzones.controllers;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.config.WorkFlowKeyConfig;
import com.digitzones.constants.Constant;
import com.digitzones.model.Classes;
import com.digitzones.model.DeviceSite;
import com.digitzones.model.LostTimeRecord;
import com.digitzones.model.Pager;
import com.digitzones.model.PressLightType;
import com.digitzones.model.User;
import com.digitzones.service.IClassesService;
import com.digitzones.service.IDeviceSiteService;
import com.digitzones.service.ILostTimeRecordService;
import com.digitzones.service.IPressLightTypeService;
import com.digitzones.service.IProductionUnitService;
import com.digitzones.util.DateStringUtil;
import com.digitzones.vo.LostTimeRecordVO;
@Controller
@RequestMapping("/lostTimeRecord")
public class LostTimeRecordController {
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private DecimalFormat decimalFormat = new DecimalFormat("#.00");
	private IDeviceSiteService deviceSiteService;
	private ILostTimeRecordService lostTimeRecordService;
	private IPressLightTypeService pressLightTypeService;
	private IClassesService classesService;
	private IProductionUnitService productionUnitService;
	private DateStringUtil util = new DateStringUtil();
	private WorkFlowKeyConfig workFlowKeyConfig;
	private ProcessEngine processEngine;
	@Autowired
	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}
	@Autowired
	public void setWorkFlowKeyConfig(WorkFlowKeyConfig workFlowKeyConfig) {
		this.workFlowKeyConfig = workFlowKeyConfig;
	}
	@Autowired
	public void setProductionUnitService(IProductionUnitService productionUnitService) {
		this.productionUnitService = productionUnitService;
	}
	@Autowired
	public void setClassesService(IClassesService classesService) {
		this.classesService = classesService;
	}

	@Autowired
	public void setPressLightTypeService(IPressLightTypeService pressLightTypeService) {
		this.pressLightTypeService = pressLightTypeService;
	}

	@Autowired
	public void setLostTimeRecordService(@Qualifier("lostTimeRecordServiceProxy")ILostTimeRecordService lostTimeRecordService) {
		this.lostTimeRecordService = lostTimeRecordService;
	}

	@Autowired
	public void setDeviceSiteService(IDeviceSiteService deviceSiteService) {
		this.deviceSiteService = deviceSiteService;
	}
	/**
	 * 根据设备站点id查找损时记录
	 * @param deviceSiteId
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryLostTimeRecordByDeviceSiteId.do")
	@ResponseBody
	public ModelMap queryLostTimeRecordByDeviceSiteId(Long deviceSiteId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		String hql = "from LostTimeRecord ltr where ltr.deviceSite.id=?0 and ltr.deleted=?1";
		Pager<LostTimeRecord> pager = lostTimeRecordService.queryObjs(hql, page, rows, new Object[] {deviceSiteId,false});
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}

	/**
	 * 添加损时记录
	 * @param pressLightRecord
	 * @return
	 */
	@RequestMapping("/addLostTimeRecord.do")
	@ResponseBody
	public ModelMap addLostTimeRecord(LostTimeRecord lostTimeRecord,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		DeviceSite ds = deviceSiteService.queryObjById(lostTimeRecord.getDeviceSite().getId());
		if(ds==null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "请选择设备站点");
			return modelMap;
		}

		if(lostTimeRecord.getBeginTime()==null) {
			lostTimeRecord.setBeginTime(new Date());
		}

		if(lostTimeRecord.getEndTime()!=null) {
			long endTimeMilliSec = lostTimeRecord.getEndTime().getTime();
			long beginTimeMilliSec = lostTimeRecord.getBeginTime().getTime();
			long result = endTimeMilliSec - beginTimeMilliSec;
			lostTimeRecord.setSumOfLostTime(result/60/1000*1.0);
		}
		lostTimeRecord.setLostTimeTime(new Date());
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constant.User.LOGIN_USER);
		try {
			Map<String,Object> args = new HashMap<>();
			args.put("businessKey", workFlowKeyConfig.getLostTimeWorkflowKey());
			Serializable id = lostTimeRecordService.addLostTimeRecord(lostTimeRecord,user,args);
			//执行损时申请
			TaskService taskService = processEngine.getTaskService();
			Map<String,Object> variables = new HashMap<>();
			variables.put(Constant.Workflow.LOSTTIME_CONFIRM_ROLES, "损时确认人");
			variables.put(Constant.Workflow.LOSTTIME_CONFIRM_PERSON, "");
			variables.put(Constant.Workflow.LOSTTIME_CONFIRM_PERSONS, "");
			Task task = taskService.createTaskQuery().processInstanceBusinessKey(id+"").taskAssignee(user.getUsername()).singleResult();
			taskService.complete(task.getId(),variables);
		}catch(ActivitiObjectNotFoundException e) {
			e.printStackTrace();
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "启动流程失败:启动流程的key不存在！");
			return modelMap;
		}
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "添加成功!");
		return modelMap;
	}
	/**
	 * 根据id查询损时记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryLostTimeRecordById.do")
	@ResponseBody
	public LostTimeRecordVO queryLostTimeRecordById(Long id) {
		LostTimeRecord plr = lostTimeRecordService.queryObjById(id);
		return model2VO(plr);
	}


	private LostTimeRecordVO model2VO(LostTimeRecord lostTimeRecord) {
		LostTimeRecordVO vo = new LostTimeRecordVO();
		vo.setId(lostTimeRecord.getId());
		if(lostTimeRecord.getBeginTime()!=null) {
			vo.setBeginTime(format.format(lostTimeRecord.getBeginTime()));
		}

		if(lostTimeRecord.getEndTime()!=null) {
			vo.setEndTime(format.format(lostTimeRecord.getEndTime()));
		}
		vo.setDescription(lostTimeRecord.getDescription());
		vo.setLostTimeTypeName(lostTimeRecord.getLostTimeTypeName());
		vo.setReason(lostTimeRecord.getReason());
		return vo;
	}


	/**
	 * 编辑损时记录
	 * @param pressLightRecord
	 * @return
	 */
	@RequestMapping("/updateLostTimeRecord.do")
	@ResponseBody
	public ModelMap updatePressLightRecord(LostTimeRecord lostTimeRecord) {
		ModelMap modelMap = new ModelMap();

		LostTimeRecord plr = lostTimeRecordService.queryObjById(lostTimeRecord.getId());
		if(lostTimeRecord.getEndTime()!=null) {
			long endTimeMilliSec = lostTimeRecord.getEndTime().getTime();
			long beginTimeMilliSec = lostTimeRecord.getBeginTime().getTime();
			long result = endTimeMilliSec - beginTimeMilliSec;
			plr.setSumOfLostTime(result/60/1000*1.0);
		}

		plr.setBeginTime(lostTimeRecord.getBeginTime());
		plr.setEndTime(lostTimeRecord.getEndTime());
		plr.setDescription(lostTimeRecord.getDescription());
		plr.setLostTimeTypeName(lostTimeRecord.getLostTimeTypeName());
		plr.setLostTimeTypeCode(lostTimeRecord.getLostTimeTypeCode());
		plr.setReason(lostTimeRecord.getReason());
		lostTimeRecordService.updateObj(plr);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "操作成功!");
		return modelMap;
	}
	/**
	 * 确认
	 * @param pressLightRecord
	 * @return
	 */
	@RequestMapping("/confirmLostTimeRecord.do")
	@ResponseBody
	public ModelMap confirmLostTimeRecord(Long id,String suggestion,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Constant.User.LOGIN_USER);
		LostTimeRecord plr = lostTimeRecordService.queryObjById(id);
		plr.setConfirmTime(new Date());
		Map<String,Object> args = new HashMap<>();
		args.put("suggestion", suggestion);
		lostTimeRecordService.confirm(plr,user,args);
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("title", "提示");
		modelMap.addAttribute("message", "操作成功!");
		return modelMap;
	}
	/**
	 * 根据id删除损时记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteLostTimeRecord.do")
	@ResponseBody
	public ModelMap deleteLostTimeRecord(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		LostTimeRecord pr = lostTimeRecordService.queryObjById(Long.valueOf(id));
		pr.setDeleted(true);
		try {
			lostTimeRecordService.deleteLostTimeRecord(pr);
		}catch(RuntimeException e) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", e.getMessage());
			return modelMap;
		}
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 查询当前年所有月份的损时统计
	 * 工厂级：故障时间
	 * @return
	 */
	@RequestMapping("/queryLostTimeRecordFor1Year.do")
	@ResponseBody
	public ModelMap queryLostTimeRecordFor1Year() {
		ModelMap modelMap = new ModelMap();
		List<List<Object>> data = new ArrayList<>();
		Object[] months = {"月份","1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
		data.add(Arrays.asList(months));

		//查询所有大类别
		List<PressLightType> types = pressLightTypeService.queryFirstLevelType();
		if(types==null || types.size()<=0) {
			List<Object> l = new ArrayList<>();
			l.add("");
			for(int i = 1;i<13;i++) {
				l.add(0);
			}
			data.add(l);
			modelMap.addAttribute("data",data);
			return modelMap;
		}

		for(PressLightType plt : types) {
			List<Object> list = new ArrayList<>();
			list.add(plt.getName());

			data.add(list);
		}

		double max = 0;

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		for(int i = 1;i<=12;i++) {
			if(i<=month) {
				List<LostTimeRecord> list = lostTimeRecordService.queryLostTimeRecordByYearAndMonth(year, i);
				data:for(int j = 0;j<data.size()-1;j++) {
					List<Object> typeList = data.get(j+1);
					for(int k = 0;k<list.size();k++) {
						LostTimeRecord ltr = list.get(k);
						if(ltr.getLostTimeTypeName().equals(typeList.get(0))) {
							if(ltr.getSumOfLostTime()>max) {
								max = ltr.getSumOfLostTime();
							}
							typeList.add(ltr.getSumOfLostTime());
							continue data;
						}
					}
					typeList.add(0);
				}
			}else {
				for(int j = 0;j<data.size()-1;j++) {
					List<Object> typeList = data.get(j+1);
					typeList.add(0);
				}
			}
		}
		modelMap.addAttribute("max",max + max * 0.05);
		modelMap.addAttribute("data",data);
		return modelMap;
	}
	/**
	 * 工厂级：故障停机
	 * @return
	 */
	@RequestMapping("/queryLostTimeRecordOfHalt.do")
	@ResponseBody
	public ModelMap queryLostTimeRecordOfHalt() {
		ModelMap modelMap = new ModelMap();
		//存放每个月的故障小时数
		List<Double> hours = new ArrayList<>();
		//存放故障小时数和总运行数的占比
		List<Double>  ratios = new ArrayList<>();
		//查询所有单元内的站点数目
		Long count = deviceSiteService.queryCountOfDeviceSite();
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		for(int i = 1;i<=12;i++) {
			if(i<=month) {
				Double hour = lostTimeRecordService.queryHoursOfLostTimeRecordByYearAndMonth(year, i);
				Double planHaltHour = lostTimeRecordService.queryHoursOfPlanHaltByYearAndMonth(year, i);
				hours.add(hour);
				if(hour!=null) {
					switch(i) {
					case 1:
					case 3:
					case 5:
					case 7:
					case 8:
					case 10:
					case 12:{
						ratios.add(hour/(count*24*31-(planHaltHour==null?0:planHaltHour))*100);
						break;
					}
					case 4:
					case 6:
					case 9:
					case 11:{
						ratios.add(hour/(count*24*30-(planHaltHour==null?0:planHaltHour))*100);
						break;
					}
					case 2:{
						GregorianCalendar gc = new GregorianCalendar();
						if(gc.isLeapYear(c.get(Calendar.YEAR))) {
							ratios.add(hour/(count*24*29-(planHaltHour==null?0:planHaltHour))*100);
						}else {
							ratios.add(hour/(count*24*28-(planHaltHour==null?0:planHaltHour))*100);
						}
						break;
					}
					}
				}else {
					ratios.add(0.0);
				}
			}else {
				hours.add(0.0);
				ratios.add(0.0);
			}
		}
		modelMap.addAttribute("hours", hours);
		modelMap.addAttribute("ratios", ratios);
		return modelMap;
	}
	/**
	 * 损时原因分析
	 * @param productionUnitId
	 * @return
	 */
	@RequestMapping("/queryLostTimeReason4ProductionUnit.do")
	@ResponseBody
	public ModelMap queryLostTimeReason4ProductionUnit(Long productionUnitId) {
		ModelMap modelMap = new ModelMap();
		//产生一个月的时间
		DateStringUtil util = new DateStringUtil();
		List<Date> days =util.generateOneMonthDay(util.date2Month(new Date()));

		List<String> dayList = new ArrayList<>();
		for(Date day : days) {
			dayList.add(util.date2DayOfMonth(day));
		}
		//根据产线查找目标OEE
		double goalOee = productionUnitService.queryOeeByProductionUnitId(productionUnitId)*1.0/100;
		//存放每个班的损时数
		List<String> lostTimeList = new ArrayList<>();
		//存放总损时数(每个班的损时List)
		List<List<String>> list = new ArrayList<>();
		Date date = new Date();
		//根据生产单元id查询设备站点
		List<DeviceSite> deviceSites = deviceSiteService.queryDeviceSitesByProductionUnitId(productionUnitId);
		//查询所有班次
		List<Classes> classesList = classesService.queryAllClasses();

		double totalHours = 0;
		for(int i = 0;i<classesList.size();i++) {


			Classes c = classesList.get(i);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(c.getStartTime());
			//结束时间
			Calendar cal = Calendar.getInstance();
			cal.setTime(c.getEndTime());
			long totalMinutes = 0;
			//班次开始时间大于结束时间
			if(calendar.get(Calendar.HOUR_OF_DAY)<cal.get(Calendar.HOUR_OF_DAY)) {
				totalMinutes = cal.get(Calendar.HOUR_OF_DAY)*60+cal.get(Calendar.MINUTE)-calendar.get(Calendar.HOUR_OF_DAY)*60-calendar.get(Calendar.MINUTE);
			}else {
				totalMinutes = 24*60+(calendar.get(Calendar.HOUR_OF_DAY)*60+calendar.get(Calendar.MINUTE))-(cal.get(Calendar.HOUR_OF_DAY)*60-cal.get(Calendar.MINUTE));
			}

			double sumHours = totalMinutes*1.0/60;
			totalHours+=sumHours;
			for(Date now : days) {
				String nowDay = util.date2DayOfMonth(now);
				String dateDay = util.date2DayOfMonth(date);
				double sumLostTimeCount = 0;
				if(Integer.valueOf(nowDay)<=Integer.valueOf(dateDay)) {

					for(DeviceSite ds : deviceSites) {
						//查询损时时间(包括计划停机时间)
						double lostTime = lostTimeRecordService.queryLostTime4PerDay(c,ds.getId(),now);
						//查询计划停机时间
						double planHaltTime = lostTimeRecordService.queryPlanHaltTime(c, ds.getId(),now);
						sumLostTimeCount += (lostTime-planHaltTime)/60;
					}
					lostTimeList.add(decimalFormat.format(sumLostTimeCount));
				}else {
					lostTimeList.add(0+"");
				}
			}
			list.add(lostTimeList);
		}
		List<String> goalLostTimeList = new ArrayList<>();
		//计算损时目标
		double goalLostTime = totalHours * (1-goalOee);
		for(@SuppressWarnings("unused") Date now : days) {
			goalLostTimeList.add(decimalFormat.format(goalLostTime));
		}
		modelMap.addAttribute("classes",classesList);
		modelMap.addAttribute("goalLostTimeList",goalLostTimeList);
		modelMap.addAttribute("lostTimeList",list);
		modelMap.addAttribute("days", dayList);
		return modelMap;
	}
	/**
	 * 按分钟查询实时损时记录
	 * @return
	 */
	@RequestMapping("/queryLostTime4RealTime.do")
	@ResponseBody
	public ModelMap queryLostTime4RealTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		ModelMap modelMap = new ModelMap();
		//产生720分钟时间
		List<Date> minutesList = util.generate720Minutes(new Date());
		List<String> minutes = new ArrayList<>();
		List<Integer> minuteCountList = new ArrayList<>();
		for(Date date : minutesList) {
			minutes.add(simpleDateFormat.format(date));
			minuteCountList.add(lostTimeRecordService.queryLostTime4RealTime(date));
		}

		modelMap.addAttribute("minutes", minutes);
		modelMap.addAttribute("lostTimeCounts", minuteCountList);
		return modelMap;
	}
} 
