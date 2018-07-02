package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.constants.Constant;
import com.digitzones.dao.IWorkSheetDetailDao;
import com.digitzones.dao.IWorkpieceProcessDeviceSiteMappingDao;
import com.digitzones.model.Pager;
import com.digitzones.model.WorkSheetDetail;
import com.digitzones.model.WorkpieceProcessDeviceSiteMapping;
import com.digitzones.service.IWorkSheetDetailService;
@Service
public class WorkSheetDetailServiceImpl implements IWorkSheetDetailService {
	private IWorkSheetDetailDao workSheetDetailDao;
	private IWorkpieceProcessDeviceSiteMappingDao workpieceProcessDeviceSiteMappingDao;
	private Random random = new Random();
	@Autowired
	public void setWorkpieceProcessDeviceSiteMappingDao(
			IWorkpieceProcessDeviceSiteMappingDao workpieceProcessDeviceSiteMappingDao) {
		this.workpieceProcessDeviceSiteMappingDao = workpieceProcessDeviceSiteMappingDao;
	}

	@Autowired
	public void setWorkSheetDetailDao(IWorkSheetDetailDao workSheetDetailDao) {
		this.workSheetDetailDao = workSheetDetailDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return workSheetDetailDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(WorkSheetDetail obj) {
		workSheetDetailDao.update(obj);
	}

	@Override
	public WorkSheetDetail queryByProperty(String name, String value) {
		return workSheetDetailDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(WorkSheetDetail obj) {
		return workSheetDetailDao.save(obj);
	}

	@Override
	public WorkSheetDetail queryObjById(Long id) {
		return workSheetDetailDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		workSheetDetailDao.deleteById(id);
	}

	@Override
	public List<WorkSheetDetail> queryWorkSheetDetailsByWorkSheetId(Long workSheetId) {
		return workSheetDetailDao.findByHQL("from WorkSheetDetail wsd where wsd.workSheet.id=?0",new Object[] {workSheetId});
	}

	@Override
	public void buildWorkSheetDetailListInMemoryByWorkpieceId(Long workpieceId) {
		Constant.workSheetDetail.clear();
		//根据工件id查询工件工序和站点对象
		String hql = "from WorkpieceProcessDeviceSiteMapping wpdsm where wpdsm.workpieceProcess.workpiece.id=?0";
		List<WorkpieceProcessDeviceSiteMapping> workpieceProcessDeviceSiteMappings = workpieceProcessDeviceSiteMappingDao.findByHQL(hql, new Object[] {workpieceId});
		for(WorkpieceProcessDeviceSiteMapping wpdsm : workpieceProcessDeviceSiteMappings) {
			WorkSheetDetail detail = new WorkSheetDetail();
			detail.setId(random.nextLong());
			detail.setDeviceCode(wpdsm.getDeviceSite().getDevice().getCode());
			detail.setDeviceName(wpdsm.getDeviceSite().getDevice().getName());
			detail.setDeviceSiteCode(wpdsm.getDeviceSite().getCode());
			detail.setDeviceSiteName(wpdsm.getDeviceSite().getName());
			detail.setDeviceSiteId(wpdsm.getDeviceSite().getId());
			detail.setProcessCode(wpdsm.getWorkpieceProcess().getProcess().getCode());
			detail.setProcessName(wpdsm.getWorkpieceProcess().getProcess().getName());
			detail.setParameterSource(wpdsm.getDeviceSite().getDevice().getParameterValueType());
			
			Constant.workSheetDetail.add(detail);
		}
	}
}
