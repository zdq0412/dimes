package com.digitzones.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 状态故障代码
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="STATUSTROUBLECODE")
public class StatusTroubleCode extends CommonModel {
	private static final long serialVersionUID = 1L;
	/**参数代码*/
	private String parameterCode;
	/**参数名称*/
	private String parameterName;
	/**条件*/
	private String conditions;
	public String getParameterCode() {
		return parameterCode;
	}
	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
}