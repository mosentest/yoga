package com.yoga.dao;

import java.util.List;

import org.junit.Test;

import com.yoga.entity.TbCourse;
import com.yoga.util.Page;

public class TbCourseDAOTest {
	private TbCourseDAO dao = new TbCourseDAO();
	@Test
	public void testSave() {
		
	}

	@Test
	public void testUpdate() {
		
	}

	@Test
	public void testDelete() {
		
	}

	@Test
	public void testFindById() {
		
	}

	@Test
	public void testFindByExample() {
		
	}

	@Test
	public void testFindByProperty() {
		
	}

	@Test
	public void testFindByCouresName() {
		
	}

	@Test
	public void testFindByCoursePrice() {
		
	}

	@Test
	public void testFindAllStringArray() {
		
	}

	@Test
	public void testFindAllIntIntStringArray() {
		Page<TbCourse> findAll = dao.findAll(1, 10, null);
		List<TbCourse> content = findAll.getContent();
		for(TbCourse t :content){
			System.out.println(t.getTbCourseType().getType()+"--"+t.getCouresName());
		}
	}

}
