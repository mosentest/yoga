package com.yoga.dao;

import com.yoga.util.Page;

/**
 * 
 * 定一个一个接口，把没有的方法定义出来
 * 
 * @author hwb
 * 
 * @param <T>
 */
public interface BaseDao<T> {
	public Page<T> findAll(int page, int size, String... params);
}
