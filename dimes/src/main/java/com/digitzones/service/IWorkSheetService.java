package com.digitzones.service;

import java.util.List;

import com.digitzones.model.WorkSheet;
import com.digitzones.model.WorkSheetDetail;
/**
 * 工单service
 * @author zdq
 * 2018年6月20日
 */
public interface IWorkSheetService extends ICommonService<WorkSheet> {
	/**
	 * 根据设备站点id查询非当前站点下的工单
	 * @param deviceSiteId
	 * @return
	 */
	public List<WorkSheet> queryOtherWorkSheetByDeviceSiteId(Long deviceSiteId);
	/**
	 * 根据站点id查找工单信息
	 * @param deviceSiteId
	 * @return
	 */
	public List<WorkSheet> queryWorkSheetsByDeviceSiteId(Long deviceSiteId);
	/**
	 * 根据站点id和条件查询工单
	 * @param deviceSiteId
	 * @param q
	 * @return
	 */
	public List<WorkSheet> queryWorkSheetsByDeviceSiteIdAndConditions(Long deviceSiteId,String q);
	/**
	 * 根据设备站点id及 条件查询工单
	 * @param deviceSiteId
	 * @param q
	 * @return
	 */
	public List<WorkSheet> queryOtherWorkSheetByDeviceSiteIdAndConditions(Long deviceSiteId,String q);
	/**
	 * 添加工单
	 * @param workSheet
	 */
	public void addWorkSheet(WorkSheet workSheet);
	/**
	 * 更新工单及工单详情
	 * @param workSheet
	 * @param workSheetDetails
	 */
	public void updateWorkSheetAndWorkSheetDetails(WorkSheet workSheet,List<WorkSheetDetail> workSheetDetails);
}
