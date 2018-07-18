package com.digitzones.service;

import com.digitzones.model.UserRole;
/**
 * 用户角色关联service
 * @author zdq
 * 2018年7月18日
 */
public interface IUserRoleService extends ICommonService<UserRole> {
	/**
	 *  根据角色id查找记录数
	 * @param roleId
	 * @return
	 */
	public Long queryCountByRoleId(Long roleId);
}
