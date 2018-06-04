package com.digitzones.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 装备
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="EQUIPMENT")
public class Equipment extends CommonModel {
	private static final long serialVersionUID = 1L;
	/**规格型号*/
	private String unitType;
	/**状态*/
	private String status;
	/**制造商*/
	private String manufacturer;
	/**经销商*/
	private String trader;
	/**出厂日期*/
	private Date outFactoryDate;
	/**计量目标*/
	private Float measurementObjective;
	/**计量类型*/
	private Float measurementType;
	/**计量累积*/
	private Float cumulation;
	/**计量差异*/
	private Float measurementDifference;
	/**装备类型*/
	private EquipmentType equipmentType;
	/**装备图片*/
	private String pic;
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getTrader() {
		return trader;
	}
	public void setTrader(String trader) {
		this.trader = trader;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOutFactoryDate() {
		return outFactoryDate;
	}
	public void setOutFactoryDate(Date outFactoryDate) {
		this.outFactoryDate = outFactoryDate;
	}
	public Float getMeasurementObjective() {
		return measurementObjective;
	}
	public void setMeasurementObjective(Float measurementObjective) {
		this.measurementObjective = measurementObjective;
	}
	public Float getMeasurementType() {
		return measurementType;
	}
	public void setMeasurementType(Float measurementType) {
		this.measurementType = measurementType;
	}
	@ManyToOne
	@JoinColumn(name="EQUIPMENTTYPE_ID")
	public EquipmentType getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(EquipmentType equipmentType) {
		this.equipmentType = equipmentType;
	}
	public Float getCumulation() {
		return cumulation;
	}
	public void setCumulation(Float cumulation) {
		this.cumulation = cumulation;
	}
	public Float getMeasurementDifference() {
		return measurementDifference;
	}
	public void setMeasurementDifference(Float measurementDifference) {
		this.measurementDifference = measurementDifference;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
}
