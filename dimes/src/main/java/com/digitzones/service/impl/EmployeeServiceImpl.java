package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IEmployeeDao;
import com.digitzones.model.Employee;
import com.digitzones.model.Pager;
import com.digitzones.service.IEmployeeService;
@Service
public class EmployeeServiceImpl implements IEmployeeService {
	private IEmployeeDao employeeDao;
	@Autowired
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return employeeDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(Employee obj) {
		employeeDao.update(obj);
	}

	@Override
	public Employee queryByProperty(String name, String value) {
		return employeeDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(Employee obj) {
		return employeeDao.save(obj);
	}

	@Override
	public Employee queryObjById(Long id) {
		return employeeDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		employeeDao.deleteById(id);
	}

	@Override
	public List<Employee> queryAllEmployees() {
		return employeeDao.findAll();
	}

}
