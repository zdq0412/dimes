package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.INGReasonDao;
import com.digitzones.model.NGReason;
import com.digitzones.model.Pager;
import com.digitzones.service.INGReasonService;
@Service
public class NGReasonServiceImpl implements INGReasonService {
	private INGReasonDao ngReasonDao;
	@Autowired
	public void setNgReasonDao(INGReasonDao ngReasonDao) {
		this.ngReasonDao = ngReasonDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return ngReasonDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(NGReason obj) {
		ngReasonDao.update(obj);
	}

	@Override
	public NGReason queryByProperty(String name, String value) {
		return ngReasonDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(NGReason obj) {
		return ngReasonDao.save(obj);
	}

	@Override
	public NGReason queryObjById(Long id) {
		return ngReasonDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		ngReasonDao.deleteById(id);
	}

	@Override
	public List<NGReason> queryNGReasonsByNGReasonTypeId(Long ngReasonTypeId) {
		return ngReasonDao.findByHQL("from NGReason reason where reason.ngReasonType.id=?0", new Object[] {ngReasonTypeId});
	}
}
