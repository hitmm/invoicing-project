package com.glacier.frame.entity.system;

import java.util.ArrayList;
import java.util.List;

public class CompanyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart = -1;

    protected Integer limitEnd = -1;

    public CompanyExample() {
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

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
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

        public Criteria andCompanyIdIsNull() {
            addCriterion("temp_company.company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("temp_company.company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(String value) {
            addCriterion("temp_company.company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(String value) {
            addCriterion("temp_company.company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(String value) {
            addCriterion("temp_company.company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("temp_company.company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(String value) {
            addCriterion("temp_company.company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("temp_company.company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLike(String value) {
            addCriterion("temp_company.company_id like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotLike(String value) {
            addCriterion("temp_company.company_id not like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<String> values) {
            addCriterion("temp_company.company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<String> values) {
            addCriterion("temp_company.company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(String value1, String value2) {
            addCriterion("temp_company.company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(String value1, String value2) {
            addCriterion("temp_company.company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyCnNameIsNull() {
            addCriterion("temp_company.company_cn_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyCnNameIsNotNull() {
            addCriterion("temp_company.company_cn_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyCnNameEqualTo(String value) {
            addCriterion("temp_company.company_cn_name =", value, "companyCnName");
            return (Criteria) this;
        }

        public Criteria andCompanyCnNameNotEqualTo(String value) {
            addCriterion("temp_company.company_cn_name <>", value, "companyCnName");
            return (Criteria) this;
        }

        public Criteria andCompanyCnNameGreaterThan(String value) {
            addCriterion("temp_company.company_cn_name >", value, "companyCnName");
            return (Criteria) this;
        }

        public Criteria andCompanyCnNameGreaterThanOrEqualTo(String value) {
            addCriterion("temp_company.company_cn_name >=", value, "companyCnName");
            return (Criteria) this;
        }

        public Criteria andCompanyCnNameLessThan(String value) {
            addCriterion("temp_company.company_cn_name <", value, "companyCnName");
            return (Criteria) this;
        }

        public Criteria andCompanyCnNameLessThanOrEqualTo(String value) {
            addCriterion("temp_company.company_cn_name <=", value, "companyCnName");
            return (Criteria) this;
        }

        public Criteria andCompanyCnNameLike(String value) {
            addCriterion("temp_company.company_cn_name like", value, "companyCnName");
            return (Criteria) this;
        }

        public Criteria andCompanyCnNameNotLike(String value) {
            addCriterion("temp_company.company_cn_name not like", value, "companyCnName");
            return (Criteria) this;
        }

        public Criteria andCompanyCnNameIn(List<String> values) {
            addCriterion("temp_company.company_cn_name in", values, "companyCnName");
            return (Criteria) this;
        }

        public Criteria andCompanyCnNameNotIn(List<String> values) {
            addCriterion("temp_company.company_cn_name not in", values, "companyCnName");
            return (Criteria) this;
        }

        public Criteria andCompanyCnNameBetween(String value1, String value2) {
            addCriterion("temp_company.company_cn_name between", value1, value2, "companyCnName");
            return (Criteria) this;
        }

        public Criteria andCompanyCnNameNotBetween(String value1, String value2) {
            addCriterion("temp_company.company_cn_name not between", value1, value2, "companyCnName");
            return (Criteria) this;
        }

        public Criteria andCompanyAliasIsNull() {
            addCriterion("temp_company.company_alias is null");
            return (Criteria) this;
        }

        public Criteria andCompanyAliasIsNotNull() {
            addCriterion("temp_company.company_alias is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyAliasEqualTo(String value) {
            addCriterion("temp_company.company_alias =", value, "companyAlias");
            return (Criteria) this;
        }

        public Criteria andCompanyAliasNotEqualTo(String value) {
            addCriterion("temp_company.company_alias <>", value, "companyAlias");
            return (Criteria) this;
        }

        public Criteria andCompanyAliasGreaterThan(String value) {
            addCriterion("temp_company.company_alias >", value, "companyAlias");
            return (Criteria) this;
        }

        public Criteria andCompanyAliasGreaterThanOrEqualTo(String value) {
            addCriterion("temp_company.company_alias >=", value, "companyAlias");
            return (Criteria) this;
        }

        public Criteria andCompanyAliasLessThan(String value) {
            addCriterion("temp_company.company_alias <", value, "companyAlias");
            return (Criteria) this;
        }

        public Criteria andCompanyAliasLessThanOrEqualTo(String value) {
            addCriterion("temp_company.company_alias <=", value, "companyAlias");
            return (Criteria) this;
        }

        public Criteria andCompanyAliasLike(String value) {
            addCriterion("temp_company.company_alias like", value, "companyAlias");
            return (Criteria) this;
        }

        public Criteria andCompanyAliasNotLike(String value) {
            addCriterion("temp_company.company_alias not like", value, "companyAlias");
            return (Criteria) this;
        }

        public Criteria andCompanyAliasIn(List<String> values) {
            addCriterion("temp_company.company_alias in", values, "companyAlias");
            return (Criteria) this;
        }

        public Criteria andCompanyAliasNotIn(List<String> values) {
            addCriterion("temp_company.company_alias not in", values, "companyAlias");
            return (Criteria) this;
        }

        public Criteria andCompanyAliasBetween(String value1, String value2) {
            addCriterion("temp_company.company_alias between", value1, value2, "companyAlias");
            return (Criteria) this;
        }

        public Criteria andCompanyAliasNotBetween(String value1, String value2) {
            addCriterion("temp_company.company_alias not between", value1, value2, "companyAlias");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameIsNull() {
            addCriterion("temp_company.company_en_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameIsNotNull() {
            addCriterion("temp_company.company_en_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameEqualTo(String value) {
            addCriterion("temp_company.company_en_name =", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameNotEqualTo(String value) {
            addCriterion("temp_company.company_en_name <>", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameGreaterThan(String value) {
            addCriterion("temp_company.company_en_name >", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameGreaterThanOrEqualTo(String value) {
            addCriterion("temp_company.company_en_name >=", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameLessThan(String value) {
            addCriterion("temp_company.company_en_name <", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameLessThanOrEqualTo(String value) {
            addCriterion("temp_company.company_en_name <=", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameLike(String value) {
            addCriterion("temp_company.company_en_name like", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameNotLike(String value) {
            addCriterion("temp_company.company_en_name not like", value, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameIn(List<String> values) {
            addCriterion("temp_company.company_en_name in", values, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameNotIn(List<String> values) {
            addCriterion("temp_company.company_en_name not in", values, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameBetween(String value1, String value2) {
            addCriterion("temp_company.company_en_name between", value1, value2, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCompanyEnNameNotBetween(String value1, String value2) {
            addCriterion("temp_company.company_en_name not between", value1, value2, "companyEnName");
            return (Criteria) this;
        }

        public Criteria andCeoIdIsNull() {
            addCriterion("temp_company.ceo_id is null");
            return (Criteria) this;
        }

        public Criteria andCeoIdIsNotNull() {
            addCriterion("temp_company.ceo_id is not null");
            return (Criteria) this;
        }

        public Criteria andCeoIdEqualTo(String value) {
            addCriterion("temp_company.ceo_id =", value, "ceoId");
            return (Criteria) this;
        }

        public Criteria andCeoIdNotEqualTo(String value) {
            addCriterion("temp_company.ceo_id <>", value, "ceoId");
            return (Criteria) this;
        }

        public Criteria andCeoIdGreaterThan(String value) {
            addCriterion("temp_company.ceo_id >", value, "ceoId");
            return (Criteria) this;
        }

        public Criteria andCeoIdGreaterThanOrEqualTo(String value) {
            addCriterion("temp_company.ceo_id >=", value, "ceoId");
            return (Criteria) this;
        }

        public Criteria andCeoIdLessThan(String value) {
            addCriterion("temp_company.ceo_id <", value, "ceoId");
            return (Criteria) this;
        }

        public Criteria andCeoIdLessThanOrEqualTo(String value) {
            addCriterion("temp_company.ceo_id <=", value, "ceoId");
            return (Criteria) this;
        }

        public Criteria andCeoIdLike(String value) {
            addCriterion("temp_company.ceo_id like", value, "ceoId");
            return (Criteria) this;
        }

        public Criteria andCeoIdNotLike(String value) {
            addCriterion("temp_company.ceo_id not like", value, "ceoId");
            return (Criteria) this;
        }

        public Criteria andCeoIdIn(List<String> values) {
            addCriterion("temp_company.ceo_id in", values, "ceoId");
            return (Criteria) this;
        }

        public Criteria andCeoIdNotIn(List<String> values) {
            addCriterion("temp_company.ceo_id not in", values, "ceoId");
            return (Criteria) this;
        }

        public Criteria andCeoIdBetween(String value1, String value2) {
            addCriterion("temp_company.ceo_id between", value1, value2, "ceoId");
            return (Criteria) this;
        }

        public Criteria andCeoIdNotBetween(String value1, String value2) {
            addCriterion("temp_company.ceo_id not between", value1, value2, "ceoId");
            return (Criteria) this;
        }

        public Criteria andFaxIsNull() {
            addCriterion("temp_company.fax is null");
            return (Criteria) this;
        }

        public Criteria andFaxIsNotNull() {
            addCriterion("temp_company.fax is not null");
            return (Criteria) this;
        }

        public Criteria andFaxEqualTo(String value) {
            addCriterion("temp_company.fax =", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotEqualTo(String value) {
            addCriterion("temp_company.fax <>", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThan(String value) {
            addCriterion("temp_company.fax >", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThanOrEqualTo(String value) {
            addCriterion("temp_company.fax >=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThan(String value) {
            addCriterion("temp_company.fax <", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThanOrEqualTo(String value) {
            addCriterion("temp_company.fax <=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLike(String value) {
            addCriterion("temp_company.fax like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotLike(String value) {
            addCriterion("temp_company.fax not like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxIn(List<String> values) {
            addCriterion("temp_company.fax in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotIn(List<String> values) {
            addCriterion("temp_company.fax not in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxBetween(String value1, String value2) {
            addCriterion("temp_company.fax between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotBetween(String value1, String value2) {
            addCriterion("temp_company.fax not between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andHostUrlIsNull() {
            addCriterion("temp_company.host_url is null");
            return (Criteria) this;
        }

        public Criteria andHostUrlIsNotNull() {
            addCriterion("temp_company.host_url is not null");
            return (Criteria) this;
        }

        public Criteria andHostUrlEqualTo(String value) {
            addCriterion("temp_company.host_url =", value, "hostUrl");
            return (Criteria) this;
        }

        public Criteria andHostUrlNotEqualTo(String value) {
            addCriterion("temp_company.host_url <>", value, "hostUrl");
            return (Criteria) this;
        }

        public Criteria andHostUrlGreaterThan(String value) {
            addCriterion("temp_company.host_url >", value, "hostUrl");
            return (Criteria) this;
        }

        public Criteria andHostUrlGreaterThanOrEqualTo(String value) {
            addCriterion("temp_company.host_url >=", value, "hostUrl");
            return (Criteria) this;
        }

        public Criteria andHostUrlLessThan(String value) {
            addCriterion("temp_company.host_url <", value, "hostUrl");
            return (Criteria) this;
        }

        public Criteria andHostUrlLessThanOrEqualTo(String value) {
            addCriterion("temp_company.host_url <=", value, "hostUrl");
            return (Criteria) this;
        }

        public Criteria andHostUrlLike(String value) {
            addCriterion("temp_company.host_url like", value, "hostUrl");
            return (Criteria) this;
        }

        public Criteria andHostUrlNotLike(String value) {
            addCriterion("temp_company.host_url not like", value, "hostUrl");
            return (Criteria) this;
        }

        public Criteria andHostUrlIn(List<String> values) {
            addCriterion("temp_company.host_url in", values, "hostUrl");
            return (Criteria) this;
        }

        public Criteria andHostUrlNotIn(List<String> values) {
            addCriterion("temp_company.host_url not in", values, "hostUrl");
            return (Criteria) this;
        }

        public Criteria andHostUrlBetween(String value1, String value2) {
            addCriterion("temp_company.host_url between", value1, value2, "hostUrl");
            return (Criteria) this;
        }

        public Criteria andHostUrlNotBetween(String value1, String value2) {
            addCriterion("temp_company.host_url not between", value1, value2, "hostUrl");
            return (Criteria) this;
        }

        public Criteria andIntroIsNull() {
            addCriterion("temp_company.intro is null");
            return (Criteria) this;
        }

        public Criteria andIntroIsNotNull() {
            addCriterion("temp_company.intro is not null");
            return (Criteria) this;
        }

        public Criteria andIntroEqualTo(String value) {
            addCriterion("temp_company.intro =", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotEqualTo(String value) {
            addCriterion("temp_company.intro <>", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThan(String value) {
            addCriterion("temp_company.intro >", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThanOrEqualTo(String value) {
            addCriterion("temp_company.intro >=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThan(String value) {
            addCriterion("temp_company.intro <", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThanOrEqualTo(String value) {
            addCriterion("temp_company.intro <=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLike(String value) {
            addCriterion("temp_company.intro like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotLike(String value) {
            addCriterion("temp_company.intro not like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroIn(List<String> values) {
            addCriterion("temp_company.intro in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotIn(List<String> values) {
            addCriterion("temp_company.intro not in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroBetween(String value1, String value2) {
            addCriterion("temp_company.intro between", value1, value2, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotBetween(String value1, String value2) {
            addCriterion("temp_company.intro not between", value1, value2, "intro");
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