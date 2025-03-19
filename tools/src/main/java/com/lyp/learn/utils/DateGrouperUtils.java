package com.lyp.learn.utils;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author liyapu
 * @date 2024-10-25 20:56
 * @description
 */
public class DateGrouperUtils {

    /**
     * 一个月的毫秒数
     */
    Long oneMonthInMillis = 24 * 60 * 60 * 1000L * 31;

    /**
     * 1毫秒
     */
    public static final Long ONE_MILLIS = 1L;


    /**
     * 将给定的开始日期和结束日期按照groupSize天为一个分组，分成不同的组
     *
     * @param startDate 开始日期,包含此日期
     * @param endDate   结束日期,包含此日期
     * @return 分组后的日期对列表，每个对包含一个开始日期和一个结束日期
     */
    public static List<Pair<LocalDate, LocalDate>> groupDatesByGroupSize(LocalDate startDate, LocalDate endDate, Long groupSize) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate) || Objects.isNull(groupSize) || groupSize <= 0) {
            return Collections.emptyList();
        }
        List<Pair<LocalDate, LocalDate>> dateGroups = new ArrayList<>();
        LocalDate currentStartDate = startDate;

        while (!currentStartDate.isAfter(endDate)) {
            LocalDate currentEndDate = currentStartDate.plusDays(groupSize - 1L);
            if (currentEndDate.isAfter(endDate)) {
                currentEndDate = endDate;
            }
            dateGroups.add(Pair.of(currentStartDate, currentEndDate));
            currentStartDate = currentEndDate.plusDays(1);
        }

        return dateGroups;
    }

    /**
     * 将给定的开始时间戳和结束时间戳按照rangeTime为一个分组，分成不同的组
     *
     * @param startTimestamp 开始时间戳,包含此时间
     * @param endTimestamp   结束时间戳,包含此时间
     * @return 分组后的时间戳对列表，每个对包含一个开始时间戳和一个结束时间戳
     */
    public static List<Pair<Long, Long>> groupTimestampsByGroupSize(Long startTimestamp, Long endTimestamp, long rangeTime) {
        if (Objects.isNull(startTimestamp) || Objects.isNull(endTimestamp) || rangeTime <= 0) {
            return Collections.emptyList();
        }
        List<Pair<Long, Long>> timestampGroups = new ArrayList<>();
        Long currentStartTimestamp = startTimestamp;
        while (currentStartTimestamp <= endTimestamp) {
            Long currentEndTimestamp = currentStartTimestamp + rangeTime;
            if (currentEndTimestamp > endTimestamp) {
                currentEndTimestamp = endTimestamp;
            }
            timestampGroups.add(Pair.of(currentStartTimestamp, currentEndTimestamp));
            currentStartTimestamp = currentEndTimestamp + 1;
        }
        return timestampGroups;
    }

