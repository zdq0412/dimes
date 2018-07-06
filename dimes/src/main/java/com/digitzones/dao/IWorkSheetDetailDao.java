package com.digitzones.dao;

import java.util.List;

import com.digitzones.model.Pager;
import com.digitzones.model.ProcessDeviceSiteMapping;
import com.digitzones.model.WorkSheetDetail;
/**
 * 工单详情dao
 * @author zdq
 * 2018年6月20日
 */
public interface IWorkSheetDetailDao extends ICommonDao<WorkSheetDetail> {
	/**
	 * 查询不在[deviceSiteIdList]的设备站点
	 * @param deviceSiteIdList 设备站点List
	 * @param processId 工序id
	 * @return
	 */
	public Pager<ProcessDeviceSiteMapping> queryDeviceSiteOutOfListByProcessId(List<Long> deviceSiteIdList,Long processId,int pageNo, int pageSize);

	/**
	 * 根据工序id和工单id查找工单详情数
	 * @param processId
	 * @param workSheetID
	 * @return
	 */
	public Long queryCountByProcessIdAndWorkSheetId(Long processId,Long workSheetID);
}
