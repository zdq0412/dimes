package com.digitzones.dao.impl;
import org.springframework.stereotype.Repository;
import com.digitzones.dao.IProductionUnitDao;
import com.digitzones.model.ProductionUnit;
@Repository
public class ProductionUnitDaoImpl extends CommonDaoImpl<ProductionUnit> implements IProductionUnitDao {
	public ProductionUnitDaoImpl() {
		super(ProductionUnit.class);
	}
}
