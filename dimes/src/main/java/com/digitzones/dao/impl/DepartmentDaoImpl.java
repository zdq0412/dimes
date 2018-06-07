package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IDepartmentDao;
import com.digitzones.model.Department;
@Repository
public class DepartmentDaoImpl extends CommonDaoImpl<Department> implements IDepartmentDao {
	public DepartmentDaoImpl() {
		super(Department.class);
	}
}
