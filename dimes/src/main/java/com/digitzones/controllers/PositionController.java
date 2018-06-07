package com.digitzones.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Pager;
import com.digitzones.model.Position;
/**
 * 部门管理控制器
 * @author zdq
 * 2018年6月7日
 */
import com.digitzones.service.IPositionService;
@Controller
@RequestMapping("/position")
public class PositionController {
	private IPositionService positionService;
	@Autowired
	public void setPositionService(IPositionService positionService) {
		this.positionService = positionService;
	}
/*	@RequestMapping("/queryPositionsByDepartmentId.do")
	@ResponseBody
	public List<Position> queryPositionsByDepartmentId(@RequestParam("deptId") Long deptId){
		List<Position> list = positionService.queryPositionByDepartmentId(deptId);
		return list;
	}*/
	
	/**
	 * 分页查询职位
	 * @param pid
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping("/queryPositionsByDepartmentId.do")
	@ResponseBody
	public ModelMap queryPositionsByDepartmentId(@RequestParam("deptId")Long deptId,@RequestParam(defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<Position> pager = positionService.queryObjs("select p from Position p inner join p.department d where d.id=?0", page, rows,new Object[] {deptId});
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pager.getData());
		mm.addAttribute("total", pager.getTotalCount());
		return mm;
	}
} 
