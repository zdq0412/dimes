package com.digitzones.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.DeviceSite;
import com.digitzones.model.Pager;
import com.digitzones.model.Parameters;
import com.digitzones.model.ProcessDeviceSiteMapping;
import com.digitzones.model.ProcessType;
import com.digitzones.model.Processes;
import com.digitzones.model.ProcessesParametersMapping;
import com.digitzones.service.IProcessDeviceSiteMappingService;
import com.digitzones.service.IProcessTypeService;
import com.digitzones.service.IProcessesParametersMappingService;
import com.digitzones.service.IProcessesService;
import com.digitzones.vo.ProcessesVO;
/**
 * 工序管理控制器
 * @author zdq
 * 2018年6月7日
 */
@Controller
@RequestMapping("/processes")
public class ProcessesController {
	private IProcessesService processesService;
	private IProcessTypeService processTypeService;
	private IProcessDeviceSiteMappingService processDeviceSiteMappingService;
	private IProcessesParametersMappingService processesParametersMappingService;
	@Autowired
	public void setProcessesParametersMappingService(IProcessesParametersMappingService processesParametersMappingService) {
		this.processesParametersMappingService = processesParametersMappingService;
	}
	@Autowired
	public void setProcessDeviceSiteMappingService(IProcessDeviceSiteMappingService processDeviceSiteMappingService) {
		this.processDeviceSiteMappingService = processDeviceSiteMappingService;
	}
	@Autowired
	public void setProcessTypeService(IProcessTypeService processTypeService) {
		this.processTypeService = processTypeService;
	}
	@Autowired
	public void setProcessesService(IProcessesService processesService) {
		this.processesService = processesService;
	}
	/**
	 * 更新工序
	 * @param processes
	 * @return
	 */
	@RequestMapping("/updateProcesses.do")
	@ResponseBody
	public ModelMap updateProcesses(Processes processes) {
		ModelMap modelMap = new ModelMap();
		ProcessType processType = processes.getProcessType();
		if(processType!=null) {
			ProcessType pt = processTypeService.queryByProperty("name", processes.getName());
			if(pt==null) {
				Long id = (Long) processTypeService.addObj(processType);
				pt = processTypeService.queryObjById(id);
				pt.setId(id);
			}
			processes.setProcessType(pt);
		}
		processesService.updateObj(processes);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "编辑成功!");
		return modelMap;
	}
	/**
	 * 根据id删除工序，如果该工序存在子工序，则不允许删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteProcesses.do")
	@ResponseBody
	public ModelMap deleteProcesses(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		processesService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("msg", "该工序下存在子工序，不允许删除!");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 停用该工序
	 * @param id
	 * @return
	 */
	@RequestMapping("/disabledProcesses.do")
	@ResponseBody
	public ModelMap disabledProcesses(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Processes d = processesService.queryObjById(Long.valueOf(id));
		d.setDisabled(true);

		processesService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}
	/**
	 * 根据id查询工序
	 * @param processes
	 * @return
	 */
	@RequestMapping("/queryProcessesById.do")
	@ResponseBody
	public ProcessesVO queryProcessesById(Long id) {
		Processes processes = processesService.queryObjById(id);
		return model2Vo(processes);
	}

	/**
	 * 查找所有工序
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryProcessess.do")
	@ResponseBody
	public ModelMap queryProcessess(Integer rows,Integer page){
		String hql = "from Processes p";
		Pager<Processes> pager = processesService.queryObjs(hql, page, rows, new Object[] {});
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return  modelMap;
	}
	
	
	/**
	 * 根据工件id查询非当前工件下的工序信息
	 * @param workpieceId
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping("/queryOtherProcesses.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ModelMap queryOtherProcesses(Long workpieceId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		String hql = "select ds from Processes ds where ds.id not in ("
				+ "select pdm.process.id from WorkpieceProcessMapping pdm where pdm.workpiece.id=?0)";
		Pager<Processes> pager = processesService.queryObjs(hql, page, rows, new Object[] {workpieceId});
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}
	/**
	 * 根据工件id查找工序
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryProcessessByWorkpieceId.do")
	@ResponseBody
	public ModelMap queryProcessessByWorkpieceId(Long workpieceId,Integer rows,Integer page){
		String hql = "select p from WorkpieceProcessMapping wpm inner join wpm.process p where wpm.workpiece.id=?0  order by p.code asc";
		Pager<Processes> pager = processesService.queryObjs(hql, page, rows, new Object[] {workpieceId});
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return  modelMap;
	}

	private ProcessesVO model2Vo(Processes p) {
		ProcessesVO vo = new ProcessesVO();
		if(p == null) {
			return null;
		}

		vo.setId(p.getId());
		vo.setName(p.getName());
		vo.setCode(p.getCode());
		vo.setProcessType(p.getProcessType());
		if(p.getProcessType()!=null) {
			vo.setProcessTypeId(p.getProcessType().getId());
			vo.setProcessTypeName(p.getProcessType().getName());
		}
		vo.setNote(p.getNote());
		return vo;
	}
	/**
	 * 添加工序
	 * @param processes
	 * @return
	 */
	@RequestMapping("/addProcesses.do")
	@ResponseBody
	public ModelMap addProcesses(Processes processes) {
		ProcessType processType = processes.getProcessType();
		if(processType!=null) {
			ProcessType pt = processTypeService.queryByProperty("name", processes.getName());
			if(pt==null) {
				Long id = (Long) processTypeService.addObj(processType);
				pt = processTypeService.queryObjById(id);
				pt.setId(id);
			}
			processes.setProcessType(pt);
		}
		ModelMap modelMap = new ModelMap();
		//检测工序编码是否重复
		Processes dept4Code = processesService.queryByProperty("code", processes.getCode());
		if(dept4Code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "工序编码已被使用");
		}else {
			processesService.addObj(processes);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "添加成功!");
		}
		return modelMap;
	}

	/**
	 * 为工序添加设备站点
	 * @param classesId
	 * @param deviceIds
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

			String[] idArray = deviceSiteIds.split(",");
			for(int i = 0;i<idArray.length;i++) {
				DeviceSite device = new DeviceSite();
				device.setId(Long.valueOf(idArray[i]));
				Processes c = new Processes();
				c.setId(processesId);

				ProcessDeviceSiteMapping pdsm = new ProcessDeviceSiteMapping();
				pdsm.setDeviceSite(device);
				pdsm.setProcesses(c);

				processDeviceSiteMappingService.addObj(pdsm);
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
	 * 删除工序和设备站点的关联
	 * @param cdm
	 * @return
	 */
	@RequestMapping("/deleteDeviceSiteFromProcesses.do")
	@ResponseBody
	public ModelMap deleteDeviceSiteFromProcesses(Long processesId,String deviceSiteId) {
		if(deviceSiteId.contains("'")) {
			deviceSiteId = deviceSiteId.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		processDeviceSiteMappingService.deleteByProcessesIdAndDeviceSiteId(processesId, Long.valueOf(deviceSiteId));
		modelMap.addAttribute("message", "删除成功！");
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		return modelMap;
	}
	/**
	 * 为工序添加参数
	 * @param processesId
	 * @param parametersId
	 * @return
	 */
	@RequestMapping("/addParameters4Processes.do")
	@ResponseBody
	public ModelMap addParameters4Processes(Long processesId,String parameterIds) {
		ModelMap modelMap = new ModelMap();
		if(parameterIds!=null) {
			if(parameterIds.contains("[")) {
				parameterIds = parameterIds.replace("[", "");
			}
			if(parameterIds.contains("]")) {
				parameterIds = parameterIds.replace("]", "");
			}
			
			String[] idArray = parameterIds.split(",");
			for(int i = 0;i<idArray.length;i++) {
				Parameters device = new Parameters();
				device.setId(Long.valueOf(idArray[i]));
				Processes c = new Processes();
				c.setId(processesId);
				
				ProcessesParametersMapping pdsm = new ProcessesParametersMapping();
				pdsm.setParameters(device);
				pdsm.setProcesses(c);
				
				processesParametersMappingService.addObj(pdsm);
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
	 * 删除工序和参数的关联
	 * @param processesId
	 * @param parametersId
	 * @return
	 */
	@RequestMapping("/deleteParameterFromProcesses.do")
	@ResponseBody
	public ModelMap deleteParameterFromProcesses(Long processesId,String parametersId) {
		if(parametersId.contains("'")) {
			parametersId = parametersId.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		processesParametersMappingService.deleteByProcessesIdAndParameterId(processesId, Long.valueOf(parametersId));
		modelMap.addAttribute("message", "删除成功！");
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		return modelMap;
	}
	
	
} 
