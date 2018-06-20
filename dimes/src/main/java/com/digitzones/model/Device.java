package com.digitzones.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 设备
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="DEVICE")
public class Device extends CommonModel {
	private static final long serialVersionUID = 1L;
	/**规格型号*/
	private String unitType;
	/**设备状态*/
	private String status;
	/**制造商*/
	private String manufacturer;
	/**经销商*/
	private String trader;
	/**安装日期*/
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date installDate;
	/**出厂日期*/
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date outFactoryDate;
	/**出厂编号*/
	private String outFactoryCode;
	/**安装位置*/
	private String installPosition;
	/**是否为瓶颈设备*/
	private Boolean bottleneck;
	/**目标OEE*/
	private Float goalOee;
	/**参数取值
	 * 固定值
	 * 变动值
	 * */
	private String parameterValueType;
	/**设备类别*/
	private DeviceType deviceType;
	/**设备站点*/
	private Set<DeviceSite> deviceSites;
	/**生产单元*/
	private ProductionUnit productionUnit;
	/**设备图片*/
	private String photo;
	/**
	 * 设备和班次关联类
	 */
	private Set<ClassesDeviceMapping> classesDevice;
	@OneToMany(mappedBy="device",targetEntity=ClassesDeviceMapping.class)
	@JsonIgnore
	public Set<ClassesDeviceMapping> getClassesDevice() {
		return classesDevice;
	}
	public void setClassesDevice(Set<ClassesDeviceMapping> classesDevice) {
		this.classesDevice = classesDevice;
	}
	@OneToMany(mappedBy="device",fetch=FetchType.EAGER)
	@OrderBy("ID DESC")
	@JsonIgnore
	public Set<DeviceSite> getDeviceSites() {
		return deviceSites;
	}
	public void setDeviceSites(Set<DeviceSite> deviceSites) {
		this.deviceSites = deviceSites;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
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
	public Date getInstallDate() {
		return installDate;
	}
	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOutFactoryDate() {
		return outFactoryDate;
	}
	public void setOutFactoryDate(Date outFactoryDate) {
		this.outFactoryDate = outFactoryDate;
	}
	public String getOutFactoryCode() {
		return outFactoryCode;
	}
	public void setOutFactoryCode(String outFactoryCode) {
		this.outFactoryCode = outFactoryCode;
	}
	public String getInstallPosition() {
		return installPosition;
	}
	public void setInstallPosition(String installPosition) {
		this.installPosition = installPosition;
	}
	public Boolean getBottleneck() {
		return bottleneck;
	}
	public void setBottleneck(Boolean bottleneck) {
		this.bottleneck = bottleneck;
	}
	public Float getGoalOee() {
		return goalOee;
	}
	public void setGoalOee(Float goalOee) {
		this.goalOee = goalOee;
	}
	public String getParameterValueType() {
		return parameterValueType;
	}
	public void setParameterValueType(String parameterValueType) {
		this.parameterValueType = parameterValueType;
	}
	@ManyToOne
	@JoinColumn(name="DEVICETYPE_ID")
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	@ManyToOne
	@JoinColumn(name="PRODUCTIONUNIT_ID")
	public ProductionUnit getProductionUnit() {
		return productionUnit;
	}
	public void setProductionUnit(ProductionUnit productionUnit) {
		this.productionUnit = productionUnit;
	}
}
