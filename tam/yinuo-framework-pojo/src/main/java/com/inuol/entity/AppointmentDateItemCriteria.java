package com.inuol.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentDateItemCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppointmentDateItemCriteria() {
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

        public Criteria andScenicIdIsNull() {
            addCriterion("scenic_id is null");
            return (Criteria) this;
        }

        public Criteria andScenicIdIsNotNull() {
            addCriterion("scenic_id is not null");
            return (Criteria) this;
        }

        public Criteria andScenicIdEqualTo(Integer value) {
            addCriterion("scenic_id =", value, "scenicId");
            return (Criteria) this;
        }

        public Criteria andScenicIdNotEqualTo(Integer value) {
            addCriterion("scenic_id <>", value, "scenicId");
            return (Criteria) this;
        }

        public Criteria andScenicIdGreaterThan(Integer value) {
            addCriterion("scenic_id >", value, "scenicId");
            return (Criteria) this;
        }

        public Criteria andScenicIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("scenic_id >=", value, "scenicId");
            return (Criteria) this;
        }

        public Criteria andScenicIdLessThan(Integer value) {
            addCriterion("scenic_id <", value, "scenicId");
            return (Criteria) this;
        }

        public Criteria andScenicIdLessThanOrEqualTo(Integer value) {
            addCriterion("scenic_id <=", value, "scenicId");
            return (Criteria) this;
        }

        public Criteria andScenicIdIn(List<Integer> values) {
            addCriterion("scenic_id in", values, "scenicId");
            return (Criteria) this;
        }

        public Criteria andScenicIdNotIn(List<Integer> values) {
            addCriterion("scenic_id not in", values, "scenicId");
            return (Criteria) this;
        }

        public Criteria andScenicIdBetween(Integer value1, Integer value2) {
            addCriterion("scenic_id between", value1, value2, "scenicId");
            return (Criteria) this;
        }

        public Criteria andScenicIdNotBetween(Integer value1, Integer value2) {
            addCriterion("scenic_id not between", value1, value2, "scenicId");
            return (Criteria) this;
        }

        public Criteria andScenicIsNull() {
            addCriterion("scenic is null");
            return (Criteria) this;
        }

        public Criteria andScenicIsNotNull() {
            addCriterion("scenic is not null");
            return (Criteria) this;
        }

        public Criteria andScenicEqualTo(String value) {
            addCriterion("scenic =", value, "scenic");
            return (Criteria) this;
        }

        public Criteria andScenicNotEqualTo(String value) {
            addCriterion("scenic <>", value, "scenic");
            return (Criteria) this;
        }

        public Criteria andScenicGreaterThan(String value) {
            addCriterion("scenic >", value, "scenic");
            return (Criteria) this;
        }

        public Criteria andScenicGreaterThanOrEqualTo(String value) {
            addCriterion("scenic >=", value, "scenic");
            return (Criteria) this;
        }

        public Criteria andScenicLessThan(String value) {
            addCriterion("scenic <", value, "scenic");
            return (Criteria) this;
        }

        public Criteria andScenicLessThanOrEqualTo(String value) {
            addCriterion("scenic <=", value, "scenic");
            return (Criteria) this;
        }

        public Criteria andScenicLike(String value) {
            addCriterion("scenic like", value, "scenic");
            return (Criteria) this;
        }

        public Criteria andScenicNotLike(String value) {
            addCriterion("scenic not like", value, "scenic");
            return (Criteria) this;
        }

        public Criteria andScenicIn(List<String> values) {
            addCriterion("scenic in", values, "scenic");
            return (Criteria) this;
        }

        public Criteria andScenicNotIn(List<String> values) {
            addCriterion("scenic not in", values, "scenic");
            return (Criteria) this;
        }

        public Criteria andScenicBetween(String value1, String value2) {
            addCriterion("scenic between", value1, value2, "scenic");
            return (Criteria) this;
        }

        public Criteria andScenicNotBetween(String value1, String value2) {
            addCriterion("scenic not between", value1, value2, "scenic");
            return (Criteria) this;
        }

        public Criteria andDateIdIsNull() {
            addCriterion("date_id is null");
            return (Criteria) this;
        }

        public Criteria andDateIdIsNotNull() {
            addCriterion("date_id is not null");
            return (Criteria) this;
        }

        public Criteria andDateIdEqualTo(Integer value) {
            addCriterion("date_id =", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdNotEqualTo(Integer value) {
            addCriterion("date_id <>", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdGreaterThan(Integer value) {
            addCriterion("date_id >", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("date_id >=", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdLessThan(Integer value) {
            addCriterion("date_id <", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdLessThanOrEqualTo(Integer value) {
            addCriterion("date_id <=", value, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdIn(List<Integer> values) {
            addCriterion("date_id in", values, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdNotIn(List<Integer> values) {
            addCriterion("date_id not in", values, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdBetween(Integer value1, Integer value2) {
            addCriterion("date_id between", value1, value2, "dateId");
            return (Criteria) this;
        }

        public Criteria andDateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("date_id not between", value1, value2, "dateId");
            return (Criteria) this;
        }

        public Criteria andPlayDateIsNull() {
            addCriterion("play_date is null");
            return (Criteria) this;
        }

        public Criteria andPlayDateIsNotNull() {
            addCriterion("play_date is not null");
            return (Criteria) this;
        }

        public Criteria andPlayDateEqualTo(String value) {
            addCriterion("play_date =", value, "playDate");
            return (Criteria) this;
        }

        public Criteria andPlayDateNotEqualTo(String value) {
            addCriterion("play_date <>", value, "playDate");
            return (Criteria) this;
        }

        public Criteria andPlayDateGreaterThan(String value) {
            addCriterion("play_date >", value, "playDate");
            return (Criteria) this;
        }

        public Criteria andPlayDateGreaterThanOrEqualTo(String value) {
            addCriterion("play_date >=", value, "playDate");
            return (Criteria) this;
        }

        public Criteria andPlayDateLessThan(String value) {
            addCriterion("play_date <", value, "playDate");
            return (Criteria) this;
        }

        public Criteria andPlayDateLessThanOrEqualTo(String value) {
            addCriterion("play_date <=", value, "playDate");
            return (Criteria) this;
        }

        public Criteria andPlayDateLike(String value) {
            addCriterion("play_date like", value, "playDate");
            return (Criteria) this;
        }

        public Criteria andPlayDateNotLike(String value) {
            addCriterion("play_date not like", value, "playDate");
            return (Criteria) this;
        }

        public Criteria andPlayDateIn(List<String> values) {
            addCriterion("play_date in", values, "playDate");
            return (Criteria) this;
        }

        public Criteria andPlayDateNotIn(List<String> values) {
            addCriterion("play_date not in", values, "playDate");
            return (Criteria) this;
        }

        public Criteria andPlayDateBetween(String value1, String value2) {
            addCriterion("play_date between", value1, value2, "playDate");
            return (Criteria) this;
        }

        public Criteria andPlayDateNotBetween(String value1, String value2) {
            addCriterion("play_date not between", value1, value2, "playDate");
            return (Criteria) this;
        }

        public Criteria andTimeQuantumIsNull() {
            addCriterion("time_quantum is null");
            return (Criteria) this;
        }

        public Criteria andTimeQuantumIsNotNull() {
            addCriterion("time_quantum is not null");
            return (Criteria) this;
        }

        public Criteria andTimeQuantumEqualTo(String value) {
            addCriterion("time_quantum =", value, "timeQuantum");
            return (Criteria) this;
        }

        public Criteria andTimeQuantumNotEqualTo(String value) {
            addCriterion("time_quantum <>", value, "timeQuantum");
            return (Criteria) this;
        }

        public Criteria andTimeQuantumGreaterThan(String value) {
            addCriterion("time_quantum >", value, "timeQuantum");
            return (Criteria) this;
        }

        public Criteria andTimeQuantumGreaterThanOrEqualTo(String value) {
            addCriterion("time_quantum >=", value, "timeQuantum");
            return (Criteria) this;
        }

        public Criteria andTimeQuantumLessThan(String value) {
            addCriterion("time_quantum <", value, "timeQuantum");
            return (Criteria) this;
        }

        public Criteria andTimeQuantumLessThanOrEqualTo(String value) {
            addCriterion("time_quantum <=", value, "timeQuantum");
            return (Criteria) this;
        }

        public Criteria andTimeQuantumLike(String value) {
            addCriterion("time_quantum like", value, "timeQuantum");
            return (Criteria) this;
        }

        public Criteria andTimeQuantumNotLike(String value) {
            addCriterion("time_quantum not like", value, "timeQuantum");
            return (Criteria) this;
        }

        public Criteria andTimeQuantumIn(List<String> values) {
            addCriterion("time_quantum in", values, "timeQuantum");
            return (Criteria) this;
        }

        public Criteria andTimeQuantumNotIn(List<String> values) {
            addCriterion("time_quantum not in", values, "timeQuantum");
            return (Criteria) this;
        }

        public Criteria andTimeQuantumBetween(String value1, String value2) {
            addCriterion("time_quantum between", value1, value2, "timeQuantum");
            return (Criteria) this;
        }

        public Criteria andTimeQuantumNotBetween(String value1, String value2) {
            addCriterion("time_quantum not between", value1, value2, "timeQuantum");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
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