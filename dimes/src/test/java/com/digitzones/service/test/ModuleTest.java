package com.digitzones.service.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 功能模块测试类
 * @author zdq
 * 2018年6月6日
 */
import com.digitzones.model.Module;
import com.digitzones.service.IModuleService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:springContext-*.xml"})
public class ModuleTest {
	private IModuleService moduleService ;
	@Autowired
	public void setModuleService(IModuleService moduleService) {
		this.moduleService = moduleService;
	}
	@Test
	public void testAddModule() {
		Module parent1 = new Module();
		parent1.setName("运营中心");
		parent1.setIcon("fa fa-server");
		moduleService.addModule(parent1);
		
		
		Module son1 = new Module();
		son1.setParent(parent1);
		son1.setName("生产工单");
		son1.setLeaf(true);
		moduleService.addModule(son1);
		
		
		Module parent2 = new Module();
		parent2.setName("数字中心");
		parent2.setIcon("fa fa-bar-chart-o");
		moduleService.addModule(parent2);
	/*-------------------------------工厂级------------------------------------------*/
		Module son2 = new Module();
		son2.setParent(parent2);
		son2.setName("工厂级");
		moduleService.addModule(son2);
		
		
		Module son21 = new Module();
		son21.setParent(son2);
		son21.setName("在岗状态");
		son21.setLeaf(true);
		moduleService.addModule(son21);
		
		Module son22 = new Module();
		son22.setParent(son2);
		son22.setName("人员技能");
		son22.setLeaf(true);
		moduleService.addModule(son22);
		
		Module son23 = new Module();
		son23.setParent(son2);
		son23.setName("故障时间");
		son23.setLeaf(true);
		moduleService.addModule(son23);
		
		Module son24 = new Module();
		son24.setParent(son2);
		son24.setName("故障停机分类");
		son24.setLeaf(true);
		moduleService.addModule(son24);
		
		Module son25 = new Module();
		son25.setParent(son2);
		son25.setName("运行状态");
		son25.setLeaf(true);
		moduleService.addModule(son25);
		
		Module son26 = new Module();
		son26.setParent(son2);
		son26.setName("参数状态");
		son26.setLeaf(true);
		moduleService.addModule(son26);
		
		Module son27 = new Module();
		son27.setParent(son2);
		son27.setName("OEE");
		son27.setLeaf(true);
		moduleService.addModule(son27);
		
		Module son28 = new Module();
		son28.setParent(son2);
		son28.setName("产量");
		son28.setLeaf(true);
		moduleService.addModule(son28);
		
		Module son29 = new Module();
		son29.setParent(son2);
		son29.setName("一次性不合格率");
		son29.setLeaf(true);
		moduleService.addModule(son29);
		
		Module son210 = new Module();
		son210.setParent(son2);
		son210.setName("报废率");
		son210.setLeaf(true);
		moduleService.addModule(son210);
		
		Module son211 = new Module();
		son211.setParent(son2);
		son211.setName("报废状态");
		son211.setLeaf(true);
		moduleService.addModule(son211);
		
		Module son212 = new Module();
		son212.setParent(son2);
		son212.setName("质量日历");
		son212.setLeaf(true);
		moduleService.addModule(son212);
	/*-------------------------------产线级------------------------------------------*/
		Module son3 = new Module();
		son3.setParent(parent2);
		son3.setName("产线级");
		moduleService.addModule(son3);
		
		Module son31 = new Module();
		son31.setParent(son3);
		son31.setName("OEE记录表");
		son31.setLeaf(true);
		moduleService.addModule(son31);
		
		Module son32 = new Module();
		son32.setParent(son3);
		son32.setName("产量记录表");
		son32.setLeaf(true);
		moduleService.addModule(son32);
		
		Module son33 = new Module();
		son33.setParent(son3);
		son33.setName("不合格记录表");
		son33.setLeaf(true);
		moduleService.addModule(son33);
		
		Module son34 = new Module();
		son34.setParent(son3);
		son34.setName("人员技能");
		son34.setLeaf(true);
		moduleService.addModule(son34);
		
		Module son35 = new Module();
		son35.setParent(son3);
		son35.setName("安全日历");
		son35.setLeaf(true);
		moduleService.addModule(son35);
		
		Module son36 = new Module();
		son36.setParent(son3);
		son36.setName("损失时间分析");
		son36.setLeaf(true);
		moduleService.addModule(son36);
		
		Module son4 = new Module();
		son4.setParent(parent2);
		son4.setName("系统配置");
		son4.setLeaf(true);
		moduleService.addModule(son4);
		
		
		Module parent3 = new Module();
		parent3.setName("执行数据");
		parent3.setIcon("fa fa-book");
		moduleService.addModule(parent3);
		
		Module son5 = new Module();
		son5.setParent(parent3);
		son5.setName("不合格品");
		son5.setLeaf(true);
		moduleService.addModule(son5);
		
		Module son6 = new Module();
		son6.setParent(parent3);
		son6.setName("按灯管理");
		son6.setLeaf(true);
		moduleService.addModule(son6);
		
		Module son7 = new Module();
		son7.setParent(parent3);
		son7.setName("损时记录");
		son7.setLeaf(true);
		moduleService.addModule(son7);
		
		Module son8 = new Module();
		son8.setParent(parent3);
		son8.setName("装备关联");
		son8.setLeaf(true);
		moduleService.addModule(son8);
		
		Module son9 = new Module();
		son9.setParent(parent3);
		son9.setName("量具关联");
		son9.setLeaf(true);
		moduleService.addModule(son9);
		
		Module son10 = new Module();
		son10.setParent(parent3);
		son10.setName("报表中心");
		moduleService.addModule(son10);
		
		Module son101 = new Module();
		son101.setParent(son10);
		son101.setName("装备关联记录");
		son101.setLeaf(true);
		moduleService.addModule(son101);
		
		Module son102 = new Module();
		son102.setParent(son10);
		son102.setName("量具关联记录");
		son102.setLeaf(true);
		moduleService.addModule(son102);
		
		Module son103 = new Module();
		son103.setParent(son10);
		son103.setName("NG记录");
		son103.setLeaf(true);
		moduleService.addModule(son103);
		
		Module son104 = new Module();
		son104.setParent(son10);
		son104.setName("损时明细");
		son104.setLeaf(true);
		moduleService.addModule(son104);
		
		
		Module parent4 = new Module();
		parent4.setName("基础资料");
		parent4.setIcon("fa fa-bullseye");
		moduleService.addModule(parent4);
		
		Module basicSon1 = new Module();
		basicSon1.setParent(parent4);
		basicSon1.setName("公司结构");
		moduleService.addModule(basicSon1);
		
		Module basicSon11 = new Module();
		basicSon11.setParent(basicSon1);
		basicSon11.setName("组织结构");
		basicSon11.setLeaf(true);
		basicSon11.setUrl("console/jsp/org.jsp");
		moduleService.addModule(basicSon11);
		
		Module basicSon12 = new Module();
		basicSon12.setParent(basicSon1);
		basicSon12.setName("生产单元");
		basicSon12.setLeaf(true);
		moduleService.addModule(basicSon12);
		
		Module basicSon130 = new Module();
		basicSon130.setParent(basicSon1);
		basicSon130.setName("人员技能");
		basicSon130.setLeaf(true);
		moduleService.addModule(basicSon130);
		
		Module basicSon13 = new Module();
		basicSon13.setParent(basicSon1);
		basicSon13.setName("人员资料");
		basicSon13.setLeaf(true);
		moduleService.addModule(basicSon13);	

		
		Module basicSon14 = new Module();
		basicSon14.setParent(basicSon1);
		basicSon14.setName("班次定义");
		basicSon14.setLeaf(true);
		moduleService.addModule(basicSon14);
		
		Module basicSon2 = new Module();
		basicSon2.setParent(parent4);
		basicSon2.setName("装备信息");
		moduleService.addModule(basicSon2);
		
		Module basicSon21 = new Module();
		basicSon21.setParent(basicSon2);
		basicSon21.setName("设备信息");
		basicSon21.setLeaf(true);
		moduleService.addModule(basicSon21);
		
		Module basicSon22 = new Module();
		basicSon22.setParent(basicSon2);
		basicSon22.setName("装备信息");
		basicSon22.setLeaf(true);
		moduleService.addModule(basicSon22);
		
		Module basicSon23 = new Module();
		basicSon23.setParent(basicSon2);
		basicSon23.setName("量具信息");
		basicSon23.setLeaf(true);
		moduleService.addModule(basicSon23);
		
		Module basicSon24 = new Module();
		basicSon24.setParent(basicSon2);
		basicSon24.setName("故障原因");
		basicSon24.setLeaf(true);
		moduleService.addModule(basicSon24);
		
		
		Module basicSon3 = new Module();
		basicSon3.setParent(parent4);
		basicSon3.setName("物料信息");
		moduleService.addModule(basicSon3);
		
		Module basicSon31 = new Module();
		basicSon31.setParent(basicSon3);
		basicSon31.setName("参数信息");
		basicSon31.setLeaf(true);
		moduleService.addModule(basicSon31);
		
		Module basicSon32 = new Module();
		basicSon32.setParent(basicSon3);
		basicSon32.setName("工序信息");
		basicSon32.setLeaf(true);
		moduleService.addModule(basicSon32);
		
		Module basicSon33 = new Module();
		basicSon33.setParent(basicSon3);
		basicSon33.setName("工件信息");
		basicSon33.setLeaf(true);
		moduleService.addModule(basicSon33);
		
		Module basicSon34 = new Module();
		basicSon34.setParent(basicSon3);
		basicSon34.setName("不良原因");
		basicSon34.setLeaf(true);
		moduleService.addModule(basicSon34);
		
		Module basicSon4 = new Module();
		basicSon4.setParent(parent4);
		basicSon4.setName("质量安全");
		moduleService.addModule(basicSon4);
		
		Module basicSon41 = new Module();
		basicSon41.setParent(basicSon4);
		basicSon41.setName("安环类别");
		basicSon41.setLeaf(true);
		moduleService.addModule(basicSon41);
		
		Module basicSon42 = new Module();
		basicSon42.setParent(basicSon4);
		basicSon42.setName("质量类别");
		basicSon42.setLeaf(true);
		moduleService.addModule(basicSon42);
	}
	@Test
	public void testAddEmployeeUrl() {
		Module module = moduleService.queryByProperty("name","人员资料");
		module.setUrl("console/jsp/employee.jsp");
		
		moduleService.updateModule(module);
	}
	@Test
	public void testAddProductionUnitUrl() {
		Module module = moduleService.queryByProperty("name","生产单元");
		module.setUrl("console/jsp/productionUnit.jsp");
		
		moduleService.updateModule(module);
	}
	@Test
	public void testAddDeviceUrl() {
		Module module = moduleService.queryByProperty("name", "设备信息");
		module.setUrl("console/jsp/device.jsp");
		
		moduleService.updateModule(module);
	}
}
