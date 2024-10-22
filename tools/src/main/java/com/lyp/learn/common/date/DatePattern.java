package com.lyp.learn.common.date;

import com.lyp.learn.common.*;
import com.lyp.learn.common.tuple.Tuple;
import com.lyp.learn.common.tuple.Tuple2;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * DatePattern.java
 *
 * @author at 2023/12/22 23:54
 */
@SuppressWarnings("unused")
public class DatePattern {

    private DatePattern() {
    }

    /**
     * 标准日期时间正则，每个字段支持单个数字或2个数字，包括：
     * yyyy-MM-dd HH:mm:ss.SSS
     * yyyy-MM-dd HH:mm:ss
     * yyyy-MM-dd HH:mm
     * yyyy-MM-dd
     * </pre>
     */
    public static final Pattern REGEX_NORM =
            Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}(\\s\\d{1,2}:\\d{1,2}(:\\d{1,2})?)?(.\\d{1,3})?");

    /**
     * 年月格式：yyyy-MM
     */
    public static final String NORM_MONTH_PATTERN = "yyyy-MM";

    /**
     * 年月格式 ：yyyy-MM
     */
    public static final DateTimeFormatter NORM_MONTH_FORMATTER = DateTimeFormatter.ofPattern(NORM_MONTH_PATTERN);

    /**
     * 简单年月格式：yyyyMM
     */
    public static final String SIMPLE_MONTH_PATTERN = "yyyyMM";
    /**
     * 简单年月格式 ：yyyyMM
     */
    public static final DateTimeFormatter SIMPLE_MONTH_FORMATTER = DateTimeFormatter.ofPattern(SIMPLE_MONTH_PATTERN);

    /**
     * 标准日期格式：yyyy-MM-dd
     */
    public static final String NORM_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 标准日期格式 {@link DateTimeFormatter}：yyyy-MM-dd
     */
    public static final DateTimeFormatter NORM_DATE_FORMATTER = DateTimeFormatter.ofPattern(NORM_DATE_PATTERN);

    /**
     * 标准时间格式：HH:mm:ss
     */
    public static final String NORM_TIME_PATTERN = "HH:mm:ss";

    /**
     * 标准日期格式 {@link DateTimeFormatter}：HH:mm:ss
     */
    public static final DateTimeFormatter NORM_TIME_FORMATTER = DateTimeFormatter.ofPattern(NORM_TIME_PATTERN);

    /**
     * 标准日期时间格式，精确到分：yyyy-MM-dd HH:mm
     */
    public static final String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";

    /**
     * 标准日期格式 {@link DateTimeFormatter}：yyyy-MM-dd HH:mm
     */
    public static final DateTimeFormatter NORM_DATETIME_MINUTE_FORMATTER =
            DateTimeFormatter.ofPattern(NORM_DATETIME_MINUTE_PATTERN);

    /**
     * 标准日期时间格式，精确到秒：yyyy-MM-dd HH:mm:ss
     */
    public static final String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 标准日期时间格式，精确到秒 {@link DateTimeFormatter}：yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter NORM_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(NORM_DATETIME_PATTERN);

    /**
     * 标准日期时间格式，精确到毫秒：yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 标准日期时间格式，精确到毫秒 {@link DateTimeFormatter}：yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final DateTimeFormatter NORM_DATETIME_MS_FORMATTER = DateTimeFormatter.ofPattern(NORM_DATETIME_MS_PATTERN);

    /**
     * ISO8601日期时间格式，精确到毫秒：yyyy-MM-dd HH:mm:ss,SSS
     */
    public static final String ISO8601_PATTERN = "yyyy-MM-dd HH:mm:ss,SSS";

    /**
     * 标准日期格式 {@link DateTimeFormatter}：yyyy-MM-dd HH:mm:ss,SSS
     */
    public static final DateTimeFormatter ISO8601_FORMATTER = DateTimeFormatter.ofPattern(ISO8601_PATTERN);

    /**
     * 标准日期格式：yyyy年MM月dd日
     */
    public static final String CHINESE_DATE_PATTERN = "yyyy年MM月dd日";

    /**
     * 标准日期格式 {@link DateTimeFormatter}：yyyy年MM月dd日
     */
    public static final DateTimeFormatter CHINESE_DATE_FORMATTER = DateTimeFormatter.ofPattern(CHINESE_DATE_PATTERN);

    /**
     * 标准日期格式：yyyy年MM月dd日 HH时mm分ss秒
     */
    public static final String CHINESE_DATE_TIME_PATTERN = "yyyy年MM月dd日HH时mm分ss秒";

    /**
     * 标准日期格式 {@link DateTimeFormatter}：yyyy年MM月dd日HH时mm分ss秒
     */
    public static final DateTimeFormatter CHINESE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(CHINESE_DATE_TIME_PATTERN);

    /**
     * 标准日期格式：yyyyMMdd
     */
    public static final String PURE_DATE_PATTERN = "yyyyMMdd";

    /**
     * 标准日期格式 {@link DateTimeFormatter}：yyyyMMdd
     */
    public static final DateTimeFormatter PURE_DATE_FORMATTER = DateTimeFormatter.ofPattern(PURE_DATE_PATTERN);

    /**
     * 标准日期格式：HHmmss
     */
    public static final String PURE_TIME_PATTERN = "HHmmss";

    /**
     * 标准日期格式 {@link DateTimeFormatter}：HHmmss
     */
    public static final DateTimeFormatter PURE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PURE_TIME_PATTERN);

    /**
     * 标准日期格式：yyyyMMddHHmmss
     */
    public static final String PURE_DATETIME_PATTERN = "yyyyMMddHHmmss";

    /**
     * 标准日期格式 {@link DateTimeFormatter}：yyyyMMddHHmmss
     */
    public static final DateTimeFormatter PURE_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(PURE_DATETIME_PATTERN);

    /**
     * 标准日期格式：yyyyMMddHHmmssSSS
     */
    public static final String PURE_DATETIME_MS_PATTERN = "yyyyMMddHHmmssSSS";

    /**
     * 标准日期格式 {@link DateTimeFormatter}：yyyyMMddHHmmssSSS
     */
    public static final DateTimeFormatter PURE_DATETIME_MS_FORMATTER = DateTimeFormatter.ofPattern(PURE_DATETIME_MS_PATTERN);

    /**
     * 所有的日期格式
     */
    private static final List<String> allPatterns =
            Collections.unmodifiableList(ListUtil.newArrayList(PURE_DATE_PATTERN, NORM_MONTH_PATTERN, SIMPLE_MONTH_PATTERN,
                    NORM_DATE_PATTERN, NORM_TIME_PATTERN, NORM_DATETIME_MINUTE_PATTERN,
                    NORM_DATETIME_PATTERN, NORM_DATETIME_MS_PATTERN, ISO8601_PATTERN,
                    CHINESE_DATE_TIME_PATTERN, PURE_DATETIME_PATTERN,
                    PURE_DATETIME_MS_PATTERN));

    /**
     * 所有的日期格式以及正则
     */
    private static final List<Tuple2<String, String>> allRegexPattern =
            Collections.unmodifiableList(ListUtil.newArrayList(
                    Tuple.of("[\\d]{4}[\\d]{2}[\\d]{2}", PURE_DATE_PATTERN),
                    Tuple.of("[\\d]{4}-[\\d]{2}-[\\d]{2}", NORM_DATE_PATTERN),
                    Tuple.of("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}", NORM_DATETIME_MINUTE_PATTERN),
                    Tuple.of("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2}", NORM_DATETIME_PATTERN),
                    Tuple.of("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2}\\.[\\d]{3}", NORM_DATETIME_MS_PATTERN),
                    Tuple.of("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2},[\\d]{3}", ISO8601_PATTERN),
                    Tuple.of("[\\d]{4}年[\\d]{2}月[\\d]{2}日[\\d]{2}时[\\d]{2}分[\\d]{2}秒", CHINESE_DATE_TIME_PATTERN),
                    Tuple.of("[\\d]{4}[\\d]{2}[\\d]{2}[\\d]{2}[\\d]{2}[\\d]{2}", PURE_DATETIME_PATTERN),
                    Tuple.of("[\\d]{4}[\\d]{2}[\\d]{2}[\\d]{2}[\\d]{2}[\\d]{2}[\\d]{3}", PURE_DATETIME_MS_PATTERN)
            ));

    /**
     * 所有的日期时间格式以及正则
     */
    private static final List<Tuple2<String, String>> allLocalDateTimeRegexPattern =
            Collections.unmodifiableList(ListUtil.newArrayList(
                    Tuple.of("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}", NORM_DATETIME_MINUTE_PATTERN),
                    Tuple.of("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2}", NORM_DATETIME_PATTERN),
                    Tuple.of("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2}\\.[\\d]{3}", NORM_DATETIME_MS_PATTERN),
                    Tuple.of("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2},[\\d]{3}", ISO8601_PATTERN),
                    Tuple.of("[\\d]{4}年[\\d]{2}月[\\d]{2}日[\\d]{2}时[\\d]{2}分[\\d]{2}秒", CHINESE_DATE_TIME_PATTERN),
                    Tuple.of("[\\d]{4}[\\d]{2}[\\d]{2}[\\d]{2}[\\d]{2}[\\d]{2}", PURE_DATETIME_PATTERN),
                    Tuple.of("[\\d]{4}[\\d]{2}[\\d]{2}[\\d]{2}[\\d]{2}[\\d]{2}[\\d]{3}", PURE_DATETIME_MS_PATTERN)
            ));

    /**
     * 获取所有的日期格式化pattern
     *
     * @return 不可修改的List
     */

    public static List<String> getAllPattern() {
        return allPatterns;
    }

    /**
     * 获取所有的日期格式化pattern
     *
     * @return 不可修改的List
     */

    public static List<Tuple2<String, String>> getAllRegexPattern() {
        return allRegexPattern;
    }

    /**
     * 获取所有的日期格式化pattern
     *
     * @return 不可修改的List
     */

    public static List<Tuple2<String, String>> getAllLocalDateTimePatternRegex() {
        return allLocalDateTimeRegexPattern;
    }

    /**
     * 获取当前日期格式的pattern
     *
     * @param dateStr 日期格式字符串
     * @return Option
     * @throws IllegalArgumentException dateStr不能为空
     */

    public static Option<String> getPattern(String dateStr) {
        Assert.isTrue(StrUtil.isNotEmpty(dateStr), () -> new IllegalArgumentException("dateStr不能为空"));
        return Option.fromOptional(CollUtil.filterNullStream(getAllRegexPattern())
                .filter(tuple2 -> StrUtil.isMatch(dateStr, tuple2.v1()))
                .map(Tuple2::v2)
                .findFirst());
    }

    /**
     * 获取当前日期格式的pattern
     *
     * @param dateStr 日期格式字符串
     * @return Option
     * @throws IllegalArgumentException dateStr不能为空
     */

    public static Option<String> getLocalDateTimePattern(String dateStr) {
        Assert.isTrue(StrUtil.isNotEmpty(dateStr), () -> new IllegalArgumentException("dateStr不能为空"));
        return Option.fromOptional(
                CollUtil.filterNullStream(DatePattern.getAllLocalDateTimePatternRegex())
                        .filter(tuple2 -> StrUtil.isMatch(dateStr, tuple2.v1()))
                        .map(Tuple2::v2)
                        .findFirst());
    }
}
