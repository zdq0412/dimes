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
 * 工序类别
 * @author zdq
 * 2018年6月2日
 */
@Entity
@Table(name="PROCESSTYPE")
public class ProcessType extends CommonModel {
	private static final long serialVersionUID = 1L;
	
	private ProcessType parent;
	/**
	 * 子类别
	 */
	private Set<ProcessType> children;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent")
	@OrderBy(" ID DESC")
	public Set<ProcessType> getChildren() {
		return children;
	}
	public void setChildren(Set<ProcessType> children) {
		this.children = children;
	}
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	@JsonIgnore
	public ProcessType getParent() {
		return parent;
	}
	public void setParent(ProcessType parent) {
		this.parent = parent;
	}
}
