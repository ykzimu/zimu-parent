package com.zimu.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.SingularAttribute;

import com.zimu.common.bean.Pageable;
import com.zimu.common.bean.Pager;

/**
 * 
 * @ClassName: BaseDao
 * 
 * @Description: Dao接口
 * 
 * @author yangkun
 * 
 * @date 2018年3月12日 下午2:13:22
 * 
 * @param <T>
 *            实体类
 * 
 * @param <PK>
 *            主键
 */
public interface BaseDao<T, PK extends Serializable> {

	void create(Collection<T> objs);

	void create(T object);

	PK save(T entity);

	PK save(String entityName, T entity);

	T merge(T entity);

	T merge(String entityName, T entity);

	void persist(T object);

	void persist(String entityName, T object);

	void update(T entity);

	void update(Collection<T> objs);

	void update(String entityName, T entity);

	void saveOrUpdate(T entity);

	void saveOrUpdate(String entityName, T entity);

	void delete(PK id);

	int delete(PK[] ids);

	void delete(T entity);

	void delete(String entityName, T entity);

	void delete(Collection<T> pos);

	int deleteByField(SingularAttribute<T, ?> propertyName, Object value);

	int deleteByField(String propertyName, Object value);

	int deleteAll();

	List<T> findAll();

	List<T> findAll(SingularAttribute<T, ?> orderBy);

	List<T> findAll(String orderBy);

	List<T> findByField(SingularAttribute<T, ?> propertyName, Object value);

	List<T> findByField(SingularAttribute<T, ?> propertyName, Object value, SingularAttribute<T, ?> orderBy);

	List<T> findByField(String propertyName, Object value);

	List<T> findByField(String propertyName, Object value, String orderBy);

	List<T> findByFields(Map<String, Object> params);

	List<T> findByFields(Map<String, Object> params, SingularAttribute<T, ?> orderBy);

	List<T> findByFields(Map<String, Object> params, String orderBy);

	Pager<T> findByPager(Pageable pager);

	<E> Pager<E> findByPager(CriteriaQuery<E> cq, Pageable pager);

	T get(PK id);

	T get(String entityName, PK id);

	T get(String propertyName, Object value);

	List<T> get(List<PK> ids);

	List<T> get(PK[] ids);

	List<T> getAll();

	T getByField(SingularAttribute<T, ?> propertyName, Object value);

	T getByField(String propertyName, Object value);

	T getByFields(Map<String, Object> params);

	T load(PK id);

	void load(Object object, PK id);

	T load(String entityName, PK id);

	void refresh(Object object);

	void refresh(String entityName, Object object);

	long getCount();

	long getCount(Map<String, Object> params);

	long getCount(String propertyName, Object value);

	long getCount(SingularAttribute<T, ?> propertyName, Object value);

	<E> Long getCountByCriteria(CriteriaQuery<E> cq);

	long getTotalCount();

	boolean isExist(PK id);

	boolean isExist(T object);

	boolean isExist(String propertyName, Object value);

	boolean isExist(SingularAttribute<T, ?> propertyName, Object value);

	void clear();

	void flush();

	void evict(T entity);
}
