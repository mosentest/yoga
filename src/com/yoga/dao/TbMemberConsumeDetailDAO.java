package com.yoga.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbMemberConsumeDetail;
import com.yoga.util.Page;

public class TbMemberConsumeDetailDAO extends BaseHibernateDAO implements BaseDao<TbMemberConsumeDetail> {
	private static final Logger log = LoggerFactory.getLogger(TbMemberConsumeDetailDAO.class);

	// property constants

	public void save(TbMemberConsumeDetail transientInstance) {
		log.debug("saving TbMemberConsumeDetail instance");
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

	public void update(TbMemberConsumeDetail transientInstance) {
		log.debug("update TbMemberConsumeDetail instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			session.merge(transientInstance);
			beginTransaction.commit();
			log.debug("update successful");
		} catch (RuntimeException re) {
			beginTransaction.rollback();
			log.error("update failed", re);
			throw re;
		}finally{
			session.close();
		}
	}
	

	public void delete(String memberConsumeId) {
		log.debug("deleting TbMemberConsumeDetail instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			SQLQuery createSQLQuery = session.createSQLQuery("DELETE * FROM tb_member_consume_detail WHERE member_consume_id = '" + memberConsumeId+"' ");
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
	public void delete(TbMemberConsumeDetail persistentInstance) {
		log.debug("deleting TbMemberConsumeDetail instance");
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
		}finally{
			session.close();
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

	/**
	 * 根据订单号查询详细的消费
	 * @param consumeId
	 * @return
	 */
	public List findAll(String memberConsumeId) {
		log.debug("finding all TbMemberConsumeDetail instances");
		try {
			String queryString = "from TbMemberConsumeDetail tb where tb.tbConsume.tbMemberConsume='" + memberConsumeId + "'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Page<TbMemberConsumeDetail> findAll(int page, int size, String... params) {
		return null;
	}
}