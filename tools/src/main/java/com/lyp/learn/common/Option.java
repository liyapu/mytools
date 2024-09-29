package com.lyp.learn.common;

import com.lyp.learn.common.function.FunctionExtension;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 扩展 {@link Optional}，添加 Java 9 特性
 *
 * @author at 2023/11/23 17:28
 */
@SuppressWarnings("unused")
public final class Option<T> {

    /**
     * {@link Option#empty()}的常量
     */
    private static final Option<?> EMPTY = new Option<>();

    /**
     * 如果不为{@code null},表示存在
     * 如果为{@code null},表示不存在
     */
    private final T value;

    /**
     * 创建一个空的实例{@link Option}
     */
    private Option() {
        this.value = null;
    }

    private Option(T value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * 返回一个空的 {@code Option}实例,里面不包含任何值
     *
     * @param <T> 元素类型
     * @return 一个空的 {@code Option}
     * @since 1.0.2
     */
    public static <T> Option<T> empty() {
        @SuppressWarnings("unchecked")
        Option<T> t = (Option<T>) EMPTY;
        return t;
    }

    /**
     * {@code value}可以为空，
     * 如果为空，返回一个空的{@code Option}
     * 如果不为空，返回一个不空的{@code Option}
     *
     * @param value 传入的值，可以为空
     * @param <T>   元素类型
     * @return 返回的{@code Option}
     * @since 1.0.2
     */
    public static <T> Option<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    /**
     * 如果值{@code value}存在，返回该值
     * 否则 throws {@code NoSuchElementException}
     *
     * @return 返回非空的值
     * @throws NoSuchElementException 如果值为空
     * @see Option#isPresent()
     * @since 1.0.2
     */
    public T get() {
        if (value == null) {
            throw new NoSuchElementException("value值不存在");
        }
        return value;
    }

    /**
     * 返回{@code true} 如果值存在， 否则返回{@code false}
     *
     * @return {@code boolean}
     * @since 1.0.2
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * 如果值{@code value}不存在，返回true
     * 否则 返回false
     *
     * @return boolean
     * @since 1.0.2
     */
    public boolean isEmpty() {
        return value == null;
    }

    /**
     * 如果值{@code value}存在,执行{@code action}
     * 否则,执行{@code emptyAction}
     *
     * @param action      非空,执行的操作
     * @param emptyAction 空,执行的操作
     * @since 1.0.2
     */
    public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
        if (value != null) {
            action.accept(value);
        } else {
            emptyAction.run();
        }
    }

    /**
     * 如果值{@code value}不存在,执行{@code emptyAction}
     *
     * @param emptyAction 空,执行的操作
     * @since 1.0.8
     */
    public void ifEmpty(Runnable emptyAction) {
        if (value == null) {
            emptyAction.run();
        }
    }

    /**
     * 如果值存在,返回当前{@code Option}
     * 否则返回{@code supplier}提供的{@code Option}
     *
     * @param supplier 空,提供的supplier
     * @return Option
     * @since 1.0.2
     */
    public Option<T> or(Supplier<? extends Option<? extends T>> supplier) {
        Objects.requireNonNull(supplier);
        if (isPresent()) {
            return this;
        } else {
            @SuppressWarnings("unchecked")
            Option<T> r = (Option<T>) supplier.get();
            return Objects.requireNonNull(r);
        }
    }

    /**
     * 如果值存在,返回当前{@code Option}
     * 否则返回 val
     *
     * @param val 待替换的值
     */
    public Option<T> orReplace(T val) {
        if (isPresent()) {
            return this;
        } else {
            return Option.ofNullable(val);
        }
    }

    /**
     * 返回{@code Stream}
     *
     * @return Stream
     * @see Stream
     * @since 1.0.2
     */
    public Stream<T> stream() {
        if (!isPresent()) {
            return Stream.empty();
        } else {
            return Stream.of(value);
        }
    }

