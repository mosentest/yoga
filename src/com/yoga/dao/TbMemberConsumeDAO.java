package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbMemberConsume;

public class TbMemberConsumeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbMemberConsumeDAO.class);
	// property constants
	public static final String COST = "cost";

	public void save(TbMemberConsume transientInstance) {
		log.debug("saving TbMemberConsume instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbMemberConsume persistentInstance) {
		log.debug("deleting TbMemberConsume instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbMemberConsume findById(java.lang.String id) {
		log.debug("getting TbMemberConsume instance with id: " + id);
		try {
			TbMemberConsume instance = (TbMemberConsume) getSession().get("com.yoga.entity.TbMemberConsume", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbMemberConsume instance) {
		log.debug("finding TbMemberConsume instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbMemberConsume").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbMemberConsume instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbMemberConsume as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCost(Object cost) {
		return findByProperty(COST, cost);
	}

	public List findAll() {
		log.debug("finding all TbMemberConsume instances");
		try {
			String queryString = "from TbMemberConsume";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbMemberConsume merge(TbMemberConsume detachedInstance) {
		log.debug("merging TbMemberConsume instance");
		try {
			TbMemberConsume result = (TbMemberConsume) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbMemberConsume instance) {
		log.debug("attaching dirty TbMemberConsume instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbMemberConsume instance) {
		log.debug("attaching clean TbMemberConsume instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}