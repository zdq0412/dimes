package com.digitzones.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Department;
import com.digitzones.model.Pager;
import com.digitzones.model.ProductionUnit;
import com.digitzones.service.IProductionUnitService;
import com.digitzones.vo.DepartmentVO;
import com.digitzones.vo.ProductionUnitVO;
/**
 * 生产单元管理控制器
 * @author zdq
 * 2018年6月7日
 */
@Controller
@RequestMapping("/productionUnit")
public class ProductionUnitController {
	private IProductionUnitService productionUnitService;
	@Autowired
	public void setProductionUnitService(IProductionUnitService productionUnitService) {
		this.productionUnitService = productionUnitService;
	}
	/**
	 * 查询生产单元
	 * @return
	 */
	@RequestMapping("/queryTopProductionUnits.do")
	@ResponseBody
	public List<ProductionUnit> queryTopProductionUnits(){
		return productionUnitService.queryTopProductionUnits();
	}
	
	/**
	 * 分页查询生产单元
	 * @param pid
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryProductionUnitsByParentId.do")
	@ResponseBody
	public ModelMap queryProductionUnitsByParentId(@RequestParam(value="pid",required=false)Long pid,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<Object[]> pager = null;
		if(pid==null) {
			pager = productionUnitService.queryObjs("from ProductionUnit pu inner join pu.parent p ", page, rows, new Object[] {});
		}else {
			pager = productionUnitService.queryObjs("from ProductionUnit pu inner join pu.parent p  where p.id=?0", page, rows, new Object[] {pid});
		}

		Pager<ProductionUnitVO> pagerProductionUnitVO = new Pager<>();
		model2VO(pager, pagerProductionUnitVO);
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pagerProductionUnitVO.getData());
		mm.addAttribute("total", pager.getTotalCount());
		mm.addAttribute("code", "0");
		mm.addAttribute("msg", "");
		return mm;
	}

	private void model2VO(Pager<Object[]> pagerProductionUnit,Pager<ProductionUnitVO> ProductionUnitVO) {
		List<Object[]> productionUnits = pagerProductionUnit.getData();
		List<ProductionUnitVO> productionUnitVOs = ProductionUnitVO.getData();
		for(Object[] obj:productionUnits) {
			ProductionUnitVO vo = new ProductionUnitVO();
			ProductionUnit son = (ProductionUnit) obj[0];
			ProductionUnit parent = (ProductionUnit) obj[1];

			vo.setId(son.getId());
			vo.setName(son.getName());
			parent.setChildren(null);
			vo.setParent(parent);
			vo.setCode(son.getCode());
			vo.setDisabled(son.getDisabled());
			vo.setNote(son.getNote());
			productionUnitVOs.add(vo);
		}
	}
	/**
	 * 更新部门
	 * @param department
	 * @return
	 *//*
	@RequestMapping("/updateDepartment.do")
	@ResponseBody
	public ModelMap updateDepartment(Department department) {
		ModelMap modelMap = new ModelMap();
		Department d = departmentService.queryDepartmentByProperty("name",department.getName());
		if(d!=null && !department.getId().equals(d.getId())) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "部门名称已被使用");
		}else {
			departmentService.updateObj(department);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "编辑成功!");
		}
		return modelMap;
	}
	*//**
	 * 根据id删除部门，如果该部门存在子部门，则不允许删除
	 * @param id
	 * @return
	 *//*
	@RequestMapping("/deleteDepartment.do")
	@ResponseBody
	public ModelMap deleteDepartment(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Long count = departmentService.queryCountOfSubDepartment(Long.valueOf(id));
		if(count>0) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("msg", "该部门下存在子部门，不允许删除!");
			modelMap.addAttribute("message", "该部门下存在子部门，不允许删除!");
		}else {
			departmentService.deleteDepartment(Long.valueOf(id));
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("statusCode", 200);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("msg", "该部门下存在子部门，不允许删除!");
			modelMap.addAttribute("message", "成功删除!");
		}
		return modelMap;
	}
	*//**
	 * 停用该部门
	 * @param id
	 * @return
	 *//*
	@RequestMapping("/disabledDepartment.do")
	@ResponseBody
	public ModelMap disabledDepartment(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Department d = departmentService.queryDepartmentById(Long.valueOf(id));
		d.setDisabled(true);
		
		departmentService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}
	*//**
	 * 根据id查询部门
	 * @param department
	 * @return
	 *//*
	@RequestMapping("/queryDepartmentById.do")
	@ResponseBody
	public Department queryDepartmentById(Long id) {
		Department department = departmentService.queryDepartmentById(id);
		return department;
	}

	*//**
	 * 查找所有部门
	 * @return
	 *//*
	@RequestMapping("/queryAllDepartments.do")
	@ResponseBody
	public List<Department> queryAllDepartments(){
		return departmentService.queryAllDepartments();
	}
	*//**
	 * 查询部门和子部门信息	 
	 * @return
	 *//*
	@RequestMapping("/querySubDepartments.do")
	@ResponseBody
	public List<Department> querySubDepartments(@RequestParam("id")Long pid){
		List<Department> list = departmentService.querySubDepartment(pid);
		List<Department> deptList = new ArrayList<>();
		convertChidrenToList(list, deptList);
		return deptList;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryRaletedDocuments.do")
	@ResponseBody
	public ModelMap queryRaletedDocuments(@RequestParam("id")Long relatedId,@RequestParam(defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page ) {
		String hql = "from RelatedDocument rd where rd.relatedId = ?0 and rd.relatedType=?1";
		Pager<RelatedDocument> pager = departmentService.queryObjs(hql, page, rows, new Object[] {relatedId,Constant.RelatedDoc.DEPARTMENT});
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows",pager.getData());
		return modelMap;
	}
	*//**
	 * 将所有子部门存入和父部门相同的列表对象
	 * @param list
	 * @return
	 *//*
	private void convertChidrenToList(List<Department> list,List<Department> deptList){
		for(int i = 0;i<list.size();i++) {
			Department d = list.get(i);
			deptList.add(d);
			if(d.getChildren()!=null || d.getChildren().size()!=0) {
				convertChidrenToList(d.getChildren(), deptList);
			}
		}
	}
	*//**
	 * 添加部门
	 * @param department
	 * @return
	 *//*
	@RequestMapping("/addDepartment.do")
	@ResponseBody
	public ModelMap addDepartment(Department department) {
		ModelMap modelMap = new ModelMap();
		//检测部门编码和名称是否重复
		Department dept4Code = departmentService.queryDepartmentByProperty("code", department.getCode());
		if(dept4Code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "部门编码已被使用");
		}else {
			Department	dept4Name = departmentService.queryDepartmentByProperty("name",department.getName());
			if(dept4Name!=null) {
				modelMap.addAttribute("success", false);
				modelMap.addAttribute("msg", "部门名称已被使用");
			}else {
				departmentService.addDepartment(department);
				modelMap.addAttribute("success", true);
				modelMap.addAttribute("msg", "添加成功!");
			}
		}
		return modelMap;
	}*/
} 
