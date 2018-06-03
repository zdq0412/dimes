package com.digitzones.model;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	public WorkpieceType getParent() {
		return parent;
	}
	public void setParent(WorkpieceType parent) {
		this.parent = parent;
	}
}
