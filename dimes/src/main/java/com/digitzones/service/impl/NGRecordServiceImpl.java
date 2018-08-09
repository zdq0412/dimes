package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.constants.Constant;
import com.digitzones.dao.INGProcessMethodDao;
import com.digitzones.dao.INGRecordDao;
import com.digitzones.model.Classes;
import com.digitzones.model.NGProcessMethod;
import com.digitzones.model.NGRecord;
import com.digitzones.model.Pager;
import com.digitzones.model.User;
import com.digitzones.model.WorkSheetDetail;
import com.digitzones.service.INGRecordService;
import com.digitzones.service.IWorkSheetDetailService;
@Service("ngRecordService")
public class NGRecordServiceImpl implements INGRecordService {
	private INGRecordDao ngRecordDao;
	private IWorkSheetDetailService workSheetDetailService;
	private INGProcessMethodDao ngProcessMethodDao;
	@Autowired
	public void setNgProcessMethodDao(INGProcessMethodDao ngProcessMethodDao) {
		this.ngProcessMethodDao = ngProcessMethodDao;
	}

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
		Serializable ngRecordId = ngRecordDao.save(obj);
		//添加处理详情
		NGProcessMethod method = new NGProcessMethod();
		method.setNgRecord(obj);
		method.setProcessMethod(Constant.ProcessRecord.SCRAP);

		NGProcessMethod method1 = new NGProcessMethod();
		method1.setNgRecord(obj);
		method1.setProcessMethod(Constant.ProcessRecord.REPAIR);

		NGProcessMethod method2 = new NGProcessMethod();
		method2.setNgRecord(obj);
		method2.setProcessMethod(Constant.ProcessRecord.COMPROMISE);

		String processMethod = obj.getProcessingMethod();
		switch(processMethod) {
		case Constant.ProcessRecord.SCRAP:method.setNgCount(obj.getNgCount());break;
		case Constant.ProcessRecord.REPAIR:method1.setNgCount(obj.getNgCount());break;
		case Constant.ProcessRecord.COMPROMISE:method2.setNgCount(obj.getNgCount());break;
		}
		//添加处理详情
		ngProcessMethodDao.save(method);
		ngProcessMethodDao.save(method1);
		ngProcessMethodDao.save(method2);
		return ngRecordId;
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
	public void auditNGRecord(NGRecord record,User user,Map<String,Object> args) {
		record.setAuditDate(new Date());
		if(user!=null) {
			record.setAuditorId(user.getId());
			record.setAuditorName(user.getUsername());
		}
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

	@Override
	public Integer queryScrapCountByDateAndProcessId(Date date, Long processId) {
		return ngRecordDao.queryScrapCountByDateAndProcessId(date, processId);
	}

	@Override
	public Integer queryNgCountByDeviceSiteId(Long deviceSiteId, Date today) {
		return ngRecordDao.queryNgCountByDeviceSiteId(deviceSiteId, today);
	}

	@Override
	public void reviewNGRecord(NGRecord record, User user, Map<String, Object> args) {
		record.setReviewDate(new Date());
		if(user!=null) {
			record.setReviewerId(user.getId());
			record.setReviewerName(user.getUsername());
		}
		ngRecordDao.update(record);
	}

	@Override
	public void confirmNGRecord(NGRecord record, User user, Map<String, Object> args) {
		record.setConfirmDate(new Date());
		if(user!=null) {
			record.setConfirmUserId(user.getId());
			record.setConfirmUsername(user.getUsername());
		}

		ngRecordDao.update(record);
	}

	@Override
	public void deleteNGRecord(NGRecord record) {
		record.setDeleted(true);
		ngRecordDao.update(record);
	}

	@Override
	public Serializable addNGRecord(NGRecord record, User user, Map<String, Object> args) {
		if(user!=null) {
			record.setInputUserId(user.getId());
			record.setInputDate(new Date());
			record.setInputUsername(user.getUsername());
		}
		return this.addObj(record);
	}

	@Override
	public Integer queryNgCount4Class(Classes classes, Long deviceSiteId,Date date) {
		return ngRecordDao.queryNgCount4Class(classes, deviceSiteId, date);
	}

	@Override
	public Integer queryNgCountFromBeginOfMonthUntilTheDate(Date date) {
		return ngRecordDao.queryNgCountFromBeginOfMonthUntilTheDate(date);
	}

	@Override
	public Long queryNgCount4TheDate(Date date) {
		return ngRecordDao.queryNgCount4TheDate(date);
	}
}
