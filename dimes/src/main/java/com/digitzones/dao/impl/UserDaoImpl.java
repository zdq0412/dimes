package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IUserDao;
import com.digitzones.model.User;
@Repository
public class UserDaoImpl extends CommonDaoImpl<User> implements IUserDao {
	public UserDaoImpl() {
		super(User.class);
	}
}
