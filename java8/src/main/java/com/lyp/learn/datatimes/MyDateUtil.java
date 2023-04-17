package com.lyp.learn.datatimes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Objects;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author liyapu
 * @date 2023-04-12 14:29
 * @description 日期相关工具类
 */
public class MyDateUtil {

    /**
     * Date 按照默认时区 转成 LocalDateTime
     *
     * @param date 日期时间
     * @return LocalDateTime 表示的日期时间
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        if (Objects.isNull(date)) {
            throw new IllegalArgumentException("日期时间不能为空");
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDateTime 按照默认时区 转成 Date
     *
     * @param localDateTime 日期时间
     * @return Date 表示的日期时间
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            throw new IllegalArgumentException("本地日期时间不能为空");
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取指定季度的开始和结束日期
     *
     * @param quarters 需要的季度 0:本季度; 1:下季度; -1:上季度; 以此类推
     * @return Pair.getLeft 季度开始日期，Pair.getRight 季度的结束日期
     */
    public static Pair<LocalDate, LocalDate> getStartEndLocalDateOfQuarter(long quarters) {
        LocalDate localDate = LocalDate.now().plusMonths(quarters * 3);
        Month firstMonthOfQuarter = localDate.getMonth().firstMonthOfQuarter();
        LocalDate startDate = LocalDate.of(localDate.getYear(), firstMonthOfQuarter, 1);

        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        LocalDate endDate = LocalDate
            .of(localDate.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(localDate.isLeapYear()));
        return Pair.of(startDate, endDate);
    }

    /**
     * 获取指定季度的开始和结束日期时间
     * 日期开始时间 00:00:00.000
     * 日期结束时间 23:59:59.999
     *
     * @param quarters 需要的季度 0:本季度; 1:下季度; -1:上季度; 以此类推
     * @return Pair.getLeft 季度开始日期时间，Pair.getRight 季度的结束日期时间
     */
    public static Pair<LocalDateTime, LocalDateTime> getStartEndLocalDateTimeOfQuarter(long quarters) {
        Pair<LocalDate, LocalDate> pair = getStartEndLocalDateOfQuarter(quarters);
        LocalDateTime startDateTime = LocalDateTime.of(pair.getLeft(), LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(pair.getRight(), LocalTime.MIN);
        return Pair.of(startDateTime, endDateTime);
    }

    /**
     * 获取指定季度的开始和结束日期时间 秒数
     *
     * @param quarters 需要的季度 0:本季度; 1:下季度; -1:上季度; 以此类推
     * @return Pair.getLeft 季度开始日期时间的秒数，Pair.getRight 季度的结束日期时间秒数
     */
    public static Pair<Long, Long> getStartEndSecondOfQuarter(long quarters) {
        Pair<LocalDateTime, LocalDateTime> pair = getStartEndLocalDateTimeOfQuarter(quarters);
        long startSecond = pair.getLeft().toEpochSecond(ZoneOffset.ofHours(8));
        long endSecond = pair.getRight().toEpochSecond(ZoneOffset.ofHours(8));
        return Pair.of(startSecond, endSecond);
    }

    /**
     * 获取指定季度的开始和结束日期时间 毫秒数
     *
     * @param quarters 需要的季度 0:本季度; 1:下季度; -1:上季度; 以此类推
     * @return Pair.getLeft 季度开始日期时间的毫秒数，Pair.getRight 季度的结束日期时间毫秒数
     */
    public static Pair<Long, Long> getStartEndMilliSecondOfQuarter(long quarters) {
        Pair<LocalDateTime, LocalDateTime> pair = getStartEndLocalDateTimeOfQuarter(quarters);
        long startMilliSecond = pair.getLeft().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        long endMilliSecond = pair.getRight().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        return Pair.of(startMilliSecond, endMilliSecond);
    }

}