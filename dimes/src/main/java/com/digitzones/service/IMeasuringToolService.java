package com.digitzones.service;

import java.util.List;

import com.digitzones.model.Equipment;
/**
 * 装备service
 * @author zdq
 * 2018年6月11日
 */
public interface IMeasuringToolService extends ICommonService<Equipment> {
	/**
	 * 查询所有量具
	 * @return
	 */
	public List<Equipment> queryAllMeasuringTools();
	/**
	 * 根据量具编码或名称或规格型号查询量具信息
	 * @param value
	 * @return
	 */
	public List<Equipment> queryMeasuringToolsByCodeOrNameOrUnity(String value);
}
