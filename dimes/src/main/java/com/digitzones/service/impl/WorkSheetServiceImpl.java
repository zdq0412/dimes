package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.constants.Constant;
import com.digitzones.dao.IWorkSheetDao;
import com.digitzones.dao.IWorkSheetDetailDao;
import com.digitzones.dao.IWorkSheetDetailParametersRecordDao;
import com.digitzones.model.Pager;
import com.digitzones.model.WorkSheet;
import com.digitzones.model.WorkSheetDetail;
import com.digitzones.model.WorkSheetDetailParametersRecord;
import com.digitzones.service.IWorkSheetService;
@Service
public class WorkSheetServiceImpl implements IWorkSheetService {
	private IWorkSheetDao workSheetDao;
	private IWorkSheetDetailDao workSheetDetailDao;
	private IWorkSheetDetailParametersRecordDao workSheetDetailParametersRecordDao;
	@Autowired
	public void setWorkSheetDetailParametersRecordDao(
			IWorkSheetDetailParametersRecordDao workSheetDetailParametersRecordDao) {
		this.workSheetDetailParametersRecordDao = workSheetDetailParametersRecordDao;
	}

	@Autowired
	public void setWorkSheetDetailDao(IWorkSheetDetailDao workSheetDetailDao) {
		this.workSheetDetailDao = workSheetDetailDao;
	}

	@Autowired
	public void setWorkSheetDao(IWorkSheetDao workSheetDao) {
		this.workSheetDao = workSheetDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return workSheetDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(WorkSheet obj) {
		workSheetDao.update(obj);
	}

	@Override
	public WorkSheet queryByProperty(String name, String value) {
		return workSheetDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(WorkSheet obj) {
		return workSheetDao.save(obj);
	}

	@Override
	public WorkSheet queryObjById(Long id) {
		return workSheetDao.findById(id);
	}
	@Override
	public void deleteObj(Long id) {
		workSheetDao.deleteById(id);
	}

	@Override
	public List<WorkSheet> queryOtherWorkSheetByDeviceSiteId(Long deviceSiteId) {
		String hql = "from WorkSheet ws where ws.id in "
				+ "(select wsd.workSheet.id from WorkSheetDetail wsd where wsd.deviceSiteId=?0)"
				+ " and ws.id not in (select pr.workSheetId from ProcessRecord pr where pr.deviceSiteId=?0)"
				+ " and ws.deleted=?1";
		return workSheetDao.findByHQL(hql, new Object[] {deviceSiteId,false});
	}

	public List<WorkSheet> queryOtherWorkSheetByDeviceSiteIdAndConditions(Long deviceSiteId,String q){
		String hql = "from WorkSheet ws where ws.id in "
				+ "(select wsd.workSheet.id from WorkSheetDetail wsd where wsd.deviceSiteId=?0)"
				+ " and ws.id not in (select pr.workSheetId from ProcessRecord pr where pr.deviceSiteId=?0)"
				+ " and ws.deleted=?1 and (ws.no like ?2 or ws.workPieceCode like ?2 or ws.customerGraphNumber like ?2"
				+ " or ws.batchNumber like ?2 or ws.stoveNumber like ?2 or ws.productCount like ?2)";
		return workSheetDao.findByHQL(hql, new Object[] {deviceSiteId,false,"%"+q+"%"});
	}
	
	@Override
	public void addWorkSheet(WorkSheet workSheet) {
		workSheetDao.save(workSheet);
		
		//for(WorkSheetDetail detail : Constant.workSheetDetail) {
		for(int i = 0;i<Constant.workSheetDetail.size();i++) {
			WorkSheetDetail detail = Constant.workSheetDetail.get(i);
			detail.setWorkSheet(workSheet);
			workSheetDetailDao.save(detail);
			
			Set<WorkSheetDetailParametersRecord> parameterRecords = detail.getParameterRecords();
			for(WorkSheetDetailParametersRecord wsdpr : parameterRecords) {
				WorkSheetDetailParametersRecord paramRecord = new WorkSheetDetailParametersRecord();
				paramRecord.setWorkSheetDetail(detail);
				paramRecord.setCurrentDate(wsdpr.getCurrentDate());
				paramRecord.setDeleted(wsdpr.getDeleted());
				paramRecord.setId(wsdpr.getId());
				paramRecord.setLowLine(wsdpr.getLowLine());
				paramRecord.setUpLine(wsdpr.getUpLine());
				paramRecord.setStandardValue(wsdpr.getStandardValue());
				paramRecord.setNote(wsdpr.getNote());
				paramRecord.setParameterCode(wsdpr.getParameterCode());
				paramRecord.setParameterName(wsdpr.getParameterName());
				paramRecord.setParameterValue(wsdpr.getParameterValue());
				paramRecord.setStatus(wsdpr.getStatus());
				paramRecord.setStatusCode(wsdpr.getStatusCode());
				paramRecord.setUnit(wsdpr.getUnit());
				workSheetDetailParametersRecordDao.save(paramRecord);
			}
		}
	}

	@Override
	public void updateWorkSheetAndWorkSheetDetails(WorkSheet workSheet, List<WorkSheetDetail> workSheetDetails) {
		WorkSheet ws = workSheetDao.findById(workSheet.getId());
		ws.setBatchNumber(workSheet.getBatchNumber());
		ws.setManufactureDate(workSheet.getManufactureDate());
		ws.setProductCount(workSheet.getProductCount());
		ws.setStoveNumber(workSheet.getStoveNumber());
		ws.setNote(workSheet.getNote());
		ws.setProductionUnitId(workSheet.getProductionUnitId());
		ws.setProductionUnitName(workSheet.getProductionUnitName());
		ws.setProductionUnitCode(workSheet.getProductionUnitCode());
		//更新工单
		workSheetDao.update(ws);
		//更新工单详情
		if(workSheetDetails!=null ) {
			for(int i = 0;i<workSheetDetails.size();i++) {
				WorkSheetDetail detail = workSheetDetails.get(i);
				WorkSheetDetail d = workSheetDetailDao.findById(detail.getId());
				d.setCompleteCount(detail.getCompleteCount());
				d.setStatus(detail.getStatus());
				d.setNote(detail.getNote());
				d.setUnqualifiedCount(detail.getUnqualifiedCount());
				d.setQualifiedCount(detail.getQualifiedCount());
				d.setRepairCount(detail.getRepairCount());
				d.setReportCount(detail.getReportCount());
				d.setScrapCount(detail.getScrapCount());
				d.setProductionCount(detail.getProductionCount());
				workSheetDetailDao.update(d);
			}
		}
	}
}
