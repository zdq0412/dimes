package com.digitzones.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 参数类型管理控制器
 * @author zdq
 * 2018年6月7日
 */
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Pager;
import com.digitzones.model.Parameters;
import com.digitzones.service.IParameterService;
@Controller
@RequestMapping("/parameter")
public class ParameterController {
	private IParameterService parameterService;
	@Autowired
	public void setParameterService(IParameterService parameterService) {
		this.parameterService = parameterService;
	}
	/**
	 * 分页查询参数信息
	 * @param pid
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryParametersByParameterTypeId.do")
	@ResponseBody
	public ModelMap queryParametersByParameterTypeId(@RequestParam(value="parameterTypeId",required=false)Long parameterTypeId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<Object[]> pager = null;
		if(parameterTypeId==null) {
			pager = parameterService.queryObjs("select p from Parameters p inner join p.parameterType pt ", page, rows, new Object[] {});
		}else {
			pager = parameterService.queryObjs("select p from Parameters p inner join p.parameterType pt  where pt.id=?0", page, rows, new Object[] {parameterTypeId});
		}
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pager.getData());
		mm.addAttribute("total", pager.getTotalCount());
		mm.addAttribute("code", "0");
		mm.addAttribute("msg", "");
		return mm;
	}

	/**
	 * 添加参数
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/addParameter.do")
	@ResponseBody
	public ModelMap addParameter(Parameters parameter) {
		ModelMap modelMap = new ModelMap();
		Parameters parameter4Code = parameterService.queryByProperty("code", parameter.getCode());
		if(parameter4Code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "参数编码已被使用");
		}else {
			parameterService.addObj(parameter);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "添加成功!");
		}
		return modelMap;
	}
	/**
	 * 根据id查询参数
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryParameterById.do")
	@ResponseBody
	public Parameters queryParameterById(Long id) {
		Parameters parameter = parameterService.queryObjById(id);
		return parameter;
	}
	/**
	 * 更新参数信息
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/updateParameter.do")
	@ResponseBody
	public ModelMap updateParameter(Parameters parameter) {
		ModelMap modelMap = new ModelMap();
		Parameters pu = parameterService.queryByProperty("name", parameter.getName());
		if(pu!=null && !parameter.getId().equals(pu.getId())) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "参数名称已被使用");
		}else {
			parameterService.updateObj(parameter);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "编辑成功!");
		}
		return modelMap;
	}
	/**
	 * 根据id删除参数信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteParameter.do")
	@ResponseBody
	public ModelMap deleteParameter(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		parameterService.deleteObj(Long.valueOf(id));
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
	@RequestMapping("/disabledParameter.do")
	@ResponseBody
	public ModelMap disabledParameter(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Parameters d = parameterService.queryObjById(Long.valueOf(id));
		d.setDisabled(true);

		parameterService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}
} 
