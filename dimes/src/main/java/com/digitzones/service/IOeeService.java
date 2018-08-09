package com.digitzones.service;

import java.util.Date;
import java.util.List;

import com.digitzones.model.Classes;

/**
 * oee业务逻辑
 * @author zdq
 * 2018年7月25日
 */
public interface IOeeService {
	/**
	 * 根据站点id查询当天的损时数
	 * @param today
	 * @return
	 */
	public Integer queryLostTimeByDeviceSiteId(Date today,Long deviceSiteId,Classes c);
	/**
	 * 查询设备站点当天的NG信息
	 * @param today
	 * @param deviceSiteId
	 * @return
	 */
	public List<Object[]> queryNGInfo4CurrentDay(Date today,Long deviceSiteId);
	/**
	 * 根据工件、工序、站点id查询加工节拍
	 * @param workpieceId
	 * @param processId
	 * @param deviceSiteId
	 * @return
	 */
	public Float queryProcessingBeatByWorkpieceIdAndProcessIdAndDeviceSiteId(Long workpieceId,Long processId,Long deviceSiteId);

	/**
	 * 查询设备站点当月的NG信息
	 * @param deviceSiteId
	 * @return
	 */
	public List<Object[]> queryNGInfo4CurrentMonth(Long deviceSiteId);
	/**
	 * 查询设备站点上月的NG信息
	 * @param deviceSiteId
	 * @return
	 */
	public List<Object[]> queryNGInfo4PreMonth(Long deviceSiteId);
}
