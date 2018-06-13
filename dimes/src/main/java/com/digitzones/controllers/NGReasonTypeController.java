package com.digitzones.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 参数类型管理控制器
 * @author zdq
 * 2018年6月7日
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.NGReasonType;
import com.digitzones.model.Pager;
import com.digitzones.service.INGReasonTypeService;
import com.digitzones.vo.NGReasonTypeVO;
@Controller
@RequestMapping("/ngReasonType")
public class NGReasonTypeController {
	private INGReasonTypeService ngReasonTypeService;
	@Autowired
	public void setNgReasonTypeService(INGReasonTypeService ngReasonTypeService) {
		this.ngReasonTypeService = ngReasonTypeService;
	}
	/**
	 * 查询顶层不良原因类型
	 * @return
	 */
	@RequestMapping("/queryTopNgReasonTypes.do")
	@ResponseBody
	public List<NGReasonType> queryTopNgReasonTypes(){
		return ngReasonTypeService.queryTopNGReasonType();
	}
	/**
	 * 分页查询不良原因类型
	 * @param pid
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryNGReasonTypesByParentId.do")
	@ResponseBody
	public ModelMap queryNGReasonTypesByParentId(@RequestParam(value="pid",required=false)Long pid,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<Object[]> pager = null;
		if(pid==null) {
			pager = ngReasonTypeService.queryObjs("from NGReasonType pt inner join pt.parent p ", page, rows, new Object[] {});
		}else {
			pager = ngReasonTypeService.queryObjs("from NGReasonType pt inner join pt.parent p  where p.id=?0", page, rows, new Object[] {pid});
		}
		Pager<NGReasonTypeVO> pagerParameterTypeVO = new Pager<>();
		model2VO(pager, pagerParameterTypeVO);
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pagerParameterTypeVO.getData());
		mm.addAttribute("total", pager.getTotalCount());
		mm.addAttribute("code", "0");
		mm.addAttribute("msg", "");
		return mm;
	}

	private void model2VO(Pager<Object[]> pagerParameterType,Pager<NGReasonTypeVO> parameterTypeVO) {
		List<Object[]> parameterTypes = pagerParameterType.getData();
		List<NGReasonTypeVO> parameterTypeVOs = parameterTypeVO.getData();
		for(Object[] obj:parameterTypes) {
			NGReasonTypeVO vo = new NGReasonTypeVO();
			NGReasonType son = (NGReasonType) obj[0];
			NGReasonType parent = (NGReasonType) obj[1];

			vo.setId(son.getId());
			vo.setName(son.getName());
			parent.setChildren(null);
			vo.setParent(parent);
			vo.setCode(son.getCode());
			vo.setDisabled(son.getDisabled());
			vo.setNote(son.getNote());
			parameterTypeVOs.add(vo);
		}
	}
	/**
	 * 添加不良原因类别
	 * @param department
	 * @return
	 */
	@RequestMapping("/addNGReasonType.do")
	@ResponseBody
	public ModelMap addNGReasonType(NGReasonType ngReasonType) {
		ModelMap modelMap = new ModelMap();
		NGReasonType parameterType4Code = ngReasonTypeService.queryByProperty("code", ngReasonType.getCode());
		if(parameterType4Code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "不良原因类别编码已被使用");
		}else {
			NGReasonType	parameterType4Name = ngReasonTypeService.queryByProperty("name",ngReasonType.getName());
			if(parameterType4Name!=null) {
				modelMap.addAttribute("success", false);
				modelMap.addAttribute("msg", "不良原因类别名称已被使用");
			}else {
				ngReasonTypeService.addObj(ngReasonType);
				modelMap.addAttribute("success", true);
				modelMap.addAttribute("msg", "添加成功!");
			}
		}
		return modelMap;
	}
	/**
	 * 根据id查询不良原因类别
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryNGReasonTypeById.do")
	@ResponseBody
	public NGReasonType queryNGReasonTypeById(Long id) {
		NGReasonType NGReasonType = ngReasonTypeService.queryObjById(id);
		return NGReasonType;
	}
	/**
	 * 更新不良原因类别
	 * @param NGReasonType
	 * @return
	 */
	@RequestMapping("/updateNGReasonType.do")
	@ResponseBody
	public ModelMap updateNGReasonType(NGReasonType NGReasonType) {
		ModelMap modelMap = new ModelMap();
		NGReasonType pu = ngReasonTypeService.queryByProperty("name", NGReasonType.getName());
		if(pu!=null && !NGReasonType.getId().equals(pu.getId())) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "不良原因类别名称已被使用");
		}else {
			ngReasonTypeService.updateObj(NGReasonType);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "编辑成功!");
		}
		return modelMap;
	}
	/**
	 * 根据id删除不良原因类别
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteNGReasonType.do")
	@ResponseBody
	public ModelMap deleteNGReasonType(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Long count = ngReasonTypeService.queryCountOfSubNGReasonType(Long.valueOf(id));
		if(count>0) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("msg", "该类别下存在子类别，不允许删除!");
			modelMap.addAttribute("message", "该类别下存在子类别，不允许删除!");
		}else {
			ngReasonTypeService.deleteObj(Long.valueOf(id));
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("statusCode", 200);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "成功删除!");
		}
		return modelMap;
	}
	/**
	 * 停用该不良原因类别
	 * @param id
	 * @return
	 */
	@RequestMapping("/disabledNGReasonType.do")
	@ResponseBody
	public ModelMap disabledNGReasonType(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		NGReasonType d = ngReasonTypeService.queryObjById(Long.valueOf(id));
		d.setDisabled(true);
		
		ngReasonTypeService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}
} 
