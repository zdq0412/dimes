package com.digitzones.controllers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.ClassType;
import com.digitzones.model.Classes;
import com.digitzones.model.ClassesDeviceMapping;
import com.digitzones.model.Device;
import com.digitzones.model.DeviceSite;
import com.digitzones.model.Pager;
import com.digitzones.service.IClassesDeviceMappingService;
/**
 * 班次管理控制器
 * @author zdq
 * 2018年6月7日
 */
import com.digitzones.service.IClassesService;
import com.digitzones.service.IClassesTypeService;
import com.digitzones.service.IDeviceService;
import com.digitzones.vo.ClassesVO;
@Controller
@RequestMapping("/classes")
public class ClassesController {
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	private IClassesService classesService;
	private IClassesTypeService classesTypeService;
	private IDeviceService deviceService;
	
	private IClassesDeviceMappingService classesDeviceMappingService;
	
	@Autowired
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	@Autowired
	public void setClassesDeviceMappingService(IClassesDeviceMappingService classesDeviceMappingService) {
		this.classesDeviceMappingService = classesDeviceMappingService;
	}
	@Autowired
	public void setClassesService(IClassesService classesService) {
		this.classesService = classesService;
	}
	@Autowired
	public void setClassesTypeService(IClassesTypeService classesTypeService) {
		this.classesTypeService = classesTypeService;
	}

