package com.digitzones.constants;
/**
 * 常量定义
 * @author zdq
 * 2018年6月8日
 */
import java.util.ArrayList;
import java.util.List;

import com.digitzones.model.WorkSheetDetail;
public class Constant {
	/**
	 * 工单详情静态内存存储列表
	 */
	public static List<WorkSheetDetail> workSheetDetail = new ArrayList<>();
	/**
	 * 存储工件id，只在添加工单时使用
	 */
	public static String WORKPIECEID = "UNSIGNED";
	/**
	 * 相关文档相关的常量
	 */
	public static class RelatedDoc{
		/**跟部门相关的文档*/
		public static final String DEPARTMENT = "DEPARTMENT";
	}
	/**
	 * 设备站点常量
	 * @author zdq
	 * 2018年6月23日
	 */
	public static class DeviceSite{
		/**站点运行状态：运行*/
		public static final String RUNNING = "0";
		/**站点运行状态：待机*/
		public static final String STANDBY = "1";
		/**站点运行状态：停机*/
		public static final String HALT = "2";
	}
	/**
	 * 用户常量
	 * @author zdq
	 * 2018年6月23日
	 */
	public static class User{
		/**存放在session中登录用户的key*/
		public static final String LOGIN_USER = "loginUser";
	}
	/**
	 * 加工信息常量
	 * @author zdq
	 * 2018年6月25日
	 */
	public static class ProcessRecord{
		/**不良品*/
		public static final String NG = "ng";
		public static final String OK = "ok";
		/**返修*/
		public static final String REPAIR = "repair";
		/**报废*/
		public static final String SCRAP = "scrap";
		/**让步接收*/
		public static final String COMPROMISE = "compromise";
		
	}
	/**
	 * 参数类型
	 * @author zdq
	 * 2018年6月27日
	 */
	public static class ParameterType{
		/**工艺参数*/
		public static final String ART="ART";
		/**设备参数*/
		public static final String DEVICE="DEVICE";
	}
	/**
	 * 装备类型
	 * @author zdq
	 * 2018年6月27日
	 */
	public static class EquipmentType{
		/**装备*/
		public static final String EQUIPMENT="EQUIPMENT";
		/**设备参数*/
		public static final String MEASURINGTOOL="MEASURINGTOOL";
	}
	/**
	 * 装备
	 * @author zdq
	 * 2018年6月28日
	 */
	public static class Equipment{
		/**计时型*/
		public static final String TIMING = "TIMING";
		/**计数型*/
		public static final String COUNTING = "COUNTING";
	}
	/**
	 *  工单常量类
	 * @author zdq
	 * 2018年7月10日
	 */
	public static class WorkSheet{
		/**普通工单*/
		public static final String COMMON = "common";
		/**返修工单*/
		public static final String REPAIR = "repair";
	}
	/**
	 * @author zdq
	 * 2018年7月23日
	 */
	public static  class PressLightType{
		/**人员*/
		public static final String EMPLOYEE="employee";
		/**设备*/
		public static final String DEVICE="device";
		/**物料*/
		public static final String MATERIEL="materiel";
		/**方法*/
		public static final String METHOD="method";
		/**安环*/
		public static final String SECUREENVIRONMENT="secureEnvironment";
		/**测量*/
		public static final String MEASURE="measure";
	}
	/**
	 * 工作流静态类
	 * @author zdq
	 * 2018年8月2日
	 */
	public static class Workflow{
		/**意见*/
		public static final String SUGGESTION = "suggestion";
		
		/**启动流程的用户ID*/
		public static final String APPLY_USER_ID = "applyUserId";
		/**损时确认角色*/
		public static final String LOSTTIME_CONFIRM_ROLES = "lostTimeConfirmRoles";
		/**损时候选确认人*/
		public static final String LOSTTIME_CONFIRM_PERSONS = "lostTimeConfirmPersons";
		/**损时确认人*/
		public static final String LOSTTIME_CONFIRM_PERSON = "lostTimeConfirmPerson";
		
		/**熄灯人*/
		public static final String LIGHTOUT_PERSON = "lightoutPerson";
		/**熄灯候选人*/
		public static final String LIGHTOUT_PERSONS = "lightoutPersons";
		/**熄灯角色*/
		public static final String LIGHTOUT_ROLES = "lightoutRoles";
		
		/**按灯恢复人*/
		public static final String LIGHTOUT_RECOVER_PERSON = "lightoutRecoverPerson";
		/**按灯恢复候选人*/
		public static final String LIGHTOUT_RECOVER_PERSONS = "lightoutRecoverPersons";
		/**按灯恢复角色*/
		public static final String LIGHTOUT_RECOVER_ROLES = "lightoutRecoverRoles";
		
		/**按灯确认人*/
		public static final String LIGHTOUT_CONFIRM_PERSON = "lightoutConfirmPerson";
		/**按灯确认候选人*/
		public static final String LIGHTOUT_CONFIRM_PERSONS = "lightoutConfirmPersons";
		/**按灯确认角色*/
		public static final String LIGHTOUT_CONFIRM_ROLES = "lightoutConfirmRoles";
		
		/**不合格品审核人*/
		public static final String NG_AUDIT_PERSON = "ngAuditPerson";
		/**不合格品候选审核人*/
		public static final String NG_AUDIT_PERSONS = "ngAuditPersons";
		/**不合格品候选审核角色*/
		public static final String NG_AUDIT_ROLES = "ngAuditRoles";
		
		/**不合格品复核人*/
		public static final String NG_REVIEW_PERSON = "ngReviewPerson";
		/**不合格品候选复核人*/
		public static final String NG_REVIEW_PERSONS = "ngReviewPersons";
		/**不合格品候选复核角色*/
		public static final String NG_REVIEW_ROLES = "ngReviewRoles";
		
		/**不合格品确认人*/
		public static final String NG_CONFIRM_PERSON = "ngConfirmPerson";
		/**不合格品候选确认人*/
		public static final String NG_CONFIRM_PERSONS = "ngConfirmPersons";
		/**不合格品候选确认角色*/
		public static final String NG_CONFIRM_ROLES = "ngConfirmRoles";
	}
}
