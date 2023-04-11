package com.lyp.learn.datatimes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * Joda 的关键日期/时间概念
 * Joda 使用以下概念，它们可以应用到任何日期/时间库：
 *
 * 不可变性（Immutability）
 * 瞬间性（Instant）
 * 局部性（Partial）
 * 年表（Chronology）
 * 时区（Time zone）
 * 我将针对 Joda 依次讨论每一个概念。
 *
 * ---不可变性
 * 我在本文讨论的 Joda 类具有不可变性，因此它们的实例无法被修改。（不可变类的一个优点就是它们是线程安全的）。
 * 我将向您展示的用于处理日期计算的 API 方法全部返回一个对应 Joda 类的新实例，同时保持原始实例不变。
 * 当您通过一个 API 方法操作 Joda 类时，您必须捕捉该方法的返回值，因为您正在处理的实例不能被修改。
 * 您可能对这种模式很熟悉；比如，这正是 java.lang.String 的各种操作方法的工作方式。
 *
 * ---瞬间性
 * Instant 表示时间上的某个精确的时刻，使用从 epoch 开始计算的毫秒表示。
 * 这一定义与 JDK 相同，这就是为什么任何 Joda Instant 子类都可以与 JDK Date 和 Calendar 类兼容的原因。
 * 更通用一点的定义是：一个瞬间 就是指时间线上只出现一次且唯一的一个时间点，并且这种日期结构只能以一种有意义的方式出现一次。
 *
 * ---局部性
 * 一个局部时间，正如我将在本文中将其称为局部时间片段一样，它指的是时间的一部分片段。瞬间性指定了与 epoch 相对的时间上的一个精确时刻，
 * 与此相反，局部时间片段指的是在时间上可以来回 “移动” 的一个时刻，这样它便可以应用于多个实例。
 * 比如，6 月 2 日 可以应用于任意一年的 6 月份（使用 Gregorian 日历）的第二天的任意瞬间。
 * 同样，11:06 p.m. 可以应用于任意一年的任意一天，并且每天只能使用一次。即使它们没有指定一个时间上的精确时刻，局部时间片段仍然是有用的。
 * 我喜欢将局部时间片段看作一个重复周期中的一点，这样的话，如果我正在考虑的日期构建可以以一种有意义的方式出现多次（即重复的），那么它就是一个局部时间。
 *
 * ---年表
 * Joda 本质 — 以及其设计核心 — 的关键就是年表（它的含义由一个同名抽象类捕捉）。
 * 从根本上讲，年表是一种日历系统 — 一种计算时间的特殊方式 — 并且是一种在其中执行日历算法的框架。受 Joda 支持的年表的例子包括：
 *
 * ISO（默认）
 * Coptic
 * Julian
 * Islamic
 * Joda-Time 1.6 支持 8 种年表，每一种都可以作为特定日历系统的计算引擎。
 *
 * 时区
 * 时区是值一个相对于英国格林威治的地理位置，用于计算时间。要了解事件发生的精确时间，还必须知道发生此事件的位置。
 * 任何严格的时间计算都必须涉及时区（或相对于 GMT），除非在同一个时区内发生了相对时间计算（即时这样时区也很重要，如果事件对于位于另一个时区的各方存在利益关系的话）。
 *
 * DateTimeZone 是 Joda 库用于封装位置概念的类。许多日期和时间计算都可以在不涉及时区的情况下完成，
 * 但是仍然需要了解 DateTimeZone 如何影响 Joda 的操作。
 * 默认时间，即从运行代码的机器的系统时钟检索到的时间，在大部分情况下被使用。
 *
 *
 * LocalDate、LocalTime、LocalDateTime 类是其中较重要的几个类，它们的实例是不可变的对象，
 * 分别表示使用 ISO-8601日历系统的日期、时间、日期和时间。
 * 它们提供了简单的本地日期或时间，并不包含当前的时间信息，也不包含与时区相关的信息。
 * 注：ISO-8601日历系统是国际标准化组织制定的现代公民的日期和时间的表示法，也就是公历。
 */
public class DateTimeDemo {

