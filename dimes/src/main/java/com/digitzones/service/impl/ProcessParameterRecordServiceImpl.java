package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IProcessParameterRecordDao;
import com.digitzones.model.Pager;
import com.digitzones.model.ProcessParameterRecord;
import com.digitzones.service.IProcessParameterRecordService;
@Service
public class ProcessParameterRecordServiceImpl implements IProcessParameterRecordService {
	private IProcessParameterRecordDao processParameterRecordDao;
	@Autowired
	public void setProcessParameterRecordDao(IProcessParameterRecordDao processParameterRecordDao) {
		this.processParameterRecordDao = processParameterRecordDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return processParameterRecordDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(ProcessParameterRecord obj) {
		processParameterRecordDao.update(obj);
	}

	@Override
	public ProcessParameterRecord queryByProperty(String name, String value) {
		return processParameterRecordDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(ProcessParameterRecord obj) {
		return processParameterRecordDao.save(obj);
	}

	@Override
	public ProcessParameterRecord queryObjById(Long id) {
		return processParameterRecordDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		processParameterRecordDao.deleteById(id);
	}
}
