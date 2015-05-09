package com.yoga.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.yoga.entity.TbClassrooms;

public class TbClassroomsDAOTest {

	private TbClassroomsDAO dao = new TbClassroomsDAO();
	@Test
	public void testSave() {
		TbClassrooms transientInstance = new TbClassrooms();
		Session session = dao.getSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		transientInstance.setClassroomsId("1212");
		transientInstance.setClassroomsName("34343");
		transientInstance.setClassroomsState(false);
		dao.save(transientInstance);
		transaction.commit();
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
