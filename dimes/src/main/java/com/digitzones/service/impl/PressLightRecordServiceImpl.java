package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IPressLightRecordDao;
import com.digitzones.model.Pager;
import com.digitzones.model.PressLightRecord;
import com.digitzones.service.IPressLightRecordService;
@Service
public class PressLightRecordServiceImpl implements IPressLightRecordService {
	private IPressLightRecordDao pressLightRecordDao;
	@Autowired
	public void setPressLightRecordDao(IPressLightRecordDao pressLightRecordDao) {
		this.pressLightRecordDao = pressLightRecordDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return pressLightRecordDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(PressLightRecord obj) {
		pressLightRecordDao.update(obj);
	}

	@Override
	public PressLightRecord queryByProperty(String name, String value) {
		return pressLightRecordDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(PressLightRecord obj) {
		return pressLightRecordDao.save(obj);
	}

	@Override
	public PressLightRecord queryObjById(Long id) {
		return pressLightRecordDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		pressLightRecordDao.deleteById(id);
	}
}
