package com.digitzones.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Device;
import com.digitzones.model.Pager;
import com.digitzones.model.ProductionUnit;
import com.digitzones.service.IDeviceService;
import com.digitzones.service.IProductionUnitService;
/**
 * 设备管理控制器
 * @author zdq
 * 2018年6月7日
 */
@Controller
@RequestMapping("/device")
public class DeviceController {
	private IDeviceService deviceService;
	private IProductionUnitService productionUnitService;
	@Autowired
	public void setProductionUnitService(IProductionUnitService productionUnitService) {
		this.productionUnitService = productionUnitService;
	}

	@Autowired
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	/**
	 * 查找不在生产单元的设备
	 * @return
	 */
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
	/**
	 * 更新设备的生产单元信息
	 * @param productionUnitId 生产单元ID
	 * @param deviceIds 设备id字符串，形式如[11,22,33]
	 * @return
	 */
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
	/**
	 * 将设备的生产单元置为null
	 * @param productionUnitId
	 * @return
	 */
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

	/**
	 * 停用该设备
	 * @param id
	 * @return
	 */
	@RequestMapping("/disabledDevice.do")
	@ResponseBody
	public ModelMap disabledDevice(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Device d = deviceService.queryObjById(Long.valueOf(id));
		d.setDisabled(true);

		deviceService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}

	/**
	 * 分页查询生产单元中的设备
	 * @param pid
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryDevicesByProductionUnitId.do")
	@ResponseBody
	public ModelMap queryDevicesByProductionUnitId(@RequestParam(value="productionUnitId",required=false)Long productionUnitId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<Device> pager = null;
		if(productionUnitId==null) {
			pager = deviceService.queryObjs("select d from Device d inner join d.productionUnit p ", page, rows, new Object[] {});
		}else if(productionUnitId==-1) {
			pager = deviceService.queryObjs("select d from Device d where d.productionUnit is null", page, rows, new Object[] {});
		}
		else {
			pager = deviceService.queryObjs("select d from Device d inner join d.productionUnit p  where p.id=?0", page, rows, new Object[] {productionUnitId});
		}

		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pager.getData());
		mm.addAttribute("total", pager.getTotalCount());
		mm.addAttribute("code", "0");
		mm.addAttribute("msg", "");
		return mm;
	}

	/**
	 * 在生产单元中添加设备
	 * @param department
	 * @return
	 */
	@RequestMapping("/addDevice.do")
	@ResponseBody
	public ModelMap addDevice(Device device) {
		ModelMap modelMap = new ModelMap();
		Device device4Code = deviceService.queryByProperty("code", device.getCode());
		if(device4Code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "设备编码已被使用");
		}else {
			deviceService.addObj(device);
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
	@RequestMapping("/queryDeviceById.do")
	@ResponseBody
	public Device queryDeviceById(Long id) {
		Device device = deviceService.queryObjById(id);
		return device;
	}
	/**
	 * 更新部门
	 * @param department
	 * @return
	 */
	@RequestMapping("/updateDevice.do")
	@ResponseBody
	public ModelMap updateDevice(Device device) {
		ModelMap modelMap = new ModelMap();
		deviceService.updateObj(device);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "编辑成功!");
		return modelMap;
	}

	/**
	 * 根据id删除设备
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteDevice.do")
	@ResponseBody
	public ModelMap deleteDevice(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		deviceService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
} 
