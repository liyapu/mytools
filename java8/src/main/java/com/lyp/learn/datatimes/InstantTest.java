package com.lyp.learn.datatimes;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author liyapu
 * @date 2023-03-22 14:34
 * @description 时间戳 Instant
 * Instant是时间线上的一个点，表示一个时间戳。Instant可以精确到纳秒，这超过了long的最大表示范围，所以在Instant的实现中是分成了两部分来表示，
 * 一部分是seconds，表示从1970-01-01 00:00:00开始到现在的秒数，另一个部分是nanos，表示纳秒部分。
 * 以下是创建Instant的两种方法：
 * Instant now = Instant.now();
 * 获取当前时刻的时间戳，结果为：2020-02-20T14:14:15.913Z;
 * Instant instant = Instant.ofEpochSecond(60, 100000);
 * ofEpochSecond()方法的第一个参数为秒，第二个参数为纳秒，上面的代码表示从1970-01-01 00:00:00开始后一分钟的10万纳秒的时刻，
 * 其结果为：1970-01-01T00:01:00.000100Z。
 */
public class InstantTest {


    // Instant:时间点
    //替换原有的Date
    @Test
    public void test1() {
        // now():得到Instant的实例
        Instant instant = Instant.now();// 表示自1970年1月1日0时0分0秒（UTC）开始的秒数
        System.out.println(instant);

        // atOffset():得到带偏移量的日期时间
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        // 得到时间戳
        long milli = instant.toEpochMilli();
        System.out.println(milli);//

        // 根据毫秒数，得到时间点的对象
        Instant instant2 = Instant.ofEpochMilli(milli);
        System.out.println(instant2);

        Date date = new Date();//被76行替代
        Date date1 = new Date(3252345324534L);//被89行替代
    }

    /**
     * Instant
     * 机器的日期和时间格式
     * 时间戳(以Unix元年：1970年1月1日 00:00:00 到某个时间之间的毫秒数)
     */
    @Test
    public void test2() {
        Instant instant = Instant.now();
        System.out.println("instant : " + instant); //默认获取 UTC 时区
        System.out.println("instant.getEpochSecond() 获取秒：" + instant.getEpochSecond());
        System.out.println(instant.getNano());

        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        System.out.println(offsetDateTime.toEpochSecond()); //返回时间戳
        System.out.println(offsetDateTime.toLocalDateTime());
        System.out.println();

        Instant instant1 = Instant.ofEpochSecond(30);
        System.out.println("instant1 : " + instant1);
        System.out.println();

        Instant instant2 = Instant.ofEpochSecond(86400);
        System.out.println("instant2 : " + instant2);
        System.out.println();

    }
    
}
