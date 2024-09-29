package com.lyp.learn.common.ops;

import com.lyp.learn.common.CollUtil;
import com.lyp.learn.common.Option;
import com.lyp.learn.common.SetUtil;

import java.util.Set;
import java.util.stream.Stream;

/**
 * SetOps
 *
 * @author at 2023/12/14 19:32
 */
public final class SetOps<E> {

    private final Set<E> dataSet;

    private SetOps(Set<E> dataSet) {
        this.dataSet = dataSet;
    }

    // static ----------------------------------------------------------------------------------------------------------

    public static <E> SetOps<E> empty() {
        return of(SetUtil.newHashSet());
    }

    @SafeVarargs
    public static <E> SetOps<E> of(E... elements) {
        return new SetOps<>(SetUtil.newHashSet(elements));
    }

    public static <E> SetOps<E> of(Set<E> dataSet) {
        return new SetOps<>(CollUtil.emptyIfNull(dataSet));
    }

    public static <E> SetOps<E> union(SetOps<E> left, SetOps<E> right) {
        return Option.ofNullable(left)
                .map(l -> left.merge(right))
                .orElse(empty());
    }

    // instance --------------------------------------------------------------------------------------------------------

    @SafeVarargs
    public final SetOps<E> append(E... elements) {
        Option.ofNullable(elements)
                .map(Stream::of)
                .ifPresent(s -> s.forEach(dataSet::add));

        return this;
    }

    @SafeVarargs
    public final SetOps<E> merge(SetOps<E>... others) {
        Option.ofNullable(others)
                .map(Stream::of)
                .ifPresent(s -> s.map(t -> t.dataSet)
                        .forEach(dataSet::addAll));

        return this;
    }

    @SafeVarargs
    public final boolean only(E... elements) {
        return Option.ofNullable(elements)
                .map(SetUtil::newHashSet)
                .map(el -> el.containsAll(dataSet))
                .orElse(false);
    }

    @SafeVarargs
    public final boolean any(E... elements) {
        return Option.ofNullable(elements)
                .map(Stream::of)
                .map(s -> s.anyMatch(dataSet::contains))
                .orElse(false);
    }

    @SafeVarargs
    public final boolean none(E... elements) {
        return Option.ofNullable(elements)
                .map(Stream::of)
                .map(s -> s.noneMatch(dataSet::contains))
                .orElse(false);
    }
}
