package com.yoga.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbStaff;
import com.yoga.entity.TbUser;
import com.yoga.util.Page;

public class TbUserDAO extends BaseHibernateDAO  implements BaseDao<TbUser>{
	private static final Logger log = LoggerFactory.getLogger(TbUserDAO.class);
	// property constants
	public static final String USER_USERNAME = "userUsername";
	public static final String USER_PASSWORD = "userPassword";

	public void save(TbUser transientInstance) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		log.debug("saving TbUser instance");
		try {
			session.save(transientInstance);
			beginTransaction.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			beginTransaction.rollback();
			log.error("save failed", re);
			throw re;
		} finally {
			session.close();
		}
	}

	public void udpate(TbUser transientInstance) {
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		log.debug("saving TbUser instance");
		try {
			session.merge(transientInstance);
			beginTransaction.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			beginTransaction.rollback();
			log.error("save failed", re);
			throw re;
		} finally {
			session.close();
		}
	}
	
	public void delete(TbUser persistentInstance) {
		log.debug("deleting TbUser instance");
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
		} finally {
			session.close();
		}
	}

	public TbUser findById(java.lang.String id) {
		log.debug("getting TbUser instance with id: " + id);
		try {
			TbUser instance = (TbUser) getSession().get("com.yoga.entity.TbUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbUser instance) {
		log.debug("finding TbUser instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbUser").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbUser instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbUser as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserUsername(Object userUsername) {
		return findByProperty(USER_USERNAME, userUsername);
	}

	public List findByUserPassword(Object userPassword) {
		return findByProperty(USER_PASSWORD, userPassword);
	}

	public List findAll(String... params) {
		log.debug("finding all TbUser instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * userid，username，name
	 */
	@Override
	public Page<TbUser> findAll(int page, int size, String... params) {
		Page<TbUser> pageList = new Page<TbUser>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbUser instances");
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
		String queryString = "from TbUser";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.userId like:cid and ");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				buffer.append(" tb.userUsername like:cname and ");
			}
			if (params[2] != null && !"".equals(params[2].trim())) {
				buffer.append(" tb.tbStaff.staffName like:ctime and ");
			}
			buffer.append(" 1=1 ");
		}
		Query queryObject = getSession().createQuery(buffer.toString());
		// 分页显示的操作
		if (params != null && params.length > 0) {
			if (params[0] != null && !"".equals(params[0].trim())) {
				queryObject.setString("cid", "%" + params[0] + "%");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				queryObject.setString("cname", "%" + params[1] + "%");
			}
			if (params[2] != null && !"".equals(params[2].trim())) {
				queryObject.setString("ctime", "%" + params[2] + "%");
			}
		}
		return queryObject;
	}

}