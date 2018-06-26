package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IDeviceSiteDao;
import com.digitzones.model.DeviceSite;
@Repository
public class DeviceSiteDaoImpl extends CommonDaoImpl<DeviceSite> implements IDeviceSiteDao {
	public DeviceSiteDaoImpl() {
		super(DeviceSite.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Long queryCountOfDeviceSite() {
		return (Long) this.getHibernateTemplate().find("select count(*) from DeviceSite ds where ds.device.productionUnit!=null",new Object[] {}).get(0);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Long queryCountOfDeviceSiteByStatus(String status) {
		return (Long) this.getHibernateTemplate().find("select count(*) from DeviceSite ds where ds.status=?0",new Object[] {status}).get(0);
	}
}
