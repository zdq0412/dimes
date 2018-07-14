package com.digitzones.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 员工实体
 * @author zdq
 * 2018年6月2日
 */
@Entity
@Table(name="EMPLOYEE")
public class Employee extends CommonModel {
	private static final long serialVersionUID = 1L;
	/**头像*/
	private String photo;
	/**员工所在岗位*/
	private Position position;
	/**员工所在生产单元*/
	private ProductionUnit productionUnit;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PRODUCTIONUNIT_ID")
	public ProductionUnit getProductionUnit() {
		return productionUnit;
	}
	public void setProductionUnit(ProductionUnit productionUnit) {
		this.productionUnit = productionUnit;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="POSITION_ID")
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
