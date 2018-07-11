package com.digitzones.controllers;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.NGReasonType;
import com.digitzones.service.INGReasonTypeService;
import com.digitzones.service.IProcessRecordService;
import com.digitzones.util.DateStringUtil;
/**
 * 报废率
 * @author zdq
 * 2018年7月10日
 */
@Controller
@RequestMapping("/scraprate")
public class ScraprateController {
	private DecimalFormat format = new DecimalFormat("#");
	private DateStringUtil util = new DateStringUtil();
	private IProcessRecordService processRecordService;
	private INGReasonTypeService ngReasonTypeService;
	@Autowired
	public void setNgReasonTypeService(INGReasonTypeService ngReasonTypeService) {
		this.ngReasonTypeService = ngReasonTypeService;
	}
	@Autowired
	public void setProcessRecordService(IProcessRecordService processRecordService) {
		this.processRecordService = processRecordService;
	}
	/**
	 * 报废率：工厂级
	 * @return
	 */
	@RequestMapping("/queryScraprate.do")
	@ResponseBody
	public ModelMap queryScraprate() {
		ModelMap modelMap = new ModelMap();
		Calendar c = Calendar.getInstance();
		Date now = new Date();
		c.setTime(now);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
		//产生一年的月份
		List<Date> months = util.generateOneYearMonth(now);
		List<String> monthsStrList = new ArrayList<>();
		//查找所有ng类别
		List<NGReasonType> ngTypes = ngReasonTypeService.queryAllNGReasonTypes();
		Map<String,List<String>> map = new HashMap<>();
		List<String> ngTypesStrList = new ArrayList<>();
		List<String> totalList = new ArrayList<>();
		for(Date today : months) {
			monthsStrList.add(util.date2MonthOnly(today));
			//查找报废品数量
			Integer scrapCount = processRecordService.queryWorkSheetScrapCountPerMonth(today);
			//查找报废品数量
			long ngCount = processRecordService.queryWorkSheetNGCountPerMonth(today);
			//查找合格品数量
			long notNgCount = processRecordService.queryWorkSHeetNotNGCountPerMonth(today);
			double ppm = 0;
			if(notNgCount!=0 && ngCount != 0) {
				//计算ppm
				ppm = (scrapCount*1.0/(notNgCount+ngCount))*1000000;
			}else {
				ppm = 0;
			}
			totalList.add(format.format(ppm));
		}
		
		map.put("总PPM", totalList);
		
		for(NGReasonType type : ngTypes) {
			List<String> ppmList = new ArrayList<>();
			ngTypesStrList.add(type.getName());
			for(Date today : months) {
				//查找报废品数量
				int scrapCount = processRecordService.queryWorkSheetScrapCountPerMonth(today,type.getId());
				//查找报废品数量
				long ngCount = processRecordService.queryWorkSheetNGCountPerMonth(today);
				//查找合格品数量
				long notNgCount = processRecordService.queryWorkSHeetNotNGCountPerMonth(today);
				double ppm = 0;
				if(notNgCount!=0 && ngCount != 0) {
					//计算ppm
					ppm = (scrapCount*1.0/(notNgCount+ngCount))*1000000;
				}else {
					ppm = 0;
				}
				ppmList.add(format.format(ppm));
			}
			map.put(type.getName(), ppmList);
		}
		
		modelMap.addAttribute("monthList", monthsStrList);
		modelMap.addAttribute("ppmMap", map);
		modelMap.addAttribute("ngTypeNames", ngTypesStrList);
		return modelMap;
	}
} 
