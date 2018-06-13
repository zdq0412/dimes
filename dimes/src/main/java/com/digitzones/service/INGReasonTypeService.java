package com.digitzones.service;

import java.io.Serializable;
import java.util.List;

import com.digitzones.model.NGReasonType;
/**
 * 不良原因类型service
 * @author zdq
 * 2018年6月13日
 */
public interface INGReasonTypeService extends ICommonService<NGReasonType> {
	/**
	 * 根据父不良原因类型id查询子类型数量
	 * @param pid
	 * @return
	 */
	public Long queryCountOfSubNGReasonType(Serializable pid);
	/**
	 * 查找顶层不良原因类型
	 * @return
	 */
	public List<NGReasonType> queryTopNGReasonType();
}
