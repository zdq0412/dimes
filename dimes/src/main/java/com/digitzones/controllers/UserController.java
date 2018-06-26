package com.digitzones.controllers;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.constants.Constant;
import com.digitzones.model.Pager;
import com.digitzones.model.User;
import com.digitzones.service.IUserService;
import com.digitzones.util.PasswordEncoder;
@Controller
@RequestMapping("/user")
public class UserController {
	private IUserService userService;
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	/**
	 * 查询用户信息
	 * @return
	 */
	@RequestMapping("/queryUsers.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ModelMap queryUsers(@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		Pager<User> pager = userService.queryObjs("from User u", page, rows, new Object[] {});
		modelMap.addAttribute("total",pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}
	/**
	 * 添加 用户信息
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/addUser.do")
	@ResponseBody
	public ModelMap addUser(User user,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
			User u = userService.queryByProperty("username", user.getUsername());
			if(u!=null) {
				modelMap.addAttribute("success", false);
				modelMap.addAttribute("msg", "用户名称已被使用");
			}else {
				HttpSession session = request.getSession();
				User loginUser = (User) session.getAttribute(Constant.User.LOGIN_USER);
				user.setCreateDate(new Date());
				if(loginUser!=null) {
					user.setCreateUserId(loginUser.getId());
					user.setCreateUsername(loginUser.getUsername());
				}
				if(user.getEmployee().getId()==null) {
					user.setEmployee(null);
				}
				user.setPassword(new PasswordEncoder(user.getUsername()).encode(user.getPassword()));
				userService.addObj(user);
				modelMap.addAttribute("success", true);
				modelMap.addAttribute("msg", "添加成功!");
			}
		return modelMap;
	}
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryUserById.do")
	@ResponseBody
	public User queryUserById(Long id) {
		User user =  userService.queryObjById(id);
		return user;
	}

	/**
	 * 更新用户信息
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/updateUser.do")
	@ResponseBody
	public ModelMap updateUser(User user) {
		ModelMap modelMap = new ModelMap();

		User u = userService.queryByProperty("username", user.getUsername());
		if(u!=null && !u.getId().equals(user.getId())) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "用户名称已被使用");
		}else {
			User user0 = userService.queryObjById(user.getId());
			user0.setModifyDate(new Date());
			user0.setUsername(user.getUsername());
			user0.setNote(user.getNote());
			if(user.getEmployee()!=null) {
				user0.setEmployee(user.getEmployee());
			}
			userService.updateObj(user0);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "更新成功!");
		}
		return modelMap;
	}
	
	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteUser.do")
	@ResponseBody
	public ModelMap deleteUser(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		userService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 停用该班次
	 * @param id
	 * @return
	 */
	@RequestMapping("/disabledUser.do")
	@ResponseBody
	public ModelMap disabledUser(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		User d = userService.queryObjById(Long.valueOf(id));
		d.setDisable(true);

		userService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login(User user,HttpServletRequest request) {
		User u = userService.login(user.getUsername(), new PasswordEncoder(user.getUsername()).encode(user.getPassword()));
		String returnPage = "redirect:/console/jsp/console.jsp";
		if(u==null) {
			returnPage = "redirect:/login.jsp";
		}
		return returnPage;
	}
} 
