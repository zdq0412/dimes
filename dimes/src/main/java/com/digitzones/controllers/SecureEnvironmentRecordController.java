package com.digitzones.controllers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Pager;
import com.digitzones.model.SecureEnvironmentRecord;
import com.digitzones.service.ISecureEnvironmentRecordService;
import com.digitzones.vo.SecureEnvironmentRecordVO;
/**
 * 安环记录控制器
 * @author zdq
 * 2018年7月11日
 */
@Controller
@RequestMapping("/secureEnvironmentRecord")
public class SecureEnvironmentRecordController {
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private ISecureEnvironmentRecordService secureEnvironmentRecordService;
	@Autowired
	public void setSecureEnvironmentRecordService(ISecureEnvironmentRecordService secureEnvironmentRecordService) {
		this.secureEnvironmentRecordService = secureEnvironmentRecordService;
	}

	/**
	 * 查询安环记录信息
	 * @return
	 */
	@RequestMapping("/querySecureEnvironmentRecords.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ModelMap querySecureEnvironmentRecords(@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		Pager<SecureEnvironmentRecord> pager = secureEnvironmentRecordService.queryObjs("from SecureEnvironmentRecord c ", page, rows, new Object[] {});
		List<SecureEnvironmentRecordVO> vos = new ArrayList<>();
		List<SecureEnvironmentRecord> records = pager.getData();
		for(SecureEnvironmentRecord record:records) {
			vos.add(model2vo(record));
		}
		modelMap.addAttribute("rows", vos);
		modelMap.addAttribute("total",pager.getTotalCount());
		return modelMap;
	}

	
	private SecureEnvironmentRecordVO model2vo(SecureEnvironmentRecord record) {
		if(record == null) {
			return null;
		}
		SecureEnvironmentRecordVO vo = new SecureEnvironmentRecordVO();
		vo.setId(record.getId());
		if(record.getCurrentDate()!=null) {
			vo.setCurrentDate(format.format(record.getCurrentDate()));
		}
		vo.setDescription(record.getDescription());
		vo.setGradeId(record.getGradeId());
		vo.setGradeName(record.getGradeName());
		vo.setTypeId(record.getTypeId());
		vo.setTypeName(record.getTypeName());
		return vo;
	}
	
	/**
	 * 添加等级技能信息
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/addSecureEnvironmentRecord.do")
	@ResponseBody
	public ModelMap addSecureEnvironmentRecord(SecureEnvironmentRecord secureEnvironmentRecord) {
		ModelMap modelMap = new ModelMap();
		secureEnvironmentRecordService.addObj(secureEnvironmentRecord);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "添加成功!");
		return modelMap;
	}

	/**
	 * 根据id查询安环记录信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/querySecureEnvironmentRecordById.do")
	@ResponseBody
	public SecureEnvironmentRecordVO querySecureEnvironmentRecordById(Long id) {
		SecureEnvironmentRecord c =  secureEnvironmentRecordService.queryObjById(id);
		return model2vo(c);
	}
	/**
	 * 更新安环记录
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/updateSecureEnvironmentRecord.do")
	@ResponseBody
	public ModelMap updateSecureEnvironmentRecord(SecureEnvironmentRecord secureEnvironmentRecord) {
		ModelMap modelMap = new ModelMap();
		secureEnvironmentRecordService.updateObj(secureEnvironmentRecord);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "更新成功!");
		return modelMap;
	}

	/**
	 * 根据id删除安环记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteSecureEnvironmentRecord.do")
	@ResponseBody
	public ModelMap deleteSecureEnvironmentRecord(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		secureEnvironmentRecordService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
} 
