package com.lyp.learn.utils;

import java.time.*;
import java.time.temporal.WeekFields;

/**
 * @author liyapu
 * @date 2025-09-17 12:30
 * @desc
 */
public class WeekUtils {

    public static void main(String[] args) {

        findLastSameWeekAndDay(LocalDate.now());
        findLastSameWeekAndDay(LocalDate.of(2024, 1, 1)); // 跨年周测试
        findLastSameWeekAndDay(LocalDate.of(2023, 12, 31)); // 年最后一周测试
        findLastSameWeekAndDay(LocalDate.of(2024, 2, 29)); // 闰日测试
        findLastSameWeekAndDay(LocalDate.of(2027, 12, 31)); //
        findLastSameWeekAndDay(LocalDate.of(2026, 12, 31)); //
    }

    /**
     * 查找与输入日期具有相同周数和星期几的去年日期,兜底返回去年今日
     *
     * @param inputDate 输入日期
     * @return 匹配的去年日期或去年今日（自动处理闰年）
     */
    public static LocalDate findLastSameWeekAndDay(LocalDate inputDate) {
        System.out.println("入参: " + inputDate + ",验证去年周数: " + inputDate.get(WeekFields.ISO.weekOfWeekBasedYear()) +
                "，星期" + inputDate.getDayOfWeek().getValue());
        // 尝试在去年直接查找相同周数和星期几
        LocalDate lastYearSameWeek = findInYear(inputDate);
        if (lastYearSameWeek != null) {
            System.out.println("结果: " + lastYearSameWeek + ",验证去年周数: " + lastYearSameWeek.get(WeekFields.ISO.weekOfWeekBasedYear()) +
                    "，星期" + lastYearSameWeek.getDayOfWeek().getValue());
            System.out.println();
            return lastYearSameWeek;
        }
        // 默认返回去年今日（处理闰年情况）
        return getLastYearToday(inputDate);
    }

    /**
     * 在指定年份查找匹配的周数和星期几
     */
    private static LocalDate findInYear(LocalDate inputDate) {
        // 获取输入日期的周数和星期几（ISO标准）
        int inputWeek = inputDate.get(WeekFields.ISO.weekOfWeekBasedYear());
        DayOfWeek inputDayOfWeek = inputDate.getDayOfWeek();

        try {
            int year = inputDate.getYear();
            int lastYear = year - 1;
            //先查找去年的 1月1号
            LocalDate lastYearStart = LocalDate.of(lastYear, 1, 1);
            // 获取去年的周数和星期几
            int lastInputWeek = lastYearStart.get(WeekFields.ISO.weekOfWeekBasedYear());
            LocalDate lastYearDay = null;

            int yearWeekCount = getWeeksInYearISO(year);
            int lastYearWeekCount = getWeeksInYearISO(lastYear);

            if (lastInputWeek == 1) {
                //去年1月1日是第一周开始
                if (lastYearWeekCount < yearWeekCount) {
                    //去年周数小于今年周数，按照周数星期无法找到，默认降级为去年今日
                    lastYearDay = getLastYearToday(inputDate);
                } else {
                    lastYearDay = lastYearStart.with(WeekFields.ISO.weekOfWeekBasedYear(), inputWeek)
                            .with(WeekFields.ISO.dayOfWeek(), inputDayOfWeek.getValue());
                }
            } else {
                //去年1月1日属于前年周数
                if (lastYearWeekCount < yearWeekCount) {
                    lastYearDay = getLastYearToday(inputDate);
                } else {
                    lastYearDay = lastYearStart.plusDays(7).with(WeekFields.ISO.weekOfWeekBasedYear(), inputWeek)
                            .with(WeekFields.ISO.dayOfWeek(), inputDayOfWeek.getValue());
                }
            }
            return lastYearDay;
        } catch (Exception e) {
//            log.warn("findInYear error, year:{}", inputDate, e);
            System.out.println("findInYear error, year:" + inputDate + ",error:" + e);
            return null;
        }
    }

    /**
     * 获取指定年份的周数（ISO标准）
     *
     * @param year 年份
     * @return 该年的总周数（52或53）
     */
    public static int getWeeksInYearISO(int year) {
        int weekCount = LocalDate.of(year, 12, 31).get(WeekFields.ISO.weekOfWeekBasedYear());
        if (weekCount == 1) {
            return 52;
        }
        return 53;
    }

    /**
     * 安全获取去年今日（处理闰年2月29日情况）
     */
    private static LocalDate getLastYearToday(LocalDate date) {
        int lastYear = date.getYear() - 1;
        MonthDay monthDay = MonthDay.from(date);

        // 处理2月29日情况
        if (monthDay.equals(MonthDay.of(Month.FEBRUARY, 29)) && !Year.isLeap(lastYear)) {
            return LocalDate.of(lastYear, Month.FEBRUARY, 28);
        }
        return LocalDate.of(lastYear, date.getMonth(), date.getDayOfMonth());
    }
}
