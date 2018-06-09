package com.digitzones.service;

import java.io.Serializable;
import java.util.List;

import com.digitzones.model.Department;

/**
 * 部门管理业务接口
 * @author zdq
 * 2018年6月7日
 */
public interface IDepartmentService extends ICommonService<Department>{
	/**
	 * 查找顶层部门，实际上就是公司信息
	 * @return
	 */
	public List<Department> queryTopDepartment();
	/**
	 * 查找子部门 
	 * @param pid 父部门id
	 * @return
	 */
	public List<Department> querySubDepartment(Serializable pid);
	/**
	 * 添加部门
	 * @param department
	 * @return
	 */
	public Serializable addDepartment(Department department);
	/**
	 * 根据id查找部门
	 * @param id
	 * @return
	 */
	public Department queryDepartmentById(Serializable id);
	/**
	 * 根据属性名称查找部门信息
	 * @param name
	 * @param value
	 * @return
	 */
	public Department queryDepartmentByProperty(String name,Object value);
	/**
	 * 查询所有部门信息
	 * @return
	 */
	public List<Department> queryAllDepartments();
	/**
	 * 根据id删除部门
	 * @param id
	 */
	public void deleteDepartment(Serializable id);
	/**
	 * 根据父部门id查询子部门数量
	 * @param pid
	 * @return
	 */
	public Long queryCountOfSubDepartment(Serializable pid);
	
}
