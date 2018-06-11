package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IDeviceTypeDao;
import com.digitzones.model.DeviceType;
@Repository
public class DeviceTypeDaoImpl extends CommonDaoImpl<DeviceType> implements IDeviceTypeDao {

	public DeviceTypeDaoImpl() {
		super(DeviceType.class);
	}
}
