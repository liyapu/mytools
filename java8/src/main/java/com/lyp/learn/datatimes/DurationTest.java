package com.lyp.learn.datatimes;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;

/**
 * @author liyapu
 * @date 2023-03-22 14:35
 * @description 持续时间，时间差  Duration
 * 有了时间点，自然就衍生出时间段了，那就是Duration。
 * Duration的内部实现与Instant类似，也是包含两部分：seconds表示秒，nanos表示纳秒。
 * Duration是两个时间戳的差值，
 * 所以使用java.time中的时间戳类，
 * 例如Instant、LocalDateTime等实现了Temporal类的日期时间类为参数，通过Duration.between()方法创建Duration对象：
 * LocalDateTime from = LocalDateTime.of(2020, Month.JANUARY, 22, 16, 6, 0);    // 2020-01-22 16:06:00
 * LocalDateTime to = LocalDateTime.of(2020, Month.FEBRUARY, 22, 16, 6, 0);     // 2020-02-22 16:06:00
 * Duration duration = Duration.between(from, to);     // 表示从 2020-01-22 16:06:00到 2020-02-22 16:06:00 这段时间
 *
 *
 * Duration对象还可以通过of()方法创建，该方法接受一个时间段长度，和一个时间单位作为参数：
 * Duration duration1 = Duration.of(5, ChronoUnit.DAYS);       // 5天
 * Duration duration2 = Duration.of(1000, ChronoUnit.MILLIS);  // 1000毫秒
 *
 * Duration类通过秒和纳秒相结合来描述一个时间量，最高精度是纳秒。时间量可以为正也可以为负，
 * 比如1天（86400秒0纳秒）、-1天（-86400秒0纳秒）、1年（31556952秒0纳秒）、1毫秒（0秒1000000纳秒）等。
 * Duration类是不可变的、线程安全的、最终类。是JDK8新增的。
 *
 * Duraction：表示时间的存储用于度量时分秒，一般用于存储多个时间之间相差的时间值。对应 LocalTime
 */
public class DurationTest {

    /**
     * Duration--------操作时间间隔 秒，毫秒
     */
    @Test
    public void test1() {
        Duration duration = Duration.ofDays(1);
        System.out.println("duration : " + duration);

        Duration duration1 = Duration.ofHours(2);
        System.out.println("duration1 : " + duration1);

        LocalTime time = LocalTime.now();
        LocalTime time2 = time.plusHours(1);
        Duration duration2 = Duration.between(time, time2);
        System.out.println("duration2 : " + duration2);
        System.out.println("duration2.getSeconds() : " + duration2.getSeconds());
        System.out.println("duration2.getNano() : " + duration2.getNano());
        System.out.println("duration2.toNanos() : " + duration2.toNanos());
        System.out.println("duration2.toMinutes() : " + duration2.toMinutes());
        System.out.println("duration2.toHours() : " + duration2.toHours());
        System.out.println("duration2.toDays() : " + duration2.toDays());

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.plusDays(1);
        Duration duration3 = Duration.between(localDateTime, localDateTime1);
        System.out.println("duration3 : " + duration3);
        System.out.println("duration3.toHours : " + duration3.toHours());

        Instant instant = Instant.now();
        Instant instant1 = instant.plusMillis(1000);
        Duration duration4 = Duration.between(instant, instant1);
        System.out.println("duration4 : " + duration4);
        System.out.println("duration4 : " + duration4.getNano());
        System.out.println();
    }

    @Test
    public void testDate1() {
        LocalDateTime localDateTime1 = LocalDateTime.of(2023, 4, 1, 10, 10, 10);
        LocalDateTime localDateTime2 = LocalDateTime.now();

        Duration duration = Duration.between(localDateTime1, localDateTime2);
        System.out.println("duration.getSeconds = " + duration.getSeconds());
        System.out.println("duration.getNano    = " + duration.getNano());
        System.out.println("duration.toString   = " + duration.toString());
        System.out.println("duration.toNanos    = " + duration.toNanos());
        System.out.println("duration.toMillis   = " + duration.toMillis());
        System.out.println("duration.toMinutes  = " + duration.toMinutes());
        System.out.println("duration.toHours    = " + duration.toHours());
        System.out.println("duration.toDays     = " + duration.toDays());
    }

