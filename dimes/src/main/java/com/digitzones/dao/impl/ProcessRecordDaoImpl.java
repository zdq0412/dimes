package com.digitzones.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.digitzones.constants.Constant;
import com.digitzones.dao.IProcessRecordDao;
import com.digitzones.model.ProcessRecord;
@Repository
public class ProcessRecordDaoImpl extends CommonDaoImpl<ProcessRecord> implements IProcessRecordDao {
	public ProcessRecordDaoImpl() {
		super(ProcessRecord.class);
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Long[]> queryCountByDeviceSiteIdAndStatus(Long deviceSiteId, String status) {
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE,0);
		
		Date begin = c.getTime();
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE,59);
		Date end = c.getTime();
		
		String hql = "select pr.workPieceId,pr.processId,pr.deviceSiteId , count(*) from ProcessRecord pr  left join Classes c on pr.classesid=c.id where "
				+ " pr.deviceSiteId=?0 and pr.status=?1  and (pr.collectionDate between ?2 and ?3 ) and " + 
				" (c.beginTime<c.endTime and CONVERT(varchar(100),pr.collectionDate,108) between c.beginTime and c.endTime) or ((c.beginTime>c.endTime ) " + 
				" and ((CONVERT(varchar(100),pr.collectionDate,108) between c.beginTime and '23:59')) or (CONVERT(varchar(100),pr.collectionDate,108) between '00:00' and c.endTime)) " + 
				" group by pr.workPieceId,pr.processId,pr.deviceSiteId ";
		return getSession().createSQLQuery(hql).setParameter(0, deviceSiteId).setParameter(1,status)
				.setParameter(2, begin)
				.setParameter(3,end)
				.list();
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Long[]> queryPreMonthDeviceSiteIdAndStatus(Long deviceSiteId, String status) {
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DATE, 1);
		
		Date dateBegin = c.getTime();
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, -1);
		
		Date dateEnd = c.getTime();
		String hql = "select pr.workPieceId,pr.processId,pr.deviceSiteId ,pr.classesId, count(*) from ProcessRecord pr  left join Classes c on pr.classesid=c.id where "
				+ " pr.deviceSiteId=?0 and pr.status=?1 and collectionDate  between ?2 and ?3" + 
				" group by pr.workPieceId,pr.processId,pr.deviceSiteId ,pr.classesId";
		return getSession().createSQLQuery(hql)
				.setParameter(0, deviceSiteId)
				.setParameter(1,status)
				.setParameter(2,dateBegin)
				.setParameter(3,dateEnd)
				.list();
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Long[]> queryCurrentMonthDeviceSiteIdAndStatus(Long deviceSiteId, String status) {
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.set(Calendar.DATE, 1);
		
		Date dateBegin = c.getTime();
		String hql = "select pr.workPieceId,pr.processId,pr.deviceSiteId ,pr.classesId, count(*) from ProcessRecord pr  left join Classes c on pr.classesid=c.id where "
				+ " pr.deviceSiteId=?0 and pr.status=?1 and collectionDate  between ?2 and ?3" + 
				" group by pr.workPieceId,pr.processId,pr.deviceSiteId ,pr.classesId";
		return getSession().createSQLQuery(hql)
				.setParameter(0, deviceSiteId)
				.setParameter(1,status)
				.setParameter(2,dateBegin)
				.setParameter(3, now)
				.list();
	}
	@SuppressWarnings("deprecation")
	@Override
	public Long queryCountByDeviceSiteIdAndNotNg(Long deviceSiteId) {
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE,0);
		
		Date begin = c.getTime();
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE,59);
		Date end = c.getTime();
		
		String hql = "select  count(*) from ProcessRecord pr  left join Classes c on pr.classesid=c.id where "
				+ " pr.deviceSiteId=?0 and pr.status!=?1  and (pr.collectionDate between ?2 and ?3 ) and (c.beginTime<c.endTime and CONVERT(varchar(100),pr.collectionDate,108) between c.beginTime and c.endTime) or ((c.beginTime>c.endTime ) " + 
				" and ((CONVERT(varchar(100),pr.collectionDate,108) between c.beginTime and '23:59')) or (CONVERT(varchar(100),pr.collectionDate,108) between '00:00' and c.endTime))";
		return ((Integer) getSession().createSQLQuery(hql).setParameter(0, deviceSiteId)
				.setParameter(1,Constant.ProcessRecord.NG)
				.setParameter(2, begin)
				.setParameter(3,end)
				.uniqueResult()).longValue();
	}
	@SuppressWarnings("deprecation")
	@Override
	public Long queryCurrentDayCountByDeviceSiteId(Long deviceSiteId) {
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE,0);
		
		Date begin = c.getTime();
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE,59);
		Date end = c.getTime();
		
		String hql = "select  count(*) from ProcessRecord pr left join Classes c on pr.classesid=c.id where "
				+ " pr.deviceSiteId=?0  and (pr.collectionDate between ?1 and ?2 ) and (c.beginTime<c.endTime and CONVERT(varchar(100),pr.collectionDate,108) between c.beginTime and c.endTime) or ((c.beginTime>c.endTime ) " + 
				" and ((CONVERT(varchar(100),pr.collectionDate,108) between c.beginTime and '23:59')) or (CONVERT(varchar(100),pr.collectionDate,108) between '00:00' and c.endTime))";
		
			Integer result =  (Integer) getSession().createSQLQuery(hql).setParameter(0, deviceSiteId)
					.setParameter(1, begin)
					.setParameter(2,end)
					.uniqueResult();
		return result.longValue();
	}
}
