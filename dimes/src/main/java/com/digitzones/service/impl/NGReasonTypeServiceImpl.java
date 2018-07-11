package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.INGReasonTypeDao;
import com.digitzones.model.NGReasonType;
import com.digitzones.model.Pager;
import com.digitzones.service.INGReasonTypeService;
@Service
public class NGReasonTypeServiceImpl implements INGReasonTypeService {
	private INGReasonTypeDao ngReasonTypeDao;
	@Autowired
	public void setNgReasonTypeDao(INGReasonTypeDao ngReasonTypeDao) {
		this.ngReasonTypeDao = ngReasonTypeDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return ngReasonTypeDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(NGReasonType obj) {
		ngReasonTypeDao.update(obj);
	}

	@Override
	public NGReasonType queryByProperty(String name, String value) {
		return ngReasonTypeDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(NGReasonType obj) {
		return ngReasonTypeDao.save(obj);
	}

	@Override
	public NGReasonType queryObjById(Long id) {
		return ngReasonTypeDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		ngReasonTypeDao.deleteById(id);
	}

	@Override
	public Long queryCountOfSubNGReasonType(Serializable pid) {
		return ngReasonTypeDao.findCount("from NGReasonType d inner join d.parent p where p.id=?0", new Object[] {pid});
	}

	@Override
	public List<NGReasonType> queryTopNGReasonType() {
		return  ngReasonTypeDao.findByHQL("from NGReasonType p where p.parent is null", new Object[] {});
	}

	@Override
	public List<NGReasonType> queryAllNGReasonTypes() {
		return ngReasonTypeDao.findByHQL("from NGReasonType p where p.parent is not null",  new Object[] {});
	}
}
