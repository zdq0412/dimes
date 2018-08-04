package com.digitzones.controllers;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.config.WorkFlowKeyConfig;
import com.digitzones.constants.Constant;
import com.digitzones.model.DeviceSite;
import com.digitzones.model.Pager;
import com.digitzones.model.PressLightRecord;
import com.digitzones.model.User;
import com.digitzones.service.IDeviceSiteService;
import com.digitzones.service.IPressLightRecordService;
@Controller
@RequestMapping("/pressLightRecord")
public class PressLightRecordController {
	private IPressLightRecordService pressLightRecordService;
	private IDeviceSiteService deviceSiteService;
	private WorkFlowKeyConfig workFlowKeyConfig;
	@Autowired
	public void setWorkFlowKeyConfig(WorkFlowKeyConfig workFlowKeyConfig) {
		this.workFlowKeyConfig = workFlowKeyConfig;
	}
	@Autowired
	public void setDeviceSiteService(IDeviceSiteService deviceSiteService) {
		this.deviceSiteService = deviceSiteService;
	}
	@Autowired
	public void setPressLightRecordService(@Qualifier("pressLightRecordServiceProxy")IPressLightRecordService pressLightRecordService) {
		this.pressLightRecordService = pressLightRecordService;
	} 
	/**
	 * 根据设备站点id查找按灯记录
	 * @param deviceSiteId
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryPressLightRecordByDeviceSiteId.do")
	@ResponseBody
	public ModelMap queryPressLightRecordByDeviceSiteId(Long deviceSiteId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		String hql = "from PressLightRecord plr where plr.deviceSite.id=?0 and plr.deleted=?1";
		Pager<PressLightRecord> pager = pressLightRecordService.queryObjs(hql, page, rows, new Object[] {deviceSiteId,false});
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}
	/**
	 * 添加按灯记录
	 * @param pressLightRecord
	 * @return
	 */
	@RequestMapping("/addPressLightRecord.do")
	@ResponseBody
	public ModelMap addPressLightRecord(PressLightRecord pressLightRecord,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		DeviceSite ds = deviceSiteService.queryObjById(pressLightRecord.getDeviceSite().getId());
		if(ds==null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "请选择设备站点");
			return modelMap;
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Constant.User.LOGIN_USER);
		modelMap.put("businessKey", workFlowKeyConfig.getPressLightWorkflowKey());
		pressLightRecord.setPressLightTime(new Date());
		pressLightRecordService.addPressLightRecord(pressLightRecord, user, modelMap);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "添加成功!");
		return modelMap;
	}
	/**
	 * 根据id查询按灯记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryPressLightRecordById.do")
	@ResponseBody
	public PressLightRecord queryPressLightRecordById(Long id) {
		PressLightRecord plr = pressLightRecordService.queryObjById(id);
		return plr;
	}
	
	
	
	/**
	 * 编辑按灯记录
	 * @param pressLightRecord
	 * @return
	 */
	@RequestMapping("/updatePressLightRecord.do")
	@ResponseBody
	public ModelMap updatePressLightRecord(PressLightRecord pressLightRecord) {
		ModelMap modelMap = new ModelMap();
		
		PressLightRecord plr = pressLightRecordService.queryObjById(pressLightRecord.getId());
		plr.setHalt(pressLightRecord.getHalt());
		plr.setReason(pressLightRecord.getReason());
		plr.setRecoverMethod(pressLightRecord.getRecoverMethod());
		plr.setSmallPressLightTypeName(pressLightRecord.getSmallPressLightTypeName());
		plr.setPressLightTypeName(pressLightRecord.getPressLightTypeName());
		
		pressLightRecordService.updateObj(plr);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "添加成功!");
		return modelMap;
	}
	/**
	 * 恢复
	 * @param pressLightRecord
	 * @return
	 */
	@RequestMapping("/recoverPressLightRecord.do")
	@ResponseBody
	public ModelMap recoverPressLightRecord(Long id,String suggestion,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Constant.User.LOGIN_USER);
		
		PressLightRecord plr = pressLightRecordService.queryObjById(id);
		plr.setRecovered(true);
		plr.setRecoverTime(new Date());
		plr.setHalt(false);
		Map<String,Object> args = new HashMap<>();
		args.put("suggestion", suggestion);
		pressLightRecordService.recoverPressLightRecord(plr, user, args);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("title", "提示");
		modelMap.addAttribute("message", "操作成功!");
		return modelMap;
	}
	/**
	 * 确认
	 * @param pressLightRecord
	 * @return
	 */
	@RequestMapping("/confirmPressLightRecord.do")
	@ResponseBody
	public ModelMap confirmPressLightRecord(Long id,String suggestion,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		PressLightRecord plr = pressLightRecordService.queryObjById(id);
		plr.setConfirmTime(new Date());
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Constant.User.LOGIN_USER);
		Map<String,Object> args = new HashMap<>();
		args.put("suggestion", suggestion);
		pressLightRecordService.confirmPressLightRecord(plr, user, args);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("title", "提示");
		modelMap.addAttribute("message", "操作成功!");
		return modelMap;
	}
	/**
	 *熄灯
	 * @param pressLightRecord
	 * @return
	 */
	@RequestMapping("/lightOutPressLightRecord.do")
	@ResponseBody
	public ModelMap lightOutPressLightRecord(Long id,String suggestion,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		PressLightRecord plr = pressLightRecordService.queryObjById(id);
		plr.setLightOutTime(new Date());
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Constant.User.LOGIN_USER);
		Map<String,Object> args = new HashMap<>();
		args.put("suggestion", suggestion);
		pressLightRecordService.lightoutPressLightRecord(plr, user, args);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("title", "提示");
		modelMap.addAttribute("message", "操作成功!");
		return modelMap;
	}
	
	
	/**
	 * 根据id删除按灯记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletePressLightRecord.do")
	@ResponseBody
	public ModelMap deletePressLightRecord(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		PressLightRecord pr = pressLightRecordService.queryObjById(Long.valueOf(id));
		pr.setDeleted(true);
		try {
			pressLightRecordService.deletePressLightRecord(pr);
		}catch(RuntimeException e) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", e.getMessage());
			return modelMap;
		}
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
} 
