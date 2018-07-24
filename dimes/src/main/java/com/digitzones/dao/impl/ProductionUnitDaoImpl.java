package com.digitzones.dao.impl;
import org.springframework.stereotype.Repository;
import com.digitzones.dao.IProductionUnitDao;
import com.digitzones.model.ProductionUnit;
@Repository
public class ProductionUnitDaoImpl extends CommonDaoImpl<ProductionUnit> implements IProductionUnitDao {
	public ProductionUnitDaoImpl() {
		super(ProductionUnit.class);
	}
	@SuppressWarnings("deprecation")
	@Override
	public Integer queryGoalOutputByProductionUnitId(Long productionUnitId) {
		String sql = "select goalOutput from PRODUCTIONUNIT where ID=?0";
		Integer goalOutput = (Integer) this.getSession().createSQLQuery(sql).setParameter(0,productionUnitId).uniqueResult();
		return goalOutput==null?0:goalOutput;
	}

	@SuppressWarnings({ "deprecation"})
	@Override
	public double queryOeeByProductionUnitId(Long productionUnitId) {
		
		String sql = "select goalOee from productionunit p where p.id=?0";
		double goalOee =  (double) getSession().createSQLQuery(sql)
					.setParameter(0, productionUnitId)
					.uniqueResult();
		return goalOee;
	}
}
