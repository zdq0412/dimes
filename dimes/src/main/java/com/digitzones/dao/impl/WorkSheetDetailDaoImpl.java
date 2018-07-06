package com.digitzones.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IWorkSheetDetailDao;
import com.digitzones.model.Pager;
import com.digitzones.model.ProcessDeviceSiteMapping;
import com.digitzones.model.WorkSheetDetail;
@Repository
public class WorkSheetDetailDaoImpl extends CommonDaoImpl<WorkSheetDetail> implements IWorkSheetDetailDao {

	public WorkSheetDetailDaoImpl() {
		super(WorkSheetDetail.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<ProcessDeviceSiteMapping> queryDeviceSiteOutOfListByProcessId(List<Long> deviceSiteIdList, Long processId,int pageNo, int pageSize) {
		String hql = "from ProcessDeviceSiteMapping pdsm where pdsm.deviceSite.id not in (:deviceSiteIds) and pdsm.processes.id=:processId";
		
		int startIndex = Pager.getStartOfPage(pageNo, pageSize);
		
		long totalCount = 0;
		List<ProcessDeviceSiteMapping>  list = null;
		
		if(deviceSiteIdList.size()<=0) {
			totalCount = (long) getSession().createQuery("select count(*) from ProcessDeviceSiteMapping pdsm where pdsm.processes.id=:processId")
					.setParameter("processId",processId)
					.list().get(0);
			
			if(totalCount<=0) {
				return new Pager<ProcessDeviceSiteMapping>();
			}
			hql = "from ProcessDeviceSiteMapping pdsm where pdsm.processes.id=:processId";
			list = getSession().createQuery(hql)
					.setParameter("processId",processId)
					.setMaxResults(pageSize)
					.setFirstResult(startIndex)
					.list();
		}else {
			totalCount = (long) getSession().createQuery("select count(*) from ProcessDeviceSiteMapping pdsm where pdsm.deviceSite.id not in (:deviceSiteIds) and pdsm.processes.id=:processId")
					.setParameter("processId",processId)
					.setParameterList("deviceSiteIds", deviceSiteIdList)
					.list().get(0);
			
			if(totalCount<=0) {
				return new Pager<ProcessDeviceSiteMapping>();
			}
			list = getSession().createQuery(hql)
					.setParameter("processId",processId)
					.setParameterList("deviceSiteIds", deviceSiteIdList)
					.setMaxResults(pageSize)
					.setFirstResult(startIndex)
					.list();
		}
		return new Pager<ProcessDeviceSiteMapping>(startIndex,  pageSize, totalCount,list);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Long queryCountByProcessIdAndWorkSheetId(Long processId, Long workSheetID) {
		return (Long) this.getHibernateTemplate().find("select count(*) from WorkSheetDetail wsd where wsd.processId=?0 and wsd.workSheet.id=?1", new Object[] {processId,workSheetID}).get(0);
	}
}
