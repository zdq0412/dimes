package com.digitzones.controllers;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Classes;
import com.digitzones.model.DeviceSite;
import com.digitzones.model.DeviceSiteParameterMapping;
import com.digitzones.model.ProductionUnit;
import com.digitzones.service.IClassesService;
import com.digitzones.service.IDeviceSiteParameterMappingService;
import com.digitzones.service.IDeviceSiteService;
import com.digitzones.service.ILostTimeRecordService;
import com.digitzones.service.INGRecordService;
import com.digitzones.service.IProcessRecordService;
import com.digitzones.service.IProductionUnitService;
import com.digitzones.util.DateStringUtil;
@Controller
@RequestMapping("/oee")
public class OeeController {
	private DecimalFormat format = new DecimalFormat("#.00");
	private IProductionUnitService productionUnitService;
	private IDeviceSiteService deviceSiteService;
	private IClassesService classesService;
	private ILostTimeRecordService lostTimeRecordService;
	private Date now = new Date();
	private IProcessRecordService processRecordService;
	private INGRecordService ngRecordService;
	private IDeviceSiteParameterMappingService deviceSiteParameterMappingService;
	@Autowired
	public void setDeviceSiteParameterMappingService(IDeviceSiteParameterMappingService deviceSiteParameterMappingService) {
		this.deviceSiteParameterMappingService = deviceSiteParameterMappingService;
	}
	@Autowired
	public void setNgRecordService(INGRecordService ngRecordService) {
		this.ngRecordService = ngRecordService;
	}
	@Autowired
	public void setProcessRecordService(IProcessRecordService processRecordService) {
		this.processRecordService = processRecordService;
	}
	@Autowired
	public void setLostTimeRecordService(ILostTimeRecordService lostTimeRecordService) {
		this.lostTimeRecordService = lostTimeRecordService;
	}
	@Autowired
	public void setClassesService(IClassesService classesService) {
		this.classesService = classesService;
	}
	@Autowired
	public void setProductionUnitService(IProductionUnitService productionUnitService) {
		this.productionUnitService = productionUnitService;
	}
	@Autowired
	public void setDeviceSiteService(IDeviceSiteService deviceSiteService) {
		this.deviceSiteService = deviceSiteService;
	}
	/**
	 * 工厂级oee查询
	 * @return
	 */
	@RequestMapping("/queryOee4Factory")
	@ResponseBody
	public ModelMap queryOee4Factory(){
		ModelMap modelMap = new ModelMap();
		//查找所有生产单元
		List<ProductionUnit> productionUnits = productionUnitService.queryAllProductionUnits();
		modelMap.put("productionUnits", productionUnits);
		modelMap.put("values", queryOee4PerProductionUnit(productionUnits));
		//产生一个月的时间
		DateStringUtil util = new DateStringUtil();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		List<Date> days =util.generateOneMonthDay(util.date2Month(c.getTime()));
		c.set(Calendar.DATE, days.size());
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		modelMap.put("preMonthOee", format.format(oneMonthOee(c,days)*100));
		c.add(Calendar.MONTH, 1);
		days = util.generateOneMonthDay(util.date2Month(c.getTime()));
		modelMap.put("currentMonthOee",format.format(oneMonthOee(c,days)*100));
		modelMap.put("currentDayOee",format.format(currentDayOee()*100));
		return modelMap;
	}
	/**
	 * 当天OEE值
	 * @return
	 */
	private Double currentDayOee() {
		double sumOee = 0;
		//根据生产单元查询设备站点
		List<BigDecimal> deviceSiteIds = deviceSiteService.queryAllDeviceSiteIds();
		//查询当前班次
		Classes c = classesService.queryCurrentClasses();
		//根据设备站点查询加工信息
		for(BigDecimal deviceSiteId : deviceSiteIds) {
			DeviceSite ds = new DeviceSite();
			ds.setId(deviceSiteId.longValue());
			sumOee += computeOee4CurrentClass(c, ds,new Date());
		}
		return sumOee/deviceSiteIds.size();
	}
	/**
	 * 查询每个生产单元的OEE值
	 * @param productionUnits
	 * @return
	 */
	private List<String> queryOee4PerProductionUnit(List<ProductionUnit> productionUnits){
		List<String> values = new ArrayList<>();
		for(ProductionUnit pu : productionUnits) {
			double sumOee = 0;
			//根据生产单元查询设备站点
			List<BigDecimal> deviceSites = deviceSiteService.queryDeviceSiteIdsByProductionUnitId(pu.getId());
			//查询当前班次
			Classes c = classesService.queryCurrentClasses();
			//根据设备站点查询加工信息
			for(BigDecimal dsid : deviceSites) {
				DeviceSite ds = new DeviceSite();
				ds.setId(dsid.longValue());
				sumOee += computeOee4CurrentClass(c, ds,new Date());
			}

			values.add(format.format(sumOee/deviceSites.size()*100));
		}
		return values;
	}
	/**
	 * 查询上个月的OEE值
	 * @return
	 */
	private Double oneMonthOee(Calendar c,List<Date> days) {
		//计算上个月总工时，单位：秒
		long total = 24*3600*days.size();
		//查询上个月损时数,包括计划停机,单位：分钟
		double totalLostTime =  lostTimeRecordService.queryLostTimeFromBeginOfMonthUntilTheDate(c.getTime(), null);
		//查询上个月计划停机数,单位：分钟
		double totalPlanHalt = lostTimeRecordService.queryLostTimeFromBeginOfMonthUntilTheDate(c.getTime(), true);
		//时间开动率
		double timeRate=(total-totalLostTime*60)/(total-totalPlanHalt*60);
		//查询上月生产数量，总标准节拍(加工数量*标准节拍),总短停机时间(即时节拍-标准节拍的和)
		Object[] countAndSumOfStandardBeatAndShortHalt = processRecordService.queryCountAndSumOfStandardBeatAndSumOfShortHaltFromBeginOfMonthUntilTheDate(c.getTime());
		//总标准节拍
		double sumOfStandardBeat = 0;
		//总短停机时间
		double sumOfShortHalt = 0;
		//总生产数
		double count = 0;
		if(countAndSumOfStandardBeatAndShortHalt!=null && countAndSumOfStandardBeatAndShortHalt.length>0) {
			count = (int)countAndSumOfStandardBeatAndShortHalt[0];
			if(countAndSumOfStandardBeatAndShortHalt[1]!=null) {
				sumOfStandardBeat = (int)countAndSumOfStandardBeatAndShortHalt[1];
			}
			if(countAndSumOfStandardBeatAndShortHalt[2]!=null) {
				sumOfShortHalt = (int)countAndSumOfStandardBeatAndShortHalt[2];
			}
		}
		//性能开动率
		double performanceRate = 0;
		if((sumOfStandardBeat+sumOfShortHalt)!=0) {
			performanceRate = sumOfStandardBeat/(sumOfStandardBeat+sumOfShortHalt);
		}
		//查询不合格品数
		int ngCount = ngRecordService.queryNgCountFromBeginOfMonthUntilTheDate(c.getTime());
		//合格品数量
		int notNgCount = (int) (count - ngCount);
		//合格品率
		double qualifiedRate = notNgCount / count;

		return timeRate * performanceRate * qualifiedRate;
	}
	/**
	 * oee:Overall Equipment Effectiveness设备综合效率
	 * 公式：OEE=时间开动率*性能开动率*合格品率*100%
	 * 时间开动率=((当前时间-班次开始时间)-损时)/((当前时间-班次开始时间)-计划停机时间)
	 * 性能开动率=理论加工周期/实际加工周期=加工数量*标准节拍/(加工数量*标准节拍+短停机)
	 * 短停机=(即时节拍1-标准节拍)+(即时节拍2-标准节拍)...
	 * 合格品率＝合格品数量/加工数量
	 * 查询当前班的当前设备即时OEE
	 * @param currentClass 当前班次
	 * @param ds 设备站点
	 * @return
	 */
	private double computeOee4CurrentClass(Classes currentClass ,DeviceSite deviceSite,Date date) {
		//获取当前班次在线时长,当前时间-班次开始时间，单位：秒
		long onlineTime = cumputeClassOnlineTime(currentClass);
		//查询损时时间(包括计划停机时间)，单位：分钟
		double lostTime = lostTimeRecordService.queryLostTime(currentClass,deviceSite.getId(),date);
		//查询计划停机时间，单位：分钟
		double planHaltTime = lostTimeRecordService.queryPlanHaltTime(currentClass, deviceSite.getId(),date);
		//时间开动率
		double timeRate = 0;
		if(onlineTime!=0) {
			timeRate=(onlineTime-lostTime*60)/(onlineTime-planHaltTime*60);
		}
		//查询当前班，当前设备的生产数量，总标准节拍(加工数量*标准节拍),总短停机时间(即时节拍-标准节拍的和)
		Object[] countAndSumOfStandardBeatAndShortHalt = processRecordService.queryCountAndSumOfStandardBeatAndSumOfShortHalt4CurrentClass(currentClass, deviceSite.getId(),date);
		//总标准节拍
		double sumOfStandardBeat = 0;
		//总短停机时间
		double sumOfShortHalt = 0;
		//总生产数
		double count = 0;
		if(countAndSumOfStandardBeatAndShortHalt!=null && countAndSumOfStandardBeatAndShortHalt.length>0) {
			count = (int)countAndSumOfStandardBeatAndShortHalt[0];
			if(countAndSumOfStandardBeatAndShortHalt[1]!=null) {
				sumOfStandardBeat = (int)countAndSumOfStandardBeatAndShortHalt[1];
			}
			if(countAndSumOfStandardBeatAndShortHalt[2]!=null) {
				sumOfShortHalt = (int)countAndSumOfStandardBeatAndShortHalt[2];
			}
		}
		//性能开动率
		double performanceRate = 0;
		if((sumOfStandardBeat+sumOfShortHalt)!=0) {
			performanceRate = sumOfStandardBeat/(sumOfStandardBeat+sumOfShortHalt);
		}
		//查询不合格品数
		int ngCount = ngRecordService.queryNgCount4Class(currentClass, deviceSite.getId(),date);
		//合格品数量
		int notNgCount = (int) (count - ngCount);
		//合格品率
		double qualifiedRate = 0;
		if(count!=0) {
			qualifiedRate= notNgCount / count;
		}

		return timeRate * performanceRate * qualifiedRate;
	}
	/**
	 * 计算当前班次的在线时间，单位：秒
	 * @param c
	 * @return
	 */
	private long cumputeClassOnlineTime(Classes c) {
		//当前时间-班次开始时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		//当前时间，单位(秒)
		long currentTime = calendar.get(Calendar.HOUR_OF_DAY)*3600 + calendar.get(Calendar.MINUTE)*60 + calendar.get(Calendar.SECOND);
		Calendar cal = Calendar.getInstance();
		cal.setTime(c.getStartTime());
		//当前班次的开始时间，单位(秒)
		long classesBeginTime = cal.get(Calendar.HOUR_OF_DAY)*3600 + cal.get(Calendar.MINUTE)*60 + cal.get(Calendar.SECOND);
		long onlineTime = 0;
		if(currentTime>classesBeginTime) {
			onlineTime = currentTime - classesBeginTime;
		}else {
			onlineTime = currentTime+24*3600 - classesBeginTime;
		}
		return onlineTime;
	}

