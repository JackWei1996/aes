package com.aisino.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MailExample() {
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

        public Criteria andMIdIsNull() {
            addCriterion("m_id is null");
            return (Criteria) this;
        }

        public Criteria andMIdIsNotNull() {
            addCriterion("m_id is not null");
            return (Criteria) this;
        }

        public Criteria andMIdEqualTo(Integer value) {
            addCriterion("m_id =", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotEqualTo(Integer value) {
            addCriterion("m_id <>", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdGreaterThan(Integer value) {
            addCriterion("m_id >", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("m_id >=", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdLessThan(Integer value) {
            addCriterion("m_id <", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdLessThanOrEqualTo(Integer value) {
            addCriterion("m_id <=", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdIn(List<Integer> values) {
            addCriterion("m_id in", values, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotIn(List<Integer> values) {
            addCriterion("m_id not in", values, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdBetween(Integer value1, Integer value2) {
            addCriterion("m_id between", value1, value2, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotBetween(Integer value1, Integer value2) {
            addCriterion("m_id not between", value1, value2, "mId");
            return (Criteria) this;
        }

        public Criteria andProNameIsNull() {
            addCriterion("pro_name is null");
            return (Criteria) this;
        }

        public Criteria andProNameIsNotNull() {
            addCriterion("pro_name is not null");
            return (Criteria) this;
        }

        public Criteria andProNameEqualTo(String value) {
            addCriterion("pro_name =", value, "proName");
            return (Criteria) this;
        }

        public Criteria andProNameNotEqualTo(String value) {
            addCriterion("pro_name <>", value, "proName");
            return (Criteria) this;
        }

        public Criteria andProNameGreaterThan(String value) {
            addCriterion("pro_name >", value, "proName");
            return (Criteria) this;
        }

        public Criteria andProNameGreaterThanOrEqualTo(String value) {
            addCriterion("pro_name >=", value, "proName");
            return (Criteria) this;
        }

        public Criteria andProNameLessThan(String value) {
            addCriterion("pro_name <", value, "proName");
            return (Criteria) this;
        }

        public Criteria andProNameLessThanOrEqualTo(String value) {
            addCriterion("pro_name <=", value, "proName");
            return (Criteria) this;
        }

        public Criteria andProNameLike(String value) {
            addCriterion("pro_name like", value, "proName");
            return (Criteria) this;
        }

        public Criteria andProNameNotLike(String value) {
            addCriterion("pro_name not like", value, "proName");
            return (Criteria) this;
        }

        public Criteria andProNameIn(List<String> values) {
            addCriterion("pro_name in", values, "proName");
            return (Criteria) this;
        }

        public Criteria andProNameNotIn(List<String> values) {
            addCriterion("pro_name not in", values, "proName");
            return (Criteria) this;
        }

        public Criteria andProNameBetween(String value1, String value2) {
            addCriterion("pro_name between", value1, value2, "proName");
            return (Criteria) this;
        }

        public Criteria andProNameNotBetween(String value1, String value2) {
            addCriterion("pro_name not between", value1, value2, "proName");
            return (Criteria) this;
        }

        public Criteria andSeMailIsNull() {
            addCriterion("se_mail is null");
            return (Criteria) this;
        }

        public Criteria andSeMailIsNotNull() {
            addCriterion("se_mail is not null");
            return (Criteria) this;
        }

        public Criteria andSeMailEqualTo(String value) {
            addCriterion("se_mail =", value, "seMail");
            return (Criteria) this;
        }

        public Criteria andSeMailNotEqualTo(String value) {
            addCriterion("se_mail <>", value, "seMail");
            return (Criteria) this;
        }

        public Criteria andSeMailGreaterThan(String value) {
            addCriterion("se_mail >", value, "seMail");
            return (Criteria) this;
        }

        public Criteria andSeMailGreaterThanOrEqualTo(String value) {
            addCriterion("se_mail >=", value, "seMail");
            return (Criteria) this;
        }

        public Criteria andSeMailLessThan(String value) {
            addCriterion("se_mail <", value, "seMail");
            return (Criteria) this;
        }

        public Criteria andSeMailLessThanOrEqualTo(String value) {
            addCriterion("se_mail <=", value, "seMail");
            return (Criteria) this;
        }

        public Criteria andSeMailLike(String value) {
            addCriterion("se_mail like", value, "seMail");
            return (Criteria) this;
        }

        public Criteria andSeMailNotLike(String value) {
            addCriterion("se_mail not like", value, "seMail");
            return (Criteria) this;
        }

        public Criteria andSeMailIn(List<String> values) {
            addCriterion("se_mail in", values, "seMail");
            return (Criteria) this;
        }

        public Criteria andSeMailNotIn(List<String> values) {
            addCriterion("se_mail not in", values, "seMail");
            return (Criteria) this;
        }

        public Criteria andSeMailBetween(String value1, String value2) {
            addCriterion("se_mail between", value1, value2, "seMail");
            return (Criteria) this;
        }

        public Criteria andSeMailNotBetween(String value1, String value2) {
            addCriterion("se_mail not between", value1, value2, "seMail");
            return (Criteria) this;
        }

        public Criteria andReMailIsNull() {
            addCriterion("re_mail is null");
            return (Criteria) this;
        }

        public Criteria andReMailIsNotNull() {
            addCriterion("re_mail is not null");
            return (Criteria) this;
        }

        public Criteria andReMailEqualTo(String value) {
            addCriterion("re_mail =", value, "reMail");
            return (Criteria) this;
        }

        public Criteria andReMailNotEqualTo(String value) {
            addCriterion("re_mail <>", value, "reMail");
            return (Criteria) this;
        }

        public Criteria andReMailGreaterThan(String value) {
            addCriterion("re_mail >", value, "reMail");
            return (Criteria) this;
        }

        public Criteria andReMailGreaterThanOrEqualTo(String value) {
            addCriterion("re_mail >=", value, "reMail");
            return (Criteria) this;
        }

        public Criteria andReMailLessThan(String value) {
            addCriterion("re_mail <", value, "reMail");
            return (Criteria) this;
        }

        public Criteria andReMailLessThanOrEqualTo(String value) {
            addCriterion("re_mail <=", value, "reMail");
            return (Criteria) this;
        }

        public Criteria andReMailLike(String value) {
            addCriterion("re_mail like", value, "reMail");
            return (Criteria) this;
        }

        public Criteria andReMailNotLike(String value) {
            addCriterion("re_mail not like", value, "reMail");
            return (Criteria) this;
        }

        public Criteria andReMailIn(List<String> values) {
            addCriterion("re_mail in", values, "reMail");
            return (Criteria) this;
        }

        public Criteria andReMailNotIn(List<String> values) {
            addCriterion("re_mail not in", values, "reMail");
            return (Criteria) this;
        }

        public Criteria andReMailBetween(String value1, String value2) {
            addCriterion("re_mail between", value1, value2, "reMail");
            return (Criteria) this;
        }

        public Criteria andReMailNotBetween(String value1, String value2) {
            addCriterion("re_mail not between", value1, value2, "reMail");
            return (Criteria) this;
        }

        public Criteria andCoMailIsNull() {
            addCriterion("co_mail is null");
            return (Criteria) this;
        }

        public Criteria andCoMailIsNotNull() {
            addCriterion("co_mail is not null");
            return (Criteria) this;
        }

        public Criteria andCoMailEqualTo(String value) {
            addCriterion("co_mail =", value, "coMail");
            return (Criteria) this;
        }

        public Criteria andCoMailNotEqualTo(String value) {
            addCriterion("co_mail <>", value, "coMail");
            return (Criteria) this;
        }

        public Criteria andCoMailGreaterThan(String value) {
            addCriterion("co_mail >", value, "coMail");
            return (Criteria) this;
        }

        public Criteria andCoMailGreaterThanOrEqualTo(String value) {
            addCriterion("co_mail >=", value, "coMail");
            return (Criteria) this;
        }

        public Criteria andCoMailLessThan(String value) {
            addCriterion("co_mail <", value, "coMail");
            return (Criteria) this;
        }

        public Criteria andCoMailLessThanOrEqualTo(String value) {
            addCriterion("co_mail <=", value, "coMail");
            return (Criteria) this;
        }

        public Criteria andCoMailLike(String value) {
            addCriterion("co_mail like", value, "coMail");
            return (Criteria) this;
        }

        public Criteria andCoMailNotLike(String value) {
            addCriterion("co_mail not like", value, "coMail");
            return (Criteria) this;
        }

        public Criteria andCoMailIn(List<String> values) {
            addCriterion("co_mail in", values, "coMail");
            return (Criteria) this;
        }

        public Criteria andCoMailNotIn(List<String> values) {
            addCriterion("co_mail not in", values, "coMail");
            return (Criteria) this;
        }

        public Criteria andCoMailBetween(String value1, String value2) {
            addCriterion("co_mail between", value1, value2, "coMail");
            return (Criteria) this;
        }

        public Criteria andCoMailNotBetween(String value1, String value2) {
            addCriterion("co_mail not between", value1, value2, "coMail");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andFileIsNull() {
            addCriterion("file is null");
            return (Criteria) this;
        }

        public Criteria andFileIsNotNull() {
            addCriterion("file is not null");
            return (Criteria) this;
        }

        public Criteria andFileEqualTo(String value) {
            addCriterion("file =", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileNotEqualTo(String value) {
            addCriterion("file <>", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileGreaterThan(String value) {
            addCriterion("file >", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileGreaterThanOrEqualTo(String value) {
            addCriterion("file >=", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileLessThan(String value) {
            addCriterion("file <", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileLessThanOrEqualTo(String value) {
            addCriterion("file <=", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileLike(String value) {
            addCriterion("file like", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileNotLike(String value) {
            addCriterion("file not like", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileIn(List<String> values) {
            addCriterion("file in", values, "file");
            return (Criteria) this;
        }

        public Criteria andFileNotIn(List<String> values) {
            addCriterion("file not in", values, "file");
            return (Criteria) this;
        }

        public Criteria andFileBetween(String value1, String value2) {
            addCriterion("file between", value1, value2, "file");
            return (Criteria) this;
        }

        public Criteria andFileNotBetween(String value1, String value2) {
            addCriterion("file not between", value1, value2, "file");
            return (Criteria) this;
        }

        public Criteria andSeTimeIsNull() {
            addCriterion("se_time is null");
            return (Criteria) this;
        }

        public Criteria andSeTimeIsNotNull() {
            addCriterion("se_time is not null");
            return (Criteria) this;
        }

        public Criteria andSeTimeEqualTo(Date value) {
            addCriterion("se_time =", value, "seTime");
            return (Criteria) this;
        }

        public Criteria andSeTimeNotEqualTo(Date value) {
            addCriterion("se_time <>", value, "seTime");
            return (Criteria) this;
        }

        public Criteria andSeTimeGreaterThan(Date value) {
            addCriterion("se_time >", value, "seTime");
            return (Criteria) this;
        }

        public Criteria andSeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("se_time >=", value, "seTime");
            return (Criteria) this;
        }

        public Criteria andSeTimeLessThan(Date value) {
            addCriterion("se_time <", value, "seTime");
            return (Criteria) this;
        }

        public Criteria andSeTimeLessThanOrEqualTo(Date value) {
            addCriterion("se_time <=", value, "seTime");
            return (Criteria) this;
        }

        public Criteria andSeTimeIn(List<Date> values) {
            addCriterion("se_time in", values, "seTime");
            return (Criteria) this;
        }

        public Criteria andSeTimeNotIn(List<Date> values) {
            addCriterion("se_time not in", values, "seTime");
            return (Criteria) this;
        }

        public Criteria andSeTimeBetween(Date value1, Date value2) {
            addCriterion("se_time between", value1, value2, "seTime");
            return (Criteria) this;
        }

        public Criteria andSeTimeNotBetween(Date value1, Date value2) {
            addCriterion("se_time not between", value1, value2, "seTime");
            return (Criteria) this;
        }

        public Criteria andStatuIsNull() {
            addCriterion("statu is null");
            return (Criteria) this;
        }

        public Criteria andStatuIsNotNull() {
            addCriterion("statu is not null");
            return (Criteria) this;
        }

        public Criteria andStatuEqualTo(Integer value) {
            addCriterion("statu =", value, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuNotEqualTo(Integer value) {
            addCriterion("statu <>", value, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuGreaterThan(Integer value) {
            addCriterion("statu >", value, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuGreaterThanOrEqualTo(Integer value) {
            addCriterion("statu >=", value, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuLessThan(Integer value) {
            addCriterion("statu <", value, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuLessThanOrEqualTo(Integer value) {
            addCriterion("statu <=", value, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuIn(List<Integer> values) {
            addCriterion("statu in", values, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuNotIn(List<Integer> values) {
            addCriterion("statu not in", values, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuBetween(Integer value1, Integer value2) {
            addCriterion("statu between", value1, value2, "statu");
            return (Criteria) this;
        }

        public Criteria andStatuNotBetween(Integer value1, Integer value2) {
            addCriterion("statu not between", value1, value2, "statu");
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