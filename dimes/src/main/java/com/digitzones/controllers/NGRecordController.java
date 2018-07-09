package com.digitzones.controllers;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.constants.Constant;
import com.digitzones.model.DeviceSite;
import com.digitzones.model.NGRecord;
import com.digitzones.model.Pager;
import com.digitzones.model.User;
import com.digitzones.service.IDeviceSiteService;
import com.digitzones.service.INGRecordService;
import com.digitzones.vo.NGRecordVO;
/**
 * 不合格品记录控制器
 * @author zdq
 * 2018年6月28日
 */
@Controller
@RequestMapping("/ngRecord")
public class NGRecordController {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private INGRecordService ngRecordService;
	private IDeviceSiteService deviceSiteService;
	@Autowired
	public void setDeviceSiteService(IDeviceSiteService deviceSiteService) {
		this.deviceSiteService = deviceSiteService;
	}

	@Autowired
	public void setNgRecordService(INGRecordService ngRecordService) {
		this.ngRecordService = ngRecordService;
	}

	/**
	 * 根据设备站点id查找不合格品记录
	 * @param deviceSiteId
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryNGRecordByDeviceSiteId.do")
	@ResponseBody
	public ModelMap queryNGRecordByDeviceSiteId(Long deviceSiteId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		String hql = "from NGRecord record where record.deviceSiteId=?0 and record.deleted=?1";
		Pager<NGRecord> pager = ngRecordService.queryObjs(hql, page, rows, new Object[] {deviceSiteId,false});
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}

	/**
	 * 添加不合格品记录
	 * @param pressLightRecord
	 * @return
	 */
	@RequestMapping("/addNGRecord.do")
	@ResponseBody
	public ModelMap addNGRecord(NGRecord ngRecord,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		DeviceSite ds = deviceSiteService.queryObjById(ngRecord.getDeviceSiteId());
		if(ds==null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "请选择设备站点");
			return modelMap;
		}
		//获取当前登录用户
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constant.User.LOGIN_USER);
		if(user!=null) {
			ngRecord.setInputUserId(user.getId());
			ngRecord.setInputUsername(user.getUsername());
		}
		ngRecord.setInputDate(new Date());
		ngRecordService.addObj(ngRecord);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "添加成功!");
		return modelMap;
	}
	/**
	 * 根据id查询不合格记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryNGRecordById.do")
	@ResponseBody
	public NGRecordVO queryNGRecordById(Long id) {
		NGRecord record = ngRecordService.queryObjById(id);
		return model2vo(record);
	}
	
	private NGRecordVO model2vo(NGRecord record) {
		if(record == null) {
			return null;
		}
		NGRecordVO vo = new NGRecordVO();
		vo.setId(record.getId());
		vo.setProcessCode(record.getProcessCode());
		vo.setNo(record.getNo());
		vo.setProcessName(record.getProcessName());
		vo.setProcessId(record.getProcessId());
		vo.setWorkpieceCode(record.getWorkpieceCode());
		vo.setProcessingMethod(record.getProcessingMethod());
		vo.setWorkpieceId(record.getWorkpieceId());
		vo.setWorkSheetId(record.getWorkSheetId());
		vo.setWorkpieceName(record.getWorkpieceName());
		vo.setStoveNumber(record.getStoveNumber());
		vo.setCustomerGraphNumber(record.getCustomerGraphNumber());
		vo.setGraphNumber(record.getGraphNumber());
		vo.setVersion(record.getVersion());
		vo.setNgCount(record.getNgCount());
		vo.setNgReason(record.getNgReason());
		vo.setNgReasonId(record.getNgReasonId());
		vo.setNgTypeId(record.getNgTypeId());
		vo.setNgTypeName(record.getNgTypeName());
		vo.setUnitType(record.getUnitType());
		vo.setBatchNumber(record.getBatchNumber());
		if(record.getOccurDate()!=null) {
			vo.setOccurDate(sdf.format(record.getOccurDate()));
		}
		return vo;
	}
	/**
	 * 编辑不合格品记录
	 * @param pressLightRecord
	 * @return
	 */
	@RequestMapping("/updateNGRecord.do")
	@ResponseBody
	public ModelMap updateNGRecord(NGRecord record) {
		ModelMap modelMap = new ModelMap();
		NGRecord ng = ngRecordService.queryObjById(record.getId());
		ng.setWorkSheetId(record.getWorkSheetId());
		ng.setOccurDate(record.getOccurDate());
		ng.setNo(record.getNo());
		ng.setWorkpieceId(record.getWorkpieceId());
		ng.setWorkpieceCode(record.getWorkpieceCode());
		ng.setWorkpieceName(record.getWorkpieceName());
		ng.setCustomerGraphNumber(record.getCustomerGraphNumber());
		ng.setVersion(record.getVersion());
		ng.setBatchNumber(record.getBatchNumber());
		ng.setStoveNumber(record.getStoveNumber());
		ng.setGraphNumber(record.getGraphNumber());
		ng.setUnitType(record.getUnitType());
		ng.setNgCount(record.getNgCount());
		ng.setNgTypeId(record.getNgTypeId());
		ng.setNgTypeName(record.getNgTypeName());
		ng.setNgReason(record.getNgReason());
		ng.setNgReasonId(record.getNgReasonId());
		ng.setProcessId(record.getProcessId());
		ng.setProcessCode(record.getProcessCode());
		ng.setProcessName(record.getProcessName());
		ng.setProcessingMethod(record.getProcessingMethod());
		ngRecordService.updateObj(ng);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "更新成功!");
		return modelMap;
	}
	/**
	 * 根据id删除不合格品记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteNGRecord.do")
	@ResponseBody
	public ModelMap deleteNGRecord(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		NGRecord record = ngRecordService.queryObjById(Long.valueOf(id));
		record.setDeleted(true);
		ngRecordService.updateObj(record);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 复核
	 * @param id
	 * @return
	 */
	@RequestMapping("/reviewNGRecord.do")
	@ResponseBody
	public ModelMap reviewNGRecord(NGRecord r,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		NGRecord record = ngRecordService.queryObjById(r.getId());
		
		if(record.getReviewerId()!=null) {
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "该记录已复核!");
			return modelMap;
		}
		
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute(Constant.User.LOGIN_USER);
		if(loginUser!=null) {
			record.setReviewDate(new Date());
			record.setReviewerId(loginUser.getId());
			record.setReviewerName(loginUser.getUsername());
		}
		record.setReviewSuggestion(r.getReviewSuggestion());
		ngRecordService.updateObj(record);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 审核
	 * @param id
	 * @return
	 */
	@RequestMapping("/auditNGRecord.do")
	@ResponseBody
	public ModelMap auditNGRecord(NGRecord r,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		NGRecord record = ngRecordService.queryObjById(r.getId());
		
		if(record.getAuditorId()!=null) {
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "该记录已审核!");
			return modelMap;
		}
		
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute(Constant.User.LOGIN_USER);
		if(loginUser!=null) {
			record.setAuditDate(new Date());
			record.setAuditorId(loginUser.getId());
			record.setAuditorName(loginUser.getUsername());
		}
		record.setAuditSuggestion(r.getAuditSuggestion());
		ngRecordService.auditNGRecord(record);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 确认
	 * @param id
	 * @return
	 */
	@RequestMapping("/confirmNGRecord.do")
	@ResponseBody
	public ModelMap confirmNGRecord(NGRecord r,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		NGRecord record = ngRecordService.queryObjById(r.getId());
		
		if(record.getConfirmUserId()!=null) {
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "该记录已确认!");
			return modelMap;
		}
		
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute(Constant.User.LOGIN_USER);
		if(loginUser!=null) {
			record.setConfirmDate(new Date());
			record.setConfirmUserId(loginUser.getId());
			record.setConfirmUsername(loginUser.getUsername());
		}
		record.setConfirmSuggestion(r.getConfirmSuggestion());
		ngRecordService.updateObj(record);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	
	
} 
