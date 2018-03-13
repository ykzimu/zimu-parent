package com.zimu.common.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;

import com.zimu.common.bean.Pageable;
import com.zimu.common.bean.Pager;
import com.zimu.common.dao.BaseDao;
import com.zimu.common.service.BaseService;

/**
 * Service实现类 - Service实现类基类
 */

public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

	protected BaseDao<T, PK> baseDao;

	protected BaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	@Autowired
	public void setBaseDao(BaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}

	public void create(T object) {
		baseDao.create(object);
	}

	public void create(Collection<T> objs) {
		baseDao.create(objs);
	}

	public T get(PK id) {
		return baseDao.get(id);
	}

	public T load(PK id) {
		return baseDao.load(id);
	}

	public List<T> get(PK[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.emptyList();
		}
		return baseDao.get(ids);
	}

	public T getByField(String propertyName, Object value) {
		return baseDao.getByField(propertyName, value);
	}

	public T getByField(SingularAttribute<T, ?> name, Object value) {
		return baseDao.getByField(name, value);
	}

	public T get(String propertyName, Object value) {
		return baseDao.getByField(propertyName, value);
	}

	public T get(SingularAttribute<T, ?> name, Object value) {
		return baseDao.getByField(name, value);
	}

	public List<T> findByField(String propertyName, Object value) {
		return baseDao.findByField(propertyName, value);
	}

	public List<T> findByField(SingularAttribute<T, ?> name, Object value) {
		return baseDao.findByField(name, value);
	}

	public List<T> getList(String propertyName, Object value) {
		return baseDao.findByField(propertyName, value);
	}

	public List<T> getList(SingularAttribute<T, ?> name, Object value) {
		return baseDao.findByField(name, value);
	}

	public List<T> getAll() {
		return baseDao.findAll();
	}

	public Long getTotalCount() {
		return baseDao.getTotalCount();
	}

	public boolean isUnique(String propertyName, Object oldValue, Object newValue) {
		// return baseDao.isUnique(propertyName, oldValue, newValue);
		return true;
	}

	public boolean isExist(String propertyName, Object value) {
		return baseDao.isExist(propertyName, value);
	}

	public PK save(T entity) {
		return baseDao.save(entity);
	}

	public void update(T entity) {
		baseDao.update(entity);
	}

	public void delete(T entity) {
		baseDao.delete(entity);
	}

	public void delete(PK id) {
		baseDao.delete(id);
	}

	public void delete(PK[] ids) {
		if (ids == null || ids.length == 0) {
			return;
		}
		baseDao.delete(ids);
	}

	public void delete(Collection<T> entities) {
		if (entities == null || entities.isEmpty()) {
			return;
		}
		baseDao.delete(entities);
	}

	public int deleteByField(SingularAttribute<T, ?> name, Object value) {
		return baseDao.deleteByField(name, value);
	}

	public void flush() {
		baseDao.flush();
	}

	public void clear() {
		baseDao.clear();
	}

	public void detach(Object object) {
		// baseDao.detach(object);
	}

	public Pager<T> findByPager(Pageable pager) {
		return baseDao.findByPager(pager);
	}

	public Pager<T> findByPager(CriteriaQuery<T> c, Pageable pager) {
		return baseDao.findByPager(c, pager);
	}
}
