package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IWorkSheetDetailDao;
import com.digitzones.model.WorkSheetDetail;
@Repository
public class WorkSheetDetailDaoImpl extends CommonDaoImpl<WorkSheetDetail> implements IWorkSheetDetailDao {

	public WorkSheetDetailDaoImpl() {
		super(WorkSheetDetail.class);
	}
}
