package com.xiatianlong.service.impl;


import com.xiatianlong.entity.XtlUserEntity;
import com.xiatianlong.service.BaseService;
import com.xiatianlong.service.dao.HibernateDao;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * BaseServiceImpl
 * Created by xiatianlong on 2016/12/30.
 */
public abstract class BaseServiceImpl implements BaseService {

	/** 日志 */
	protected final Logger log = Logger.getLogger(getClass());

	@Autowired
	protected HibernateDao hibernateDao;

	protected Session getSession() {
		return hibernateDao.getSession();
	}

	@Autowired
	protected MessageSource messageSource;

	/**
	 * single message
	 * @param messageKey
	 * 			message key
	 * @return  message
	 */
	protected String getMessage(String messageKey) {
		return messageSource.getMessage(messageKey, null, Locale.CHINA);
	}

	/**
	 * has param message
	 * @param messageKey
	 * 			message key
	 * @param args
	 * 			message param array
	 * @return  message
	 */
	protected String getMessage(String messageKey, Object[] args) {
		return messageSource.getMessage(messageKey, args, Locale.CHINA);
	}

	/**
	 * 持久化单个实体
	 *
	 * @param entity 实体
	 * @param <T>    实体类型
	 * @throws Exception
     */
	@Transactional
	public <T> void persist(T entity) throws Exception {
		getSession().persist(entity);
	}

	/**
	 * 持久化实体集合
	 *
	 * @param entities 实体
	 * @param <T>    实体类型
	 * @throws Exception
     */
	@Transactional
	public <T> void persist(Collection<T> entities) throws Exception {
		for (T entity : entities) {
			getSession().persist(entity);
		}
	}

	/**
	 * 更新单个实体
	 *
	 * @param entity 实体
	 * @param <T>    实体类型
	 * @throws Exception
     */
	@Transactional
	public <T> void merge(T entity) throws Exception {
		getSession().merge(entity);
	}

	/**
	 * 更新实体集合
	 *
	 * @param entities 实体
	 * @param <T>      实体类型
	 * @throws Exception
     */
	@Transactional
	public <T> void merge(Collection<T> entities) throws Exception {
		for (T entity : entities) {
			getSession().merge(entity);
		}
	}

	/**
	 * 删除单个实体
	 *
	 * @param entity 实体
	 * @param <T>    实体类型
	 * @throws Exception
     */
	@Transactional
	public <T> void delete(T entity) throws Exception {
		getSession().delete(entity);
	}

	/**
	 * 删除实体集合
	 *
	 * @param entities 实体
	 * @param <T>      实体类型
	 * @throws Exception
     */
	@Transactional
	public <T> void delete(Collection<T> entities) throws Exception {
		for (T entity : entities) {
			getSession().delete(entity);
		}
	}

	/**
	 * 通过单一条件获取唯一实体
	 *
	 * @param cls Class对象
	 * @param fieldName 文件名称
	 * @param value value对象
	 * @param <T> 实体类型
	 * @return 实体
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public <T> T findUniqueByProperty(Class<T> cls, String fieldName, Object value) throws Exception {
		return (T) getSession().createCriteria(cls).add(Restrictions.eq(fieldName, value)).uniqueResult();
	}

	/**
	 * 通过单一条件获取第一个实体
	 *
	 * @param cls Class对象
	 * @param fieldName 文件名称
	 * @param value value对象
	 * @param <T> 实体类型
	 * @return 实体
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T> T findFirstByProperty(Class<T> cls, String fieldName, Object value) throws Exception {
		return (T) getSession().createCriteria(cls).add(Restrictions.eq(fieldName, value)).setMaxResults(1).uniqueResult();
	}

	/**
	 * 通过复数条件获取唯一实体
	 *
	 * @param cls Class对象
	 * @param args 参数map
	 * @param <T> 实体类型
	 * @return 实体
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T> T findUniqueResultByMap(Class<T> cls, Map<String, Object> args) throws Exception {
		Criteria criteria = getSession().createCriteria(cls);
		for (String key : args.keySet()) {
			criteria.add(Restrictions.eq(key, args.get(key)));
		}
		return (T) criteria.uniqueResult();
	}

	/**
	 * 通过单一条件获取实体集合
	 *
	 * @param cls Class对象
	 * @param fieldName 字段名称
	 * @param value value值
	 * @param <T> 实体类型
	 * @return 实体
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T> List<T> findByProperty(Class<T> cls, String fieldName, Object value) throws Exception {
		return getSession().createCriteria(cls).add(Restrictions.eq(fieldName, value)).list();
	}

	/**
	 * 通过主键获取实体
	 *
	 * @param cls  Class对象
	 * @param id ID主键
	 * @param <T> 实体类型
	 * @return 实体
     * @throws Exception
     */
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T> T get(Class<T> cls, Serializable id) throws Exception {
		return (T) getSession().get(cls, id);
	}

	/**
	 * 通过主键获取实体
	 *
	 * @param cls Class对象
	 * @param id ID主键
	 * @param <T> 实体类型
	 * @return 实体
     * @throws Exception
     */
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T> T load(Class<T> cls, Serializable id) throws Exception {
		return (T) getSession().load(cls, id);
	}


	/**
	 * 获取当前登录的管理员
	 * @param request   请求
	 * @return  用户
	 */
	public XtlUserEntity getAdmin(HttpServletRequest request){
		return (XtlUserEntity) request.getSession().getAttribute("SESSION_USER_ADMIN");
	}

}
