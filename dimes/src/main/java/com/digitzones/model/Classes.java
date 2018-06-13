package com.digitzones.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 班次
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="CLASSES")
public class Classes extends CommonModel {
	private static final long serialVersionUID = 1L;
	/**开始时间*/
	@DateTimeFormat(pattern="HH:mm")
	private Date startTime;
	/**结束时间*/
	@DateTimeFormat(pattern="HH:mm")
	private Date endTime;
	/**是否跨天*/
	private Boolean crossDay;
	/**班次类别代码*/
	private String classTypeCode;
	/**班次类别名称*/
	private String classTypeName;
	/**班次类别*/
	private ClassType classType;
	
	public Classes() {
		startTime = new Date();
		endTime = new Date();
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CLASSTYPE_ID")
	public ClassType getClassType() {
		return classType;
	}
	public void setClassType(ClassType classType) {
		this.classType = classType;
	}
	
	public String getClassTypeCode() {
		return classTypeCode;
	}
	public String getClassTypeName() {
		return classTypeName;
	}
	public void setClassTypeCode(String classTypeCode) {
		this.classTypeCode = classTypeCode;
	}
	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="beginTime")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Boolean getCrossDay() {
		return crossDay;
	}
	public void setCrossDay(Boolean crossDay) {
		this.crossDay = crossDay;
	}
}
