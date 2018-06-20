package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IWorkpieceTypeDao;
import com.digitzones.model.Pager;
import com.digitzones.model.WorkpieceType;
import com.digitzones.service.IWorkpieceTypeService;
@Service
public class WorkpieceTypeServiceImpl implements IWorkpieceTypeService {
	private IWorkpieceTypeDao workpieceTypeDao;
	@Autowired
	public void setWorkpieceTypeDao(IWorkpieceTypeDao workpieceTypeDao) {
		this.workpieceTypeDao = workpieceTypeDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return workpieceTypeDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(WorkpieceType obj) {
		workpieceTypeDao.update(obj);
	}

	@Override
	public WorkpieceType queryByProperty(String name, String value) {
		return workpieceTypeDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(WorkpieceType obj) {
		return workpieceTypeDao.save(obj);
	}

	@Override
	public WorkpieceType queryObjById(Long id) {
		return workpieceTypeDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		workpieceTypeDao.deleteById(id);
	}

	@Override
	public List<WorkpieceType> queryTopWorkpieceType() {
		return workpieceTypeDao.findByHQL("from WorkpieceType d where d.parent is null", new Object[] {});
	}

	@Override
	public Long queryCountOfSubWorkpieceType(Serializable pid) {
		return workpieceTypeDao.findCount("from WorkpieceType d inner join d.parent p where p.id=?0", new Object[] {pid});
	}
}
