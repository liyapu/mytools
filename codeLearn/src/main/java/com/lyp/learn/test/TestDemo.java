package com.lyp.learn.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.TypeReference;
import com.lyp.learn.utils.HttpClient;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-06-20 13:00
 */
public class TestDemo {

    @Test
    public void test56() {
        String str = "12345@qq.com.cn";
        String regex = "^(\\w+)@\\w+(\\.(\\w+))+$";

        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(str);

        boolean isFind = matcher.find();
        System.out.println(isFind);
        //遍历获取所有的捕获组内容
        System.out.println(matcher.groupCount());
        System.out.println();

        for (int i = 0; i <= matcher.groupCount(); i++) {
            String string = matcher.group(i);
            System.out.println(string);
        }
    }

    @Test
    public void test55() {
        String content = "I am noob from runoob.com.";
        String pattern = ".*runoob.*";
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(isMatch); //true
    }

    @Test
    public void test54() {
        System.out.println(Long.MAX_VALUE);
        long time = 30 * 24 * 3600 * 1000L;
        System.out.println(time);
    }

    @Test
    public void test53() {
        String strTemp = "104753,104777,104795,104816,104939,105036,105518,107489,107506,107514,107526,107527,107530,107537,107545,107548,107556,107560,107566,107567,107576,107578,107593,107600,107775,107800,206119,206283,206539,206541,206542,206543,206544,206547,206548,206550,206551,206553,206554,206555,206556,206557,206558,206564,206565,206566,206567,206568,206569,206570,206572,206574,206575,206576,206577,206578,206580,206581,206583,206584,206585,206587,206797,207681,207686,207690,280852,307610,307614,307617,307621,307625,307635,307670,307678,307715,307718,308068,380994,405057,405068,405084,405102,405116,405117,405119,405128,405146,405298,405306,405325,405326,406241,406265,406593,411370,412958,505526,505529,505530,505531,505532,505533,505534,505535,505536,505537,505538,505539,505540,505541,505542,505543,505545,505546,505547,505989,506109,506322,506340,506469,506562,506755,507834,507835,507836,507839,519403,580808,600678,604996,619792,706082,706301,706492,707845,707846,707848,707851,707853,707860,707866,707868,707873,707877,707878,707884,806240,807790,819355,917108,1088166,2002493,2002522,2002523,2002867,2002961,2002966,2002979,2003035,2003080,2003096,2003100,2003113,2003133,2003180,2003271,2003352,2003384,2003716,2003735,2003743,2003746,2003748,2003751,2003794,2003796,2003803,2003827,2003831,2004023,2004147,2004495,2004544,2004695,2004723,2004772,2004778,2004803,2004807,2004809,2004936,2004944,2004946,2004951,2004982,2005155,2005160,2005165,2005192,2005233,2005291,2005347,2005351,2005418,2005420,2005427,2005435,2005446,2005468,2005474,2005481,2005487,2005527,2005556,2005591,2005612,2005622,2005719,2005910,2019413,2019414,2019415,2019416,2019435,2019442,2019822,2019842,2081168,2101930,2101952,2108624,2116044,2116368,2116666,2117520,2117858,2118238,2129660,2131758,2132684,4082260,4082268,4082288,4082304,4082354,4082370,4082392,4082404,4082500,4082546,4082574,4082582,4082664,4082680,4082716,4082728,4082742,4082760,4082786,4082800,4082854,4082928,4082980,4083046,4083054,4083106,4083116,4083118,4083124,4083182,4083216,4083288,4083346,4083430,4083434,4083442,4083476,4083512,4083534,4083570,4083688,4083710,4083744,4083806,4083836,4083964,4084070,4084078,4084158,4084262,4084426,4084454,4084482,4084532,4084610,4084636,4084660,4084664,4084676,4084722,4084724,4096276,4096280,6080866,6080872,6080874,6080892,6080896,6080898,6080912,6080916,10088138,10088152,20022451,20027081,20028831,20029471,20030591,20030751,20036032,20040072,20050212,20067936,20070676,20071316,40101900,40101904,40105616,40105624,40105644,40105652,40105656,40111434,40117854,40126618,80119812,80129622,200110920,200110980,200111080,200115776,200116090,200118876";
        String[] arrStr = strTemp.split(",");
        for (String s : arrStr) {
            String url = "http://test-novel-web.coohua.top//book/detail?bookId=" + s;
            String response = HttpClient.doGet(url);
            // System.out.println(response);
            JSONObject jsonObject = JSONObject.parseObject(response);
            //System.out.println(JSONPath.eval(jsonObject,"$.data"));
            // System.out.println(JSONPath.eval(jsonObject,"$.code"));

            // Integer code = jsonObject.getInteger("code");
            // String msg = jsonObject.getString("msg");
            //System.out.println("code = " + code);
            //System.out.println("msg = " + msg);
            if (!JSONPath.eval(jsonObject, "$.code").toString().equalsIgnoreCase("0")) {
                System.out.println("response = " + response);
                System.out.println("bookId=" + s);
                System.out.println("----------------");
            }
        }

    }

