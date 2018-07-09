package com.digitzones.dao;

import com.digitzones.model.ProductionUnit;
/**
 * 生产单元数据访问接口
 * @author zdq
 * 2018年6月11日
 */
public interface IProductionUnitDao extends ICommonDao<ProductionUnit> {
	/**
	 * 根据产线id查找目标产量
	 * @param productionUnitId
	 * @return
	 */
	public Integer queryGoalOutputByProductionUnitId(Long productionUnitId);
}
