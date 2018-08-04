package com.digitzones.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.digitzones.constants.Constant;
import com.digitzones.model.Classes;
import com.digitzones.model.LostTimeRecord;
import com.digitzones.model.Pager;
import com.digitzones.model.User;
import com.digitzones.service.ILostTimeRecordService;
/**
 * 损时业务静态代理类
 * @author zdq
 * 2018年8月2日
 */
@Service("lostTimeRecordServiceProxy")
public class LostTimeRecordServiceProxy implements ILostTimeRecordService {
	private ILostTimeRecordService lostTimeRecordService;
	private ProcessEngine processEngine;
	@Autowired
	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}

	@Autowired
	public void setLostTimeRecordService(@Qualifier("lostTimeRecordService") ILostTimeRecordService lostTimeRecordService) {
		this.lostTimeRecordService = lostTimeRecordService;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return lostTimeRecordService.queryObjs(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(LostTimeRecord obj) {
		lostTimeRecordService.updateObj(obj);
	}

	@Override
	public LostTimeRecord queryByProperty(String name, String value) {
		return lostTimeRecordService.queryByProperty(name, value);
	}

	@Override
	public Serializable addObj(LostTimeRecord obj) {
		Serializable id = lostTimeRecordService.addObj(obj);
		return id;
	}

	@Override
	public LostTimeRecord queryObjById(Long id) {
		return lostTimeRecordService.queryObjById(id);
	}

	@Override
	public void deleteObj(Long id) {
		lostTimeRecordService.deleteObj(id);
	}

	@Override
	public List<LostTimeRecord> queryLostTimeRecordByYearAndMonth(Integer year, Integer month) {
		return lostTimeRecordService.queryLostTimeRecordByYearAndMonth(year, month);
	}

	@Override
	public Double queryHoursOfLostTimeRecordByYearAndMonth(Integer year, Integer month) {
		return lostTimeRecordService.queryHoursOfLostTimeRecordByYearAndMonth(year, month);
	}

	@Override
	public Double queryHoursOfPlanHaltByYearAndMonth(Integer year, Integer month) {
		return lostTimeRecordService.queryHoursOfPlanHaltByYearAndMonth(year, month);
	}

	@Override
	public Double queryLostTime(Classes c, Long deviceSiteId, Date date) {
		return lostTimeRecordService.queryLostTime(c, deviceSiteId, date);
	}

	@Override
	public Double queryPlanHaltTime(Classes c, Long deviceSiteId, Date date) {
		return lostTimeRecordService.queryPlanHaltTime(c, deviceSiteId, date);
	}

	@Override
	public Double queryLostTime4PerDay(Classes c, Long deviceSiteId, Date date) {
		return lostTimeRecordService.queryLostTime4PerDay(c, deviceSiteId, date);
	}

	@Override
	public Integer queryLostTime4RealTime(Date date) {
		return lostTimeRecordService.queryLostTime4RealTime(date);
	}

	@Override
	public void confirm(LostTimeRecord lostTimeRecord,User user,Map<String,Object> args) {
		lostTimeRecordService.confirm(lostTimeRecord,user,args);
		TaskService taskService = processEngine.getTaskService();
		Task task = taskService.createTaskQuery().processInstanceBusinessKey(lostTimeRecord.getId()+"").singleResult();
		taskService.setVariableLocal(task.getId(), "suggestion", args.get("suggestion"));
		taskService.complete(task.getId());
	}

	@Override
	public Serializable addLostTimeRecord(LostTimeRecord lostTimeRecord, User user,Map<String,Object> args) {
		//执行主要业务
		Serializable id = lostTimeRecordService.addLostTimeRecord(lostTimeRecord, user,args);
		Map<String, Object> variables = new HashMap<>();
		if(user!=null) {
			variables.put(Constant.Workflow.APPLY_USER_ID, user.getUsername());
		}
		RuntimeService runtimeService = processEngine.getRuntimeService();
		//启动流程
		runtimeService.startProcessInstanceByKey((String)args.get("businessKey"),""+id, variables);
		return id;
	}

	@Override
	public void deleteLostTimeRecord(LostTimeRecord lostTimeRecord) {
		RuntimeService runtimeService = processEngine.getRuntimeService();
		List<Execution> executions = runtimeService.createExecutionQuery().processInstanceBusinessKey(lostTimeRecord.getId()+"").list();
		if(executions!=null && executions.size()>0) {
			throw new RuntimeException("该记录上流程还没结束，无法删除！");
		}	
		
		lostTimeRecordService.deleteLostTimeRecord(lostTimeRecord);
	}
}
