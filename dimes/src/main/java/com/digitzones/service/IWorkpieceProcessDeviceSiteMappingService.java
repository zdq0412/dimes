package com.digitzones.service;

import com.digitzones.model.Pager;
import com.digitzones.model.WorkpieceProcessDeviceSiteMapping;
/**
 * 工件工序站点service
 * @author zdq
 * 2018年6月15日
 */
public interface IWorkpieceProcessDeviceSiteMappingService extends ICommonService<WorkpieceProcessDeviceSiteMapping> {
	/**
	 * 根据工件id查询'工件工序站点',如果'工件工序站点'中不存在该工件信息，则将该工件相关的工序站点，添加到'工件工序站点'中
	 * @param workpieceId
	 * @param rows
	 * @param page
	 * @return
	 */
	public Pager<WorkpieceProcessDeviceSiteMapping> queryOrAddWorkpieceProcessDeviceSiteMappingByWorkpieceId(Long workpieceId,Integer rows,Integer page);
}
