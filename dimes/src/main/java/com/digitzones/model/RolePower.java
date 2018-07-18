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
 * 角色和权限关联实体
 * @author zdq
 * 2018年7月18日
 */
@Entity
@Table(name="ROLE_POWER")
public class RolePower {
	private Long id;
	private Power power;
	private Role role;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="POWER_ID")
	public Power getPower() {
		return power;
	}
	public void setPower(Power power) {
		this.power = power;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ROLE_ID")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
