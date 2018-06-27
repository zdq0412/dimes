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

import com.digitzones.constants.Constant;
import com.digitzones.model.Classes;
import com.digitzones.model.DeviceSite;
import com.digitzones.model.ProductionUnit;
import com.digitzones.model.WorkpieceProcessDeviceSiteMapping;
import com.digitzones.service.IClassesService;
import com.digitzones.service.IDeviceService;
import com.digitzones.service.IDeviceSiteService;
import com.digitzones.service.ILostTimeRecordService;
import com.digitzones.service.IProcessRecordService;
import com.digitzones.service.IProductionUnitService;
import com.digitzones.service.IWorkpieceProcessDeviceSiteMappingService;
import com.digitzones.util.DateStringUtil;
@Controller
@RequestMapping("/oee")
public class OeeController {
	private DecimalFormat format = new DecimalFormat("#.00");
	private IProductionUnitService productionUnitService;
	private IDeviceSiteService deviceSiteService;
	private IProcessRecordService processRecordService;
	private IWorkpieceProcessDeviceSiteMappingService workpieceProcessDeviceSiteMappingService;
	private IClassesService classesService;
	private ILostTimeRecordService lostTimeRecordService;
	private IDeviceService deviceService;
	@Autowired
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
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
	public void setProcessRecordService(IProcessRecordService processRecordService) {
		this.processRecordService = processRecordService;
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
		modelMap.put("preMonthOee", format.format(preMonthOee()*100));
		modelMap.put("currentMonthOee",format.format(currentMonthOee()*100));
		modelMap.put("currentDayOee",format.format(currentDayOee()*100));
		return modelMap;
	}
	/**
	 * 当天OEE值
	 * @return
	 */
	private Double currentDayOee() {
		double sumOee = 0;
		//根据生产单元查询设备站点
		List<DeviceSite> deviceSites = deviceSiteService.queryAllDeviceSites();
		//根据设备站点查询加工信息
		for(DeviceSite ds : deviceSites) {
			//查询当前班次
			Classes c = classesService.queryCurrentClasses();
			//查询加工信息中的工序，工件，设备站点id,NG数量
			List<Long[]> idList = processRecordService.queryCountByDeviceSiteIdAndStatus(ds.getId(), Constant.ProcessRecord.NG);
			sumOee += queryOee(c, ds,idList);
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
			//根据设备站点查询加工信息
			for(DeviceSite ds : deviceSites) {
				//查询当前班次
				Classes c = classesService.queryCurrentClasses();
				//查询加工信息中的工序，工件，设备站点id,NG数量
				List<Long[]> idList = processRecordService.queryCountByDeviceSiteIdAndStatus(ds.getId(), Constant.ProcessRecord.NG);
				sumOee += queryOee(c, ds,idList);
			}

			values.add(format.format(sumOee*100));
		}
		return values;
	}
	/**
	 * 查询上个月的OEE值
	 * @return
	 */
	private Double preMonthOee() {
		double sumOee = 0;
		//根据生产单元查询设备站点
		List<DeviceSite> deviceSites = deviceSiteService.queryAllDeviceSites();
		//根据设备站点查询加工信息
		//根据设备站点查询加工信息
		for(DeviceSite ds : deviceSites) {
			//查询当前班次
			Classes c = classesService.queryCurrentClasses();
			//查询加工信息中的工序，工件，设备站点id,NG数量
			List<Long[]> idList = processRecordService.queryPreMonthDeviceSiteIdAndStatus(ds.getId(), Constant.ProcessRecord.NG);
			sumOee += queryOee(c, ds,idList);
		}
		return sumOee;
	}

	/**
	 * oee算法
	 * @param c
	 * @param ds
	 * @return
	 */
	private double queryOee(Classes c , DeviceSite ds,List<Long[]> idList) {
		Date now = new Date();
		double sumOee = 0;
		//查询损时时间(包括计划停机时间)
		double lostTime = lostTimeRecordService.queryLostTime(c,ds.getId(),new Date());
		//查询计划停机时间
		double planHaltTime = lostTimeRecordService.queryPlanHaltTime(c, ds.getId(),new Date());

		if(idList != null && idList.size()>0) {
			for(Long[] ids : idList) {
				//根据工序,工件，设备站点查找标准节拍
				WorkpieceProcessDeviceSiteMapping wpdsm = workpieceProcessDeviceSiteMappingService.queryByWorkPieceIdAndProcessIdAndDeviceSiteId(ids[0], ids[1], ids[2]);
				Float processingBeat = wpdsm.getProcessingBeat();
				//oee公式:（总加工时间-损时时间-NG件数*标准节拍）/总加工时间 
				//总加工时间，公式：总加工时间=当前时间-班次的开始时间 -计划停机时间
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(now);
				int totalMinutes = calendar.get(Calendar.HOUR_OF_DAY)*60 + calendar.get(Calendar.MINUTE);
				Calendar cal = Calendar.getInstance();
				cal.setTime(c.getStartTime());
				//查询计划停机时间
				int classesBeginMinutes = cal.get(Calendar.HOUR_OF_DAY)*60 + cal.get(Calendar.MINUTE);
				//总加工时间
				double sumMinutes = totalMinutes - classesBeginMinutes - planHaltTime;
				double oee = 0;
				if(totalMinutes>classesBeginMinutes) {
					oee = (sumMinutes-lostTime-ids[3]*processingBeat)/sumMinutes;
					//
				}else {
					oee = (24+sumMinutes-lostTime-ids[3]*processingBeat)/(24*60+sumMinutes);
				}
				sumOee += oee;
			}
		}else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			int totalMinutes = calendar.get(Calendar.HOUR_OF_DAY)*60 + calendar.get(Calendar.MINUTE);
			Calendar cal = Calendar.getInstance();
			cal.setTime(c.getStartTime());
			int classesBeginMinutes = cal.get(Calendar.HOUR_OF_DAY)*60 + cal.get(Calendar.MINUTE);
			//总加工时间
			double sumMinutes = totalMinutes - classesBeginMinutes - planHaltTime;
			double oee = 0;
			if(totalMinutes>classesBeginMinutes) {
				oee = (sumMinutes-lostTime)/sumMinutes;
				//
			}else {
				oee = (24+sumMinutes-lostTime)/(24*60+sumMinutes);
			}
			sumOee += oee;
		}

