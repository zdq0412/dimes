package com.digitzones.service;

import java.util.List;

import com.digitzones.model.Classes;
import com.digitzones.model.LostTimeRecord;
/**
 * 损时记录service
 * @author zdq
 * 2018年6月11日
 */
public interface ILostTimeRecordService extends ICommonService<LostTimeRecord> {
	/**
	 * 根据年和月份查询当前月的损时记录
	 * @param year
	 * @param month
	 * @return
	 */
	public List<LostTimeRecord> queryLostTimeRecordByYearAndMonth(Integer year,Integer month);
	/**
	 * 根据年月信息查询当月的总共的损时数
	 * @param year
	 * @param month
	 * @return
	 */
	public Double queryHoursOfLostTimeRecordByYearAndMonth(Integer year,Integer month) ;
	/**
	 * 根据年月信息查询当月的总共的计划停机数
	 * @param year
	 * @param month
	 * @return
	 */
	public Double queryHoursOfPlanHaltByYearAndMonth(Integer year,Integer month) ;
	/**
	 * 根据时间段查找损时信息
	 * @param begin
	 * @param end
	 * @return
	 */
	public Double queryLostTimeByTime(Classes c,Long deviceSiteId);
}
