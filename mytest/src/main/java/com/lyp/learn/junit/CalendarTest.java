package com.lyp.learn.junit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Test;

/**
 * @author liyapu
 * @date 2023-05-15 17:01
 * @description Calendar时间类的使用
 * 原文链接：https://blog.csdn.net/Lzfnemo2009/article/details/125229166
 *
 * 注意事项
 * 西方星期的开始为周日，中国为周一。
 * 在Calendar类中，月份的表示是以0-11代表1-12月。
 * 日期是有大小关系的，时间靠后，时间越大。
 */
public class CalendarTest {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * 原文链接：https://blog.csdn.net/weixin_41874888/article/details/104266956
     * Calendar类概念
     * Calendar是日历类，在Date后出现，替换掉了许多Date的方法。该类将所有可能用到的时间信息封装为静态成员变量，方便获取。
     * Calendar为抽象类，由于语言敏感性，Calendar类在创建对象时并非直接创建，而是通过静态方法创建，将语言敏感内容处理好，再返回子类对象，如下：
     *
     * Calendar类静态方法
     * Calendar c = Calendar.getInstance();  //返回当前时间
     */

    /**
     * 一 Calendar类的简单使用
     * YEAR	年
     * MONTH	月（注意月的值是从O开始的，即如果取出的值为0，即为1月) 从0开始算起，最大11；0代表1月，11代表12月。
     * DATE 	日
     * HOUR	以12小时为周期的小时(注意:正午和午夜用O表示，而不是12)
     * MINUTE	分钟
     * SECOND	秒钟
     * MILLISECOND	亳秒
     * HOUR_OF_DAY	以24小时为周期的小时
     * DAY_OF_WEEK	-星期的第几天，返回值范围1-7，星期日为第1天，星期一为第2天，以此类推
     * DAY_OF_MONTH	-个月中的第几天，从1开始
     * DAY_OF_YEAR	-年中的第几天
     * WEEK_OF_MONTH	-月中第几个星期
     * WEEK OF YEAR	-年中第几个星期
     */

    /**
     * public void add(int field,int amount)   //指定字段增加某值
     * 可以使用add方法将日历字段加上或者减去任意时间
     */
    @Test
    public void test1() {

        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar默认时间 " + dateFormat.format(calendar.getTime()));

        //修改当前时间为3天后
        calendar.add(Calendar.DATE, 3);
        //修改当前时间为5小时后
        calendar.add(Calendar.HOUR, 5);
        System.out.println("calendar修改后时间 " + dateFormat.format(calendar.getTime()));

        calendar.add(Calendar.YEAR, -1);
        System.out.println("calendar修改后时间 " + dateFormat.format(calendar.getTime()));
    }

    @Test
    public void test2() {
        //public final void set(int field,int value)//设置指定字段的值
        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar 默认时间 " + dateFormat.format(calendar.getTime()));

        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 20);
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        System.out.println("calendar修改后时间 " + dateFormat.format(calendar.getTime()));
        System.out.println("HOUR_OF_DAY " + calendar.get(Calendar.HOUR_OF_DAY));
    }

    @Test
    public void test3() {
        //public final Date getTime() //获取该日历对象转成的日期对象
        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar 默认时间 " + dateFormat.format(calendar.getTime()));
    }

    @Test
    public void test4() {
        Calendar calendar = Calendar.getInstance();

        System.out.println(calendar.get(Calendar.YEAR));//2022
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.MONTH) + 1);//获取Calendar中的月份，注意Calendar中的月份是从零开始的，需要+1
        System.out.println(calendar.get(Calendar.DATE));//10(10号)
        System.out.println(calendar.get(Calendar.HOUR));//10(12小时为周期)
        System.out.println(calendar.get(Calendar.MINUTE));//41(分钟)
        System.out.println(calendar.get(Calendar.SECOND));//26(秒)
        System.out.println(calendar.get(Calendar.MILLISECOND));//356毫秒(指的是当前时间的毫秒数)
        System.out.println("----");
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));//22(24小时为周期)
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));//6(星期日为1,以此类推)
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//10(一个月中的第几天)
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));//161(一年中的第几天)
        System.out.println(calendar.get(Calendar.WEEK_OF_MONTH));//2(一个月中的第几周)
        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));//24(一年中的第几周)
    }

    /**
     * add与roll的区别
     * roll以日为单位增加,不会超出当前月,以月为单位不会超过当前年,以年为单位增加,会一直累加
     */
    @Test
    public void test5() {
        Calendar calendar1 = Calendar.getInstance();
        System.out.println("calendar1 默认时间 " + dateFormat.format(calendar1.getTime()));

        //以日为单位增加
        calendar1.add(Calendar.DATE, 500);
        System.out.println(calendar1.get(Calendar.YEAR));
        System.out.println(calendar1.get(Calendar.MONTH));
        System.out.println(calendar1.get(Calendar.DATE));
        System.out.println("calendar1 改后时间 " + dateFormat.format(calendar1.getTime()));
        System.out.println();

        Calendar calendar2 = Calendar.getInstance();
        System.out.println("calendar2 默认时间 " + dateFormat.format(calendar2.getTime()));

        calendar2.roll(Calendar.DATE, 500);
        System.out.println(calendar2.get(Calendar.YEAR));
        System.out.println(calendar2.get(Calendar.MONTH));
        System.out.println(calendar2.get(Calendar.DATE));
        System.out.println("calendar2 改后时间 " + dateFormat.format(calendar2.getTime()));
    }

    /**
     * 指定某一时间点
     * 设置某一时间点,calendar默认规则为设置哪个,哪个生效,
     * 没有设置的还是当前时间的数值,例如小时显示23,没有清零,23点则是当前时间的小时
     */
    @Test
    public void test6() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(1999, 1, 20);
        System.out.println(calendar.get(Calendar.YEAR));//1999
        System.out.println(calendar.get(Calendar.MONTH));//1
        System.out.println(calendar.get(Calendar.DATE));//20

        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));//23
    }

    /**
     * 样式格式化
     * 将calendar类转化为date再进行格式化
     */
    @Test
    public void test7() {
        Calendar calendar = Calendar.getInstance();

        Date time = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(time));//2022-06-10
    }

    /**
     * 样式格式化
     * 将calendar类转化为date再进行格式化
     */
    @Test
    public void test8() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar 默认时间 " + dateFormat.format(calendar.getTime()));

        calendar.set(Calendar.MONTH, 10);
        calendar.set(Calendar.DATE, 10);
        calendar.set(Calendar.HOUR, 10);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 10);
        System.out.println("calendar 改后时间 " + dateFormat.format(calendar.getTime()));

        calendar.clear(Calendar.DATE);
        System.out.println("calendar clear DATE 后时间 " + dateFormat.format(calendar.getTime()));

        calendar.clear();
        System.out.println("calendar clear后时间 " + dateFormat.format(calendar.getTime()));

    }

}
