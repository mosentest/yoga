package com.yoga.controller;

import com.yoga.util.JsonResponse;

public interface BaseController<T> {
	/**
	 * 添加数据
	 * 
	 * @param entity
	 * @return
	 */
	public JsonResponse<T> add(T entity);

	/**
	 * 删除数据
	 * 
	 * @param entity
	 * @return
	 */
	public JsonResponse<T> delete(T entity);

	/**
	 * 更新数据
	 * 
	 * @param entity
	 * @return
	 */
	public JsonResponse<T> update(T entity);

	/**
	 * 条件查询，分页功能
	 * 
	 * @param page
	 * @param size
	 * @param obeject
	 * @return
	 */
	public JsonResponse<T> list(int page, int size, String[] params);

	/**
	 * 显示一条记录
	 * 
	 * @param params
	 * @return
	 */
	public JsonResponse<T> showOne(String... params);
}
