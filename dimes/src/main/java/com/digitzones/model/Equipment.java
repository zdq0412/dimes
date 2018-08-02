package com.digitzones.model;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date outFactoryDate;
	/**计量目标*/
	private Float measurementObjective;
	/**计量类型*/
	private String measurementType;
	/**计量累积*/
	private Float cumulation;
	/**计量差异*/
	private Float measurementDifference;
	/**质保期*/
	private Float warrantyPeriod;
	/**装备类型*/
	private EquipmentType equipmentType;
	/**装备图片*/
	private Blob pic;
	/**图片名称*/
	private String picName;
	/**记录是量具还是装备*/
	private String baseCode;
	
	public Float getWarrantyPeriod() {
		return warrantyPeriod;
	}
	public void setWarrantyPeriod(Float warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}
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
	public String getBaseCode() {
		return baseCode;
	}
	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
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
	public String getMeasurementType() {
		return measurementType;
	}
	public void setMeasurementType(String measurementType) {
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
	@JsonIgnore
	public Blob getPic() {
		return pic;
	}
	public void setPic(Blob pic) {
		this.pic = pic;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	
}
