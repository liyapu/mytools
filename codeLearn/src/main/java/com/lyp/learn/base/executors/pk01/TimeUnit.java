
package com.lyp.learn.base.executors.pk01;

import com.lyp.learn.base.threads.pk01.Object;

/**
 * A TimeUnit表示给定的粒度单位的持续时间，
 * 并且提供了跨单元转换的实用方法，并且在这些单元中执行定时和延迟操作。
 * A TimeUnit不保留时间信息，但只能帮助组织和使用可能在不同上下文中单独维护的时间表示。
 * 一纳秒定义为千分之一微秒，微秒为千分之一毫秒，毫秒为千分之一秒，
 * 一分钟为六十秒，一小时为六十分钟，一天为二十四小时。
 * A TimeUnit主要用于通知基于时间的方法如何解释给定的时序参数。
 * 例如，下面的代码将在50毫秒超时如果lock不可用：
 *
 *    Lock lock = ...; if (lock.tryLock(50L, TimeUnit.MILLISECONDS)) ...
 *    而此代码将在50秒内超时：
 *    Lock lock = ...; if (lock.tryLock(50L, TimeUnit.SECONDS)) ...
 *    然而，请注意，
 *    不能保证特定的超时执行将能够以与给定的TimeUnit相同的粒度注意到时间的TimeUnit
 */
public enum TimeUnit {
    //1纳秒
    NANOSECONDS {
        @Override
        public long toNanos(long d)   { return d; }
        @Override
        public long toMicros(long d)  { return d/(C1/C0); }
        @Override
        public long toMillis(long d)  { return d/(C2/C0); }
        @Override
        public long toSeconds(long d) { return d/(C3/C0); }
        @Override
        public long toMinutes(long d) { return d/(C4/C0); }
        @Override
        public long toHours(long d)   { return d/(C5/C0); }
        @Override
        public long toDays(long d)    { return d/(C6/C0); }
        @Override
        public long convert(long d, TimeUnit u) { return u.toNanos(d); }
        @Override
        int excessNanos(long d, long m) { return (int)(d - (m*C2)); }
    },

    //1微秒
    MICROSECONDS {
        @Override
        public long toNanos(long d)   { return x(d, C1/C0, MAX/(C1/C0)); }
        @Override
        public long toMicros(long d)  { return d; }
        @Override
        public long toMillis(long d)  { return d/(C2/C1); }
        @Override
        public long toSeconds(long d) { return d/(C3/C1); }
        @Override
        public long toMinutes(long d) { return d/(C4/C1); }
        @Override
        public long toHours(long d)   { return d/(C5/C1); }
        @Override
        public long toDays(long d)    { return d/(C6/C1); }
        @Override
        public long convert(long d, TimeUnit u) { return u.toMicros(d); }
        @Override
        int excessNanos(long d, long m) { return (int)((d*C1) - (m*C2)); }
    },

    //1毫秒
    MILLISECONDS {
        @Override
        public long toNanos(long d)   { return x(d, C2/C0, MAX/(C2/C0)); }
        @Override
        public long toMicros(long d)  { return x(d, C2/C1, MAX/(C2/C1)); }
        @Override
        public long toMillis(long d)  { return d; }
        @Override
        public long toSeconds(long d) { return d/(C3/C2); }
        @Override
        public long toMinutes(long d) { return d/(C4/C2); }
        @Override
        public long toHours(long d)   { return d/(C5/C2); }
        @Override
        public long toDays(long d)    { return d/(C6/C2); }
        @Override
        public long convert(long d, TimeUnit u) { return u.toMillis(d); }
        @Override
        int excessNanos(long d, long m) { return 0; }
    },

    //1秒
    SECONDS {
        @Override
        public long toNanos(long d)   { return x(d, C3/C0, MAX/(C3/C0)); }
        @Override
        public long toMicros(long d)  { return x(d, C3/C1, MAX/(C3/C1)); }
        @Override
        public long toMillis(long d)  { return x(d, C3/C2, MAX/(C3/C2)); }
        @Override
        public long toSeconds(long d) { return d; }
        @Override
        public long toMinutes(long d) { return d/(C4/C3); }
        @Override
        public long toHours(long d)   { return d/(C5/C3); }
        @Override
        public long toDays(long d)    { return d/(C6/C3); }
        @Override
        public long convert(long d, TimeUnit u) { return u.toSeconds(d); }
        @Override
        int excessNanos(long d, long m) { return 0; }
    },

    //1分钟
    MINUTES {
        @Override
        public long toNanos(long d)   { return x(d, C4/C0, MAX/(C4/C0)); }
        @Override
        public long toMicros(long d)  { return x(d, C4/C1, MAX/(C4/C1)); }
        @Override
        public long toMillis(long d)  { return x(d, C4/C2, MAX/(C4/C2)); }
        @Override
        public long toSeconds(long d) { return x(d, C4/C3, MAX/(C4/C3)); }
        @Override
        public long toMinutes(long d) { return d; }
        @Override
        public long toHours(long d)   { return d/(C5/C4); }
        @Override
        public long toDays(long d)    { return d/(C6/C4); }
        @Override
        public long convert(long d, TimeUnit u) { return u.toMinutes(d); }
        @Override
        int excessNanos(long d, long m) { return 0; }
    },

