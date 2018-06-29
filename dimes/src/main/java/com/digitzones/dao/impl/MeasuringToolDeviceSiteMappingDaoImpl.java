package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IMeasuringToolDeviceSiteMappingDao;
import com.digitzones.model.EquipmentDeviceSiteMapping;
@Repository
public class MeasuringToolDeviceSiteMappingDaoImpl extends CommonDaoImpl<EquipmentDeviceSiteMapping> implements IMeasuringToolDeviceSiteMappingDao {

	public MeasuringToolDeviceSiteMappingDaoImpl() {
		super(EquipmentDeviceSiteMapping.class);
	}
}
