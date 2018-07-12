package com.digitzones.controllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 人员技能控制器
 * @author zdq
 * 2018年7月12日
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.service.IProcessSkillLevelService;
@Controller
@RequestMapping("/employeeSkill")
public class EmployeeSkillController {
	private IProcessSkillLevelService processSkillLevelService;
	@Autowired
	public void setProcessSkillLevelService(IProcessSkillLevelService processSkillLevelService) {
		this.processSkillLevelService = processSkillLevelService;
	}
	/**
	 * 人员技能：工厂级
	 * @return
	 */
	@RequestMapping("/queryEmployeeSkill.do")
	@ResponseBody
	public ModelMap queryEmployeeSkill() {
		ModelMap modelMap = new ModelMap();
		
		List<Map<String,Object>> inner = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> outer = new ArrayList<Map<String,Object>>();
		List<String> names = new ArrayList<>();
		
		//查询所有的技能等级，技能编码及名称，每个等级下的工序数
		List<Object[]> skillLevelList = processSkillLevelService.queryCount4SkillLevel();
		
		for(int i = 0;i< skillLevelList.size();i++) {
			Object[] oa = skillLevelList.get(i);
			Map<String,Object> map = new HashMap<>();
			map.put("name", oa[1]);
			map.put("value", oa[2]);
			if(i == 0) {
				map.put("selected",true);
			}
			inner.add(map);
			
			names.add(oa[1]+"");
		}
		
		//每道工序下的等级数
		List<Object[]> processList = processSkillLevelService.queryCount4ProcessBySkillLevelCode();
		for(Object[] oa : processList) {
			Map<String,Object> map = new HashMap<>();
			map.put("name", oa[0]);
			map.put("value", oa[2]);
			
			outer.add(map);
			
			names.add(oa[0]+"");
		}
		
		modelMap.addAttribute("inner", inner);
		modelMap.addAttribute("outer", outer);
		modelMap.addAttribute("names", names);
		return modelMap;
	}
} 
