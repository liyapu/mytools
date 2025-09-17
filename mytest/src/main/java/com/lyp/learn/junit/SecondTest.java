package com.lyp.learn.junit;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * @author liyapu
 * @date 2025-09-16 18:03
 * @desc
 */
public class SecondTest {

    @Test
    public void test0001211(){

//        findMatchingDateLastYear(LocalDate.of(2025, 9, 16));
        findMatchingDateLastYear(LocalDate.of(2024, 2, 29));
//        findMatchingDateLastYear(LocalDate.of(2023,12,31));
//        findMatchingDateLastYear(LocalDate.of(2027,12,31));
    }

    public static LocalDate findMatchingDateLastYear(LocalDate currentDate) {
        WeekFields weekFields = WeekFields.of(Locale.CHINA);

        System.out.println("今年: " + currentDate + ",周数: " + currentDate.get(weekFields.weekOfWeekBasedYear()) +
                "，星期" + currentDate.getDayOfWeek().getValue());

        // 获取当前周数
        int currentWeek = currentDate.get(weekFields.weekOfWeekBasedYear());
        // 获取当前是星期几
        DayOfWeek currentDayOfWeek = currentDate.getDayOfWeek();

        // 尝试在去年找到相同周数和星期几的日期
        LocalDate lastYearDate = findDateInLastYear(
                currentDate.getYear() - 1,
                currentWeek,
                currentDayOfWeek,weekFields
        );

        // 如果找不到，尝试调整星期几
        if (lastYearDate == null) {
            for (int i = 1; i < 7; i++) {
                DayOfWeek adjustedDay = currentDayOfWeek.minus(i);
                lastYearDate = findDateInLastYear(
                        currentDate.getYear() - 1,
                        currentWeek,
                        adjustedDay,weekFields
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
                        currentDayOfWeek,weekFields
                );
                if (lastYearDate != null) break;
            }
        }

//        return lastYearDate != null ? lastYearDate : LocalDate.of(currentDate.getYear() - 1, 1, 1); // 默认返回去年第一天
        lastYearDate =  lastYearDate != null ? lastYearDate : getLastYearSameDay(currentDate);
        System.out.println("目标日期: " + lastYearDate + ",周数: " + lastYearDate.get(weekFields.weekOfWeekBasedYear()) +
                "，星期" + lastYearDate.getDayOfWeek().getValue());
        System.out.println();
        return lastYearDate;
    }

    private static LocalDate findDateInLastYear(int year, int week, DayOfWeek dayOfWeek, WeekFields weekFields) {
        // 获取去年的第一周第一天(ISO标准周一为第一天)
        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1)
                .with(weekFields.dayOfWeek(), 1);

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
}
