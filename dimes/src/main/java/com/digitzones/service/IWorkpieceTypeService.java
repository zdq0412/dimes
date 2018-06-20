package com.digitzones.service;

import java.io.Serializable;
import java.util.List;

import com.digitzones.model.WorkpieceType;
/**
 * 工件类别service
 * @author zdq
 * 2018年6月15日
 */
public interface IWorkpieceTypeService extends ICommonService<WorkpieceType> {
	/**
	 * 查找顶层工序类别
	 * @return
	 */
	public List<WorkpieceType> queryTopWorkpieceType();
	/**
	 * 根据父类别id查询子类别数量
	 * @param pid
	 * @return
	 */
	public Long queryCountOfSubWorkpieceType(Serializable pid);
}
