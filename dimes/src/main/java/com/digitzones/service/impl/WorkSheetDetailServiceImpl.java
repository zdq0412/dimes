package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IWorkSheetDetailDao;
import com.digitzones.model.Pager;
import com.digitzones.model.WorkSheetDetail;
import com.digitzones.service.IWorkSheetDetailService;
@Service
public class WorkSheetDetailServiceImpl implements IWorkSheetDetailService {
	private IWorkSheetDetailDao workSheetDetailDao;
	@Autowired
	public void setWorkSheetDetailDao(IWorkSheetDetailDao workSheetDetailDao) {
		this.workSheetDetailDao = workSheetDetailDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return workSheetDetailDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(WorkSheetDetail obj) {
		workSheetDetailDao.update(obj);
	}

	@Override
	public WorkSheetDetail queryByProperty(String name, String value) {
		return workSheetDetailDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(WorkSheetDetail obj) {
		return workSheetDetailDao.save(obj);
	}

	@Override
	public WorkSheetDetail queryObjById(Long id) {
		return workSheetDetailDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		workSheetDetailDao.deleteById(id);
	}

	@Override
	public List<WorkSheetDetail> queryWorkSheetDetailsByWorkSheetId(Long workSheetId) {
		return workSheetDetailDao.findByHQL("from WorkSheetDetail wsd where wsd.workSheet.id=?0",new Object[] {workSheetId});
	}

}
