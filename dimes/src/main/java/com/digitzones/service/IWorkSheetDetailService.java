package com.digitzones.service;

import java.util.List;

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
}
