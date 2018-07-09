package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.constants.Constant;
import com.digitzones.dao.INGRecordDao;
import com.digitzones.model.NGRecord;
import com.digitzones.model.Pager;
import com.digitzones.model.WorkSheetDetail;
import com.digitzones.service.INGRecordService;
import com.digitzones.service.IWorkSheetDetailService;
@Service
public class NGRecordServiceImpl implements INGRecordService {
	private INGRecordDao ngRecordDao;
	private IWorkSheetDetailService workSheetDetailService;
	public void setWorkSheetDetailService(IWorkSheetDetailService workSheetDetailService) {
		this.workSheetDetailService = workSheetDetailService;
	}

	@Autowired
	public void setNgRecordDao(INGRecordDao ngRecordDao) {
		this.ngRecordDao = ngRecordDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return ngRecordDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(NGRecord obj) {
		ngRecordDao.update(obj);
	}

	@Override
	public NGRecord queryByProperty(String name, String value) {
		return ngRecordDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(NGRecord obj) {
		return ngRecordDao.save(obj);
	}

	@Override
	public NGRecord queryObjById(Long id) {
		return ngRecordDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		ngRecordDao.deleteById(id);
	}

	@Override
	public void auditNGRecord(NGRecord record) {
		String processMethod = record.getProcessingMethod();
		//判断是否存在工单
		Long workSheetId = record.getWorkSheetId();
		if(workSheetId!=null) {
			//获取设备站点id
			Long deviceSiteId = record.getDeviceSiteId();
			//获取工序代码
			String processCode = record.getProcessCode();
			
			if(Constant.ProcessRecord.REPAIR.equals(processMethod)) {
				//根据工单和设备站点查找
				List<WorkSheetDetail> details = workSheetDetailService.queryWorkSheetDetailByWorkSheetIdAndDeviceSiteIdAndProccessId(workSheetId, deviceSiteId, processCode);
				for(WorkSheetDetail detail : details) {
					detail.setRepairCount(detail.getRepairCount()+1);
					detail.setQualifiedCount(detail.getQualifiedCount()-1);
					detail.setUnqualifiedCount(detail.getUnqualifiedCount()+1);
					
					workSheetDetailService.updateObj(detail);
				}
			}
			if(Constant.ProcessRecord.SCRAP.equals(processMethod)) {
				//根据工单和设备站点查找
				List<WorkSheetDetail> details = workSheetDetailService.queryWorkSheetDetailByWorkSheetIdAndDeviceSiteIdAndProccessId(workSheetId, deviceSiteId, processCode);
				for(WorkSheetDetail detail : details) {
					detail.setScrapCount(detail.getScrapCount()+1);
					detail.setQualifiedCount(detail.getQualifiedCount()-1);
					detail.setUnqualifiedCount(detail.getUnqualifiedCount()+1);
					
					workSheetDetailService.updateObj(detail);
				}
			}
		}
		ngRecordDao.update(record);
	}
}
