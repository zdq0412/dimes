package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IEmployeeSkillMappingDao;
import com.digitzones.model.EmployeeSkillMapping;
@Repository
public class EmployeeSkillMappingDaoImpl extends CommonDaoImpl<EmployeeSkillMapping> implements IEmployeeSkillMappingDao {

	public EmployeeSkillMappingDaoImpl() {
		super(EmployeeSkillMapping.class);
	}

	@Override
	public Integer queryCountBySkillLevelIdAndEmployeeId(Long employeeId, String skillLevelCode) {
		String sql = "select COUNT(es.Id) from EMPLOYEE_SKILL es  inner join PROCESSSKILLLEVEL psl on es.SKILLLEVEL_ID =psl.ID where es.EMPLOYEE_ID=?0 and psl.code=?1";
		@SuppressWarnings("deprecation")
		Integer count = (Integer) getSession().createSQLQuery(sql)
					.setParameter(0,employeeId)
					.setParameter(1,skillLevelCode)
					.uniqueResult();
		return count==null?0:count;
	}

	@Override
	public Integer queryCountBySkillLevelIdAndEmployeeIdAndProductionUnitId(Long employeeId, String skillLevelCode,
			Long productionUnitId) {
		String sql = "select COUNT(es.Id) from EMPLOYEE_SKILL es  inner join PROCESSSKILLLEVEL psl on es.SKILLLEVEL_ID =psl.ID inner join EMPLOYEE e on es.EMPLOYEE_ID=e.id\r\n" + 
				 " where es.EMPLOYEE_ID=?0 and psl.code=?1 and e.PRODUCTIONUNIT_ID=?2";
		@SuppressWarnings("deprecation")
		Integer count = (Integer) getSession().createSQLQuery(sql)
					.setParameter(0,employeeId)
					.setParameter(1,skillLevelCode)
					.setParameter(2,productionUnitId)
					.uniqueResult();
		return count==null?0:count;
	}
}
