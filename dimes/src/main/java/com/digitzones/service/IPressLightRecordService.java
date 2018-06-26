package com.digitzones.service;

import java.util.Date;

import com.digitzones.model.PressLightRecord;
/**
 * 按灯(故障)记录service
 * @author zdq
 * 2018年6月20日
 */
public interface IPressLightRecordService extends ICommonService<PressLightRecord> {
	/**
	 * 根据按灯日期查询按灯次数
	 * @param pressLightTime
	 * @return
	 */
	public Long queryCountByPressLightTime(Date pressLightTime);
}
