package com.digitzones.service.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.digitzones.model.ProductionUnit;
import com.digitzones.service.IProductionUnitService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:springContext-*.xml"})
public class ProductionUnitTest {
	private IProductionUnitService productionUnitService;
	@Autowired
	public void setProductionUnitService(IProductionUnitService productionUnitService) {
		this.productionUnitService = productionUnitService;
	}
	@Test
	public void testAddDepartment() {
		ProductionUnit d0 = new ProductionUnit();
		d0.setName("嘉兴迪筑工业技术有限公司");
		d0.setCode("ORG");
		
		productionUnitService.addObj(d0);
		
		ProductionUnit d1 = new ProductionUnit();
		d1.setName("销售部");
		d1.setParent(d0);
		d1.setCode("SALE");
		productionUnitService.addObj(d1);
		
		ProductionUnit d2 = new ProductionUnit();
		d2.setName("研发部");
		d2.setParent(d0);
		d2.setCode("RESEARCH");
		productionUnitService.addObj(d2);
		
		
		ProductionUnit d3 = new ProductionUnit();
		d3.setCode("RESEARCH01");
		d3.setName("研发部一");
		d3.setParent(d2);
		productionUnitService.addObj(d3);
	}
}
