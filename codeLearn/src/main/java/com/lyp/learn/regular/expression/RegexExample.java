package com.lyp.learn.regular.expression;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: liyapu
 * @description: 正则表达式 测试类
 * @date 2019-09-09 15:35
 */
public class RegexExample {

    /**
     * + 号是特殊字符，想要匹配普通的+号，就要转义 \, \是个特殊的字符，想要输出\，就要转义 \,所以 \\+ ,就是匹配普通的 +
     */
    @Test
    public void test01() {
        System.out.println("-1234".matches("-?\\d+"));
        System.out.println("1234".matches("-?\\d+"));
        System.out.println("+1234".matches("-?\\d+"));
        System.out.println("+1234".matches("(-|\\+)?\\d+"));
    }

    /**
     * Pattern complie(String regex)
     * 由于Pattern的构造函数是私有的,不可以直接创建,
     * 所以通过静态方法compile(String regex)方法来创建,将给定的正则表达式编译并赋予给Pattern类
     *
     * String pattern() 返回正则表达式的字符串形式,其实就是返回Pattern.complile(String regex)的regex参数
     */
    @Test
    public void test02(){
        Pattern p1 = Pattern.compile("\\d+");
        System.out.println(p1.pattern());

        Pattern p2 = Pattern.compile("\\?|\\*");
        System.out.println(p2.pattern());
    }

    /**
     * 可以在Compile 方法中，指定一个特殊标志：
     * Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
     * Pattern 类包含多个标志(int 类型),这些标志可以控制Pattern 匹配模式的方式。上面代码中的标志使模式匹配是忽略大小写
     */
    @Test
    public void testCompile(){
        Pattern p1 = Pattern.compile("[a-z]*");
        Matcher m1 = p1.matcher("aabbcc");
        System.out.println(m1.matches());

        Matcher m2 = p1.matcher("aaBBcc");
        System.out.println(m2.matches());
    }

    @Test
    public void testCompile2(){
        Pattern p1 = Pattern.compile("[a-z]*",Pattern.CASE_INSENSITIVE);
        Matcher m1 = p1.matcher("aabbcc");
        System.out.println(m1.matches());

        Matcher m2 = p1.matcher("aaBBcc");
        System.out.println(m2.matches());
    }


    /**
     * 预编译的正则表达式，可以重用
     *
     */
    public boolean match(String line, String regExpress) {
        //编译正则表达式
        Pattern p = Pattern.compile(regExpress);
        //匹配
        Matcher m = p.matcher(line);
        //是否匹配
        boolean isMatch = m.matches();
        return isMatch;
    }

    /**
     * ? 零次或一次
     */
    @Test
    public void test03() {
        String regExpress = "fo?";
        System.out.println(match("f", regExpress));
        System.out.println(match("fo", regExpress));
        System.out.println(match("foo", regExpress));
        System.out.println(match("fooo", regExpress));
        System.out.println(match("foooo", regExpress));
    }

    /**
     * * 零次或多次
     */
    @Test
    public void test04() {
        String regExpress = "fo*";
        System.out.println(match("f", regExpress));
        System.out.println(match("fo", regExpress));
        System.out.println(match("foo", regExpress));
        System.out.println(match("fooo", regExpress));
        System.out.println(match("foooo", regExpress));
    }

    /**
     * + 一次或多次
     */
    @Test
    public void test05() {
        String regExpress = "fo+";
        System.out.println(match("f", regExpress));
        System.out.println(match("fo", regExpress));
        System.out.println(match("foo", regExpress));
        System.out.println(match("fooo", regExpress));
        System.out.println(match("foooo", regExpress));
    }

    /**
     * 另一种写法，一般用于只用一次的匹配的
     * 等价于match方法的(上面的三行写法)
     */
    @Test
    public void test06() {
        boolean isMatch = Pattern.matches("^a\\w*y$", "aabby");
        System.out.println(isMatch);
    }

    /**
     * matches() 对整个字符串进行匹配，只有整个字符串都匹配了才返回true
     */
    @Test
    public void test07() {
        Pattern p = Pattern.compile("\\d+");


        Matcher m1 = p.matcher("22bb33");
        System.out.println(m1.matches());// 返回false,因为bb不能被\d+匹配,导致整个字符串匹配未成功


        Matcher m2 = p.matcher("223344");
        System.out.println(m2.matches()); // 返回true,因为\d+匹配到了整个字符串
    }

