package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbMemberConsumeDetail;

public class TbMemberConsumeDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbMemberConsumeDetailDAO.class);

	// property constants

	public void save(TbMemberConsumeDetail transientInstance) {
		log.debug("saving TbMemberConsumeDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbMemberConsumeDetail persistentInstance) {
		log.debug("deleting TbMemberConsumeDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbMemberConsumeDetail findById(java.lang.Integer id) {
		log.debug("getting TbMemberConsumeDetail instance with id: " + id);
		try {
			TbMemberConsumeDetail instance = (TbMemberConsumeDetail) getSession().get("com.yoga.entity.TbMemberConsumeDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbMemberConsumeDetail instance) {
		log.debug("finding TbMemberConsumeDetail instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbMemberConsumeDetail").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbMemberConsumeDetail instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbMemberConsumeDetail as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TbMemberConsumeDetail instances");
		try {
			String queryString = "from TbMemberConsumeDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbMemberConsumeDetail merge(TbMemberConsumeDetail detachedInstance) {
		log.debug("merging TbMemberConsumeDetail instance");
		try {
			TbMemberConsumeDetail result = (TbMemberConsumeDetail) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbMemberConsumeDetail instance) {
		log.debug("attaching dirty TbMemberConsumeDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbMemberConsumeDetail instance) {
		log.debug("attaching clean TbMemberConsumeDetail instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}