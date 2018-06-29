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
import com.digitzones.model.EquipmentDeviceSiteMapping;
import com.digitzones.model.Pager;
import com.digitzones.model.User;
import com.digitzones.service.IDeviceSiteService;
import com.digitzones.service.IMeasuringToolDeviceSiteMappingService;
import com.digitzones.vo.EquipmentDeviceSiteMappingVO;
/**
 * 设备站点和量具关联记录
 * @author zdq
 * 2018年6月28日
 */
@Controller
@RequestMapping("/measuringToolMappingRecord")
public class MeasuringToolMappingRecordController {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private IMeasuringToolDeviceSiteMappingService  measuringToolDeviceSiteMappingService;
	private IDeviceSiteService deviceSiteService;
	@Autowired
	public void setDeviceSiteService(IDeviceSiteService deviceSiteService) {
		this.deviceSiteService = deviceSiteService;
	}

	@Autowired
	public void setMeasuringToolDeviceSiteMappingService(IMeasuringToolDeviceSiteMappingService measuringToolDeviceSiteMappingService) {
		this.measuringToolDeviceSiteMappingService = measuringToolDeviceSiteMappingService;
	}

	/**
	 * 根据设备站点id查找量具关联
	 * @param deviceSiteId
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryMeasuringToolMappingRecordByDeviceSiteId.do")
	@ResponseBody
	public ModelMap queryMeasuringToolMappingRecordByDeviceSiteId(Long deviceSiteId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		String hql = "from EquipmentDeviceSiteMapping edsm where edsm.deviceSite.id=?0 and edsm.unbind=?1 and edsm.measuringTool.baseCode=?2";
		Pager<EquipmentDeviceSiteMapping> pager = measuringToolDeviceSiteMappingService.queryObjs(hql, page, rows, new Object[] {deviceSiteId,false,Constant.EquipmentType.MEASURINGTOOL});
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", pager.getData());
		return modelMap;
	}

	/**
	 * 添加量具关联记录
	 * @param pressLightRecord
	 * @return
	 */
	@RequestMapping("/addMeasuringToolMappingRecord.do")
	@ResponseBody
	public ModelMap addMeasuringToolMappingRecord(EquipmentDeviceSiteMapping measuringToolDeviceSiteMapping,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		DeviceSite ds = deviceSiteService.queryObjById(measuringToolDeviceSiteMapping.getDeviceSite().getId());
		if(ds==null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "请选择设备站点");
			return modelMap;
		}

		//判断该量具是否已经被关联到设备上
		EquipmentDeviceSiteMapping edm = measuringToolDeviceSiteMappingService.queryByNo(measuringToolDeviceSiteMapping.getNo());
		if(edm!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "该量具已被关联到 " + edm.getDeviceSite().getCode() + "上");
			return modelMap;
		}
		
		if(measuringToolDeviceSiteMapping.getMappingDate()==null) {
			measuringToolDeviceSiteMapping.setMappingDate(new Date());
		}

		//获取当前登录用户
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constant.User.LOGIN_USER);
		if(user!=null) {
			measuringToolDeviceSiteMapping.setBindUserId(user.getId());
			measuringToolDeviceSiteMapping.setBindUsername(user.getUsername());
		}
		measuringToolDeviceSiteMappingService.addObj(measuringToolDeviceSiteMapping);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "添加成功!");
		return modelMap;
	}
	/**
	 * 根据id查询量具关联记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryMeasuringToolMappingRecordById.do")
	@ResponseBody
	public EquipmentDeviceSiteMappingVO queryMeasuringToolMappingRecordById(Long id) {
		EquipmentDeviceSiteMapping plr = measuringToolDeviceSiteMappingService.queryObjById(id);
		
		return model2VO(plr);
	}
	/**
	 * 将领域模型转换为值对象模型
	 * @param plr
	 * @return
	 */
	private EquipmentDeviceSiteMappingVO model2VO(EquipmentDeviceSiteMapping plr) {
		if(plr == null) {
			return null;
		}
		EquipmentDeviceSiteMappingVO vo = new EquipmentDeviceSiteMappingVO();
		vo.setId(plr.getId());
		vo.setBindUsername(plr.getBindUsername());
		vo.setBindUserId(plr.getBindUserId());
		vo.setDeviceSite(plr.getDeviceSite());
		vo.setEquipment(plr.getEquipment());
		vo.setHelperId(plr.getHelperId());
		vo.setHelperName(plr.getHelperName());
		vo.setNo(plr.getNo());
		vo.setWorkSheetCode(plr.getWorkSheetCode());
		if(plr.getMappingDate()!=null) {
			vo.setMappingDate(sdf.format(plr.getMappingDate()));
		}
		vo.setUsageRate(plr.getUsageRate());
		return vo;
	}

	/**
	 * 编辑量具关联记录
	 * @param pressLightRecord
	 * @return
	 */
	@RequestMapping("/updateMeasuringToolMappingRecord.do")
	@ResponseBody
	public ModelMap updateMeasuringToolMappingRecord(EquipmentDeviceSiteMapping measuringToolDeviceSiteMapping) {
		ModelMap modelMap = new ModelMap();
		measuringToolDeviceSiteMappingService.updateObj(measuringToolDeviceSiteMapping);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "更新成功!");
		return modelMap;
	}
	/**
	 * 根据id删除量具关联记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteMeasuringToolMappingRecord.do")
	@ResponseBody
	public ModelMap deleteMeasuringToolMappingRecord(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		measuringToolDeviceSiteMappingService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
	/**
	 * 解除量具关联
	 * @param id
	 * @return
	 */
	@RequestMapping("/unbindMeasuringToolMappingRecord.do")
	@ResponseBody
	public ModelMap unbindMeasuringToolMappingRecord(String id,HttpServletRequest request) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		//获取当前登录用户
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constant.User.LOGIN_USER);

		ModelMap modelMap = new ModelMap();
		EquipmentDeviceSiteMapping measuringToolDeviceSiteMapping = measuringToolDeviceSiteMappingService.queryObjById(Long.valueOf(id));
		if(user!=null) {
			measuringToolDeviceSiteMapping.setUnbindUserId(user.getId());
			measuringToolDeviceSiteMapping.setUnbindUsername(user.getUsername());
		}
		measuringToolDeviceSiteMapping.setUnbind(true);
		measuringToolDeviceSiteMapping.setUnbindDate(new Date());
		measuringToolDeviceSiteMappingService.updateObj(measuringToolDeviceSiteMapping);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "操作成功!");
		return modelMap;
	}
} 
