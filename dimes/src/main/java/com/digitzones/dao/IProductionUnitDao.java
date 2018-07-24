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
	/**
	 * 根据生产单元id查找最大目标oee
	 * @param productionUnitId
	 * @return
	 */
	public double queryOeeByProductionUnitId(Long productionUnitId);
}
