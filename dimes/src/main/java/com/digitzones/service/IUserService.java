package com.digitzones.service;

import com.digitzones.model.User;
/**
 * 用户管理service
 * @author zdq
 * 2018年6月11日
 */
public interface IUserService extends ICommonService<User> {
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username,String password);
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public User queryUserByUsername(String username);
}
