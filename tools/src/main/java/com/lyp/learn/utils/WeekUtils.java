package com.lyp.learn.utils;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;

/**
 * @author liyapu
 * @date 2025-09-17 12:30
 * @desc 周日历网址  https://www.huilvwang.com/rili/rili.html?year=2025
 */
public class WeekUtils {


    public static void main(String[] args) {

        findLastSameWeekAndDay(LocalDate.now());
        findLastSameWeekAndDay(LocalDate.of(2024, 1, 1)); // 跨年周测试
        findLastSameWeekAndDay(LocalDate.of(2023, 12, 31)); // 年最后一周测试
        findLastSameWeekAndDay(LocalDate.of(2024, 2, 29)); // 闰日测试
        findLastSameWeekAndDay(LocalDate.of(2027, 12, 31)); //
        findLastSameWeekAndDay(LocalDate.of(2026, 12, 31)); //
        findLastSameWeekAndDay(LocalDate.of(2026, 12, 1)); //
        findLastSameWeekAndDay(LocalDate.of(2027, 12, 31)); //
        findLastSameWeekAndDay(LocalDate.of(2028, 01, 1)); //
        findLastSameWeekAndDay(LocalDate.of(2020, 01, 1)); //
        findLastSameWeekAndDay(LocalDate.of(2021, 01, 1)); //

        for (int i = 2000; i < 2050; i++) {
            LocalDate startDate = LocalDate.of(i, 1, 1);
            LocalDate startDateCal = findLastSameWeekAndDay(startDate);
            System.out.println("相差天数：" + ChronoUnit.DAYS.between(startDate, startDateCal));


            LocalDate endDate = LocalDate.of(i, 12, 31);
            LocalDate endDateCal = findLastSameWeekAndDay(endDate);
            System.out.println("相差天数：" + ChronoUnit.DAYS.between(endDate, endDateCal));
        }
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
        //ISO标准:每周以‌周一‌为第一天，‌第一周‌必须包含‌至少4天‌的当年日期
        WeekFields weekFields = WeekFields.ISO;
        // 获取输入日期所在的周数和星期几
        int inputWeek = inputDate.get(weekFields.weekOfWeekBasedYear());
        DayOfWeek inputDayOfWeek = inputDate.getDayOfWeek();

        try {
            //获取输入日期所在周对应的年
            int year = inputDate.get(weekFields.weekBasedYear());
            int lastYear = year - 1;
            //先查找去年的1月1号
            LocalDate lastYearStart = LocalDate.of(lastYear, 1, 1);
            // 获取去年1月1号所在的周数
            int lastInputWeek = lastYearStart.get(weekFields.weekOfWeekBasedYear());
            LocalDate lastYearDay = null;

            //使用ISO标准计算年份具有的多少周, 返回 52/53周
            int yearWeekCount = LocalDate.of(year, 12, 28).get(WeekFields.ISO.weekOfWeekBasedYear());
            int lastYearWeekCount = LocalDate.of(lastYear, 12, 28).get(WeekFields.ISO.weekOfWeekBasedYear());

            if (lastInputWeek == 1) {
                //去年1月1日是第一周开始
                if (lastYearWeekCount < yearWeekCount && inputWeek == yearWeekCount) {
                    //去年周数小于今年周数，按照周数星期无法找到，默认降级为去年今日
                    lastYearDay = getLastYearToday(inputDate);
                } else {
                    lastYearDay = lastYearStart.with(weekFields.weekOfWeekBasedYear(), inputWeek)
                            .with(weekFields.dayOfWeek(), inputDayOfWeek.getValue());
                }
            } else {
                //去年1月1日属于前年周数
                if (lastYearWeekCount < yearWeekCount && inputWeek == yearWeekCount) {
                    lastYearDay = getLastYearToday(inputDate);
                } else {
                    lastYearDay = lastYearStart.plusDays(7).with(weekFields.weekOfWeekBasedYear(), inputWeek)
                            .with(weekFields.dayOfWeek(), inputDayOfWeek.getValue());
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
        return weekCount;
    }

    /**
     * 安全获取去年今日（处理闰年2月29日情况）
     */
    private static LocalDate getLastYearToday(LocalDate inputDate) {
        int lastYear = inputDate.getYear() - 1;
        MonthDay monthDay = MonthDay.from(inputDate);

        // 处理2月29日情况
        if (monthDay.equals(MonthDay.of(Month.FEBRUARY, 29)) && !Year.isLeap(lastYear)) {
            return LocalDate.of(lastYear, Month.FEBRUARY, 28);
        }
        return LocalDate.of(lastYear, inputDate.getMonth(), inputDate.getDayOfMonth());
    }


    @Test
    public void test00122() {
        findSameWeekAndDayLastYear(LocalDate.now());
        findSameWeekAndDayLastYear(LocalDate.of(2024, 1, 1)); // 跨年周测试
        findSameWeekAndDayLastYear(LocalDate.of(2023, 12, 31)); // 年最后一周测试
        findSameWeekAndDayLastYear(LocalDate.of(2024, 2, 29)); // 闰日测试
        findSameWeekAndDayLastYear(LocalDate.of(2027, 12, 31)); //
        findSameWeekAndDayLastYear(LocalDate.of(2026, 12, 31)); //
        findSameWeekAndDayLastYear(LocalDate.of(2026, 12, 1)); //
        findSameWeekAndDayLastYear(LocalDate.of(2027, 12, 31)); //
        findSameWeekAndDayLastYear(LocalDate.of(2028, 01, 1)); //
    }

    /**
     * 查找与输入日期具有相同周数和星期几的去年日期
     *
     * @param inputDate 输入日期
     * @return 去年相同周数和星期几的日期，找不到则返回去年今日
     */
    public static LocalDate findSameWeekAndDayLastYear(LocalDate inputDate) {
        if (inputDate == null) {
            throw new IllegalArgumentException("输入日期不能为null");
        }
        System.out.println("入参: " + inputDate + ",验证去年周数: " + inputDate.get(WeekFields.ISO.weekOfWeekBasedYear()) +
                "，星期" + inputDate.getDayOfWeek().getValue());

        LocalDate lastYearSameDay = inputDate.minusYears(1);

        // 获取输入日期的周数和星期几
//        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int inputWeek = inputDate.get(WeekFields.ISO.weekOfWeekBasedYear());
        DayOfWeek inputDayOfWeek = inputDate.getDayOfWeek();

        // 计算去年同期的起始日期范围
        LocalDate startOfLastYear = LocalDate.of(lastYearSameDay.getYear(), 1, 1);
        LocalDate endOfLastYear = LocalDate.of(lastYearSameDay.getYear(), 12, 31);

        // 遍历去年同期的日期范围
        for (LocalDate date = startOfLastYear; date.isBefore(endOfLastYear) || date.isEqual(endOfLastYear); date = date.plusDays(1)) {
            if (date.get(WeekFields.ISO.weekOfWeekBasedYear()) == inputWeek &&
                    date.getDayOfWeek() == inputDayOfWeek) {
                System.out.println("结果: " + date + ",验证去年周数: " + date.get(WeekFields.ISO.weekOfWeekBasedYear()) +
                        "，星期" + date.getDayOfWeek().getValue());
                System.out.println();
                return date;
            }
        }

        // 找不到则返回去年今日
        System.out.println("结果: " + lastYearSameDay + ",验证去年周数: " + lastYearSameDay.get(WeekFields.ISO.weekOfWeekBasedYear()) +
                "，星期" + lastYearSameDay.getDayOfWeek().getValue());
        System.out.println();
        return lastYearSameDay;
    }

    @Test
    public void test001222() {
        findSameWeekAndDayLastYear2(LocalDate.now());
        findSameWeekAndDayLastYear2(LocalDate.of(2024, 1, 1)); // 跨年周测试
        findSameWeekAndDayLastYear2(LocalDate.of(2023, 12, 31)); // 年最后一周测试
        findSameWeekAndDayLastYear2(LocalDate.of(2024, 2, 29)); // 闰日测试
        findSameWeekAndDayLastYear2(LocalDate.of(2027, 12, 31)); //
        findSameWeekAndDayLastYear2(LocalDate.of(2026, 12, 31)); //
        findSameWeekAndDayLastYear2(LocalDate.of(2026, 12, 1)); //
        findSameWeekAndDayLastYear2(LocalDate.of(2027, 12, 31)); //
        findSameWeekAndDayLastYear2(LocalDate.of(2028, 01, 1)); //
    }

    /**
     * 查找与输入日期具有相同ISO周数和星期几的去年日期
     *
     * @param inputDate 输入日期
     * @return 去年相同ISO周数和星期几的日期，找不到则返回去年今日
     */
    public static LocalDate findSameWeekAndDayLastYear2(LocalDate inputDate) {
        if (inputDate == null) {
            throw new IllegalArgumentException("输入日期不能为null");
        }
        System.out.println("入参: " + inputDate + ",验证去年周数: " + inputDate.get(WeekFields.ISO.weekOfWeekBasedYear()) +
                "，星期" + inputDate.getDayOfWeek().getValue());

        LocalDate lastYearSameDay = inputDate.minusYears(1);
        WeekFields isoWeekFields = WeekFields.ISO;

        // 获取输入日期的ISO周数和星期几
        int inputWeek = inputDate.get(isoWeekFields.weekOfWeekBasedYear());
        DayOfWeek inputDayOfWeek = inputDate.getDayOfWeek();

        // 计算去年相同ISO周的第一天（周一）
        LocalDate lastYearWeekStart = LocalDate.of(lastYearSameDay.getYear(), 1, 1)
                .with(isoWeekFields.weekOfWeekBasedYear(), inputWeek)
                .with(isoWeekFields.dayOfWeek(), 1); // ISO周从周一开始

        // 计算目标日期（去年相同周数的对应星期几）
        LocalDate result = lastYearWeekStart.with(inputDayOfWeek);

        // 验证结果年份是否正确
        LocalDate dateResult = result.getYear() == lastYearSameDay.getYear() ? result : lastYearSameDay;
        System.out.println("结果: " + dateResult + ",验证去年周数: " + dateResult.get(WeekFields.ISO.weekOfWeekBasedYear()) +
                "，星期" + dateResult.getDayOfWeek().getValue());
        System.out.println();
        return dateResult;
    }

    @Test
    public void test0012223() {
        findSameIsoWeekLastYear3(LocalDate.now());
        findSameIsoWeekLastYear3(LocalDate.of(2024, 1, 1)); // 跨年周测试
        findSameIsoWeekLastYear3(LocalDate.of(2023, 12, 31)); // 年最后一周测试
        findSameIsoWeekLastYear3(LocalDate.of(2024, 2, 29)); // 闰日测试
        findSameIsoWeekLastYear3(LocalDate.of(2027, 12, 31)); //
        findSameIsoWeekLastYear3(LocalDate.of(2026, 12, 31)); //
        findSameIsoWeekLastYear3(LocalDate.of(2026, 12, 1)); //
        findSameIsoWeekLastYear3(LocalDate.of(2027, 12, 31)); //
        findSameIsoWeekLastYear3(LocalDate.of(2028, 01, 1)); //
    }

    /**
     * 查找去年相同ISO周数和星期几的日期
     *
     * @param inputDate 输入日期
     * @return 去年相同ISO周数和星期几的日期，找不到则返回去年今日
     */
    public static LocalDate findSameIsoWeekLastYear3(LocalDate inputDate) {
        if (inputDate == null) {
            throw new IllegalArgumentException("输入日期不能为null");
        }
        System.out.println("入参: " + inputDate + ",验证去年周数: " + inputDate.get(WeekFields.ISO.weekOfWeekBasedYear()) +
                "，星期" + inputDate.getDayOfWeek().getValue());

        LocalDate lastYearSameDay = inputDate.minusYears(1);
        WeekFields isoWeekFields = WeekFields.ISO;

        // 获取输入日期的ISO周数和星期几
        int inputWeek = inputDate.get(isoWeekFields.weekOfWeekBasedYear());
        DayOfWeek inputDayOfWeek = inputDate.getDayOfWeek();

        // 计算去年相同周的第一天（ISO周从周一开始）
        LocalDate lastYearWeekStart = LocalDate.of(lastYearSameDay.getYear(), 1, 1)
                .with(isoWeekFields.weekOfWeekBasedYear(), inputWeek)
                .with(isoWeekFields.dayOfWeek(), DayOfWeek.MONDAY.getValue());

        // 计算目标日期
        LocalDate result = lastYearWeekStart.with(inputDayOfWeek);

        // 验证结果是否在去年范围内
        if (result.getYear() == lastYearSameDay.getYear()) {
            System.out.println("结果: " + result + ",验证去年周数: " + result.get(WeekFields.ISO.weekOfWeekBasedYear()) +
                    "，星期" + result.getDayOfWeek().getValue());
            System.out.println();
            return result;
        }

        // 处理跨年周特殊情况
        if (inputWeek == 1 && result.getYear() > lastYearSameDay.getYear()) {
            // 如果输入日期是第一周但结果属于明年，尝试去年的最后一周
            LocalDate lastWeekOfLastYear = LocalDate.of(lastYearSameDay.getYear(), 12, 28)
                    .with(isoWeekFields.dayOfWeek(), DayOfWeek.MONDAY.getValue());
            int lastWeek = lastWeekOfLastYear.get(isoWeekFields.weekOfWeekBasedYear());

            LocalDate adjustedResult = lastWeekOfLastYear
                    .with(isoWeekFields.weekOfWeekBasedYear(), lastWeek)
                    .with(inputDayOfWeek);

            if (adjustedResult.getYear() == lastYearSameDay.getYear()) {
                System.out.println("结果: " + adjustedResult + ",验证去年周数: " + adjustedResult.get(WeekFields.ISO.weekOfWeekBasedYear()) +
                        "，星期" + adjustedResult.getDayOfWeek().getValue());
                System.out.println();
                return adjustedResult;
            }
        } else if (inputWeek >= 52 && result.getYear() < lastYearSameDay.getYear()) {
            // 如果输入日期是年末周但结果属于前年，尝试今年的第一周
            LocalDate firstWeekOfLastYear = LocalDate.of(lastYearSameDay.getYear(), 1, 1)
                    .with(isoWeekFields.dayOfWeek(), DayOfWeek.MONDAY.getValue());

            LocalDate adjustedResult = firstWeekOfLastYear
                    .with(isoWeekFields.weekOfWeekBasedYear(), 1)
                    .with(inputDayOfWeek);

            if (adjustedResult.getYear() == lastYearSameDay.getYear()) {
                System.out.println("结果: " + adjustedResult + ",验证去年周数: " + adjustedResult.get(WeekFields.ISO.weekOfWeekBasedYear()) +
                        "，星期" + adjustedResult.getDayOfWeek().getValue());
                System.out.println();
                return adjustedResult;
            }
        }

        System.out.println("结果: " + lastYearSameDay + ",验证去年周数: " + lastYearSameDay.get(WeekFields.ISO.weekOfWeekBasedYear()) +
                "，星期" + lastYearSameDay.getDayOfWeek().getValue());
        System.out.println();
        return lastYearSameDay;
    }

    @Test
    public void test0012224() {
        System.out.println(getWeeksInYear(LocalDate.of(2020, 10, 1)));
        System.out.println(getWeeksInYear(LocalDate.of(2021, 10, 1)));
        System.out.println(getWeeksInYear(LocalDate.of(2022, 10, 1)));
        System.out.println(getWeeksInYear(LocalDate.of(2023, 10, 1)));
        System.out.println(getWeeksInYear(LocalDate.of(2024, 10, 1)));
        System.out.println(getWeeksInYear(LocalDate.of(2025, 10, 1)));
        System.out.println(getWeeksInYear(LocalDate.of(2026, 10, 1)));
        System.out.println(getWeeksInYear(LocalDate.of(2027, 10, 1)));
        System.out.println(getWeeksInYear(LocalDate.of(2028, 10, 1)));
        System.out.println(getWeeksInYear(LocalDate.of(2029, 10, 1)));
        System.out.println(getWeeksInYear(LocalDate.of(2030, 10, 1)));
    }

    /**
     * 获取指定日期所在年份的ISO周总数
     *
     * @param date 输入日期
     * @return 该年份的ISO周总数（52或53）
     */
    public static int getWeeksInYear(LocalDate date) {
        // 获取该年12月28日的日期（ISO周计算规则关键日期）
        LocalDate dec28 = LocalDate.of(date.getYear(), 12, 28);
        // 使用ISO标准计算该日期的周数
        return dec28.get(WeekFields.ISO.weekOfWeekBasedYear());
    }

    @Test
    public void test0012225() {
        for (int i = 2000; i < 3000; i++) {
            int a = getWeeksInYearISO(i);
            int b = getWeeksInYear(LocalDate.of(i, 10, 1));
            if (a != b) {
                System.out.println(i);
            }
        }
    }

    @Test
    public void test0012226() {
        System.out.println(LocalDate.of(2021, 1, 1).get(WeekFields.ISO.weekBasedYear()));
        System.out.println(LocalDate.of(2021, 1, 1).getYear());
    }


    /**
     * 查找与输入日期具有相同周数和星期几的去年日期,兜底返回去年今日
     *
     * @param inputDate 输入日期
     */
    public static LocalDate findLastSameWeekAndDay2(LocalDate inputDate) {
        // ISO标准:每周以‌周一‌为第一天，‌第一周‌必须包含‌至少4天‌的当年日期
        WeekFields weekFields = WeekFields.ISO;

        // 获取输入日期所在的周数
        int inputWeek = inputDate.get(weekFields.weekOfWeekBasedYear());
        // 获取输入日期是星期几
        DayOfWeek inputDayOfWeek = inputDate.getDayOfWeek();
        // 获取输入日期所在周对应的年
        int inputYear = inputDate.get(weekFields.weekBasedYear());

        int lastYear = inputYear - 1;

        // 使用ISO标准计算年份具有多少周, 返回 52/53周
        int yearWeekCount = LocalDate.of(inputYear, 12, 28).get(WeekFields.ISO.weekOfWeekBasedYear());
        int lastYearWeekCount = LocalDate.of(lastYear, 12, 28).get(WeekFields.ISO.weekOfWeekBasedYear());

        if (lastYearWeekCount < yearWeekCount && inputWeek == yearWeekCount) {
            // 去年周数小于今年周数，按照周数星期无法找到，默认降级为去年今日
            return getLastYearToday(inputDate);
        }
        //使用去年的中间日期 6月1号定位,然后在此基础上调整 多少周,星期几
        return LocalDate.of(lastYear, 6, 1)
                .with(weekFields.weekOfWeekBasedYear(), inputWeek)
                .with(weekFields.dayOfWeek(), inputDayOfWeek.getValue());
    }

    /**
     * 查找与输入日期具有相同周数和星期几的去年日期,兜底返回去年今日
     *
     * @param inputDate 输入日期
     */
    public static LocalDate findLastSameWeekAndDay3(LocalDate inputDate) {
        // ISO标准:每周以‌周一‌为第一天，‌第一周‌必须包含‌至少4天‌的当年日期
        WeekFields weekFields = WeekFields.ISO;

        // 获取输入日期所在的周数
        int inputWeek = inputDate.get(weekFields.weekOfWeekBasedYear());
        // 获取输入日期是星期几
        DayOfWeek inputDayOfWeek = inputDate.getDayOfWeek();
        // 获取输入日期所在周对应的年
        int inputYear = inputDate.get(weekFields.weekBasedYear());

        int lastYear = inputYear - 1;

        // 使用ISO标准计算年份具有多少周, 返回 52/53周
        int lastYearWeekCount = LocalDate.of(lastYear, 12, 28).get(WeekFields.ISO.weekOfWeekBasedYear());

        if (lastYearWeekCount < inputWeek) {
            // 去年周数小于今年周数，按照周数星期无法找到，默认降级为去年今日
            return getLastYearToday(inputDate);
        }
        //使用去年的中间日期 6月1号定位,然后在此基础上调整 多少周,星期几
        return LocalDate.of(lastYear, 6, 1)
                .with(weekFields.weekOfWeekBasedYear(), inputWeek)
                .with(weekFields.dayOfWeek(), inputDayOfWeek.getValue());
    }

    @Test
    public void test056(){
        LocalDate inputDate = LocalDate.of(2000, 1, 1);
        System.out.println("--inputDate " + inputDate);
        LocalDate date = null;
        for (int i = 0; i < 2000000; i++) {
            date = inputDate.plusDays(i);
            LocalDate result1 = findLastSameWeekAndDay2(date);
            LocalDate result2 = findLastSameWeekAndDay3(date);
            if(!result1.equals(result2)){
                System.out.println(date);
            }
        }
        System.out.println("--date " + date);
    }
}
