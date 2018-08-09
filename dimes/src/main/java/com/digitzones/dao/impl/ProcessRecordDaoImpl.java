package com.digitzones.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.digitzones.constants.Constant;
import com.digitzones.dao.IProcessRecordDao;
import com.digitzones.model.Classes;
import com.digitzones.model.ProcessRecord;
@Repository
public class ProcessRecordDaoImpl extends CommonDaoImpl<ProcessRecord> implements IProcessRecordDao {
	public ProcessRecordDaoImpl() {
		super(ProcessRecord.class);
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
				+ " pr.deviceSiteId=?0 and pr.status!=?1  and (pr.collectionDate between ?2 and ?3 ) and (c.beginTime<c.endTime and CONVERT(varchar(100),pr.collectionDate,108) >= CONVERT(varchar(100),c.beginTime,108) and CONVERT(varchar(100),pr.collectionDate,108)<=CONVERT(varchar(100),c.endTime,108)) or ((c.beginTime>c.endTime )" + 
				" and ((CONVERT(varchar(100),pr.collectionDate,108) >= CONVERT(varchar(100),c.beginTime,108) and CONVERT(varchar(100),pr.collectionDate,108)<='23:59')) or (CONVERT(varchar(100),pr.collectionDate,108) >= '00:00' and CONVERT(varchar(100),pr.collectionDate,108)<=CONVERT(varchar(100),c.endTime,108))) ";
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
				+ " pr.deviceSiteId=?0  and (pr.collectionDate between ?1 and ?2 ) and (c.beginTime<c.endTime and CONVERT(varchar(100),pr.collectionDate,108) >= CONVERT(varchar(100),c.beginTime,108) and CONVERT(varchar(100),pr.collectionDate,108)<=CONVERT(varchar(100),c.endTime,108)) or ((c.beginTime>c.endTime ) " + 
				" and ((CONVERT(varchar(100),pr.collectionDate,108) >= CONVERT(varchar(100),c.beginTime,108) and CONVERT(varchar(100),pr.collectionDate,108)<='23:59')) or (CONVERT(varchar(100),pr.collectionDate,108) >= '00:00' and CONVERT(varchar(100),pr.collectionDate,108)<=CONVERT(varchar(100),c.endTime,108)))";

		Integer result =  (Integer) getSession().createSQLQuery(hql).setParameter(0, deviceSiteId)
				.setParameter(1, begin)
				.setParameter(2,end)
				.uniqueResult();
		return result.longValue();
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Long[]> queryByDay(Long deviceSiteId, String status, Date now) {
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
				" (c.beginTime<c.endTime and CONVERT(varchar(100),pr.collectionDate,108) >= CONVERT(varchar(100),c.beginTime,108) and CONVERT(varchar(100),pr.collectionDate,108)<=CONVERT(varchar(100),c.endTime,108)) or ((c.beginTime>c.endTime ) " + 
				" and ((CONVERT(varchar(100),pr.collectionDate,108) >= CONVERT(varchar(100),c.beginTime,108) and CONVERT(varchar(100),pr.collectionDate,108)<='23:59')) or (CONVERT(varchar(100),pr.collectionDate,108) >= '00:00' and CONVERT(varchar(100),pr.collectionDate,108)<=CONVERT(varchar(100),c.endTime,108))) " + 
				" group by pr.workPieceId,pr.processId,pr.deviceSiteId ";
		return getSession().createSQLQuery(hql).setParameter(0, deviceSiteId).setParameter(1,status)
				.setParameter(2, begin)
				.setParameter(3,end)
				.list();
	}
	@Override
	public Integer queryOutput4EmployeePerMonth(int year,int month,Long empId) {
		String hql = "select  COUNT(id) from ProcessRecord pr "
				+ "where year(pr.collectionDate)=?0 and month(pr.collectionDate)=?1  and pr.productUserId=?2";
		@SuppressWarnings({ "rawtypes", "deprecation" })
		List list = getSession().createSQLQuery(hql)
					.setParameter(0, year)
					.setParameter(1, month)
					.setParameter(2, empId)
					.list();
		if(list!=null&&list.size()>0) {
			return  (Integer) list.get(0);
		}
		return 0;
	}
	@Override
	public Integer queryOutput4ProcessPerMonth(int year, int month,Long processId) {
		String hql = "select  COUNT(id) from ProcessRecord pr "
				+ "where year(pr.collectionDate)=?0 and month(pr.collectionDate)=?1 and pr.processId=?2";
		@SuppressWarnings({ "rawtypes", "deprecation" })
		List list = getSession().createSQLQuery(hql)
					.setParameter(0, year)
					.setParameter(1, month)
					.setParameter(2,processId)
					.list();
		if(list!=null&&list.size()>0) {
			return  ((Integer) list.get(0)).intValue();
		}
		return 0;
	}
	@Override
	public Integer queryOutput4DeviceSitePerMonth(int year, int month,Long deviceSiteId) {
		String hql = "select COUNT(id) from ProcessRecord pr "
				+ "where year(pr.collectionDate)=?0 and month(pr.collectionDate)=?1 and pr.deviceSiteId=?2";
		@SuppressWarnings({ "rawtypes", "deprecation" })
		List list = getSession().createSQLQuery(hql)
								.setParameter(0, year)
								.setParameter(1, month)
								.setParameter(2, deviceSiteId)
								.list();
		if(list!=null&&list.size()>0) {
			return (Integer) list.get(0);
		}
		return 0;
	}
	@SuppressWarnings("deprecation")
	@Override
	public Integer queryWorkSheetNGCountPerMonth(int year, int month) {
		String sql = "select sum(method.ngCount) from PROCESSRECORD pr inner join NGRECORD ng  on pr.no = ng.no"
				+ " inner join NGPROCESSMETHOD method on method.NGRECORD_ID=ng.id "
				+ " inner join WORKSHEET ws on pr.no = ws.no "
				+ " where year(pr.collectionDate)=?0 and month(pr.collectionDate)=?1  and method.processMethod!=?2";
		@SuppressWarnings("rawtypes")
		List list = getSession().createSQLQuery(sql)
								.setParameter(0, year)
								.setParameter(1, month)
								.setParameter(2, Constant.ProcessRecord.COMPROMISE)
								.list();
		if(list!=null&&list.size()>0) {
			return (Integer) list.get(0);
		}
		return 0;
	}
	@SuppressWarnings("deprecation")
	@Override
	public Integer queryWorkSHeetNotNGCountPerMonth(int year, int month) {
		String sql = "select COUNT(pr.id) from PROCESSRECORD pr inner join WORKSHEET ws on pr.no = ws.no "
				+ " where ws.workSheetType=?2 and pr.no not in " + 
				" (select no from NGRECORD)"
				+ " and year(pr.collectionDate)=?0 and month(pr.collectionDate)=?1";
		@SuppressWarnings("rawtypes")
		List list = getSession().createSQLQuery(sql)
								.setParameter(0, year)
								.setParameter(1, month)
								.setParameter(2, Constant.WorkSheet.COMMON)
								.list();
		if(list!=null&&list.size()>0) {
			return (Integer) list.get(0);
		}
		return 0;
	}
	@Override
	public Integer queryCountByClassesIdAndDay(Long classesId, Date day,Long productionUnitId) {
		Calendar c = Calendar.getInstance() ;
		c.setTime(day);
		String sql = "select COUNT(pr.id) from PROCESSRECORD pr inner join DEVICESITE site on pr.deviceSiteId=site.id"
				+ " inner join Device d on site.device_id = d.id "
				+ " where year(pr.collectionDate)=?0 and month(pr.collectionDate)=?1"
				+ " and day(pr.collectionDate)=?2 and pr.classesId=?3 and d.productionunit_id=?4";
		
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DAY_OF_MONTH);
		@SuppressWarnings({ "rawtypes", "deprecation" })
		List list = getSession().createSQLQuery(sql)
								.setParameter(0, year)
								.setParameter(1, month)
								.setParameter(2, date)
								.setParameter(3,classesId)
								.setParameter(4, productionUnitId)
								.list();
		if(list!=null&&list.size()>0) {
			return (Integer) list.get(0);
		}
		return 0;
	}
	@Override
	public Integer queryWorkSheetNGCountPerClasses4ProductionUnit(int year, int month, int day, Long classId,
			Long productionUnitId) {
		String sql = "select sum(method.ngCount) from PROCESSRECORD pr inner join NGRECORD ng on pr.no = ng.no "
				+ " inner join NGPROCESSMETHOD method on method.NGRECORD_ID=ng.id "
				+ " inner join DEVICESITE ds on pr.deviceSiteCode=ds.code "
				+ " inner join DEVICE d on ds.DEVICE_ID=d.id "
				+ " inner join PRODUCTIONUNIT p on d.PRODUCTIONUNIT_ID=p.id "  
				+ " where year(pr.collectionDate)=?0 and month(pr.collectionDate)=?1"
				+ " and day(pr.collectionDate)=?2 and pr.classesId=?3 and p.id=?4";
		
		@SuppressWarnings({ "rawtypes", "deprecation" })
		List list = getSession().createSQLQuery(sql)
								.setParameter(0, year)
								.setParameter(1, month)
								.setParameter(2, day)
								.setParameter(3,classId)
								.setParameter(4, productionUnitId)
								.list();
		if(list!=null&&list.size()>0) {
			return (Integer) list.get(0);
		}
		return 0;
	}
	@Override
	public Integer queryWorkSHeetNotNGCountPerClasses4ProductionUnit(int year, int month, int day, Long classId,
			Long productionUnitId) {
		String sql = "select COUNT(*) from PROCESSRECORD pr  inner join worksheet ws on pr.no=ws.no "
				+ " inner join DEVICESITE ds on pr.deviceSiteCode=ds.code "
				+ " inner join DEVICE d on ds.DEVICE_ID=d.id "
				+ " inner join PRODUCTIONUNIT p on d.PRODUCTIONUNIT_ID=p.id "  
				+ " where year(pr.collectionDate)=?0 and month(pr.collectionDate)=?1"
				+ " and day(pr.collectionDate)=?2 and pr.classesId=?3 and p.id=?4 and ws.worksheettype=?5"
				+ " and pr.no not in (select ng.no  from ngrecord ng)";
		
		@SuppressWarnings({ "rawtypes", "deprecation" })
		List list = getSession().createSQLQuery(sql)
								.setParameter(0, year)
								.setParameter(1, month)
								.setParameter(2, day)
								.setParameter(3,classId)
								.setParameter(4, productionUnitId)
								.setParameter(5, Constant.WorkSheet.COMMON)
								.list();
		if(list!=null&&list.size()>0) {
			return (Integer) list.get(0);
		}
		return 0;
	}
	@SuppressWarnings("deprecation")
	@Override
	public Integer queryWorkSheetScrapCountPerMonth(int year, int month,Long ngTypeId) {
		String sql = "select sum(method.ngCount) from PROCESSRECORD pr inner join NGRECORD ng  on pr.no = ng.no "
				+ " inner join NGPROCESSMETHOD method on method.NGRECORD_ID=ng.id "
				+ " inner join WORKSHEET ws on pr.no = ws.no "
				+ " where year(pr.collectionDate)=?0 and month(pr.collectionDate)=?1 "
				+ " and method.processMethod=?2 and ng.ngTypeId=?3";
		@SuppressWarnings("rawtypes")
		List list = getSession().createSQLQuery(sql)
								.setParameter(0, year)
								.setParameter(1, month)
								.setParameter(2,Constant.ProcessRecord.SCRAP)
								.setParameter(3,ngTypeId)
								.list();
		if(list!=null&&list.size()>0) {
			return (Integer) list.get(0);
		}
		return 0;
	}
	@SuppressWarnings("deprecation")
	@Override
	public Integer queryWorkSheetScrapCountPerMonth(int year, int month) {
		String sql = "select sum(method.ngCount) from PROCESSRECORD pr inner join NGRECORD ng  on pr.no = ng.no "
				+ " inner join NGPROCESSMETHOD method on method.NGRECORD_ID=ng.id "
				+ " inner join WORKSHEET ws on pr.no = ws.no "
				+ " where year(pr.collectionDate)=?0 and month(pr.collectionDate)=?1 "
				+ " and method.processMethod=?2";
		@SuppressWarnings("rawtypes")
		List list = getSession().createSQLQuery(sql)
								.setParameter(0, year)
								.setParameter(1, month)
								.setParameter(2,Constant.ProcessRecord.SCRAP)
								.list();
		if(list!=null&&list.size()>0) {
			return (Integer) list.get(0);
		}
		return 0;
	}
	@SuppressWarnings({ "deprecation"})
	@Override
	public Object[] queryCountAndSumOfStandardBeatAndSumOfShortHalt4CurrentClass(Classes classes, Long deviceSiteId,Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE,0);

