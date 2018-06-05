package com.digitzones.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 工件工序实体和参数的关联实体
 * @author zdq
 * 2018年6月5日
 */
@Entity
@Table(name="WORKPIECEPROCESS_PARAMETER")
public class WorkpieceProcessParameterMapping {
	private Long id;
	/**单位*/
	private String unit;
	/**备注*/
	private String note;
	/**上线*/
	private Float upLine;
	/**下线*/
	private Float lowLine;
	/**工件工序关联实体*/
	private WorkpieceProcessMapping workpieceProcess;
	/**参数*/
	private Parameters parameter;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Float getUpLine() {
		return upLine;
	}
	public void setUpLine(Float upLine) {
		this.upLine = upLine;
	}
	public Float getLowLine() {
		return lowLine;
	}
	public void setLowLine(Float lowLine) {
		this.lowLine = lowLine;
	}
	@ManyToOne
	@JoinColumn(name="WORKPIECEPROCESS_ID")
	public WorkpieceProcessMapping getWorkpieceProcess() {
		return workpieceProcess;
	}
	public void setWorkpieceProcess(WorkpieceProcessMapping workpieceProcess) {
		this.workpieceProcess = workpieceProcess;
	}
	@ManyToOne
	@JoinColumn(name="PARAMETER_ID")
	public Parameters getParameter() {
		return parameter;
	}
	public void setParameter(Parameters parameter) {
		this.parameter = parameter;
	}
}
