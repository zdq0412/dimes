package com.digitzones.service;

import java.util.List;

import com.digitzones.model.Workpiece;
/**
 * 工序service
 * @author zdq
 * 2018年6月15日
 */
public interface IWorkpieceService extends ICommonService<Workpiece> {
	/**
	 * 根据条件查询工件信息
	 * @param q
	 * @return
	 */
	public List<Workpiece> queryAllWorkpieces(String q);
	/**
	 * 根据所有工件信息
	 * @return
	 */
	public List<Workpiece> queryAllWorkpieces();
}
