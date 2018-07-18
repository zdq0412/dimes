package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IUserRoleDao;
import com.digitzones.model.Pager;
import com.digitzones.model.UserRole;
import com.digitzones.service.IUserRoleService;
@Service
public class UserRoleServiceImpl implements IUserRoleService {
	private IUserRoleDao userRoleDao;
	@Autowired
	public void setUserRoleDao(IUserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return userRoleDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(UserRole obj) {
		userRoleDao.update(obj);
	}

	@Override
	public UserRole queryByProperty(String name, String value) {
		return userRoleDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(UserRole obj) {
		return userRoleDao.save(obj);
	}

	@Override
	public UserRole queryObjById(Long id) {
		return userRoleDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		userRoleDao.deleteById(id);
	}
	@Override
	public Long queryCountByRoleId(Long roleId) {
		return userRoleDao.findCount("from UserRole ur where ur.role.id=?0", new Object[] {roleId});
	}
}
