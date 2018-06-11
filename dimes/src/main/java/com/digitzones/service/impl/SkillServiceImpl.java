package com.digitzones.service.impl;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.ISkillDao;
import com.digitzones.model.Pager;
import com.digitzones.model.Skill;
import com.digitzones.service.ISkillService;
@Service
public class SkillServiceImpl implements ISkillService {
	private ISkillDao skillDao;
	@Autowired
	public void setSkillDao(ISkillDao skillDao) {
		this.skillDao = skillDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return skillDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(Skill obj) {
		skillDao.update(obj);
	}

	@Override
	public Skill queryByProperty(String name, String value) {
		return skillDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(Skill obj) {
		return skillDao.save(obj);
	}

	@Override
	public Skill queryObjById(Long id) {
		return skillDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		skillDao.deleteById(id);
	}
}
