package com.digitzones.util;

public class StringUtil {
	/**
	 * 去掉原字符串中指定字符串
	 * @param resource 原字符串
	 * @param removedString 去掉的字符串
	 * @return 去掉removedString后的字符串
	 */
	public static String remove(String resource,String removedString) {
		if(resource==null) {
			return null;
		}
		return resource.replace(removedString,"");
	}
}
