package com.digitzones.service.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.digitzones.model.ParameterType;
import com.digitzones.service.IParameterTypeService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:springContext-*.xml"})
public class ParameterTypeTest {
	private IParameterTypeService parameterTypeService;
	@Autowired
	public void setParameterTypeService(IParameterTypeService parameterTypeService) {
		this.parameterTypeService = parameterTypeService;
	}
	@Test
	public void testAddParameterType() {
		ParameterType parent = new ParameterType();
		parent.setCode("TOPTYPE");
		parent.setName("参数类型");
		
		parameterTypeService.addObj(parent);
		
		ParameterType child1 = new ParameterType();
		child1.setCode("TECHNOLOGY_TYPE");
		child1.setName("工艺参数");
		child1.setParent(parent);
		parameterTypeService.addObj(child1);
		
		
		ParameterType child2 = new ParameterType();
		child2.setCode("DEVICE_TYPE");
		child2.setName("设备参数");
		child2.setParent(parent);
		parameterTypeService.addObj(child2);
		
		
	}
}
