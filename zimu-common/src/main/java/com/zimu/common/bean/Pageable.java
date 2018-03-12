package com.zimu.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.zimu.common.bean.QueryFilter.Operator;
import com.zimu.common.bean.QueryOrder.Direction;

public class Pageable implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_PAGE_SIZE = 20;
	private static final int MAX_PAGE_SIZE = 1000;// 每页最大记录数限制
	protected int pageNum = 1;
	protected int pageSize = DEFAULT_PAGE_SIZE;
	// 查询条件
	private String property;
	private String keyword;
	private Operator symbol;
	private List<QueryOrder> sortClauses = new ArrayList<QueryOrder>();
	private String startDate = null;
	private String endDate = null;
	// 排序字段
	private String orderBy;
	private QueryOrder.Direction orderType;
	private List<QueryFilter> filters = new ArrayList<QueryFilter>();
	private Map<String, Object> queryMap = new HashMap<String, Object>();

	public Pageable() {
	}

	public Pageable(Integer pageNum, Integer pageSize) {
		if (pageNum != null && pageNum.intValue() >= 1) {
			this.pageNum = pageNum.intValue();
		}

		if ((pageSize != null) && (pageSize.intValue() >= 1) && (pageSize.intValue() <= MAX_PAGE_SIZE)) {
			this.pageSize = pageSize.intValue();
		}
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		if (pageNum < 1)
			pageNum = 1;
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if ((pageSize < 1) || (pageSize > MAX_PAGE_SIZE))
			pageSize = DEFAULT_PAGE_SIZE;
		this.pageSize = pageSize;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String searchProperty) {
		this.property = searchProperty;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String searchValue) {
		this.keyword = searchValue;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderProperty) {
		this.orderBy = orderProperty;
	}

	public QueryOrder.Direction getOrderType() {
		return this.orderType;
	}

	public void setOrderType(QueryOrder.Direction orderDirection) {
		this.orderType = orderDirection;
	}

	public void addFilter(String property, QueryFilter.Operator operator, Object value) {
		if (filters == null) {
			filters = new ArrayList<QueryFilter>();
		}
		this.filters.add(new QueryFilter(property, operator, value));
	}

	public void addFilter(String property, Object value) {
		if (filters == null) {
			filters = new ArrayList<QueryFilter>();
		}
		this.filters.add(new QueryFilter(property, Operator.eq, value));
	}

	public List<QueryFilter> getFilters() {
		if (filters == null) {
			filters = new ArrayList<QueryFilter>();
		}
		if (queryMap != null && !queryMap.isEmpty()) {
			Set<String> keys = queryMap.keySet();
			for (String key : keys) {
				Object value = queryMap.get(key);
				if (value != null && StringUtils.isNotEmpty(key)) {
					QueryFilter term = new QueryFilter();
					term.setProperty(key);
					term.setValue(value);
					term.setOperator(QueryFilter.Operator.eq);
					filters.add(term);
				}
			}
			queryMap = null;
		}
		return this.filters;
	}

	public void setFilters(List<QueryFilter> filters) {
		this.filters = filters;
	}

	public List<QueryOrder> getSortClauses() {
		if (sortClauses == null) {
			sortClauses = new ArrayList<QueryOrder>();
		}
		return this.sortClauses;
	}

	public void setSortClauses(List<QueryOrder> orders) {
		this.sortClauses = orders;
	}

	public void addOrder(String property, Direction direction) {
		if (sortClauses == null) {
			sortClauses = new ArrayList<QueryOrder>();
		}
		this.sortClauses.add(new QueryOrder(property, direction));
	}

	public Operator getSymbol() {
		return symbol;
	}

	public void setSymbol(Operator symbol) {
		this.symbol = symbol;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (this == obj)
			return true;
		Pageable localPageable = (Pageable) obj;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getPageNum(), localPageable.getPageNum());
		builder.append(getPageSize(), localPageable.getPageSize());
		builder.append(getProperty(), localPageable.getProperty());
		builder.append(getKeyword(), localPageable.getKeyword());
		builder.append(getOrderBy(), localPageable.getOrderBy());
		builder.append(getOrderType(), localPageable.getOrderType());
		builder.append(getFilters(), localPageable.getFilters());
		builder.append(getSortClauses(), localPageable.getSortClauses());
		return builder.isEquals();
	}

	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder(17, 37);
		builder.append(getPageNum());
		builder.append(getPageSize());
		builder.append(getProperty());
		builder.append(getKeyword());
		builder.append(getOrderBy());
		builder.append(getOrderType());
		builder.append(getFilters());
		builder.append(getSortClauses());
		return builder.toHashCode();
	}

	public Map<String, Object> getQueryMap() {
		return queryMap;
	}

	public void setQueryMap(Map<String, Object> queryMap) {
		this.queryMap = queryMap;
	}
}
