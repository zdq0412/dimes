package com.digitzones.service;

import java.io.Serializable;
import java.util.List;

import com.digitzones.model.PressLightType;
/**
 * 按灯类型servic
 * @author zdq
 * 2018年6月13日
 */
public interface IPressLightTypeService extends ICommonService<PressLightType> {
	/**
	 * 根据父类型id查询子类型数量
	 * @param pid
	 * @return
	 */
	public Long queryCountOfSubPressLightType(Serializable pid);
	/**
	 * 查找顶层类型
	 * @return
	 */
	public List<PressLightType> queryTopPressLightType();
	/**
	 * 查询第一级按灯类型，即level=1
	 * @return
	 */
	public List<PressLightType> queryFirstLevelType();
	/**
	 * 根据父类 别id查询所有子类别
	 * @param pid
	 * @return
	 */
	public List<PressLightType> queryAllPressLightTypesByParentId(Long pid);
}
