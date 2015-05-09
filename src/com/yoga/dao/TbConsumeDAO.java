package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbClassrooms;
import com.yoga.entity.TbConsume;
import com.yoga.util.Page;

public class TbConsumeDAO extends BaseHibernateDAO implements BaseDao<TbConsume>{
	private static final Logger log = LoggerFactory.getLogger(TbConsumeDAO.class);
	// property constants
	public static final String CONSUME_NAME = "consumeName";
	public static final String CONSUME_PRICE = "consumePrice";

	public void save(TbConsume transientInstance) {
		log.debug("saving TbConsume instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbConsume persistentInstance) {
		log.debug("deleting TbConsume instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbConsume findById(java.lang.String id) {
		log.debug("getting TbConsume instance with id: " + id);
		try {
			TbConsume instance = (TbConsume) getSession().get("com.yoga.entity.TbConsume", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbConsume instance) {
		log.debug("finding TbConsume instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbConsume").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbConsume instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbConsume as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByConsumeName(Object consumeName) {
		return findByProperty(CONSUME_NAME, consumeName);
	}

	public List findByConsumePrice(Object consumePrice) {
		return findByProperty(CONSUME_PRICE, consumePrice);
	}

	public List findAll(String... params) {
		log.debug("finding all TbConsume instances");
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
	public Page<TbConsume> findAll(int page, int size, String... params) {
		Page<TbConsume> pageList = new Page<TbConsume>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbConsume instances");
		try {
			Query queryObject = repeatCode(params);
			queryObject.setFirstResult((page - 1) * size);// 显示第几页，当前页
			queryObject.setMaxResults(size);// 每页做多显示的记录数
			List list = queryObject.list();
			pageList.setTotalElement(findAll(params).size(),size);
			pageList.setContent(list);
			return pageList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	private Query repeatCode(String... params) {
		String queryString = "from TbConsume";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.consumeId=:cid and ");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				buffer.append(" tb.consumeName=:cname and ");
			}
			if (params[2] != null && !"".equals(params[2].trim())) {
				buffer.append(" tb.consumePrice=:cstate and ");
			}
			buffer.append(" 1=1 ");
		}
		Query queryObject = getSession().createQuery(buffer.toString());
		// 分页显示的操作
		if (params != null && params.length > 0) {
			if (params[0] != null && !"".equals(params[0].trim())) {
				queryObject.setString("cid", params[0]);
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				queryObject.setString("cname", params[1]);
			}
			if (params[2] != null && !"".equals(params[2].trim())) {
				queryObject.setString("cstate", params[2]);
			}
		}
		return queryObject;
	}
}