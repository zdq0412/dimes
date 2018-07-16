package com.digitzones.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digitzones.model.User;
import com.digitzones.service.IUserService;
@Service("userInfoService")
public class UserInfoService implements UserDetailsService {
	private IUserService userService;
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.queryUserByUsername(username);
		System.out.println(user);
		List<GrantedAuthority> list = new ArrayList<>();
		UserDetails ud = new org.springframework.security.core.userdetails.User(username, user.getPassword(), list);
		return ud;
	}
}
