package com.digitzones.model;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 设备类别
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="DEVICETYPE")
public class DeviceType extends CommonModel {
	private static final long serialVersionUID = 1L;
	private DeviceType parent;
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	public DeviceType getParent() {
		return parent;
	}
	public void setParent(DeviceType parent) {
		this.parent = parent;
	}
}
