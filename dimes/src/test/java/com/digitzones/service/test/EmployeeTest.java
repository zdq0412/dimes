package com.digitzones.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.digitzones.model.Employee;
import com.digitzones.model.Pager;
import com.digitzones.model.Position;
import com.digitzones.service.IEmployeeService;
import com.digitzones.service.IPositionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:springContext-*.xml"})
public class EmployeeTest {
	private IEmployeeService employeeService;
	private IPositionService positionService;
	@Autowired
	public void setPositionService(IPositionService positionService) {
		this.positionService = positionService;
	}
	@Autowired
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testQueryEmployeesByDepartmentId() {
		String hql = "from Employee e left join e.position p left join p.department d where d.id=?0";
		Pager<Object[]> pager = employeeService.queryObjs(hql, 1, 20, 179l);
		List<Object[]> list = pager.getData();
		System.out.println(list);
	}
	@Test
	public void testAddEmployee() {
		Position p = positionService.queryByProperty("name", "DDDDDD");
		
		for(int i = 0;i<100;i++) {
			Employee e = new Employee();
			e.setCode("employee" + i);
			e.setName("employee" + i);
			e.setNote("note" + i);
			e.setPosition(p);
			
			employeeService.addObj(e);
		}
	}
}
