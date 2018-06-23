package com.digitzones.dao;

import com.digitzones.model.DeviceSite;
/**
 * 设备站点dao
 * @author zdq
 * 2018年6月11日
 */
public interface IDeviceSiteDao extends ICommonDao<DeviceSite> {
	/**
	 * 查询生产单元内的站点数目
	 * @return
	 */
	public Long queryCountOfDeviceSite();
}
