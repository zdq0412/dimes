package com.digitzones.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Device;
import com.digitzones.model.DeviceSite;
import com.digitzones.model.Pager;
import com.digitzones.service.IDeviceSiteService;
/**
 * 设备站点管理控制器
 * @author zdq
 * 2018年6月7日
 */
@Controller
@RequestMapping("/deviceSite")
public class DeviceSiteController {
	private IDeviceSiteService deviceSiteService;
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
	/*
	*//**
	 * 查找不在生产单元的设备
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryIdleDevices.do")
	@ResponseBody
	public ModelMap queryIdleDevices(@RequestParam(defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page){
		Pager<Device> pager = deviceService.queryObjs("from Device d where d.productionUnit is null", page, rows, new Object[] {});
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}
	*//**
	 * 更新设备的生产单元信息
	 * @param productionUnitId 生产单元ID
	 * @param deviceIds 设备id字符串，形式如[11,22,33]
	 * @return
	 *//*
	@RequestMapping("/updateDeviceProductionUnit.do")
	@ResponseBody
	public ModelMap updateDeviceProductionUnit(Long productionUnitId,String deviceIds) {
		ModelMap modelMap = new ModelMap();
		if(deviceIds!=null) {
			if(deviceIds.contains("[")) {
				deviceIds = deviceIds.replace("[", "");
			}
			if(deviceIds.contains("]")) {
				deviceIds = deviceIds.replace("]", "");
			}

			String[] idArray = deviceIds.split(",");
			for(int i = 0;i<idArray.length;i++) {
				Device device = deviceService.queryObjById(Long.valueOf(idArray[i]));
				ProductionUnit pu = productionUnitService.queryObjById(productionUnitId);

				device.setProductionUnit(pu);

				deviceService.updateObj(device);
				modelMap.addAttribute("success",true);
				modelMap.addAttribute("msg","操作完成!");
			}
		}else {
			modelMap.addAttribute("success",false);
			modelMap.addAttribute("msg","操作失败!");
		}

		return modelMap;
	}
	*//**
	 * 将设备的生产单元置为null
	 * @param productionUnitId
	 * @return
	 *//*
	@RequestMapping("/updateDeviceProductionUnitNull.do")
	@ResponseBody
	public ModelMap updateDeviceProductionUnitNull(String deviceId) {
		if(deviceId!=null && deviceId.contains("'")) {
			deviceId = deviceId.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Device device = deviceService.queryObjById(Long.valueOf(deviceId));
		device.setProductionUnit(null);
		deviceService.updateObj(device);
		modelMap.addAttribute("message", "操作完成!");
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		return modelMap;
	}

	
	*/

	
} 
