package com.digitzones.model;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 班次类别
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="CLASSTYPE")
public class ClassType extends CommonModel {
	private static final long serialVersionUID = 1L;
	private ClassType parent;
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	public ClassType getParent() {
		return parent;
	}
	public void setParent(ClassType parent) {
		this.parent = parent;
	}
}
