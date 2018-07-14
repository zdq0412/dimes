package com.digitzones.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IProcessSkillLevelDao;
import com.digitzones.model.ProcessSkillLevel;
@Repository
public class ProcessSkillLevelDaoImpl extends CommonDaoImpl<ProcessSkillLevel> implements IProcessSkillLevelDao {
	public ProcessSkillLevelDaoImpl() {
		super(ProcessSkillLevel.class);
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Object[]> queryCount4SkillLevel() {
		String sql = "select es.code,es.name,COUNT(es.id) from PROCESSSKILLLEVEL psl"
				+ " right join SKILLLEVEL es"
				+ " on es.code=psl.code "
				+ " group by es.code,es.name";
		return getSession().createSQLQuery(sql).list();
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Object[]> queryCount4ProcessBySkillLevelCode(String skillLevelCode) {
		String sql = "select p.code,p.name,COUNT(*) from SKILL s inner join PROCESSES p on s.PROCESS_ID=p.id inner join PROCESSSKILLLEVEL psl on s.id=psl.SKILL_ID where psl.code=?0 group by p.name,p.code";
		return getSession().createSQLQuery(sql).setParameter(0, skillLevelCode).list();
	}
}
