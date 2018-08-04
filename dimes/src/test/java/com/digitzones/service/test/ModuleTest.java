package com.digitzones.service.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.digitzones.constants.Constant;
import com.digitzones.model.Department;
import com.digitzones.model.EquipmentType;
/**
 * 功能模块测试类
 * @author zdq
 * 2018年6月6日
 */
import com.digitzones.model.Module;
import com.digitzones.model.NGReasonType;
import com.digitzones.model.ParameterType;
import com.digitzones.model.PressLightType;
import com.digitzones.model.ProcessType;
import com.digitzones.model.ProductionUnit;
import com.digitzones.model.Role;
import com.digitzones.model.SkillLevel;
import com.digitzones.model.WorkpieceType;
import com.digitzones.service.IDepartmentService;
import com.digitzones.service.IEquipmentTypeService;
import com.digitzones.service.IModuleService;
import com.digitzones.service.INGReasonTypeService;
import com.digitzones.service.IParameterTypeService;
import com.digitzones.service.IPressLightTypeService;
import com.digitzones.service.IProcessTypeService;
import com.digitzones.service.IProductionUnitService;
import com.digitzones.service.IRoleService;
import com.digitzones.service.ISkillLevelService;
import com.digitzones.service.IWorkpieceTypeService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:springContext-*.xml"})
public class ModuleTest {
	private IModuleService moduleService ;
	private IDepartmentService departmentService;
	private IProductionUnitService productionUnitService;
	private INGReasonTypeService ngReasonTypeService;
	private IParameterTypeService parameterTypeService;
	private IPressLightTypeService  pressLightTypeService;
	private IWorkpieceTypeService workpieceTypeService;
	private IEquipmentTypeService equipmentTypeService;
	private ISkillLevelService skillLevelService;
	private IProcessTypeService processTypeService;
	private IRoleService roleService;
	@Autowired
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	@Autowired
	public void setProcessTypeService(IProcessTypeService processTypeService) {
		this.processTypeService = processTypeService;
	}
	@Autowired
	public void setSkillLevelService(ISkillLevelService skillLevelService) {
		this.skillLevelService = skillLevelService;
	}
	@Autowired
	public void setEquipmentTypeService(IEquipmentTypeService equipmentTypeService) {
		this.equipmentTypeService = equipmentTypeService;
	}
	@Autowired
	public void setWorkpieceTypeService(IWorkpieceTypeService workpieceTypeService) {
		this.workpieceTypeService = workpieceTypeService;
	}
	@Autowired
	public void setPressLightTypeService(IPressLightTypeService pressLightTypeService) {
		this.pressLightTypeService = pressLightTypeService;
	}
	@Autowired
	public void setParameterTypeService(IParameterTypeService parameterTypeService) {
		this.parameterTypeService = parameterTypeService;
	}
	@Autowired
	public void setNgReasonTypeService(INGReasonTypeService ngReasonTypeService) {
		this.ngReasonTypeService = ngReasonTypeService;
	}
	@Autowired
	public void setProductionUnitService(IProductionUnitService productionUnitService) {
		this.productionUnitService = productionUnitService;
	}
	@Autowired
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
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
		moduleService.addModule(son1);
		
		Module son11 = new Module();
		son11.setUrl("console/jsp/workSheet.jsp");
		son11.setLeaf(true);
		son11.setName("生产工单");
		son11.setParent(son1);
		moduleService.addModule(son11);
		
		Module parent2 = new Module();
		parent2.setName("数字中心");
		parent2.setIcon("fa fa-bar-chart-o");
		moduleService.addModule(parent2);
		
		
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
		son22.setUrl("console/jsp/employeeSkill.jsp");
		moduleService.addModule(son22);
		
		Module son23 = new Module();
		son23.setParent(son2);
		son23.setName("故障时间");
		son23.setUrl("console/jsp/breakdownTime.jsp");
		son23.setLeaf(true);
		moduleService.addModule(son23);
		
		Module son24 = new Module();
		son24.setParent(son2);
		son24.setName("故障停机分类");
		son24.setLeaf(true);
		son24.setUrl("console/jsp/breakdownHalt.jsp");
		moduleService.addModule(son24);
		
		Module son25 = new Module();
		son25.setParent(son2);
		son25.setName("运行状态");
		son25.setLeaf(true);
		son25.setUrl("console/jsp/runningStatus.jsp");
		moduleService.addModule(son25);
		
