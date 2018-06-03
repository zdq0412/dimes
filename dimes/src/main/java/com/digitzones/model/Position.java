package com.digitzones.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 岗位实体
 * @author zdq
 * 2018年6月2日
 */
@Entity
@Table(name="POSITION")
public class Position extends CommonModel {
	private static final long serialVersionUID = 1L;
	/**所在部门*/
	private Department department;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="DEPARTMENT_ID")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
}
