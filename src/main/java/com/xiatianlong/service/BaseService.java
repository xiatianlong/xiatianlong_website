package com.xiatianlong.service;

import com.xiatianlong.entity.XtlUserEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * BaseService
 * Created by xiatianlong on 2016/12/30.
 */
public interface BaseService {

	/**
	 * 持久化一个实体对象
	 * 
	 * @param entity 实体
	 * @throws Exception
	 */
	<T> void persist(T entity) throws Exception;

	/**
	 * 持久化一批实体对象
	 * 
	 * @param entities 实体
	 * @throws Exception
	 */
	<T> void persist(Collection<T> entities) throws Exception;

	/**
	 * 更新一个实体对象
	 * 
	 * @param entity 实体
	 * @throws Exception
	 */
	<T> void merge(T entity) throws Exception;

	/**
	 * 更新一批实体对象
	 * 
	 * @param entities 实体
	 * @throws Exception
	 */
	<T> void merge(Collection<T> entities) throws Exception;

	/**
	 * 删除一个实体对象
	 * 
	 * @param entity 实体
	 * @throws Exception
	 */
	<T> void delete(T entity) throws Exception;

	/**
	 * 删除一批实体对象
	 * 
	 * @param entities 实体
	 * @throws Exception
	 */
	<T> void delete(Collection<T> entities) throws Exception;

	/**
	 * 通过单个属性获取唯一实体对象
	 * 
	 * @param cls Class对象
	 * @param fieldName 文件名称
	 * @param value value对象
	 * @return 唯一实体对象
	 * @throws Exception
	 */
	<T> T findUniqueByProperty(Class<T> cls, String fieldName, Object value) throws Exception;

	/**
	 * 通过单个属性获取第一个实体对象
	 * 
	 * @param cls Class对象
	 * @param fieldName 文件名称
	 * @param value value对象
	 * @return 第一个实体对象
	 * @throws Exception
	 */
	<T> T findFirstByProperty(Class<T> cls, String fieldName, Object value) throws Exception;

	/**
	 * 通过多个属性获取唯一实体对象
	 *
	 * @param cls Class对象
	 * @param args 参数map
	 * @return 唯一实体对象
	 * @throws Exception
	 */
	<T> T findUniqueResultByMap(Class<T> cls, Map<String, Object> args) throws Exception;

	/**
	 * 通过单个属性获取一组实体对象
	 * 
	 * @param cls Class对象
	 * @param fieldName 字段名称
	 * @param value value值
	 * @return 一组实体对象
	 * @throws Exception
	 */
	<T> List<T> findByProperty(Class<T> cls, String fieldName, Object value) throws Exception;

	/**
	 * 通过主键获取实体对象
	 * 
	 * @param cls  Class对象
	 * @param id ID主键
	 * @return 实体对象
	 * @throws Exception
	 */
	<T> T get(Class<T> cls, Serializable id) throws Exception;

	/**
	 * 通过主键获取实体对象
	 * 
	 * @param cls Class对象
	 * @param id ID主键
	 * @return 实体对象
	 * @throws Exception
	 */
	<T> T load(Class<T> cls, Serializable id) throws Exception;

	/**
	 * 获取当前登录的管理员
	 * @param request   请求
	 * @return  用户
	 */
	XtlUserEntity getAdmin(HttpServletRequest request);

}