    /**
     * ================LocalDate=========================
     * LocalDate是一个不可变的类，它表示默认格式(yyyy-MM-dd)的日期.
     * LocalDate和LocalTime和最基本的String一样，是不变类型，不但线程安全，而且不能修改。
     *
     * getYear()      int    获取当前日期的年份
     * getMonth()    Month    获取当前日期的月份对象
     * getMonthValue()    int    获取当前日期是第几月
     * getDayOfWeek()    DayOfWeek    表示该对象表示的日期是星期几
     * getDayOfMonth()    int    表示该对象表示的日期是这个月第几天
     * getDayOfYear()    int    表示该对象表示的日期是今年第几天
     * withYear(int year)    LocalDate    修改当前对象的年份
     * withMonth(int month)    LocalDate    修改当前对象的月份
     * withDayOfMonth(int dayOfMonth)    LocalDate    修改当前对象在当月的日期
     * isLeapYear()    boolean    是否是闰年
     * lengthOfMonth()    int    这个月有多少天
     * lengthOfYear()    int    该对象表示的年份有多少天（365或者366）
     * plusYears(long yearsToAdd)    LocalDate    当前对象增加指定的年份数
     * plusMonths(long monthsToAdd)    LocalDate    当前对象增加指定的月份数
     * plusWeeks(long weeksToAdd)    LocalDate    当前对象增加指定的周数
     * plusDays(long daysToAdd)    LocalDate    当前对象增加指定的天数
     * minusYears(long yearsToSubtract)    LocalDate    当前对象减去指定的年数
     * minusMonths(long monthsToSubtract)    LocalDate    当前对象减去注定的月数
     * minusWeeks(long weeksToSubtract)    LocalDate    当前对象减去指定的周数
     * minusDays(long daysToSubtract)    LocalDate    当前对象减去指定的天数
     * compareTo(ChronoLocalDate other)    int    比较当前对象和other对象在时间上的大小，返回值如果为正，则当前对象时间较晚，
     * isBefore(ChronoLocalDate other)    boolean    比较当前对象日期是否在other对象日期之前 <
     * isAfter(ChronoLocalDate other)    boolean    比较当前对象日期是否在other对象日期之后 >
     * isEqual(ChronoLocalDate other)    boolean    比较两个日期对象是否相等
     */
    @Test
    public void test1() {
        // 日期
        LocalDate date = LocalDate.now();

        System.out.println("date :" + date);
        System.out.println("date.getYear() :" + date.getYear());
        System.out.println("date.get(ChronoField.YEAR) : " + date.get(ChronoField.YEAR));
        System.out.println("date.getMonth() : " + date.getMonth());
        System.out.println("date.getMonthValue() :" + date.getMonthValue());
        System.out.println("date.get(ChronoField.MONTH_OF_YEAR) : " + date.get(ChronoField.MONTH_OF_YEAR));
        System.out.println("date.getDayOfMonth() : " + date.getDayOfMonth());
        System.out.println("date.get(ChronoField.DAY_OF_MONTH) : " + date.get(ChronoField.DAY_OF_MONTH));
        System.out.println("date.getDayOfWeek() : " + date.getDayOfWeek());
        System.out.println("date.getDayOfWeek().getValue(): " + date.getDayOfWeek().getValue());
        System.out.println("date.getDayOfYear() : " + date.getDayOfYear());
        System.out.println("date.getLong(ChronoField.DAY_OF_YEAR) : " + date.getLong(ChronoField.DAY_OF_YEAR));
        System.out.println("date.getLong(ChronoField.DAY_OF_MONTH) : " + date.getLong(ChronoField.DAY_OF_MONTH));
        System.out.println("date.getLong(ChronoField.MONTH_OF_YEAR) : " + date.getLong(ChronoField.MONTH_OF_YEAR));
        System.out.println("date.getLong(ChronoField.YEAR) : " + date.getLong(ChronoField.YEAR));
        System.out.println("date.lengthOfYear() : " + date.lengthOfYear());
        System.out.println("date.lengthOfMonth() : " + date.lengthOfMonth());
        System.out.println("date.isLeapYear() : " + date.isLeapYear());

        System.out.println();

        System.out.println("date.plusYears(2): " + date.plusYears(2));
        System.out.println("date.minusYears(5) : " + date.minusYears(5));
        System.out.println("date.plusMonths(2) : " + date.plusMonths(2));
        System.out.println("date.minusMonths(5) : " + date.minusMonths(5));
        System.out.println("date.plusDays(2) : " + date.plusDays(2));
        System.out.println("date.minusDays(5) : " + date.minusDays(5));
        System.out.println("date.plusWeeks(2) : " + date.plusWeeks(2));
        System.out.println("date.minusWeeks(5) : " + date.minusWeeks(5));
        System.out.println("date.minusYears(1).minusMonths(1).minusDays(1) : " + date.minusYears(1).minusMonths(1).minusDays(1));
        System.out.println("date.plus(2,ChronoUnit.YEARS) : " + date.plus(2,ChronoUnit.YEARS));
        System.out.println("date.minus(5,ChronoUnit.YEARS) : " + date.minus(5,ChronoUnit.YEARS));
        System.out.println("date.plus(2,ChronoUnit.DAYS) : " + date.plus(2,ChronoUnit.DAYS));
        System.out.println("date.minus(5,ChronoUnit.DAYS) : " + date.minus(5,ChronoUnit.DAYS));
        System.out.println();

        System.out.println("date.withYear(2015) : " + date.withYear(2015));
        System.out.println("date.withMonth(6) : " + date.withMonth(6));
        System.out.println("date.withDayOfMonth(10): " + date.withDayOfMonth(10));
        System.out.println("date.withDayOfYear(300) : " + date.withDayOfYear(300));
        System.out.println("date.with(ChronoField.YEAR,2011) : " + date.with(ChronoField.YEAR,2011));
        System.out.println("date.with(ChronoField.MONTH_OF_YEAR,2) : " + date.with(ChronoField.MONTH_OF_YEAR,2));
        System.out.println();

        System.out.println("date.with(.firstDayOfMonth()) : " + date.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println("date.with(.firstDayOfNextMonth()) : " + date.with(TemporalAdjusters.firstDayOfNextMonth()));
        System.out.println("date.with(.firstDayOfYear()) : " + date.with(TemporalAdjusters.firstDayOfYear()));
        System.out.println("date.with(.firstDayOfNextYear()) : " + date.with(TemporalAdjusters.firstDayOfNextYear()));
        System.out.println("date.with(.firstInMonth(DayOfWeek.TUESDAY)) : " + date.with(TemporalAdjusters.firstInMonth(DayOfWeek.TUESDAY)));
        System.out.println("date.with(.firstInMonth(DayOfWeek.SUNDAY)) : " + date.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY)));
        System.out.println("date.with(.lastDayOfMonth()) : " + date.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println("date.with(.lastDayOfYear() : " + date.with(TemporalAdjusters.lastDayOfYear()));
        System.out.println("date.with(.lastInMonth(DayOfWeek.SUNDAY)) : " + date.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY)));

        System.out.println("date.with(.previous(DayOfWeek.TUESDAY)) : " + date.with(TemporalAdjusters.previous(DayOfWeek.TUESDAY)));
        System.out.println("date.with(.previousOrSame(DayOfWeek.TUESDAY)) : "  + date.with(TemporalAdjusters.previousOrSame(DayOfWeek.TUESDAY)));
        System.out.println("date.with(.next(DayOfWeek.SUNDAY)) : " + date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)));
        System.out.println("date.with(.nextOrSame(DayOfWeek.SUNDAY)) : " + date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)));
        System.out.println();
        System.out.println("date.with(.dayOfWeekInMonth(1,DayOfWeek.SUNDAY)) : " + date.with(TemporalAdjusters.dayOfWeekInMonth(1,DayOfWeek.SUNDAY)));
        System.out.println("date.with(.dayOfWeekInMonth(2,DayOfWeek.SUNDAY)) : " + date.with(TemporalAdjusters.dayOfWeekInMonth(2,DayOfWeek.SUNDAY)));
        System.out.println("date.with(.dayOfWeekInMonth(3,DayOfWeek.SUNDAY)) : " + date.with(TemporalAdjusters.dayOfWeekInMonth(3,DayOfWeek.SUNDAY)));
        System.out.println("date.with(.dayOfWeekInMonth(4,DayOfWeek.SUNDAY)) : " + date.with(TemporalAdjusters.dayOfWeekInMonth(4,DayOfWeek.SUNDAY)));
        System.out.println("date.with(.dayOfWeekInMonth(5,DayOfWeek.SUNDAY)) : " + date.with(TemporalAdjusters.dayOfWeekInMonth(5,DayOfWeek.SUNDAY)));
        System.out.println("date.with(.dayOfWeekInMonth(-1,DayOfWeek.SUNDAY)) : " + date.with(TemporalAdjusters.dayOfWeekInMonth(-1,DayOfWeek.SUNDAY)));
        System.out.println("date.with(.dayOfWeekInMonth(-2,DayOfWeek.SUNDAY)) : " + date.with(TemporalAdjusters.dayOfWeekInMonth(-2,DayOfWeek.SUNDAY)));
        System.out.println("date.with(.dayOfWeekInMonth(0,DayOfWeek.SUNDAY)) : " + date.with(TemporalAdjusters.dayOfWeekInMonth(0,DayOfWeek.SUNDAY)));

        System.out.println();

        LocalDate date2 = LocalDate.of(2015,10,10);
        System.out.println("date2 : " + date2);
        System.out.println();

        LocalDate date3 = LocalDate.parse("2016-10-10");
        System.out.println("date3 : " + date3);
        System.out.println("date3.getYear() : " + date3.getYear());
        System.out.println("date3.getMonth() : " + date3.getMonth());
        System.out.println("date3.getDayOfMonth() : " + date3.getDayOfMonth());
        System.out.println();

        //LocalDate date4 = LocalDate.parse("2016-10-10",DateTimeFormatter.ofLocalizedDate("yyMMdd"));

        LocalDate localDate5 = LocalDateTime.now().toLocalDate();
        System.out.println("localDate5 : " + localDate5);

        boolean isBefore = date2.isBefore(date);
        System.out.println("isBefore :" + isBefore);
        boolean isAfter = date2.isAfter(date);
        System.out.println("isAfter :" + isAfter);
        boolean isEqual = date2.isEqual(date);
        System.out.println("isEqual :" + isEqual);
        boolean isEqual2 = date2.isEqual(date2);
        System.out.println(isEqual2);
    }

    /**
     *
     * ===============LocalTime===========================
     * LocalTime是一个不可变的类，它的实例代表一个符合人类可读格式的时间，默认格式是hh:mm:ss.zzz
     */
    @Test
    public void test2(){
        System.out.println("-----------------------------时间-------------------------");
        LocalTime time = LocalTime.now();
        System.out.println(time);
        System.out.println("time.getHour() : " + time.getHour());
        System.out.println("time.get(ChronoField.HOUR_OF_DAY) : " + time.get(ChronoField.HOUR_OF_DAY));
        System.out.println("time.get(ChronoField.HOUR_OF_AMPM) : " + time.get(ChronoField.HOUR_OF_AMPM));
        System.out.println("time.getMinute() : " + time.getMinute());
        System.out.println("time.get(ChronoField.MINUTE_OF_HOUR): " + time.get(ChronoField.MINUTE_OF_HOUR));
        System.out.println("time.get(ChronoField.MINUTE_OF_DAY) : " + time.get(ChronoField.MINUTE_OF_DAY));
        System.out.println("time.getSecond() : " + time.getSecond());
        System.out.println("time.get(ChronoField.SECOND_OF_MINUTE) : " + time.get(ChronoField.SECOND_OF_MINUTE));
        System.out.println("time.get(ChronoField.SECOND_OF_DAY) : " +time.get(ChronoField.SECOND_OF_DAY));
        System.out.println("time.getNano() : " + time.getNano());
        System.out.println("time.getLong(ChronoField.HOUR_OF_DAY) : " + time.getLong(ChronoField.HOUR_OF_DAY));
        System.out.println("time.getLong(ChronoField.HOUR_OF_AMPM) : " + time.getLong(ChronoField.HOUR_OF_AMPM));
        System.out.println("time.getLong(ChronoField.MINUTE_OF_DAY) : " + time.getLong(ChronoField.MINUTE_OF_DAY));
        System.out.println("time.getLong(ChronoField.MINUTE_OF_HOUR) : " + time.getLong(ChronoField.MINUTE_OF_HOUR));
        System.out.println("time.getLong(ChronoField.SECOND_OF_DAY) : " + time.getLong(ChronoField.SECOND_OF_DAY));
        System.out.println("time.getLong(ChronoField.SECOND_OF_MINUTE) : " + time.getLong(ChronoField.SECOND_OF_MINUTE));
        System.out.println();

        LocalTime time2 = LocalTime.of(15,30);
        System.out.println("time2.getHour(): " + time2.getHour());
        System.out.println("time2.get(ChronoField.HOUR_OF_DAY) : " + time2.get(ChronoField.HOUR_OF_DAY));
        System.out.println("time2.get(ChronoField.HOUR_OF_AMPM) : " + time2.get(ChronoField.HOUR_OF_AMPM));
        System.out.println();

        LocalTime time3 = LocalTime.of(17,50,50);
        System.out.println("time3.getHour() : " + time3.getHour());
        System.out.println("time3.getMinute() : " + time3.getMinute());
        System.out.println("time3.getSecond() : " + time3.getSecond());
        System.out.println();

        LocalTime time4 = LocalTime.parse("16:20:30");
        System.out.println("time4.getHour() : " + time4.getHour());
        System.out.println("time4.getMinute() : " + time4.getMinute());
        System.out.println();

        //LocalTime time5 = LocalTime.parse();

        LocalTime time6 = LocalDateTime.now().toLocalTime();
        System.out.println("time6 : " + time6);
    }

    /**
     * ==============LocalDateTime===============================
     * LocalDateTime是一个不可变的日期-时间对象，它表示一组日期-时间，默认格式是yyyy-MM-dd-HH-mm-ss.zzz
     */
    @Test
    public void test3(){
        System.out.println("------------------日期--时间---------------------");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime : " + localDateTime);
        System.out.println("localDateTime.getYear() : " + localDateTime.getYear());
        System.out.println("localDateTime.get(ChronoField.YEAR) : " + localDateTime.get(ChronoField.YEAR));
        System.out.println("localDateTime.getHour() : " + localDateTime.getHour());
        System.out.println("localDateTime.get(ChronoField.HOUR_OF_DAY) : " + localDateTime.get(ChronoField.HOUR_OF_DAY));
        System.out.println();

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(date,time);
        System.out.println("localDateTime1 : " + localDateTime1);
        System.out.println();

        LocalDateTime localDateTime2 = LocalDateTime.of(2012,5,20,15,35,38);
        System.out.println("localDateTime2 : " + localDateTime2);
        System.out.println("localDateTime2.getDayOfMonth() : " + localDateTime2.getDayOfMonth());
        System.out.println();

        LocalDateTime localDateTime3 = LocalDateTime.of(2012, Month.MAY,6,14,30,55);
        System.out.println("localDateTime3 : " + localDateTime3);
        System.out.println();

        LocalDateTime localDateTime41 = date.atTime(time);
        System.out.println("localDateTime41 : " + localDateTime41);
        System.out.println();

        LocalDateTime localDateTime42 = date.atTime(15, 36);
        System.out.println("localDateTime42 : " + localDateTime42);
        System.out.println();

        LocalDateTime localDateTime5 = time.atDate(date);
        System.out.println("localDateTime5 : " + localDateTime5);
        System.out.println();

        /**
         * 获取今天的开始和结束日期
         */
        LocalDateTime localDateTimeMin = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime localDateTimeMax = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        System.out.println("localDateTimeMin : " + localDateTimeMin);
        System.out.println("localDateTimeMax : " + localDateTimeMax);

    }


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
        Period period2 = Period.between(date,date2);
        System.out.println("period2 : " + period2);
        System.out.println("period2.isNegative() : " + period2.isNegative());
        System.out.println("period2.isZero() : "  + period2.isZero());
        System.out.println("period2.getYears() : " + period2.getYears());
        System.out.println("period2.getMonths() : " + period2.getMonths());
        System.out.println("period2.getDays() : " + period2.getDays());
        System.out.println();

        Period period3 = Period.between(date2,date);
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
     * 时间校正器
     * TemporalAdjuster
     */
    @Test
    public void testTemporalAdjuster(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt1 = ldt.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(ldt1);

        //下一个周日
        LocalDateTime ldt2 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt2);
    }

    /**
     * 时间格式化
     * 打印输出及解析日期-时间对象
     *
     * 时间.format(DateTimeFormatter实例)
     * DateTimeFormatter实例.format(时间)
     */
    @Test
    public void test8(){

        LocalDate localDateNow = LocalDate.now();
        System.out.println("localDateNow : " + localDateNow);
        System.out.println(localDateNow.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(localDateNow.format(DateTimeFormatter.ISO_DATE));
        System.out.println(localDateNow.format(DateTimeFormatter.ISO_LOCAL_DATE));

        LocalDate localDateNow2 = LocalDate.parse("20181218",DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("localDateNow2 :" + localDateNow2);
        System.out.println("localDateNow2.getYear() : " + localDateNow2.getYear());

        LocalDate localDateNow3 = LocalDate.parse("2018-10-10",DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("localDateNow3 : " + localDateNow3);
        System.out.println();

        LocalTime localTimeNow = LocalTime.now();
        System.out.println("localTimeNow : " + localTimeNow);
        System.out.println("DateTimeFormatter.ISO_LOCAL_TIME : " + localTimeNow.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println();

        LocalDateTime localDateTimeNow = LocalDateTime.now();
        DateTimeFormatter chinaDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        System.out.println("chinaDateTimeFormatter " + chinaDateTimeFormatter.format(localDateTimeNow));

        DateTimeFormatter chinaDateTimeFormatterCustom = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendLiteral(" ")
            .append(DateTimeFormatter.ISO_LOCAL_TIME)
            .toFormatter();

        System.out.println("localDateTimeNow : " + localDateTimeNow);
        System.out
            .println("DateTimeFormatter.BASIC_ISO_DATE : " + localDateTimeNow.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("DateTimeFormatter.ISO_DATE : " + localDateTimeNow.format(DateTimeFormatter.ISO_DATE));
        System.out
            .println("DateTimeFormatter.ISO_DATE_TIME : " + localDateTimeNow.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out
            .println("DateTimeFormatter.ISO_LOCAL_DATE : " + localDateTimeNow.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("DateTimeFormatter.ISO_TIME : " + localDateTimeNow.format(DateTimeFormatter.ISO_TIME));
        System.out.println("chinaDateTimeFormatter : " + localDateTimeNow.format(chinaDateTimeFormatter));
        System.out.println("chinaDateTimeFormatterCustom : " + localDateTimeNow.format(chinaDateTimeFormatterCustom));
        System.out.println(localDateTimeNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(localDateTimeNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        System.out.println(localDateTimeNow.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));

        LocalDateTime localDateTimeNow1 = LocalDateTime
            .parse("2018-12-18 15:41:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("localDateTimeNow1 : " + localDateTimeNow1);

        LocalDateTime localDateTimeNow2 = LocalDateTime.parse("2018-12-18 15:41:15", chinaDateTimeFormatter);
        System.out.println("localDateTimeNow2 : " + localDateTimeNow2);
        System.out.println();
    }


    @Test
    public void test08(){
        Long createTime = 1638193736089L; //2021-11-29 21:48:56
        DateTimeFormatter chinaDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
    }

    /**
     * 带时区的
     * ZoneDate ZoneTime ZoneDateTime
     */
    @Test
    public  void test9(){
        System.out.println("-----------支持的所有时区-----------------");
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        for(String s : availableZoneIds){
            System.out.println(s);
        }
        System.out.println("------------------时区-----------------------------");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("zonedDateTime : " + zonedDateTime);

        LocalDate date = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        System.out.println("date.atStartOfDay(zoneId) : " + date.atStartOfDay(zoneId));

        System.out.println("localDateTime.atZone(zoneId) : " + localDateTime.atZone(zoneId));

    }

    @Test
    public void test10(){

        //当前日期
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        //当前日期减 1个月
        LocalDateTime nowBeforeMonth = now.minusMonths(1);
        System.out.println(nowBeforeMonth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        //指定日期减 1个月
        LocalDateTime localDateTime = LocalDateTime.of(2020, 1,6,14,30,55);
        LocalDateTime localDateTimeBeforeMonth = localDateTime.minusMonths(1);
        // LocalDateTime 是不可变对象，修改一次之后，都是返回新对象，需要重新接收新对象
        localDateTimeBeforeMonth.withHour(23);
        localDateTimeBeforeMonth.withMinute(59);
        localDateTimeBeforeMonth.withSecond(59);
        System.out.println(localDateTimeBeforeMonth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        //指定日期减去 1个月，时分秒 重置为任意值
        LocalDateTime ldt = LocalDateTime.of(2020, 1, 6, 14, 30, 55);
        LocalDateTime ldtbm = ldt.minusMonths(1)
            .withHour(23)
            .withMinute(59)
            .withSecond(59);
        System.out.println(ldtbm.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

}