		Date begin = c.getTime();
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE,59);
		Date end = c.getTime();

		String sql = "select count(pr.id) _count,sum(pr.standardBeat) sumStandardBeat,sum(pr.realBeat-pr.standardBeat) from ProcessRecord pr  inner join Classes c on pr.classesid=c.id where "
				+ " pr.deviceSiteId=?0 and c.id=?1  and (pr.collectionDate between ?2 and ?3 ) and " + 
				" (c.beginTime<c.endTime and CONVERT(varchar(100),pr.collectionDate,108) >= CONVERT(varchar(100),c.beginTime,108) and CONVERT(varchar(100),pr.collectionDate,108)<=CONVERT(varchar(100),c.endTime,108)) or ((c.beginTime>c.endTime ) " + 
				" and ((CONVERT(varchar(100),pr.collectionDate,108) >= CONVERT(varchar(100),c.beginTime,108) and CONVERT(varchar(100),pr.collectionDate,108)<='23:59')) or "
				+ " (CONVERT(varchar(100),pr.collectionDate,108) >= '00:00' and CONVERT(varchar(100),pr.collectionDate,108)<=CONVERT(varchar(100),c.endTime,108))) "; 
		Object[] list = (Object[]) getSession().createSQLQuery(sql)
				.setParameter(0, deviceSiteId)
				.setParameter(1,classes.getId())
				.setParameter(2, begin)
				.setParameter(3,end)
				.uniqueResult();
		
		return list;
	}
	@SuppressWarnings("deprecation")
	@Override
	public Object[] queryCountAndSumOfStandardBeatAndSumOfShortHaltFromBeginOfMonthUntilTheDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND, 0);
		
		Date beginOfMonth = c.getTime();
		String sql = "select count(pr.id) _count,sum(pr.standardBeat) sumStandardBeat,sum(pr.realBeat-pr.standardBeat) "
				+ " from ProcessRecord pr  where  pr.collectionDate between ?0 and ?1 "; 
		Object[] list = (Object[]) getSession().createSQLQuery(sql)
												.setParameter(0, beginOfMonth)
												.setParameter(1, date)
												.uniqueResult();
		
		return list;
	}
}
