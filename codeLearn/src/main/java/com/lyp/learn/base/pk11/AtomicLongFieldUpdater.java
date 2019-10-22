///*
// * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// */
//
///*
// *
// *
// *
// *
// *
// * Written by Doug Lea with assistance from members of JCP JSR-166
// * Expert Group and released to the public domain, as explained at
// * http://creativecommons.org/publicdomain/zero/1.0/
// */
//
//package com.lyp.learn.base.pk11;
//
//import sun.reflect.CallerSensitive;
//import sun.reflect.Reflection;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//import java.security.AccessController;
//import java.security.PrivilegedActionException;
//import java.security.PrivilegedExceptionAction;
//import java.util.Objects;
//import java.util.function.LongBinaryOperator;
//import java.util.function.LongUnaryOperator;
//
///**
// * 一种基于反射的实用程序，可以对指定类的指定的volatile long字段进行原子更新。
// * 该类设计用于原子数据结构，其中同一节点的多个字段独立受原子更新的影响。
// *
// * 注意只能够原子更新里面的某一个long型的变量。
// *
// * 请注意，该类中的compareAndSet方法的保证弱于其他原子类。
// *因为这个类不能确保该字段的所有用途都适用于原子访问的目的，
// * 所以它可以保证原子性只能在相同更新程序上的compareAndSet和set其他调用。
// * @param <T> 包含可更新字段的对象的类型
// */
//public abstract class AtomicLongFieldUpdater<T> {
//    /**
//     * 创建并返回具有给定字段的对象的更新程序。
//     * 需要Class参数来检查反射类型和泛型类型是否匹配。
//     *
//     * 实际上返回的是CASUpdater对象，或者LockedUpdater对象；
//     * 具体返回哪一个类取决于JVM是否支持long类型的CAS函数
//     * @param tclass 持有该字段的对象的类
//     * @param fieldName 要更新的字段的名称
//     * @param <U> tclass的实例类型
//     * @return 更新者
//     * @throws IllegalArgumentException 如果该字段不是易失性长类型
//     * @throws RuntimeException 如果类不保留字段或错误的类型，
//     *                          或者根据Java语言访问控制，该调用者无法访问该字段，
//     *                          则会使用嵌套的基于反射的异常
//     */
//    @CallerSensitive
//    public static <U> AtomicLongFieldUpdater<U> newUpdater(Class<U> tclass, String fieldName) {
//        Class<?> caller = Reflection.getCallerClass();
//          //直接cas实现  compareAndSet
////        if (AtomicLong.VM_SUPPORTS_LONG_CAS)
////            return new CASUpdater<U>(tclass, fieldName, caller);
////        else
//            //带synchronized锁实现  compareAndSet
////            return new LockedUpdater<U>(tclass, fieldName, caller);
//        return null;
//    }
//
//    /**
//     * Protected do-nothing constructor for use by subclasses.
//     */
//    protected AtomicLongFieldUpdater() {
//    }
//
//    /**
//     * Atomically sets the field of the given object managed by this updater
//     * to the given updated value if the current value {@code ==} the
//     * expected value. This method is guaranteed to be atomic with respect to
//     * other calls to {@code compareAndSet} and {@code set}, but not
//     * necessarily with respect to other changes in the field.
//     *
//     * @param obj An object whose field to conditionally set
//     * @param expect the expected value
//     * @param update the new value
//     * @return {@code true} if successful
//     * @throws ClassCastException if {@code obj} is not an instance
//     * of the class possessing the field established in the constructor
//     */
//    public abstract boolean compareAndSet(T obj, long expect, long update);
//
//    /**
//     * Atomically sets the field of the given object managed by this updater
//     * to the given updated value if the current value {@code ==} the
//     * expected value. This method is guaranteed to be atomic with respect to
//     * other calls to {@code compareAndSet} and {@code set}, but not
//     * necessarily with respect to other changes in the field.
//     *
//     * <p><a href="package-summary.html#weakCompareAndSet">May fail
//     * spuriously and does not provide ordering guarantees</a>, so is
//     * only rarely an appropriate alternative to {@code compareAndSet}.
//     *
//     * @param obj An object whose field to conditionally set
//     * @param expect the expected value
//     * @param update the new value
//     * @return {@code true} if successful
//     * @throws ClassCastException if {@code obj} is not an instance
//     * of the class possessing the field established in the constructor
//     */
//    public abstract boolean weakCompareAndSet(T obj, long expect, long update);
//
//    /**
//     * Sets the field of the given object managed by this updater to the
//     * given updated value. This operation is guaranteed to act as a volatile
//     * store with respect to subsequent invocations of {@code compareAndSet}.
//     *
//     * @param obj An object whose field to set
//     * @param newValue the new value
//     */
//    public abstract void set(T obj, long newValue);
//
//    /**
//     * Eventually sets the field of the given object managed by this
//     * updater to the given updated value.
//     *
//     * @param obj An object whose field to set
//     * @param newValue the new value
//     * @since 1.6
//     */
//    public abstract void lazySet(T obj, long newValue);
//
//    /**
//     * Gets the current value held in the field of the given object managed
//     * by this updater.
//     *
//     * @param obj An object whose field to get
//     * @return the current value
//     */
//    public abstract long get(T obj);
//
//    /**
//     * Atomically sets the field of the given object managed by this updater
//     * to the given value and returns the old value.
//     *
//     * @param obj An object whose field to get and set
//     * @param newValue the new value
//     * @return the previous value
//     */
//    public long getAndSet(T obj, long newValue) {
//        long prev;
//        do {
//            prev = get(obj);
//        } while (!compareAndSet(obj, prev, newValue));
//        return prev;
//    }
//
//    /**
//     * Atomically increments by one the current value of the field of the
//     * given object managed by this updater.
//     *
//     * @param obj An object whose field to get and set
//     * @return the previous value
//     */
//    public long getAndIncrement(T obj) {
//        long prev, next;
//        do {
//            prev = get(obj);
//            next = prev + 1;
//        } while (!compareAndSet(obj, prev, next));
//        return prev;
//    }
//
//    /**
//     * Atomically decrements by one the current value of the field of the
//     * given object managed by this updater.
//     *
//     * @param obj An object whose field to get and set
//     * @return the previous value
//     */
//    public long getAndDecrement(T obj) {
//        long prev, next;
//        do {
//            prev = get(obj);
//            next = prev - 1;
//        } while (!compareAndSet(obj, prev, next));
//        return prev;
//    }
//
//    /**
//     * Atomically adds the given value to the current value of the field of
//     * the given object managed by this updater.
//     *
//     * @param obj An object whose field to get and set
//     * @param delta the value to add
//     * @return the previous value
//     */
//    public long getAndAdd(T obj, long delta) {
//        long prev, next;
//        do {
//            prev = get(obj);
//            next = prev + delta;
//        } while (!compareAndSet(obj, prev, next));
//        return prev;
//    }
//
//    /**
//     * Atomically increments by one the current value of the field of the
//     * given object managed by this updater.
//     *
//     * @param obj An object whose field to get and set
//     * @return the updated value
//     */
//    public long incrementAndGet(T obj) {
//        long prev, next;
//        do {
//            prev = get(obj);
//            next = prev + 1;
//        } while (!compareAndSet(obj, prev, next));
//        return next;
//    }
//
//    /**
//     * Atomically decrements by one the current value of the field of the
//     * given object managed by this updater.
//     *
//     * @param obj An object whose field to get and set
//     * @return the updated value
//     */
//    public long decrementAndGet(T obj) {
//        long prev, next;
//        do {
//            prev = get(obj);
//            next = prev - 1;
//        } while (!compareAndSet(obj, prev, next));
//        return next;
//    }
//
//    /**
//     * Atomically adds the given value to the current value of the field of
//     * the given object managed by this updater.
//     *
//     * @param obj An object whose field to get and set
//     * @param delta the value to add
//     * @return the updated value
//     */
//    public long addAndGet(T obj, long delta) {
//        long prev, next;
//        do {
//            prev = get(obj);
//            next = prev + delta;
//        } while (!compareAndSet(obj, prev, next));
//        return next;
//    }
//
//    /**
//     * Atomically updates the field of the given object managed by this updater
//     * with the results of applying the given function, returning the previous
//     * value. The function should be side-effect-free, since it may be
//     * re-applied when attempted updates fail due to contention among threads.
//     *
//     * @param obj An object whose field to get and set
//     * @param updateFunction a side-effect-free function
//     * @return the previous value
//     * @since 1.8
//     */
//    public final long getAndUpdate(T obj, LongUnaryOperator updateFunction) {
//        long prev, next;
//        do {
//            prev = get(obj);
//            next = updateFunction.applyAsLong(prev);
//        } while (!compareAndSet(obj, prev, next));
//        return prev;
//    }
//
//    /**
//     * Atomically updates the field of the given object managed by this updater
//     * with the results of applying the given function, returning the updated
//     * value. The function should be side-effect-free, since it may be
//     * re-applied when attempted updates fail due to contention among threads.
//     *
//     * @param obj An object whose field to get and set
//     * @param updateFunction a side-effect-free function
//     * @return the updated value
//     * @since 1.8
//     */
//    public final long updateAndGet(T obj, LongUnaryOperator updateFunction) {
//        long prev, next;
//        do {
//            prev = get(obj);
//            next = updateFunction.applyAsLong(prev);
//        } while (!compareAndSet(obj, prev, next));
//        return next;
//    }
//
//    /**
//     * Atomically updates the field of the given object managed by this
//     * updater with the results of applying the given function to the
//     * current and given values, returning the previous value. The
//     * function should be side-effect-free, since it may be re-applied
//     * when attempted updates fail due to contention among threads.  The
//     * function is applied with the current value as its first argument,
//     * and the given update as the second argument.
//     *
//     * @param obj An object whose field to get and set
//     * @param x the update value
//     * @param accumulatorFunction a side-effect-free function of two arguments
//     * @return the previous value
//     * @since 1.8
//     */
//    public final long getAndAccumulate(T obj, long x,
//                                       LongBinaryOperator accumulatorFunction) {
//        long prev, next;
//        do {
//            prev = get(obj);
//            next = accumulatorFunction.applyAsLong(prev, x);
//        } while (!compareAndSet(obj, prev, next));
//        return prev;
//    }
//
//    /**
//     * Atomically updates the field of the given object managed by this
//     * updater with the results of applying the given function to the
//     * current and given values, returning the updated value. The
//     * function should be side-effect-free, since it may be re-applied
//     * when attempted updates fail due to contention among threads.  The
//     * function is applied with the current value as its first argument,
//     * and the given update as the second argument.
//     *
//     * @param obj An object whose field to get and set
//     * @param x the update value
//     * @param accumulatorFunction a side-effect-free function of two arguments
//     * @return the updated value
//     * @since 1.8
//     */
//    public final long accumulateAndGet(T obj, long x,
//                                       LongBinaryOperator accumulatorFunction) {
//        long prev, next;
//        do {
//            prev = get(obj);
//            next = accumulatorFunction.applyAsLong(prev, x);
//        } while (!compareAndSet(obj, prev, next));
//        return next;
//    }
//
//    private static final class CASUpdater<T> extends AtomicLongFieldUpdater<T> {
//        private static final sun.misc.Unsafe U = sun.misc.Unsafe.getUnsafe();
//        private final long offset;
//        /**
//         * if field is protected, the subclass constructing updater, else
//         * the same as tclass
//         */
//        private final Class<?> cclass;
//        /** class holding the field */
//        private final Class<T> tclass;
//
//        CASUpdater(final Class<T> tclass, final String fieldName,
//                   final Class<?> caller) {
//            final Field field;
//            final int modifiers;
//            try {
//                field = AccessController.doPrivileged(
//                    new PrivilegedExceptionAction<Field>() {
//                        public Field run() throws NoSuchFieldException {
//                            return tclass.getDeclaredField(fieldName);
//                        }
//                    });
//                modifiers = field.getModifiers();
//                sun.reflect.misc.ReflectUtil.ensureMemberAccess(
//                    caller, tclass, null, modifiers);
//                ClassLoader cl = tclass.getClassLoader();
//                ClassLoader ccl = caller.getClassLoader();
//                if ((ccl != null) && (ccl != cl) &&
//                    ((cl == null) || !isAncestor(cl, ccl))) {
//                    sun.reflect.misc.ReflectUtil.checkPackageAccess(tclass);
//                }
//            } catch (PrivilegedActionException pae) {
//                throw new RuntimeException(pae.getException());
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//
//            if (field.getType() != long.class)
//                throw new IllegalArgumentException("Must be long type");
//
//            if (!Modifier.isVolatile(modifiers))
//                throw new IllegalArgumentException("Must be volatile type");
//
//            // Access to protected field members is restricted to receivers only
//            // of the accessing class, or one of its subclasses, and the
//            // accessing class must in turn be a subclass (or package sibling)
//            // of the protected member's defining class.
//            // If the updater refers to a protected field of a declaring class
//            // outside the current package, the receiver argument will be
//            // narrowed to the type of the accessing class.
//            this.cclass = (Modifier.isProtected(modifiers) &&
//                           tclass.isAssignableFrom(caller) &&
//                           !isSamePackage(tclass, caller))
//                          ? caller : tclass;
//            this.tclass = tclass;
//            this.offset = U.objectFieldOffset(field);
//        }
//
//        /**
//         * Checks that target argument is instance of cclass.  On
//         * failure, throws cause.
//         */
//        private final void accessCheck(T obj) {
//            if (!cclass.isInstance(obj))
//                throwAccessCheckException(obj);
//        }
//
//        /**
//         * Throws access exception if accessCheck failed due to
//         * protected access, else ClassCastException.
//         */
//        private final void throwAccessCheckException(T obj) {
//            if (cclass == tclass)
//                throw new ClassCastException();
//            else
//                throw new RuntimeException(
//                    new IllegalAccessException(
//                        "Class " +
//                        cclass.getName() +
//                        " can not access a protected member of class " +
//                        tclass.getName() +
//                        " using an instance of " +
//                        obj.getClass().getName()));
//        }
//
//        public final boolean compareAndSet(T obj, long expect, long update) {
//            accessCheck(obj);
//            return U.compareAndSwapLong(obj, offset, expect, update);
//        }
//
//        public final boolean weakCompareAndSet(T obj, long expect, long update) {
//            accessCheck(obj);
//            return U.compareAndSwapLong(obj, offset, expect, update);
//        }
//
//        public final void set(T obj, long newValue) {
//            accessCheck(obj);
//            U.putLongVolatile(obj, offset, newValue);
//        }
//
//        public final void lazySet(T obj, long newValue) {
//            accessCheck(obj);
//            U.putOrderedLong(obj, offset, newValue);
//        }
//
//        public final long get(T obj) {
//            accessCheck(obj);
//            return U.getLongVolatile(obj, offset);
//        }
//
//        public final long getAndSet(T obj, long newValue) {
//            accessCheck(obj);
//            return U.getAndSetLong(obj, offset, newValue);
//        }
//
//        public final long getAndAdd(T obj, long delta) {
//            accessCheck(obj);
//            return U.getAndAddLong(obj, offset, delta);
//        }
//
//        public final long getAndIncrement(T obj) {
//            return getAndAdd(obj, 1);
//        }
//
//        public final long getAndDecrement(T obj) {
//            return getAndAdd(obj, -1);
//        }
//
//        public final long incrementAndGet(T obj) {
//            return getAndAdd(obj, 1) + 1;
//        }
//
//        public final long decrementAndGet(T obj) {
//            return getAndAdd(obj, -1) - 1;
//        }
//
//        public final long addAndGet(T obj, long delta) {
//            return getAndAdd(obj, delta) + delta;
//        }
//    }
//
//    private static final class LockedUpdater<T> extends AtomicLongFieldUpdater<T> {
//        private static final sun.misc.Unsafe U = sun.misc.Unsafe.getUnsafe();
//        private final long offset;
//        /**
//         * if field is protected, the subclass constructing updater, else
//         * the same as tclass
//         */
//        private final Class<?> cclass;
//        /** class holding the field */
//        private final Class<T> tclass;
//
//        LockedUpdater(final Class<T> tclass, final String fieldName,
//                      final Class<?> caller) {
//            Field field = null;
//            int modifiers = 0;
//            try {
//                field = AccessController.doPrivileged(
//                    new PrivilegedExceptionAction<Field>() {
//                        public Field run() throws NoSuchFieldException {
//                            return tclass.getDeclaredField(fieldName);
//                        }
//                    });
//                modifiers = field.getModifiers();
//                sun.reflect.misc.ReflectUtil.ensureMemberAccess(
//                    caller, tclass, null, modifiers);
//                ClassLoader cl = tclass.getClassLoader();
//                ClassLoader ccl = caller.getClassLoader();
//                if ((ccl != null) && (ccl != cl) &&
//                    ((cl == null) || !isAncestor(cl, ccl))) {
//                    sun.reflect.misc.ReflectUtil.checkPackageAccess(tclass);
//                }
//            } catch (PrivilegedActionException pae) {
//                throw new RuntimeException(pae.getException());
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//
//            if (field.getType() != long.class)
//                throw new IllegalArgumentException("Must be long type");
//
//            if (!Modifier.isVolatile(modifiers))
//                throw new IllegalArgumentException("Must be volatile type");
//
//            // Access to protected field members is restricted to receivers only
//            // of the accessing class, or one of its subclasses, and the
//            // accessing class must in turn be a subclass (or package sibling)
//            // of the protected member's defining class.
//            // If the updater refers to a protected field of a declaring class
//            // outside the current package, the receiver argument will be
//            // narrowed to the type of the accessing class.
//            this.cclass = (Modifier.isProtected(modifiers) &&
//                           tclass.isAssignableFrom(caller) &&
//                           !isSamePackage(tclass, caller))
//                          ? caller : tclass;
//            this.tclass = tclass;
//            this.offset = U.objectFieldOffset(field);
//        }
//
//        /**
//         * Checks that target argument is instance of cclass.  On
//         * failure, throws cause.
//         */
//        private final void accessCheck(T obj) {
//            if (!cclass.isInstance(obj))
//                throw accessCheckException(obj);
//        }
//
//        /**
//         * Returns access exception if accessCheck failed due to
//         * protected access, else ClassCastException.
//         */
//        private final RuntimeException accessCheckException(T obj) {
//            if (cclass == tclass)
//                return new ClassCastException();
//            else
//                return new RuntimeException(
//                    new IllegalAccessException(
//                        "Class " +
//                        cclass.getName() +
//                        " can not access a protected member of class " +
//                        tclass.getName() +
//                        " using an instance of " +
//                        obj.getClass().getName()));
//        }
//
//        public final boolean compareAndSet(T obj, long expect, long update) {
//            accessCheck(obj);
//            synchronized (this) {
//                long v = U.getLong(obj, offset);
//                if (v != expect)
//                    return false;
//                U.putLong(obj, offset, update);
//                return true;
//            }
//        }
//
//        public final boolean weakCompareAndSet(T obj, long expect, long update) {
//            return compareAndSet(obj, expect, update);
//        }
//
//        public final void set(T obj, long newValue) {
//            accessCheck(obj);
//            synchronized (this) {
//                U.putLong(obj, offset, newValue);
//            }
//        }
//
//        public final void lazySet(T obj, long newValue) {
//            set(obj, newValue);
//        }
//
//        public final long get(T obj) {
//            accessCheck(obj);
//            synchronized (this) {
//                return U.getLong(obj, offset);
//            }
//        }
//    }
//
//    /**
//     * Returns true if the second classloader can be found in the first
//     * classloader's delegation chain.
//     * Equivalent to the inaccessible: first.isAncestor(second).
//     */
//    static boolean isAncestor(ClassLoader first, ClassLoader second) {
//        ClassLoader acl = first;
//        do {
//            acl = acl.getParent();
//            if (second == acl) {
//                return true;
//            }
//        } while (acl != null);
//        return false;
//    }
//
//    /**
//     * Returns true if the two classes have the same class loader and
//     * package qualifier
//     */
//    private static boolean isSamePackage(Class<?> class1, Class<?> class2) {
//        return class1.getClassLoader() == class2.getClassLoader()
//               && Objects.equals(getPackageName(class1), getPackageName(class2));
//}
//
//    private static String getPackageName(Class<?> cls) {
//        String cn = cls.getName();
//        int dot = cn.lastIndexOf('.');
//        return (dot != -1) ? cn.substring(0, dot) : "";
//    }
//}
