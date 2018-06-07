package com.digitzones.controllers;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Department;
import com.digitzones.model.Pager;
import com.digitzones.service.IDepartmentService;
import com.digitzones.vo.DepartmentVO;
/**
 * 部门管理控制器
 * @author zdq
 * 2018年6月7日
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
	private IDepartmentService departmentService;
	@Autowired
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	/**
	 * 查询公司名称
	 * @return
	 */
	@RequestMapping("/queryTopDepartments.do")
	@ResponseBody
	public List<Department> queryTopDepartments(){
		return departmentService.queryTopDepartment();
	}
	/**
	 * 查询部门和子部门信息	 
	 * @return
	 */
	@RequestMapping("/querySubDepartments.do")
	@ResponseBody
	public List<Department> querySubDepartments(@RequestParam("pid")Long pid){
		List<Department> list = departmentService.querySubDepartment(pid);
		List<Department> deptList = new ArrayList<>();
		convertChidrenToList(list, deptList);
		return deptList;
	}
	/**
	 * 分页查询部门
	 * @param pid
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryDepartmentsByParentId.do")
	@ResponseBody
	public ModelMap queryDepartmentsByParentId(@RequestParam("pid")Long pid,@RequestParam(defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<Object[]> pager = departmentService.queryObjs("from Department d inner join d.parent p  where p.id=?0", page, rows, new Object[] {pid});
		
		Pager<DepartmentVO> pagerDeptVO = new Pager<>();
		model2VO(pager, pagerDeptVO);
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pagerDeptVO.getData());
		mm.addAttribute("total", pager.getTotalCount());
		return mm;
	}
	
	private void model2VO(Pager<Object[]> pagerDept,Pager<DepartmentVO> pagerDeptVO) {
		List<Object[]> depts = pagerDept.getData();
		List<DepartmentVO> deptVOs = pagerDeptVO.getData();
		for(Object[] obj:depts) {
			DepartmentVO vo = new DepartmentVO();
			Department son = (Department) obj[0];
			Department parent = (Department) obj[1];
			
			vo.setId(son.getId());
			vo.setName(son.getName());
			vo.setParent(parent);
			vo.setCode(son.getCode());
			
			deptVOs.add(vo);
		}
	}
	/**
	 * 将所有子部门存入和父部门相同的列表对象
	 * @param list
	 * @return
	 */
	private void convertChidrenToList(List<Department> list,List<Department> deptList){
		for(int i = 0;i<list.size();i++) {
			Department d = list.get(i);
			deptList.add(d);
			if(d.getChildren()!=null || d.getChildren().size()!=0) {
				convertChidrenToList(d.getChildren(), deptList);
			}
		}
	}
} 
