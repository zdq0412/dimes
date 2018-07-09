package com.digitzones.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.NGReason;
import com.digitzones.model.Pager;
import com.digitzones.service.INGReasonService;
/**
 * 不良原因管理控制器
 * @author zdq
 * 2018年6月7日
 */
@Controller
@RequestMapping("/ngReason")
public class NGReasonController {
	private INGReasonService ngReasonService;
	@Autowired
	public void setNgReasonService(INGReasonService ngReasonService) {
		this.ngReasonService = ngReasonService;
	}

	/**
	 * 分页查询不良原因信息
	 * @param pid
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryNGReasonsByNGReasonTypeId.do")
	@ResponseBody
	public ModelMap queryNGReasonsByNGReasonTypeId(@RequestParam(value="ngReasonTypeId",required=false)Long parameterTypeId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<Object[]> pager = null;
		pager = ngReasonService.queryObjs("select p from NGReason p inner join p.ngReasonType pt  where pt.id=?0", page, rows, new Object[] {parameterTypeId});
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pager.getData());
		mm.addAttribute("total", pager.getTotalCount());
		mm.addAttribute("code", "0");
		mm.addAttribute("msg", "");
		return mm;
	}

	/**
	 * 添加不良原因
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/addNGReason.do")
	@ResponseBody
	public ModelMap addNGReason(NGReason parameter) {
		ModelMap modelMap = new ModelMap();
		NGReason parameter4Code = ngReasonService.queryByProperty("ngCode", parameter.getNgCode());
		if(parameter4Code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "不良原因编码已被使用");
		}else {
			ngReasonService.addObj(parameter);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "添加成功!");
		}
		return modelMap;
	}
	/**
	 * 根据id查询不良原因
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryNGReasonById.do")
	@ResponseBody
	public NGReason queryNGReasonById(Long id) {
		NGReason parameter = ngReasonService.queryObjById(id);
		return parameter;
	}
	/**
	 * 更新不良原因
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/updateNGReason.do")
	@ResponseBody
	public ModelMap updateNGReason(NGReason parameter) {
		ModelMap modelMap = new ModelMap();
		ngReasonService.updateObj(parameter);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "编辑成功!");
		return modelMap;
	}
	/**
	 * 根据id删除不良原因
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteNGReason.do")
	@ResponseBody
	public ModelMap deleteNGReason(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		ngReasonService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 根据不良原因类型查找不良原因
	 * @param ngReasonTypeId
	 * @return
	 */
	@RequestMapping("/queryNGReasonsByNGReasonTypeIdNoPager.do")
	@ResponseBody
	public List<NGReason> queryNGReasonsByNGReasonTypeIdNoPager(Long ngReasonTypeId){
		return ngReasonService.queryNGReasonsByNGReasonTypeId(ngReasonTypeId);
	}
} 
