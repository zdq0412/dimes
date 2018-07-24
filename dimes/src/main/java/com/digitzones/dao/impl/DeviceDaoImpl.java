package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IDeviceDao;
import com.digitzones.model.Device;
@Repository
public class DeviceDaoImpl extends CommonDaoImpl<Device> implements IDeviceDao {

	public DeviceDaoImpl() {
		super(Device.class);
	}
}