	/**
	 * 查询当前月的OEE值
	 * @return
	 *//*
	private Double currentMonthOee() {
		double sumOee = 0;
		//根据生产单元查询设备站点
		List<BigDecimal> deviceSites = deviceSiteService.queryAllDeviceSiteIds();
		List<Classes> classList = classesService.queryAllClasses();
		//产生一个月的时间
		DateStringUtil util = new DateStringUtil();
		List<Date> days =util.generateOneMonthDay(util.date2Month(new Date()));
		for(Date today : days) {
			for(Classes classes : classList) {
				//根据设备站点查询加工信息
				for(BigDecimal dsid : deviceSites) {
					DeviceSite ds = new DeviceSite();
					ds.setId(dsid.longValue());
					sumOee += computeOee4CurrentClass(classes, ds, today);
				}
			}
		}

		if(classList!=null&&classList.size()>0) {
			if(deviceSites!=null &&deviceSites.size()>0) {
				return sumOee/(deviceSites.size()*classList.size()*days.size());
			}
		}

		return 0.0;
	}*/

	/**
	 * 查询每个生产单元的OEE值
	 * @return
	 */
	@RequestMapping("/queryOee4ProductionUnit.do")
	@ResponseBody
	public ModelMap queryOee4ProductionUnit(Long productionUnitId) {
		if(productionUnitId==null) {
			return null;
		}
		//根据产线查找目标OEE
		double goalOee = productionUnitService.queryOeeByProductionUnitId(productionUnitId);
		List<String> goalOeeList = new ArrayList<>();
		ModelMap modelMap = new ModelMap();
		//产生一个月的时间
		DateStringUtil util = new DateStringUtil();
		List<Date> days =util.generateOneMonthDay(util.date2Month(new Date()));
		List<List<String>> oeeList = new ArrayList<>();

		List<String> dayList = new ArrayList<>();
		for(Date day : days) {
			dayList.add(util.date2DayOfMonth(day));
		}
		Date date = new Date();
		//根据生产单元id查询设备站点
		List<BigDecimal> deviceSiteIds = deviceSiteService.queryDeviceSiteIdsByProductionUnitId(productionUnitId);
		//查询所有班次
		List<Classes> classesList = classesService.queryAllClasses();
		for(int i = 0;i<classesList.size();i++) {
			Classes c = classesList.get(i);
			List<String> oees = new ArrayList<>();
			for(Date now : days) {
				goalOeeList.add(format.format(goalOee));

				String nowDay = util.date2DayOfMonth(now);
				String dateDay = util.date2DayOfMonth(date);
				if(Integer.valueOf(nowDay)<=Integer.valueOf(dateDay)) {
					double oee = 0;
					for(BigDecimal deviceSiteId : deviceSiteIds) {
						DeviceSite ds = new DeviceSite();
						ds.setId(deviceSiteId.longValue());
						oee +=computeOee4CurrentClass(c,ds,now);
					}
					oees.add(format.format(oee/deviceSiteIds.size()*100));
				}else {
					oees.add(0+"");
				}
			}
			oeeList.add(oees);
		}
		//求平均值
		List<String> avgs = new ArrayList<>();
		for(int j = 0;j<days.size();j++) {
			double sum = 0;
			for(int i = 0;i<oeeList.size();i++) {
				if(j<oeeList.get(i).size()) {
					try {
						sum += Double.parseDouble(oeeList.get(i).get(j));
					}catch (Exception e) {
						System.err.println(e.getMessage());
						sum += 0;
					}
				}
			}
			avgs.add(format.format(sum/oeeList.size()));
		}

		modelMap.addAttribute("classes",classesList);
		modelMap.addAttribute("goalOeeList",goalOeeList);
		modelMap.addAttribute("avgs",avgs);
		modelMap.addAttribute("oeeList", oeeList);
		modelMap.addAttribute("days", dayList);
		return modelMap;
	}

