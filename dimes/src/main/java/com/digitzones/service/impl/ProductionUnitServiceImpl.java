package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IProductionUnitDao;
import com.digitzones.model.Pager;
import com.digitzones.model.ProductionUnit;
import com.digitzones.service.IProductionUnitService;
@Service
public class ProductionUnitServiceImpl implements IProductionUnitService {
	private IProductionUnitDao productionUnitDao;
	@Autowired
	public void setProductionUnitDao(IProductionUnitDao productionUnitDao) {
		this.productionUnitDao = productionUnitDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return this.productionUnitDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(ProductionUnit obj) {
		productionUnitDao.update(obj);
	}

	@Override
	public ProductionUnit queryByProperty(String name, String value) {
		return productionUnitDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(ProductionUnit obj) {
		return productionUnitDao.save(obj);
	}

	@Override
	public ProductionUnit queryObjById(Long id) {
		return productionUnitDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		productionUnitDao.deleteById(id);
	}

	@Override
	public List<ProductionUnit> queryTopProductionUnits() {
		return  productionUnitDao.findByHQL("from ProductionUnit pu where pu.parent is null order by code", new Object[] {});
	}

	@Override
	public Long queryCountOfSubProductionUnit(Serializable pid) {
		return productionUnitDao.findCount("from ProductionUnit pu inner join pu.parent p where p.id=?0 order by code", new Object[] {pid});
	}

	@Override
	public List<ProductionUnit> queryAllProductionUnits() {
		return this.productionUnitDao.findByHQL("from ProductionUnit pu where pu.parent!=null order by code", new Object[] {});
	}

	@Override
	public Integer queryGoalOutputByProductionUnitId(Long productionUnitId) {
		return productionUnitDao.queryGoalOutputByProductionUnitId(productionUnitId);
	}

	@Override
	public double queryOeeByProductionUnitId(Long productionUnitId) {
		return productionUnitDao.queryOeeByProductionUnitId(productionUnitId);
	}
}
