package com.digitzones.dao;

import java.io.File;
import java.io.Serializable;

import com.digitzones.model.Equipment;
/**
 * 装备dao
 * @author zdq
 * 2018年6月11日
 */
public interface IEquipmentDao extends ICommonDao<Equipment> {
	/**
	 * 添加装备
	 * @param equipment
	 * @param pic 装备图片文件
	 * @return
	 */
	public Serializable addEquipment(Equipment equipment,File pic);
}
