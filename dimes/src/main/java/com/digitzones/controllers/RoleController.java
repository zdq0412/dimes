package com.digitzones.controllers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.digitzones.model.Role;
import com.digitzones.model.User;
import com.digitzones.service.IPowerService;
import com.digitzones.service.IRoleService;
import com.digitzones.service.IUserRoleService;
import com.digitzones.vo.PowerVO;
@Controller
@RequestMapping("/role")
public class RoleController {
	private IRoleService roleService;
	private IUserRoleService userRoleService;
	private IPowerService powerService;
	@Autowired
	public void setPowerService(IPowerService powerService) {
		this.powerService = powerService;
	}
	@Autowired
	public void setUserRoleService(IUserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}
	@Autowired
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	/**
	 * 查询角色信息
	 * @return
	 */
	@RequestMapping("/queryRoles.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ModelMap queryRoles(@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		Pager<Role> pager = roleService.queryObjs("from Role u", page, rows, new Object[] {});
		modelMap.addAttribute("total",pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}
	/**
	 * 添加 角色信息
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/addRole.do")
	@ResponseBody
	public ModelMap addRole(Role role,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		Role u = roleService.queryByProperty("roleName",role.getRoleName());
		if(u!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "角色名称已被使用");
		}else {
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute(Constant.User.LOGIN_USER);
			role.setCreateDate(new Date());
			if(loginUser!=null) {
				role.setCreateUserId(loginUser.getId());
				role.setCreateUsername(loginUser.getUsername());
			}
			roleService.addObj(role);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "添加成功!");
		}
		return modelMap;
	}
	/**
	 * 根据id查询角色信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryRoleById.do")
	@ResponseBody
	public Role queryRoleById(Long id) {
		Role role =  roleService.queryObjById(id);
		return role;
	}

	/**
	 * 更新角色信息
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/updateRole.do")
	@ResponseBody
	public ModelMap updateRole(Role role,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();

		Role u = roleService.queryByProperty("roleName", role.getRoleName());
		if(u!=null && !u.getId().equals(role.getId())) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "角色名称已被使用");
		}else {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute(Constant.User.LOGIN_USER);
			Role role0 = roleService.queryObjById(role.getId());
			if(user!=null) {
				role0.setModifyUserId(user.getId());
				role0.setModifyUsername(user.getUsername());
			}
			role0.setModifyDate(new Date());
			role0.setRoleName(role.getRoleName());
			role0.setNote(role.getNote());
			roleService.updateObj(role0);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "更新成功!");
		}
		return modelMap;
	}

	/**
	 * 根据id删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRole.do")
	@ResponseBody
	public ModelMap deleteRole(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();

		Long roleId = Long.valueOf(id);

		//根据角色查询用户
		Long userCount = userRoleService.queryCountByRoleId(roleId);
		if(userCount>0) {
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "删除失败,角色已被使用!");
			return modelMap;
		}
		roleService.deleteObj(roleId);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 停用该角色
	 * @param id
	 * @return
	 */
	@RequestMapping("/disabledRole.do")
	@ResponseBody
	public ModelMap disabledRole(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}

		Long roleId = Long.valueOf(id);
		ModelMap modelMap = new ModelMap();
		//根据角色查询用户
		Long userCount = userRoleService.queryCountByRoleId(roleId);
		if(userCount>0) {
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "操作失败,角色已被使用!");
			return modelMap;
		}
		Role d = roleService.queryObjById(roleId);
		d.setDisable(true);

		roleService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}
	/**
	 * 1、根据用户id查找角色
	 * 2、查找所有角色
	 * @param userId
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping("/queryPowers.do")
	@ResponseBody
	public List<PowerVO> queryRoles(Long roleId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page){
		//查询所有权限
		List<Power> allPowers = powerService.queryAllPowers();
		//根据角色id查找权限
		List<Power> rolePowers = powerService.queryPowersByRoleId(roleId);
		List<PowerVO> vos = new ArrayList<>();
		if(allPowers!=null) {
			for(int i = 0;i<allPowers.size();i++) {
				Power role = allPowers.get(i);
				PowerVO vo = model2vo(role);
				if(rolePowers!=null) {
					for(int j = 0;j<rolePowers.size();j++) {
						Power r = rolePowers.get(j);
						if(role.getId().equals(r.getId())) {
							vo.setChecked(true);
							break;
						}
					}
				}
				vos.add(vo);
			}
		}
		return vos;
	}

	private PowerVO model2vo(Power r) {
		if(r == null) {
			return null;
		}
		PowerVO vo = new PowerVO();
		vo.setId(r.getId());
		vo.setPowerCode(r.getPowerCode());
		vo.setPowerName(r.getPowerName());
		vo.setNote(r.getNote());
		return vo;
	}
	
	/**
	 * 为角色分配权限
	 * @param roleId
	 * @param powerIds
	 * @return
	 */
	@RequestMapping("/signPowers.do")
	@ResponseBody
	public ModelMap signRoles(Long roleId,String powerIds) {
		ModelMap modelMap = new ModelMap();
		if(powerIds!=null) {
			if(powerIds.contains("[")) {
				powerIds = powerIds.replace("[", "");
			}
			if(powerIds.contains("]")) {
				powerIds = powerIds.replace("]", "");
			}

			String[] idArray = powerIds.split(",");

			roleService.addPowersForRole(roleId, idArray);
			modelMap.addAttribute("success",true);
			modelMap.addAttribute("msg","操作完成!");
		}else {
			modelMap.addAttribute("success",false);
			modelMap.addAttribute("msg","操作失败!");
		}
		return modelMap;
	}
} 
