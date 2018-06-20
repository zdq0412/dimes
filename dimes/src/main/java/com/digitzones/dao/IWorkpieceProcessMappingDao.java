package com.digitzones.dao;

import java.util.List;

import com.digitzones.model.WorkpieceProcessMapping;
/**
 * 工件工序关联dao
 * @author zdq
 * 2018年6月15日
 */
public interface IWorkpieceProcessMappingDao extends ICommonDao<WorkpieceProcessMapping> {
	/**
	 * 根据工件id和工序id删除关联
	 * @param workpieceId
	 * @param processId
	 */
	public void deleteByWorkpieceIdAndProcessId(Long workpieceId,Long processId);
	/**
	 * 根据工件id查询'工件 工序'
	 * @param workpieceId
	 * @return
	 */
	public List<WorkpieceProcessMapping> queryByWorkpieceId(Long workpieceId);
}
