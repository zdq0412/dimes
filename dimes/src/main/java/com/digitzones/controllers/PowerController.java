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
import com.digitzones.model.Power;
import com.digitzones.model.User;
import com.digitzones.service.IPowerService;
import com.digitzones.service.IRolePowerService;
@Controller
@RequestMapping("/power")
public class PowerController {
	private IPowerService powerService;
	private IRolePowerService rolePowerService;
	@Autowired
	public void setRolePowerService(IRolePowerService rolePowerService) {
		this.rolePowerService = rolePowerService;
	}
	@Autowired
	public void setPowerService(IPowerService powerService) {
		this.powerService = powerService;
	}
	/**
	 * 查询权限信息
	 * @return
	 */
	@RequestMapping("/queryPowers.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ModelMap queryPowers(@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		Pager<Power> pager = powerService.queryObjs("from Power u", page, rows, new Object[] {});
		modelMap.addAttribute("total",pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}
	/**
	 * 添加 权限信息
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/addPower.do")
	@ResponseBody
	public ModelMap addPower(Power power,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		Power u = powerService.queryByProperty("powerName",power.getPowerName());
		if(u!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "权限名称已被使用");
		}else {
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute(Constant.User.LOGIN_USER);
			power.setCreateDate(new Date());
			if(loginUser!=null) {
				power.setCreateUserId(loginUser.getId());
				power.setCreateUsername(loginUser.getUsername());
			}
			powerService.addObj(power);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "添加成功!");
		}
		return modelMap;
	}
	/**
	 * 根据id查询权限信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryPowerById.do")
	@ResponseBody
	public Power queryPowerById(Long id) {
		Power power =  powerService.queryObjById(id);
		return power;
	}

	/**
	 * 更新权限信息
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/updatePower.do")
	@ResponseBody
	public ModelMap updatePower(Power power,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();

		Power u = powerService.queryByProperty("powerName", power.getPowerName());
		if(u!=null && !u.getId().equals(power.getId())) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "权限名称已被使用");
		}else {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute(Constant.User.LOGIN_USER);
			Power power0 = powerService.queryObjById(power.getId());
			if(user!=null) {
				power0.setModifyUserId(user.getId());
				power0.setModifyUsername(user.getUsername());
			}
			power0.setModifyDate(new Date());
			power0.setPowerName(power.getPowerName());
			power0.setNote(power.getNote());
			powerService.updateObj(power0);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "更新成功!");
		}
		return modelMap;
	}

	/**
	 * 根据id删除权限
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletePower.do")
	@ResponseBody
	public ModelMap deletePower(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();

		Long powerId = Long.valueOf(id);

		//根据权限查询用户
		Long userCount = rolePowerService.queryCountByPowerId(powerId);
		if(userCount>0) {
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "删除失败,权限已被使用!");
			return modelMap;
		}
		powerService.deleteObj(powerId);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 停用该权限
	 * @param id
	 * @return
	 */
	@RequestMapping("/disabledPower.do")
	@ResponseBody
	public ModelMap disabledPower(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}

		Long powerId = Long.valueOf(id);
		ModelMap modelMap = new ModelMap();
		//根据权限查询用户
		Long userCount = rolePowerService.queryCountByPowerId(powerId);
		if(userCount>0) {
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "操作失败,权限已被使用!");
			return modelMap;
		}
		Power d = powerService.queryObjById(powerId);
		d.setDisable(true);

		powerService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}
} 
