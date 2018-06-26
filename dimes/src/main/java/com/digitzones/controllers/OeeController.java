package com.digitzones.controllers;
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
import com.digitzones.service.IDeviceSiteService;
import com.digitzones.service.ILostTimeRecordService;
import com.digitzones.service.IProcessRecordService;
import com.digitzones.service.IProductionUnitService;
import com.digitzones.service.IWorkpieceProcessDeviceSiteMappingService;
@Controller
@RequestMapping("/oee")
public class OeeController {
	private IProductionUnitService productionUnitService;
	private IDeviceSiteService deviceSiteService;
	private IProcessRecordService processRecordService;
	private IWorkpieceProcessDeviceSiteMappingService workpieceProcessDeviceSiteMappingService;
	private IClassesService classesService;
	private ILostTimeRecordService lostTimeRecordService;
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
		modelMap.put("preMonthOee", preMonthOee()*100);
		modelMap.put("currentMonthOee",currentMonthOee()*100);
		modelMap.put("currentDayOee",currentDayOee()*100);
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
			//查询加工信息中的工序，工件，设备站点id,NG数量
			List<Long[]> idList = processRecordService.queryCountByDeviceSiteIdAndStatus(ds.getId(), Constant.ProcessRecord.NG);
			for(Long[] ids : idList) {
				Classes c = classesService.queryObjById(ids[3]);
				//根据工序,工件，设备站点查找标准节拍
				WorkpieceProcessDeviceSiteMapping wpdsm = workpieceProcessDeviceSiteMappingService.queryByWorkPieceIdAndProcessIdAndDeviceSiteId(ids[0], ids[1], ids[2]);
				Float processingBeat = wpdsm.getProcessingBeat();
				//oee公式:（总加工时间-损时时间-NG件数*标准节拍）/总加工时间 
				//总加工时间，公式：当前时间-班次的开始时间-损时时间-计划停机时间
				Date now = new Date();
				//查询损时时间(包括计划停机时间)
				Double lostTime = lostTimeRecordService.queryLostTimeByTime(c.getStartTime(), now); 
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(now);
				int totalMinutes = calendar.get(Calendar.HOUR)*60 + calendar.get(Calendar.MINUTE);
				double oee = (totalMinutes-lostTime-ids[4]*processingBeat)/totalMinutes;
				sumOee += oee;
			}
		}
		return sumOee;
	}
	/**
	 * 查询每个生产单元的OEE值
	 * @param productionUnits
	 * @return
	 */
	private List<Double> queryOee4PerProductionUnit(List<ProductionUnit> productionUnits){
		List<Double> values = new ArrayList<>();
		for(ProductionUnit pu : productionUnits) {
			double sumOee = 0;
			//根据生产单元查询设备站点
			List<DeviceSite> deviceSites = deviceSiteService.queryDeviceSitesByProductionUnitId(pu.getId());
			//根据设备站点查询加工信息
			for(DeviceSite ds : deviceSites) {
				//查询加工信息中的工序，工件，设备站点id,NG数量
				List<Long[]> idList = processRecordService.queryCountByDeviceSiteIdAndStatus(ds.getId(), Constant.ProcessRecord.NG);
				for(Long[] ids : idList) {
					Classes c = classesService.queryObjById(ids[3]);
					//根据工序,工件，设备站点查找标准节拍
					WorkpieceProcessDeviceSiteMapping wpdsm = workpieceProcessDeviceSiteMappingService.queryByWorkPieceIdAndProcessIdAndDeviceSiteId(ids[0], ids[1], ids[2]);
					Float processingBeat = wpdsm.getProcessingBeat();
					//oee公式:（总加工时间-损时时间-NG件数*标准节拍）/总加工时间 
					//总加工时间，公式：当前时间-班次的开始时间-损时时间-计划停机时间
					Date now = new Date();
					//查询损时时间(包括计划停机时间)
					Double lostTime = lostTimeRecordService.queryLostTimeByTime(c.getStartTime(), now); 
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(now);
					int totalMinutes = calendar.get(Calendar.HOUR)*60 + calendar.get(Calendar.MINUTE);
					double oee = (totalMinutes-lostTime-ids[4]*processingBeat)/totalMinutes;
					sumOee += oee;
				}
			}

			values.add(sumOee*100);
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
		for(DeviceSite ds : deviceSites) {
			//查询加工信息中的工序，工件，设备站点id,NG数量
			List<Long[]> idList = processRecordService.queryPreMonthDeviceSiteIdAndStatus(ds.getId(), Constant.ProcessRecord.NG);
			for(Long[] ids : idList) {
				Classes c = classesService.queryObjById(ids[3]);
				//根据工序,工件，设备站点查找标准节拍
				WorkpieceProcessDeviceSiteMapping wpdsm = workpieceProcessDeviceSiteMappingService.queryByWorkPieceIdAndProcessIdAndDeviceSiteId(ids[0], ids[1], ids[2]);
				Float processingBeat = wpdsm.getProcessingBeat();
				//oee公式:（总加工时间-损时时间-NG件数*标准节拍）/总加工时间 
				//总加工时间，公式：当前时间-班次的开始时间-损时时间-计划停机时间
				Date now = new Date();
				//查询损时时间(包括计划停机时间)
				Double lostTime = lostTimeRecordService.queryLostTimeByTime(c.getStartTime(), now); 
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(now);
				int totalMinutes = calendar.get(Calendar.HOUR)*60 + calendar.get(Calendar.MINUTE);
				double oee = (totalMinutes-lostTime-ids[4]*processingBeat)/totalMinutes;
				sumOee += oee;
			}
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
			//查询加工信息中的工序，工件，设备站点id,NG数量
			List<Long[]> idList = processRecordService.queryCurrentMonthDeviceSiteIdAndStatus(ds.getId(), Constant.ProcessRecord.NG);
			for(Long[] ids : idList) {
				Classes c = classesService.queryObjById(ids[3]);
				//根据工序,工件，设备站点查找标准节拍
				WorkpieceProcessDeviceSiteMapping wpdsm = workpieceProcessDeviceSiteMappingService.queryByWorkPieceIdAndProcessIdAndDeviceSiteId(ids[0], ids[1], ids[2]);
				Float processingBeat = wpdsm.getProcessingBeat();
				//oee公式:（总加工时间-损时时间-NG件数*标准节拍）/总加工时间 
				//总加工时间，公式：当前时间-班次的开始时间-损时时间-计划停机时间
				Date now = new Date();
				//查询损时时间(包括计划停机时间)
				Double lostTime = lostTimeRecordService.queryLostTimeByTime(c.getStartTime(), now); 
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(now);
				int totalMinutes = calendar.get(Calendar.HOUR)*60 + calendar.get(Calendar.MINUTE);
				double oee = (totalMinutes-lostTime-ids[4]*processingBeat)/totalMinutes;
				sumOee += oee;
			}
		}
		return sumOee;
	}
} 
