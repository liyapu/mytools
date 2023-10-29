package com.lyp.learn.junit;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-09 13:18
 */
@Slf4j
public class FirstTest {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

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
     *【强制】浮点数之间的等值判断，基本数据类型不能用==来比较，包装数据类型不能用 equals
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
    public void testFloat2(){
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
     *  说明：BigDecimal(double)存在精度损失风险，在精确计算或值比较的场景中可能会导致业务逻辑异常。
     *   如：BigDecimal g = new BigDecimal(0.1F); 
     *   实际的存储值为：0.10000000149
     */
    @Test
    public void testBigDecimalDemo1(){
        //正例：优先推荐入参为 String 的构造方法，或使用 BigDecimal 的 valueOf 方法，此方法内部其实执行了
        //Double 的 toString，而 Double 的 toString 按 double 的实际能表达的精度对尾数进行了截断。
        BigDecimal recommend1 = new BigDecimal("0.1");
        BigDecimal recommend2 = BigDecimal.valueOf(0.1);
    }

    @Test
    public void testStr(){
        String str = "10309\n" + "10309\n"  + "9453\n" + "97119\n" + "97119";
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
    public void testYearNums(){
        //获取今年的天数
        int intdaysOfThisYear=LocalDate.now().lengthOfYear();
        //获取指定某年的天数
        int intdaysOfThisYear2 = LocalDate.of(2011, 1, 1).lengthOfYear();

    }

    /**
     * 【强制】日期格式化时，传入pattern中表示年份统一使用小写的y（说明：日期格式化时，yyyy表示当天所在的年，
     * 而大写的YYYY代表是week in which year（JDK7之后引入的概念），意思是当天所在的周属于的年份，一周从周日开始，周六结束，只要本周跨年，返回的YYYY就是下一年）
     */
    @Test
    public void testYear(){
        //正例：表示日期和时间的格式如下所示
        String strTime = "2021-12-02";
        LocalDate parse = LocalDate.parse(strTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("parse : " + parse) ;

        LocalDateTime localDateTimeNow1 = LocalDateTime.parse("2018-12-18 15:41:15",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
    public void testBigDecimalCompareTo(){
        BigDecimal num1 = new BigDecimal("100.000");
        BigDecimal num2 = new BigDecimal("100");

        System.out.println(Objects.equals(num1,num2));
        System.out.println(num1.compareTo(num2));

    }

    /**
     * 获取当前时间的 毫秒时间戳
     */
    @Test
    public void testEpochMilli(){
        long epochMilli = Instant.now().toEpochMilli();
        System.out.println(epochMilli);
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);
    }

    @Test
    public void testBigDecimal4(){
        //Java有自带的 stripTrailingZeros() 方法用于去除末尾多余的0

        BigDecimal num = new BigDecimal("100.000");
        BigDecimal numNoEndZero = num.stripTrailingZeros();  //numNoEndZero  ：1E+2
        System.out.println(numNoEndZero.toString());

        //按上面的方法输出结果，会显示科学计数法，所以需要处理一下，解决方法：
        String numNoEndZeroStr = new BigDecimal("100.000").stripTrailingZeros().toPlainString();   //numNoEndZeroStr  ：100
        System.out.println(numNoEndZeroStr);

    }

    @Test
    public void testBigDecimal3(){
        BigDecimal bg = new BigDecimal("100000000.0000000");
        System.out.println(bg);


        System.out.println(bg.toString());

        //stripTrailingZeros()函数就是用于去除末尾多余的0的
        String bgStr = bg.stripTrailingZeros().toPlainString();
        System.out.println(bgStr);
    }

    @Test
    public void testBigDecimal2(){
        BigDecimal bg = new BigDecimal(100.0000000);
        System.out.println(bg);

        System.out.println(bg.toString());

        String bgStr = bg.stripTrailingZeros().toPlainString();
        System.out.println(bgStr);
    }

    @Test
    public void testOptional(){
//        Optional<Integer> numOptional = Optional.ofNullable(null);
        Optional<Integer> numOptional = Optional.ofNullable(2);
        Integer count  = 100;
        String confirmAuthorization = "--";

        if(numOptional.isPresent() && null != numOptional.get()) {
            confirmAuthorization = "确认";
        }
        System.out.println("confirmAuthorization = " + confirmAuthorization);

    }

    /**
     * 测试 list 方法
     */
    @Test
    public void testList02(){
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
    public void testList01(){
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
     *
     * 我们可以利用不间断空格的Unicode编码来移除它，其编码为\u00A0
     */
    @Test
    public void testNbsp1(){
        final char c1 = ' '; //db里的空格
        final char c2 = ' '; //手动输入的空格
        System.out.println((int)c1); //160
        System.out.println((int)c2); //32
    }

    @Test
    public void testNbsp2(){
//        replace("\u00A0", "")
//        replaceAll("\\u00A0+", "")  //这是正则表达式的写法
        String idCard = "  22072 2198808014012    ";
        String idCardTemp = idCard.replace("\u00A0", "");
        System.out.println("idCardTemp= " + idCardTemp);

    }
    @Test
    public void testTrim(){
        String str = "a b       c   d   e   f";

        String idCard = "   22011      219880    7014218    ";
//        String idCardTemp = idCard.trim();
        String idCardTemp = replaceBlank(idCard);
        System.out.println("idCard = "+ idCard);
        System.out.println("idCardTemp = "+ idCardTemp);

    }

    @Test
    public void testTrim2(){
        String str = "a b       c   d   e   f";

        String idCard = "   22011      219880    7014218    ";
        //        String idCardTemp = idCard.trim();
        String idCardTemp = replaceBlank2(idCard);
        System.out.println("idCard = "+ idCard);
        System.out.println("idCardTemp = "+ idCardTemp);

    }

    @Test
    public void test5(){

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
    public void test4(){
        List<String> names = Arrays.asList("赵","钱","孙","李","周","郑","王");
        List<String> nums = Arrays.asList("一","二","三","四","五","六","七","八","九","十");
        for (String name : names) {
            for (String num : nums) {
                System.out.println(name + num);
            }
        }

    }

    @Test
    public void testL3(){
        LocalDate localDateTimeNow1 = LocalDate.parse("2018-12",DateTimeFormatter.ofPattern("yyyy-MM"));
        System.out.println("localDateTimeNow1 : " + localDateTimeNow1);
    }


    @Test
    public void testL2() {
        List<String> payrollMonthList = Arrays.asList("2019-01", "2019-02", "2019-03", "2019-10", "2019-11", "2019-12");
        for (String payrollMonth : payrollMonthList) {
            LocalDate parse = LocalDate.parse(payrollMonth,DateTimeFormatter.ofPattern("yyyy-MM"));
            System.out.println(parse.format(DateTimeFormatter.ofPattern("yyyy-MM")));
        }
    }


        @Test
    public void testL1(){
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
                month = Strings.padStart((Integer.parseInt(month) - 1)+"",2,'0');
            }
            String settleMonth = year + month;
            System.out.println(settleMonth);
        }

    }

    @Test
    public void addTest(){
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
     *   去除子符串首尾空格方法
     *   匹配首尾空白字符的正则表达式：^\s* ¦\s*$
     *   评注：可以用来删除行首行尾的空白字符(包括空格、制表符、换页符等等)，非常有用的表达式
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
