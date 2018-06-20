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
 * 工件类别
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="WORKPIECETYPE")
public class WorkpieceType extends CommonModel {
	private static final long serialVersionUID = 1L;
	private WorkpieceType parent;
	/**
	 * 子类别
	 */
	private Set<WorkpieceType> children;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent")
	@OrderBy(" ID DESC")
	public Set<WorkpieceType> getChildren() {
		return children;
	}
	public void setChildren(Set<WorkpieceType> children) {
		this.children = children;
	}
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	@JsonIgnore
	public WorkpieceType getParent() {
		return parent;
	}
	public void setParent(WorkpieceType parent) {
		this.parent = parent;
	}
}
