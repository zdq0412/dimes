package com.digitzones.service;

import com.digitzones.model.Pager;
/**
 * 通用分页查询业务接口
 * @author zdq
 * 2018年6月7日
 */
public interface ICommonService<T> {
	/**
	 * 分页查询对象
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values);
}
