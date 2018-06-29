package com.digitzones.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 装备类型管理控制器
 * @author zdq
 * 2018年6月7日
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Pager;
import com.digitzones.model.EquipmentType;
import com.digitzones.service.IEquipmentTypeService;
import com.digitzones.vo.EquipmentTypeVO;
@Controller
@RequestMapping("/equipmentType")
public class EquipmentTypeController {
	private IEquipmentTypeService equipmentTypeService;
	@Autowired
	public void setEquipmentTypeService(IEquipmentTypeService equipmentTypeService) {
		this.equipmentTypeService = equipmentTypeService;
	}
	/**
	 * 查询生产单元
	 * @return
	 */
	@RequestMapping("/queryTopEquipmentTypes.do")
	@ResponseBody
	public List<EquipmentType> queryTopEquipmentTypes(){
		return equipmentTypeService.queryTopEquipmentType();
	}
	/**
	 * 分页查询装备类型
	 * @param pid
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryEquipmentTypesByParentId.do")
	@ResponseBody
	public ModelMap queryEquipmentTypesByParentId(@RequestParam(value="pid",required=false)Long pid,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<Object[]> pager = null;
		pager = equipmentTypeService.queryObjs("from EquipmentType pt inner join pt.parent p  where p.id=?0", page, rows, new Object[] {pid});
		Pager<EquipmentTypeVO> pagerEquipmentTypeVO = new Pager<>();
		model2VO(pager, pagerEquipmentTypeVO);
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pagerEquipmentTypeVO.getData());
		mm.addAttribute("total", pager.getTotalCount());
		mm.addAttribute("code", "0");
		mm.addAttribute("msg", "");
		return mm;
	}

	private void model2VO(Pager<Object[]> pagerEquipmentType,Pager<EquipmentTypeVO> equipmentTypeVO) {
		List<Object[]> equipmentTypes = pagerEquipmentType.getData();
		List<EquipmentTypeVO> equipmentTypeVOs = equipmentTypeVO.getData();
		for(Object[] obj:equipmentTypes) {
			EquipmentTypeVO vo = new EquipmentTypeVO();
			EquipmentType son = (EquipmentType) obj[0];
			EquipmentType parent = (EquipmentType) obj[1];

			vo.setId(son.getId());
			vo.setName(son.getName());
			parent.setChildren(null);
			vo.setParent(parent);
			vo.setCode(son.getCode());
			vo.setDisabled(son.getDisabled());
			vo.setNote(son.getNote());
			vo.setBaseCode(son.getBaseCode());
			equipmentTypeVOs.add(vo);

		}
	}
	/**
	 * 添加装备类型
	 * @param department
	 * @return
	 */
	@RequestMapping("/addEquipmentType.do")
	@ResponseBody
	public ModelMap addEquipmentType(EquipmentType equipmentType) {
		ModelMap modelMap = new ModelMap();
		EquipmentType equipmentType4Code = equipmentTypeService.queryByProperty("code", equipmentType.getCode());
		if(equipmentType4Code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "装备类别编码已被使用");
		}else {
			equipmentTypeService.addObj(equipmentType);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "添加成功!");
		}
		return modelMap;
	}
	/**
	 * 根据id查询装备类型
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryEquipmentTypeById.do")
	@ResponseBody
	public EquipmentType queryEquipmentTypeById(Long id) {
		EquipmentType equipmentType = equipmentTypeService.queryObjById(id);
		return equipmentType;
	}
	/**
	 * 更新装备类型
	 * @param department
	 * @return
	 */
	@RequestMapping("/updateEquipmentType.do")
	@ResponseBody
	public ModelMap updateEquipmentType(EquipmentType equipmentType) {
		ModelMap modelMap = new ModelMap();
		equipmentTypeService.updateObj(equipmentType);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "编辑成功!");
		return modelMap;
	}
	/**
	 * 根据id删除装备类型
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteEquipmentType.do")
	@ResponseBody
	public ModelMap deleteEquipmentType(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Long count = equipmentTypeService.queryCountOfSubEquipmentType(Long.valueOf(id));
		Long equipmentCount = equipmentTypeService.queryCountOfEquipment(Long.valueOf(id));
		if(count>0 || equipmentCount>0) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("msg", "该装备类型下存在子类型或装备，不允许删除!");
			modelMap.addAttribute("message", "该装备类型下存在子类型或装备，不允许删除!");
		}else {
			equipmentTypeService.deleteObj(Long.valueOf(id));
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("statusCode", 200);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "成功删除!");
		}
		return modelMap;
	}
	/**
	 * 停用该装备类型
	 * @param id
	 * @return
	 */
	@RequestMapping("/disabledEquipmentType.do")
	@ResponseBody
	public ModelMap disabledEquipmentType(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		EquipmentType d = equipmentTypeService.queryObjById(Long.valueOf(id));
		d.setDisabled(true);

		equipmentTypeService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}
} 
