package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbStaffDetail;

public class TbStaffDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TbStaffDetailDAO.class);
	// property constants
	public static final String STAFF_CARD = "staffCard";
	public static final String STAFF_ADDRESS = "staffAddress";
	public static final String STAFF_EMAIL = "staffEmail";

	public void save(TbStaffDetail transientInstance) {
		log.debug("saving TbStaffDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbStaffDetail persistentInstance) {
		log.debug("deleting TbStaffDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbStaffDetail findById(java.lang.Integer id) {
		log.debug("getting TbStaffDetail instance with id: " + id);
		try {
			TbStaffDetail instance = (TbStaffDetail) getSession().get(
					"com.yoga.entity.TbStaffDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbStaffDetail instance) {
		log.debug("finding TbStaffDetail instance by example");
		try {
			List results = getSession()
					.createCriteria("com.yoga.entity.TbStaffDetail")
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
		log.debug("finding TbStaffDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TbStaffDetail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStaffCard(Object staffCard) {
		return findByProperty(STAFF_CARD, staffCard);
	}

	public List findByStaffAddress(Object staffAddress) {
		return findByProperty(STAFF_ADDRESS, staffAddress);
	}

	public List findByStaffEmail(Object staffEmail) {
		return findByProperty(STAFF_EMAIL, staffEmail);
	}

	public List findAll() {
		log.debug("finding all TbStaffDetail instances");
		try {
			String queryString = "from TbStaffDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbStaffDetail merge(TbStaffDetail detachedInstance) {
		log.debug("merging TbStaffDetail instance");
		try {
			TbStaffDetail result = (TbStaffDetail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbStaffDetail instance) {
		log.debug("attaching dirty TbStaffDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbStaffDetail instance) {
		log.debug("attaching clean TbStaffDetail instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}