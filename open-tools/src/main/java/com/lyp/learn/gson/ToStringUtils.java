package com.lyp.learn.gson;

import com.alibaba.fastjson.JSON;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Java toString 字符串转换为 json 字符串
 * https://blog.csdn.net/ChinaLiaoTian/article/details/129090944
 */
public class ToStringUtils {

    /**
     * toString格式反序列化
     */
    /**
     * 数字类型匹配（包括整形和浮点型） & 日期类型匹配 & 对象类型匹配 & ...
     */
    public static Pattern datePattern = Pattern.compile("^[a-zA-Z]{3} [a-zA-Z]{3} [0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} CST ((19|20)\\d{2})$");
    public static Pattern numPattern = Pattern.compile("^-?[0-9]+\\.?[0-9]*$");
    public static Pattern objectPattern = Pattern.compile("^[a-zA-Z0-9\\.]+\\(.+\\)$");
    public static Pattern listPattern = Pattern.compile("^\\[.*\\]$");
    public static Pattern mapPattern = Pattern.compile("^\\{.*\\}$");
    public static Pattern supperPattern = Pattern.compile("^super=[a-zA-Z0-9\\.]+\\(.+\\)$");
    public static final String NULL = "null";

    /**
     * toString -> json
     */
    public static String toJSONString(String toString) throws ParseException {
        return JSON.toJSONString(toMap(toString));
    }

    /**
     * toString -> object
     */
    public static <T> T toObject(String toString, Class<T> clazz) throws ParseException {
        return JSON.parseObject(toJSONString(toString), clazz);
    }

    /**
     * toString -> map
     */
    private static Map<String, Object> toMap(String toString) throws ParseException {
        if (StringUtils.isEmpty(toString = StringUtils.trim(toString))) {
            return toString == null ? null : new HashMap<>();
        }

        // 移除最外层"()"
        toString = StringUtils.substringAfter(toString, "(").trim();
        toString = StringUtils.substringBeforeLast(toString, ")").trim();

        String token;
        Map<String, Object> map = new HashMap<>();
        while (StringUtils.isNotEmpty(toString) && StringUtils.isNotEmpty(token = splitToken(toString))) {
            toString = StringUtils.removeStart(StringUtils.removeStart(toString, token).trim(), ",").trim();

            // 如果带"super="(lombok的@ToString(callSuper=true)引入)，按照当前层继续处理
            if (supperPattern.matcher(token).matches()) {
                token = token.substring(token.indexOf("(") + 1, token.length() - 1);
                toString = String.format("%s,%s", token, toString);
                continue;
            }

            Pair<String, String> keyValue = parseToken(token);
            map.put(keyValue.getKey(), buildTypeValue(keyValue.getKey(), keyValue.getValue()));
        }
        return map;
    }

    static Pair<String, String> parseToken(String token) {
        assert Objects.nonNull(token) && token.contains("=");
        int pos = token.indexOf("=");
        return new javafx.util.Pair<>(token.substring(0, pos), token.substring(pos + 1));
    }

    /**
     * 单个token解析
     *
     * @param key 可根据key设置自定义序列化操作
     */
    private static Object buildTypeValue(String key, String value) throws ParseException {
        if (StringUtils.isEmpty(value)) {
            return null;
        } else if (value.equals(NULL)) {
            return null;
        }

        // 日期类型
        if (datePattern.matcher(value).matches()) {
            return new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", new Locale("us")).parse(value).getTime();
        }
        // 数字类型
        if (numPattern.matcher(value).matches()) {
            return value;
        }
        // 集合类型
        if (listPattern.matcher(value).matches()) {
            return buildListValue(value);
        }
        // map类型
        if (mapPattern.matcher(value).matches()) {
            return buildMapValue(value);
        }
        // 对象类型
        if (objectPattern.matcher(value).matches()) {
            return toMap(value);
        }

        // 其他都认为是string类型
        return value;
    }

    /**
     * 集合类型
     */
    private static Object buildListValue(String value) throws ParseException {
        List<Object> result = new ArrayList<>();

        value = value.substring(1, value.length() - 1).trim();
        if (StringUtils.isEmpty(value)) {
            return result;
        }

        String token = null;
        while (StringUtils.isNotBlank(value) && StringUtils.isNotBlank(token = splitToken(value))) {
            result.add(buildTypeValue(null, token));
            value = StringUtils.removeStart(StringUtils.removeStart(value, token).trim(), ",").trim();
        }

        return result;
    }

    static String splitToken(String toString) {
        if (StringUtils.isBlank(toString)) {
            return toString;
        }

        int bracketNum = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < toString.length(); i++) {
            Character c = toString.charAt(i);
            if (tokenMap.containsValue(c)) {
                stack.push(c);
            } else if (tokenMap.containsKey(c) && Objects.equals(stack.peek(), tokenMap.get(c))) {
                stack.pop();
            } else if ((c == ',') && stack.isEmpty()) {
                return toString.substring(0, i);
            }
        }
        if (stack.isEmpty()) {
            return toString;
        }
        throw new RuntimeException("splitFirstToken error, bracketNum=" + bracketNum + ", toString=" + toString);
    }

    /**
     * 获取第一个token，注意: toString不再包括最外层的()
     */
    private final static Map<Character, Character> tokenMap = new HashMap<>();

    static {
        tokenMap.put(')', '(');
        tokenMap.put('}', '{');
        tokenMap.put(']', '[');
    }


    /**
     * map类型
     */
    private static Map<Object, Object> buildMapValue(String value) throws ParseException {
        Map<Object, Object> result = new HashMap<>();
        value = value.substring(1, value.length() - 1).trim();
        if (StringUtils.isEmpty(value)) {
            return result;
        }

        String token = null;
        while (StringUtils.isNotEmpty(token = splitToken(value))) {
            Pair<String, String> keyValue = parseToken(token);
            result.put(buildTypeValue(keyValue.getKey(), keyValue.getKey()), buildTypeValue(keyValue.getKey(), keyValue.getValue()));

            value = StringUtils.removeStart(StringUtils.removeStart(value, token).trim(), ",").trim();
        }

        return result;
    }
}
