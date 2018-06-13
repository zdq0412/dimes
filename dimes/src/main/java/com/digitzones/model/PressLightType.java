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
 * 按灯类别
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="PRESSLIGHTTYPE")
public class PressLightType extends CommonModel {
	private static final long serialVersionUID = 1L;
	private PressLightType parent;
	/**
	 * 子类别
	 */
	private Set<PressLightType> children;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent")
	@OrderBy(" ID DESC")
	public Set<PressLightType> getChildren() {
		return children;
	}
	public void setChildren(Set<PressLightType> children) {
		this.children = children;
	}
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	@JsonIgnore
	public PressLightType getParent() {
		return parent;
	}
	public void setParent(PressLightType parent) {
		this.parent = parent;
	}
}
