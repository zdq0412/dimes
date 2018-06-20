package com.digitzones.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 生产单元实体
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="PRODUCTIONUNIT")
public class ProductionUnit extends CommonModel {
	private static final long serialVersionUID = 1L;
	/**父单元*/
	private ProductionUnit parent;
	/**子生产单元*/
	private Set<ProductionUnit> children;
	/**设备*/
	private List<Device> devices;
	@OneToMany(mappedBy="productionUnit",fetch=FetchType.EAGER)
	@OrderBy("ID DESC")
	@JsonIgnore
	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent")
	@OrderBy("ID DESC")
	public Set<ProductionUnit> getChildren() {
		return children;
	}

	public void setChildren(Set<ProductionUnit> children) {
		this.children = children;
	}

	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	@JsonIgnore
	public ProductionUnit getParent() {
		return parent;
	}

	public void setParent(ProductionUnit parent) {
		this.parent = parent;
	}
}
