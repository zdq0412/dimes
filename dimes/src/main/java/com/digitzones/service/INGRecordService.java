package com.digitzones.service;

import com.digitzones.model.NGRecord;
/**
 * 不合格记录service
 * @author zdq
 * 2018年7月8日
 */
public interface INGRecordService extends ICommonService<NGRecord> {
	/**
	 * 审核不合格记录，会影响工单详情中的报废和返修数量
	 * @param record
	 */
	public void auditNGRecord(NGRecord record);
}
