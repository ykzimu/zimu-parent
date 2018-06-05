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
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.Session;

import com.zimu.common.bean.Pageable;
import com.zimu.common.bean.Pager;
import com.zimu.common.bean.QueryFilter;
import com.zimu.common.bean.QueryFilter.Operator;
import com.zimu.common.bean.QueryOrder;
import com.zimu.common.bean.QueryOrder.Direction;
import com.zimu.common.dao.BaseDao;

public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	private static final String DEFAULT_PK_NAME = "id";

	/**
	 * 数据库映射实体类型
	 */
	protected Class<T> entityClass;

	/**
	 * EntityManager
	 */
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

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected Class<T> getEntityClass() {
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
		return (PK) this.getSession().save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> PK save(String entityName, E entity) {
		return (PK) this.getSession().save(entityName, entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T merge(T entity) {
		return (T) this.getSession().merge(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> E merge(String entityName, E entity) {
		return (E) this.getSession().merge(entityName, entity);
	}

	@Override
	public void persist(T object) {
		this.getSession().persist(object);
	}

	@Override
	public <E> void persist(String entityName, E object) {
		this.getSession().persist(entityName, object);
	}

	@Override
	public void update(T entity) {
		this.getSession().update(entity);
	}

	@Override
	public void update(Collection<T> objs) {
		if (objs == null || objs.isEmpty()) {
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
	public <E> void update(String entityName, E entity) {
		this.getSession().update(entityName, entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		this.getSession().saveOrUpdate(entity);
	}

	@Override
	public <E> void saveOrUpdate(String entityName, E entity) {
		this.getSession().saveOrUpdate(entityName, entity);
	}

	@Override
	public void delete(PK id) {
		T entity = get(id);
		if (entity != null) {
			this.getSession().delete(entity);
		}

	}

	@Override
	public int delete(List<PK> ids) {
		return this.delete(ids, null);
	}

	@Override
	public int delete(PK[] ids) {
		if (ids == null || ids.length == 0) {
			return 0;
		}
		List<PK> list = (List<PK>) Arrays.asList(ids);
		return this.delete(list, null);
	}

	@Override
	public int delete(List<PK> ids, SingularAttribute<T, PK> id) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaDelete<T> cd = cb.createCriteriaDelete(this.entityClass);
		Root<T> root = cd.from(this.entityClass);
		String pkName = DEFAULT_PK_NAME;
		if (id != null) {
			pkName = id.getName();
		}
		cd.where(cb.in(root.get(pkName)).value(ids));
		return this.entityManager.createQuery(cd).executeUpdate();
	}

	@Override
	public int delete(PK[] ids, SingularAttribute<T, PK> id) {
		if (ids == null || ids.length == 0) {
			return 0;
		}
		List<PK> list = (List<PK>) Arrays.asList(ids);
		return this.delete(list, id);
	}

	@Override
	public void delete(Collection<T> pos) {
		if (pos == null || pos.isEmpty()) {
			return;
		}
		for (T obj : pos) {
			this.getSession().delete(obj);
		}

	}

	@Override
	public void delete(T entity) {
		this.getSession().delete(entity);
	}

	@Override
	public <E> void delete(String entityName, E entity) {
		this.getSession().delete(entityName, entity);
	}

	@Override
	public int deleteByField(SingularAttribute<T, ?> propertyName, Object value) {
		return deleteByField(propertyName.getName(), value);
	}

	@Override
	public int deleteByField(String propertyName, Object value) {
		Map<String, Object> params = new HashMap<>();
		params.put(propertyName, value);
		return deleteByMap(params);
	}

	@Override
	public int deleteAll() {
		return this.deleteByMap(null);
	}

	private int deleteByMap(Map<String, Object> params) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaDelete<T> cd = cb.createCriteriaDelete(this.entityClass);
		Root<T> root = cd.from(this.entityClass);
		if (params != null && !params.isEmpty()) {
			List<Predicate> predicates = new ArrayList<>();
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
			}
			cd.where(predicates.toArray(new Predicate[0]));
		}
		return this.entityManager.createQuery(cd).executeUpdate();
	}

	@Override
	public List<T> findAll() {
		return this.findByMap(null, null);
	}

	@Override
	public List<T> findAll(SingularAttribute<T, ?> orderBy) {
		return this.findByMap(null, orderBy.getName());
	}

	@Override
	public List<T> findAll(String orderBy) {
		return this.findByMap(null, orderBy);
	}

	@Override
	public List<T> findByField(SingularAttribute<T, ?> propertyName, Object value) {
		return this.findByField(propertyName.getName(), value);
	}

	@Override
	public List<T> findByField(SingularAttribute<T, ?> propertyName, Object value, SingularAttribute<T, ?> orderBy) {
		return this.findByField(propertyName.getName(), value, orderBy.getName());
	}

	@Override
	public List<T> findByField(String propertyName, Object value) {
		return this.findByField(propertyName, value, null);
	}

	@Override
	public List<T> findByField(String propertyName, Object value, String orderBy) {
		Map<String, Object> params = new HashMap<>();
		params.put(propertyName, value);
		return this.findByMap(params, orderBy);
	}

	@Override
	public List<T> findByFields(Map<String, Object> params) {
		return this.findByMap(params, null);
	}

	@Override
	public List<T> findByFields(Map<String, Object> params, SingularAttribute<T, ?> orderBy) {
		return this.findByMap(params, orderBy.getName());
	}

	@Override
	public List<T> findByFields(Map<String, Object> params, String orderBy) {
		return this.findByMap(params, orderBy);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager<T> findByPager(Pageable pageable) {

		if (pageable == null) {
			pageable = new Pageable();
		}

		// 根
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
		Root<T> root = cq.from(this.entityClass);
		cq.select(root);

		// 查询参数
		List<Predicate> predicates = new ArrayList<Predicate>();
		List<QueryFilter> params = pageable.getFilters();
		if (StringUtils.isNotBlank(pageable.getProperty()) && StringUtils.isNotBlank(pageable.getKeyword())) {
			QueryFilter term = new QueryFilter();
			term.setProperty(pageable.getProperty());
			term.setValue(pageable.getKeyword());
			term.setOperator(pageable.getSymbol());
			params.add(term);
		}

		for (QueryFilter param : params) {
			String key = param.getProperty();
			Object value = param.getValue();
			Operator operator = param.getOperator();
			if (operator == null) {
				operator = Operator.like;
			}
			if (StringUtils.isNotBlank(key) && value != null) {
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
						predicates.add(cb.between(root.get(key), (Comparable) obj1[0], (Comparable) obj1[1]));
					}
					break;
				case lt:
					if (Number.class.isAssignableFrom(javaType) && NumberUtils.isCreatable(value.toString())) {
						predicates.add(cb.lt(root.get(key), (Number) value));
					}
					break;
				case le:
					if (Number.class.isAssignableFrom(javaType) && NumberUtils.isCreatable(value.toString())) {
						predicates.add(cb.le(root.get(key), (Number) value));
					}
					break;
				case gt:
					if (Number.class.isAssignableFrom(javaType) && NumberUtils.isCreatable(value.toString())) {
						predicates.add(cb.gt(root.get(key), (Number) value));
					}
					break;
				case ge:
					if (Number.class.isAssignableFrom(javaType) && NumberUtils.isCreatable(value.toString())) {
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
		return findByPager(cq, pageable);

	}

	@Override
	public <E> Pager<E> findByPager(CriteriaQuery<E> cq, Pageable pageable) {
		if (pageable == null) {
			pageable = new Pageable();
		}
		Integer pageNumber = pageable.getPageNum();
		Integer pageSize = pageable.getPageSize();

		//
		CriteriaBuilder cb = getCriteriaBuilder();

		// 执行count查询
		Long totalCount = this.getCountByCriteria(cq);
		Pager<E> result = new Pager<E>(pageable);
		if (totalCount == null || totalCount <= 0) {
			return result;
		}

		// 排序操作
		Set<Root<?>> roots = cq.getRoots();
		Root<?> root = roots.iterator().next();

		// 2.加入pager中传入的排序
		String orderBy = pageable.getOrderBy();
		Direction orderType = pageable.getOrderType();

		// 单个排序条件
		if (StringUtils.isNotBlank(orderBy) && orderType != null) {
			if (orderType == Direction.desc) {
				cq.orderBy(cb.desc(root.get(orderBy)));
			} else {
				cq.orderBy(cb.asc(root.get(orderBy)));
			}
		}

		// 多个排序条件
		List<QueryOrder> params = pageable.getSortClauses();
		if (params != null && !params.isEmpty()) {
			for (QueryOrder param : params) {
				orderBy = param.getProperty();
				orderType = param.getDirection();
				if (StringUtils.isNotBlank(orderBy) && orderType != null) {
					if (orderType == Direction.desc) {
						cq.orderBy(cb.desc(root.get(orderBy)));
					} else {
						cq.orderBy(cb.asc(root.get(orderBy)));
					}
				}
			}
		}
		//
		TypedQuery<E> query = this.entityManager.createQuery(cq);
		int startIndex = (pageNumber - 1) * pageSize;
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		result = new Pager<E>(pageable, query.getResultList(), totalCount.longValue());
		return result;
	}

	public List<T> findByCriteria(CriteriaQuery<T> cq) {
		TypedQuery<T> query = this.entityManager.createQuery(cq);
		return query.getResultList();
	}

	public List<T> findByCriteria(CriteriaQuery<T> cq, int firstResult, int maxResults) {
		TypedQuery<T> query = this.entityManager.createQuery(cq);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	private List<T> findByMap(Map<String, Object> params, String orderBy) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
		Root<T> root = cq.from(this.entityClass);
		cq.select(root);
		if (params != null && !params.isEmpty()) {
			List<Predicate> predicates = new ArrayList<>();
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
			}
			cq.where(predicates.toArray(new Predicate[0]));
		}
		if (StringUtils.isNotBlank(orderBy)) {
			cq.orderBy(cb.asc(root.get(orderBy)));
		}

		return this.findByCriteria(cq);
	}

	@Override
	public T get(PK id) {
		return (T) this.getSession().get(this.entityClass, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E> E get(String entityName, PK id) {
		return (E) this.getSession().get(entityName, id);
	}

	@Override
	public T get(String propertyName, Object value) {
		return this.getByField(propertyName, value);
	}

	@Override
	public List<T> get(List<PK> ids) {
		return this.get(ids, null);
	}

	@Override
	public List<T> get(PK[] ids) {
		List<PK> list = (List<PK>) Arrays.asList(ids);
		return this.get(list, null);
	}

	@Override
	public List<T> get(List<PK> ids, SingularAttribute<T, PK> id) {
		if (ids == null || ids.isEmpty()) {
			return new ArrayList<T>();
		}
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
		Root<T> root = cq.from(entityClass);
		cq.select(root);
		String pkName = DEFAULT_PK_NAME;
		if (id != null) {
			pkName = id.getName();
		}
		cq.where(cb.in(root.get(pkName)).value(ids));
		TypedQuery<T> typedQuery = this.entityManager.createQuery(cq);
		return typedQuery.getResultList();
	}

	@Override
	public List<T> get(PK[] ids, SingularAttribute<T, PK> id) {
		List<PK> list = (List<PK>) Arrays.asList(ids);
		return this.get(list, id);
	}

	@Override
	public List<T> getAll() {
		return this.findAll();
	}

	@Override
	public T getByField(SingularAttribute<T, ?> propertyName, Object value) {
		return this.getByField(propertyName.getName(), value);
	}

	@Override
	public T getByField(String propertyName, Object value) {
		Map<String, Object> params = new HashMap<>();
		params.put(propertyName, value);
		return this.getByFields(params);
	}

	@Override
	public T getByFields(Map<String, Object> params) {
		List<T> restlt = this.findByMap(params, null);
		if (restlt == null || restlt.size() > 1) {
			return null;
		}
		return restlt.get(0);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public T getByCriteria(CriteriaQuery<T> cq) {

		try {
			TypedQuery<T> query = this.entityManager.createQuery(cq);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public T load(PK id) {
		return (T) this.getSession().load(this.entityClass, id);
	}

	@Override
	public void load(Object object, PK id) {
		this.getSession().load(object, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E> E load(String entityName, PK id) {
		return (E) this.getSession().load(entityName, id);
	}

	@Override
	public void refresh(Object object) {
		this.getSession().refresh(object);
	}

	@Override
	public void refresh(String entityName, Object object) {
		this.getSession().refresh(entityName, object);
	}

	@Override
	public long getCount() {
		return this.getCount(null);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<T> root = cq.from(this.entityClass);
		cq.select(cb.count(root));
		if (params != null && !params.isEmpty()) {
			List<Predicate> predicates = new ArrayList<>();
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
			}
			cq.where(predicates.toArray(new Predicate[0]));
		}

		TypedQuery<Long> query = this.entityManager.createQuery(cq);
		Long totalCount = query.getSingleResult();
		return totalCount;
	}

	@Override
	public long getCount(String propertyName, Object value) {
		Map<String, Object> params = new HashMap<>();
		params.put(propertyName, value);
		return this.getCount(params);
	}

	@Override
	public long getCount(SingularAttribute<T, ?> propertyName, Object value) {
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
		ccq.getRoots().addAll(roots);
		ccq.select(cb.count(root));
		Predicate restriction = cq.getRestriction();
		if (restriction != null) {
			ccq.where(restriction);
		}
		// 查询总条数
		TypedQuery<Long> ctq = this.entityManager.createQuery(ccq);
		Long totalCount = ctq.getSingleResult();
		return totalCount;
	}

	@Override
	public long getTotalCount() {
		return getCount();
	}

	@Override
	public boolean isExist(PK id) {
		T entity = (T) this.getSession().get(this.entityClass, id);
		return entity != null;
	}

	@Override
	public boolean isExist(T object) {
		return this.getSession().contains(object);
	}

	@Override
	public boolean isExist(String propertyName, Object value) {
		T entity = getByField(propertyName, value);
		if (entity == null) {
			return false;
		}
		return true;

	}

	@Override
	public boolean isExist(SingularAttribute<T, ?> propertyName, Object value) {
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

	protected CriteriaBuilder getCriteriaBuilder() {
		return this.entityManager.getCriteriaBuilder();
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
	 * 根据给定的条件进行组合查询 等价的Sql语句为 select * from mainTable where mainTable.mainCol in
	 * (select subCol from subTable where subTable.subQureyCol |*symbolType*|
	 * keyword); symbolType 当前仅取可">,<,=,like"，参见SymbolType 枚举类型。
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