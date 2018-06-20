package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IPressLightRecordDao;
import com.digitzones.model.PressLightRecord;
@Repository
public class PressLightRecordDaoImpl extends CommonDaoImpl<PressLightRecord> implements IPressLightRecordDao {
	public PressLightRecordDaoImpl() {
		super(PressLightRecord.class);
	}
}
