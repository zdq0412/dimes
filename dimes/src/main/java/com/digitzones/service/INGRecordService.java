package com.digitzones.service;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.digitzones.model.NGRecord;
import com.digitzones.model.User;
/**
 * 不合格记录service
 * @author zdq
 * 2018年7月8日
 */
public interface INGRecordService extends ICommonService<NGRecord> {
	/**
	 * 审核不合格记录，会影响工单详情中的报废和返修数量
	 * @param record
	 */
	public void auditNGRecord(NGRecord record,User user,Map<String,Object> args);
	/**
	 * 复核
	 * @param record
	 * @param user
	 * @param args
	 */
	public void reviewNGRecord(NGRecord record,User user,Map<String,Object> args);
	/**
	 * 确认
	 * @param record
	 * @param user
	 * @param args
	 */
	public void confirmNGRecord(NGRecord record,User user,Map<String,Object> args);
	/**
	 * 设置删除标志
	 * @param record
	 */
	public void deleteNGRecord(NGRecord record);
	
	
	/**
	 * 添加NG记录
	 * @param record
	 * @param user
	 * @param args
	 */
	public Serializable addNGRecord(NGRecord record,User user,Map<String,Object> args);
	
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
}
