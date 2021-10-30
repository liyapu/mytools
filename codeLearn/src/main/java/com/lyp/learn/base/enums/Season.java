package com.lyp.learn.base.enums;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 13:33
 */
interface Information {
    void show();
}

 enum Season implements Information {

    SPRING("spring", "春暖花开"){
        @Override
        public void show() {
            System.out.println("春天在哪里?");
        }
    },
    SUMMER("summer", "夏日炎炎"){
        @Override
        public void show() {
            System.out.println("夏天在哪里?");
        }
    },
    AUTUMN("autumn", "秋高气爽"){
        @Override
        public void show() {
            System.out.println("秋天在哪里?");
        }
    },
    WINTER("winter", "白雪皑皑"){
        @Override
        public void show() {
            System.out.println("冬天在哪里?");
        }
    };


    private final String seasonName;
    private final String seasonDesc;

    private Season (String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }
    public String getSeasonDesc() {
        return seasonDesc;
    }



    @Override
    public String toString() {
        return "Season [seasonName=" + seasonName + ", seasonDesc=" + seasonDesc + "]";
    }

//    public void show() {
//        System.out.println("这是一个季节");
//    }

}

class TestSeason {
    public static void main(String[] args) {
        Season spring = Season.SPRING;
        System.out.println(spring);
        spring.show();
        System.out.println(spring.getSeasonName());
        System.out.println();

        // 1. values() 方法：返回所有值
        Season[] seasons = Season.values();
        for (int i=0; i<seasons.length;i++) {
            System.out.println(seasons[i]);
        }
        System.out.println();

        // 2. valueOf() 方法：返回指定对象的值（要求传入的形参name是枚举类对象的名字）
        // 否则，报 java.lang.IllegalArgumentException 异常
        String str = "WINTER";
        Season sea = Season.valueOf(str);
        System.out.println(sea);
        sea.show();

    }
}