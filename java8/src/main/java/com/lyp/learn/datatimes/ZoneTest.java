package com.lyp.learn.datatimes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * @author liyapu
 * @date 2023-04-12 11:22
 * @description 时区的处理
 * Java8 中加入了对时区的支持，带时区的时间为分别为：ZonedDate、ZonedTime、ZonedDateTime。其中每个时区都对应着 ID，地区ID都为 “{区域}/{城市}”的格式。
 * 例如 ：Asia/Shanghai 等
 * ZoneId：该类中包含了所有的时区信息
 * getAvailableZoneIds() : 可以获取所有时区时区信息
 * of(id) : 用指定的时区信息获取 ZoneId 对象
 *
 * 原文：https://blog.csdn.net/weixin_43088443/article/details/112723760
 */
public class ZoneTest {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 查看所有的可用时区
     */
    @Test
    public void test1() {
        // 调用ZoneId类的getAvailableZoneIds
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }
    /*
     * 结果：
     * ……
     * Pacific/Guadalcanal
     * Europe/Athens
     * US/Pacific
     * Europe/Monaco
     */

    /**
     * 通过时区构建LocalDateTime
     */
    @Test
    public void test2() {
        LocalDateTime ldt1 = LocalDateTime.now(ZoneId.of("UTC"));
        System.out.println(dateFormatter.format(ldt1));
        ZonedDateTime atZone = ldt1.atZone(ZoneId.of("UTC"));
        System.out.println(atZone);
        // 输出
        //2020-03-21 09:41:59
        //2020-03-21T09:41:59.476Z[UTC]
    }
    /*
     * 结果：
     * 2018-01-21T23:15:27.902
     */

    /**
     * 以时区格式显示时间
     */
    @Test
    public void test3() {
        LocalDateTime ldt1 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime atZone = ldt1.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(atZone);
    }
    /*
     * 结果：
     * 2018-01-22T12:15:20.131+08:00[Asia/Shanghai]
     */

    /**
     * 带时区的
     * ZoneDate ZoneTime ZoneDateTime
     */
    @Test
    public void test9() {
        System.out.println("-----------支持的所有时区-----------------");
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        for (String s : availableZoneIds) {
            System.out.println(s);
        }
        System.out.println("------------------时区-----------------------------");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("zonedDateTime : " + zonedDateTime);

        LocalDate date = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        System.out.println("date.atStartOfDay(zoneId) : " + date.atStartOfDay(zoneId));

        System.out.println("localDateTime.atZone(zoneId) : " + localDateTime.atZone(zoneId));

    }
}