		Module son26 = new Module();
		son26.setParent(son2);
		son26.setName("参数状态");
		son26.setUrl("console/jsp/parameterStatus.jsp");
		son26.setLeaf(true);
		moduleService.addModule(son26);
		
		Module son27 = new Module();
		son27.setParent(son2);
		son27.setName("OEE");
		son27.setUrl("console/jsp/oee.jsp");
		son27.setLeaf(true);
		moduleService.addModule(son27);
		
		Module son28 = new Module();
		son28.setParent(son2);
		son28.setName("产量");
		son28.setUrl("console/jsp/output.jsp");
		son28.setLeaf(true);
		moduleService.addModule(son28);
		
		Module son29 = new Module();
		son29.setParent(son2);
		son29.setName("一次性不合格率");
		son29.setUrl("console/jsp/fractionDefective.jsp");
		son29.setLeaf(true);
		moduleService.addModule(son29);
		
		Module son210 = new Module();
		son210.setParent(son2);
		son210.setName("报废率");
		son210.setUrl("console/jsp/scraprate.jsp");
		son210.setLeaf(true);
		moduleService.addModule(son210);
		
		Module son211 = new Module();
		son211.setParent(son2);
		son211.setName("报废状态");
		son211.setUrl("console/jsp/scrapStatus.jsp");
		son211.setLeaf(true);
		moduleService.addModule(son211);
		
		Module son212 = new Module();
		son212.setParent(son2);
		son212.setName("质量日历");
		son212.setUrl("console/jsp/qualityCalendar.jsp");
		son212.setLeaf(true);
		moduleService.addModule(son212);
		
		Module son3 = new Module();
		son3.setParent(parent2);
		son3.setName("产线级");
		moduleService.addModule(son3);
		
		Module son31 = new Module();
		son31.setParent(son3);
		son31.setName("OEE记录表");
		son31.setLeaf(true);
		son31.setUrl("console/jsp/oee4ProductionUnit.jsp");
		moduleService.addModule(son31);
		
		Module son32 = new Module();
		son32.setParent(son3);
		son32.setName("产量记录表");
		son32.setUrl("console/jsp/output4ProductionUnit.jsp");
		son32.setLeaf(true);
		moduleService.addModule(son32);
		
		Module son33 = new Module();
		son33.setParent(son3);
		son33.setName("不合格记录表");
		son33.setUrl("console/jsp/ngRecord4ProductionUnit.jsp");
		son33.setLeaf(true);
		moduleService.addModule(son33);
		
		Module son34 = new Module();
		son34.setParent(son3);
		son34.setName("人员技能-产线级");
		son34.setLeaf(true);
		son34.setUrl("console/jsp/employeeSkill4ProductionUnit.jsp");
		moduleService.addModule(son34);
		
		Module son35 = new Module();
		son35.setParent(son3);
		son35.setName("安全日历");
		son35.setLeaf(true);
		son35.setUrl("console/jsp/secureEnvironment.jsp");
		moduleService.addModule(son35);
		
		Module son36 = new Module();
		son36.setParent(son3);
		son36.setName("损时分析");
		son36.setLeaf(true);
		son36.setUrl("console/jsp/lostTimeReason.jsp");
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
		

		Module m = new Module();
		m.setName("执行数据");
		m.setParent(parent3);
		m.setLeaf(false);
		moduleService.addModule(m);
		
		Module child = new Module();
		child.setName("设备执行");
		child.setParent(m);
		child.setLeaf(true);
		child.setUrl("console/jsp/processRecord.jsp");
		moduleService.addModule(child);
		
		Module son5 = new Module();
		son5.setParent(m);
		son5.setName("不合格品");
		son5.setUrl("console/jsp/ngRecord.jsp");
		son5.setLeaf(true);
		moduleService.addModule(son5);
		
		
		Module son66 = new Module();
		son66.setParent(m);
		son66.setDisabled(false);
		son66.setName("按灯管理");
		son66.setLeaf(true);
		son66.setUrl("console/jsp/pressLightRecord.jsp");
		
		moduleService.addModule(son66);
		
		
		Module son7 = new Module();
		son7.setParent(m);
		son7.setName("损时记录");
		son7.setLeaf(true);
		son7.setUrl("console/jsp/lostTimeRecord.jsp");
		moduleService.addModule(son7);
		
