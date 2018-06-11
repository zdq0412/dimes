package com.digitzones.dao.impl;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IEmployeeSkillMappingDao;
import com.digitzones.model.EmployeeSkillMapping;
@Repository
public class EmployeeSkillMappingDaoImpl extends CommonDaoImpl<EmployeeSkillMapping> implements IEmployeeSkillMappingDao {

	public EmployeeSkillMappingDaoImpl() {
		super(EmployeeSkillMapping.class);
	}
}
