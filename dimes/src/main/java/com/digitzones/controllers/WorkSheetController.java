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
import com.digitzones.model.WorkSheetDetail;
import com.digitzones.model.Workpiece;
import com.digitzones.service.IWorkSheetService;
@Controller
@RequestMapping("/workSheet")
public class WorkSheetController {
	private IWorkSheetService workSheetService;
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
		Pager<Workpiece> pager = workSheetService.queryObjs("select d from WorkSheet d where d.productionUnitId=?0", page, rows, new Object[] {productionUnitId});
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pager.getData());
		mm.addAttribute("total", pager.getTotalCount());
		return mm;
	}
	/**
	 * 查询
	 * @return
	 */
	public List<WorkSheetDetail> queryWorkSheetDetailsInMemoryByWorkpieceId(Long workpieceId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page){
		
	}
} 
