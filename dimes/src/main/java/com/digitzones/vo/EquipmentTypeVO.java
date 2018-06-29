package com.digitzones.vo;
import com.digitzones.model.EquipmentType;
/**
 * 装备类型值对象实体
 * @author zdq
 * 2018年6月7日
 */
public class EquipmentTypeVO {
	private Long id;
	private String name;
	private EquipmentType parent;
	private String code;
	private Boolean disabled;
	private String note;
	private String baseCode;
	public String getBaseCode() {
		return baseCode;
	}
	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
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
	public EquipmentType getParent() {
		return parent;
	}
	public void setParent(EquipmentType parent) {
		this.parent = parent;
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
}