		Module son8 = new Module();
		son8.setParent(m);
		son8.setName("装备关联");
		son8.setLeaf(true);
		son8.setUrl("console/jsp/equipmentMappingRecord.jsp");
		moduleService.addModule(son8);
		
		Module son9 = new Module();
		son9.setParent(m);
		son9.setName("量具关联");
		son9.setLeaf(true);
		son9.setUrl("console/jsp/measuringToolMappingRecord.jsp");
		moduleService.addModule(son9);
		
		Module qualityCalendarRecord = new Module();
		qualityCalendarRecord.setParent(m);
		qualityCalendarRecord.setName("质量投诉记录");
		qualityCalendarRecord.setLeaf(true);
		qualityCalendarRecord.setUrl("console/jsp/qualityCalendarRecord.jsp");
		moduleService.addModule(qualityCalendarRecord);
		
		Module secureEnvironmentRecord = new Module();
		secureEnvironmentRecord.setParent(m);
		secureEnvironmentRecord.setName("安环记录");
		secureEnvironmentRecord.setLeaf(true);
		secureEnvironmentRecord.setUrl("console/jsp/secureEnvironmentRecord.jsp");
		moduleService.addModule(secureEnvironmentRecord);
		
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
		basicSon12.setUrl("console/jsp/productionUnit.jsp");
		moduleService.addModule(basicSon12);
		
		Module basicSon130 = new Module();
		basicSon130.setParent(basicSon1);
		basicSon130.setName("人员技能");
		basicSon130.setUrl("console/jsp/skill.jsp");
		basicSon130.setLeaf(true);
		moduleService.addModule(basicSon130);
		
		Module basicSon13 = new Module();
		basicSon13.setParent(basicSon1);
		basicSon13.setName("人员资料");
		basicSon13.setLeaf(true);
		basicSon13.setUrl("console/jsp/employee.jsp");
		moduleService.addModule(basicSon13);	

		
		Module basicSon14 = new Module();
		basicSon14.setParent(basicSon1);
		basicSon14.setName("班次定义");
		basicSon14.setLeaf(true);
		basicSon14.setUrl("console/jsp/classes.jsp");
		moduleService.addModule(basicSon14);
		
		Module basicSon2 = new Module();
		basicSon2.setParent(parent4);
		basicSon2.setName("装备信息");
		moduleService.addModule(basicSon2);
		
		Module basicSon21 = new Module();
		basicSon21.setParent(basicSon2);
		basicSon21.setName("设备信息");
		basicSon21.setLeaf(true);
		basicSon21.setUrl("console/jsp/device.jsp");
		moduleService.addModule(basicSon21);
		
		Module basicSon22 = new Module();
		basicSon22.setParent(basicSon2);
		basicSon22.setName("装备信息");
		basicSon22.setUrl("console/jsp/equipmentType.jsp");
		basicSon22.setLeaf(true);
		moduleService.addModule(basicSon22);
		
		Module basicSon23 = new Module();
		basicSon23.setParent(basicSon2);
		basicSon23.setName("量具信息");
		basicSon23.setLeaf(true);
		basicSon23.setUrl("console/jsp/measuringToolType.jsp");
		moduleService.addModule(basicSon23);
		
		Module basicSon24 = new Module();
		basicSon24.setParent(basicSon2);
		basicSon24.setName("故障原因");
		basicSon24.setLeaf(true);
		basicSon24.setUrl("console/jsp/pressLightType.jsp");
		moduleService.addModule(basicSon24);
		
		
		Module basicSon3 = new Module();
		basicSon3.setParent(parent4);
		basicSon3.setName("物料信息");
		moduleService.addModule(basicSon3); 
		
		
		Module workpieceType = new Module();
		workpieceType.setName("工件类别");
		workpieceType.setUrl("console/jsp/workpieceType.jsp");
		workpieceType.setLeaf(true);
		workpieceType.setParent(basicSon3);
		
		moduleService.addModule(workpieceType);
		
		Module qualityCalendarType = new Module();
		qualityCalendarType.setName("质量日历类别");
		qualityCalendarType.setUrl("console/jsp/qualityCalendarType.jsp");
		qualityCalendarType.setLeaf(true);
		qualityCalendarType.setParent(basicSon3);
		
		moduleService.addModule(qualityCalendarType);
		
		Module secureEnvironmentType = new Module();
		secureEnvironmentType.setName("安环类别");
		secureEnvironmentType.setUrl("console/jsp/secureEnvironmentType.jsp");
		secureEnvironmentType.setLeaf(true);
		secureEnvironmentType.setParent(basicSon3);
		
