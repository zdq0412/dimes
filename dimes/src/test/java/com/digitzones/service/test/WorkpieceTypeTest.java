package com.digitzones.service.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.digitzones.model.WorkpieceType;
import com.digitzones.service.IWorkpieceTypeService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:springContext-*.xml"})
public class WorkpieceTypeTest {
	private IWorkpieceTypeService workpieceTypeService;
	@Autowired
	public void setWorkpieceTypeService(IWorkpieceTypeService workpieceTypeService) {
		this.workpieceTypeService = workpieceTypeService;
	}
	@Test
	public void testAddWorkpieceType() {
		WorkpieceType workpieceType = new WorkpieceType();
		workpieceType.setCode("PARENT_TYPE");
		workpieceType.setName("工件类别");
		
		workpieceTypeService.addObj(workpieceType);
	}
}
