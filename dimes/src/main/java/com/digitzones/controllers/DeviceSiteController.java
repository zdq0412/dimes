package com.digitzones.controllers;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.constants.Constant;
import com.digitzones.model.Device;
import com.digitzones.model.DeviceSite;
import com.digitzones.model.Pager;
import com.digitzones.model.PressLightRecord;
import com.digitzones.service.IDeviceSiteService;
import com.digitzones.service.IPressLightRecordService;
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
	@Autowired
	public void setPressLightRecordService(IPressLightRecordService pressLightRecordService) {
		this.pressLightRecordService = pressLightRecordService;
	}
	@Autowired
	public void setDeviceSiteService(IDeviceSiteService deviceSiteService) {
		this.deviceSiteService = deviceSiteService;
	}
	/**
	 * 查询设备下的站点
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
		/*if(deviceId==null) {
			pager = deviceSiteService.queryObjs("select ds from DeviceSite ds inner join ds.device d ", page, rows, new Object[] {});
		}else {
			pager = deviceSiteService.queryObjs("select ds from DeviceSite ds inner join ds.device d  where d.id=?0", page, rows, new Object[] {deviceId});
		}*/
		pager = deviceSiteService.queryObjs("select ds from DeviceSite ds inner join ds.device d  where d.id=?0", page, rows, new Object[] {deviceId});

		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pager.getData());
		mm.addAttribute("total", pager.getTotalCount());
		mm.addAttribute("code", "0");
		mm.addAttribute("msg", "");
		return mm;
	}
	@RequestMapping("/queryAllDeviceSitesByDeviceId.do")
	@ResponseBody
	public List<DeviceSite> queryAllDeviceSitesByDeviceId(@RequestParam(value="deviceId",required=false)Long deviceId) {
		List<DeviceSite> list = deviceSiteService.queryAllDeviceSitesByDeviceId(deviceId);
		return list; 
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
		//查询站点代码是否被使用
		DeviceSite ds = deviceSiteService.queryByProperty("code", deviceSIte.getCode());
		if(ds!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "站点代码已被使用!");
			return modelMap;
		}
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
		
		Long deviceSiteId = Long.valueOf(id);
		
		//查找该设备站点下的损时记录数
		Integer lostTimeRecordCount = deviceSiteService.queryLostTimeCountByDeviceSiteId(deviceSiteId);
		if(lostTimeRecordCount>0) {
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "操作失败，该站点存在损时记录！");
			return modelMap;
		}
		//查找该设备站点下的按灯记录数
		Integer pressLightRecordCount = deviceSiteService.queryPressLightCountByDeviceSiteId(deviceSiteId);
		if(pressLightRecordCount>0) {
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "操作失败，该设备站点下存在按灯记录!");
			return modelMap;
		}
		//查找该设备站点下的关联的工序数
		Integer processCount = deviceSiteService.queryProcessDeviceSiteMappingCountByDeviceSiteId(deviceSiteId);
		if(processCount>0) {
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "操作失败，该站点存在与之关联的工序！");
			return modelMap;
		}
		deviceSiteService.deleteObj(deviceSiteId);
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
				+ "select pdm.deviceSite.id from ProcessDeviceSiteMapping pdm where pdm.processes.id=?0 and pdm.deviceSite.disabled=?1)";
		Pager<DeviceSite> pager = deviceSiteService.queryObjs(hql, page, rows, new Object[] {processId,false});
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
		//查询站点报警信息
		List<PressLightRecord> records= pressLightRecordService.queryPressLightRecordsByTime(new Date());
		modelMap.addAttribute("runningCount", runningCount);
		modelMap.addAttribute("haltCount", haltCount);
		modelMap.addAttribute("warnningCount", warnningCount);
		modelMap.addAttribute("records", records);
		return modelMap;
	}
} 
