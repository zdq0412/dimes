package com.digitzones.controllers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Pager;
import com.digitzones.model.Parameters;
import com.digitzones.model.ProcessParameterRecord;
import com.digitzones.model.WorkpieceProcessParameterMapping;
import com.digitzones.service.IParameterService;
import com.digitzones.service.IProcessParameterRecordService;
import com.digitzones.service.IWorkpieceProcessParameterMappingService;
@Controller
@RequestMapping("/processParameterRecord")
public class ProcessParameterRecordController {
	private IProcessParameterRecordService  processParameterRecordService;
	private IParameterService parameterService;
	private IWorkpieceProcessParameterMappingService workpieceProcessParameterMappingService;
	@Autowired
	public void setWorkpieceProcessParameterMappingService(
			IWorkpieceProcessParameterMappingService workpieceProcessParameterMappingService) {
		this.workpieceProcessParameterMappingService = workpieceProcessParameterMappingService;
	}
	@Autowired
	public void setParameterService(IParameterService parameterService) {
		this.parameterService = parameterService;
	}
	@Autowired
	public void setProcessParameterRecordService(IProcessParameterRecordService processParameterRecordService) {
		this.processParameterRecordService = processParameterRecordService;
	}
	/**
	 * 根据加工信息id查询 参数信息
	 * @param processRecordId
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryProcessParameterRecordByProcessRecordId.do")
	@ResponseBody
	public ModelMap queryProcessParameterRecordByProcessRecordId(Long processRecordId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page){
		Pager<ProcessParameterRecord> pager = processParameterRecordService.queryObjs("from ProcessParameterRecord ppr where ppr.processRecord.id=?0 and ppr.deleted=?1", page, rows, new Object[] {processRecordId,false});
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
	@RequestMapping("/addProcessParameterRecord.do")
	@ResponseBody
	public ModelMap addProcessParameterRecord(ProcessParameterRecord processParameterRecord) {
		ModelMap modelMap = new ModelMap();
		processParameterRecordService.addObj(processParameterRecord);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "添加成功!");
		return modelMap;
	}
	/**
	 * 更新加工参数记录
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/updateProcessParameterRecord.do")
	@ResponseBody
	public ModelMap updateProcessParameterRecord(ProcessParameterRecord processParameterRecord) {
		ModelMap modelMap = new ModelMap();
		processParameterRecordService.updateObj(processParameterRecord);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "添加成功!");
		return modelMap;
	}
	/**
	 * 根据id查询加工参数记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryProcessParameterRecordById.do")
	@ResponseBody
	public ProcessParameterRecord queryProcessParameterRecordById(Long id) {
		return processParameterRecordService.queryObjById(id);
	}
	/**
	 * 根据id删除加工参数记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteProcessParameterRecord.do")
	@ResponseBody
	public ModelMap deleteProcessParameterRecord(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		ProcessParameterRecord pr = processParameterRecordService.queryObjById(Long.valueOf(id));
		pr.setDeleted(true);
		processParameterRecordService.updateObj(pr);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 根据工序工件编码查询参数信息
	 * @param workPieceCode
	 * @param processCode
	 * @return
	 */
	@RequestMapping("/queryParametersByWorkpieceCodeAndProcessCode.do")
	@ResponseBody
	public List<WorkpieceProcessParameterMapping> queryParametersByWorkpieceCodeAndProcessCode(String workPieceCode,String processCode){
		List<WorkpieceProcessParameterMapping> list = null;
		if(processCode == null) {
			list = new ArrayList<>();
			List<Parameters> parameters = parameterService.queryAllParameters();
			for(Parameters p : parameters) {
				WorkpieceProcessParameterMapping w = new WorkpieceProcessParameterMapping();
				w.setParameter(p);
				list.add(w);
			}
		}else {
			list = workpieceProcessParameterMappingService.queryByWorkpieceCodeAndProcessCode(workPieceCode, processCode);
		}
		return list;
	}
} 
