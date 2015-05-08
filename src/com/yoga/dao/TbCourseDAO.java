package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbCourse;

public class TbCourseDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TbCourseDAO.class);
	// property constants
	public static final String COURES_NAME = "couresName";
	public static final String COURSE_PRICE = "coursePrice";

	public void save(TbCourse transientInstance) {
		log.debug("saving TbCourse instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbCourse persistentInstance) {
		log.debug("deleting TbCourse instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbCourse findById(java.lang.String id) {
		log.debug("getting TbCourse instance with id: " + id);
		try {
			TbCourse instance = (TbCourse) getSession().get(
					"com.yoga.entity.TbCourse", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbCourse instance) {
		log.debug("finding TbCourse instance by example");
		try {
			List results = getSession()
					.createCriteria("com.yoga.entity.TbCourse")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbCourse instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbCourse as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCouresName(Object couresName) {
		return findByProperty(COURES_NAME, couresName);
	}

	public List findByCoursePrice(Object coursePrice) {
		return findByProperty(COURSE_PRICE, coursePrice);
	}

	public List findAll() {
		log.debug("finding all TbCourse instances");
		try {
			String queryString = "from TbCourse";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbCourse merge(TbCourse detachedInstance) {
		log.debug("merging TbCourse instance");
		try {
			TbCourse result = (TbCourse) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbCourse instance) {
		log.debug("attaching dirty TbCourse instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbCourse instance) {
		log.debug("attaching clean TbCourse instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}