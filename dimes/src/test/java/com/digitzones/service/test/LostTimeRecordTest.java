package com.digitzones.service.test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.digitzones.service.ILostTimeRecordService;
import com.digitzones.service.IPressLightTypeService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:springContext-*.xml"})
public class LostTimeRecordTest {
	private ILostTimeRecordService lostTimeRecordService;
	private IPressLightTypeService pressLightTypeService;
	@Autowired
	public void setPressLightTypeService(IPressLightTypeService pressLightTypeService) {
		this.pressLightTypeService = pressLightTypeService;
	}
	@Autowired
	public void setLostTimeRecordService(ILostTimeRecordService lostTimeRecordService) {
		this.lostTimeRecordService = lostTimeRecordService;
	}
}
