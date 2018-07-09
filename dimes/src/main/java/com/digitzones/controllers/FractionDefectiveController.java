package com.digitzones.controllers;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.service.IProcessRecordService;
import com.digitzones.util.DateStringUtil;
@Controller
@RequestMapping("/fractionDefective")
public class FractionDefectiveController {
	private DecimalFormat format = new DecimalFormat("#");
	private DateStringUtil util = new DateStringUtil();
	private IProcessRecordService processRecordService;
	@Autowired
	public void setProcessRecordService(IProcessRecordService processRecordService) {
		this.processRecordService = processRecordService;
	}
	/**
	 * 查询一次性不合格率
	 * @return
	 */
	@RequestMapping("/queryFractionDefective.do")
	@ResponseBody
	public ModelMap queryFractionDefective() {
		ModelMap modelMap = new ModelMap();
		Calendar c = Calendar.getInstance();
		Date now = new Date();
		c.setTime(now);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
		//产生上个月天数
		List<Date> preMonth = util.generateOneMonthDay(util.date2Month(c.getTime()));
		//产生本月天数
		List<Date> thisMonth = util.generateOneMonthDay(util.date2Month(now));
		
		List<String> preMonthList = new ArrayList<>();
		List<String> thisMonthList =new ArrayList<>();
		List<String> prePPMList = new ArrayList<>();
		List<String> thisPPMList = new ArrayList<>();
		for(Date pre : preMonth) {
			//查找不合格品数量
			long ngCount = processRecordService.queryWorkSheetNGCountPerMonth(pre);
			//查找合格品数量
			long notNgCount = processRecordService.queryWorkSHeetNotNGCountPerMonth(pre);
			double ppm = 0;
			if(notNgCount!=0 || ngCount != 0) {
				//计算ppm
				ppm = (ngCount*1.0/(notNgCount+ngCount))*1000000;
			}else {
				ppm = 1000000;
			}
			preMonthList.add(util.date2DayOfMonth(pre));
			prePPMList.add(format.format(ppm));
		}
		for(Date today : thisMonth) {
			//查找不合格品数量
			long ngCount = processRecordService.queryWorkSheetNGCountPerMonth(today);
			//查找合格品数量
			long notNgCount = processRecordService.queryWorkSHeetNotNGCountPerMonth(today);
			double ppm = 0;
			if(notNgCount!=0 || ngCount != 0) {
				//计算ppm
				ppm = (ngCount*1.0/(notNgCount+ngCount))*1000000;
			}else {
				ppm = 1000000;
			}
			thisMonthList.add(util.date2DayOfMonth(today));
			thisPPMList.add(format.format(ppm));
		}
		modelMap.addAttribute("preMonth", preMonthList);
		modelMap.addAttribute("thisMonth", thisMonthList);
		modelMap.addAttribute("prePPMList", prePPMList);
		modelMap.addAttribute("thisPPMList", thisPPMList);
		return modelMap;
	}
} 
