package com.digitzones.model;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 技能类别
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="SKILLTYPE")
public class SkillType extends CommonModel {
	private static final long serialVersionUID = 1L;
	private SkillType parent;
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	public SkillType getParent() {
		return parent;
	}
	public void setParent(SkillType parent) {
		this.parent = parent;
	}
}
