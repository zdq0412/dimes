package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IProcessRecordDao;
import com.digitzones.model.Pager;
import com.digitzones.model.ProcessRecord;
import com.digitzones.service.IProcessRecordService;
@Service
public class ProcessRecordServiceImpl implements IProcessRecordService {
	private IProcessRecordDao processRecordDao;
	@Autowired
	public void setProcessRecordDao(IProcessRecordDao processRecordDao) {
		this.processRecordDao = processRecordDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return processRecordDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(ProcessRecord obj) {
		processRecordDao.update(obj);
	}

	@Override
	public ProcessRecord queryByProperty(String name, String value) {
		return processRecordDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(ProcessRecord obj) {
		return processRecordDao.save(obj);
	}

	@Override
	public ProcessRecord queryObjById(Long id) {
		return processRecordDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		processRecordDao.deleteById(id);
	}
}