package com.yoga.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbCourse;
import com.yoga.util.DateUtil;
import com.yoga.util.Page;

public class TbCourseDAO extends BaseHibernateDAO implements BaseDao<TbCourse> {
	private static final Logger log = LoggerFactory.getLogger(TbCourseDAO.class);
	// property constants
	public static final String COURES_NAME = "couresName";
	public static final String COURSE_PRICE = "coursePrice";

	public void save(TbCourse transientInstance) {
		log.debug("saving TbCourse instance");
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

	public void update(TbCourse transientInstance) {
		log.debug("updating TbCourse instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			// http://www.blogjava.net/hrcdg/articles/157724.html
			session.merge(transientInstance);
			beginTransaction.commit();
			log.debug("update successful");
		} catch (RuntimeException re) {
			re.printStackTrace();
			beginTransaction.rollback();
			log.error("update failed", re);
			throw re;
		} finally {
			session.close();
		}
	}
	
	public void delete(TbCourse persistentInstance) {
		log.debug("deleting TbCourse instance");
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
		}finally {
			session.close();
		}
	}

	public TbCourse findById(java.lang.String id) {
		log.debug("getting TbCourse instance with id: " + id);
		try {
			TbCourse instance = (TbCourse) getSession().get("com.yoga.entity.TbCourse", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbCourse instance) {
		log.debug("finding TbCourse instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbCourse").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbCourse instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbCourse as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCouresName(Object couresName) {
		return findByProperty(COURES_NAME, couresName);
	}

	public List findByCoursePrice(Object coursePrice) {
		return findByProperty(COURSE_PRICE, coursePrice);
	}

	public List findAll(String... params) {
		log.debug("finding all TbCourse instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * 2015-5-8 约定param只能传3个参数，对应表的字段<br>
	 * <b> 传参数的时候params必须为数组并且对应字段</b>
	 */
	@Override
	public Page<TbCourse> findAll(int page, int size, String... params) {
		Page<TbCourse> pageList = new Page<TbCourse>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbConsume instances");
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
		String queryString = "from TbCourse";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.consumeId like:cid and ");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				buffer.append(" tb.couresName like:cname and ");
			}
			if (params[2] != null && !"".equals(params[2].trim())) {
				buffer.append(" tb.courseDate=:ctime and ");
			}
			if (params[3] != null && !"".equals(params[3].trim())) {
				buffer.append(" tb.coursePrice=:cprice and ");
			}
			buffer.append(" 1=1 ");
		}
		Query queryObject = getSession().createQuery(buffer.toString());
		// 分页显示的操作
		if (params != null && params.length > 0) {
			if (params[0] != null && !"".equals(params[0].trim())) {
				queryObject.setString("cid", "%"+params[0]+ "%");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				queryObject.setString("cname",  "%"+params[1]+ "%");
			}
			if (params[2] != null && !"".equals(params[2].trim())) {
				queryObject.setDate("ctime", DateUtil.str2Date(params[2], "yyyy-MM-dd"));
			}
			if (params[3] != null && !"".equals(params[3].trim())) {
				queryObject.setString("cprice", params[3]);
			}
		}
		return queryObject;
	}
}