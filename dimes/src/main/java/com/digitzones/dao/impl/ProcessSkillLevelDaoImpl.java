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
	public List<Object[]> queryCount4ProcessBySkillLevelCode() {
		String sql = "select p.name,p.code,COUNT(distinct p.id) from PROCESSES p "
				+ " left join SKILL s on p.id = s.process_id "
				+ " inner join processskilllevel psl on s.id = psl.skill_Id  "
				+ " group by p.name,p.code";
		return getSession().createSQLQuery(sql).list();
	}
}
