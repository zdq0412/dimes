package com.digitzones.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 质量日历类别
 * @author zdq
 * 2018年6月5日
 */
@Entity
@Table(name="QUALITYCALENDARTYPE")
public class QualityCalendarType {
	private Long id;
	/**类别名称*/
	private String name;
	/**图标*/
	private String icon;
	/**父类别*/
	private QualityCalendarType parent;
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