//    public static void main(String[] args) {
//        LocalDate startDate = LocalDate.of(2023, 10, 1);
//        LocalDate endDate = LocalDate.of(2023, 10, 31);
////        LocalDate endDate = LocalDate.of(2023, 10, 1);
//
//        List<Pair<LocalDate, LocalDate>> dateGroups = groupDatesByGroupSize(startDate, endDate, -10L);
//
//        for (Pair<LocalDate, LocalDate> group : dateGroups) {
//            System.out.println("Start Date: " + group.getLeft() + ", End Date: " + group.getRight());
//        }
//    }

    @Test
    public void testGroupTimestampsByGroupSize() {
//        "startTime":1721386923931   2024-07-19 19:02:03
//        "endTime":1731754923931     2024-11-16 19:02:03
        List<Pair<Long, Long>> pairs = groupTimestampsByGroupSize(1721386923931L, 1731754923931L, oneMonthInMillis);
        System.out.println(pairs);
    }

    /**
     * 使用java代码，使用java8的日期工具类，实现一个算法，
     * 1. 获取上自然月的开始和结束
     * 2. 然后按照16天一个分组，分成组
     * 3. 每个组的开始日期变成昨天的日期的23点的毫秒数，每个组的结束日期变成结束日期的22点59分59秒的毫秒数
     * 4. 返回每个组的经过3变换后的开始和结束时间毫秒数
     */
    public List<Pair<Long, Long>> getLastMonthGroups() {
        //每个组的分组大小
        Integer groupSize = 16;
//        Integer groupSize = 1;

        LocalDate today = LocalDate.now();

        // 获取上个月的第一天和最后一天
        LocalDate firstDayOfLastMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = firstDayOfLastMonth.with(TemporalAdjusters.lastDayOfMonth());

        // 按16天分组
        List<Pair<LocalDate, LocalDate>> dateGroups = splitIntoGroups(firstDayOfLastMonth, lastDayOfLastMonth, groupSize);

        // 转换每个组的开始和结束时间为毫秒数
        List<Pair<Long, Long>> result = new ArrayList<>();
        for (Pair<LocalDate, LocalDate> group : dateGroups) {
            LocalDate groupStartDate = group.getLeft();
            ;
            LocalDate groupEndDate = group.getRight();

            //调整开始时间为前一天的23:00:00,转换为毫秒数
            long startMillis = groupStartDate.minusDays(1).atTime(23, 0, 0).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
            //调整结束时间为当天的22:59:59,转换为毫秒数
            long endMillis = groupEndDate.atTime(22, 59, 59).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
            result.add(Pair.of(startMillis, endMillis));
        }
        return result;
    }

    private List<Pair<LocalDate, LocalDate>> splitIntoGroups(LocalDate startDate, LocalDate endDate, Integer groupSize) {
        List<Pair<LocalDate, LocalDate>> groups = new ArrayList<>();
        LocalDate currentStart = startDate;

        while (!currentStart.isAfter(endDate)) {
            LocalDate currentEnd = currentStart.plusDays(groupSize - 1);
            if (currentEnd.isAfter(endDate)) {
                currentEnd = endDate;
            }
            groups.add(Pair.of(currentStart, currentEnd));
            currentStart = currentEnd.plusDays(1);
        }
        return groups;
    }

    @Test
    public void testGetLastMonthGroups01() {
        List<Pair<Long, Long>> lastMonthGroups = getLastMonthGroups();
        System.out.println("lastMonthGroups :::" + JsonUtil.writeToString(lastMonthGroups));
    }

    public List<Pair<Long, Long>> getLastMonthScheduleGroups() {
        //每个组的分组大小
        Integer groupSize = 16;
        LocalDate today = LocalDate.now();

        // 获取上个月的第一天和最后一天
        LocalDate firstDayOfLastMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = firstDayOfLastMonth.with(TemporalAdjusters.lastDayOfMonth());

        return splitGroupsBySize(firstDayOfLastMonth, lastDayOfLastMonth, groupSize);
    }

    private List<Pair<Long, Long>> splitGroupsBySize(LocalDate startDate, LocalDate endDate, Integer groupSize) {
        List<Pair<Long, Long>> groupList = new ArrayList<>();
        LocalDate currentStart = startDate;

        while (!currentStart.isAfter(endDate)) {
            LocalDate currentEnd = currentStart.plusDays(groupSize - 1);
            if (currentEnd.isAfter(endDate)) {
                currentEnd = endDate;
            }
            //调整开始时间为前一天的23:00:00,转换为毫秒数
            long startMillis = currentStart.minusDays(1).atTime(23, 0, 0).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
            //调整结束时间为当天的22:59:59,转换为毫秒数
            long endMillis = currentEnd.atTime(22, 59, 59).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
            groupList.add(Pair.of(startMillis, endMillis));
            currentStart = currentEnd.plusDays(1);
        }
        return groupList;
    }

    @Test
    public void testGetLastMonthScheduleGroups() {
        List<Pair<Long, Long>> lastMonthScheduleGroups = getLastMonthScheduleGroups();
        System.out.println("lastMonthScheduleGroups :::" + JsonUtil.writeToString(lastMonthScheduleGroups));

    }
}

