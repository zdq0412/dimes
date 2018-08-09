package com.digitzones.dao;

import java.util.Date;

import com.digitzones.model.Classes;
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
	 * 查询从月初到给定时间的总损时(工厂级)
	 * @param date
	 * @param halt null:查询所有   true:只查询计划停机   false：只查询非计划停机
	 * @return
	 */
	public Integer queryLostTimeFromBeginOfMonthUntilTheDate(Date date,Boolean halt);
	/**
	 * 查询计划停机时间
	 * @param year
	 * @param month
	 * @return
	 */
	public Double queryHoursOfPlanHaltByYearAndMonth(Integer year,Integer month) ;
	/**
	 * 根据时间段查找损时信息
	 * @param c
	 * @param deviceSiteId
	 * @param date 损时日期
	 * @return
	 */
	public Double queryLostTime(Classes c,Long deviceSiteId,Date date);
	/**
	 * 查找计划停机时间
		@param c
	 * @param deviceSiteId
	 * @return
	 */
	public Double queryPlanHaltTime(Classes c,Long deviceSiteId,Date date);
	/**
	 * 每天损时
	 * @param c
	 * @param deviceSiteId
	 * @param date
	 * @return
	 */
	public Double queryLostTime4PerDay(Classes c,Long deviceSiteId,Date date);
	/**
	 * 根据年月日时分查询损时时间
	 * @param date
	 * @return
	 */
	public Integer queryLostTime4RealTime(Date date);
	/**
	 * 查询指定日期(年月日)的损时记录数
	 * @param date
	 * @return
	 */
	public Long queryLostTime4TheDate(Date date);
}
