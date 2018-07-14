package com.digitzones.dao;

import com.digitzones.model.EmployeeSkillMapping;
/**
 * 员工和技能关联dao
 * @author zdq
 * 2018年6月11日
 */
public interface IEmployeeSkillMappingDao extends ICommonDao<EmployeeSkillMapping> {
	/**
	 * 根据员工id和技能等级id查找技能数
	 * @param employeeId
	 * @param skillLevelCode
	 * @return
	 */
	public Integer queryCountBySkillLevelIdAndEmployeeId(Long employeeId,String skillLevelCode);
	/**
	 * 根据员工id、技能id和产线id查找技能数
	 * @param employeeId
	 * @param skillLevelCode
	 * @param productionUnitId
	 * @return
	 */
	public Integer queryCountBySkillLevelIdAndEmployeeIdAndProductionUnitId(Long employeeId,String skillLevelCode,Long productionUnitId);
}
