package com.digitzones.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	/**装备类型*/
	private EquipmentType equipmentType;
	/**设备*/
	private Set<Device> devices;
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
	@ManyToMany(targetEntity=Device.class)
	@JoinTable(name="EQUIPMENT_DEVICE",
	joinColumns= {@JoinColumn(name="EQUIPMENT_ID")},
	inverseJoinColumns= {@JoinColumn(name="DEVICE_ID")})
	public Set<Device> getDevices() {
		return devices;
	}
	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}
}
