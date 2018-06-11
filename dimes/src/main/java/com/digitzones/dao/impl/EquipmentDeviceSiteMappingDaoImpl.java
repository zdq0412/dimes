package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IEquipmentDeviceSiteMappingDao;
import com.digitzones.model.EquipmentDeviceSiteMapping;
@Repository
public class EquipmentDeviceSiteMappingDaoImpl extends CommonDaoImpl<EquipmentDeviceSiteMapping> implements IEquipmentDeviceSiteMappingDao {

	public EquipmentDeviceSiteMappingDaoImpl() {
		super(EquipmentDeviceSiteMapping.class);
	}
}
