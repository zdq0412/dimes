package com.digitzones.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 设备站点
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="DEVICESITE")
public class DeviceSite extends CommonModel {
	private static final long serialVersionUID = 1L;
	/**条码读头地址*/
	private String barCodeAddress;
	/**站点所在设备*/
	private Device device;
	/**设备站点状态
	 * 状态取值:0:正常运行
	 * 1：停机
	 * 2：待机
	 * */
	private String status;
	/**是否在参数状态中显示*/
	private Boolean show = false;
	
	public Boolean getShow() {
		return show;
	}
	public void setShow(Boolean show) {
		this.show = show;
	}
	public String getBarCodeAddress() {
		return barCodeAddress;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="DEVICE_ID")
	public Device getDevice() {
		return device;
	}
	public String getStatus() {
		return status;
	}
	public void setBarCodeAddress(String barCodeAddress) {
		this.barCodeAddress = barCodeAddress;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