	/**
	 * 查询班次信息
	 * @return
	 */
	@RequestMapping("/queryClasses.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ModelMap queryClasses(@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		Pager<Classes> pager = classesService.queryObjs("from Classes c ", page, rows, new Object[] {});
		
		List<ClassesVO> vos = new ArrayList<>();
		for(Classes c : pager.getData()) {
			vos.add(model2VO(c));
		}
		modelMap.addAttribute("rows", vos);
		modelMap.addAttribute("total",pager.getTotalCount());
		return modelMap;
	}
	/**
	 * 添加班次信息
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/addClasses.do")
	@ResponseBody
	public ModelMap addClasses(Classes classes) {
		ModelMap modelMap = new ModelMap();
		//查看班次类型是否 存在，如果存在，则直接添加到班次中；如果不存在，则先保存班次类型
		ClassType classType = classes.getClassType();
		if(classType!=null) {
			ClassType ct = classesTypeService.queryByProperty("name", classType.getName());
			if(ct==null) {
				Long id = (Long) classesTypeService.addObj(classType);
				ct = classesTypeService.queryObjById(id);
				ct.setId(id);
			}
			classes.setClassType(ct);
			classes.setClassTypeName(classType.getName());
			classes.setClassTypeCode(ct.getCode());
		}

		Classes c4code = classesService.queryByProperty("code", classes.getCode());
		if(c4code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "班次编码已被使用");
		}else  {
			Classes c4name = classesService.queryByProperty("name", classes.getName());
			if(c4name!=null) {
				modelMap.addAttribute("success", false);
				modelMap.addAttribute("msg", "班次名称已被使用");
			}else {
				classesService.addObj(classes);
				modelMap.addAttribute("success", true);
				modelMap.addAttribute("msg", "添加成功!");
			}
		}
		return modelMap;
	}
	/**
	 * 根据id查询班次信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryClassById.do")
	@ResponseBody
	public ClassesVO queryClassById(Long id) {
		Classes c =  classesService.queryObjById(id);
		return model2VO(c);
	}
	
	private ClassesVO model2VO(Classes c) {
		ClassesVO vo = new ClassesVO();
		vo.setId(c.getId());
		vo.setClassType(c.getClassType());
		vo.setClassTypeCode(c.getClassTypeCode());
		vo.setClassTypeName(c.getClassTypeName());
		vo.setCode(c.getCode());
		vo.setName(c.getName());
		vo.setDisabled(c.getDisabled());
		vo.setStartTime(sdf.format(c.getStartTime()));
		vo.setEndTime(sdf.format(c.getEndTime()));
		vo.setNote(c.getNote());
		return vo;
	}

	/**
	 * 添加班次信息
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/updateClasses.do")
	@ResponseBody
	public ModelMap updateClasses(Classes classes) {
		ModelMap modelMap = new ModelMap();
		//查看班次类型是否 存在，如果存在，则直接添加到班次中；如果不存在，则先保存班次类型
		ClassType classType = classes.getClassType();
		if(classType!=null) {
			ClassType ct = classesTypeService.queryByProperty("name", classType.getName());
			if(ct==null) {
				Long id = (Long) classesTypeService.addObj(classType);
				ct = classesTypeService.queryObjById(id);
				ct.setId(id);
			}
			classes.setClassType(ct);
			classes.setClassTypeName(classType.getName());
			classes.setClassTypeCode(ct.getCode());
		}

		Classes c4name = classesService.queryByProperty("name", classes.getName());
		if(c4name!=null && !c4name.getId().equals(classes.getId())) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "班次名称已被使用");
		}else {
			classesService.updateObj(classes);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "更新成功!");
		}
		return modelMap;
	}
	
	/**
	 * 根据id删除班次
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteClasses.do")
	@ResponseBody
	public ModelMap deleteClasses(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		classesService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 停用该班次
	 * @param id
	 * @return
	 */
	@RequestMapping("/disabledClasses.do")
	@ResponseBody
	public ModelMap disabledClasses(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Classes d = classesService.queryObjById(Long.valueOf(id));
		d.setDisabled(true);

		classesService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}
	/**
	 * 根据班次id查询排班的设备
	 * @param classesId
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryDevicesByClassesId.do")
	@ResponseBody
	public ModelMap queryDevicesByClassesId(Long classesId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		String hql = "select ds from DeviceSite ds left join ds.device d inner join d.classesDevice cd inner join cd.classes c  where c.id=?0";
		Pager<DeviceSite> pager = classesService.queryObjs(hql, page, rows, new Object[] {classesId});
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}
	/**
	 * 根据班次id查询非当前班次的设备
	 * @param classesId
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryOtherDevices.do")
	@ResponseBody
	public ModelMap queryOtherDevices(Long classesId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		String hql = "select d from Device d where d.id not in (select cdm.device.id from ClassesDeviceMapping cdm"
				+ " ) or d.id in (select cdm.device.id from ClassesDeviceMapping cdm where cdm.classes.id!=?0)";
		Pager<Device> pager = classesService.queryObjs(hql, page, rows, new Object[] {classesId});
		ModelMap modelMap = new ModelMap();
		
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}
	/**
	 * 向班次中添加设备
	 * @param classesId
	 * @param deviceIds
	 * @return
	 */
	@RequestMapping("/addDevice4Classes.do")
	@ResponseBody
	public ModelMap addDevice4Classes(Long classesId,String deviceIds) {
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
				Device device = new Device();
				device.setId(Long.valueOf(idArray[i]));
				Classes c = new Classes();
				c.setId(classesId);
				
				ClassesDeviceMapping cdm = new ClassesDeviceMapping();
				cdm.setClasses(c);
				cdm.setDevice(device);
				
				classesDeviceMappingService.addObj(cdm);
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
	 * 删除班次和设备的关联
	 * @param cdm
	 * @return
	 */
	@RequestMapping("/deleteDeviceFromClasses.do")
	@ResponseBody
	public ModelMap deleteDeviceFromClasses(Long classesId,String deviceSiteId) {
		if(deviceSiteId.contains("'")) {
			deviceSiteId = deviceSiteId.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Device device = deviceService.queryDeviceByDeviceSiteId(Long.valueOf(deviceSiteId));
		if(device!=null) {
			classesDeviceMappingService.deleteByClassesIdAndDeviceId(classesId, device.getId());
			modelMap.addAttribute("message", "删除成功！");
			modelMap.addAttribute("statusCode", 200);
			modelMap.addAttribute("title", "操作提示");
		}
		return modelMap;
	}
} 
