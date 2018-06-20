package com.digitzones.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.DeviceSite;
import com.digitzones.model.Pager;
import com.digitzones.model.ProcessRecord;
import com.digitzones.model.WorkSheet;
import com.digitzones.model.WorkSheetDetail;
import com.digitzones.service.IDeviceSiteService;
import com.digitzones.service.IProcessRecordService;
import com.digitzones.service.IWorkSheetDetailService;
import com.digitzones.service.IWorkSheetService;
@Controller
@RequestMapping("/processRecord")
public class ProcessRecordController {
	private IProcessRecordService processRecordService;
	private IDeviceSiteService deviceSiteService;
	private IWorkSheetService workSheetService;
	private IWorkSheetDetailService workSheetDetailService;
	@Autowired
	public void setWorkSheetDetailService(IWorkSheetDetailService workSheetDetailService) {
		this.workSheetDetailService = workSheetDetailService;
	}
	@Autowired
	public void setWorkSheetService(IWorkSheetService workSheetService) {
		this.workSheetService = workSheetService;
	}
	@Autowired
	public void setDeviceSiteService(IDeviceSiteService deviceSiteService) {
		this.deviceSiteService = deviceSiteService;
	}
	@Autowired
	public void setProcessRecordService(IProcessRecordService processRecordService) {
		this.processRecordService = processRecordService;
	}
	/**
	 * 根据设备站点id查询加工信息
	 * @param deviceSiteId
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryProcessRecordByDeviceSiteId.do")
	@ResponseBody
	public ModelMap queryProcessRecordByDeviceSiteId(Long deviceSiteId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page){
		Pager<ProcessRecord> pager = processRecordService.queryObjs("from ProcessRecord pr where pr.deviceSiteId=?0 and pr.deleted=?1", page, rows, new Object[] {deviceSiteId,false});
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}
	/**
	 * 添加加工记录
	 * @param processRecord
	 * @return
	 */
	@RequestMapping("/addProcessRecord.do")
	@ResponseBody
	public ModelMap addProcessRecord(ProcessRecord processRecord) {
		ModelMap modelMap = new ModelMap();
		DeviceSite ds = deviceSiteService.queryObjById(processRecord.getDeviceSiteId());
		if(ds==null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "请选择设备站点");
			return modelMap;
		}
		
		ProcessRecord c4code = processRecordService.queryByProperty("serialNo", processRecord.getSerialNo());
		if(c4code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "生产序号已被使用");
		}else  {
			processRecordService.addObj(processRecord);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "添加成功!");
		}
		return modelMap;
	}
	/**
	 * 更新加工记录
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/updateProcessRecord.do")
	@ResponseBody
	public ModelMap updateProcessRecord(ProcessRecord processRecord) {
		ModelMap modelMap = new ModelMap();
		ProcessRecord c4code = processRecordService.queryByProperty("serialNo", processRecord.getSerialNo());
		if(c4code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "生产序号已被使用");
		}else  {
			processRecordService.updateObj(processRecord);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "添加成功!");
		}
		return modelMap;
	}
	/**
	 * 根据id查询加工记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryProcessRecordById.do")
	@ResponseBody
	public ProcessRecord queryProcessRecordById(Long id) {
		return processRecordService.queryObjById(id);
	}
	
	
	/**
	 * 根据id删除加工记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteProcessRecord.do")
	@ResponseBody
	public ModelMap deleteProcessRecord(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		ProcessRecord pr = processRecordService.queryObjById(Long.valueOf(id));
		pr.setDeleted(true);
		processRecordService.updateObj(pr);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 根据设备站点id查询非当前设备站点下的订单
	 * @param deviceSiteId 设备站点id
	 * @return
	 */
	@RequestMapping("/queryOtherWorkSheetByDeviceSiteId.do")
	@ResponseBody
	public List<WorkSheet> queryOtherWorkSheetByDeviceSiteId(Long deviceSiteId){
		return workSheetService.queryOtherWorkSheetByDeviceSiteId(deviceSiteId);
	}
	/**
	 * 根据工单id查找工单 详情
	 * @param workSheetId
	 * @return
	 */
	@RequestMapping("/queryWorkSheetDetailsByWorkSheetId.do")
	@ResponseBody
	public List<WorkSheetDetail> queryWorkSheetDetailsByWorkSheetId(Long workSheetId){
		return workSheetDetailService.queryWorkSheetDetailsByWorkSheetId(workSheetId);
	}
} 
