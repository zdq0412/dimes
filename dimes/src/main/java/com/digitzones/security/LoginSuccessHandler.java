package com.digitzones.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.digitzones.constants.Constant;
import com.digitzones.model.User;
import com.digitzones.service.IUserService;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	private IUserService userService;
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		User user = userService.queryByProperty("username", authentication.getName());
		session.setAttribute(Constant.User.LOGIN_USER, user);
	}
}
