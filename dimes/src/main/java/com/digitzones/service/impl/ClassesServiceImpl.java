package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IClassesDao;
import com.digitzones.model.Classes;
import com.digitzones.model.Pager;
import com.digitzones.service.IClassesService;
@Service
public class ClassesServiceImpl implements IClassesService {
	private IClassesDao  classesDao;
	@Autowired
	public void setClassesDao(IClassesDao classesDao) {
		this.classesDao = classesDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return classesDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(Classes obj) {
		classesDao.update(obj);
	}

	@Override
	public Classes queryByProperty(String name, String value) {
		return classesDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(Classes obj) {
		return classesDao.save(obj);
	}

	@Override
	public Classes queryObjById(Long id) {
		return classesDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		classesDao.deleteById(id);
	}

	@Override
	public Classes queryCurrentClasses() {
		return classesDao.queryCurrentClasses();
	}

	@Override
	public List<Classes> queryAllClasses() {
		return classesDao.findAll();
	}
}
