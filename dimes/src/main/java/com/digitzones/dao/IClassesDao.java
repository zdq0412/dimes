package com.digitzones.dao;

import com.digitzones.model.Classes;
/**
 * 班次数据访问接口
 * @author zdq
 * 2018年6月11日
 */
public interface IClassesDao extends ICommonDao<Classes> {
	/**
	 * 查询当前时间的班次 
	 * @return
	 */
	public Classes queryCurrentClasses();
}
