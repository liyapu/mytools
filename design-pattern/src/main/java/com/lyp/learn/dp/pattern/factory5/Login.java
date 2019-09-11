package com.lyp.learn.dp.pattern.factory5;

import java.util.Date;

public class Login {
    private Integer id;
    private Date date;

    public Login(){

    }

    public Login(Integer id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
