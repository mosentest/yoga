package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbCourseType;

public class TbCourseTypeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbCourseTypeDAO.class);
	// property constants
	public static final String TYPE = "type";

	public void save(TbCourseType transientInstance) {
		log.debug("saving TbCourseType instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void update(TbCourseType transientInstance) {
		log.debug("updating TbCourseType instance");
		try {
			getSession().merge(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(TbCourseType persistentInstance) {
		log.debug("deleting TbCourseType instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbCourseType findById(java.lang.Integer id) {
		log.debug("getting TbCourseType instance with id: " + id);
		try {
			TbCourseType instance = (TbCourseType) getSession().get("com.yoga.entity.TbCourseType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbCourseType instance) {
		log.debug("finding TbCourseType instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbCourseType").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbCourseType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbCourseType as model where model." + propertyName + "= ?";
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
		log.debug("finding all TbCourseType instances");
		//TODO...好奇怪拿不到
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			String queryString = "from TbCourseType";
			Query queryObject = session.createQuery(queryString);
			beginTransaction.commit();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public TbCourseType merge(TbCourseType detachedInstance) {
		log.debug("merging TbCourseType instance");
		try {
			TbCourseType result = (TbCourseType) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbCourseType instance) {
		log.debug("attaching dirty TbCourseType instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbCourseType instance) {
		log.debug("attaching clean TbCourseType instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}