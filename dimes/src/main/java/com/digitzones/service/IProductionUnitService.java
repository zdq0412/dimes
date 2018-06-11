package com.digitzones.service;

import java.util.List;

import com.digitzones.model.ProductionUnit;
/**
 * 生产单元业务逻辑接口
 * @author zdq
 * 2018年6月11日
 */
public interface IProductionUnitService extends ICommonService<ProductionUnit>{
	/**
	 * 查询所有生产单元
	 * @return
	 */
	public List<ProductionUnit> queryTopProductionUnits();
}
