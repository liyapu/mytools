package com.tusdao.control.model.base;

/**
 * @author liyapu
 * @date 2019-12-21 08:56:07
 */
public class ApplyReport {
    private Long id;

    private String organCode;

    private String organName;

    private String legal;

    private Integer originalLogo;

    private Integer alreadyOriginal;

    private String organProvince;

    private String organProvinceName;

    private String organCity;

    private String organCityName;

    private String newspaper;

    private String director;

    private String spaperPhone;

    private String spaperMail;

    private String headName;

    private String headDept;

    private String headPhone;

    private String headMail;

    private String entryName;

    private String entryIdent;

    private String entryPhone;

    private String entryMail;

    private String entryDept;

    private String auditName;

    private String auditIdent;

    private String auditPhone;

    private String auditMail;

    private String auditDept;

    private String visitName;

    private String visitIdent;

    private String visitPhone;

    private String visitMail;

    private String visitDept;

    private String statName;

    private String statIdent;

    private String statPhone;

    private String statMail;

    private String statDept;

    private Integer isNew;

    private Long organId;

    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode == null ? null : organCode.trim();
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName == null ? null : organName.trim();
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal == null ? null : legal.trim();
    }

    public Integer getOriginalLogo() {
        return originalLogo;
    }

    public void setOriginalLogo(Integer originalLogo) {
        this.originalLogo = originalLogo;
    }

    public Integer getAlreadyOriginal() {
        return alreadyOriginal;
    }

    public void setAlreadyOriginal(Integer alreadyOriginal) {
        this.alreadyOriginal = alreadyOriginal;
    }

    public String getOrganProvince() {
        return organProvince;
    }

    public void setOrganProvince(String organProvince) {
        this.organProvince = organProvince == null ? null : organProvince.trim();
    }

    public String getOrganProvinceName() {
        return organProvinceName;
    }

    public void setOrganProvinceName(String organProvinceName) {
        this.organProvinceName = organProvinceName == null ? null : organProvinceName.trim();
    }

    public String getOrganCity() {
        return organCity;
    }

    public void setOrganCity(String organCity) {
        this.organCity = organCity == null ? null : organCity.trim();
    }

    public String getOrganCityName() {
        return organCityName;
    }

    public void setOrganCityName(String organCityName) {
        this.organCityName = organCityName == null ? null : organCityName.trim();
    }

    public String getNewspaper() {
        return newspaper;
    }

    public void setNewspaper(String newspaper) {
        this.newspaper = newspaper == null ? null : newspaper.trim();
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director == null ? null : director.trim();
    }

    public String getSpaperPhone() {
        return spaperPhone;
    }

    public void setSpaperPhone(String spaperPhone) {
        this.spaperPhone = spaperPhone == null ? null : spaperPhone.trim();
    }

    public String getSpaperMail() {
        return spaperMail;
    }

    public void setSpaperMail(String spaperMail) {
        this.spaperMail = spaperMail == null ? null : spaperMail.trim();
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName == null ? null : headName.trim();
    }

    public String getHeadDept() {
        return headDept;
    }

    public void setHeadDept(String headDept) {
        this.headDept = headDept == null ? null : headDept.trim();
    }

    public String getHeadPhone() {
        return headPhone;
    }

    public void setHeadPhone(String headPhone) {
        this.headPhone = headPhone == null ? null : headPhone.trim();
    }

    public String getHeadMail() {
        return headMail;
    }

    public void setHeadMail(String headMail) {
        this.headMail = headMail == null ? null : headMail.trim();
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName == null ? null : entryName.trim();
    }

    public String getEntryIdent() {
        return entryIdent;
    }

    public void setEntryIdent(String entryIdent) {
        this.entryIdent = entryIdent == null ? null : entryIdent.trim();
    }

    public String getEntryPhone() {
        return entryPhone;
    }

    public void setEntryPhone(String entryPhone) {
        this.entryPhone = entryPhone == null ? null : entryPhone.trim();
    }

    public String getEntryMail() {
        return entryMail;
    }

    public void setEntryMail(String entryMail) {
        this.entryMail = entryMail == null ? null : entryMail.trim();
    }

    public String getEntryDept() {
        return entryDept;
    }

    public void setEntryDept(String entryDept) {
        this.entryDept = entryDept == null ? null : entryDept.trim();
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName == null ? null : auditName.trim();
    }

    public String getAuditIdent() {
        return auditIdent;
    }

    public void setAuditIdent(String auditIdent) {
        this.auditIdent = auditIdent == null ? null : auditIdent.trim();
    }

    public String getAuditPhone() {
        return auditPhone;
    }

    public void setAuditPhone(String auditPhone) {
        this.auditPhone = auditPhone == null ? null : auditPhone.trim();
    }

    public String getAuditMail() {
        return auditMail;
    }

    public void setAuditMail(String auditMail) {
        this.auditMail = auditMail == null ? null : auditMail.trim();
    }

    public String getAuditDept() {
        return auditDept;
    }

    public void setAuditDept(String auditDept) {
        this.auditDept = auditDept == null ? null : auditDept.trim();
    }

    public String getVisitName() {
        return visitName;
    }

    public void setVisitName(String visitName) {
        this.visitName = visitName == null ? null : visitName.trim();
    }

    public String getVisitIdent() {
        return visitIdent;
    }

    public void setVisitIdent(String visitIdent) {
        this.visitIdent = visitIdent == null ? null : visitIdent.trim();
    }

    public String getVisitPhone() {
        return visitPhone;
    }

    public void setVisitPhone(String visitPhone) {
        this.visitPhone = visitPhone == null ? null : visitPhone.trim();
    }

    public String getVisitMail() {
        return visitMail;
    }

    public void setVisitMail(String visitMail) {
        this.visitMail = visitMail == null ? null : visitMail.trim();
    }

    public String getVisitDept() {
        return visitDept;
    }

    public void setVisitDept(String visitDept) {
        this.visitDept = visitDept == null ? null : visitDept.trim();
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName == null ? null : statName.trim();
    }

    public String getStatIdent() {
        return statIdent;
    }

    public void setStatIdent(String statIdent) {
        this.statIdent = statIdent == null ? null : statIdent.trim();
    }

    public String getStatPhone() {
        return statPhone;
    }

    public void setStatPhone(String statPhone) {
        this.statPhone = statPhone == null ? null : statPhone.trim();
    }

    public String getStatMail() {
        return statMail;
    }

    public void setStatMail(String statMail) {
        this.statMail = statMail == null ? null : statMail.trim();
    }

    public String getStatDept() {
        return statDept;
    }

    public void setStatDept(String statDept) {
        this.statDept = statDept == null ? null : statDept.trim();
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Long getOrganId() {
        return organId;
    }

    public void setOrganId(Long organId) {
        this.organId = organId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}