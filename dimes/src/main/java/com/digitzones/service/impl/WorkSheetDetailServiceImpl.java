package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.constants.Constant;
import com.digitzones.dao.IWorkSheetDetailDao;
import com.digitzones.dao.IWorkpieceProcessMappingDao;
import com.digitzones.dao.IWorkpieceProcessParameterMappingDao;
import com.digitzones.model.Pager;
import com.digitzones.model.ProcessDeviceSiteMapping;
import com.digitzones.model.WorkSheetDetail;
import com.digitzones.model.WorkSheetDetailParametersRecord;
import com.digitzones.model.WorkpieceProcessMapping;
import com.digitzones.model.WorkpieceProcessParameterMapping;
import com.digitzones.service.IWorkSheetDetailService;
@Service
public class WorkSheetDetailServiceImpl implements IWorkSheetDetailService {
	private IWorkSheetDetailDao workSheetDetailDao;
	private Random random = new Random();
	private IWorkpieceProcessMappingDao workPieceProcessMappingDao;
	private IWorkpieceProcessParameterMappingDao workpieceProcessParameterMappingDao;
	@Autowired
	public void setWorkpieceProcessParameterMappingDao(
			IWorkpieceProcessParameterMappingDao workpieceProcessParameterMappingDao) {
		this.workpieceProcessParameterMappingDao = workpieceProcessParameterMappingDao;
	}

	@Autowired
	public void setWorkPieceProcessMappingDao(IWorkpieceProcessMappingDao workPieceProcessMappingDao) {
		this.workPieceProcessMappingDao = workPieceProcessMappingDao;
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
		//根据工件id查询工件工序和站点对象
		String hql = "from WorkpieceProcessMapping wpdsm where wpdsm.workpiece.id=?0";
		List<WorkpieceProcessMapping> workpieceProcessMappings = workPieceProcessMappingDao.findByHQL(hql, new Object[] {workpieceId});
		for(WorkpieceProcessMapping wpm : workpieceProcessMappings) {
			WorkSheetDetail detail = new WorkSheetDetail();
			detail.setId(random.nextLong());
			detail.setProcessCode(wpm.getProcess().getCode());
			detail.setProcessId(wpm.getProcess().getId());
			detail.setProcessName(wpm.getProcess().getName());
			detail.setParameterSource(wpm.getParameterValueType());
			
			Constant.workSheetDetail.add(detail);
		}
		
		long recordId = 1;
		
		//查询工件工序的参数
		for(WorkSheetDetail detail : Constant.workSheetDetail) {
			String hql1 = "from WorkpieceProcessParameterMapping wppm where wppm.workpieceProcess.workpiece.id=?0 and wppm.workpieceProcess.process.id=?1";
		    List<WorkpieceProcessParameterMapping>  list = workpieceProcessParameterMappingDao.findByHQL(hql1, new Object[] {workpieceId,detail.getProcessId()});
		    
		    if(list!=null && list.size()>0) {
		    	Set<WorkSheetDetailParametersRecord> parametersRecords = new HashSet<>();
		    	for(WorkpieceProcessParameterMapping wppm : list) {
		    		WorkSheetDetailParametersRecord wdpr = new WorkSheetDetailParametersRecord();
		    		wdpr.setLowLine(wppm.getLowLine());
		    		wdpr.setUpLine(wppm.getUpLine());
		    		wdpr.setStandardValue(wppm.getStandardValue());
		    		wdpr.setParameterCode(wppm.getParameter().getCode());
		    		wdpr.setParameterName(wppm.getParameter().getName());
		    		wdpr.setId(recordId++);
		    		
		    		parametersRecords.add(wdpr);
		    	}
		    	detail.setParameterRecords(parametersRecords);
		    }
		}
	}

	@Override
	public Pager<ProcessDeviceSiteMapping> queryOtherDeviceSitesByProcessId(Long processId, int pageNo, int pageSize) {
		List<Long> deviceSiteIdList = new ArrayList<>();
		for(WorkSheetDetail detail : Constant.workSheetDetail) {
			Long deviceSiteId = detail.getDeviceSiteId();
			if(deviceSiteId!=null && deviceSiteId>0) {
				deviceSiteIdList.add(detail.getDeviceSiteId());
			}
		}
		return workSheetDetailDao.queryDeviceSiteOutOfListByProcessId(deviceSiteIdList, processId, pageNo, pageSize);
	}

	@Override
	public Long queryCountByProcessId(Long processId,Long workSheetId) {
		return workSheetDetailDao.queryCountByProcessIdAndWorkSheetId(processId, workSheetId);
	}
}
