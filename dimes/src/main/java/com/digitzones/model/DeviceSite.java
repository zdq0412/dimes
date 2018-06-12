package com.digitzones.model;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	/**站点参数*/
	private Set<Parameters> parameters;
	/**工序*/
	private Processes process;
	public String getBarCodeAddress() {
		return barCodeAddress;
	}
	public void setBarCodeAddress(String barCodeAddress) {
		this.barCodeAddress = barCodeAddress;
	}
	@ManyToOne
	@JoinColumn(name="DEVICE_ID")
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	@ManyToMany(targetEntity=Parameters.class)
	@JoinTable(name="DEVICESITE_PARAMETERS",joinColumns= {
			@JoinColumn(name="DEVICESITE_ID")
	},inverseJoinColumns= {@JoinColumn(name="PARAMETER_ID")})
	@JsonIgnore
	public Set<Parameters> getParameters() {
		return parameters;
	}
	public void setParameters(Set<Parameters> parameters) {
		this.parameters = parameters;
	}
	@ManyToOne
	@JoinColumn(name="PROCESS_ID")
	public Processes getProcess() {
		return process;
	}
	public void setProcess(Processes process) {
		this.process = process;
	}
}
