package com.digitzones.dao.impl;
import org.springframework.stereotype.Repository;

import com.digitzones.dao.IClassesDao;
import com.digitzones.model.Classes;
@Repository
public class ClassesDaoImpl extends CommonDaoImpl<Classes> implements IClassesDao{
	public ClassesDaoImpl() {
		super(Classes.class);
	}
}
