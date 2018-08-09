package com.digitzones.dao.impl;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.digitzones.constants.Constant;
import com.digitzones.dao.INGRecordDao;
import com.digitzones.model.Classes;
import com.digitzones.model.NGRecord;
@SuppressWarnings("deprecation")
@Repository
public class NGRecordDaoImpl extends CommonDaoImpl<NGRecord> implements INGRecordDao {
	public NGRecordDaoImpl() {
		super(NGRecord.class);
	}

	@Override
	public Integer queryScrapCountByDateAndProcessId(Date date, Long processId) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		String sql = "select SUM(method.ngCount) from NGRECORD record inner join NGPROCESSMETHOD method on record.id = method.NGRECORD_ID" 
				+ " where year(record.occurDate)=?0 and month(record.occurDate)=?1 and day(occurDate)=?2"
				+ " and method.processMethod=?3 and record.processId=?4";
		
		Integer result = (Integer) getSession().createSQLQuery(sql)
					.setParameter(0, year)
					.setParameter(1, month)
					.setParameter(2, day)
					.setParameter(3, Constant.ProcessRecord.SCRAP)
					.setParameter(4, processId)
					.uniqueResult();
		return result!=null?result:0;
	}

	@Override
	public Integer queryNgCountByDeviceSiteId(Long deviceSiteId, Date today) {
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		String sql = "select SUM(method.ngCount) from NGRECORD record inner join NGPROCESSMETHOD method "
				+ " on record.id=method.NGRECORD_ID where record.deviceSiteId=?3 "
				+ "and year(record.occurDate)=?0 and month(record.occurDate)=?1 and day(occurDate)=?2";
		
		Integer result = (Integer) getSession().createSQLQuery(sql)
					.setParameter(0, year)
					.setParameter(1, month)
					.setParameter(2, day)
					.setParameter(3, deviceSiteId)
					.uniqueResult();
		return result!=null?result:0;
	}

	@Override
	public Integer queryNgCount4Class(Classes classes, Long deviceSiteId,Date now) {
		//当前时间
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(now);
		//班次开始时间
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(classes.getStartTime());
		startCalendar.set(Calendar.YEAR, nowCalendar.get(Calendar.YEAR));
		startCalendar.set(Calendar.MONTH, nowCalendar.get(Calendar.MONTH)+1);
		startCalendar.set(Calendar.DATE, nowCalendar.get(Calendar.DATE));
		//班次结束时间
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(classes.getStartTime());
		endCalendar.set(Calendar.YEAR, nowCalendar.get(Calendar.YEAR));
		endCalendar.set(Calendar.MONTH, nowCalendar.get(Calendar.MONTH)+1);
		endCalendar.set(Calendar.DATE, nowCalendar.get(Calendar.DATE));
		
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();
		//午夜十二点
		Calendar midnightCalendar = Calendar.getInstance();
		midnightCalendar.setTime(now);
		midnightCalendar.set(Calendar.HOUR_OF_DAY, 23);
		midnightCalendar.set(Calendar.MINUTE, 59);
		midnightCalendar.set(Calendar.SECOND, 59);
		
		Date midnightDate = midnightCalendar.getTime();
		String sql = "select SUM(method.ngCount) from NGRECORD record inner join NGPROCESSMETHOD method "
				+ " on record.id=method.NGRECORD_ID where record.deviceSiteId=?2 "
				+ "and record.occurDate>=?0 and record.occurDate<?1 and record.no!=null and method.processMethod!=?3";
		
		@SuppressWarnings("unchecked")
		SQLQuery<Integer> query = getSession().createSQLQuery(sql);
		//班次开始时间小于结束时间
		if(startDate.getTime()<endDate.getTime()) {
			query.setParameter(0, startDate)
				.setParameter(1, now);
		}else {//开始时间》结束时间，说明跨天
			if(now.getTime()>startDate.getTime() && now.getTime()<=midnightDate.getTime()) {
				query.setParameter(0, startDate)
					.setParameter(1, now);
			}
			
			if(now.getTime()<endDate.getTime()) {
				query.setParameter(0, midnightDate)
					.setParameter(1,now);
			}
		}
		
		query.setParameter(2, deviceSiteId);
		query.setParameter(3, Constant.ProcessRecord.COMPROMISE);
		Integer result = (Integer) query.uniqueResult();
		return result!=null?result:0;
	}

	@Override
	public Integer queryNgCountFromBeginOfMonthUntilTheDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND, 0);
		Date beginOfMonth = c.getTime();
		String sql = "select SUM(method.ngCount) from NGRECORD record inner join NGPROCESSMETHOD method "
				+ " on record.id=method.NGRECORD_ID where  record.occurDate between ?0 and ?1 and record.no!=null and method.processMethod!=?2";
		Integer result = (Integer) getSession().createSQLQuery(sql)
									.setParameter(0, beginOfMonth)
									.setParameter(1, date)
									.setParameter(2, Constant.ProcessRecord.COMPROMISE)
									.uniqueResult();
		return result!=null?result:0;
	}

	@Override
	public Long queryNgCount4TheDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);
		String hql = "select sum(method.ngCount) from NGProcessMethod method inner join method.ngRecord record "
				+ " where year(record.occurDate)=?0 and month(record.occurDate)=?1 and day(record.occurDate)=?2";
		return (Long) getHibernateTemplate().find(hql, new Object[] {year,month,day}).get(0);
	}
}
