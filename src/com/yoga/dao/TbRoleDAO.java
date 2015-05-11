package com.yoga.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbRole;
import com.yoga.util.Page;

public class TbRoleDAO extends BaseHibernateDAO implements BaseDao<TbRole> {
	private static final Logger log = LoggerFactory.getLogger(TbRoleDAO.class);
	// property constants
	public static final String TYPE = "type";

	public int save(TbRole transientInstance) {
		log.debug("saving TbRole instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			getSession().save(transientInstance);
			beginTransaction.commit();
			log.debug("save successful");
			return transientInstance.getId();
		} catch (RuntimeException re) {
			beginTransaction.rollback();
			log.error("save failed", re);
			throw re;
		}
	}

	public void update(TbRole transientInstance) {
		log.debug("updating TbRole instance");
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

	public void delete(TbRole persistentInstance) {
		log.debug("deleting TbRole instance");
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
		}
	}

	public TbRole findById(java.lang.Integer id) {
		log.debug("getting TbRole instance with id: " + id);
		try {
			TbRole instance = (TbRole) getSession().get("com.yoga.entity.TbRole", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbRole instance) {
		log.debug("finding TbRole instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbRole").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbRole instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbRole as model where model." + propertyName + "= ?";
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

	public List findAll(String... params) {
		log.debug("finding all TbRole instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Page<TbRole> findAll(int page, int size, String... params) {
		Page<TbRole> pageList = new Page<TbRole>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbRole instances");
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

	private Query repeatCode(String[] params) {
		String queryString = "from TbRole";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.type like:name and ");
			}
			buffer.append(" 1=1 ");
		}
		Query queryObject = getSession().createQuery(buffer.toString());
		// 分页显示的操作
		if (params != null && params.length > 0) {
			if (params[0] != null && !"".equals(params[0].trim())) {
				queryObject.setString("name", "%" + params[0] + "%");
			}
		}
		return queryObject;
	}

}