package com.digitzones.model;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 按灯类别
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="PRESSLIGHTTYPE")
public class PressLightType extends CommonModel {
	private static final long serialVersionUID = 1L;
	private PressLightType parent;
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	public PressLightType getParent() {
		return parent;
	}
	public void setParent(PressLightType parent) {
		this.parent = parent;
	}
}
