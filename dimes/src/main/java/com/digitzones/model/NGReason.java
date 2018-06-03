package com.digitzones.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * NG原因
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="NGREASON")
public class NGReason {
	/**对象标识*/
	private Long id;
	/**NG代码*/
	private String ngCode;
	/**NG原因*/
	private String ngReason;
	/**备注*/
	private String note;
	/**处理方法*/
	private String processingMethod;
	/**类型*/
	private NGReasonType ngReasonType;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNgCode() {
		return ngCode;
	}
	public void setNgCode(String ngCode) {
		this.ngCode = ngCode;
	}
	public String getNgReason() {
		return ngReason;
	}
	public void setNgReason(String ngReason) {
		this.ngReason = ngReason;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getProcessingMethod() {
		return processingMethod;
	}
	public void setProcessingMethod(String processingMethod) {
		this.processingMethod = processingMethod;
	}
	@ManyToOne
	@JoinColumn(name="NGREASONTYPE_ID")
	public NGReasonType getNgReasonType() {
		return ngReasonType;
	}
	public void setNgReasonType(NGReasonType ngReasonType) {
		this.ngReasonType = ngReasonType;
	}
}
