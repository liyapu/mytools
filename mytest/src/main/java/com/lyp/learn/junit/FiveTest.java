package com.lyp.learn.junit;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * @author liyapu
 * @date 2025-09-16 18:11
 * @desc
 */
public class FiveTest {

    @Test
    public void test0001211() {
        LocalDate today = LocalDate.of(2025, 9, 16); // 当前测试日期


        // 使用中国本地化周规则
        WeekFields weekFields = WeekFields.of(Locale.CHINA);

        // 获取匹配结果
        LocalDate matchedDate = findMatchingDate(today, weekFields);

        System.out.println("今天是: " + today);
        System.out.println("匹配的日期是: " + matchedDate);
    }

    public static LocalDate findMatchingDate(LocalDate currentDate, WeekFields weekFields) {
        // 获取当前周数和星期几
        int currentWeek = currentDate.get(weekFields.weekOfWeekBasedYear());
        DayOfWeek currentDayOfWeek = currentDate.getDayOfWeek();

        // 尝试在去年找到相同周数和星期几的日期
        LocalDate lastYearDate = findDateInLastYear(
                currentDate.getYear() - 1,
                currentWeek,
                currentDayOfWeek,
                weekFields
        );

        // 如果找不到，尝试调整星期几
        if (lastYearDate == null) {
            for (int i = 1; i < 7; i++) {
                DayOfWeek adjustedDay = currentDayOfWeek.minus(i);
                lastYearDate = findDateInLastYear(
                        currentDate.getYear() - 1,
                        currentWeek,
                        adjustedDay,
                        weekFields
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
                        currentDayOfWeek,
                        weekFields
                );
                if (lastYearDate != null) break;
            }
        }

        // 如果最终找不到，返回去年今日（处理特殊日期）
        if (lastYearDate == null) {
            return getLastYearSameDay(currentDate);
        }

        return lastYearDate;
    }

    private static LocalDate findDateInLastYear(int year, int week, DayOfWeek dayOfWeek, WeekFields weekFields) {
        // 获取去年的第一周第一天
        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1)
                .with(weekFields.dayOfWeek(), 1); // 调整为周一

        // 计算目标周的周一
        LocalDate targetWeekMonday = firstDayOfYear.plusWeeks(week - 1);

        // 计算该周的目标星期几
        LocalDate targetDate = targetWeekMonday.with(dayOfWeek);

        // 检查是否确实属于目标周
        if (targetDate.get(weekFields.weekOfWeekBasedYear()) == week) {
            return targetDate;
        }
        return null;
    }

    /**
     * 获取去年今日日期，处理特殊日期（如闰年2月29日）
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
    public void test001() {
        LocalDate firstDay = getFirstWeekMondayOfLastYear();
        System.out.println("去年第一周第一天(周一): " + firstDay);
    }

    /**
     * 获取去年第一周的周一（符合中国手机日历标准）
     *
     * @return 去年第一周周一的日期
     */
    public static LocalDate getFirstWeekMondayOfLastYear() {
        // 使用中国周定义（周一为一周第一天）
        WeekFields chinaWeekFields = WeekFields.of(Locale.CHINA);

        // 获取去年的1月1日
        LocalDate lastYearJan1 = LocalDate.now().minusYears(1).withDayOfYear(1);

        // 找到第一个周一（中国周起始日）
        LocalDate firstMonday = lastYearJan1.with(chinaWeekFields.dayOfWeek(), 1L);

        // 验证是否确实是第一周（可能跨年）
        if (firstMonday.get(chinaWeekFields.weekOfWeekBasedYear()) != 1) {
            firstMonday = firstMonday.plusWeeks(1);
        }

        return firstMonday;


    }


    public static LocalDate findSameWeekAndDay(LocalDate inputDate) {
        // 获取输入日期的周数和星期几
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int inputWeek = inputDate.get(weekFields.weekOfWeekBasedYear());
        int inputDayOfWeek = inputDate.getDayOfWeek().getValue();
        int inputYear = inputDate.getYear();

        // 尝试在当前年度查找
        LocalDate sameWeekDate = findInYear(inputYear, inputWeek, inputDayOfWeek);
        if (sameWeekDate != null) {
            return sameWeekDate;
        }

        // 尝试在去年查找（最多回溯14天）
        LocalDate lastYearDate = inputDate.minusDays(1);
        for (int i = 0; i < 14; i++) {
            int lastYearWeek = lastYearDate.get(weekFields.weekOfWeekBasedYear());
            if (lastYearWeek == inputWeek &&
                    lastYearDate.getDayOfWeek().getValue() == inputDayOfWeek) {
                return lastYearDate;
            }
            lastYearDate = lastYearDate.minusDays(1);
        }

        // 默认返回去年今日（处理闰年情况）
        LocalDate lastYearToday = LocalDate.of(inputYear - 1, inputDate.getMonth(), 1)
                .withDayOfMonth(Math.min(inputDate.getDayOfMonth(),
                        inputDate.getMonth().length(inputDate.isLeapYear())));

        return lastYearToday;
    }

    private static LocalDate findInYear(int year, int week, int dayOfWeek) {
        LocalDate date = LocalDate.of(year, 1, 1)
                .with(WeekFields.ISO.weekOfWeekBasedYear(), week)
                .with(WeekFields.ISO.dayOfWeek(), dayOfWeek);

        return date.getYear() == year ? date : null;
    }

    @Test
    public void test00011() {
        LocalDate testDate = LocalDate.of(2024, 1, 1); // 周一
        System.out.println("匹配日期: " + findSameWeekAndDay(testDate));
        System.out.println("匹配日期: " + findSameWeekAndDay(LocalDate.now()));
    }

    @Test
    public void test000123() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        WeekFields weekFields = WeekFields.ISO;

        System.out.println("2024-01-01的ISO周数: " +
                date.get(weekFields.weekOfWeekBasedYear())); // 输出1
        System.out.println("所属ISO年份: " +
                date.get(weekFields.weekBasedYear())); // 输出2024
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

    public static void main(String[] args) {
//        System.out.println(findCalSameWeekAndDay(LocalDate.now())) ;
//        System.out.println(findCalSameWeekAndDay(LocalDate.of(2024, 1, 1))); // 跨年周测试
//        System.out.println(findCalSameWeekAndDay(LocalDate.of(2023, 12, 31))); // 年最后一周测试
//        System.out.println(findCalSameWeekAndDay(LocalDate.of(2024, 2, 29))); // 闰日测试
//        System.out.println(findCalSameWeekAndDay(LocalDate.of(2023, 12, 31))); //
//        System.out.println(findCalSameWeekAndDay(LocalDate.of(2027, 12, 31))); //

        findLastSameWeekAndDay(LocalDate.now());
        findLastSameWeekAndDay(LocalDate.of(2024, 1, 1)); // 跨年周测试
        findLastSameWeekAndDay(LocalDate.of(2023, 12, 31)); // 年最后一周测试
        findLastSameWeekAndDay(LocalDate.of(2024, 2, 29)); // 闰日测试
        findLastSameWeekAndDay(LocalDate.of(2027, 12, 31)); //
        findLastSameWeekAndDay(LocalDate.of(2026, 12, 31)); //
    }

    @Test
    public void test00012111() {
        System.out.println(getWeeksInYearISO(2026));
        System.out.println(getWeeksInYearISO(2025));
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






}