    /**
     * 如果值存在,返回当前值
     * 否则抛出异常{@code NoSuchElementException}.
     *
     * @return T {@code value}
     * @throws NoSuchElementException 如果值不存在,抛出异常
     * @since 1.0.2
     */
    public T orElseThrow() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }


    /**
     * 如果{@code value}存在，执行{@code consumer}逻辑
     * 否则不做任何事
     *
     * @param consumer 消费逻辑
     * @since 1.0.2
     */
    public void ifPresent(Consumer<? super T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }

    /**
     * 如果{@code value}存在，并且 {@code predicate}存在,返回当前{@code Option}
     * 否则返回 empty
     *
     * @param predicate 预言
     * @return Option
     * @throws NullPointerException 如果{@code predicate}是{@code null}
     * @since 1.0.2
     */
    public Option<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isPresent()) {
            return this;
        } else {
            return predicate.test(value) ? this : empty();
        }
    }

    /**
     * 执行map逻辑
     * 如果{@code value}存在，执行mapper
     * 否则 什么都不执行
     *
     * @param mapper 一个{@code mapper}的函数
     * @param <U>    返回值元素类型
     * @return Option
     * @throws NullPointerException 如果{@code mapper}为空
     * @since 1.0.2
     */
    public <U> Option<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return empty();
        } else {
            return Option.ofNullable(mapper.apply(value));
        }
    }

    /**
     * 替换Option内部的值
     *
     * @param r   将要替换的值
     * @param <U> 返回值元素类型
     * @return Option
     * @since 1.0.8
     */
    public <U> Option<U> replace(U r) {
        return Option.ofNullable(r);
    }


    /**
     * 执行map逻辑
     * 如果{@code value}存在，执行mapper
     * 否则 什么都不执行
     *
     * @param mapper 一个{@code mapper}的函数
     * @param <U>    返回值元素类型
     * @return Option
     * @throws NullPointerException 如果{@code mapper}为空
     * @throws Throwable            抛出异常
     * @since 1.0.8
     */
    public <U> Option<U> mapExt(FunctionExtension<? super T, ? extends U> mapper) throws Throwable {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return empty();
        } else {
            return Option.ofNullable(mapper.apply(value));
        }
    }

    /**
     * 执行peek逻辑
     * 无论 value是否存在都执行
     *
     * @param peek 一个{@code peek}的函数
     * @return Option
     * @throws NullPointerException 如果{@code peek}为空
     * @since 1.0.3
     */
    public Option<T> peek(Consumer<? super T> peek) {
        Objects.requireNonNull(peek);
        peek.accept(value);
        return this;
    }

    /**
     * 执行peek逻辑
     * 如果{@code value}存在，执行peek
     * 否则 什么都不执行
     *
     * @param peek 一个{@code peek}的函数
     * @return Option
     * @throws NullPointerException 如果{@code peek}为空
     * @since 1.0.8
     */
    public Option<T> peekOnPresent(Consumer<? super T> peek) {
        Objects.requireNonNull(peek);
        if (isPresent()) {
            peek.accept(value);
        }
        return this;
    }

    /**
     * 如果value值存在，执行{@code mapper}逻辑
     * 否则什么都不执行
     *
     * @param mapper 一个{@code mapper}的函数
     * @param <U>    返回值元素类型
     * @return Option
     * @throws NullPointerException 如果{@code mapper}为{@code null} 或者 mapper返回一个null
     * @since 1.0.2
     */
    public <U> Option<U> flatMap(Function<? super T, Option<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return empty();
        } else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }

    /**
     * 如果value值存在，执行{@code mapper}逻辑
     * 否则什么都不执行
     *
     * @param mapper 一个{@code mapper}的函数
     * @param <U>    返回值元素类型
     * @return Option
     * @throws NullPointerException 如果{@code mapper}为{@code null} 或者 mapper返回一个null
     * @since 1.0.5
     */
    public <U> Option<U> flatMapOnOptional(Function<? super T, Optional<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return empty();
        } else {
            return fromOptional(Objects.requireNonNull(mapper.apply(value)));
        }
    }

    /**
     * 如果{@code value}存在,则返回;否则返回{@code other}
     *
     * @param other 如果{@code value}是{@code null},需要返回的值，可以为{@code null}
     * @return 当前值{@code value},否则{@code other}
     * @since 1.0.2
     */
    public T orElse(T other) {
        return value != null ? value : other;
    }

    /**
     * 如果当前值{@code value}存在,返回当前值{@code value}
     * 否则，返回{@code other}
     *
     * @param other 提供{@code other}值的{@link Supplier}
     * @return T {@code value}
     * @throws NullPointerException 如果值{@code value}不存在并且{@code other}为{@code null}
     * @since 1.0.2
     */
    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

    /**
     * 如果当前值{@code value}存在,返回
     * 否则抛出异常{@link Supplier}
     *
     * @param exceptionSupplier 提供异常的{@link Supplier}
     * @param <X>               元素类型
     * @return T {@code value}
     * @throws X 抛出的异常类型
     * @since 1.0.2
     */
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    /**
     * {@code equals}方法
     *
     * @param obj 需要比较的值
     * @return boolean
     * @since 1.0.2
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Option)) {
            return false;
        }

        Option<?> other = (Option<?>) obj;
        return Objects.equals(value, other.value);
    }

    /**
     * {@code hashCode}
     *
     * @return int
     * @since 1.0.2
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /**
     * {@code toString}
     *
     * @return String
     * @since 1.0.2
     */
    @Override
    public String toString() {
        return value != null
                ? String.format("Option[%s]", value)
                : "Option.empty";
    }

    /**
     * 转换成Optional
     *
     * @return Optional
     * @since 1.0.3
     */
    public Optional<T> toOptional() {
        return Optional.ofNullable(value);
    }

    /**
     * 从Optional转换到Option
     *
     * @return Optional
     * @since 1.0.5
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <U> Option<U> fromOptional(Optional<U> optional) {
        return Option.ofNullable(optional.orElse(null));
    }

    /**
     * 返回一个 {@code Option} 通过一个不空的值
     *
     * @param value 值
     * @param <T>   元素类型
     * @return 返回不空的Option
     * @throws NullPointerException 如果值为空
     * @since 1.0.2
     */
    private static <T> Option<T> of(T value) {
        return new Option<>(value);
    }
}
