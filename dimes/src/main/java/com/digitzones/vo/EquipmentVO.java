package com.digitzones.vo;
import com.digitzones.model.EquipmentType;
/**
 * 装备值对象
 * @author zdq
 * 2018年7月17日
 */
public class EquipmentVO {
	private Long id;
	private String code;
	private String name;
	/**规格型号*/
	private String unitType;
	/**状态*/
	private String status;
	/**制造商*/
	private String manufacturer;
	/**经销商*/
	private String trader;
	/**出厂日期*/
	private String outFactoryDate;
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
	private String pic;
	/**记录是量具还是装备*/
	private String baseCode;
	/**二维码图片路径*/
	private String qrPath;
	public String getQrPath() {
		return qrPath;
	}
	public void setQrPath(String qrPath) {
		this.qrPath = qrPath;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getOutFactoryDate() {
		return outFactoryDate;
	}
	public void setOutFactoryDate(String outFactoryDate) {
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
	public Float getWarrantyPeriod() {
		return warrantyPeriod;
	}
	public void setWarrantyPeriod(Float warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}
	public EquipmentType getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(EquipmentType equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getBaseCode() {
		return baseCode;
	}
	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
	}
}
