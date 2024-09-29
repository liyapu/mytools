package com.lyp.learn.common;

import org.springframework.lang.NonNull;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static java.lang.String.join;

/**
 * 安全操作工具类.
 *
 * @author at 2023/02/07 11:12
 * @since 1.2.1
 */
@SuppressWarnings("unused")
public final class SafeUtil {

    private static final LocalDate DEFAULT_DATE = LocalDate.parse("1970-01-01",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    private SafeUtil() {
        // Hidden
    }

    /**
     * 安全操作，规避 NPE
     *
     * @author kangyonggen
     * @since 1.2.1
     */
    public static Integer wrap(Integer a, Integer b, BinaryOperator<Integer> merge) {
        return merge.apply(zeroIfNull(a), zeroIfNull(b));
    }

    /**
     * 安全操作，规避 NPE
     *
     * @author kangyonggen
     * @since 1.2.1
     */
    public static Long wrap(Long a, Long b, BinaryOperator<Long> merge) {
        return merge.apply(zeroIfNull(a), zeroIfNull(b));
    }

    /**
     * 安全操作，规避 NPE
     *
     * @author kangyonggen
     * @since 1.2.1
     */
    public static BigDecimal wrap(BigDecimal a, BigDecimal b, BinaryOperator<BigDecimal> merge) {
        return merge.apply(zeroIfNull(a), zeroIfNull(b));
    }

    /**
     * 安全操作，规避 NPE
     *
     * @author kangyonggen
     * @since 1.2.1
     */
    public static Integer reduce(BinaryOperator<Integer> merge, Integer... numbs) {
        return Arrays.stream(numbs).reduce(0, (a, b) -> wrap(a, b, merge));
    }

    /**
     * 安全操作，规避 NPE
     *
     * @author kangyonggen
     * @since 1.2.1
     */
    public static Long reduce(BinaryOperator<Long> merge, Long... numbs) {
        return Arrays.stream(numbs).reduce(0L, (a, b) -> wrap(a, b, merge));
    }

    /**
     * 安全操作，规避 NPE
     *
     * @author kangyonggen
     * @since 1.2.1
     */
    public static BigDecimal reduce(BinaryOperator<BigDecimal> merge, BigDecimal... numbs) {
        return Arrays.stream(numbs).reduce(BigDecimal.ZERO, (a, b) -> wrap(a, b, merge));
    }

    /**
     * 默认值，规避 NPE
     *
     * @author kangyonggen
     * @since 1.2.1
     */
    public static Integer zeroIfNull(Integer number) {
        return Option.ofNullable(number).orElse(NumberUtil.INTEGER_ZERO);
    }

    /**
     * 默认值，规避 NPE
     *
     * @author kangyonggen
     * @since 1.2.1
     */
    public static Long zeroIfNull(Long number) {
        return Option.ofNullable(number).orElse(NumberUtil.LONG_ZERO);
    }

    /**
     * 默认值，规避 NPE
     *
     * @author kangyonggen
     * @since 1.2.1
     */
    public static BigDecimal zeroIfNull(BigDecimal number) {
        return Option.ofNullable(number).orElse(BigDecimal.ZERO);
    }

    /**
     * 检查对象 {@code PO} 是否有字段为 {@code null}<br/><br/<p>
     * <b color="yellow"> {@code PO} 必须提供 {@code Getter,Setter} 方法，否则不会生效<br/>
     * </b></p>
     *
     * @param po  待检查对象
     * @param <T> 泛型
     * @throws IllegalArgumentException 参数为null
     * @throws IllegalStateException    字段为null
     * @author kangyonggen
     * @see #assertNonNullFieldWithIgnores(Object, String...)
     * @since 1.2.1
     */
    public static <T> void assertNonNullFieldIgnoreId(@NonNull T po) {
        SafeUtil.assertNonNullFieldWithIgnores(po, "id");
    }

    /**
     * 检查对象 {@code PO} 是否有字段为 {@code null}<br/><br/<p>
     * <b color="yellow">{@code PO} 必须提供 {@code Getter,Setter} 方法，否则不会生效<br/></b></p>
     *
     * @param po      待检查对象
     * @param ignores 可以忽略的字段
     * @param <T>     泛型
     * @throws IllegalArgumentException 参数为null
     * @throws IllegalStateException    字段为null
     * @author kangyonggen
     * @since 1.2.1
     */
    public static <T> void assertNonNullFieldWithIgnores(@NonNull T po, String... ignores) {
        Assert.notNull(po, "参数不能为空!");
        Set<String> ignoreFields = Arrays.stream(ignores).collect(Collectors.toSet());

        try {
            Class<?> cls = po.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(cls);
            List<String> errors = ListUtil.newArrayList();

            for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                if (ignoreFields.contains(pd.getName())) {
                    continue;
                }

                if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
                    Object value = pd.getReadMethod().invoke(po);

                    if (Objects.isNull(value)) {
                        errors.add(pd.getName());
                    }
                }
            }

            if (CollUtil.isNotEmpty(errors)) {
                throw new IllegalStateException(StrUtil.format("{}: {} 不能为null", cls.getSimpleName(), join(",", errors)));
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("SafeUtil.assertNonNullField：BeanInfo处理异常", e);
        }
    }

