package com.lyp.learn.base.enums.year;

import java.util.List;

public enum YearEnum {

    SPRING("spring", "春暖花开"){
        @Override
        public void say() {
            System.out.println("春天~~~~~~~");
        }

        @Override
        public void show(Object object, List<Object> objectList) {
            System.out.println("春天11111111");
        }
    },SUMMER("summer", "夏日炎炎"){
        @Override
        public void say() {
            System.out.println("夏天~~~~~~~~~~~~");
        }

        @Override
        public void show(Object object, List<Object> objectList) {
            System.out.println("夏天22222222222222222222222");
        }
    },
    AUTUMN("autumn", "秋高气爽"){
        @Override
        public void say() {
            System.out.println("秋天");
        }

        @Override
        public void show(Object object, List<Object> objectList) {
            System.out.println("秋天33333333333333333333");
        }
    },
    WINTER("winter", "白雪皑皑"){
        @Override
        public void say() {
            System.out.println("冬天");
        }

        @Override
        public void show(Object object, List<Object> objectList) {
            System.out.println("冬天4444444444444444");
        }
    };


    public abstract void say();
    public abstract void show(Object object, List<Object> objectList);

    private String name;
    private String desc;

    YearEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
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
}
