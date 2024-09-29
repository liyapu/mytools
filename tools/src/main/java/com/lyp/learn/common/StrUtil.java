package com.lyp.learn.common;

import com.lyp.learn.common.text.StrFormatter;
import com.lyp.learn.common.text.StrPool;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

/**
 * StrUtil
 *
 * @author at 2023/12/4 20:25
 */
@SuppressWarnings("unused")
public final class StrUtil implements StrPool {

    private StrUtil() {
    }

    /**
     * 下划线换驼峰
     */
    public static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = false;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (currentChar == '_') {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(currentChar));
                    capitalizeNext = false;
                } else {
                    result.append(currentChar);
                }
            }
        }

        return result.toString();
    }


    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     * 空字符串 {@code ""}.
     *
     * @since 1.0.0
     */
    public static final String EMPTY = "";

    /**
     * UTF-8
     */
    public static final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;

    /**
     * 字符串常量：{@code "null"} <br>
     * 注意：{@code "null" != null}
     */
    public static final String NULL = "null";

    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") =》 this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") =》 this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") =》 this is \a for b<br>
     *
     * @param template 文本模板，被替换的部分用 {} 表示，如果模板为null，返回"null"
     * @param params   参数值
     * @return 格式化后的文本，如果模板为null，返回"null"
     * @since 1.0.0
     */
    public static String format(CharSequence template, Object... params) {
        if (null == template) {
            return NULL;
        }
        if (Objects.isNull(params) || params.length == 0 || isBlank(template)) {
            return template.toString();
        }
        return StrFormatter.format(template.toString(), params);
    }

    /**
     * 判断字符串是否为null或者为空或者为空格
     * <pre>
     * CharSequenceUtil.isBlank(null)      = true
     * CharSequenceUtil.isBlank("")        = true
     * CharSequenceUtil.isBlank(" ")       = true
     * CharSequenceUtil.isBlank("bob")     = false
     * CharSequenceUtil.isBlank("  bob  ") = false
     * </pre>
     *
     * @param cs 校验的字符换，可以为null
     * @return {@code true} 如果字符串为null或者为空或者为空白字符
     * @since 1.0.0
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否为null或者为空或者为空格
     * <pre>
     * CharSequenceUtil.isBlank(null)      = false
     * CharSequenceUtil.isBlank("")        = false
     * CharSequenceUtil.isBlank(" ")       = false
     * CharSequenceUtil.isBlank("bob")     = true
     * CharSequenceUtil.isBlank("  bob  ") = true
     * </pre>
     *
     * @param cs 校验的字符换，可以为null
     * @return {@code false} 如果字符串为null或者为空或者为空白字符
     * @since 1.0.0
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * 将对象转为字符串<br>
     *
     * <pre>
     * 1、Byte数组和ByteBuffer会被转换为对应字符串的数组
     * 2、对象数组会调用Arrays.toString方法
     * </pre>
     *
     * @param obj 对象
     * @return 字符串
     * @since 1.0.0
     */
    public static String utf8Str(Object obj) {
        return str(obj, CHARSET_UTF_8);
    }

    /**
     * 将对象转为字符串
     * <pre>
     * 	 1、Byte数组和ByteBuffer会被转换为对应字符串的数组
     * 	 2、对象数组会调用Arrays.toString方法
     * </pre>
     *
     * @param obj     对象
     * @param charset 字符集
     * @return 字符串
     */
    public static String str(Object obj, Charset charset) {
        if (null == obj) {
            return null;
        }

        if (obj instanceof String) {
            return (String) obj;
        } else if (obj instanceof byte[]) {
            return str((byte[]) obj, charset);
        } else if (obj instanceof Byte[]) {
            return str((Byte[]) obj, charset);
        } else if (obj instanceof ByteBuffer) {
            return str((ByteBuffer) obj, charset);
        } else if (obj.getClass().isArray()) {
            return Arrays.deepToString((Object[]) obj);
        }

        return obj.toString();
    }

    /**
     * 将byte数组转为字符串
     *
     * @param bytes   byte数组
     * @param charset 字符集
     * @return 字符串
     */
    public static String str(byte[] bytes, String charset) {
        return str(bytes, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    /**
     * 解码字节码
     *
     * @param data    字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 解码后的字符串
     */
    public static String str(byte[] data, Charset charset) {
        if (data == null) {
            return null;
        }

        if (null == charset) {
            return new String(data);
        }
        return new String(data, charset);
    }

    /**
     * 将Byte数组转为字符串
     *
     * @param bytes   byte数组
     * @param charset 字符集
     * @return 字符串
     */
    public static String str(Byte[] bytes, String charset) {
        return str(bytes, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    /**
     * 解码字节码
     *
     * @param data    字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 解码后的字符串
     * @since 1.0.0
     */
    public static String str(Byte[] data, Charset charset) {
        if (data == null) {
            return null;
        }

        byte[] bytes = new byte[data.length];
        Byte dataByte;
        for (int i = 0; i < data.length; i++) {
            dataByte = data[i];
            bytes[i] = (null == dataByte) ? -1 : dataByte;
        }

        return str(bytes, charset);
    }

    /**
     * 将编码的byteBuffer数据转换为字符串
     *
     * @param data    数据
     * @param charset 字符集，如果为空使用当前系统字符集
     * @return 字符串
     */
    public static String str(ByteBuffer data, String charset) {
        if (data == null) {
            return null;
        }
        if (isBlank(charset)) {
            return str(data, (Charset) null);
        }
        return str(data, Charset.forName(charset));
    }

    /**
     * 将编码的byteBuffer数据转换为字符串
     *
     * @param data    数据
     * @param charset 字符集，如果为空使用当前系统字符集
     * @return 字符串
     * @since 1.0.0
     */
    public static String str(ByteBuffer data, Charset charset) {
        if (null == charset) {
            charset = Charset.defaultCharset();
        }
        return charset.decode(data).toString();
    }

    /**
     * 判断字符串是否为null或者为非空
     * <pre>
     * StrUtil.isNotEmpty(null)      = false
     * StrUtil.isNotEmpty("")        = false
     * StrUtil.isNotEmpty(" ")       = true
     * StrUtil.isNotEmpty("bob")     = true
     * StrUtil.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param cs 字符串
     * @return true:字符串不为null并且长度不为0
     * @since 1.0.0
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 判断字符串是否为null或者为空
     * <pre>
     * StrUtil.isEmpty(null)      = true
     * StrUtil.isEmpty("")        = true
     * StrUtil.isEmpty(" ")       = false
     * StrUtil.isEmpty("bob")     = false
     * StrUtil.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param cs 字符串
     * @return true:字符串为null或者长度为0
     * @since 1.0.0
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * <p> 返回当前字符串 或者 如果当前字符串为 {@code null}, 返回空字符串 ("").</p>
     *
     * <pre>
     * StrUtil.defaultString(null)  = ""
     * StrUtil.defaultString("")    = ""
     * StrUtil.defaultString("bat") = "bat"
     * </pre>
     *
     * @param str 需要检查的字符串，有可能是null
     * @return 当前字符串 或者 如果当前字符串为 {@code null}, 返回空字符串 ("")
     * @see String#valueOf(Object)
     * @since 1.0.0
     */
    public static String defaultString(final String str) {
        return defaultString(str, EMPTY);
    }

    /**
     * <p>返回当前字符串 或者 如果当前字符串为 {@code null}, 返回 {@code defaultStr}.</p>
     *
     * <pre>
     * StrUtil.defaultString(null, "NULL")  = "NULL"
     * StrUtil.defaultString("", "NULL")    = ""
     * StrUtil.defaultString("bat", "NULL") = "bat"
     * </pre>
     *
     * @param str        需要检查的字符串，有可能是null
     * @param defaultStr 需要返回的默认字符串
     *                   如果给null,返回的可能为null
     * @return 当前字符串 或者 如果当前字符串为 {@code null}, 返回{@code defaultStr}
     * @see String#valueOf(Object)
     * @since 1.0.0
     */
    public static String defaultString(final String str, final String defaultStr) {
        return str == null ? defaultStr : str;
    }

    /**
     * 字符串按照某个特定字符进行分割
     * <pre>
     * StrUtil.split(null, *)         = null
     * StrUtil.split("", *)           = []
     * StrUtil.split("abc def", null) = ["abc", "def"]
     * StrUtil.split("abc def", " ")  = ["abc", "def"]
     * StrUtil.split("abc  def", " ") = ["abc", "def"]
     * StrUtil.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
     * </pre>
     *
     * @param str            需要进行分割的字符串
     * @param separatorChars 分隔符
     * @return 解析的字符串数组,  如果为null,即为null
     * @since 1.0.0
     */
    public static String[] split(final String str, final String separatorChars) {
        return splitWorker(str, separatorChars, -1, false);
    }

    /**
     * <p>Joins the elements of the provided varargs into a
     * single String containing the provided elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * {@code null} elements and separator are treated as empty Strings ("").</p>
     *
     * <pre>
     * StringUtils.joinWith(",", {"a", "b"})        = "a,b"
     * StringUtils.joinWith(",", {"a", "b",""})     = "a,b,"
     * StringUtils.joinWith(",", {"a", null, "b"})  = "a,,b"
     * StringUtils.joinWith(null, {"a", "b"})       = "ab"
     * </pre>
     *
     * @param separator the separator character to use, null treated as ""
     * @param objects   the varargs providing the values to join together. {@code null} elements are treated as ""
     * @return the joined String.
     * @throws IllegalArgumentException if a null varargs is provided
     * @since 1.0.0
     */
    public static String joinWith(final String separator, final Object... objects) {
        if (objects == null) {
            throw new IllegalArgumentException("Object varargs must not be null");
        }

        final String sanitizedSeparator = defaultString(separator);

        final StringBuilder result = new StringBuilder();

        final Iterator<Object> iterator = Arrays.asList(objects).iterator();
        while (iterator.hasNext()) {
            final String value = Objects.toString(iterator.next(), "");
            result.append(value);

            if (iterator.hasNext()) {
                result.append(sanitizedSeparator);
            }
        }

        return result.toString();
    }

    /**
     * 判断str是否匹配正则表达式
     *
     * @param str   待匹配字符串
     * @param regex 正则表达式
     * @return true:匹配  false:不匹配
     * @throws IllegalArgumentException 参数不能为空
     * @since 1.0.7
     */
    public static boolean isMatch(String str, String regex) {
        Assert.notNull(str, "str不能为空");
        Assert.notNull(regex, "regex不能为空");
        Assert.isTrue(StrUtil.isNotEmpty(str), () -> new IllegalArgumentException("str不能为空串"));
        Assert.isTrue(StrUtil.isNotEmpty(regex), () -> new IllegalArgumentException("regex不能为空串"));
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }

    @SuppressWarnings("SameParameterValue")
    private static String[] splitWorker(final String str, final String separatorChars,
                                        final int max, final boolean preserveAllTokens) {
        if (str == null) {
            return null;
        }
        final int len = str.length();
        if (len == 0) {
            return EMPTY_STRING_ARRAY;
        }

        final List<String> list = new ArrayList<>();

        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;

        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // Optimise 1 character case
            final char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }

        return list.toArray(new String[0]);
    }

    public static String trim(String coordinate) {
        return Option.ofNullable(coordinate).map(String::trim).orElse(EMPTY);
    }
}
