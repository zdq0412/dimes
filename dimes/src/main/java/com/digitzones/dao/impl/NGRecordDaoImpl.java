package com.digitzones.dao.impl;
import org.springframework.stereotype.Repository;
import com.digitzones.dao.INGRecordDao;
import com.digitzones.model.NGRecord;
@Repository
public class NGRecordDaoImpl extends CommonDaoImpl<NGRecord> implements INGRecordDao {
	public NGRecordDaoImpl() {
		super(NGRecord.class);
	}
}
