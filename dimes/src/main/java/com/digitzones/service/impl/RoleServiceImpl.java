package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IRoleDao;
import com.digitzones.dao.IRolePowerDao;
import com.digitzones.model.Pager;
import com.digitzones.model.Power;
import com.digitzones.model.Role;
import com.digitzones.model.RolePower;
import com.digitzones.service.IRoleService;
@Service
public class RoleServiceImpl implements IRoleService {
	private IRoleDao roleDao;
	private IRolePowerDao rolePowerDao;
	@Autowired
	public void setRolePowerDao(IRolePowerDao rolePowerDao) {
		this.rolePowerDao = rolePowerDao;
	}

	@Autowired
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return roleDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(Role obj) {
		roleDao.update(obj);
	}

	@Override
	public Role queryByProperty(String name, String value) {
		return roleDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(Role obj) {
		return roleDao.save(obj);
	}

	@Override
	public Role queryObjById(Long id) {
		return roleDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		//删除角色下的权限
		List<RolePower> rolePowers = rolePowerDao.findByHQL("from RolePower rp where rp.role.id=?0", new Object[] {id});
		if(rolePowers!=null) {
			for(RolePower rolePower : rolePowers) {
				rolePowerDao.delete(rolePower);
			}
		}

		roleDao.deleteById(id);
	}
	@Override
	public List<Role> queryAllRoles() {
		return roleDao.findAll();
	}

	@Override
	public List<Role> queryRolesByUserId(Long userId) {
		return roleDao.findByHQL("select ur.role from UserRole ur where ur.user.id=?0", new Object[] {userId});
	}

	@Override
	public void addPowersForRole(Long roleId, String[] powerIds) {
		//删除角色下的权限
		List<RolePower> rolePowers = rolePowerDao.findByHQL("from RolePower rp where rp.role.id=?0", new Object[] {roleId});
		if(rolePowers!=null) {
			for(RolePower rolePower : rolePowers) {
				rolePowerDao.delete(rolePower);
			}
		}
		//为角色添加新权限
		Role role = new Role();
		role.setId(roleId);
		for(String powerId : powerIds) {
			RolePower rolePower = new RolePower();
			Power power = new Power();
			power.setId(Long.valueOf(powerId));
			rolePower.setPower(power);
			rolePower.setRole(role);
			rolePowerDao.save(rolePower);
		}
	}
}
