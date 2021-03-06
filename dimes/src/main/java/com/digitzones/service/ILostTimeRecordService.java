package com.digitzones.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.digitzones.model.Classes;
import com.digitzones.model.LostTimeRecord;
import com.digitzones.model.User;
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
	 * 查询当天损时时间
	 * @param c
	 * @param deviceSiteId
	 * @param date 损时日期
	 * @return
	 */
	public Double queryLostTime(Classes c,Long deviceSiteId,Date date);
	/**
	 * 查询当天计划停机时间
	 * @param c
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
	 * 损时确认
	 * @param lostTimeRecord
	 */
	public void confirm(LostTimeRecord lostTimeRecord,User user,Map<String,Object> args);
	/**
	 * 添加损时记录
	 * @param lostTimeRecord
	 * @param user
	 * @return
	 */
	public Serializable addLostTimeRecord(LostTimeRecord lostTimeRecord,User user,Map<String,Object> args);
	/**
	 * 设置删除标志
	 * @param lostTimeRecord
	 */
	public void deleteLostTimeRecord(LostTimeRecord lostTimeRecord);
	/**
	 * 查询从月初到给定时间的总损时(工厂级)
	 * @param date
	 * @param halt null:查询所有   true:只查询计划停机   false：只查询非计划停机
	 * @return
	 */
	public Integer queryLostTimeFromBeginOfMonthUntilTheDate(Date date,Boolean halt);
	/**
	 * 查询指定日期(年月日)的损时记录数
	 * @param date
	 * @return
	 */
	public Long queryLostTime4TheDate(Date date);
}
