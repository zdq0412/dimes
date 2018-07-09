package com.digitzones.service;

import java.util.List;

import com.digitzones.model.Pager;
import com.digitzones.model.ProcessDeviceSiteMapping;
import com.digitzones.model.WorkSheetDetail;
/**
 * 工单详情service
 * @author zdq
 * 2018年6月20日
 */
public interface IWorkSheetDetailService extends ICommonService<WorkSheetDetail> {
	/**
	 * 根据工单id查找该工单详情
	 * @param workSheetId
	 * @return
	 */
	public List<WorkSheetDetail> queryWorkSheetDetailsByWorkSheetId(Long workSheetId);
	/**
	 * 根据工件id查询工序，设备站点等信息，并在内存中构建为WorkSheetDetail类型的列表 
	 * @param workpieceId
	 * @return
	 */
	public void buildWorkSheetDetailListInMemoryByWorkpieceId(Long workpieceId);
	/**
	 * 根据工序id查询设备站点 
	 * @param processId
	 * @return
	 */
	public Pager<ProcessDeviceSiteMapping> queryOtherDeviceSitesByProcessId(Long processId,int pageNo, int pageSize);
	/**
	 * 查询该工序下的工单详情数
	 * @param processId
	 * @return
	 */
	public Long queryCountByProcessId(Long processId,Long workSheetId);
	
	/**
	 * 根据工单id和设备站点id查找工单详情
	 * @param workSheetId
	 * @param deviceSiteId
	 * @return
	 */
	public List<WorkSheetDetail> queryWorkSheetDetailByWorkSheetIdAndDeviceSiteId(Long workSheetId,Long deviceSiteId);
	/**
	 * 根据工单id，站点id和工序代码查找工单详情
	 * @param workSheetId
	 * @param deviceSiteId
	 * @param processCode
	 * @return
	 */
	public List<WorkSheetDetail> queryWorkSheetDetailByWorkSheetIdAndDeviceSiteIdAndProccessId(Long workSheetId,Long deviceSiteId,String processCode);
}
