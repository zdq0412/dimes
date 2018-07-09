package com.digitzones.dao;

import java.util.Date;

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
	/**
	 * 根据时间查找班次信息
	 * @param date
	 * @return
	 */
	public Classes queryClassesByTime(Date date);
}
