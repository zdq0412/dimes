package com.digitzones.controllers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 装备管理控制器
 * @author zdq
 * 2018年6月7日
 */
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Equipment;
import com.digitzones.model.Pager;
import com.digitzones.service.IEquipmentService;
@Controller
@RequestMapping("/equipment")
public class EquipmentController {
	private IEquipmentService equipmentService;
	@Autowired
	public void setEquipmentService(IEquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}
	/**
	 * 分页查询参数信息
	 * @param pid
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryEquipmentsByEquipmentTypeId.do")
	@ResponseBody 
	public ModelMap queryEquipmentsByEquipmentTypeId(@RequestParam(value="equipmentTypeId",required=false)Long equipmentTypeId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<Object[]> pager = null;
		pager = equipmentService.queryObjs("select p from Equipment p inner join p.equipmentType pt  where pt.id=?0", page, rows, new Object[] {equipmentTypeId});
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pager.getData());
		mm.addAttribute("total", pager.getTotalCount());
		mm.addAttribute("code", "0");
		mm.addAttribute("msg", "");
		return mm;
	}
	/**
	 * 添加参数
	 * @param equipment
	 * @return
	 */
	@RequestMapping("/addEquipment.do")
	@ResponseBody
	public ModelMap addEquipment(Equipment equipment) {
		ModelMap modelMap = new ModelMap();
		Equipment equipment4Code = equipmentService.queryByProperty("code", equipment.getCode());
		if(equipment4Code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "装备编码已被使用");
		}else {
			equipmentService.addObj(equipment);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "添加成功!");
		}
		return modelMap;
	}
	@RequestMapping("/upload.do")
	@ResponseBody
	public ModelMap upload(Part file,HttpServletRequest request) {
		String dir = request.getServletContext().getRealPath("/")+"console/equipmentImgs/";
		String realName = file.getSubmittedFileName();
		ModelMap modelMap = new ModelMap();
		String fileName = new Date().getTime()+ realName.substring(realName.lastIndexOf("."));
		InputStream is;
		try {
			is = file.getInputStream();
			File out = new File(dir,fileName);
			FileCopyUtils.copy(is, new FileOutputStream(out));
			modelMap.addAttribute("statusCode", 200);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "文件上传成功！");
			modelMap.addAttribute("filePath", "console/equipmentImgs/" + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return modelMap;
	}
	/**
	 * 根据id查询参数
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryEquipmentById.do")
	@ResponseBody
	public Equipment queryEquipmentById(Long id) {
		Equipment equipment = equipmentService.queryObjById(id);
		return equipment;
	}
	/**
	 * 更新参数信息
	 * @param equipment
	 * @return
	 */
	@RequestMapping("/updateEquipment.do")
	@ResponseBody
	public ModelMap updateEquipment(Equipment equipment) {
		ModelMap modelMap = new ModelMap();
		equipmentService.updateObj(equipment);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "编辑成功!");
		return modelMap;
	}
	/**
	 * 根据id删除参数信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteEquipment.do")
	@ResponseBody
	public ModelMap deleteEquipment(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		equipmentService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 停用该参数
	 * @param id
	 * @return
	 */
	@RequestMapping("/disabledEquipment.do")
	@ResponseBody
	public ModelMap disabledEquipment(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Equipment d = equipmentService.queryObjById(Long.valueOf(id));
		d.setDisabled(true);

		equipmentService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}
	/**
	 * 根据工序id查询参数 信息
	 * @param processId
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping("/queryEquipmentByProcessId.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ModelMap queryEquipmentByProcessId(Long processId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		String hql = "select ds from ProcessesEquipmentMapping pdm inner join  pdm.equipments ds  inner join pdm.processes p where p.id=?0";
		Pager<Equipment> pager = equipmentService.queryObjs(hql, page, rows, new Object[] {processId});
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}
	/**
	 * 查询所有的装备
	 * @return
	 */
	@RequestMapping("/queryAllEquipments.do")
	@ResponseBody
	public List<Equipment> queryAllEquipments(){
		return equipmentService.queryAllEquipments();
	}

} 
