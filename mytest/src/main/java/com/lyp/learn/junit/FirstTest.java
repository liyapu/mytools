package com.lyp.learn.junit;

import cn.hutool.json.JSONUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lyp.learn.hamcrest.City;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-09 13:18
 */
@Slf4j
public class FirstTest {

    @Test
    public void test0004556(){
        Map<String,BigDecimal> baseDaysRestoredSalesMap = null;
        BigDecimal sumReceiveCycleBaseDaysRestoredSales = MapUtils.emptyIfNull(baseDaysRestoredSalesMap).values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sumReceiveCycleBaseDaysRestoredSales);
    }

    @Test
    public void test0222(){
        Map<Long,String> map = new HashMap<>();
        String s = map.get(null);
        System.out.println(s);
    }

    @Test
    public void testadd0122(){
        String  str = "0\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "3\n" +
                "2\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "3\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "4\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "13\n" +
                "0\n" +
                "12\n" +
                "3\n" +
                "3\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "7\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "1\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "3\n" +
                "1\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "4\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "5\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "1\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "2\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "9\n" +
                "0\n" +
                "6\n" +
                "0\n" +
                "1\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "6\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "2\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "1\n" +
                "4\n" +
                "0";
        String[] split = str.split("\n");
        int sum = Arrays.stream(split).mapToInt(a -> Integer.parseInt(a)).sum();
        System.out.println(sum);
    }


    @Test
    public void testJoin01(){
//        List<String> strList = Lists.newArrayList();
        List<String> strList = Lists.newArrayList("aaa","bbb");
        String str = ListUtils.emptyIfNull(strList)
                .stream()
                .collect(Collectors.joining(";"));
        System.out.println(str);
    }

    private static final String TODAY = "20250507";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.BASIC_ISO_DATE;


   @Test
    public void test0021111(){
        List<String> dates = Lists.newArrayList(
                "20250409", "20250410", "20250411", "20250412", "20250413", "20250414", "20250415",
                "20250416", "20250417", "20250418", "20250419", "20250420", "20250421", "20250422",
                "20250423", "20250424", "20250425", "20250426", "20250427", "20250428", "20250429",
                "20250430", "20250501", "20250502", "20250503", "20250504", "20250505", "20250506",
                "20250507", "20250508", "20250509", "20250510", "20250511", "20250512", "20250513",
                "20250514"
        );

        printHeader();
        dates.forEach(this::calculateAndPrintPeriod);
    }

    private  void calculateAndPrintPeriod(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr, DATE_FORMAT);
        LocalDate today = LocalDate.parse(TODAY, DATE_FORMAT);
        long daysDiff = date.toEpochDay() - today.toEpochDay();

        String period;
        if (daysDiff >= -28 && daysDiff <= -22) {
            period = "T-28-T-22";
        } else if (daysDiff >= -21 && daysDiff <= -15) {
            period = "T-21-T-15";
        } else if (daysDiff >= -14 && daysDiff <= -8) {
            period = "T-14-T-8";
        } else if (daysDiff >= -7 && daysDiff <= -1) {
            period = "T-7-T-1";
        } else if (daysDiff >= 0 && daysDiff <= 6) {
            period = "T0-T6";
        } else if (daysDiff == 7) {
            period = "T7";
        } else {
            period = "Unknown";
        }

        System.out.printf("│ %-10s │ %-12s │ %+4d 天 │%n",
                date.format(DateTimeFormatter.ISO_DATE),
                period,
                daysDiff);
    }

    private  void printHeader() {
        System.out.println("┌────────────┬──────────────┬──────────┐");
        System.out.println("│   日期     │    周期      │ 相差天数 │");
        System.out.println("├────────────┼──────────────┼──────────┤");
    }

    @Test
    public void test00211(){
        System.out.println(buildDemandDateStr(LocalDate.now()));
    }

    private static String buildDemandDateStr(LocalDate inputDate) {
        if(Objects.isNull(inputDate)){
            return StringUtils.EMPTY;
        }
        return  inputDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINA);
    }

    @Test
    public void test001a1(){
        System.out.println(LocalDate.of(2027,1,1).with(WeekFields.ISO.weekOfWeekBasedYear(), 10).with(WeekFields.ISO.dayOfWeek(), 1));
        System.out.println(LocalDate.of(2027,1,1).with(WeekFields.ISO.weekOfWeekBasedYear(), 10).with(WeekFields.ISO.dayOfWeek(), 2));
        System.out.println(LocalDate.of(2027,1,1).with(WeekFields.ISO.weekOfWeekBasedYear(), 10).with(WeekFields.ISO.dayOfWeek(), 3));
        System.out.println(LocalDate.of(2027,1,1).with(WeekFields.ISO.weekOfWeekBasedYear(), 10).with(WeekFields.ISO.dayOfWeek(), 4));
        System.out.println(LocalDate.of(2027,1,1).with(WeekFields.ISO.weekOfWeekBasedYear(), 10).with(WeekFields.ISO.dayOfWeek(), 5));
        System.out.println(LocalDate.of(2027,1,1).with(WeekFields.ISO.weekOfWeekBasedYear(), 10).with(WeekFields.ISO.dayOfWeek(), 6));
        System.out.println(LocalDate.of(2027,1,1).with(WeekFields.ISO.weekOfWeekBasedYear(), 10).with(WeekFields.ISO.dayOfWeek(), 7));
        System.out.println();
        System.out.println(LocalDate.of(2027,1,4).with(WeekFields.ISO.weekOfWeekBasedYear(), 10).with(WeekFields.ISO.dayOfWeek(), 1));
        System.out.println(LocalDate.of(2027,1,4).with(WeekFields.ISO.weekOfWeekBasedYear(), 10).with(WeekFields.ISO.dayOfWeek(), 2));
        System.out.println(LocalDate.of(2027,1,4).with(WeekFields.ISO.weekOfWeekBasedYear(), 10).with(WeekFields.ISO.dayOfWeek(), 3));
        System.out.println(LocalDate.of(2027,1,4).with(WeekFields.ISO.weekOfWeekBasedYear(), 10).with(WeekFields.ISO.dayOfWeek(), 4));
        System.out.println(LocalDate.of(2027,1,4).with(WeekFields.ISO.weekOfWeekBasedYear(), 10).with(WeekFields.ISO.dayOfWeek(), 5));
        System.out.println(LocalDate.of(2027,1,4).with(WeekFields.ISO.weekOfWeekBasedYear(), 10).with(WeekFields.ISO.dayOfWeek(), 6));
        System.out.println(LocalDate.of(2027,1,4).with(WeekFields.ISO.weekOfWeekBasedYear(), 10).with(WeekFields.ISO.dayOfWeek(), 7));
        System.out.println();
        System.out.println(LocalDate.of(2027,1,4).with(WeekFields.ISO.weekOfWeekBasedYear(), 53).with(WeekFields.ISO.dayOfWeek(), 7));
        System.out.println(LocalDate.of(2027,1,4).with(WeekFields.ISO.weekOfWeekBasedYear(), 53).with(WeekFields.ISO.dayOfWeek(), 7));
        System.out.println(LocalDate.of(2027,1,4).with(WeekFields.ISO.weekOfWeekBasedYear(), 53).with(WeekFields.ISO.dayOfWeek(), 7));
    }


    @Test
    public void a3(){
        List<BigDecimal> list1 = Lists.newArrayList(new BigDecimal("1"),new BigDecimal("0"),null,new BigDecimal("5"));

//        Optional<BigDecimal> reduce1 = list1.stream().reduce(BigDecimal::add);
//        System.out.println(reduce1.get());

        BigDecimal reduce2 = list1.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(reduce2);
    }

    @Test
    public void a2(){
        System.out.println(new BigDecimal("12.3456").intValue());
        System.out.println(new BigDecimal("12.456").intValue());
        System.out.println(new BigDecimal("12.567").intValue());
        System.out.println(new BigDecimal("12.678").intValue());
        System.out.println("-------");
        System.out.println(new BigDecimal("12.3456").multiply(new BigDecimal("100")).intValue());
        System.out.println(new BigDecimal("12.456").multiply(new BigDecimal("100")).intValue());
        System.out.println(new BigDecimal("12.567").multiply(new BigDecimal("100")).intValue());
        System.out.println(new BigDecimal("12.678").multiply(new BigDecimal("100")).intValue());
    }

    @Test
    public void testa2(){
        System.out.println(getFutureDaysRange(LocalDate.now(),7));
    }

    public static List<LocalDate> getFutureDaysRange(LocalDate baseDate, int daysAfter) {
        if (daysAfter <= 0) {
            throw new IllegalArgumentException("天数参数必须大于0");
        }

        List<LocalDate> dateRange = new ArrayList<>(daysAfter);

        for (int i = 1; i <= daysAfter; i++) {
            dateRange.add(baseDate.plusDays(i));
        }

        return dateRange;
    }


    @Test
    public void testa1(){
        System.out.println(getPreviousDaysRange(LocalDate.now(),7));
    }
    /**
     * 获取从今天(T0)往前N天到昨天(T0-1)的日期列表
     * @param daysBefore 要获取的天数范围（如7表示[T0-7, T0-1]）
     * @return 包含N个LocalDate的List，按从早到晚排序
     * @throws IllegalArgumentException 如果daysBefore为负数
     */
    public static List<LocalDate> getPreviousDaysRange(LocalDate baseDate,int daysBefore) {
        if (daysBefore < 0) {
            throw new IllegalArgumentException("天数参数不能为负数");
        }

        LocalDate startDate = baseDate.minusDays(daysBefore);
        List<LocalDate> dateRange = new ArrayList<>(daysBefore);

        // Java 8兼容的日期遍历方式
        for (int i = 0; i < daysBefore; i++) {
            dateRange.add(startDate.plusDays(i));
        }

        return dateRange;
    }

    @Test
    public void test0001211(){

//        findMatchingDateLastYear(LocalDate.of(2025, 9, 16));
        findMatchingDateLastYear(LocalDate.of(2024, 2, 29));
//        findMatchingDateLastYear(LocalDate.of(2023,12,31));
//        findMatchingDateLastYear(LocalDate.of(2027,12,31));
    }

    public static LocalDate findMatchingDateLastYear(LocalDate currentDate) {
        System.out.println("今年: " + currentDate + ",周数: " + currentDate.get(WeekFields.ISO.weekOfWeekBasedYear()) +
                "，星期" + currentDate.getDayOfWeek().getValue());

        // 获取当前周数
        int currentWeek = currentDate.get(WeekFields.ISO.weekOfWeekBasedYear());
        // 获取当前是星期几
        DayOfWeek currentDayOfWeek = currentDate.getDayOfWeek();

        // 尝试在去年找到相同周数和星期几的日期
        LocalDate lastYearDate = findDateInLastYear(
                currentDate.getYear() - 1,
                currentWeek,
                currentDayOfWeek
        );

        // 如果找不到，尝试调整星期几
        if (lastYearDate == null) {
            for (int i = 1; i < 7; i++) {
                DayOfWeek adjustedDay = currentDayOfWeek.minus(i);
                lastYearDate = findDateInLastYear(
                        currentDate.getYear() - 1,
                        currentWeek,
                        adjustedDay
                );
                if (lastYearDate != null) break;
            }
        }

        // 如果仍然找不到，尝试调整周数
        if (lastYearDate == null) {
            for (int i = 1; i < currentWeek; i++) {
                lastYearDate = findDateInLastYear(
                        currentDate.getYear() - 1,
                        currentWeek - i,
                        currentDayOfWeek
                );
                if (lastYearDate != null) break;
            }
        }

//        return lastYearDate != null ? lastYearDate : LocalDate.of(currentDate.getYear() - 1, 1, 1); // 默认返回去年第一天
        lastYearDate =  lastYearDate != null ? lastYearDate : getLastYearSameDay(currentDate);
        System.out.println("目标日期: " + lastYearDate + ",周数: " + lastYearDate.get(WeekFields.ISO.weekOfWeekBasedYear()) +
                "，星期" + lastYearDate.getDayOfWeek().getValue());
        System.out.println();
        return lastYearDate;
    }

    private static LocalDate findDateInLastYear(int year, int week, DayOfWeek dayOfWeek) {
        // 获取去年的第一周第一天(ISO标准周一为第一天)
        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1)
                .with(WeekFields.ISO.dayOfWeek(), 1);

        // 计算目标周的周一
        LocalDate targetWeekMonday = firstDayOfYear.plusWeeks(week - 1);

        // 计算该周的目标星期几
        LocalDate targetDate = targetWeekMonday.with(dayOfWeek);

        // 检查是否确实属于目标周
        if (targetDate.get(WeekFields.ISO.weekOfWeekBasedYear()) == week) {
            return targetDate;
        }
        return null;
    }

    /**
     * 获取去年今日日期，处理特殊日期（如闰年2月29日）
     * @param currentDate 当前日期
     * @return 去年对应的日期，若去年不存在相同日期则返回2月28日
     */
    public static LocalDate getLastYearSameDay(LocalDate currentDate) {
        int lastYear = currentDate.getYear() - 1;
        int month = currentDate.getMonthValue();
        int day = currentDate.getDayOfMonth();

        // 处理2月29日特殊情况
        if (month == 2 && day == 29 && !LocalDate.of(lastYear, 1, 1).isLeapYear()) {
            return LocalDate.of(lastYear, 2, 28);
        }

        // 处理其他月份31日的情况（如4月31日不存在）
        try {
            return LocalDate.of(lastYear, month, day);
        } catch (Exception e) {
            // 如果日期无效（如9月31日），返回该月最后一天
            return LocalDate.of(lastYear, month, 1)
                    .withDayOfMonth(LocalDate.of(lastYear, month, 1).lengthOfMonth());
        }
    }

    @Test
    public void test000121(){
        System.out.println(getFirstWeekMondayOfLastYear(LocalDate.now()));;
    }

    public static LocalDate getFirstWeekMondayOfLastYear(LocalDate currentDate) {
        // 获取去年的年份
        int lastYear = currentDate.getYear() - 1;

        // 使用ISO周标准(周一为一周的第一天)
        WeekFields weekFields = WeekFields.ISO;

        // 获取周字段定义(根据地区)
//        WeekFields weekFields = WeekFields.of(Locale.CHINA);
        // 获取当前是今年的第几周
        int weekOfYear = currentDate.get(weekFields.weekOfWeekBasedYear());
        // 获取当前是星期几
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        System.out.println("当前日期: " + currentDate + ",今年第" + weekOfYear + "周，星期" + dayOfWeek.getValue());



        // 获取去年的第一周第一天(周一)
        LocalDate lastYearSameWeekDate = LocalDate.of(lastYear, 1, 1)
                .with(weekFields.dayOfWeek(), 1L); // 调整为周一

        System.out.println("去年相同周数和星期几的日期: " + lastYearSameWeekDate + ",验证去年周数: " + lastYearSameWeekDate.get(weekFields.weekOfWeekBasedYear()) +
                "，星期" + lastYearSameWeekDate.getDayOfWeek().getValue());

        // 计算第38周的周一
        LocalDate targetWeekMonday = lastYearSameWeekDate.plusWeeks(weekOfYear - 1);

        // 计算该周的星期二
        LocalDate targetDate = targetWeekMonday.with(dayOfWeek);

        // 检查是否跨年(周数属于去年还是前年)
        int actualWeek = targetDate.get(WeekFields.ISO.weekOfWeekBasedYear());
        if (actualWeek != weekOfYear) {
            // 如果周数不匹配，说明跨年了，需要往前调整一周
            targetDate = targetDate.minusWeeks(1);
        }



//        // 验证是否确实是第一周(可能跨年)
//        if (lastYearSameWeekDate.get(weekFields.weekOfWeekBasedYear()) != 1) {
//            lastYearSameWeekDate = lastYearSameWeekDate.plusWeeks(1);
//        }
        System.out.println("去年相同周数和星期几的日期: " + lastYearSameWeekDate + ",验证去年周数: " + lastYearSameWeekDate.get(weekFields.weekOfWeekBasedYear()) +
                "，星期" + lastYearSameWeekDate.getDayOfWeek().getValue());
        System.out.println();

        return lastYearSameWeekDate;

    }

        @Test
    public void test00001231111(){
        // 当前日期
        LocalDate currentDate = LocalDate.of(2025, 9, 16);

        WeekFields weekFields = WeekFields.of(Locale.CHINA);

        // 获取当前周数和星期
        int currentWeek = currentDate.get(weekFields.weekOfWeekBasedYear());
        DayOfWeek currentDayOfWeek = currentDate.getDayOfWeek();

        // 计算去年的相同周数和星期
        LocalDate lastYearDate = findSameWeekLastYear(currentDate, currentWeek, currentDayOfWeek, weekFields);

        System.out.println("去年相同周数和星期二的日期是: " + lastYearDate);
    }

    public static LocalDate findSameWeekLastYear(LocalDate date, int targetWeek, DayOfWeek targetDay, WeekFields weekFields) {
        // 获取去年的年份
        int lastYear = date.getYear() - 1;

        // 构建去年的第一周第一天(ISO标准周一为第一天)
        LocalDate firstDayOfLastYear = LocalDate.of(lastYear, 1, 1)
                .with(weekFields.dayOfWeek(), 1); // 调整为周一

        // 计算第38周的周一
        LocalDate targetWeekMonday = firstDayOfLastYear.plusWeeks(targetWeek - 1);

        // 计算该周的星期二
        LocalDate targetDate = targetWeekMonday.with(targetDay);

        // 检查是否跨年(周数属于去年还是前年)
        int actualWeek = targetDate.get(weekFields.weekOfWeekBasedYear());
        if (actualWeek != targetWeek) {
            // 如果周数不匹配，说明跨年了，需要往前调整一周
            targetDate = targetDate.minusWeeks(1);
        }
        return targetDate;
    }




    @Test
    public void test0000123111(){
        calLastYearSameWeekDay(LocalDate.now());
        calLastYearSameWeekDay(LocalDate.of(2023,12,31));
        calLastYearSameWeekDay(LocalDate.of(2027,12,31));
    }

    private static void calLastYearSameWeekDay(LocalDate currentDate) {

        /// ////////
        // 获取周字段定义(根据地区)
        WeekFields weekFields = WeekFields.of(Locale.CHINA);
        // 获取当前是今年的第几周
        int weekOfYear = currentDate.get(weekFields.weekOfWeekBasedYear());
        // 获取当前是星期几
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        System.out.println("当前日期: " + currentDate + ",今年第" + weekOfYear + "周，星期" + dayOfWeek.getValue());

        LocalDate lastYearDate = currentDate.minusYears(1);

        // 直接定位到去年相同周数的第一周
        // 计算去年相同周数和星期几的日期
//        LocalDate lastYearSameWeekDate = lastYearDate
//                .with(weekFields.weekOfWeekBasedYear(), weekOfYear)
//                .with(weekFields.dayOfWeek(), dayOfWeek.getValue()+1); // 设置为该周的第一天

        // 定位到去年该周的第一天(周一)
        LocalDate lastYearSameWeekDate = lastYearDate
                .with(weekFields.weekOfWeekBasedYear(), weekOfYear)
                .with(weekFields.dayOfWeek(), 1);
        System.out.println("去年相同周数和星期几的日期: " + lastYearSameWeekDate + ",验证去年周数: " + lastYearSameWeekDate.get(weekFields.weekOfWeekBasedYear()) +
                "，星期" + lastYearSameWeekDate.getDayOfWeek().getValue());

        // 计算该周中与目标星期几相同的日期
        lastYearSameWeekDate =  lastYearSameWeekDate.plusDays(dayOfWeek.getValue() - 1);


        System.out.println("去年相同周数和星期几的日期: " + lastYearSameWeekDate + ",验证去年周数: " + lastYearSameWeekDate.get(weekFields.weekOfWeekBasedYear()) +
                "，星期" + lastYearSameWeekDate.getDayOfWeek().getValue());
        System.out.println();
    }

    private static LocalDate findLastYearSameWeekDate(LocalDate currentDate,
                                                      int weekOfYear,
                                                      DayOfWeek dayOfWeek) {
        WeekFields weekFields = WeekFields.of(Locale.CHINA);
        LocalDate lastYearDate = currentDate.minusYears(1);

        // 直接定位到去年相同周数的第一周
        LocalDate firstDayOfTargetWeek = lastYearDate
                .with(weekFields.weekOfWeekBasedYear(), weekOfYear)
                .with(weekFields.dayOfWeek(), dayOfWeek.getValue()+1); // 设置为该周的第一天

        // 计算该周中与当前星期几相同的日期
//        int daysToAdd = dayOfWeek.getValue() - 1;
//        return firstDayOfTargetWeek.plusDays(daysToAdd);
        return firstDayOfTargetWeek;
    }

    @Test
    public void test000012311(){
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        System.out.println("当前日期: " + currentDate);

        // 获取周字段定义(根据地区)
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        // 获取当前是今年的第几周
        int weekOfYear = currentDate.get(weekFields.weekOfWeekBasedYear());

        // 获取当前是星期几
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        System.out.println("今年第" + weekOfYear + "周，星期" + dayOfWeek.getValue());

        // 计算去年相同周数和星期几的日期
        LocalDate lastYearDate = findLastYearSameWeekDay(currentDate, weekOfYear, dayOfWeek);

        System.out.println("去年相同周数的日期: " + lastYearDate);
        System.out.println("去年第" + lastYearDate.get(weekFields.weekOfWeekBasedYear()) +
                "周，星期" + lastYearDate.getDayOfWeek().getValue());
    }

    private static LocalDate findLastYearSameWeekDay(LocalDate currentDate, int weekOfYear, DayOfWeek dayOfWeek) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        LocalDate lastYearDate = currentDate.minusYears(1);

        // 尝试直接查找相同周数和星期几
        LocalDate candidate = lastYearDate
                .with(weekFields.weekOfWeekBasedYear(), weekOfYear)
                .with(weekFields.dayOfWeek(), dayOfWeek.getValue());

        // 验证找到的日期是否确实在去年的同一周
        if (candidate.get(weekFields.weekOfWeekBasedYear()) == weekOfYear) {
            return candidate;
        }

        // 如果找不到，则逐日向前查找
        for (int i = 1; i <= 6; i++) {
            candidate = candidate.minusDays(1);
            if (candidate.get(weekFields.weekOfWeekBasedYear()) == weekOfYear &&
                    candidate.getDayOfWeek() == dayOfWeek) {
                return candidate;
            }
        }

        // 如果仍然找不到，返回最接近的日期
        return candidate;
    }

    @Test
    public void test00001231(){
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();

        // 获取周字段定义(根据地区)
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        // 获取当前是今年的第几周
        int weekOfYear = currentDate.get(weekFields.weekOfWeekBasedYear());

        // 获取当前是星期几
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

        // 计算去年相同周数和星期几的日期
        LocalDate lastYearSameWeekDate = currentDate.minusYears(1)
                .with(weekFields.weekOfWeekBasedYear(), weekOfYear)
                .with(weekFields.dayOfWeek(), dayOfWeek.getValue());

        System.out.println("当前日期: " + currentDate);
        System.out.println("今年第" + weekOfYear + "周，星期" + dayOfWeek.getValue());
        System.out.println("去年相同周数的日期: " + lastYearSameWeekDate);
        System.out.println("去年第" + lastYearSameWeekDate.get(weekFields.weekOfWeekBasedYear()) +
                "周，星期" + lastYearSameWeekDate.getDayOfWeek().getValue());
    }

    public static BigDecimal divide(BigDecimal num1, BigDecimal num2, int scale) {
        if (num1 == null || num2 == null || num2.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        return num1.divide(num2, scale, RoundingMode.HALF_UP);
    }

    public static String divideStr(BigDecimal num1, BigDecimal num2, int scale) {
        return Optional.ofNullable(divide(num1, num2, scale))
                .map(BigDecimal::toPlainString)
                .orElse(StringUtils.EMPTY);
    }

    public static String divideStr(BigDecimal num1, BigDecimal num2) {
        return divideStr(num1, num2, 1);
    }

    @Test
    public void test0000123(){
        BigDecimal num1 = new BigDecimal("122.2345");
        BigDecimal num2 = new BigDecimal("23.566");
        System.out.println(divideStr(num1, num2));
        System.out.println(divideStr(num1, num2, 3));
    }


    public static List<String> generateHalfHourIntervals() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.with(LocalTime.MIN);
        List<String> intervals = new ArrayList<>();

        while (startOfDay.isBefore(now)) {
            int currentMinute = startOfDay.getMinute();
            int adjustedMinute = currentMinute < 30 ? 0 : 30;
            LocalDateTime adjustedTime = startOfDay.withMinute(adjustedMinute);

            // 跳过当前未完成的半小时区间
            if (adjustedTime.isBefore(now)) {
                intervals.add(adjustedTime.format(FORMATTER));
            }
            startOfDay = startOfDay.plusMinutes(30);
        }
        return intervals;
    }

    @Test
    public void test000012212() {
        generateHalfHourIntervals().forEach(System.out::println);
    }


    public static List<String> generateAdjustedTimestamps() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.with(LocalTime.MIN);
        LocalDateTime adjustedEnd = now.withMinute(now.getMinute() < 30 ? 0 : 30);
        List<String> timestamps = new ArrayList<>();

        while (startOfDay.isBefore(adjustedEnd)) {
            int minute = startOfDay.getMinute();
            LocalDateTime adjustedTime = startOfDay.withMinute(minute < 30 ? 0 : 30);
            timestamps.add(adjustedTime.format(FORMATTER));
            startOfDay = startOfDay.plusMinutes(30);
        }
        return timestamps;
    }


    @Test
    public void test00001221() {
        generateAdjustedTimestamps().forEach(System.out::println);
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    public static List<String> generateHalfHourTimestamps(LocalDate baseLocalDate) {
        LocalTime localTime = LocalTime.now();
        int nowHour = localTime.getHour();
        int nowMinute = localTime.getMinute() < 30 ? 0 : 30;


        LocalDateTime baseDayCurrent = baseLocalDate.atTime(nowHour, nowMinute, 0, 0);
        LocalDateTime baseStartOfDay = baseLocalDate.atTime(LocalTime.MIN);
        List<String> dateHourList = new ArrayList<>();

        while (baseStartOfDay.isBefore(baseDayCurrent)) {
            int minute = baseStartOfDay.getMinute();
            int adjustedMinute = minute < 30 ? 0 : 30;
            LocalDateTime adjustedTime = baseStartOfDay.withMinute(adjustedMinute);

            dateHourList.add(adjustedTime.format(FORMATTER));

            baseStartOfDay = baseStartOfDay.plusMinutes(30);
        }
        return dateHourList;
    }


    @Test
    public void test0000122() {
        LocalDate baseLocalDate = LocalDate.now().minusDays(10);
        generateHalfHourTimestamps(baseLocalDate).forEach(System.out::println);
    }


    @Test
    public void test111100002() {
        String str = "hello,wold, 你好，世界!!!";
        System.out.println(str);
        byte[] bytes = str.getBytes();
        System.out.println(bytes);
        System.out.println(new String(bytes));
    }

    @Test
    public void test110001() {
        List<String> result = new ArrayList<>();
        List<String> s1List = Lists.newArrayList("1", "2", "3");
        List<String> s2List = Collections.emptyList();
        List<String> s3List = Lists.newArrayList("4", "5");

        result.addAll(s1List);
        result.addAll(s2List);
        result.addAll(s3List);
        System.out.println(result);
    }

    @Test
    public void test100001() {
        double a = 200.00;
        double b = -200.00;
        System.out.println(a);
        System.out.println(b);
    }


    @Test
    public void test0000002() {
        System.out.println(getWeekNumber(LocalDate.of(2026, 01, 01)));
        System.out.println(getWeekNumber(LocalDate.of(2026, 01, 02)));
        System.out.println(getWeekNumber(LocalDate.of(2026, 01, 03)));
        System.out.println(getWeekNumber(LocalDate.of(2026, 01, 04)));
        System.out.println(getWeekNumber(LocalDate.of(2026, 01, 05)));
        System.out.println(getWeekNumber(LocalDate.of(2026, 01, 06)));
        System.out.println(getWeekNumber(LocalDate.of(2026, 01, 07)));
        System.out.println(getWeekNumber(LocalDate.of(2026, 01, 8)));
        System.out.println(getWeekNumber(LocalDate.of(2026, 01, 9)));
    }

    @Test
    public void test0000001() {
        System.out.println(getWeekNumber(LocalDate.of(2025, 01, 01)));
        System.out.println(getWeekNumber(LocalDate.of(2025, 01, 02)));
        System.out.println(getWeekNumber(LocalDate.of(2025, 01, 03)));
        System.out.println(getWeekNumber(LocalDate.of(2025, 01, 04)));
        System.out.println(getWeekNumber(LocalDate.of(2025, 01, 05)));
        System.out.println(getWeekNumber(LocalDate.of(2025, 01, 06)));
        System.out.println(getWeekNumber(LocalDate.of(2025, 01, 07)));
        System.out.println(getWeekNumber(LocalDate.of(2025, 01, 8)));
        System.out.println(getWeekNumber(LocalDate.of(2025, 01, 9)));
    }

    public static int getWeekNumber(LocalDate date) {
        DayOfWeek daysOfWeek = LocalDate.of(date.getYear(), 1, 1).getDayOfWeek();
        int rightDays = DayOfWeek.SUNDAY.getValue() - daysOfWeek.getValue() + 1;
        // ISO 周系统，周一为一周的第一天，且第一周至少包含4天
        WeekFields weekFields = WeekFields.ISO;
        // 如果第一周不够4天，则升级为第一周
        if (rightDays < weekFields.getMinimalDaysInFirstWeek()) {
            return date.get(weekFields.weekOfYear()) + 1;
        } else {
            return date.get(weekFields.weekOfYear());
        }
    }

    @Test
    public void test000001() {
        // 创建示例Map
        Map<Long, List<Long>> rdcId2FailedRawSkuListMap = new HashMap<>();
        rdcId2FailedRawSkuListMap.put(1001L, Arrays.asList(5001L, 5002L, 5003L));
        rdcId2FailedRawSkuListMap.put(2002L, Arrays.asList(6001L, 6002L));

        // 转换为易读字符串
        String result = rdcId2FailedRawSkuListMap.entrySet().stream()
                .map(entry -> String.format("rdcId:%d 失败商品列表: %s",
                        entry.getKey(),
                        entry.getValue().stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(","))))
                .collect(Collectors.joining("; "));

        System.out.println(result);
    }

    @Test
    public void test00031() {
        // 原始List
        List<Integer> numbers = Arrays.asList(5, 2, 8, 2, 5, 2, 2, 2, 1, 3);

        // 1. 使用自定义比较器创建TreeSet(降序)
        Set<Integer> sortedSet = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1); // 降序排列
            }
        });

        // 2. 将List元素添加到TreeSet
        sortedSet.addAll(numbers);

        // 3. 结果输出
        System.out.println("原始List: " + numbers);
        System.out.println("排序后Set: " + sortedSet);

        // 4. 如果需要转回List
        List<Integer> sortedList = new ArrayList<>(sortedSet);
        System.out.println("排序后List: " + sortedList);
    }

    @Test
    public void tst0002() {
        LocalDate date1 = LocalDate.of(2025, 5, 1);
        LocalDate date2 = LocalDate.of(2025, 5, 3);

        // 方法1：推荐方式
        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        System.out.println("间隔天数: " + daysBetween);
    }

    @Test
    public void test00001() {
        String a = "SHD202505190000034736";
        System.out.println(a.substring(3, 11));
        String b = "PO202505190000034747";
        System.out.println(b.substring(2, 10));

    }

    @Test
    public void test00002() {
        City city1 = new City();
        city1.setName("北京");
        city1.setState("100");

        City city2 = new City();
        city2.setName("上海");
        city2.setState("200");

        List<City> cityList1 = Lists.newArrayList(city1, city2);
        System.out.println(cityList1);
        System.out.println("---------------");

        List<City> cityList2 = cityList1.stream()
                .map(c -> {
                    if (c.getState().equals("100")) {
                        c.setName("北京修改后的值");
                    }
                    return c;
                }).collect(Collectors.toList());
        System.out.println(cityList1);
        System.out.println(cityList2);
    }

    @Test
    public void test0001() {
        Boolean hit = null;
        System.out.println(Boolean.TRUE.equals(hit));
        System.out.println(Boolean.FALSE.equals(hit));
        int a = 3;
        if (a > 1 && Boolean.TRUE.equals(hit)) {
            System.out.println("在白名单，不应该执行到此");
        } else {
            System.out.println("不在在白名单，不应该执行到此");
        }
    }

    @Test
    public void test0003() {
        String numStr1 = "10";
        String numStr2 = "2";

        // 字符串比较
        int stringCompareResult = numStr1.compareTo(numStr2);
        if (stringCompareResult < 0) {
            System.out.println(numStr1 + " 小于 " + numStr2 + " (字符串比较)");
        } else if (stringCompareResult > 0) {
            System.out.println(numStr1 + " 大于 " + numStr2 + " (字符串比较)");
        } else {
            System.out.println(numStr1 + " 等于 " + numStr2 + " (字符串比较)");
        }

        // 数字比较
        int num1 = Integer.parseInt(numStr1);
        int num2 = Integer.parseInt(numStr2);
        if (num1 < num2) {
            System.out.println(num1 + " 小于 " + num2 + " (数字比较)");
        } else if (num1 > num2) {
            System.out.println(num1 + " 大于 " + num2 + " (数字比较)");
        } else {
            System.out.println(num1 + " 等于 " + num2 + " (数字比较)");
        }
    }


    @Test
    public void test0002() {
        List<Integer> numbers = IntStream.range(0, 100)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(numbers);
    }

    @Test
    public void test001() {
        Map<Long, Long> map = new HashMap<>();
        //[100551198173809,100551238455134,100551133851243,100551301491118,100551301264893]}
        //,response:{"code":0,"errorMessage":"success","rdcId":323,"skuToTargetInventory":{}}
        map.put(100551198173809L, 100L);
        map.put(100551238455134L, 200L);
        map.put(100551133851243L, 300L);
        map.put(100551301491118L, 400L);
        map.put(100551301264893L, 500L);
        System.out.println(JSONUtil.toJsonStr(map));
    }

    @Test
    public void test002() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 11);
        map.put(2, null);
        //get存在的值
        int a1 = map.get(1);
        //get存在的值，但是value为null
        //int a2 = map.get(2);//java.lang.NullPointerException
        //int a2 = map.getOrDefault(2, 0);//java.lang.NullPointerException
        int a2 = Optional.ofNullable(map.get(2)).orElse(0);
        //get不存在的值
        //int a3 = map.get(3);//java.lang.NullPointerException
        int a3 = map.getOrDefault(3, 0);
        //get不存在的值
        int a4 = Optional.ofNullable(map.get(4)).orElse(0);

        System.out.println("a1 = " + a1);
        System.out.println("a2 = " + a2);
        System.out.println("a3 = " + a3);
        System.out.println("a4 = " + a4);
    }

    @Test
    public void testSet001() {
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(4);
        set1.add(5);

        Set<Integer> set2 = new HashSet<>();
        set2.add(1);
        set2.add(2);
        set2.add(9);
        System.out.println(set1.containsAll(set2));
    }

    @Test
    public void testBigDecimal0001() {
        BigDecimal a = new BigDecimal("120.456");
        BigDecimal b = new BigDecimal("100");
        System.out.println(a.divide(b, 2, RoundingMode.HALF_UP).toPlainString());
        System.out.println(a.divide(b, 2, RoundingMode.HALF_UP).stripTrailingZeros());
    }

    @Test
    public void testBigDecimal0002() {
        BigDecimal a = new BigDecimal(0.3333333333333);
        BigDecimal b = new BigDecimal(100);
        System.out.println(a.divide(b));
//        System.out.println(a.divide(b, 2, RoundingMode.HALF_UP).toPlainString());
//        System.out.println(a.divide(b, 2, RoundingMode.HALF_UP).stripTrailingZeros());
    }

    @Test
    public void testLocal0001() {
        System.out.println(NumberUtils.isCreatable("123"));
        System.out.println(NumberUtils.isCreatable("123.123"));
        System.out.println(NumberUtils.isCreatable("123L"));
        System.out.println(NumberUtils.isCreatable("123f"));
        System.out.println(NumberUtils.isCreatable("a"));
        System.out.println(NumberUtils.isCreatable("123a"));

        System.out.println("-------------");
        System.out.println(NumberUtils.isDigits("123"));
        System.out.println(NumberUtils.isDigits("123.123"));
        System.out.println(NumberUtils.isDigits("123L"));
        System.out.println(NumberUtils.isDigits("123f"));
        System.out.println(NumberUtils.isDigits("a"));
        System.out.println(NumberUtils.isDigits("123a"));

    }


    public static Long localDateTimeToLong(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return 0L;
        }

        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @Test
    public void testSub011() {
        String billCode = "SHD20220616000165443";
        int dateInt = Integer.parseInt(billCode.substring(3, 11));
        System.out.println(dateInt);

    }

    @Test
    public void testListEmpty() {
        List<Integer> intList = new ArrayList<>();
//        intList.add(1);
//        intList.add(2);
//        intList.add(3);
        intList.forEach(a -> System.out.println(a));
    }

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Test
    public void testa12() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        remove(list, 2);
    }

    public static void remove(List<Integer> list, Integer elem) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(elem)) {
                list.remove(list.get(i));
            }
        }
        System.out.println(list);

    }


    /**
     * 双冷
     */
    @Test
    public void testsql2() {
        String s = "INSERT INTO `return_process_rule_date` SET  `return_bill_no` = '%s', `compute_start` = '2024-01-12 13:04:10', `free_storage_day` = '3', `punish_day` = '0', `free_storage_start` = '2024-01-12 13:04:10', `free_storage_end` = '2024-01-14 13:04:10', `punish_start` = '1970-01-01 00:00:01', `punish_end` = '1970-01-01 00:00:01', `destroy` = '2024-01-15 13:04:10', `valid` = '1', `operator` = 'liyapu', `create_time` = '2024-01-12 13:04:09', `update_time` = '2024-01-12 13:04:09';";
        String str = "TGD2024010816358,TGD2024011141992,TGD2024011141994,TGD2024011143883,TGD2024011144613,TGD2024011144614,TGD2024011144630,TGD2024011145586,TGD2024011145623,TGD2024011145624,TGD2024011148156,TGD2024011150018,TGD2024011243963,TGD2024011243965,TGD2024011243966,TGD2024011244716,TGD2024011244766,TGD2024011244774,TGD2024011244817,TGD2024011244841,TGD2024011244844,TGD2024011244845,TGD2024011244854,TGD2024011244880,TGD2024011244896,TGD2024011244944,TGD2024011244946,TGD2024011244977,TGD2024011244978,TGD2024011244985,TGD2024011244987,TGD2024011245700,TGD2024011245721,TGD2024011245742,TGD2024011245763,TGD2024011245809,TGD2024011245832,TGD2024011245864,TGD2024011245883,TGD2024011245884,TGD2024011245918,TGD2024011245941,TGD2024011245944,TGD2024011245954,TGD2024011245993,TGD2024011246190,TGD2024011246223,TGD2024011246236,TGD2024011246238,TGD2024011246260,TGD2024011246271,TGD2024011246285,TGD2024011246296,TGD2024011246423,TGD2024011246432,TGD2024011246442,TGD2024011246460,TGD2024011246481,TGD2024011246502,TGD2024011246575,TGD2024011246594,TGD2024011246601,TGD2024011246649,TGD2024011246650,TGD2024011247201,TGD2024011247318,TGD2024011247337,TGD2024011247339,TGD2024011247342,TGD2024011247344,TGD2024011247434,TGD2024011247473,TGD2024011247482,TGD2024011247490,TGD2024011247491,TGD2024011247493,TGD2024011247496,TGD2024011247552,TGD2024011247558,TGD2024011247572,TGD2024011247582,TGD2024011248226,TGD2024011248227,TGD2024011248244,TGD2024011248251,TGD2024011248290,TGD2024011248297,TGD2024011248305,TGD2024011248322,TGD2024011248323,TGD2024011248354,TGD2024011248359,TGD2024011248360,TGD2024011248420,TGD2024011248423,TGD2024011248437,TGD2024011248486,TGD2024011248492,TGD2024011248506,TGD2024011248513,TGD2024011248515,TGD2024011248521,TGD2024011248549,TGD2024011248575,TGD2024011249065,TGD2024011249068,TGD2024011249092,TGD2024011249112,TGD2024011249158,TGD2024011249160,TGD2024011249161,TGD2024011249162,TGD2024011249195,TGD2024011249201,TGD2024011249213,TGD2024011249233,TGD2024011249247,TGD2024011249248,TGD2024011249298,TGD2024011249304,TGD2024011249351,TGD2024011249365,TGD2024011249398,TGD2024011249415,TGD2024011249441,TGD2024011250103,TGD2024011250152,TGD2024011250211,TGD2024011250214,TGD2024011250247,TGD2024011250268,TGD2024011250279,TGD2024011250315,TGD2024011250330,TGD2024011250395,TGD2024011250405,TGD2024011250423,TGD2024011250425,TGD2024011250449,TGD2024011250459,TGD2024011250491,TGD2024011251081,TGD2024011251099,TGD2024011251100,TGD2024011251145,TGD2024011251173,TGD2024011251214,TGD2024011251253,TGD2024011251287,TGD2024011251303,TGD2024011251312,TGD2024011252003,TGD2024011252005,TGD2024011252010,TGD2024011252015,TGD2024011252017,TGD2024011252024,TGD2024011253004,TGD2024011253026,TGD2024011253027,TGD2024011253030,TGD2024011253031,TGD2024011253032,TGD2024011253045,TGD2024011253047,TGD2024011253051,TGD2024011253052,TGD2024011253060,TGD2024011253092";
        final String[] strArr = str.split(",");
        for (int i = 0; i < strArr.length; i++) {
            System.out.println(String.format(s, strArr[i]));
        }
    }

    /**
     * 常温 452
     */
    @Test
    public void testSql1() {
        String s = "INSERT INTO `return_process_rule_date` SET  `return_bill_no` = '%s', `compute_start` = '2024-01-12 12:40:01', `free_storage_day` = '1', `punish_day` = '6', `free_storage_start` = '2024-01-12 12:40:01', `free_storage_end` = '2024-01-12 12:40:01', `punish_start` = '2024-01-13 12:40:01', `punish_end` = '2024-01-18 12:40:01', `destroy` = '2024-01-19 12:40:01', `valid` = '1', `operator` = 'liyapu', `create_time` = '2024-01-12 12:40:01', `update_time` = '2024-01-12 12:40:01';";

        String str = "TGD2024010892967,TGD2024010896733,TGD2024011143889,TGD2024011144617,TGD2024011147135,TGD2024011148151,TGD2024011149008,TGD2024011150042,TGD2024011243903,TGD2024011243936,TGD2024011243972,TGD2024011243973,TGD2024011243975,TGD2024011243979,TGD2024011243980,TGD2024011243986,TGD2024011243996,TGD2024011243997,TGD2024011244660,TGD2024011244677,TGD2024011244680,TGD2024011244683,TGD2024011244691,TGD2024011244698,TGD2024011244732,TGD2024011244750,TGD2024011244751,TGD2024011244758,TGD2024011244765,TGD2024011244775,TGD2024011244780,TGD2024011244782,TGD2024011244783,TGD2024011244784,TGD2024011244785,TGD2024011244786,TGD2024011244787,TGD2024011244789,TGD2024011244790,TGD2024011244798,TGD2024011244804,TGD2024011244808,TGD2024011244810,TGD2024011244814,TGD2024011244815,TGD2024011244821,TGD2024011244840,TGD2024011244864,TGD2024011244866,TGD2024011244877,TGD2024011244881,TGD2024011244908,TGD2024011244909,TGD2024011244911,TGD2024011244917,TGD2024011244920,TGD2024011244936,TGD2024011244937,TGD2024011244964,TGD2024011244973,TGD2024011244983,TGD2024011244989,TGD2024011245629,TGD2024011245645,TGD2024011245658,TGD2024011245659,TGD2024011245664,TGD2024011245670,TGD2024011245683,TGD2024011245685,TGD2024011245694,TGD2024011245695,TGD2024011245698,TGD2024011245699,TGD2024011245711,TGD2024011245713,TGD2024011245715,TGD2024011245716,TGD2024011245732,TGD2024011245738,TGD2024011245746,TGD2024011245773,TGD2024011245781,TGD2024011245806,TGD2024011245808,TGD2024011245811,TGD2024011245815,TGD2024011245825,TGD2024011245842,TGD2024011245846,TGD2024011245849,TGD2024011245855,TGD2024011245865,TGD2024011245875,TGD2024011245907,TGD2024011245909,TGD2024011245917,TGD2024011245929,TGD2024011245940,TGD2024011245945,TGD2024011245963,TGD2024011245971,TGD2024011245973,TGD2024011245974,TGD2024011245981,TGD2024011245982,TGD2024011245986,TGD2024011245989,TGD2024011246203,TGD2024011246212,TGD2024011246222,TGD2024011246225,TGD2024011246234,TGD2024011246237,TGD2024011246239,TGD2024011246243,TGD2024011246244,TGD2024011246245,TGD2024011246247,TGD2024011246249,TGD2024011246259,TGD2024011246276,TGD2024011246294,TGD2024011246295,TGD2024011246312,TGD2024011246314,TGD2024011246320,TGD2024011246349,TGD2024011246358,TGD2024011246372,TGD2024011246381,TGD2024011246387,TGD2024011246390,TGD2024011246396,TGD2024011246405,TGD2024011246412,TGD2024011246413,TGD2024011246480,TGD2024011246489,TGD2024011246493,TGD2024011246509,TGD2024011246513,TGD2024011246520,TGD2024011246522,TGD2024011246526,TGD2024011246528,TGD2024011246534,TGD2024011246535,TGD2024011246537,TGD2024011246548,TGD2024011246557,TGD2024011246580,TGD2024011246581,TGD2024011246610,TGD2024011246616,TGD2024011246624,TGD2024011246628,TGD2024011246635,TGD2024011246637,TGD2024011246642,TGD2024011247198,TGD2024011247205,TGD2024011247206,TGD2024011247215,TGD2024011247218,TGD2024011247221,TGD2024011247232,TGD2024011247234,TGD2024011247242,TGD2024011247246,TGD2024011247253,TGD2024011247254,TGD2024011247264,TGD2024011247265,TGD2024011247273,TGD2024011247274,TGD2024011247275,TGD2024011247298,TGD2024011247320,TGD2024011247340,TGD2024011247343,TGD2024011247355,TGD2024011247363,TGD2024011247364,TGD2024011247368,TGD2024011247377,TGD2024011247379,TGD2024011247381,TGD2024011247385,TGD2024011247386,TGD2024011247415,TGD2024011247417,TGD2024011247418,TGD2024011247427,TGD2024011247431,TGD2024011247432,TGD2024011247442,TGD2024011247443,TGD2024011247451,TGD2024011247453,TGD2024011247456,TGD2024011247457,TGD2024011247471,TGD2024011247472,TGD2024011247474,TGD2024011247475,TGD2024011247480,TGD2024011247481,TGD2024011247483,TGD2024011247487,TGD2024011247494,TGD2024011247499,TGD2024011247500,TGD2024011247523,TGD2024011247527,TGD2024011247535,TGD2024011247540,TGD2024011247543,TGD2024011247553,TGD2024011247554,TGD2024011247574,TGD2024011247577,TGD2024011247583,TGD2024011247597,TGD2024011247609,TGD2024011247614,TGD2024011247624,TGD2024011247625,TGD2024011248187,TGD2024011248193,TGD2024011248195,TGD2024011248196,TGD2024011248197,TGD2024011248198,TGD2024011248203,TGD2024011248225,TGD2024011248240,TGD2024011248241,TGD2024011248252,TGD2024011248280,TGD2024011248283,TGD2024011248286,TGD2024011248295,TGD2024011248348,TGD2024011248349,TGD2024011248367,TGD2024011248375,TGD2024011248376,TGD2024011248387,TGD2024011248401,TGD2024011248412,TGD2024011248444,TGD2024011248459,TGD2024011248473,TGD2024011248479,TGD2024011248489,TGD2024011248493,TGD2024011248495,TGD2024011248523,TGD2024011248524,TGD2024011248528,TGD2024011248536,TGD2024011248542,TGD2024011248553,TGD2024011248557,TGD2024011248559,TGD2024011249031,TGD2024011249032,TGD2024011249035,TGD2024011249037,TGD2024011249044,TGD2024011249046,TGD2024011249058,TGD2024011249067,TGD2024011249070,TGD2024011249072,TGD2024011249073,TGD2024011249078,TGD2024011249079,TGD2024011249082,TGD2024011249093,TGD2024011249179,TGD2024011249180,TGD2024011249192,TGD2024011249200,TGD2024011249202,TGD2024011249207,TGD2024011249210,TGD2024011249223,TGD2024011249224,TGD2024011249225,TGD2024011249242,TGD2024011249270,TGD2024011249276,TGD2024011249284,TGD2024011249289,TGD2024011249297,TGD2024011249301,TGD2024011249302,TGD2024011249312,TGD2024011249313,TGD2024011249318,TGD2024011249319,TGD2024011249323,TGD2024011249332,TGD2024011249338,TGD2024011249340,TGD2024011249341,TGD2024011249347,TGD2024011249355,TGD2024011249367,TGD2024011249388,TGD2024011249395,TGD2024011249406,TGD2024011249408,TGD2024011249414,TGD2024011249417,TGD2024011249425,TGD2024011249428,TGD2024011249429,TGD2024011249431,TGD2024011249447,TGD2024011249449,TGD2024011249455,TGD2024011249456,TGD2024011250058,TGD2024011250060,TGD2024011250066,TGD2024011250071,TGD2024011250082,TGD2024011250094,TGD2024011250098,TGD2024011250100,TGD2024011250111,TGD2024011250113,TGD2024011250115,TGD2024011250119,TGD2024011250128,TGD2024011250129,TGD2024011250134,TGD2024011250175,TGD2024011250193,TGD2024011250195,TGD2024011250196,TGD2024011250199,TGD2024011250200,TGD2024011250207,TGD2024011250232,TGD2024011250233,TGD2024011250234,TGD2024011250235,TGD2024011250238,TGD2024011250239,TGD2024011250250,TGD2024011250252,TGD2024011250253,TGD2024011250258,TGD2024011250262,TGD2024011250281,TGD2024011250282,TGD2024011250285,TGD2024011250288,TGD2024011250292,TGD2024011250302,TGD2024011250305,TGD2024011250306,TGD2024011250309,TGD2024011250319,TGD2024011250321,TGD2024011250329,TGD2024011250331,TGD2024011250333,TGD2024011250338,TGD2024011250344,TGD2024011250353,TGD2024011250355,TGD2024011250360,TGD2024011250361,TGD2024011250363,TGD2024011250372,TGD2024011250375,TGD2024011250417,TGD2024011250422,TGD2024011250424,TGD2024011250447,TGD2024011250448,TGD2024011250453,TGD2024011250458,TGD2024011250460,TGD2024011250464,TGD2024011250476,TGD2024011250482,TGD2024011250483,TGD2024011250484,TGD2024011250485,TGD2024011251015,TGD2024011251029,TGD2024011251046,TGD2024011251048,TGD2024011251064,TGD2024011251065,TGD2024011251077,TGD2024011251085,TGD2024011251096,TGD2024011251097,TGD2024011251111,TGD2024011251115,TGD2024011251117,TGD2024011251120,TGD2024011251121,TGD2024011251144,TGD2024011251152,TGD2024011251155,TGD2024011251169,TGD2024011251177,TGD2024011251193,TGD2024011251196,TGD2024011251197,TGD2024011251205,TGD2024011251223,TGD2024011251224,TGD2024011251231,TGD2024011251239,TGD2024011251241,TGD2024011251270,TGD2024011251272,TGD2024011251278,TGD2024011251280,TGD2024011251305,TGD2024011251313,TGD2024011251337,TGD2024011251338,TGD2024011251339,TGD2024011252004,TGD2024011252006,TGD2024011252028,TGD2024011252031,TGD2024011252033,TGD2024011252042,TGD2024011253013,TGD2024011253014,TGD2024011253020,TGD2024011253039,TGD2024011253050,TGD2024011253066,TGD2024011253074,TGD2024011253075,TGD2024011253076,TGD2024011253084,TGD2024011253085,TGD2024011253087,TGD2024011253094";
        final String[] strArr = str.split(",");
        for (int i = 0; i < strArr.length; i++) {
            System.out.println(String.format(s, strArr[i]));
        }
    }

    @Test
    public void testStrT() {
        String str = "real compute process rule date detail is null returnBillNo:TGD2024011253094\n" +
                "adjust\t\n" +
                " real compute process rule date detail is null returnBillNo:TGD2024011253092\n" +
                "adjust\t\n" +
                " real compute process rule date detail is null returnBillNo:TGD2024011246650\n" +
                "adjust\t\n" +
                " real compute process rule date detail is null returnBillNo:TGD2024010816358"
                + ""
                + "";
        String[] strArr = str.split(":");
        for (int i = 0; i < strArr.length; i++) {
            String s = strArr[i];
            if (s.startsWith("TGD")) {
                System.out.println(s.split("\\n")[0].trim().substring(0, 16));
            }
        }
    }

    @Test
    public void testLongInteger() {
        Long a = 300L;
        Long b = 300L;
        System.out.println(a == b);
    }

    @Test
    public void testFormatStr() {
//        System.out.println(String.format("aa%sbb%scc", 'A', 7));
        List<Integer> intNums = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
        intNums.forEach(i -> {
            if (i == 4) {
                return; // forEach 中 return 类似于continue;
//                continue; //使用continue报错
            }
            System.out.println(i);
        });
    }


    @Test
    public void testFormat() {
        int maxLength = 8;
        int id = 5113;
        String result = String.format("%0" + maxLength + "d", id);
        System.out.println(result);
    }

    @Test
    public void testUUid22() {
        String u = "my_" + UUID.randomUUID();
        System.out.println(u);
    }

    @Test
    public void testUUid() {
        String u = UUID.randomUUID().toString();
        System.out.println(u);
    }

    @Test
    public void testMap1() {
        Map<String, String> map = new HashMap<>();
        Map<String, String> temp1 = null;

//        map.put(temp1);

    }

    @Test
    public void testInteger() {
        Integer a = new Integer(2);
        Integer b = new Integer(2);
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }

    @Test
    public void testLong() {
        Long a = new Long(2);
        Long b = new Long(2);
        System.out.println(a.equals(b));


        Long aa = new Long(1234567890123567L);
        Long bb = new Long(1234567890123567L);
        System.out.println(aa.equals(bb));
    }

    @Test
    public void testStreamOf() {
        List<Integer> compSkuList = Lists.newArrayList(1, 2, 3);
        //List<Integer> compSkuList = new ArrayList<>();
        //List<Integer> adjustSkuList = Lists.newArrayList(10, 20, 30, 1, 2);
        List<Integer> adjustSkuList = new ArrayList<>();
        List<Integer> needPriceSkuList = Stream.of(compSkuList, adjustSkuList)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());

        for (Integer num : needPriceSkuList) {
            System.out.println("num = " + num);
        }

    }

    @Test
    public void testBillNo() {
        //单号生成规则：PF+yyyyMMdd+自增id
        String date = "20230523";
        long id = 111;
        String idStr = getResultIdStr(id, 6);

        final String result = String.format("%s%s%s", "PF", date, idStr);
        System.out.println("result = " + result);
    }

    private String getResultIdStr(long id, int maxLength) {
        String aa = String.format("%0" + maxLength + "d", id);
        return aa;
    }

    @Test
    public void testFormatLength() {
        System.out.println(String.format("%06d", 123));
        System.out.println(String.format("%06d", 1234));
        System.out.println(String.format("%06d", 12345));
        System.out.println(String.format("%06d", 123456));
        System.out.println(String.format("%06d", 1234567));
        System.out.println(String.format("%06d", 12345678));
        System.out.println();
        System.out.println(String.format("%01d", 123));
        System.out.println(String.format("%02d", 123));
        System.out.println(String.format("%03d", 123));
        System.out.println(String.format("%04d", 123));
        System.out.println(String.format("%05d", 123));
        System.out.println(String.format("%06d", 123));
    }

    @Test
    public void testCalendar3() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar默认时间 " + dateFormat.format(calendar.getTime()));

        calendar.setTime(new Date());

        //当周开始时间：2023-05-14 00:00:00
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println("当周开始时间 " + dateFormat.format(calendar.getTime()));
        //当周结束时间：2023-05-20 23:59:59
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        System.out.println("当周结束时间 " + dateFormat.format(calendar.getTime()));
    }

    @Test
    public void testCalendar2() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        //当月开始时间：2022-03-01 00:00:00
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println("当月开始时间 " + dateFormat.format(calendar.getTime()));
        //当月结束时间：2022-03-31 23:59:59
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        System.out.println("当月结束时间 " + dateFormat.format(calendar.getTime()));
    }

    @Test
    public void LocalDateMin() {
        LocalDateTime today = LocalDateTime.of(2023, 4, 24, 10, 10, 10);
        Long createFinanceIntegrateBillWaitDay = 23L;
        LocalDateTime startLocalDateTime = today.minusDays(createFinanceIntegrateBillWaitDay);
        System.out.println("startLocalDateTime = " + startLocalDateTime);
    }

    @Test
    public void testCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MILLISECOND, -1);
        //当月需要自动商家确认的买赔单，对账周期的结束时间，一定是上个月月底
        Date checkEndDate = calendar.getTime();

        System.out.println("checkEndDate = " + checkEndDate);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateStr = format.format(checkEndDate);
        System.out.println("dateStr = " + dateStr);
    }

    @Test
    public void testListSet() {
        Map<Long, Object> skuIdToSkuBoMap = new HashMap<>();
        skuIdToSkuBoMap.put(111L, new Object());
        skuIdToSkuBoMap.put(222L, new Object());
        skuIdToSkuBoMap.put(333L, new Object());
        List<Long> skuIdList = Lists.newArrayList(skuIdToSkuBoMap.keySet());
        System.out.println(skuIdList);
    }

    @Test
    public void testSupport() {
        List<Integer> supportOld = Arrays
                .asList(230017, 230018, 230019, 230020, 230021, 230022, 230023, 230024, 230011, 230012,
                        230013, 230014, 230015, 230016, 230001, 230002, 230025, 230026, 230027, 230028, 230029, 230030, 230031,
                        230032, 230033, 230034,
                        230035, 230036, 230007, 230008, 230009, 230079, 230080, 230081, 230092, 230082, 230083, 230084, 230085,
                        230086, 230087, 230088,
                        230089, 230090, 230091, 230077, 230078, 230061, 230062, 230063, 230064, 230065, 230066, 230067, 230068,
                        230069, 230070, 230071,
                        230072, 230073, 230074, 230075, 230076, 230037, 230038, 230039, 230040, 230041, 230042, 230043, 230050,
                        230051, 230052, 230053,
                        230054, 230055, 230056, 230057, 230058, 230059, 230060, 230044, 230045, 230046, 230047, 230048, 230049,
                        230094);

        List<Integer> listNew = Arrays
                .asList(230017, 230018, 230019, 230020, 230021, 230022, 230023, 230024, 230011, 230012,
                        230013, 230014, 230015, 230016, 230001, 230002, 230025, 230026, 230027, 230028, 230029, 230010, 230003,
                        230004, 230005,
                        230006, 230030, 230031, 230032, 230033, 230034, 230035, 230036, 230007, 230008, 230009, 230079, 230080,
                        230081, 230082, 230083, 230084, 230085, 230086, 230087, 230088, 230089, 230090, 230077, 230078, 230061,
                        230062, 230063, 230064, 230065, 230066, 230067, 230068, 230069, 230070, 230071, 230072, 230073, 230074,
                        230075, 230076, 230037, 230038, 230039, 230040, 230041, 230042, 230043, 230050, 230051, 230052, 230053,
                        230054, 230055, 230056, 230057, 230058, 230059, 230060, 230044, 230045, 230046, 230047, 230048, 230049,
                        230091);

        for (Integer old : supportOld) {
            if (!listNew.contains(old)) {
                System.out.println(old);
            }
        }

        System.out.println("-----------------");
        for (Integer xin : listNew) {
            if (!supportOld.contains(xin)) {
                System.out.println(xin);
            }
        }

    }

    @Test
    public void testLocalDateTime() {
        long lo = LocalDateTime.now().minusDays(366).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println("lo = " + lo);
    }

    @Test
    public void testJoinIn() {
        List<Long> poiIdList = new ArrayList<>();
        poiIdList.add(1L);
        poiIdList.add(5L);
        poiIdList.add(8L);

        String result1 = String.join(",", poiIdList.stream().map(k -> k.toString()).collect(
                Collectors.toList()));
        String result2 = poiIdList.stream().map(Object::toString).collect(Collectors.joining(","));
        System.out.println(result1);
        System.out.println(result2);
    }

    @Test
    public void testJoinIn2() {
        List<Long> poiIdList = new ArrayList<>();
        poiIdList.add(1L);
        poiIdList.add(5L);
        poiIdList.add(8L);

        String result = poiIdList.stream().map(k -> k.toString()).collect(Collectors.joining(","));
        System.out.println(result);
    }

    @Test
    public void testObjectIsNull() {
        Long id = null;
        long logisticsDeviationQty = Objects.isNull(id) ? 0 : id;
        System.out.println("------");
    }

    @Test
    public void testTreeMap() {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("H", "HHH");
        treeMap.put("c", "cccc");
        treeMap.put("C", "CCC");
        treeMap.put("A", "AAA");
        treeMap.put("b", "bbb");
        treeMap.put("a", "aaa");
        treeMap.put("B", "BBB");

        System.out.println(treeMap);
    }

    /**
     * 空列表  --> stream 流， 不会执行了后续逻辑
     */

    @Test
    public void testListNullEmpty() {
        //List<Integer> intLists = Arrays.asList(1, 2, 3, 4, 5, 6);
        //List<Integer> intLists = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> intLists = new ArrayList<>();
        final List<Integer> listResult = intLists.stream()
                .map(i -> i * i)
                .peek(System.out::println)
                .collect(Collectors.toList());
        System.out.println("listResult = " + listResult);
    }

    @Test
    public void testStrNull() {
        String str1 = null;
        System.out.println(str1 + "");
        System.out.println(String.valueOf(str1));
        System.out.println();
        String str2 = "aa";
        System.out.println(str2 + "");
        System.out.println(String.valueOf(str2));

        System.out.println("-----------");
    }

    @Test
    public void testListNull() {
        List<Integer> intList = null;
        //List<Integer> intList = Arrays.asList(1, 2, 3);
        final List<Integer> newIntList = ListUtils.emptyIfNull(intList).stream()
                .map(i -> i + 10)
                .peek(i -> System.out.println("i peek " + i))
                .collect(Collectors.toList());
        System.out.println(newIntList);
    }

    @Test
    public void testSanMu() {
        Object obj = null;
        int a = obj == null ? 1 : null;
        System.out.println(a);
    }

    @Test
    public void testForEach() {
        List<String> listNull = null;
        List<String> collect = CollectionUtils.emptyIfNull(listNull).stream()
                .map(s -> "---->" + s)
                .collect(Collectors.toList());
    }

    /**
     * 字符统计长度
     * 字数统计网站 https://zishu.xpcha.com/
     */
    @Test
    public void testCharCount() {
        String str = "重要提醒，我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789我是来凑字符的0123456789等2个仓库，共计处理！";
        System.out.println(str.length());
    }

    /**
     * 保留三位小数
     */
    @Test
    public void testThree() {
        long quantity = 500000L;
        int mg_to_kg = 1000_000;

        calPrint(quantity, mg_to_kg);
        calPrint(1343483434334L, mg_to_kg);
        calPrint(1300000000023232L, mg_to_kg);
        calPrint(500L, mg_to_kg);
        calPrint(300L, mg_to_kg);
    }

    private void calPrint(long quantity, int mg_to_kg) {
        String str = new BigDecimal(quantity).divide(new BigDecimal(mg_to_kg))
                .setScale(3, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
        System.out.println(str);
    }

    @Test
    public void testSubString() {
        String str = "中国很伟大，国人奋进。";
        System.out.println(str.length());
        System.out.println(str.substring(2));
        System.out.println(str.substring(0, 5));
    }

    @Test
    public void testGetOutNo() {
        String str = "msg";
        String[] splitList = str.split("\\n");
        List<String> result = Lists.newArrayList();
        for (String line : splitList) {
            int startIndex = line.indexOf("SJCK");
            if (startIndex <= 0) {
                continue;
            }
            String outNo = line.substring(startIndex, startIndex + "SJCK2022052301769085".length());
            if (result.contains(outNo)) {
                continue;
            }
            result.add(outNo);
            System.out.println(outNo);
        }

    }

    @Test
    public void testBigDecimal2222() {
        String str = "2.99aa";
        BigDecimal salePriceDecimal = new BigDecimal(str);
        BigDecimal hundred = new BigDecimal("100");
        BigDecimal multiply = salePriceDecimal.multiply(hundred);
        System.out.println(multiply);
    }

    /**
     * (五) 日期时间
     * 1. 【强制】日期格式化时，传入 pattern 中表示年份统一使用小写的 y。
     * 说明：日期格式化时，yyyy 表示当天所在的年，而大写的 YYYY 代表是 week in which year（JDK7 之后
     * 引入的概念），意思是当天所在的周属于的年份，一周从周日开始，周六结束，只要本周跨年，返回的 YYYY
     * 就是下一年。
     * 正例：表示日期和时间的格式如下所示：
     * new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
     *
     * 2. 【强制】在日期格式中分清楚大写的 M 和小写的 m，大写的 H 和小写的 h 分别指代的意义。
     * 说明：日期格式中的这两对字母表意如下：
     * 1） 表示月份是大写的 M； 2） 表示分钟则是小写的 m； 3） 24 小时制的是大写的 H； 4） 12 小时制的则是小写的 h。
     *
     * 3. 【强制】获取当前毫秒数：System.currentTimeMillis(); 而不是 new Date().getTime()。
     * 说明：如果想获取更加精确的纳秒级时间值，使用 System.nanoTime 的方式。在 JDK8 中，针对统计时间
     * 等场景，推荐使用 Instant 类。
     *
     * 4. 【强制】不允许在程序任何地方中使用：1）java.sql.Date。 2）java.sql.Time。 3）java.sql.Timestamp。
     * 说明：第 1 个不记录时间，getHours()抛出异常；第 2 个不记录日期，getYear()抛出异常；第 3 个在构造
     * 方法 super((time/1000)*1000)，在 Timestamp 属性 fastTime 和 nanos 分别存储秒和纳秒信息。
     * 反例： java.util.Date.after(Date)进行时间比较时，当入参是 java.sql.Timestamp 时，会触发 JDK
     * BUG(JDK9 已修复)，可能导致比较时的意外结果。
     */

    /**
     * 【强制】任何货币金额，均以最小货币单位且整型类型来进行存储。
     */
    /**
     * 【强制】浮点数之间的等值判断，基本数据类型不能用==来比较，包装数据类型不能用 equals
     * 来判断。
     * 说明：浮点数采用“尾数+阶码”的编码方式，类似于科学计数法的“有效数字+指数”的表示方式。二进
     * 制无法精确表示大部分的十进制小数，具体原理参考《码出高效》。
     */
    @Test
    public void testFloat1() {
        //反例：
        float a = 1.0F - 0.9F;
        float b = 0.9F - 0.8F;
        if (a == b) {
            // 预期进入此代码块，执行其它业务逻辑
            // 但事实上 a==b 的结果为 false
        }
        Float x = Float.valueOf(a);
        Float y = Float.valueOf(b);
        if (x.equals(y)) {
            // 预期进入此代码块，执行其它业务逻辑
            // 但事实上 equals 的结果为 false
        }
    }

    @Test
    public void testFloat2() {
        //正例：
        //(1) 指定一个误差范围，两个浮点数的差值在此范围之内，则认为是相等的。
        float a = 1.0F - 0.9F;
        float b = 0.9F - 0.8F;
        float diff = 1e-6F;
        if (Math.abs(a - b) < diff) {
            System.out.println("true");
        }

        //(2) 使用 BigDecimal 来定义值，再进行浮点数的运算操作。
        BigDecimal aa = new BigDecimal("1.0");
        BigDecimal bb = new BigDecimal("0.9");
        BigDecimal cc = new BigDecimal("0.8");
        BigDecimal x = aa.subtract(bb);
        BigDecimal y = bb.subtract(cc);
        if (x.compareTo(y) == 0) {
            System.out.println("true");
        }
    }

    /**
     * 【强制】如上所示 BigDecimal 的等值比较应使用 compareTo()方法，而不是 equals()方法。
     *  说明：equals()方法会比较值和精度（1.0 与 1.00 返回结果为 false），而 compareTo()则会忽略精度。
     */

    /**
     * 【强制】禁止使用构造方法 BigDecimal(double)的方式把 double 值转化为 BigDecimal 对象。
     * 说明：BigDecimal(double)存在精度损失风险，在精确计算或值比较的场景中可能会导致业务逻辑异常。
     * 如：BigDecimal g = new BigDecimal(0.1F);
     * 实际的存储值为：0.10000000149
     */
    @Test
    public void testBigDecimalDemo1() {
        //正例：优先推荐入参为 String 的构造方法，或使用 BigDecimal 的 valueOf 方法，此方法内部其实执行了
        //Double 的 toString，而 Double 的 toString 按 double 的实际能表达的精度对尾数进行了截断。
        BigDecimal recommend1 = new BigDecimal("0.1");
        BigDecimal recommend2 = BigDecimal.valueOf(0.1);
    }

    @Test
    public void testStr() {
        String str = "10309\n" + "10309\n" + "9453\n" + "97119\n" + "97119";
        String[] split = str.split("\n");
        String collect = Arrays.stream(str.split("\n"))
                .collect(Collectors.toSet()).stream()
                .collect(Collectors.joining(","));
        System.out.println(collect);
    }

    /**
     * 强制】不要在程序中写死一年为365天，避免在公历闰年时出现日期转换错误或程序逻辑错误
     */
    @Test
    public void testYearNums() {
        //获取今年的天数
        int intdaysOfThisYear = LocalDate.now().lengthOfYear();
        //获取指定某年的天数
        int intdaysOfThisYear2 = LocalDate.of(2011, 1, 1).lengthOfYear();

    }

    /**
     * 【强制】日期格式化时，传入pattern中表示年份统一使用小写的y（说明：日期格式化时，yyyy表示当天所在的年，
     * 而大写的YYYY代表是week in which year（JDK7之后引入的概念），意思是当天所在的周属于的年份，一周从周日开始，周六结束，只要本周跨年，返回的YYYY就是下一年）
     */
    @Test
    public void testYear() {
        //正例：表示日期和时间的格式如下所示
        String strTime = "2021-12-02";
        LocalDate parse = LocalDate.parse(strTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("parse : " + parse);

        LocalDateTime localDateTimeNow1 = LocalDateTime.parse("2018-12-18 15:41:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("localDateTimeNow1 : " + localDateTimeNow1);
    }

    /**
     * 【强制】在日期格式中分清楚大写的M和小写的m，大写的H和小写的h分别指代的意义
     *
     * 说明：日期格式中的这两对字母表意如下：
     * 1）表示月份是大写的M
     * 2）表示分钟则是小写的m
     *
     * 3）24小时制的是大写的H
     * 4）12小时制的则是小写的h
     */

    /**
     * 【强制】BigDecimal的等值比较应使用compareTo()方法，而不是equals()方法
     * （说明：说明：equals()方法会比较值和精度（1.0与1.00返回结果为false），而compareTo()则会忽略精度）
     */
    @Test
    public void testBigDecimalCompareTo() {
        BigDecimal num1 = new BigDecimal("100.000");
        BigDecimal num2 = new BigDecimal("100");

        System.out.println(Objects.equals(num1, num2));
        System.out.println(num1.compareTo(num2));

    }

    /**
     * 获取当前时间的 毫秒时间戳
     */
    @Test
    public void testEpochMilli() {
        long epochMilli = Instant.now().toEpochMilli();
        System.out.println(epochMilli);
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);
    }

    @Test
    public void testBigDecimal4() {
        //Java有自带的 stripTrailingZeros() 方法用于去除末尾多余的0

        BigDecimal num = new BigDecimal("100.000");
        BigDecimal numNoEndZero = num.stripTrailingZeros();  //numNoEndZero  ：1E+2
        System.out.println(numNoEndZero.toString());

        //按上面的方法输出结果，会显示科学计数法，所以需要处理一下，解决方法：
        String numNoEndZeroStr = new BigDecimal("100.000").stripTrailingZeros().toPlainString();   //numNoEndZeroStr  ：100
        System.out.println(numNoEndZeroStr);

    }

    @Test
    public void testBigDecimal3() {
        BigDecimal bg = new BigDecimal("100000000.0000000");
        System.out.println(bg);


        System.out.println(bg.toString());

        //stripTrailingZeros()函数就是用于去除末尾多余的0的
        String bgStr = bg.stripTrailingZeros().toPlainString();
        System.out.println(bgStr);
    }

    @Test
    public void testBigDecimal2() {
        BigDecimal bg = new BigDecimal(100.0000000);
        System.out.println(bg);

        System.out.println(bg.toString());

        String bgStr = bg.stripTrailingZeros().toPlainString();
        System.out.println(bgStr);
    }

    @Test
    public void testOptional() {
//        Optional<Integer> numOptional = Optional.ofNullable(null);
        Optional<Integer> numOptional = Optional.ofNullable(2);
        Integer count = 100;
        String confirmAuthorization = "--";

        if (numOptional.isPresent() && null != numOptional.get()) {
            confirmAuthorization = "确认";
        }
        System.out.println("confirmAuthorization = " + confirmAuthorization);

    }

    /**
     * 测试 list 方法
     */
    @Test
    public void testList02() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);

        List<Integer> others = new ArrayList<>();
        others.add(2);
        others.add(4);
        others.add(6);
        others.add(8);

        boolean b1 = ints.retainAll(others);
        System.out.println("ints = " + ints);
        System.out.println("others = " + others);
    }

    /**
     * 测试 list 方法
     */
    @Test
    public void testList01() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);

        List<Integer> others = new ArrayList<>();
        others.add(2);
        others.add(4);
        others.add(6);
        others.add(8);

        boolean b = ints.removeAll(others);
        System.out.println("ints = " + ints);
        System.out.println("others = " + others);
    }

    /**
     * nbsp NBSP 其ASCII码值为160，这才知道，原来ASCII码中除了32之外还有160这个特殊的空格
     * 但是不间断空格有个问题，就是它无法被trim()所裁剪，也无法被正则表达式的\s所匹配，也无法被StringUtils的isBlank()所识别，也就是说，无法像裁剪寻常空格那样移除这个不间断空格。
     * <p>
     * 我们可以利用不间断空格的Unicode编码来移除它，其编码为\u00A0
     */
    @Test
    public void testNbsp1() {
        final char c1 = ' '; //db里的空格
        final char c2 = ' '; //手动输入的空格
        System.out.println((int) c1); //160
        System.out.println((int) c2); //32
    }

    @Test
    public void testNbsp2() {
//        replace("\u00A0", "")
//        replaceAll("\\u00A0+", "")  //这是正则表达式的写法
        String idCard = "  22072 2198808014012    ";
        String idCardTemp = idCard.replace("\u00A0", "");
        System.out.println("idCardTemp= " + idCardTemp);

    }

    @Test
    public void testTrim() {
        String str = "a b       c   d   e   f";

        String idCard = "   22011      219880    7014218    ";
//        String idCardTemp = idCard.trim();
        String idCardTemp = replaceBlank(idCard);
        System.out.println("idCard = " + idCard);
        System.out.println("idCardTemp = " + idCardTemp);

    }

    @Test
    public void testTrim2() {
        String str = "a b       c   d   e   f";

        String idCard = "   22011      219880    7014218    ";
        //        String idCardTemp = idCard.trim();
        String idCardTemp = replaceBlank2(idCard);
        System.out.println("idCard = " + idCard);
        System.out.println("idCardTemp = " + idCardTemp);

    }

    @Test
    public void test5() {

        long numberOfOffender = 6L;
        BigDecimal selfProportion = new BigDecimal(0.9);
        long numberOfOffenderAllow = selfProportion.multiply(BigDecimal.valueOf(6)).longValue();
        if (numberOfOffender >= numberOfOffenderAllow) {
            // 大于等于设置的比例，那么就是不违规
            System.out.println("满足");
        } else {
            System.out.println("不满足");
        }
    }

    @Test
    public void test4() {
        List<String> names = Arrays.asList("赵", "钱", "孙", "李", "周", "郑", "王");
        List<String> nums = Arrays.asList("一", "二", "三", "四", "五", "六", "七", "八", "九", "十");
        for (String name : names) {
            for (String num : nums) {
                System.out.println(name + num);
            }
        }

    }

    @Test
    public void testL3() {
        LocalDate localDateTimeNow1 = LocalDate.parse("2018-12", DateTimeFormatter.ofPattern("yyyy-MM"));
        System.out.println("localDateTimeNow1 : " + localDateTimeNow1);
    }


    @Test
    public void testL2() {
        List<String> payrollMonthList = Arrays.asList("2019-01", "2019-02", "2019-03", "2019-10", "2019-11", "2019-12");
        for (String payrollMonth : payrollMonthList) {
            LocalDate parse = LocalDate.parse(payrollMonth, DateTimeFormatter.ofPattern("yyyy-MM"));
            System.out.println(parse.format(DateTimeFormatter.ofPattern("yyyy-MM")));
        }
    }


    @Test
    public void testL1() {
        List<String> payrollMonthList = Arrays.asList("2019-01", "2019-02", "2019-03", "2019-10", "2019-11", "2019-12");
        for (String payrollMonth : payrollMonthList) {
            String[] yearMonthArray = payrollMonth.split("-");
            if (yearMonthArray.length != 2) {
                log.error("FranchiseePayrollRuleServiceImpl.getLastMonthSettlementPayableAmount, settleMonth parse error,"
                        + " payrollMonth:{}", payrollMonth);
                throw new IllegalArgumentException("settleMonth parse error!");
            }
            String year = yearMonthArray[0];
            String month = yearMonthArray[1];
            if (Integer.parseInt(month) > 12) {
                log.error("FranchiseePayrollRuleServiceImpl.getPayrollCostLowerLimitCheck, settleMonth parse  error, " +
                        "payrollMonth:{}", payrollMonth);
                throw new IllegalArgumentException("settleMonth parse error!");
            }
            // 取上个月的薪资总数
            if (StringUtils.equals("01", month)) {
                month = "12";
                year = String.valueOf(Integer.parseInt(year) - 1);
            } else {
                month = Strings.padStart((Integer.parseInt(month) - 1) + "", 2, '0');
            }
            String settleMonth = year + month;
            System.out.println(settleMonth);
        }

    }

    @Test
    public void addTest() {
        String s1 = "+";
        String s2 = "+";
        String s3 = "+";
        System.out.println(s2.equals(s1));
    }

    /**
     * 1.如果字符串最后一位有值，则没有区别，
     * 2.若干最后n位都是切割符，split(",")不会继续切分，split(",", -1)会继续切分
     */
    @Test
    public void testCommaData() {
        String line = " Sachin ,, M,\"数学，,,,科学,英语\",需要改进这些主题。,,,,";
        String[] result1 = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        System.out.println("result1 len is  = " + result1.length);
        for (int i = 0; i < result1.length; i++) {
            System.out.println(i + "-" + result1[i]);
        }
        System.out.println("------------------");

        String[] result2 = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        System.out.println("result2 len is =" + result2.length);
        for (int i = 0; i < result2.length; i++) {
            System.out.println(i + "-" + result2[i]);
        }


    }

    @Test
    public void testCommaData2() {
        String line = " Sachin ,, M,\"数学，,,,科学,英语\",需要改进这些主题。,,,,";

        //双引号内的逗号不分割  双引号外的逗号进行分割
        String[] result = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
        for (String s : result) {
            System.out.println(s);
        }
    }

    @Test
    public void testCommaData3() {
        String line = " Sachin ,, M,\"数学，,,,科学,英语\",需要改进这些主题。,,,,";
        String linee = " \"boyet.com\", 48 , ,\"Saturday, April 23, 2005\", \"Mack \"\"The Knife\"\"\" ";


        //双引号内的逗号不分割  双引号外的逗号进行分割
        String[] result = linee.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
//        String[] strArr = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1); //双引号内的逗号不分割  双引号外的逗号进行分割
        for (String s : result) {
            System.out.println(s);
        }
    }

    //英文表头正则表达式
    private static final Pattern headPattern = Pattern.compile("^[A-Za-z0-9]+$");

    Pattern pt = Pattern.compile("^[　*| *| *|\\s*]*|[　*| *| *|\\s*]*$");

    /**
     * 去除子符串首尾空格方法
     * 匹配首尾空白字符的正则表达式：^\s* ¦\s*$
     * 评注：可以用来删除行首行尾的空白字符(包括空格、制表符、换页符等等)，非常有用的表达式
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        Pattern pattern = Pattern.compile("^\\s*|\\s*$");
        Matcher mt = pattern.matcher(str);
        str = mt.replaceAll("");
        return str;
    }

    public static String replaceBlank2(String str) {
        Pattern pattern = Pattern.compile("^\\s*|^\\u00A0*|\\u00A0*$|\\s*$");
        Matcher mt = pattern.matcher(str);
        str = mt.replaceAll("");
        return str;
    }

    @Test
    public void testPatternRemove() {
        //"	43688569-8","	渭南市中心医院","	0000131427","	1","	2016-12-29 17:38:38","	2017-01-01 08:35:55","	","	3.1","	史捍党","	1","	1956-10-24 16:53:20","	60","	0001","	2","	27","	1","	612101195610246439","	陕西省渭南市临渭区","	27","	陕西省渭南市临渭区故市镇楼史村二组","	714000","	故市镇楼史村二组","	13093993399","	714000","	临渭区故市镇楼史村二组","	13093993399","	714000","	史小民","	2","	临渭区故市镇楼史村2组","	13093993399","	2","	11","	07","	11","	11","	07","	3","	R04.000","	鼻出血","	R04.000","	鼻出血","	1","	R04.000","	鼻出血","	1","	J34.200","	鼻中隔偏曲","	1","	J32.900","	慢性鼻窦炎","	1","	I10.x04","	高血压Ⅱ期","	1","	I25.103","	冠状动脉粥样硬化性心脏病","	1","	Z95.501","	冠状动脉支架植入后状态","	1","	-","	","	4","	-","	","	4","	-","	","	4","	-","	","	4","	-","	","	4","	-","	","	4","	-","	","	4","	-","	","	4","	-","	","	4","	-","	","	4","	-","	","	4","	-","	","	4","	-","	","	4","	-","	","	4","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	-","	-","	-","	","	","	1","	","	郑建军","	郑建军","	田宏","	田宏","	田阳阳","	-","	-","	刘舒眉","	1","	宋建涛","	赵亚红","	2017-01-01 00:00:00","	2","	6","	4","	-","	-","	-","	-","	","	-","	-","	-","	-","	-","	-","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	","	0","	-","	","	","	","	","	-","	0","	0","	0","	0","	0","	0","	1","	-","	1","	-",2371.5,2371.5,204,44,29,0,0,1005,625,295,0,0,0,0,0,0,0,140.4,0,0,0,0,0,0,0,0,0,6,23.1,0
        String row = "\"\t43688569-8\",\"\t渭南市中心医院\",\"\t0000131427\",\"\t1\",\"\t2016-12-29 17:38:38\",\"\t2017-01-01 08:35:55\",\"\t\",\"\t3.1\",\"\t史捍党\",\"\t1\",\"\t1956-10-24 16:53:20\",\"\t60\",\"\t0001\",\"\t2\",\"\t27\",\"\t1\",\"\t612101195610246439\",\"\t陕西省渭南市临渭区\",\"\t27\",\"\t陕西省渭南市临渭区故市镇楼史村二组\",\"\t714000\",\"\t故市镇楼史村二组\",\"\t13093993399\",\"\t714000\",\"\t临渭区故市镇楼史村二组\",\"\t13093993399\",\"\t714000\",\"\t史小民\",\"\t2\",\"\t临渭区故市镇楼史村2组\",\"\t13093993399\",\"\t2\",\"\t11\",\"\t07\",\"\t11\",\"\t11\",\"\t07\",\"\t3\",\"\tR04.000\",\"\t鼻出血\",\"\tR04.000\",\"\t鼻出血\",\"\t1\",\"\tR04.000\",\"\t鼻出血\",\"\t1\",\"\tJ34.200\",\"\t鼻中隔偏曲\",\"\t1\",\"\tJ32.900\",\"\t慢性鼻窦炎\",\"\t1\",\"\tI10.x04\",\"\t高血压Ⅱ期\",\"\t1\",\"\tI25.103\",\"\t冠状动脉粥样硬化性心脏病\",\"\t1\",\"\tZ95.501\",\"\t冠状动脉支架植入后状态\",\"\t1\",\"\t-\",\"\t\",\"\t4\",\"\t-\",\"\t\",\"\t4\",\"\t-\",\"\t\",\"\t4\",\"\t-\",\"\t\",\"\t4\",\"\t-\",\"\t\",\"\t4\",\"\t-\",\"\t\",\"\t4\",\"\t-\",\"\t\",\"\t4\",\"\t-\",\"\t\",\"\t4\",\"\t-\",\"\t\",\"\t4\",\"\t-\",\"\t\",\"\t4\",\"\t-\",\"\t\",\"\t4\",\"\t-\",\"\t\",\"\t4\",\"\t-\",\"\t\",\"\t4\",\"\t-\",\"\t\",\"\t4\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t-\",\"\t-\",\"\t-\",\"\t\",\"\t\",\"\t1\",\"\t\",\"\t郑建军\",\"\t郑建军\",\"\t田宏\",\"\t田宏\",\"\t田阳阳\",\"\t-\",\"\t-\",\"\t刘舒眉\",\"\t1\",\"\t宋建涛\",\"\t赵亚红\",\"\t2017-01-01 00:00:00\",\"\t2\",\"\t6\",\"\t4\",\"\t-\",\"\t-\",\"\t-\",\"\t-\",\"\t\",\"\t-\",\"\t-\",\"\t-\",\"\t-\",\"\t-\",\"\t-\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t0\",\"\t-\",\"\t\",\"\t\",\"\t\",\"\t\",\"\t-\",\"\t0\",\"\t0\",\"\t0\",\"\t0\",\"\t0\",\"\t0\",\"\t1\",\"\t-\",\"\t1\",\"\t-\",2371.5,2371.5,204,44,29,0,0,1005,625,295,0,0,0,0,0,0,0,140.4,0,0,0,0,0,0,0,0,0,6,23.1,0";
        String str = "	\t\t43688569-8   \t ";
        String str2 = " \t  \t 	43688569-8   \t ";

//        String str = "	";
//        String str2 ="	";


        System.out.println("-" + str + "-");
//        if (null != str2 && !"".equals(str2)) {
//            str2 = str2.replaceAll("^[　*| *| *|\\s*]*", "").replaceAll("[　*| *| *|\\s*]*$", "");
//            System.out.println("-" + str2 + "-");
//
//        }
        Matcher mt = pt.matcher(str);
        String newStr = mt.replaceAll("");
        System.out.println("-" + newStr + "-");
//        System.out.println("-" + str. + "-");
//        System.out.println("-" + str + "-");
//        System.out.println("-" + str + "-");
    }

    @Test
    public void testPattern() {
        String indexValue = "A01";
        boolean isMatch = headPattern.matcher(indexValue).matches();
        System.out.println(" isMatch = " + isMatch);
    }

    @Test
    public void testSub() {
        Set<String> largePartCodeSet = new HashSet<>();
        largePartCodeSet.add("A11");
        largePartCodeSet.add("A22");
        largePartCodeSet.add("A33");

//        String str = "A49.800x001";
//        String str = "A1";
        String icd10Code = "A11.aaa";

        boolean isMatch = false;

        if (StringUtils.isNotBlank(icd10Code) && icd10Code.length() >= 3 && largePartCodeSet.contains(icd10Code.substring(0, 3))) {
            isMatch = true;
        }
        System.out.println("isMatch  = " + isMatch);
    }

    @Test
    public void testRemove() {
        String url = "https://blog.csdn.net:8080/abc/";
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        System.out.println(url);
    }


    @Test
    public void testGetHost() {
        String url = "https://blog.csdn.net:8080/jjjj";
        String[] arrs = url.split("/", 4);
        String resultUrl = "";
        if (arrs.length >= 3) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                sb.append(arrs[i].trim());
                if (i == 1) {
                    sb.append("//");
                }
            }
            sb.append("/");
            resultUrl = sb.toString();
        } else {
            if (!url.endsWith("/")) {
                resultUrl = url + "/";
            }
        }
        System.out.println(resultUrl);

    }

    @Test
    public void testNameLength() {
        String name = "病例导入模板----@#？*&^<40条数据可成功1阿里佛拉法基阿拉姜色放辣椒失蜡法讲哦我金佛我我讲哦期无日期挖掘时拉卡拉设计逻辑 阿里可交付垃圾法拉进来弗拉上级分类阿拉斯加放辣椒放辣椒水立方进阿里案例可使肌肤垃圾分类卡就是快乐的腹肌啊来介绍了按理说可交付拉设计费拉结束了可交付2.jpg";
        String other = "病例导入模板----0条数据0条数据0条数据0条数据0条数据0条数据0条数据0条数据0条数据0条数据0条数据0条数据0条数据0条数据0条数据0条数据0条数据0条数据0条数据0条数据0条数据0条数据.xlsx";
        String other1 = "/data/develop/ncpcs-notify/upload-files/2020/06/病例导入模板----@#？*&^<40条数据可成功1阿里佛拉法基阿拉姜色放辣椒失蜡法讲哦我金佛我我讲哦期无日期挖掘时拉卡拉设计逻辑 阿可交付垃圾法拉进来弗拉上级分类阿拉斯加放辣椒放辣椒水立方进阿里案例可使肌肤垃圾分类卡就是快乐的腹肌啊来介绍了按理说可交付拉设计费拉结束了可交付2_849828f361ff487b9f09151ae7585e32.jpg";
        String other2 = " /data/develop/ncpcs-notify/upload-files/2020/06/病例导模板条数据可成功1阿里佛拉法基阿拉姜色放辣椒失蜡法讲哦我金佛我我讲哦期无日期挖掘时拉卡拉设计逻辑 阿里可交付垃圾法拉进来弗拉上级分类阿拉斯加放辣椒放辣椒水立方进阿里案例可使肌肤垃圾分类卡就是快乐的腹肌啊来介绍了按理说可交付拉设计费拉结束了可交付2_12f9615864e346e386210de261ed04cc.jpg";
        String other3 = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890abc";
        String other4 = "病例导模板条数据可成功1阿里佛拉法基阿拉姜色放辣椒失讲哦设计逻交付垃拉进来弗拉上级分类阿拉斯加放辣椒放辣椒水立方进阿里案例可使肌肤垃圾分类卡就是快乐的腹肌啊来介绍了按理说可交付拉设计费拉结束了可交付2";
        String other5 = "abcdef1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";

        System.out.println("name 长度: " + name.length());
        System.out.println("other 长度: " + other.length());
        System.out.println("other1 长度: " + other1.length());
        System.out.println("other2 长度: " + other2.length());
        System.out.println("other3 长度: " + other3.length());
        System.out.println("other4 长度: " + other4.length());
        System.out.println("other5 长度: " + other5.length());
    }

    @Test
    public void test2() {
        int readNum = 6;
        int total = 360;
        int readRate = Math.round((readNum * 100F) / total);

        System.out.println(readRate);
    }

    @Test
    public void testInt() {
        Integer a = 89998;
        Integer b = 89998;
        System.out.println(Objects.equals(a, b));
    }

    @Test
    public void testImg() {
//        String str = "        <img src=\"img/java/jvm%s.png\"/><br/>";
//        String str = "\t\t\t<img src=\"img/spring/yc%s.png\"/><br/>";
//        String str = "<img src=\"img/distribution/zk%s.png\"/><br/>";
//        String str ="<img src=\"img/mysql/mysql%s.png\"/><br/>";
        String str = "<img src=\"img/mysql/mycat%s.png\"/><br/>";

//        String str = "    <img src=\"img/distribution/acmq%s.png\"/><br/>";
        int num = 1;
        for (int i = 0; i < 500; i++) {
            System.out.println(String.format(str, num++));
        }
    }

    /**
     * 测试上传附件，重命名之后，匹配 uuid 字符串
     */
    @Test
    public void testUUid2() {
//        String uuid1 = "在e65deb4c-a110-49c8-a4ef-6e69447968d6";
//        String uuid1 = "在e65deb4ca11049c8a4ef6e69447968d6";
        String uuid1 = "北京儿童医院——监测平台近期开发计划_c5cf113415be40ab8c7fab3bde192acb.xlsx";
//        String regex = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
//        System.out.println(uuid1.matches(regex));

//        String reg = "[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}";
        Pattern p = Pattern.compile("_[0-9a-f]{32}\\.");
        Matcher m = p.matcher(uuid1);
        System.out.println(m.find());
    }


    @Test
    public void testEqual() {
//        AssertEquals.assertEquals(2, 1+1);
    }

    @Test
    public void testintByte() {
        int a = 10;
        byte b = 10;
        System.out.println(a == b);
    }

    @Test
    public void testintInteger() {
        int a = 6666;
        Integer b = 6666;
        System.out.println(a == b);
    }

    @Test
    public void testBigDecimal() {
        BigDecimal bd1 = new BigDecimal(0.1);
        System.out.println(bd1);
        BigDecimal bd2 = new BigDecimal("0.1");
        System.out.println(bd2);
    }

    @Test
    public void testIdNumber() {
        String idNum = "530111197308175070";
    }

    @Test
    public void testDot() {
        String pk = "com.lyp.learn";
        String newPk = pk.replace(".", File.separator);
        System.out.println(newPk);
    }

    @Test
    public void userIdRole() {
        String temp = "'1100','1101','1102','1103','1104','1105','1106','1107','1108','1109','1110','1111','1112','1113','1114','1115','1116','1117','1118','1119','1120','1121','1122','1123','1124','1125','1126','1127','1128','1129','1130','1131','1132','1133','1134','1135','1136','1137','1138','1139','1140','1141','1142','1143','1144','1145','1146','1147','1148','1149','1150','1151','1152','1153','1154','1155','1156','1157','1158','1159','1160','1161','1162','1163','1164','1165','1166','1167','1168','1169','1170','1171','1172','1173','1174','1175','1176','1177','1178','1179','1180','1181','1182','1183','1184','1185','1186','1187','1188','1189','1190','1191','1192','1193','1194','1195','1196','1197','1198','1199','1200','1201','1202','1203','1204','1205','1206','1207','1208','1209','1210','1211','1212','1213','1214','1215','1216','1217','1218','1219','1220','1221','1222','1223','1224','1225','1226','1227','1228','1229','1230','1231','1232','1233','1234','1235','1236','1237','1238','1239','1240','1241','1242','1243','1244','1245','1246','1247','1248','1249','1250','1251','1252','1253','1254','1255','1256','1257','1258','1259','1260','1261','1262','1263','1264','1265','1266','1267','1268','1269','1270','1271','1272','1273','1274','1275','1276','1277','1278','1279','1280','1281','1282','1283','1284','1285','1286','1287','1288','1289','1290','1291','1292','1293','1294','1295','1296','1297','1298','1299','1300','1301','1302','1303','1304','1305','1306','1307','1308','1309','1310','1311','1312','1313','1314','1315','1316','1317','1318','1319','1320','1321','1322','1323','1324','1325','1326','1327','1328','1329','1330','1331','1332','1333','1334','1335','1336','1337','1338','1339','1340','1341','1342','1343','1344','1345','1346','1347','1348','1349','1350','1351','1352','1353','1354','1355','1356','1357','1358','1359','1360','1361','1362','1363','1364','1365','1366','1367','1368','1369','1370','1371','1372','1373','1374','1375','1376','1377','1378','1379','1380','1381','1382','1383','1384','1385','1386','1387','1388','1389','1390','1391','1392','1393','1394','1395','1396','1397','1398','1399','1400','1401','1402','1403','1404','1405','1406','1407','1408','1409','1410','1411','1412','1413','1414','1415','1416','1417','1418','1419','1420','1421','1422','1098'";

        String[] arr = temp.split(",");
        System.out.println(arr.length);
//        System.out.println(arr[3]);
//        System.out.println(Integer.parseInt(arr[3]));
        // INSERT INTO `control_user_role_rel` (`user_id`, `role_id`, `rel_time`, `oper_user`) VALUES ('111', '155', '2020-04-15 14:01:23', '443');
        String sql = "INSERT INTO `control_user_role_rel` (`user_id`, `role_id`, `rel_time`, `oper_user`) VALUES (%s, '155', '%s', '443');";
        for (String s : arr) {
            String result = Strings.lenientFormat(sql, s, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            System.out.println(result);
        }
    }
}
