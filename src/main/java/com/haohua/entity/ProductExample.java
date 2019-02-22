package com.haohua.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductExample {
    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    protected String orderByClause;

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    protected boolean distinct;

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public ProductExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPro_nameIsNull() {
            addCriterion("pro_name is null");
            return (Criteria) this;
        }

        public Criteria andPro_nameIsNotNull() {
            addCriterion("pro_name is not null");
            return (Criteria) this;
        }

        public Criteria andPro_nameEqualTo(String value) {
            addCriterion("pro_name =", value, "pro_name");
            return (Criteria) this;
        }

        public Criteria andPro_nameNotEqualTo(String value) {
            addCriterion("pro_name <>", value, "pro_name");
            return (Criteria) this;
        }

        public Criteria andPro_nameGreaterThan(String value) {
            addCriterion("pro_name >", value, "pro_name");
            return (Criteria) this;
        }

        public Criteria andPro_nameGreaterThanOrEqualTo(String value) {
            addCriterion("pro_name >=", value, "pro_name");
            return (Criteria) this;
        }

        public Criteria andPro_nameLessThan(String value) {
            addCriterion("pro_name <", value, "pro_name");
            return (Criteria) this;
        }

        public Criteria andPro_nameLessThanOrEqualTo(String value) {
            addCriterion("pro_name <=", value, "pro_name");
            return (Criteria) this;
        }

        public Criteria andPro_nameLike(String value) {
            addCriterion("pro_name like", value, "pro_name");
            return (Criteria) this;
        }

        public Criteria andPro_nameNotLike(String value) {
            addCriterion("pro_name not like", value, "pro_name");
            return (Criteria) this;
        }

        public Criteria andPro_nameIn(List<String> values) {
            addCriterion("pro_name in", values, "pro_name");
            return (Criteria) this;
        }

        public Criteria andPro_nameNotIn(List<String> values) {
            addCriterion("pro_name not in", values, "pro_name");
            return (Criteria) this;
        }

        public Criteria andPro_nameBetween(String value1, String value2) {
            addCriterion("pro_name between", value1, value2, "pro_name");
            return (Criteria) this;
        }

        public Criteria andPro_nameNotBetween(String value1, String value2) {
            addCriterion("pro_name not between", value1, value2, "pro_name");
            return (Criteria) this;
        }

        public Criteria andPro_priceIsNull() {
            addCriterion("pro_price is null");
            return (Criteria) this;
        }

        public Criteria andPro_priceIsNotNull() {
            addCriterion("pro_price is not null");
            return (Criteria) this;
        }

        public Criteria andPro_priceEqualTo(BigDecimal value) {
            addCriterion("pro_price =", value, "pro_price");
            return (Criteria) this;
        }

        public Criteria andPro_priceNotEqualTo(BigDecimal value) {
            addCriterion("pro_price <>", value, "pro_price");
            return (Criteria) this;
        }

        public Criteria andPro_priceGreaterThan(BigDecimal value) {
            addCriterion("pro_price >", value, "pro_price");
            return (Criteria) this;
        }

        public Criteria andPro_priceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pro_price >=", value, "pro_price");
            return (Criteria) this;
        }

        public Criteria andPro_priceLessThan(BigDecimal value) {
            addCriterion("pro_price <", value, "pro_price");
            return (Criteria) this;
        }

        public Criteria andPro_priceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pro_price <=", value, "pro_price");
            return (Criteria) this;
        }

        public Criteria andPro_priceIn(List<BigDecimal> values) {
            addCriterion("pro_price in", values, "pro_price");
            return (Criteria) this;
        }

        public Criteria andPro_priceNotIn(List<BigDecimal> values) {
            addCriterion("pro_price not in", values, "pro_price");
            return (Criteria) this;
        }

        public Criteria andPro_priceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pro_price between", value1, value2, "pro_price");
            return (Criteria) this;
        }

        public Criteria andPro_priceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pro_price not between", value1, value2, "pro_price");
            return (Criteria) this;
        }

        public Criteria andInventoryIsNull() {
            addCriterion("inventory is null");
            return (Criteria) this;
        }

        public Criteria andInventoryIsNotNull() {
            addCriterion("inventory is not null");
            return (Criteria) this;
        }

        public Criteria andInventoryEqualTo(Integer value) {
            addCriterion("inventory =", value, "inventory");
            return (Criteria) this;
        }

        public Criteria andInventoryNotEqualTo(Integer value) {
            addCriterion("inventory <>", value, "inventory");
            return (Criteria) this;
        }

        public Criteria andInventoryGreaterThan(Integer value) {
            addCriterion("inventory >", value, "inventory");
            return (Criteria) this;
        }

        public Criteria andInventoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("inventory >=", value, "inventory");
            return (Criteria) this;
        }

        public Criteria andInventoryLessThan(Integer value) {
            addCriterion("inventory <", value, "inventory");
            return (Criteria) this;
        }

        public Criteria andInventoryLessThanOrEqualTo(Integer value) {
            addCriterion("inventory <=", value, "inventory");
            return (Criteria) this;
        }

        public Criteria andInventoryIn(List<Integer> values) {
            addCriterion("inventory in", values, "inventory");
            return (Criteria) this;
        }

        public Criteria andInventoryNotIn(List<Integer> values) {
            addCriterion("inventory not in", values, "inventory");
            return (Criteria) this;
        }

        public Criteria andInventoryBetween(Integer value1, Integer value2) {
            addCriterion("inventory between", value1, value2, "inventory");
            return (Criteria) this;
        }

        public Criteria andInventoryNotBetween(Integer value1, Integer value2) {
            addCriterion("inventory not between", value1, value2, "inventory");
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

        public Criteria andVersionEqualTo(Long value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Long value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Long value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Long value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Long value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Long value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Long> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Long> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Long value1, Long value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Long value1, Long value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andPro_nameLikeInsensitive(String value) {
            addCriterion("upper(pro_name) like", value.toUpperCase(), "pro_name");
            return (Criteria) this;
        }
    }

    /**
     *
     * @mbg.generated do_not_delete_during_merge Wed Feb 20 20:43:17 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
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