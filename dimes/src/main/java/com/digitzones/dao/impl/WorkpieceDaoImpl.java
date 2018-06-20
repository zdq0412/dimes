package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IWorkpieceDao;
import com.digitzones.model.Workpiece;
@Repository
public class WorkpieceDaoImpl extends CommonDaoImpl<Workpiece> implements IWorkpieceDao {

	public WorkpieceDaoImpl() {
		super(Workpiece.class);
	}
}
