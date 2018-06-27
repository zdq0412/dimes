package com.digitzones.dao;

import com.digitzones.model.Device;
/**
 * 设备dao
 * @author zdq
 * 2018年6月11日
 */
public interface IDeviceDao extends ICommonDao<Device> {
	/**
	 * 根据生产单元id查找最大目标oee
	 * @param productionUnitId
	 * @return
	 */
	public double queryOeeByProductionUnitId(Long productionUnitId);
}
