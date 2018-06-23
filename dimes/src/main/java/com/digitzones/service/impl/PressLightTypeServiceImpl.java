package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IPressLightTypeDao;
import com.digitzones.model.Pager;
import com.digitzones.model.PressLightType;
import com.digitzones.service.IPressLightTypeService;
@Service
public class PressLightTypeServiceImpl implements IPressLightTypeService {
	private IPressLightTypeDao pressLightTypeDao;
	@Autowired
	public void setPressLightTypeDao(IPressLightTypeDao pressLightTypeDao) {
		this.pressLightTypeDao = pressLightTypeDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return pressLightTypeDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(PressLightType obj) {
		pressLightTypeDao.update(obj);
	}

	@Override
	public PressLightType queryByProperty(String name, String value) {
		return pressLightTypeDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(PressLightType obj) {
		return pressLightTypeDao.save(obj);
	}

	@Override
	public PressLightType queryObjById(Long id) {
		return pressLightTypeDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		pressLightTypeDao.deleteById(id);
	}

	@Override
	public Long queryCountOfSubPressLightType(Serializable pid) {
		return pressLightTypeDao.findCount("from PressLightType d inner join d.parent p where p.id=?0", new Object[] {pid});
	}

	@Override
	public List<PressLightType> queryTopPressLightType() {
		return  pressLightTypeDao.findByHQL("from PressLightType p where p.parent is null", new Object[] {});
	}

	@Override
	public List<PressLightType> queryFirstLevelType() {
		return pressLightTypeDao.findByHQL("from PressLightType p where p.level=?0", new Object[] {1});
	}

	@Override
	public List<PressLightType> queryAllPressLightTypesByParentId(Long pid) {
		return pressLightTypeDao.findByHQL("from PressLightType p where p.parent.id=?0", new Object[] {pid});
	}
}
