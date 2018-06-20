package com.digitzones.service;

import java.util.List;

import com.digitzones.model.Pager;
import com.digitzones.model.WorkpieceProcessParameterMapping;
/**
 * 工件工序站点service
 * @author zdq
 * 2018年6月15日
 */
public interface IWorkpieceProcessParameterMappingService extends ICommonService<WorkpieceProcessParameterMapping> {
	/**
	 * 根据工件id查询'工件工序参数'
	 * @param workpieceId
	 * @param rows
	 * @param page
	 * @return
	 */
	public Pager<WorkpieceProcessParameterMapping> queryWorkpieceProcessParameterMappingByWorkpieceId(Long workpieceId,Integer rows,Integer page);
	/**
	 * 根据工件编码和工序编码查找'工件工序参数关联对象'
	 * @param workpieceCode
	 * @param processCode
	 * @return
	 */
	public List<WorkpieceProcessParameterMapping> queryByWorkpieceCodeAndProcessCode(String workpieceCode,String processCode);
}
