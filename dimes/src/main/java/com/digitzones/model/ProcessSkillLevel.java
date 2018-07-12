package com.digitzones.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 每个工序下的技能等级
 * @author zdq
 * 2018年6月2日
 */
@Entity
@Table(name="PROCESSSKILLLEVEL")
public class ProcessSkillLevel extends CommonModel {
	private static final long serialVersionUID = 1L;
	/**技能*/
	private Skill skill;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SKILL_ID")
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
}
