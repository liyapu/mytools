package com.lyp.learn.utils;

import com.google.common.collect.Lists;
import com.lyp.learn.ex.GateWayException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description 公共转换类
 */
public class ConvertUtils {
    private static final String sharePoiName = "cangcang";
    private static final Pattern pattern = Pattern.compile(sharePoiName);
    private static final Pattern p = Pattern.compile("（");
    private static final Pattern p1 = Pattern.compile("仓");

    /**
     * 获取异常msg
     * @param ex 异常
     * @return {@link String}
     */
    public static String getMsgByException(Exception ex) {
        String msg = ex.getMessage();
        if (StringUtils.isEmpty(msg)) {
            try {
                Field[] fields = ex.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    if ("msg".equals(fields[i].getName())) {
                        fields[i].setAccessible(true);
                        Object obj = fields[i].get(ex);
                        msg = (obj == null ? "" : obj.toString());
                        break;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (StringUtils.isEmpty(msg)) {
            Throwable cause = ex.getCause();
            while (cause != null) {
                msg = cause.getMessage();
                if (!StringUtils.isEmpty(msg)) {
                    break;
                }
                cause = cause.getCause();
            }
        }
        if (StringUtils.isEmpty(msg)) {
            msg = ex.getClass().getName();
        }
        return msg;
    }

    /**
     * 异常转换
     * @param ex 异常
     * @return {@link GateWayException}
     */
    public static GateWayException wrapGateWayException(Exception ex) {
        if (ex instanceof GateWayException) {
            return (GateWayException) ex;
        } else if (ex.getCause() != null && ex.getCause() instanceof GateWayException) {
            return (GateWayException) ex.getCause();
        }
        return new GateWayException(getMsgByException(ex), ex);
    }



}
