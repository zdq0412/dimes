package com.digitzones.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 流程名称配置
 * @author zdq
 * 2018年8月3日
 */
@Component
public class WorkFlowKeyConfig {
	@Value("${lostTimeWorkflowKey}")
	private String lostTimeWorkflowKey;
	@Value("${pressLightWorkflowKey}")
	private String pressLightWorkflowKey;
	@Value("${ngWorkflowKey}")
	private String ngWorkflowKey;
	public String getLostTimeWorkflowKey() {
		return lostTimeWorkflowKey;
	}
	public void setLostTimeWorkflowKey(String lostTimeWorkflowKey) {
		this.lostTimeWorkflowKey = lostTimeWorkflowKey;
	}
	public String getPressLightWorkflowKey() {
		return pressLightWorkflowKey;
	}
	public void setPressLightWorkflowKey(String pressLightWorkflowKey) {
		this.pressLightWorkflowKey = pressLightWorkflowKey;
	}
	public String getNgWorkflowKey() {
		return ngWorkflowKey;
	}
	public void setNgWorkflowKey(String ngWorkflowKey) {
		this.ngWorkflowKey = ngWorkflowKey;
	}
	
}
