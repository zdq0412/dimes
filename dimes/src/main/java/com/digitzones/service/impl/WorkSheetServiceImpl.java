package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IWorkSheetDao;
import com.digitzones.model.Pager;
import com.digitzones.model.WorkSheet;
import com.digitzones.service.IWorkSheetService;
@Service
public class WorkSheetServiceImpl implements IWorkSheetService {
	private IWorkSheetDao workSheetDao;
	@Autowired
	public void setWorkSheetDao(IWorkSheetDao workSheetDao) {
		this.workSheetDao = workSheetDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return workSheetDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(WorkSheet obj) {
		workSheetDao.update(obj);
	}

	@Override
	public WorkSheet queryByProperty(String name, String value) {
		return workSheetDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(WorkSheet obj) {
		return workSheetDao.save(obj);
	}

	@Override
	public WorkSheet queryObjById(Long id) {
		return workSheetDao.findById(id);
	}
	@Override
	public void deleteObj(Long id) {
		workSheetDao.deleteById(id);
	}

	@Override
	public List<WorkSheet> queryOtherWorkSheetByDeviceSiteId(Long deviceSiteId) {
		return workSheetDao.findByHQL("from WorkSheet ws where ws.id not in (select wsd.workSheet.id from WorkSheetDetail wsd where wsd.deviceSiteId=?0)", new Object[] {deviceSiteId});
	}
}
