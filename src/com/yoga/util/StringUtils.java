package com.yoga.util;

public class StringUtils {

	public static boolean isEmpty(String msg) {
		boolean flag = true;
		if (msg != null && !"".equals(msg)) {
			flag = false;
		}
		return flag;
	}
	
}
