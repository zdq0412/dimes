package com.digitzones.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.digitzones.model.ClassType;
import com.digitzones.model.CommonModel;

/**
 * 班次VO
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="CLASSES")
public class ClassesVO extends CommonModel {
	private static final long serialVersionUID = 1L;
	/**开始时间*/
	private String startTime;
	/**结束时间*/
	private String endTime;
	/**是否跨天*/
	private Integer crossDay = 0;
	/**班次类别代码*/
	private String classTypeCode;
	/**班次类别名称*/
	private String classTypeName;
	/**班次类别*/
	private ClassType classType;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getCrossDay() {
		return crossDay;
	}
	public void setCrossDay(Integer crossDay) {
		this.crossDay = crossDay;
	}
	public String getClassTypeCode() {
		return classTypeCode;
	}
	public void setClassTypeCode(String classTypeCode) {
		this.classTypeCode = classTypeCode;
	}
	public String getClassTypeName() {
		return classTypeName;
	}
	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}
	public ClassType getClassType() {
		return classType;
	}
	public void setClassType(ClassType classType) {
		this.classType = classType;
	}
}
