package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IPowerDao;
import com.digitzones.model.Pager;
import com.digitzones.model.Power;
import com.digitzones.service.IPowerService;
@Service
public class PowerServiceImpl implements IPowerService {
	private IPowerDao powerDao;
	@Autowired
	public void setPowerDao(IPowerDao powerDao) {
		this.powerDao = powerDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return powerDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(Power obj) {
		powerDao.update(obj);
	}

	@Override
	public Power queryByProperty(String name, String value) {
		return powerDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(Power obj) {
		return powerDao.save(obj);
	}

	@Override
	public Power queryObjById(Long id) {
		return powerDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		powerDao.deleteById(id);
	}

	@Override
	public List<Power> queryAllPowers() {
		return powerDao.findAll();
	}

	@Override
	public List<Power> queryPowersByRoleId(Long roleId) {
		return powerDao.findByHQL("select rp.power from RolePower rp where rp.role.id=?0", new Object[] {});
	}
}
