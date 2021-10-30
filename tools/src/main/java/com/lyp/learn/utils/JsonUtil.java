package com.lyp.learn.utils;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TBase;

/**
 * @Description Json静态工具（基于jackson） 考虑到大多数使用场景，所以：反序列化方法会吞掉异常；序列化方法会抛出异常
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String DYNC_FILTER = "DYNC_FILTER";

    @JsonFilter(DYNC_FILTER)
    interface DynamicFilter{

    }

    static {
        //序列化时，跳过null属性
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //序列化时，遇到空bean（无属性）时不会失败
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //反序列化时，遇到未知属性（在bean上找不到对应属性）时不会失败
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //反序列化时，将空数组([])当做null来处理（以便把空数组反序列化到对象属性上——对php生成的json的map属性很有用）
        MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        //不通过fields来探测（仅通过标准getter探测）
        MAPPER.configure(MapperFeature.AUTO_DETECT_FIELDS, false);
        //如果是thrift实体，则序列化时，忽略以set开头或以Iterator结尾的属性（这些属性是thrift自动生成的)
        SimpleBeanPropertyFilter newFilter = new SimpleBeanPropertyFilter() {
            @Override
            public void serializeAsField(
                    Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
                if (!writer.getName().startsWith("set") && !writer.getName().endsWith("Iterator")) {
                    writer.serializeAsField(pojo, jgen, provider);
                }
            }
        };
        MAPPER.setFilterProvider(new SimpleFilterProvider().addFilter(DYNC_FILTER, newFilter));
        MAPPER.addMixIn(TBase.class, DynamicFilter.class);
        //日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MAPPER.setDateFormat(sdf);
    }

    public static ObjectNode createNode() {
        return MAPPER.createObjectNode();
    }

    /* ====================== 反序列化工具 ==================== */

    /**
     * Json串转为对象
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T parse(String json, TypeReference<T> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(String.format("parse failed. json=%s type=%s",json, type),e);
        }
    }

    /**
     * Json串转为对象
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T parse(String json, Class<T> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(String.format("parse failed. json=%s type=%s",json, type),e);
        }
    }

    /**
     * 输入流转为对象
     *
     * @param stream
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T parse(InputStream stream, TypeReference<T> type) {
        try {
            return MAPPER.readValue(stream, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 输入流转为对象
     *
     * @param stream
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T parse(InputStream stream, Class<T> type) {
        try {
            return MAPPER.readValue(stream, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* ====================== 序列化工具 ==================== */

    /**
     * 序列化对象转为json-string
     *
     * @param target
     * @return
     */
    public static String writeToString(Object target) {
        if (target == null){
            return null;
        }
        if (target.getClass() == String.class){
            return target.toString();
        }
        try {
            return MAPPER.writeValueAsString(target);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 序列化对象并写入Writer
     *
     * @param writer
     * @param target
     * @throws IOException
     */
    public static void write(Writer writer, Object target) throws IOException {
        MAPPER.writeValue(writer, target);
    }

    /**
     * 序列化对象并写入Stream
     *
     * @param stream
     * @param target
     * @throws IOException
     */
    public static void write(OutputStream stream, Object target) throws IOException {
        MAPPER.writeValue(stream, target);
    }

    /**
     * 将操作中心日志对象序列化为String
     ***/
    public static String logWriteToString(Object target) {
        if (target == null){
            return null;
        }
        if (target.getClass() == String.class){
            return target.toString();
        }
        try {
            String ans = MAPPER.writeValueAsString(target).replaceAll("[{}]", "");
            return ans.replace("\"", "");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象序列化为json字符串，自动去掉thrift实体自动生成的set**和**Iterator属性，同时格式化json字符串
     *
     * @param obj
     * @return
     */
    public static String jsonFormatWithPrettyFormat(Object obj) {
        try {
            if (obj == null) {
                return "";
            }
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception ex) {
            log.error("使用默认序列化器发生错误，降级使用自定义序列化器", ex);
            return writeToString(obj);
        }
    }

    /**
     * 获取aop参数的json，同时格式化json字符串
     *
     * @param obj
     * @return
     */
    public static String getAopParamJsonWithPrettyFormat(Object obj) {
        try {
            if (obj == null) {
                return "";
            }
            Object object = obj;
            if (obj instanceof String) {
                String param = obj.toString();
                if (StringUtils.isEmpty(param)) {
                    return param;
                }
                object = parse(param, Object.class);
            } else if (obj.getClass().isArray()) {
                Object[] array = (Object[]) obj;
                if (array.length == 1) {
                    object = array[0];
                }
            }
            return jsonFormatWithPrettyFormat(obj);
        } catch (Exception ex) {
            log.error("参数格式化错误", ex);
            return writeToString(obj);
        }
    }

    /**
     * 对象序列化为json字符串，自动去掉thrift实体自动生成的set**和**Iterator属性
     *
     * @param obj
     * @return
     */
    public static String jsonFormatNoPrettyFormat(Object obj) {
        try {
            if (obj == null) {
                return "";
            }
            return MAPPER.writeValueAsString(obj);
        } catch (Exception ex) {
            log.error("参数格式化错误", ex);
            return writeToString(obj);
        }
    }

    /**
     * 通过json获取map
     * @param json
     * @return
     */
    public static Map<String, String> getSharePoiSimpleNameMap(String json) {
        try {
            if (StringUtils.isBlank(json)) {
                return Maps.newHashMap();
            }
            Map<String, String> map = JsonUtil.parse(json, Map.class);
            return map;
        } catch (Exception e) {
            return Maps.newHashMap();
        }
    }

    /**
     * 对象 转为 json
     *
     * @param obj
     * @return
     * @throws IOException
     */
    public static String object2Json(Object obj) {
        if (obj == null) {
            return null;
        }
        Writer strWriter = new StringWriter();
        try {
            MAPPER.writeValue(strWriter, obj);
            return strWriter.toString();
        } catch (Exception e) {
            log.error("Java对象转为json失败: message={} obj={}", e.getMessage(), obj, e);
            return null;
        }
    }
}