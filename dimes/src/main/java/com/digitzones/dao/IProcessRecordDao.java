package com.digitzones.dao;

import java.util.Date;
import java.util.List;

import com.digitzones.model.ProcessRecord;
/**
 * 加工记录dao
 * @author zdq
 * 2018年6月20日
 */
public interface IProcessRecordDao extends ICommonDao<ProcessRecord> {
	/**
	 * 查询当前工序、工件、设备站点下的,当前班次的，该状态的加工记录数
	 * @param deviceSiteId
	 * @param status
	 * @return List<Long[]> :工件id，工序id，设备站点id,班次id，记录数
	 */
	public List<Long[]> queryCountByDeviceSiteIdAndStatus(Long deviceSiteId,String status);
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
	 * 查询上个月工序、工件、设备站点下的,当前班次的，该状态的加工记录数
	 * @param deviceSiteId
	 * @param status
	 * @return List<Long[]> :工件id，工序id，设备站点id,班次id，记录数
	 */
	public List<Long[]> queryPreMonthDeviceSiteIdAndStatus(Long deviceSiteId,String status);
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
}
