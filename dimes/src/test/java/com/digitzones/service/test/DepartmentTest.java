package com.digitzones.service.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.digitzones.model.Department;
import com.digitzones.service.IDepartmentService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:springContext-*.xml"})
public class DepartmentTest {
	private IDepartmentService departmentService;
	@Autowired
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	@Test
	public void testAddDepartment() {
		Department d0 = new Department();
		d0.setName("嘉兴迪筑工业技术有限公司");
		d0.setCode("ORG");
		
		departmentService.addDepartment(d0);
		
		Department d1 = new Department();
		d1.setName("销售部");
		d1.setParent(d0);
		d1.setCode("SALE");
		departmentService.addDepartment(d1);
		
		Department d2 = new Department();
		d2.setName("研发部");
		d2.setParent(d0);
		d2.setCode("RESEARCH");
		departmentService.addDepartment(d2);
		
		
		Department d3 = new Department();
		d3.setCode("RESEARCH01");
		d3.setName("研发部一");
		d3.setParent(d2);
		departmentService.addDepartment(d3);
		
	}
}
