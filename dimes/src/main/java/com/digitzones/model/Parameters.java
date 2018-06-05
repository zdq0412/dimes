package com.digitzones.model;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 参数
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="PARAMETERS")
public class Parameters extends CommonModel {
	private static final long serialVersionUID = 1L;
	private Boolean kfc;
	public Boolean getKfc() {
		return kfc;
	}
	public void setKfc(Boolean kfc) {
		this.kfc = kfc;
	}
}
