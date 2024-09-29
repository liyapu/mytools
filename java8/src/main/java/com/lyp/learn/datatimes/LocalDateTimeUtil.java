package com.lyp.learn.datatimes;

import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public class LocalDateTimeUtil {

    private LocalDateTimeUtil() {
    }

    /**
     * 毫秒转{@link LocalDateTime}，使用默认时区
     *
     * <p>注意：此方法使用默认时区，如果非UTC，会产生时间偏移</p>
     *
     * @param epochMilli 从1970-01-01T00:00:00Z开始计数的毫秒数
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(long epochMilli) {
        return of(Instant.ofEpochMilli(epochMilli));
    }

    /**
     * 毫秒转{@link LocalDateTime}，使用指定时区
     *
     * @param epochMilli 从1970-01-01T00:00:00Z开始计数的毫秒数
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(long epochMilli, ZoneId zoneId) {
        return of(Instant.ofEpochMilli(epochMilli), zoneId);
    }

    /**
     * {@link Instant}转{@link LocalDateTime}，使用默认时区
     *
     * @param instant {@link Instant}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(Instant instant) {
        return of(instant, ZoneId.systemDefault());
    }

    /**
     * {@link Instant}转{@link LocalDateTime}
     *
     * @param instant {@link Instant}
     * @param zoneId  时区
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(Instant instant, ZoneId zoneId) {
        if (null == instant) {
            return null;
        }
        return LocalDateTime.ofInstant(instant,
                Optional.ofNullable(zoneId).orElse(ZoneId.systemDefault()));
    }

    /**
     * 将秒时间戳转换成 {@link LocalDateTime},使用默认时区
     *
     * @param epochSecond 毫秒计算的时间戳
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime ofSecs(Long epochSecond) {
        return of(Instant.ofEpochSecond(epochSecond));
    }

    /**
     * 将秒时间戳转换成 {@link LocalDateTime},使用指定时区
     *
     * @param epochSecond 毫秒计算的时间戳
     * @param zoneId      时区
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime ofSecs(Long epochSecond, ZoneId zoneId) {
        return of(Instant.ofEpochSecond(epochSecond), zoneId);
    }


    /**
     * 修改为一天的开始时间，例如：2020-02-02 00:00:00,000
     *
     * @param time 日期时间 LocalDateTime
     * @return 一天的开始时间
     */
    public static LocalDateTime beginOfDay(LocalDateTime time) {
        return time.with(LocalTime.MIN);
    }

    /**
     * 修改为一天的开始时间，例如：2020-02-02 00:00:00,000
     *
     * @param time 日期时间 时间戳
     * @return 一天的开始时间
     */
    public static LocalDateTime beginOfDay(long time) {
        return beginOfDay(of(time));
    }

    /**
     * 修改为delay天后的开始时间，例如：2020-02-02 00:00:00,000
     *
     * @param delay 天数 , 可正 可负
     * @return delay天后的开始时间
     */
    public static LocalDateTime beginOfDay(int delay) {
        return beginOfDay(now().plusDays(delay));
    }

    /**
     * 修改为一天的<b>开始时间戳</b>
     *
     * @param time LocalDateTime
     * @return 开始时间戳
     */
    public static long beginTimestampOfDay(LocalDateTime time) {
        return toEpochMilli(beginOfDay(time));
    }

    /**
     * 修改为一天的<b>开始时间戳</b>
     *
     * @param time 时间戳
     * @return 开始时间戳
     */
    public static long beginTimestampOfDay(long time) {
        return toEpochMilli(beginOfDay(of(time)));
    }

    /**
     * 修改为某一天的开始时间戳。
     * 该天为 当前的delay天
     * 当delay为正， T + delay天
     * 当delay为负， T- |delay|天
     *
     * @param delay 延迟的天数
     * @return 开始时间戳
     */
    public static long beginTimestampOfDay(int delay) {
        return toEpochMilli(beginOfDay(delay));
    }

    /**
     * 修改为今天的开始时间，例如：2020-02-02 00:00:00,000
     *
     * @return 今天的开始时间
     */
    public static LocalDateTime beginOfToday() {
        return beginOfDay(now());
    }

    /**
     * 修改为今天的开始时间戳，例如：2020-02-02 00:00:00,000
     *
     * @return 今天的开始时间戳
     */
    public static long beginTimestampOfToday() {
        return toEpochMilli(beginOfToday());
    }

    /**
     * 修改为一天的开始时间，临界点可以通过自定义的方式
     *
     * @param time  日期时间
     * @param split 分界点
     * @return 一天的开始时间
     */
    public static LocalDateTime beginOfDayOnCustomSplit(LocalDateTime time, LocalTime split) {
        LocalTime nowLocalTime = time.toLocalTime();
        if (nowLocalTime.isBefore(split)) {
            return time.plusDays(-1).with(split);
        } else {
            return time.with(split);
        }
    }

    /**
     * 修改为一天的开始时间，临界点可以通过自定义的方式
     *
     * @param time      日期时间
     * @param splitHour 分界点
     * @return 一天的开始时间
     */
    public static LocalDateTime beginOfDayOnCustomSplit(LocalDateTime time, int splitHour) {
        LocalTime split = LocalTime.of(splitHour, 0);
        return beginOfDayOnCustomSplit(time, split);
    }

    /**
     * 修改为今天的开始时间，开始时间的临界点可以通过自定义的方式
     * 举例：
     * 以23:00为分界点。
     * 今天的日期为:08-13
     * 则23点之前的今天开始时间为 8-12 23：00
     * 23点之后的今天开始时间为  8-13 23: 00
     *
     * @param splitHour 分界点
     * @return 今天的开始时间（以自定义分界点为准）
     */
    public static LocalDateTime beginOfTodayOnCustomSplit(int splitHour) {
        return beginOfDayOnCustomSplit(now(), splitHour);
    }

    /**
     * <p>档期业务</p>
     * 已23点为临界点，返回当天的售卖开始时间。
     *
     * @return 今天的开始售卖时间
     */
    public static LocalDateTime beginOfSell() {
        return beginOfSell(0);
    }

    /**
     * <p>档期业务</p>
     * 已23点为临界点，返回当天之后delay天的售卖开始时间。
     * * delay为0,表示今天
     * * delay为正,表示今天之后
     * * delay为负,表示今天之前
     *
     * @return delay天后的开始售卖时间戳
     */
    public static LocalDateTime beginOfSell(int delay) {
        LocalTime split = LocalTime.of(23, 0);
        return beginOfDayOnCustomSplit(now().plusDays(delay), split);
    }

    /**
     * 修改为一天的开始时间，临界点可以通过自定义的方式
     *
     * @param time  日期时间
     * @param split 分界点
     * @return 一天的开始时间
     */
    public static long beginTimestampOfDayOnCustomSplit(LocalDateTime time, LocalTime split) {
        return toEpochMilli(beginOfDayOnCustomSplit(time, split));
    }

    /**
     * 修改为一天的开始时间戳，临界点可以通过自定义的方式
     *
     * @param splitHour 当天的小时(24进制)
     * @return 一天的开始时间戳
     */
    public static long beginTimestampOfDayOnCustomSplit(LocalDateTime time, int splitHour) {
        return toEpochMilli(beginOfDayOnCustomSplit(time, splitHour));
    }

    /**
     * 修改为今天的开始时间，临界点可以通过自定义{@code splitHour}的方式
     *
     * @param splitHour 小时分界点(24进制)
     * @return 今天的开始时间
     */
    public static long beginTimestampOfTodayOnCustomSplit(int splitHour) {
        return beginTimestampOfDayOnCustomSplit(now(), splitHour);
    }

    /**
     * <p>档期业务</p>
     * 已23点为临界点，返回当天的售卖开始时间。
     *
     * @return 今天的开始售卖时间戳
     */
    public static long beginTimestampOfSell() {
        return toEpochMilli(beginOfSell());
    }

    /**
     * <p>档期业务</p>
     * 已23点为临界点，返回当天之后delay天的售卖开始时间。
     * delay为0,表示今天
     * delay为正,表示今天之后
     * delay为负,表示今天之前
     *
     * @return delay天后的开始售卖时间戳
     */
    public static long beginTimestampOfSell(int delay) {
        return toEpochMilli(beginOfSell(delay));
    }

    /**
     * 延迟delay天数的履约开始时间（业务）
     *
     * @param delay 天数
     * @return 开始时间
     */
    public static LocalDateTime beginOfDeliveryDay(int delay) {
        return beginOfDay(++delay);
    }

    /**
     * 延迟delay天数的履约开始时间（业务）
     *
     * @param delay 天数
     * @return 开始时间
     */
    public static long beginTimestampOfDeliveryDay(int delay) {
        return toEpochMilli(beginOfDeliveryDay(delay));
    }

    /**
     * 修改为一天的结束时间，例如：2020-02-02 23:59:59,999
     *
     * @param time 日期时间
     * @return 一天的结束时间
     */
    public static LocalDateTime endOfDay(LocalDateTime time) {
        return time.with(LocalTime.MAX);
    }

    /**
     * 修改为一天的结束时间，例如：2020-02-02 23:59:59,999
     *
     * @param time 日期时间戳
     * @return 一天的结束时间
     */
    public static LocalDateTime endOfDay(long time) {
        return endOfDay(of(time));
    }

    /**
     * 修改为一天的结束时间，例如：2020-02-02 23:59:59,999
     * delay:
     * 为正 延后delay天
     * 为负 提前|delay|天
     *
     * @param delay 延迟的天数
     * @return 一天的结束时间
     */
    public static LocalDateTime endOfDay(int delay) {
        return endOfDay(addDays(now(), delay));
    }

    /**
     * 修改为一天的结束时间，临界点可以通过自定义的方式
     *
     * @param time  日期时间
     * @param split 分界点
     * @return 一天的结束
     */
    public static LocalDateTime endOfDayOnCustomSplit(LocalDateTime time, LocalTime split) {
        LocalTime nowLocalTime = time.toLocalTime();
        if (nowLocalTime.isBefore(split)) {
            return time.with(split.plusNanos(-1));
        } else {
            return time.plusDays(1).with(split.plusNanos(-1));
        }
    }

    /**
     * 修改为一天的结束时间，分界点可以通过自定义的方式
     *
     * @param time      日期时间
     * @param splitHour 分界点(24进制)
     * @return 一天的结束时间
     */
    public static LocalDateTime endOfDayOnCustomSplit(LocalDateTime time, int splitHour) {
        LocalTime split = LocalTime.of(splitHour, 0);
        return endOfDayOnCustomSplit(time, split);
    }

    /**
     * 修改为今天的结束时间，例如：2020-02-02 23:59:59,999
     *
     * @return 今天的结束时间
     */
    public static LocalDateTime endOfToday() {
        return endOfDay(now());
    }

    /**
     * 修改为一天的结束时间，例如：2020-02-02 23:59:59,999
     *
     * @param time 日期时间戳
     * @return 一天的结束时间戳
     */
    public static long endTimestampOfDay(long time) {
        return toEpochMilli(endOfDay(time));
    }

    /**
     * 修改为一天的结束时间戳，例如：2020-02-02 23:59:59,999
     *
     * @param time 日期时间
     * @return 一天的结束时间戳
     */
    public static long endTimestampOfDay(LocalDateTime time) {
        return toEpochMilli(endOfDay(time));
    }

    /**
     * 修改为今天的结束时间戳，例如：2020-02-02 23:59:59,999
     *
     * @return 今天的结束时间戳
     */
    public static long endTimestampOfToday() {
        return endTimestampOfDay(now());
    }

    /**
     * 修改为今天的开始时间，分界点可以通过自定义的方式
     *
     * @param splitHour 分界点
     * @return 今天的结束时间
     */
    public static LocalDateTime endOfTodayOnCustomSplit(int splitHour) {
        return endOfDayOnCustomSplit(now(), splitHour);
    }

    /**
     * 获取<b>售卖结束时间</b>, delay为正表示 T + delay  为负 表示 T - |delay|
     *
     * @return 当天的<b>售卖结束时间</b>
     */
    public static LocalDateTime endOfSell(int delay) {
        return endOfDayOnCustomSplit(now().plusDays(delay), 23);
    }

    /**
     * 获取今天的<b>售卖结束时间<b/>
     *
     * @return 今天的<b>售卖结束时间</b>
     */
    public static LocalDateTime endOfSell() {
        return endOfSell(0);
    }

    /**
     * 修改为今天的<b>售卖结束时间戳</b>
     *
     * @return 今天的<b>售卖结束时间戳</b>
     */
    public static long endTimestampOfSell() {
        return endTimestampOfSell(0);
    }

    /**
     * 获取<b>售卖结束时间戳</b>, delay为正表示 T + delay  为负 表示 T - |delay|
     *
     * @return 当天的<b>售卖结束时间戳</b>
     */
    public static long endTimestampOfSell(int delay) {
        return toEpochMilli(endOfSell(delay));
    }

    /**
     * 延迟delay天数的履约结束时间（业务）
     *
     * @param delay 天数
     * @return 开始时间
     */
    public static LocalDateTime endOfDeliveryDay(int delay) {
        return endOfDay(++delay).withNano(0);
    }

    /**
     * 延迟delay天数的履约结束时间（业务）
     *
     * @param delay 天数
     * @return 开始时间
     */
    public static long endTimestampOfDeliveryDay(int delay) {
        return toEpochMilli(endOfDeliveryDay(delay));
    }

    /**
     * 当前时间，默认时区
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * {@link TemporalAccessor}转换为 时间戳（从1970-01-01T00:00:00Z开始的毫秒数）
     *
     * @param temporalAccessor Date对象
     * @return 时间戳
     */
    public static long toEpochMilli(TemporalAccessor temporalAccessor) {
        return toInstant(temporalAccessor).toEpochMilli();
    }

    /**
     * {@link TemporalAccessor}转换为 时间戳（从1970-01-01T00:00:00Z开始的毫秒数）
     *
     * @param temporalAccessor Date对象
     * @return 时间戳
     */
    public static long toEpochSecs(TemporalAccessor temporalAccessor) {
        return toInstant(temporalAccessor).toEpochMilli() / 1000;
    }

    /**
     * {@link TemporalAccessor}转换为 {@link Instant}对象
     *
     * @param temporalAccessor Date对象
     * @return {@link Instant}对象
     */
    public static Instant toInstant(TemporalAccessor temporalAccessor) {
        if (null == temporalAccessor) {
            return null;
        }

        Instant result;
        if (temporalAccessor instanceof Instant) {
            result = (Instant) temporalAccessor;
        } else if (temporalAccessor instanceof LocalDateTime) {
            result = ((LocalDateTime) temporalAccessor).atZone(ZoneId.systemDefault()).toInstant();
        } else if (temporalAccessor instanceof ZonedDateTime) {
            result = ((ZonedDateTime) temporalAccessor).toInstant();
        } else if (temporalAccessor instanceof OffsetDateTime) {
            result = ((OffsetDateTime) temporalAccessor).toInstant();
        } else if (temporalAccessor instanceof LocalDate) {
            result = ((LocalDate) temporalAccessor).atStartOfDay(ZoneId.systemDefault())
                    .toInstant();
        } else if (temporalAccessor instanceof LocalTime) {
            // 指定本地时间转换 为Instant，取当天日期
            result = ((LocalTime) temporalAccessor).atDate(LocalDate.now())
                    .atZone(ZoneId.systemDefault()).toInstant();
        } else if (temporalAccessor instanceof OffsetTime) {
            // 指定本地时间转换 为Instant，取当天日期
            result = ((OffsetTime) temporalAccessor).atDate(LocalDate.now()).toInstant();
        } else {
            result = Instant.from(temporalAccessor);
        }

        return result;
    }

    /**
     * 格式化日期时间为指定格式
     *
     * @param time      {@link TemporalAccessor}
     * @param formatter 日期格式化器，预定义的格式见：{@link DateTimeFormatter}
     * @return 格式化后的字符串
     */
    public static String format(TemporalAccessor time, DateTimeFormatter formatter) {
        if (null == time) {
            return null;
        }
        if (null == formatter) {
            formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        }
        return formatter.format(time);
    }

    /**
     * 格式化日期时间为指定格式
     *
     * @param time   {@link TemporalAccessor}
     * @param format 日期格式 {@link DatePattern}
     * @return 格式化后的字符串
     */
    public static String format(TemporalAccessor time, String format) {
        final DateTimeFormatter formatter = StringUtils.isBlank(format) ? null : DateTimeFormatter.ofPattern(format);
        return format(time, formatter);
    }

    /**
     * 格式化日期时间为yyyy-MM-dd HH:mm:ss格式
     *
     * @param time {@link LocalDateTime}
     * @return 格式化后的字符串
     */
    public static String formatNormal(LocalDateTime time) {
        return format(time, DatePattern.NORM_DATETIME_FORMATTER);
    }

    /**
     * 格式化日期时间为yyyy-MM-dd HH:mm:ss格式
     *
     * @param time {@link LocalDateTime}
     * @return 格式化后的字符串
     */
    public static String format(LocalDateTime time) {
        return format(time, DatePattern.NORM_DATETIME_FORMATTER);
    }

    /**
     * 格式化日期时间为yyyy-MM-dd HH:mm:ss格式
     *
     * @param time 时间戳
     * @return 格式化后的字符串
     */
    public static String formatNormal(long time) {
        return formatNormal(of(time));
    }

    /**
     * 获取两个日期的差，如果结束时间早于开始时间，获取结果为负。
     * <p>
     * 返回结果为时间差的long值
     *
     * @param startTimeInclude 开始时间（包括）
     * @param endTimeExclude   结束时间（不包括）
     * @param unit             时间差单位
     * @return 时间差
     */
    public static long between(LocalDateTime startTimeInclude, LocalDateTime endTimeExclude,
                               ChronoUnit unit) {
        return unit.between(startTimeInclude, endTimeExclude);
    }

    /**
     * 获取两个日期的差，如果结束时间早于开始时间，获取结果为负。
     * <p>
     * 返回结果为时间差的long值
     *
     * @param startTimeInclude 开始时间（包括）
     * @param endTimeExclude   结束时间（不包括）
     * @param unit             时间差单位
     * @return 时间差
     */
    public static long between(long startTimeInclude, long endTimeExclude, ChronoUnit unit) {
        return between(of(startTimeInclude), of(endTimeExclude), unit);
    }

    /**
     * 获取两个日期的差，如果结束时间早于开始时间，获取结果为负。
     * <p>
     * 返回结果为时间差的long值
     *
     * @param startTimeInclude 开始时间（包括）
     * @param endTimeExclude   结束时间（不包括）
     * @return 时间差(天)
     */
    public static long betweenOfDays(long startTimeInclude, long endTimeExclude) {
        return between(of(startTimeInclude), of(endTimeExclude), ChronoUnit.DAYS);
    }

    /**
     * 获取两个日期的差，如果结束时间早于开始时间，获取结果为负。
     * <p>
     * 返回结果为时间差的long值
     *
     * @param startTimeInclude 开始时间（包括）
     * @param endTimeExclude   结束时间（不包括）
     * @return 时间差 (天)
     */
    public static long betweenOfDays(LocalDateTime startTimeInclude, LocalDateTime endTimeExclude) {
        return between(startTimeInclude, endTimeExclude, ChronoUnit.DAYS);
    }

    /**
     * 增加 days 天数
     *
     * @param time 时间戳
     * @param days 天数
     * @return LocalDateTime
     */
    public static LocalDateTime addDays(long time, int days) {
        return addDays(of(time), days);
    }

    /**
     * 增加 days 天数
     *
     * @param time 时间
     * @param days 天数
     * @return LocalDateTime
     */
    public static LocalDateTime addDays(LocalDateTime time, int days) {
        return time.plusDays(days);
    }

    /**
     * 增加 days 天数
     *
     * @param time 时间戳
     * @param days 天数
     * @return 时间戳
     */
    public static long addDaysThenTimestamp(long time, int days) {
        return addDaysThenTimestamp(of(time), days);
    }

    /**
     * 增加 days 天数
     *
     * @param time 时间
     * @param days 天数
     * @return 时间戳
     */
    public static long addDaysThenTimestamp(LocalDateTime time, int days) {
        return toEpochMilli(addDays(time, days));
    }

    /**
     * 增加 hours 小时
     *
     * @param time  时间
     * @param hours 小时
     * @return LocalDateTime
     */
    public static LocalDateTime addHours(LocalDateTime time, int hours) {
        return time.plusHours(hours);
    }

    /**
     * 增加 hours 小时
     *
     * @param time  时间戳
     * @param hours 小时
     * @return LocalDateTime
     */
    public static LocalDateTime addHours(long time, int hours) {
        return addHours(of(time), hours);
    }

    /**
     * 增加 hours 小时
     *
     * @param time  时间
     * @param hours 小时
     * @return 时间戳
     */
    public static long addHoursThenTimestamp(LocalDateTime time, int hours) {
        return toEpochMilli(addHours(time, hours));
    }

    /**
     * 增加 hours 小时
     *
     * @param time  时间
     * @param hours 小时
     * @return 时间戳
     */
    public static long addHoursThenTimestamp(long time, int hours) {
        return toEpochMilli(addHours(of(time), hours));
    }

    /**
     * 获取当前时间戳
     *
     * @return 时间戳
     */
    public static long currentTimeMillis() {
        return toEpochMilli(now());
    }

    /**
     * 转换为LocalDate
     *
     * @param localDateTime 待转换的时间
     */
    public static LocalDate toLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    /**
     * 日期字符串转换为日期对象
     *
     * @param text      日期字符串
     * @param formatter 日期的格式
     * @return LocalDateTime
     * @throws IllegalArgumentException 参数不能为空
     */
    public static LocalDateTime parse(String text, String formatter) {
        return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(formatter));
    }


    /**
     * 获取当前时间的字符串表示
     *
     * @return 返回格式 yyyyMMddHHmmss 样式的字符串时间
     */
    public static String getNowDateTimeFormat() {
        return LocalDateTime.now().format(DatePattern.PURE_DATETIME_FORMATTER);
    }
}
