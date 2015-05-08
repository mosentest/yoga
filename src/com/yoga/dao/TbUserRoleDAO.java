package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbUserRole;

public class TbUserRoleDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbUserRoleDAO.class);

	// property constants

	public void save(TbUserRole transientInstance) {
		log.debug("saving TbUserRole instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbUserRole persistentInstance) {
		log.debug("deleting TbUserRole instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbUserRole findById(java.lang.Integer id) {
		log.debug("getting TbUserRole instance with id: " + id);
		try {
			TbUserRole instance = (TbUserRole) getSession().get("com.yoga.entity.TbUserRole", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbUserRole instance) {
		log.debug("finding TbUserRole instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbUserRole").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbUserRole instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbUserRole as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TbUserRole instances");
		try {
			String queryString = "from TbUserRole";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbUserRole merge(TbUserRole detachedInstance) {
		log.debug("merging TbUserRole instance");
		try {
			TbUserRole result = (TbUserRole) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbUserRole instance) {
		log.debug("attaching dirty TbUserRole instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbUserRole instance) {
		log.debug("attaching clean TbUserRole instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}