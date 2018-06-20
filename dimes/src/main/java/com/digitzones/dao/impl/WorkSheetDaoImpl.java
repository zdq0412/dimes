package com.digitzones.dao.impl;
import org.springframework.stereotype.Repository;
import com.digitzones.dao.IWorkSheetDao;
import com.digitzones.model.WorkSheet;
@Repository
public class WorkSheetDaoImpl extends CommonDaoImpl<WorkSheet> implements IWorkSheetDao {
	public WorkSheetDaoImpl() {
		super(WorkSheet.class);
	}
}
