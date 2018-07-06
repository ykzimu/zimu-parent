package com.zimu.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserGithubEntityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserGithubEntityExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andGithubUserIdIsNull() {
            addCriterion("github_user_id is null");
            return (Criteria) this;
        }

        public Criteria andGithubUserIdIsNotNull() {
            addCriterion("github_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andGithubUserIdEqualTo(Long value) {
            addCriterion("github_user_id =", value, "githubUserId");
            return (Criteria) this;
        }

        public Criteria andGithubUserIdNotEqualTo(Long value) {
            addCriterion("github_user_id <>", value, "githubUserId");
            return (Criteria) this;
        }

        public Criteria andGithubUserIdGreaterThan(Long value) {
            addCriterion("github_user_id >", value, "githubUserId");
            return (Criteria) this;
        }

        public Criteria andGithubUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("github_user_id >=", value, "githubUserId");
            return (Criteria) this;
        }

        public Criteria andGithubUserIdLessThan(Long value) {
            addCriterion("github_user_id <", value, "githubUserId");
            return (Criteria) this;
        }

        public Criteria andGithubUserIdLessThanOrEqualTo(Long value) {
            addCriterion("github_user_id <=", value, "githubUserId");
            return (Criteria) this;
        }

        public Criteria andGithubUserIdIn(List<Long> values) {
            addCriterion("github_user_id in", values, "githubUserId");
            return (Criteria) this;
        }

        public Criteria andGithubUserIdNotIn(List<Long> values) {
            addCriterion("github_user_id not in", values, "githubUserId");
            return (Criteria) this;
        }

        public Criteria andGithubUserIdBetween(Long value1, Long value2) {
            addCriterion("github_user_id between", value1, value2, "githubUserId");
            return (Criteria) this;
        }

        public Criteria andGithubUserIdNotBetween(Long value1, Long value2) {
            addCriterion("github_user_id not between", value1, value2, "githubUserId");
            return (Criteria) this;
        }

        public Criteria andLoginIsNull() {
            addCriterion("login is null");
            return (Criteria) this;
        }

        public Criteria andLoginIsNotNull() {
            addCriterion("login is not null");
            return (Criteria) this;
        }

        public Criteria andLoginEqualTo(String value) {
            addCriterion("login =", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginNotEqualTo(String value) {
            addCriterion("login <>", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginGreaterThan(String value) {
            addCriterion("login >", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginGreaterThanOrEqualTo(String value) {
            addCriterion("login >=", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginLessThan(String value) {
            addCriterion("login <", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginLessThanOrEqualTo(String value) {
            addCriterion("login <=", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginLike(String value) {
            addCriterion("login like", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginNotLike(String value) {
            addCriterion("login not like", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginIn(List<String> values) {
            addCriterion("login in", values, "login");
            return (Criteria) this;
        }

        public Criteria andLoginNotIn(List<String> values) {
            addCriterion("login not in", values, "login");
            return (Criteria) this;
        }

        public Criteria andLoginBetween(String value1, String value2) {
            addCriterion("login between", value1, value2, "login");
            return (Criteria) this;
        }

        public Criteria andLoginNotBetween(String value1, String value2) {
            addCriterion("login not between", value1, value2, "login");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlIsNull() {
            addCriterion("avatar_url is null");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlIsNotNull() {
            addCriterion("avatar_url is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlEqualTo(String value) {
            addCriterion("avatar_url =", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotEqualTo(String value) {
            addCriterion("avatar_url <>", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlGreaterThan(String value) {
            addCriterion("avatar_url >", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlGreaterThanOrEqualTo(String value) {
            addCriterion("avatar_url >=", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlLessThan(String value) {
            addCriterion("avatar_url <", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlLessThanOrEqualTo(String value) {
            addCriterion("avatar_url <=", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlLike(String value) {
            addCriterion("avatar_url like", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotLike(String value) {
            addCriterion("avatar_url not like", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlIn(List<String> values) {
            addCriterion("avatar_url in", values, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotIn(List<String> values) {
            addCriterion("avatar_url not in", values, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlBetween(String value1, String value2) {
            addCriterion("avatar_url between", value1, value2, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotBetween(String value1, String value2) {
            addCriterion("avatar_url not between", value1, value2, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andGravatarIdIsNull() {
            addCriterion("gravatar_id is null");
            return (Criteria) this;
        }

        public Criteria andGravatarIdIsNotNull() {
            addCriterion("gravatar_id is not null");
            return (Criteria) this;
        }

        public Criteria andGravatarIdEqualTo(String value) {
            addCriterion("gravatar_id =", value, "gravatarId");
            return (Criteria) this;
        }

        public Criteria andGravatarIdNotEqualTo(String value) {
            addCriterion("gravatar_id <>", value, "gravatarId");
            return (Criteria) this;
        }

        public Criteria andGravatarIdGreaterThan(String value) {
            addCriterion("gravatar_id >", value, "gravatarId");
            return (Criteria) this;
        }

        public Criteria andGravatarIdGreaterThanOrEqualTo(String value) {
            addCriterion("gravatar_id >=", value, "gravatarId");
            return (Criteria) this;
        }

        public Criteria andGravatarIdLessThan(String value) {
            addCriterion("gravatar_id <", value, "gravatarId");
            return (Criteria) this;
        }

        public Criteria andGravatarIdLessThanOrEqualTo(String value) {
            addCriterion("gravatar_id <=", value, "gravatarId");
            return (Criteria) this;
        }

        public Criteria andGravatarIdLike(String value) {
            addCriterion("gravatar_id like", value, "gravatarId");
            return (Criteria) this;
        }

        public Criteria andGravatarIdNotLike(String value) {
            addCriterion("gravatar_id not like", value, "gravatarId");
            return (Criteria) this;
        }

        public Criteria andGravatarIdIn(List<String> values) {
            addCriterion("gravatar_id in", values, "gravatarId");
            return (Criteria) this;
        }

        public Criteria andGravatarIdNotIn(List<String> values) {
            addCriterion("gravatar_id not in", values, "gravatarId");
            return (Criteria) this;
        }

        public Criteria andGravatarIdBetween(String value1, String value2) {
            addCriterion("gravatar_id between", value1, value2, "gravatarId");
            return (Criteria) this;
        }

        public Criteria andGravatarIdNotBetween(String value1, String value2) {
            addCriterion("gravatar_id not between", value1, value2, "gravatarId");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andHtmlUrlIsNull() {
            addCriterion("html_url is null");
            return (Criteria) this;
        }

        public Criteria andHtmlUrlIsNotNull() {
            addCriterion("html_url is not null");
            return (Criteria) this;
        }

        public Criteria andHtmlUrlEqualTo(String value) {
            addCriterion("html_url =", value, "htmlUrl");
            return (Criteria) this;
        }

        public Criteria andHtmlUrlNotEqualTo(String value) {
            addCriterion("html_url <>", value, "htmlUrl");
            return (Criteria) this;
        }

        public Criteria andHtmlUrlGreaterThan(String value) {
            addCriterion("html_url >", value, "htmlUrl");
            return (Criteria) this;
        }

        public Criteria andHtmlUrlGreaterThanOrEqualTo(String value) {
            addCriterion("html_url >=", value, "htmlUrl");
            return (Criteria) this;
        }

        public Criteria andHtmlUrlLessThan(String value) {
            addCriterion("html_url <", value, "htmlUrl");
            return (Criteria) this;
        }

        public Criteria andHtmlUrlLessThanOrEqualTo(String value) {
            addCriterion("html_url <=", value, "htmlUrl");
            return (Criteria) this;
        }

        public Criteria andHtmlUrlLike(String value) {
            addCriterion("html_url like", value, "htmlUrl");
            return (Criteria) this;
        }

        public Criteria andHtmlUrlNotLike(String value) {
            addCriterion("html_url not like", value, "htmlUrl");
            return (Criteria) this;
        }

        public Criteria andHtmlUrlIn(List<String> values) {
            addCriterion("html_url in", values, "htmlUrl");
            return (Criteria) this;
        }

        public Criteria andHtmlUrlNotIn(List<String> values) {
            addCriterion("html_url not in", values, "htmlUrl");
            return (Criteria) this;
        }

        public Criteria andHtmlUrlBetween(String value1, String value2) {
            addCriterion("html_url between", value1, value2, "htmlUrl");
            return (Criteria) this;
        }

        public Criteria andHtmlUrlNotBetween(String value1, String value2) {
            addCriterion("html_url not between", value1, value2, "htmlUrl");
            return (Criteria) this;
        }

        public Criteria andFollowersUrlIsNull() {
            addCriterion("followers_url is null");
            return (Criteria) this;
        }

        public Criteria andFollowersUrlIsNotNull() {
            addCriterion("followers_url is not null");
            return (Criteria) this;
        }

        public Criteria andFollowersUrlEqualTo(String value) {
            addCriterion("followers_url =", value, "followersUrl");
            return (Criteria) this;
        }

        public Criteria andFollowersUrlNotEqualTo(String value) {
            addCriterion("followers_url <>", value, "followersUrl");
            return (Criteria) this;
        }

        public Criteria andFollowersUrlGreaterThan(String value) {
            addCriterion("followers_url >", value, "followersUrl");
            return (Criteria) this;
        }

        public Criteria andFollowersUrlGreaterThanOrEqualTo(String value) {
            addCriterion("followers_url >=", value, "followersUrl");
            return (Criteria) this;
        }

        public Criteria andFollowersUrlLessThan(String value) {
            addCriterion("followers_url <", value, "followersUrl");
            return (Criteria) this;
        }

        public Criteria andFollowersUrlLessThanOrEqualTo(String value) {
            addCriterion("followers_url <=", value, "followersUrl");
            return (Criteria) this;
        }

        public Criteria andFollowersUrlLike(String value) {
            addCriterion("followers_url like", value, "followersUrl");
            return (Criteria) this;
        }

        public Criteria andFollowersUrlNotLike(String value) {
            addCriterion("followers_url not like", value, "followersUrl");
            return (Criteria) this;
        }

        public Criteria andFollowersUrlIn(List<String> values) {
            addCriterion("followers_url in", values, "followersUrl");
            return (Criteria) this;
        }

        public Criteria andFollowersUrlNotIn(List<String> values) {
            addCriterion("followers_url not in", values, "followersUrl");
            return (Criteria) this;
        }

        public Criteria andFollowersUrlBetween(String value1, String value2) {
            addCriterion("followers_url between", value1, value2, "followersUrl");
            return (Criteria) this;
        }

        public Criteria andFollowersUrlNotBetween(String value1, String value2) {
            addCriterion("followers_url not between", value1, value2, "followersUrl");
            return (Criteria) this;
        }

        public Criteria andFollowingUrlIsNull() {
            addCriterion("following_url is null");
            return (Criteria) this;
        }

        public Criteria andFollowingUrlIsNotNull() {
            addCriterion("following_url is not null");
            return (Criteria) this;
        }

        public Criteria andFollowingUrlEqualTo(String value) {
            addCriterion("following_url =", value, "followingUrl");
            return (Criteria) this;
        }

        public Criteria andFollowingUrlNotEqualTo(String value) {
            addCriterion("following_url <>", value, "followingUrl");
            return (Criteria) this;
        }

        public Criteria andFollowingUrlGreaterThan(String value) {
            addCriterion("following_url >", value, "followingUrl");
            return (Criteria) this;
        }

        public Criteria andFollowingUrlGreaterThanOrEqualTo(String value) {
            addCriterion("following_url >=", value, "followingUrl");
            return (Criteria) this;
        }

        public Criteria andFollowingUrlLessThan(String value) {
            addCriterion("following_url <", value, "followingUrl");
            return (Criteria) this;
        }

        public Criteria andFollowingUrlLessThanOrEqualTo(String value) {
            addCriterion("following_url <=", value, "followingUrl");
            return (Criteria) this;
        }

        public Criteria andFollowingUrlLike(String value) {
            addCriterion("following_url like", value, "followingUrl");
            return (Criteria) this;
        }

        public Criteria andFollowingUrlNotLike(String value) {
            addCriterion("following_url not like", value, "followingUrl");
            return (Criteria) this;
        }

        public Criteria andFollowingUrlIn(List<String> values) {
            addCriterion("following_url in", values, "followingUrl");
            return (Criteria) this;
        }

        public Criteria andFollowingUrlNotIn(List<String> values) {
            addCriterion("following_url not in", values, "followingUrl");
            return (Criteria) this;
        }

        public Criteria andFollowingUrlBetween(String value1, String value2) {
            addCriterion("following_url between", value1, value2, "followingUrl");
            return (Criteria) this;
        }

        public Criteria andFollowingUrlNotBetween(String value1, String value2) {
            addCriterion("following_url not between", value1, value2, "followingUrl");
            return (Criteria) this;
        }

        public Criteria andGistsUrlIsNull() {
            addCriterion("gists_url is null");
            return (Criteria) this;
        }

        public Criteria andGistsUrlIsNotNull() {
            addCriterion("gists_url is not null");
            return (Criteria) this;
        }

        public Criteria andGistsUrlEqualTo(String value) {
            addCriterion("gists_url =", value, "gistsUrl");
            return (Criteria) this;
        }

        public Criteria andGistsUrlNotEqualTo(String value) {
            addCriterion("gists_url <>", value, "gistsUrl");
            return (Criteria) this;
        }

        public Criteria andGistsUrlGreaterThan(String value) {
            addCriterion("gists_url >", value, "gistsUrl");
            return (Criteria) this;
        }

        public Criteria andGistsUrlGreaterThanOrEqualTo(String value) {
            addCriterion("gists_url >=", value, "gistsUrl");
            return (Criteria) this;
        }

        public Criteria andGistsUrlLessThan(String value) {
            addCriterion("gists_url <", value, "gistsUrl");
            return (Criteria) this;
        }

        public Criteria andGistsUrlLessThanOrEqualTo(String value) {
            addCriterion("gists_url <=", value, "gistsUrl");
            return (Criteria) this;
        }

        public Criteria andGistsUrlLike(String value) {
            addCriterion("gists_url like", value, "gistsUrl");
            return (Criteria) this;
        }

        public Criteria andGistsUrlNotLike(String value) {
            addCriterion("gists_url not like", value, "gistsUrl");
            return (Criteria) this;
        }

        public Criteria andGistsUrlIn(List<String> values) {
            addCriterion("gists_url in", values, "gistsUrl");
            return (Criteria) this;
        }

        public Criteria andGistsUrlNotIn(List<String> values) {
            addCriterion("gists_url not in", values, "gistsUrl");
            return (Criteria) this;
        }

        public Criteria andGistsUrlBetween(String value1, String value2) {
            addCriterion("gists_url between", value1, value2, "gistsUrl");
            return (Criteria) this;
        }

        public Criteria andGistsUrlNotBetween(String value1, String value2) {
            addCriterion("gists_url not between", value1, value2, "gistsUrl");
            return (Criteria) this;
        }

        public Criteria andStarredUrlIsNull() {
            addCriterion("starred_url is null");
            return (Criteria) this;
        }

        public Criteria andStarredUrlIsNotNull() {
            addCriterion("starred_url is not null");
            return (Criteria) this;
        }

        public Criteria andStarredUrlEqualTo(String value) {
            addCriterion("starred_url =", value, "starredUrl");
            return (Criteria) this;
        }

        public Criteria andStarredUrlNotEqualTo(String value) {
            addCriterion("starred_url <>", value, "starredUrl");
            return (Criteria) this;
        }

        public Criteria andStarredUrlGreaterThan(String value) {
            addCriterion("starred_url >", value, "starredUrl");
            return (Criteria) this;
        }

        public Criteria andStarredUrlGreaterThanOrEqualTo(String value) {
            addCriterion("starred_url >=", value, "starredUrl");
            return (Criteria) this;
        }

        public Criteria andStarredUrlLessThan(String value) {
            addCriterion("starred_url <", value, "starredUrl");
            return (Criteria) this;
        }

        public Criteria andStarredUrlLessThanOrEqualTo(String value) {
            addCriterion("starred_url <=", value, "starredUrl");
            return (Criteria) this;
        }

        public Criteria andStarredUrlLike(String value) {
            addCriterion("starred_url like", value, "starredUrl");
            return (Criteria) this;
        }

        public Criteria andStarredUrlNotLike(String value) {
            addCriterion("starred_url not like", value, "starredUrl");
            return (Criteria) this;
        }

        public Criteria andStarredUrlIn(List<String> values) {
            addCriterion("starred_url in", values, "starredUrl");
            return (Criteria) this;
        }

        public Criteria andStarredUrlNotIn(List<String> values) {
            addCriterion("starred_url not in", values, "starredUrl");
            return (Criteria) this;
        }

        public Criteria andStarredUrlBetween(String value1, String value2) {
            addCriterion("starred_url between", value1, value2, "starredUrl");
            return (Criteria) this;
        }

        public Criteria andStarredUrlNotBetween(String value1, String value2) {
            addCriterion("starred_url not between", value1, value2, "starredUrl");
            return (Criteria) this;
        }

        public Criteria andSubscriptionsUrlIsNull() {
            addCriterion("subscriptions_url is null");
            return (Criteria) this;
        }

        public Criteria andSubscriptionsUrlIsNotNull() {
            addCriterion("subscriptions_url is not null");
            return (Criteria) this;
        }

        public Criteria andSubscriptionsUrlEqualTo(String value) {
            addCriterion("subscriptions_url =", value, "subscriptionsUrl");
            return (Criteria) this;
        }

        public Criteria andSubscriptionsUrlNotEqualTo(String value) {
            addCriterion("subscriptions_url <>", value, "subscriptionsUrl");
            return (Criteria) this;
        }

        public Criteria andSubscriptionsUrlGreaterThan(String value) {
            addCriterion("subscriptions_url >", value, "subscriptionsUrl");
            return (Criteria) this;
        }

        public Criteria andSubscriptionsUrlGreaterThanOrEqualTo(String value) {
            addCriterion("subscriptions_url >=", value, "subscriptionsUrl");
            return (Criteria) this;
        }

        public Criteria andSubscriptionsUrlLessThan(String value) {
            addCriterion("subscriptions_url <", value, "subscriptionsUrl");
            return (Criteria) this;
        }

        public Criteria andSubscriptionsUrlLessThanOrEqualTo(String value) {
            addCriterion("subscriptions_url <=", value, "subscriptionsUrl");
            return (Criteria) this;
        }

        public Criteria andSubscriptionsUrlLike(String value) {
            addCriterion("subscriptions_url like", value, "subscriptionsUrl");
            return (Criteria) this;
        }

        public Criteria andSubscriptionsUrlNotLike(String value) {
            addCriterion("subscriptions_url not like", value, "subscriptionsUrl");
            return (Criteria) this;
        }

        public Criteria andSubscriptionsUrlIn(List<String> values) {
            addCriterion("subscriptions_url in", values, "subscriptionsUrl");
            return (Criteria) this;
        }

        public Criteria andSubscriptionsUrlNotIn(List<String> values) {
            addCriterion("subscriptions_url not in", values, "subscriptionsUrl");
            return (Criteria) this;
        }

        public Criteria andSubscriptionsUrlBetween(String value1, String value2) {
            addCriterion("subscriptions_url between", value1, value2, "subscriptionsUrl");
            return (Criteria) this;
        }

        public Criteria andSubscriptionsUrlNotBetween(String value1, String value2) {
            addCriterion("subscriptions_url not between", value1, value2, "subscriptionsUrl");
            return (Criteria) this;
        }

        public Criteria andOrganizationsUrlIsNull() {
            addCriterion("organizations_url is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationsUrlIsNotNull() {
            addCriterion("organizations_url is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationsUrlEqualTo(String value) {
            addCriterion("organizations_url =", value, "organizationsUrl");
            return (Criteria) this;
        }

        public Criteria andOrganizationsUrlNotEqualTo(String value) {
            addCriterion("organizations_url <>", value, "organizationsUrl");
            return (Criteria) this;
        }

        public Criteria andOrganizationsUrlGreaterThan(String value) {
            addCriterion("organizations_url >", value, "organizationsUrl");
            return (Criteria) this;
        }

        public Criteria andOrganizationsUrlGreaterThanOrEqualTo(String value) {
            addCriterion("organizations_url >=", value, "organizationsUrl");
            return (Criteria) this;
        }

        public Criteria andOrganizationsUrlLessThan(String value) {
            addCriterion("organizations_url <", value, "organizationsUrl");
            return (Criteria) this;
        }

        public Criteria andOrganizationsUrlLessThanOrEqualTo(String value) {
            addCriterion("organizations_url <=", value, "organizationsUrl");
            return (Criteria) this;
        }

        public Criteria andOrganizationsUrlLike(String value) {
            addCriterion("organizations_url like", value, "organizationsUrl");
            return (Criteria) this;
        }

        public Criteria andOrganizationsUrlNotLike(String value) {
            addCriterion("organizations_url not like", value, "organizationsUrl");
            return (Criteria) this;
        }

        public Criteria andOrganizationsUrlIn(List<String> values) {
            addCriterion("organizations_url in", values, "organizationsUrl");
            return (Criteria) this;
        }

        public Criteria andOrganizationsUrlNotIn(List<String> values) {
            addCriterion("organizations_url not in", values, "organizationsUrl");
            return (Criteria) this;
        }

        public Criteria andOrganizationsUrlBetween(String value1, String value2) {
            addCriterion("organizations_url between", value1, value2, "organizationsUrl");
            return (Criteria) this;
        }

        public Criteria andOrganizationsUrlNotBetween(String value1, String value2) {
            addCriterion("organizations_url not between", value1, value2, "organizationsUrl");
            return (Criteria) this;
        }

        public Criteria andReposUrlIsNull() {
            addCriterion("repos_url is null");
            return (Criteria) this;
        }

        public Criteria andReposUrlIsNotNull() {
            addCriterion("repos_url is not null");
            return (Criteria) this;
        }

        public Criteria andReposUrlEqualTo(String value) {
            addCriterion("repos_url =", value, "reposUrl");
            return (Criteria) this;
        }

        public Criteria andReposUrlNotEqualTo(String value) {
            addCriterion("repos_url <>", value, "reposUrl");
            return (Criteria) this;
        }

        public Criteria andReposUrlGreaterThan(String value) {
            addCriterion("repos_url >", value, "reposUrl");
            return (Criteria) this;
        }

        public Criteria andReposUrlGreaterThanOrEqualTo(String value) {
            addCriterion("repos_url >=", value, "reposUrl");
            return (Criteria) this;
        }

        public Criteria andReposUrlLessThan(String value) {
            addCriterion("repos_url <", value, "reposUrl");
            return (Criteria) this;
        }

        public Criteria andReposUrlLessThanOrEqualTo(String value) {
            addCriterion("repos_url <=", value, "reposUrl");
            return (Criteria) this;
        }

        public Criteria andReposUrlLike(String value) {
            addCriterion("repos_url like", value, "reposUrl");
            return (Criteria) this;
        }

        public Criteria andReposUrlNotLike(String value) {
            addCriterion("repos_url not like", value, "reposUrl");
            return (Criteria) this;
        }

        public Criteria andReposUrlIn(List<String> values) {
            addCriterion("repos_url in", values, "reposUrl");
            return (Criteria) this;
        }

        public Criteria andReposUrlNotIn(List<String> values) {
            addCriterion("repos_url not in", values, "reposUrl");
            return (Criteria) this;
        }

        public Criteria andReposUrlBetween(String value1, String value2) {
            addCriterion("repos_url between", value1, value2, "reposUrl");
            return (Criteria) this;
        }

        public Criteria andReposUrlNotBetween(String value1, String value2) {
            addCriterion("repos_url not between", value1, value2, "reposUrl");
            return (Criteria) this;
        }

        public Criteria andEventsUrlIsNull() {
            addCriterion("events_url is null");
            return (Criteria) this;
        }

        public Criteria andEventsUrlIsNotNull() {
            addCriterion("events_url is not null");
            return (Criteria) this;
        }

        public Criteria andEventsUrlEqualTo(String value) {
            addCriterion("events_url =", value, "eventsUrl");
            return (Criteria) this;
        }

        public Criteria andEventsUrlNotEqualTo(String value) {
            addCriterion("events_url <>", value, "eventsUrl");
            return (Criteria) this;
        }

        public Criteria andEventsUrlGreaterThan(String value) {
            addCriterion("events_url >", value, "eventsUrl");
            return (Criteria) this;
        }

        public Criteria andEventsUrlGreaterThanOrEqualTo(String value) {
            addCriterion("events_url >=", value, "eventsUrl");
            return (Criteria) this;
        }

        public Criteria andEventsUrlLessThan(String value) {
            addCriterion("events_url <", value, "eventsUrl");
            return (Criteria) this;
        }

        public Criteria andEventsUrlLessThanOrEqualTo(String value) {
            addCriterion("events_url <=", value, "eventsUrl");
            return (Criteria) this;
        }

        public Criteria andEventsUrlLike(String value) {
            addCriterion("events_url like", value, "eventsUrl");
            return (Criteria) this;
        }

        public Criteria andEventsUrlNotLike(String value) {
            addCriterion("events_url not like", value, "eventsUrl");
            return (Criteria) this;
        }

        public Criteria andEventsUrlIn(List<String> values) {
            addCriterion("events_url in", values, "eventsUrl");
            return (Criteria) this;
        }

        public Criteria andEventsUrlNotIn(List<String> values) {
            addCriterion("events_url not in", values, "eventsUrl");
            return (Criteria) this;
        }

        public Criteria andEventsUrlBetween(String value1, String value2) {
            addCriterion("events_url between", value1, value2, "eventsUrl");
            return (Criteria) this;
        }

        public Criteria andEventsUrlNotBetween(String value1, String value2) {
            addCriterion("events_url not between", value1, value2, "eventsUrl");
            return (Criteria) this;
        }

        public Criteria andReceivedEventsUrlIsNull() {
            addCriterion("received_events_url is null");
            return (Criteria) this;
        }

        public Criteria andReceivedEventsUrlIsNotNull() {
            addCriterion("received_events_url is not null");
            return (Criteria) this;
        }

        public Criteria andReceivedEventsUrlEqualTo(String value) {
            addCriterion("received_events_url =", value, "receivedEventsUrl");
            return (Criteria) this;
        }

        public Criteria andReceivedEventsUrlNotEqualTo(String value) {
            addCriterion("received_events_url <>", value, "receivedEventsUrl");
            return (Criteria) this;
        }

        public Criteria andReceivedEventsUrlGreaterThan(String value) {
            addCriterion("received_events_url >", value, "receivedEventsUrl");
            return (Criteria) this;
        }

        public Criteria andReceivedEventsUrlGreaterThanOrEqualTo(String value) {
            addCriterion("received_events_url >=", value, "receivedEventsUrl");
            return (Criteria) this;
        }

        public Criteria andReceivedEventsUrlLessThan(String value) {
            addCriterion("received_events_url <", value, "receivedEventsUrl");
            return (Criteria) this;
        }

        public Criteria andReceivedEventsUrlLessThanOrEqualTo(String value) {
            addCriterion("received_events_url <=", value, "receivedEventsUrl");
            return (Criteria) this;
        }

        public Criteria andReceivedEventsUrlLike(String value) {
            addCriterion("received_events_url like", value, "receivedEventsUrl");
            return (Criteria) this;
        }

        public Criteria andReceivedEventsUrlNotLike(String value) {
            addCriterion("received_events_url not like", value, "receivedEventsUrl");
            return (Criteria) this;
        }

        public Criteria andReceivedEventsUrlIn(List<String> values) {
            addCriterion("received_events_url in", values, "receivedEventsUrl");
            return (Criteria) this;
        }

        public Criteria andReceivedEventsUrlNotIn(List<String> values) {
            addCriterion("received_events_url not in", values, "receivedEventsUrl");
            return (Criteria) this;
        }

        public Criteria andReceivedEventsUrlBetween(String value1, String value2) {
            addCriterion("received_events_url between", value1, value2, "receivedEventsUrl");
            return (Criteria) this;
        }

        public Criteria andReceivedEventsUrlNotBetween(String value1, String value2) {
            addCriterion("received_events_url not between", value1, value2, "receivedEventsUrl");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andSiteAdminIsNull() {
            addCriterion("site_admin is null");
            return (Criteria) this;
        }

        public Criteria andSiteAdminIsNotNull() {
            addCriterion("site_admin is not null");
            return (Criteria) this;
        }

        public Criteria andSiteAdminEqualTo(String value) {
            addCriterion("site_admin =", value, "siteAdmin");
            return (Criteria) this;
        }

        public Criteria andSiteAdminNotEqualTo(String value) {
            addCriterion("site_admin <>", value, "siteAdmin");
            return (Criteria) this;
        }

        public Criteria andSiteAdminGreaterThan(String value) {
            addCriterion("site_admin >", value, "siteAdmin");
            return (Criteria) this;
        }

        public Criteria andSiteAdminGreaterThanOrEqualTo(String value) {
            addCriterion("site_admin >=", value, "siteAdmin");
            return (Criteria) this;
        }

        public Criteria andSiteAdminLessThan(String value) {
            addCriterion("site_admin <", value, "siteAdmin");
            return (Criteria) this;
        }

        public Criteria andSiteAdminLessThanOrEqualTo(String value) {
            addCriterion("site_admin <=", value, "siteAdmin");
            return (Criteria) this;
        }

        public Criteria andSiteAdminLike(String value) {
            addCriterion("site_admin like", value, "siteAdmin");
            return (Criteria) this;
        }

        public Criteria andSiteAdminNotLike(String value) {
            addCriterion("site_admin not like", value, "siteAdmin");
            return (Criteria) this;
        }

        public Criteria andSiteAdminIn(List<String> values) {
            addCriterion("site_admin in", values, "siteAdmin");
            return (Criteria) this;
        }

        public Criteria andSiteAdminNotIn(List<String> values) {
            addCriterion("site_admin not in", values, "siteAdmin");
            return (Criteria) this;
        }

        public Criteria andSiteAdminBetween(String value1, String value2) {
            addCriterion("site_admin between", value1, value2, "siteAdmin");
            return (Criteria) this;
        }

        public Criteria andSiteAdminNotBetween(String value1, String value2) {
            addCriterion("site_admin not between", value1, value2, "siteAdmin");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andBlogIsNull() {
            addCriterion("blog is null");
            return (Criteria) this;
        }

        public Criteria andBlogIsNotNull() {
            addCriterion("blog is not null");
            return (Criteria) this;
        }

        public Criteria andBlogEqualTo(String value) {
            addCriterion("blog =", value, "blog");
            return (Criteria) this;
        }

        public Criteria andBlogNotEqualTo(String value) {
            addCriterion("blog <>", value, "blog");
            return (Criteria) this;
        }

        public Criteria andBlogGreaterThan(String value) {
            addCriterion("blog >", value, "blog");
            return (Criteria) this;
        }

        public Criteria andBlogGreaterThanOrEqualTo(String value) {
            addCriterion("blog >=", value, "blog");
            return (Criteria) this;
        }

        public Criteria andBlogLessThan(String value) {
            addCriterion("blog <", value, "blog");
            return (Criteria) this;
        }

        public Criteria andBlogLessThanOrEqualTo(String value) {
            addCriterion("blog <=", value, "blog");
            return (Criteria) this;
        }

        public Criteria andBlogLike(String value) {
            addCriterion("blog like", value, "blog");
            return (Criteria) this;
        }

        public Criteria andBlogNotLike(String value) {
            addCriterion("blog not like", value, "blog");
            return (Criteria) this;
        }

        public Criteria andBlogIn(List<String> values) {
            addCriterion("blog in", values, "blog");
            return (Criteria) this;
        }

        public Criteria andBlogNotIn(List<String> values) {
            addCriterion("blog not in", values, "blog");
            return (Criteria) this;
        }

        public Criteria andBlogBetween(String value1, String value2) {
            addCriterion("blog between", value1, value2, "blog");
            return (Criteria) this;
        }

        public Criteria andBlogNotBetween(String value1, String value2) {
            addCriterion("blog not between", value1, value2, "blog");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andHireableIsNull() {
            addCriterion("hireable is null");
            return (Criteria) this;
        }

        public Criteria andHireableIsNotNull() {
            addCriterion("hireable is not null");
            return (Criteria) this;
        }

        public Criteria andHireableEqualTo(String value) {
            addCriterion("hireable =", value, "hireable");
            return (Criteria) this;
        }

        public Criteria andHireableNotEqualTo(String value) {
            addCriterion("hireable <>", value, "hireable");
            return (Criteria) this;
        }

        public Criteria andHireableGreaterThan(String value) {
            addCriterion("hireable >", value, "hireable");
            return (Criteria) this;
        }

        public Criteria andHireableGreaterThanOrEqualTo(String value) {
            addCriterion("hireable >=", value, "hireable");
            return (Criteria) this;
        }

        public Criteria andHireableLessThan(String value) {
            addCriterion("hireable <", value, "hireable");
            return (Criteria) this;
        }

        public Criteria andHireableLessThanOrEqualTo(String value) {
            addCriterion("hireable <=", value, "hireable");
            return (Criteria) this;
        }

        public Criteria andHireableLike(String value) {
            addCriterion("hireable like", value, "hireable");
            return (Criteria) this;
        }

        public Criteria andHireableNotLike(String value) {
            addCriterion("hireable not like", value, "hireable");
            return (Criteria) this;
        }

        public Criteria andHireableIn(List<String> values) {
            addCriterion("hireable in", values, "hireable");
            return (Criteria) this;
        }

        public Criteria andHireableNotIn(List<String> values) {
            addCriterion("hireable not in", values, "hireable");
            return (Criteria) this;
        }

        public Criteria andHireableBetween(String value1, String value2) {
            addCriterion("hireable between", value1, value2, "hireable");
            return (Criteria) this;
        }

        public Criteria andHireableNotBetween(String value1, String value2) {
            addCriterion("hireable not between", value1, value2, "hireable");
            return (Criteria) this;
        }

        public Criteria andBioIsNull() {
            addCriterion("bio is null");
            return (Criteria) this;
        }

        public Criteria andBioIsNotNull() {
            addCriterion("bio is not null");
            return (Criteria) this;
        }

        public Criteria andBioEqualTo(String value) {
            addCriterion("bio =", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioNotEqualTo(String value) {
            addCriterion("bio <>", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioGreaterThan(String value) {
            addCriterion("bio >", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioGreaterThanOrEqualTo(String value) {
            addCriterion("bio >=", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioLessThan(String value) {
            addCriterion("bio <", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioLessThanOrEqualTo(String value) {
            addCriterion("bio <=", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioLike(String value) {
            addCriterion("bio like", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioNotLike(String value) {
            addCriterion("bio not like", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioIn(List<String> values) {
            addCriterion("bio in", values, "bio");
            return (Criteria) this;
        }

        public Criteria andBioNotIn(List<String> values) {
            addCriterion("bio not in", values, "bio");
            return (Criteria) this;
        }

        public Criteria andBioBetween(String value1, String value2) {
            addCriterion("bio between", value1, value2, "bio");
            return (Criteria) this;
        }

        public Criteria andBioNotBetween(String value1, String value2) {
            addCriterion("bio not between", value1, value2, "bio");
            return (Criteria) this;
        }

        public Criteria andPublicReposIsNull() {
            addCriterion("public_repos is null");
            return (Criteria) this;
        }

        public Criteria andPublicReposIsNotNull() {
            addCriterion("public_repos is not null");
            return (Criteria) this;
        }

        public Criteria andPublicReposEqualTo(Integer value) {
            addCriterion("public_repos =", value, "publicRepos");
            return (Criteria) this;
        }

        public Criteria andPublicReposNotEqualTo(Integer value) {
            addCriterion("public_repos <>", value, "publicRepos");
            return (Criteria) this;
        }

        public Criteria andPublicReposGreaterThan(Integer value) {
            addCriterion("public_repos >", value, "publicRepos");
            return (Criteria) this;
        }

        public Criteria andPublicReposGreaterThanOrEqualTo(Integer value) {
            addCriterion("public_repos >=", value, "publicRepos");
            return (Criteria) this;
        }

        public Criteria andPublicReposLessThan(Integer value) {
            addCriterion("public_repos <", value, "publicRepos");
            return (Criteria) this;
        }

        public Criteria andPublicReposLessThanOrEqualTo(Integer value) {
            addCriterion("public_repos <=", value, "publicRepos");
            return (Criteria) this;
        }

        public Criteria andPublicReposIn(List<Integer> values) {
            addCriterion("public_repos in", values, "publicRepos");
            return (Criteria) this;
        }

        public Criteria andPublicReposNotIn(List<Integer> values) {
            addCriterion("public_repos not in", values, "publicRepos");
            return (Criteria) this;
        }

        public Criteria andPublicReposBetween(Integer value1, Integer value2) {
            addCriterion("public_repos between", value1, value2, "publicRepos");
            return (Criteria) this;
        }

        public Criteria andPublicReposNotBetween(Integer value1, Integer value2) {
            addCriterion("public_repos not between", value1, value2, "publicRepos");
            return (Criteria) this;
        }

        public Criteria andPublicGistsIsNull() {
            addCriterion("public_gists is null");
            return (Criteria) this;
        }

        public Criteria andPublicGistsIsNotNull() {
            addCriterion("public_gists is not null");
            return (Criteria) this;
        }

        public Criteria andPublicGistsEqualTo(Integer value) {
            addCriterion("public_gists =", value, "publicGists");
            return (Criteria) this;
        }

        public Criteria andPublicGistsNotEqualTo(Integer value) {
            addCriterion("public_gists <>", value, "publicGists");
            return (Criteria) this;
        }

        public Criteria andPublicGistsGreaterThan(Integer value) {
            addCriterion("public_gists >", value, "publicGists");
            return (Criteria) this;
        }

        public Criteria andPublicGistsGreaterThanOrEqualTo(Integer value) {
            addCriterion("public_gists >=", value, "publicGists");
            return (Criteria) this;
        }

        public Criteria andPublicGistsLessThan(Integer value) {
            addCriterion("public_gists <", value, "publicGists");
            return (Criteria) this;
        }

        public Criteria andPublicGistsLessThanOrEqualTo(Integer value) {
            addCriterion("public_gists <=", value, "publicGists");
            return (Criteria) this;
        }

        public Criteria andPublicGistsIn(List<Integer> values) {
            addCriterion("public_gists in", values, "publicGists");
            return (Criteria) this;
        }

        public Criteria andPublicGistsNotIn(List<Integer> values) {
            addCriterion("public_gists not in", values, "publicGists");
            return (Criteria) this;
        }

        public Criteria andPublicGistsBetween(Integer value1, Integer value2) {
            addCriterion("public_gists between", value1, value2, "publicGists");
            return (Criteria) this;
        }

        public Criteria andPublicGistsNotBetween(Integer value1, Integer value2) {
            addCriterion("public_gists not between", value1, value2, "publicGists");
            return (Criteria) this;
        }

        public Criteria andFollowersIsNull() {
            addCriterion("followers is null");
            return (Criteria) this;
        }

        public Criteria andFollowersIsNotNull() {
            addCriterion("followers is not null");
            return (Criteria) this;
        }

        public Criteria andFollowersEqualTo(Integer value) {
            addCriterion("followers =", value, "followers");
            return (Criteria) this;
        }

        public Criteria andFollowersNotEqualTo(Integer value) {
            addCriterion("followers <>", value, "followers");
            return (Criteria) this;
        }

        public Criteria andFollowersGreaterThan(Integer value) {
            addCriterion("followers >", value, "followers");
            return (Criteria) this;
        }

        public Criteria andFollowersGreaterThanOrEqualTo(Integer value) {
            addCriterion("followers >=", value, "followers");
            return (Criteria) this;
        }

        public Criteria andFollowersLessThan(Integer value) {
            addCriterion("followers <", value, "followers");
            return (Criteria) this;
        }

        public Criteria andFollowersLessThanOrEqualTo(Integer value) {
            addCriterion("followers <=", value, "followers");
            return (Criteria) this;
        }

        public Criteria andFollowersIn(List<Integer> values) {
            addCriterion("followers in", values, "followers");
            return (Criteria) this;
        }

        public Criteria andFollowersNotIn(List<Integer> values) {
            addCriterion("followers not in", values, "followers");
            return (Criteria) this;
        }

        public Criteria andFollowersBetween(Integer value1, Integer value2) {
            addCriterion("followers between", value1, value2, "followers");
            return (Criteria) this;
        }

        public Criteria andFollowersNotBetween(Integer value1, Integer value2) {
            addCriterion("followers not between", value1, value2, "followers");
            return (Criteria) this;
        }

        public Criteria andFollowingIsNull() {
            addCriterion("following is null");
            return (Criteria) this;
        }

        public Criteria andFollowingIsNotNull() {
            addCriterion("following is not null");
            return (Criteria) this;
        }

        public Criteria andFollowingEqualTo(Integer value) {
            addCriterion("following =", value, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingNotEqualTo(Integer value) {
            addCriterion("following <>", value, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingGreaterThan(Integer value) {
            addCriterion("following >", value, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingGreaterThanOrEqualTo(Integer value) {
            addCriterion("following >=", value, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingLessThan(Integer value) {
            addCriterion("following <", value, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingLessThanOrEqualTo(Integer value) {
            addCriterion("following <=", value, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingIn(List<Integer> values) {
            addCriterion("following in", values, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingNotIn(List<Integer> values) {
            addCriterion("following not in", values, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingBetween(Integer value1, Integer value2) {
            addCriterion("following between", value1, value2, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingNotBetween(Integer value1, Integer value2) {
            addCriterion("following not between", value1, value2, "following");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andPrivateGistsIsNull() {
            addCriterion("private_gists is null");
            return (Criteria) this;
        }

        public Criteria andPrivateGistsIsNotNull() {
            addCriterion("private_gists is not null");
            return (Criteria) this;
        }

        public Criteria andPrivateGistsEqualTo(Integer value) {
            addCriterion("private_gists =", value, "privateGists");
            return (Criteria) this;
        }

        public Criteria andPrivateGistsNotEqualTo(Integer value) {
            addCriterion("private_gists <>", value, "privateGists");
            return (Criteria) this;
        }

        public Criteria andPrivateGistsGreaterThan(Integer value) {
            addCriterion("private_gists >", value, "privateGists");
            return (Criteria) this;
        }

        public Criteria andPrivateGistsGreaterThanOrEqualTo(Integer value) {
            addCriterion("private_gists >=", value, "privateGists");
            return (Criteria) this;
        }

        public Criteria andPrivateGistsLessThan(Integer value) {
            addCriterion("private_gists <", value, "privateGists");
            return (Criteria) this;
        }

        public Criteria andPrivateGistsLessThanOrEqualTo(Integer value) {
            addCriterion("private_gists <=", value, "privateGists");
            return (Criteria) this;
        }

        public Criteria andPrivateGistsIn(List<Integer> values) {
            addCriterion("private_gists in", values, "privateGists");
            return (Criteria) this;
        }

        public Criteria andPrivateGistsNotIn(List<Integer> values) {
            addCriterion("private_gists not in", values, "privateGists");
            return (Criteria) this;
        }

        public Criteria andPrivateGistsBetween(Integer value1, Integer value2) {
            addCriterion("private_gists between", value1, value2, "privateGists");
            return (Criteria) this;
        }

        public Criteria andPrivateGistsNotBetween(Integer value1, Integer value2) {
            addCriterion("private_gists not between", value1, value2, "privateGists");
            return (Criteria) this;
        }

        public Criteria andTotalPrivateReposIsNull() {
            addCriterion("total_private_repos is null");
            return (Criteria) this;
        }

        public Criteria andTotalPrivateReposIsNotNull() {
            addCriterion("total_private_repos is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPrivateReposEqualTo(Integer value) {
            addCriterion("total_private_repos =", value, "totalPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andTotalPrivateReposNotEqualTo(Integer value) {
            addCriterion("total_private_repos <>", value, "totalPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andTotalPrivateReposGreaterThan(Integer value) {
            addCriterion("total_private_repos >", value, "totalPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andTotalPrivateReposGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_private_repos >=", value, "totalPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andTotalPrivateReposLessThan(Integer value) {
            addCriterion("total_private_repos <", value, "totalPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andTotalPrivateReposLessThanOrEqualTo(Integer value) {
            addCriterion("total_private_repos <=", value, "totalPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andTotalPrivateReposIn(List<Integer> values) {
            addCriterion("total_private_repos in", values, "totalPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andTotalPrivateReposNotIn(List<Integer> values) {
            addCriterion("total_private_repos not in", values, "totalPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andTotalPrivateReposBetween(Integer value1, Integer value2) {
            addCriterion("total_private_repos between", value1, value2, "totalPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andTotalPrivateReposNotBetween(Integer value1, Integer value2) {
            addCriterion("total_private_repos not between", value1, value2, "totalPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andOwnedPrivateReposIsNull() {
            addCriterion("owned_private_repos is null");
            return (Criteria) this;
        }

        public Criteria andOwnedPrivateReposIsNotNull() {
            addCriterion("owned_private_repos is not null");
            return (Criteria) this;
        }

        public Criteria andOwnedPrivateReposEqualTo(Integer value) {
            addCriterion("owned_private_repos =", value, "ownedPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andOwnedPrivateReposNotEqualTo(Integer value) {
            addCriterion("owned_private_repos <>", value, "ownedPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andOwnedPrivateReposGreaterThan(Integer value) {
            addCriterion("owned_private_repos >", value, "ownedPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andOwnedPrivateReposGreaterThanOrEqualTo(Integer value) {
            addCriterion("owned_private_repos >=", value, "ownedPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andOwnedPrivateReposLessThan(Integer value) {
            addCriterion("owned_private_repos <", value, "ownedPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andOwnedPrivateReposLessThanOrEqualTo(Integer value) {
            addCriterion("owned_private_repos <=", value, "ownedPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andOwnedPrivateReposIn(List<Integer> values) {
            addCriterion("owned_private_repos in", values, "ownedPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andOwnedPrivateReposNotIn(List<Integer> values) {
            addCriterion("owned_private_repos not in", values, "ownedPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andOwnedPrivateReposBetween(Integer value1, Integer value2) {
            addCriterion("owned_private_repos between", value1, value2, "ownedPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andOwnedPrivateReposNotBetween(Integer value1, Integer value2) {
            addCriterion("owned_private_repos not between", value1, value2, "ownedPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andDiskUsageIsNull() {
            addCriterion("disk_usage is null");
            return (Criteria) this;
        }

        public Criteria andDiskUsageIsNotNull() {
            addCriterion("disk_usage is not null");
            return (Criteria) this;
        }

        public Criteria andDiskUsageEqualTo(String value) {
            addCriterion("disk_usage =", value, "diskUsage");
            return (Criteria) this;
        }

        public Criteria andDiskUsageNotEqualTo(String value) {
            addCriterion("disk_usage <>", value, "diskUsage");
            return (Criteria) this;
        }

        public Criteria andDiskUsageGreaterThan(String value) {
            addCriterion("disk_usage >", value, "diskUsage");
            return (Criteria) this;
        }

        public Criteria andDiskUsageGreaterThanOrEqualTo(String value) {
            addCriterion("disk_usage >=", value, "diskUsage");
            return (Criteria) this;
        }

        public Criteria andDiskUsageLessThan(String value) {
            addCriterion("disk_usage <", value, "diskUsage");
            return (Criteria) this;
        }

        public Criteria andDiskUsageLessThanOrEqualTo(String value) {
            addCriterion("disk_usage <=", value, "diskUsage");
            return (Criteria) this;
        }

        public Criteria andDiskUsageLike(String value) {
            addCriterion("disk_usage like", value, "diskUsage");
            return (Criteria) this;
        }

        public Criteria andDiskUsageNotLike(String value) {
            addCriterion("disk_usage not like", value, "diskUsage");
            return (Criteria) this;
        }

        public Criteria andDiskUsageIn(List<String> values) {
            addCriterion("disk_usage in", values, "diskUsage");
            return (Criteria) this;
        }

        public Criteria andDiskUsageNotIn(List<String> values) {
            addCriterion("disk_usage not in", values, "diskUsage");
            return (Criteria) this;
        }

        public Criteria andDiskUsageBetween(String value1, String value2) {
            addCriterion("disk_usage between", value1, value2, "diskUsage");
            return (Criteria) this;
        }

        public Criteria andDiskUsageNotBetween(String value1, String value2) {
            addCriterion("disk_usage not between", value1, value2, "diskUsage");
            return (Criteria) this;
        }

        public Criteria andCollaboratorsIsNull() {
            addCriterion("collaborators is null");
            return (Criteria) this;
        }

        public Criteria andCollaboratorsIsNotNull() {
            addCriterion("collaborators is not null");
            return (Criteria) this;
        }

        public Criteria andCollaboratorsEqualTo(Integer value) {
            addCriterion("collaborators =", value, "collaborators");
            return (Criteria) this;
        }

        public Criteria andCollaboratorsNotEqualTo(Integer value) {
            addCriterion("collaborators <>", value, "collaborators");
            return (Criteria) this;
        }

        public Criteria andCollaboratorsGreaterThan(Integer value) {
            addCriterion("collaborators >", value, "collaborators");
            return (Criteria) this;
        }

        public Criteria andCollaboratorsGreaterThanOrEqualTo(Integer value) {
            addCriterion("collaborators >=", value, "collaborators");
            return (Criteria) this;
        }

        public Criteria andCollaboratorsLessThan(Integer value) {
            addCriterion("collaborators <", value, "collaborators");
            return (Criteria) this;
        }

        public Criteria andCollaboratorsLessThanOrEqualTo(Integer value) {
            addCriterion("collaborators <=", value, "collaborators");
            return (Criteria) this;
        }

        public Criteria andCollaboratorsIn(List<Integer> values) {
            addCriterion("collaborators in", values, "collaborators");
            return (Criteria) this;
        }

        public Criteria andCollaboratorsNotIn(List<Integer> values) {
            addCriterion("collaborators not in", values, "collaborators");
            return (Criteria) this;
        }

        public Criteria andCollaboratorsBetween(Integer value1, Integer value2) {
            addCriterion("collaborators between", value1, value2, "collaborators");
            return (Criteria) this;
        }

        public Criteria andCollaboratorsNotBetween(Integer value1, Integer value2) {
            addCriterion("collaborators not between", value1, value2, "collaborators");
            return (Criteria) this;
        }

        public Criteria andTwoFactorAuthenticationIsNull() {
            addCriterion("two_factor_authentication is null");
            return (Criteria) this;
        }

        public Criteria andTwoFactorAuthenticationIsNotNull() {
            addCriterion("two_factor_authentication is not null");
            return (Criteria) this;
        }

        public Criteria andTwoFactorAuthenticationEqualTo(String value) {
            addCriterion("two_factor_authentication =", value, "twoFactorAuthentication");
            return (Criteria) this;
        }

        public Criteria andTwoFactorAuthenticationNotEqualTo(String value) {
            addCriterion("two_factor_authentication <>", value, "twoFactorAuthentication");
            return (Criteria) this;
        }

        public Criteria andTwoFactorAuthenticationGreaterThan(String value) {
            addCriterion("two_factor_authentication >", value, "twoFactorAuthentication");
            return (Criteria) this;
        }

        public Criteria andTwoFactorAuthenticationGreaterThanOrEqualTo(String value) {
            addCriterion("two_factor_authentication >=", value, "twoFactorAuthentication");
            return (Criteria) this;
        }

        public Criteria andTwoFactorAuthenticationLessThan(String value) {
            addCriterion("two_factor_authentication <", value, "twoFactorAuthentication");
            return (Criteria) this;
        }

        public Criteria andTwoFactorAuthenticationLessThanOrEqualTo(String value) {
            addCriterion("two_factor_authentication <=", value, "twoFactorAuthentication");
            return (Criteria) this;
        }

        public Criteria andTwoFactorAuthenticationLike(String value) {
            addCriterion("two_factor_authentication like", value, "twoFactorAuthentication");
            return (Criteria) this;
        }

        public Criteria andTwoFactorAuthenticationNotLike(String value) {
            addCriterion("two_factor_authentication not like", value, "twoFactorAuthentication");
            return (Criteria) this;
        }

        public Criteria andTwoFactorAuthenticationIn(List<String> values) {
            addCriterion("two_factor_authentication in", values, "twoFactorAuthentication");
            return (Criteria) this;
        }

        public Criteria andTwoFactorAuthenticationNotIn(List<String> values) {
            addCriterion("two_factor_authentication not in", values, "twoFactorAuthentication");
            return (Criteria) this;
        }

        public Criteria andTwoFactorAuthenticationBetween(String value1, String value2) {
            addCriterion("two_factor_authentication between", value1, value2, "twoFactorAuthentication");
            return (Criteria) this;
        }

        public Criteria andTwoFactorAuthenticationNotBetween(String value1, String value2) {
            addCriterion("two_factor_authentication not between", value1, value2, "twoFactorAuthentication");
            return (Criteria) this;
        }

        public Criteria andPlanNameIsNull() {
            addCriterion("plan_name is null");
            return (Criteria) this;
        }

        public Criteria andPlanNameIsNotNull() {
            addCriterion("plan_name is not null");
            return (Criteria) this;
        }

        public Criteria andPlanNameEqualTo(String value) {
            addCriterion("plan_name =", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotEqualTo(String value) {
            addCriterion("plan_name <>", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThan(String value) {
            addCriterion("plan_name >", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThanOrEqualTo(String value) {
            addCriterion("plan_name >=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThan(String value) {
            addCriterion("plan_name <", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThanOrEqualTo(String value) {
            addCriterion("plan_name <=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLike(String value) {
            addCriterion("plan_name like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotLike(String value) {
            addCriterion("plan_name not like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameIn(List<String> values) {
            addCriterion("plan_name in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotIn(List<String> values) {
            addCriterion("plan_name not in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameBetween(String value1, String value2) {
            addCriterion("plan_name between", value1, value2, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotBetween(String value1, String value2) {
            addCriterion("plan_name not between", value1, value2, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanSpaceIsNull() {
            addCriterion("plan_space is null");
            return (Criteria) this;
        }

        public Criteria andPlanSpaceIsNotNull() {
            addCriterion("plan_space is not null");
            return (Criteria) this;
        }

        public Criteria andPlanSpaceEqualTo(String value) {
            addCriterion("plan_space =", value, "planSpace");
            return (Criteria) this;
        }

        public Criteria andPlanSpaceNotEqualTo(String value) {
            addCriterion("plan_space <>", value, "planSpace");
            return (Criteria) this;
        }

        public Criteria andPlanSpaceGreaterThan(String value) {
            addCriterion("plan_space >", value, "planSpace");
            return (Criteria) this;
        }

        public Criteria andPlanSpaceGreaterThanOrEqualTo(String value) {
            addCriterion("plan_space >=", value, "planSpace");
            return (Criteria) this;
        }

        public Criteria andPlanSpaceLessThan(String value) {
            addCriterion("plan_space <", value, "planSpace");
            return (Criteria) this;
        }

        public Criteria andPlanSpaceLessThanOrEqualTo(String value) {
            addCriterion("plan_space <=", value, "planSpace");
            return (Criteria) this;
        }

        public Criteria andPlanSpaceLike(String value) {
            addCriterion("plan_space like", value, "planSpace");
            return (Criteria) this;
        }

        public Criteria andPlanSpaceNotLike(String value) {
            addCriterion("plan_space not like", value, "planSpace");
            return (Criteria) this;
        }

        public Criteria andPlanSpaceIn(List<String> values) {
            addCriterion("plan_space in", values, "planSpace");
            return (Criteria) this;
        }

        public Criteria andPlanSpaceNotIn(List<String> values) {
            addCriterion("plan_space not in", values, "planSpace");
            return (Criteria) this;
        }

        public Criteria andPlanSpaceBetween(String value1, String value2) {
            addCriterion("plan_space between", value1, value2, "planSpace");
            return (Criteria) this;
        }

        public Criteria andPlanSpaceNotBetween(String value1, String value2) {
            addCriterion("plan_space not between", value1, value2, "planSpace");
            return (Criteria) this;
        }

        public Criteria andPlanCollaboratorsIsNull() {
            addCriterion("plan_collaborators is null");
            return (Criteria) this;
        }

        public Criteria andPlanCollaboratorsIsNotNull() {
            addCriterion("plan_collaborators is not null");
            return (Criteria) this;
        }

        public Criteria andPlanCollaboratorsEqualTo(Integer value) {
            addCriterion("plan_collaborators =", value, "planCollaborators");
            return (Criteria) this;
        }

        public Criteria andPlanCollaboratorsNotEqualTo(Integer value) {
            addCriterion("plan_collaborators <>", value, "planCollaborators");
            return (Criteria) this;
        }

        public Criteria andPlanCollaboratorsGreaterThan(Integer value) {
            addCriterion("plan_collaborators >", value, "planCollaborators");
            return (Criteria) this;
        }

        public Criteria andPlanCollaboratorsGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_collaborators >=", value, "planCollaborators");
            return (Criteria) this;
        }

        public Criteria andPlanCollaboratorsLessThan(Integer value) {
            addCriterion("plan_collaborators <", value, "planCollaborators");
            return (Criteria) this;
        }

        public Criteria andPlanCollaboratorsLessThanOrEqualTo(Integer value) {
            addCriterion("plan_collaborators <=", value, "planCollaborators");
            return (Criteria) this;
        }

        public Criteria andPlanCollaboratorsIn(List<Integer> values) {
            addCriterion("plan_collaborators in", values, "planCollaborators");
            return (Criteria) this;
        }

        public Criteria andPlanCollaboratorsNotIn(List<Integer> values) {
            addCriterion("plan_collaborators not in", values, "planCollaborators");
            return (Criteria) this;
        }

        public Criteria andPlanCollaboratorsBetween(Integer value1, Integer value2) {
            addCriterion("plan_collaborators between", value1, value2, "planCollaborators");
            return (Criteria) this;
        }

        public Criteria andPlanCollaboratorsNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_collaborators not between", value1, value2, "planCollaborators");
            return (Criteria) this;
        }

        public Criteria andPlanPrivateReposIsNull() {
            addCriterion("plan_private_repos is null");
            return (Criteria) this;
        }

        public Criteria andPlanPrivateReposIsNotNull() {
            addCriterion("plan_private_repos is not null");
            return (Criteria) this;
        }

        public Criteria andPlanPrivateReposEqualTo(Integer value) {
            addCriterion("plan_private_repos =", value, "planPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andPlanPrivateReposNotEqualTo(Integer value) {
            addCriterion("plan_private_repos <>", value, "planPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andPlanPrivateReposGreaterThan(Integer value) {
            addCriterion("plan_private_repos >", value, "planPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andPlanPrivateReposGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_private_repos >=", value, "planPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andPlanPrivateReposLessThan(Integer value) {
            addCriterion("plan_private_repos <", value, "planPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andPlanPrivateReposLessThanOrEqualTo(Integer value) {
            addCriterion("plan_private_repos <=", value, "planPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andPlanPrivateReposIn(List<Integer> values) {
            addCriterion("plan_private_repos in", values, "planPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andPlanPrivateReposNotIn(List<Integer> values) {
            addCriterion("plan_private_repos not in", values, "planPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andPlanPrivateReposBetween(Integer value1, Integer value2) {
            addCriterion("plan_private_repos between", value1, value2, "planPrivateRepos");
            return (Criteria) this;
        }

        public Criteria andPlanPrivateReposNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_private_repos not between", value1, value2, "planPrivateRepos");
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