package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IDeviceSiteParameterMappingDao;
import com.digitzones.model.DeviceSiteParameterMapping;
import com.digitzones.model.Pager;
import com.digitzones.service.IDeviceSiteParameterMappingService;
@Service
public class DeviceSiteParameterMappingServiceImpl implements IDeviceSiteParameterMappingService {
	private IDeviceSiteParameterMappingDao deviceSiteParameterMappingDao;
	@Autowired
	public void setDeviceSiteParameterMappingDao(IDeviceSiteParameterMappingDao deviceSiteParameterMappingDao) {
		this.deviceSiteParameterMappingDao = deviceSiteParameterMappingDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return deviceSiteParameterMappingDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(DeviceSiteParameterMapping obj) {
		deviceSiteParameterMappingDao.update(obj);
	}

	@Override
	public DeviceSiteParameterMapping queryByProperty(String name, String value) {
		return deviceSiteParameterMappingDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(DeviceSiteParameterMapping obj) {
		return deviceSiteParameterMappingDao.save(obj);
	}

	@Override
	public DeviceSiteParameterMapping queryObjById(Long id) {
		return deviceSiteParameterMappingDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		deviceSiteParameterMappingDao.deleteById(id);
	}
}
