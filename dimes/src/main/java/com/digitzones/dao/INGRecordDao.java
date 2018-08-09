package com.digitzones.dao;

import java.util.Date;

import com.digitzones.model.Classes;
import com.digitzones.model.NGRecord;
/**
 * 不合格品记录dao
 * @author zdq
 * 2018年7月8日
 */
public interface INGRecordDao extends ICommonDao<NGRecord> {
	/**
	 * 根据工序id和日期（天）查找报废品数量 
	 * @param date
	 * @param processId
	 * @return
	 */
	public Integer queryScrapCountByDateAndProcessId(Date date,Long processId);
	/**
	 * 根据设备站点id查询当天ng数量
	 * @param deviceSiteId
	 * @return
	 */
	public Integer queryNgCountByDeviceSiteId(Long deviceSiteId,Date today);
	/**
	 * 查找当前班，该设备上的实时NG数
	 * @param classes
	 * @param deviceSiteId
	 * @return
	 */
	public Integer queryNgCount4Class(Classes classes ,Long deviceSiteId,Date date);
	/**
	 * 查询从月初到给定时间的NG数
	 * @param date
	 * @return
	 */
	public Integer queryNgCountFromBeginOfMonthUntilTheDate(Date date);
	/**
	 * 查询指定日期的ng记录数
	 * @param date
	 * @return
	 */
	public Long queryNgCount4TheDate(Date date);
}
