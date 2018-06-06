package com.digitzones.dao.impl;
import org.springframework.stereotype.Repository;
import com.digitzones.dao.IModuleDao;
import com.digitzones.model.Module;
@Repository
public class ModuleDaoImpl extends CommonDaoImpl<Module> implements IModuleDao {
	public ModuleDaoImpl() {
		super(Module.class);
	}
}
