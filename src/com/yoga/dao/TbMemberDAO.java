package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbCourse;
import com.yoga.entity.TbMember;
import com.yoga.util.DateUtil;
import com.yoga.util.Page;

public class TbMemberDAO extends BaseHibernateDAO implements BaseDao<TbMember> {
	private static final Logger log = LoggerFactory.getLogger(TbMemberDAO.class);
	// property constants
	public static final String MEMBER_USERNAME = "memberUsername";
	public static final String MEMBER_NAME = "memberName";
	public static final String MEMBER_SEX = "memberSex";
	public static final String MEMBER_CARD = "memberCard";
	public static final String MEMBER_PHONE = "memberPhone";
	public static final String MEMBER_ADDRESS = "memberAddress";

	public void save(TbMember transientInstance) {
		log.debug("saving TbMember instance");
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

	public void update(TbMember transientInstance) {
		log.debug("updating TbCourse instance");
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

	public void delete(TbMember persistentInstance) {
		log.debug("deleting TbMember instance");
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

	public TbMember findById(java.lang.String id) {
		log.debug("getting TbMember instance with id: " + id);
		try {
			TbMember instance = (TbMember) getSession().get("com.yoga.entity.TbMember", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbMember instance) {
		log.debug("finding TbMember instance by example");
		try {
			List results = getSession().createCriteria("com.yoga.entity.TbMember").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbMember instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbMember as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMemberUsername(Object memberUsername) {
		return findByProperty(MEMBER_USERNAME, memberUsername);
	}

	public List findByMemberName(Object memberName) {
		return findByProperty(MEMBER_NAME, memberName);
	}

	public List findByMemberSex(Object memberSex) {
		return findByProperty(MEMBER_SEX, memberSex);
	}

	public List findByMemberCard(Object memberCard) {
		return findByProperty(MEMBER_CARD, memberCard);
	}

	public List findByMemberPhone(Object memberPhone) {
		return findByProperty(MEMBER_PHONE, memberPhone);
	}

	public List findByMemberAddress(Object memberAddress) {
		return findByProperty(MEMBER_ADDRESS, memberAddress);
	}

	public List findAll(String... params) {
		log.debug("finding all TbMember instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Page<TbMember> findAll(int page, int size, String... params) {
		Page<TbMember> pageList = new Page<TbMember>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbMember instances");
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
		String queryString = "from TbMember";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.memberId like:cid and ");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				buffer.append(" tb.memberName like:cname and ");
			}
			if (params[2] != null && !"".equals(params[2].trim())) {
				buffer.append(" tb.memberAddress like:ctime and ");
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