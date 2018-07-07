package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.constants.Constant;
import com.digitzones.dao.IProcessRecordDao;
import com.digitzones.dao.IWorkSheetDetailDao;
import com.digitzones.model.Pager;
import com.digitzones.model.ProcessRecord;
import com.digitzones.model.WorkSheetDetail;
import com.digitzones.service.IProcessRecordService;
@Service
public class ProcessRecordServiceImpl implements IProcessRecordService {
	private IProcessRecordDao processRecordDao;
	private IWorkSheetDetailDao workSheetDetailDao;
	@Autowired
	public void setWorkSheetDetailDao(IWorkSheetDetailDao workSheetDetailDao) {
		this.workSheetDetailDao = workSheetDetailDao;
	}

	@Autowired
	public void setProcessRecordDao(IProcessRecordDao processRecordDao) {
		this.processRecordDao = processRecordDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return processRecordDao.findByPage(hql, pageNo, pageSize, values);
	}
	@Override
	public void updateObj(ProcessRecord obj) {
		ProcessRecord pr = processRecordDao.findById(obj.getId());
		if(!pr.getStatus().equals(obj.getStatus())) {
			//根据工单id，工序id，站点id更新工单详情信息
			String status = obj.getStatus();
			Object[] values = {obj.getWorkSheetId(),obj.getProcessId(),obj.getDeviceSiteId()};
			//不合格品增1
			if(Constant.ProcessRecord.NG.equals(status)) {
				List<WorkSheetDetail> details = workSheetDetailDao.findByHQL("from WorkSheetDetail wsd where wsd.workSheet.id=?0 and wsd.processId=?1 and wsd.deviceSiteId=?2", values);
				for(WorkSheetDetail d : details) {
					d.setUnqualifiedCount(d.getUnqualifiedCount()+1);
					workSheetDetailDao.update(d);
				}
			}
			//合格品增1
			if(Constant.ProcessRecord.OK.equals(status)) {
				List<WorkSheetDetail> details = workSheetDetailDao.findByHQL("from WorkSheetDetail wsd where wsd.workSheet.id=?0 and wsd.processId=?1 and wsd.deviceSiteId=?2", values);
				for(WorkSheetDetail d : details) {
					d.setQualifiedCount(d.getQualifiedCount()+1);
					workSheetDetailDao.update(d);
				}
			}
			//原不合格品减1
			if(Constant.ProcessRecord.NG.equals(pr.getStatus())) {
				List<WorkSheetDetail> details = workSheetDetailDao.findByHQL("from WorkSheetDetail wsd where wsd.workSheet.id=?0 and wsd.processId=?1 and wsd.deviceSiteId=?2", values);
				for(WorkSheetDetail d : details) {
					d.setUnqualifiedCount(d.getUnqualifiedCount()-1);
					workSheetDetailDao.update(d);
				}
			}
			//原合格品减1
			if(Constant.ProcessRecord.OK.equals(pr.getStatus())) {
				List<WorkSheetDetail> details = workSheetDetailDao.findByHQL("from WorkSheetDetail wsd where wsd.workSheet.id=?0 and wsd.processId=?1 and wsd.deviceSiteId=?2", values);
				for(WorkSheetDetail d : details) {
					d.setQualifiedCount(d.getQualifiedCount()-1);
					workSheetDetailDao.update(d);
				}
			}
		}
		processRecordDao.update(obj);
	}

	@Override
	public ProcessRecord queryByProperty(String name, String value) {
		return processRecordDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(ProcessRecord obj) {
		//根据工单id，工序id，站点id更新工单详情信息
		String status = obj.getStatus();
		Object[] values = {obj.getWorkSheetId(),obj.getProcessId(),obj.getDeviceSiteId()};
		//不合格品增1
		if(Constant.ProcessRecord.NG.equals(status)) {
			List<WorkSheetDetail> details = workSheetDetailDao.findByHQL("from WorkSheetDetail wsd where wsd.workSheet.id=?0 and wsd.processId=?1 and wsd.deviceSiteId=?2", values);
			for(WorkSheetDetail d : details) {
				d.setUnqualifiedCount(d.getUnqualifiedCount()+1);
				workSheetDetailDao.update(d);
			}
		}
		//合格品增1
		if(Constant.ProcessRecord.OK.equals(status)) {
			List<WorkSheetDetail> details = workSheetDetailDao.findByHQL("from WorkSheetDetail wsd where wsd.workSheet.id=?0 and wsd.processId=?1 and wsd.deviceSiteId=?2", values);
			for(WorkSheetDetail d : details) {
				d.setQualifiedCount(d.getQualifiedCount()+1);
				workSheetDetailDao.update(d);
			}
		}
		//完工数增1 
		List<WorkSheetDetail> details = workSheetDetailDao.findByHQL("from WorkSheetDetail wsd where wsd.workSheet.id=?0 and wsd.processId=?1 and wsd.deviceSiteId=?2", values);
		for(WorkSheetDetail d : details) {
			d.setCompleteCount(d.getCompleteCount()+1);
			workSheetDetailDao.update(d);
		}
		return processRecordDao.save(obj);
	}

	@Override
	public ProcessRecord queryObjById(Long id) {
		return processRecordDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		processRecordDao.deleteById(id);
	}

	@Override
	public List<Long[]> queryCountByDeviceSiteIdAndStatus(Long deviceSiteId, String status) {
		return processRecordDao.queryCountByDeviceSiteIdAndStatus(deviceSiteId, status);
	}

	@Override
	public List<Long[]> queryPreMonthDeviceSiteIdAndStatus(Long deviceSiteId, String status) {
		return processRecordDao.queryPreMonthDeviceSiteIdAndStatus(deviceSiteId, status);
	}

	@Override
	public List<Long[]> queryCurrentMonthDeviceSiteIdAndStatus(Long deviceSiteId, String status) {
		return processRecordDao.queryCurrentMonthDeviceSiteIdAndStatus(deviceSiteId, status);
	}

	@Override
	public Long queryCountByDeviceSiteIdAndNotNg(Long deviceSiteId) {
		return processRecordDao.queryCountByDeviceSiteIdAndNotNg(deviceSiteId);
	}

	@Override
	public Long queryCurrentDayCountByDeviceSiteId(Long deviceSiteId) {
		return processRecordDao.queryCurrentDayCountByDeviceSiteId(deviceSiteId);
	}

	@Override
	public List<Long[]> queryByDay(Long deviceSiteId, String status, Date now) {
		return processRecordDao.queryByDay(deviceSiteId, status, now);
	}
}
