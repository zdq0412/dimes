package com.digitzones.dao.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IClassesDao;
import com.digitzones.model.Classes;
@Repository
public class ClassesDaoImpl extends CommonDaoImpl<Classes> implements IClassesDao{
	private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	public ClassesDaoImpl() {
		super(Classes.class);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public Classes queryCurrentClasses() {
		String sql = "select * from Classes c where "
				+ " (CONVERT(varchar(100),?0,108) >=CONVERT(varchar(100),c.beginTime,108) and CONVERT(varchar(100),?0,108)<='23:59' ) or (CONVERT(varchar(100),?0,108) >= '00:00' and CONVERT(varchar(100),?0,108)<=CONVERT(varchar(100),c.endTime,108))";
		List<Classes> list = null;
		try {
			list = getSession().createSQLQuery(sql)
					.setParameter(0, sdf.parse(sdf.format(new Date()))).addEntity(Classes.class)
					.list();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (list!=null && list.size()>0)?list.get(0):null;
	}

	@SuppressWarnings({ "deprecation", "unchecked"})
	@Override
	public Classes queryClassesByTime(Date date) {
		String sql = "select * from Classes c where "
				+ " (CONVERT(varchar(100),?0,108) >=CONVERT(varchar(100),c.beginTime,108) and CONVERT(varchar(100),?0,108)<=CONVERT(varchar(100),c.endTime,108) and CONVERT(varchar(100),c.beginTime,108)<CONVERT(varchar(100),c.endTime,108)) or"
				+ " ((CONVERT(varchar(100),?0,108) >=CONVERT(varchar(100),c.beginTime,108) and CONVERT(varchar(100),?0,108)<='23:59') or (CONVERT(varchar(100),?0,108)>='00:00' and CONVERT(varchar(100),?0,108)<=CONVERT(varchar(100),c.endTime,108)) and CONVERT(varchar(100),c.beginTime,108)>CONVERT(varchar(100),c.endTime,108))";
		List<Classes> list = null;
		try {
			Date d = sdf.parse(sdf.format(date));
			list = getSession().createSQLQuery(sql)
					.setParameter(0, d).addEntity(Classes.class)
					.list();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (list!=null && list.size()>0)?list.get(0):null;
	}
}
