package com.yoga.dao;

import com.yoga.util.HibernateSessionFactory;
import org.hibernate.Session;


public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
}