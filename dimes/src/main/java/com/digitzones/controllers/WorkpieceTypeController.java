package com.digitzones.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Pager;
import com.digitzones.model.WorkpieceType;
import com.digitzones.service.IWorkpieceTypeService;
import com.digitzones.vo.WorkpieceTypeVO;
/**
 * 工件类别管理控制器
 * @author zdq
 * 2018年6月7日
 */
@Controller
@RequestMapping("/workpieceType")
public class WorkpieceTypeController {
	private IWorkpieceTypeService workpieceTypeService;
	@Autowired
	public void setWorkpieceTypeService(IWorkpieceTypeService workpieceTypeService) {
		this.workpieceTypeService = workpieceTypeService;
	}
	
	/**
	 * 查询工件类别信息
	 * @return
	 */
	@RequestMapping("/queryWorkpieceTypesByParentId.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ModelMap queryWorkpieceTypesByParentId(Long pid,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<Object[]> pager = null;
		if(pid==null) {
			pager = workpieceTypeService.queryObjs("from WorkpieceType d inner join d.parent p ", page, rows, new Object[] {});
		}else {
			pager = workpieceTypeService.queryObjs("from WorkpieceType d inner join d.parent p  where p.id=?0", page, rows, new Object[] {pid});
		}

		Pager<WorkpieceTypeVO> pagerDeptVO = new Pager<>();
		model2VO(pager, pagerDeptVO);
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pagerDeptVO.getData());
		mm.addAttribute("total", pager.getTotalCount());
		return mm;
	}
	

	private void model2VO(Pager<Object[]> pagerDept,Pager<WorkpieceTypeVO> pagerDeptVO) {
		List<Object[]> depts = pagerDept.getData();
		List<WorkpieceTypeVO> deptVOs = pagerDeptVO.getData();
		for(Object[] obj:depts) {
			WorkpieceTypeVO vo = new WorkpieceTypeVO();
			WorkpieceType son = (WorkpieceType) obj[0];
			WorkpieceType parent = (WorkpieceType) obj[1];

			vo.setId(son.getId());
			vo.setName(son.getName());
			parent.setChildren(null);
			vo.setParent(parent);
			vo.setCode(son.getCode());
			vo.setDisabled(son.getDisabled());
			vo.setNote(son.getNote());
			deptVOs.add(vo);
		}
	}
	/**
	 * 添加工件类别信息
	 * @param workpieceType
	 * @return
	 */
	@RequestMapping("/addWorkpieceType.do")
	@ResponseBody
	public ModelMap addWorkpieceType(WorkpieceType workpieceType) {
		ModelMap modelMap = new ModelMap();

		WorkpieceType c4code = workpieceTypeService.queryByProperty("code", workpieceType.getCode());
		if(c4code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "工序类别编码已被使用");
		}else  {
			WorkpieceType c4name = workpieceTypeService.queryByProperty("name", workpieceType.getName());
			if(c4name!=null) {
				modelMap.addAttribute("success", false);
				modelMap.addAttribute("msg", "工序类别名称已被使用");
			}else {
				workpieceTypeService.addObj(workpieceType);
				modelMap.addAttribute("success", true);
				modelMap.addAttribute("msg", "添加成功!");
			}
		}
		return modelMap;
	}
	/**
	 * 根据id查询工件类别
	 * @param workpieceType
	 * @return
	 */
	@RequestMapping("/queryWorkpieceTypeById.do")
	@ResponseBody
	public WorkpieceType queryWorkpieceTypeById(Long id) {
		return workpieceTypeService.queryObjById(id);
	}
	/**
	 * 更新工序类别信息
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/updateWorkpieceType.do")
	@ResponseBody
	public ModelMap updateWorkpieceType(WorkpieceType workpieceType) {
		ModelMap modelMap = new ModelMap();

		WorkpieceType c4name = workpieceTypeService.queryByProperty("name", workpieceType.getName());
		if(c4name!=null && !c4name.getId().equals(workpieceType.getId())) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "工序类别名称已被使用");
		}else {
			workpieceTypeService.updateObj(workpieceType);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "更新成功!");
		}
		return modelMap;
	}
	/**
	 * 根据id删除工序类别
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteWorkpieceType.do")
	@ResponseBody
	public ModelMap deleteWorkpieceType(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		
		long count = workpieceTypeService.queryCountOfSubWorkpieceType(Long.valueOf(id));
		if(count >0) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "操作失败：该类别下存在子类别");
		}else {
			workpieceTypeService.deleteObj(Long.valueOf(id));
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("statusCode", 200);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "成功删除!");
		}
		return modelMap;
	}
	/**
	 * 停用该工序类别
	 * @param id
	 * @return
	 */
	@RequestMapping("/disabledWorkpieceType.do")
	@ResponseBody
	public ModelMap disabledWorkpieceType(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		WorkpieceType d = workpieceTypeService.queryObjById(Long.valueOf(id));
		d.setDisabled(true);

		workpieceTypeService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}
	
	/**
	 * 查询顶层工序类别
	 * @return
	 */
	@RequestMapping("/queryTopWorkpieceTypes.do")
	@ResponseBody
	public List<WorkpieceType> queryTopWorkpieceTypes(){
		return workpieceTypeService.queryTopWorkpieceType();
	}
} 
