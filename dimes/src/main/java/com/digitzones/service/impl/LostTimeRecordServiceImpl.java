package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.ILostTimeRecordDao;
import com.digitzones.model.LostTimeRecord;
import com.digitzones.model.Pager;
import com.digitzones.service.ILostTimeRecordService;
@Service
public class LostTimeRecordServiceImpl implements ILostTimeRecordService {
	private ILostTimeRecordDao lostTimeRecordDao;
	@Autowired
	public void setLostTimeRecordDao(ILostTimeRecordDao lostTimeRecordDao) {
		this.lostTimeRecordDao = lostTimeRecordDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return lostTimeRecordDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(LostTimeRecord obj) {
		lostTimeRecordDao.update(obj);
	}

	@Override
	public LostTimeRecord queryByProperty(String name, String value) {
		return lostTimeRecordDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(LostTimeRecord obj) {
		return lostTimeRecordDao.save(obj);
	}

	@Override
	public LostTimeRecord queryObjById(Long id) {
		return lostTimeRecordDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		lostTimeRecordDao.deleteById(id);
	}

}
