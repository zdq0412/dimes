package com.digitzones.dao.impl;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IClassesDao;
import com.digitzones.model.Classes;
@Repository
public class ClassesDaoImpl extends CommonDaoImpl<Classes> implements IClassesDao{
	public ClassesDaoImpl() {
		super(Classes.class);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public Classes queryCurrentClasses() {
		String sql = "select * from Classes c where "
				+ " (CONVERT(varchar(100),?0,108) >CONVERT(varchar(100),c.beginTime,108) and CONVERT(varchar(100),?0,108)<'23:59' ) or (CONVERT(varchar(100),?0,108) >= '00:00' and CONVERT(varchar(100),?0,108)<CONVERT(varchar(100),c.endTime,108))";
		List<Classes> list =  getSession().createSQLQuery(sql)
				.setParameter(0, new Date()).addEntity(Classes.class)
				.list();
		return (list!=null && list.size()>0)?list.get(0):null;
	}
}
