package com.digitzones.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private Date beginTime;
	/**结束时间*/
	private Date endTime;
	/**是否跨天*/
	private Boolean crossDay;
	@Temporal(TemporalType.TIME)
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	@Temporal(TemporalType.TIME)
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
