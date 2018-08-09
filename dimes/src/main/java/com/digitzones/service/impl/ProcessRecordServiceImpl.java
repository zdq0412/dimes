package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IProcessRecordDao;
import com.digitzones.dao.IWorkSheetDetailDao;
import com.digitzones.model.Classes;
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
		//ProcessRecord pr = processRecordDao.findById(obj.getId());
		/*if(!pr.getStatus().equals(obj.getStatus())) {
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
		}*/
		processRecordDao.update(obj);
	}

	@Override
	public ProcessRecord queryByProperty(String name, String value) {
		return processRecordDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(ProcessRecord obj) {
		//根据工单id，工序id，站点id更新工单详情信息
		//String status = obj.getStatus();
		Object[] values = {obj.getWorkSheetId(),obj.getProcessId(),obj.getDeviceSiteId()};
		//不合格品增1
		/*if(Constant.ProcessRecord.NG.equals(status)) {
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
		}*/
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
	public Long queryCurrentDayCountByDeviceSiteId(Long deviceSiteId) {
		return processRecordDao.queryCurrentDayCountByDeviceSiteId(deviceSiteId);
	}

	@Override
	public List<Long[]> queryByDay(Long deviceSiteId, String status, Date now) {
		return processRecordDao.queryByDay(deviceSiteId, status, now);
	}
	@Override
	public Integer queryOutput4EmployeePerMonth(Date date,Long empId) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return processRecordDao.queryOutput4EmployeePerMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1,empId);
	}

	@Override
	public Integer queryOutput4ProcessPerMonth(Date date,Long processId) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return processRecordDao.queryOutput4ProcessPerMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1,processId);
	}

	@Override
	public int queryOutput4DeviceSitePerMonth(Date date,Long deviceSiteId) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return processRecordDao.queryOutput4DeviceSitePerMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1,deviceSiteId);
	}

	@Override
	public Integer queryWorkSheetNGCountPerMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Integer result = processRecordDao.queryWorkSheetNGCountPerMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1);
		return result==null?0:result;
	}

	@Override
	public Integer queryWorkSHeetNotNGCountPerMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Integer result = processRecordDao.queryWorkSHeetNotNGCountPerMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1);
		return result==null?0:result;
	}

	@Override
	public Integer queryCountByClassesIdAndDay(Long classesId, Date day,Long productionUnitId) {
		Integer result = processRecordDao.queryCountByClassesIdAndDay(classesId, day,productionUnitId);
		return result==null?0:result;
	}

	@Override
	public Integer queryWorkSheetNGCountPerClasses4ProductionUnit(Date date, Long classId, Long productionUnitId) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Integer result = processRecordDao.queryWorkSheetNGCountPerClasses4ProductionUnit(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1,c.get(Calendar.DATE), classId, productionUnitId);
		return result==null?0:result;
	}

	@Override
	public Integer queryWorkSHeetNotNGCountPerClasses4ProductionUnit(Date date, Long classId, Long productionUnitId) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Integer result = processRecordDao.queryWorkSHeetNotNGCountPerClasses4ProductionUnit(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1,c.get(Calendar.DATE), classId, productionUnitId);
		return result==null?0:result;
	}

	@Override
	public Integer queryWorkSheetScrapCountPerMonth(Date date,Long ngTypeId) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Integer result = processRecordDao.queryWorkSheetScrapCountPerMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1,ngTypeId);
		return result==null?0:result;
	}

	@Override
	public Integer queryWorkSheetScrapCountPerMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Integer result = processRecordDao.queryWorkSheetScrapCountPerMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1);
		return result==null?0:result;
	}

	@Override
	public Object[] queryCountAndSumOfStandardBeatAndSumOfShortHalt4CurrentClass(Classes c, Long deviceSiteId,Date date) {
		return processRecordDao.queryCountAndSumOfStandardBeatAndSumOfShortHalt4CurrentClass(c, deviceSiteId,date);
	}

	@Override
	public Object[] queryCountAndSumOfStandardBeatAndSumOfShortHaltFromBeginOfMonthUntilTheDate(Date date) {
		return processRecordDao.queryCountAndSumOfStandardBeatAndSumOfShortHaltFromBeginOfMonthUntilTheDate(date);
	}
}
