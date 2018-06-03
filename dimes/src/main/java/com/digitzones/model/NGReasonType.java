package com.digitzones.model;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	public NGReasonType getParent() {
		return parent;
	}
	public void setParent(NGReasonType parent) {
		this.parent = parent;
	}
}
