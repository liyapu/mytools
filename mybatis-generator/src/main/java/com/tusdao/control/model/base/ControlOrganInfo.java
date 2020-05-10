package com.tusdao.control.model.base;

import java.util.Date;

/**
 * @author liyapu
 * @date 2020-05-08 01:53:31
 */
public class ControlOrganInfo {
    private Long organId;

    private String appId;

    private String appSecret;

    private String organIdShow;

    private String organCode;

    private Long organParentId;

    private String chainUuid;

    private String organName;

    private String organDesc;

    private String agentAddress;

    private Byte organClassify;

    private Byte organNature;

    private String organProvince;

    private String organCity;

    private String legal;

    private Byte hemopathy;

    private Byte solidTumor;

    private Byte nationalPoint;

    private Byte fotonCenter;

    private Byte register;

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    public String getOrganIdShow() {
        return organIdShow;
    }

    public void setOrganIdShow(String organIdShow) {
        this.organIdShow = organIdShow == null ? null : organIdShow.trim();
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

    public Byte getOrganClassify() {
        return organClassify;
    }

    public void setOrganClassify(Byte organClassify) {
        this.organClassify = organClassify;
    }

    public Byte getOrganNature() {
        return organNature;
    }

    public void setOrganNature(Byte organNature) {
        this.organNature = organNature;
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

    public Byte getHemopathy() {
        return hemopathy;
    }

    public void setHemopathy(Byte hemopathy) {
        this.hemopathy = hemopathy;
    }

    public Byte getSolidTumor() {
        return solidTumor;
    }

    public void setSolidTumor(Byte solidTumor) {
        this.solidTumor = solidTumor;
    }

    public Byte getNationalPoint() {
        return nationalPoint;
    }

    public void setNationalPoint(Byte nationalPoint) {
        this.nationalPoint = nationalPoint;
    }

    public Byte getFotonCenter() {
        return fotonCenter;
    }

    public void setFotonCenter(Byte fotonCenter) {
        this.fotonCenter = fotonCenter;
    }

    public Byte getRegister() {
        return register;
    }

    public void setRegister(Byte register) {
        this.register = register;
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