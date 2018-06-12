package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IParameterDao;
import com.digitzones.model.Pager;
import com.digitzones.model.Parameters;
import com.digitzones.service.IParameterService;
@Service
public class ParameterServiceImpl implements IParameterService {
	private IParameterDao parameterDao;
	@Autowired
	public void setParameterDao(IParameterDao parameterDao) {
		this.parameterDao = parameterDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return parameterDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(Parameters obj) {
		parameterDao.update(obj);
	}

	@Override
	public Parameters queryByProperty(String name, String value) {
		return parameterDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(Parameters obj) {
		return parameterDao.save(obj);
	}

	@Override
	public Parameters queryObjById(Long id) {
		return parameterDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		parameterDao.deleteById(id);
	}

}
