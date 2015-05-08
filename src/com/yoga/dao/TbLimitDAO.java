package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbLimit;

public class TbLimitDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbLimitDAO.class);
	// property constants
	public static final String NAME = "name";

	public void save(TbLimit transientInstance) {
		log.debug("saving TbLimit instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbLimit persistentInstance) {
		log.debug("deleting TbLimit instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbLimit findById(java.lang.Integer id) {
		log.debug("getting TbLimit instance with id: " + id);
		try {
			TbLimit instance = (TbLimit) getSession().get("com.yoga.entity.TbLimit", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbLimit instance) {
		log.debug("finding TbLimit instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbLimit").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbLimit instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbLimit as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all TbLimit instances");
		try {
			String queryString = "from TbLimit";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbLimit merge(TbLimit detachedInstance) {
		log.debug("merging TbLimit instance");
		try {
			TbLimit result = (TbLimit) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbLimit instance) {
		log.debug("attaching dirty TbLimit instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbLimit instance) {
		log.debug("attaching clean TbLimit instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}