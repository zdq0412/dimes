package com.digitzones.vo;

public class NGReasonVO {
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
	private String qrPath;
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
	public String getQrPath() {
		return qrPath;
	}
	public void setQrPath(String qrPath) {
		this.qrPath = qrPath;
	}
	
}