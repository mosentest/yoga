package com.yoga.util;

/**
 * 常量类
 * 
 * @author hwb
 * 
 */
public class Constants {

	public final static String EXIST = "该编号已存在";
	public final static String SUCCESS = "成功";
	public final static String FAILURE = "失败";
	public final static String GET = "获取";
	public final static String ADD = "保存";
	public final static String EDIT = "修改";
	public final static String DELETE = "删除";
	public final static String CLASSROOM = "课室";
	public final static String CONSUME = "消费品";
	public final static String LIMIT = "权限";
	public final static String MEMBER = "会员";
	public final static String ORDER = "订单";
	public final static String ROLE = "角色";
	public final static String STAFF = "员工";
	public final static String USER = "用户";
	public final static String DETAIL = "详细";
	public final static String MESSAGE = "信息";

	public static String getTip(String... msg) {
		StringBuffer buffer = new StringBuffer();
		for (String arg : msg) {
			buffer.append(arg);
		}
		return buffer.toString();
	}
}
