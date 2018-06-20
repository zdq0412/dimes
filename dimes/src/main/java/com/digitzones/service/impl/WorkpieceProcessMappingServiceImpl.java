package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IWorkpieceProcessMappingDao;
import com.digitzones.model.Pager;
import com.digitzones.model.WorkpieceProcessMapping;
import com.digitzones.service.IWorkpieceProcessMappingService;
@Service
public class WorkpieceProcessMappingServiceImpl implements IWorkpieceProcessMappingService {
	private IWorkpieceProcessMappingDao workpieceProcessMappingDao;
	@Autowired
	public void setWorkpieceProcessMappingDao(IWorkpieceProcessMappingDao workpieceProcessMappingDao) {
		this.workpieceProcessMappingDao = workpieceProcessMappingDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return workpieceProcessMappingDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(WorkpieceProcessMapping obj) {
		workpieceProcessMappingDao.update(obj);
	}

	@Override
	public WorkpieceProcessMapping queryByProperty(String name, String value) {
		return workpieceProcessMappingDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(WorkpieceProcessMapping obj) {
		return workpieceProcessMappingDao.save(obj);
	}

	@Override
	public WorkpieceProcessMapping queryObjById(Long id) {
		return workpieceProcessMappingDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		workpieceProcessMappingDao.deleteById(id);
	}

	@Override
	public void deleteByWorkpieceIdAndProcessId(Long workpieceId, Long processId) {
		workpieceProcessMappingDao.deleteByWorkpieceIdAndProcessId(workpieceId, processId);
	}

	@Override
	public WorkpieceProcessMapping queryByWorkpieceIdAndProcessId(Long workpieceId, Long processId) {
		return workpieceProcessMappingDao.findByHQL("from WorkpieceProcessMapping wpm where wpm.workpiece.id=?0 and wpm.process.id=?1", new Object[] {workpieceId,processId}).get(0);
	}
}