    //1小时
    HOURS {
        @Override
        public long toNanos(long d)   { return x(d, C5/C0, MAX/(C5/C0)); }
        @Override
        public long toMicros(long d)  { return x(d, C5/C1, MAX/(C5/C1)); }
        @Override
        public long toMillis(long d)  { return x(d, C5/C2, MAX/(C5/C2)); }
        @Override
        public long toSeconds(long d) { return x(d, C5/C3, MAX/(C5/C3)); }
        @Override
        public long toMinutes(long d) { return x(d, C5/C4, MAX/(C5/C4)); }
        @Override
        public long toHours(long d)   { return d; }
        @Override
        public long toDays(long d)    { return d/(C6/C5); }
        @Override
        public long convert(long d, TimeUnit u) { return u.toHours(d); }
        @Override
        int excessNanos(long d, long m) { return 0; }
    },

    //1天
    DAYS {
        @Override
        public long toNanos(long d)   { return x(d, C6/C0, MAX/(C6/C0)); }
        @Override
        public long toMicros(long d)  { return x(d, C6/C1, MAX/(C6/C1)); }
        @Override
        public long toMillis(long d)  { return x(d, C6/C2, MAX/(C6/C2)); }
        @Override
        public long toSeconds(long d) { return x(d, C6/C3, MAX/(C6/C3)); }
        @Override
        public long toMinutes(long d) { return x(d, C6/C4, MAX/(C6/C4)); }
        @Override
        public long toHours(long d)   { return x(d, C6/C5, MAX/(C6/C5)); }
        @Override
        public long toDays(long d)    { return d; }
        @Override
        public long convert(long d, TimeUnit u) { return u.toDays(d); }
        @Override
        int excessNanos(long d, long m) { return 0; }
    };

    static final long C0 = 1L;//1纳秒
    static final long C1 = C0 * 1000L;//1微秒
    static final long C2 = C1 * 1000L;//1毫秒
    static final long C3 = C2 * 1000L;//1秒
    static final long C4 = C3 * 60L;//1分
    static final long C5 = C4 * 60L;//1小时
    static final long C6 = C5 * 24L;//1天

    static final long MAX = Long.MAX_VALUE;


    static long x(long d, long m, long over) {
        if (d >  over) {
            return Long.MAX_VALUE;
        }
        if (d < -over) {
            return Long.MIN_VALUE;
        }
        return d * m;
    }

    abstract int excessNanos(long d, long m);

    //将给定单元的时间段转换到此单元
    public long convert(long sourceDuration, TimeUnit sourceUnit) {
        throw new AbstractMethodError();
    }

    //等效于 NANOSECONDS.convert(duration, this)
    public long toNanos(long duration) {
        throw new AbstractMethodError();
    }

    //等效于 MICROSECONDS.convert(duration, this)
    public long toMicros(long duration) {
        throw new AbstractMethodError();
    }

    //等效于 MILLISECONDS.convert(duration, this)
    public long toMillis(long duration) {
        throw new AbstractMethodError();
    }

    //等效于 SECONDS.convert(duration, this)
    public long toSeconds(long duration) {
        throw new AbstractMethodError();
    }

    //等效于 MINUTES.convert(duration, this)
    public long toMinutes(long duration) {
        throw new AbstractMethodError();
    }

    //等效于 HOURS.convert(duration, this)
    public long toHours(long duration) {
        throw new AbstractMethodError();
    }

    //等效于 DAYS.convert(duration, this)
    public long toDays(long duration) {
        throw new AbstractMethodError();
    }

    //使用此时间单元执行计时的 Object.wait
    public void timedWait(Object obj, long timeout) throws InterruptedException {
        if (timeout > 0) {
            long ms = toMillis(timeout);
            int ns = excessNanos(timeout, ms);
            obj.wait(ms, ns);
        }
    }

    //使用此时间单元执行计时的 Thread.join
    public void timedJoin(Thread thread, long timeout) throws InterruptedException {
        if (timeout > 0) {
            long ms = toMillis(timeout);
            int ns = excessNanos(timeout, ms);
            thread.join(ms, ns);
        }
    }

    //使用此单元执行 Thread.sleep.这是将时间参数转换为 Thread.sleep 方法所需格式的便捷方法
    public void sleep(long timeout) throws InterruptedException {
        if (timeout > 0) {
            long ms = toMillis(timeout);
            int ns = excessNanos(timeout, ms);
            Thread.sleep(ms, ns);
        }
    }

}