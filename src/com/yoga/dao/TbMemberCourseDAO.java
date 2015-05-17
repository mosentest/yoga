package com.yoga.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbMemberCourse;
import com.yoga.util.Page;

public class TbMemberCourseDAO extends BaseHibernateDAO implements BaseDao<TbMemberCourse>{
	private static final Logger log = LoggerFactory.getLogger(TbMemberCourseDAO.class);
	// property constants
	public static final String COST = "cost";

	public void save(TbMemberCourse transientInstance) {
		log.debug("saving TbMemberCourse instance");
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
	
	public void update(TbMemberCourse transientInstance) {
		log.debug("udpate TbMemberCourse instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			session.merge(transientInstance);
			beginTransaction.commit();
			log.debug("udpate successful");
		} catch (RuntimeException re) {
			beginTransaction.rollback();
			log.error("udpate failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public void delete(TbMemberCourse persistentInstance) {
		log.debug("deleting TbMemberCourse instance");
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

	public TbMemberCourse findById(java.lang.String id) {
		log.debug("getting TbMemberCourse instance with id: " + id);
		try {
			TbMemberCourse instance = (TbMemberCourse) getSession().get("com.yoga.entity.TbMemberCourse", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbMemberCourse instance) {
		log.debug("finding TbMemberCourse instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbMemberCourse").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbMemberCourse instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbMemberCourse as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCost(Object cost) {
		return findByProperty(COST, cost);
	}

	public List findAll(String... params) {
		log.debug("finding all TbMemberCourse instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Page<TbMemberCourse> findAll(int page, int size, String... params) {
		Page<TbMemberCourse> pageList = new Page<TbMemberCourse>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbMemberCourse instances");
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
		String queryString = "from TbMemberCourse";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.memberCourseId like:id and ");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				buffer.append(" tb.tbMember.memberName like:name and ");
			}
			buffer.append(" 1=1 ");
		}
		Query queryObject = getSession().createQuery(buffer.toString());
		// 分页显示的操作
		if (params != null && params.length > 0) {
			if (params[0] != null && !"".equals(params[0].trim())) {
				queryObject.setString("id", params[0]);
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				queryObject.setString("name",  "%"+params[1]+ "%");
			}
		}
		return queryObject;
	}
}