    @Test
    public void test52() {
        int max = Integer.MAX_VALUE;
        System.out.println(max);
        System.out.println(max / (86400));
    }

    @Test
    public void test51() {
        hash("79384343ff");
        System.out.println();
        hash("wewie2222222rlkdmfldmf");
        System.out.println();
        hash("880560956");

    }

    static final int hash(Object key) {
        int h;
        int r;
        r = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        System.out.println(key.hashCode());
        System.out.println(r);
        System.out.println(key.hashCode() >>> 16);
        System.out.println(Integer.toBinaryString(key.hashCode()));
        System.out.println(Integer.toBinaryString(key.hashCode() >>> 16));
        return r;
    }

    @Test
    public void test50() {
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setName("aa");
        person.setAge(20);

        personList.add(person);

        List<Person> newList = new ArrayList<>();
        for (Person p : personList) {
            newList.add(p);
        }

        Person pp = newList.get(0);
        pp.setName("bb");
        System.out.println();
    }

    @Test
    public void tet49() {
        Map map = new HashMap();
        System.out.println(map == null);
        System.out.println(map.isEmpty());
    }

    @Test
    public void test48() {
        HashMap hm = new HashMap();
        hm.put("a", "aaaa");
        System.out.println(null == hm);
        System.out.println(hm.size());
        System.out.println(hm.isEmpty());
    }

    @Test
    public void test47() {
        double d1 = 2.4;
        System.out.println(d1);
        int i1 = (int) (d1 * 100);
        System.out.println(i1);
    }

    @Test
    public void test46() {
        Double d1 = 40.11;
        Double d2 = 40.12;
        Double d3 = 40.13;
        Double d4 = 40.14;
        Double d5 = 40.15;
        Double d6 = 40.16;
        Double d7 = 40.17;
        Double d8 = 40.18;
        Double d9 = 40.19;
        Double d10 = 40.39;
        int aa1 = formatDoubleTOInt(d1, 100);
        int aa2 = formatDoubleTOInt(d2, 100);
        int aa3 = formatDoubleTOInt(d3, 100);
        int aa4 = formatDoubleTOInt(d4, 100);
        int aa5 = formatDoubleTOInt(d5, 100);
        int aa6 = formatDoubleTOInt(d6, 100);
        int aa7 = formatDoubleTOInt(d7, 100);
        int aa8 = formatDoubleTOInt(d8, 100);
        int aa9 = formatDoubleTOInt(d9, 100);
        int aa10 = formatDoubleTOInt(d10, 100);
        System.out.println(aa1);
        System.out.println(aa2);
        System.out.println(aa3);
        System.out.println(aa4);
        System.out.println(aa5);
        System.out.println(aa6);
        System.out.println(aa7);
        System.out.println(aa8);
        System.out.println(aa9);
        System.out.println(aa10);
    }

    @Test
    public void test45() {
        Double d1 = 40.11;
        Double d2 = 40.12;
        Double d3 = 40.13;
        Double d4 = 40.14;
        Double d5 = 40.15;
        Double d6 = 40.16;
        Double d7 = 40.17;
        Double d8 = 40.18;
        Double d9 = 40.19;
        Double d10 = 40.39;
        int aa1 = (int) (d1 * 100);
        int aa2 = (int) (d2 * 100);
        int aa3 = (int) (d3 * 100);
        int aa4 = (int) (d4 * 100);
        int aa5 = (int) (d5 * 100);
        int aa6 = (int) (d6 * 100);
        int aa7 = (int) (d7 * 100);
        int aa8 = (int) (d8 * 100);
        int aa9 = (int) (d9 * 100);
        int aa10 = (int) (d10 * 100);
        System.out.println(aa1);
        System.out.println(aa2);
        System.out.println(aa3);
        System.out.println(aa4);
        System.out.println(aa5);
        System.out.println(aa6);
        System.out.println(aa7);
        System.out.println(aa8);
        System.out.println(aa9);
        System.out.println(aa10);
    }

