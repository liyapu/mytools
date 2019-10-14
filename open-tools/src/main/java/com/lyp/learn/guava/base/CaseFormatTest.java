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
 *      格式	                说明	                范例
 * LOWER_CAMEL	        Java变量的命名规则	    lowerCamel
 * LOWER_HYPHEN	        连字符连接变量的命名规则	lower-hyphen
 * LOWER_UNDERSCORE 	C ++变量命名规则	        lower_underscore
 * UPPER_CAMEL	        Java和C++类的命名规则	    UpperCamel
 * UPPER_UNDERSCORE	    Java和C++常量的命名规则	UPPER_UNDERSCORE
 */
public class CaseFormatTest {

    /**
     * converterTo 格式器转化为targetFormat格式
     */
    @Test
    public void testConverterTo() {
        Converter<String, String> camelConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);
        System.out.println(camelConverter.convert("input-camel"));  // INPUT-CAMEL
        System.out.println(camelConverter.convert("inputCamel"));  // INPUT_CAMEL
        System.out.println(camelConverter.convert("input_camel"));  // INPUT_CAMEL
        System.out.println(camelConverter.convert("InputCamel"));
    }

    /**
     * to 转换指定类型字符串
     */
    @Test
    public void testTo() {
        String input = "ting-feng";
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, input));      // tingFeng
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_UNDERSCORE, input)); // ting_feng
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, input));      // TingFeng
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE,input));  // TING_FENG
    }

}
