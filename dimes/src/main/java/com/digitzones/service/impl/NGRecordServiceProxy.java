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
import com.digitzones.model.NGRecord;
import com.digitzones.model.Pager;
import com.digitzones.model.User;
import com.digitzones.service.INGRecordService;
@Service("ngRecordServiceProxy")
public class NGRecordServiceProxy implements INGRecordService {
	private INGRecordService ngRecordService;
	private ProcessEngine processEngine;
	@Autowired
	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}

	@Autowired
	public void setNgRecordService(@Qualifier("ngRecordService")INGRecordService ngRecordService) {
		this.ngRecordService = ngRecordService;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return ngRecordService.queryObjs(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(NGRecord obj) {
		ngRecordService.updateObj(obj);
	}

	@Override
	public NGRecord queryByProperty(String name, String value) {
		return ngRecordService.queryByProperty(name, value);
	}

	@Override
	public Serializable addObj(NGRecord obj) {
		Serializable id = ngRecordService.addObj(obj);
		return id;
	}

	@Override
	public NGRecord queryObjById(Long id) {
		return ngRecordService.queryObjById(id);
	}

	@Override
	public void deleteObj(Long id) {
		ngRecordService.deleteObj(id);
	}

	@Override
	public void auditNGRecord(NGRecord record,User user,Map<String,Object> args) {
		ngRecordService.auditNGRecord(record,user,args);
		
		Map<String, Object> variables = new HashMap<>();
		variables.put(Constant.Workflow.NG_REVIEW_PERSON, "");
		variables.put(Constant.Workflow.NG_REVIEW_PERSONS, "");
		variables.put(Constant.Workflow.NG_REVIEW_ROLES, "NG复核人");
		TaskService taskService = processEngine.getTaskService();
		Task task  = taskService.createTaskQuery().processInstanceBusinessKey(record.getId()+"").singleResult();
		if(task!=null) {
			taskService.setVariableLocal(task.getId(), "suggestion", args.get("suggestion"));
			taskService.complete(task.getId(), variables);
		}
	}

	@Override
	public Integer queryScrapCountByDateAndProcessId(Date date, Long processId) {
		return ngRecordService.queryScrapCountByDateAndProcessId(date, processId);
	}

	@Override
	public Integer queryNgCountByDeviceSiteId(Long deviceSiteId, Date today) {
		return ngRecordService.queryNgCountByDeviceSiteId(deviceSiteId, today);
	}

	@Override
	public void reviewNGRecord(NGRecord record, User user, Map<String, Object> args) {
		ngRecordService.reviewNGRecord(record, user, args);
		Map<String, Object> variables = new HashMap<>();
		variables.put(Constant.Workflow.NG_CONFIRM_PERSON, "");
		variables.put(Constant.Workflow.NG_CONFIRM_PERSONS, "");
		variables.put(Constant.Workflow.NG_CONFIRM_ROLES, "NG确认人");
		TaskService taskService = processEngine.getTaskService();
		Task task  = taskService.createTaskQuery().processInstanceBusinessKey(record.getId()+"").singleResult();
		if(task!=null) {
			taskService.setVariableLocal(task.getId(), "suggestion", args.get("suggestion"));
			taskService.complete(task.getId(), variables);
		}
	}

	@Override
	public void confirmNGRecord(NGRecord record, User user, Map<String, Object> args) {
		ngRecordService.confirmNGRecord(record, user, args);
		TaskService taskService = processEngine.getTaskService();
		Task task  = taskService.createTaskQuery().processInstanceBusinessKey(record.getId()+"").singleResult();
		if(task!=null) {
			taskService.setVariableLocal(task.getId(), "suggestion", args.get("suggestion"));
			taskService.complete(task.getId());
		}
	}

	@Override
	public void deleteNGRecord(NGRecord record) {
		RuntimeService runtimeService = processEngine.getRuntimeService();
		List<Execution> executions = runtimeService.createExecutionQuery().processInstanceBusinessKey(record.getId()+"").list();
		if(executions!=null && executions.size()>0) {
			throw new RuntimeException("该记录上流程还没结束，无法删除！");
		}
		ngRecordService.deleteNGRecord(record);
	}

	@Override
	public Serializable addNGRecord(NGRecord record, User user, Map<String, Object> args) {
		Serializable id =ngRecordService.addNGRecord(record, user, args);
		Map<String, Object> variables = new HashMap<>();
		variables.put(Constant.Workflow.APPLY_USER_ID, user.getUsername());
		
		variables.put(Constant.Workflow.NG_AUDIT_PERSON, "");
		variables.put(Constant.Workflow.NG_AUDIT_PERSONS, "");
		variables.put(Constant.Workflow.NG_AUDIT_ROLES, "NG审核人");
		RuntimeService runtimeService = processEngine.getRuntimeService();
		//启动流程
		runtimeService.startProcessInstanceByKey((String)args.get("businessKey"),""+id, variables);
		return id;
	}
}
