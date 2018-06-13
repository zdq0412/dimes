package com.digitzones.model;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * NG原因类别
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="NGREASONTYPE")
public class NGReasonType extends CommonModel {
	private static final long serialVersionUID = 1L;
	private NGReasonType parent;
	/**
	 * 子部门
	 */
	private Set<NGReasonType> children;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent")
	public Set<NGReasonType> getChildren() {
		return children;
	}
	public void setChildren(Set<NGReasonType> children) {
		this.children = children;
	}
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	@JsonIgnore
	public NGReasonType getParent() {
		return parent;
	}
	public void setParent(NGReasonType parent) {
		this.parent = parent;
	}
}