		moduleService.addModule(secureEnvironmentType);

		Module basicSon31 = new Module();
		basicSon31.setParent(basicSon3);
		basicSon31.setName("参数信息");
		basicSon31.setLeaf(true);
		basicSon31.setUrl("console/jsp/parameterType.jsp");
		moduleService.addModule(basicSon31);
		
		Module basicSon32 = new Module();
		basicSon32.setParent(basicSon3);
		basicSon32.setName("工序信息");
		basicSon32.setLeaf(true);
		basicSon32.setUrl("console/jsp/processes.jsp");
		moduleService.addModule(basicSon32);
		
		Module basicSon33 = new Module();
		basicSon33.setParent(basicSon3);
		basicSon33.setName("工件信息");
		basicSon33.setLeaf(true);
		basicSon33.setUrl("console/jsp/workpiece.jsp");
		moduleService.addModule(basicSon33);
		
		Module basicSon34 = new Module();
		basicSon34.setParent(basicSon3);
		basicSon34.setName("不良原因");
		basicSon34.setLeaf(true);
		basicSon34.setUrl("console/jsp/ngReasonType.jsp");
		moduleService.addModule(basicSon34);
		
		Module basicSon4 = new Module();
		basicSon4.setParent(parent4);
		basicSon4.setName("质量安全");
		moduleService.addModule(basicSon4);
		
		Module basicSon41 = new Module();
		basicSon41.setParent(basicSon4);
		basicSon41.setName("安环类别");
		basicSon41.setLeaf(true);
		basicSon41.setUrl("console/jsp/secureEnvironmentType.jsp");
		moduleService.addModule(basicSon41);
		
		Module basicSon42 = new Module();
		basicSon42.setParent(basicSon4);
		basicSon42.setName("质量类别");
		basicSon42.setUrl("console/jsp/qualityType.jsp");
		basicSon42.setLeaf(true);
		moduleService.addModule(basicSon42);
		
		Module sysSetParent = new Module();
		sysSetParent.setName("系统设置");
		sysSetParent.setIcon("fa fa-cog");
		
		moduleService.addModule(sysSetParent);
		
		Module sysSetChild = new Module();
		sysSetChild.setName("系统设置");
		sysSetChild.setParent(sysSetParent);
		moduleService.addModule(sysSetChild);
		
		
		Module workflow = new Module();
		workflow.setParent(sysSetChild);
		workflow.setDisabled(false);
		workflow.setLeaf(true);
		workflow.setPriority(10);
		workflow.setUrl("console/jsp/workflow.jsp");
		moduleService.addModule(workflow);
		
		Module skillLevel = new Module();
		skillLevel.setName("技能等级");
		skillLevel.setParent(sysSetChild);
		skillLevel.setUrl("console/jsp/skillLevel.jsp");
		skillLevel.setLeaf(true);
		skillLevel.setDisabled(false);
		moduleService.addModule(skillLevel);
		
		Module qualityGrade = new Module();
		qualityGrade.setName("质量等级");
		qualityGrade.setParent(sysSetChild);
		qualityGrade.setUrl("console/jsp/qualityGrade.jsp");
		qualityGrade.setLeaf(true);
		qualityGrade.setDisabled(false);
		moduleService.addModule(qualityGrade);
		
		Module secureEnvironmentGrade = new Module();
		secureEnvironmentGrade.setName("安环等级");
		secureEnvironmentGrade.setParent(sysSetChild);
		secureEnvironmentGrade.setUrl("console/jsp/secureEnvironmentGrade.jsp");
		secureEnvironmentGrade.setLeaf(true);
		secureEnvironmentGrade.setDisabled(false);
		moduleService.addModule(secureEnvironmentGrade);
		
		
		Module user = new Module();
		user.setName("用户管理");
		user.setParent(sysSetChild);
		user.setUrl("console/jsp/user.jsp");
		user.setLeaf(true);
		user.setDisabled(false);
		moduleService.addModule(user);
		
		Module role = new Module();
		role.setName("角色管理");
		role.setParent(sysSetChild);
		role.setUrl("console/jsp/role.jsp");
		role.setLeaf(true);
		role.setDisabled(false);
		moduleService.addModule(role);
		
