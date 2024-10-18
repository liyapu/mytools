package com.lyp.learn.utils.wrapper;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;


@Slf4j
public class GsonUtil {
    private static Gson gson = new Gson();

    public GsonUtil() {
    }

    public static String toJsonStr(Object object) {
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            log.error("GsonUtil.toJsonStr error", e);
        }
        return null;
    }

    public static String toJsonStr(Object obj, Type type) {
        try {
            return gson.toJson(obj, type);
        } catch (Exception e) {
            log.error("GsonUtil.toJsonStr error", e);
        }
        return null;
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (Exception e) {
            log.error("GsonUtil.fromJson error，json={}", json, e);
            return null;
        }
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }
}
