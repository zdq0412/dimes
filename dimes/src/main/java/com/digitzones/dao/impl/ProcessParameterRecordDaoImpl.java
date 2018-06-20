package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IProcessParameterRecordDao;
import com.digitzones.model.ProcessParameterRecord;
@Repository
public class ProcessParameterRecordDaoImpl extends CommonDaoImpl<ProcessParameterRecord> implements IProcessParameterRecordDao {
	public ProcessParameterRecordDaoImpl() {
		super(ProcessParameterRecord.class);
	}
}
