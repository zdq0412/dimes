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
	/**
	 * 删除员工
	 * @param ids 要删除的员工的id数组
	 * @return boolean true:成功，false：失败，关联用户
	 */
	public boolean deleteEmployees(String[] ids);
}
