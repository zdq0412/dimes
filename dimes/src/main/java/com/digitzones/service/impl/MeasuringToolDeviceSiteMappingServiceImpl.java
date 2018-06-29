package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IMeasuringToolDeviceSiteMappingDao;
import com.digitzones.model.EquipmentDeviceSiteMapping;
import com.digitzones.model.Pager;
import com.digitzones.service.IMeasuringToolDeviceSiteMappingService;
@Service
public class MeasuringToolDeviceSiteMappingServiceImpl implements IMeasuringToolDeviceSiteMappingService {
	private IMeasuringToolDeviceSiteMappingDao equipmentDeviceSiteMappingDao;
	@Autowired
	public void setMeasuringToolDeviceSiteMappingDao(IMeasuringToolDeviceSiteMappingDao equipmentDeviceSiteMappingDao) {
		this.equipmentDeviceSiteMappingDao = equipmentDeviceSiteMappingDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return equipmentDeviceSiteMappingDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(EquipmentDeviceSiteMapping obj) {
		equipmentDeviceSiteMappingDao.update(obj);
	}

	@Override
	public EquipmentDeviceSiteMapping queryByProperty(String name, String value) {
		return equipmentDeviceSiteMappingDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(EquipmentDeviceSiteMapping obj) {
		return equipmentDeviceSiteMappingDao.save(obj);
	}

	@Override
	public EquipmentDeviceSiteMapping queryObjById(Long id) {
		return equipmentDeviceSiteMappingDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		equipmentDeviceSiteMappingDao.deleteById(id);
	}

	@Override
	public EquipmentDeviceSiteMapping queryByNo(String no) {
		return equipmentDeviceSiteMappingDao.findSingleByProperty("no", no);
	}
}
