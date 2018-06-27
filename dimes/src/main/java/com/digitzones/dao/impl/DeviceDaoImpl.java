package com.digitzones.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IDeviceDao;
import com.digitzones.model.Device;
@Repository
public class DeviceDaoImpl extends CommonDaoImpl<Device> implements IDeviceDao {

	public DeviceDaoImpl() {
		super(Device.class);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public double queryOeeByProductionUnitId(Long productionUnitId) {
		List<Float> list = (List<Float>) getHibernateTemplate().find("select max(d.goalOee) from Device d where d.productionUnit.id=?0 and d.bottleneck=?1", new Object[] {productionUnitId,true});
		if(list!=null&&list.size()>0) {
			if(list.get(0)!=null) {
				return list.get(0);
			}
		}
		
		return 0;
	}
}
