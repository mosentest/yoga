package com.yoga.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class Page<T> implements Iterable<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long totalElement;// 总数

	private int totalPage;// 总页数

	private int currentPage;// 当前页

	private int pageSize;// 行数

	private List<T> content;// 内容

	public Page() {
	}

	public boolean isEmpty() {
		return totalElement == 0;
	}

	public void setTotalElement(long totalElement, int size) {
		this.totalElement = totalElement;
		if (totalElement % size == 0) {
			totalPage = (int) totalElement / size;
		} else {
			totalPage = (int) totalElement / size + 1;
		}
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public long getTotalElement() {
		return totalElement;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public List<T> getContent() {
		return content;
	}

	@Override
	public Iterator<T> iterator() {
		return content.iterator();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}