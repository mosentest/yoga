package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbMemberCourse;

public class TbMemberCourseDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbMemberCourseDAO.class);
	// property constants
	public static final String COST = "cost";

	public void save(TbMemberCourse transientInstance) {
		log.debug("saving TbMemberCourse instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbMemberCourse persistentInstance) {
		log.debug("deleting TbMemberCourse instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbMemberCourse findById(java.lang.String id) {
		log.debug("getting TbMemberCourse instance with id: " + id);
		try {
			TbMemberCourse instance = (TbMemberCourse) getSession().get("com.yoga.entity.TbMemberCourse", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbMemberCourse instance) {
		log.debug("finding TbMemberCourse instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbMemberCourse").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbMemberCourse instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbMemberCourse as model where model." + propertyName + "= ?";
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
		log.debug("finding all TbMemberCourse instances");
		try {
			String queryString = "from TbMemberCourse";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbMemberCourse merge(TbMemberCourse detachedInstance) {
		log.debug("merging TbMemberCourse instance");
		try {
			TbMemberCourse result = (TbMemberCourse) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbMemberCourse instance) {
		log.debug("attaching dirty TbMemberCourse instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbMemberCourse instance) {
		log.debug("attaching clean TbMemberCourse instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}