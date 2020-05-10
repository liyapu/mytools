package com.tusdao.control.model.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControlOrganInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ControlOrganInfoExample() {
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

        public Criteria andAppIdIsNull() {
            addCriterion("app_id is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(String value) {
            addCriterion("app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(String value) {
            addCriterion("app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(String value) {
            addCriterion("app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(String value) {
            addCriterion("app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(String value) {
            addCriterion("app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLike(String value) {
            addCriterion("app_id like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotLike(String value) {
            addCriterion("app_id not like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<String> values) {
            addCriterion("app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<String> values) {
            addCriterion("app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(String value1, String value2) {
            addCriterion("app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(String value1, String value2) {
            addCriterion("app_id not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNull() {
            addCriterion("app_secret is null");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNotNull() {
            addCriterion("app_secret is not null");
            return (Criteria) this;
        }

        public Criteria andAppSecretEqualTo(String value) {
            addCriterion("app_secret =", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotEqualTo(String value) {
            addCriterion("app_secret <>", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThan(String value) {
            addCriterion("app_secret >", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThanOrEqualTo(String value) {
            addCriterion("app_secret >=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThan(String value) {
            addCriterion("app_secret <", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThanOrEqualTo(String value) {
            addCriterion("app_secret <=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLike(String value) {
            addCriterion("app_secret like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotLike(String value) {
            addCriterion("app_secret not like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretIn(List<String> values) {
            addCriterion("app_secret in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotIn(List<String> values) {
            addCriterion("app_secret not in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretBetween(String value1, String value2) {
            addCriterion("app_secret between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotBetween(String value1, String value2) {
            addCriterion("app_secret not between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andOrganIdShowIsNull() {
            addCriterion("organ_id_show is null");
            return (Criteria) this;
        }

        public Criteria andOrganIdShowIsNotNull() {
            addCriterion("organ_id_show is not null");
            return (Criteria) this;
        }

        public Criteria andOrganIdShowEqualTo(String value) {
            addCriterion("organ_id_show =", value, "organIdShow");
            return (Criteria) this;
        }

        public Criteria andOrganIdShowNotEqualTo(String value) {
            addCriterion("organ_id_show <>", value, "organIdShow");
            return (Criteria) this;
        }

        public Criteria andOrganIdShowGreaterThan(String value) {
            addCriterion("organ_id_show >", value, "organIdShow");
            return (Criteria) this;
        }

        public Criteria andOrganIdShowGreaterThanOrEqualTo(String value) {
            addCriterion("organ_id_show >=", value, "organIdShow");
            return (Criteria) this;
        }

        public Criteria andOrganIdShowLessThan(String value) {
            addCriterion("organ_id_show <", value, "organIdShow");
            return (Criteria) this;
        }

        public Criteria andOrganIdShowLessThanOrEqualTo(String value) {
            addCriterion("organ_id_show <=", value, "organIdShow");
            return (Criteria) this;
        }

        public Criteria andOrganIdShowLike(String value) {
            addCriterion("organ_id_show like", value, "organIdShow");
            return (Criteria) this;
        }

        public Criteria andOrganIdShowNotLike(String value) {
            addCriterion("organ_id_show not like", value, "organIdShow");
            return (Criteria) this;
        }

        public Criteria andOrganIdShowIn(List<String> values) {
            addCriterion("organ_id_show in", values, "organIdShow");
            return (Criteria) this;
        }

        public Criteria andOrganIdShowNotIn(List<String> values) {
            addCriterion("organ_id_show not in", values, "organIdShow");
            return (Criteria) this;
        }

        public Criteria andOrganIdShowBetween(String value1, String value2) {
            addCriterion("organ_id_show between", value1, value2, "organIdShow");
            return (Criteria) this;
        }

        public Criteria andOrganIdShowNotBetween(String value1, String value2) {
            addCriterion("organ_id_show not between", value1, value2, "organIdShow");
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

        public Criteria andOrganParentIdIsNull() {
            addCriterion("organ_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andOrganParentIdIsNotNull() {
            addCriterion("organ_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrganParentIdEqualTo(Long value) {
            addCriterion("organ_parent_id =", value, "organParentId");
            return (Criteria) this;
        }

        public Criteria andOrganParentIdNotEqualTo(Long value) {
            addCriterion("organ_parent_id <>", value, "organParentId");
            return (Criteria) this;
        }

        public Criteria andOrganParentIdGreaterThan(Long value) {
            addCriterion("organ_parent_id >", value, "organParentId");
            return (Criteria) this;
        }

        public Criteria andOrganParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("organ_parent_id >=", value, "organParentId");
            return (Criteria) this;
        }

        public Criteria andOrganParentIdLessThan(Long value) {
            addCriterion("organ_parent_id <", value, "organParentId");
            return (Criteria) this;
        }

        public Criteria andOrganParentIdLessThanOrEqualTo(Long value) {
            addCriterion("organ_parent_id <=", value, "organParentId");
            return (Criteria) this;
        }

        public Criteria andOrganParentIdIn(List<Long> values) {
            addCriterion("organ_parent_id in", values, "organParentId");
            return (Criteria) this;
        }

        public Criteria andOrganParentIdNotIn(List<Long> values) {
            addCriterion("organ_parent_id not in", values, "organParentId");
            return (Criteria) this;
        }

        public Criteria andOrganParentIdBetween(Long value1, Long value2) {
            addCriterion("organ_parent_id between", value1, value2, "organParentId");
            return (Criteria) this;
        }

        public Criteria andOrganParentIdNotBetween(Long value1, Long value2) {
            addCriterion("organ_parent_id not between", value1, value2, "organParentId");
            return (Criteria) this;
        }

        public Criteria andChainUuidIsNull() {
            addCriterion("chain_uuid is null");
            return (Criteria) this;
        }

        public Criteria andChainUuidIsNotNull() {
            addCriterion("chain_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andChainUuidEqualTo(String value) {
            addCriterion("chain_uuid =", value, "chainUuid");
            return (Criteria) this;
        }

        public Criteria andChainUuidNotEqualTo(String value) {
            addCriterion("chain_uuid <>", value, "chainUuid");
            return (Criteria) this;
        }

        public Criteria andChainUuidGreaterThan(String value) {
            addCriterion("chain_uuid >", value, "chainUuid");
            return (Criteria) this;
        }

        public Criteria andChainUuidGreaterThanOrEqualTo(String value) {
            addCriterion("chain_uuid >=", value, "chainUuid");
            return (Criteria) this;
        }

        public Criteria andChainUuidLessThan(String value) {
            addCriterion("chain_uuid <", value, "chainUuid");
            return (Criteria) this;
        }

        public Criteria andChainUuidLessThanOrEqualTo(String value) {
            addCriterion("chain_uuid <=", value, "chainUuid");
            return (Criteria) this;
        }

        public Criteria andChainUuidLike(String value) {
            addCriterion("chain_uuid like", value, "chainUuid");
            return (Criteria) this;
        }

        public Criteria andChainUuidNotLike(String value) {
            addCriterion("chain_uuid not like", value, "chainUuid");
            return (Criteria) this;
        }

        public Criteria andChainUuidIn(List<String> values) {
            addCriterion("chain_uuid in", values, "chainUuid");
            return (Criteria) this;
        }

        public Criteria andChainUuidNotIn(List<String> values) {
            addCriterion("chain_uuid not in", values, "chainUuid");
            return (Criteria) this;
        }

        public Criteria andChainUuidBetween(String value1, String value2) {
            addCriterion("chain_uuid between", value1, value2, "chainUuid");
            return (Criteria) this;
        }

        public Criteria andChainUuidNotBetween(String value1, String value2) {
            addCriterion("chain_uuid not between", value1, value2, "chainUuid");
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

        public Criteria andOrganDescIsNull() {
            addCriterion("organ_desc is null");
            return (Criteria) this;
        }

        public Criteria andOrganDescIsNotNull() {
            addCriterion("organ_desc is not null");
            return (Criteria) this;
        }

        public Criteria andOrganDescEqualTo(String value) {
            addCriterion("organ_desc =", value, "organDesc");
            return (Criteria) this;
        }

        public Criteria andOrganDescNotEqualTo(String value) {
            addCriterion("organ_desc <>", value, "organDesc");
            return (Criteria) this;
        }

        public Criteria andOrganDescGreaterThan(String value) {
            addCriterion("organ_desc >", value, "organDesc");
            return (Criteria) this;
        }

        public Criteria andOrganDescGreaterThanOrEqualTo(String value) {
            addCriterion("organ_desc >=", value, "organDesc");
            return (Criteria) this;
        }

        public Criteria andOrganDescLessThan(String value) {
            addCriterion("organ_desc <", value, "organDesc");
            return (Criteria) this;
        }

        public Criteria andOrganDescLessThanOrEqualTo(String value) {
            addCriterion("organ_desc <=", value, "organDesc");
            return (Criteria) this;
        }

        public Criteria andOrganDescLike(String value) {
            addCriterion("organ_desc like", value, "organDesc");
            return (Criteria) this;
        }

        public Criteria andOrganDescNotLike(String value) {
            addCriterion("organ_desc not like", value, "organDesc");
            return (Criteria) this;
        }

        public Criteria andOrganDescIn(List<String> values) {
            addCriterion("organ_desc in", values, "organDesc");
            return (Criteria) this;
        }

        public Criteria andOrganDescNotIn(List<String> values) {
            addCriterion("organ_desc not in", values, "organDesc");
            return (Criteria) this;
        }

        public Criteria andOrganDescBetween(String value1, String value2) {
            addCriterion("organ_desc between", value1, value2, "organDesc");
            return (Criteria) this;
        }

        public Criteria andOrganDescNotBetween(String value1, String value2) {
            addCriterion("organ_desc not between", value1, value2, "organDesc");
            return (Criteria) this;
        }

        public Criteria andAgentAddressIsNull() {
            addCriterion("agent_address is null");
            return (Criteria) this;
        }

        public Criteria andAgentAddressIsNotNull() {
            addCriterion("agent_address is not null");
            return (Criteria) this;
        }

        public Criteria andAgentAddressEqualTo(String value) {
            addCriterion("agent_address =", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressNotEqualTo(String value) {
            addCriterion("agent_address <>", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressGreaterThan(String value) {
            addCriterion("agent_address >", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressGreaterThanOrEqualTo(String value) {
            addCriterion("agent_address >=", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressLessThan(String value) {
            addCriterion("agent_address <", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressLessThanOrEqualTo(String value) {
            addCriterion("agent_address <=", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressLike(String value) {
            addCriterion("agent_address like", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressNotLike(String value) {
            addCriterion("agent_address not like", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressIn(List<String> values) {
            addCriterion("agent_address in", values, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressNotIn(List<String> values) {
            addCriterion("agent_address not in", values, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressBetween(String value1, String value2) {
            addCriterion("agent_address between", value1, value2, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressNotBetween(String value1, String value2) {
            addCriterion("agent_address not between", value1, value2, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andOrganClassifyIsNull() {
            addCriterion("organ_classify is null");
            return (Criteria) this;
        }

        public Criteria andOrganClassifyIsNotNull() {
            addCriterion("organ_classify is not null");
            return (Criteria) this;
        }

        public Criteria andOrganClassifyEqualTo(Byte value) {
            addCriterion("organ_classify =", value, "organClassify");
            return (Criteria) this;
        }

        public Criteria andOrganClassifyNotEqualTo(Byte value) {
            addCriterion("organ_classify <>", value, "organClassify");
            return (Criteria) this;
        }

        public Criteria andOrganClassifyGreaterThan(Byte value) {
            addCriterion("organ_classify >", value, "organClassify");
            return (Criteria) this;
        }

        public Criteria andOrganClassifyGreaterThanOrEqualTo(Byte value) {
            addCriterion("organ_classify >=", value, "organClassify");
            return (Criteria) this;
        }

        public Criteria andOrganClassifyLessThan(Byte value) {
            addCriterion("organ_classify <", value, "organClassify");
            return (Criteria) this;
        }

        public Criteria andOrganClassifyLessThanOrEqualTo(Byte value) {
            addCriterion("organ_classify <=", value, "organClassify");
            return (Criteria) this;
        }

        public Criteria andOrganClassifyIn(List<Byte> values) {
            addCriterion("organ_classify in", values, "organClassify");
            return (Criteria) this;
        }

        public Criteria andOrganClassifyNotIn(List<Byte> values) {
            addCriterion("organ_classify not in", values, "organClassify");
            return (Criteria) this;
        }

        public Criteria andOrganClassifyBetween(Byte value1, Byte value2) {
            addCriterion("organ_classify between", value1, value2, "organClassify");
            return (Criteria) this;
        }

        public Criteria andOrganClassifyNotBetween(Byte value1, Byte value2) {
            addCriterion("organ_classify not between", value1, value2, "organClassify");
            return (Criteria) this;
        }

        public Criteria andOrganNatureIsNull() {
            addCriterion("organ_nature is null");
            return (Criteria) this;
        }

        public Criteria andOrganNatureIsNotNull() {
            addCriterion("organ_nature is not null");
            return (Criteria) this;
        }

        public Criteria andOrganNatureEqualTo(Byte value) {
            addCriterion("organ_nature =", value, "organNature");
            return (Criteria) this;
        }

        public Criteria andOrganNatureNotEqualTo(Byte value) {
            addCriterion("organ_nature <>", value, "organNature");
            return (Criteria) this;
        }

        public Criteria andOrganNatureGreaterThan(Byte value) {
            addCriterion("organ_nature >", value, "organNature");
            return (Criteria) this;
        }

        public Criteria andOrganNatureGreaterThanOrEqualTo(Byte value) {
            addCriterion("organ_nature >=", value, "organNature");
            return (Criteria) this;
        }

        public Criteria andOrganNatureLessThan(Byte value) {
            addCriterion("organ_nature <", value, "organNature");
            return (Criteria) this;
        }

        public Criteria andOrganNatureLessThanOrEqualTo(Byte value) {
            addCriterion("organ_nature <=", value, "organNature");
            return (Criteria) this;
        }

        public Criteria andOrganNatureIn(List<Byte> values) {
            addCriterion("organ_nature in", values, "organNature");
            return (Criteria) this;
        }

        public Criteria andOrganNatureNotIn(List<Byte> values) {
            addCriterion("organ_nature not in", values, "organNature");
            return (Criteria) this;
        }

        public Criteria andOrganNatureBetween(Byte value1, Byte value2) {
            addCriterion("organ_nature between", value1, value2, "organNature");
            return (Criteria) this;
        }

        public Criteria andOrganNatureNotBetween(Byte value1, Byte value2) {
            addCriterion("organ_nature not between", value1, value2, "organNature");
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

        public Criteria andHemopathyIsNull() {
            addCriterion("hemopathy is null");
            return (Criteria) this;
        }

        public Criteria andHemopathyIsNotNull() {
            addCriterion("hemopathy is not null");
            return (Criteria) this;
        }

        public Criteria andHemopathyEqualTo(Byte value) {
            addCriterion("hemopathy =", value, "hemopathy");
            return (Criteria) this;
        }

        public Criteria andHemopathyNotEqualTo(Byte value) {
            addCriterion("hemopathy <>", value, "hemopathy");
            return (Criteria) this;
        }

        public Criteria andHemopathyGreaterThan(Byte value) {
            addCriterion("hemopathy >", value, "hemopathy");
            return (Criteria) this;
        }

        public Criteria andHemopathyGreaterThanOrEqualTo(Byte value) {
            addCriterion("hemopathy >=", value, "hemopathy");
            return (Criteria) this;
        }

        public Criteria andHemopathyLessThan(Byte value) {
            addCriterion("hemopathy <", value, "hemopathy");
            return (Criteria) this;
        }

        public Criteria andHemopathyLessThanOrEqualTo(Byte value) {
            addCriterion("hemopathy <=", value, "hemopathy");
            return (Criteria) this;
        }

        public Criteria andHemopathyIn(List<Byte> values) {
            addCriterion("hemopathy in", values, "hemopathy");
            return (Criteria) this;
        }

        public Criteria andHemopathyNotIn(List<Byte> values) {
            addCriterion("hemopathy not in", values, "hemopathy");
            return (Criteria) this;
        }

        public Criteria andHemopathyBetween(Byte value1, Byte value2) {
            addCriterion("hemopathy between", value1, value2, "hemopathy");
            return (Criteria) this;
        }

        public Criteria andHemopathyNotBetween(Byte value1, Byte value2) {
            addCriterion("hemopathy not between", value1, value2, "hemopathy");
            return (Criteria) this;
        }

        public Criteria andSolidTumorIsNull() {
            addCriterion("solid_tumor is null");
            return (Criteria) this;
        }

        public Criteria andSolidTumorIsNotNull() {
            addCriterion("solid_tumor is not null");
            return (Criteria) this;
        }

        public Criteria andSolidTumorEqualTo(Byte value) {
            addCriterion("solid_tumor =", value, "solidTumor");
            return (Criteria) this;
        }

        public Criteria andSolidTumorNotEqualTo(Byte value) {
            addCriterion("solid_tumor <>", value, "solidTumor");
            return (Criteria) this;
        }

        public Criteria andSolidTumorGreaterThan(Byte value) {
            addCriterion("solid_tumor >", value, "solidTumor");
            return (Criteria) this;
        }

        public Criteria andSolidTumorGreaterThanOrEqualTo(Byte value) {
            addCriterion("solid_tumor >=", value, "solidTumor");
            return (Criteria) this;
        }

        public Criteria andSolidTumorLessThan(Byte value) {
            addCriterion("solid_tumor <", value, "solidTumor");
            return (Criteria) this;
        }

        public Criteria andSolidTumorLessThanOrEqualTo(Byte value) {
            addCriterion("solid_tumor <=", value, "solidTumor");
            return (Criteria) this;
        }

        public Criteria andSolidTumorIn(List<Byte> values) {
            addCriterion("solid_tumor in", values, "solidTumor");
            return (Criteria) this;
        }

        public Criteria andSolidTumorNotIn(List<Byte> values) {
            addCriterion("solid_tumor not in", values, "solidTumor");
            return (Criteria) this;
        }

        public Criteria andSolidTumorBetween(Byte value1, Byte value2) {
            addCriterion("solid_tumor between", value1, value2, "solidTumor");
            return (Criteria) this;
        }

        public Criteria andSolidTumorNotBetween(Byte value1, Byte value2) {
            addCriterion("solid_tumor not between", value1, value2, "solidTumor");
            return (Criteria) this;
        }

        public Criteria andNationalPointIsNull() {
            addCriterion("national_point is null");
            return (Criteria) this;
        }

        public Criteria andNationalPointIsNotNull() {
            addCriterion("national_point is not null");
            return (Criteria) this;
        }

        public Criteria andNationalPointEqualTo(Byte value) {
            addCriterion("national_point =", value, "nationalPoint");
            return (Criteria) this;
        }

        public Criteria andNationalPointNotEqualTo(Byte value) {
            addCriterion("national_point <>", value, "nationalPoint");
            return (Criteria) this;
        }

        public Criteria andNationalPointGreaterThan(Byte value) {
            addCriterion("national_point >", value, "nationalPoint");
            return (Criteria) this;
        }

        public Criteria andNationalPointGreaterThanOrEqualTo(Byte value) {
            addCriterion("national_point >=", value, "nationalPoint");
            return (Criteria) this;
        }

        public Criteria andNationalPointLessThan(Byte value) {
            addCriterion("national_point <", value, "nationalPoint");
            return (Criteria) this;
        }

        public Criteria andNationalPointLessThanOrEqualTo(Byte value) {
            addCriterion("national_point <=", value, "nationalPoint");
            return (Criteria) this;
        }

        public Criteria andNationalPointIn(List<Byte> values) {
            addCriterion("national_point in", values, "nationalPoint");
            return (Criteria) this;
        }

        public Criteria andNationalPointNotIn(List<Byte> values) {
            addCriterion("national_point not in", values, "nationalPoint");
            return (Criteria) this;
        }

        public Criteria andNationalPointBetween(Byte value1, Byte value2) {
            addCriterion("national_point between", value1, value2, "nationalPoint");
            return (Criteria) this;
        }

        public Criteria andNationalPointNotBetween(Byte value1, Byte value2) {
            addCriterion("national_point not between", value1, value2, "nationalPoint");
            return (Criteria) this;
        }

        public Criteria andFotonCenterIsNull() {
            addCriterion("foton_center is null");
            return (Criteria) this;
        }

        public Criteria andFotonCenterIsNotNull() {
            addCriterion("foton_center is not null");
            return (Criteria) this;
        }

        public Criteria andFotonCenterEqualTo(Byte value) {
            addCriterion("foton_center =", value, "fotonCenter");
            return (Criteria) this;
        }

        public Criteria andFotonCenterNotEqualTo(Byte value) {
            addCriterion("foton_center <>", value, "fotonCenter");
            return (Criteria) this;
        }

        public Criteria andFotonCenterGreaterThan(Byte value) {
            addCriterion("foton_center >", value, "fotonCenter");
            return (Criteria) this;
        }

        public Criteria andFotonCenterGreaterThanOrEqualTo(Byte value) {
            addCriterion("foton_center >=", value, "fotonCenter");
            return (Criteria) this;
        }

        public Criteria andFotonCenterLessThan(Byte value) {
            addCriterion("foton_center <", value, "fotonCenter");
            return (Criteria) this;
        }

        public Criteria andFotonCenterLessThanOrEqualTo(Byte value) {
            addCriterion("foton_center <=", value, "fotonCenter");
            return (Criteria) this;
        }

        public Criteria andFotonCenterIn(List<Byte> values) {
            addCriterion("foton_center in", values, "fotonCenter");
            return (Criteria) this;
        }

        public Criteria andFotonCenterNotIn(List<Byte> values) {
            addCriterion("foton_center not in", values, "fotonCenter");
            return (Criteria) this;
        }

        public Criteria andFotonCenterBetween(Byte value1, Byte value2) {
            addCriterion("foton_center between", value1, value2, "fotonCenter");
            return (Criteria) this;
        }

        public Criteria andFotonCenterNotBetween(Byte value1, Byte value2) {
            addCriterion("foton_center not between", value1, value2, "fotonCenter");
            return (Criteria) this;
        }

        public Criteria andRegisterIsNull() {
            addCriterion("register is null");
            return (Criteria) this;
        }

        public Criteria andRegisterIsNotNull() {
            addCriterion("register is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterEqualTo(Byte value) {
            addCriterion("register =", value, "register");
            return (Criteria) this;
        }

        public Criteria andRegisterNotEqualTo(Byte value) {
            addCriterion("register <>", value, "register");
            return (Criteria) this;
        }

        public Criteria andRegisterGreaterThan(Byte value) {
            addCriterion("register >", value, "register");
            return (Criteria) this;
        }

        public Criteria andRegisterGreaterThanOrEqualTo(Byte value) {
            addCriterion("register >=", value, "register");
            return (Criteria) this;
        }

        public Criteria andRegisterLessThan(Byte value) {
            addCriterion("register <", value, "register");
            return (Criteria) this;
        }

        public Criteria andRegisterLessThanOrEqualTo(Byte value) {
            addCriterion("register <=", value, "register");
            return (Criteria) this;
        }

        public Criteria andRegisterIn(List<Byte> values) {
            addCriterion("register in", values, "register");
            return (Criteria) this;
        }

        public Criteria andRegisterNotIn(List<Byte> values) {
            addCriterion("register not in", values, "register");
            return (Criteria) this;
        }

        public Criteria andRegisterBetween(Byte value1, Byte value2) {
            addCriterion("register between", value1, value2, "register");
            return (Criteria) this;
        }

        public Criteria andRegisterNotBetween(Byte value1, Byte value2) {
            addCriterion("register not between", value1, value2, "register");
            return (Criteria) this;
        }

        public Criteria andOrganStatusIsNull() {
            addCriterion("organ_status is null");
            return (Criteria) this;
        }

        public Criteria andOrganStatusIsNotNull() {
            addCriterion("organ_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrganStatusEqualTo(Byte value) {
            addCriterion("organ_status =", value, "organStatus");
            return (Criteria) this;
        }

        public Criteria andOrganStatusNotEqualTo(Byte value) {
            addCriterion("organ_status <>", value, "organStatus");
            return (Criteria) this;
        }

        public Criteria andOrganStatusGreaterThan(Byte value) {
            addCriterion("organ_status >", value, "organStatus");
            return (Criteria) this;
        }

        public Criteria andOrganStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("organ_status >=", value, "organStatus");
            return (Criteria) this;
        }

        public Criteria andOrganStatusLessThan(Byte value) {
            addCriterion("organ_status <", value, "organStatus");
            return (Criteria) this;
        }

        public Criteria andOrganStatusLessThanOrEqualTo(Byte value) {
            addCriterion("organ_status <=", value, "organStatus");
            return (Criteria) this;
        }

        public Criteria andOrganStatusIn(List<Byte> values) {
            addCriterion("organ_status in", values, "organStatus");
            return (Criteria) this;
        }

        public Criteria andOrganStatusNotIn(List<Byte> values) {
            addCriterion("organ_status not in", values, "organStatus");
            return (Criteria) this;
        }

        public Criteria andOrganStatusBetween(Byte value1, Byte value2) {
            addCriterion("organ_status between", value1, value2, "organStatus");
            return (Criteria) this;
        }

        public Criteria andOrganStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("organ_status not between", value1, value2, "organStatus");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(Long value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(Long value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(Long value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(Long value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(Long value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(Long value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<Long> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<Long> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(Long value1, Long value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(Long value1, Long value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
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

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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