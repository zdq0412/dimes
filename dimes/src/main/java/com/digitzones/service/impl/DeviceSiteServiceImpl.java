package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IDeviceSiteDao;
import com.digitzones.model.DeviceSite;
import com.digitzones.model.Pager;
import com.digitzones.service.IDeviceSiteService;
@Service
public class DeviceSiteServiceImpl implements IDeviceSiteService {
	private IDeviceSiteDao deviceSiteDao;
	@Autowired
	public void setDeviceSiteDao(IDeviceSiteDao deviceSiteDao) {
		this.deviceSiteDao = deviceSiteDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return deviceSiteDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(DeviceSite obj) {
		deviceSiteDao.update(obj);
	}

	@Override
	public DeviceSite queryByProperty(String name, String value) {
		return deviceSiteDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(DeviceSite obj) {
		return deviceSiteDao.save(obj);
	}

	@Override
	public DeviceSite queryObjById(Long id) {
		return deviceSiteDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		deviceSiteDao.deleteById(id);
	}

}