		Module power = new Module();
		power.setName("权限管理");
		power.setParent(sysSetChild);
		power.setUrl("console/jsp/power.jsp");
		power.setLeaf(true);
		power.setDisabled(false);
		moduleService.addModule(power);
	}
	
	@Test
	public void init() {
		//新增一个根部门
		Department d0 = new Department();
		d0.setName("嘉兴迪筑工业技术有限公司");
		d0.setCode("ORG");
		departmentService.addDepartment(d0);
		
		//新增一个根生产单元
		ProductionUnit rootProductionUnit = new ProductionUnit();
		rootProductionUnit.setName("嘉兴迪筑工业技术有限公司");
		rootProductionUnit.setCode("ROOTPRODUCTIONUNIT");
		
		productionUnitService.addObj(rootProductionUnit);
		
		//新增一个根生产单元
		ProductionUnit notCategories = new ProductionUnit();
		notCategories.setName("未分类");
		notCategories.setCode("NOTCATEGORIES");
		productionUnitService.addObj(notCategories);
		//不良原因类型
		NGReasonType ngReasonType = new NGReasonType();
		ngReasonType.setCode("NGREASONTYPE");
		ngReasonType.setName("不良原因类型");
		
		ngReasonTypeService.addObj(ngReasonType);
		//参数类型
		ParameterType parent1 = new ParameterType();
		parent1.setCode("ART");
		parent1.setName("工艺参数");
		
		parameterTypeService.addObj(parent1);
		ParameterType parent2 = new ParameterType();
		parent2.setCode("DEVICE");
		parent2.setName("设备参数");
		
		parameterTypeService.addObj(parent2);
		//故障或按灯类型
		PressLightType plt = new PressLightType();
		plt.setCode("TOPTYPE");
		plt.setName("故障类型");
		
		pressLightTypeService.addObj(plt);
		
		PressLightType employee = new PressLightType();
		employee.setCode(Constant.PressLightType.EMPLOYEE);
		employee.setName("人员");
		employee.setParent(plt);
		pressLightTypeService.addObj(employee);
		
		PressLightType device = new PressLightType();
		device.setCode(Constant.PressLightType.DEVICE);
		device.setName("设备");
		device.setParent(plt);
		pressLightTypeService.addObj(device);
		
		PressLightType materiel = new PressLightType();
		materiel.setCode(Constant.PressLightType.DEVICE);
		materiel.setName("物料");
		materiel.setParent(plt);
		pressLightTypeService.addObj(materiel);
		
		PressLightType method = new PressLightType();
		method.setCode(Constant.PressLightType.DEVICE);
		method.setName("方法");
		method.setParent(plt);
		pressLightTypeService.addObj(method);
		
		PressLightType secureEnvironment = new PressLightType();
		secureEnvironment.setCode(Constant.PressLightType.DEVICE);
		secureEnvironment.setName("安环");
		secureEnvironment.setParent(plt);
		pressLightTypeService.addObj(secureEnvironment);
		
		PressLightType measure = new PressLightType();
		measure.setCode(Constant.PressLightType.DEVICE);
		measure.setName("测量");
		measure.setParent(plt);
		pressLightTypeService.addObj(measure);
		
		
		//工件类型
		WorkpieceType workpieceType = new WorkpieceType();
		workpieceType.setCode("PARENT_TYPE");
		workpieceType.setName("工件类别");
		
		workpieceTypeService.addObj(workpieceType);
		
		//量具和装备类型
		EquipmentType equipmentType = new EquipmentType();
		equipmentType.setCode("EQUIPMENT");
		equipmentType.setName("装备");
		
		equipmentTypeService.addObj(equipmentType);
		
		EquipmentType measuringtool = new EquipmentType();
		measuringtool.setCode("MEASURINGTOOL");
		measuringtool.setName("量具");
		
		equipmentTypeService.addObj(measuringtool);
		
		
		//技能等级维护
		SkillLevel I = new SkillLevel();
		I.setCode("I");
		I.setDisabled(false);
		I.setName("I");
		I.setNote("");
		
		skillLevelService.addObj(I);
		
		SkillLevel L = new SkillLevel();
		L.setCode("L");
		L.setDisabled(false);
		L.setName("L");
		L.setNote("");
		
		skillLevelService.addObj(L);
		
		SkillLevel O = new SkillLevel();
		O.setCode("O");
		O.setDisabled(false);
		O.setName("O");
		O.setNote("");
		
		skillLevelService.addObj(O);
		
		SkillLevel U = new SkillLevel();
		U.setCode("U");
		U.setDisabled(false);
		U.setName("U");
		U.setNote("");
		
		skillLevelService.addObj(U);
		
		//初始化工序根类别
		ProcessType rootProcessType = new ProcessType();
		rootProcessType.setCode("root");
		rootProcessType.setName("工序");
		
		processTypeService.addObj(rootProcessType);
		//预设角色
		Role r0 = new Role();
		r0.setAllowDelete(false);
		r0.setRoleName("NG审核人");
		r0.setDisable(false);
		r0.setNote("不合格品审核人，预设角色，不允许删除和修改!");
		roleService.addObj(r0);
		
		Role r1 = new Role();
		r1.setAllowDelete(false);
		r1.setRoleName("NG复核人");
		r1.setDisable(false);
		r1.setNote("不合格品复核人，预设角色，不允许删除和修改!");
		roleService.addObj(r1);
		
		Role r2 = new Role();
		r2.setAllowDelete(false);
		r2.setRoleName("NG确认人");
		r2.setDisable(false);
		r2.setNote("不合格品确认人，预设角色，不允许删除和修改!");
		roleService.addObj(r2);
		
		Role r3 = new Role();
		r3.setAllowDelete(false);
		r3.setRoleName("按灯恢复人");
		r3.setDisable(false);
		r3.setNote("按灯恢复人，预设角色，不允许删除和修改!");
		roleService.addObj(r3);
		
		Role r4 = new Role();
		r4.setAllowDelete(false);
		r4.setRoleName("熄灯人");
		r4.setDisable(false);
		r4.setNote("熄灯人，预设角色，不允许删除和修改!");
		roleService.addObj(r4);
		
		
		Role r6 = new Role();
		r6.setAllowDelete(false);
		r6.setRoleName("按灯确认人");
		r6.setDisable(false);
		r6.setNote("按灯确认人，预设角色，不允许删除和修改!");
		roleService.addObj(r6);
		
		Role r7 = new Role();
		r7.setAllowDelete(false);
		r7.setRoleName("损时确认人");
		r7.setDisable(false);
		r7.setNote("损时确认人，预设角色，不允许删除和修改!");
		roleService.addObj(r7);
		
	}
	@Test
	public void add() {
		/*Role r0 = new Role();
		r0.setAllowDelete(false);
		r0.setRoleName("NG审核人");
		r0.setDisable(false);
		r0.setNote("不合格品审核人，预设角色，不允许删除和修改!");
		roleService.addObj(r0);
		
		Role r1 = new Role();
		r1.setAllowDelete(false);
		r1.setRoleName("NG复核人");
		r1.setDisable(false);
		r1.setNote("不合格品复核人，预设角色，不允许删除和修改!");
		roleService.addObj(r1);
		
		Role r2 = new Role();
		r2.setAllowDelete(false);
		r2.setRoleName("NG确认人");
		r2.setDisable(false);
		r2.setNote("不合格品确认人，预设角色，不允许删除和修改!");
		roleService.addObj(r2);
		
		Role r3 = new Role();
		r3.setAllowDelete(false);
		r3.setRoleName("按灯恢复人");
		r3.setDisable(false);
		r3.setNote("按灯恢复人，预设角色，不允许删除和修改!");
		roleService.addObj(r3);
		
		Role r4 = new Role();
		r4.setAllowDelete(false);
		r4.setRoleName("熄灯人");
		r4.setDisable(false);
		r4.setNote("熄灯人，预设角色，不允许删除和修改!");
		roleService.addObj(r4);
		
		Role r5 = new Role();
		r5.setAllowDelete(false);
		r5.setRoleName("熄灯人");
		r5.setDisable(false);
		r5.setNote("熄灯人，预设角色，不允许删除和修改!");
		roleService.addObj(r5);
		
		Role r6 = new Role();
		r6.setAllowDelete(false);
		r6.setRoleName("按灯确认人");
		r6.setDisable(false);
		r6.setNote("按灯确认人，预设角色，不允许删除和修改!");
		roleService.addObj(r6);
		
		Role r7 = new Role();
		r7.setAllowDelete(false);
		r7.setRoleName("损时确认人");
		r7.setDisable(false);
		r7.setNote("损时确认人，预设角色，不允许删除和修改!");
		roleService.addObj(r7);*/
	}
}
