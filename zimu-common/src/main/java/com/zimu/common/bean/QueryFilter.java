package com.zimu.common.bean;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class QueryFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Operator {
		eq, ne, between, lt, le, gt, ge, like, notLike, in, isEmpty, isNotEmpty, isNull, isNotNull;

		public static Operator fromString(String value) {
			return valueOf(value.toLowerCase());
		}
	}

	private String property;
	private QueryFilter.Operator operator;
	private Object value;
	private Boolean ignoreCase = Boolean.valueOf(false);

	public QueryFilter() {
	}

	public QueryFilter(String property, QueryFilter.Operator operator, Object value) {
		this.property = property;
		this.operator = operator;
		this.value = value;
	}

	public QueryFilter(String property, QueryFilter.Operator operator, Object value, boolean ignoreCase) {
		this.property = property;
		this.operator = operator;
		this.value = value;
		this.ignoreCase = Boolean.valueOf(ignoreCase);
	}

	public static QueryFilter eq(String property, Object value) {
		return new QueryFilter(property, QueryFilter.Operator.eq, value);
	}

	public static QueryFilter eq(String property, Object value, boolean ignoreCase) {
		return new QueryFilter(property, QueryFilter.Operator.eq, value, ignoreCase);
	}

	public static QueryFilter ne(String property, Object value) {
		return new QueryFilter(property, QueryFilter.Operator.ne, value);
	}

	public static QueryFilter ne(String property, Object value, boolean ignoreCase) {
		return new QueryFilter(property, QueryFilter.Operator.ne, value, ignoreCase);
	}

	public static QueryFilter gt(String property, Object value) {
		return new QueryFilter(property, QueryFilter.Operator.gt, value);
	}

	public static QueryFilter lt(String property, Object value) {
		return new QueryFilter(property, QueryFilter.Operator.lt, value);
	}

	public static QueryFilter ge(String property, Object value) {
		return new QueryFilter(property, QueryFilter.Operator.ge, value);
	}

	public static QueryFilter le(String property, Object value) {
		return new QueryFilter(property, QueryFilter.Operator.le, value);
	}

	public static QueryFilter like(String property, Object value) {
		return new QueryFilter(property, QueryFilter.Operator.like, value);
	}

	public static QueryFilter in(String property, Object value) {
		return new QueryFilter(property, QueryFilter.Operator.in, value);
	}

	public static QueryFilter isEmpty(String property) {
		return new QueryFilter(property, QueryFilter.Operator.isEmpty, null);
	}

	public static QueryFilter isNotEmpty(String property) {
		return new QueryFilter(property, QueryFilter.Operator.isNotEmpty, null);
	}

	public static QueryFilter isNull(String property) {
		return new QueryFilter(property, QueryFilter.Operator.isNull, null);
	}

	public static QueryFilter isNotNull(String property) {
		return new QueryFilter(property, QueryFilter.Operator.isNotNull, null);
	}

	public QueryFilter ignoreCase() {
		this.ignoreCase = Boolean.valueOf(true);
		return this;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public QueryFilter.Operator getOperator() {
		return this.operator;
	}

	public void setOperator(QueryFilter.Operator operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Boolean getIgnoreCase() {
		return this.ignoreCase;
	}

	public void setIgnoreCase(Boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (this == obj)
			return true;
		QueryFilter localFilter = (QueryFilter) obj;
		return new EqualsBuilder().append(getProperty(), localFilter.getProperty())
				.append(getOperator(), localFilter.getOperator()).append(getValue(), localFilter.getValue()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(getProperty()).append(getOperator()).append(getValue()).toHashCode();
	}

}
