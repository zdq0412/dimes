package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IDeviceDao;
import com.digitzones.model.Device;
@Repository
public class DeviceDaoImpl extends CommonDaoImpl<Device> implements IDeviceDao {

	public DeviceDaoImpl() {
		super(Device.class);
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
