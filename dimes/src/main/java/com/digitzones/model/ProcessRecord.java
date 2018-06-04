package com.digitzones.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 加工记录
 * @author zdq
 * 2018年6月4日
 */
@Entity
@Table(name="PROCESSRECORD")
public class ProcessRecord {
	private Long id;
	/**设备站点代码*/
	private String deviceSiteCode;
	/**设备站点名称*/
	private String deviceSiteName;
	/**采集时间*/
	private Date collectionDate;
	/**流水号*/
	private String serialNo;
	/**生产人员ID*/
	private Long productUserId;
	/**生产人员姓名*/
	private String productUserName;
	/**所属工单*/
	private WorkSheet workSheet;
	/**班次代码*/
	private String classesCode;
	/**班次名称*/
	private  String classesName;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeviceSiteCode() {
		return deviceSiteCode;
	}
	public void setDeviceSiteCode(String deviceSiteCode) {
		this.deviceSiteCode = deviceSiteCode;
	}
	public String getDeviceSiteName() {
		return deviceSiteName;
	}
	public void setDeviceSiteName(String deviceSiteName) {
		this.deviceSiteName = deviceSiteName;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCollectionDate() {
		return collectionDate;
	}
	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public Long getProductUserId() {
		return productUserId;
	}
	public void setProductUserId(Long productUserId) {
		this.productUserId = productUserId;
	}
	public String getProductUserName() {
		return productUserName;
	}
	public void setProductUserName(String productUserName) {
		this.productUserName = productUserName;
	}
	@ManyToOne
	@JoinColumn(name="WORKSHEET_ID")
	public WorkSheet getWorkSheet() {
		return workSheet;
	}
	public void setWorkSheet(WorkSheet workSheet) {
		this.workSheet = workSheet;
	}
	public String getClassesCode() {
		return classesCode;
	}
	public void setClassesCode(String classesCode) {
		this.classesCode = classesCode;
	}
	public String getClassesName() {
		return classesName;
	}
	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}
}
