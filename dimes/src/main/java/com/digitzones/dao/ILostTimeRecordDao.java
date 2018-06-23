package com.digitzones.dao;

import com.digitzones.model.LostTimeRecord;
/**
 * 损时记录dao
 * @author zdq
 * 2018年6月11日
 */
public interface ILostTimeRecordDao extends ICommonDao<LostTimeRecord> {
	/**
	 * 根据年月信息查询当月的总共的损时数
	 * @param year
	 * @param month
	 * @return
	 */
	public Double queryHoursOfLostTimeRecordByYearAndMonth(Integer year,Integer month) ;
	/**
	 * 查询计划停机时间
	 * @param year
	 * @param month
	 * @return
	 */
	public Double queryHoursOfPlanHaltByYearAndMonth(Integer year,Integer month) ;
}
