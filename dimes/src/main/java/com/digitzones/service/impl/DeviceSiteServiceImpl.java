package com.digitzones.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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

	@Override
	public Long queryCountOfDeviceSite() {
		return deviceSiteDao.queryCountOfDeviceSite();
	}

	@Override
	public Long queryCountOfDeviceSiteByStatus(String status) {
		return deviceSiteDao.queryCountOfDeviceSiteByStatus(status);
	}

	@Override
	public List<DeviceSite> queryDeviceSitesByProductionUnitId(Long productionUnitId) {
		String hql = "select ds from DeviceSite ds inner join ds.device d inner join d.productionUnit p where p.id=?0";
		return deviceSiteDao.findByHQL(hql, new Object[] {productionUnitId});
	}

	@Override
	public List<DeviceSite> queryAllDeviceSites() {
		return deviceSiteDao.findAll();
	}

	@Override
	public List<DeviceSite> queryDeviceSitesByShow(boolean isShow) {
		return this.deviceSiteDao.findListByProperty("show", isShow);
	}

	@Override
	public List<DeviceSite> queryAllDeviceSitesByDeviceId(Long deviceId) {
		return this.deviceSiteDao.findByHQL("from DeviceSite ds where ds.device.id=?0", new Object[] {deviceId});
	}

	@Override
	public List<DeviceSite> queryDeviceSitesByClassesId(Long classesId) {
		return this.deviceSiteDao.queryDeviceSitesByClassesId(classesId);
	}

	@Override
	public Integer queryLostTimeCountByDeviceSiteId(Long deviceSiteId) {
		return deviceSiteDao.queryLostTimeCountByDeviceSiteId(deviceSiteId);
	}

	@Override
	public Integer queryPressLightCountByDeviceSiteId(Long deviceSiteId) {
		return deviceSiteDao.queryPressLightCountByDeviceSiteId(deviceSiteId);
	}

	@Override
	public Integer queryProcessDeviceSiteMappingCountByDeviceSiteId(Long deviceSiteId) {
		return deviceSiteDao.queryProcessDeviceSiteMappingCountByDeviceSiteId(deviceSiteId);
	}

	@Override
	public List<BigDecimal> queryDeviceSiteIdsByProductionUnitId(Long productionUnitId) {
		return deviceSiteDao.queryDeviceSiteIdsByProductionUnitId(productionUnitId);
	}

	@Override
	public List<BigDecimal> queryAllDeviceSiteIds() {
		return deviceSiteDao.queryAllDeviceSiteIds();
	}
}
