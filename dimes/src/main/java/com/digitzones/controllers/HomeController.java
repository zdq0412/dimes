package com.digitzones.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digitzones.service.ILostTimeRecordService;
import com.digitzones.service.INGRecordService;
import com.digitzones.service.IPressLightRecordService;
@Controller
@RequestMapping("/home")
public class HomeController {
	private INGRecordService ngRecordService;
	private ILostTimeRecordService lostTimeService;
	private IPressLightRecordService pressLightRecordService;
	@Autowired
	public void setNgRecordService(@Qualifier("ngRecordServiceProxy")INGRecordService ngRecordService) {
		this.ngRecordService = ngRecordService;
	}
	@Autowired
	public void setLostTimeService(@Qualifier("lostTimeRecordServiceProxy")ILostTimeRecordService lostTimeService) {
		this.lostTimeService = lostTimeService;
	}
	@Autowired
	public void setPressLightRecordService(@Qualifier("pressLightRecordServiceProxy")IPressLightRecordService pressLightRecordService) {
		this.pressLightRecordService = pressLightRecordService;
	}
	
	
} 
