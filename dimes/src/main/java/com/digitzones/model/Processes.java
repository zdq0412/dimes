package com.digitzones.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 工序实体
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="PROCESSES")
public class Processes extends CommonModel{
	private static final long serialVersionUID = 1L;
	private ProcessType processType;
	@ManyToOne
	@JoinColumn(name="PROCESSTYPE_ID")
	public ProcessType getProcessType() {
		return processType;
	}
	public void setProcessType(ProcessType processType) {
		this.processType = processType;
	}
}
