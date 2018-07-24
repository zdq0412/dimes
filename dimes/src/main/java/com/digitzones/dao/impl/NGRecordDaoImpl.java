package com.digitzones.dao.impl;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.digitzones.constants.Constant;
import com.digitzones.dao.INGRecordDao;
import com.digitzones.model.NGRecord;
@Repository
public class NGRecordDaoImpl extends CommonDaoImpl<NGRecord> implements INGRecordDao {
	public NGRecordDaoImpl() {
		super(NGRecord.class);
	}

	@SuppressWarnings("deprecation")
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

	@SuppressWarnings("deprecation")
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
}
