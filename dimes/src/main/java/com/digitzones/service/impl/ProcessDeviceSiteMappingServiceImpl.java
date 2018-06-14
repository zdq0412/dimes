package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IProcessDeviceSiteMappingDao;
import com.digitzones.model.Pager;
import com.digitzones.model.ProcessDeviceSiteMapping;
import com.digitzones.service.IProcessDeviceSiteMappingService;
@Service
public class ProcessDeviceSiteMappingServiceImpl implements IProcessDeviceSiteMappingService {
	private IProcessDeviceSiteMappingDao processDeviceSiteMappingDao;
	@Autowired
	public void setProcessDeviceSiteMappingDao(IProcessDeviceSiteMappingDao processDeviceSiteMappingDao) {
		this.processDeviceSiteMappingDao = processDeviceSiteMappingDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return processDeviceSiteMappingDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(ProcessDeviceSiteMapping obj) {
		processDeviceSiteMappingDao.update(obj);
	}

	@Override
	public ProcessDeviceSiteMapping queryByProperty(String name, String value) {
		return processDeviceSiteMappingDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(ProcessDeviceSiteMapping obj) {
		return processDeviceSiteMappingDao.save(obj);
	}

	@Override
	public ProcessDeviceSiteMapping queryObjById(Long id) {
		return processDeviceSiteMappingDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		processDeviceSiteMappingDao.deleteById(id);
	}

	@Override
	public void deleteByProcessesIdAndDeviceSiteId(Long processesId, Long deviceSiteId) {
		processDeviceSiteMappingDao.deleteByProcessesIdAndDeviceSiteId(processesId, deviceSiteId);
	}
}
