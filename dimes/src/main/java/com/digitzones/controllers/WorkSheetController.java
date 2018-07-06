package com.digitzones.controllers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 工件管理控制器
 * @author zdq
 * 2018年6月7日
 */
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.constants.Constant;
import com.digitzones.dto.WorkSheetDetailDto;
import com.digitzones.model.DeviceSite;
import com.digitzones.model.Pager;
import com.digitzones.model.ProcessDeviceSiteMapping;
import com.digitzones.model.Processes;
import com.digitzones.model.WorkSheet;
import com.digitzones.model.WorkSheetDetail;
import com.digitzones.model.WorkSheetDetailParametersRecord;
import com.digitzones.model.Workpiece;
import com.digitzones.service.IDeviceSiteService;
import com.digitzones.service.IProcessesService;
import com.digitzones.service.IWorkSheetDetailParametersRecordService;
import com.digitzones.service.IWorkSheetDetailService;
import com.digitzones.service.IWorkSheetService;
import com.digitzones.vo.WorkSheetDetailVO;
import com.digitzones.vo.WorkSheetVO;
@Controller
@RequestMapping("/workSheet")
public class WorkSheetController {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private IWorkSheetService workSheetService;
	private IWorkSheetDetailService workSheetDetailService;
	private IDeviceSiteService deviceSiteService;
	private IProcessesService processService;
	private IWorkSheetDetailParametersRecordService parameterRecordService;
	@Autowired
	public void setParameterRecordService(IWorkSheetDetailParametersRecordService parameterRecordService) {
		this.parameterRecordService = parameterRecordService;
	}
	@Autowired
	public void setDeviceSiteService(IDeviceSiteService deviceSiteService) {
		this.deviceSiteService = deviceSiteService;
	}
	@Autowired
	public void setProcessService(IProcessesService processService) {
		this.processService = processService;
	}
	@Autowired
	public void setWorkSheetDetailService(IWorkSheetDetailService workSheetDetailService) {
		this.workSheetDetailService = workSheetDetailService;
	}
	@Autowired
	public void setWorkSheetService(IWorkSheetService workSheetService) {
		this.workSheetService = workSheetService;
	}
	/**
	 * 根据生产单元id查询工单信息
	 * @return
	 */
	@RequestMapping("/queryWorkSheetsByProductionUnitId.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ModelMap queryWorkSheetsByProductionUnitId(Long productionUnitId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<Workpiece> pager = workSheetService.queryObjs("select d from WorkSheet d where d.productionUnitId=?0 and deleted=?1", page, rows, new Object[] {productionUnitId,false});
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pager.getData());
		mm.addAttribute("total", pager.getTotalCount());
		return mm;
	}
	/**
	 * 根据工件id查询工单详情
	 * @return
	 */
	@RequestMapping("/queryWorkSheetDetailsInMemoryByWorkpieceId.do")
	@ResponseBody
	public List<WorkSheetDetail> queryWorkSheetDetailsInMemoryByWorkpieceId(Long workpieceId){
		if(!Constant.WORKPIECEID.equals(workpieceId+"")) {
			Constant.WORKPIECEID = workpieceId+"";
			Constant.workSheetDetail.clear();
			workSheetDetailService.buildWorkSheetDetailListInMemoryByWorkpieceId(workpieceId);
		}
		return Constant.workSheetDetail;
	}
	/**
	 * 根据工序id查询该工序内的其他设备信息
	 * @param processId
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping("/queryOtherDeviceSitesByProcessId.do")
	@ResponseBody
	public ModelMap queryOtherDeviceSitesByProcessId(Long processId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page){
		ModelMap modelMap = new ModelMap();
		Pager<ProcessDeviceSiteMapping> pager = workSheetDetailService.queryOtherDeviceSitesByProcessId(processId, page, rows);
		modelMap.addAttribute("rows",pager.getData());
		modelMap.addAttribute("total", pager.getTotalCount());
		return modelMap;
	}
	/**
	 * 为工单中的工序添加设备站点
	 * @param processesId
	 * @param deviceSiteIds
	 * @return
	 */
	@RequestMapping("/addDeviceSite4Processes.do")
	@ResponseBody
	public ModelMap addDeviceSite4Processes(Long processesId,String deviceSiteIds) {
		ModelMap modelMap = new ModelMap();
		if(deviceSiteIds!=null) {
			if(deviceSiteIds.contains("[")) {
				deviceSiteIds = deviceSiteIds.replace("[", "");
			}
			if(deviceSiteIds.contains("]")) {
				deviceSiteIds = deviceSiteIds.replace("]", "");
			}

			WorkSheetDetail d = null;
			//删除工序中设备站点为空的项
			for(WorkSheetDetail detail : Constant.workSheetDetail) {
				if(detail.getProcessId().equals(processesId) && detail.getDeviceSiteId()==null) {
					d = detail;
				}
			}

			if(d != null) {
				Constant.workSheetDetail.remove(d);
			}

			long detailId = 1;

			Processes c = processService.queryObjById(processesId);
			String[] idArray = deviceSiteIds.split(",");
			for(int i = 0;i<idArray.length;i++) {
				DeviceSite deviceSite = deviceSiteService.queryObjById(Long.valueOf(idArray[i]));

				WorkSheetDetail detail = new WorkSheetDetail();
				detail.setDeviceCode(deviceSite.getDevice().getCode());
				detail.setDeviceName(deviceSite.getDevice().getName());
				detail.setDeviceSiteId(deviceSite.getId());
				detail.setDeviceSiteCode(deviceSite.getCode());
				detail.setDeviceSiteName(deviceSite.getName());
				detail.setProcessId(c.getId());
				detail.setProcessCode(c.getCode());
				detail.setParameterSource(deviceSite.getDevice().getParameterValueType());
				detail.setProcessName(c.getName());
				detail.setId(detailId++);

				detail.setParameterRecords(d.getParameterRecords());
				Constant.workSheetDetail.add(detail);

				modelMap.addAttribute("success",true);
				modelMap.addAttribute("msg","操作完成!");
			}
		}else {
			modelMap.addAttribute("success",false);
			modelMap.addAttribute("msg","操作失败!");
		}
		return modelMap;
	}
	/**
	 * 更新工单
	 * @param workSheet
	 * @return
	 */
	@RequestMapping("/updateWorkSheet.do")
	@ResponseBody
	public ModelMap updateWorkSheet(String details,String workSheet) {
		ModelMap modelMap = new ModelMap();
		
		System.out.println(details);
		System.out.println("-----------------");
		System.out.println(workSheet);
		
		
		modelMap.addAttribute("success",true);
		modelMap.addAttribute("msg","操作完成!");
		return modelMap;
	}
	/**
	 * 更新工单详情
	 * @param workSheetDetail
	 * @return
	 */
	@RequestMapping("/updateWorkSheetDetail.do")
	@ResponseBody
	public ModelMap updateWorkSheetDetail(WorkSheetDetailDto workSheetDetailDto) {
		ModelMap modelMap = new ModelMap();
		WorkSheetDetail detail = workSheetDetailService.queryObjById(workSheetDetailDto.getId());
		detail.setRepairCount(workSheetDetailDto.getRepairCount());
		detail.setReportCount(workSheetDetailDto.getReportCount());
		detail.setQualifiedCount(workSheetDetailDto.getQualifiedCount());
		detail.setUnqualifiedCount(workSheetDetailDto.getUnqualifiedCount());
		detail.setScrapCount(workSheetDetailDto.getScrapCount());
		detail.setStatus(workSheetDetailDto.getStatus());
		detail.setNote(workSheetDetailDto.getNote());
		detail.setCompleteCount(workSheetDetailDto.getCompleteCount());
		detail.setProductionCount(workSheetDetailDto.getProductionCount());

		workSheetDetailService.updateObj(detail);
		modelMap.addAttribute("success",true);
		modelMap.addAttribute("msg","操作完成!");
		return modelMap;
	}
	/**
	 * 根据工单详情查找工单参数记录
	 * @param workSheetDetailId
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping("/queryParameterRecordsByWorkSheetDetailId.do")
	@ResponseBody
	public ModelMap queryParameterRecordsByWorkSheetDetailId(Long workSheetDetailId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		String hql = "from WorkSheetDetailParametersRecord wsdpr where wsdpr.workSheetDetail.id=?0";
		@SuppressWarnings("unchecked")
		Pager<WorkSheetDetailParametersRecord> pager = parameterRecordService.queryObjs(hql, page, rows, new Object[] {workSheetDetailId});
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}

	/**
	 * 根据工单id查询工单详情
	 * @param workSheetId
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping("/queryWorkSheetDetailByWorkSheetId.do")
	@ResponseBody
	public ModelMap queryWorkSheetDetailByWorkSheetId(Long workSheetId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page){
		ModelMap modelMap = new ModelMap();
		String hql = "from WorkSheetDetail wsd where wsd.workSheet.id=?0";
		@SuppressWarnings("unchecked")
		Pager<WorkSheetDetail> pager = workSheetDetailService.queryObjs(hql, page, rows, new Object[] {workSheetId});
		List<WorkSheetDetailVO> vos = new ArrayList<>();
		for(WorkSheetDetail detail : pager.getData()) {
			vos.add(workSheetDetail2VO(detail));
		}
		modelMap.addAttribute("rows",vos);
		modelMap.addAttribute("total", pager.getTotalCount());
		return modelMap;
	}


	private WorkSheetDetailVO workSheetDetail2VO(WorkSheetDetail detail) {
		if(detail == null) {
			return null;
		}
		WorkSheetDetailVO vo = new WorkSheetDetailVO();
		vo.setId(detail.getId());
		vo.setCompleteCount(detail.getCompleteCount());
		vo.setRepairCount(detail.getRepairCount());
		vo.setReportCount(detail.getReportCount());
		vo.setQualifiedCount(detail.getQualifiedCount());
		vo.setUnqualifiedCount(detail.getUnqualifiedCount());
		vo.setScrapCount(detail.getScrapCount());
		vo.setStatus(detail.getStatus());
		vo.setNote(detail.getNote());
		vo.setCompleteCount(detail.getCompleteCount());
		vo.setProductionCount(detail.getProductionCount());
		if(detail.getDeleted()!=null) {
			vo.setDeleted(detail.getDeleted()?"Y":"N");
		}
		if(detail.getWorkSheet()!=null) {
			vo.setWorkSheetId(detail.getWorkSheet().getId());
			vo.setWorkSheetNo(detail.getWorkSheet().getNo());
		}

		vo.setDeviceCode(detail.getDeviceCode());
		vo.setDeviceName(detail.getDeviceName());
		vo.setDeviceSiteCode(detail.getDeviceSiteCode());
		vo.setDeviceSiteId(detail.getDeviceSiteId());
		vo.setDeviceSiteName(detail.getDeviceSiteName());
		vo.setProcessId(detail.getProcessId());
		vo.setProcessCode(detail.getProcessCode());
		vo.setProcessName(detail.getProcessName());
		return vo;
	}
	/**
	 * 根据id查询工单
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryWorkSheetById.do")
	@ResponseBody
	public WorkSheetVO queryWorkSheetById(Long id) {
		return model2VO(workSheetService.queryObjById(id));
	}

	private WorkSheetVO model2VO(WorkSheet workSheet) {
		if(workSheet == null) {
			return null;
		}

		WorkSheetVO vo = new WorkSheetVO();
		vo.setId(workSheet.getId());
		vo.setCustomerGraphNumber(workSheet.getCustomerGraphNumber());
		vo.setGraphNumber(workSheet.getGraphNumber());
		vo.setNo(workSheet.getNo());
		vo.setBatchNumber(workSheet.getBatchNumber());
		vo.setNote(workSheet.getNote());
		if(workSheet.getCompleteTime()!=null) {
			vo.setCompleteTime(sdf.format(workSheet.getCompleteTime()));
		}
		if(workSheet.getMakeDocumentDate()!=null) {
			vo.setMakeDocumentDate(sdf.format(workSheet.getMakeDocumentDate()));
		}
		if(workSheet.getManufactureDate()!=null) {
			vo.setManufactureDate(sdf.format(workSheet.getManufactureDate()));
		}
		vo.setDeleted(workSheet.getDeleted()?"Y":"N");
		vo.setWorkPieceName(workSheet.getWorkPieceName());
		vo.setWorkPieceCode(workSheet.getWorkPieceCode());
		vo.setStatus(workSheet.getStatus());
		vo.setStoveNumber(workSheet.getStoveNumber());
		vo.setProductCount(workSheet.getProductCount());
		vo.setProductionUnitCode(workSheet.getProductionUnitCode());
		vo.setProductionUnitName(workSheet.getProductionUnitName());
		vo.setProductionUnitId(workSheet.getProductionUnitId());
		vo.setUnitType(workSheet.getUnitType());
		vo.setVersion(workSheet.getVersion());
		vo.setDocumentMaker(workSheet.getDocumentMaker());
		return vo;
	}
	/**
	 * 添加工单
	 * @param workSheet
	 * @return
	 */
	@RequestMapping("/addWorkSheet.do")
	@ResponseBody
	public ModelMap addWorkSheet(WorkSheet workSheet) {
		ModelMap modelMap = new ModelMap();
		workSheet.setMakeDocumentDate(new Date());
		workSheetService.addWorkSheet(workSheet);
		Constant.workSheetDetail.clear();
		modelMap.addAttribute("success",true);
		modelMap.addAttribute("msg","添加成功!");
		return modelMap;
	}
	/**
	 * 删除工单
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteWorkSheet.do")
	@ResponseBody
	public ModelMap deleteWorkSheet(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		WorkSheet workSheet = workSheetService.queryObjById(Long.valueOf(id));
		workSheet.setDeleted(true);
		workSheetService.updateObj(workSheet);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 根据工序id查找工单详情参数
	 * @param processId
	 * @return
	 */
	@RequestMapping("/queryParametersByParameterRecordId.do")
	@ResponseBody
	public Set<WorkSheetDetailParametersRecord> queryParametersByParameterRecordId(Long processId,Long deviceSiteId){
		for(WorkSheetDetail detail : Constant.workSheetDetail) {
			if(detail.getProcessId().equals(processId) && detail.getDeviceSiteId()==deviceSiteId) {
				return detail.getParameterRecords();
			}
		}
		return new HashSet<>();
	}
	@RequestMapping("/updateParameterRecord.do")
	@ResponseBody
	public void updateParameterRecord(Long id,Float upLine,Float lowLine,Float standardValue) {
		for(WorkSheetDetail detail : Constant.workSheetDetail) {
			Set<WorkSheetDetailParametersRecord> recordSet = detail.getParameterRecords();
			if(recordSet!=null) {
				for(WorkSheetDetailParametersRecord pr : recordSet) {
					if(pr.getId().equals(id)) {
						pr.setLowLine(lowLine);
						pr.setUpLine(upLine);
						pr.setStandardValue(standardValue);
					}
				}
			}
		}
	}
	@RequestMapping("/updateWorkSheetDetailParameterRecord.do")
	@ResponseBody
	public void updateWorkSheetDetailParameterRecord(Long id,Float upLine,Float lowLine,Float standardValue) {
		WorkSheetDetailParametersRecord  record = parameterRecordService.queryObjById(id);
		record.setUpLine(upLine);
		record.setLowLine(lowLine);
		record.setStandardValue(standardValue);
		parameterRecordService.updateObj(record);
	}
	/**
	 * 根据id删除工单详情
	 * @param processId
	 * @return
	 */
	@RequestMapping("/deleteWorkSheetDetail.do")
	@ResponseBody
	public ModelMap deleteWorkSheetDetail(String processId,String id,String workSheetId) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}

		if(processId!=null && processId.contains("'")) {
			processId = processId.replace("'", "");
		}
		if(workSheetId!=null && workSheetId.contains("'")) {
			workSheetId = workSheetId.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Long count = workSheetDetailService.queryCountByProcessId(Long.valueOf(processId),Long.valueOf(workSheetId));
		if(count<=1) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "不允许删除该工序下的最后一个站点！");
		}else {
			WorkSheetDetail detail = workSheetDetailService.queryObjById(Long.valueOf(id));
			if(detail==null)	{
				modelMap.addAttribute("success", false);
				modelMap.addAttribute("statusCode", 300);
				modelMap.addAttribute("title", "操作提示");
				modelMap.addAttribute("message", "该记录不存在");
			}else if((detail.getCompleteCount()!=null &&detail.getCompleteCount()!=0)
					|| (detail.getRepairCount()!=null && detail.getRepairCount()!=0)
				|| (detail.getReportCount()!=null && detail.getReportCount()!=0)
				|| (detail.getProductionCount()!=null && detail.getProductionCount()!=0)
				|| (detail.getQualifiedCount()!=null && detail.getQualifiedCount()!=0)
				|| (detail.getUnqualifiedCount()!=null && detail.getUnqualifiedCount()!=0)
				|| (detail.getScrapCount()!=null && detail.getScrapCount()!=0)){
				
				modelMap.addAttribute("success", true);
				modelMap.addAttribute("statusCode", 300);
				modelMap.addAttribute("title", "操作提示");
				modelMap.addAttribute("message", "该工序已被使用，不允许删除!");
			}else {
				workSheetDetailService.deleteObj(Long.valueOf(id));
				modelMap.addAttribute("success", true);
				modelMap.addAttribute("statusCode", 200);
				modelMap.addAttribute("title", "操作提示");
				modelMap.addAttribute("message", "删除成功！");
			}
		}
		return modelMap;
	}
} 
