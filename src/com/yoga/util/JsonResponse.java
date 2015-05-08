package com.yoga.util;

/**
 * Action类返回的格式
 * 
 * @author hwb
 * 
 * @param <T>
 */
public class JsonResponse<T> {
	private boolean success;
	private String msg;
	private Page<T> page = null;
	private T entity = null;

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
