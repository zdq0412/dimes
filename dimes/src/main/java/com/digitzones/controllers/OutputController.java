package com.digitzones.controllers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.service.IProcessRecordService;
import com.digitzones.service.IProductionUnitService;
import com.digitzones.util.DateStringUtil;
/**
 * 产量
 * @author zdq
 * 2018年7月9日
 */
import com.digitzones.vo.Output;
@Controller
@RequestMapping("/output")
public class OutputController {
	private DateStringUtil util = new DateStringUtil();
	private IProcessRecordService processRecordService;
	private IProductionUnitService productionUnitService;
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
		List<String> head = new ArrayList<>();
		head.add("月份");
		head.add("产量");
		head.add("人员");
		list.add(head);
		for(Date month : months) {
			Object[] results = processRecordService.queryOutput4EmployeePerMonth(month);
			if(results!=null) {
				List<String> l = new ArrayList<>();
				l.add(util.date2MonthOnly(month));
				l.add(results[4]+"");
				l.add(results[2]+":"+results[3]);
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
		List<String> head = new ArrayList<>();
		head.add("月份");
		head.add("产量");
		head.add("工序");
		list.add(head);
		for(Date month : months) {
			Object[] results = processRecordService.queryOutput4ProcessPerMonth(month);
			if(results!=null) {
				List<String> l = new ArrayList<>();
				l.add(util.date2MonthOnly(month));
				l.add(results[2]+"");
				l.add(results[1]+"");
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
		Output output = new Output();
		output.setMonth("月份");
		output.setOutputValue("产量");
		output.setText("站点");
		List<String> head = new ArrayList<>();
		head.add("月份");
		head.add("产量");
		head.add("工序");
		list.add(head);
		for(Date month : months) {
			Object[] results = processRecordService.queryOutput4DeviceSitePerMonth(month);
			if(results!=null) {
				List<String> l = new ArrayList<>();
				l.add(util.date2MonthOnly(month));
				l.add(results[3]+"");
				l.add(results[2]+"");
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
		Date now = new Date();
		//产生一个月的日期
		List<Date> days = util.generateOneMonthDay(util.date2Month(now));
		return modelMap;
	}
} 
