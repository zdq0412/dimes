package com.digitzones.service;

import com.digitzones.model.EquipmentDeviceSiteMapping;
/**
 * 装备和设备站点关联service
 * @author zdq
 * 2018年6月11日
 */
public interface IMeasuringToolDeviceSiteMappingService extends ICommonService<EquipmentDeviceSiteMapping> {
	/**
	 * 根据序列号查询装备和设备的关联
	 * @param no
	 * @return
	 */
	public EquipmentDeviceSiteMapping queryByNo(String no);
}
