package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IClassesDeviceMappingDao;
import com.digitzones.model.ClassesDeviceMapping;
@Repository
public class ClassesDeviceMappingDaoImpl extends CommonDaoImpl<ClassesDeviceMapping> implements IClassesDeviceMappingDao {

	public ClassesDeviceMappingDaoImpl() {
		super(ClassesDeviceMapping.class);
	}
}
