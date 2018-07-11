package com.digitzones.controllers;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Classes;
import com.digitzones.model.DeviceSite;
import com.digitzones.model.Employee;
import com.digitzones.model.Processes;
import com.digitzones.service.IClassesService;
import com.digitzones.service.IDeviceSiteService;
import com.digitzones.service.IEmployeeService;
import com.digitzones.service.IProcessRecordService;
import com.digitzones.service.IProcessesService;
import com.digitzones.service.IProductionUnitService;
import com.digitzones.util.DateStringUtil;
@Controller
@RequestMapping("/output")
public class OutputController {
	private DateStringUtil util = new DateStringUtil();
	private IProcessRecordService processRecordService;
	private IProductionUnitService productionUnitService;
	private IClassesService classService;
	private IEmployeeService employeeService;
	private IProcessesService processService;
	private IDeviceSiteService deviceSiteService;
	@Autowired
	public void setDeviceSiteService(IDeviceSiteService deviceSiteService) {
		this.deviceSiteService = deviceSiteService;
	}
	@Autowired
	public void setProcessService(IProcessesService processService) {
		this.processService = processService;
	}
	@Autowired
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	@Autowired
	public void setClassService(IClassesService classService) {
		this.classService = classService;
	}
	@Autowired
	public void setProductionUnitService(IProductionUnitService productionUnitService) {
		this.productionUnitService = productionUnitService;
	}
	@Autowired
	public void setProcessRecordService(IProcessRecordService processRecordService) {
		this.processRecordService = processRecordService;
	}
	/**
	 * 查询人员月产量
	 * @return
	 */
	@RequestMapping("/queryOutput4EmployeePerMonth.do")
	@ResponseBody
	public List<List<String>> queryOutput4EmployeePerMonth(){
		List<List<String>> list = new ArrayList<>();
		//生成十二个月
		List<Date> months = util.generateOneYearMonth(new Date());
		//查找所有员工
		List<Employee> empList = employeeService.queryAllEmployees();
		List<String> head = new ArrayList<>();
		head.add("月份");
		head.add("产量");
		head.add("人员");
		list.add(head);
		for(Date month : months) {
			for(Employee emp : empList) {
				Integer count = processRecordService.queryOutput4EmployeePerMonth(month,emp.getId());
				List<String> l = new ArrayList<>();
				l.add(util.date2MonthOnly(month));
				l.add(count + "");
				l.add(emp.getCode()+":" + emp.getName());
				list.add(l);
			}
		}
		return list; 
	}
	/**
	 * 查询工序月产量
	 * @return
	 */
	@RequestMapping("/queryOutput4ProcessPerMonth.do")
	@ResponseBody
	public List<List<String>> queryOutput4ProcessPerMonth(){
		List<List<String>> list = new ArrayList<>();
		//生成十二个月
		List<Date> months = util.generateOneYearMonth(new Date());
		//查找所有工序
		List<Processes> proList = processService.queryAllProcesses();
		List<String> head = new ArrayList<>();
		head.add("月份");
		head.add("产量");
		head.add("工序");
		list.add(head);
		for(Date month : months) {
			for(Processes p : proList) {
				int count = processRecordService.queryOutput4ProcessPerMonth(month,p.getId());
				List<String> l = new ArrayList<>();
				l.add(util.date2MonthOnly(month));
				l.add(count+"");
				l.add(p.getCode()+":" + p.getName());
				list.add(l);
			}
		}
		return list; 
	}
	/**
	 * 查询工序月产量
	 * @return
	 */
	@RequestMapping("/queryOutput4DeviceSitePerMonth.do")
	@ResponseBody
	public List<List<String>> queryOutput4DeviceSitePerMonth(){
		List<List<String>> list = new ArrayList<>();
		//生成十二个月
		List<Date> months = util.generateOneYearMonth(new Date());
		List<String> head = new ArrayList<>();
		//查找所有站点
		List<DeviceSite> deviceSiteList = deviceSiteService.queryAllDeviceSites();
		head.add("月份");
		head.add("产量");
		head.add("站点");
		list.add(head);
		for(Date month : months) {
			for(DeviceSite deviceSite:deviceSiteList) {
				int count = processRecordService.queryOutput4DeviceSitePerMonth(month,deviceSite.getId());
				List<String> l = new ArrayList<>();
				l.add(util.date2MonthOnly(month));
				l.add(count+"");
				l.add(deviceSite.getCode() + ":" + deviceSite.getName());
				list.add(l);
			}
		}
		return list; 
	}
	/**
	 * 产线级产量
	 * @param productionUnitId
	 * @return
	 */
	@RequestMapping("/queryOutput4ProductionUnit.do")
	@ResponseBody
	public ModelMap queryOutput4ProductionUnit(Long productionUnitId) {
		if(productionUnitId==null) {
			productionUnitId = 0l;
		}
		ModelMap modelMap = new ModelMap();
		//通过产线id查找产量目标
		int goalOutput = productionUnitService.queryGoalOutputByProductionUnitId(productionUnitId);
		List<String> goalOutputList = new ArrayList<>();
		//查找所有班次
		List<Classes> classesList = classService.queryAllClasses();
		//班次名称
		List<String> classNameList = new ArrayList<>();
		Date now = new Date();
		Map<String, List<String>> classOutputMap = new HashMap<>();
		//产生一个月的日期
		List<Date> days = util.generateOneMonthDay(util.date2Month(now));
		List<String> strDays = new ArrayList<>();
		for(Classes c : classesList) {
			classNameList.add(c.getName());
			List<String> outputList = new ArrayList<>();
			for(Date d : days) {
				long count = processRecordService.queryCountByClassesIdAndDay(c.getId(), d,productionUnitId);
				outputList.add(count+"");
			}
			
			classOutputMap.put(c.getName(), outputList);
		}
		for(Date d : days) {
			strDays.add(util.date2DayOfMonth(d));
			goalOutputList.add(goalOutput + "");
		}
		
		modelMap.addAttribute("goalOutput", goalOutputList);
		modelMap.addAttribute("classNameList", classNameList);
		modelMap.addAttribute("outputMap", classOutputMap);
		modelMap.addAttribute("days", strDays);
		return modelMap;
	}
} 
