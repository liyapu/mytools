package com.lyp.learn.junit;

import com.google.common.base.Strings;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-09 13:18
 */
@Slf4j
public class FirstTest {

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
