package com.digitzones.constants;
/**
 * 常量定义
 * @author zdq
 * 2018年6月8日
 */
public class Constant {
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
		public static final String NG = "ng";
	}
}
