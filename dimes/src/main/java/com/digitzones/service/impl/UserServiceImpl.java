package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IUserDao;
import com.digitzones.model.Pager;
import com.digitzones.model.User;
import com.digitzones.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {
	private IUserDao userDao;
	@Autowired
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return userDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(User obj) {
		userDao.update(obj);
	}

	@Override
	public User queryByProperty(String name, String value) {
		return userDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(User obj) {
		return userDao.save(obj);
	}

	@Override
	public User queryObjById(Long id) {
		return userDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		userDao.deleteById(id);
	}
}
