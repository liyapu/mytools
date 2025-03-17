package com.lyp.learn.tools;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.function.Function;

public class ExcelValueParseUtil {


    public static Function<String, Integer> parseInteger = (str) -> {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    };

    public static Function<String, List<Integer>> parseIntegerList = (str) -> {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return Lists.newArrayList(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            return null;
        }
    };

    public static Function<String, Long> parseLong = (str) -> {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return null;
        }
    };

    public static Function<String, List<Long>> parseLongList = (str) -> {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return Lists.newArrayList(Long.parseLong(str));
        } catch (NumberFormatException e) {
            return null;
        }
    };

}