		return sumOee;
	}

	/**
	 * 查询上个月的OEE值
	 * @return
	 */
	private Double currentMonthOee() {
		double sumOee = 0;
		//根据生产单元查询设备站点
		List<DeviceSite> deviceSites = deviceSiteService.queryAllDeviceSites();
		//根据设备站点查询加工信息
		for(DeviceSite ds : deviceSites) {
			//查询当前班次
			Classes c = classesService.queryCurrentClasses();
			//查询加工信息中的工序，工件，设备站点id,NG数量
			List<Long[]> idList = processRecordService.queryCurrentMonthDeviceSiteIdAndStatus(ds.getId(), Constant.ProcessRecord.NG);
			sumOee += queryOee(c, ds,idList);
		}
		return sumOee;
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
		double goalOee = deviceService.queryOeeByProductionUnitId(productionUnitId);
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
				if(Integer.valueOf(nowDay)<=Integer.valueOf(dateDay)) {
					double sumOee = 0;
					for(DeviceSite ds : deviceSites) {
						List<Long[]> idList = processRecordService.queryByDay(ds.getId(), Constant.ProcessRecord.NG, now);
						//查询损时时间(包括计划停机时间)
						double lostTime = lostTimeRecordService.queryLostTime4PerDay(c,ds.getId(),now);
						//查询计划停机时间
						double planHaltTime = lostTimeRecordService.queryPlanHaltTime(c, ds.getId(),now);

						if(idList != null && idList.size()>0) {
							for(Long[] ids : idList) {
								//根据工序,工件，设备站点查找标准节拍
								WorkpieceProcessDeviceSiteMapping wpdsm = workpieceProcessDeviceSiteMappingService.queryByWorkPieceIdAndProcessIdAndDeviceSiteId(ids[0], ids[1], ids[2]);
								Float processingBeat = wpdsm.getProcessingBeat();
								//oee公式:（总加工时间-损时时间-NG件数*标准节拍）/总加工时间 
								//总加工时间，公式：总加工时间=当前时间-班次的开始时间 -计划停机时间
								//开始时间
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(c.getStartTime());
								//结束时间
								Calendar cal = Calendar.getInstance();
								cal.setTime(c.getEndTime());
								long totalMinutes = 0;
								//班次开始时间大于结束时间
								if(calendar.get(Calendar.HOUR_OF_DAY)<cal.get(Calendar.HOUR_OF_DAY)) {
									totalMinutes = cal.get(Calendar.HOUR_OF_DAY)*60+cal.get(Calendar.MINUTE)-calendar.get(Calendar.HOUR_OF_DAY)*60-calendar.get(Calendar.MINUTE);
								}else {
									totalMinutes = 24*60+(calendar.get(Calendar.HOUR_OF_DAY)*60+calendar.get(Calendar.MINUTE))-(cal.get(Calendar.HOUR_OF_DAY)*60-cal.get(Calendar.MINUTE));
								}
								double sumMinutes = totalMinutes- planHaltTime;
								double oee = (sumMinutes-lostTime-ids[3]*processingBeat)/sumMinutes;
								sumOee += oee;
							}
						}else {
							//开始时间
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(c.getStartTime());
							//结束时间
							Calendar cal = Calendar.getInstance();
							cal.setTime(c.getEndTime());
							long totalMinutes = 0;
							//班次开始时间大于结束时间
							if(calendar.get(Calendar.HOUR_OF_DAY)<cal.get(Calendar.HOUR_OF_DAY)) {
								totalMinutes = cal.get(Calendar.HOUR_OF_DAY)*60+cal.get(Calendar.MINUTE)-calendar.get(Calendar.HOUR_OF_DAY)*60-calendar.get(Calendar.MINUTE);
							}else {
								totalMinutes = 24*60+(calendar.get(Calendar.HOUR_OF_DAY)*60+calendar.get(Calendar.MINUTE))-(cal.get(Calendar.HOUR_OF_DAY)*60-cal.get(Calendar.MINUTE));
							}
							//总加工时间
							double sumMinutes = totalMinutes- planHaltTime;
							double oee  = (sumMinutes-lostTime)/sumMinutes;
							sumOee += oee;
						}
					}
					oees.add(format.format(sumOee*100/deviceSites.size()));
				}
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
