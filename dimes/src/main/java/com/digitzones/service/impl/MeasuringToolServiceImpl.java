package com.digitzones.service.impl;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.constants.Constant;
import com.digitzones.dao.IMeasuringToolDao;
import com.digitzones.model.Equipment;
import com.digitzones.model.Pager;
import com.digitzones.service.IMeasuringToolService;
@Service
public class MeasuringToolServiceImpl implements IMeasuringToolService {
	private IMeasuringToolDao equipmentDao;
	@Autowired
	public void setEquipmentDao(IMeasuringToolDao equipmentDao) {
		this.equipmentDao = equipmentDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return equipmentDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(Equipment obj) {
		equipmentDao.update(obj);
	}

	@Override
	public Equipment queryByProperty(String name, String value) {
		return equipmentDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(Equipment obj) {
		return equipmentDao.save(obj);
	}

	@Override
	public Equipment queryObjById(Long id) {
		return equipmentDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		equipmentDao.deleteById(id);
	}

	@Override
	public List<Equipment> queryAllMeasuringTools() {
		return equipmentDao.findByHQL("from Equipment e where e.baseCode=?0", new Object[] {Constant.EquipmentType.MEASURINGTOOL});
	}

	@Override
	public List<Equipment> queryMeasuringToolsByCodeOrNameOrUnity(String value) {
		String hql = "from Equipment e where (e.code like ?0 or e.name like ?0 or e.unitType like ?0) and e.baseCode=?1";
		return equipmentDao.findByHQL(hql,new Object[] {"%" + value + "%",Constant.EquipmentType.MEASURINGTOOL});
	}

	@Override
	public Serializable addMeasuringTool(Equipment equipment, File pic) {
		return equipmentDao.addMeasuringTool(equipment, pic);
	}

	@Override
	public void updateMeasuringTool(Equipment equipment, File pic) {
		equipmentDao.updateMeasuringTool(equipment, pic);
	}
}
