package com.digitzones.model;

import java.util.List;
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
 * 生产单元实体
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="PRODUCTIONUNIT")
public class ProductionUnit extends CommonModel {
	private static final long serialVersionUID = 1L;
	/**父单元*/
	private ProductionUnit parent;
	/**子生产单元*/
	private Set<ProductionUnit> children;
	/**设备*/
	private List<Device> devices;
	/**目标产量*/
	private Integer goalOutput = 0;
	/**产线目标oee*/
	private Float goalOee=0f;
	/**产线损时目标 */
	private Float goalLostTime=0f;
	/**产线不合格目标 */
	private Float goalNg=0f;
	@OneToMany(mappedBy="productionUnit",fetch=FetchType.EAGER)
	@OrderBy("ID DESC")
	@JsonIgnore
	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent")
	@OrderBy("ID DESC")
	public Set<ProductionUnit> getChildren() {
		return children;
	}

	public void setChildren(Set<ProductionUnit> children) {
		this.children = children;
	}

	public Integer getGoalOutput() {
		return goalOutput;
	}

	public void setGoalOutput(Integer goalOutput) {
		this.goalOutput = goalOutput;
	}

	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	@JsonIgnore
	public ProductionUnit getParent() {
		return parent;
	}

	public void setParent(ProductionUnit parent) {
		this.parent = parent;
	}

	public Float getGoalOee() {
		return goalOee;
	}

	public void setGoalOee(Float goalOee) {
		this.goalOee = goalOee;
	}

	public Float getGoalLostTime() {
		return goalLostTime;
	}

	public void setGoalLostTime(Float goalLostTime) {
		this.goalLostTime = goalLostTime;
	}

	public Float getGoalNg() {
		return goalNg;
	}

	public void setGoalNg(Float goalNg) {
		this.goalNg = goalNg;
	}
	
}
