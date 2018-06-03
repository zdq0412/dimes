package com.digitzones.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 工件实体
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="WORKPIECE")
public class Workpiece extends CommonModel {
	private static final long serialVersionUID = 1L;
	/**规格型号*/
	private String unitType;
	/**版本号*/
	private String version;
	/**设计号*/
	private String designNo;
	/**客户图号*/
	private String customerGraphNumber;
	/**计量单位*/
	private String measurementUnit;
	/**工件类别*/
	private WorkpieceType workpieceType;
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDesignNo() {
		return designNo;
	}
	public void setDesignNo(String designNo) {
		this.designNo = designNo;
	}
	public String getCustomerGraphNumber() {
		return customerGraphNumber;
	}
	public void setCustomerGraphNumber(String customerGraphNumber) {
		this.customerGraphNumber = customerGraphNumber;
	}
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	@ManyToOne
	@JoinColumn(name="WORKPIECETYPE_ID")
	public WorkpieceType getWorkpieceType() {
		return workpieceType;
	}
	public void setWorkpieceType(WorkpieceType workpieceType) {
		this.workpieceType = workpieceType;
	}
	
}
