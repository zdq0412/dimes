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
import com.digitzones.model.QualityCalendarRecord;
import com.digitzones.service.IQualityCalendarRecordService;
import com.digitzones.vo.QualityCalendarRecordVO;
/**
 * 质量记录控制器
 * @author zdq
 * 2018年7月11日
 */
@Controller
@RequestMapping("/qualityCalendarRecord")
public class QualityCalendarRecordController {
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private IQualityCalendarRecordService qualityCalendarRecordService;
	@Autowired
	public void setQualityCalendarRecordService(IQualityCalendarRecordService qualityCalendarRecordService) {
		this.qualityCalendarRecordService = qualityCalendarRecordService;
	}
	/**
	 * 查询质量记录信息
	 * @return
	 */
	@RequestMapping("/queryQualityCalendarRecords.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ModelMap queryQualityCalendarRecords(@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		ModelMap modelMap = new ModelMap();
		Pager<QualityCalendarRecord> pager = qualityCalendarRecordService.queryObjs("from QualityCalendarRecord c ", page, rows, new Object[] {});
		List<QualityCalendarRecordVO> vos = new ArrayList<>();
		List<QualityCalendarRecord> records = pager.getData();
		for(QualityCalendarRecord r : records) {
			vos.add(model2vo(r));
		}
		modelMap.addAttribute("rows", vos);
		modelMap.addAttribute("total",pager.getTotalCount());
		return modelMap;
	}

	private QualityCalendarRecordVO model2vo(QualityCalendarRecord record) {
		if(record == null) {
			return null;
		}
		QualityCalendarRecordVO vo = new QualityCalendarRecordVO();
		vo.setId(record.getId());
		vo.setContacts(record.getContacts());
		vo.setContent(record.getContent());
		if(record.getCurrentDate()!=null) {
			vo.setCurrentDate(format.format(record.getCurrentDate()));
		}
		vo.setGradeId(record.getGradeId());
		vo.setGradeName(record.getGradeName());
		vo.setTypeId(record.getTypeId());
		vo.setTypeName(record.getTypeName());
		vo.setNote(record.getNote());
		vo.setTel(record.getTel());
		vo.setCustomer(record.getCustomer());
		return  vo;
	}
	/**
	 * 添加等级技能信息
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/addQualityCalendarRecord.do")
	@ResponseBody
	public ModelMap addQualityCalendarRecord(QualityCalendarRecord qualityCalendarRecord) {
		ModelMap modelMap = new ModelMap();
		qualityCalendarRecordService.addObj(qualityCalendarRecord);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "添加成功!");
		return modelMap;
	}

	/**
	 * 根据id查询质量记录信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryQualityCalendarRecordById.do")
	@ResponseBody
	public QualityCalendarRecordVO queryQualityCalendarRecordById(Long id) {
		QualityCalendarRecord c =  qualityCalendarRecordService.queryObjById(id);
		return model2vo(c);
	}
	/**
	 * 更新质量记录
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/updateQualityCalendarRecord.do")
	@ResponseBody
	public ModelMap updateQualityCalendarRecord(QualityCalendarRecord qualityCalendarRecord) {
		ModelMap modelMap = new ModelMap();
		qualityCalendarRecordService.updateObj(qualityCalendarRecord);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "更新成功!");
		return modelMap;
	}
	/**
	 * 根据id删除质量记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteQualityCalendarRecord.do")
	@ResponseBody
	public ModelMap deleteQualityCalendarRecord(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		qualityCalendarRecordService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}
} 
