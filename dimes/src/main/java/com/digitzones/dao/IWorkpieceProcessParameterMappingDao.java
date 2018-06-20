package com.digitzones.dao;

import com.digitzones.model.WorkpieceProcessParameterMapping;
/**
 * 工件工序参数关联dao
 * @author zdq
 * 2018年6月15日
 */
public interface IWorkpieceProcessParameterMappingDao extends ICommonDao<WorkpieceProcessParameterMapping> {
	/**
	 * 根据工件id查询'工件工序站点'数量
	 * @param workpieceId
	 * @return
	 */
	public Long queryCountByWorkpieceId(Long workpieceId);
}
