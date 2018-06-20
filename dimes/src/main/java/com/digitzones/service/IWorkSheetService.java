package com.digitzones.service;

import java.util.List;

import com.digitzones.model.WorkSheet;
/**
 * 工单service
 * @author zdq
 * 2018年6月20日
 */
public interface IWorkSheetService extends ICommonService<WorkSheet> {
	/**
	 * 根据设备站点id查询非当前站点下的工单
	 * @param deviceSiteId
	 * @return
	 */
	public List<WorkSheet> queryOtherWorkSheetByDeviceSiteId(Long deviceSiteId);
}
