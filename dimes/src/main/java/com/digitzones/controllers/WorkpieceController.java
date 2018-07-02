package com.digitzones.controllers;
import java.util.List;

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

import com.digitzones.model.Pager;
import com.digitzones.model.ProcessDeviceSiteMapping;
import com.digitzones.model.Processes;
import com.digitzones.model.ProcessesParametersMapping;
import com.digitzones.model.Workpiece;
import com.digitzones.model.WorkpieceProcessDeviceSiteMapping;
import com.digitzones.model.WorkpieceProcessMapping;
import com.digitzones.model.WorkpieceProcessParameterMapping;
import com.digitzones.service.IProcessDeviceSiteMappingService;
import com.digitzones.service.IProcessesParametersMappingService;
import com.digitzones.service.IWorkpieceProcessDeviceSiteMappingService;
import com.digitzones.service.IWorkpieceProcessMappingService;
import com.digitzones.service.IWorkpieceProcessParameterMappingService;
import com.digitzones.service.IWorkpieceService;
@Controller
@RequestMapping("/workpiece")
public class WorkpieceController {
	private IWorkpieceService workpieceService;
	private IWorkpieceProcessMappingService workpieceProcessMappingService;
	private IWorkpieceProcessDeviceSiteMappingService workpieceProcessDeviceSiteMappingService;
	private IWorkpieceProcessParameterMappingService workpieceProcessParameterMappingService;
	private IProcessesParametersMappingService processesParametersMappingService;
	@Autowired
	public void setProcessesParametersMappingService(IProcessesParametersMappingService processesParametersMappingService) {
		this.processesParametersMappingService = processesParametersMappingService;
	}
	@Autowired
	public void setWorkpieceProcessParameterMappingService(
			IWorkpieceProcessParameterMappingService workpieceProcessParameterMappingService) {
		this.workpieceProcessParameterMappingService = workpieceProcessParameterMappingService;
	}
	private IProcessDeviceSiteMappingService processDeviceSiteMappingService;
	@Autowired
	public void setProcessDeviceSiteMappingService(IProcessDeviceSiteMappingService processDeviceSiteMappingService) {
		this.processDeviceSiteMappingService = processDeviceSiteMappingService;
	}
	@Autowired
	public void setWorkpieceProcessDeviceSiteMappingService(
			IWorkpieceProcessDeviceSiteMappingService workpieceProcessDeviceSiteMappingService) {
		this.workpieceProcessDeviceSiteMappingService = workpieceProcessDeviceSiteMappingService;
	}
	@Autowired
	public void setWorkpieceProcessMappingService(IWorkpieceProcessMappingService workpieceProcessMappingService) {
		this.workpieceProcessMappingService = workpieceProcessMappingService;
	}
	@Autowired
	public void setWorkpieceService(IWorkpieceService workpieceService) {
		this.workpieceService = workpieceService;
	}
	/**
	 * 根据工件类别id查找工件信息
	 * @return
	 */
	@RequestMapping("/queryWorkpiecesByTypeId.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ModelMap queryWorkpiecesByTypeId(Long typeId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<Workpiece> pager = null;
		if(typeId==null) {
			pager = workpieceService.queryObjs("select d from Workpiece d inner join d.workpieceType p ", page, rows, new Object[] {});
		}else {
			pager = workpieceService.queryObjs("select d from Workpiece d inner join d.workpieceType p  where p.id=?0", page, rows, new Object[] {typeId});
		}
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pager.getData());
		mm.addAttribute("total", pager.getTotalCount());
		return mm;
	}
	/**
	 * 根据工件id查找工序和站点等信息
	 * @return
	 */
	@RequestMapping("/queryWorkpieceProcessDeviceSiteMappingsByWorkpieceId.do")
	@ResponseBody
	public ModelMap queryWorkpieceProcessDeviceSiteMappingsByWorkpieceId(Long workpieceId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<WorkpieceProcessDeviceSiteMapping> pager = workpieceProcessDeviceSiteMappingService.queryOrAddWorkpieceProcessDeviceSiteMappingByWorkpieceId(workpieceId, rows, page);
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pager.getData());
		mm.addAttribute("total", pager.getTotalCount());
		return mm;
	}
	/**
	 * 根据工件id查找工序和参数等信息
	 * @return
	 */
	@RequestMapping("/queryWorkpieceProcessParameterMappingsByWorkpieceId.do")
	@ResponseBody
	public ModelMap queryWorkpieceProcessParameterMappingsByWorkpieceId(Long workpieceId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<WorkpieceProcessParameterMapping> pager = workpieceProcessParameterMappingService.queryWorkpieceProcessParameterMappingByWorkpieceId(workpieceId, rows, page);
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pager.getData());
		mm.addAttribute("total", pager.getTotalCount());
		return mm;
	}

	/**
	 * 根据工件id查询非当前工件的'工件工序站点'信息
	 * @param classesId
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryOtherProcessDeviceSites.do")
	@ResponseBody
	public ModelMap queryOtherProcessDeviceSites(Long workpieceId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		String hql = "select pdsm from ProcessDeviceSiteMapping pdsm where pdsm.processes.id not in ("
				+ "select wpdsm.workpieceProcess.process.id from WorkpieceProcessDeviceSiteMapping wpdsm"
				+ " )";
		Pager<ProcessDeviceSiteMapping> pager = workpieceService.queryObjs(hql, page, rows, new Object[] {});
		ModelMap modelMap = new ModelMap();
		
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}
	/**
	 * 根据工件id查询非当前工件的'工件工序参数'信息
	 * @param classesId
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryOtherProcessParameters.do")
	@ResponseBody
	public ModelMap queryOtherProcessParameters(Long workpieceId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		String hql = "select pdsm from ProcessesParametersMapping pdsm where pdsm.processes.id not in ("
				+ "select wpdsm.workpieceProcess.process.id from WorkpieceProcessParameterMapping wpdsm"
				+ " )";
		Pager<ProcessesParametersMapping> pager = workpieceService.queryObjs(hql, page, rows, new Object[] {});
		ModelMap modelMap = new ModelMap();
		
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}
	
	/**
	 * 为工件添加工序和站点信息
	 * @param workpieceId
	 * @param processDeviceSiteIds
	 * @return
	 */
	@RequestMapping("/addProcessDeviceSite4Workpiece.do")
	@ResponseBody
	public ModelMap addProcessDeviceSite4Workpiece(Long workpieceId,String processDeviceSiteIds) {
		ModelMap modelMap = new ModelMap();
		if(processDeviceSiteIds!=null) {
			if(processDeviceSiteIds.contains("[")) {
				processDeviceSiteIds = processDeviceSiteIds.replace("[", "");
			}
			if(processDeviceSiteIds.contains("]")) {
				processDeviceSiteIds = processDeviceSiteIds.replace("]", "");
			}

			String[] idArray = processDeviceSiteIds.split(",");
			for(int i = 0;i<idArray.length;i++) {
				ProcessDeviceSiteMapping pdsm = processDeviceSiteMappingService.queryObjById(Long.valueOf(idArray[i]));
				WorkpieceProcessMapping wpm = workpieceProcessMappingService.queryByWorkpieceIdAndProcessId(workpieceId, pdsm.getProcesses().getId());
				
				
				
				WorkpieceProcessDeviceSiteMapping wpdsm = new WorkpieceProcessDeviceSiteMapping();
				wpdsm.setDeviceSite(pdsm.getDeviceSite());
				wpdsm.setWorkpieceProcess(wpm);
				
				
				workpieceProcessDeviceSiteMappingService.addObj(wpdsm);
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
	 * 为工件添加工序和参数信息
	 * @param workpieceId
	 * @param processDeviceSiteIds
	 * @return
	 */
	@RequestMapping("/addProcessParameter4Workpiece.do")
	@ResponseBody
	public ModelMap addProcessParameter4Workpiece(Long workpieceId,String processDeviceSiteIds) {
		ModelMap modelMap = new ModelMap();
		if(processDeviceSiteIds!=null) {
			if(processDeviceSiteIds.contains("[")) {
				processDeviceSiteIds = processDeviceSiteIds.replace("[", "");
			}
			if(processDeviceSiteIds.contains("]")) {
				processDeviceSiteIds = processDeviceSiteIds.replace("]", "");
			}
			
			String[] idArray = processDeviceSiteIds.split(",");
			for(int i = 0;i<idArray.length;i++) {
				
				ProcessesParametersMapping pdsm = processesParametersMappingService.queryObjById(Long.valueOf(idArray[i]));
				WorkpieceProcessMapping wpm = workpieceProcessMappingService.queryByWorkpieceIdAndProcessId(workpieceId, pdsm.getProcesses().getId());
				
				
				
				WorkpieceProcessParameterMapping wpdsm = new WorkpieceProcessParameterMapping();
				wpdsm.setParameter(pdsm.getParameters());
				wpdsm.setWorkpieceProcess(wpm);
				
				workpieceProcessParameterMappingService.addObj(wpdsm);
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
	 * 删除工件，工序和设备站点的关联
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteProcessDeviceSiteFromWorkpiece.do")
	@ResponseBody
	public ModelMap deleteProcessDeviceSiteFromWorkpiece(String id) {
		if(id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		workpieceProcessDeviceSiteMappingService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("message", "删除成功！");
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		return modelMap;
	}
	/**
	 * 删除工件，工序和参数的关联
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteProcessParameterFromWorkpiece.do")
	@ResponseBody
	public ModelMap deleteProcessParameterFromWorkpiece(String id) {
		if(id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		workpieceProcessParameterMappingService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("message", "删除成功！");
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		return modelMap;
	}
	
	/**
	 * 根据id查询'工件工序设备站点'
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryWorkpieceProcessDeviceSiteMappingById.do")
	@ResponseBody
	public WorkpieceProcessDeviceSiteMapping queryWorkpieceProcessDeviceSiteMappingById(Long id) {
		WorkpieceProcessDeviceSiteMapping workpieceProcessDeviceSiteMapping = workpieceProcessDeviceSiteMappingService.queryObjById(id);
		return workpieceProcessDeviceSiteMapping;
	}
	/**
	 * 根据id查询'工件工序参数'
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryWorkpieceProcessParameterMappingById.do")
	@ResponseBody
	public WorkpieceProcessParameterMapping queryWorkpieceProcessParameterMappingById(Long id) {
		WorkpieceProcessParameterMapping workpieceProcessParameterMapping = workpieceProcessParameterMappingService.queryObjById(id);
		return workpieceProcessParameterMapping;
	}
	/**
	 * 更新
	 * @param processes
	 * @return
	 */
	@RequestMapping("/updateWorkpieceProcessDeviceSiteMapping.do")
	@ResponseBody
	public ModelMap updateWorkpieceProcessDeviceSiteMapping(WorkpieceProcessDeviceSiteMapping workpieceProcessDeviceSiteMapping) {
		ModelMap modelMap = new ModelMap();
		WorkpieceProcessDeviceSiteMapping wpdsm = workpieceProcessDeviceSiteMappingService.queryObjById(workpieceProcessDeviceSiteMapping.getId());
		wpdsm.setProcessingBeat(workpieceProcessDeviceSiteMapping.getProcessingBeat());
		
		workpieceProcessDeviceSiteMappingService.updateObj(wpdsm);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "编辑成功!");
		return modelMap;
	}
	/**
	 * 更新
	 * @param processes
	 * @return
	 */
	@RequestMapping("/updateWorkpieceProcessParameterMapping.do")
	@ResponseBody
	public ModelMap updateWorkpieceProcessParameterMapping(WorkpieceProcessParameterMapping workpieceProcessParameterMapping) {
		ModelMap modelMap = new ModelMap();
		WorkpieceProcessParameterMapping wpdsm = workpieceProcessParameterMappingService.queryObjById(workpieceProcessParameterMapping.getId());
		wpdsm.setLowLine(workpieceProcessParameterMapping.getLowLine());
		wpdsm.setNote(workpieceProcessParameterMapping.getNote());
		wpdsm.setUpLine(workpieceProcessParameterMapping.getUpLine());
		wpdsm.setUnit(workpieceProcessParameterMapping.getUnit());
		wpdsm.setStandardValue(workpieceProcessParameterMapping.getStandardValue());
		
		workpieceProcessParameterMappingService.updateObj(wpdsm);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "编辑成功!");
		return modelMap;
	}
	/**
	 * 添加工件信息
	 * @param workpieceType
	 * @return
	 */
	@RequestMapping("/addWorkpiece.do")
	@ResponseBody
	public ModelMap addWorkpieceType(Workpiece workpiece) {
		ModelMap modelMap = new ModelMap();

		Workpiece c4code = workpieceService.queryByProperty("code", workpiece.getCode());
		if(c4code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "工件编码已被使用");
		}else  {
			workpieceService.addObj(workpiece);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "添加成功!");
		}
		return modelMap;
	}

	/**
	 * 根据id查询工件
	 * @param workpiece
	 * @return
	 */
	@RequestMapping("/queryWorkpieceById.do")
	@ResponseBody
	public Workpiece queryWorkpieceById(Long id) {
		return workpieceService.queryObjById(id);
	}

	/**
	 * 更新工件类别信息
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/updateWorkpiece.do")
	@ResponseBody
	public ModelMap updateWorkpiece(Workpiece workpiece) {
		ModelMap modelMap = new ModelMap();

		workpieceService.updateObj(workpiece);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "更新成功!");
		return modelMap;
	}
	/**
	 * 根据id删除工件
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteWorkpiece.do")
	@ResponseBody
	public ModelMap deleteWorkpiece(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();

		workpieceService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 停用该工件
	 * @param id
	 * @return
	 */
	@RequestMapping("/disabledWorkpiece.do")
	@ResponseBody
	public ModelMap disabledWorkpiece(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Workpiece d = workpieceService.queryObjById(Long.valueOf(id));
		d.setDisabled(true);

		workpieceService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}
	/**
	 * 为工件添加工序
	 * @param workpieceId
	 * @param processesId
	 * @return
	 */
	@RequestMapping("/addProcesses4Workpiece.do")
	@ResponseBody
	public ModelMap addProcesses4Workpiece(Long workpieceId,String processesId) {
		ModelMap modelMap = new ModelMap();
		if(processesId!=null) {
			if(processesId.contains("[")) {
				processesId = processesId.replace("[", "");
			}
			if(processesId.contains("]")) {
				processesId = processesId.replace("]", "");
			}

			String[] idArray = processesId.split(",");
			for(int i = 0;i<idArray.length;i++) {
				Processes device = new Processes();
				device.setId(Long.valueOf(idArray[i]));
				Workpiece c = new Workpiece();
				c.setId(workpieceId);
				
				WorkpieceProcessMapping cdm = new WorkpieceProcessMapping();
				cdm.setProcess(device);
				cdm.setWorkpiece(c);
				
				workpieceProcessMappingService.addObj(cdm);
				
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
	 * 删除工序和工件的关联
	 * @param cdm
	 * @return
	 */
	@RequestMapping("/deleteProcessesFromWorkpiece.do")
	@ResponseBody
	public ModelMap deleteDeviceSiteFromProcesses(Long workpieceId,String processesId) {
		if(processesId.contains("'")) {
			processesId = processesId.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		workpieceProcessMappingService.deleteByWorkpieceIdAndProcessId(workpieceId, Long.valueOf(processesId));
		modelMap.addAttribute("message", "删除成功！");
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		return modelMap;
	}
	/**
	 * 根据条件查询工件信息
	 * @param q
	 * @return
	 */
	@RequestMapping("/queryWorkpieces.do")
	@ResponseBody
	public List<Workpiece> queryWorkpieces(String q){
		if(q!=null && !"".equals(q.trim())) {
			return workpieceService.queryAllWorkpieces(q);
		}else {
			return workpieceService.queryAllWorkpieces();
		}
	}
} 
