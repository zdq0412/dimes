package com.digitzones.service;

import java.io.File;
import java.io.Serializable;
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
	/**
	 * 根据量具编码或名称或规格型号查询装备信息
	 * @param value
	 * @return
	 */
	public List<Equipment> queryEquipmentsByCodeOrNameOrUnity(String value);
	/**
	 * 添加装备
	 * @param equipment
	 * @param pic 装备图片文件
	 * @return
	 */
	public Serializable addEquipment(Equipment equipment,File pic);
}
