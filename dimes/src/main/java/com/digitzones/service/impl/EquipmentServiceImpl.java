package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IEquipmentDao;
import com.digitzones.model.Equipment;
import com.digitzones.model.Pager;
import com.digitzones.service.IEquipmentService;
@Service
public class EquipmentServiceImpl implements IEquipmentService {
	private IEquipmentDao equipmentDao;
	@Autowired
	public void setEquipmentDao(IEquipmentDao equipmentDao) {
		this.equipmentDao = equipmentDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return equipmentDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(Equipment obj) {
		equipmentDao.update(obj);
	}

	@Override
	public Equipment queryByProperty(String name, String value) {
		return equipmentDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(Equipment obj) {
		return equipmentDao.save(obj);
	}

	@Override
	public Equipment queryObjById(Long id) {
		return equipmentDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		equipmentDao.deleteById(id);
	}

}
