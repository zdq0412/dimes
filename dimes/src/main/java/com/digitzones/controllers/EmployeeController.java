package com.digitzones.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Department;
import com.digitzones.model.Employee;
import com.digitzones.model.Pager;
import com.digitzones.model.Position;
import com.digitzones.service.IEmployeeService;
import com.digitzones.vo.EmployeeVO;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	private IEmployeeService employeeService;
	@Autowired
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	@RequestMapping("/queryEmployeesByDepartmentId.do")
	@ResponseBody
	public ModelMap queryEmployeesByDepartmentId(Long departmentId,Integer rows,Integer page) {
		ModelMap modelMap = new ModelMap();
		String hql = "from Employee e left join e.position p left join p.department d where d.id=?0";
		@SuppressWarnings("unchecked")
		Pager<Object[]> objPager = employeeService.queryObjs(hql, page, rows, new Object[] {departmentId});
		List<Object[]> objs = objPager.getData();
		modelMap.addAttribute("rows", objArray2EmployeeVO(objs));
		modelMap.addAttribute("total", objPager.getTotalCount());
		return modelMap;
	}

	/**
	 * 将模型对象List转换为VO对象List
	 * @param objList
	 * @return
	 */
	private List<EmployeeVO> objArray2EmployeeVO(List<Object[]> objList){
		List<EmployeeVO> voList = new ArrayList<>();
		if(objList!=null) {
			for(int i = 0;i<objList.size();i++) {
				Object[] o = objList.get(i);
				EmployeeVO vo = new EmployeeVO();
				if(o[0]!=null) {
					Employee e = (Employee) o[0];
					vo.setId(e.getId());
					vo.setName(e.getName());
					vo.setNote(e.getNote());
					vo.setPhoto(e.getPhoto());
					vo.setCode(e.getCode());
					vo.setDisabled(e.getDisabled());
				}

				if(o[1]!=null) {
					Position p = (Position)o[1];
					vo.setPositionId(p.getId());
					vo.setPositionCode(p.getCode());
					vo.setPositionName(p.getName());
				}
				if(o[2]!=null) {
					Department d = (Department)o[2];
					vo.setDepartmentId(d.getId());
					vo.setDepartmentCode(d.getCode());
					vo.setDepartmentName(d.getName());
				}

				voList.add(vo);
			}
		}
		return voList;
	}
	/**
	 * 添加员工信息
	 * @param employee 员工对象
	 * @return
	 */
	@RequestMapping("/addEmployee.do")
	@ResponseBody
	public ModelMap addEmployee(Employee employee) {
		ModelMap modelMap = new ModelMap();
		Employee e = employeeService.queryByProperty("code",employee.getCode());
		if(e!=null) {
			modelMap.addAttribute("msg", "员工代码已被使用!");
			modelMap.addAttribute("success", false);
		}else {
			employeeService.addObj(employee);
			modelMap.addAttribute("msg", "添加成功!");
			modelMap.addAttribute("success", true);
		}
		return modelMap;
	}
	/**
	 * 根据员工id查询员工信息
	 * @param id 员工对象id
	 * @return
	 */
	@RequestMapping("/queryEmployeeById.do")
	@ResponseBody
	public Employee queryEmployeeById(Long id) {
		Employee e = employeeService.queryObjById(id);
		return e;
	}
	/**
	 * 查询所有员工对象
	 * @return
	 */
	@RequestMapping("/queryAllEmployees.do")
	@ResponseBody
	public List<Employee> queryAllEmployees() {
		return employeeService.queryAllEmployees();
	}
	/**
	 * 更新员工信息
	 * @param department
	 * @return
	 */
	@RequestMapping("/updateEmployee.do")
	@ResponseBody
	public ModelMap updateEmployee(Employee employee) {
		ModelMap modelMap = new ModelMap();
		employeeService.updateObj(employee);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "编辑成功!");
		return modelMap;
	}
	/**
	 * 删除员工
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteEmployee.do")
	@ResponseBody
	public ModelMap deleteEmployee(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		employeeService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("msg", "成功删除!");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 停用该员工
	 * @param id
	 * @return
	 */
	@RequestMapping("/disabledEmployee.do")
	@ResponseBody
	public ModelMap disabledEmployee(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Employee d = employeeService.queryObjById(Long.valueOf(id));
		d.setDisabled(true);

		employeeService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}
	
	
}
