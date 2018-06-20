package com.digitzones.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 工件工序关联实体
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="WORKPIECE_PROCESS")
public class WorkpieceProcessMapping {
	/**对象标识*/
	private Long id;
	/**工件*/
	private Workpiece workpiece;
	/**工序*/
	private Processes process;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="WORKPIECE_ID")
	public Workpiece getWorkpiece() {
		return workpiece;
	}
	public void setWorkpiece(Workpiece workpiece) {
		this.workpiece = workpiece;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PROCESS_ID")
	public Processes getProcess() {
		return process;
	}
	public void setProcess(Processes process) {
		this.process = process;
	}
}
