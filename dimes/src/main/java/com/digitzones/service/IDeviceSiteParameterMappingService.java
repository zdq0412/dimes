package com.digitzones.service;

import java.util.List;

import com.digitzones.model.DeviceSiteParameterMapping;
/**
 * 站点和参数关联service
 * @author zdq
 * 2018年6月26日
 */
public interface IDeviceSiteParameterMappingService extends ICommonService<DeviceSiteParameterMapping> {
	/**
	 * 根据设备站点查询设备参数
	 * @param deviceSiteId
	 * @return
	 */
	public List<DeviceSiteParameterMapping> queryByDeviceSiteId(Long deviceSiteId);
}