    /**
     * 检查对象 {@code PO} 是否有字段为 {@code null}<br/><br/<p>
     * <b color="yellow">{@code PO} 必须提供 {@code Getter,Setter} 方法，否则不会生效<br/></b></p>
     *
     * @param po          待检查对象
     * @param necessaries 必须检查的字段
     * @param <T>         泛型
     * @throws IllegalArgumentException 参数为null
     * @throws IllegalStateException    字段为null
     * @author kangyonggen
     * @since 1.2.1
     */
    public static <T> void assertNonNullFieldWithNecessary(@NonNull T po, String... necessaries) {
        Assert.notNull(po, "参数不能为空!");
        Set<String> necessaryFields = Arrays.stream(necessaries)
                .map(StrUtil::toCamelCase)
                .collect(Collectors.toSet());

        try {
            Class<?> cls = po.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(cls);
            List<String> errors = ListUtil.newArrayList();

            for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                if (!necessaryFields.contains(pd.getName())) {
                    continue;
                }

                if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
                    Object value = pd.getReadMethod().invoke(po);

                    if (Objects.isNull(value)) {
                        errors.add(pd.getName());
                    }
                }
            }

            if (CollUtil.isNotEmpty(errors)) {
                throw new IllegalStateException(StrUtil.format("{}: {} 不能为null", cls.getSimpleName(), join(",", errors)));
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("SafeUtil.assertNonNullField：BeanInfo处理异常", e);
        }
    }

    /**
     * 遍历 PO 字段，如果字段值为 {@code null}，设置默认值：<br/><br/<p>
     * <b color="yellow"> {@code PO} 必须提供 {@code Getter,Setter} 方法，否则不会生效<br/>
     * </b></p><pre>{@code
     * 1. int,short,long,byte,float,double => 0
     * 2. BigDecimal => 0
     * 3. String => ""
     * 4. LocalDate => 1970-01-01
     * 5. LocalDateTime => 1970-01-01 00:00:01.000
     * }</pre>
     *
     * @param po  待检查对象
     * @param <T> 泛型
     * @return po self
     * @throws IllegalArgumentException 参数为空
     * @throws IllegalStateException    只支持基本类型
     * @author kangyonggen
     * @see #poSafeWrap(Object, String...)
     * @since 1.2.1
     */
    public static <T> T poSafeWrapIgnoreId(@NonNull T po) {
        return SafeUtil.poSafeWrap(po, "id");
    }

    /**
     * 遍历 PO 字段，如果字段值为 {@code null}，设置默认值：<br/><br/<p>
     * <b color="yellow">{@code PO} 必须提供 {@code Getter,Setter} 方法，否则不会生效<br/>
     * </b></p><pre>{@code
     * 1. int,short,long,byte,float,double => 0
     * 2. BigDecimal => 0
     * 3. String => ""
     * 4. LocalDate => 1970-01-01
     * 5. LocalDateTime => 1970-01-01 00:00:01.000
     * }</pre>
     *
     * @param po      待检查对象
     * @param ignores 可以忽略的字段
     * @param <T>     泛型
     * @return po self
     * @throws IllegalArgumentException 参数为空
     * @throws IllegalStateException    只支持基本类型
     * @author kangyonggen
     * @since 1.2.1
     */
    public static <T> T poSafeWrap(@NonNull T po, String... ignores) {
        Assert.notNull(po, "PO参数不能为空!");
        Set<String> ignoreFields = Arrays.stream(ignores).collect(Collectors.toSet());

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(po.getClass());

            for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                if (ignoreFields.contains(pd.getName())) {
                    continue;
                }

                if (pd.getReadMethod() != null && pd.getWriteMethod() != null && !"class".equals(pd.getName())) {
                    Object value = pd.getReadMethod().invoke(po);
                    Class<?> filedType = pd.getPropertyType();

                    if (value == null && filedType != null) {
                        if (BigDecimal.class.equals(filedType)) {
                            pd.getWriteMethod().invoke(po, BigDecimal.ZERO);
                        } else if (Integer.class.isAssignableFrom(filedType)) {
                            pd.getWriteMethod().invoke(po, 0);
                        } else if (Long.class.isAssignableFrom(filedType)) {
                            pd.getWriteMethod().invoke(po, 0L);
                        } else if (Short.class.isAssignableFrom(filedType)) {
                            pd.getWriteMethod().invoke(po, (short) 0);
                        } else if (Byte.class.isAssignableFrom(filedType)) {
                            pd.getWriteMethod().invoke(po, (byte) 0);
                        } else if (Float.class.isAssignableFrom(filedType)) {
                            pd.getWriteMethod().invoke(po, 0.0F);
                        } else if (Double.class.isAssignableFrom(filedType)) {
                            pd.getWriteMethod().invoke(po, 0.0);
                        } else if (String.class.equals(filedType)) {
                            pd.getWriteMethod().invoke(po, "");
                        } else if (LocalDate.class.equals(filedType)) {
                            pd.getWriteMethod().invoke(po, DEFAULT_DATE);
                        } else if (LocalDateTime.class.equals(filedType)) {
                            pd.getWriteMethod().invoke(po, DEFAULT_DATE.atStartOfDay().plusSeconds(1L));
                        } else {
                            throw new IllegalStateException(
                                    "SafeUtil.poSafeWrap不支持该类型：" + filedType.getSimpleName());
                        }
                    }
                }
            }

            return po;

        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("SafeUtil.poSafeWrap：BeanInfo处理异常", e);
        }
    }
}
