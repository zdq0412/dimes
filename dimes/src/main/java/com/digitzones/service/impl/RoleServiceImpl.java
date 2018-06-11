package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IRoleDao;
import com.digitzones.model.Pager;
import com.digitzones.model.Role;
import com.digitzones.service.IRoleService;
@Service
public class RoleServiceImpl implements IRoleService {
	private IRoleDao roleDao;
	@Autowired
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return roleDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(Role obj) {
		roleDao.update(obj);
	}

	@Override
	public Role queryByProperty(String name, String value) {
		return roleDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(Role obj) {
		return roleDao.save(obj);
	}

	@Override
	public Role queryObjById(Long id) {
		return roleDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		roleDao.deleteById(id);
	}
}
