package com.digitzones.controllers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Processes;
import com.digitzones.service.INGRecordService;
import com.digitzones.service.IProcessesService;
import com.digitzones.util.DateStringUtil;
/**
 * 报废率
 * @author zdq
 * 2018年7月10日
 */
@Controller
@RequestMapping("/scrapStatus")
public class ScrapStatusController {
	private DateStringUtil util = new DateStringUtil();
	private IProcessesService processService;
	private INGRecordService ngRecordService;
	@Autowired
	public void setNgRecordService(INGRecordService ngRecordService) {
		this.ngRecordService = ngRecordService;
	}
	@Autowired
	public void setProcessService(IProcessesService processService) {
		this.processService = processService;
	}
	/**
	 * 报废状态：工厂级
	 * @return
	 */
	@RequestMapping("/queryScrapStatus.do")
	@ResponseBody
	public ModelMap queryScrapStatus() {
		ModelMap modelMap = new ModelMap();
		//存储日期值
		List<String> daysList = new  ArrayList<>();
		List<String> processNameList = new  ArrayList<>();
		//查找所有工序
		List<Processes> processList = processService.queryAllProcesses();
		//生成当月日期(天)
		Date now = new Date();
		List<List<Integer>> dataList = new ArrayList<>();
		List<Date> days = util.generateOneMonthDay(util.date2Month(now));
		//查找当月该工序下的报废数
		for(int i = 0;i<processList.size();i++) {
			Processes p = processList.get(i);
			processNameList.add(p.getName());
			for(int j = 0;j<days.size();j++) {
				//根据工序及日期查找报废数量
				int scrapCount = ngRecordService.queryScrapCountByDateAndProcessId(days.get(j), p.getId());
				List<Integer> data = new ArrayList<>();
				data.add(i);
				data.add(j);
				data.add(scrapCount);
				
				dataList.add(data);
			}
		}
		
		for(Date today : days) {
			daysList.add(util.date2DayOfMonth(today));
		}
		modelMap.addAttribute("days",daysList);
		modelMap.addAttribute("processes",processNameList);
		modelMap.addAttribute("dataList",dataList);
		return modelMap;
	}
} 
