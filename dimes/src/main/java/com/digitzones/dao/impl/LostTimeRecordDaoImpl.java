package com.digitzones.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.ILostTimeRecordDao;
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
	public Double queryLostTimeByTime(Date begin, Date end) {
		String hql = "select sum(sumOfLostTime) from LostTimeRecord ltr where ltr.sumOfLostTime!=null and ltr.beginTime>?0 and ltr.endTime<=?1";
		return (Double) this.getHibernateTemplate().find(hql, new Object[] {begin,end}).get(0);
	}
}
