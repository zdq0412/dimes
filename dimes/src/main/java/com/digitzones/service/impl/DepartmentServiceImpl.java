package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IDepartmentDao;
import com.digitzones.model.Department;
import com.digitzones.model.Pager;
import com.digitzones.service.IDepartmentService;
@Service
public class DepartmentServiceImpl implements IDepartmentService {
	private IDepartmentDao departmentDao;
	@Autowired
	public void setDepartmentDao(IDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	public List<Department> queryTopDepartment() {
		return departmentDao.findByHQL("from Department d where d.parent is null", new Object[] {});
	}

	@Override
	public List<Department> querySubDepartment(Serializable pid) {
		return departmentDao.findByHQL("from Department d where d.id = ?0 ", new Object[] {pid});
	}

	@Override
	public Serializable addDepartment(Department department) {
		return departmentDao.save(department);
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return departmentDao.findByPage(hql, pageNo, pageSize, values);
	}
}
