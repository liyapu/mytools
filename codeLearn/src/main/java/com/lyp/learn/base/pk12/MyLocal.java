package com.lyp.learn.base.pk12;

public class MyLocal {
    ThreadLocal<Long> longLocal = new ThreadLocal<>();
    ThreadLocal<String> strLocal = new ThreadLocal<>();

    public void setLocal(){
        longLocal.set(Thread.currentThread().getId());
        strLocal.set(Thread.currentThread().getName());
    }

    public Long getLongLocal(){
        return longLocal.get();
    }

    public String getStrLocal(){
       return  strLocal.get();
    }
}
