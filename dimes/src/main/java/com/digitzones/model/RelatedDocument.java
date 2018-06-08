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
 * 相关文档
 * @author zdq
 * 2018年6月4日
 */
@Entity
@Table(name="RELATEDDOCUMENT")
public class RelatedDocument {
	private Long id;
	/**文档名称*/
	private String documentName;
	/**文档说明*/
	private String note;
	/**关联代码*/
	private String relatedCode;
	/**关联名称*/
	private String relatedName;
	/**上传时间*/
	private Date uploadDate;
	/**关联的类型，如：部门，参数，生产单元等，参照常量声明部分*/
	private String relatedType;
	/**关联对象的ID*/
	private Long relatedId;
	/**存储路径*/
	private String path;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getRelatedCode() {
		return relatedCode;
	}
	public void setRelatedCode(String relatedCode) {
		this.relatedCode = relatedCode;
	}
	public String getRelatedName() {
		return relatedName;
	}
	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
	}
	public String getRelatedType() {
		return relatedType;
	}
	public void setRelatedType(String relatedType) {
		this.relatedType = relatedType;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public Long getRelatedId() {
		return relatedId;
	}
	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}
}
