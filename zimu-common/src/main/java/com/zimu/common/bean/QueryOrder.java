package com.zimu.common.bean;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class QueryOrder implements Serializable {
	private static final long serialVersionUID = -3078342809727773232L;
	private static final QueryOrder.Direction DESC = QueryOrder.Direction.desc;
	private String property;
	private QueryOrder.Direction direction = DESC;

	public enum Direction {
		asc, desc;

		public static Direction fromString(String value) {
			return valueOf(value.toLowerCase());
		}
	}

	public QueryOrder() {
	}

	public QueryOrder(String property, QueryOrder.Direction direction) {
		this.property = property;
		this.direction = direction;
	}

	public static QueryOrder asc(String property) {
		return new QueryOrder(property, QueryOrder.Direction.asc);
	}

	public static QueryOrder desc(String property) {
		return new QueryOrder(property, QueryOrder.Direction.desc);
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public QueryOrder.Direction getDirection() {
		return this.direction;
	}

	public void setDirection(QueryOrder.Direction direction) {
		this.direction = direction;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (this == obj)
			return true;
		QueryOrder localOrder = (QueryOrder) obj;
		return new EqualsBuilder().append(getProperty(), localOrder.getProperty())
				.append(getDirection(), localOrder.getDirection()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(getProperty()).append(getDirection()).toHashCode();
	}
}