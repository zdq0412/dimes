package com.digitzones.service;

import com.digitzones.model.ProcessDeviceSiteMapping;
/**
 * 工序和设备站点关联service
 * @author zdq
 * 2018年6月14日
 */
public interface IProcessDeviceSiteMappingService extends ICommonService<ProcessDeviceSiteMapping> {
	/**
	 * 根据工序id和设备站点id删除关联
	 * @param processesId 工序id
	 * @param deviceSiteId 设备站点id
	 */
	public void deleteByProcessesIdAndDeviceSiteId(Long processesId,Long deviceSiteId);
}
