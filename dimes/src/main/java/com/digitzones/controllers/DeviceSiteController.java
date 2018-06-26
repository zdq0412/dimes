package com.digitzones.controllers;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.constants.Constant;
import com.digitzones.model.Classes;
import com.digitzones.model.Device;
import com.digitzones.model.DeviceSite;
import com.digitzones.model.Pager;
import com.digitzones.model.WorkpieceProcessDeviceSiteMapping;
import com.digitzones.service.IClassesService;
import com.digitzones.service.IDeviceSiteService;
import com.digitzones.service.ILostTimeRecordService;
import com.digitzones.service.IPressLightRecordService;
import com.digitzones.service.IProcessRecordService;
import com.digitzones.service.IWorkpieceProcessDeviceSiteMappingService;
/**
 * 设备站点管理控制器
 * @author zdq
 * 2018年6月7日
 */
@Controller
@RequestMapping("/deviceSite")
public class DeviceSiteController {
	private IDeviceSiteService deviceSiteService;
	private IPressLightRecordService pressLightRecordService;
	private IProcessRecordService processRecordService;
	private IWorkpieceProcessDeviceSiteMappingService workpieceProcessDeviceSiteMappingService;
	private IClassesService classesService;
	private ILostTimeRecordService lostTimeRecordService;
	@Autowired
	public void setLostTimeRecordService(ILostTimeRecordService lostTimeRecordService) {
		this.lostTimeRecordService = lostTimeRecordService;
	}
	@Autowired
	public void setClassesService(IClassesService classesService) {
		this.classesService = classesService;
	}
	@Autowired
	public void setProcessRecordService(IProcessRecordService processRecordService) {
		this.processRecordService = processRecordService;
	}
	@Autowired
	public void setWorkpieceProcessDeviceSiteMappingService(
			IWorkpieceProcessDeviceSiteMappingService workpieceProcessDeviceSiteMappingService) {
		this.workpieceProcessDeviceSiteMappingService = workpieceProcessDeviceSiteMappingService;
	}
	@Autowired
	public void setPressLightRecordService(IPressLightRecordService pressLightRecordService) {
		this.pressLightRecordService = pressLightRecordService;
	}
	@Autowired
	public void setDeviceSiteService(IDeviceSiteService deviceSiteService) {
		this.deviceSiteService = deviceSiteService;
	}
	/**
	 * 分页查询生产单元中的设备
	 * @param pid
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryDeviceSitesByDeviceId.do")
	@ResponseBody
	public ModelMap queryDeviceSitesByDeviceId(@RequestParam(value="deviceId",required=false)Long deviceId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<Device> pager = null;
		if(deviceId==null) {
			pager = deviceSiteService.queryObjs("select ds from DeviceSite ds inner join ds.device d ", page, rows, new Object[] {});
		}else {
			pager = deviceSiteService.queryObjs("select ds from DeviceSite ds inner join ds.device d  where d.id=?0", page, rows, new Object[] {deviceId});
		}

		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pager.getData());
		mm.addAttribute("total", pager.getTotalCount());
		mm.addAttribute("code", "0");
		mm.addAttribute("msg", "");
		return mm;
	}

	/**
	 * 为设备添加设备站点
	 * @param deviceSite
	 * @return
	 */
	@RequestMapping("/addDeviceSite.do")
	@ResponseBody
	public ModelMap addDeviceSite(DeviceSite deviceSite) {
		ModelMap modelMap = new ModelMap();
		DeviceSite device4Code = deviceSiteService.queryByProperty("code", deviceSite.getCode());
		if(device4Code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "设备站点编码已被使用");
		}else {
			deviceSiteService.addObj(deviceSite);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "添加成功!");
		}
		return modelMap;
	}
	
	/**
	 * 根据id查询设备
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryDeviceSiteById.do")
	@ResponseBody
	public DeviceSite queryDeviceSiteById(Long id) {
		DeviceSite deviceSite = deviceSiteService.queryObjById(id);
		return deviceSite;
	}
	/**
	 * 更新部门
	 * @param department
	 * @return
	 */
	@RequestMapping("/updateDeviceSite.do")
	@ResponseBody
	public ModelMap updateDeviceSite(DeviceSite deviceSIte) {
		ModelMap modelMap = new ModelMap();
		deviceSiteService.updateObj(deviceSIte);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "编辑成功!");
		return modelMap;
	}
	
	/**
	 * 停用该设备站点
	 * @param id
	 * @return
	 */
	@RequestMapping("/disabledDeviceSite.do")
	@ResponseBody
	public ModelMap disabledDeviceSite(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		DeviceSite d = deviceSiteService.queryObjById(Long.valueOf(id));
		d.setDisabled(true);

		deviceSiteService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}

	/**
	 * 根据id删除设备站点
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteDeviceSite.do")
	@ResponseBody
	public ModelMap deleteDeviceSite(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		deviceSiteService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 根据工序id查询站点 信息
	 * @param processId
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping("/queryDeviceSitesByProcessId.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ModelMap queryDeviceSitesByProcessId(Long processId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		String hql = "select ds from ProcessDeviceSiteMapping pdm inner join  pdm.deviceSite ds  inner join pdm.processes p where p.id=?0";
		Pager<DeviceSite> pager = deviceSiteService.queryObjs(hql, page, rows, new Object[] {processId});
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}
	/**
	 * 根据工序id查询非当前工序下的站点信息
	 * @param processId
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping("/queryOtherDeviceSites.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ModelMap queryOtherDeviceSites(Long processId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		String hql = "select ds from DeviceSite ds where ds.id not in ("
				+ "select pdm.deviceSite.id from ProcessDeviceSiteMapping pdm) or ds.id in (select pdm_.deviceSite.id from ProcessDeviceSiteMapping pdm_ where pdm_.processes.id!=?0)";
		Pager<DeviceSite> pager = deviceSiteService.queryObjs(hql, page, rows, new Object[] {processId});
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}
	/**
	 * 查询站点的运行状态，工厂级--->运行状态
	 * @return
	 */
	@RequestMapping("/queryDeviceSiteRunningStatus.do")
	@ResponseBody
	public ModelMap queryDeviceSiteRunningStatus() {
		ModelMap modelMap = new ModelMap();
		//查询正在运行的站点数量
		Long runningCount = deviceSiteService.queryCountOfDeviceSiteByStatus(Constant.DeviceSite.RUNNING);
		//查询停机的站点数量
		Long haltCount = deviceSiteService.queryCountOfDeviceSiteByStatus(Constant.DeviceSite.HALT);
		//查询站点报警次数
		Long warnningCount = pressLightRecordService.queryCountByPressLightTime(new Date());
		modelMap.addAttribute("runningCount", runningCount);
		modelMap.addAttribute("haltCount", haltCount);
		modelMap.addAttribute("warnningCount", warnningCount);
		return modelMap;
	}
	/**
	 * 查询在参数状态页面显示的设备站点
	 * @return
	 */
	@RequestMapping("/queryDeviceSite4ParameterStatusShow.do")
	@ResponseBody
	public ModelMap queryDeviceSite4ParameterStatusShow() {
		ModelMap modelMap = new ModelMap();
		List<DeviceSite> deviceSites = deviceSiteService.queryDeviceSitesByShow(true);
		List<Double> oees = new ArrayList<>();
		//良品率
		List<Double> rtys = new ArrayList<>();
		//根据设备站点查询加工信息
		for(DeviceSite ds : deviceSites) {
			//根据设备站点id查询当天的加工数量
			Long count = processRecordService.queryCurrentDayCountByDeviceSiteId(ds.getId());
			//根据设备站点id查询当前非NG数量
			Long notNgCount = processRecordService.queryCountByDeviceSiteIdAndNotNg(ds.getId());
			rtys.add(notNgCount*1.0/count*100);
			//查询加工信息中的工序，工件，设备站点id,NG数量
			List<Long[]> idList = processRecordService.queryCountByDeviceSiteIdAndStatus(ds.getId(), Constant.ProcessRecord.NG);
			for(Long[] ids : idList) {
				Classes c = classesService.queryObjById(ids[3]);
				//根据工序,工件，设备站点查找标准节拍
				WorkpieceProcessDeviceSiteMapping wpdsm = workpieceProcessDeviceSiteMappingService.queryByWorkPieceIdAndProcessIdAndDeviceSiteId(ids[0], ids[1], ids[2]);
				Float processingBeat = wpdsm.getProcessingBeat();
				//oee公式:（总加工时间-损时时间-NG件数*标准节拍）/总加工时间 
				//总加工时间，公式：当前时间-班次的开始时间-损时时间-计划停机时间
				Date now = new Date();
				//查询损时时间(包括计划停机时间)
				Double lostTime = lostTimeRecordService.queryLostTimeByTime(c.getStartTime(), now); 
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(now);
				int totalMinutes = calendar.get(Calendar.HOUR)*60 + calendar.get(Calendar.MINUTE);
				double oee = (totalMinutes-lostTime-ids[4]*processingBeat)/totalMinutes;
				oees.add(oee*100);
			}
		}
		modelMap.addAttribute("deviceSites", deviceSites);
		modelMap.addAttribute("oees", oees);
		modelMap.addAttribute("rtys", rtys);
		return modelMap;
	}
} 
