package com.lyp.learn.datatimes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import org.junit.jupiter.api.Test;

/**
 * @author liyapu
 * @date 2023-04-11 19:13
 * @description 获取开始结束日期方法示例
 * @see LocalDateTimeUtils
 */
public class StartEndDemo {

    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     * 获取当天的最大最小开始日期时间
     */
    @Test
    public void minMaxDateTime() {
        LocalDateTime todayMin = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime todayMax = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        System.out.println("todayMin = " + todayMin);
        System.out.println("todayMin = " + dateTimeFormatter.format(todayMin));
        System.out.println();
        System.out.println("todayMax = " + todayMax);
        System.out.println("todayMax = " + dateTimeFormatter.format(todayMax));
    }

    @Test
    public void test11() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间 " + dateTimeFormatter.format(LocalDateTime.now()));
        System.out.println();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("周 开始日期 " + formatter.format(getStartDayOfWeek(0)));
        System.out.println("周 结束日期 " + formatter.format(getEndDayOfWeek(0)));
        System.out.println();

        System.out.println("月 开始日期 " + formatter.format(getStartDayOfMonth1(0)));
        System.out.println("月 开始日期 " + formatter.format(getStartDayOfMonth2(0)));
        System.out.println("月 结束日期 " + formatter.format(getEndDayOfMonth1(0)));
        System.out.println("月 结束日期 " + formatter.format(getEndDayOfMonth2(0)));
        System.out.println();

        System.out.println("季 开始日期 " + formatter.format(getStartDayOfQuarter(0)));
        System.out.println("季 结束日期 " + formatter.format(getEndDayOfQuarter(0)));
        System.out.println();

