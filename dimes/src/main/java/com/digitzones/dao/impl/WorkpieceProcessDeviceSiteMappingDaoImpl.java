package com.digitzones.dao.impl;

import java.util.List;

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

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public Float queryProcessingBeatByProductionUnitId(Long productionUnitId) {
		String sql = "select processingBeat from WORKPIECEPROCESS_DEVICESITE wpd inner join DEVICESITE ds on wpd.DEVICESITE_ID = ds.id inner join DEVICE d on ds.DEVICE_ID=d.id where d.PRODUCTIONUNIT_ID=?0  and d.bottleneck=1";
		List<Float> processingBeats = (List<Float>) getSession().createSQLQuery(sql).setParameter(0, productionUnitId).list();
		if(processingBeats!=null&&processingBeats.size()>0) {
			return processingBeats.get(0)==null?0:processingBeats.get(0);
		}
		return 0f;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public Float queryProcessingBeatByClassesId(Long classesId) {
		String sql = "select processingBeat from WORKPIECEPROCESS_DEVICESITE wpd inner join DEVICESITE ds on wpd.DEVICESITE_ID = ds.id inner join DEVICE d on ds.DEVICE_ID=d.id inner join CLASSES_DEVICE cd on d.id=cd.DEVICE_ID inner join CLASSES c on c.id=cd.CLASSES_ID where  d.bottleneck=1 and c.id=?0";
		List<Float> processingBeats = (List<Float>) getSession().createSQLQuery(sql).setParameter(0, classesId).list();
		if(processingBeats!=null&&processingBeats.size()>0) {
			return processingBeats.get(0)==null?0:processingBeats.get(0);
		}
		return 0f;
	}

	@Override
	public Float queryProcessingBeat(Long workPieceId, Long processId, Long deviceSiteId) {
		String hql = "select wpdsm.processingBeat from WorkpieceProcessDeviceSiteMapping wpdsm where wpdsm.workpieceProcess.workpiece.id=?0"
				+ " and wpdsm.workpieceProcess.process.id=?1 and wpdsm.deviceSite.id=?2" ;
		return (Float) this.getSession().createQuery(hql)
								.setParameter(0, workPieceId)
								.setParameter(1, processId)
								.setParameter(2, deviceSiteId)
								.uniqueResult();
	}
}
