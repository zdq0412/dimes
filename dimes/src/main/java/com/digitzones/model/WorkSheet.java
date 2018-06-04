package com.digitzones.model;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 工单
 * @author zdq
 * 2018年6月4日
 */
@Entity
@Table(name="WORKSHEET")
public class WorkSheet {
	private Long id;
	/**工单编号*/
	private String no;
	/**生产日期*/
	private Date manufactureDate;
	/**制单人*/
	private String documentMaker;
	/**制单日期*/
	private Date makeDocumentDate;
	/**工件代码*/
	private String workPieceCode;
	/**工件名称*/
	private String workPieceName;
	/**规格型号*/
	private String unitType;
	/**客户图号*/
	private String customerGraphNumber;
	/**图号*/
	private String graphNumber;
	/**版本号*/
	private String version;
	/**生产数量*/
	private int productCount;
	/**批号*/
	private String batchNumber;
	/**炉号*/
	private String stoveNumber;
	/**生产单元代码*/
	private String productionUnitCode;
	/**生产单元名称*/
	private String productionUnitName;
	/**备注*/
	private String note;
	/**工单状态*/
	private String status;
	/**完成时间*/
	private Date completeTime;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getManufactureDate() {
		return manufactureDate;
	}
	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	public String getDocumentMaker() {
		return documentMaker;
	}
	public void setDocumentMaker(String documentMaker) {
		this.documentMaker = documentMaker;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMakeDocumentDate() {
		return makeDocumentDate;
	}
	public void setMakeDocumentDate(Date makeDocumentDate) {
		this.makeDocumentDate = makeDocumentDate;
	}
	public String getWorkPieceCode() {
		return workPieceCode;
	}
	public void setWorkPieceCode(String workPieceCode) {
		this.workPieceCode = workPieceCode;
	}
	public String getWorkPieceName() {
		return workPieceName;
	}
	public void setWorkPieceName(String workPieceName) {
		this.workPieceName = workPieceName;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getCustomerGraphNumber() {
		return customerGraphNumber;
	}
	public void setCustomerGraphNumber(String customerGraphNumber) {
		this.customerGraphNumber = customerGraphNumber;
	}
	public String getGraphNumber() {
		return graphNumber;
	}
	public void setGraphNumber(String graphNumber) {
		this.graphNumber = graphNumber;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getStoveNumber() {
		return stoveNumber;
	}
	public void setStoveNumber(String stoveNumber) {
		this.stoveNumber = stoveNumber;
	}
	public String getProductionUnitCode() {
		return productionUnitCode;
	}
	public void setProductionUnitCode(String productionUnitCode) {
		this.productionUnitCode = productionUnitCode;
	}
	public String getProductionUnitName() {
		return productionUnitName;
	}
	public void setProductionUnitName(String productionUnitName) {
		this.productionUnitName = productionUnitName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	
}