	/**
	 * 查询运行率top
	 * @return
	 */
	@RequestMapping("/queryOperationRateTop.do")
	@ResponseBody
	public ModelMap queryOperationRateTop() {
		ModelMap modelMap = new ModelMap();
		String[] placingList = {
				"第一名",
				"第二名",
				"第三名",
				"第四名",
				"第五名",
				"第六名",
				"第七名",
				"第八名",
				"第九名",
				"第十名",
		};
		List<String> placing = new ArrayList<>();
		List<String> deviceSiteNames = new ArrayList<>();
		List<Object[]> values = new ArrayList<>();
		Date today = new Date();
		//根据生产单元查询设备站点
		List<DeviceSite> deviceSites = deviceSiteService.queryAllDeviceSites();
		//查询当前班次
		Classes c = classesService.queryCurrentClasses();
		//根据设备站点查询加工信息
		for(DeviceSite ds : deviceSites) {
			double oee = computeOee4CurrentClass(c, ds,today);
			Object[] o = new Object[2];
			o[0] = ds.getName();
			o[1] = oee;
			values.add(o);
		} 
		//排序
		Object[] t = null;
		for (int i = 0; i < values.size() - 1; i++) {
			for (int j = 0; j < values.size() - 1 - i; j++) {
				Object[] v1 = values.get(j);
				Object[] v2 = values.get(j+1);
				if ((Double)v1[1] <(Double)v2[1]) {
					t = v1;
					values.set(j, v2);
					values.set(j+1, t);
				}
			}
		}

		for(int i=0;i<values.size()&&i<10;i++) {
			placing.add(placingList[i]);
			deviceSiteNames.add(values.get(i)[0]+"");
		}

		modelMap.addAttribute("placing", placing);
		modelMap.addAttribute("deviceSiteNames", deviceSiteNames);
		return modelMap;
	}

