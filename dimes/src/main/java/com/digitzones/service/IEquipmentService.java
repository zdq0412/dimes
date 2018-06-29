package com.digitzones.service;

import java.util.List;

import com.digitzones.model.Equipment;
/**
 * 装备service
 * @author zdq
 * 2018年6月11日
 */
public interface IEquipmentService extends ICommonService<Equipment> {
	/**
	 * 查询所有装备
	 * @return
	 */
	public List<Equipment> queryAllEquipments();
}