    @Test
    public void test44() {
        double num1 = 4.14;
        int num2 = (int) (num1 * 100);
        System.out.println(num2);
        int num3 = formatDoubleTOInt(num1, 100);
        System.out.println(num3);
    }

    @Test
    public void test43() {
        double num1 = 49.99;
        int num2 = (int) (num1 * 100);
        System.out.println(num2);
    }

    @Test
    public void test42() {
        Double d1 = 4515.21;
        Double d2 = 4515.22;
        Double d3 = 4515.23;
        Double d4 = 4515.24;
        Double d5 = 4515.25;
        Double d6 = 4515.26;
        Double d7 = 4515.27;
        Double d8 = 4515.28;
        Double d9 = 4515.29;
        Double d10 = 4515.39;
        int aa1 = formatDoubleTOInt(d1, 100);
        int aa2 = formatDoubleTOInt(d2, 100);
        int aa3 = formatDoubleTOInt(d3, 100);
        int aa4 = formatDoubleTOInt(d4, 100);
        int aa5 = formatDoubleTOInt(d5, 100);
        int aa6 = formatDoubleTOInt(d6, 100);
        int aa7 = formatDoubleTOInt(d7, 100);
        int aa8 = formatDoubleTOInt(d8, 100);
        int aa9 = formatDoubleTOInt(d9, 100);
        int aa10 = formatDoubleTOInt(d10, 100);
        System.out.println(aa1);
        System.out.println(aa2);
        System.out.println(aa3);
        System.out.println(aa4);
        System.out.println(aa5);
        System.out.println(aa6);
        System.out.println(aa7);
        System.out.println(aa8);
        System.out.println(aa9);
        System.out.println(aa10);
    }

    @Test
    public void test41() {
        Double d1 = 4515.21;
        Double d2 = 4515.22;
        Double d3 = 4515.23;
        Double d4 = 4515.24;
        Double d5 = 4515.25;
        Double d6 = 4515.26;
        Double d7 = 4515.27;
        Double d8 = 4515.28;
        Double d9 = 4515.29;
        Double d10 = 4515.39;
        int aa1 = (int) (d1 * 100);
        int aa2 = (int) (d2 * 100);
        int aa3 = (int) (d3 * 100);
        int aa4 = (int) (d4 * 100);
        int aa5 = (int) (d5 * 100);
        int aa6 = (int) (d6 * 100);
        int aa7 = (int) (d7 * 100);
        int aa8 = (int) (d8 * 100);
        int aa9 = (int) (d9 * 100);
        int aa10 = (int) (d10 * 100);
        System.out.println(aa1);
        System.out.println(aa2);
        System.out.println(aa3);
        System.out.println(aa4);
        System.out.println(aa5);
        System.out.println(aa6);
        System.out.println(aa7);
        System.out.println(aa8);
        System.out.println(aa9);
        System.out.println(aa10);
    }

    @Test
    public void test40() {
        Double d1 = 49.91;
        Double d2 = 49.92;
        Double d3 = 49.93;
        Double d4 = 49.94;
        Double d5 = 49.95;
        Double d6 = 49.96;
        Double d7 = 49.97;
        Double d8 = 49.98;
        Double d9 = 49.99;
        Double d10 = 49.00;
        int aa1 = (int) (d1 * 100);
        int aa2 = (int) (d2 * 100);
        int aa3 = (int) (d3 * 100);
        int aa4 = (int) (d4 * 100);
        int aa5 = (int) (d5 * 100);
        int aa6 = (int) (d6 * 100);
        int aa7 = (int) (d7 * 100);
        int aa8 = (int) (d8 * 100);
        int aa9 = (int) (d9 * 100);
        int aa10 = (int) (d10 * 100);
        System.out.println(aa1);
        System.out.println(aa2);
        System.out.println(aa3);
        System.out.println(aa4);
        System.out.println(aa5);
        System.out.println(aa6);
        System.out.println(aa7);
        System.out.println(aa8);
        System.out.println(aa9);
        System.out.println(aa10);
    }

    @Test
    public void test39() {
        Double d1 = 4515.23;
        Double d2 = 49.97;
        Double d3 = 49.98;
        Double d4 = 49.99;
        int a1 = formatDoubleTOInt(d1, 100);
        System.out.println(d1);
        System.out.println(a1);
        System.out.println("-----------------");
        int aa1 = (int) (d1 * 100);
        int aa2 = (int) (d2 * 100);
        int aa3 = (int) (d3 * 100);
        int aa4 = (int) (d4 * 100);
        System.out.println(aa1);
        System.out.println(aa2);
        System.out.println(aa3);
        System.out.println(aa4);

    }

