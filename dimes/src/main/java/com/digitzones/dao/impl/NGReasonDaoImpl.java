package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.INGReasonDao;
import com.digitzones.model.NGReason;
@Repository
public class NGReasonDaoImpl extends CommonDaoImpl<NGReason> implements INGReasonDao {
	public NGReasonDaoImpl() {
		super(NGReason.class);
	}
}
