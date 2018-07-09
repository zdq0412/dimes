package com.digitzones.dao.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.ILostTimeRecordDao;
import com.digitzones.model.Classes;
import com.digitzones.model.LostTimeRecord;
@Repository
public class LostTimeRecordDaoImpl extends CommonDaoImpl<LostTimeRecord> implements ILostTimeRecordDao {
	public LostTimeRecordDaoImpl() {
		super(LostTimeRecord.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Double queryHoursOfLostTimeRecordByYearAndMonth(Integer year, Integer month) {
		String hql = "select sum(ltr.sumOfLostTime)/60 from LostTimeRecord ltr where year(ltr.beginTime)=?0 and month(ltr.beginTime)=?1  and ltr.deleted=?2 "
				+ " and ltr.sumOfLostTime!=0 and ltr.planHalt!=?3";
		return  (Double) this.getHibernateTemplate().find(hql, new Object[] {year,month,false,true}).get(0);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Double queryHoursOfPlanHaltByYearAndMonth(Integer year, Integer month) {
		String hql = "select sum(ltr.sumOfLostTime)/60 from LostTimeRecord ltr where year(ltr.beginTime)=?0 and month(ltr.beginTime)=?1  and ltr.deleted=?2 "
				+ " and ltr.sumOfLostTime!=0 and ltr.planHalt=?3";
		return  (Double) this.getHibernateTemplate().find(hql, new Object[] {year,month,false,true}).get(0);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Double queryLostTime(Classes c,Long deviceSiteId,Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();

		if(c!=null &&c.getStartTime()!=null) {
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
	}
	Date begin = c2.getTime();
	c3.setTime(c.getEndTime());
	c3.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
	c3.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
	c3.set(Calendar.DATE,calendar.get(Calendar.DATE));

	Date end = c3.getTime();
	String hql = "select sum(sumOfLostTime) from LostTimeRecord ltr where ltr.sumOfLostTime!=null and ltr.beginTime>=?0 and ltr.endTime<=?1"
			+ " and ltr.deviceSite.id=?2";
	return (Double) this.getHibernateTemplate().find(hql, new Object[] {begin,end,deviceSiteId}).get(0);
}
@SuppressWarnings("deprecation")
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

@SuppressWarnings("deprecation")
@Override
public Double queryPlanHaltTime(Classes c, Long deviceSiteId ,Date now) {
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(now);
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
	String hql = "select sum(sumOfLostTime) from LostTimeRecord ltr where ltr.sumOfLostTime!=null and ltr.beginTime>?0 and ltr.endTime<=?1"
			+ " and ltr.deviceSite.id=?2 and planHalt=?3";
	return (Double) this.getHibernateTemplate().find(hql, new Object[] {begin,now,deviceSiteId,true}).get(0);
}
}
