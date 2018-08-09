package com.digitzones.dao;

import java.util.Date;
import java.util.List;

import com.digitzones.model.Classes;
import com.digitzones.model.ProcessRecord;
/**
 * 加工记录dao
 * @author zdq
 * 2018年6月20日
 */
public interface IProcessRecordDao extends ICommonDao<ProcessRecord> {
	/**
	 * 根据设备站点id查询当天的加工数量
	 * @param deviceSiteId
	 * @return 
	 */
	public Long queryCountByDeviceSiteIdAndNotNg(Long deviceSiteId);
	/**
	 * 查询当天的加工数量
	 * @param deviceSiteId
	 * @return
	 */
	public Long queryCurrentDayCountByDeviceSiteId(Long deviceSiteId);
	/**
	 * 查询当前月工序、工件、设备站点下的,当前班次的，该状态的加工记录数
	 * @param deviceSiteId
	 * @param status
	 * @return List<Long[]> :工件id，工序id，设备站点id,班次id，记录数
	 */
	public List<Long[]> queryCurrentMonthDeviceSiteIdAndStatus(Long deviceSiteId,String status);
	/**
	 * 根据日期(天)查询工序、工件、设备站点id及该状态下的记录数
	 * @param deviceSiteId
	 * @param status
	 * @param day
	 * @return
	 */
	public List<Long[]> queryByDay(Long deviceSiteId,String status,Date day);
	/**
	 * 查找当前月下的人员产量
	 * @param year
	 * @param month
	 * @param empId
	 * @return
	 */
	public Integer queryOutput4EmployeePerMonth(int year,int month,Long empId);
	/**
	 * 查找当前月下的工序产量
	 * @param year
	 * @param month
	 * @param processId
	 * @return
	 */
	public Integer queryOutput4ProcessPerMonth(int year,int month,Long processId);
	/**
	 * 查找当前月下的设备站点产量
	 * @param year
	 * @param month
	 * @return
	 */
	public Integer queryOutput4DeviceSitePerMonth(int year,int month,Long deviceSiteId);
	/**
	 * 查找当前月 下的不合格数
	 * @param year
	 * @param month
	 * @return
	 */
	public Integer queryWorkSheetNGCountPerMonth(int year,int month);
	/**
	 * 查找报废品数量
	 * @param year
	 * @param month
	 * @param ngTypeId
	 * @return
	 */
	public Integer queryWorkSheetScrapCountPerMonth(int year,int month,Long ngTypeId);
	/**
	 * 查找当月所有类别的报废数量
	 * @param year
	 * @param month
	 * @return
	 */
	public Integer queryWorkSheetScrapCountPerMonth(int year,int month);
	/**
	 * 查找当前月下的合格数
	 * @param year
	 * @param month
	 * @return
	 */
	public Integer queryWorkSHeetNotNGCountPerMonth(int year,int month);
	/**
	 * 查找当前天，当前班次的不合格数
	 * @param year
	 * @param month
	 * @return
	 */
	public Integer queryWorkSheetNGCountPerClasses4ProductionUnit(int year,int month,int day,Long classId,Long productionUnitId);
	/**
	 * 查找当天当前班次的合格数
	 * @param year
	 * @param month
	 * @return
	 */
	public Integer queryWorkSHeetNotNGCountPerClasses4ProductionUnit(int year,int month,int day,Long classId,Long productionUnitId);
	/**
	 * 根据班次id和日查找产量
	 * @param classesId
	 * @param day
	 * @return
	 */
	public Integer queryCountByClassesIdAndDay(Long classesId,Date day,Long productionUnitId);
	/**
	 * 查询当前班次下的给定设备站点的加工数量、总标准节拍、总短停机时间
	 * @param c
	 * @param deviceSiteId
	 * @return 0:生产数量 1:总标准节拍 2:总短停机时间
	 */
	public Object[] queryCountAndSumOfStandardBeatAndSumOfShortHalt4CurrentClass(Classes c,Long deviceSiteId,Date date);
	/**
	 * 查询从月初一直到给定时间的加工总数、总标准节拍和总短停机时间
	 * @param date
	 * @return 0:生产数量 1:总标准节拍 2:总短停机时间
	 */
	public Object[] queryCountAndSumOfStandardBeatAndSumOfShortHaltFromBeginOfMonthUntilTheDate(Date date);
}
