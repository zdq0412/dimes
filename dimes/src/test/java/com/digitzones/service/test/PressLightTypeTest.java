package com.digitzones.service.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.digitzones.model.PressLightType;
import com.digitzones.service.IPressLightTypeService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:springContext-*.xml"})
public class PressLightTypeTest {
	private IPressLightTypeService  pressLightTypeService;
	@Autowired
	public void setPressLightTypeService(IPressLightTypeService pressLightTypeService) {
		this.pressLightTypeService = pressLightTypeService;
	}
	@Test
	public void testAddPressLightType() {
		PressLightType plt = new PressLightType();
		plt.setCode("TOPTYPE");
		plt.setName("故障类型");
		
		pressLightTypeService.addObj(plt);
	}
}
