package com.digitzones.vo;

import java.util.Set;

import com.digitzones.model.ProductionUnit;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 生产单元值对象实体
 * @author zdq
 * 2018年6月7日
 */
@SuppressWarnings("rawtypes")
public class ProductionUnitVO {
	private Long id;
	private String name;
	private Long pid;
	private Integer status;
	private Set children;
	private ProductionUnit parent;
	private String code;
	private Boolean disabled;
	private String note;
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@JsonIgnore
	public Set getChildren() {
		return children;
	}
	public void setChildren(Set children) {
		this.children = children;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProductionUnit getParent() {
		return parent;
	}
	public void setParent(ProductionUnit parent) {
		this.parent = parent;
	}
	
}
