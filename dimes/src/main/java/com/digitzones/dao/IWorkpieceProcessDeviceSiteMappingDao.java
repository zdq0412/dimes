package com.digitzones.dao;

import com.digitzones.model.WorkpieceProcessDeviceSiteMapping;
/**
 * 工件工序站点关联dao
 * @author zdq
 * 2018年6月15日
 */
public interface IWorkpieceProcessDeviceSiteMappingDao extends ICommonDao<WorkpieceProcessDeviceSiteMapping> {
	/**
	 * 根据工件id查询'工件工序站点'数量
	 * @param workpieceId
	 * @return
	 */
	public Long queryCountByWorkpieceId(Long workpieceId);
}