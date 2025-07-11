package com.lyp.learn.streampk;

import com.lyp.learn.bean.Food;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author liyapu
 * @date 2025-07-11 14:24
 * @description
 */
public class SafeUtilDemo {
    public static final DateTimeFormatter PARTITION_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    public void test01() {
        Food food = new Food();

        // sku信息
        String skuName1 = SafeUtil.getValueOrDefault(() -> food, "", f -> f.getName());
        String skuName2 = SafeUtil.getValueOrDefault(() -> food, "", Food::getName);
        Integer age = (Integer) SafeUtil.getValueOrDefault(() -> food, "", Food::getAge);
        // 入库日期
        String inboundDate = SafeUtil.getValueOrDefault(food::getProductDate, "", SafeUtilDemo::toYYYYMMDDFromMill);

        Long currentTime = System.currentTimeMillis();
        //临期剩余天数
        int criticalRemainingDays = SafeUtil.getValueOrDefault(food::getProductDate, -1,
                date -> Math.toIntExact(Math.max(0, SafeUtilDemo.getDaysBetween(currentTime, date)))
        );
        // 禁售剩余天数
        Long forbidRemainingDays = SafeUtil.getValueOrDefault(food::getProductDate, -1L,
                date -> Math.max(0L, SafeUtilDemo.getDaysBetween(currentTime, date)));

    }


    public static String toYYYYMMDDFromMill(Long milli) {
        return PARTITION_DATE_FORMAT.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(milli), ZoneId.systemDefault()));
    }

    /**
     * 获得两个毫秒级时间戳相差的天数，timestamp1 < timestamp2时返回正数，timestamp1 > timestamp2返回负数
     */
    public static long getDaysBetween(Long timestamp1, Long timestamp2) {
        // 将时间戳转换为 LocalDate
        LocalDate date1 = Instant.ofEpochMilli(timestamp1).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = Instant.ofEpochMilli(timestamp2).atZone(ZoneId.systemDefault()).toLocalDate();

        // 计算相差的天数
        return ChronoUnit.DAYS.between(date1, date2);
    }
}