    /**
     * lookingAt() 对前面的字符串进行匹配,只有匹配到的字符串在最前面才返回true
     */
    @Test
    public void test08() {
        Pattern p = Pattern.compile("\\d+");

        Matcher m1 = p.matcher("22bb33");
        System.out.println(m1.lookingAt());// 返回true,因为\d+匹配到了前面的22

        Matcher m2 = p.matcher("aa2233");
        System.out.println(m2.matches());// 返回false,因为\d+不能匹配前面的aa
    }


    /**
     * find()对字符串进行匹配，匹配到的字符串可以在任何位置
     */
    @Test
    public void test09() {
        Pattern p = Pattern.compile("\\d+");

        Matcher m1 = p.matcher("2233");
        System.out.println(m1.find());

        Matcher m2 = p.matcher("22aa33");
        System.out.println(m2.find());

        Matcher m3 = p.matcher("aa22bb33");
        System.out.println(m3.find());

        Matcher m4 = p.matcher("aabb");
        System.out.println(m4.find());
    }

    /**
     * 当使用matches()，lookingAt()，find()执行匹配操作后，就可以利用以上三个方法得到更详细的信息：
     * <p>
     * start()返回匹配到的子字符串的第一个字符在原字符串中的索引位置；
     * end()返回匹配到的子字符串的最后一个字符在原字符串中的索引位置；
     * group()返回匹配到的子字符串。
     */
    @Test
    public void test10() {
        Pattern p = Pattern.compile("\\d+");

        System.out.println("==========find 方法==========");
        Matcher m = p.matcher("aaa2223bb");
        System.out.println(m.find());// 匹配2223
        System.out.println(m.start());// 返回3
        System.out.println(m.end());// 返回7,返回的是2223后的索引号
        System.out.println(m.group());// 返回2223

        System.out.println("==========lookingAt 方法==========");
        m = p.matcher("2223bb");
        System.out.println(m.lookingAt()); // 匹配2223
        System.out.println(m.start()); // 返回0,由于lookingAt()只能匹配前面的字符串,所以当使用lookingAt()匹配时,start()方法总是返回0
        System.out.println(m.end()); // 返回4
        System.out.println(m.group()); // 返回2223

        System.out.println("==========matches 方法==========");
        m = p.matcher("2223bb");
        System.out.println(m.matches()); // 匹配整个字符串，返回false，所以后边的执行会报错
        System.out.println(m.start());
        System.out.println(m.end());
        System.out.println(m.group());
    }


    /**
     * find  循环输出
     */
    @Test
    public void test11() {
        Pattern p = Pattern.compile("\\d+");

        Matcher m1 = p.matcher("aa11bb22 cc333dd44");
        while (m1.find()) {
            System.out.println("开始位置：" + m1.start() + "\t结束位置:" + m1.end() + "\t 匹配字符串：" + m1.group());
        }
    }

    /**
     * start(int i)、end(int i)，group(int i)、groupCount()
     * start()，end()，group()均有一个重载方法，它们是start(int i)，end(int i)，group(int i)专用于分组操作，
     * Mathcer 类还有一个groupCount()用于返回有多少组。
     */
    @Test
    public void testFindStart() {
        Pattern p = Pattern.compile("([a-z]+)(\\d+)");
        Matcher m = p.matcher("aaa2223bb");

        System.out.println(m.find()); // 匹配aaa2223
        System.out.println(m.groupCount()); // 返回2,因为有2组
        System.out.println(m.start(1)); // 返回0 返回第一组匹配到的子字符串的第一个字符在原字符串中的索引号
        System.out.println(m.start(2)); // 返回3 返回第二组匹配到的子字符串的第一个字符在原字符串中的索引号
        System.out.println(m.end(1)); // 返回3 返回第一组匹配到的子字符串的最后一个字符在原字符串中的索引号
        System.out.println(m.end(2)); // 返回7
        System.out.println(m.group(1)); // 返回aaa,返回第一组匹配到的子字符串
        System.out.println(m.group(2)); // 返回2223,返回第二组匹配到的子字符串
    }

