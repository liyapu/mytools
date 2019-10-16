package com.lyp.learn.bean;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-16 20:19
 */
public class QuarterVo {
    private String name;
    private String desc;

    public QuarterVo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "QuarterVo{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
