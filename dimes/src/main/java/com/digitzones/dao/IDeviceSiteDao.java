package com.digitzones.dao;

import java.util.List;

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
	/**
	 * 根据站点状态查询
	 * @param status
	 * @return
	 */
	public Long queryCountOfDeviceSiteByStatus(String status);
	/**
	 * 根据班次id查询设备站点
	 * @param classesId
	 * @return
	 */
	public List<DeviceSite> queryDeviceSitesByClassesId(Long classesId);
}
