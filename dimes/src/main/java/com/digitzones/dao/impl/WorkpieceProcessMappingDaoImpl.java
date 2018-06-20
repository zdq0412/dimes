package com.digitzones.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IWorkpieceProcessMappingDao;
import com.digitzones.model.WorkpieceProcessMapping;
@Repository
public class WorkpieceProcessMappingDaoImpl extends CommonDaoImpl<WorkpieceProcessMapping> implements IWorkpieceProcessMappingDao {
	public WorkpieceProcessMappingDaoImpl() {
		super(WorkpieceProcessMapping.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteByWorkpieceIdAndProcessId(Long workpieceId, Long processId) {
		getSession().createSQLQuery("delete from WORKPIECE_PROCESS where WORKPIECE_ID=?0 and PROCESS_ID=?1")
		.setParameter(0, workpieceId)
		.setParameter(1, processId)
		.executeUpdate();
	}

	@Override
	public List<WorkpieceProcessMapping> queryByWorkpieceId(Long workpieceId) {
		return this.findByHQL("from WorkpieceProcessMapping wpm where wpm.workpiece.id=?0", new Object[] {workpieceId});
	}
}
