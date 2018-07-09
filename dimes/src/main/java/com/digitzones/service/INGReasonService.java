package com.digitzones.service;

import java.util.List;

import com.digitzones.model.NGReason;
/**
 * 不良原因service
 * @author zdq
 * 2018年6月13日
 */
public interface INGReasonService extends ICommonService<NGReason> {
	/**
	 * 根据不合格类型id查找不合格原因
	 * @param ngReasonTypeId
	 * @return
	 */
	public List<NGReason> queryNGReasonsByNGReasonTypeId(Long ngReasonTypeId);
}
