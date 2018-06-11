package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IEquipmentTypeDao;
import com.digitzones.model.EquipmentType;
import com.digitzones.model.Pager;
import com.digitzones.service.IEquipmentTypeService;
@Service
public class EquipmentTypeServiceImpl implements IEquipmentTypeService {
	private IEquipmentTypeDao equipmentTypeDao;
	@Autowired
	public void setEquipmentTypeDao(IEquipmentTypeDao equipmentTypeDao) {
		this.equipmentTypeDao = equipmentTypeDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return equipmentTypeDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(EquipmentType obj) {
		equipmentTypeDao.update(obj);
	}

	@Override
	public EquipmentType queryByProperty(String name, String value) {
		return equipmentTypeDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(EquipmentType obj) {
		return equipmentTypeDao.save(obj);
	}

	@Override
	public EquipmentType queryObjById(Long id) {
		return equipmentTypeDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		equipmentTypeDao.deleteById(id);
	}

}
