package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbStaff;
import com.yoga.entity.TbStaffDetail;
import com.yoga.util.Page;

public class TbStaffDetailDAO extends BaseHibernateDAO implements BaseDao<TbStaffDetail> {
	private static final Logger log = LoggerFactory.getLogger(TbStaffDetailDAO.class);
	// property constants
	public static final String STAFF_CARD = "staffCard";
	public static final String STAFF_ADDRESS = "staffAddress";
	public static final String STAFF_EMAIL = "staffEmail";

	public void save(TbStaffDetail transientInstance) {
		log.debug("saving TbStaffDetail instance");
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
	
	public void update(TbStaffDetail transientInstance) {
		log.debug("updating TbStaffDetail instance");
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

	public void delete(TbStaffDetail persistentInstance) {
		log.debug("deleting TbStaffDetail instance");
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
		} finally {
			getSession().close();
		}
	}

	public TbStaffDetail findById(java.lang.Integer id) {
		log.debug("getting TbStaffDetail instance with id: " + id);
		try {
			TbStaffDetail instance = (TbStaffDetail) getSession().get("com.yoga.entity.TbStaffDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbStaffDetail instance) {
		log.debug("finding TbStaffDetail instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbStaffDetail").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbStaffDetail instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbStaffDetail as model where model." + propertyName + "= ?";
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

	public List findAll(String... params) {
		log.debug("finding all TbStaffDetail instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Page<TbStaffDetail> findAll(int page, int size, String... params) {
		Page<TbStaffDetail> pageList = new Page<TbStaffDetail>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbStaffDetail instances");
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
		String queryString = "from TbStaffDetail";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.tbStaff.staffId like:cid and ");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				buffer.append(" tb.tbStaff.staffName like:cname and ");
			}
			if (params[2] != null && !"".equals(params[2].trim())) {
				buffer.append(" tb.tbStaff.staffPost like:ctime and ");//职务
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