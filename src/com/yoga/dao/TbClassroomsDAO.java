package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.yoga.entity.TbClassrooms;
import com.yoga.util.Page;

public class TbClassroomsDAO extends BaseHibernateDAO implements BaseDao<TbClassrooms> {
	private static final Logger log = LoggerFactory.getLogger(TbClassroomsDAO.class);
	// property constants
	public static final String CLASSROOMS_NAME = "classroomsName";
	public static final String CLASSROOMS_STATE = "classroomsState";

	public void save(TbClassrooms transientInstance) {
		log.debug("saving TbClassrooms instance");
		Transaction beginTransaction = getSession().beginTransaction();
		try {
			beginTransaction.begin();
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			beginTransaction.rollback();
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbClassrooms persistentInstance) {
		log.debug("deleting TbClassrooms instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbClassrooms findById(java.lang.String id) {
		log.debug("getting TbClassrooms instance with id: " + id);
		try {
			TbClassrooms instance = (TbClassrooms) getSession().get("com.yoga.entity.TbClassrooms", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbClassrooms instance) {
		log.debug("finding TbClassrooms instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbClassrooms").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbClassrooms instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbClassrooms as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByClassroomsName(Object classroomsName) {
		return findByProperty(CLASSROOMS_NAME, classroomsName);
	}

	public List findByClassroomsState(Object classroomsState) {
		return findByProperty(CLASSROOMS_STATE, classroomsState);
	}

	public List findAll() {
		log.debug("finding all TbClassrooms instances");
		try {
			String queryString = "from TbClassrooms";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbClassrooms merge(TbClassrooms detachedInstance) {
		log.debug("merging TbClassrooms instance");
		try {
			TbClassrooms result = (TbClassrooms) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbClassrooms instance) {
		log.debug("attaching dirty TbClassrooms instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbClassrooms instance) {
		log.debug("attaching clean TbClassrooms instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 2015-5-8 约定param只能传3个参数，对应表的字段<br>
	 * <b> 传参数的时候params必须为数组并且对应字段</b>
	 */
	@Override
	public Page<TbClassrooms> findAll(int page, int size, String... params) {
		Page<TbClassrooms> pageList = new Page<TbClassrooms>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbClassrooms instances");
		try {
			String queryString = "from TbClassrooms";
			StringBuffer buffer = new StringBuffer();
			buffer.append(queryString);
			if (params != null && params.length > 0) {
				buffer.append(" as tb where ");
				if (params[0] != null && "".equals(params[0].trim())) {
					buffer.append(" tb.classroomsId=:cid and ");
				}
				if (params[1] != null && "".equals(params[1].trim())) {
					buffer.append(" tb.classroomsName=:cname and ");
				}
				if (params[2] != null && "".equals(params[2].trim())) {
					buffer.append(" tb.classroomsState=:cstate and ");
				}
				buffer.append(" 1=1 ");
			}
			Query queryObject = getSession().createQuery(buffer.toString());
			// 分页显示的操作
			if (params != null && params.length > 0) {
				if (params[0] != null && "".equals(params[0].trim())) {
					queryObject.setString("cid", params[0]);
				}
				if (params[1] != null && "".equals(params[1].trim())) {
					queryObject.setString("cname", params[1]);
				}
				if (params[2] != null && "".equals(params[2].trim())) {
					queryObject.setBoolean("cstate", Boolean.parseBoolean(params[2]));
				}
			}
			queryObject.setFirstResult(page - 1);// 显示第几页，当前页
			queryObject.setMaxResults(size);// 每页做多显示的记录数
			List list = queryObject.list();
			pageList.setTotalElement(list.size(), size);
			pageList.setContent(list);
			return pageList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}