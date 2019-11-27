package com.lyp.learn.utils;

/**
 * @author: liyapu
 * @description:
 * @date 2019-11-27 19:56
 */
public class Three<K,R,C> {
    private K code;
    private R name;
    private C desc;

    public Three() {
    }

    public Three(K code, R name, C desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public K getCode() {
        return code;
    }

    public void setCode(K code) {
        this.code = code;
    }

    public R getName() {
        return name;
    }

    public void setName(R name) {
        this.name = name;
    }

    public C getDesc() {
        return desc;
    }

    public void setDesc(C desc) {
        this.desc = desc;
    }

    public static  <K,R,C> Three<K,R,C> of(K code,R name,C desc){
        Three<K,R,C> three = new Three<>(code,name,desc);
        return three;
    }

    @Override
    public String toString() {
        return "Three{" +
                "code=" + code +
                ", name=" + name +
                ", desc=" + desc +
                '}';
    }
}
