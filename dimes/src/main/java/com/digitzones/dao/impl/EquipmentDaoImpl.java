package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IEquipmentDao;
import com.digitzones.model.Equipment;
@Repository
public class EquipmentDaoImpl extends CommonDaoImpl<Equipment> implements IEquipmentDao {

	public EquipmentDaoImpl() {
		super(Equipment.class);
	}
}
