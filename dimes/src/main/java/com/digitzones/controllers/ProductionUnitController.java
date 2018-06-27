package com.digitzones.controllers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.model.Device;
import com.digitzones.model.DeviceSite;
import com.digitzones.model.Pager;
import com.digitzones.model.ProductionUnit;
import com.digitzones.service.IDeviceService;
import com.digitzones.service.IProductionUnitService;
import com.digitzones.vo.Node;
import com.digitzones.vo.ProductionUnitVO;
/**
 * 生产单元管理控制器
 * @author zdq
 * 2018年6月7日
 */
@Controller
@RequestMapping("/productionUnit")
public class ProductionUnitController {
	private IProductionUnitService productionUnitService;
	private IDeviceService deviceService;
	@Autowired
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	@Autowired
	public void setProductionUnitService(IProductionUnitService productionUnitService) {
		this.productionUnitService = productionUnitService;
	}
	/**
	 * 查询生产单元
	 * @return
	 */
	@RequestMapping("/queryTopProductionUnits.do")
	@ResponseBody
	public List<ProductionUnit> queryTopProductionUnits(){
		return productionUnitService.queryTopProductionUnits();
	}
	/**
	 * 查找设备站点树，结构为生产单元-->设备-->设备站点
	 * @return
	 */
	@RequestMapping("/queryDeviceSiteTree.do")
	@ResponseBody
	public List<Node> queryDeviceSiteTree() {
		//查找生产单元树形结构
		List<ProductionUnit> pus = productionUnitService.queryTopProductionUnits();
		List<Node> nodes = new ArrayList<>();
		objList2NodeList(nodes, pus);
		return nodes;
	}
	/**
	 * 构建树形结构，结构为：生产单元-->设备-->设备站点
	 * @param nodes
	 * @param pus
	 */
	private void objList2NodeList(List<Node> nodes,Collection<ProductionUnit> pus) {
		for(ProductionUnit pu : pus) {
			Node node = new Node();
			node.setId(pu.getId());
			node.setName(pu.getName());
			nodes.add(node);
			List<Node> nodeList = new ArrayList<Node>();
			node.setChildren(nodeList);
			if(pu.getChildren()!=null &&pu.getChildren().size()>0) {
				objList2NodeList(nodeList, pu.getChildren());
			}else {
				//根据生产单元id查询设备
				List<Device> devices = deviceService.queryDevicesByProductionUnitId(pu.getId());
				for(Device d : devices) {
					Node no = new Node();
					no.setId(d.getId());
					no.setName(d.getName());
					nodeList.add(no);
					
					
					List<Node> dsList = new ArrayList<>();
					no.setChildren(dsList);
					Set<DeviceSite> deviceSites = d.getDeviceSites();
					for(DeviceSite ds : deviceSites) {
						Node n = new Node();
						n.setId(ds.getId());
						n.setName(ds.getName());
						dsList.add(n);
					}
				}
			}
		}
	}
	/**
	 * 分页查询生产单元
	 * @param pid
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryProductionUnitsByParentId.do")
	@ResponseBody
	public ModelMap queryProductionUnitsByParentId(@RequestParam(value="pid",required=false)Long pid,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page) {
		Pager<Object[]> pager = null;
		if(pid==null) {
			pager = productionUnitService.queryObjs("from ProductionUnit pu inner join pu.parent p ", page, rows, new Object[] {});
		}else {
			pager = productionUnitService.queryObjs("from ProductionUnit pu inner join pu.parent p  where p.id=?0", page, rows, new Object[] {pid});
		}

		Pager<ProductionUnitVO> pagerProductionUnitVO = new Pager<>();
		model2VO(pager, pagerProductionUnitVO);
		ModelMap mm = new ModelMap();
		mm.addAttribute("rows",pagerProductionUnitVO.getData());
		mm.addAttribute("total", pager.getTotalCount());
		mm.addAttribute("code", "0");
		mm.addAttribute("msg", "");
		return mm;
	}

	private void model2VO(Pager<Object[]> pagerProductionUnit,Pager<ProductionUnitVO> ProductionUnitVO) {
		List<Object[]> productionUnits = pagerProductionUnit.getData();
		List<ProductionUnitVO> productionUnitVOs = ProductionUnitVO.getData();
		for(Object[] obj:productionUnits) {
			ProductionUnitVO vo = new ProductionUnitVO();
			ProductionUnit son = (ProductionUnit) obj[0];
			ProductionUnit parent = (ProductionUnit) obj[1];

			vo.setId(son.getId());
			vo.setName(son.getName());
			parent.setChildren(null);
			vo.setParent(parent);
			vo.setCode(son.getCode());
			vo.setDisabled(son.getDisabled());
			vo.setNote(son.getNote());
			productionUnitVOs.add(vo);
		}
	}

	/**
	 * 添加生产单元
	 * @param department
	 * @return
	 */
	@RequestMapping("/addProductionUnit.do")
	@ResponseBody
	public ModelMap addProductionUnit(ProductionUnit productionUnit) {
		ModelMap modelMap = new ModelMap();
		ProductionUnit productionUnit4Code = productionUnitService.queryByProperty("code", productionUnit.getCode());
		if(productionUnit4Code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "生产单元编码已被使用");
		}else {
			ProductionUnit	productionUnit4Name = productionUnitService.queryByProperty("name",productionUnit.getName());
			if(productionUnit4Name!=null) {
				modelMap.addAttribute("success", false);
				modelMap.addAttribute("msg", "生产单元名称已被使用");
			}else {
				productionUnitService.addObj(productionUnit);
				modelMap.addAttribute("success", true);
				modelMap.addAttribute("msg", "添加成功!");
			}
		}
		return modelMap;
	}
	/**
	 * 根据id查询生产单元
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryProductionUnitById.do")
	@ResponseBody
	public ProductionUnit queryProductionUnitById(Long id) {
		ProductionUnit productionUnit = productionUnitService.queryObjById(id);
		return productionUnit;
	}
	
	/**
	 * 查询所有生产单元
	 * @return
	 */
	@RequestMapping("/queryAllProductionUnits.do")
	@ResponseBody
	public List<ProductionUnit> queryAllProductionUnits(){
		return productionUnitService.queryAllProductionUnits();
	}
	/**
	 * 更新部门
	 * @param department
	 * @return
	 */
	@RequestMapping("/updateProductionUnit.do")
	@ResponseBody
	public ModelMap updateProductionUnit(ProductionUnit productionUnit) {
		ModelMap modelMap = new ModelMap();
		ProductionUnit pu = productionUnitService.queryByProperty("name", productionUnit.getName());
		if(pu!=null && !productionUnit.getId().equals(pu.getId())) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "生产单元名称已被使用");
		}else {
			productionUnitService.updateObj(productionUnit);
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "编辑成功!");
		}
		return modelMap;
	}
	/**
	 * 根据id删除部门，如果该部门存在子部门，则不允许删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteProductionUnit.do")
	@ResponseBody
	public ModelMap deleteProductionUnit(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Long count = productionUnitService.queryCountOfSubProductionUnit(Long.valueOf(id));
		if(count>0) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("statusCode", 300);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("msg", "该生产单元下存在子部门，不允许删除!");
			modelMap.addAttribute("message", "该生产单元下存在子部门，不允许删除!");
		}else {
			productionUnitService.deleteObj(Long.valueOf(id));
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("statusCode", 200);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "成功删除!");
		}
		return modelMap;
	}
	/**
	 * 停用该部门
	 * @param id
	 * @return
	 */
	@RequestMapping("/disabledProductionUnit.do")
	@ResponseBody
	public ModelMap disabledProductionUnit(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		ProductionUnit d = productionUnitService.queryObjById(Long.valueOf(id));
		d.setDisabled(true);

		productionUnitService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}
} 
