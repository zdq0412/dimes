package com.digitzones.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.PressLight;
import com.digitzones.model.Pager;
import com.digitzones.service.IPressLightService;
/**
 * 按灯管理控制器
 * @author zdq
 * 2018年6月7日
 */
@Controller
@RequestMapping("/pressLight")
public class PressLightController {
	private IPressLightService pressLightService;
	@Autowired
	public void setPressLightService(IPressLightService pressLightService) {
		this.pressLightService = pressLightService;
	}

	/**
	 * 根据类别id查询按灯原因
	 * @param typeId
	 * @return
	 */
	@RequestMapping("/queryAllPressLightByTypeId.do")
	@ResponseBody
	public List<PressLight> queryAllPressLightByTypeId(Long typeId){
		List<PressLight> list = pressLightService.queryAllPressLightByTypeId(typeId);
		return list;
	}
	
	/**
	 * 分页查询按灯信息
	 * @param pid
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryPressLightsByPressLightTypeId.do")
	@ResponseBody
	public ModelMap queryPressLightsByPressLightTypeId(@RequestParam(value="pressLightTypeId",required=false)Long parameterTypeId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<Object[]> pager = null;
		if(parameterTypeId==null) {
			pager = pressLightService.queryObjs("select p from PressLight p inner join p.pressLightType pt ", page, rows, new Object[] {});
		}else {
			pager = pressLightService.queryObjs("select p from PressLight p inner join p.pressLightType pt  where pt.id=?0", page, rows, new Object[] {parameterTypeId});
		}
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pager.getData());
		mm.addAttribute("total", pager.getTotalCount());
		mm.addAttribute("code", "0");
		mm.addAttribute("msg", "");
		return mm;
	}

	/**
	 * 添加按灯
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/addPressLight.do")
	@ResponseBody
	public ModelMap addPressLight(PressLight parameter) {
		ModelMap modelMap = new ModelMap();
		PressLight parameter4Code = pressLightService.queryByProperty("code", parameter.getCode());
		if(parameter4Code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "按灯编码已被使用");
		}else {
			pressLightService.addObj(parameter);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "添加成功!");
		}
		return modelMap;
	}
	/**
	 * 根据id查询按灯
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryPressLightById.do")
	@ResponseBody
	public PressLight queryPressLightById(Long id) {
		PressLight parameter = pressLightService.queryObjById(id);
		return parameter;
	}
	/**
	 * 更新按灯
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/updatePressLight.do")
	@ResponseBody
	public ModelMap updatePressLight(PressLight parameter) {
		ModelMap modelMap = new ModelMap();
		pressLightService.updateObj(parameter);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "编辑成功!");
		return modelMap;
	}
	/**
	 * 根据id删除按灯
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletePressLight.do")
	@ResponseBody
	public ModelMap deletePressLight(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		pressLightService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
} 
