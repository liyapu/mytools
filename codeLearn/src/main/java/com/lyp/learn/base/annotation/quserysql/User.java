package com.lyp.learn.base.annotation.quserysql;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-04 20:39
 */


@Table(value = "user")
public class User {
    @Column("id")
    private Integer id;

    @Column("name")
    private String name;

    @Column(value="password")
    private String password;

    @Column(value="address")
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
