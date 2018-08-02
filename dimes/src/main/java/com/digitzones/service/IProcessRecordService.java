package com.digitzones.service;

import java.util.Date;
import java.util.List;

import com.digitzones.model.ProcessRecord;
/**
 * 加工信息service
 * @author zdq
 * 2018年6月20日
 */
public interface IProcessRecordService extends ICommonService<ProcessRecord> {
	/**
	 * 查询当天的加工数量
	 * @param deviceSiteId
	 * @return
	 */
	public Long queryCurrentDayCountByDeviceSiteId(Long deviceSiteId);
	/**
	 * 根据日期(天)查询工序、工件、设备站点id及该状态下的记录数
	 * @param deviceSiteId
	 * @param status
	 * @param now
	 * @return
	 */
	public List<Long[]> queryByDay(Long deviceSiteId, String status, Date now) ;
	/**
	 * 查找当前月份的人员产量
	 * @param date
	 * @param empId
	 * @return
	 */
	public Integer queryOutput4EmployeePerMonth(Date date,Long empId);
	/**
	 * 查找当前月下的工序产量
	 * @param date
	 * @param processId
	 * @return
	 */
	public Integer queryOutput4ProcessPerMonth(Date date,Long processId);
	/**
	 * 查找当前月下的站点产量
	 * @param date
	 * @return
	 */
	public int queryOutput4DeviceSitePerMonth(Date date,Long deviceSiteId);
	/**
	 * 查找当前月 下的不合格数
	 * @param date
	 * @return
	 */
	public Integer queryWorkSheetNGCountPerMonth(Date date);
	/**
	 * 查找报废品数量
	 * @param year
	 * @param month
	 * @param ngTypeId
	 * @return
	 */
	public Integer queryWorkSheetScrapCountPerMonth(Date date,Long ngTypeId);
	/**
	 * 查找当月所有的报废数量
	 * @param date
	 * @return
	 */
	public Integer queryWorkSheetScrapCountPerMonth(Date date);
	
	/**
	 * 查找当前天，当前班次的不合格数：产线级
	 * @param date
	 * @param classId
	 * @param productionUnitId
	 * @return
	 */
	public Integer queryWorkSheetNGCountPerClasses4ProductionUnit(Date date,Long classId,Long productionUnitId);
	/**
	 * 查找当前月下的合格数
	 * @param date
	 * @return
	 */
	public Integer queryWorkSHeetNotNGCountPerMonth(Date date);
	/**
	 * 查找当前天，当前班次的合格数：产线级
	 * @param date
	 * @param classId
	 * @param productionUnitId
	 * @return
	 */
	public Integer queryWorkSHeetNotNGCountPerClasses4ProductionUnit(Date date,Long classId,Long productionUnitId);
	/**
	 * 根据班次id和日查找产量
	 * @param classesId
	 * @param day
	 * @return
	 */
	public Integer queryCountByClassesIdAndDay(Long classesId,Date day,Long productionUnitId);
}
