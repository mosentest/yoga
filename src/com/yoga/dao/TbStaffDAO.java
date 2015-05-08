package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbStaff;

public class TbStaffDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbStaffDAO.class);
	// property constants
	public static final String STAFF_NAME = "staffName";
	public static final String STAFF_SEX = "staffSex";
	public static final String STAFF_AGE = "staffAge";
	public static final String STAFF_POST = "staffPost";
	public static final String STAFF_PHONE = "staffPhone";
	public static final String ID_DELETE = "idDelete";

	public void save(TbStaff transientInstance) {
		log.debug("saving TbStaff instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbStaff persistentInstance) {
		log.debug("deleting TbStaff instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbStaff findById(java.lang.String id) {
		log.debug("getting TbStaff instance with id: " + id);
		try {
			TbStaff instance = (TbStaff) getSession().get("com.yoga.entity.TbStaff", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbStaff instance) {
		log.debug("finding TbStaff instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbStaff").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbStaff instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbStaff as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStaffName(Object staffName) {
		return findByProperty(STAFF_NAME, staffName);
	}

	public List findByStaffSex(Object staffSex) {
		return findByProperty(STAFF_SEX, staffSex);
	}

	public List findByStaffAge(Object staffAge) {
		return findByProperty(STAFF_AGE, staffAge);
	}

	public List findByStaffPost(Object staffPost) {
		return findByProperty(STAFF_POST, staffPost);
	}

	public List findByStaffPhone(Object staffPhone) {
		return findByProperty(STAFF_PHONE, staffPhone);
	}

	public List findByIdDelete(Object idDelete) {
		return findByProperty(ID_DELETE, idDelete);
	}

	public List findAll() {
		log.debug("finding all TbStaff instances");
		try {
			String queryString = "from TbStaff";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbStaff merge(TbStaff detachedInstance) {
		log.debug("merging TbStaff instance");
		try {
			TbStaff result = (TbStaff) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbStaff instance) {
		log.debug("attaching dirty TbStaff instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbStaff instance) {
		log.debug("attaching clean TbStaff instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}