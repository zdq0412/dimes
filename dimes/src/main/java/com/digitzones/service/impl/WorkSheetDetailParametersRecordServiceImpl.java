package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IWorkSheetDetailParametersRecordDao;
import com.digitzones.model.Pager;
import com.digitzones.model.WorkSheetDetailParametersRecord;
import com.digitzones.service.IWorkSheetDetailParametersRecordService;
@Service
public class WorkSheetDetailParametersRecordServiceImpl implements IWorkSheetDetailParametersRecordService {
	private IWorkSheetDetailParametersRecordDao workSheetDetailParametersRecordDao;
	@Autowired
	public void setWorkSheetDetailParametersRecordDao(
			IWorkSheetDetailParametersRecordDao workSheetDetailParametersRecordDao) {
		this.workSheetDetailParametersRecordDao = workSheetDetailParametersRecordDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return workSheetDetailParametersRecordDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(WorkSheetDetailParametersRecord obj) {
		workSheetDetailParametersRecordDao.update(obj);
	}

	@Override
	public WorkSheetDetailParametersRecord queryByProperty(String name, String value) {
		return workSheetDetailParametersRecordDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(WorkSheetDetailParametersRecord obj) {
		return workSheetDetailParametersRecordDao.save(obj);
	}

	@Override
	public WorkSheetDetailParametersRecord queryObjById(Long id) {
		return workSheetDetailParametersRecordDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		workSheetDetailParametersRecordDao.deleteById(id);
	}
}
