package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbConsume;

public class TbConsumeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbConsumeDAO.class);
	// property constants
	public static final String CONSUME_NAME = "consumeName";
	public static final String CONSUME_PRICE = "consumePrice";

	public void save(TbConsume transientInstance) {
		log.debug("saving TbConsume instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbConsume persistentInstance) {
		log.debug("deleting TbConsume instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbConsume findById(java.lang.String id) {
		log.debug("getting TbConsume instance with id: " + id);
		try {
			TbConsume instance = (TbConsume) getSession().get("com.yoga.entity.TbConsume", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbConsume instance) {
		log.debug("finding TbConsume instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbConsume").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbConsume instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbConsume as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByConsumeName(Object consumeName) {
		return findByProperty(CONSUME_NAME, consumeName);
	}

	public List findByConsumePrice(Object consumePrice) {
		return findByProperty(CONSUME_PRICE, consumePrice);
	}

	public List findAll() {
		log.debug("finding all TbConsume instances");
		try {
			String queryString = "from TbConsume";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbConsume merge(TbConsume detachedInstance) {
		log.debug("merging TbConsume instance");
		try {
			TbConsume result = (TbConsume) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbConsume instance) {
		log.debug("attaching dirty TbConsume instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbConsume instance) {
		log.debug("attaching clean TbConsume instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}