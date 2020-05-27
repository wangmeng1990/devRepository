package com.ark.hngxt.product.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecommendOrganizationCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RecommendOrganizationCriteria() {
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

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
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

        public Criteria andLogoIsNull() {
            addCriterion("logo is null");
            return (Criteria) this;
        }

        public Criteria andLogoIsNotNull() {
            addCriterion("logo is not null");
            return (Criteria) this;
        }

        public Criteria andLogoEqualTo(String value) {
            addCriterion("logo =", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotEqualTo(String value) {
            addCriterion("logo <>", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThan(String value) {
            addCriterion("logo >", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThanOrEqualTo(String value) {
            addCriterion("logo >=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThan(String value) {
            addCriterion("logo <", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThanOrEqualTo(String value) {
            addCriterion("logo <=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLike(String value) {
            addCriterion("logo like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotLike(String value) {
            addCriterion("logo not like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoIn(List<String> values) {
            addCriterion("logo in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotIn(List<String> values) {
            addCriterion("logo not in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoBetween(String value1, String value2) {
            addCriterion("logo between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotBetween(String value1, String value2) {
            addCriterion("logo not between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLoanCountIsNull() {
            addCriterion("loan_count is null");
            return (Criteria) this;
        }

        public Criteria andLoanCountIsNotNull() {
            addCriterion("loan_count is not null");
            return (Criteria) this;
        }

        public Criteria andLoanCountEqualTo(Integer value) {
            addCriterion("loan_count =", value, "loanCount");
            return (Criteria) this;
        }

        public Criteria andLoanCountNotEqualTo(Integer value) {
            addCriterion("loan_count <>", value, "loanCount");
            return (Criteria) this;
        }

        public Criteria andLoanCountGreaterThan(Integer value) {
            addCriterion("loan_count >", value, "loanCount");
            return (Criteria) this;
        }

        public Criteria andLoanCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("loan_count >=", value, "loanCount");
            return (Criteria) this;
        }

        public Criteria andLoanCountLessThan(Integer value) {
            addCriterion("loan_count <", value, "loanCount");
            return (Criteria) this;
        }

        public Criteria andLoanCountLessThanOrEqualTo(Integer value) {
            addCriterion("loan_count <=", value, "loanCount");
            return (Criteria) this;
        }

        public Criteria andLoanCountIn(List<Integer> values) {
            addCriterion("loan_count in", values, "loanCount");
            return (Criteria) this;
        }

        public Criteria andLoanCountNotIn(List<Integer> values) {
            addCriterion("loan_count not in", values, "loanCount");
            return (Criteria) this;
        }

        public Criteria andLoanCountBetween(Integer value1, Integer value2) {
            addCriterion("loan_count between", value1, value2, "loanCount");
            return (Criteria) this;
        }

        public Criteria andLoanCountNotBetween(Integer value1, Integer value2) {
            addCriterion("loan_count not between", value1, value2, "loanCount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountIsNull() {
            addCriterion("loan_amount is null");
            return (Criteria) this;
        }

        public Criteria andLoanAmountIsNotNull() {
            addCriterion("loan_amount is not null");
            return (Criteria) this;
        }

        public Criteria andLoanAmountEqualTo(Long value) {
            addCriterion("loan_amount =", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountNotEqualTo(Long value) {
            addCriterion("loan_amount <>", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountGreaterThan(Long value) {
            addCriterion("loan_amount >", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("loan_amount >=", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountLessThan(Long value) {
            addCriterion("loan_amount <", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountLessThanOrEqualTo(Long value) {
            addCriterion("loan_amount <=", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountIn(List<Long> values) {
            addCriterion("loan_amount in", values, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountNotIn(List<Long> values) {
            addCriterion("loan_amount not in", values, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountBetween(Long value1, Long value2) {
            addCriterion("loan_amount between", value1, value2, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountNotBetween(Long value1, Long value2) {
            addCriterion("loan_amount not between", value1, value2, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andButtCountIsNull() {
            addCriterion("butt_count is null");
            return (Criteria) this;
        }

        public Criteria andButtCountIsNotNull() {
            addCriterion("butt_count is not null");
            return (Criteria) this;
        }

        public Criteria andButtCountEqualTo(Integer value) {
            addCriterion("butt_count =", value, "buttCount");
            return (Criteria) this;
        }

        public Criteria andButtCountNotEqualTo(Integer value) {
            addCriterion("butt_count <>", value, "buttCount");
            return (Criteria) this;
        }

        public Criteria andButtCountGreaterThan(Integer value) {
            addCriterion("butt_count >", value, "buttCount");
            return (Criteria) this;
        }

        public Criteria andButtCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("butt_count >=", value, "buttCount");
            return (Criteria) this;
        }

        public Criteria andButtCountLessThan(Integer value) {
            addCriterion("butt_count <", value, "buttCount");
            return (Criteria) this;
        }

        public Criteria andButtCountLessThanOrEqualTo(Integer value) {
            addCriterion("butt_count <=", value, "buttCount");
            return (Criteria) this;
        }

        public Criteria andButtCountIn(List<Integer> values) {
            addCriterion("butt_count in", values, "buttCount");
            return (Criteria) this;
        }

        public Criteria andButtCountNotIn(List<Integer> values) {
            addCriterion("butt_count not in", values, "buttCount");
            return (Criteria) this;
        }

        public Criteria andButtCountBetween(Integer value1, Integer value2) {
            addCriterion("butt_count between", value1, value2, "buttCount");
            return (Criteria) this;
        }

        public Criteria andButtCountNotBetween(Integer value1, Integer value2) {
            addCriterion("butt_count not between", value1, value2, "buttCount");
            return (Criteria) this;
        }

        public Criteria andProductCountIsNull() {
            addCriterion("product_count is null");
            return (Criteria) this;
        }

        public Criteria andProductCountIsNotNull() {
            addCriterion("product_count is not null");
            return (Criteria) this;
        }

        public Criteria andProductCountEqualTo(Integer value) {
            addCriterion("product_count =", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotEqualTo(Integer value) {
            addCriterion("product_count <>", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountGreaterThan(Integer value) {
            addCriterion("product_count >", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_count >=", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountLessThan(Integer value) {
            addCriterion("product_count <", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountLessThanOrEqualTo(Integer value) {
            addCriterion("product_count <=", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountIn(List<Integer> values) {
            addCriterion("product_count in", values, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotIn(List<Integer> values) {
            addCriterion("product_count not in", values, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountBetween(Integer value1, Integer value2) {
            addCriterion("product_count between", value1, value2, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotBetween(Integer value1, Integer value2) {
            addCriterion("product_count not between", value1, value2, "productCount");
            return (Criteria) this;
        }

        public Criteria andServiceCountIsNull() {
            addCriterion("service_count is null");
            return (Criteria) this;
        }

        public Criteria andServiceCountIsNotNull() {
            addCriterion("service_count is not null");
            return (Criteria) this;
        }

        public Criteria andServiceCountEqualTo(Integer value) {
            addCriterion("service_count =", value, "serviceCount");
            return (Criteria) this;
        }

        public Criteria andServiceCountNotEqualTo(Integer value) {
            addCriterion("service_count <>", value, "serviceCount");
            return (Criteria) this;
        }

        public Criteria andServiceCountGreaterThan(Integer value) {
            addCriterion("service_count >", value, "serviceCount");
            return (Criteria) this;
        }

        public Criteria andServiceCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("service_count >=", value, "serviceCount");
            return (Criteria) this;
        }

        public Criteria andServiceCountLessThan(Integer value) {
            addCriterion("service_count <", value, "serviceCount");
            return (Criteria) this;
        }

        public Criteria andServiceCountLessThanOrEqualTo(Integer value) {
            addCriterion("service_count <=", value, "serviceCount");
            return (Criteria) this;
        }

        public Criteria andServiceCountIn(List<Integer> values) {
            addCriterion("service_count in", values, "serviceCount");
            return (Criteria) this;
        }

        public Criteria andServiceCountNotIn(List<Integer> values) {
            addCriterion("service_count not in", values, "serviceCount");
            return (Criteria) this;
        }

        public Criteria andServiceCountBetween(Integer value1, Integer value2) {
            addCriterion("service_count between", value1, value2, "serviceCount");
            return (Criteria) this;
        }

        public Criteria andServiceCountNotBetween(Integer value1, Integer value2) {
            addCriterion("service_count not between", value1, value2, "serviceCount");
            return (Criteria) this;
        }

        public Criteria andServiceScoreIsNull() {
            addCriterion("service_score is null");
            return (Criteria) this;
        }

        public Criteria andServiceScoreIsNotNull() {
            addCriterion("service_score is not null");
            return (Criteria) this;
        }

        public Criteria andServiceScoreEqualTo(String value) {
            addCriterion("service_score =", value, "serviceScore");
            return (Criteria) this;
        }

        public Criteria andServiceScoreNotEqualTo(String value) {
            addCriterion("service_score <>", value, "serviceScore");
            return (Criteria) this;
        }

        public Criteria andServiceScoreGreaterThan(String value) {
            addCriterion("service_score >", value, "serviceScore");
            return (Criteria) this;
        }

        public Criteria andServiceScoreGreaterThanOrEqualTo(String value) {
            addCriterion("service_score >=", value, "serviceScore");
            return (Criteria) this;
        }

        public Criteria andServiceScoreLessThan(String value) {
            addCriterion("service_score <", value, "serviceScore");
            return (Criteria) this;
        }

        public Criteria andServiceScoreLessThanOrEqualTo(String value) {
            addCriterion("service_score <=", value, "serviceScore");
            return (Criteria) this;
        }

        public Criteria andServiceScoreLike(String value) {
            addCriterion("service_score like", value, "serviceScore");
            return (Criteria) this;
        }

        public Criteria andServiceScoreNotLike(String value) {
            addCriterion("service_score not like", value, "serviceScore");
            return (Criteria) this;
        }

        public Criteria andServiceScoreIn(List<String> values) {
            addCriterion("service_score in", values, "serviceScore");
            return (Criteria) this;
        }

        public Criteria andServiceScoreNotIn(List<String> values) {
            addCriterion("service_score not in", values, "serviceScore");
            return (Criteria) this;
        }

        public Criteria andServiceScoreBetween(String value1, String value2) {
            addCriterion("service_score between", value1, value2, "serviceScore");
            return (Criteria) this;
        }

        public Criteria andServiceScoreNotBetween(String value1, String value2) {
            addCriterion("service_score not between", value1, value2, "serviceScore");
            return (Criteria) this;
        }

        public Criteria andValuationCountIsNull() {
            addCriterion("valuation_count is null");
            return (Criteria) this;
        }

        public Criteria andValuationCountIsNotNull() {
            addCriterion("valuation_count is not null");
            return (Criteria) this;
        }

        public Criteria andValuationCountEqualTo(Integer value) {
            addCriterion("valuation_count =", value, "valuationCount");
            return (Criteria) this;
        }

        public Criteria andValuationCountNotEqualTo(Integer value) {
            addCriterion("valuation_count <>", value, "valuationCount");
            return (Criteria) this;
        }

        public Criteria andValuationCountGreaterThan(Integer value) {
            addCriterion("valuation_count >", value, "valuationCount");
            return (Criteria) this;
        }

        public Criteria andValuationCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("valuation_count >=", value, "valuationCount");
            return (Criteria) this;
        }

        public Criteria andValuationCountLessThan(Integer value) {
            addCriterion("valuation_count <", value, "valuationCount");
            return (Criteria) this;
        }

        public Criteria andValuationCountLessThanOrEqualTo(Integer value) {
            addCriterion("valuation_count <=", value, "valuationCount");
            return (Criteria) this;
        }

        public Criteria andValuationCountIn(List<Integer> values) {
            addCriterion("valuation_count in", values, "valuationCount");
            return (Criteria) this;
        }

        public Criteria andValuationCountNotIn(List<Integer> values) {
            addCriterion("valuation_count not in", values, "valuationCount");
            return (Criteria) this;
        }

        public Criteria andValuationCountBetween(Integer value1, Integer value2) {
            addCriterion("valuation_count between", value1, value2, "valuationCount");
            return (Criteria) this;
        }

        public Criteria andValuationCountNotBetween(Integer value1, Integer value2) {
            addCriterion("valuation_count not between", value1, value2, "valuationCount");
            return (Criteria) this;
        }

        public Criteria andAverageAcceptanceTimeIsNull() {
            addCriterion("average_acceptance_time is null");
            return (Criteria) this;
        }

        public Criteria andAverageAcceptanceTimeIsNotNull() {
            addCriterion("average_acceptance_time is not null");
            return (Criteria) this;
        }

        public Criteria andAverageAcceptanceTimeEqualTo(String value) {
            addCriterion("average_acceptance_time =", value, "averageAcceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAverageAcceptanceTimeNotEqualTo(String value) {
            addCriterion("average_acceptance_time <>", value, "averageAcceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAverageAcceptanceTimeGreaterThan(String value) {
            addCriterion("average_acceptance_time >", value, "averageAcceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAverageAcceptanceTimeGreaterThanOrEqualTo(String value) {
            addCriterion("average_acceptance_time >=", value, "averageAcceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAverageAcceptanceTimeLessThan(String value) {
            addCriterion("average_acceptance_time <", value, "averageAcceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAverageAcceptanceTimeLessThanOrEqualTo(String value) {
            addCriterion("average_acceptance_time <=", value, "averageAcceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAverageAcceptanceTimeLike(String value) {
            addCriterion("average_acceptance_time like", value, "averageAcceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAverageAcceptanceTimeNotLike(String value) {
            addCriterion("average_acceptance_time not like", value, "averageAcceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAverageAcceptanceTimeIn(List<String> values) {
            addCriterion("average_acceptance_time in", values, "averageAcceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAverageAcceptanceTimeNotIn(List<String> values) {
            addCriterion("average_acceptance_time not in", values, "averageAcceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAverageAcceptanceTimeBetween(String value1, String value2) {
            addCriterion("average_acceptance_time between", value1, value2, "averageAcceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAverageAcceptanceTimeNotBetween(String value1, String value2) {
            addCriterion("average_acceptance_time not between", value1, value2, "averageAcceptanceTime");
            return (Criteria) this;
        }

        public Criteria andInterestRateRangIsNull() {
            addCriterion("interest_rate_rang is null");
            return (Criteria) this;
        }

        public Criteria andInterestRateRangIsNotNull() {
            addCriterion("interest_rate_rang is not null");
            return (Criteria) this;
        }

        public Criteria andInterestRateRangEqualTo(String value) {
            addCriterion("interest_rate_rang =", value, "interestRateRang");
            return (Criteria) this;
        }

        public Criteria andInterestRateRangNotEqualTo(String value) {
            addCriterion("interest_rate_rang <>", value, "interestRateRang");
            return (Criteria) this;
        }

        public Criteria andInterestRateRangGreaterThan(String value) {
            addCriterion("interest_rate_rang >", value, "interestRateRang");
            return (Criteria) this;
        }

        public Criteria andInterestRateRangGreaterThanOrEqualTo(String value) {
            addCriterion("interest_rate_rang >=", value, "interestRateRang");
            return (Criteria) this;
        }

        public Criteria andInterestRateRangLessThan(String value) {
            addCriterion("interest_rate_rang <", value, "interestRateRang");
            return (Criteria) this;
        }

        public Criteria andInterestRateRangLessThanOrEqualTo(String value) {
            addCriterion("interest_rate_rang <=", value, "interestRateRang");
            return (Criteria) this;
        }

        public Criteria andInterestRateRangLike(String value) {
            addCriterion("interest_rate_rang like", value, "interestRateRang");
            return (Criteria) this;
        }

        public Criteria andInterestRateRangNotLike(String value) {
            addCriterion("interest_rate_rang not like", value, "interestRateRang");
            return (Criteria) this;
        }

        public Criteria andInterestRateRangIn(List<String> values) {
            addCriterion("interest_rate_rang in", values, "interestRateRang");
            return (Criteria) this;
        }

        public Criteria andInterestRateRangNotIn(List<String> values) {
            addCriterion("interest_rate_rang not in", values, "interestRateRang");
            return (Criteria) this;
        }

        public Criteria andInterestRateRangBetween(String value1, String value2) {
            addCriterion("interest_rate_rang between", value1, value2, "interestRateRang");
            return (Criteria) this;
        }

        public Criteria andInterestRateRangNotBetween(String value1, String value2) {
            addCriterion("interest_rate_rang not between", value1, value2, "interestRateRang");
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