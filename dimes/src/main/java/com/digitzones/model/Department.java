package com.digitzones.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	/**
	 * 子部门
	 */
	private List<Department> children;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent")
	@Fetch(FetchMode.SUBSELECT)
	public List<Department> getChildren() {
		return children;
	}
	public void setChildren(List<Department> children) {
		this.children = children;
	}
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	@JsonIgnore
	public Department getParent() {
		return parent;
	}
	public void setParent(Department parent) {
		this.parent = parent;
	}
	
}
