package com.lyp.learn.base.demo.pk14;

import java.util.concurrent.TimeUnit;

public class TimeUnitDemo {
    public static void main(String[] args) throws InterruptedException {
        //线程睡眠100毫秒
        TimeUnit.MILLISECONDS.sleep(100);
        //线程睡眠5秒
        TimeUnit.SECONDS.sleep(5);

        System.out.println(TimeUnit.MINUTES.toMicros(1));
        System.out.println(TimeUnit.MINUTES.toMillis(1));
        System.out.println(TimeUnit.MINUTES.toSeconds(1));
        System.out.println(TimeUnit.MINUTES.toHours(1));
        System.out.println(TimeUnit.MINUTES.toDays(1));

        System.out.println();
        //不到1个小时的，都没有了
        System.out.println(TimeUnit.MINUTES.toHours(1));
        System.out.println(TimeUnit.MINUTES.toHours(58));
        System.out.println(TimeUnit.MINUTES.toHours(60));
        System.out.println(TimeUnit.MINUTES.toHours(61));
        System.out.println(TimeUnit.MINUTES.toHours(182));

        System.out.println();

        //把小时转成不同的单位
        System.out.println(TimeUnit.HOURS.toDays(1));
        System.out.println(TimeUnit.HOURS.toMinutes(1));
        System.out.println(TimeUnit.HOURS.toMinutes(2));
        System.out.println(TimeUnit.HOURS.toSeconds(1));

        System.out.println();
        //3天转成小时
        System.out.println(TimeUnit.HOURS.convert(3,TimeUnit.DAYS));

    }
}
