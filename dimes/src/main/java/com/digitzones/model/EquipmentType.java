package com.digitzones.model;

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
 * 装备类别
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="EQUIPMENTTYPE")
public class EquipmentType extends CommonModel {
	private static final long serialVersionUID = 1L;
	/**父类别*/
	private EquipmentType parent;
	/**记录是量具还是装备*/
	private String baseCode;
	/**子类别*/
	private Set<EquipmentType> children;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent")
	@OrderBy("ID DESC")
	public Set<EquipmentType> getChildren() {
		return children;
	}

	public void setChildren(Set<EquipmentType> children) {
		this.children = children;
	}
	
	public String getBaseCode() {
		return baseCode;
	}

	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
	}

	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	@JsonIgnore
	public EquipmentType getParent() {
		return parent;
	}
	public void setParent(EquipmentType parent) {
		this.parent = parent;
	}
}
