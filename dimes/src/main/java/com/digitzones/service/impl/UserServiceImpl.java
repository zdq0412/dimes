package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

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

	@Override
	public User login(String username, String password) {
		List<User> users = this.userDao.findByHQL("from User u where u.username=?0 and u.password=?1", new Object[] {username,password});
		return (users!=null&&users.size()>0)?users.get(0):null;
	}

	@Override
	public User queryUserByUsername(String username) {
		return userDao.findSingleByProperty("username", username);
	}

	@Override
	public List<User> queryNotCurrentUsers(Long currentUserId) {
		return userDao.findByHQL("from User u where u.id!=?0", new Object[] {currentUserId});
	}

	@Override
	public List<User> queryAllUsers() {
		return userDao.findAll();
	}
}
