package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.ILostTimeRecordDao;
import com.digitzones.model.LostTimeRecord;
@Repository
public class LostTimeRecordDaoImpl extends CommonDaoImpl<LostTimeRecord> implements ILostTimeRecordDao {
	public LostTimeRecordDaoImpl() {
		super(LostTimeRecord.class);
	}
}
