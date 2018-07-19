package com.digitzones.model;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 质量日历类别
 * @author zdq
 * 2018年6月5日
 */
@Entity
@Table(name="QUALITYCALENDARTYPE")
public class QualityCalendarType extends CommonModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**图标*/
	private String icon;
	/**父类别*/
	private QualityCalendarType parent;
	/**
	 * 子类别
	 */
	private Set<QualityCalendarType> children;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent")
	@OrderBy(" ID DESC")
	@JsonIgnore
	public Set<QualityCalendarType> getChildren() {
		return children;
	}
	public void setChildren(Set<QualityCalendarType> children) {
		this.children = children;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	public QualityCalendarType getParent() {
		return parent;
	}
	public void setParent(QualityCalendarType parent) {
		this.parent = parent;
	}
}
