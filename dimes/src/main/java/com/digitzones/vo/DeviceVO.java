package com.digitzones.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.digitzones.model.CommonModel;
import com.digitzones.model.DeviceType;
import com.digitzones.model.ProductionUnit;

/**
 * 设备VO
 * @author zdq
 * 2018年6月3日
 */
public class DeviceVO extends CommonModel {
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
	private String installDate;
	/**出厂日期*/
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date outFactoryDate;
	/**出厂编号*/
	private String outFactoryCode;
	/**安装位置*/
	private String installPosition;
	/**是否为瓶颈设备*/
	private String bottleneck;
	/**目标OEE*/
	private Float goalOee;
	/**参数取值
	 * 固定值
	 * 变动值
	 * */
	private String parameterValueType;
	/**设备类别*/
	private DeviceType deviceType;
	/**生产单元*/
	private ProductionUnit productionUnit;
	/**设备图片*/
	private String photo;
	/**二维码图片路径*/
	private String qrPath;
	public String getQrPath() {
		return qrPath;
	}
	public void setQrPath(String qrPath) {
		this.qrPath = qrPath;
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
	public String getInstallDate() {
		return installDate;
	}
	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}
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
	public String getBottleneck() {
		return bottleneck;
	}
	public void setBottleneck(String bottleneck) {
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
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	public ProductionUnit getProductionUnit() {
		return productionUnit;
	}
	public void setProductionUnit(ProductionUnit productionUnit) {
		this.productionUnit = productionUnit;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
