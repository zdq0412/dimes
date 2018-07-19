package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IQualityCalendarRecordDao;
import com.digitzones.model.QualityCalendarRecord;
@Repository
public class QualityCalendarRecordDaoImpl extends CommonDaoImpl<QualityCalendarRecord> implements IQualityCalendarRecordDao {
	public QualityCalendarRecordDaoImpl() {
		super(QualityCalendarRecord.class);
	}
}
