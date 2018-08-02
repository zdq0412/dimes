package com.digitzones.controllers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitzones.config.QRConfig;
import com.digitzones.model.Device;
import com.digitzones.model.Pager;
import com.digitzones.model.ProductionUnit;
import com.digitzones.service.IDeviceService;
import com.digitzones.service.IProductionUnitService;
import com.digitzones.util.QREncoder;
import com.digitzones.vo.DeviceVO;
/**
 * 设备管理控制器
 * @author zdq
 * 2018年6月7日
 */
@Controller
@RequestMapping("/device")
public class DeviceController {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private QRConfig config ;
	@Autowired
	public void setConfig(QRConfig config) {
		this.config = config;
	}
	private QREncoder qrEncoder = new QREncoder();
	private IDeviceService deviceService;
	private IProductionUnitService productionUnitService;
	@Autowired
	public void setProductionUnitService(IProductionUnitService productionUnitService) {
		this.productionUnitService = productionUnitService;
	}

	@Autowired
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	/**
	 * 查找不在生产单元的设备
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryIdleDevices.do")
	@ResponseBody
	public ModelMap queryIdleDevices(@RequestParam(defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page,HttpServletRequest request){
		Pager<Device> pager = deviceService.queryObjs("from Device d where d.productionUnit is null", page, rows, new Object[] {});
		ModelMap modelMap = new ModelMap();
		List<DeviceVO> vos = new ArrayList<>();
		if(pager.getData()!=null && pager.getData().size()>0) {
			for(Device d : pager.getData()) {
				vos.add(model2VO(d, request));
			}
		}
		modelMap.addAttribute("total", pager.getTotalCount());
		modelMap.addAttribute("rows", vos);
		return modelMap;
	}
	/**
	 * 更新设备的生产单元信息
	 * @param productionUnitId 生产单元ID
	 * @param deviceIds 设备id字符串，形式如[11,22,33]
	 * @return
	 */
	@RequestMapping("/updateDeviceProductionUnit.do")
	@ResponseBody
	public ModelMap updateDeviceProductionUnit(Long productionUnitId,String deviceIds) {
		ModelMap modelMap = new ModelMap();
		if(deviceIds!=null) {
			if(deviceIds.contains("[")) {
				deviceIds = deviceIds.replace("[", "");
			}
			if(deviceIds.contains("]")) {
				deviceIds = deviceIds.replace("]", "");
			}

			String[] idArray = deviceIds.split(",");
			for(int i = 0;i<idArray.length;i++) {
				Device device = deviceService.queryObjById(Long.valueOf(idArray[i]));
				ProductionUnit pu = productionUnitService.queryObjById(productionUnitId);

				device.setProductionUnit(pu);

				deviceService.updateObj(device);
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
	 * 将设备的生产单元置为null
	 * @param productionUnitId
	 * @return
	 */
	@RequestMapping("/updateDeviceProductionUnitNull.do")
	@ResponseBody
	public ModelMap updateDeviceProductionUnitNull(String deviceId) {
		if(deviceId!=null && deviceId.contains("'")) {
			deviceId = deviceId.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Device device = deviceService.queryObjById(Long.valueOf(deviceId));
		device.setProductionUnit(null);
		deviceService.updateObj(device);
		modelMap.addAttribute("message", "操作完成!");
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		return modelMap;
	}

	/**
	 * 停用该设备
	 * @param id
	 * @return
	 */
	@RequestMapping("/disabledDevice.do")
	@ResponseBody
	public ModelMap disabledDevice(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		Device d = deviceService.queryObjById(Long.valueOf(id));
		d.setDisabled(true);

		deviceService.updateObj(d);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("message", "已停用");
		modelMap.addAttribute("title", "操作提示!");
		return modelMap;
	}

	/**
	 * 分页查询生产单元中的设备
	 * @param pid
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryDevicesByProductionUnitId.do")
	@ResponseBody
	public ModelMap queryDevicesByProductionUnitId(@RequestParam(value="productionUnitId",required=false)Long productionUnitId,@RequestParam(value="rows",defaultValue="20")Integer rows,@RequestParam(defaultValue="1")Integer page,HttpServletRequest request) {
		Pager<Device> pager = null;
		if(productionUnitId==null) {
			pager = deviceService.queryObjs("select d from Device d inner join d.productionUnit p ", page, rows, new Object[] {});
		}else if(productionUnitId==-1) {
			pager = deviceService.queryObjs("select d from Device d where d.productionUnit is null", page, rows, new Object[] {});
		}
		else {
			pager = deviceService.queryObjs("select d from Device d inner join d.productionUnit p  where p.id=?0", page, rows, new Object[] {productionUnitId});
		}

		ModelMap mm = new ModelMap();
		List<DeviceVO> vos = new ArrayList<>();
		if(pager.getData()!=null && pager.getData().size()>0) {
			for(Device d : pager.getData()) {
				vos.add(model2VO(d, request));
			}
		}
		mm.addAttribute("total", pager.getTotalCount());
		mm.addAttribute("rows", vos);
		mm.addAttribute("code", "0");
		mm.addAttribute("msg", "");
		return mm;
	}

	/**
	 * 在生产单元中添加设备
	 * @param department
	 * @return
	 */
	@RequestMapping("/addDevice.do")
	@ResponseBody
	public ModelMap addDevice(Device device,HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		Device device4Code = deviceService.queryByProperty("code", device.getCode());
		if(device4Code!=null) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("msg", "设备编码已被使用");
		}else {
			if(device.getPhotoName()!=null) {
				String dir = request.getServletContext().getRealPath("/");
				File file = new File(dir,device.getPhotoName());
				deviceService.addDevice(device,file);
			}else {
				deviceService.addObj(device);
			}
			modelMap.addAttribute("success", true);
			modelMap.addAttribute("msg", "添加成功!");
		}
		return modelMap;
	}
	@RequestMapping("/upload.do")
	@ResponseBody
	public ModelMap upload(Part file,HttpServletRequest request) {
		String dir = request.getServletContext().getRealPath("/")+"console/deviceImgs/";
		String realName = file.getSubmittedFileName();
		ModelMap modelMap = new ModelMap();
		String fileName = new Date().getTime()+ realName.substring(realName.lastIndexOf("."));
		InputStream is;
		try {
			is = file.getInputStream();
			File parentDir = new File(dir);
			if(!parentDir.exists()) {
				parentDir.mkdirs();
			}
			File out = new File(parentDir,fileName);
			FileCopyUtils.copy(is, new FileOutputStream(out));
			modelMap.addAttribute("statusCode", 200);
			modelMap.addAttribute("title", "操作提示");
			modelMap.addAttribute("message", "文件上传成功！");
			modelMap.addAttribute("filePath", "console/deviceImgs/" + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return modelMap;
	}
	/**
	 * 根据id查询设备
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryDeviceById.do")
	@ResponseBody
	public DeviceVO queryDeviceById(Long id,HttpServletRequest request) {
		Device device = deviceService.queryObjById(id);
		return model2VO(device,request);
	}
	/**
	 * 将领域模型转换为值对象
	 * @param device
	 * @return
	 */
	private DeviceVO model2VO(Device device,HttpServletRequest request) {
		if(device == null) {
			return null;
		}

		DeviceVO vo = new DeviceVO();
		vo.setId(device.getId());
		vo.setCode(device.getCode());
		vo.setName(device.getName());
		if(device.getInstallDate()!=null) {
			vo.setInstallDate(sdf.format(device.getInstallDate()));
		}
		vo.setInstallPosition(device.getInstallPosition());
		vo.setDeviceType(device.getDeviceType());
		vo.setManufacturer(device.getManufacturer());
		vo.setNote(device.getNote());
		vo.setBottleneck(device.getBottleneck().toString());
		vo.setOutFactoryCode(device.getOutFactoryCode());
		if(device.getPhoto()!=null) {
			String dir = request.getServletContext().getRealPath("/");
			String realName = device.getPhotoName();
			String fileName = realName;
			InputStream is;
			try {
				is = device.getPhoto().getBinaryStream();
				File out = new File(dir,fileName);
				FileCopyUtils.copy(is, new FileOutputStream(out));
				vo.setPhoto(dir + "/" + fileName);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		vo.setOutFactoryDate(device.getOutFactoryDate());
		vo.setParameterValueType(device.getParameterValueType());
		vo.setUnitType(device.getUnitType());
		vo.setDeviceType(device.getDeviceType());
		vo.setProductionUnit(device.getProductionUnit());
		vo.setTrader(device.getTrader());
		vo.setStatus(device.getStatus());
		return vo;
	}
	/**
	 * 更新部门
	 * @param department
	 * @return
	 */
	@RequestMapping("/updateDevice.do")
	@ResponseBody
	public ModelMap updateDevice(Device device) {
		ModelMap modelMap = new ModelMap();
		deviceService.updateObj(device);
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("msg", "编辑成功!");
		return modelMap;
	}

	/**
	 * 根据id删除设备
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteDevice.do")
	@ResponseBody
	public ModelMap deleteDevice(String id) {
		if(id!=null && id.contains("'")) {
			id = id.replace("'", "");
		}
		ModelMap modelMap = new ModelMap();
		deviceService.deleteObj(Long.valueOf(id));
		modelMap.addAttribute("success", true);
		modelMap.addAttribute("statusCode", 200);
		modelMap.addAttribute("title", "操作提示");
		modelMap.addAttribute("message", "成功删除!");
		return modelMap;
	}

	/**
	 * 打印设备的二维码
	 * @param ids 设备id字符串
	 * @return
	 */
	@RequestMapping("/printQr.do")
	@ResponseBody
	public List<DeviceVO> printQr(String ids,HttpServletRequest request) {
		String dir = request.getServletContext().getRealPath("/");
		List<DeviceVO> vos = new ArrayList<>();
		String[] idStr = ids.split(",");
		for(int i = 0 ;i<idStr.length;i++) {
			String id = idStr[i];
			Device e = deviceService.queryObjById(Long.valueOf(id));
			DeviceVO vo = model2VO(e,request);
			vo.setQrPath(qrEncoder.generatePR(e.getCode(),dir , config.getQrPath()));
			vos.add(vo);
		}
		return vos;
	}
} 
