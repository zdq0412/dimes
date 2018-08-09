package com.digitzones.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IOeeDao;
import com.digitzones.model.Classes;
import com.digitzones.service.IOeeService;
@Service
public class OeeServiceImpl implements IOeeService {
	private IOeeDao oeeDao;
	@Autowired
	public void setOeeDao(IOeeDao oeeDao) {
		this.oeeDao = oeeDao;
	}
	@Override
	public Float queryProcessingBeatByWorkpieceIdAndProcessIdAndDeviceSiteId(Long workpieceId, Long processId,
			Long deviceSiteId) {
		return oeeDao.queryProcessingBeatByWorkpieceIdAndProcessIdAndDeviceSiteId(workpieceId, processId, deviceSiteId);
	}
	@Override
	public Integer queryLostTimeByDeviceSiteId(Date today, Long deviceSiteId, Classes c) {
		return oeeDao.queryLostTimeByDeviceSiteId(today, deviceSiteId, c);
	}
	@Override
	public List<Object[]> queryNGInfo4CurrentDay(Date today, Long deviceSiteId) {
		return oeeDao.queryNGInfo4CurrentDay(today, deviceSiteId);
	}
	@Override
	public List<Object[]> queryNGInfo4CurrentMonth(Long deviceSiteId) {
		return oeeDao.queryNGInfo4CurrentMonth(deviceSiteId);
	}
	@Override
	public List<Object[]> queryNGInfo4PreMonth(Long deviceSiteId) {
		return oeeDao.queryNGInfo4PreMonth(deviceSiteId);
	}
}
