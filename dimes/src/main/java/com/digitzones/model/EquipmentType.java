package com.digitzones.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	public EquipmentType getParent() {
		return parent;
	}
	public void setParent(EquipmentType parent) {
		this.parent = parent;
	}
}
