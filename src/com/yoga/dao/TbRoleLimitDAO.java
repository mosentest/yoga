package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbRoleLimit;

public class TbRoleLimitDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbRoleLimitDAO.class);

	// property constants

	public void save(TbRoleLimit transientInstance) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		log.debug("saving TbRoleLimit instance");
		try {
			session.save(transientInstance);
			beginTransaction.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			beginTransaction.rollback();
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * 根据角色编号删除对应信息
	 * 
	 * @param roleId
	 */
	public void delete(int roleId) {
		log.debug("deleting TbRoleLimit instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			//2015-5-11，写sql代码
			SQLQuery createSQLQuery = session.createSQLQuery("DELETE * FROM tb_role_limit WHERE role_id=" + roleId);
			createSQLQuery.executeUpdate();
			beginTransaction.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			beginTransaction.rollback();
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void delete(TbRoleLimit persistentInstance) {
		log.debug("deleting TbRoleLimit instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			beginTransaction.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			beginTransaction.rollback();
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbRoleLimit findById(java.lang.Integer id) {
		log.debug("getting TbRoleLimit instance with id: " + id);
		try {
			TbRoleLimit instance = (TbRoleLimit) getSession().get("com.yoga.entity.TbRoleLimit", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbRoleLimit instance) {
		log.debug("finding TbRoleLimit instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbRoleLimit").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbRoleLimit instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbRoleLimit as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll(int id) {
		log.debug("finding all TbRoleLimit instances");
		try {
			String queryString = "from TbRoleLimit where tbRole.id= " + id;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbRoleLimit merge(TbRoleLimit detachedInstance) {
		log.debug("merging TbRoleLimit instance");
		try {
			TbRoleLimit result = (TbRoleLimit) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbRoleLimit instance) {
		log.debug("attaching dirty TbRoleLimit instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbRoleLimit instance) {
		log.debug("attaching clean TbRoleLimit instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}