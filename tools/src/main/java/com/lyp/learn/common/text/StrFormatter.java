package com.lyp.learn.common.text;


import com.lyp.learn.common.StrUtil;

import java.util.Objects;

/**
 * StrFormatter.java
 *
 * @author at 2023/12/22 23:46
 */
public class StrFormatter {

    /**
     * 格式化字符串<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") =》 this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") =》 this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") =》 this is \a for b<br>
     *
     * @param strPattern 字符串模板
     * @param argArray   参数列表
     * @return 结果
     */
    public static String format(final String strPattern, final Object... argArray) {
        if (StrUtil.isBlank(strPattern) || Objects.isNull(argArray) || argArray.length == 0) {
            return strPattern;
        }
        final int strPatternLength = strPattern.length();

        // 初始化定义好的长度以获得更好的性能
        StringBuilder stringBuilder = new StringBuilder(strPatternLength + 50);

        // 记录已经处理到的位置
        int handledPosition = 0;
        // 占位符所在位置
        int delimIndex;
        for (int argIndex = 0; argIndex < argArray.length; argIndex++) {
            delimIndex = strPattern.indexOf(StrUtil.EMPTY_JSON, handledPosition);
            // 剩余部分无占位符
            if (delimIndex == -1) {
                // 不带占位符的模板直接返回
                if (handledPosition == 0) {
                    return strPattern;
                }
                // 字符串模板剩余部分不再包含占位符，加入剩余部分后返回结果
                stringBuilder.append(strPattern, handledPosition, strPatternLength);
                return stringBuilder.toString();
            }

            // 转义符
            if (delimIndex > 0 && strPattern.charAt(delimIndex - 1) == StrUtil.C_BACKSLASH) {
                // 双转义符
                if (delimIndex > 1 && strPattern.charAt(delimIndex - 2) == StrUtil.C_BACKSLASH) {
                    // 转义符之前还有一个转义符，占位符依旧有效
                    stringBuilder.append(strPattern, handledPosition, delimIndex - 1);
                    stringBuilder.append(StrUtil.utf8Str(argArray[argIndex]));
                    handledPosition = delimIndex + 2;
                } else {
                    // 占位符被转义
                    argIndex--;
                    stringBuilder.append(strPattern, handledPosition, delimIndex - 1);
                    stringBuilder.append(StrUtil.C_DELIM_START);
                    handledPosition = delimIndex + 1;
                }
            } else {
                // 正常占位符
                stringBuilder.append(strPattern, handledPosition, delimIndex);
                stringBuilder.append(StrUtil.utf8Str(argArray[argIndex]));
                handledPosition = delimIndex + 2;
            }
        }

        // append the characters following the last {} pair.
        // 加入最后一个占位符后所有的字符
        stringBuilder.append(strPattern, handledPosition, strPattern.length());

        return stringBuilder.toString();
    }
}
