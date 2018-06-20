package com.digitzones.service;

import com.digitzones.model.WorkpieceProcessMapping;
/**
 * 工件工序关联service
 * @author zdq
 * 2018年6月15日
 */
public interface IWorkpieceProcessMappingService extends ICommonService<WorkpieceProcessMapping> {
	/**
	 * 根据工件id和工序id删除关联
	 * @param workpieceId
	 * @param processId
	 */
	public void deleteByWorkpieceIdAndProcessId(Long workpieceId,Long processId);
	/**
	 * 根据工序和工件id查找关联对象
	 * @param workpieceId
	 * @param processId
	 * @return
	 */
	public WorkpieceProcessMapping queryByWorkpieceIdAndProcessId(Long workpieceId,Long processId);
}
