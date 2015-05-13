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

import com.yoga.entity.TbMemberCourseDetail;

public class TbMemberCourseDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbMemberCourseDetailDAO.class);

	// property constants

	public void save(TbMemberCourseDetail transientInstance) {
		log.debug("saving TbMemberCourseDetail instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			session.save(transientInstance);
			beginTransaction.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			beginTransaction.rollback();
			log.error("save failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public void delete(String memberCourseId) {
		log.debug("deleting TbMemberCourseDetail instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			SQLQuery createSQLQuery = session.createSQLQuery("DELETE * FROM tb_member_course_detail WHERE member_course_id = '" + memberCourseId+"' ");
			createSQLQuery.executeUpdate();
			beginTransaction.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			beginTransaction.rollback();
			log.error("delete failed", re);
			throw re;
		}finally{
			session.close();
		}
	}
	public void delete(TbMemberCourseDetail persistentInstance) {
		log.debug("deleting TbMemberCourseDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	

	public TbMemberCourseDetail findById(java.lang.Integer id) {
		log.debug("getting TbMemberCourseDetail instance with id: " + id);
		try {
			TbMemberCourseDetail instance = (TbMemberCourseDetail) getSession().get("com.yoga.entity.TbMemberCourseDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbMemberCourseDetail instance) {
		log.debug("finding TbMemberCourseDetail instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbMemberCourseDetail").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbMemberCourseDetail instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbMemberCourseDetail as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TbMemberCourseDetail instances");
		try {
			String queryString = "from TbMemberCourseDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbMemberCourseDetail merge(TbMemberCourseDetail detachedInstance) {
		log.debug("merging TbMemberCourseDetail instance");
		try {
			TbMemberCourseDetail result = (TbMemberCourseDetail) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbMemberCourseDetail instance) {
		log.debug("attaching dirty TbMemberCourseDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbMemberCourseDetail instance) {
		log.debug("attaching clean TbMemberCourseDetail instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}