package com.lyp.learn.guava.base;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-14 19:12
 *
 * CaseFormat 以提供不同 ASCII 字符格式之间的转换，比如，编程语言的命名规范
 *
 *      格式	                说明	                                范例
 * LOWER_CAMEL	        Java变量的命名规则（java变量命名规范）	    lowerCamel
 * LOWER_HYPHEN	        连字符连接变量的命名规则	                lower-hyphen
 * LOWER_UNDERSCORE 	C ++变量命名规则	                        lower_underscore
 *
 * UPPER_CAMEL	        Java和C++类的命名规则（java类名命名规范）	UpperCamel
 * UPPER_UNDERSCORE	    Java和C++常量的命名规则（java常量命名规范）	UPPER_UNDERSCORE
 *
 *
 *
修饰符和类型	                方法说明
Converter<String,String>	converterTo(CaseFormat targetFormat)
                            返回一个转换，从这个格式转换targetFormat格式.

String	                    to(CaseFormat format, String str)
                            转换指定类型字符串.

static CaseFormat	        valueOf(String name)
                            返回此类型具有指定名称的枚举常量.

static CaseFormat[]	        values()
                            返回一个包含该枚举类型的常量数组中的顺序被声明.
 */
public class CaseFormatTest {

    /**
     * converterTo 格式器转化为targetFormat格式
     * 返回一个格式化器，把 UPPER_CAMEL 转化为 LOWER_CAMEL
     * 格式化器可以多次重复使用
     */
    @Test
    public void testConverterTo1() {
        //大写驼峰 转化为 小写驼峰
        Converter<String, String> camelConverter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
        String temp = camelConverter.convert("InputTest");
        System.out.println(temp);

        System.out.println(camelConverter.convert("InputTestInputTest"));

    }


    /**
     * to 转换指定类型字符串
     * 把 已有字符串 转换成不同命名规则的名字
     */
    @Test
    public void testTo() {
        String input = "input-test";
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, input));
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_UNDERSCORE, input));
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, input));
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE,input));
    }

}
