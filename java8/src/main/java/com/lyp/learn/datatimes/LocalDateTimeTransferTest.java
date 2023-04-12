package com.lyp.learn.datatimes;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.junit.jupiter.api.Test;

/**
 * @author liyapu
 * @date 2023-03-22 14:37
 * @description
 */
public class LocalDateTimeTransferTest {

    /**
     * Date转LocalDateTime
     */
    @Test
    public void dateToLocalDateTime1() {
        //方法一
        LocalDateTime localDateTime1 = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("localDateTime1 = " + localDateTime1);

        //方法二
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        System.out.println("localDateTime2 = " + localDateTime2);
    }

    /**
     * LocalDateTime转Date
     */
    @Test
    public void localDateTimeToDate1() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("dae = " + date);
    }

    //=========== LocalDateTime与String互转 ==========================================
    @Test
    public void LocalDateTimeToStr() {
        //时间转字符串格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
        System.out.println("dateTime = " + dateTime);

        //字符串转时间
        String dateTimeStr = "2018-07-28 14:11:15";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime2 = LocalDateTime.parse(dateTimeStr, df);
        System.out.println("dateTime2 = " + dateTime2);

    }
    //==============LocalDateTime==========时间戳=======================================

    /**
     * LocalDateTime 转 时间戳 秒
     * 10位数字，比如
     * 1679468114
     */
    @Test
    public void testLocalDateTime2() {
        //获得当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        //将当前时间转为时间戳  秒
        long second1 = localDateTime.toEpochSecond(ZoneOffset.ofHours(8));
        System.out.println("second1 = " + second1);

        //将当前时间转为时间戳  秒
        long second2 = localDateTime.toInstant(ZoneOffset.ofHours(8)).getEpochSecond();
        System.out.println("second2 = " + second2);
        //获取秒数
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        System.out.println("second3 = " + second);
    }

    /**
     * LocalDateTime 转 时间戳 毫秒
     * 13位数字，比如
     * 1679468066824
     */
    @Test
    public void testLocalDateTime3() {
        //获取毫秒数
        Long milliSecond1 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("milliSecond1 = " + milliSecond1);

        Long milliSecond2 = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println("milliSecond2 = " + milliSecond2);
    }

    /**
     * 时间戳 转LocalDateTime
     */
    @Test
    public void unixToLocalDateTime1() {
        //获得时间戳  秒
        long second1 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond();
        long second2 = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).getEpochSecond();
        System.out.println("second1 = " + second1);
        System.out.println("second2 = " + second2);
        System.out.println();
        //将当前时间戳转换当前时间
        LocalDateTime localDateTime1 = LocalDateTime.ofEpochSecond(second1, 0, ZoneOffset.ofHours(8));
        System.out.println("localDateTime1 = " + localDateTime1);
        System.out.println("-----------");

        //获得时间戳  毫秒
        long milliseconds1 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long milliseconds2 = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println("milliseconds1 = " + milliseconds1);
        System.out.println("milliseconds2 = " + milliseconds2);
        System.out.println();
        //将当前时间戳转为当前时间
        LocalDateTime localDateTime2 = LocalDateTime.ofEpochSecond(milliseconds1 / 1000, 0, ZoneOffset.ofHours(8));
        System.out.println("localDateTime2 = " + localDateTime2);
    }

    //==============LocalDate==========时间戳=======================================

    /**
     * LocalDate 转 时间戳
     */
    @Test
    public void localDateToUnix1() {
        //获得时间戳 秒
        long second = LocalDate.now().atStartOfDay(ZoneOffset.ofHours(8)).toInstant().getEpochSecond();
        System.out.println("second = " + second);

        //获得时间戳 毫秒
        long milliseconds = LocalDate.now().atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
        System.out.println("milliseconds = " + milliseconds);
    }

    /**
     * 时间戳与LocalDate互转
     */
    @Test
    public void testUnixToLocalDate1() {
        //获得时间戳 毫秒
        long milliseconds = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        //当当前时间戳转为当前日期
        LocalDate localDate1 = Instant.ofEpochMilli(milliseconds).atZone(ZoneOffset.ofHours(8)).toLocalDate();
        System.out.println("localDate1 = " + localDate1);

        //获得当前时间戳 秒
        long seconds = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond();
        LocalDate localDate2 = Instant.ofEpochSecond(seconds).atZone(ZoneOffset.ofHours(8)).toLocalDate();
        System.out.println("localDate2 = " + localDate2);
    }

}

















