package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IDeviceSiteDao;
import com.digitzones.model.DeviceSite;
@Repository
public class DeviceSiteDaoImpl extends CommonDaoImpl<DeviceSite> implements IDeviceSiteDao {
	public DeviceSiteDaoImpl() {
		super(DeviceSite.class);
	}
}
