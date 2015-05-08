package com.yoga.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoga.entity.TbMember;

public class TbMemberDAO extends BaseHibernateDAO {
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
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbMember persistentInstance) {
		log.debug("deleting TbMember instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
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

	public List findAll() {
		log.debug("finding all TbMember instances");
		try {
			String queryString = "from TbMember";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbMember merge(TbMember detachedInstance) {
		log.debug("merging TbMember instance");
		try {
			TbMember result = (TbMember) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbMember instance) {
		log.debug("attaching dirty TbMember instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbMember instance) {
		log.debug("attaching clean TbMember instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}