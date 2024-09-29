package com.lyp.learn.common.date;

import com.lyp.learn.common.Assert;
import com.lyp.learn.common.Option;
import com.lyp.learn.common.StrUtil;
import com.lyp.learn.common.tuple.Tuple2;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.zone.ZoneRules;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 日期工具类
 *
 * @author at 2023/12/22 23:58
 */
@SuppressWarnings("unused")
public class LocalDateUtil {

    private LocalDateUtil() {
    }

    /**
     * 默认日期
     */
    public static final LocalDate DEFAULT_DATE = parse("1970-01-01");

    /**
     * 一天的秒数
     */
    private static final Long SECONDS_OF_A_DAY = Duration.ofDays(1).getSeconds();

    /**
     * 一秒的毫秒数
     */
    private static final Long MILLIS_OF_A_SEC = 1000L;

    // *********************************************
    // 字符串 日期 互转

    /**
     * 将毫秒时间戳转换成 {@link LocalDate},使用默认时区
     * <b>注意：此方法使用电脑默认时区，会产生时间偏移, 闰秒问题见 {@link LocalDate}</b>
     *
     * @param epochMilli 毫秒计算的时间戳
     * @return {@link LocalDate}
     */
    public static LocalDate of(long epochMilli) {
        return of(Instant.ofEpochMilli(epochMilli));
    }

    /**
     * 将毫秒时间戳转换成 {@link LocalDate},使用指定时区
     * <b>注意：此方法使用电脑默认时区，会产生时间偏移, 闰秒问题见 {@link LocalDate}</b>
     *
     * @param epochMilli 毫秒计算的时间戳
     * @param zoneId     时区
     * @return {@link LocalDate}
     */
    public static LocalDate of(long epochMilli, ZoneId zoneId) {
        return of(Instant.ofEpochMilli(epochMilli), zoneId);
    }

    /**
     * 获取 {@code LocalDate}实例，通过{@code Instant}和默认时区ID
     *
     * @param instant 实例
     * @return LocalDate
     */

    public static LocalDate of(Instant instant) {
        return of(instant, null);
    }

    /**
     * 获取 {@code LocalDate}实例，通过{@code Instant}和时区ID
     * 如果时区ID为NULL，使用系统默认时区
     *
     * @param instant 实例
     * @param zoneId  时区ID
     * @return LocalDate
     */
    public static LocalDate of(Instant instant, ZoneId zoneId) {
        if (null == instant) {
            return null;
        }
        zoneId = Option.ofNullable(zoneId).orElse(ZoneId.systemDefault());
        ZoneRules rules = zoneId.getRules();
        ZoneOffset offset = rules.getOffset(instant);
        long localSecond = instant.getEpochSecond() + offset.getTotalSeconds();
        long localEpochDay = Math.floorDiv(localSecond, SECONDS_OF_A_DAY);
        return LocalDate.ofEpochDay(localEpochDay);
    }

