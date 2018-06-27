package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IDeviceDao;
import com.digitzones.dao.IDeviceSiteDao;
import com.digitzones.model.Device;
import com.digitzones.model.Pager;
import com.digitzones.service.IDeviceService;
@Service
public class DeviceServiceImpl implements IDeviceService {
	private IDeviceDao deviceDao;
	private IDeviceSiteDao deviceSiteDao;
	@Autowired
	public void setDeviceSiteDao(IDeviceSiteDao deviceSiteDao) {
		this.deviceSiteDao = deviceSiteDao;
	}

	@Autowired
	public void setDeviceDao(IDeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return deviceDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(Device obj) {
		deviceDao.update(obj);
	}

	@Override
	public Device queryByProperty(String name, String value) {
		return deviceDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(Device obj) {
		return deviceDao.save(obj);
	}

	@Override
	public Device queryObjById(Long id) {
		return deviceDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		deviceDao.deleteById(id);
	}

	@Override
	public Device queryDeviceByDeviceSiteId(Long deviceSiteId) {
		
		return this.deviceSiteDao.findById(deviceSiteId).getDevice();
	}

	@Override
	public List<Device> queryDevicesByProductionUnitId(Long productionUnitId) {
		return this.deviceDao.findByHQL("from Device d where d.productionUnit.id=?0", new Object[] {productionUnitId});
	}

	@Override
	public double queryOeeByProductionUnitId(Long productionUnitId) {
		return deviceDao.queryOeeByProductionUnitId(productionUnitId);
	}
}
