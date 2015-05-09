package com.yoga.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbMemberConsume;
import com.yoga.util.Page;

public class TbMemberConsumeDAO extends BaseHibernateDAO implements BaseDao<TbMemberConsume>{
	private static final Logger log = LoggerFactory.getLogger(TbMemberConsumeDAO.class);
	// property constants
	public static final String COST = "cost";

	public void save(TbMemberConsume transientInstance) {
		log.debug("saving TbMemberConsume instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			getSession().save(transientInstance);
			beginTransaction.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			beginTransaction.rollback();
			log.error("save failed", re);
			throw re;
		}finally {
			getSession().close();
		}
	}

	public void update(TbMemberConsume transientInstance) {
		log.debug("updating TbMemberConsume instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			// http://www.blogjava.net/hrcdg/articles/157724.html
			getSession().merge(transientInstance);
			beginTransaction.commit();
			log.debug("update successful");
		} catch (RuntimeException re) {
			re.printStackTrace();
			beginTransaction.rollback();
			log.error("update failed", re);
			throw re;
		} finally {
			getSession().close();
		}
	}
	
	public void delete(TbMemberConsume persistentInstance) {
		log.debug("deleting TbMemberConsume instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			getSession().delete(persistentInstance);
			beginTransaction.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			beginTransaction.rollback();
			log.error("delete failed", re);
			throw re;
		}finally {
			getSession().close();
		}
	}

	public TbMemberConsume findById(java.lang.String id) {
		log.debug("getting TbMemberConsume instance with id: " + id);
		try {
			TbMemberConsume instance = (TbMemberConsume) getSession().get("com.yoga.entity.TbMemberConsume", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbMemberConsume instance) {
		log.debug("finding TbMemberConsume instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbMemberConsume").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbMemberConsume instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbMemberConsume as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll(String... params) {
		log.debug("finding all TbMemberConsume instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * 2015-5-8 约定param只能传1个参数，对应表的字段<br>
	 * <b> 传参数的时候params必须为数组并且对应字段</b>
	 */
	@Override
	public Page<TbMemberConsume> findAll(int page, int size, String... params) {
		Page<TbMemberConsume> pageList = new Page<TbMemberConsume>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbMemberConsume instances");
		try {
			Query queryObject = repeatCode(params);
			queryObject.setFirstResult((page - 1) * size);// 显示第几页，当前页
			queryObject.setMaxResults(size);// 每页做多显示的记录数
			List list = queryObject.list();
			pageList.setTotalElement(findAll(params).size(), size);
			pageList.setContent(list);
			return pageList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	private Query repeatCode(String... params) {
		String queryString = "from TbMemberConsume";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.memberConsumeId=:id and ");
			}
			if (params[1] != null && !"".equals(params[2].trim())) {
				buffer.append(" tb.tbMember.memberUsername=:name and ");
			}
			buffer.append(" 1=1 ");
		}
		Query queryObject = getSession().createQuery(buffer.toString());
		// 分页显示的操作
		if (params != null && params.length > 0) {
			if (params[0] != null && !"".equals(params[0].trim())) {
				queryObject.setString("id", params[0]);
			}
		}
		return queryObject;
	}
}