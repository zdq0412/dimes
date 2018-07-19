package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.ISecureEnvironmentRecordDao;
import com.digitzones.model.Pager;
import com.digitzones.model.SecureEnvironmentRecord;
import com.digitzones.service.ISecureEnvironmentRecordService;
@Service
public class SecureEnvironmentRecordServiceImpl implements ISecureEnvironmentRecordService {
	private ISecureEnvironmentRecordDao secureEnvironmentRecordDao;
	@Autowired
	public void setSecureEnvironmentRecordDao(ISecureEnvironmentRecordDao secureEnvironmentRecordDao) {
		this.secureEnvironmentRecordDao = secureEnvironmentRecordDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return secureEnvironmentRecordDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(SecureEnvironmentRecord obj) {
		secureEnvironmentRecordDao.update(obj);
	}

	@Override
	public SecureEnvironmentRecord queryByProperty(String name, String value) {
		return secureEnvironmentRecordDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(SecureEnvironmentRecord obj) {
		return secureEnvironmentRecordDao.save(obj);
	}

	@Override
	public SecureEnvironmentRecord queryObjById(Long id) {
		return secureEnvironmentRecordDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		secureEnvironmentRecordDao.deleteById(id);
	}
}
