package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IProcessesDao;
import com.digitzones.model.Pager;
import com.digitzones.model.Processes;
import com.digitzones.service.IProcessesService;
@Service
public class ProcessesServiceImpl implements IProcessesService {
	private IProcessesDao processesDao;
	@Autowired
	public void setProcessesDao(IProcessesDao processesDao) {
		this.processesDao = processesDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return processesDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(Processes obj) {
		processesDao.update(obj);
	}

	@Override
	public Processes queryByProperty(String name, String value) {
		return processesDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(Processes obj) {
		return processesDao.save(obj);
	}

	@Override
	public Processes queryObjById(Long id) {
		return processesDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		processesDao.deleteById(id);;
	}
	@Override
	public List<Processes> queryProcessByWorkpieceIdAndDeviceSiteId(Long workpieceId, Long deviceSiteId) {
		String hql = "select wpdsm.workpieceProcess.process from WorkpieceProcessDeviceSiteMapping wpdsm where wpdsm.workpieceProcess.workpiece.id=?0 and wpdsm.deviceSite.id=?1";
		return processesDao.findByHQL(hql, new Object[] {workpieceId,deviceSiteId});
	}
}
