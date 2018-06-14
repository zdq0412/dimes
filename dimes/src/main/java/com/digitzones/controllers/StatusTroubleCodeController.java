package com.digitzones.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Pager;
import com.digitzones.model.StatusTroubleCode;
import com.digitzones.service.IStatusTroubleCodeService;
/**
 * 部门管理控制器
 * @author zdq
 * 2018年6月7日
 */
@Controller
@RequestMapping("/statusTroubleCode")
public class StatusTroubleCodeController {
	private IStatusTroubleCodeService statusTroubleCodeService;
	@Autowired
	public void setStatusTroubleCodeService(IStatusTroubleCodeService statusTroubleCodeService) {
		this.statusTroubleCodeService = statusTroubleCodeService;
	}

	/**
	 * 根据工序id查询状态故障代码 
	 * @param processId
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping("/queryStatusTroubleCodesByProcessId.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ModelMap queryStatusTroubleCodesByProcessId(Long processId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		String hql = "select stc from StatusTroubleCode stc where stc.processes.id=?0";
		Pager<StatusTroubleCode> pager = statusTroubleCodeService.queryObjs(hql, page, rows, new Object[] {processId});
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}

	/**
	 * 添加状态故障代码
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/addStatusTroubleCode.do")
	@ResponseBody
	public ModelMap addStatusTroubleCode(StatusTroubleCode statusTroubleCode) {
		ModelMap modelMap = new ModelMap();
		StatusTroubleCode statusTroubleCode4Code = statusTroubleCodeService.queryByProperty("code", statusTroubleCode.getCode());
		if(statusTroubleCode4Code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "状态故障编码已被使用");
		}else {
			statusTroubleCodeService.addObj(statusTroubleCode);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "添加成功!");
		}
		return modelMap;
	}

	/**
	 * 根据id查询状态故障代码
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryStatusTroubleCodeById.do")
	@ResponseBody
	public StatusTroubleCode queryStatusTroubleCodeById(Long id) {
		StatusTroubleCode statusTroubleCode = statusTroubleCodeService.queryObjById(id);
		return statusTroubleCode;
	}

	/**
	 * 更新状态故障信息
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/updateStatusTroubleCode.do")
	@ResponseBody
	public ModelMap updateStatusTroubleCode(StatusTroubleCode statusTroubleCode) {
		ModelMap modelMap = new ModelMap();
		statusTroubleCodeService.updateObj(statusTroubleCode);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "编辑成功!");
		return modelMap;
	}
	/**
	 * 根据id删除参数信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteStatusTroubleCode.do")
	@ResponseBody
	public ModelMap deleteStatusTroubleCode(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		statusTroubleCodeService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
} 
