package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IProcessRecordDao;
import com.digitzones.model.ProcessRecord;
@Repository
public class ProcessRecordDaoImpl extends CommonDaoImpl<ProcessRecord> implements IProcessRecordDao {

	public ProcessRecordDaoImpl() {
		super(ProcessRecord.class);
	}
}
