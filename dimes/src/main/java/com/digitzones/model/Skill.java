package com.digitzones.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 人员技能
 * @author zdq
 * 2018年6月2日
 */
@Entity
@Table(name="SKILL")
public class Skill extends CommonModel{
	private SkillType skillType;
	/**该技能所属的工序*/
	private Processes process;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PROCESS_ID")
	public Processes getProcess() {
		return process;
	}
	public void setProcess(Processes process) {
		this.process = process;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SKILLTYPE_ID")
	public SkillType getSkillType() {
		return skillType;
	}
	public void setSkillType(SkillType skillType) {
		this.skillType = skillType;
	}
	private static final long serialVersionUID = 1L;
}
