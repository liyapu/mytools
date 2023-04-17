package com.lyp.learn.datatimes;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;

/**
 * @author liyapu
 * @date 2023-04-12 14:10
 * @description
 */
public class ChronoUnitTest {

    /**
     * 使用ChronoUnit类可以快速方便的计算出两个时间日期之间的间隔天数
     */
    @Test
    public void testChronoUnit1() {
        LocalDate startDate = LocalDate.of(2023, 4, 6);
        System.out.println("开始时间:" + startDate);

        LocalDate endDate = LocalDate.now();
        System.out.println("结束时间:" + endDate);

        long days = ChronoUnit.DAYS.between(startDate, endDate); // 获取间隔天数
        System.out.println("间隔天数:" + days);
    }

    @Test
    public void test1() {
        LocalDateTime localDateTime1 = LocalDateTime.of(2023, 4, 1, 10, 10, 10);
        LocalDateTime localDateTime2 = LocalDateTime.now();

        Duration duration = Duration.between(localDateTime1, localDateTime2);
        System.out.println("duration.getSeconds = " + duration.getSeconds());
        System.out.println("duration.getNano    = " + duration.getNano());
        System.out.println("duration.toString   = " + duration.toString());
        System.out.println();
        System.out.println("duration.toNanos    = " + duration.toNanos());
        System.out.println("duration.toMillis   = " + duration.toMillis());
        System.out.println("duration.toMinutes  = " + duration.toMinutes());
        System.out.println("duration.toHours    = " + duration.toHours());
        System.out.println("duration.toDays     = " + duration.toDays());
        System.out.println();
        System.out.println("ChronoUnit.NANOS.between = " + ChronoUnit.NANOS.between(localDateTime1, localDateTime2));
        System.out.println("ChronoUnit.MILLIS.between = " + ChronoUnit.MILLIS.between(localDateTime1, localDateTime2));
        System.out
            .println("ChronoUnit.SECONDS.between = " + ChronoUnit.SECONDS.between(localDateTime1, localDateTime2));
        System.out
            .println("ChronoUnit.MINUTES.between = " + ChronoUnit.MINUTES.between(localDateTime1, localDateTime2));
        System.out.println("ChronoUnit.HOURS.between = " + ChronoUnit.HOURS.between(localDateTime1, localDateTime2));
        System.out.println("ChronoUnit.DAYS.between = " + ChronoUnit.DAYS.between(localDateTime1, localDateTime2));


    }

}
