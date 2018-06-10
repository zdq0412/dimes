package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IEmployeeDao;
import com.digitzones.model.Employee;
@Repository
public class EmployeeDaoImpl extends CommonDaoImpl<Employee> implements IEmployeeDao {

	public EmployeeDaoImpl() {
		super(Employee.class);
	}
}
