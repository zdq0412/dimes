package com.digitzones.dao;

import java.util.Date;

import com.digitzones.model.NGRecord;
/**
 * 不合格品记录dao
 * @author zdq
 * 2018年7月8日
 */
public interface INGRecordDao extends ICommonDao<NGRecord> {
	/**
	 * 根据工序id和日期（天）查找报废品数量 
	 * @param date
	 * @param processId
	 * @return
	 */
	public Integer queryScrapCountByDateAndProcessId(Date date,Long processId);
}
