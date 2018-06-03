package com.digitzones.model;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	public ParameterType getParent() {
		return parent;
	}
	public void setParent(ParameterType parent) {
		this.parent = parent;
	}
}
