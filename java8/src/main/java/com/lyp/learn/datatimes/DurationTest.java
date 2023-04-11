package com.lyp.learn.datatimes;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

/**
 * @author liyapu
 * @date 2023-03-22 14:35
 * @description 持续时间，时间差  Duration
 * 有了时间点，自然就衍生出时间段了，那就是Duration。Duration的内部实现与Instant类似，也是包含两部分：seconds表示秒，nanos表示纳秒。Duration是两个时间戳的差值，所以使用java.time中的时间戳类，例如Instant、LocalDateTime等实现了Temporal类的日期时间类为参数，通过Duration.between()方法创建Duration对象：
 * LocalDateTime from = LocalDateTime.of(2020, Month.JANUARY, 22, 16, 6, 0);    // 2020-01-22 16:06:00
 * LocalDateTime to = LocalDateTime.of(2020, Month.FEBRUARY, 22, 16, 6, 0);     // 2020-02-22 16:06:00
 * Duration duration = Duration.between(from, to);     // 表示从 2020-01-22 16:06:00到 2020-02-22 16:06:00 这段时间
 * <p>
 * <p>
 * Duration对象还可以通过of()方法创建，该方法接受一个时间段长度，和一个时间单位作为参数：
 * Duration duration1 = Duration.of(5, ChronoUnit.DAYS);       // 5天
 * Duration duration2 = Duration.of(1000, ChronoUnit.MILLIS);  // 1000毫秒
 */
public class DurationTest {

    /**
     * Duration--------操作时间间隔 秒，毫秒
     */
    @Test
    public void test5() {
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

}
