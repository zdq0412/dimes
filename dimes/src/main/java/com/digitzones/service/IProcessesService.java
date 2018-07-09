package com.digitzones.service;

import java.util.List;

import com.digitzones.model.Processes;
/**
 * 工序service
 * @author zdq
 * 2018年6月14日
 */
public interface IProcessesService extends ICommonService<Processes> {
	/**
	 * 根据工件id和设备站点id查找工序
	 * @param workpieceId
	 * @param deviceSiteId
	 * @return
	 */
	public List<Processes> queryProcessByWorkpieceIdAndDeviceSiteId(Long workpieceId,Long deviceSiteId);

}
