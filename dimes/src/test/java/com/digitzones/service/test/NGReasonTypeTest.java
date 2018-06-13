package com.digitzones.service.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.digitzones.model.NGReasonType;
import com.digitzones.service.INGReasonTypeService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:springContext-*.xml"})
public class NGReasonTypeTest {
	private INGReasonTypeService ngReasonTypeService;
	@Autowired
	public void setNgReasonTypeService(INGReasonTypeService ngReasonTypeService) {
		this.ngReasonTypeService = ngReasonTypeService;
	}
	@Test
	public void testAddNGReasonType() {
		NGReasonType ngReasonType = new NGReasonType();
		ngReasonType.setCode("NGREASONTYPE");
		ngReasonType.setName("不良原因类型");
		
		ngReasonTypeService.addObj(ngReasonType);
	}
}
