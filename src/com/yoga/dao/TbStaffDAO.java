package com.yoga.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbStaff;
import com.yoga.util.Page;

public class TbStaffDAO extends BaseHibernateDAO  implements BaseDao<TbStaff> {
	private static final Logger log = LoggerFactory.getLogger(TbStaffDAO.class);
	// property constants
	public static final String STAFF_NAME = "staffName";
	public static final String STAFF_SEX = "staffSex";
	public static final String STAFF_AGE = "staffAge";
	public static final String STAFF_POST = "staffPost";//职务
	public static final String STAFF_PHONE = "staffPhone";

	// public static final String ID_DELETE = "idDelete";

	public void save(TbStaff transientInstance) {
		log.debug("saving TbStaff instance");
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
		} finally {
			getSession().close();
		}
	}

	public void update(TbStaff transientInstance) {
		log.debug("updating TbStaff instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			getSession().merge(transientInstance);
			beginTransaction.commit();
			log.debug("update successful");
		} catch (RuntimeException re) {
			beginTransaction.rollback();
			log.error("update failed", re);
			throw re;
		} finally {
			getSession().close();
		}
	}
	
	public void delete(TbStaff persistentInstance) {
		log.debug("deleting TbStaff instance");
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

	public List findAll(String... params) {
		log.debug("finding all TbStaff instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Page<TbStaff> findAll(int page, int size, String... params) {
		Page<TbStaff> pageList = new Page<TbStaff>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbStaff instances");
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
		String queryString = "from TbStaff";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.staffId like:cid and ");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				buffer.append(" tb.staffName like:cname and ");
			}
			if (params[2] != null && !"".equals(params[2].trim())) {
				buffer.append(" tb.staffPost like:ctime and ");//职务
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