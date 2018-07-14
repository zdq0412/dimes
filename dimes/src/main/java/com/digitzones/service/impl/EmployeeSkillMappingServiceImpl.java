package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IEmployeeSkillMappingDao;
import com.digitzones.model.EmployeeSkillMapping;
import com.digitzones.model.Pager;
import com.digitzones.service.IEmployeeSkillMappingService;
@Service
public class EmployeeSkillMappingServiceImpl implements IEmployeeSkillMappingService {
	private IEmployeeSkillMappingDao employeeSkillMappingDao;
	@Autowired
	public void setEmployeeSkillMappingDao(IEmployeeSkillMappingDao employeeSkillMappingDao) {
		this.employeeSkillMappingDao = employeeSkillMappingDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return employeeSkillMappingDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(EmployeeSkillMapping obj) {
		employeeSkillMappingDao.update(obj);
	}

	@Override
	public EmployeeSkillMapping queryByProperty(String name, String value) {
		return employeeSkillMappingDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(EmployeeSkillMapping obj) {
		return employeeSkillMappingDao.save(obj);
	}

	@Override
	public EmployeeSkillMapping queryObjById(Long id) {
		return employeeSkillMappingDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		employeeSkillMappingDao.deleteById(id);
	}

	@Override
	public Integer queryCountBySkillLevelIdAndEmployeeId(Long employeeId, String skillLevelCode) {
		return employeeSkillMappingDao.queryCountBySkillLevelIdAndEmployeeId(employeeId, skillLevelCode);
	}

	@Override
	public Integer queryCountBySkillLevelIdAndEmployeeIdAndProductionUnitId(Long employeeId, String skillLevelCode,
			Long productionUnitId) {
		return employeeSkillMappingDao.queryCountBySkillLevelIdAndEmployeeIdAndProductionUnitId(employeeId, skillLevelCode, productionUnitId);
	}

}
