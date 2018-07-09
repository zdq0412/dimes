package com.digitzones.service;

import java.util.Date;
import java.util.List;

import com.digitzones.model.Classes;
/**
 * 班次业务逻辑接口
 * @author zdq
 * 2018年6月11日
 */
public interface IClassesService extends ICommonService<Classes> {
	/**
	 * 查询当前时间的班次 
	 * @return
	 */
	public Classes queryCurrentClasses();
	/**
	 * 查询所有班次
	 * @return
	 */
	public List<Classes> queryAllClasses();
	/**
	 * 根据时间查找班次信息
	 * @param date
	 * @return
	 */
	public Classes queryClassesByTime(Date date);
}
