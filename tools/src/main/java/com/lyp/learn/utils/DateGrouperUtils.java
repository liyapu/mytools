package com.lyp.learn.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalDate;
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

    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2023, 10, 1);
        LocalDate endDate = LocalDate.of(2023, 10, 31);
//        LocalDate endDate = LocalDate.of(2023, 10, 1);

        List<Pair<LocalDate, LocalDate>> dateGroups = groupDatesByGroupSize(startDate, endDate, -10L);

        for (Pair<LocalDate, LocalDate> group : dateGroups) {
            System.out.println("Start Date: " + group.getLeft() + ", End Date: " + group.getRight());
        }
    }
}

