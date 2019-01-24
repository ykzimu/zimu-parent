package com.zimu.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DevDatabaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DevDatabaseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameIsNull() {
            addCriterion("database_name is null");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameIsNotNull() {
            addCriterion("database_name is not null");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameEqualTo(String value) {
            addCriterion("database_name =", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotEqualTo(String value) {
            addCriterion("database_name <>", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameGreaterThan(String value) {
            addCriterion("database_name >", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("database_name >=", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameLessThan(String value) {
            addCriterion("database_name <", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameLessThanOrEqualTo(String value) {
            addCriterion("database_name <=", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameLike(String value) {
            addCriterion("database_name like", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotLike(String value) {
            addCriterion("database_name not like", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameIn(List<String> values) {
            addCriterion("database_name in", values, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotIn(List<String> values) {
            addCriterion("database_name not in", values, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameBetween(String value1, String value2) {
            addCriterion("database_name between", value1, value2, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotBetween(String value1, String value2) {
            addCriterion("database_name not between", value1, value2, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseDescIsNull() {
            addCriterion("database_desc is null");
            return (Criteria) this;
        }

        public Criteria andDatabaseDescIsNotNull() {
            addCriterion("database_desc is not null");
            return (Criteria) this;
        }

        public Criteria andDatabaseDescEqualTo(String value) {
            addCriterion("database_desc =", value, "databaseDesc");
            return (Criteria) this;
        }

        public Criteria andDatabaseDescNotEqualTo(String value) {
            addCriterion("database_desc <>", value, "databaseDesc");
            return (Criteria) this;
        }

        public Criteria andDatabaseDescGreaterThan(String value) {
            addCriterion("database_desc >", value, "databaseDesc");
            return (Criteria) this;
        }

        public Criteria andDatabaseDescGreaterThanOrEqualTo(String value) {
            addCriterion("database_desc >=", value, "databaseDesc");
            return (Criteria) this;
        }

        public Criteria andDatabaseDescLessThan(String value) {
            addCriterion("database_desc <", value, "databaseDesc");
            return (Criteria) this;
        }

        public Criteria andDatabaseDescLessThanOrEqualTo(String value) {
            addCriterion("database_desc <=", value, "databaseDesc");
            return (Criteria) this;
        }

        public Criteria andDatabaseDescLike(String value) {
            addCriterion("database_desc like", value, "databaseDesc");
            return (Criteria) this;
        }

        public Criteria andDatabaseDescNotLike(String value) {
            addCriterion("database_desc not like", value, "databaseDesc");
            return (Criteria) this;
        }

        public Criteria andDatabaseDescIn(List<String> values) {
            addCriterion("database_desc in", values, "databaseDesc");
            return (Criteria) this;
        }

        public Criteria andDatabaseDescNotIn(List<String> values) {
            addCriterion("database_desc not in", values, "databaseDesc");
            return (Criteria) this;
        }

        public Criteria andDatabaseDescBetween(String value1, String value2) {
            addCriterion("database_desc between", value1, value2, "databaseDesc");
            return (Criteria) this;
        }

        public Criteria andDatabaseDescNotBetween(String value1, String value2) {
            addCriterion("database_desc not between", value1, value2, "databaseDesc");
            return (Criteria) this;
        }

        public Criteria andDatabaseUsernameIsNull() {
            addCriterion("database_username is null");
            return (Criteria) this;
        }

        public Criteria andDatabaseUsernameIsNotNull() {
            addCriterion("database_username is not null");
            return (Criteria) this;
        }

        public Criteria andDatabaseUsernameEqualTo(String value) {
            addCriterion("database_username =", value, "databaseUsername");
            return (Criteria) this;
        }

        public Criteria andDatabaseUsernameNotEqualTo(String value) {
            addCriterion("database_username <>", value, "databaseUsername");
            return (Criteria) this;
        }

        public Criteria andDatabaseUsernameGreaterThan(String value) {
            addCriterion("database_username >", value, "databaseUsername");
            return (Criteria) this;
        }

        public Criteria andDatabaseUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("database_username >=", value, "databaseUsername");
            return (Criteria) this;
        }

        public Criteria andDatabaseUsernameLessThan(String value) {
            addCriterion("database_username <", value, "databaseUsername");
            return (Criteria) this;
        }

        public Criteria andDatabaseUsernameLessThanOrEqualTo(String value) {
            addCriterion("database_username <=", value, "databaseUsername");
            return (Criteria) this;
        }

        public Criteria andDatabaseUsernameLike(String value) {
            addCriterion("database_username like", value, "databaseUsername");
            return (Criteria) this;
        }

        public Criteria andDatabaseUsernameNotLike(String value) {
            addCriterion("database_username not like", value, "databaseUsername");
            return (Criteria) this;
        }

        public Criteria andDatabaseUsernameIn(List<String> values) {
            addCriterion("database_username in", values, "databaseUsername");
            return (Criteria) this;
        }

        public Criteria andDatabaseUsernameNotIn(List<String> values) {
            addCriterion("database_username not in", values, "databaseUsername");
            return (Criteria) this;
        }

        public Criteria andDatabaseUsernameBetween(String value1, String value2) {
            addCriterion("database_username between", value1, value2, "databaseUsername");
            return (Criteria) this;
        }

        public Criteria andDatabaseUsernameNotBetween(String value1, String value2) {
            addCriterion("database_username not between", value1, value2, "databaseUsername");
            return (Criteria) this;
        }

        public Criteria andDatabasePasswordIsNull() {
            addCriterion("database_password is null");
            return (Criteria) this;
        }

        public Criteria andDatabasePasswordIsNotNull() {
            addCriterion("database_password is not null");
            return (Criteria) this;
        }

        public Criteria andDatabasePasswordEqualTo(String value) {
            addCriterion("database_password =", value, "databasePassword");
            return (Criteria) this;
        }

        public Criteria andDatabasePasswordNotEqualTo(String value) {
            addCriterion("database_password <>", value, "databasePassword");
            return (Criteria) this;
        }

        public Criteria andDatabasePasswordGreaterThan(String value) {
            addCriterion("database_password >", value, "databasePassword");
            return (Criteria) this;
        }

        public Criteria andDatabasePasswordGreaterThanOrEqualTo(String value) {
            addCriterion("database_password >=", value, "databasePassword");
            return (Criteria) this;
        }

        public Criteria andDatabasePasswordLessThan(String value) {
            addCriterion("database_password <", value, "databasePassword");
            return (Criteria) this;
        }

        public Criteria andDatabasePasswordLessThanOrEqualTo(String value) {
            addCriterion("database_password <=", value, "databasePassword");
            return (Criteria) this;
        }

        public Criteria andDatabasePasswordLike(String value) {
            addCriterion("database_password like", value, "databasePassword");
            return (Criteria) this;
        }

        public Criteria andDatabasePasswordNotLike(String value) {
            addCriterion("database_password not like", value, "databasePassword");
            return (Criteria) this;
        }

        public Criteria andDatabasePasswordIn(List<String> values) {
            addCriterion("database_password in", values, "databasePassword");
            return (Criteria) this;
        }

        public Criteria andDatabasePasswordNotIn(List<String> values) {
            addCriterion("database_password not in", values, "databasePassword");
            return (Criteria) this;
        }

        public Criteria andDatabasePasswordBetween(String value1, String value2) {
            addCriterion("database_password between", value1, value2, "databasePassword");
            return (Criteria) this;
        }

        public Criteria andDatabasePasswordNotBetween(String value1, String value2) {
            addCriterion("database_password not between", value1, value2, "databasePassword");
            return (Criteria) this;
        }

        public Criteria andDatabaseDriverClassNameIsNull() {
            addCriterion("database_driver_class_name is null");
            return (Criteria) this;
        }

        public Criteria andDatabaseDriverClassNameIsNotNull() {
            addCriterion("database_driver_class_name is not null");
            return (Criteria) this;
        }

        public Criteria andDatabaseDriverClassNameEqualTo(String value) {
            addCriterion("database_driver_class_name =", value, "databaseDriverClassName");
            return (Criteria) this;
        }

        public Criteria andDatabaseDriverClassNameNotEqualTo(String value) {
            addCriterion("database_driver_class_name <>", value, "databaseDriverClassName");
            return (Criteria) this;
        }

        public Criteria andDatabaseDriverClassNameGreaterThan(String value) {
            addCriterion("database_driver_class_name >", value, "databaseDriverClassName");
            return (Criteria) this;
        }

        public Criteria andDatabaseDriverClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("database_driver_class_name >=", value, "databaseDriverClassName");
            return (Criteria) this;
        }

        public Criteria andDatabaseDriverClassNameLessThan(String value) {
            addCriterion("database_driver_class_name <", value, "databaseDriverClassName");
            return (Criteria) this;
        }

        public Criteria andDatabaseDriverClassNameLessThanOrEqualTo(String value) {
            addCriterion("database_driver_class_name <=", value, "databaseDriverClassName");
            return (Criteria) this;
        }

        public Criteria andDatabaseDriverClassNameLike(String value) {
            addCriterion("database_driver_class_name like", value, "databaseDriverClassName");
            return (Criteria) this;
        }

        public Criteria andDatabaseDriverClassNameNotLike(String value) {
            addCriterion("database_driver_class_name not like", value, "databaseDriverClassName");
            return (Criteria) this;
        }

        public Criteria andDatabaseDriverClassNameIn(List<String> values) {
            addCriterion("database_driver_class_name in", values, "databaseDriverClassName");
            return (Criteria) this;
        }

        public Criteria andDatabaseDriverClassNameNotIn(List<String> values) {
            addCriterion("database_driver_class_name not in", values, "databaseDriverClassName");
            return (Criteria) this;
        }

        public Criteria andDatabaseDriverClassNameBetween(String value1, String value2) {
            addCriterion("database_driver_class_name between", value1, value2, "databaseDriverClassName");
            return (Criteria) this;
        }

        public Criteria andDatabaseDriverClassNameNotBetween(String value1, String value2) {
            addCriterion("database_driver_class_name not between", value1, value2, "databaseDriverClassName");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(Integer value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(Integer value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(Integer value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(Integer value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(Integer value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<Integer> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<Integer> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(Integer value1, Integer value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}