package com.digitzones.model;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 角色
 * @author zdq
 * 2018年6月4日
 */
@Entity
@Table(name="ROLE")
public class Role {
	/**对象标识*/
	private Long id;
	/**角色名称*/
	private String roleName;
	/**创建日期*/
	private Date createDate;
	/**备注*/
	private String note;
	/**是否停用*/
	private Boolean disable;
	/**创建用户id*/
	private Long createUserId;
	/**创建用户名称*/
	private String createUsername;
	/**修改用户ID*/
	private Long modifyUserId;
	/**修改用户名称*/
	private String modifyUsername;
	/**修改日期*/
	private Date modifyDate;
	/**对应权限*/
	private Set<Power> powers;
	/**该角色可操作的功能模块*/
	private Set<Module> modules;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Boolean getDisable() {
		return disable;
	}
	public void setDisable(Boolean disable) {
		this.disable = disable;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUsername() {
		return createUsername;
	}
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}
	public Long getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getModifyUsername() {
		return modifyUsername;
	}
	public void setModifyUsername(String modifyUsername) {
		this.modifyUsername = modifyUsername;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@ManyToMany(targetEntity=Power.class)
	@JoinTable(name="ROLE_POWER",
	joinColumns= {@JoinColumn(name="ROLE_ID")},
	inverseJoinColumns= {@JoinColumn(name="POWER_ID")})
	public Set<Power> getPowers() {
		return powers;
	}
	public void setPowers(Set<Power> powers) {
		this.powers = powers;
	}
	@ManyToMany(targetEntity=Module.class)
	@JoinTable(name="ROLE_Module",
	joinColumns= {@JoinColumn(name="ROLE_ID")},
	inverseJoinColumns= {@JoinColumn(name="Module_ID")})
	public Set<Module> getModules() {
		return modules;
	}
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
}
