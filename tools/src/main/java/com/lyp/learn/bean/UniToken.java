package com.lyp.learn.bean;


import java.io.Serializable;
import java.util.List;

/**
 * 验证实体类
 * <p>
 * Created with IntelliJ IDEA.
 * Description:
 * User: Anzai
 * Date: 2018-01-05
 * Time: 10:06
 */
public class UniToken implements Serializable {
    private static final long serialVersionUID = 2687180341537772470L;

    private Long userId;

    private String userIdShow;

    private String userName;

    private String userPhone;

    private String accountName;

    private Long organId;

    private String organIdShow;

    private String organCode;

    private String organName;

    private List<Long> role;

    private String token;

    private Long expireTime;


    public Long getUserId() {
        return userId;
    }

    public UniToken setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UniToken setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public UniToken setUserPhone(String userPhone) {
        this.userPhone = userPhone;
        return this;
    }

    public String getAccountName() {
        return accountName;
    }

    public UniToken setAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public Long getOrganId() {
        return organId;
    }

    public UniToken setOrganId(Long organId) {
        this.organId = organId;
        return this;
    }

    public String getUserIdShow() {
        return userIdShow;
    }

    public UniToken setUserIdShow(String userIdShow) {
        this.userIdShow = userIdShow;
        return this;
    }

    public String getOrganIdShow() {
        return organIdShow;
    }

    public UniToken setOrganIdShow(String organIdShow) {
        this.organIdShow = organIdShow;
        return this;
    }

    public String getOrganCode() {
        return organCode;
    }

    public UniToken setOrganCode(String organCode) {
        this.organCode = organCode;
        return this;
    }

    public String getOrganName() {
        return organName;
    }

    public UniToken setOrganName(String organName) {
        this.organName = organName;
        return this;
    }

    public List<Long> getRole() {
        return role;
    }

    public UniToken setRole(List<Long> role) {
        this.role = role;
        return this;
    }

    public String getToken() {
        return token;
    }

    public UniToken setToken(String token) {
        this.token = token;
        return this;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public UniToken setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
        return this;
    }


    @Override
    public String toString() {
        return "UniToken{" +
                "userId=" + userId +
                ", userIdShow='" + userIdShow + '\'' +
                ", userName='" + userName + '\'' +
                ", accountName='" + accountName + '\'' +
                ", organId=" + organId +
                ", organIdShow=" + organIdShow +
                ", organCode=" + organCode +
                ", organName='" + organName + '\'' +
                ", role=" + role +
                ", token='" + token + '\'' +
                ", expireTime=" + expireTime +
                '}';
    }
}
