package com.yoga.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbLimit;
import com.yoga.util.Page;

public class TbLimitDAO extends BaseHibernateDAO  implements BaseDao<TbLimit> {
	private static final Logger log = LoggerFactory.getLogger(TbLimitDAO.class);
	// property constants
	public static final String NAME = "name";

	public void save(TbLimit transientInstance) {
		log.debug("saving TbLimit instance");
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

	public void update(TbLimit transientInstance) {
		log.debug("updating TbLimit instance");
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
	
	public void delete(TbLimit persistentInstance) {
		log.debug("deleting TbLimit instance");
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

	public TbLimit findById(java.lang.Integer id) {
		log.debug("getting TbLimit instance with id: " + id);
		try {
			TbLimit instance = (TbLimit) getSession().get("com.yoga.entity.TbLimit", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbLimit instance) {
		log.debug("finding TbLimit instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbLimit").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbLimit instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbLimit as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll(String... params) {
		log.debug("finding all TbLimit instances");
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
	public Page<TbLimit> findAll(int page, int size, String... params) {
		Page<TbLimit> pageList = new Page<TbLimit>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbLimit instances");
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
		String queryString = "from TbLimit";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.name=:name and ");
			}
			buffer.append(" 1=1 ");
		}
		Query queryObject = getSession().createQuery(buffer.toString());
		// 分页显示的操作
		if (params != null && params.length > 0) {
			if (params[0] != null && !"".equals(params[0].trim())) {
				queryObject.setString("name", params[0]);
			}
		}
		return queryObject;
	}
}