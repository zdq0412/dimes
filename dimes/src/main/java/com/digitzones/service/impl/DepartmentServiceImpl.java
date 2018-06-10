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
public class DepartmentServiceImpl  implements IDepartmentService {
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

	@Override
	public Department queryDepartmentById(Serializable id) {
		return departmentDao.findById(id);
	}

	@Override
	public Department queryDepartmentByProperty(String name, Object value) {
		return departmentDao.findSingleByProperty(name, value);
	}

	@Override
	public List<Department> queryAllDepartments() {
		return departmentDao.findAll();
	}

	@Override
	public void updateObj(Department obj) {
		this.departmentDao.update(obj);
	}

	@Override
	public void deleteDepartment(Serializable id) {
		departmentDao.deleteById(id);
	}

	@Override
	public Long queryCountOfSubDepartment(Serializable pid) {
		return departmentDao.findCount("from Department d inner join d.parent p where p.id=?0", new Object[] {pid});
	}

	@Override
	public Department queryByProperty(String name, String value) {
		return departmentDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(Department obj) {
		return departmentDao.save(obj);
	}

	@Override
	public Department queryObjById(Long id) {
		return departmentDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		departmentDao.deleteById(id);
	}
}