        System.out.println("年 开始日期 " + formatter.format(getStartDayOfYear1(0)));
        System.out.println("年 开始日期 " + formatter.format(getStartDayOfYear2(0)));
        System.out.println("年 结束日期 " + formatter.format(getEndDayOfYear1(0)));
        System.out.println("年 结束日期 " + formatter.format(getEndDayOfYear2(0)));
    }

    /**
     * 获取周 第一天
     *
     * @param weeks 0本周，1下周，-1上周 以此类推
     * @return java.lang.String
     */
    public static LocalDate getStartDayOfWeek(long weeks) {
        LocalDate resDate = LocalDate.now().plusWeeks(weeks);
        DayOfWeek week = resDate.getDayOfWeek();
        int value = week.getValue();
        return resDate.minusDays(value - 1);
    }

    /**
     * 获取周最后一天
     *
     * @param weeks 0本周，1下周，-1上周 以此类推
     * @return LocalDate
     */
    public static LocalDate getEndDayOfWeek(long weeks) {
        LocalDate resDate = LocalDate.now().plusWeeks(weeks);
        DayOfWeek week = resDate.getDayOfWeek();
        int value = week.getValue();
        return resDate.plusDays(7 - value);
    }

    /**
     * 获取月份 第一天
     *
     * @param months 0本月，1下月，-1上月 以此类推
     * @return LocalDate
     */
    public static LocalDate getStartDayOfMonth1(long months) {
        LocalDate resDate = LocalDate.now().plusMonths(months);
        Month month = resDate.getMonth();
        return LocalDate.of(resDate.getYear(), month, 1);
    }

    /**
     * 获取月份 第一天
     *
     * @param months 0本月，1下月，-1上月 以此类推
     * @return LocalDate
     */
    public static LocalDate getStartDayOfMonth2(long months) {
        return LocalDate.now().plusMonths(months).with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取月份 最后一天
     *
     * @param months 0本月，1下月，-1上月 以此类推
     * @return LocalDate
     */
    public static LocalDate getEndDayOfMonth1(long months) {
        LocalDate resDate = LocalDate.now().plusMonths(months);
        Month month = resDate.getMonth();
        int length = month.length(resDate.isLeapYear());
        return LocalDate.of(resDate.getYear(), month, length);
    }

    /**
     * 获取月份 最后一天
     *
     * @param months 0本月，1下月，-1上月 以此类推
     * @return LocalDate
     */
    public static LocalDate getEndDayOfMonth2(long months) {
        return LocalDate.now().plusMonths(months).with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取季度 第一天
     *
     * @param quarters 0本季度，1下季度，-1上季度 以此类推
     * @return LocalDate
     */
    public static LocalDate getStartDayOfQuarter(long quarters) {
        LocalDate resDate = LocalDate.now().plusMonths(quarters * 3);
        Month month = resDate.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        return LocalDate.of(resDate.getYear(), firstMonthOfQuarter, 1);
    }

    /**
     * 获取季度 最后一天
     *
     * @param quarters 0本季度，1下季度，-1上季度 以此类推
     * @return LocalDate
     */
    public static LocalDate getEndDayOfQuarter(long quarters) {
        LocalDate resDate = LocalDate.now().plusMonths(quarters * 3);
        Month month = resDate.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        return LocalDate.of(resDate.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(resDate.isLeapYear()));
    }

    /**
     * 获取年份 第一天
     *
     * @param years 0本今年，1明年，-1去年 以此类推
     * @return LocalDate
     */
    public static LocalDate getStartDayOfYear1(long years) {
        LocalDate resDate = LocalDate.now().plusYears(years);
        return LocalDate.of(resDate.getYear(), Month.JANUARY, 1);
    }

    /**
     * 获取年份 第一天
     *
     * @param years 0本今年，1明年，-1去年 以此类推
     * @return LocalDate
     */
    public static LocalDate getStartDayOfYear2(long years) {
        return LocalDate.now().plusYears(years).with(TemporalAdjusters.firstDayOfYear());
    }

    /**
     * 获取年份 最后一天
     *
     * @param years 0本今年，1明年，-1去年 以此类推
     * @return LocalDate
     */
    public static LocalDate getEndDayOfYear1(long years) {
        LocalDate resDate = LocalDate.now().plusYears(years);
        return LocalDate.of(resDate.getYear(), Month.DECEMBER, Month.DECEMBER.length(resDate.isLeapYear()));
    }

    /**
     * 获取年份 最后一天
     *
     * @param years 0本今年，1明年，-1去年 以此类推
     * @return LocalDate
     */
    public static LocalDate getEndDayOfYear2(long years) {
        return LocalDate.now().plusYears(years).with(TemporalAdjusters.lastDayOfYear());
    }

    /**
     * 季度开始和结束时间测试
     */
    @Test
    public void testQuarter() {
        // quarters 0本季度，1下季度，-1上季度 以此类推
        long quarters = -4;
        LocalDate localDate = LocalDate.now();
        //LocalDate localDate = LocalDate.of(2023, 1, 5);
        LocalDate resDate = localDate.plusMonths(quarters * 3);
        Month month = resDate.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        LocalDate startQuarter = LocalDate.of(resDate.getYear(), firstMonthOfQuarter, 1);
        System.out.println("startQuarter = " + dateFormatter.format(startQuarter));

        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        LocalDate endQuarter = LocalDate
            .of(resDate.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(resDate.isLeapYear()));
        System.out.println("endQuarter   = " + dateFormatter.format(endQuarter));
    }

    @Test
    public void testNext() {
        LocalDate localDate = LocalDate.now();
        System.out.println("当前本地日期 " + dateFormatter.format(localDate));

        LocalDate date1 = localDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("当前月的第一天 " + dateFormatter.format(date1));

        LocalDate date2 = localDate.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("当前月的最后一天 " + dateFormatter.format(date2));

        LocalDate date3 = localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println("下一个星期一 " + dateFormatter.format(date3));

        LocalDate date4 = localDate.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println("下个月的第一天 " + dateFormatter.format(date4));

        LocalDate date6 = localDate.with(TemporalAdjusters.firstDayOfYear());
        System.out.println("一年的第一天 " + dateFormatter.format(date6));

        LocalDate date5 = localDate.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("一年中的最后一天 " + dateFormatter.format(date5));

        LocalDate date7 = localDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
        System.out.println("当前月的最后一个星期日 " + dateFormatter.format(date7));
    }


    @Test
    public void testMinMax() {
        System.out.println("LocalTime min = " + timeFormatter.format(LocalTime.MIN));
        System.out.println("LocalTime min = " + timeFormatter.format(LocalTime.MAX));

        System.out.println("LocalDate min = " + dateFormatter.format(LocalDate.MIN));
        System.out.println("LocalDate max = " + dateFormatter.format(LocalDate.MAX));

        System.out.println("LocalDateTime min = " + dateTimeFormatter.format(LocalDateTime.MIN));
        System.out.println("LocalDateTime max = " + dateTimeFormatter.format(LocalDateTime.MAX));
    }

}
