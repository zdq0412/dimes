package com.digitzones.service;
/**
 * 岗位业务逻辑接口
 * @author zdq
 * 2018年6月7日
 */

import java.io.Serializable;
import java.util.List;

import com.digitzones.model.Position;

public interface IPositionService extends ICommonService<Position> {
	/**
	 * 添加岗位信息
	 * @param position
	 * @return
	 */
	public Serializable addPosition(Position position);
	/**
	 * 根据id删除岗位
	 * @param id
	 */
	public void deletePosition(Serializable id);
	/**
	 * 更新岗位信息
	 * @param position
	 */
	public void updatePosition(Position position);
	/**
	 * 根据部门id查找岗位信息
	 * @param deptId
	 * @return
	 */
	public List<Position> queryPositionByDepartmentId(Serializable  deptId);
	/**
	 * 根据id查找职位信息
	 * @param id
	 * @return
	 */
	public Position queryPositionById(Serializable id);
}
