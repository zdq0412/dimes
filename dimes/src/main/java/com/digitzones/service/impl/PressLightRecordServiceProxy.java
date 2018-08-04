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
import com.digitzones.model.Pager;
import com.digitzones.model.PressLightRecord;
import com.digitzones.model.User;
import com.digitzones.service.IPressLightRecordService;
@Service("pressLightRecordServiceProxy")
public class PressLightRecordServiceProxy implements IPressLightRecordService {
	private IPressLightRecordService pressLightRecordService;
	private ProcessEngine processEngine;
	@Autowired
	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}

	@Autowired
	public void setPressLightRecordService(@Qualifier("pressLightRecordService")IPressLightRecordService pressLightRecordService) {
		this.pressLightRecordService = pressLightRecordService;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager queryObjs(String hql, int pageNo, int pageSize, Object... values) {
		return pressLightRecordService.queryObjs(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateObj(PressLightRecord obj) {
		pressLightRecordService.updateObj(obj);
	}

	@Override
	public PressLightRecord queryByProperty(String name, String value) {
		return pressLightRecordService.queryByProperty(name, value);
	}

	@Override
	public Serializable addObj(PressLightRecord obj) {
		return pressLightRecordService.addObj(obj);
	}

	@Override
	public PressLightRecord queryObjById(Long id) {
		return pressLightRecordService.queryObjById(id);
	}

	@Override
	public void deleteObj(Long id) {
		pressLightRecordService.deleteObj(id);
	}

	@Override
	public Long queryCountByPressLightTime(Date pressLightTime) {
		return pressLightRecordService.queryCountByPressLightTime(pressLightTime);
	}

	@Override
	public List<PressLightRecord> queryPressLightRecordsByTime(Date date) {
		return pressLightRecordService.queryPressLightRecordsByTime(date);
	}

	@Override
	public Serializable addPressLightRecord(PressLightRecord pressLightRecord, User user, Map<String, Object> args) {
		Serializable id = pressLightRecordService.addPressLightRecord(pressLightRecord, user, args);
		
		Map<String, Object> variables = new HashMap<>();
		variables.put(Constant.Workflow.APPLY_USER_ID, user.getUsername());
		
		variables.put(Constant.Workflow.LIGHTOUT_PERSON, "");
		variables.put(Constant.Workflow.LIGHTOUT_PERSONS, "");
		variables.put(Constant.Workflow.LIGHTOUT_ROLES, "熄灯人");
		RuntimeService runtimeService = processEngine.getRuntimeService();
		//启动流程
		runtimeService.startProcessInstanceByKey((String)args.get("businessKey"),""+id, variables);
		return id;
	}

	@Override
	public void recoverPressLightRecord(PressLightRecord pressLightRecord, User user, Map<String, Object> args) {
		pressLightRecordService.recoverPressLightRecord(pressLightRecord, user, args);
		Map<String, Object> variables = new HashMap<>();
		variables.put(Constant.Workflow.LIGHTOUT_CONFIRM_PERSON, "");
		variables.put(Constant.Workflow.LIGHTOUT_CONFIRM_PERSONS, "");
		variables.put(Constant.Workflow.LIGHTOUT_CONFIRM_ROLES, "按灯确认人");
		TaskService taskService = processEngine.getTaskService();
		Task task  = taskService.createTaskQuery().processInstanceBusinessKey(pressLightRecord.getId()+"").singleResult();
		taskService.setVariableLocal(task.getId(), "suggestion", args.get("suggestion"));
		taskService.complete(task.getId(), variables);
	}

	@Override
	public void lightoutPressLightRecord(PressLightRecord pressLightRecord, User user, Map<String, Object> args) {
		pressLightRecordService.lightoutPressLightRecord(pressLightRecord, user, args);
		Map<String, Object> variables = new HashMap<>();
		variables.put(Constant.Workflow.LIGHTOUT_RECOVER_PERSON, "");
		variables.put(Constant.Workflow.LIGHTOUT_RECOVER_PERSONS, "");
		variables.put(Constant.Workflow.LIGHTOUT_RECOVER_ROLES, "按灯恢复人");
		TaskService taskService = processEngine.getTaskService();
		Task task  = taskService.createTaskQuery().processInstanceBusinessKey(pressLightRecord.getId()+"").singleResult();
		taskService.setVariableLocal(task.getId(), "suggestion", args.get("suggestion"));
		taskService.complete(task.getId(), variables);
	}

	@Override
	public void confirmPressLightRecord(PressLightRecord pressLightRecord, User user, Map<String, Object> args) {
		pressLightRecordService.confirmPressLightRecord(pressLightRecord, user, args);
		TaskService taskService = processEngine.getTaskService();
		Task task  = taskService.createTaskQuery().processInstanceBusinessKey(pressLightRecord.getId()+"").singleResult();
		taskService.setVariableLocal(task.getId(), "suggestion", args.get("suggestion"));
		taskService.complete(task.getId());
	}

	@Override
	public void deletePressLightRecord(PressLightRecord pressLightRecord) {
		RuntimeService runtimeService = processEngine.getRuntimeService();
		List<Execution> executions = runtimeService.createExecutionQuery().processInstanceBusinessKey(pressLightRecord.getId()+"").list();
		if(executions!=null && executions.size()>0) {
			throw new RuntimeException("该记录上流程还没结束，无法删除！");
		}
		
		pressLightRecordService.deletePressLightRecord(pressLightRecord);
	}
}
