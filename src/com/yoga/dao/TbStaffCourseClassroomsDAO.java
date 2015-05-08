package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbStaffCourseClassrooms;

public class TbStaffCourseClassroomsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbStaffCourseClassroomsDAO.class);

	// property constants

	public void save(TbStaffCourseClassrooms transientInstance) {
		log.debug("saving TbStaffCourseClassrooms instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbStaffCourseClassrooms persistentInstance) {
		log.debug("deleting TbStaffCourseClassrooms instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbStaffCourseClassrooms findById(java.lang.Integer id) {
		log.debug("getting TbStaffCourseClassrooms instance with id: " + id);
		try {
			TbStaffCourseClassrooms instance = (TbStaffCourseClassrooms) getSession().get("com.yoga.entity.TbStaffCourseClassrooms", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbStaffCourseClassrooms instance) {
		log.debug("finding TbStaffCourseClassrooms instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbStaffCourseClassrooms").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbStaffCourseClassrooms instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbStaffCourseClassrooms as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TbStaffCourseClassrooms instances");
		try {
			String queryString = "from TbStaffCourseClassrooms";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbStaffCourseClassrooms merge(TbStaffCourseClassrooms detachedInstance) {
		log.debug("merging TbStaffCourseClassrooms instance");
		try {
			TbStaffCourseClassrooms result = (TbStaffCourseClassrooms) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbStaffCourseClassrooms instance) {
		log.debug("attaching dirty TbStaffCourseClassrooms instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbStaffCourseClassrooms instance) {
		log.debug("attaching clean TbStaffCourseClassrooms instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}