    /**
     * 将日期按照YYYY-MM-DD格式转换成字符串
     *
     * @param date 日期
     * @return {@link String}
     */
    public static String format(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN));
    }

    /**
     * 将日期按照{@code format}的格式进行格式化输出
     *
     * @param date   日期
     * @param format 日期输出格式,具体格式可参见 {@link DateTimeFormatter}
     * @return {@link String}
     */
    public static String format(LocalDate date, String format) {
        return date.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 将秒时间戳转换成 {@link LocalDate},使用电脑本地时区
     * <b>注意：此方法使用电脑默认时区，会产生时间偏移, 闰秒问题见 {@link LocalDate}</b>
     *
     * @param epochSecond 毫秒计算的时间戳
     * @return {@link LocalDate}
     */
    public static LocalDate ofSecs(Long epochSecond) {
        return of(Instant.ofEpochSecond(epochSecond));
    }

    /**
     * 将秒时间戳转换成 {@link LocalDate},使用指定时区
     * <b>闰秒问题见 {@link LocalDate}</b>
     *
     * @param epochSecond 毫秒计算的时间戳
     * @param zoneId      时区
     * @return {@link LocalDate}
     */
    public static LocalDate ofSecs(Long epochSecond, ZoneId zoneId) {
        return of(Instant.ofEpochSecond(epochSecond), zoneId);
    }

    /**
     * 获得今天的日期,时区按照机器时区计算
     *
     * @return {@link LocalDate}
     */
    public static LocalDate today() {
        return LocalDate.now();
    }

    /**
     * 获得明天的日期,时区按照机器时区计算
     *
     * @return {@link LocalDate}
     */
    public static LocalDate tomorrow() {
        return LocalDate.now().plusDays(1);
    }

    /**
     * 获得昨天的日期,时区按照机器时区计算
     *
     * @return {@link LocalDate}
     */
    public static LocalDate yesterday() {
        return LocalDate.now().minusDays(1);
    }

    /**
     * 将日期转换成日期开始的第一秒的时间戳,采用默认时区
     *
     * @param date 日期
     * @return {@link Long}
     */
    public static Long toEpochSecs(LocalDate date) {
        ZoneRules rules = ZoneId.systemDefault().getRules();
        ZoneOffset offset = rules.getOffset(LocalDateTimeUtil.toInstant(date));
        return date.toEpochDay() * SECONDS_OF_A_DAY - offset.getTotalSeconds();
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
     * 计算两天的间隔, 如果{@code endDateString}表示的日期在{@code startDateString}日期之前,则为负数 例如相邻的两天算差一天
     *
     * @param startDateString 开始的日期,格式为YYYY-MM-DD
     * @param endDateString   结束的日期,格式为YYYY-MM-DD
     * @return {@link Long}
     */
    public static Long getDaysDelta(String startDateString, String endDateString) {
        LocalDate startDate = parse(startDateString);
        LocalDate endDate = parse(endDateString);
        return endDate.toEpochDay() - startDate.toEpochDay();

    }

    /**
     * 计算两天的间隔, 如果{@code endDateString}表示的日期在{@code startDateString}日期之前,则为负数
     *
     * @param startDate 开始的日期
     * @param endDate   结束的日期
     * @return {@link Long}
     */
    public static Long getDaysDelta(LocalDate startDate, LocalDate endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
            return 0L;
        }
        return endDate.toEpochDay() - startDate.toEpochDay();
    }

    /**
     * 计算这是今年的第几天,例如 2018-1-1是第1天,从1开始
     *
     * @param dateString 日期,格式为YYYY-MM-DD
     * @return {@link Integer}
     */
    public static Integer getDayOfYear(String dateString) {
        return getDayOfYear(parse(dateString));
    }

    /**
     * 计算这是今年的第几天,例如 2018-1-1是第1天,从1开始
     *
     * @param date 日期
     * @return {@link Integer}
     */
    public static Integer getDayOfYear(LocalDate date) {
        // 加1是表示的天数
        return getDaysDelta(LocalDate.of(date.getYear(), 1, 1), date).intValue() + 1;
    }

    /**
     * 计算这是今天到明年还有多少天,例如 2021.12.31为1
     *
     * @param dateString 日期,格式为YYYY-MM-DD
     * @return {@link Long}
     */
    public static Long getDayToNextYear(String dateString) {
        return getDayToNextYear(parse(dateString));
    }

    /**
     * 计算这是今天到明年还有多少天,例如 2021.12.31为1
     *
     * @param date 日期
     * @return {@link Long}
     */
    public static Long getDayToNextYear(LocalDate date) {
        return getDaysDelta(date, LocalDate.of(date.getYear() + 1, 1, 1));
    }

    /**
     * 计算这是今天是这个月的第几天,从1开始
     *
     * @param date 日期
     * @return {@link Integer}
     */
    public static Integer getDayOfMonth(LocalDate date) {
        return date.getDayOfMonth();
    }

    /**
     * 计算这是今天是这个月的第几天,从1开始
     *
     * @param date 日期,格式为YYYY-MM-DD
     * @return {@link Integer}
     */
    public static Integer getDayOfMonth(String date) {
        return getDayOfMonth(parse(date));
    }

    /**
     * 计算今天是到下个月还有多少天
     *
     * @param date 日期
     * @return {@link Integer}
     */
    public static Integer getDayToNextMonth(LocalDate date) {
        return getDaysDelta(date, date.plusMonths(1).withDayOfMonth(1)).intValue();
    }

    /**
     * 计算今天是到下个月还有多少天
     *
     * @param dateString 日期,格式为YYYY-MM-DD
     * @return {@link Integer}
     */
    public static Integer getDayToNextMonth(String dateString) {
        return getDayToNextMonth(parse(dateString));
    }

    /**
     * 计算今天是这周的哪天
     *
     * @param dateString 日期,格式为YYYY-MM-DD
     * @return {@link DayOfWeek}
     */
    public static DayOfWeek getDayOfWeek(String dateString) {
        return parse(dateString).getDayOfWeek();
    }

    /**
     * 计算今天是这周的哪天
     *
     * @param date 日期
     * @return {@link DayOfWeek}
     */
    public static DayOfWeek getDayOfWeek(LocalDate date) {
        return date.getDayOfWeek();
    }

    /**
     * 计算这周距离下周还有多少天,周一为下周的开始
     *
     * @param date 日期
     * @return {@link Integer}
     */
    public static Integer getDayToNextWeek(LocalDate date) {
        LocalDate nextWeek = date.plusWeeks(1).minusDays(date.getDayOfWeek().getValue() - 1);
        return getDaysDelta(date, nextWeek).intValue();
    }

    /**
     * 计算这周距离下周还有多少天,周一为下周的开始
     *
     * @param dateString 日期字符串
     * @return {@link Integer}
     */
    public static Integer getDayToNextWeek(String dateString) {
        return getDayToNextWeek(parse(dateString));
    }

    /**
     * 判断两个日期为同一天
     *
     * @param before 第一个日期参数，不能为NULL
     * @param after  第二个日期参数，不能为NULL
     * @return boolean
     * @throws IllegalArgumentException 参数异常
     */
    public static boolean isEqual(LocalDate before, LocalDate after) {
        Assert.notNull(before, "before不能为NULL");
        Assert.notNull(after, "after不能为NULL");
        return before.isEqual(after);
    }

    /**
     * 判断两个日期，第一个日期在第二个日期之前
     *
     * @param before 第一个日期参数，不能为NULL
     * @param after  第二个日期参数，不能为NULL
     * @return boolean
     * @throws IllegalArgumentException 参数异常
     */
    public static boolean isBefore(LocalDate before, LocalDate after) {
        Assert.notNull(before, "before不能为NULL");
        Assert.notNull(after, "after不能为NULL");
        return before.isBefore(after);
    }

    /**
     * 判断两个日期，第一个日期在第二个日期之后
     *
     * @param before 第一个日期参数，不能为NULL
     * @param after  第二个日期参数，不能为NULL
     * @return boolean
     * @throws IllegalArgumentException 参数异常
     */
    public static boolean isAfter(LocalDate before, LocalDate after) {
        Assert.notNull(before, "before不能为NULL");
        Assert.notNull(after, "after不能为NULL");
        return before.isAfter(after);
    }

    /**
     * 判断两个日期，第一个日期在第二个日期之前或者相等
     *
     * @param before 第一个日期参数，不能为NULL
     * @param after  第二个日期参数，不能为NULL
     * @return boolean
     * @throws IllegalArgumentException 参数异常
     */
    public static boolean isBeforeOrEqual(LocalDate before, LocalDate after) {
        return isBefore(before, after) || isEqual(before, after);
    }

    /**
     * 判断两个日期，第一个日期在第二个日期之后或者相等
     *
     * @param before 第一个日期参数，不能为NULL
     * @param after  第二个日期参数，不能为NULL
     * @return boolean
     * @throws IllegalArgumentException 参数异常
     */
    public static boolean isAfterOrEqual(LocalDate before, LocalDate after) {
        return isAfter(before, after) || isEqual(before, after);
    }

    /**
     * 日期字符串转换为日期对象
     *
     * @param text      日期字符串
     * @param formatter 格式字符串
     * @return LocalDate
     * @throws IllegalArgumentException 参数不能为空
     * @throws DateTimeParseException   if the text cannot be parsed
     */
    public static LocalDate parse(String text, String formatter) {
        Assert.isTrue(StrUtil.isNotEmpty(text), () -> new IllegalArgumentException("text不能为空"));
        Assert.isTrue(StrUtil.isNotEmpty(formatter), () -> new IllegalArgumentException("formatter不能为空"));
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(formatter));
    }

    /**
     * 日期字符串转换为日期对象
     *
     * @param text 日期字符串
     * @return LocalDate
     * @throws IllegalArgumentException 参数不能为空
     * @throws DateTimeParseException   if the text cannot be parsed
     */
    public static LocalDate parse(String text) {
        Assert.isTrue(StrUtil.isNotEmpty(text), () -> new IllegalArgumentException("text不能为空"));
        return DatePattern
                .getPattern(text)
                .map(formatter -> parse(text, formatter))
                .orElseThrow(() -> {
                    String patterns = StrUtil.joinWith(";", DatePattern.getAllRegexPattern()
                            .stream()
                            .map(Tuple2::v2)
                            .collect(Collectors.toList()));

                    return new IllegalArgumentException(StrUtil.format("text:{}没有找到合适的日期格式,当前支持的日期格式有:{}",
                            text,
                            patterns));
                });
    }

    /**
     * 两个日期的时间差
     *
     * @param startDateInclude 开始日期（包括）
     * @param endDateExclude   结束日期（不包括）
     * @param unit             时间差单位
     * @return 时间差
     */
    public static long between(LocalDate startDateInclude, LocalDate endDateExclude,
                               ChronoUnit unit) {
        return unit.between(startDateInclude, endDateExclude);
    }

    /**
     * 两个日期相差的天数
     *
     * @param startDateInclude 开始日期（包括）
     * @param endDateExclude   结束日期（不包括）
     * @return 相差的天数
     */
    public static long betweenOfDays(LocalDate startDateInclude, LocalDate endDateExclude) {
        return between(startDateInclude, endDateExclude, ChronoUnit.DAYS);
    }

    /**
     * 距离1970-01-01的天数
     *
     * @param endDateExclude 结束日期（不包括）
     * @return 相差的天数
     */
    public static long betweenOfDaysDefault(LocalDate endDateExclude) {
        return betweenOfDays(DEFAULT_DATE, endDateExclude);
    }
}