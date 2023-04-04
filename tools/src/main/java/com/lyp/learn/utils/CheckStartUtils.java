//package com.lyp.learn.utils;
//
//import com.facebook.swift.codec.ThriftField;
//import com.facebook.swift.codec.ThriftStruct;
//import com.facebook.swift.service.ThriftMethod;
//import com.facebook.swift.service.ThriftService;
//import com.lyp.learn.bean.BaseResponse;
//import java.beans.IntrospectionException;
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Properties;
//import java.util.Set;
//import java.util.stream.Collectors;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.ehcache.config.builders.ConfigurationBuilder;
//import org.reflections.Reflections;
//import org.reflections.scanners.MethodAnnotationsScanner;
//import org.reflections.scanners.ResourcesScanner;
//import org.reflections.scanners.SubTypesScanner;
//import org.reflections.util.ClasspathHelper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.PropertiesLoaderUtils;
//
///**
// * //校验thrift格式，校验失败，程序退出
// * List<Class<?>> whiteClassList = Lists.newArrayList(ReturnAddedServiceExportResponse.class);
// * CheckUtils.check("com.lyp..adservie.client", MccConfig.class, whiteClassList,BaseResponse.class, true);
// */
//@Slf4j
//public class CheckStartUtils {
//
//    private static Logger logger = LoggerFactory.getLogger(CheckUtils.class);
//    private static List<String> serviceSuffixList = Arrays.asList("ThriftService", "TService");
//
//    /**
//     * 校验thrift规范
//     * 校验mccconfig规范
//     * 校验mafka灰度链路配置
//     *
//     * @param clientPackagePath
//     * @param baseResponseClass
//     */
//    public static void checkByCustomerBaseResponse(String clientPackagePath, Class<?> baseResponseClass,
//        boolean checkServiceSuffix) {
//        check(clientPackagePath, null, new ArrayList<>(), baseResponseClass, checkServiceSuffix);
//    }
//
//    /**
//     * 校验thrift规范
//     * 校验mccconfig规范
//     * 校验mafka灰度链路配置
//     *
//     * @param clientPackagePath
//     * @param mccConfigClazz
//     */
//    public static void check(String clientPackagePath, Class mccConfigClazz) {
//        check(clientPackagePath, mccConfigClazz, new ArrayList<>(), BaseResponse.class, true);
//    }
//
//    /**
//     * 校验
//     *
//     * @param clientPackagePath
//     * @param mccConfigClazz
//     */
//    public static void check(String clientPackagePath, Class mccConfigClazz, List<Class<?>> whiteClassList) {
//        check(clientPackagePath, mccConfigClazz, whiteClassList, BaseResponse.class, true);
//    }
//
//    public static void check(String clientPackagePath, Class mccConfigClazz, List<Class<?>> whiteClassList,
//        Class<?> baseResponseClass, boolean checkServiceSuffix) {
//        if (!ProcessInfoUtil.getHostEnv().equals(HostEnv.TEST)) {
//            return;
//        }
//        checkThrift(clientPackagePath, whiteClassList, baseResponseClass, checkServiceSuffix);
//        checkMccConfigConverter(mccConfigClazz);
////        checkMafkaGrayAsSet();
//    }
//
//    /**
//     * 校验thrift
//     *
//     * @param clientPackagePath
//     */
//    private static void checkThrift(String clientPackagePath, List<Class<?>> whiteClassList,
//        Class<?> baseResponseClass, boolean checkServiceSuffix) {
//        if (StringUtils.isEmpty(clientPackagePath)) {
//            exitOnCheckFail("client包路径不能为空");
//        }
//
//        Reflections reflections = new Reflections((new ConfigurationBuilder())
//            .setUrls(ClasspathHelper.forPackage(clientPackagePath, new ClassLoader[0]))
//            .setScanners(new MethodAnnotationsScanner(),
//                new SubTypesScanner(false),
//                new ResourcesScanner()));
//
////        Set<Class<? extends Enum>> enumSet = reflections.getSubTypesOf(Enum.class);
////        if (!enumSet.isEmpty()) {
////            List<String> stringList = enumSet.stream().map(k -> k.getName()).collect(Collectors.toList());
////            exitOnCheckFail("不允许使用枚举:" + String.join(";", stringList));
////        }
//
//        Set<Class<?>> subClass = reflections.getSubTypesOf(Object.class);
//        for (Class<?> item : subClass) {
//            if (item.getName().endsWith("Builder")) {
//                continue;
//            }
//            //check枚举
////            if (item.isEnum()) {
////                exitOnCheckFail(item.getName() + "thrift中不允许使用枚举类型");
////            }
//            if (item.isInterface()) {
//                //校验接口
//                checkInterface(item, whiteClassList, baseResponseClass);
//            } else {
//                //校验类
//                checkClass(item);
//                //校验类的ThriftField序号是否合法
//                checkThriftFieldOrder(item);
//            }
//        }
//        if (checkServiceSuffix) {
//            String suffix1 = serviceSuffixList.get(0);
//            String suffix2 = serviceSuffixList.get(1);
//            List<String> interfaceOneList = subClass.stream()
//                .filter(k -> k.isInterface() && k.getName().endsWith(suffix1))
//                .map(k -> k.getName()).collect(Collectors.toList());
//            List<String> interfaceTwoList = subClass.stream()
//                .filter(k -> k.isInterface() && k.getName().endsWith(suffix2))
//                .map(k -> k.getName()).collect(Collectors.toList());
//            if (interfaceOneList.size() > 0 && interfaceTwoList.size() > 0) {
//                if (interfaceOneList.size() > interfaceTwoList.size()) {
//                    exitOnCheckFail("服务" + String.join(",", interfaceTwoList) + "的名称不是以" + suffix1 + "结尾");
//                } else {
//                    exitOnCheckFail("服务" + String.join(",", interfaceOneList) + "的名称不是以" + suffix2 + "结尾");
//                }
//            }
//        }
//    }
//
//    /**
//     * 校验mcc配置转换类型
//     *
//     * @param mccConfigClazz
//     */
//    private static void checkMccConfigConverter(Class mccConfigClazz) {
//        if (mccConfigClazz == null) {
//            return;
//        }
//
//        Field[] fields = mccConfigClazz.getDeclaredFields();
//        for (Field field : fields) {
//            if (field == null) {
//                continue;
//            }
//            MtConfig mtConfig = field.getAnnotation(MtConfig.class);
//            if (mtConfig != null && !mtConfig.converter().equals(MtConfigConverter.class)) {
//                exitOnCheckFail(mtConfig.key() + "禁止自定义converter");
//            }
//        }
//    }
//
//    private static void checkThriftFieldOrder(Class<?> item) {
//        ThriftStruct annotation = item.getAnnotation(ThriftStruct.class);
//        if (annotation == null) {
//            return;
//        }
//        if (item.getSuperclass() != null) {
//            ThriftStruct superAnnotation = item.getSuperclass().getAnnotation(ThriftStruct.class);
//            if (superAnnotation == null) {
//                getMaxOrMinOrderAndCheckDuplicateOrder(item);
//            } else {
//                List<Integer> orderList = getMaxOrMinOrderAndCheckDuplicateOrder(item);
//                List<Integer> supperOrderList = getMaxOrMinOrderAndCheckDuplicateOrder(item.getSuperclass());
//                long sameCount = orderList.stream().filter(k -> supperOrderList.contains(k)).count();
//                if (sameCount > 0) {
//                    exitOnCheckFail(item.getName() + "的字段顺序不能和父类的顺序重复");
//                }
//                checkThriftFieldOrder(item.getSuperclass());
//            }
//        } else {
//            getMaxOrMinOrderAndCheckDuplicateOrder(item);
//        }
//    }
//
//    private static List<Integer> getMaxOrMinOrderAndCheckDuplicateOrder(Class<?> item) {
//        Field[] fields = item.getDeclaredFields();
//        List<Integer> orderList = new ArrayList<>();
//        for (Field field : fields) {
//            if (field.getName().contains("jacocoData")) {
//                continue;
//            }
//            try {
//                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), item);
//                Method getMethod = pd.getReadMethod();
//
//                ThriftField getMethodAnnotation = getMethod.getAnnotation(ThriftField.class);
//                ThriftField fieldAnnotation = field.getAnnotation(ThriftField.class);
//                if (fieldAnnotation != null) {
//                    Integer value = (int) fieldAnnotation.value();
//                    orderList.add(value);
//                } else if (getMethodAnnotation != null) {
//                    Integer value = (int) getMethodAnnotation.value();
//                    orderList.add(value);
//                }
//            } catch (Exception e) {
//
//            }
//        }
//        List<Integer> distinctOrderList = orderList.stream().distinct().collect(Collectors.toList());
//        if (distinctOrderList.size() != orderList.size()) {
//            exitOnCheckFail(item.getName() + "ThriftField序号有重复值");
//        }
//        return orderList;
//    }
//
//    /**
//     * 校验java类
//     *
//     * @param item class
//     * @return
//     */
//    private static void checkClass(Class<?> item) {
//        ThriftStruct annotation = item.getAnnotation(ThriftStruct.class);
//        if (annotation == null) {
//            exitOnCheckFail(item.getName() + "没有添加@ThriftStruct注解");
//        }
//        Field[] fields = item.getDeclaredFields();
//        Set<Integer> set = new HashSet<>(16);
//        for (Field field : fields) {
//            if (field.getName().contains("jacocoData")) {
//                continue;
//            }
//            //校验属性类型
//            checkClassType(item.getName(), field.getName(), field.getType());
//
//            try {
//                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), item);
//                Method getMethod = pd.getReadMethod();
//                Method setMethod = pd.getWriteMethod();
//
//                ThriftField getMethodAnnotation = getMethod.getAnnotation(ThriftField.class);
//                ThriftField setMethodAnnotation = setMethod.getAnnotation(ThriftField.class);
//
//                ThriftField fieldAnnotation = field.getAnnotation(ThriftField.class);
//                if (fieldAnnotation == null) {
//                    if (getMethodAnnotation == null) {
//                        exitOnCheckFail(item.getName() + "#" + getMethod.getName() + "没有添加@ThriftField注解");
//                    }
//                    Integer value = (int) getMethodAnnotation.value();
//                    if (set.contains(value)) {
//                        exitOnCheckFail(item.getName() + "#" + getMethod.getName() + "@ThriftField注解值重复");
//                    }
//                    set.add(value);
//
//                    if (setMethodAnnotation == null) {
//                        exitOnCheckFail(item.getName() + "#" + setMethod.getName() + "没有添加@ThriftField注解");
//                    }
//                } else {
//                    Integer value = (int) fieldAnnotation.value();
//                    if (set.contains(value)) {
//                        exitOnCheckFail(item.getName() + "#" + field.getName() + "@ThriftField注解值重复");
//                    }
//                    set.add(value);
//                    if (getMethodAnnotation != null) {
//                        exitOnCheckFail(item.getName() + "#" + getMethod.getName() + "不能添加@ThriftField注解");
//                    }
//                    if (setMethodAnnotation != null) {
//                        exitOnCheckFail(item.getName() + "#" + setMethod.getName() + "不能添加@ThriftField注解");
//                    }
//                }
//
//            } catch (IntrospectionException e) {
//                logger.error(item.getName() + "#" + field.getName() + "," + e.getMessage());
//            }
//        }
//    }
//
//    /**
//     * 失败后停止服务
//     *
//     * @param msg 错误信息
//     * @return
//     */
//    private static void exitOnCheckFail(String msg) {
//        System.out.println("\033[1;90m" + msg);
//        System.out.println("\033[1;91m" + msg);
//        System.out.println("\033[1;92m" + msg);
//        System.out.println("\033[1;93m" + msg);
//        System.out.println("\033[1;94m" + msg);
//        System.out.println("\033[1;95m" + msg);
//        System.out.println("\033[1;96m" + msg);
//        System.out.println("\033[1;97m" + msg);
//        System.out.println("\033[1;90;47m" + msg);
//        System.out.println("\033[1;91;46m" + msg);
//        System.out.println("\033[1;92;45m" + msg);
//        System.out.println("\033[1;93;44m" + msg);
//        System.out.println("\033[1;94;43m" + msg);
//        System.out.println("\033[1;95;42m" + msg);
//        System.out.println("\033[1;96;41m" + msg);
//        System.out.println("\033[1;97;40m" + msg);
//        logger.error(msg);
//        System.exit(1);
//    }
//
//    /**
//     * 校验接口
//     *
//     * @param item 接口class
//     * @return
//     */
//    private static void checkInterface(Class<?> item, List<Class<?>> whiteClassList, Class<?> baseResponseClass) {
//        long num = serviceSuffixList.stream().filter(k -> item.getName().endsWith(k)).count();
//        if (num == 0) {
//            exitOnCheckFail(item.getName() + "不是以" + String.join("或", serviceSuffixList) + "结尾");
//        }
//
//        ThriftService annotation = item.getAnnotation(ThriftService.class);
//        if (annotation == null) {
//            exitOnCheckFail(item.getName() + "没有添加@ThriftService注解");
//        }
//        Method[] methods = item.getMethods();
//        for (Method method : methods) {
//            ThriftMethod thriftMethod = method.getAnnotation(ThriftMethod.class);
//            if (thriftMethod == null) {
//                exitOnCheckFail(item.getName() + "#" + method.getName() + "没有添加@ThriftMethod注解");
//            }
//            //校验参数类型
//            checkClassType(item.getName(), method.getParameterTypes());
//            Class<?> returnType = method.getReturnType();
//            if (CollectionUtils.isNotEmpty(whiteClassList) && whiteClassList.contains(returnType)) {
//                continue;
//            }
//            if (!baseResponseClass.isAssignableFrom(returnType)) {
//                exitOnCheckFail(item.getName() + "#" + method.getName() + "返回对象没有继承BaseResponse");
//            }
//        }
//    }
//
//    /**
//     * 方法的参数类型
//     *
//     * @param className 类名
//     * @param types     方法被校验的参数的class
//     * @return
//     */
//    private static void checkClassType(String className, Class<?>[] types) {
//        for (int i = 0; i < types.length; i++) {
//            checkClassType(className, "第" + i + "个参数", types[i]);
//        }
//    }
//
//    /**
//     * 校验类中属相的参数类型
//     *
//     * @param className 类名
//     * @param paramName 参数名
//     * @param type      类中被校验的参数的class
//     * @return
//     */
//    private static void checkClassType(String className, String paramName, Class<?> type) {
//        String typeName = type.getTypeName();
////        if (type.isEnum()) {
////            exitOnCheckFail(className + "#" + paramName + "类型错误，不允许使用枚举类型");
////        }
//        if (
////            type == Integer.class
////            || type == Long.class
////            || type == Double.class
////            || type == Float.class
////            || type == Byte.class
////            || type == Short.class
////            || type == Character.class
////            || type == Boolean.class||
//            type == Date.class
//                || typeName.contains("java.time")
//                || typeName.contains("java.math")
//                || typeName.contains("Map")
//        ) {
//            exitOnCheckFail(className + "#" + paramName + "类型错误，不允许使用java.time下的类型/java.math下的类型/map类型");
//        }
//    }
//
//    /**
//     * 校验当前项目中mafka.properties文件中是否全部配置了灰度链路开关
//     */
//    private static void checkMafkaGrayAsSet() {
//        String mafkaConfigFileName = "maf.properties";
//        try {
//            Resource[] resources = new PathMatchingResourcePatternResolver()
//                .getResources("classpath:" + mafkaConfigFileName);
//            if (resources == null || resources.length == 0) {
//                return;
//            }
//            if (!resources[0].exists()) {
//                return;
//            }
//            Properties properties = PropertiesLoaderUtils.loadProperties(resources[0]);
//            List<String> groupList = properties.keySet().stream()
//                .map(k -> k.toString().substring(0, k.toString().lastIndexOf("."))).distinct()
//                .collect(Collectors.toList());
//            List<String> errorList = new ArrayList<>();
//            for (String key : groupList) {
//                if (!properties.containsKey(key + ".grayAsSet")) {
//                    errorList.add(key);
//                }
//            }
//            if (errorList.size() > 0) {
//                exitOnCheckFail(mafkaConfigFileName + "中的" + String.join(",", errorList) + "没有配置灰度链路开关");
//            }
//        } catch (Exception e) {
//            exitOnCheckFail("验证" + mafkaConfigFileName + "中的灰度链路配置时异常：" + e.getMessage());
//        }
//    }
//}
