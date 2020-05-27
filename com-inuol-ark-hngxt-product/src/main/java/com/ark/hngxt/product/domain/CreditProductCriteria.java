package com.ark.hngxt.product.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CreditProductCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CreditProductCriteria() {
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

        public Criteria andCreditLevelCodeIsNull() {
            addCriterion("credit_level_code is null");
            return (Criteria) this;
        }

        public Criteria andCreditLevelCodeIsNotNull() {
            addCriterion("credit_level_code is not null");
            return (Criteria) this;
        }

        public Criteria andCreditLevelCodeEqualTo(String value) {
            addCriterion("credit_level_code =", value, "creditLevelCode");
            return (Criteria) this;
        }

        public Criteria andCreditLevelCodeNotEqualTo(String value) {
            addCriterion("credit_level_code <>", value, "creditLevelCode");
            return (Criteria) this;
        }

        public Criteria andCreditLevelCodeGreaterThan(String value) {
            addCriterion("credit_level_code >", value, "creditLevelCode");
            return (Criteria) this;
        }

        public Criteria andCreditLevelCodeGreaterThanOrEqualTo(String value) {
            addCriterion("credit_level_code >=", value, "creditLevelCode");
            return (Criteria) this;
        }

        public Criteria andCreditLevelCodeLessThan(String value) {
            addCriterion("credit_level_code <", value, "creditLevelCode");
            return (Criteria) this;
        }

        public Criteria andCreditLevelCodeLessThanOrEqualTo(String value) {
            addCriterion("credit_level_code <=", value, "creditLevelCode");
            return (Criteria) this;
        }

        public Criteria andCreditLevelCodeLike(String value) {
            addCriterion("credit_level_code like", value, "creditLevelCode");
            return (Criteria) this;
        }

        public Criteria andCreditLevelCodeNotLike(String value) {
            addCriterion("credit_level_code not like", value, "creditLevelCode");
            return (Criteria) this;
        }

        public Criteria andCreditLevelCodeIn(List<String> values) {
            addCriterion("credit_level_code in", values, "creditLevelCode");
            return (Criteria) this;
        }

        public Criteria andCreditLevelCodeNotIn(List<String> values) {
            addCriterion("credit_level_code not in", values, "creditLevelCode");
            return (Criteria) this;
        }

        public Criteria andCreditLevelCodeBetween(String value1, String value2) {
            addCriterion("credit_level_code between", value1, value2, "creditLevelCode");
            return (Criteria) this;
        }

        public Criteria andCreditLevelCodeNotBetween(String value1, String value2) {
            addCriterion("credit_level_code not between", value1, value2, "creditLevelCode");
            return (Criteria) this;
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

        public Criteria andBeTopIsNull() {
            addCriterion("be_top is null");
            return (Criteria) this;
        }

        public Criteria andBeTopIsNotNull() {
            addCriterion("be_top is not null");
            return (Criteria) this;
        }

        public Criteria andBeTopEqualTo(Integer value) {
            addCriterion("be_top =", value, "beTop");
            return (Criteria) this;
        }

        public Criteria andBeTopNotEqualTo(Integer value) {
            addCriterion("be_top <>", value, "beTop");
            return (Criteria) this;
        }

        public Criteria andBeTopGreaterThan(Integer value) {
            addCriterion("be_top >", value, "beTop");
            return (Criteria) this;
        }

        public Criteria andBeTopGreaterThanOrEqualTo(Integer value) {
            addCriterion("be_top >=", value, "beTop");
            return (Criteria) this;
        }

        public Criteria andBeTopLessThan(Integer value) {
            addCriterion("be_top <", value, "beTop");
            return (Criteria) this;
        }

        public Criteria andBeTopLessThanOrEqualTo(Integer value) {
            addCriterion("be_top <=", value, "beTop");
            return (Criteria) this;
        }

        public Criteria andBeTopIn(List<Integer> values) {
            addCriterion("be_top in", values, "beTop");
            return (Criteria) this;
        }

        public Criteria andBeTopNotIn(List<Integer> values) {
            addCriterion("be_top not in", values, "beTop");
            return (Criteria) this;
        }

        public Criteria andBeTopBetween(Integer value1, Integer value2) {
            addCriterion("be_top between", value1, value2, "beTop");
            return (Criteria) this;
        }

        public Criteria andBeTopNotBetween(Integer value1, Integer value2) {
            addCriterion("be_top not between", value1, value2, "beTop");
            return (Criteria) this;
        }

        public Criteria andApplyCountIsNull() {
            addCriterion("apply_count is null");
            return (Criteria) this;
        }

        public Criteria andApplyCountIsNotNull() {
            addCriterion("apply_count is not null");
            return (Criteria) this;
        }

        public Criteria andApplyCountEqualTo(Long value) {
            addCriterion("apply_count =", value, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountNotEqualTo(Long value) {
            addCriterion("apply_count <>", value, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountGreaterThan(Long value) {
            addCriterion("apply_count >", value, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountGreaterThanOrEqualTo(Long value) {
            addCriterion("apply_count >=", value, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountLessThan(Long value) {
            addCriterion("apply_count <", value, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountLessThanOrEqualTo(Long value) {
            addCriterion("apply_count <=", value, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountIn(List<Long> values) {
            addCriterion("apply_count in", values, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountNotIn(List<Long> values) {
            addCriterion("apply_count not in", values, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountBetween(Long value1, Long value2) {
            addCriterion("apply_count between", value1, value2, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountNotBetween(Long value1, Long value2) {
            addCriterion("apply_count not between", value1, value2, "applyCount");
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

        public Criteria andOrganizationIsNull() {
            addCriterion("organization is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIsNotNull() {
            addCriterion("organization is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationEqualTo(String value) {
            addCriterion("organization =", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationNotEqualTo(String value) {
            addCriterion("organization <>", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationGreaterThan(String value) {
            addCriterion("organization >", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationGreaterThanOrEqualTo(String value) {
            addCriterion("organization >=", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationLessThan(String value) {
            addCriterion("organization <", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationLessThanOrEqualTo(String value) {
            addCriterion("organization <=", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationLike(String value) {
            addCriterion("organization like", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationNotLike(String value) {
            addCriterion("organization not like", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationIn(List<String> values) {
            addCriterion("organization in", values, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationNotIn(List<String> values) {
            addCriterion("organization not in", values, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationBetween(String value1, String value2) {
            addCriterion("organization between", value1, value2, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationNotBetween(String value1, String value2) {
            addCriterion("organization not between", value1, value2, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNull() {
            addCriterion("organization_id is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNotNull() {
            addCriterion("organization_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdEqualTo(Long value) {
            addCriterion("organization_id =", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotEqualTo(Long value) {
            addCriterion("organization_id <>", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThan(Long value) {
            addCriterion("organization_id >", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("organization_id >=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThan(Long value) {
            addCriterion("organization_id <", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThanOrEqualTo(Long value) {
            addCriterion("organization_id <=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIn(List<Long> values) {
            addCriterion("organization_id in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotIn(List<Long> values) {
            addCriterion("organization_id not in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdBetween(Long value1, Long value2) {
            addCriterion("organization_id between", value1, value2, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotBetween(Long value1, Long value2) {
            addCriterion("organization_id not between", value1, value2, "organizationId");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationIsNull() {
            addCriterion("parent_organization is null");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationIsNotNull() {
            addCriterion("parent_organization is not null");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationEqualTo(String value) {
            addCriterion("parent_organization =", value, "parentOrganization");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationNotEqualTo(String value) {
            addCriterion("parent_organization <>", value, "parentOrganization");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationGreaterThan(String value) {
            addCriterion("parent_organization >", value, "parentOrganization");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationGreaterThanOrEqualTo(String value) {
            addCriterion("parent_organization >=", value, "parentOrganization");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationLessThan(String value) {
            addCriterion("parent_organization <", value, "parentOrganization");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationLessThanOrEqualTo(String value) {
            addCriterion("parent_organization <=", value, "parentOrganization");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationLike(String value) {
            addCriterion("parent_organization like", value, "parentOrganization");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationNotLike(String value) {
            addCriterion("parent_organization not like", value, "parentOrganization");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationIn(List<String> values) {
            addCriterion("parent_organization in", values, "parentOrganization");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationNotIn(List<String> values) {
            addCriterion("parent_organization not in", values, "parentOrganization");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationBetween(String value1, String value2) {
            addCriterion("parent_organization between", value1, value2, "parentOrganization");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationNotBetween(String value1, String value2) {
            addCriterion("parent_organization not between", value1, value2, "parentOrganization");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationIdIsNull() {
            addCriterion("parent_organization_id is null");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationIdIsNotNull() {
            addCriterion("parent_organization_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationIdEqualTo(Long value) {
            addCriterion("parent_organization_id =", value, "parentOrganizationId");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationIdNotEqualTo(Long value) {
            addCriterion("parent_organization_id <>", value, "parentOrganizationId");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationIdGreaterThan(Long value) {
            addCriterion("parent_organization_id >", value, "parentOrganizationId");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_organization_id >=", value, "parentOrganizationId");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationIdLessThan(Long value) {
            addCriterion("parent_organization_id <", value, "parentOrganizationId");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_organization_id <=", value, "parentOrganizationId");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationIdIn(List<Long> values) {
            addCriterion("parent_organization_id in", values, "parentOrganizationId");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationIdNotIn(List<Long> values) {
            addCriterion("parent_organization_id not in", values, "parentOrganizationId");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationIdBetween(Long value1, Long value2) {
            addCriterion("parent_organization_id between", value1, value2, "parentOrganizationId");
            return (Criteria) this;
        }

        public Criteria andParentOrganizationIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_organization_id not between", value1, value2, "parentOrganizationId");
            return (Criteria) this;
        }

        public Criteria andAmountStartIsNull() {
            addCriterion("amount_start is null");
            return (Criteria) this;
        }

        public Criteria andAmountStartIsNotNull() {
            addCriterion("amount_start is not null");
            return (Criteria) this;
        }

        public Criteria andAmountStartEqualTo(BigDecimal value) {
            addCriterion("amount_start =", value, "amountStart");
            return (Criteria) this;
        }

        public Criteria andAmountStartNotEqualTo(BigDecimal value) {
            addCriterion("amount_start <>", value, "amountStart");
            return (Criteria) this;
        }

        public Criteria andAmountStartGreaterThan(BigDecimal value) {
            addCriterion("amount_start >", value, "amountStart");
            return (Criteria) this;
        }

        public Criteria andAmountStartGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_start >=", value, "amountStart");
            return (Criteria) this;
        }

        public Criteria andAmountStartLessThan(BigDecimal value) {
            addCriterion("amount_start <", value, "amountStart");
            return (Criteria) this;
        }

        public Criteria andAmountStartLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_start <=", value, "amountStart");
            return (Criteria) this;
        }

        public Criteria andAmountStartIn(List<BigDecimal> values) {
            addCriterion("amount_start in", values, "amountStart");
            return (Criteria) this;
        }

        public Criteria andAmountStartNotIn(List<BigDecimal> values) {
            addCriterion("amount_start not in", values, "amountStart");
            return (Criteria) this;
        }

        public Criteria andAmountStartBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_start between", value1, value2, "amountStart");
            return (Criteria) this;
        }

        public Criteria andAmountStartNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_start not between", value1, value2, "amountStart");
            return (Criteria) this;
        }

        public Criteria andAmountEndIsNull() {
            addCriterion("amount_end is null");
            return (Criteria) this;
        }

        public Criteria andAmountEndIsNotNull() {
            addCriterion("amount_end is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEndEqualTo(BigDecimal value) {
            addCriterion("amount_end =", value, "amountEnd");
            return (Criteria) this;
        }

        public Criteria andAmountEndNotEqualTo(BigDecimal value) {
            addCriterion("amount_end <>", value, "amountEnd");
            return (Criteria) this;
        }

        public Criteria andAmountEndGreaterThan(BigDecimal value) {
            addCriterion("amount_end >", value, "amountEnd");
            return (Criteria) this;
        }

        public Criteria andAmountEndGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_end >=", value, "amountEnd");
            return (Criteria) this;
        }

        public Criteria andAmountEndLessThan(BigDecimal value) {
            addCriterion("amount_end <", value, "amountEnd");
            return (Criteria) this;
        }

        public Criteria andAmountEndLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_end <=", value, "amountEnd");
            return (Criteria) this;
        }

        public Criteria andAmountEndIn(List<BigDecimal> values) {
            addCriterion("amount_end in", values, "amountEnd");
            return (Criteria) this;
        }

        public Criteria andAmountEndNotIn(List<BigDecimal> values) {
            addCriterion("amount_end not in", values, "amountEnd");
            return (Criteria) this;
        }

        public Criteria andAmountEndBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_end between", value1, value2, "amountEnd");
            return (Criteria) this;
        }

        public Criteria andAmountEndNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_end not between", value1, value2, "amountEnd");
            return (Criteria) this;
        }

        public Criteria andInterestRateStartIsNull() {
            addCriterion("interest_rate_start is null");
            return (Criteria) this;
        }

        public Criteria andInterestRateStartIsNotNull() {
            addCriterion("interest_rate_start is not null");
            return (Criteria) this;
        }

        public Criteria andInterestRateStartEqualTo(BigDecimal value) {
            addCriterion("interest_rate_start =", value, "interestRateStart");
            return (Criteria) this;
        }

        public Criteria andInterestRateStartNotEqualTo(BigDecimal value) {
            addCriterion("interest_rate_start <>", value, "interestRateStart");
            return (Criteria) this;
        }

        public Criteria andInterestRateStartGreaterThan(BigDecimal value) {
            addCriterion("interest_rate_start >", value, "interestRateStart");
            return (Criteria) this;
        }

        public Criteria andInterestRateStartGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_rate_start >=", value, "interestRateStart");
            return (Criteria) this;
        }

        public Criteria andInterestRateStartLessThan(BigDecimal value) {
            addCriterion("interest_rate_start <", value, "interestRateStart");
            return (Criteria) this;
        }

        public Criteria andInterestRateStartLessThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_rate_start <=", value, "interestRateStart");
            return (Criteria) this;
        }

        public Criteria andInterestRateStartIn(List<BigDecimal> values) {
            addCriterion("interest_rate_start in", values, "interestRateStart");
            return (Criteria) this;
        }

        public Criteria andInterestRateStartNotIn(List<BigDecimal> values) {
            addCriterion("interest_rate_start not in", values, "interestRateStart");
            return (Criteria) this;
        }

        public Criteria andInterestRateStartBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_rate_start between", value1, value2, "interestRateStart");
            return (Criteria) this;
        }

        public Criteria andInterestRateStartNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_rate_start not between", value1, value2, "interestRateStart");
            return (Criteria) this;
        }

        public Criteria andInterestRateEndIsNull() {
            addCriterion("interest_rate_end is null");
            return (Criteria) this;
        }

        public Criteria andInterestRateEndIsNotNull() {
            addCriterion("interest_rate_end is not null");
            return (Criteria) this;
        }

        public Criteria andInterestRateEndEqualTo(BigDecimal value) {
            addCriterion("interest_rate_end =", value, "interestRateEnd");
            return (Criteria) this;
        }

        public Criteria andInterestRateEndNotEqualTo(BigDecimal value) {
            addCriterion("interest_rate_end <>", value, "interestRateEnd");
            return (Criteria) this;
        }

        public Criteria andInterestRateEndGreaterThan(BigDecimal value) {
            addCriterion("interest_rate_end >", value, "interestRateEnd");
            return (Criteria) this;
        }

        public Criteria andInterestRateEndGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_rate_end >=", value, "interestRateEnd");
            return (Criteria) this;
        }

        public Criteria andInterestRateEndLessThan(BigDecimal value) {
            addCriterion("interest_rate_end <", value, "interestRateEnd");
            return (Criteria) this;
        }

        public Criteria andInterestRateEndLessThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_rate_end <=", value, "interestRateEnd");
            return (Criteria) this;
        }

        public Criteria andInterestRateEndIn(List<BigDecimal> values) {
            addCriterion("interest_rate_end in", values, "interestRateEnd");
            return (Criteria) this;
        }

        public Criteria andInterestRateEndNotIn(List<BigDecimal> values) {
            addCriterion("interest_rate_end not in", values, "interestRateEnd");
            return (Criteria) this;
        }

        public Criteria andInterestRateEndBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_rate_end between", value1, value2, "interestRateEnd");
            return (Criteria) this;
        }

        public Criteria andInterestRateEndNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_rate_end not between", value1, value2, "interestRateEnd");
            return (Criteria) this;
        }

        public Criteria andLimitStartIsNull() {
            addCriterion("limit_start is null");
            return (Criteria) this;
        }

        public Criteria andLimitStartIsNotNull() {
            addCriterion("limit_start is not null");
            return (Criteria) this;
        }

        public Criteria andLimitStartEqualTo(Integer value) {
            addCriterion("limit_start =", value, "limitStart");
            return (Criteria) this;
        }

        public Criteria andLimitStartNotEqualTo(Integer value) {
            addCriterion("limit_start <>", value, "limitStart");
            return (Criteria) this;
        }

        public Criteria andLimitStartGreaterThan(Integer value) {
            addCriterion("limit_start >", value, "limitStart");
            return (Criteria) this;
        }

        public Criteria andLimitStartGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_start >=", value, "limitStart");
            return (Criteria) this;
        }

        public Criteria andLimitStartLessThan(Integer value) {
            addCriterion("limit_start <", value, "limitStart");
            return (Criteria) this;
        }

        public Criteria andLimitStartLessThanOrEqualTo(Integer value) {
            addCriterion("limit_start <=", value, "limitStart");
            return (Criteria) this;
        }

        public Criteria andLimitStartIn(List<Integer> values) {
            addCriterion("limit_start in", values, "limitStart");
            return (Criteria) this;
        }

        public Criteria andLimitStartNotIn(List<Integer> values) {
            addCriterion("limit_start not in", values, "limitStart");
            return (Criteria) this;
        }

        public Criteria andLimitStartBetween(Integer value1, Integer value2) {
            addCriterion("limit_start between", value1, value2, "limitStart");
            return (Criteria) this;
        }

        public Criteria andLimitStartNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_start not between", value1, value2, "limitStart");
            return (Criteria) this;
        }

        public Criteria andLimitEndIsNull() {
            addCriterion("limit_end is null");
            return (Criteria) this;
        }

        public Criteria andLimitEndIsNotNull() {
            addCriterion("limit_end is not null");
            return (Criteria) this;
        }

        public Criteria andLimitEndEqualTo(Integer value) {
            addCriterion("limit_end =", value, "limitEnd");
            return (Criteria) this;
        }

        public Criteria andLimitEndNotEqualTo(Integer value) {
            addCriterion("limit_end <>", value, "limitEnd");
            return (Criteria) this;
        }

        public Criteria andLimitEndGreaterThan(Integer value) {
            addCriterion("limit_end >", value, "limitEnd");
            return (Criteria) this;
        }

        public Criteria andLimitEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_end >=", value, "limitEnd");
            return (Criteria) this;
        }

        public Criteria andLimitEndLessThan(Integer value) {
            addCriterion("limit_end <", value, "limitEnd");
            return (Criteria) this;
        }

        public Criteria andLimitEndLessThanOrEqualTo(Integer value) {
            addCriterion("limit_end <=", value, "limitEnd");
            return (Criteria) this;
        }

        public Criteria andLimitEndIn(List<Integer> values) {
            addCriterion("limit_end in", values, "limitEnd");
            return (Criteria) this;
        }

        public Criteria andLimitEndNotIn(List<Integer> values) {
            addCriterion("limit_end not in", values, "limitEnd");
            return (Criteria) this;
        }

        public Criteria andLimitEndBetween(Integer value1, Integer value2) {
            addCriterion("limit_end between", value1, value2, "limitEnd");
            return (Criteria) this;
        }

        public Criteria andLimitEndNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_end not between", value1, value2, "limitEnd");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIsNull() {
            addCriterion("credit_level is null");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIsNotNull() {
            addCriterion("credit_level is not null");
            return (Criteria) this;
        }

        public Criteria andCreditLevelEqualTo(String value) {
            addCriterion("credit_level =", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelNotEqualTo(String value) {
            addCriterion("credit_level <>", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelGreaterThan(String value) {
            addCriterion("credit_level >", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelGreaterThanOrEqualTo(String value) {
            addCriterion("credit_level >=", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelLessThan(String value) {
            addCriterion("credit_level <", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelLessThanOrEqualTo(String value) {
            addCriterion("credit_level <=", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelLike(String value) {
            addCriterion("credit_level like", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelNotLike(String value) {
            addCriterion("credit_level not like", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIn(List<String> values) {
            addCriterion("credit_level in", values, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelNotIn(List<String> values) {
            addCriterion("credit_level not in", values, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelBetween(String value1, String value2) {
            addCriterion("credit_level between", value1, value2, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelNotBetween(String value1, String value2) {
            addCriterion("credit_level not between", value1, value2, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andImgIsNull() {
            addCriterion("img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("img not between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andBeMainHotIsNull() {
            addCriterion("be_main_hot is null");
            return (Criteria) this;
        }

        public Criteria andBeMainHotIsNotNull() {
            addCriterion("be_main_hot is not null");
            return (Criteria) this;
        }

        public Criteria andBeMainHotEqualTo(Integer value) {
            addCriterion("be_main_hot =", value, "beMainHot");
            return (Criteria) this;
        }

        public Criteria andBeMainHotNotEqualTo(Integer value) {
            addCriterion("be_main_hot <>", value, "beMainHot");
            return (Criteria) this;
        }

        public Criteria andBeMainHotGreaterThan(Integer value) {
            addCriterion("be_main_hot >", value, "beMainHot");
            return (Criteria) this;
        }

        public Criteria andBeMainHotGreaterThanOrEqualTo(Integer value) {
            addCriterion("be_main_hot >=", value, "beMainHot");
            return (Criteria) this;
        }

        public Criteria andBeMainHotLessThan(Integer value) {
            addCriterion("be_main_hot <", value, "beMainHot");
            return (Criteria) this;
        }

        public Criteria andBeMainHotLessThanOrEqualTo(Integer value) {
            addCriterion("be_main_hot <=", value, "beMainHot");
            return (Criteria) this;
        }

        public Criteria andBeMainHotIn(List<Integer> values) {
            addCriterion("be_main_hot in", values, "beMainHot");
            return (Criteria) this;
        }

        public Criteria andBeMainHotNotIn(List<Integer> values) {
            addCriterion("be_main_hot not in", values, "beMainHot");
            return (Criteria) this;
        }

        public Criteria andBeMainHotBetween(Integer value1, Integer value2) {
            addCriterion("be_main_hot between", value1, value2, "beMainHot");
            return (Criteria) this;
        }

        public Criteria andBeMainHotNotBetween(Integer value1, Integer value2) {
            addCriterion("be_main_hot not between", value1, value2, "beMainHot");
            return (Criteria) this;
        }

        public Criteria andBeHotIsNull() {
            addCriterion("be_hot is null");
            return (Criteria) this;
        }

        public Criteria andBeHotIsNotNull() {
            addCriterion("be_hot is not null");
            return (Criteria) this;
        }

        public Criteria andBeHotEqualTo(Integer value) {
            addCriterion("be_hot =", value, "beHot");
            return (Criteria) this;
        }

        public Criteria andBeHotNotEqualTo(Integer value) {
            addCriterion("be_hot <>", value, "beHot");
            return (Criteria) this;
        }

        public Criteria andBeHotGreaterThan(Integer value) {
            addCriterion("be_hot >", value, "beHot");
            return (Criteria) this;
        }

        public Criteria andBeHotGreaterThanOrEqualTo(Integer value) {
            addCriterion("be_hot >=", value, "beHot");
            return (Criteria) this;
        }

        public Criteria andBeHotLessThan(Integer value) {
            addCriterion("be_hot <", value, "beHot");
            return (Criteria) this;
        }

        public Criteria andBeHotLessThanOrEqualTo(Integer value) {
            addCriterion("be_hot <=", value, "beHot");
            return (Criteria) this;
        }

        public Criteria andBeHotIn(List<Integer> values) {
            addCriterion("be_hot in", values, "beHot");
            return (Criteria) this;
        }

        public Criteria andBeHotNotIn(List<Integer> values) {
            addCriterion("be_hot not in", values, "beHot");
            return (Criteria) this;
        }

        public Criteria andBeHotBetween(Integer value1, Integer value2) {
            addCriterion("be_hot between", value1, value2, "beHot");
            return (Criteria) this;
        }

        public Criteria andBeHotNotBetween(Integer value1, Integer value2) {
            addCriterion("be_hot not between", value1, value2, "beHot");
            return (Criteria) this;
        }

        public Criteria andBeMainHotSortIsNull() {
            addCriterion("be_main_hot_sort is null");
            return (Criteria) this;
        }

        public Criteria andBeMainHotSortIsNotNull() {
            addCriterion("be_main_hot_sort is not null");
            return (Criteria) this;
        }

        public Criteria andBeMainHotSortEqualTo(Integer value) {
            addCriterion("be_main_hot_sort =", value, "beMainHotSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotSortNotEqualTo(Integer value) {
            addCriterion("be_main_hot_sort <>", value, "beMainHotSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotSortGreaterThan(Integer value) {
            addCriterion("be_main_hot_sort >", value, "beMainHotSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("be_main_hot_sort >=", value, "beMainHotSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotSortLessThan(Integer value) {
            addCriterion("be_main_hot_sort <", value, "beMainHotSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotSortLessThanOrEqualTo(Integer value) {
            addCriterion("be_main_hot_sort <=", value, "beMainHotSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotSortIn(List<Integer> values) {
            addCriterion("be_main_hot_sort in", values, "beMainHotSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotSortNotIn(List<Integer> values) {
            addCriterion("be_main_hot_sort not in", values, "beMainHotSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotSortBetween(Integer value1, Integer value2) {
            addCriterion("be_main_hot_sort between", value1, value2, "beMainHotSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotSortNotBetween(Integer value1, Integer value2) {
            addCriterion("be_main_hot_sort not between", value1, value2, "beMainHotSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppIsNull() {
            addCriterion("be_main_hot_app is null");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppIsNotNull() {
            addCriterion("be_main_hot_app is not null");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppEqualTo(Integer value) {
            addCriterion("be_main_hot_app =", value, "beMainHotApp");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppNotEqualTo(Integer value) {
            addCriterion("be_main_hot_app <>", value, "beMainHotApp");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppGreaterThan(Integer value) {
            addCriterion("be_main_hot_app >", value, "beMainHotApp");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppGreaterThanOrEqualTo(Integer value) {
            addCriterion("be_main_hot_app >=", value, "beMainHotApp");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppLessThan(Integer value) {
            addCriterion("be_main_hot_app <", value, "beMainHotApp");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppLessThanOrEqualTo(Integer value) {
            addCriterion("be_main_hot_app <=", value, "beMainHotApp");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppIn(List<Integer> values) {
            addCriterion("be_main_hot_app in", values, "beMainHotApp");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppNotIn(List<Integer> values) {
            addCriterion("be_main_hot_app not in", values, "beMainHotApp");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppBetween(Integer value1, Integer value2) {
            addCriterion("be_main_hot_app between", value1, value2, "beMainHotApp");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppNotBetween(Integer value1, Integer value2) {
            addCriterion("be_main_hot_app not between", value1, value2, "beMainHotApp");
            return (Criteria) this;
        }

        public Criteria andBeHotAppIsNull() {
            addCriterion("be_hot_app is null");
            return (Criteria) this;
        }

        public Criteria andBeHotAppIsNotNull() {
            addCriterion("be_hot_app is not null");
            return (Criteria) this;
        }

        public Criteria andBeHotAppEqualTo(Integer value) {
            addCriterion("be_hot_app =", value, "beHotApp");
            return (Criteria) this;
        }

        public Criteria andBeHotAppNotEqualTo(Integer value) {
            addCriterion("be_hot_app <>", value, "beHotApp");
            return (Criteria) this;
        }

        public Criteria andBeHotAppGreaterThan(Integer value) {
            addCriterion("be_hot_app >", value, "beHotApp");
            return (Criteria) this;
        }

        public Criteria andBeHotAppGreaterThanOrEqualTo(Integer value) {
            addCriterion("be_hot_app >=", value, "beHotApp");
            return (Criteria) this;
        }

        public Criteria andBeHotAppLessThan(Integer value) {
            addCriterion("be_hot_app <", value, "beHotApp");
            return (Criteria) this;
        }

        public Criteria andBeHotAppLessThanOrEqualTo(Integer value) {
            addCriterion("be_hot_app <=", value, "beHotApp");
            return (Criteria) this;
        }

        public Criteria andBeHotAppIn(List<Integer> values) {
            addCriterion("be_hot_app in", values, "beHotApp");
            return (Criteria) this;
        }

        public Criteria andBeHotAppNotIn(List<Integer> values) {
            addCriterion("be_hot_app not in", values, "beHotApp");
            return (Criteria) this;
        }

        public Criteria andBeHotAppBetween(Integer value1, Integer value2) {
            addCriterion("be_hot_app between", value1, value2, "beHotApp");
            return (Criteria) this;
        }

        public Criteria andBeHotAppNotBetween(Integer value1, Integer value2) {
            addCriterion("be_hot_app not between", value1, value2, "beHotApp");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppSortIsNull() {
            addCriterion("be_main_hot_app_sort is null");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppSortIsNotNull() {
            addCriterion("be_main_hot_app_sort is not null");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppSortEqualTo(Integer value) {
            addCriterion("be_main_hot_app_sort =", value, "beMainHotAppSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppSortNotEqualTo(Integer value) {
            addCriterion("be_main_hot_app_sort <>", value, "beMainHotAppSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppSortGreaterThan(Integer value) {
            addCriterion("be_main_hot_app_sort >", value, "beMainHotAppSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("be_main_hot_app_sort >=", value, "beMainHotAppSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppSortLessThan(Integer value) {
            addCriterion("be_main_hot_app_sort <", value, "beMainHotAppSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppSortLessThanOrEqualTo(Integer value) {
            addCriterion("be_main_hot_app_sort <=", value, "beMainHotAppSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppSortIn(List<Integer> values) {
            addCriterion("be_main_hot_app_sort in", values, "beMainHotAppSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppSortNotIn(List<Integer> values) {
            addCriterion("be_main_hot_app_sort not in", values, "beMainHotAppSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppSortBetween(Integer value1, Integer value2) {
            addCriterion("be_main_hot_app_sort between", value1, value2, "beMainHotAppSort");
            return (Criteria) this;
        }

        public Criteria andBeMainHotAppSortNotBetween(Integer value1, Integer value2) {
            addCriterion("be_main_hot_app_sort not between", value1, value2, "beMainHotAppSort");
            return (Criteria) this;
        }

        public Criteria andApplyRateIsNull() {
            addCriterion("apply_rate is null");
            return (Criteria) this;
        }

        public Criteria andApplyRateIsNotNull() {
            addCriterion("apply_rate is not null");
            return (Criteria) this;
        }

        public Criteria andApplyRateEqualTo(String value) {
            addCriterion("apply_rate =", value, "applyRate");
            return (Criteria) this;
        }

        public Criteria andApplyRateNotEqualTo(String value) {
            addCriterion("apply_rate <>", value, "applyRate");
            return (Criteria) this;
        }

        public Criteria andApplyRateGreaterThan(String value) {
            addCriterion("apply_rate >", value, "applyRate");
            return (Criteria) this;
        }

        public Criteria andApplyRateGreaterThanOrEqualTo(String value) {
            addCriterion("apply_rate >=", value, "applyRate");
            return (Criteria) this;
        }

        public Criteria andApplyRateLessThan(String value) {
            addCriterion("apply_rate <", value, "applyRate");
            return (Criteria) this;
        }

        public Criteria andApplyRateLessThanOrEqualTo(String value) {
            addCriterion("apply_rate <=", value, "applyRate");
            return (Criteria) this;
        }

        public Criteria andApplyRateLike(String value) {
            addCriterion("apply_rate like", value, "applyRate");
            return (Criteria) this;
        }

        public Criteria andApplyRateNotLike(String value) {
            addCriterion("apply_rate not like", value, "applyRate");
            return (Criteria) this;
        }

        public Criteria andApplyRateIn(List<String> values) {
            addCriterion("apply_rate in", values, "applyRate");
            return (Criteria) this;
        }

        public Criteria andApplyRateNotIn(List<String> values) {
            addCriterion("apply_rate not in", values, "applyRate");
            return (Criteria) this;
        }

        public Criteria andApplyRateBetween(String value1, String value2) {
            addCriterion("apply_rate between", value1, value2, "applyRate");
            return (Criteria) this;
        }

        public Criteria andApplyRateNotBetween(String value1, String value2) {
            addCriterion("apply_rate not between", value1, value2, "applyRate");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNull() {
            addCriterion("total_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNotNull() {
            addCriterion("total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountEqualTo(BigDecimal value) {
            addCriterion("total_amount =", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotEqualTo(BigDecimal value) {
            addCriterion("total_amount <>", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThan(BigDecimal value) {
            addCriterion("total_amount >", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amount >=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThan(BigDecimal value) {
            addCriterion("total_amount <", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amount <=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIn(List<BigDecimal> values) {
            addCriterion("total_amount in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotIn(List<BigDecimal> values) {
            addCriterion("total_amount not in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amount between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amount not between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andApplyRateTrueIsNull() {
            addCriterion("apply_rate_true is null");
            return (Criteria) this;
        }

        public Criteria andApplyRateTrueIsNotNull() {
            addCriterion("apply_rate_true is not null");
            return (Criteria) this;
        }

        public Criteria andApplyRateTrueEqualTo(String value) {
            addCriterion("apply_rate_true =", value, "applyRateTrue");
            return (Criteria) this;
        }

        public Criteria andApplyRateTrueNotEqualTo(String value) {
            addCriterion("apply_rate_true <>", value, "applyRateTrue");
            return (Criteria) this;
        }

        public Criteria andApplyRateTrueGreaterThan(String value) {
            addCriterion("apply_rate_true >", value, "applyRateTrue");
            return (Criteria) this;
        }

        public Criteria andApplyRateTrueGreaterThanOrEqualTo(String value) {
            addCriterion("apply_rate_true >=", value, "applyRateTrue");
            return (Criteria) this;
        }

        public Criteria andApplyRateTrueLessThan(String value) {
            addCriterion("apply_rate_true <", value, "applyRateTrue");
            return (Criteria) this;
        }

        public Criteria andApplyRateTrueLessThanOrEqualTo(String value) {
            addCriterion("apply_rate_true <=", value, "applyRateTrue");
            return (Criteria) this;
        }

        public Criteria andApplyRateTrueLike(String value) {
            addCriterion("apply_rate_true like", value, "applyRateTrue");
            return (Criteria) this;
        }

        public Criteria andApplyRateTrueNotLike(String value) {
            addCriterion("apply_rate_true not like", value, "applyRateTrue");
            return (Criteria) this;
        }

        public Criteria andApplyRateTrueIn(List<String> values) {
            addCriterion("apply_rate_true in", values, "applyRateTrue");
            return (Criteria) this;
        }

        public Criteria andApplyRateTrueNotIn(List<String> values) {
            addCriterion("apply_rate_true not in", values, "applyRateTrue");
            return (Criteria) this;
        }

        public Criteria andApplyRateTrueBetween(String value1, String value2) {
            addCriterion("apply_rate_true between", value1, value2, "applyRateTrue");
            return (Criteria) this;
        }

        public Criteria andApplyRateTrueNotBetween(String value1, String value2) {
            addCriterion("apply_rate_true not between", value1, value2, "applyRateTrue");
            return (Criteria) this;
        }

        public Criteria andTotalAmountTrueIsNull() {
            addCriterion("total_amount_true is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountTrueIsNotNull() {
            addCriterion("total_amount_true is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountTrueEqualTo(BigDecimal value) {
            addCriterion("total_amount_true =", value, "totalAmountTrue");
            return (Criteria) this;
        }

        public Criteria andTotalAmountTrueNotEqualTo(BigDecimal value) {
            addCriterion("total_amount_true <>", value, "totalAmountTrue");
            return (Criteria) this;
        }

        public Criteria andTotalAmountTrueGreaterThan(BigDecimal value) {
            addCriterion("total_amount_true >", value, "totalAmountTrue");
            return (Criteria) this;
        }

        public Criteria andTotalAmountTrueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amount_true >=", value, "totalAmountTrue");
            return (Criteria) this;
        }

        public Criteria andTotalAmountTrueLessThan(BigDecimal value) {
            addCriterion("total_amount_true <", value, "totalAmountTrue");
            return (Criteria) this;
        }

        public Criteria andTotalAmountTrueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amount_true <=", value, "totalAmountTrue");
            return (Criteria) this;
        }

        public Criteria andTotalAmountTrueIn(List<BigDecimal> values) {
            addCriterion("total_amount_true in", values, "totalAmountTrue");
            return (Criteria) this;
        }

        public Criteria andTotalAmountTrueNotIn(List<BigDecimal> values) {
            addCriterion("total_amount_true not in", values, "totalAmountTrue");
            return (Criteria) this;
        }

        public Criteria andTotalAmountTrueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amount_true between", value1, value2, "totalAmountTrue");
            return (Criteria) this;
        }

        public Criteria andTotalAmountTrueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amount_true not between", value1, value2, "totalAmountTrue");
            return (Criteria) this;
        }
        
        public Criteria andBeSpecialIsNull() {
            addCriterion("be_special is null");
            return (Criteria) this;
        }

        public Criteria andBeSpecialIsNotNull() {
            addCriterion("be_special is not null");
            return (Criteria) this;
        }

        public Criteria andBeSpecialEqualTo(Integer value) {
            addCriterion("be_special =", value, "beSpecial");
            return (Criteria) this;
        }

        public Criteria andBeSpecialNotEqualTo(Integer value) {
            addCriterion("be_special <>", value, "beSpecial");
            return (Criteria) this;
        }

        public Criteria andBeSpecialGreaterThan(Integer value) {
            addCriterion("be_special >", value, "beSpecial");
            return (Criteria) this;
        }

        public Criteria andBeSpecialGreaterThanOrEqualTo(Integer value) {
            addCriterion("be_special >=", value, "beSpecial");
            return (Criteria) this;
        }

        public Criteria andBeSpecialLessThan(Integer value) {
            addCriterion("be_special <", value, "beSpecial");
            return (Criteria) this;
        }

        public Criteria andBeSpecialLessThanOrEqualTo(Integer value) {
            addCriterion("be_special <=", value, "beSpecial");
            return (Criteria) this;
        }

        public Criteria andBeSpecialIn(List<Integer> values) {
            addCriterion("be_special in", values, "beSpecial");
            return (Criteria) this;
        }

        public Criteria andBeSpecialNotIn(List<Integer> values) {
            addCriterion("be_special not in", values, "beSpecial");
            return (Criteria) this;
        }

        public Criteria andBeSpecialBetween(Integer value1, Integer value2) {
            addCriterion("be_special between", value1, value2, "beSpecial");
            return (Criteria) this;
        }

        public Criteria andBeSpecialNotBetween(Integer value1, Integer value2) {
            addCriterion("be_special not between", value1, value2, "beSpecial");
            return (Criteria) this;
        }

        public Criteria andCreditModelIsNull() {
            addCriterion("credit_model is null");
            return (Criteria) this;
        }

        public Criteria andCreditModelIsNotNull() {
            addCriterion("credit_model is not null");
            return (Criteria) this;
        }

        public Criteria andCreditModelEqualTo(String value) {
            addCriterion("credit_model =", value, "creditModel");
            return (Criteria) this;
        }

        public Criteria andCreditModelNotEqualTo(String value) {
            addCriterion("credit_model <>", value, "creditModel");
            return (Criteria) this;
        }

        public Criteria andCreditModelGreaterThan(String value) {
            addCriterion("credit_model >", value, "creditModel");
            return (Criteria) this;
        }

        public Criteria andCreditModelGreaterThanOrEqualTo(String value) {
            addCriterion("credit_model >=", value, "creditModel");
            return (Criteria) this;
        }

        public Criteria andCreditModelLessThan(String value) {
            addCriterion("credit_model <", value, "creditModel");
            return (Criteria) this;
        }

        public Criteria andCreditModelLessThanOrEqualTo(String value) {
            addCriterion("credit_model <=", value, "creditModel");
            return (Criteria) this;
        }

        public Criteria andCreditModelLike(String value) {
            addCriterion("credit_model like", value, "creditModel");
            return (Criteria) this;
        }

        public Criteria andCreditModelNotLike(String value) {
            addCriterion("credit_model not like", value, "creditModel");
            return (Criteria) this;
        }

        public Criteria andCreditModelIn(List<String> values) {
            addCriterion("credit_model in", values, "creditModel");
            return (Criteria) this;
        }

        public Criteria andCreditModelNotIn(List<String> values) {
            addCriterion("credit_model not in", values, "creditModel");
            return (Criteria) this;
        }

        public Criteria andCreditModelBetween(String value1, String value2) {
            addCriterion("credit_model between", value1, value2, "creditModel");
            return (Criteria) this;
        }

        public Criteria andCreditModelNotBetween(String value1, String value2) {
            addCriterion("credit_model not between", value1, value2, "creditModel");
            return (Criteria) this;
        }

        public Criteria andRatingModelIsNull() {
            addCriterion("rating_model is null");
            return (Criteria) this;
        }

        public Criteria andRatingModelIsNotNull() {
            addCriterion("rating_model is not null");
            return (Criteria) this;
        }

        public Criteria andRatingModelEqualTo(String value) {
            addCriterion("rating_model =", value, "ratingModel");
            return (Criteria) this;
        }

        public Criteria andRatingModelNotEqualTo(String value) {
            addCriterion("rating_model <>", value, "ratingModel");
            return (Criteria) this;
        }

        public Criteria andRatingModelGreaterThan(String value) {
            addCriterion("rating_model >", value, "ratingModel");
            return (Criteria) this;
        }

        public Criteria andRatingModelGreaterThanOrEqualTo(String value) {
            addCriterion("rating_model >=", value, "ratingModel");
            return (Criteria) this;
        }

        public Criteria andRatingModelLessThan(String value) {
            addCriterion("rating_model <", value, "ratingModel");
            return (Criteria) this;
        }

        public Criteria andRatingModelLessThanOrEqualTo(String value) {
            addCriterion("rating_model <=", value, "ratingModel");
            return (Criteria) this;
        }

        public Criteria andRatingModelLike(String value) {
            addCriterion("rating_model like", value, "ratingModel");
            return (Criteria) this;
        }

        public Criteria andRatingModelNotLike(String value) {
            addCriterion("rating_model not like", value, "ratingModel");
            return (Criteria) this;
        }

        public Criteria andRatingModelIn(List<String> values) {
            addCriterion("rating_model in", values, "ratingModel");
            return (Criteria) this;
        }

        public Criteria andRatingModelNotIn(List<String> values) {
            addCriterion("rating_model not in", values, "ratingModel");
            return (Criteria) this;
        }

        public Criteria andRatingModelBetween(String value1, String value2) {
            addCriterion("rating_model between", value1, value2, "ratingModel");
            return (Criteria) this;
        }

        public Criteria andRatingModelNotBetween(String value1, String value2) {
            addCriterion("rating_model not between", value1, value2, "ratingModel");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPublishDateIsNull() {
            addCriterion("publish_date is null");
            return (Criteria) this;
        }

        public Criteria andPublishDateIsNotNull() {
            addCriterion("publish_date is not null");
            return (Criteria) this;
        }

        public Criteria andPublishDateEqualTo(Date value) {
            addCriterion("publish_date =", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotEqualTo(Date value) {
            addCriterion("publish_date <>", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateGreaterThan(Date value) {
            addCriterion("publish_date >", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateGreaterThanOrEqualTo(Date value) {
            addCriterion("publish_date >=", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLessThan(Date value) {
            addCriterion("publish_date <", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLessThanOrEqualTo(Date value) {
            addCriterion("publish_date <=", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateIn(List<Date> values) {
            addCriterion("publish_date in", values, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotIn(List<Date> values) {
            addCriterion("publish_date not in", values, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateBetween(Date value1, Date value2) {
            addCriterion("publish_date between", value1, value2, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotBetween(Date value1, Date value2) {
            addCriterion("publish_date not between", value1, value2, "publishDate");
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