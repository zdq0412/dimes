package com.digitzones.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitzones.dao.IWorkpieceProcessParameterMappingDao;
import com.digitzones.model.Pager;
import com.digitzones.model.WorkpieceProcessParameterMapping;
import com.digitzones.service.IWorkpieceProcessParameterMappingService;
@Service
public class WorkpieceProcessParameterMappingServiceImpl implements IWorkpieceProcessParameterMappingService {
	private IWorkpieceProcessParameterMappingDao workpieceProcessParameterMappingDao;

	@Autowired
	public void setWorkpieceProcessParameterMappingDao(
			IWorkpieceProcessParameterMappingDao workpieceProcessParameterMappingDao) {
		this.workpieceProcessParameterMappingDao = workpieceProcessParameterMappingDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return workpieceProcessParameterMappingDao.findByPage(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(WorkpieceProcessParameterMapping obj) {
		workpieceProcessParameterMappingDao.update(obj);
	}

	@Override
	public WorkpieceProcessParameterMapping queryByProperty(String name, String value) {
		return workpieceProcessParameterMappingDao.findSingleByProperty(name, value);
	}

	@Override
	public Serializable addObj(WorkpieceProcessParameterMapping obj) {
		return workpieceProcessParameterMappingDao.save(obj);
	}

	@Override
	public WorkpieceProcessParameterMapping queryObjById(Long id) {
		return workpieceProcessParameterMappingDao.findById(id);
	}

	@Override
	public void deleteObj(Long id) {
		workpieceProcessParameterMappingDao.deleteById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<WorkpieceProcessParameterMapping> queryWorkpieceProcessParameterMappingByWorkpieceId(
			Long workpieceId, Integer rows, Integer page) {
	/*	//查询'工件工序站点'表中是否存在该工件信息
		Long count = workpieceProcessParameterMappingDao.queryCountByWorkpieceId(workpieceId);
		//不存在
		if(count<=0) {
			//查询所有工序
			List<WorkpieceProcessMapping> wpmList = workpieceProcessMappingDao.queryByWorkpieceId(workpieceId);
			if(wpmList != null && wpmList.size()>0) {
				//将工序信息和站点信息添加到'工件工序站点'表中
				for(WorkpieceProcessMapping wpm : wpmList) {
					Processes p = wpm.getProcess();
					//根据工序查询站点
					List<ProcessParameterMapping> pdsmList = processParameterMappingDao.findByHQL("from ProcessParameterMapping pdsm where pdsm.processes.id=?0", new Object[] {p.getId()});
					for(ProcessParameterMapping pdsm : pdsmList) {
						Parameter ds = pdsm.getParameter();
						//将关联添加到‘工件工序站点’
						WorkpieceProcessParameterMapping  wpdsm = new WorkpieceProcessParameterMapping();
						wpdsm.setParameter(ds);
						wpdsm.setWorkpieceProcess(wpm);
						
						workpieceProcessParameterMappingDao.save(wpdsm);
					}
				}
			}
		}*/
		return this.queryObjs("from WorkpieceProcessParameterMapping wpdsm where wpdsm.workpieceProcess.workpiece.id=?0", page, rows, new Object[] {workpieceId});
	}
}