	/**
	 * 查询在参数状态页面显示的设备站点
	 * @return
	 */
	@RequestMapping("/queryDeviceSite4ParameterStatusShow.do")
	@ResponseBody
	public ModelMap queryDeviceSite4ParameterStatusShow() {
		DecimalFormat format = new DecimalFormat("#.00");

		ModelMap modelMap = new ModelMap();
		List<DeviceSite> deviceSites = deviceSiteService.queryDeviceSitesByShow(true);
		List<String> oees = new ArrayList<>();
		List<List<DeviceSiteParameterMapping>> list = new ArrayList<>();
		//良品率
		List<String> rtys = new ArrayList<>();
		//查询当前班次
		Classes c = classesService.queryCurrentClasses();
		if(c==null) {
			modelMap.addAttribute("error", "不存在当前班次信息!");
			return modelMap;
		}
		Date now = new Date();
		//根据设备站点查询加工信息
		for(DeviceSite ds : deviceSites) {
			//查询设备站点关联的参数
			list.add(deviceSiteParameterMappingService.queryByDeviceSiteId(ds.getId()));

			//查询当前班，当前设备的生产数量，总标准节拍(加工数量*标准节拍),总短停机时间(即时节拍-标准节拍的和)
			Object[] countAndSumOfStandardBeatAndShortHalt = processRecordService.queryCountAndSumOfStandardBeatAndSumOfShortHalt4CurrentClass(c, ds.getId(),now);
			//总生产数
			double count = 0;
			if(countAndSumOfStandardBeatAndShortHalt!=null && countAndSumOfStandardBeatAndShortHalt.length>0) {
				count = (int)countAndSumOfStandardBeatAndShortHalt[0];
			}
			//查询不合格品数
			int ngCount = ngRecordService.queryNgCount4Class(c, ds.getId(),now);
			//合格品数量
			int notNgCount = (int) (count - ngCount);
			if(count == 0) {
				rtys.add("100");
			}else {
				rtys.add(format.format(notNgCount*1.0/count*100));
			}
			double oee = computeOee4CurrentClass(c,ds, now);
			oees.add(format.format(oee*100));
		}
		modelMap.addAttribute("deviceSites", deviceSites);
		modelMap.addAttribute("parameters", list);
		modelMap.addAttribute("oees", oees);
		modelMap.addAttribute("rtys", rtys);
		return modelMap;
	}
} 
