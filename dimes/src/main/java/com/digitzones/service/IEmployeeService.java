package com.digitzones.service;

import java.util.List;

import com.digitzones.model.Employee;
/**
 * 人员业务逻辑接口
 * @author zdq
 * 2018年6月10日
 */
public interface IEmployeeService extends ICommonService<Employee> {
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> queryAllEmployees();
}
