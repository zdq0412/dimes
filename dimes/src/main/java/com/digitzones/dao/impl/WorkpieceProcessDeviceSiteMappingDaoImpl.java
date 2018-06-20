package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IWorkpieceProcessDeviceSiteMappingDao;
import com.digitzones.model.WorkpieceProcessDeviceSiteMapping;
@Repository
public class WorkpieceProcessDeviceSiteMappingDaoImpl extends CommonDaoImpl<WorkpieceProcessDeviceSiteMapping>
		implements IWorkpieceProcessDeviceSiteMappingDao {

	public WorkpieceProcessDeviceSiteMappingDaoImpl() {
		super(WorkpieceProcessDeviceSiteMapping.class);
	}

	@Override
	public Long queryCountByWorkpieceId(Long workpieceId) {
		return this.findCount(" from WorkpieceProcessDeviceSiteMapping wpdsm where wpdsm.workpieceProcess.workpiece.id=?0", new Object[] {workpieceId});
	}
}
