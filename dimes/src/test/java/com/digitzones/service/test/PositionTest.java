package com.digitzones.service.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.digitzones.model.Department;
import com.digitzones.model.Position;
import com.digitzones.service.IPositionService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:springContext-*.xml"})
public class PositionTest {
	private IPositionService positionService;
	@Autowired
	public void setPositionService(IPositionService positionService) {
		this.positionService = positionService;
	}
	@Test
	public void testAddPosition() {
		Department d = new Department();
		d.setId(58l);
		
		Position p0 = new Position();
		p0.setCode("P0");
		p0.setName("开发人员0");
		p0.setDepartment(d);
		positionService.addPosition(p0);
		
		Position p1 = new Position();
		p1.setCode("P1");
		p1.setName("开发人员1");
		p1.setDepartment(d);
		positionService.addPosition(p1);
	}
}
