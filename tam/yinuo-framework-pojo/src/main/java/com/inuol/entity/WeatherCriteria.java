package com.inuol.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WeatherCriteria() {
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

        public Criteria andWindDirIsNull() {
            addCriterion("wind_dir is null");
            return (Criteria) this;
        }

        public Criteria andWindDirIsNotNull() {
            addCriterion("wind_dir is not null");
            return (Criteria) this;
        }

        public Criteria andWindDirEqualTo(String value) {
            addCriterion("wind_dir =", value, "windDir");
            return (Criteria) this;
        }

        public Criteria andWindDirNotEqualTo(String value) {
            addCriterion("wind_dir <>", value, "windDir");
            return (Criteria) this;
        }

        public Criteria andWindDirGreaterThan(String value) {
            addCriterion("wind_dir >", value, "windDir");
            return (Criteria) this;
        }

        public Criteria andWindDirGreaterThanOrEqualTo(String value) {
            addCriterion("wind_dir >=", value, "windDir");
            return (Criteria) this;
        }

        public Criteria andWindDirLessThan(String value) {
            addCriterion("wind_dir <", value, "windDir");
            return (Criteria) this;
        }

        public Criteria andWindDirLessThanOrEqualTo(String value) {
            addCriterion("wind_dir <=", value, "windDir");
            return (Criteria) this;
        }

        public Criteria andWindDirLike(String value) {
            addCriterion("wind_dir like", value, "windDir");
            return (Criteria) this;
        }

        public Criteria andWindDirNotLike(String value) {
            addCriterion("wind_dir not like", value, "windDir");
            return (Criteria) this;
        }

        public Criteria andWindDirIn(List<String> values) {
            addCriterion("wind_dir in", values, "windDir");
            return (Criteria) this;
        }

        public Criteria andWindDirNotIn(List<String> values) {
            addCriterion("wind_dir not in", values, "windDir");
            return (Criteria) this;
        }

        public Criteria andWindDirBetween(String value1, String value2) {
            addCriterion("wind_dir between", value1, value2, "windDir");
            return (Criteria) this;
        }

        public Criteria andWindDirNotBetween(String value1, String value2) {
            addCriterion("wind_dir not between", value1, value2, "windDir");
            return (Criteria) this;
        }

        public Criteria andWindScIsNull() {
            addCriterion("wind_sc is null");
            return (Criteria) this;
        }

        public Criteria andWindScIsNotNull() {
            addCriterion("wind_sc is not null");
            return (Criteria) this;
        }

        public Criteria andWindScEqualTo(String value) {
            addCriterion("wind_sc =", value, "windSc");
            return (Criteria) this;
        }

        public Criteria andWindScNotEqualTo(String value) {
            addCriterion("wind_sc <>", value, "windSc");
            return (Criteria) this;
        }

        public Criteria andWindScGreaterThan(String value) {
            addCriterion("wind_sc >", value, "windSc");
            return (Criteria) this;
        }

        public Criteria andWindScGreaterThanOrEqualTo(String value) {
            addCriterion("wind_sc >=", value, "windSc");
            return (Criteria) this;
        }

        public Criteria andWindScLessThan(String value) {
            addCriterion("wind_sc <", value, "windSc");
            return (Criteria) this;
        }

        public Criteria andWindScLessThanOrEqualTo(String value) {
            addCriterion("wind_sc <=", value, "windSc");
            return (Criteria) this;
        }

        public Criteria andWindScLike(String value) {
            addCriterion("wind_sc like", value, "windSc");
            return (Criteria) this;
        }

        public Criteria andWindScNotLike(String value) {
            addCriterion("wind_sc not like", value, "windSc");
            return (Criteria) this;
        }

        public Criteria andWindScIn(List<String> values) {
            addCriterion("wind_sc in", values, "windSc");
            return (Criteria) this;
        }

        public Criteria andWindScNotIn(List<String> values) {
            addCriterion("wind_sc not in", values, "windSc");
            return (Criteria) this;
        }

        public Criteria andWindScBetween(String value1, String value2) {
            addCriterion("wind_sc between", value1, value2, "windSc");
            return (Criteria) this;
        }

        public Criteria andWindScNotBetween(String value1, String value2) {
            addCriterion("wind_sc not between", value1, value2, "windSc");
            return (Criteria) this;
        }

        public Criteria andCondTxtIsNull() {
            addCriterion("cond_txt is null");
            return (Criteria) this;
        }

        public Criteria andCondTxtIsNotNull() {
            addCriterion("cond_txt is not null");
            return (Criteria) this;
        }

        public Criteria andCondTxtEqualTo(String value) {
            addCriterion("cond_txt =", value, "condTxt");
            return (Criteria) this;
        }

        public Criteria andCondTxtNotEqualTo(String value) {
            addCriterion("cond_txt <>", value, "condTxt");
            return (Criteria) this;
        }

        public Criteria andCondTxtGreaterThan(String value) {
            addCriterion("cond_txt >", value, "condTxt");
            return (Criteria) this;
        }

        public Criteria andCondTxtGreaterThanOrEqualTo(String value) {
            addCriterion("cond_txt >=", value, "condTxt");
            return (Criteria) this;
        }

        public Criteria andCondTxtLessThan(String value) {
            addCriterion("cond_txt <", value, "condTxt");
            return (Criteria) this;
        }

        public Criteria andCondTxtLessThanOrEqualTo(String value) {
            addCriterion("cond_txt <=", value, "condTxt");
            return (Criteria) this;
        }

        public Criteria andCondTxtLike(String value) {
            addCriterion("cond_txt like", value, "condTxt");
            return (Criteria) this;
        }

        public Criteria andCondTxtNotLike(String value) {
            addCriterion("cond_txt not like", value, "condTxt");
            return (Criteria) this;
        }

        public Criteria andCondTxtIn(List<String> values) {
            addCriterion("cond_txt in", values, "condTxt");
            return (Criteria) this;
        }

        public Criteria andCondTxtNotIn(List<String> values) {
            addCriterion("cond_txt not in", values, "condTxt");
            return (Criteria) this;
        }

        public Criteria andCondTxtBetween(String value1, String value2) {
            addCriterion("cond_txt between", value1, value2, "condTxt");
            return (Criteria) this;
        }

        public Criteria andCondTxtNotBetween(String value1, String value2) {
            addCriterion("cond_txt not between", value1, value2, "condTxt");
            return (Criteria) this;
        }

        public Criteria andTmpIsNull() {
            addCriterion("tmp is null");
            return (Criteria) this;
        }

        public Criteria andTmpIsNotNull() {
            addCriterion("tmp is not null");
            return (Criteria) this;
        }

        public Criteria andTmpEqualTo(String value) {
            addCriterion("tmp =", value, "tmp");
            return (Criteria) this;
        }

        public Criteria andTmpNotEqualTo(String value) {
            addCriterion("tmp <>", value, "tmp");
            return (Criteria) this;
        }

        public Criteria andTmpGreaterThan(String value) {
            addCriterion("tmp >", value, "tmp");
            return (Criteria) this;
        }

        public Criteria andTmpGreaterThanOrEqualTo(String value) {
            addCriterion("tmp >=", value, "tmp");
            return (Criteria) this;
        }

        public Criteria andTmpLessThan(String value) {
            addCriterion("tmp <", value, "tmp");
            return (Criteria) this;
        }

        public Criteria andTmpLessThanOrEqualTo(String value) {
            addCriterion("tmp <=", value, "tmp");
            return (Criteria) this;
        }

        public Criteria andTmpLike(String value) {
            addCriterion("tmp like", value, "tmp");
            return (Criteria) this;
        }

        public Criteria andTmpNotLike(String value) {
            addCriterion("tmp not like", value, "tmp");
            return (Criteria) this;
        }

        public Criteria andTmpIn(List<String> values) {
            addCriterion("tmp in", values, "tmp");
            return (Criteria) this;
        }

        public Criteria andTmpNotIn(List<String> values) {
            addCriterion("tmp not in", values, "tmp");
            return (Criteria) this;
        }

        public Criteria andTmpBetween(String value1, String value2) {
            addCriterion("tmp between", value1, value2, "tmp");
            return (Criteria) this;
        }

        public Criteria andTmpNotBetween(String value1, String value2) {
            addCriterion("tmp not between", value1, value2, "tmp");
            return (Criteria) this;
        }

        public Criteria andQltyIsNull() {
            addCriterion("qlty is null");
            return (Criteria) this;
        }

        public Criteria andQltyIsNotNull() {
            addCriterion("qlty is not null");
            return (Criteria) this;
        }

        public Criteria andQltyEqualTo(String value) {
            addCriterion("qlty =", value, "qlty");
            return (Criteria) this;
        }

        public Criteria andQltyNotEqualTo(String value) {
            addCriterion("qlty <>", value, "qlty");
            return (Criteria) this;
        }

        public Criteria andQltyGreaterThan(String value) {
            addCriterion("qlty >", value, "qlty");
            return (Criteria) this;
        }

        public Criteria andQltyGreaterThanOrEqualTo(String value) {
            addCriterion("qlty >=", value, "qlty");
            return (Criteria) this;
        }

        public Criteria andQltyLessThan(String value) {
            addCriterion("qlty <", value, "qlty");
            return (Criteria) this;
        }

        public Criteria andQltyLessThanOrEqualTo(String value) {
            addCriterion("qlty <=", value, "qlty");
            return (Criteria) this;
        }

        public Criteria andQltyLike(String value) {
            addCriterion("qlty like", value, "qlty");
            return (Criteria) this;
        }

        public Criteria andQltyNotLike(String value) {
            addCriterion("qlty not like", value, "qlty");
            return (Criteria) this;
        }

        public Criteria andQltyIn(List<String> values) {
            addCriterion("qlty in", values, "qlty");
            return (Criteria) this;
        }

        public Criteria andQltyNotIn(List<String> values) {
            addCriterion("qlty not in", values, "qlty");
            return (Criteria) this;
        }

        public Criteria andQltyBetween(String value1, String value2) {
            addCriterion("qlty between", value1, value2, "qlty");
            return (Criteria) this;
        }

        public Criteria andQltyNotBetween(String value1, String value2) {
            addCriterion("qlty not between", value1, value2, "qlty");
            return (Criteria) this;
        }

        public Criteria andQueryDateIsNull() {
            addCriterion("query_date is null");
            return (Criteria) this;
        }

        public Criteria andQueryDateIsNotNull() {
            addCriterion("query_date is not null");
            return (Criteria) this;
        }

        public Criteria andQueryDateEqualTo(Date value) {
            addCriterion("query_date =", value, "queryDate");
            return (Criteria) this;
        }

        public Criteria andQueryDateNotEqualTo(Date value) {
            addCriterion("query_date <>", value, "queryDate");
            return (Criteria) this;
        }

        public Criteria andQueryDateGreaterThan(Date value) {
            addCriterion("query_date >", value, "queryDate");
            return (Criteria) this;
        }

        public Criteria andQueryDateGreaterThanOrEqualTo(Date value) {
            addCriterion("query_date >=", value, "queryDate");
            return (Criteria) this;
        }

        public Criteria andQueryDateLessThan(Date value) {
            addCriterion("query_date <", value, "queryDate");
            return (Criteria) this;
        }

        public Criteria andQueryDateLessThanOrEqualTo(Date value) {
            addCriterion("query_date <=", value, "queryDate");
            return (Criteria) this;
        }

        public Criteria andQueryDateIn(List<Date> values) {
            addCriterion("query_date in", values, "queryDate");
            return (Criteria) this;
        }

        public Criteria andQueryDateNotIn(List<Date> values) {
            addCriterion("query_date not in", values, "queryDate");
            return (Criteria) this;
        }

        public Criteria andQueryDateBetween(Date value1, Date value2) {
            addCriterion("query_date between", value1, value2, "queryDate");
            return (Criteria) this;
        }

        public Criteria andQueryDateNotBetween(Date value1, Date value2) {
            addCriterion("query_date not between", value1, value2, "queryDate");
            return (Criteria) this;
        }

        public Criteria andDayTimeIsNull() {
            addCriterion("day_time is null");
            return (Criteria) this;
        }

        public Criteria andDayTimeIsNotNull() {
            addCriterion("day_time is not null");
            return (Criteria) this;
        }

        public Criteria andDayTimeEqualTo(String value) {
            addCriterion("day_time =", value, "dayTime");
            return (Criteria) this;
        }

        public Criteria andDayTimeNotEqualTo(String value) {
            addCriterion("day_time <>", value, "dayTime");
            return (Criteria) this;
        }

        public Criteria andDayTimeGreaterThan(String value) {
            addCriterion("day_time >", value, "dayTime");
            return (Criteria) this;
        }

        public Criteria andDayTimeGreaterThanOrEqualTo(String value) {
            addCriterion("day_time >=", value, "dayTime");
            return (Criteria) this;
        }

        public Criteria andDayTimeLessThan(String value) {
            addCriterion("day_time <", value, "dayTime");
            return (Criteria) this;
        }

        public Criteria andDayTimeLessThanOrEqualTo(String value) {
            addCriterion("day_time <=", value, "dayTime");
            return (Criteria) this;
        }

        public Criteria andDayTimeLike(String value) {
            addCriterion("day_time like", value, "dayTime");
            return (Criteria) this;
        }

        public Criteria andDayTimeNotLike(String value) {
            addCriterion("day_time not like", value, "dayTime");
            return (Criteria) this;
        }

        public Criteria andDayTimeIn(List<String> values) {
            addCriterion("day_time in", values, "dayTime");
            return (Criteria) this;
        }

        public Criteria andDayTimeNotIn(List<String> values) {
            addCriterion("day_time not in", values, "dayTime");
            return (Criteria) this;
        }

        public Criteria andDayTimeBetween(String value1, String value2) {
            addCriterion("day_time between", value1, value2, "dayTime");
            return (Criteria) this;
        }

        public Criteria andDayTimeNotBetween(String value1, String value2) {
            addCriterion("day_time not between", value1, value2, "dayTime");
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