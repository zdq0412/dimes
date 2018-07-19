package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.ISecureEnvironmentTypeDao;
import com.digitzones.model.SecureEnvironmentType;
@Repository
public class SecureEnvironmentTypeDaoImpl extends CommonDaoImpl<SecureEnvironmentType> implements ISecureEnvironmentTypeDao {

	public SecureEnvironmentTypeDaoImpl() {
		super(SecureEnvironmentType.class);
	}
}
