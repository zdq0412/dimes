package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.ILostTimeRecordDao;
import com.digitzones.model.Classes;
import com.digitzones.model.LostTimeRecord;
import com.digitzones.model.Pager;
import com.digitzones.service.ILostTimeRecordService;
@Service
public class LostTimeRecordServiceImpl implements ILostTimeRecordService {
	private ILostTimeRecordDao lostTimeRecordDao;
	@Autowired
	public void setLostTimeRecordDao(ILostTimeRecordDao lostTimeRecordDao) {
		this.lostTimeRecordDao = lostTimeRecordDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return lostTimeRecordDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(LostTimeRecord obj) {
		lostTimeRecordDao.update(obj);
	}

	@Override
	public LostTimeRecord queryByProperty(String name, String value) {
		return lostTimeRecordDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(LostTimeRecord obj) {
		return lostTimeRecordDao.save(obj);
	}

	@Override
	public LostTimeRecord queryObjById(Long id) {
		return lostTimeRecordDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		lostTimeRecordDao.deleteById(id);
	}

	@Override
	public List<LostTimeRecord> queryLostTimeRecordByYearAndMonth(Integer year,Integer month) {
		String hql = "select new LostTimeRecord(ltr.lostTimeTypeName,sum(ltr.sumOfLostTime)/60) from LostTimeRecord ltr where year(ltr.beginTime)=?0 and month(ltr.beginTime)=?1  and ltr.deleted=?2 "
				+ " and ltr.sumOfLostTime!=0 and ltr.planHalt!=?3"
				+ " group by ltr.lostTimeTypeName";
		return this.lostTimeRecordDao.findByHQL(hql,new Object[] {year,month,false,true});
	}

	@Override
	public Double queryHoursOfLostTimeRecordByYearAndMonth(Integer year, Integer month) {
		return this.lostTimeRecordDao.queryHoursOfLostTimeRecordByYearAndMonth(year, month);
	}

	@Override
	public Double queryHoursOfPlanHaltByYearAndMonth(Integer year, Integer month) {
		return lostTimeRecordDao.queryHoursOfPlanHaltByYearAndMonth(year, month);
	}

	@Override
	public Double queryLostTimeByTime(Classes c,Long deviceSiteId) {
		Double result = lostTimeRecordDao.queryLostTimeByTime(c,deviceSiteId);
		return result==null?0:result;
	}
}
