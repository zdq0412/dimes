package com.digitzones.dao.impl;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.digitzones.constants.Constant;
import com.digitzones.dao.ILostTimeRecordDao;
import com.digitzones.model.Classes;
import com.digitzones.model.LostTimeRecord;
@SuppressWarnings("deprecation")
@Repository
public class LostTimeRecordDaoImpl extends CommonDaoImpl<LostTimeRecord> implements ILostTimeRecordDao {
	public LostTimeRecordDaoImpl() {
		super(LostTimeRecord.class);
	}

	@Override
	public Double queryHoursOfLostTimeRecordByYearAndMonth(Integer year, Integer month) {
		String hql = "select sum(ltr.sumOfLostTime)/60 from LostTimeRecord ltr where year(ltr.beginTime)=?0 and month(ltr.beginTime)=?1  and ltr.deleted=?2 "
				+ " and ltr.sumOfLostTime!=0 and ltr.planHalt!=?3";
		return  (Double) this.getHibernateTemplate().find(hql, new Object[] {year,month,false,true}).get(0);
	}

	@Override
	public Double queryHoursOfPlanHaltByYearAndMonth(Integer year, Integer month) {
		String hql = "select sum(ltr.sumOfLostTime)/60 from LostTimeRecord ltr where year(ltr.beginTime)=?0 and month(ltr.beginTime)=?1  and ltr.deleted=?2 "
				+ " and ltr.sumOfLostTime!=0 and ltr.planHalt=?3";
		return  (Double) this.getHibernateTemplate().find(hql, new Object[] {year,month,false,true}).get(0);
	}

	@Override
	public Double queryLostTime(Classes c,Long deviceSiteId,Date now) {
		//当前时间
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(now);
		//班次开始时间
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(c.getStartTime());
		startCalendar.set(Calendar.YEAR, nowCalendar.get(Calendar.YEAR));
		startCalendar.set(Calendar.MONTH, nowCalendar.get(Calendar.MONTH)+1);
		startCalendar.set(Calendar.DATE, nowCalendar.get(Calendar.DATE));
		//班次结束时间
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(c.getEndTime());
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
		String sql = "select sum(sumOfLostTime) from LostTimeRecord ltr where ltr.sumOfLostTime!=null and "
				+ " ltr.beginTime>=?0 and ltr.endTime<=?1"
				+ " and ltr.deviceSite_id=?2";
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
		Integer result = (Integer) query.uniqueResult();
		return result!=null?result.doubleValue():0;
	}
	@Override
	public Double queryLostTime4PerDay(Classes c,Long deviceSiteId,Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,23);
		calendar.set(Calendar.MINUTE,59);

		date = calendar.getTime();
		Calendar c2 = Calendar.getInstance();
		if(c.getStartTime().getTime()>c.getEndTime().getTime()) {
			c2.setTime(c.getStartTime());
			c2.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
			c2.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
			c2.set(Calendar.DATE,calendar.get(Calendar.DATE)-1);
		}else {
			c2.setTime(c.getStartTime());
			c2.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
			c2.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
			c2.set(Calendar.DATE,calendar.get(Calendar.DATE));
		}
		Date begin = c2.getTime();

		String hql = "select sum(sumOfLostTime) from LostTimeRecord ltr where ltr.sumOfLostTime!=null and ltr.beginTime>=?0 and ltr.endTime<=?1"
				+ " and ltr.deviceSite.id=?2";
		Double result = (Double) this.getHibernateTemplate().find(hql, new Object[] {begin,date,deviceSiteId}).get(0);
		return result;
	}

	@Override
	public Double queryPlanHaltTime(Classes c, Long deviceSiteId ,Date now) {
		//当前时间
				Calendar nowCalendar = Calendar.getInstance();
				nowCalendar.setTime(now);
				//班次开始时间
				Calendar startCalendar = Calendar.getInstance();
				startCalendar.setTime(c.getStartTime());
				startCalendar.set(Calendar.YEAR, nowCalendar.get(Calendar.YEAR));
				startCalendar.set(Calendar.MONTH, nowCalendar.get(Calendar.MONTH)+1);
				startCalendar.set(Calendar.DATE, nowCalendar.get(Calendar.DATE));
				//班次结束时间
				Calendar endCalendar = Calendar.getInstance();
				endCalendar.setTime(c.getStartTime());
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
				String sql = "select sum(sumOfLostTime) from LostTimeRecord ltr where ltr.sumOfLostTime!=null and "
						+ " ltr.beginTime>=?0 and ltr.endTime<=?1 and ltr.planHalt=?3"
						+ " and ltr.deviceSite_id=?2";
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
				query.setParameter(3,true);
				Integer result = (Integer) query.uniqueResult();
				return result!=null?result.doubleValue():0;
	}

	@Override
	public Integer queryLostTime4RealTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day  = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		String sql = "select SUM(case endTime when null then datediff(minute,beginTime,GETDATE()) else datediff(minute,beginTime,endTime) end) from LOSTTIMERECORD record where record.planHalt=0 "
				+ " and record.lostTimeTypeCode=?0 and year(record.lostTimeTime)=?1 and month(record.lostTimeTime)=?2"
				+ " and day(record.lostTimeTime)=?3 and datename(hh,record.lostTimeTime)=?4 and datename(mi,record.lostTimeTime)=?5"
				+ " and record.deleted=0" ;
		Integer result = (Integer) getSession().createSQLQuery(sql)
					.setParameter(0, Constant.PressLightType.DEVICE)
					.setParameter(1, year)
					.setParameter(2,month)
					.setParameter(3, day)
					.setParameter(4, hour)
					.setParameter(5, minute)
					.uniqueResult();
		if(result == null) {
			return 0;
		}
		return result;
	}

	@Override
	public Integer queryLostTimeFromBeginOfMonthUntilTheDate(Date date,Boolean halt) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND, 0);
		
		Date beginOfMonth = c.getTime();
		String sql = "select SUM(case endTime when null then datediff(minute,beginTime,GETDATE()) else datediff(minute,beginTime,endTime) end) "
				+ "		from LOSTTIMERECORD record where "
				+ "  record.lostTimeTime between ?0 and ?1 "
				+ " and record.deleted=0 " ;
		if(halt!=null) {
			if(halt==true) {
				sql += " and record.planHalt=1";
			}else {
				sql +=" and record.planHalt=0";
			}
		}
		Integer result = (Integer) getSession().createSQLQuery(sql)
					.setParameter(0, beginOfMonth)
					.setParameter(1, date)
					.uniqueResult();
		if(result == null) {
			return 0;
		}
		return result;
	}

	@Override
	public Long queryLostTime4TheDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);
		String hql = "select count(*) from LostTimeRecord record where year(record.lostTimeTime)=?0 and month(record.lostTimeTime)=?1 and day(record.lostTimeTime)=?2";
		return (Long) getHibernateTemplate().find(hql, new Object[] {year,month,day}).get(0);
	}
}
