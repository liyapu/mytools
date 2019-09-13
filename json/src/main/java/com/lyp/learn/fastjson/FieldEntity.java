package com.lyp.learn.fastjson;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class FieldEntity {
    @JSONField(ordinal = 1,name="UUID")
    private int id;
    @JSONField(ordinal = 2,name="mother")
    private String mama;
    @JSONField(ordinal = 3,serialize=true)
    private String name;
    @JSONField(ordinal = 4,serialize=false)
    private String nickName;
    @JSONField(ordinal = 5,format="yyyy-MM-dd HH:mm:ss")
    private Date today;
    @JSONField(ordinal = 6,format="yyyyMMdd")
    private Date birthday;
    @JSONField(ordinal = 7,serializeUsing = MoneySerializer.class)
    private int money;
    @JSONField(ordinal = 8,serialize = true,deserialize = false)
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMama() {
        return mama;
    }

    public void setMama(String mama) {
        this.mama = mama;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "FieldEntity{" +
                "id=" + id +
                ", mama='" + mama + '\'' +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", today=" + today +
                ", birthday=" + birthday +
                ", money=" + money +
                ", phone='" + phone + '\'' +
                '}';
    }
}
