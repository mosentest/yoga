package com.yoga.dao;

import org.hibernate.Session;


public interface IBaseHibernateDAO {
	public Session getSession();
}