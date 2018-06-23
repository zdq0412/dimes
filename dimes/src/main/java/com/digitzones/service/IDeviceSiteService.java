package com.digitzones.service;

import com.digitzones.model.DeviceSite;
/**
 * 设备站点service
 * @author zdq
 * 2018年6月11日
 */
public interface IDeviceSiteService extends ICommonService<DeviceSite> {
	/**
	 * 查询生产单元内的站点数目
	 * @return
	 */
	public Long queryCountOfDeviceSite();
}