    @Test
    public void testFindStartEnd() {
        Pattern p = Pattern.compile("([a-z]+)(\\d+)");
        Matcher m1 = p.matcher("aaa2223bb");

        while (m1.find()) {
            int groupCount = m1.groupCount();
            System.out.println("groupCount = " + groupCount);
            for (int i = 0; i <= groupCount; i++) {
                System.out.println("i = " + i + "\t开始位置：" + m1.start(i) + "\t 结束位置：" + m1.end(i) + " \t 匹配字符 " + m1.group(i));
            }
        }
        System.out.println("======================");
        Matcher m2 = p.matcher("aaa2223bb444");

        while (m2.find()) {
            int groupCount = m2.groupCount();
            System.out.println("groupCount = " + groupCount);
            for (int i = 0; i <= groupCount; i++) {
                System.out.println("i = " + i + "\t开始位置：" + m2.start(i) + "\t 结束位置：" + m2.end(i) + " \t 匹配字符 " + m2.group(i));
            }
        }
    }

    @Test
    public void testFindFor() {
        String regEx = "(\\w+)%(\\d+)";
        String line = "ab%12-cd%34";

        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println("i = " + i + " \t" + matcher.group(i));
            }
            System.out.println();
        }
    }

    /**
     * 现在我们使用一下稍微高级点的正则匹配操作，
     * 例如有一段文本，里面有很多数字，而且这些数字是分开的，我们现在要将文本中所有数字都取出来。
     * 利用java的正则操作是那么的简单。
     */
    @Test
    public void testFindNum(){
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("我的QQ是:456456 我的电话是:0532214 我的邮箱是:aaa123@aaa.com");
        while (m.find()) {
            System.out.println(m.group());
        }

    }

    /**
     * 现在大家应该知道，每次执行匹配操作后start()，end()，group()三个方法的值都会改变,
     * 改变成匹配到的子字符串的信息，以及它们的重载方法，也会改变成相应的信息。
     *
     * 注意：只有当匹配操作成功，才可以使用start()，end()，group()三个方法，
     * 否则会抛出java.lang.IllegalStateException，
     * 也就是当matches()，lookingAt()，find()其中任意一个方法返回 true 时，才可以使用。
     */
    @Test
    public void testFindStatEnd(){
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("我的QQ是:456456 我的电话是:0532214 我的邮箱是:aaa123@aaa.com");
        while (m.find()) {
            System.out.println(m.group());
            System.out.print("start:" + m.start());
            System.out.println(" end:" + m.end());
        }

    }

    /**
     * String.split方法很常用，用于切割字符串，split传入的参数是正则表达式，它的内部是每次都comiple正则表达式，再调用Pattern.split方法：
     * 因此，如果你调用String.split非常频繁的话，每次都重新编译正则表达式的代价很高，性能会受到很大影响，
     *
     * 此时最好自己预编译Pattern,再调用Pattern.split方法为妙。
     * 比如下面的方法
     */
    @Test
    public void testSplit(){
        Pattern p = Pattern.compile("[,:;]");
        String[] arr = p.split("aa,23,gg:66;yy88;ee:ww,99");
        for(String a : arr){
            System.out.println(a);
        }
    }


    @Test
    public void testReplaceFirst(){
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("aa11 bb 1234ccd5ef -=88ddb");
        String str = m.replaceFirst("Y");
        System.out.println(str);
    }

    @Test
    public void testReplaceAll(){
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("aa11 bb 1234ccd5ef -=88ddb");
        String str = m.replaceAll("Y");
        System.out.println(str);
    }

    /**
     * [abc][vz]
     *
     * 复选集定义，匹配字母 a 或 b 或 c，后面跟着 v 或 z
     */
    @Test
    public void testKuoHao(){
        Pattern p = Pattern.compile("[abc][vz]");
        System.out.println(p.matcher("ab").matches());
        System.out.println(p.matcher("av").matches());
        System.out.println(p.matcher("avz").matches());
        System.out.println(p.matcher("1avz").matches());
        System.out.println(p.matcher("az").matches());
        System.out.println(p.matcher("ay").matches());
        System.out.println(p.matcher("bv").matches());
        System.out.println(p.matcher("abv").matches());
    }

    /**
     * [a-d1-7]
     *
     * 范围匹配，匹配字母 a 到 d 和数字从 1 到 7 之间，但不匹配 d1
     */
    @Test
    public void testKuoHao2(){
        Pattern p = Pattern.compile("[a-m1-5]");
        System.out.println(p.matcher("a").matches());
        System.out.println(p.matcher("b").matches());
        System.out.println(p.matcher("z").matches());
        System.out.println(p.matcher("1").matches());
        System.out.println(p.matcher("2").matches());
        System.out.println(p.matcher("8").matches());
        System.out.println(p.matcher("a3").matches());
    }

}
