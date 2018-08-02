package com.digitzones.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.digitzones.dao.IDeviceDao;
import com.digitzones.model.Device;
@Repository
public class DeviceDaoImpl extends CommonDaoImpl<Device> implements IDeviceDao {

	public DeviceDaoImpl() {
		super(Device.class);
	}

	@Override
	public Serializable addDevice(Device device, File photo) {
		if(photo!=null) {
			FileInputStream stream = null;
			try {
				stream = new FileInputStream(photo);
				device.setPhoto(Hibernate.getLobCreator(getSession()).createBlob(stream, stream.available()));
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}finally {
				if(stream!=null) {
					try {
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return this.save(device);
	}
}
