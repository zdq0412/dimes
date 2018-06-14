package com.digitzones.service;

import com.digitzones.model.Device;
/**
 * 设备service
 * @author zdq
 * 2018年6月11日
 */
public interface IDeviceService extends ICommonService<Device> {
	/**
	 * 根据设备站点查找设备
	 * @param deviceSiteId
	 * @return
	 */
	public Device queryDeviceByDeviceSiteId(Long deviceSiteId);
}
