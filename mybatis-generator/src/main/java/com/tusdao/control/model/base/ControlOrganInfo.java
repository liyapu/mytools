package com.tusdao.control.model.base;

import java.util.Date;

public class ControlOrganInfo {
    private Long organId;

    private String organCode;

    private Long organParentId;

    private String chainUuid;

    private String organName;

    private String organDesc;

    private String agentAddress;

    private String organClassify;

    private String organNature;

    private String organProvince;

    private String organCity;

    private String legal;

    private String hemopathy;

    private String solidTumor;

    private String nationalPoint;

    private String fotonCenter;

    private Byte organStatus;

    private Long createUser;

    private Date createTime;

    private Date updateTime;

    public Long getOrganId() {
        return organId;
    }

    public void setOrganId(Long organId) {
        this.organId = organId;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode == null ? null : organCode.trim();
    }

    public Long getOrganParentId() {
        return organParentId;
    }

    public void setOrganParentId(Long organParentId) {
        this.organParentId = organParentId;
    }

    public String getChainUuid() {
        return chainUuid;
    }

    public void setChainUuid(String chainUuid) {
        this.chainUuid = chainUuid == null ? null : chainUuid.trim();
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName == null ? null : organName.trim();
    }

    public String getOrganDesc() {
        return organDesc;
    }

    public void setOrganDesc(String organDesc) {
        this.organDesc = organDesc == null ? null : organDesc.trim();
    }

    public String getAgentAddress() {
        return agentAddress;
    }

    public void setAgentAddress(String agentAddress) {
        this.agentAddress = agentAddress == null ? null : agentAddress.trim();
    }

    public String getOrganClassify() {
        return organClassify;
    }

    public void setOrganClassify(String organClassify) {
        this.organClassify = organClassify == null ? null : organClassify.trim();
    }

    public String getOrganNature() {
        return organNature;
    }

    public void setOrganNature(String organNature) {
        this.organNature = organNature == null ? null : organNature.trim();
    }

    public String getOrganProvince() {
        return organProvince;
    }

    public void setOrganProvince(String organProvince) {
        this.organProvince = organProvince == null ? null : organProvince.trim();
    }

    public String getOrganCity() {
        return organCity;
    }

    public void setOrganCity(String organCity) {
        this.organCity = organCity == null ? null : organCity.trim();
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal == null ? null : legal.trim();
    }

    public String getHemopathy() {
        return hemopathy;
    }

    public void setHemopathy(String hemopathy) {
        this.hemopathy = hemopathy == null ? null : hemopathy.trim();
    }

    public String getSolidTumor() {
        return solidTumor;
    }

    public void setSolidTumor(String solidTumor) {
        this.solidTumor = solidTumor == null ? null : solidTumor.trim();
    }

    public String getNationalPoint() {
        return nationalPoint;
    }

    public void setNationalPoint(String nationalPoint) {
        this.nationalPoint = nationalPoint == null ? null : nationalPoint.trim();
    }

    public String getFotonCenter() {
        return fotonCenter;
    }

    public void setFotonCenter(String fotonCenter) {
        this.fotonCenter = fotonCenter == null ? null : fotonCenter.trim();
    }

    public Byte getOrganStatus() {
        return organStatus;
    }

    public void setOrganStatus(Byte organStatus) {
        this.organStatus = organStatus;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}