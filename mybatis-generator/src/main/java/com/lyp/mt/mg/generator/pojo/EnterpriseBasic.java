package com.lyp.mt.mg.generator.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 企业基本信息
 *
 * @author liyapu
 * @date 2019-10-10 04:17:38
 */
@Data
@EqualsAndHashCode
@ToString
public class EnterpriseBasic {
    

    private Long id;

    /**
     * 企业业务关联id
     */
    private String eid;

    /**
     * 企业logo uri
     */
    private String logo;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 英文名
     */
    private String nameEn;

    /**
     * 曾用名
     */
    private String oldname;

    /**
     * 企业产品信息
     */
    private String product;

    /**
     * 企业联系方式
     */
    private String phone;

    /**
     * 统一社会信用代码
     */
    private String creditCode;

    /**
     * 注册号
     */
    private String registNumber;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 企业类型
     */
    private String type;

    /**
     * 法定代表人
     */
    private String legalPerson;

    /**
     * 注册资本
     */
    private String registCapital;

    /**
     * 实缴资本
     */
    private String actualCapital;

    /**
     * 企业地址
     */
    private String address;

    /**
     * 经营范围
     */
    private String businessScope;

    /**
     * 组织机构代码
     */
    private String organCode;

    /**
     * 经营状态 1在营 2开业 3在册 4存续...
     */
    private String businessStatus;

    /**
     * 成立时间
     */
    private String establishTime;

    /**
     * 营业期限
     */
    private String operatingPeriod;

    /**
     * 核准日期
     */
    private String issueDate;

    /**
     * 登记机关
     */
    private String registOrgan;

    /**
     * 参保人数
     */
    private String insurePerson;

    /**
     * 人员规模
     */
    private String scale;

    /**
     * 所属地区
     */
    private String area;

    /**
     * 网址
     */
    private String website;

    /**
     * 邮箱
     */
    private String email;
}