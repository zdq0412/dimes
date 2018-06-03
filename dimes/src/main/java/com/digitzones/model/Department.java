package com.digitzones.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 部门实体
 * @author zdq
 * 2018年6月2日
 */
@Entity
@Table(name="DEPARTMENT")
public class Department extends CommonModel {
	private static final long serialVersionUID = 1L;
	/**父部门*/
	private Department parent;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PARENT_ID")
	public Department getParent() {
		return parent;
	}
	public void setParent(Department parent) {
		this.parent = parent;
	}
	
}
