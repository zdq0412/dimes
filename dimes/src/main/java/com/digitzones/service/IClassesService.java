package com.digitzones.service;

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
}
