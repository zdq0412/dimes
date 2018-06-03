package com.digitzones.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 生产单元实体
 * @author zdq
 * 2018年6月3日
 */
@Entity
@Table(name="PRODUCTIONUNIT")
public class ProductionUnit extends CommonModel {
	private static final long serialVersionUID = 1L;
	/**父单元*/
	private ProductionUnit parent;
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	public ProductionUnit getParent() {
		return parent;
	}

	public void setParent(ProductionUnit parent) {
		this.parent = parent;
	}
}
