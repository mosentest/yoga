package com.yoga.dao;

import org.junit.Test;

import com.yoga.entity.TbClassrooms;

public class TbClassroomsDAOTest {

	private TbClassroomsDAO dao = new TbClassroomsDAO();
	@Test
	public void testSave() {
		TbClassrooms transientInstance = new TbClassrooms();
		transientInstance.setClassroomsId("1212");
		transientInstance.setClassroomsName("34343");
		dao.save(transientInstance);
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
	public void testFindByClassroomsName() {
		
	}

	@Test
	public void testFindByClassroomsState() {
		
	}

	@Test
	public void testFindAllStringArray() {
		
	}

	@Test
	public void testFindAllIntIntStringArray() {
		
	}

}
