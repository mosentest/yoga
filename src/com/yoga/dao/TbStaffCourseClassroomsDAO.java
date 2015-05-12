package com.yoga.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbStaffCourseClassrooms;
import com.yoga.util.Page;

public class TbStaffCourseClassroomsDAO extends BaseHibernateDAO implements BaseDao<TbStaffCourseClassrooms> {
	private static final Logger log = LoggerFactory.getLogger(TbStaffCourseClassroomsDAO.class);

	// property constants

	public void save(TbStaffCourseClassrooms transientInstance) {
		log.debug("saving TbStaffCourseClassrooms instance");
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
		} finally {
			session.close();
		}
	}

	public void update(TbStaffCourseClassrooms transientInstance) {
		log.debug("update TbStaffCourseClassrooms instance");
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
		} finally {
			session.close();
		}
	}

	public void delete(TbStaffCourseClassrooms persistentInstance) {
		log.debug("deleting TbStaffCourseClassrooms instance");
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

	public TbStaffCourseClassrooms findById(java.lang.Integer id) {
		log.debug("getting TbStaffCourseClassrooms instance with id: " + id);
		try {
			TbStaffCourseClassrooms instance = (TbStaffCourseClassrooms) getSession().get("com.yoga.entity.TbStaffCourseClassrooms", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbStaffCourseClassrooms instance) {
		log.debug("finding TbStaffCourseClassrooms instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbStaffCourseClassrooms").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbStaffCourseClassrooms instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbStaffCourseClassrooms as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll(String... params) {
		log.debug("finding all TbStaffCourseClassrooms instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Page<TbStaffCourseClassrooms> findAll(int page, int size, String... params) {
		Page<TbStaffCourseClassrooms> pageList = new Page<TbStaffCourseClassrooms>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbStaffCourseClassrooms instances");
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

	/**
	 * 
	 * @param params
	 * @return
	 * 
	 *         private TbStaff tbStaff; 
	 *         private TbCourse tbCourse; 
	 *         private TbClassrooms tbClassrooms;
	 */
	private Query repeatCode(String[] params) {
		String queryString = "from TbStaffCourseClassrooms";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.tbStaff.staffName like:sname and ");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				buffer.append(" tb.tbCourse.couresName like:cname and ");
			}
			if (params[2] != null && !"".equals(params[2].trim())) {
				buffer.append(" tb.tbClassrooms.classroomsName like:crname and ");
			}
			buffer.append(" 1=1 ");
		}
		Query queryObject = getSession().createQuery(buffer.toString());
		// 分页显示的操作
		if (params != null && params.length > 0) {
			if (params[0] != null && !"".equals(params[0].trim())) {
				queryObject.setString("sname", "%" + params[0] + "%");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				queryObject.setString("cname", "%" + params[1] + "%");
			}
			if (params[2] != null && !"".equals(params[2].trim())) {
				queryObject.setString("crname", "%" + params[2] + "%");
			}
		}
		return queryObject;
	}

}