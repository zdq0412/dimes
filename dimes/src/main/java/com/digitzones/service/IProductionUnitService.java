package com.digitzones.service;

import java.io.Serializable;
import java.util.List;

import com.digitzones.model.ProductionUnit;
/**
 * 生产单元业务逻辑接口
 * @author zdq
 * 2018年6月11日
 */
public interface IProductionUnitService extends ICommonService<ProductionUnit>{
	/**
	 * 查询所有生产单元，用于树形结构
	 * @return
	 */
	public List<ProductionUnit> queryTopProductionUnits();
	/**
	 * 根据父生产单元id查询子生产单元数量
	 * @param pid
	 * @return
	 */
	public Long queryCountOfSubProductionUnit(Serializable pid);
	/**
	 * 查询所有的生产单元(去除最顶层生产单元)
	 * @return
	 */
	public List<ProductionUnit> queryAllProductionUnits();
}
