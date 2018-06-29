package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IMeasuringToolDao;
import com.digitzones.model.Equipment;
@Repository
public class MeasuringToolDaoImpl extends CommonDaoImpl<Equipment> implements IMeasuringToolDao {

	public MeasuringToolDaoImpl() {
		super(Equipment.class);
	}
}
