package com.zimu.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.zimu.common.bean.Pageable;
import com.zimu.common.bean.Pager;
import com.zimu.common.bean.QueryFilter;
import com.zimu.common.bean.QueryFilter.Operator;
import com.zimu.common.bean.QueryOrder;
import com.zimu.common.bean.QueryOrder.Direction;
import com.zimu.common.dao.BaseDao;
import com.zimu.common.utils.BeanUtils;

public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	// 日志记录
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	// 数据库映射实体类型
	protected Class<T> entityClass;

	protected EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDaoImpl() {
		this.entityClass = null;
		Class c = this.getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	public Class<T> getEntityClass() {
		return this.entityClass;
	}

	@SuppressWarnings("rawtypes")
	protected Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, Object value) {
		if (value == null) {
			return path.isNull();
		}
		Predicate term = null;
		// Class<?> javaType = path.getJavaType();
		// logger.info(javaType.getName());
		if (value instanceof Collection) {
			term = path.in((Collection) value);
		} else if (value.getClass().isArray()) {
			Object[] v = null;
			if (value instanceof int[]) {
				v = ArrayUtils.toObject((int[]) value);
			} else if (value instanceof long[]) {
				v = ArrayUtils.toObject((long[]) value);
			} else if (value instanceof byte[]) {
				v = ArrayUtils.toObject((byte[]) value);
			} else if (value instanceof double[]) {
				v = ArrayUtils.toObject((double[]) value);
			} else if (value instanceof char[]) {
				v = ArrayUtils.toObject((char[]) value);
			} else if (value instanceof short[]) {
				v = ArrayUtils.toObject((short[]) value);
			} else if (value instanceof float[]) {
				v = ArrayUtils.toObject((float[]) value);
			} else {
				v = (Object[]) value;
			}
			term = path.in(v);
		} else {
			term = cb.equal(path, value);
		}
		return term;
	}

	@Override
	public void create(Collection<T> objs) {
		int i = 0;
		for (T o : objs) {
			create(o);
			if (++i % 100 == 0) {
				flush();
				clear();
				i = 0;
			}
		}
	}

	@Override
	public void create(T object) {
		merge(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PK save(T entity) {
		Assert.notNull(entity, "entity must not be null");
		return (PK) this.getSession().save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PK save(String entityName, T entity) {
		Assert.hasText(entityName, "entityName must not be empty");
		Assert.notNull(entity, "entity must not be null");
		return (PK) this.getSession().save(entityName, entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T merge(T entity) {
		Assert.notNull(entity, "entity must not be null");
		return (T) this.getSession().merge(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T merge(String entityName, T entity) {
		Assert.hasText(entityName, "entityName must not be empty");
		Assert.notNull(entity, "entity must not be null");
		return (T) this.getSession().merge(entityName, entity);
	}

	@Override
	public void persist(T object) {
		Assert.notNull(object, "object must not be null");
		this.getSession().persist(object);
	}

	@Override
	public void persist(String entityName, T object) {
		Assert.hasText(entityName, "entityName must not be empty");
		Assert.notNull(object, "object must not be null");
		this.getSession().persist(entityName, object);
	}

	@Override
	public void update(T entity) {
		Assert.notNull(entity, "entity must not be null");
		this.getSession().update(entity);
	}

	@Override
	public void update(Collection<T> objs) {

		if (objs == null || objs.isEmpty()) {
			logger.info("update collection is empty");
			return;
		}

		int i = 0;
		for (T obj : objs) {
			this.getSession().update(obj);
			if (++i % 100 == 0) {
				flush();
				clear();
				i = 0;
			}
		}
	}

	@Override
	public void update(String entityName, T entity) {
		Assert.hasText(entityName, "entityName must not be empty");
		Assert.notNull(entity, "entity must not be null");
		this.getSession().update(entityName, entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		Assert.notNull(entity, "entity must not be null");
		this.getSession().saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdate(String entityName, T entity) {
		Assert.hasText(entityName, "entityName must not be empty");
		Assert.notNull(entity, "entity must not be null");
		this.getSession().saveOrUpdate(entityName, entity);
	}

	@Override
	public void delete(PK id) {
		Assert.notNull(id, "id must not be null");
		T entity = get(id);
		Assert.notNull(entity, "entity must not be null");
		this.getSession().delete(entity);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public int delete(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		String hql = "DELETE FROM " + this.entityClass.getName() + " WHERE ID IN (:ids)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("ids", ids);
		return query.executeUpdate();
	}

	@Override
	public void delete(Collection<T> pos) {

		if (pos == null || pos.isEmpty()) {
			logger.info("delete collection is empty");
			return;
		}
		for (T obj : pos) {
			this.getSession().delete(obj);
		}

	}

	@Override
	public void delete(T entity) {
		Assert.notNull(entity, "entity must not be null");
		this.getSession().delete(entity);
	}

	@Override
	public void delete(String entityName, T entity) {
		Assert.hasText(entityName, "entityName must not be empty");
		Assert.notNull(entity, "entity must not be null");
		this.getSession().delete(entityName, entity);
	}

	@Override
	public int deleteByField(SingularAttribute<T, ?> propertyName, Object value) {
		Assert.notNull(value, "value must not be null");
		return deleteByField(propertyName.getName(), value);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int deleteByField(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value must not be null");
		String hql = "DELETE FROM " + this.entityClass.getName() + " WHERE " + propertyName + "=:" + propertyName;
		Query query = getSession().createQuery(hql);
		query.setParameter(propertyName, value);
		return query.executeUpdate();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int deleteAll() {
		String hql = "DELETE FROM " + this.entityClass.getName();
		Query query = getSession().createQuery(hql);
		return query.executeUpdate();
	}

	@Override
	public List<T> findAll() {
		return this.findAll((String) null);
	}

	@Override
	public List<T> findAll(SingularAttribute<T, ?> orderBy) {
		return findAll(orderBy.getName());
	}

	@Override
	public List<T> findAll(String orderBy) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(entityClass);
		Root<T> root = q.from(entityClass);
		q.select(root);
		if (StringUtils.isNotBlank(orderBy)) {
			q.orderBy(cb.asc(root.get(orderBy)));
		}
		return this.findByCriteria(q);
	}

	@Override
	public List<T> findByField(SingularAttribute<T, ?> propertyName, Object value) {
		Assert.notNull(value, "value must not be null");
		return findByField(propertyName.getName(), value);
	}

	@Override
	public List<T> findByField(SingularAttribute<T, ?> propertyName, Object value, SingularAttribute<T, ?> orderBy) {
		Assert.notNull(value, "value must not be null");
		return findByField(propertyName.getName(), value, orderBy.getName());
	}

	@Override
	public List<T> findByField(String propertyName, Object value) {
		return this.findByField(propertyName, value, null);
	}

	@Override
	public List<T> findByField(String propertyName, Object value, String orderBy) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value must not be null");

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(entityClass);
		Root<T> root = q.from(entityClass);
		q.select(root);
		q.where(cb.equal(root.get(propertyName), value));
		if (StringUtils.isNotBlank(orderBy)) {
			q.orderBy(cb.asc(root.get(orderBy)));
		}

		return this.findByCriteria(q);
	}

	@Override
	public List<T> findByFields(Map<String, Object> params) {
		return this.findByFields(params, (String) null);
	}

	@Override
	public List<T> findByFields(Map<String, Object> params, SingularAttribute<T, ?> orderBy) {
		Assert.notNull(orderBy, "orderBy must not be null");
		return findByFields(params, orderBy.getName());
	}

	@Override
	public List<T> findByFields(Map<String, Object> params, String orderBy) {
		Assert.hasText(orderBy, "orderBy must not be empty");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(entityClass);
		Root<T> root = q.from(entityClass);
		q.select(root);
		if (params != null && !params.isEmpty()) {
			List<Predicate> predicates = new ArrayList<>();
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
			}
			q.where(predicates.toArray(new Predicate[0]));
		}
		if (StringUtils.isNotBlank(orderBy)) {
			q.orderBy(cb.asc(root.get(orderBy)));
		}

		return this.findByCriteria(q);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager<T> findByPager(Pageable pager) {

		if (pager == null) {
			pager = new Pageable();
		}

		// 根
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		cq.select(root);

		// 查询参数
		List<Predicate> predicates = new ArrayList<Predicate>();
		List<QueryFilter> params = pager.getFilters();
		if (StringUtils.isNotBlank(pager.getProperty()) && StringUtils.isNotBlank(pager.getKeyword())) {
			QueryFilter term = new QueryFilter();
			term.setProperty(pager.getProperty());
			term.setValue(pager.getKeyword());
			term.setOperator(pager.getSymbol());
			params.add(term);
		}

		for (QueryFilter param : params) {
			String key = param.getProperty();
			Object value = param.getValue();
			Operator operator = param.getOperator();
			if (operator == null) {
				operator = Operator.like;
			}
			if (StringUtils.isNotEmpty(key) && value != null) {
				Path x = root.get(key);
				Class javaType = x.getJavaType();
				switch (operator) {
				case eq:
					predicates.add(cb.equal(root.get(key), value));
					break;
				case ne:
					predicates.add(cb.notEqual(root.get(key), value));
					break;
				case between:
					Object[] obj1 = null;
					if (value instanceof Object[]) {
						obj1 = (Object[]) value;
					} else if (value instanceof Collection) {
						obj1 = ((Collection) value).toArray();
					}
					if (obj1 != null && obj1.length == 2) {
						// predicates.add(cb.between(root.get(key), obj1[0], obj1[1]));
					}
					break;
				case lt:
					if (Number.class.isAssignableFrom(javaType) && NumberUtils.isNumber(value.toString())) {
						predicates.add(cb.lt(root.get(key), (Number) value));
					}
					break;
				case le:
					if (Number.class.isAssignableFrom(javaType) && NumberUtils.isNumber(value.toString())) {
						predicates.add(cb.le(root.get(key), (Number) value));
					}
					break;
				case gt:
					if (Number.class.isAssignableFrom(javaType) && NumberUtils.isNumber(value.toString())) {
						predicates.add(cb.gt(root.get(key), (Number) value));
					}
					break;
				case ge:
					if (Number.class.isAssignableFrom(javaType) && NumberUtils.isNumber(value.toString())) {
						predicates.add(cb.ge(root.get(key), (Number) value));
					}
					break;
				case like:
					predicates.add(cb.like(root.get(key), "%" + value + "%"));
					break;
				case notLike:
					predicates.add(cb.notLike(root.get(key), "%" + value + "%"));
					break;
				case in:
					Object[] obj2 = null;
					if (value instanceof Object[]) {
						obj2 = (Object[]) value;
					} else if (value instanceof Collection) {
						obj2 = ((Collection) value).toArray();
					}
					if (obj2 != null && obj2.length != 0) {
						cb.in(root.get(key)).value(obj2);
					}
					break;
				default:
					break;
				}

			} else {

				switch (operator) {
				case isEmpty:
					predicates.add(cb.isEmpty(root.get(key)));
					break;
				case isNotEmpty:
					predicates.add(cb.isNotEmpty(root.get(key)));
					break;
				case isNull:
					predicates.add(cb.isNull(root.get(key)));
					break;
				case isNotNull:
					predicates.add(cb.isNotNull(root.get(key)));
					break;
				default:
					break;
				}

			}
		}

		cq.where(cb.and(predicates.toArray(new Predicate[0])));
		return findByPager(cq, pager);

	}

	@Override
	public <E> Pager<E> findByPager(CriteriaQuery<E> ocq, Pageable pager) {
		if (pager == null) {
			pager = new Pageable();
		}
		Integer pageNumber = pager.getPageNum();
		Integer pageSize = pager.getPageSize();

		//
		CriteriaBuilder cb = getCriteriaBuilder();

		// 执行count查询
		Long totalCount = this.getCountByCriteria(ocq);
		Pager<E> result = new Pager<E>(pager);
		if (totalCount == null || totalCount <= 0) {
			return result;
		}

		// 排序操作
		Set<Root<?>> roots = ocq.getRoots();
		Root<?> root = roots.iterator().next();

		// 2.加入pager中传入的排序
		String orderBy = pager.getOrderBy();
		Direction orderType = pager.getOrderType();

		// 单个排序条件
		if (StringUtils.isNotEmpty(orderBy) && orderType != null) {
			if (orderType == Direction.desc) {
				ocq.orderBy(cb.desc(root.get(orderBy)));
			} else {
				ocq.orderBy(cb.asc(root.get(orderBy)));
			}
		}

		// 多个排序条件
		List<QueryOrder> params = pager.getSortClauses();
		if (params != null && !params.isEmpty()) {
			for (QueryOrder param : params) {
				orderBy = param.getProperty();
				orderType = param.getDirection();
				if (StringUtils.isNotEmpty(orderBy) && orderType != null) {
					if (orderType == Direction.desc) {
						ocq.orderBy(cb.desc(root.get(orderBy)));
					} else {
						ocq.orderBy(cb.asc(root.get(orderBy)));
					}
				}
			}
		}
		//
		TypedQuery<E> query = getEntityManager().createQuery(ocq);
		int startIndex = (pageNumber - 1) * pageSize;
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		result = new Pager<E>(pager, query.getResultList(), totalCount.intValue());
		return result;
	}

	public List<T> findByCriteria(CriteriaQuery<T> c) {
		TypedQuery<T> query = getEntityManager().createQuery(c);
		return query.getResultList();
	}

	public List<T> findByCriteria(CriteriaQuery<T> c, int firstResult, int maxResults) {
		TypedQuery<T> query = getEntityManager().createQuery(c);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	@Override
	public T get(PK id) {
		Assert.notNull(id, "id must not be null");
		return (T) this.getSession().get(this.entityClass, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get(String entityName, PK id) {
		Assert.hasText(entityName, "entityName must not be empty");
		Assert.notNull(id, "id must not be null");
		return (T) this.getSession().get(entityName, id);
	}

	@Override
	public T get(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value must not be null");
		return this.getByField(propertyName, value);
	}

	@Override
	public List<T> get(List<PK> ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(entityClass);
		Root<T> root = q.from(entityClass);
		q.select(root);
		q.where(cb.in(root.get("id")).value(ids));
		TypedQuery<T> typedQuery = entityManager.createQuery(q);
		return typedQuery.getResultList();
	}

	@Override
	public List<T> get(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		List<PK> list = (List<PK>) Arrays.asList(ids);
		return get(list);
	}

	@Override
	public List<T> getAll() {
		return this.findAll();
	}

	@Override
	public T getByField(SingularAttribute<T, ?> propertyName, Object value) {
		Assert.notNull(value, "value must not be null");
		return this.getByField(propertyName.getName(), value);
	}

	@Override
	public T getByField(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value must not be null");
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(entityClass);
		Root<T> root = q.from(entityClass);
		q.select(root);
		q.where(cb.equal(root.get(propertyName), value));

		return this.getByCriteria(q);
	}

	@Override
	public T getByFields(Map<String, Object> params) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(entityClass);
		Root<T> root = q.from(entityClass);
		q.select(root);
		if (params != null && !params.isEmpty()) {
			List<Predicate> predicates = new ArrayList<>();
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
			}
			q.where(predicates.toArray(new Predicate[0]));
		}

		return this.getByCriteria(q);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public T getByCriteria(CriteriaQuery<T> c) {
		TypedQuery<T> query = getEntityManager().createQuery(c);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public T load(PK id) {
		Assert.notNull(id, "id must not be null");
		return (T) this.getSession().load(this.entityClass, id);
	}

	@Override
	public void load(Object object, PK id) {
		Assert.notNull(object, "object must not be null");
		Assert.notNull(id, "id must not be null");
		this.getSession().load(object, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T load(String entityName, PK id) {
		Assert.hasText(entityName, "entityName must not be empty");
		Assert.notNull(id, "id must not be null");
		return (T) this.getSession().load(entityName, id);
	}

	@Override
	public void refresh(Object object) {
		Assert.notNull(object, "object must not be null");
		this.getSession().refresh(object);
	}

	@Override
	public void refresh(String entityName, Object object) {
		Assert.hasText(entityName, "entityName must not be empty");
		Assert.notNull(object, "object must not be null");
		this.getSession().refresh(entityName, object);
	}

	@Override
	public long getCount() {
		return this.getCount(null);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> q = cb.createQuery(Long.class);
		Root<T> root = q.from(entityClass);
		q.select(cb.count(root));
		if (params != null && !params.isEmpty()) {
			List<Predicate> predicates = new ArrayList<>();
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
			}
			q.where(predicates.toArray(new Predicate[0]));
		}

		TypedQuery<Long> typedQuery = entityManager.createQuery(q);
		return typedQuery.getSingleResult().longValue();
	}

	@Override
	public long getCount(String propertyName, Object value) {
		Map<String, Object> params = new HashMap<>();
		params.put(propertyName, value);
		return this.getCount(params);
	}

	@Override
	public long getCount(SingularAttribute<T, ?> propertyName, Object value) {
		Assert.notNull(value, "value must not be null");
		return this.getCount(propertyName.getName(), value);
	}

	@Override
	public <E> Long getCountByCriteria(CriteriaQuery<E> cq) {
		//
		CriteriaBuilder cb = getCriteriaBuilder();

		// 执行count查询
		Set<Root<?>> roots = cq.getRoots();
		Root<?> root = roots.iterator().next();
		CriteriaQuery<Long> ccq = cb.createQuery(Long.class);
		try {
			Object qs = BeanUtils.getPrivateProperty(ccq, "queryStructure");
			BeanUtils.setPrivateProperty(qs, "roots", roots);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}

		ccq.select(cb.count(root));
		Predicate restriction = cq.getRestriction();
		if (restriction != null) {
			ccq.where(restriction);
		}
		// 查询总条数
		TypedQuery<Long> ctq = getEntityManager().createQuery(ccq);
		Long totalCount = ctq.getSingleResult();
		return totalCount;
	}

	@Override
	public long getTotalCount() {
		return getCount();
	}

	@Override
	public boolean isExist(PK id) {
		Assert.notNull(id, "id must not be null");
		T entity = (T) this.getSession().get(this.entityClass, id);
		return entity != null;
	}

	@Override
	public boolean isExist(T object) {
		Assert.notNull(object, "object must not be null");
		return this.getSession().contains(object);
	}

	@Override
	public boolean isExist(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value must not be null");
		T entity = getByField(propertyName, value);
		if (entity == null) {
			return false;
		}
		return true;

	}

	@Override
	public boolean isExist(SingularAttribute<T, ?> propertyName, Object value) {
		Assert.notNull(value, "value must not be null");
		return isExist(propertyName.getName(), value);
	}

	@Override
	public void clear() {
		this.getSession().clear();
	}

	@Override
	public void flush() {
		this.getSession().flush();
	}

	@Override
	public void evict(T entity) {
		Assert.notNull(entity, "entity must not be null");
		this.getSession().evict(entity);
	}

	/**
	 * 
	 * @Title: getSession
	 * 
	 * @Description: 获取Session
	 * 
	 * @return Session
	 * 
	 * @throws
	 */
	protected Session getSession() {
		return this.entityManager.unwrap(Session.class);
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected CriteriaBuilder getCriteriaBuilder() {
		return getEntityManager().getCriteriaBuilder();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Predicate markPredicate(Operator symbolType, Comparable keyword, Path path) {
		Predicate rtn = null;
		CriteriaBuilder cb = getCriteriaBuilder();
		switch (symbolType) {
		case eq:
			rtn = cb.equal(path, keyword);
			break;
		case like:
			if (keyword instanceof String)
				rtn = cb.like(path, "%" + keyword + "%");
			break;
		case gt:
			rtn = cb.greaterThan(path, keyword);
			break;
		case lt:
			if (keyword instanceof Comparable)
				rtn = cb.lessThan(path, keyword);
			break;
		case isNull:
			if (keyword instanceof Comparable)
				rtn = cb.isNull(path);
			break;
		default:
			break;
		}
		return rtn;
	}

	/**
	 * 根据给定的条件进行组合查询 等价的Sql语句为 select * from mainTable where mainTable.mainCol in (select subCol from subTable where subTable.subQureyCol |*symbolType*| keyword); symbolType 当前仅取可">,<,=,like"，参见SymbolType 枚举类型。
	 * 
	 * @param mainTable
	 *            查询主表对象
	 * @param subTable
	 *            查询关联表对象
	 * @param mainCol
	 *            查询主表与关联表所关联的字段
	 * @param subCol
	 *            查询关联表与主表所关联的字段
	 * @param colClass
	 *            字段类型
	 * @param subQureyCol查询条件所对应的查询关联表字段
	 * @param symbolType
	 *            查询条件类型
	 * @param keyword
	 *            查询条件取值
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Predicate markSubQuery(Class mainTable, Class subTable, SingularAttribute mainCol,
			SingularAttribute subCol, Class colClass, SingularAttribute subQureyCol, Operator symbolType,
			String keyword) {
		Predicate equal = null;
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery c = cb.createQuery(mainTable);
		Root root = c.from(mainTable);
		Path path = root.get(mainCol);
		Subquery subquery = c.subquery(colClass);
		Root subfrom = subquery.from(subTable);
		subquery.select(subfrom.get(subCol));
		subquery.where(markPredicate(symbolType, keyword, subfrom.get(subQureyCol)));
		equal = path.in(subquery);
		return equal;
	}

}