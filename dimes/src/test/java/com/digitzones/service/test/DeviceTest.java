package com.digitzones.service.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.digitzones.model.Department;
import com.digitzones.model.Device;
import com.digitzones.service.IDepartmentService;
import com.digitzones.service.IDeviceService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:springContext-*.xml"})
public class DeviceTest {
	private IDeviceService deviceService;
	@Autowired
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	@Test
	public void testAddDevice() {
		for(int i = 0;i<100;i++) {
			Device d = new Device();
			d.setCode("DEVICE_" + i);
			d.setName("Device" + i);
			d.setStatus("良好");
			d.setUnitType("UNIT_" + i);
			deviceService.addObj(d);
		}
	}
}
