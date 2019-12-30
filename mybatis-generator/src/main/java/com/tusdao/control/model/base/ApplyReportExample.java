package com.tusdao.control.model.base;

import java.util.ArrayList;
import java.util.List;

public class ApplyReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApplyReportExample() {
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

        public Criteria andOrganCodeIsNull() {
            addCriterion("organ_code is null");
            return (Criteria) this;
        }

        public Criteria andOrganCodeIsNotNull() {
            addCriterion("organ_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrganCodeEqualTo(String value) {
            addCriterion("organ_code =", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeNotEqualTo(String value) {
            addCriterion("organ_code <>", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeGreaterThan(String value) {
            addCriterion("organ_code >", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeGreaterThanOrEqualTo(String value) {
            addCriterion("organ_code >=", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeLessThan(String value) {
            addCriterion("organ_code <", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeLessThanOrEqualTo(String value) {
            addCriterion("organ_code <=", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeLike(String value) {
            addCriterion("organ_code like", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeNotLike(String value) {
            addCriterion("organ_code not like", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeIn(List<String> values) {
            addCriterion("organ_code in", values, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeNotIn(List<String> values) {
            addCriterion("organ_code not in", values, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeBetween(String value1, String value2) {
            addCriterion("organ_code between", value1, value2, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeNotBetween(String value1, String value2) {
            addCriterion("organ_code not between", value1, value2, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganNameIsNull() {
            addCriterion("organ_name is null");
            return (Criteria) this;
        }

        public Criteria andOrganNameIsNotNull() {
            addCriterion("organ_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrganNameEqualTo(String value) {
            addCriterion("organ_name =", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameNotEqualTo(String value) {
            addCriterion("organ_name <>", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameGreaterThan(String value) {
            addCriterion("organ_name >", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameGreaterThanOrEqualTo(String value) {
            addCriterion("organ_name >=", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameLessThan(String value) {
            addCriterion("organ_name <", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameLessThanOrEqualTo(String value) {
            addCriterion("organ_name <=", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameLike(String value) {
            addCriterion("organ_name like", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameNotLike(String value) {
            addCriterion("organ_name not like", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameIn(List<String> values) {
            addCriterion("organ_name in", values, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameNotIn(List<String> values) {
            addCriterion("organ_name not in", values, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameBetween(String value1, String value2) {
            addCriterion("organ_name between", value1, value2, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameNotBetween(String value1, String value2) {
            addCriterion("organ_name not between", value1, value2, "organName");
            return (Criteria) this;
        }

        public Criteria andLegalIsNull() {
            addCriterion("legal is null");
            return (Criteria) this;
        }

        public Criteria andLegalIsNotNull() {
            addCriterion("legal is not null");
            return (Criteria) this;
        }

        public Criteria andLegalEqualTo(String value) {
            addCriterion("legal =", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalNotEqualTo(String value) {
            addCriterion("legal <>", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalGreaterThan(String value) {
            addCriterion("legal >", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalGreaterThanOrEqualTo(String value) {
            addCriterion("legal >=", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalLessThan(String value) {
            addCriterion("legal <", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalLessThanOrEqualTo(String value) {
            addCriterion("legal <=", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalLike(String value) {
            addCriterion("legal like", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalNotLike(String value) {
            addCriterion("legal not like", value, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalIn(List<String> values) {
            addCriterion("legal in", values, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalNotIn(List<String> values) {
            addCriterion("legal not in", values, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalBetween(String value1, String value2) {
            addCriterion("legal between", value1, value2, "legal");
            return (Criteria) this;
        }

        public Criteria andLegalNotBetween(String value1, String value2) {
            addCriterion("legal not between", value1, value2, "legal");
            return (Criteria) this;
        }

        public Criteria andOriginalLogoIsNull() {
            addCriterion("original_logo is null");
            return (Criteria) this;
        }

        public Criteria andOriginalLogoIsNotNull() {
            addCriterion("original_logo is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalLogoEqualTo(Integer value) {
            addCriterion("original_logo =", value, "originalLogo");
            return (Criteria) this;
        }

        public Criteria andOriginalLogoNotEqualTo(Integer value) {
            addCriterion("original_logo <>", value, "originalLogo");
            return (Criteria) this;
        }

        public Criteria andOriginalLogoGreaterThan(Integer value) {
            addCriterion("original_logo >", value, "originalLogo");
            return (Criteria) this;
        }

        public Criteria andOriginalLogoGreaterThanOrEqualTo(Integer value) {
            addCriterion("original_logo >=", value, "originalLogo");
            return (Criteria) this;
        }

        public Criteria andOriginalLogoLessThan(Integer value) {
            addCriterion("original_logo <", value, "originalLogo");
            return (Criteria) this;
        }

        public Criteria andOriginalLogoLessThanOrEqualTo(Integer value) {
            addCriterion("original_logo <=", value, "originalLogo");
            return (Criteria) this;
        }

        public Criteria andOriginalLogoIn(List<Integer> values) {
            addCriterion("original_logo in", values, "originalLogo");
            return (Criteria) this;
        }

        public Criteria andOriginalLogoNotIn(List<Integer> values) {
            addCriterion("original_logo not in", values, "originalLogo");
            return (Criteria) this;
        }

        public Criteria andOriginalLogoBetween(Integer value1, Integer value2) {
            addCriterion("original_logo between", value1, value2, "originalLogo");
            return (Criteria) this;
        }

        public Criteria andOriginalLogoNotBetween(Integer value1, Integer value2) {
            addCriterion("original_logo not between", value1, value2, "originalLogo");
            return (Criteria) this;
        }

        public Criteria andAlreadyOriginalIsNull() {
            addCriterion("already_original is null");
            return (Criteria) this;
        }

        public Criteria andAlreadyOriginalIsNotNull() {
            addCriterion("already_original is not null");
            return (Criteria) this;
        }

        public Criteria andAlreadyOriginalEqualTo(Integer value) {
            addCriterion("already_original =", value, "alreadyOriginal");
            return (Criteria) this;
        }

        public Criteria andAlreadyOriginalNotEqualTo(Integer value) {
            addCriterion("already_original <>", value, "alreadyOriginal");
            return (Criteria) this;
        }

        public Criteria andAlreadyOriginalGreaterThan(Integer value) {
            addCriterion("already_original >", value, "alreadyOriginal");
            return (Criteria) this;
        }

        public Criteria andAlreadyOriginalGreaterThanOrEqualTo(Integer value) {
            addCriterion("already_original >=", value, "alreadyOriginal");
            return (Criteria) this;
        }

        public Criteria andAlreadyOriginalLessThan(Integer value) {
            addCriterion("already_original <", value, "alreadyOriginal");
            return (Criteria) this;
        }

        public Criteria andAlreadyOriginalLessThanOrEqualTo(Integer value) {
            addCriterion("already_original <=", value, "alreadyOriginal");
            return (Criteria) this;
        }

        public Criteria andAlreadyOriginalIn(List<Integer> values) {
            addCriterion("already_original in", values, "alreadyOriginal");
            return (Criteria) this;
        }

        public Criteria andAlreadyOriginalNotIn(List<Integer> values) {
            addCriterion("already_original not in", values, "alreadyOriginal");
            return (Criteria) this;
        }

        public Criteria andAlreadyOriginalBetween(Integer value1, Integer value2) {
            addCriterion("already_original between", value1, value2, "alreadyOriginal");
            return (Criteria) this;
        }

        public Criteria andAlreadyOriginalNotBetween(Integer value1, Integer value2) {
            addCriterion("already_original not between", value1, value2, "alreadyOriginal");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceIsNull() {
            addCriterion("organ_province is null");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceIsNotNull() {
            addCriterion("organ_province is not null");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceEqualTo(String value) {
            addCriterion("organ_province =", value, "organProvince");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNotEqualTo(String value) {
            addCriterion("organ_province <>", value, "organProvince");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceGreaterThan(String value) {
            addCriterion("organ_province >", value, "organProvince");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("organ_province >=", value, "organProvince");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceLessThan(String value) {
            addCriterion("organ_province <", value, "organProvince");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceLessThanOrEqualTo(String value) {
            addCriterion("organ_province <=", value, "organProvince");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceLike(String value) {
            addCriterion("organ_province like", value, "organProvince");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNotLike(String value) {
            addCriterion("organ_province not like", value, "organProvince");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceIn(List<String> values) {
            addCriterion("organ_province in", values, "organProvince");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNotIn(List<String> values) {
            addCriterion("organ_province not in", values, "organProvince");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceBetween(String value1, String value2) {
            addCriterion("organ_province between", value1, value2, "organProvince");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNotBetween(String value1, String value2) {
            addCriterion("organ_province not between", value1, value2, "organProvince");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNameIsNull() {
            addCriterion("organ_province_name is null");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNameIsNotNull() {
            addCriterion("organ_province_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNameEqualTo(String value) {
            addCriterion("organ_province_name =", value, "organProvinceName");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNameNotEqualTo(String value) {
            addCriterion("organ_province_name <>", value, "organProvinceName");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNameGreaterThan(String value) {
            addCriterion("organ_province_name >", value, "organProvinceName");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNameGreaterThanOrEqualTo(String value) {
            addCriterion("organ_province_name >=", value, "organProvinceName");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNameLessThan(String value) {
            addCriterion("organ_province_name <", value, "organProvinceName");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNameLessThanOrEqualTo(String value) {
            addCriterion("organ_province_name <=", value, "organProvinceName");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNameLike(String value) {
            addCriterion("organ_province_name like", value, "organProvinceName");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNameNotLike(String value) {
            addCriterion("organ_province_name not like", value, "organProvinceName");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNameIn(List<String> values) {
            addCriterion("organ_province_name in", values, "organProvinceName");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNameNotIn(List<String> values) {
            addCriterion("organ_province_name not in", values, "organProvinceName");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNameBetween(String value1, String value2) {
            addCriterion("organ_province_name between", value1, value2, "organProvinceName");
            return (Criteria) this;
        }

        public Criteria andOrganProvinceNameNotBetween(String value1, String value2) {
            addCriterion("organ_province_name not between", value1, value2, "organProvinceName");
            return (Criteria) this;
        }

        public Criteria andOrganCityIsNull() {
            addCriterion("organ_city is null");
            return (Criteria) this;
        }

        public Criteria andOrganCityIsNotNull() {
            addCriterion("organ_city is not null");
            return (Criteria) this;
        }

        public Criteria andOrganCityEqualTo(String value) {
            addCriterion("organ_city =", value, "organCity");
            return (Criteria) this;
        }

        public Criteria andOrganCityNotEqualTo(String value) {
            addCriterion("organ_city <>", value, "organCity");
            return (Criteria) this;
        }

        public Criteria andOrganCityGreaterThan(String value) {
            addCriterion("organ_city >", value, "organCity");
            return (Criteria) this;
        }

        public Criteria andOrganCityGreaterThanOrEqualTo(String value) {
            addCriterion("organ_city >=", value, "organCity");
            return (Criteria) this;
        }

        public Criteria andOrganCityLessThan(String value) {
            addCriterion("organ_city <", value, "organCity");
            return (Criteria) this;
        }

        public Criteria andOrganCityLessThanOrEqualTo(String value) {
            addCriterion("organ_city <=", value, "organCity");
            return (Criteria) this;
        }

        public Criteria andOrganCityLike(String value) {
            addCriterion("organ_city like", value, "organCity");
            return (Criteria) this;
        }

        public Criteria andOrganCityNotLike(String value) {
            addCriterion("organ_city not like", value, "organCity");
            return (Criteria) this;
        }

        public Criteria andOrganCityIn(List<String> values) {
            addCriterion("organ_city in", values, "organCity");
            return (Criteria) this;
        }

        public Criteria andOrganCityNotIn(List<String> values) {
            addCriterion("organ_city not in", values, "organCity");
            return (Criteria) this;
        }

        public Criteria andOrganCityBetween(String value1, String value2) {
            addCriterion("organ_city between", value1, value2, "organCity");
            return (Criteria) this;
        }

        public Criteria andOrganCityNotBetween(String value1, String value2) {
            addCriterion("organ_city not between", value1, value2, "organCity");
            return (Criteria) this;
        }

        public Criteria andOrganCityNameIsNull() {
            addCriterion("organ_city_name is null");
            return (Criteria) this;
        }

        public Criteria andOrganCityNameIsNotNull() {
            addCriterion("organ_city_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrganCityNameEqualTo(String value) {
            addCriterion("organ_city_name =", value, "organCityName");
            return (Criteria) this;
        }

        public Criteria andOrganCityNameNotEqualTo(String value) {
            addCriterion("organ_city_name <>", value, "organCityName");
            return (Criteria) this;
        }

        public Criteria andOrganCityNameGreaterThan(String value) {
            addCriterion("organ_city_name >", value, "organCityName");
            return (Criteria) this;
        }

        public Criteria andOrganCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("organ_city_name >=", value, "organCityName");
            return (Criteria) this;
        }

        public Criteria andOrganCityNameLessThan(String value) {
            addCriterion("organ_city_name <", value, "organCityName");
            return (Criteria) this;
        }

        public Criteria andOrganCityNameLessThanOrEqualTo(String value) {
            addCriterion("organ_city_name <=", value, "organCityName");
            return (Criteria) this;
        }

        public Criteria andOrganCityNameLike(String value) {
            addCriterion("organ_city_name like", value, "organCityName");
            return (Criteria) this;
        }

        public Criteria andOrganCityNameNotLike(String value) {
            addCriterion("organ_city_name not like", value, "organCityName");
            return (Criteria) this;
        }

        public Criteria andOrganCityNameIn(List<String> values) {
            addCriterion("organ_city_name in", values, "organCityName");
            return (Criteria) this;
        }

        public Criteria andOrganCityNameNotIn(List<String> values) {
            addCriterion("organ_city_name not in", values, "organCityName");
            return (Criteria) this;
        }

        public Criteria andOrganCityNameBetween(String value1, String value2) {
            addCriterion("organ_city_name between", value1, value2, "organCityName");
            return (Criteria) this;
        }

        public Criteria andOrganCityNameNotBetween(String value1, String value2) {
            addCriterion("organ_city_name not between", value1, value2, "organCityName");
            return (Criteria) this;
        }

        public Criteria andNewspaperIsNull() {
            addCriterion("newspaper is null");
            return (Criteria) this;
        }

        public Criteria andNewspaperIsNotNull() {
            addCriterion("newspaper is not null");
            return (Criteria) this;
        }

        public Criteria andNewspaperEqualTo(String value) {
            addCriterion("newspaper =", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperNotEqualTo(String value) {
            addCriterion("newspaper <>", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperGreaterThan(String value) {
            addCriterion("newspaper >", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperGreaterThanOrEqualTo(String value) {
            addCriterion("newspaper >=", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperLessThan(String value) {
            addCriterion("newspaper <", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperLessThanOrEqualTo(String value) {
            addCriterion("newspaper <=", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperLike(String value) {
            addCriterion("newspaper like", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperNotLike(String value) {
            addCriterion("newspaper not like", value, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperIn(List<String> values) {
            addCriterion("newspaper in", values, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperNotIn(List<String> values) {
            addCriterion("newspaper not in", values, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperBetween(String value1, String value2) {
            addCriterion("newspaper between", value1, value2, "newspaper");
            return (Criteria) this;
        }

        public Criteria andNewspaperNotBetween(String value1, String value2) {
            addCriterion("newspaper not between", value1, value2, "newspaper");
            return (Criteria) this;
        }

        public Criteria andDirectorIsNull() {
            addCriterion("director is null");
            return (Criteria) this;
        }

        public Criteria andDirectorIsNotNull() {
            addCriterion("director is not null");
            return (Criteria) this;
        }

        public Criteria andDirectorEqualTo(String value) {
            addCriterion("director =", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorNotEqualTo(String value) {
            addCriterion("director <>", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorGreaterThan(String value) {
            addCriterion("director >", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorGreaterThanOrEqualTo(String value) {
            addCriterion("director >=", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorLessThan(String value) {
            addCriterion("director <", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorLessThanOrEqualTo(String value) {
            addCriterion("director <=", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorLike(String value) {
            addCriterion("director like", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorNotLike(String value) {
            addCriterion("director not like", value, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorIn(List<String> values) {
            addCriterion("director in", values, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorNotIn(List<String> values) {
            addCriterion("director not in", values, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorBetween(String value1, String value2) {
            addCriterion("director between", value1, value2, "director");
            return (Criteria) this;
        }

        public Criteria andDirectorNotBetween(String value1, String value2) {
            addCriterion("director not between", value1, value2, "director");
            return (Criteria) this;
        }

        public Criteria andSpaperPhoneIsNull() {
            addCriterion("spaper_phone is null");
            return (Criteria) this;
        }

        public Criteria andSpaperPhoneIsNotNull() {
            addCriterion("spaper_phone is not null");
            return (Criteria) this;
        }

        public Criteria andSpaperPhoneEqualTo(String value) {
            addCriterion("spaper_phone =", value, "spaperPhone");
            return (Criteria) this;
        }

        public Criteria andSpaperPhoneNotEqualTo(String value) {
            addCriterion("spaper_phone <>", value, "spaperPhone");
            return (Criteria) this;
        }

        public Criteria andSpaperPhoneGreaterThan(String value) {
            addCriterion("spaper_phone >", value, "spaperPhone");
            return (Criteria) this;
        }

        public Criteria andSpaperPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("spaper_phone >=", value, "spaperPhone");
            return (Criteria) this;
        }

        public Criteria andSpaperPhoneLessThan(String value) {
            addCriterion("spaper_phone <", value, "spaperPhone");
            return (Criteria) this;
        }

        public Criteria andSpaperPhoneLessThanOrEqualTo(String value) {
            addCriterion("spaper_phone <=", value, "spaperPhone");
            return (Criteria) this;
        }

        public Criteria andSpaperPhoneLike(String value) {
            addCriterion("spaper_phone like", value, "spaperPhone");
            return (Criteria) this;
        }

        public Criteria andSpaperPhoneNotLike(String value) {
            addCriterion("spaper_phone not like", value, "spaperPhone");
            return (Criteria) this;
        }

        public Criteria andSpaperPhoneIn(List<String> values) {
            addCriterion("spaper_phone in", values, "spaperPhone");
            return (Criteria) this;
        }

        public Criteria andSpaperPhoneNotIn(List<String> values) {
            addCriterion("spaper_phone not in", values, "spaperPhone");
            return (Criteria) this;
        }

        public Criteria andSpaperPhoneBetween(String value1, String value2) {
            addCriterion("spaper_phone between", value1, value2, "spaperPhone");
            return (Criteria) this;
        }

        public Criteria andSpaperPhoneNotBetween(String value1, String value2) {
            addCriterion("spaper_phone not between", value1, value2, "spaperPhone");
            return (Criteria) this;
        }

        public Criteria andSpaperMailIsNull() {
            addCriterion("spaper_mail is null");
            return (Criteria) this;
        }

        public Criteria andSpaperMailIsNotNull() {
            addCriterion("spaper_mail is not null");
            return (Criteria) this;
        }

        public Criteria andSpaperMailEqualTo(String value) {
            addCriterion("spaper_mail =", value, "spaperMail");
            return (Criteria) this;
        }

        public Criteria andSpaperMailNotEqualTo(String value) {
            addCriterion("spaper_mail <>", value, "spaperMail");
            return (Criteria) this;
        }

        public Criteria andSpaperMailGreaterThan(String value) {
            addCriterion("spaper_mail >", value, "spaperMail");
            return (Criteria) this;
        }

        public Criteria andSpaperMailGreaterThanOrEqualTo(String value) {
            addCriterion("spaper_mail >=", value, "spaperMail");
            return (Criteria) this;
        }

        public Criteria andSpaperMailLessThan(String value) {
            addCriterion("spaper_mail <", value, "spaperMail");
            return (Criteria) this;
        }

        public Criteria andSpaperMailLessThanOrEqualTo(String value) {
            addCriterion("spaper_mail <=", value, "spaperMail");
            return (Criteria) this;
        }

        public Criteria andSpaperMailLike(String value) {
            addCriterion("spaper_mail like", value, "spaperMail");
            return (Criteria) this;
        }

        public Criteria andSpaperMailNotLike(String value) {
            addCriterion("spaper_mail not like", value, "spaperMail");
            return (Criteria) this;
        }

        public Criteria andSpaperMailIn(List<String> values) {
            addCriterion("spaper_mail in", values, "spaperMail");
            return (Criteria) this;
        }

        public Criteria andSpaperMailNotIn(List<String> values) {
            addCriterion("spaper_mail not in", values, "spaperMail");
            return (Criteria) this;
        }

        public Criteria andSpaperMailBetween(String value1, String value2) {
            addCriterion("spaper_mail between", value1, value2, "spaperMail");
            return (Criteria) this;
        }

        public Criteria andSpaperMailNotBetween(String value1, String value2) {
            addCriterion("spaper_mail not between", value1, value2, "spaperMail");
            return (Criteria) this;
        }

        public Criteria andHeadNameIsNull() {
            addCriterion("head_name is null");
            return (Criteria) this;
        }

        public Criteria andHeadNameIsNotNull() {
            addCriterion("head_name is not null");
            return (Criteria) this;
        }

        public Criteria andHeadNameEqualTo(String value) {
            addCriterion("head_name =", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameNotEqualTo(String value) {
            addCriterion("head_name <>", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameGreaterThan(String value) {
            addCriterion("head_name >", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameGreaterThanOrEqualTo(String value) {
            addCriterion("head_name >=", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameLessThan(String value) {
            addCriterion("head_name <", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameLessThanOrEqualTo(String value) {
            addCriterion("head_name <=", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameLike(String value) {
            addCriterion("head_name like", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameNotLike(String value) {
            addCriterion("head_name not like", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameIn(List<String> values) {
            addCriterion("head_name in", values, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameNotIn(List<String> values) {
            addCriterion("head_name not in", values, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameBetween(String value1, String value2) {
            addCriterion("head_name between", value1, value2, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameNotBetween(String value1, String value2) {
            addCriterion("head_name not between", value1, value2, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadDeptIsNull() {
            addCriterion("head_dept is null");
            return (Criteria) this;
        }

        public Criteria andHeadDeptIsNotNull() {
            addCriterion("head_dept is not null");
            return (Criteria) this;
        }

        public Criteria andHeadDeptEqualTo(String value) {
            addCriterion("head_dept =", value, "headDept");
            return (Criteria) this;
        }

        public Criteria andHeadDeptNotEqualTo(String value) {
            addCriterion("head_dept <>", value, "headDept");
            return (Criteria) this;
        }

        public Criteria andHeadDeptGreaterThan(String value) {
            addCriterion("head_dept >", value, "headDept");
            return (Criteria) this;
        }

        public Criteria andHeadDeptGreaterThanOrEqualTo(String value) {
            addCriterion("head_dept >=", value, "headDept");
            return (Criteria) this;
        }

        public Criteria andHeadDeptLessThan(String value) {
            addCriterion("head_dept <", value, "headDept");
            return (Criteria) this;
        }

        public Criteria andHeadDeptLessThanOrEqualTo(String value) {
            addCriterion("head_dept <=", value, "headDept");
            return (Criteria) this;
        }

        public Criteria andHeadDeptLike(String value) {
            addCriterion("head_dept like", value, "headDept");
            return (Criteria) this;
        }

        public Criteria andHeadDeptNotLike(String value) {
            addCriterion("head_dept not like", value, "headDept");
            return (Criteria) this;
        }

        public Criteria andHeadDeptIn(List<String> values) {
            addCriterion("head_dept in", values, "headDept");
            return (Criteria) this;
        }

        public Criteria andHeadDeptNotIn(List<String> values) {
            addCriterion("head_dept not in", values, "headDept");
            return (Criteria) this;
        }

        public Criteria andHeadDeptBetween(String value1, String value2) {
            addCriterion("head_dept between", value1, value2, "headDept");
            return (Criteria) this;
        }

        public Criteria andHeadDeptNotBetween(String value1, String value2) {
            addCriterion("head_dept not between", value1, value2, "headDept");
            return (Criteria) this;
        }

        public Criteria andHeadPhoneIsNull() {
            addCriterion("head_phone is null");
            return (Criteria) this;
        }

        public Criteria andHeadPhoneIsNotNull() {
            addCriterion("head_phone is not null");
            return (Criteria) this;
        }

        public Criteria andHeadPhoneEqualTo(String value) {
            addCriterion("head_phone =", value, "headPhone");
            return (Criteria) this;
        }

        public Criteria andHeadPhoneNotEqualTo(String value) {
            addCriterion("head_phone <>", value, "headPhone");
            return (Criteria) this;
        }

        public Criteria andHeadPhoneGreaterThan(String value) {
            addCriterion("head_phone >", value, "headPhone");
            return (Criteria) this;
        }

        public Criteria andHeadPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("head_phone >=", value, "headPhone");
            return (Criteria) this;
        }

        public Criteria andHeadPhoneLessThan(String value) {
            addCriterion("head_phone <", value, "headPhone");
            return (Criteria) this;
        }

        public Criteria andHeadPhoneLessThanOrEqualTo(String value) {
            addCriterion("head_phone <=", value, "headPhone");
            return (Criteria) this;
        }

        public Criteria andHeadPhoneLike(String value) {
            addCriterion("head_phone like", value, "headPhone");
            return (Criteria) this;
        }

        public Criteria andHeadPhoneNotLike(String value) {
            addCriterion("head_phone not like", value, "headPhone");
            return (Criteria) this;
        }

        public Criteria andHeadPhoneIn(List<String> values) {
            addCriterion("head_phone in", values, "headPhone");
            return (Criteria) this;
        }

        public Criteria andHeadPhoneNotIn(List<String> values) {
            addCriterion("head_phone not in", values, "headPhone");
            return (Criteria) this;
        }

        public Criteria andHeadPhoneBetween(String value1, String value2) {
            addCriterion("head_phone between", value1, value2, "headPhone");
            return (Criteria) this;
        }

        public Criteria andHeadPhoneNotBetween(String value1, String value2) {
            addCriterion("head_phone not between", value1, value2, "headPhone");
            return (Criteria) this;
        }

        public Criteria andHeadMailIsNull() {
            addCriterion("head_mail is null");
            return (Criteria) this;
        }

        public Criteria andHeadMailIsNotNull() {
            addCriterion("head_mail is not null");
            return (Criteria) this;
        }

        public Criteria andHeadMailEqualTo(String value) {
            addCriterion("head_mail =", value, "headMail");
            return (Criteria) this;
        }

        public Criteria andHeadMailNotEqualTo(String value) {
            addCriterion("head_mail <>", value, "headMail");
            return (Criteria) this;
        }

        public Criteria andHeadMailGreaterThan(String value) {
            addCriterion("head_mail >", value, "headMail");
            return (Criteria) this;
        }

        public Criteria andHeadMailGreaterThanOrEqualTo(String value) {
            addCriterion("head_mail >=", value, "headMail");
            return (Criteria) this;
        }

        public Criteria andHeadMailLessThan(String value) {
            addCriterion("head_mail <", value, "headMail");
            return (Criteria) this;
        }

        public Criteria andHeadMailLessThanOrEqualTo(String value) {
            addCriterion("head_mail <=", value, "headMail");
            return (Criteria) this;
        }

        public Criteria andHeadMailLike(String value) {
            addCriterion("head_mail like", value, "headMail");
            return (Criteria) this;
        }

        public Criteria andHeadMailNotLike(String value) {
            addCriterion("head_mail not like", value, "headMail");
            return (Criteria) this;
        }

        public Criteria andHeadMailIn(List<String> values) {
            addCriterion("head_mail in", values, "headMail");
            return (Criteria) this;
        }

        public Criteria andHeadMailNotIn(List<String> values) {
            addCriterion("head_mail not in", values, "headMail");
            return (Criteria) this;
        }

        public Criteria andHeadMailBetween(String value1, String value2) {
            addCriterion("head_mail between", value1, value2, "headMail");
            return (Criteria) this;
        }

        public Criteria andHeadMailNotBetween(String value1, String value2) {
            addCriterion("head_mail not between", value1, value2, "headMail");
            return (Criteria) this;
        }

        public Criteria andEntryNameIsNull() {
            addCriterion("entry_name is null");
            return (Criteria) this;
        }

        public Criteria andEntryNameIsNotNull() {
            addCriterion("entry_name is not null");
            return (Criteria) this;
        }

        public Criteria andEntryNameEqualTo(String value) {
            addCriterion("entry_name =", value, "entryName");
            return (Criteria) this;
        }

        public Criteria andEntryNameNotEqualTo(String value) {
            addCriterion("entry_name <>", value, "entryName");
            return (Criteria) this;
        }

        public Criteria andEntryNameGreaterThan(String value) {
            addCriterion("entry_name >", value, "entryName");
            return (Criteria) this;
        }

        public Criteria andEntryNameGreaterThanOrEqualTo(String value) {
            addCriterion("entry_name >=", value, "entryName");
            return (Criteria) this;
        }

        public Criteria andEntryNameLessThan(String value) {
            addCriterion("entry_name <", value, "entryName");
            return (Criteria) this;
        }

        public Criteria andEntryNameLessThanOrEqualTo(String value) {
            addCriterion("entry_name <=", value, "entryName");
            return (Criteria) this;
        }

        public Criteria andEntryNameLike(String value) {
            addCriterion("entry_name like", value, "entryName");
            return (Criteria) this;
        }

        public Criteria andEntryNameNotLike(String value) {
            addCriterion("entry_name not like", value, "entryName");
            return (Criteria) this;
        }

        public Criteria andEntryNameIn(List<String> values) {
            addCriterion("entry_name in", values, "entryName");
            return (Criteria) this;
        }

        public Criteria andEntryNameNotIn(List<String> values) {
            addCriterion("entry_name not in", values, "entryName");
            return (Criteria) this;
        }

        public Criteria andEntryNameBetween(String value1, String value2) {
            addCriterion("entry_name between", value1, value2, "entryName");
            return (Criteria) this;
        }

        public Criteria andEntryNameNotBetween(String value1, String value2) {
            addCriterion("entry_name not between", value1, value2, "entryName");
            return (Criteria) this;
        }

        public Criteria andEntryIdentIsNull() {
            addCriterion("entry_ident is null");
            return (Criteria) this;
        }

        public Criteria andEntryIdentIsNotNull() {
            addCriterion("entry_ident is not null");
            return (Criteria) this;
        }

        public Criteria andEntryIdentEqualTo(String value) {
            addCriterion("entry_ident =", value, "entryIdent");
            return (Criteria) this;
        }

        public Criteria andEntryIdentNotEqualTo(String value) {
            addCriterion("entry_ident <>", value, "entryIdent");
            return (Criteria) this;
        }

        public Criteria andEntryIdentGreaterThan(String value) {
            addCriterion("entry_ident >", value, "entryIdent");
            return (Criteria) this;
        }

        public Criteria andEntryIdentGreaterThanOrEqualTo(String value) {
            addCriterion("entry_ident >=", value, "entryIdent");
            return (Criteria) this;
        }

        public Criteria andEntryIdentLessThan(String value) {
            addCriterion("entry_ident <", value, "entryIdent");
            return (Criteria) this;
        }

        public Criteria andEntryIdentLessThanOrEqualTo(String value) {
            addCriterion("entry_ident <=", value, "entryIdent");
            return (Criteria) this;
        }

        public Criteria andEntryIdentLike(String value) {
            addCriterion("entry_ident like", value, "entryIdent");
            return (Criteria) this;
        }

        public Criteria andEntryIdentNotLike(String value) {
            addCriterion("entry_ident not like", value, "entryIdent");
            return (Criteria) this;
        }

        public Criteria andEntryIdentIn(List<String> values) {
            addCriterion("entry_ident in", values, "entryIdent");
            return (Criteria) this;
        }

        public Criteria andEntryIdentNotIn(List<String> values) {
            addCriterion("entry_ident not in", values, "entryIdent");
            return (Criteria) this;
        }

        public Criteria andEntryIdentBetween(String value1, String value2) {
            addCriterion("entry_ident between", value1, value2, "entryIdent");
            return (Criteria) this;
        }

        public Criteria andEntryIdentNotBetween(String value1, String value2) {
            addCriterion("entry_ident not between", value1, value2, "entryIdent");
            return (Criteria) this;
        }

        public Criteria andEntryPhoneIsNull() {
            addCriterion("entry_phone is null");
            return (Criteria) this;
        }

        public Criteria andEntryPhoneIsNotNull() {
            addCriterion("entry_phone is not null");
            return (Criteria) this;
        }

        public Criteria andEntryPhoneEqualTo(String value) {
            addCriterion("entry_phone =", value, "entryPhone");
            return (Criteria) this;
        }

        public Criteria andEntryPhoneNotEqualTo(String value) {
            addCriterion("entry_phone <>", value, "entryPhone");
            return (Criteria) this;
        }

        public Criteria andEntryPhoneGreaterThan(String value) {
            addCriterion("entry_phone >", value, "entryPhone");
            return (Criteria) this;
        }

        public Criteria andEntryPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("entry_phone >=", value, "entryPhone");
            return (Criteria) this;
        }

        public Criteria andEntryPhoneLessThan(String value) {
            addCriterion("entry_phone <", value, "entryPhone");
            return (Criteria) this;
        }

        public Criteria andEntryPhoneLessThanOrEqualTo(String value) {
            addCriterion("entry_phone <=", value, "entryPhone");
            return (Criteria) this;
        }

        public Criteria andEntryPhoneLike(String value) {
            addCriterion("entry_phone like", value, "entryPhone");
            return (Criteria) this;
        }

        public Criteria andEntryPhoneNotLike(String value) {
            addCriterion("entry_phone not like", value, "entryPhone");
            return (Criteria) this;
        }

        public Criteria andEntryPhoneIn(List<String> values) {
            addCriterion("entry_phone in", values, "entryPhone");
            return (Criteria) this;
        }

        public Criteria andEntryPhoneNotIn(List<String> values) {
            addCriterion("entry_phone not in", values, "entryPhone");
            return (Criteria) this;
        }

        public Criteria andEntryPhoneBetween(String value1, String value2) {
            addCriterion("entry_phone between", value1, value2, "entryPhone");
            return (Criteria) this;
        }

        public Criteria andEntryPhoneNotBetween(String value1, String value2) {
            addCriterion("entry_phone not between", value1, value2, "entryPhone");
            return (Criteria) this;
        }

        public Criteria andEntryMailIsNull() {
            addCriterion("entry_mail is null");
            return (Criteria) this;
        }

        public Criteria andEntryMailIsNotNull() {
            addCriterion("entry_mail is not null");
            return (Criteria) this;
        }

        public Criteria andEntryMailEqualTo(String value) {
            addCriterion("entry_mail =", value, "entryMail");
            return (Criteria) this;
        }

        public Criteria andEntryMailNotEqualTo(String value) {
            addCriterion("entry_mail <>", value, "entryMail");
            return (Criteria) this;
        }

        public Criteria andEntryMailGreaterThan(String value) {
            addCriterion("entry_mail >", value, "entryMail");
            return (Criteria) this;
        }

        public Criteria andEntryMailGreaterThanOrEqualTo(String value) {
            addCriterion("entry_mail >=", value, "entryMail");
            return (Criteria) this;
        }

        public Criteria andEntryMailLessThan(String value) {
            addCriterion("entry_mail <", value, "entryMail");
            return (Criteria) this;
        }

        public Criteria andEntryMailLessThanOrEqualTo(String value) {
            addCriterion("entry_mail <=", value, "entryMail");
            return (Criteria) this;
        }

        public Criteria andEntryMailLike(String value) {
            addCriterion("entry_mail like", value, "entryMail");
            return (Criteria) this;
        }

        public Criteria andEntryMailNotLike(String value) {
            addCriterion("entry_mail not like", value, "entryMail");
            return (Criteria) this;
        }

        public Criteria andEntryMailIn(List<String> values) {
            addCriterion("entry_mail in", values, "entryMail");
            return (Criteria) this;
        }

        public Criteria andEntryMailNotIn(List<String> values) {
            addCriterion("entry_mail not in", values, "entryMail");
            return (Criteria) this;
        }

        public Criteria andEntryMailBetween(String value1, String value2) {
            addCriterion("entry_mail between", value1, value2, "entryMail");
            return (Criteria) this;
        }

        public Criteria andEntryMailNotBetween(String value1, String value2) {
            addCriterion("entry_mail not between", value1, value2, "entryMail");
            return (Criteria) this;
        }

        public Criteria andEntryDeptIsNull() {
            addCriterion("entry_dept is null");
            return (Criteria) this;
        }

        public Criteria andEntryDeptIsNotNull() {
            addCriterion("entry_dept is not null");
            return (Criteria) this;
        }

        public Criteria andEntryDeptEqualTo(String value) {
            addCriterion("entry_dept =", value, "entryDept");
            return (Criteria) this;
        }

        public Criteria andEntryDeptNotEqualTo(String value) {
            addCriterion("entry_dept <>", value, "entryDept");
            return (Criteria) this;
        }

        public Criteria andEntryDeptGreaterThan(String value) {
            addCriterion("entry_dept >", value, "entryDept");
            return (Criteria) this;
        }

        public Criteria andEntryDeptGreaterThanOrEqualTo(String value) {
            addCriterion("entry_dept >=", value, "entryDept");
            return (Criteria) this;
        }

        public Criteria andEntryDeptLessThan(String value) {
            addCriterion("entry_dept <", value, "entryDept");
            return (Criteria) this;
        }

        public Criteria andEntryDeptLessThanOrEqualTo(String value) {
            addCriterion("entry_dept <=", value, "entryDept");
            return (Criteria) this;
        }

        public Criteria andEntryDeptLike(String value) {
            addCriterion("entry_dept like", value, "entryDept");
            return (Criteria) this;
        }

        public Criteria andEntryDeptNotLike(String value) {
            addCriterion("entry_dept not like", value, "entryDept");
            return (Criteria) this;
        }

        public Criteria andEntryDeptIn(List<String> values) {
            addCriterion("entry_dept in", values, "entryDept");
            return (Criteria) this;
        }

        public Criteria andEntryDeptNotIn(List<String> values) {
            addCriterion("entry_dept not in", values, "entryDept");
            return (Criteria) this;
        }

        public Criteria andEntryDeptBetween(String value1, String value2) {
            addCriterion("entry_dept between", value1, value2, "entryDept");
            return (Criteria) this;
        }

        public Criteria andEntryDeptNotBetween(String value1, String value2) {
            addCriterion("entry_dept not between", value1, value2, "entryDept");
            return (Criteria) this;
        }

        public Criteria andAuditNameIsNull() {
            addCriterion("audit_name is null");
            return (Criteria) this;
        }

        public Criteria andAuditNameIsNotNull() {
            addCriterion("audit_name is not null");
            return (Criteria) this;
        }

        public Criteria andAuditNameEqualTo(String value) {
            addCriterion("audit_name =", value, "auditName");
            return (Criteria) this;
        }

        public Criteria andAuditNameNotEqualTo(String value) {
            addCriterion("audit_name <>", value, "auditName");
            return (Criteria) this;
        }

        public Criteria andAuditNameGreaterThan(String value) {
            addCriterion("audit_name >", value, "auditName");
            return (Criteria) this;
        }

        public Criteria andAuditNameGreaterThanOrEqualTo(String value) {
            addCriterion("audit_name >=", value, "auditName");
            return (Criteria) this;
        }

        public Criteria andAuditNameLessThan(String value) {
            addCriterion("audit_name <", value, "auditName");
            return (Criteria) this;
        }

        public Criteria andAuditNameLessThanOrEqualTo(String value) {
            addCriterion("audit_name <=", value, "auditName");
            return (Criteria) this;
        }

        public Criteria andAuditNameLike(String value) {
            addCriterion("audit_name like", value, "auditName");
            return (Criteria) this;
        }

        public Criteria andAuditNameNotLike(String value) {
            addCriterion("audit_name not like", value, "auditName");
            return (Criteria) this;
        }

        public Criteria andAuditNameIn(List<String> values) {
            addCriterion("audit_name in", values, "auditName");
            return (Criteria) this;
        }

        public Criteria andAuditNameNotIn(List<String> values) {
            addCriterion("audit_name not in", values, "auditName");
            return (Criteria) this;
        }

        public Criteria andAuditNameBetween(String value1, String value2) {
            addCriterion("audit_name between", value1, value2, "auditName");
            return (Criteria) this;
        }

        public Criteria andAuditNameNotBetween(String value1, String value2) {
            addCriterion("audit_name not between", value1, value2, "auditName");
            return (Criteria) this;
        }

        public Criteria andAuditIdentIsNull() {
            addCriterion("audit_ident is null");
            return (Criteria) this;
        }

        public Criteria andAuditIdentIsNotNull() {
            addCriterion("audit_ident is not null");
            return (Criteria) this;
        }

        public Criteria andAuditIdentEqualTo(String value) {
            addCriterion("audit_ident =", value, "auditIdent");
            return (Criteria) this;
        }

        public Criteria andAuditIdentNotEqualTo(String value) {
            addCriterion("audit_ident <>", value, "auditIdent");
            return (Criteria) this;
        }

        public Criteria andAuditIdentGreaterThan(String value) {
            addCriterion("audit_ident >", value, "auditIdent");
            return (Criteria) this;
        }

        public Criteria andAuditIdentGreaterThanOrEqualTo(String value) {
            addCriterion("audit_ident >=", value, "auditIdent");
            return (Criteria) this;
        }

        public Criteria andAuditIdentLessThan(String value) {
            addCriterion("audit_ident <", value, "auditIdent");
            return (Criteria) this;
        }

        public Criteria andAuditIdentLessThanOrEqualTo(String value) {
            addCriterion("audit_ident <=", value, "auditIdent");
            return (Criteria) this;
        }

        public Criteria andAuditIdentLike(String value) {
            addCriterion("audit_ident like", value, "auditIdent");
            return (Criteria) this;
        }

        public Criteria andAuditIdentNotLike(String value) {
            addCriterion("audit_ident not like", value, "auditIdent");
            return (Criteria) this;
        }

        public Criteria andAuditIdentIn(List<String> values) {
            addCriterion("audit_ident in", values, "auditIdent");
            return (Criteria) this;
        }

        public Criteria andAuditIdentNotIn(List<String> values) {
            addCriterion("audit_ident not in", values, "auditIdent");
            return (Criteria) this;
        }

        public Criteria andAuditIdentBetween(String value1, String value2) {
            addCriterion("audit_ident between", value1, value2, "auditIdent");
            return (Criteria) this;
        }

        public Criteria andAuditIdentNotBetween(String value1, String value2) {
            addCriterion("audit_ident not between", value1, value2, "auditIdent");
            return (Criteria) this;
        }

        public Criteria andAuditPhoneIsNull() {
            addCriterion("audit_phone is null");
            return (Criteria) this;
        }

        public Criteria andAuditPhoneIsNotNull() {
            addCriterion("audit_phone is not null");
            return (Criteria) this;
        }

        public Criteria andAuditPhoneEqualTo(String value) {
            addCriterion("audit_phone =", value, "auditPhone");
            return (Criteria) this;
        }

        public Criteria andAuditPhoneNotEqualTo(String value) {
            addCriterion("audit_phone <>", value, "auditPhone");
            return (Criteria) this;
        }

        public Criteria andAuditPhoneGreaterThan(String value) {
            addCriterion("audit_phone >", value, "auditPhone");
            return (Criteria) this;
        }

        public Criteria andAuditPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("audit_phone >=", value, "auditPhone");
            return (Criteria) this;
        }

        public Criteria andAuditPhoneLessThan(String value) {
            addCriterion("audit_phone <", value, "auditPhone");
            return (Criteria) this;
        }

        public Criteria andAuditPhoneLessThanOrEqualTo(String value) {
            addCriterion("audit_phone <=", value, "auditPhone");
            return (Criteria) this;
        }

        public Criteria andAuditPhoneLike(String value) {
            addCriterion("audit_phone like", value, "auditPhone");
            return (Criteria) this;
        }

        public Criteria andAuditPhoneNotLike(String value) {
            addCriterion("audit_phone not like", value, "auditPhone");
            return (Criteria) this;
        }

        public Criteria andAuditPhoneIn(List<String> values) {
            addCriterion("audit_phone in", values, "auditPhone");
            return (Criteria) this;
        }

        public Criteria andAuditPhoneNotIn(List<String> values) {
            addCriterion("audit_phone not in", values, "auditPhone");
            return (Criteria) this;
        }

        public Criteria andAuditPhoneBetween(String value1, String value2) {
            addCriterion("audit_phone between", value1, value2, "auditPhone");
            return (Criteria) this;
        }

        public Criteria andAuditPhoneNotBetween(String value1, String value2) {
            addCriterion("audit_phone not between", value1, value2, "auditPhone");
            return (Criteria) this;
        }

        public Criteria andAuditMailIsNull() {
            addCriterion("audit_mail is null");
            return (Criteria) this;
        }

        public Criteria andAuditMailIsNotNull() {
            addCriterion("audit_mail is not null");
            return (Criteria) this;
        }

        public Criteria andAuditMailEqualTo(String value) {
            addCriterion("audit_mail =", value, "auditMail");
            return (Criteria) this;
        }

        public Criteria andAuditMailNotEqualTo(String value) {
            addCriterion("audit_mail <>", value, "auditMail");
            return (Criteria) this;
        }

        public Criteria andAuditMailGreaterThan(String value) {
            addCriterion("audit_mail >", value, "auditMail");
            return (Criteria) this;
        }

        public Criteria andAuditMailGreaterThanOrEqualTo(String value) {
            addCriterion("audit_mail >=", value, "auditMail");
            return (Criteria) this;
        }

        public Criteria andAuditMailLessThan(String value) {
            addCriterion("audit_mail <", value, "auditMail");
            return (Criteria) this;
        }

        public Criteria andAuditMailLessThanOrEqualTo(String value) {
            addCriterion("audit_mail <=", value, "auditMail");
            return (Criteria) this;
        }

        public Criteria andAuditMailLike(String value) {
            addCriterion("audit_mail like", value, "auditMail");
            return (Criteria) this;
        }

        public Criteria andAuditMailNotLike(String value) {
            addCriterion("audit_mail not like", value, "auditMail");
            return (Criteria) this;
        }

        public Criteria andAuditMailIn(List<String> values) {
            addCriterion("audit_mail in", values, "auditMail");
            return (Criteria) this;
        }

        public Criteria andAuditMailNotIn(List<String> values) {
            addCriterion("audit_mail not in", values, "auditMail");
            return (Criteria) this;
        }

        public Criteria andAuditMailBetween(String value1, String value2) {
            addCriterion("audit_mail between", value1, value2, "auditMail");
            return (Criteria) this;
        }

        public Criteria andAuditMailNotBetween(String value1, String value2) {
            addCriterion("audit_mail not between", value1, value2, "auditMail");
            return (Criteria) this;
        }

        public Criteria andAuditDeptIsNull() {
            addCriterion("audit_dept is null");
            return (Criteria) this;
        }

        public Criteria andAuditDeptIsNotNull() {
            addCriterion("audit_dept is not null");
            return (Criteria) this;
        }

        public Criteria andAuditDeptEqualTo(String value) {
            addCriterion("audit_dept =", value, "auditDept");
            return (Criteria) this;
        }

        public Criteria andAuditDeptNotEqualTo(String value) {
            addCriterion("audit_dept <>", value, "auditDept");
            return (Criteria) this;
        }

        public Criteria andAuditDeptGreaterThan(String value) {
            addCriterion("audit_dept >", value, "auditDept");
            return (Criteria) this;
        }

        public Criteria andAuditDeptGreaterThanOrEqualTo(String value) {
            addCriterion("audit_dept >=", value, "auditDept");
            return (Criteria) this;
        }

        public Criteria andAuditDeptLessThan(String value) {
            addCriterion("audit_dept <", value, "auditDept");
            return (Criteria) this;
        }

        public Criteria andAuditDeptLessThanOrEqualTo(String value) {
            addCriterion("audit_dept <=", value, "auditDept");
            return (Criteria) this;
        }

        public Criteria andAuditDeptLike(String value) {
            addCriterion("audit_dept like", value, "auditDept");
            return (Criteria) this;
        }

        public Criteria andAuditDeptNotLike(String value) {
            addCriterion("audit_dept not like", value, "auditDept");
            return (Criteria) this;
        }

        public Criteria andAuditDeptIn(List<String> values) {
            addCriterion("audit_dept in", values, "auditDept");
            return (Criteria) this;
        }

        public Criteria andAuditDeptNotIn(List<String> values) {
            addCriterion("audit_dept not in", values, "auditDept");
            return (Criteria) this;
        }

        public Criteria andAuditDeptBetween(String value1, String value2) {
            addCriterion("audit_dept between", value1, value2, "auditDept");
            return (Criteria) this;
        }

        public Criteria andAuditDeptNotBetween(String value1, String value2) {
            addCriterion("audit_dept not between", value1, value2, "auditDept");
            return (Criteria) this;
        }

        public Criteria andVisitNameIsNull() {
            addCriterion("visit_name is null");
            return (Criteria) this;
        }

        public Criteria andVisitNameIsNotNull() {
            addCriterion("visit_name is not null");
            return (Criteria) this;
        }

        public Criteria andVisitNameEqualTo(String value) {
            addCriterion("visit_name =", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameNotEqualTo(String value) {
            addCriterion("visit_name <>", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameGreaterThan(String value) {
            addCriterion("visit_name >", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameGreaterThanOrEqualTo(String value) {
            addCriterion("visit_name >=", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameLessThan(String value) {
            addCriterion("visit_name <", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameLessThanOrEqualTo(String value) {
            addCriterion("visit_name <=", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameLike(String value) {
            addCriterion("visit_name like", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameNotLike(String value) {
            addCriterion("visit_name not like", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameIn(List<String> values) {
            addCriterion("visit_name in", values, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameNotIn(List<String> values) {
            addCriterion("visit_name not in", values, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameBetween(String value1, String value2) {
            addCriterion("visit_name between", value1, value2, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameNotBetween(String value1, String value2) {
            addCriterion("visit_name not between", value1, value2, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitIdentIsNull() {
            addCriterion("visit_ident is null");
            return (Criteria) this;
        }

        public Criteria andVisitIdentIsNotNull() {
            addCriterion("visit_ident is not null");
            return (Criteria) this;
        }

        public Criteria andVisitIdentEqualTo(String value) {
            addCriterion("visit_ident =", value, "visitIdent");
            return (Criteria) this;
        }

        public Criteria andVisitIdentNotEqualTo(String value) {
            addCriterion("visit_ident <>", value, "visitIdent");
            return (Criteria) this;
        }

        public Criteria andVisitIdentGreaterThan(String value) {
            addCriterion("visit_ident >", value, "visitIdent");
            return (Criteria) this;
        }

        public Criteria andVisitIdentGreaterThanOrEqualTo(String value) {
            addCriterion("visit_ident >=", value, "visitIdent");
            return (Criteria) this;
        }

        public Criteria andVisitIdentLessThan(String value) {
            addCriterion("visit_ident <", value, "visitIdent");
            return (Criteria) this;
        }

        public Criteria andVisitIdentLessThanOrEqualTo(String value) {
            addCriterion("visit_ident <=", value, "visitIdent");
            return (Criteria) this;
        }

        public Criteria andVisitIdentLike(String value) {
            addCriterion("visit_ident like", value, "visitIdent");
            return (Criteria) this;
        }

        public Criteria andVisitIdentNotLike(String value) {
            addCriterion("visit_ident not like", value, "visitIdent");
            return (Criteria) this;
        }

        public Criteria andVisitIdentIn(List<String> values) {
            addCriterion("visit_ident in", values, "visitIdent");
            return (Criteria) this;
        }

        public Criteria andVisitIdentNotIn(List<String> values) {
            addCriterion("visit_ident not in", values, "visitIdent");
            return (Criteria) this;
        }

        public Criteria andVisitIdentBetween(String value1, String value2) {
            addCriterion("visit_ident between", value1, value2, "visitIdent");
            return (Criteria) this;
        }

        public Criteria andVisitIdentNotBetween(String value1, String value2) {
            addCriterion("visit_ident not between", value1, value2, "visitIdent");
            return (Criteria) this;
        }

        public Criteria andVisitPhoneIsNull() {
            addCriterion("visit_phone is null");
            return (Criteria) this;
        }

        public Criteria andVisitPhoneIsNotNull() {
            addCriterion("visit_phone is not null");
            return (Criteria) this;
        }

        public Criteria andVisitPhoneEqualTo(String value) {
            addCriterion("visit_phone =", value, "visitPhone");
            return (Criteria) this;
        }

        public Criteria andVisitPhoneNotEqualTo(String value) {
            addCriterion("visit_phone <>", value, "visitPhone");
            return (Criteria) this;
        }

        public Criteria andVisitPhoneGreaterThan(String value) {
            addCriterion("visit_phone >", value, "visitPhone");
            return (Criteria) this;
        }

        public Criteria andVisitPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("visit_phone >=", value, "visitPhone");
            return (Criteria) this;
        }

        public Criteria andVisitPhoneLessThan(String value) {
            addCriterion("visit_phone <", value, "visitPhone");
            return (Criteria) this;
        }

        public Criteria andVisitPhoneLessThanOrEqualTo(String value) {
            addCriterion("visit_phone <=", value, "visitPhone");
            return (Criteria) this;
        }

        public Criteria andVisitPhoneLike(String value) {
            addCriterion("visit_phone like", value, "visitPhone");
            return (Criteria) this;
        }

        public Criteria andVisitPhoneNotLike(String value) {
            addCriterion("visit_phone not like", value, "visitPhone");
            return (Criteria) this;
        }

        public Criteria andVisitPhoneIn(List<String> values) {
            addCriterion("visit_phone in", values, "visitPhone");
            return (Criteria) this;
        }

        public Criteria andVisitPhoneNotIn(List<String> values) {
            addCriterion("visit_phone not in", values, "visitPhone");
            return (Criteria) this;
        }

        public Criteria andVisitPhoneBetween(String value1, String value2) {
            addCriterion("visit_phone between", value1, value2, "visitPhone");
            return (Criteria) this;
        }

        public Criteria andVisitPhoneNotBetween(String value1, String value2) {
            addCriterion("visit_phone not between", value1, value2, "visitPhone");
            return (Criteria) this;
        }

        public Criteria andVisitMailIsNull() {
            addCriterion("visit_mail is null");
            return (Criteria) this;
        }

        public Criteria andVisitMailIsNotNull() {
            addCriterion("visit_mail is not null");
            return (Criteria) this;
        }

        public Criteria andVisitMailEqualTo(String value) {
            addCriterion("visit_mail =", value, "visitMail");
            return (Criteria) this;
        }

        public Criteria andVisitMailNotEqualTo(String value) {
            addCriterion("visit_mail <>", value, "visitMail");
            return (Criteria) this;
        }

        public Criteria andVisitMailGreaterThan(String value) {
            addCriterion("visit_mail >", value, "visitMail");
            return (Criteria) this;
        }

        public Criteria andVisitMailGreaterThanOrEqualTo(String value) {
            addCriterion("visit_mail >=", value, "visitMail");
            return (Criteria) this;
        }

        public Criteria andVisitMailLessThan(String value) {
            addCriterion("visit_mail <", value, "visitMail");
            return (Criteria) this;
        }

        public Criteria andVisitMailLessThanOrEqualTo(String value) {
            addCriterion("visit_mail <=", value, "visitMail");
            return (Criteria) this;
        }

        public Criteria andVisitMailLike(String value) {
            addCriterion("visit_mail like", value, "visitMail");
            return (Criteria) this;
        }

        public Criteria andVisitMailNotLike(String value) {
            addCriterion("visit_mail not like", value, "visitMail");
            return (Criteria) this;
        }

        public Criteria andVisitMailIn(List<String> values) {
            addCriterion("visit_mail in", values, "visitMail");
            return (Criteria) this;
        }

        public Criteria andVisitMailNotIn(List<String> values) {
            addCriterion("visit_mail not in", values, "visitMail");
            return (Criteria) this;
        }

        public Criteria andVisitMailBetween(String value1, String value2) {
            addCriterion("visit_mail between", value1, value2, "visitMail");
            return (Criteria) this;
        }

        public Criteria andVisitMailNotBetween(String value1, String value2) {
            addCriterion("visit_mail not between", value1, value2, "visitMail");
            return (Criteria) this;
        }

        public Criteria andVisitDeptIsNull() {
            addCriterion("visit_dept is null");
            return (Criteria) this;
        }

        public Criteria andVisitDeptIsNotNull() {
            addCriterion("visit_dept is not null");
            return (Criteria) this;
        }

        public Criteria andVisitDeptEqualTo(String value) {
            addCriterion("visit_dept =", value, "visitDept");
            return (Criteria) this;
        }

        public Criteria andVisitDeptNotEqualTo(String value) {
            addCriterion("visit_dept <>", value, "visitDept");
            return (Criteria) this;
        }

        public Criteria andVisitDeptGreaterThan(String value) {
            addCriterion("visit_dept >", value, "visitDept");
            return (Criteria) this;
        }

        public Criteria andVisitDeptGreaterThanOrEqualTo(String value) {
            addCriterion("visit_dept >=", value, "visitDept");
            return (Criteria) this;
        }

        public Criteria andVisitDeptLessThan(String value) {
            addCriterion("visit_dept <", value, "visitDept");
            return (Criteria) this;
        }

        public Criteria andVisitDeptLessThanOrEqualTo(String value) {
            addCriterion("visit_dept <=", value, "visitDept");
            return (Criteria) this;
        }

        public Criteria andVisitDeptLike(String value) {
            addCriterion("visit_dept like", value, "visitDept");
            return (Criteria) this;
        }

        public Criteria andVisitDeptNotLike(String value) {
            addCriterion("visit_dept not like", value, "visitDept");
            return (Criteria) this;
        }

        public Criteria andVisitDeptIn(List<String> values) {
            addCriterion("visit_dept in", values, "visitDept");
            return (Criteria) this;
        }

        public Criteria andVisitDeptNotIn(List<String> values) {
            addCriterion("visit_dept not in", values, "visitDept");
            return (Criteria) this;
        }

        public Criteria andVisitDeptBetween(String value1, String value2) {
            addCriterion("visit_dept between", value1, value2, "visitDept");
            return (Criteria) this;
        }

        public Criteria andVisitDeptNotBetween(String value1, String value2) {
            addCriterion("visit_dept not between", value1, value2, "visitDept");
            return (Criteria) this;
        }

        public Criteria andStatNameIsNull() {
            addCriterion("stat_name is null");
            return (Criteria) this;
        }

        public Criteria andStatNameIsNotNull() {
            addCriterion("stat_name is not null");
            return (Criteria) this;
        }

        public Criteria andStatNameEqualTo(String value) {
            addCriterion("stat_name =", value, "statName");
            return (Criteria) this;
        }

        public Criteria andStatNameNotEqualTo(String value) {
            addCriterion("stat_name <>", value, "statName");
            return (Criteria) this;
        }

        public Criteria andStatNameGreaterThan(String value) {
            addCriterion("stat_name >", value, "statName");
            return (Criteria) this;
        }

        public Criteria andStatNameGreaterThanOrEqualTo(String value) {
            addCriterion("stat_name >=", value, "statName");
            return (Criteria) this;
        }

        public Criteria andStatNameLessThan(String value) {
            addCriterion("stat_name <", value, "statName");
            return (Criteria) this;
        }

        public Criteria andStatNameLessThanOrEqualTo(String value) {
            addCriterion("stat_name <=", value, "statName");
            return (Criteria) this;
        }

        public Criteria andStatNameLike(String value) {
            addCriterion("stat_name like", value, "statName");
            return (Criteria) this;
        }

        public Criteria andStatNameNotLike(String value) {
            addCriterion("stat_name not like", value, "statName");
            return (Criteria) this;
        }

        public Criteria andStatNameIn(List<String> values) {
            addCriterion("stat_name in", values, "statName");
            return (Criteria) this;
        }

        public Criteria andStatNameNotIn(List<String> values) {
            addCriterion("stat_name not in", values, "statName");
            return (Criteria) this;
        }

        public Criteria andStatNameBetween(String value1, String value2) {
            addCriterion("stat_name between", value1, value2, "statName");
            return (Criteria) this;
        }

        public Criteria andStatNameNotBetween(String value1, String value2) {
            addCriterion("stat_name not between", value1, value2, "statName");
            return (Criteria) this;
        }

        public Criteria andStatIdentIsNull() {
            addCriterion("stat_ident is null");
            return (Criteria) this;
        }

        public Criteria andStatIdentIsNotNull() {
            addCriterion("stat_ident is not null");
            return (Criteria) this;
        }

        public Criteria andStatIdentEqualTo(String value) {
            addCriterion("stat_ident =", value, "statIdent");
            return (Criteria) this;
        }

        public Criteria andStatIdentNotEqualTo(String value) {
            addCriterion("stat_ident <>", value, "statIdent");
            return (Criteria) this;
        }

        public Criteria andStatIdentGreaterThan(String value) {
            addCriterion("stat_ident >", value, "statIdent");
            return (Criteria) this;
        }

        public Criteria andStatIdentGreaterThanOrEqualTo(String value) {
            addCriterion("stat_ident >=", value, "statIdent");
            return (Criteria) this;
        }

        public Criteria andStatIdentLessThan(String value) {
            addCriterion("stat_ident <", value, "statIdent");
            return (Criteria) this;
        }

        public Criteria andStatIdentLessThanOrEqualTo(String value) {
            addCriterion("stat_ident <=", value, "statIdent");
            return (Criteria) this;
        }

        public Criteria andStatIdentLike(String value) {
            addCriterion("stat_ident like", value, "statIdent");
            return (Criteria) this;
        }

        public Criteria andStatIdentNotLike(String value) {
            addCriterion("stat_ident not like", value, "statIdent");
            return (Criteria) this;
        }

        public Criteria andStatIdentIn(List<String> values) {
            addCriterion("stat_ident in", values, "statIdent");
            return (Criteria) this;
        }

        public Criteria andStatIdentNotIn(List<String> values) {
            addCriterion("stat_ident not in", values, "statIdent");
            return (Criteria) this;
        }

        public Criteria andStatIdentBetween(String value1, String value2) {
            addCriterion("stat_ident between", value1, value2, "statIdent");
            return (Criteria) this;
        }

        public Criteria andStatIdentNotBetween(String value1, String value2) {
            addCriterion("stat_ident not between", value1, value2, "statIdent");
            return (Criteria) this;
        }

        public Criteria andStatPhoneIsNull() {
            addCriterion("stat_phone is null");
            return (Criteria) this;
        }

        public Criteria andStatPhoneIsNotNull() {
            addCriterion("stat_phone is not null");
            return (Criteria) this;
        }

        public Criteria andStatPhoneEqualTo(String value) {
            addCriterion("stat_phone =", value, "statPhone");
            return (Criteria) this;
        }

        public Criteria andStatPhoneNotEqualTo(String value) {
            addCriterion("stat_phone <>", value, "statPhone");
            return (Criteria) this;
        }

        public Criteria andStatPhoneGreaterThan(String value) {
            addCriterion("stat_phone >", value, "statPhone");
            return (Criteria) this;
        }

        public Criteria andStatPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("stat_phone >=", value, "statPhone");
            return (Criteria) this;
        }

        public Criteria andStatPhoneLessThan(String value) {
            addCriterion("stat_phone <", value, "statPhone");
            return (Criteria) this;
        }

        public Criteria andStatPhoneLessThanOrEqualTo(String value) {
            addCriterion("stat_phone <=", value, "statPhone");
            return (Criteria) this;
        }

        public Criteria andStatPhoneLike(String value) {
            addCriterion("stat_phone like", value, "statPhone");
            return (Criteria) this;
        }

        public Criteria andStatPhoneNotLike(String value) {
            addCriterion("stat_phone not like", value, "statPhone");
            return (Criteria) this;
        }

        public Criteria andStatPhoneIn(List<String> values) {
            addCriterion("stat_phone in", values, "statPhone");
            return (Criteria) this;
        }

        public Criteria andStatPhoneNotIn(List<String> values) {
            addCriterion("stat_phone not in", values, "statPhone");
            return (Criteria) this;
        }

        public Criteria andStatPhoneBetween(String value1, String value2) {
            addCriterion("stat_phone between", value1, value2, "statPhone");
            return (Criteria) this;
        }

        public Criteria andStatPhoneNotBetween(String value1, String value2) {
            addCriterion("stat_phone not between", value1, value2, "statPhone");
            return (Criteria) this;
        }

        public Criteria andStatMailIsNull() {
            addCriterion("stat_mail is null");
            return (Criteria) this;
        }

        public Criteria andStatMailIsNotNull() {
            addCriterion("stat_mail is not null");
            return (Criteria) this;
        }

        public Criteria andStatMailEqualTo(String value) {
            addCriterion("stat_mail =", value, "statMail");
            return (Criteria) this;
        }

        public Criteria andStatMailNotEqualTo(String value) {
            addCriterion("stat_mail <>", value, "statMail");
            return (Criteria) this;
        }

        public Criteria andStatMailGreaterThan(String value) {
            addCriterion("stat_mail >", value, "statMail");
            return (Criteria) this;
        }

        public Criteria andStatMailGreaterThanOrEqualTo(String value) {
            addCriterion("stat_mail >=", value, "statMail");
            return (Criteria) this;
        }

        public Criteria andStatMailLessThan(String value) {
            addCriterion("stat_mail <", value, "statMail");
            return (Criteria) this;
        }

        public Criteria andStatMailLessThanOrEqualTo(String value) {
            addCriterion("stat_mail <=", value, "statMail");
            return (Criteria) this;
        }

        public Criteria andStatMailLike(String value) {
            addCriterion("stat_mail like", value, "statMail");
            return (Criteria) this;
        }

        public Criteria andStatMailNotLike(String value) {
            addCriterion("stat_mail not like", value, "statMail");
            return (Criteria) this;
        }

        public Criteria andStatMailIn(List<String> values) {
            addCriterion("stat_mail in", values, "statMail");
            return (Criteria) this;
        }

        public Criteria andStatMailNotIn(List<String> values) {
            addCriterion("stat_mail not in", values, "statMail");
            return (Criteria) this;
        }

        public Criteria andStatMailBetween(String value1, String value2) {
            addCriterion("stat_mail between", value1, value2, "statMail");
            return (Criteria) this;
        }

        public Criteria andStatMailNotBetween(String value1, String value2) {
            addCriterion("stat_mail not between", value1, value2, "statMail");
            return (Criteria) this;
        }

        public Criteria andStatDeptIsNull() {
            addCriterion("stat_dept is null");
            return (Criteria) this;
        }

        public Criteria andStatDeptIsNotNull() {
            addCriterion("stat_dept is not null");
            return (Criteria) this;
        }

        public Criteria andStatDeptEqualTo(String value) {
            addCriterion("stat_dept =", value, "statDept");
            return (Criteria) this;
        }

        public Criteria andStatDeptNotEqualTo(String value) {
            addCriterion("stat_dept <>", value, "statDept");
            return (Criteria) this;
        }

        public Criteria andStatDeptGreaterThan(String value) {
            addCriterion("stat_dept >", value, "statDept");
            return (Criteria) this;
        }

        public Criteria andStatDeptGreaterThanOrEqualTo(String value) {
            addCriterion("stat_dept >=", value, "statDept");
            return (Criteria) this;
        }

        public Criteria andStatDeptLessThan(String value) {
            addCriterion("stat_dept <", value, "statDept");
            return (Criteria) this;
        }

        public Criteria andStatDeptLessThanOrEqualTo(String value) {
            addCriterion("stat_dept <=", value, "statDept");
            return (Criteria) this;
        }

        public Criteria andStatDeptLike(String value) {
            addCriterion("stat_dept like", value, "statDept");
            return (Criteria) this;
        }

        public Criteria andStatDeptNotLike(String value) {
            addCriterion("stat_dept not like", value, "statDept");
            return (Criteria) this;
        }

        public Criteria andStatDeptIn(List<String> values) {
            addCriterion("stat_dept in", values, "statDept");
            return (Criteria) this;
        }

        public Criteria andStatDeptNotIn(List<String> values) {
            addCriterion("stat_dept not in", values, "statDept");
            return (Criteria) this;
        }

        public Criteria andStatDeptBetween(String value1, String value2) {
            addCriterion("stat_dept between", value1, value2, "statDept");
            return (Criteria) this;
        }

        public Criteria andStatDeptNotBetween(String value1, String value2) {
            addCriterion("stat_dept not between", value1, value2, "statDept");
            return (Criteria) this;
        }

        public Criteria andIsNewIsNull() {
            addCriterion("is_new is null");
            return (Criteria) this;
        }

        public Criteria andIsNewIsNotNull() {
            addCriterion("is_new is not null");
            return (Criteria) this;
        }

        public Criteria andIsNewEqualTo(Integer value) {
            addCriterion("is_new =", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotEqualTo(Integer value) {
            addCriterion("is_new <>", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewGreaterThan(Integer value) {
            addCriterion("is_new >", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_new >=", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewLessThan(Integer value) {
            addCriterion("is_new <", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewLessThanOrEqualTo(Integer value) {
            addCriterion("is_new <=", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewIn(List<Integer> values) {
            addCriterion("is_new in", values, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotIn(List<Integer> values) {
            addCriterion("is_new not in", values, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewBetween(Integer value1, Integer value2) {
            addCriterion("is_new between", value1, value2, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotBetween(Integer value1, Integer value2) {
            addCriterion("is_new not between", value1, value2, "isNew");
            return (Criteria) this;
        }

        public Criteria andOrganIdIsNull() {
            addCriterion("organ_id is null");
            return (Criteria) this;
        }

        public Criteria andOrganIdIsNotNull() {
            addCriterion("organ_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrganIdEqualTo(Long value) {
            addCriterion("organ_id =", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdNotEqualTo(Long value) {
            addCriterion("organ_id <>", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdGreaterThan(Long value) {
            addCriterion("organ_id >", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdGreaterThanOrEqualTo(Long value) {
            addCriterion("organ_id >=", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdLessThan(Long value) {
            addCriterion("organ_id <", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdLessThanOrEqualTo(Long value) {
            addCriterion("organ_id <=", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdIn(List<Long> values) {
            addCriterion("organ_id in", values, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdNotIn(List<Long> values) {
            addCriterion("organ_id not in", values, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdBetween(Long value1, Long value2) {
            addCriterion("organ_id between", value1, value2, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdNotBetween(Long value1, Long value2) {
            addCriterion("organ_id not between", value1, value2, "organId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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