    /**
     * 解析方法
     * 格式说明
     * 采用ISO-8601时间格式。格式为：PnYnMnDTnHnMnS   （n为个数）
     * 例如：P1Y2M10DT2H30M15.03S
     * P：开始标记
     * 1Y：一年
     * 2M：两个月
     * 10D：十天
     * T：日期和时间的分割标记
     * 2H：两个小时
     * 30M：三十分钟
     * 15S：15.02秒
     *
     * 1."P", "D", "H", "M" 和 "S"可以是大写或者小写（建议大写）
     * 2.可以用“-”表示负数
     * 示例大全
     * "PT20.345S" -- parses as "20.345 seconds"
     * "PT15M"     -- parses as "15 minutes" (where a minute is 60 seconds)
     * "PT10H"     -- parses as "10 hours" (where an hour is 3600 seconds)
     * "P2D"       -- parses as "2 days" (where a day is 24 hours or 86400 seconds)
     * "P2DT3H4M"  -- parses as "2 days, 3 hours and 4 minutes"
     * "P-6H3M"    -- parses as "-6 hours and +3 minutes"
     * "-P6H3M"    -- parses as "-6 hours and -3 minutes"
     * "-P-6H+3M"  -- parses as "+6 hours and -3 minutes"
     */
    @Test
    public void test2() {
        Duration fromChar1 = Duration.parse("P1DT1H10M10.5S");
        System.out.println("fromChar1 " + fromChar1);
        Duration fromChar2 = Duration.parse("PT10M");
        System.out.println("fromChar2 " + fromChar2);
    }

    /**
     * 比较方法
     * 比较两个时间的差
     */
    @Test
    public void test3() {
        Instant start = Instant.parse("2017-10-03T10:15:30.00Z");
        Instant end = Instant.parse("2017-10-03T10:16:30.00Z");

        // start - end
        Duration duration = Duration.between(start, end);

        // 任何一个时间单元为负数，则返回true。true：end早于start
        duration.isNegative();

        Duration.between(start, end).getSeconds();
        Duration.between(start, end).getNano();
    }

    /**
     * 增减方法
     * plusX()、minusX()
     * X表示days, hours, millis, minutes, nanos 或 seconds
     *
     * plus()/minus()方法
     * 带TemporalUnit 类型参数进行加减：
     */
    @Test
    public void test4() {
        Duration duration1 = Duration.ofHours(2);
        Duration newDuration1 = duration1.plusSeconds(33);
        System.out.println("newDuration1 = " + newDuration1);

        Duration duration2 = Duration.ofHours(2);
        Duration newDuration2 = duration2.plus(33, ChronoUnit.SECONDS);
        System.out.println("newDuration2 = " + newDuration2);
    }

    /**
     * 转换单位
     * 可以用toX来转换为其他单位，支持：toDays, toHours, toMinutes, toMillis, toNanos
     */
    @Test
    public void test5() {
        Duration duration = Duration.ofHours(2);

        duration.toDays();     // 0
        duration.toHours();    // 2
        duration.toMinutes();  // 120
        duration.toMillis();   // 7200000
        duration.toNanos();    // 7200000000000

        System.out.println("duration.toDays()  " + duration.toDays());
        System.out.println("duration.toHours()  " + duration.toHours());
        System.out.println("duration.toMinutes()  " + duration.toMinutes());
        System.out.println("duration.toMillis()  " + duration.toMillis());
        System.out.println("duration.toNanos()  " + duration.toNanos());
    }

    /**
     * 取值方法
     * 可以用getX来获得指定位置的值，因为Duration是由秒和纳秒组成，所以只能获得秒和纳秒：
     */
    @Test
    public void test6() {
        Duration duration = Duration.ofHours(2);

        duration.getSeconds();  //7200
        duration.getNano();     // 0
        System.out.println("duration.getSeconds()  " + duration.getSeconds());
        System.out.println("duration.getNano()  " + duration.getNano());

    }
}
