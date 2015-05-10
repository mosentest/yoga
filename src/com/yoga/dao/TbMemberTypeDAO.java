package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbMemberType;

public class TbMemberTypeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbMemberTypeDAO.class);
	// property constants
	public static final String TYPE = "type";

	public void save(TbMemberType transientInstance) {
		log.debug("saving TbMemberType instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbMemberType persistentInstance) {
		log.debug("deleting TbMemberType instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbMemberType findById(java.lang.Integer id) {
		log.debug("getting TbMemberType instance with id: " + id);
		try {
			TbMemberType instance = (TbMemberType) getSession().get("com.yoga.entity.TbMemberType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbMemberType instance) {
		log.debug("finding TbMemberType instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbMemberType").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbMemberType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbMemberType as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findAll() {
		log.debug("finding all TbMemberType instances");
		try {
			String queryString = "from TbMemberType";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbMemberType merge(TbMemberType detachedInstance) {
		log.debug("merging TbMemberType instance");
		try {
			TbMemberType result = (TbMemberType) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbMemberType instance) {
		log.debug("attaching dirty TbMemberType instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbMemberType instance) {
		log.debug("attaching clean TbMemberType instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}