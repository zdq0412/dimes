package com.digitzones.service;

import java.util.List;

import com.digitzones.model.Processes;
/**
 * 工序service
 * @author zdq
 * 2018年6月14日
 */
public interface IProcessesService extends ICommonService<Processes> {
	/**
	 * 根据工件id和设备站点id查找工序
	 * @param workpieceId
	 * @param deviceSiteId
	 * @return
	 */
	public List<Processes> queryProcessByWorkpieceIdAndDeviceSiteId(Long workpieceId,Long deviceSiteId);
	/**
	 * 查找所有工序
	 * @return
	 */
	public List<Processes> queryAllProcesses();
	
	/**
	 * 查找不属于当前员工的工序信息
	 * @param employeeId
	 * @return
	 */
	public List<Processes> queryOtherProcessesByEmployeeId(Long employeeId);
	/**
	 * 根据条件查找不属于当前员工的工序信息
	 * @param employeeId
	 * @param q
	 * @return
	 */
	public List<Processes> queryOtherProcessesByEmployeeIdAndCondition(Long employeeId,String q);
	/**
	 * 根据工序类型id查找工序
	 * @param processTypeId
	 * @return
	 */
	public List<Processes> queryProcessesByTypeId(Long processTypeId);
}
