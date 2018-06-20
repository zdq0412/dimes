package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IWorkpieceProcessParameterMappingDao;
import com.digitzones.model.WorkpieceProcessParameterMapping;
@Repository
public class WorkpieceProcessParameterMappingDaoImpl extends CommonDaoImpl<WorkpieceProcessParameterMapping>
		implements IWorkpieceProcessParameterMappingDao {

	public WorkpieceProcessParameterMappingDaoImpl() {
		super(WorkpieceProcessParameterMapping.class);
	}

	@Override
	public Long queryCountByWorkpieceId(Long workpieceId) {
		return this.findCount(" from WorkpieceProcessParameterMapping wpdsm where wpdsm.workpieceProcess.workpiece.id=?0", new Object[] {workpieceId});
	}
}
