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
 * 工单 详情
 * @author zdq
 * 2018年6月4日
 */
@Entity
@Table(name="WORKSHEETDETAIL")
public class WorkSheetDetail {
	private Long id;
	/**工序代码*/
	private String processCode;
	/**工序名称*/
	private String processName;
	/**设备代码*/
	private String deviceCode;
	/**设备名称*/
	private String deviceName;
	/**站点id*/
	private Long deviceSiteId;
	/**站点代码*/
	private String deviceSiteCode;
	/**站点名称*/
	private String deviceSiteName;
	/**完工数量*/
	private Integer completeCount;
	/**合格数量*/
	private Integer qualifiedCount;
	/**不合格数量*/
	private Integer unqualifiedCount;
	/**返修数量*/
	private Integer repairCount;
	/**报废数量*/
	private Integer scrapCount;
	/**参数取值来源*/
	private String parameterSource;
	/**首件报告*/
	private String firstReport;
	/**状态*/
	private String status;
	/**备注*/
	private String note;
	/**所属工单*/
	private WorkSheet workSheet;
	/**完成时间*/
	private Date completeTime;
	/**报工数*/
	private Integer reportCount;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getDeviceSiteId() {
		return deviceSiteId;
	}
	public void setDeviceSiteId(Long deviceSiteId) {
		this.deviceSiteId = deviceSiteId;
	}
	public String getProcessCode() {
		return processCode;
	}
	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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
	public Integer getCompleteCount() {
		return completeCount;
	}
	public void setCompleteCount(Integer completeCount) {
		this.completeCount = completeCount;
	}
	public Integer getQualifiedCount() {
		return qualifiedCount;
	}
	public void setQualifiedCount(Integer qualifiedCount) {
		this.qualifiedCount = qualifiedCount;
	}
	public Integer getUnqualifiedCount() {
		return unqualifiedCount;
	}
	public void setUnqualifiedCount(Integer unqualifiedCount) {
		this.unqualifiedCount = unqualifiedCount;
	}
	public Integer getRepairCount() {
		return repairCount;
	}
	public void setRepairCount(Integer repairCount) {
		this.repairCount = repairCount;
	}
	public Integer getScrapCount() {
		return scrapCount;
	}
	public void setScrapCount(Integer scrapCount) {
		this.scrapCount = scrapCount;
	}
	public String getParameterSource() {
		return parameterSource;
	}
	public void setParameterSource(String parameterSource) {
		this.parameterSource = parameterSource;
	}
	public String getFirstReport() {
		return firstReport;
	}
	public void setFirstReport(String firstReport) {
		this.firstReport = firstReport;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@ManyToOne
	@JoinColumn(name="WORKSHEET_ID")
	public WorkSheet getWorkSheet() {
		return workSheet;
	}
	public void setWorkSheet(WorkSheet workSheet) {
		this.workSheet = workSheet;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	public Integer getReportCount() {
		return reportCount;
	}
	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}
	
}
