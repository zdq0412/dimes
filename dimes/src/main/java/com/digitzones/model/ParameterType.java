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
 * 参数类别
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="PARAMETERTYPE")
public class ParameterType extends CommonModel {
	private static final long serialVersionUID = 1L;
	private ParameterType parent;
	/**记录是工艺参数还是设备参数*/
	private String baseCode;
	/**
	 * 子类别
	 */
	private Set<ParameterType> children;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent")
	@OrderBy(" ID DESC")
	public Set<ParameterType> getChildren() {
		return children;
	}
	public void setChildren(Set<ParameterType> children) {
		this.children = children;
	}
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	@JsonIgnore
	public ParameterType getParent() {
		return parent;
	}
	public void setParent(ParameterType parent) {
		this.parent = parent;
	}
	public String getBaseCode() {
		return baseCode;
	}
	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
	}
}
