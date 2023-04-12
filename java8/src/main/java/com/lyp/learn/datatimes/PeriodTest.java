package com.lyp.learn.datatimes;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;

/**
 * @author liyapu
 * @date 2023-04-12 12:57
 * @description Period类
 * Period类 通过年、月、日相结合来描述一个时间量，最高精度是天。时间量可以为正也可以为负，
 * 例如2年（2年0个月0天）、3个月（0年3个月0天）、4天（0年0月4天）等。
 * Period类是不可变的、线程安全的、最终类。是JDK8新增的。
 *
 * Period：表示日期的存储用于度量年月日，一般用于存储多个日期之间相差的日期值。对应 LocalDate
 */
public class PeriodTest {

    /**
     * Period---------操作 年，月，日
     */
    @Test
    public void test6() {

        Period period = Period.ofDays(5);
        System.out.println("period : " + period);
        System.out.println("period.minusDays(1) : " + period.minusDays(1));
        System.out.println("period.plusDays(5) : " + period.plusDays(5));
        System.out.println();

        Period period1 = Period.ofYears(3);
        System.out.println("period1 : " + period1);

        LocalDate date = LocalDate.now();
        LocalDate date2 = date.plusDays(1);
        Period period2 = Period.between(date, date2);
        System.out.println("period2 : " + period2);
        System.out.println("period2.isNegative() : " + period2.isNegative());
        System.out.println("period2.isZero() : " + period2.isZero());
        System.out.println("period2.getYears() : " + period2.getYears());
        System.out.println("period2.getMonths() : " + period2.getMonths());
        System.out.println("period2.getDays() : " + period2.getDays());
        System.out.println();

        Period period3 = Period.between(date2, date);
        System.out.println("period3 : " + period3);
        System.out.println("period3.isNegative() : " + period3.isNegative());
        System.out.println("period3.isZero() : " + period3.isZero());
        System.out.println("period3.getYears() : " + period3.getYears());
        System.out.println("period3.getMonths() : " + period3.getMonths());
        System.out.println("period3.getDays() : " + period3.getDays());
        System.out.println("period3.get(ChronoUnit.YEARS) : " + period3.get(ChronoUnit.YEARS));
        System.out.println("period3.get(ChronoUnit.MONTHS) : " + period3.get(ChronoUnit.MONTHS));
        System.out.println("period3.get(ChronoUnit.DAYS) : " + period3.get(ChronoUnit.DAYS));
        System.out.println();
    }

    /**
     * 通过时间单位创建
     * 如果仅一个值表示，如使用ofDays()方法，那么其他值为0。
     * 若仅用ofWeeks，则其天数为week数乘以7.
     */
    @Test
    public void test1() {
        Period fromUnits = Period.of(3, 10, 10);
        Period fromDays = Period.ofDays(50);
        Period fromMonths = Period.ofMonths(5);
        Period fromYears = Period.ofYears(10);
        Period fromWeeks = Period.ofWeeks(40);  //280天

        System.out.println("fromUnits " + fromUnits);
        System.out.println("fromDays " + fromDays);
        System.out.println("fromMonths " + fromMonths);
        System.out.println("fromYears " + fromYears);
        System.out.println("fromWeeks " + fromWeeks);
    }

    /**
     * 通过LocalDate创建
     */
    @Test
    public void test2() {
        LocalDate startDate = LocalDate.of(2015, 2, 20);
        LocalDate endDate = LocalDate.of(2017, 1, 15);
        // startDate减endDate
        Period period = Period.between(startDate, endDate);
        System.out.println("period = " + period);
        System.out.println("period.getYears = " + period.getYears());
        System.out.println("period.getMonths = " + period.getMonths());
        System.out.println("period.getDays = " + period.getDays());
    }

    /**
     * 解析方法
     * 格式1：“PnYnMnWnD”
     * P：开始符，表示period（即：表示年月日）；
     * Y：year；
     * M：month；
     * W：week；
     * D：day
     * P, Y, M, W, D都可以用大写或者小写。
     */
    @Test
    public void test3() {
        Period period1 = Period.parse("P2Y");       //2年
        Period period2 = Period.parse("P2Y3M5D");   //2年3月5天
        Period period3 = Period.parse("P1Y2M3W4D"); // 1年2月3周4天。即：1年2月25天

        System.out.println("period1 = " + period1);
        System.out.println("period2 = " + period2);
        System.out.println("period3 = " + period3);
    }


    /**
     * 比较方法
     * 用between来比较日期。
     */
    @Test
    public void test4() {
        LocalDate startDate = LocalDate.of(2015, 2, 20);
        LocalDate endDate = LocalDate.of(2017, 1, 15);
        //endDate-startDate
        Period period1 = Period.between(startDate, endDate);
        System.out.println("period1 = " + period1);
        // 任何一个时间单元为负数，则返回true。true：endDate早于startDate
        boolean negative1 = period1.isNegative();
        System.out.println("negative1 = " + negative1);
        System.out.println();

        //startDate-endDate
        Period period2 = Period.between(endDate, startDate);
        System.out.println("period2 = " + period2);
        // 任何一个时间单元为负数，则返回true。true：endDate早于startDate
        boolean negative2 = period2.isNegative();
        System.out.println("negative2 = " + negative2);
    }

    /**
     * 增减方法
     */
    @Test
    public void test5() {
        Period period = Period.parse("P2Y3M5D");
        System.out.println("period  " + period);

        //在day上增加，超过月份的最大天数，不会向月份字段进位
        Period period2 = period.plusDays(30);
        System.out.println("period2 " + period2);

        Period period3 = period.plusMonths(15);
        System.out.println("period3 " + period3);

        Period period4 = period.minusDays(8);
        System.out.println("period4 " + period4);
    }

    /**
     * 转换单位
     */
    @Test
    public void test7() {
        Period period = Period.parse("P1Y2M3D");
        long monthCount = period.toTotalMonths();
        System.out.println("monthCount = " + monthCount);
    }

}
