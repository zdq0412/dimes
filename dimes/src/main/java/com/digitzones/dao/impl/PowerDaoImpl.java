package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IPowerDao;
import com.digitzones.model.Power;
@Repository
public class PowerDaoImpl extends CommonDaoImpl<Power> implements IPowerDao {
	public PowerDaoImpl() {
		super(Power.class);
	}
}
