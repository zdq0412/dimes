package com.digitzones.service;

import java.util.List;

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
	/**
	 * 根据生产单元id查找设备
	 * @param productionUnitId
	 * @return
	 */
	public List<Device> queryDevicesByProductionUnitId(Long productionUnitId);
	
}
