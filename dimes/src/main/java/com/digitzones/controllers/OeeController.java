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
import com.digitzones.service.IOeeService;
import com.digitzones.service.IProductionUnitService;
import com.digitzones.service.IWorkpieceService;
import com.digitzones.util.DateStringUtil;
@Controller
@RequestMapping("/oee")
public class OeeController {
	private DecimalFormat format = new DecimalFormat("#.00");
	private IProductionUnitService productionUnitService;
	private IDeviceSiteService deviceSiteService;
	private IClassesService classesService;
	private IWorkpieceService workpieceService;
	private ILostTimeRecordService lostTimeRecordService;
	private IOeeService oeeService;
	@Autowired
	public void setWorkpieceService(IWorkpieceService workpieceService) {
		this.workpieceService = workpieceService;
	}
	@Autowired
	public void setOeeService(IOeeService oeeService) {
		this.oeeService = oeeService;
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
		//查询当前班次
		Classes c = classesService.queryCurrentClasses();
		//根据设备站点查询加工信息
		for(DeviceSite ds : deviceSites) {
			//查询ng记录中的工序id ，工件code，设备站点id,NG数量
			List<Object[]> idList = oeeService.queryNGInfo4CurrentDay(new Date(), ds.getId());
			sumOee += queryOee(c, ds,idList);
		}
		return sumOee/deviceSites.size();
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
			//查询当前班次
			Classes c = classesService.queryCurrentClasses();
			//根据设备站点查询加工信息
			for(DeviceSite ds : deviceSites) {
				//查询不合格品信息中的工序，工件，设备站点id,NG数量
				List<Object[]> idList = oeeService.queryNGInfo(new Date(), ds.getId(), c);
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
		//查询当前班次
		Classes c = classesService.queryCurrentClasses();
		//根据设备站点查询加工信息
		for(DeviceSite ds : deviceSites) {
			//查询加工信息中的工序，工件，设备站点id,NG数量
			List<Object[]> idList = oeeService.queryNGInfo4PreMonth(ds.getId());
			sumOee += queryOee(c, ds,idList);
		}
		if(deviceSites.size()>0) {
			return sumOee/deviceSites.size();
		}else {
			return 0.0;
		}
	}
	/**
	 * oee算法
	 * @param c
	 * @param ds
	 * @return
	 */
	private double queryOee(Classes c , DeviceSite ds,List<Object[]> idList) {
		Date now = new Date();
		double oee = 0;
		//查询损时时间(包括计划停机时间)
		double lostTime = lostTimeRecordService.queryLostTime(c,ds.getId(),new Date());
		//查询计划停机时间
		double planHaltTime = lostTimeRecordService.queryPlanHaltTime(c, ds.getId(),new Date());

		if(idList != null && idList.size()>0) {
			int sumNgAndProcessingBeat = 0;
			for(Object[] ids : idList) {
				Long workpieceId = workpieceService.queryByProperty("code", ids[3]+"").getId();
				//根据工序,工件，设备站点查找标准节拍
				Float processingBeat = oeeService.queryProcessingBeatByWorkpieceIdAndProcessIdAndDeviceSiteId(workpieceId, Long.parseLong(ids[2]==null?"0":ids[2]+""), Long.parseLong(ids[0]==null?"0":ids[0]+""));
				if(processingBeat==null) {
					processingBeat = 0f;
				}
				sumNgAndProcessingBeat+=Long.parseLong(ids[4]==null?"0":ids[4].toString())*processingBeat;
			}
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
			if(totalMinutes>classesBeginMinutes) {
				oee = (sumMinutes-lostTime-sumNgAndProcessingBeat)/sumMinutes;
				//
			}else {
				oee = (24+sumMinutes-lostTime-sumNgAndProcessingBeat)/(24*60+sumMinutes);
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
			if(totalMinutes>classesBeginMinutes) {
				oee = (sumMinutes-lostTime)/sumMinutes;
				//
			}else {
				oee = (24+sumMinutes-lostTime)/(24*60+sumMinutes);
			}
		}
		return oee;
	}
	/**
	 * 查询上个月的OEE值
	 * @return
	 */
	private Double currentMonthOee() {
		double sumOee = 0;
		//根据生产单元查询设备站点
		List<DeviceSite> deviceSites = deviceSiteService.queryAllDeviceSites();
		//查询当前班次
		Classes c = classesService.queryCurrentClasses();
		//根据设备站点查询加工信息
		for(DeviceSite ds : deviceSites) {
			//查询不合格品信息中的工序，工件，设备站点id,NG数量
			List<Object[]> idList = oeeService.queryNGInfo4CurrentMonth(ds.getId());
			sumOee += queryOee(c, ds,idList);
		}
		return sumOee/deviceSites.size();
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
				if(Integer.valueOf(nowDay)<=Integer.valueOf(dateDay)) {
					double oee = 0;
					for(DeviceSite ds : deviceSites) {
						List<Object[]> idList = oeeService.queryNGInfo4CurrentDay(now, ds.getId());
						//查询损时时间(包括计划停机时间)
						double lostTime = oeeService.queryLostTimeByDeviceSiteId(now, ds.getId(), c);
						//查询计划停机时间
						double planHaltTime = lostTimeRecordService.queryPlanHaltTime(c, ds.getId(),now);

						if(idList != null && idList.size()>0) {
							int sumNgAndProcessingBeat = 0;
							for(Object[] ids : idList) {
								Long workpieceId = workpieceService.queryByProperty("code", ids[3]+"").getId();
								//根据工序,工件，设备站点查找标准节拍
								Float processingBeat = oeeService.queryProcessingBeatByWorkpieceIdAndProcessIdAndDeviceSiteId(workpieceId, Long.parseLong(ids[2]==null?"0":ids[2]+""), Long.parseLong(ids[0]==null?"0":ids[0]+""));
								if(processingBeat==null) {
									processingBeat = 0f;
								}
								sumNgAndProcessingBeat+=Long.parseLong(ids[4]==null?"0":ids[4].toString())*processingBeat;
							}
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
							oee = (sumMinutes-lostTime-sumNgAndProcessingBeat)/sumMinutes;
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
							oee  = (sumMinutes-lostTime)/sumMinutes;
						}
					}
					oees.add(format.format(oee*100));
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

	/**
	 * 查询运行率top
	 * @return
	 */
	@RequestMapping("/queryOperationRateTop.do")
	@ResponseBody
	public ModelMap queryOperationRateTop() {
		ModelMap modelMap = new ModelMap();
		String[] placingList = {
				"第一名",
				"第二名",
				"第三名",
				"第四名",
				"第五名",
				"第六名",
				"第七名",
				"第八名",
				"第九名",
				"第十名",
		};
		List<String> placing = new ArrayList<>();
		List<String> deviceSiteNames = new ArrayList<>();
		List<Object[]> values = new ArrayList<>();
		Date today = new Date();
		//根据生产单元查询设备站点
		List<DeviceSite> deviceSites = deviceSiteService.queryAllDeviceSites();
		//查询当前班次
		Classes c = classesService.queryCurrentClasses();
		//根据设备站点查询加工信息
		for(DeviceSite ds : deviceSites) {
			//查询加工信息中的工序，工件，设备站点id,NG数量
			List<Object[]> idList = oeeService.queryNGInfo(today, ds.getId(), c);
			double oee = queryOee(c, ds,idList);
			Object[] o = new Object[2];
			o[0] = ds.getName();
			o[1] = oee;
			values.add(o);
		} 
		//排序
		Object[] t = null;
		for (int i = 0; i < values.size() - 1; i++) {
			for (int j = 0; j < values.size() - 1 - i; j++) {
				Object[] v1 = values.get(j);
				Object[] v2 = values.get(j+1);
				if ((Double)v1[1] <(Double)v2[1]) {
					t = v1;
					values.set(j, v2);
					values.set(j+1, t);
				}
			}
		}

		for(int i=0;i<values.size()&&i<10;i++) {
			placing.add(placingList[i]);
			deviceSiteNames.add(values.get(i)[0]+"");
		}

		modelMap.addAttribute("placing", placing);
		modelMap.addAttribute("deviceSiteNames", deviceSiteNames);
		return modelMap;
	}
} 
