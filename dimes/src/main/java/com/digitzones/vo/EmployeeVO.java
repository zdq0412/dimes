package com.digitzones.vo;

/**
 * 人员VO实体
 * @author zdq
 * 2018年6月10日
 */
public class EmployeeVO  {
	private Long id;
	private String name;
	private String photo;
	private String code;
	private Boolean disabled;
	private String note;
	private Long departmentId;
	private String departmentCode;
	private String departmentName;
	private Long positionId;
	private String positionCode;
	private String positionName;
	private Long productionUnitId;
	private String productionUnitName;
	public Long getProductionUnitId() {
		return productionUnitId;
	}
	public void setProductionUnitId(Long productionUnitId) {
		this.productionUnitId = productionUnitId;
	}
	public String getProductionUnitName() {
		return productionUnitName;
	}
	public void setProductionUnitName(String productionUnitName) {
		this.productionUnitName = productionUnitName;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