    public static int formatDoubleTOInt(double douNum, int rate) {
        BigDecimal big1 = new BigDecimal(Double.valueOf(douNum)).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal big2 = new BigDecimal(rate);
        return big1.multiply(big2).intValue();
    }

    @Test
    public void test38() {
        BigDecimal decimal = new BigDecimal(new Double(4515.23));
        BigDecimal decimal1 = decimal.multiply(new BigDecimal(100));
        System.out.println(decimal1);
    }

    @Test
    public void test37() {
        Double d1 = 65.51;
        Double d2 = 65.53;
        Double d3 = 65.55;
        Double d4 = 65.57;
        Double d5 = 65.59;
        Double d6 = 66.01;
        double d7 = 4515.23;

        int i1 = (int) (d1 * 100);
        int i2 = (int) (d2 * 100);
        int i3 = (int) (d3 * 100);
        int i4 = (int) (d4 * 100);
        int i5 = (int) (d5 * 100);
        int i6 = (int) (d6 * 100);
        int i7 = (int) (d7 * 100);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);
        System.out.println(i5);
        System.out.println(i6);
        System.out.println(i7);
    }

    @Test
    public void test36() {
        String linkUrlTemp = "douyue://novel_home?page=bookDetail&bookId=10088152";
        int equalLength = linkUrlTemp.lastIndexOf("=");
        String start = linkUrlTemp.substring(0, equalLength);
        String end = linkUrlTemp.substring(equalLength + 1);
        Integer bookId = 666;
        String linkUrlResult = start + "=" + bookId;
        System.out.println("linkUrlTemp:" + linkUrlTemp);
        System.out.println("linkUrlResult:" + linkUrlResult);
        System.out.println("end:" + end);
    }

    @Test
    public void test35() {
        String str = "a,b.c,dedf，gh";
        String[] strArr = str.split("[,.，]");
        System.out.println(strArr.length);
        for (String s : strArr) {
            System.out.println(s);
        }

    }

    @Test
    public void test34() {
        // 以 空白字符# 开头的
        Pattern pattern = Pattern.compile("^\\s*#.*");


        System.out.println(pattern.matcher("aaa").matches());
        System.out.println(pattern.matcher(" a").matches());
        System.out.println(pattern.matcher("  aaa").matches());
        System.out.println();
        System.out.println(pattern.matcher("#aaa").matches());
        System.out.println(pattern.matcher("##aaa").matches());
        System.out.println(pattern.matcher("###aaa").matches());
        System.out.println(pattern.matcher(" #aaa").matches());
        System.out.println(pattern.matcher(" ##aaa").matches());
        System.out.println(pattern.matcher(" ###aaa").matches());
        System.out.println(pattern.matcher("    #aaa").matches());

    }

    @Test
    public void test33() {
        // 要验证的字符串
        String str = "service@xsoftlab.net";
        // 邮箱验证规则
        String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);
    }

    @Test
    public void test32() {
        String string = "张三，李四,王；五,赵；六，111,222,222,333，孙七，理论,时;间,我们，中;国，时间";
        String[] authors = string.split("[,，]");
        for (int i = 0; i < authors.length; i++) {
            System.out.println(authors[i]);
        }
    }

    @Test
    public void test31() {
        String str = null;
        System.out.println(JSON.toJSONString(str));
        System.out.println("ss");
    }

    @Test
    public void test30() {
        int a = 100_000;
        int b = 100000;
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void test29() {
        String str = "abc";
        System.out.println(str.charAt(0) * 1);
        System.out.println(str.charAt(1) + 0);
        System.out.println(str.charAt(2));
    }

    @Test
    public void test28() {
        boolean isShow = true;
        isShow = isShow && false;
        System.out.println(isShow);
    }

    @Test
    public void test27() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(TestDemo.createRandomOneToEnd(10));
        }

    }

    public static int createRandomOneToEnd(int end) {
        return new Random().nextInt(end) + 1;
    }


    @Test
    public void test26() {
        System.out.println(1 << 4);
        System.out.println(Math.pow(2, 4));
        System.out.println(32 >>> 1);
    }

    @Test
    public void testJsonPath() {
        String jsonStr = "{\"code\" : 0,\"message\" : \"aaaaa\",\"result\" : {\"pushId\" : 2972}}";
        JSONPath path = JSONPath.compile("$.result.pushId");
        System.out.println(JSONPath.read(jsonStr, "$.result.pushId"));
        System.out.println(JSONPath.extract(jsonStr, "$.result.pushId"));
        Object o1 = JSONPath.read(jsonStr, "$.result.pushId");
        long longValue1 = NumberUtils.toLong(o1.toString());
        System.out.println(longValue1);


    }

    @Test
    public void test25() {
        String str = "{\"code\" : 0,\"message\" : \"aaaaa\",\"result\" : {\"pushId\" : 2972}}";
        JSONObject json = JSONObject.parseObject(str);
        System.out.println(json.getIntValue("code"));
        System.out.println(json.getString("message"));
        System.out.println(json.getJSONObject("result"));
        System.out.println(json.getJSONObject("result").get("pushId"));
        Long pushId = json.getJSONObject("result").getLong("pushId");
        System.out.println(pushId);
    }

    @Test
    public void test24() {
        char[] arrayChar = {'w', 'a', 'd'};
        int[] arrayInt = {1, 2, 3, 4, 5};
        int[] a = new int[]{3, 4, 2};
        System.out.println(arrayChar);
    }

    @Test
    public void test23() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 300; i++) {
            Person p = new Person();
            if (!list.contains(p.hashCode()))
                list.add(p.hashCode());
        }
        System.out.println(list.size());
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    @Test
    public void test22() {
        Object obj = new Object();
        System.out.println(obj);
        System.out.println(obj.hashCode());
        System.out.println(System.identityHashCode(obj));
    }

    @Test
    public void test21() {
        String s = "aaaaaaaa";
        System.out.println(s);
        System.out.println(System.identityHashCode(s));
    }

    @Test
    public void test20() {
        Integer num = 10;
        System.out.println(10);
        incrA(num);
        System.out.println(num);
    }

    public static void incrA(Integer a) {
        a += 2;
        System.out.println("incrA a = " + a);
    }

    @Test
    public void test19() {
        String str = "abcdefg      123456";
        System.out.println(str.charAt(3));
    }

    @Test
    public void test18() {
        int a = 100;
        int b = 100;
        System.out.println(a == b);
        System.out.println("--------------");
        int m = 500;
        int n = 500;
        System.out.println(m == n);

    }

    @Test
    public void test17() {
        Integer a = 100;
        Integer b = 100;
        System.out.println(a == b);
        System.out.println(a.intValue() == b.intValue());
        System.out.println("--------------");
        Integer m = 500;
        Integer n = 500;
        System.out.println(m == n);
        System.out.println(m.intValue() == n.intValue());

    }

    @Test
    public void test16() {
        Map map = new HashMap();
        map.put(51, 0);
        map.put(52, 5);
        map.put(53, 10);
        map.put(54, 10);
        map.put(55, 15);
        System.out.println(JSONObject.toJSONString(map));
        // {51:0,52:5,53:10,54:10,55:15}
    }

    @Test
    public void test15() {
        Map map = new HashMap();
        map.put(11, 51);
        map.put(22, 52);
        map.put(33, 53);
        map.put(44, 54);
        map.put(55, 55);
        map.put(66, 56);
        map.put(77, 57);
        System.out.println(JSONObject.toJSONString(map));

    }

    @Test
    public void test14() {
        String str = "{33:53,66:56,22:52,55:55,11:51,44:54,77:57}";
        //第一种方式
        Map maps = (Map) JSON.parse(str);
        System.out.println(maps);
        int taskId = (int) maps.get(44);
        System.out.println(taskId);
        System.out.println(JSONObject.toJSONString(maps));
    }

    @Test
    public void test13() {
        String ipStr = "223.201.0.0";
        String[] arr = ipStr.split("\\.");
        for (String s : arr) {
            System.out.println(s);
        }
    }

    @Test
    public void test12() {
        Set<Integer> sets = new HashSet<>();
        sets.add(2);
        sets.add(4);
        sets.add(8);
        System.out.println(JSONObject.toJSONString(sets));
        String arr = "[2,4,8]";
        Set<Integer> at = JSONObject.parseObject(arr, new TypeReference<Set<Integer>>() {
        });
        System.out.println(at);
    }

    @Test
    public void test11() {
        Person person = new Person();
        person.setAge(22);
        person.setName("aaa");
        log(person);
    }

    void log(Object object) {
        System.out.println(JSONObject.toJSONString(object));
    }

    @Test
    public void test10() {
        Integer a = 200;
        int b = 200;
        System.out.println(a == b);
    }

    @Test
    public void test9() {
        Integer num = 0;
        add(num);
        System.out.println(num);
    }

    void add(Integer num) {
        num++;
        System.out.println("add===" + num);
    }

    @Test
    public void test8() {
        List<String> discipleList = new ArrayList<>();
        discipleList.add("3333");
        discipleList.add("4444");
        discipleList.add("5555");
        System.out.println("==" + discipleList.contains(String.valueOf(3333)));
    }

    @Test
    public void test7() {
        int count = 2;
        double discount = 0.7;

        System.out.println(Math.ceil(count * discount));

    }

    @Test
    public void test6() {
        String mobile = "      14523444     ";
        System.out.println(mobile);
        System.out.println(mobile.trim());

    }

    @Test
    public void test5() {
        String str = "边阳旭禽鸿熙靖嘉德雷巧荷房凝雁兰高洁涂清霁戚昊天长建章考凌柏错芷天公西秀婉占嘉懿暴小蕊古涵桃寇紫桐是曼冬依慧巧管骞北硕浩漫夏乐水鄂春竹鄢元甲汪思菱富茵茵乔晗蕾清暄莹姬思嫒桐经艺业凝蝶冀文彬卫清涵蔡恬欣板夏彤聂念真荀和泽秉夏侯高峰幸敏思浦旭炎御念柏徭烨煜门含云盘素华似小翠公良宛亦易槐洪蔓菁钟离以彤让和同乘盈秀逯惜玉吉夏真袭姝丽晴斛雪柳弘绿蕊贾书琴宾佟佳顺慈杞冬雪皋莹琇袁灵波奈淑华索阳曦穰谷芹督正志回飞龙皇含灵佛天和竭浩岚镜向槐仆幻玉符凌兰徐访梦丘旭尧茹朝雨原灿灿充思佳敖安莲廖秋白罗觅儿邝梦泽裔翰学素白易枚颖然巢振凯委莉莉艾水蓉曲飞珍老思茵呼梅红速白枫寒莉莉茅成化红冰薇接海白郝盼夏常天玉封舒荣谷依玉郜依辰玉昂熙森悦爱邹平凡类玉石容玟丽谈绮梦梅娴静悟诗珊项语蝶嬴诗粘英慧衣端雅撒安妮叔华乐彭尔云伍晴画帖天纵圣雅容干春岚";
        char[] c = str.toCharArray();
        System.out.println(c.length);
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i] + ",");
        }
    }

    @Test
    public void test4() {
        for (int i = 0; i <= 100; i++) {
            System.out.println((int) Math.random() * 3);
        }
    }

    @Test
    public void test3() {
        Integer c1 = 600;
        Integer c2 = 600;
        System.out.println(c1.toString());
        System.out.println(c2.toString());
        System.out.println(c1 == c2);
        System.out.println(c1.equals(c2));

    }

    @Test
    public void testInteger() {
        Integer a1 = -120;
        Integer a2 = -120;
        System.out.println(a1 == a2); // true
        System.out.println(a1.equals(a2));// true
        Integer b1 = 100;
        Integer b2 = 100;
        System.out.println(b1 == b2);// true
        System.out.println(b1.equals(b2));//true

        Integer c1 = 600;
        Integer c2 = 600;
        System.out.println(c1 == c2); //false
        System.out.println(c1.equals(c2));//true

        int d1 = 800;
        int d2 = 800;
        System.out.println(d1 == d2);//true
        //System.out.println(d1.equals(d2));
    }

    @Test
    public void test1() {
        String str = "abbbb";
        char c = str.charAt(0);
        String later = str.substring(1);
        System.out.println(c);
        System.out.println(later);
    }

    @Test
    public void testType() {
        Long a1 = 33L;
        long a2 = Long.parseLong("21");

    }

    @Test
    public void testArrayList() {
        ArrayList<String> arrayLists = new ArrayList<>();
        arrayLists.add("aa");
        arrayLists.add("bb");
        arrayLists.add("cc");
        arrayLists.add("dd");
        System.out.println("arrayLists:" + arrayLists);

        List<String> lists = arrayLists.subList(1, 3);
        System.out.println("lists:" + lists.toString());
        lists.set(1, "ccccccccccc");
        System.out.println("lists:" + lists.toString());
        System.out.println("arrayLists:" + arrayLists);
        for (String s : arrayLists) {
            if ("dd".equals(s)) {
                arrayLists.remove(s);
            }
        }
    }
}
