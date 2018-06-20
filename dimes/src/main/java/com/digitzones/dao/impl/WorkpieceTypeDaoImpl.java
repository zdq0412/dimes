package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IWorkpieceTypeDao;
import com.digitzones.model.WorkpieceType;
@Repository
public class WorkpieceTypeDaoImpl extends CommonDaoImpl<WorkpieceType> implements IWorkpieceTypeDao {
	public WorkpieceTypeDaoImpl() {
		super(WorkpieceType.class);
	}
}
