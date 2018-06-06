package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IModuleDao;
import com.digitzones.model.Module;
import com.digitzones.service.IModuleService;
@Service
public class ModuleServiceImpl implements IModuleService {
	private IModuleDao moduleDao;
	@Autowired
	public void setModuleDao(IModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	@Override
	public Serializable addModule(Module module) {
		return moduleDao.save(module);
	}

	@Override
	public void updateModule(Module module) {
		moduleDao.update(module);
	}

	@Override
	public void deleteModule(Serializable id) {
		moduleDao.deleteById(id);
	}

	@Override
	public List<Module> queryTopModule() {
		return moduleDao.findByHQL("from Module m where m.parent is null", new Object[] {});
	}

	@Override
	public List<Module> querySubModule(Serializable id) {
		return moduleDao.findByHQL("from Module m where m.parent.id=?0", new Object[] {id});
	}
}
