package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IWorkpieceDao;
import com.digitzones.model.Pager;
import com.digitzones.model.Workpiece;
import com.digitzones.service.IWorkpieceService;
@Service
public class WorkpieceServiceImpl implements IWorkpieceService {
	private IWorkpieceDao workpieceDao;
	@Autowired
	public void setWorkpieceDao(IWorkpieceDao workpieceDao) {
		this.workpieceDao = workpieceDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return workpieceDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(Workpiece obj) {
		workpieceDao.update(obj);
	}

	@Override
	public Workpiece queryByProperty(String name, String value) {
		return workpieceDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(Workpiece obj) {
		return workpieceDao.save(obj);
	}

	@Override
	public Workpiece queryObjById(Long id) {
		return workpieceDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		workpieceDao.deleteById(id);
	}

	@Override
	public List<Workpiece> queryAllWorkpieces(String q) {
		String hql = "from Workpiece w where w.code like ?0 or w.name like ?0 or w.unitType like ?0 or w.customerGraphNumber like ?0";
		return this.workpieceDao.findByHQL(hql, new Object[] {"%" + q + "%"});
	}
	@Override
	public List<Workpiece> queryAllWorkpieces() {
		String hql = "from Workpiece w ";
		return this.workpieceDao.findByHQL(hql, new Object[] {});
	}
}
