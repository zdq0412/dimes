package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IDeviceTypeDao;
import com.digitzones.model.DeviceType;
import com.digitzones.model.Pager;
import com.digitzones.service.IDeviceTypeService;
@Service
public class DeviceTypeServiceImpl implements IDeviceTypeService {
	private IDeviceTypeDao deviceTypeDao;
	@Autowired
	public void setDeviceTypeDao(IDeviceTypeDao deviceTypeDao) {
		this.deviceTypeDao = deviceTypeDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return deviceTypeDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(DeviceType obj) {
		deviceTypeDao.update(obj);
	}

	@Override
	public DeviceType queryByProperty(String name, String value) {
		return deviceTypeDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(DeviceType obj) {
		return deviceTypeDao.save(obj);
	}

	@Override
	public DeviceType queryObjById(Long id) {
		return deviceTypeDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		deviceTypeDao.deleteById(id);
	}

}
