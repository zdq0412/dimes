package com.digitzones.service;

import java.util.List;

import com.digitzones.model.Parameters;
/**
 * 参数service
 * @author zdq
 * 2018年6月12日
 */
public interface IParameterService extends ICommonService<Parameters> {
	/**
	 * 查询所有 参数
	 * @return
	 */
	public List<Parameters> queryAllParameters();
}
