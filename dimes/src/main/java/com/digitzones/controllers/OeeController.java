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

import com.digitzones.model.Classes;
import com.digitzones.model.DeviceSite;
import com.digitzones.model.ProductionUnit;
import com.digitzones.service.IClassesService;
import com.digitzones.service.IDeviceSiteService;
import com.digitzones.service.ILostTimeRecordService;
import com.digitzones.service.INGRecordService;
import com.digitzones.service.IProductionUnitService;
import com.digitzones.service.IWorkpieceProcessDeviceSiteMappingService;
import com.digitzones.util.DateStringUtil;
@Controller
@RequestMapping("/oee")
public class OeeController {
	private DateStringUtil util = new DateStringUtil();
	private DecimalFormat format = new DecimalFormat("#.00");
	private IProductionUnitService productionUnitService;
	private IDeviceSiteService deviceSiteService;
	private IWorkpieceProcessDeviceSiteMappingService workpieceProcessDeviceSiteMappingService;
	private IClassesService classesService;
	private ILostTimeRecordService lostTimeRecordService;
	private INGRecordService ngRecordService;
	@Autowired
	public void setNgRecordService(INGRecordService ngRecordService) {
		this.ngRecordService = ngRecordService;
	}
	@Autowired
	public void setLostTimeRecordService(ILostTimeRecordService lostTimeRecordService) {
		this.lostTimeRecordService = lostTimeRecordService;
	}
	@Autowired
	public void setClassesService(IClassesService classesService) {
		this.classesService = classesService;
	}
	@Autowired
	public void setProductionUnitService(IProductionUnitService productionUnitService) {
		this.productionUnitService = productionUnitService;
	}
	@Autowired
	public void setDeviceSiteService(IDeviceSiteService deviceSiteService) {
		this.deviceSiteService = deviceSiteService;
	}
	@Autowired
	public void setWorkpieceProcessDeviceSiteMappingService(
			IWorkpieceProcessDeviceSiteMappingService workpieceProcessDeviceSiteMappingService) {
		this.workpieceProcessDeviceSiteMappingService = workpieceProcessDeviceSiteMappingService;
	}
	/**
	 * 工厂级oee查询
	 * @return
	 */
	@RequestMapping("/queryOee4Factory")
	@ResponseBody
	public ModelMap queryOee4Factory(){
		ModelMap modelMap = new ModelMap();
		//查找所有生产单元
		List<ProductionUnit> productionUnits = productionUnitService.queryAllProductionUnits();
		modelMap.put("productionUnits", productionUnits);
		modelMap.put("values", queryOee4PerProductionUnit(productionUnits));
		//查询当前班次
		Classes c = classesService.queryCurrentClasses();
		modelMap.put("preMonthOee", format.format(preMonthOee(c)*100));
		modelMap.put("currentMonthOee",format.format(currentMonthOee(c)*100));
		modelMap.put("currentDayOee",format.format(currentDayOee()*100));
		return modelMap;
	}
	/**
	 * 当天OEE值
	 * @return
	 */
	private Double currentDayOee() {
		double sumOee = 0;
		//查询当前班次
		Classes c = classesService.queryCurrentClasses();
		//查询当前班次的加工节拍
		Float processingBeat = workpieceProcessDeviceSiteMappingService.queryProcessingBeatByClassesId(c.getId());
		//根据班次id查询设备站点信息
		List<DeviceSite> deviceSites = deviceSiteService.queryDeviceSitesByClassesId(c.getId());
		for(DeviceSite ds : deviceSites) {
			sumOee += queryOee(c, ds,processingBeat,new Date());
		}
		return sumOee;
	}
	/**
	 * 查询每个生产单元的OEE值
	 * @param productionUnits
	 * @return
	 */
	private List<String> queryOee4PerProductionUnit(List<ProductionUnit> productionUnits){
		List<String> values = new ArrayList<>();
		for(ProductionUnit pu : productionUnits) {
			double sumOee = 0;
			//根据生产单元查询设备站点
			List<DeviceSite> deviceSites = deviceSiteService.queryDeviceSitesByProductionUnitId(pu.getId());
			//查询生产单元的加工节拍
			Float processingBeat = workpieceProcessDeviceSiteMappingService.queryProcessingBeatByProductionUnitId(pu.getId());
			//查询当前班次
			Classes c = classesService.queryCurrentClasses();
			//根据设备站点查询加工信息
			for(DeviceSite ds : deviceSites) {

				sumOee += queryOee(c, ds,processingBeat,new Date());
			}

			values.add(format.format(sumOee/deviceSites.size()*100));
		}
		return values;
	}
	/**
	 * 查询上个月的OEE值
	 * @return
	 */
	private Double preMonthOee(Classes c) {
		double sumOee = 0;
		//查询当前班次的加工节拍
		Float processingBeat = workpieceProcessDeviceSiteMappingService.queryProcessingBeatByClassesId(c.getId());
		//根据班次id查询设备站点信息
		List<DeviceSite> deviceSites = deviceSiteService.queryDeviceSitesByClassesId(c.getId());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH,-1);
		List<Date> preMonthDays = util.generateOneMonthDay(util.date2Month(calendar.getTime()));
		for(Date today : preMonthDays) {
			for(DeviceSite ds : deviceSites) {
				sumOee += queryOee(c, ds,processingBeat,today);
			}
		}
		return sumOee/preMonthDays.size();
	}
	/**
	 * oee算法
	 * @param c
	 * @param ds
	 * @return
	 */
	private double queryOee(Classes c, DeviceSite ds,Float processingBeat,Date date) {
		//查询损时时间(包括计划停机时间)
		double lostTime = lostTimeRecordService.queryLostTime(c,ds.getId(),date);
		//查询计划停机时间
		double planHaltTime = lostTimeRecordService.queryPlanHaltTime(c, ds.getId(),date);
		//查询设备站点的ng数
		int ngCount = ngRecordService.queryNgCountByDeviceSiteId(ds.getId(), date);
		//oee公式:（总加工时间-损时时间-NG件数*标准节拍）/总加工时间 
		//总加工时间，公式：总加工时间=当前时间-班次的开始时间 -计划停机时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int totalMinutes = calendar.get(Calendar.HOUR_OF_DAY)*60 + calendar.get(Calendar.MINUTE);
		Calendar cal = Calendar.getInstance();
		cal.setTime(c.getStartTime());
		//查询计划停机时间
		int classesBeginMinutes = cal.get(Calendar.HOUR_OF_DAY)*60 + cal.get(Calendar.MINUTE);
		//总加工时间
		double sumMinutes = totalMinutes - classesBeginMinutes - planHaltTime;
		double oee = 0;
		if(totalMinutes>classesBeginMinutes) {
			oee = (sumMinutes-lostTime-ngCount*processingBeat)/sumMinutes;
			//
		}else {
			oee = (24+sumMinutes-lostTime-ngCount*processingBeat)/(24*60+sumMinutes);
		}
		return oee;
	}
	/**
	 * 查询当前月的OEE值
	 * @return
	 */
	private Double currentMonthOee(Classes c) {
		double sumOee = 0;
		//查询当前班次的加工节拍
		Float processingBeat = workpieceProcessDeviceSiteMappingService.queryProcessingBeatByClassesId(c.getId());
		//根据班次id查询设备站点信息
		List<DeviceSite> deviceSites = deviceSiteService.queryDeviceSitesByClassesId(c.getId());
		List<Date> currentMonthDays = util.generateOneMonthDay(util.date2Month(new Date()));
		for(Date today : currentMonthDays) {
			for(DeviceSite ds : deviceSites) {
				sumOee += queryOee(c, ds,processingBeat,today);
			}
		}
		return sumOee/currentMonthDays.size();
	}

	/**
	 * 查询每个生产单元的OEE值
	 * @return
	 */
	@RequestMapping("/queryOee4ProductionUnit.do")
	@ResponseBody
	public ModelMap queryOee4ProductionUnit(Long productionUnitId) {
		if(productionUnitId==null) {
			productionUnitId = 0l;
		}
		//根据产线查找目标OEE
		double goalOee = productionUnitService.queryOeeByProductionUnitId(productionUnitId);
		List<String> goalOeeList = new ArrayList<>();
		ModelMap modelMap = new ModelMap();
		//产生一个月的时间
		DateStringUtil util = new DateStringUtil();
		List<Date> days =util.generateOneMonthDay(util.date2Month(new Date()));
		List<List<String>> oeeList = new ArrayList<>();

		List<String> dayList = new ArrayList<>();
		for(Date day : days) {
			dayList.add(util.date2DayOfMonth(day));
		}
		Date date = new Date();
		//根据生产单元id查询设备站点
		List<DeviceSite> deviceSites = deviceSiteService.queryDeviceSitesByProductionUnitId(productionUnitId);
		//查询所有班次
		List<Classes> classesList = classesService.queryAllClasses();
		for(int i = 0;i<classesList.size();i++) {
			Classes c = classesList.get(i);
			List<String> oees = new ArrayList<>();
			for(Date now : days) {
				goalOeeList.add(format.format(goalOee));

				String nowDay = util.date2DayOfMonth(now);
				String dateDay = util.date2DayOfMonth(date);
				double oee = 0;
				Float processingBeat = workpieceProcessDeviceSiteMappingService.queryProcessingBeatByClassesId(c.getId());
				if(Integer.valueOf(nowDay)<=Integer.valueOf(dateDay)) {
					for(DeviceSite ds : deviceSites) {
						oee = queryOee(c, ds, processingBeat, date);
					}
				}
				oees.add(format.format(oee*100/deviceSites.size()));
			}
			oeeList.add(oees);
	}
	//求平均值
	List<String> avgs = new ArrayList<>();
	for(int j = 0;j<days.size();j++) {
		double sum = 0;
		for(int i = 0;i<oeeList.size();i++) {
			if(j<oeeList.get(i).size()) {
				try {
					sum += Double.parseDouble(oeeList.get(i).get(j));
				}catch (Exception e) {
					System.err.println(e.getMessage());
					sum += 0;
				}
			}
		}
		avgs.add(format.format(sum/oeeList.size()));
	}

	modelMap.addAttribute("classes",classesList);
	modelMap.addAttribute("goalOeeList",goalOeeList);
	modelMap.addAttribute("avgs",avgs);
	modelMap.addAttribute("oeeList", oeeList);
	modelMap.addAttribute("days", dayList);
	return modelMap;
}
} 
