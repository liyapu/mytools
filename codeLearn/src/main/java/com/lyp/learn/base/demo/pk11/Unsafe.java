package com.lyp.learn.base.demo.pk11;

import java.security.*;
import java.lang.reflect.*;

import sun.misc.VM;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;


/**
 * 这个类提供了一个更底层的操作并且应该在受信任的代码中使用。
 * 可以通过内存地址存取fields,如果给出的内存地址是无效的那么会有一个不确定的运行表现。
 * A collection of methods for performing low-level, unsafe operations.
 * Although the class and all methods are public, use of this class is
 * limited because only trusted code can obtain instances of it.
 *
 * @author John R. Rose
 * @see #getUnsafe
 */

public final class Unsafe {

    private static native void registerNatives();
    static {
        registerNatives();
        Reflection.registerMethodsToFilter(Unsafe.class, "getUnsafe");
    }

    private Unsafe() {}

    /**
     * 获取Unsafe的单例(单例模式),这个方法调用应该防止在不可信的代码中实例，
     * 因为unsafe类提供了一个低级别的操作，例如直接内存存取
     */
    private static final Unsafe theUnsafe = new Unsafe();

    /**
     * 获取Unsafe实例静态方法
     *
     * 为调用者提供执行不安全操作的能力。
     * 返回的Unsafe对象应该由调用者小心保护，因为它可以用于在任意内存地址读取和写入数据。
     * 绝不能将其传递给不受信任的代码。
     *
     * 此类中的大多数方法都是非常低级的，并且对应于少量硬件指令（在典型的机器上）。
     * 鼓励编译器相应地优化这些方法。
     *
     * 以下是使用不安全操作的建议习惯用法：
     *
     * class MyTrustedClass {
     *   private static final Unsafe unsafe = Unsafe.getUnsafe（）;
     *   ...
     *   private long myCountAddress = ...;
     *   public int getCount（）{return unsafe.getByte（myCountAddress）; }
     * }
     * （它可以帮助编译器生成局部变量 final。）
     *
     * 初始化的代码主要包括调用JVM本地方法registerNatives()和sun.reflect.Reflection#registerMethodsToFilter。
     * 然后新建一个Unsafe实例命名为theUnsafe，通过静态方法getUnsafe()获取，获取的时候需要做权限判断。
     * 由此可见，Unsafe使用了单例设计(可见构造私有化了)。
     * Unsafe类做了限制，如果是普通的调用的话，它会抛出一个SecurityException异常；
     * 只有由主类加载器(BootStrap classLoader)加载的类才能调用这个类中的方法。
     * 最简单的使用方式是基于反射获取Unsafe实例。
     *
     * Field f = Unsafe.class.getDeclaredField("theUnsafe");
     * f.setAccessible(true);
     * Unsafe unsafe = (Unsafe) f.get(null);
     *
     *
     * Provides the caller with the capability of performing unsafe
     * operations.
     *
     * <p> The returned <code>Unsafe</code> object should be carefully guarded
     * by the caller, since it can be used to read and write data at arbitrary
     * memory addresses.  It must never be passed to untrusted code.
     *
     * <p> Most methods in this class are very low-level, and correspond to a
     * small number of hardware instructions (on typical machines).  Compilers
     * are encouraged to optimize these methods accordingly.
     *
     * <p> Here is a suggested idiom for using unsafe operations:
     *
     * <blockquote><pre>
     * class MyTrustedClass {
     *   private static final Unsafe unsafe = Unsafe.getUnsafe();
     *   ...
     *   private long myCountAddress = ...;
     *   public int getCount() { return unsafe.getByte(myCountAddress); }
     * }
     * </pre></blockquote>
     *
     * (It may assist compilers to make the local variable be
     * <code>final</code>.)
     *
     * @exception  SecurityException  if a security manager exists and its
     *             <code>checkPropertiesAccess</code> method doesn't allow
     *             access to the system properties.
     */
    @CallerSensitive
    public static Unsafe getUnsafe() {
        Class<?> caller = Reflection.getCallerClass();
        if (!VM.isSystemDomainLoader(caller.getClassLoader())) {
            throw new SecurityException("Unsafe");
        }
        return theUnsafe;
    }

    /// peek and poke operations
    /// (compilers should optimize these to memory ops)

    // These work on object fields in the Java heap.
    // They will not work on elements of packed arrays.

    /**
     * 从给定的Java变量中获取值。
     * 更具体地说，获取给定的字段或数组元素o给定偏移处的对象，或（如果o是从数据值给定的内存地址开始偏移。
     *
     * 除非下列情况之一成立，否则结果未定义：
     *
     * 偏移量是从#objectFieldOffset   on 获得的一些Java字段和对象的java.lang.reflect.Field所提到的o是与之相容的类田野的班级。
     *
     * 偏移量和对象引用o（null或非null）都是通过#staticFieldOffset和#staticFieldBase（分别）获得的一些Java字段的反射字段表示。
     *
     * 引用的对象o是数组和偏移量是形式的整数B+N*S，
     * 其中N是一个有效的索引的阵列，且B和S被由数组类中的#arrayBaseOffset和#arrayIndexScale（分别）获得的值。
     * 价值引用的是数组的N第th个元素。
     *
     *
     * 如果上述情况之一为真，则调用引用特定的Java变量（字段或数组元素）。
     * 但是，结果未定义如果该变量实际上不是此方法返回的类型。
     *
     *
     * 该方法通过两个参数来引用变量，等等它提供（实际上）双寄存器寻址模式
     * 对于Java变量。
     * 当对象引用为null时，此方法使用其偏移量作为绝对地址。
     * 这在操作上类似诸如#getInt（long）之类的方法，它为非Java变量提供（实际上）单寄存器寻址模式。
     * 但是，因为Java变量在内存中可能有不同的布局从非Java变量，程序员不应该假设这些两种寻址模式相当。
     * 此外，程序员应该
     * 请记住，双寄存器寻址模式的偏移不能与单寄存器寻址中使用的long很容易混淆模式。
     *
     *
     * Fetches a value from a given Java variable.
     * More specifically, fetches a field or array element within the given
     * object <code>o</code> at the given offset, or (if <code>o</code> is
     * null) from the memory address whose numerical value is the given
     * offset.
     * <p>
     * The results are undefined unless one of the following cases is true:
     * <ul>
     * <li>The offset was obtained from {@link #objectFieldOffset} on
     * the {@link Field} of some Java field and the object
     * referred to by <code>o</code> is of a class compatible with that
     * field's class.
     *
     * <li>The offset and object reference <code>o</code> (either null or
     * non-null) were both obtained via {@link #staticFieldOffset}
     * and {@link #staticFieldBase} (respectively) from the
     * reflective {@link Field} representation of some Java field.
     *
     * <li>The object referred to by <code>o</code> is an array, and the offset
     * is an integer of the form <code>B+N*S</code>, where <code>N</code> is
     * a valid index into the array, and <code>B</code> and <code>S</code> are
     * the values obtained by {@link #arrayBaseOffset} and {@link
     * #arrayIndexScale} (respectively) from the array's class.  The value
     * referred to is the <code>N</code><em>th</em> element of the array.
     *
     * </ul>
     * <p>
     * If one of the above cases is true, the call references a specific Java
     * variable (field or array element).  However, the results are undefined
     * if that variable is not in fact of the type returned by this method.
     * <p>
     * This method refers to a variable by means of two parameters, and so
     * it provides (in effect) a <em>double-register</em> addressing mode
     * for Java variables.  When the object reference is null, this method
     * uses its offset as an absolute address.  This is similar in operation
     * to methods such as {@link #getInt(long)}, which provide (in effect) a
     * <em>single-register</em> addressing mode for non-Java variables.
     * However, because Java variables may have a different layout in memory
     * from non-Java variables, programmers should not assume that these
     * two addressing modes are ever equivalent.  Also, programmers should
     * remember that offsets from the double-register addressing mode cannot
     * be portably confused with longs used in the single-register addressing
     * mode.
     *
     * @param o Java heap object in which the variable resides, if any, else
     *        null
     * @param offset indication of where the variable resides in a Java heap
     *        object, if any, else a memory address locating the variable
     *        statically
     * @return the value fetched from the indicated Java variable
     * @throws RuntimeException No defined exceptions are thrown, not even
     *         {@link NullPointerException}
     */
    public native int getInt(Object o, long offset);

    /**
     * 设置值，不管java的访问限制，其他有类似的putInt,putDouble，putLong，putChar等等
     *
     * 将值存储到给定的Java变量中。
     *
     * 前两个参数的解释完全如下#getInt（Object，long）   引用一个特定的Java变量（字段或数组元素）。
     * 给定的值存储在该变量中。
     *
     * 变量必须与方法的类型相同参数x。
     * Stores a value into a given Java variable.
     * <p>
     * The first two parameters are interpreted exactly as with
     * {@link #getInt(Object, long)} to refer to a specific
     * Java variable (field or array element).  The given value
     * is stored into that variable.
     * <p>
     * The variable must be of the same type as the method
     * parameter <code>x</code>.
     *
     * @param o Java heap object in which the variable resides, if any, else
     *        null
     * @param offset indication of where the variable resides in a Java heap
     *        object, if any, else a memory address locating the variable
     *        statically
     * @param x the value to store into the indicated Java variable
     * @throws RuntimeException No defined exceptions are thrown, not even
     *         {@link NullPointerException}
     */
    public native void putInt(Object o, long offset, int x);

    /**
     * 获取值，不管java的访问限制，其他有类似的getInt，getDouble，getLong，getChar等等
     * Fetches a reference value from a given Java variable.
     * @see #getInt(Object, long)
     */
    public native Object getObject(Object o, long offset);

    /**
     * 将引用值存储到给定的Java变量中。
     *
     * 除非x存储的引用为null或匹配字段类型，结果是未定义的。
     * 如果引用o非空，则汽车标记或该对象的其他商店障碍（如果VM需要它们）更新。
     *
     * Stores a reference value into a given Java variable.
     * <p>
     * Unless the reference <code>x</code> being stored is either null
     * or matches the field type, the results are undefined.
     * If the reference <code>o</code> is non-null, car marks or
     * other store barriers for that object (if the VM requires them)
     * are updated.
     * @see #putInt(Object, int, int)
     */
    public native void putObject(Object o, long offset, Object x);

    /** @see #getInt(Object, long) */
    public native boolean getBoolean(Object o, long offset);
    /** @see #putInt(Object, int, int) */
    public native void    putBoolean(Object o, long offset, boolean x);
    /** @see #getInt(Object, long) */
    public native byte    getByte(Object o, long offset);
    /** @see #putInt(Object, int, int) */
    public native void    putByte(Object o, long offset, byte x);
    /** @see #getInt(Object, long) */
    public native short   getShort(Object o, long offset);
    /** @see #putInt(Object, int, int) */
    public native void    putShort(Object o, long offset, short x);
    /** @see #getInt(Object, long) */
    public native char    getChar(Object o, long offset);
    /** @see #putInt(Object, int, int) */
    public native void    putChar(Object o, long offset, char x);
    /** @see #getInt(Object, long) */
    public native long    getLong(Object o, long offset);
    /** @see #putInt(Object, int, int) */
    public native void    putLong(Object o, long offset, long x);
    /** @see #getInt(Object, long) */
    public native float   getFloat(Object o, long offset);
    /** @see #putInt(Object, int, int) */
    public native void    putFloat(Object o, long offset, float x);
    /** @see #getInt(Object, long) */
    public native double  getDouble(Object o, long offset);
    /** @see #putInt(Object, int, int) */
    public native void    putDouble(Object o, long offset, double x);

    /**
     * This method, like all others with 32-bit offsets, was native
     * in a previous release but is now a wrapper which simply casts
     * the offset to a long value.  It provides backward compatibility
     * with bytecodes compiled against 1.4.
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public int getInt(Object o, int offset) {
        return getInt(o, (long)offset);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public void putInt(Object o, int offset, int x) {
        putInt(o, (long)offset, x);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public Object getObject(Object o, int offset) {
        return getObject(o, (long)offset);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public void putObject(Object o, int offset, Object x) {
        putObject(o, (long)offset, x);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public boolean getBoolean(Object o, int offset) {
        return getBoolean(o, (long)offset);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public void putBoolean(Object o, int offset, boolean x) {
        putBoolean(o, (long)offset, x);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public byte getByte(Object o, int offset) {
        return getByte(o, (long)offset);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public void putByte(Object o, int offset, byte x) {
        putByte(o, (long)offset, x);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public short getShort(Object o, int offset) {
        return getShort(o, (long)offset);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public void putShort(Object o, int offset, short x) {
        putShort(o, (long)offset, x);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public char getChar(Object o, int offset) {
        return getChar(o, (long)offset);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public void putChar(Object o, int offset, char x) {
        putChar(o, (long)offset, x);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public long getLong(Object o, int offset) {
        return getLong(o, (long)offset);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public void putLong(Object o, int offset, long x) {
        putLong(o, (long)offset, x);
    }


    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public float getFloat(Object o, int offset) {
        return getFloat(o, (long)offset);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public void putFloat(Object o, int offset, float x) {
        putFloat(o, (long)offset, x);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public double getDouble(Object o, int offset) {
        return getDouble(o, (long)offset);
    }

    /**
     * As - 1.4.1，将32位偏移量参数转换为long
     */
    @Deprecated
    public void putDouble(Object o, int offset, double x) {
        putDouble(o, (long)offset, x);
    }

    // These work on values in the C heap.

    /**
     * 从给定的内存地址中获取值。
     * 如果地址为零，或没有指向从#allocateMemory获得的块，结果未定义。
     * Fetches a value from a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #allocateMemory
     */
    public native byte    getByte(long address);

    /**
     * Stores a value into a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #getByte(long)
     */
    public native void    putByte(long address, byte x);

    /** @see #getByte(long) */
    public native short   getShort(long address);
    /** @see #putByte(long, byte) */
    public native void    putShort(long address, short x);
    /** @see #getByte(long) */
    public native char    getChar(long address);
    /** @see #putByte(long, byte) */
    public native void    putChar(long address, char x);
    /** @see #getByte(long) */
    public native int     getInt(long address);
    /** @see #putByte(long, byte) */
    public native void    putInt(long address, int x);
    /** @see #getByte(long) */
    public native long    getLong(long address);
    /** @see #putByte(long, byte) */
    public native void    putLong(long address, long x);
    /** @see #getByte(long) */
    public native float   getFloat(long address);
    /** @see #putByte(long, byte) */
    public native void    putFloat(long address, float x);
    /** @see #getByte(long) */
    public native double  getDouble(long address);
    /** @see #putByte(long, byte) */
    public native void    putDouble(long address, double x);

    /**
     * 从一个给定的内存地址获取本地指针，如果不是allocateMemory方法的，结果将不确定
     *
     * 从给定的内存地址获取本机指针。如果地址是零，或者不指向从#allocateMemory获得的块，结果是未定义的。
     *
     *  如果本机指针的宽度小于64位，则将其扩展为一个Java长的无符号数。
     *  指针可以由任何索引给定字节偏移量，只需将该偏移量（作为一个简单整数）添加到表示指针的long。
     *  实际读取的字节数从目标地址可以通过咨询#addressSize来确定。
     *
     * Fetches a native pointer from a given memory address.  If the address is
     * zero, or does not point into a block obtained from {@link
     * #allocateMemory}, the results are undefined.
     *
     * <p> If the native pointer is less than 64 bits wide, it is extended as
     * an unsigned number to a Java long.  The pointer may be indexed by any
     * given byte offset, simply by adding that offset (as a simple integer) to
     * the long representing the pointer.  The number of bytes actually read
     * from the target address maybe determined by consulting {@link
     * #addressSize}.
     *
     * @see #allocateMemory
     */
    public native long getAddress(long address);

    /**
     * 存储一个本地指针到一个给定的内存地址,如果地址不是allocateMemory方法的，结果将不确定
     * 将本机指针存储到给定的内存地址中。如果地址是零，或者不指向从#allocateMemory获得的块，结果是未定义的。
     *
     *  实际写入目标地址的字节数可能是通过咨询#addressSize确定。
     * Stores a native pointer into a given memory address.  If the address is
     * zero, or does not point into a block obtained from {@link
     * #allocateMemory}, the results are undefined.
     *
     * <p> The number of bytes actually written at the target address maybe
     * determined by consulting {@link #addressSize}.
     *
     * @see #getAddress(long)
     */
    public native void putAddress(long address, long x);

    /// wrappers for malloc, realloc, free:

    /**
     * 分配内存
     * 分配给定大小的新的本机内存块（以字节为单位）。
     * 该内存的内容未初始化; 他们通常会垃圾。生成的本机指针永远不会为零，并且将是对齐所有值类型。
     * 通过调用#freeMemory来处理这个内存，或者用#reallocateMemory调整它的大小。
     * Allocates a new block of native memory, of the given size in bytes.  The
     * contents of the memory are uninitialized; they will generally be
     * garbage.  The resulting native pointer will never be zero, and will be
     * aligned for all value types.  Dispose of this memory by calling {@link
     * #freeMemory}, or resize it with {@link #reallocateMemory}.
     *
     * @throws IllegalArgumentException if the size is negative or too large
     *         for the native size_t type
     *
     * @throws OutOfMemoryError if the allocation is refused by the system
     *
     * @see #getByte(long)
     * @see #putByte(long, byte)
     */
    public native long allocateMemory(long bytes);

    /**
     * 扩充内存
     * 将新的本机内存块调整为给定大小（以字节为单位）。
     * 该新块的内容超过了旧块的大小未初始化的; 他们通常会是垃圾。
     * 由此产生的原生当且仅当请求的大小为零时，指针才为零。
     * 该生成的本机指针将与所有值类型对齐。
     * 部署通过调用#freeMemory来调用此内存，或使用#reallocateMemory调整其大小。传递给此方法的地址可能为null
     * 在哪种情况下将执行分配。
     * Resizes a new block of native memory, to the given size in bytes.  The
     * contents of the new block past the size of the old block are
     * uninitialized; they will generally be garbage.  The resulting native
     * pointer will be zero if and only if the requested size is zero.  The
     * resulting native pointer will be aligned for all value types.  Dispose
     * of this memory by calling {@link #freeMemory}, or resize it with {@link
     * #reallocateMemory}.  The address passed to this method may be null, in
     * which case an allocation will be performed.
     *
     * @throws IllegalArgumentException if the size is negative or too large
     *         for the native size_t type
     *
     * @throws OutOfMemoryError if the allocation is refused by the system
     *
     * @see #allocateMemory
     */
    public native long reallocateMemory(long address, long bytes);

    /**
     * 将给定内存块中的所有字节设置为固定值（通常为零）。
     *
     * 此方法通过两个参数确定块的基址，
     * 因此它提供（实际上）双寄存器寻址模式，
     * 正如#getInt（Object，long）中所讨论的那样。当对象引用为null时，偏移提供绝对基址。
     *
     * 存储是确定大小的连贯（原子）单位通过地址和长度参数。
     * 如果有效地址和长度均为模8，存储以“长”单位进行。
     * 如果有效地址和长度（即相应）甚至模4或2，商店以'int'或'short'为单位进行。
     *
     * Sets all bytes in a given block of memory to a fixed value
     * (usually zero).
     *
     * <p>This method determines a block's base address by means of two parameters,
     * and so it provides (in effect) a <em>double-register</em> addressing mode,
     * as discussed in {@link #getInt(Object,long)}.  When the object reference is null,
     * the offset supplies an absolute base address.
     *
     * <p>The stores are in coherent (atomic) units of a size determined
     * by the address and length parameters.  If the effective address and
     * length are all even modulo 8, the stores take place in 'long' units.
     * If the effective address and length are (resp.) even modulo 4 or 2,
     * the stores take place in units of 'int' or 'short'.
     *
     * @since 1.7
     */
    public native void setMemory(Object o, long offset, long bytes, byte value);

    /**
     * 在给定的内存块中设置值
     * 将给定内存块中的所有字节设置为固定值（通常为零）。
     * 这提供了单寄存器寻址模式，如#getInt（Object，long）中所述。
     * 相当于setMemory(null, address, bytes, value)。
     *
     * Sets all bytes in a given block of memory to a fixed value
     * (usually zero).  This provides a <em>single-register</em> addressing mode,
     * as discussed in {@link #getInt(Object,long)}.
     *
     * <p>Equivalent to <code>setMemory(null, address, bytes, value)</code>.
     */
    public void setMemory(long address, long bytes, byte value) {
        setMemory(null, address, bytes, value);
    }

    /**
     * 将给定内存块中的所有字节设置为另一个内存的副本块。
     *
     * 此方法通过两个参数确定每个块的基址，因此它提供（实际上）双寄存器寻址模式，
     * 正如#getInt（Object，long）中所讨论的那样。当对象引用为null时，偏移提供绝对基址。
     *
     *
     * 转移是确定大小的相干（原子）单位通过地址和长度参数。
     * 如果有效地址和长度均为模8，转移以“长”单位进行。
     * 如果有效地址和长度（即相等）甚至模4或2，转移以'int'或'short'为单位进行。
     *
     * Sets all bytes in a given block of memory to a copy of another
     * block.
     *
     * <p>This method determines each block's base address by means of two parameters,
     * and so it provides (in effect) a <em>double-register</em> addressing mode,
     * as discussed in {@link #getInt(Object,long)}.  When the object reference is null,
     * the offset supplies an absolute base address.
     *
     * <p>The transfers are in coherent (atomic) units of a size determined
     * by the address and length parameters.  If the effective addresses and
     * length are all even modulo 8, the transfer takes place in 'long' units.
     * If the effective addresses and length are (resp.) even modulo 4 or 2,
     * the transfer takes place in units of 'int' or 'short'.
     *
     * @since 1.7
     */
    public native void copyMemory(Object srcBase, long srcOffset,
                                  Object destBase, long destOffset,
                                  long bytes);
    /**
     * 从一个内存块拷贝到另一个内存块
     * 将给定内存块中的所有字节设置为另一个块的副本。
     * 这提供了单寄存器寻址模式，如#getInt（Object，long）中所述。
     * 相当于copyMemory(null, srcAddress, null, destAddress, bytes)。
     * Sets all bytes in a given block of memory to a copy of another
     * block.  This provides a <em>single-register</em> addressing mode,
     * as discussed in {@link #getInt(Object,long)}.
     *
     * Equivalent to <code>copyMemory(null, srcAddress, null, destAddress, bytes)</code>.
     */
    public void copyMemory(long srcAddress, long destAddress, long bytes) {
        copyMemory(null, srcAddress, null, destAddress, bytes);
    }

    /**
     * 释放内存
     * 处置从#allocateMemory   或#reallocateMemory获得的本机内存块。
     * 传递给的地址此方法可能为null，在这种情况下不采取任何操作。
     * Disposes of a block of native memory, as obtained from {@link
     * #allocateMemory} or {@link #reallocateMemory}.  The address passed to
     * this method may be null, in which case no action is taken.
     *
     * @see #allocateMemory
     */
    public native void freeMemory(long address);

    /// random queries

    /**
     * This constant differs from all results that will ever be returned from
     * {@link #staticFieldOffset}, {@link #objectFieldOffset},
     * or {@link #arrayBaseOffset}.
     */
    public static final int INVALID_FIELD_OFFSET   = -1;

    /**
     * Returns the offset of a field, truncated to 32 bits.
     * This method is implemented as follows:
     * <blockquote><pre>
     * public int fieldOffset(Field f) {
     *     if (Modifier.isStatic(f.getModifiers()))
     *         return (int) staticFieldOffset(f);
     *     else
     *         return (int) objectFieldOffset(f);
     * }
     * </pre></blockquote>
     * @deprecated As of 1.4.1, use {@link #staticFieldOffset} for static
     * fields and {@link #objectFieldOffset} for non-static fields.
     */
    @Deprecated
    public int fieldOffset(Field f) {
        if (Modifier.isStatic(f.getModifiers())) {
            return (int) staticFieldOffset(f);
        } else {
            return (int) objectFieldOffset(f);
        }
    }

    /**
     * Returns the base address for accessing some static field
     * in the given class.  This method is implemented as follows:
     * <blockquote><pre>
     * public Object staticFieldBase(Class c) {
     *     Field[] fields = c.getDeclaredFields();
     *     for (int i = 0; i < fields.length; i++) {
     *         if (Modifier.isStatic(fields[i].getModifiers())) {
     *             return staticFieldBase(fields[i]);
     *         }
     *     }
     *     return null;
     * }
     * </pre></blockquote>
     * @deprecated As of 1.4.1, use {@link #staticFieldBase(Field)}
     * to obtain the base pertaining to a specific {@link Field}.
     * This method works only for JVMs which store all statics
     * for a given class in one place.
     */
    @Deprecated
    public Object staticFieldBase(Class<?> c) {
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (Modifier.isStatic(fields[i].getModifiers())) {
                return staticFieldBase(fields[i]);
            }
        }
        return null;
    }

    /**
     * 该方法返回给定field的内存地址偏移量，这个值对于给定的filed是唯一的且是固定不变的
     *
     * 报告给定字段在其存储分配中的位置类。不要指望对此偏移量执行任何算术运算;
     * 它只是一个传递给不安全堆内存访问器的cookie。
     *
     * 任何给定的字段将始终具有相同的偏移量和基数，
     * 而不是同一类的两个不同字段将具有相同的偏移量和基地。
     *
     *
     * 从1.4.1开始，字段的偏移量表示为长值，
     * 虽然Sun JVM不使用最重要的32位。
     * 但是，JVM实现以静态方式存储静态字段
     * 地址可以使用长偏移量和空基指针来表示
     * 表格中的字段位置可由#getInt（Object，long）使用。
     * 因此，代码将在64位平台上移植到此类JVM
     * 必须保留所有静态字段偏移量。
     *
     * Report the location of a given field in the storage allocation of its
     * class.  Do not expect to perform any sort of arithmetic on this offset;
     * it is just a cookie which is passed to the unsafe heap memory accessors.
     *
     * <p>Any given field will always have the same offset and base, and no
     * two distinct fields of the same class will ever have the same offset
     * and base.
     *
     * <p>As of 1.4.1, offsets for fields are represented as long values,
     * although the Sun JVM does not use the most significant 32 bits.
     * However, JVM implementations which store static fields at absolute
     * addresses can use long offsets and null base pointers to express
     * the field locations in a form usable by {@link #getInt(Object,long)}.
     * Therefore, code which will be ported to such JVMs on 64-bit platforms
     * must preserve all bits of static field offsets.
     * @see #getInt(Object, long)
     */
    public native long staticFieldOffset(Field f);

    /**
     * 返回指定静态field的内存地址偏移量,在这个类的其他方法中这个值只是被用作一个访问特定field的一个方式。
     * 这个值对于 给定的field是唯一的，并且后续对该方法的调用都应该返回相同的值。
     *
     * 报告一个给定的字段的位置，不管这个字段是private，public还是保护类型，和staticFieldBase结合使用
     *
     * 报告给定静态字段的位置，与#staticFieldBase一起报告。
     * 不要指望对此偏移量执行任何算术运算;它只是一个传递给不安全堆内存访问器的cookie。
     *
     * 任何给定字段将始终具有相同的偏移量，并且没有两个不同的字段同一类的字段将具有相同的偏移量。
     *
     *
     * 从1.4.1开始，字段的偏移量表示为长值，虽然Sun JVM不使用最重要的32位。
     * 很难想象一个需要更多的JVM技术几个位来编码非数组对象中的偏移量，
     * 但是，为了与本课程中的其他方法保持一致，此方法将其结果报告为long值。
     *
     * Report the location of a given static field, in conjunction with {@link
     * #staticFieldBase}.
     * <p>Do not expect to perform any sort of arithmetic on this offset;
     * it is just a cookie which is passed to the unsafe heap memory accessors.
     *
     * <p>Any given field will always have the same offset, and no two distinct
     * fields of the same class will ever have the same offset.
     *
     * <p>As of 1.4.1, offsets for fields are represented as long values,
     * although the Sun JVM does not use the most significant 32 bits.
     * It is hard to imagine a JVM technology which needs more than
     * a few bits to encode an offset within a non-array object,
     * However, for consistency with other methods in this class,
     * this method reports its result as a long value.
     * @see #getInt(Object, long)
     */
    public native long objectFieldOffset(Field f);

    /**
     * 获取一个给定字段的位置
     * 报告给定静态字段的位置，与#staticFieldOffset一起报告。
     * 获取基础“对象”，如果有的话，使用其中的静态字段给定的类可以通过像#getInt（Object，很长）。该值可以为null。
     * 该值可以指对象这是一个“cookie”，不能保证是一个真正的对象，它应该除了作为get和put例程的参数之外，
     * 不得以任何方式使用这个类。
     * Report the location of a given static field, in conjunction with {@link
     * #staticFieldOffset}.
     * <p>Fetch the base "Object", if any, with which static fields of the
     * given class can be accessed via methods like {@link #getInt(Object,
     * long)}.  This value may be null.  This value may refer to an object
     * which is a "cookie", not guaranteed to be a real Object, and it should
     * not be used in any way except as argument to the get and put routines in
     * this class.
     */
    public native Object staticFieldBase(Field f);

    /**
     * Detect if the given class may need to be initialized. This is often
     * needed in conjunction with obtaining the static field base of a
     * class.
     * @return false only if a call to {@code ensureClassInitialized} would have no effect
     */
    public native boolean shouldBeInitialized(Class<?> c);

    /**
     * 确保给定class被初始化，这往往需要结合基类的静态域（field）
     * Ensure the given class has been initialized. This is often
     * needed in conjunction with obtaining the static field base of a
     * class.
     */
    public native void ensureClassInitialized(Class<?> c);

    /**
     * 可以获取数组第一个元素的偏移地址
     * 获取给定数组类型的存储分配中第一个元素的开始地址。
     * 如果#arrayIndexScale 返回非零值对于同一个类，
     * 你可以使用比例因子，组合基础地址，去访问给定类型的数组的新的地址元素
     * Report the offset of the first element in the storage allocation of a
     * given array class.  If {@link #arrayIndexScale} returns a non-zero value
     * for the same class, you may use that scale factor, together with this
     * base offset, to form new offsets to access elements of arrays of the
     * given class.
     *
     * @see #getInt(Object, long)
     * @see #putInt(Object, long, int)
     */
    public native int arrayBaseOffset(Class<?> arrayClass);

    /** The value of {@code arrayBaseOffset(boolean[].class)} */
    public static final int ARRAY_BOOLEAN_BASE_OFFSET
            = theUnsafe.arrayBaseOffset(boolean[].class);

    /** The value of {@code arrayBaseOffset(byte[].class)} */
    public static final int ARRAY_BYTE_BASE_OFFSET
            = theUnsafe.arrayBaseOffset(byte[].class);

    /** The value of {@code arrayBaseOffset(short[].class)} */
    public static final int ARRAY_SHORT_BASE_OFFSET
            = theUnsafe.arrayBaseOffset(short[].class);

    /** The value of {@code arrayBaseOffset(char[].class)} */
    public static final int ARRAY_CHAR_BASE_OFFSET
            = theUnsafe.arrayBaseOffset(char[].class);

    /** The value of {@code arrayBaseOffset(int[].class)} */
    public static final int ARRAY_INT_BASE_OFFSET
            = theUnsafe.arrayBaseOffset(int[].class);

    /** The value of {@code arrayBaseOffset(long[].class)} */
    public static final int ARRAY_LONG_BASE_OFFSET
            = theUnsafe.arrayBaseOffset(long[].class);

    /** The value of {@code arrayBaseOffset(float[].class)} */
    public static final int ARRAY_FLOAT_BASE_OFFSET
            = theUnsafe.arrayBaseOffset(float[].class);

    /** The value of {@code arrayBaseOffset(double[].class)} */
    public static final int ARRAY_DOUBLE_BASE_OFFSET
            = theUnsafe.arrayBaseOffset(double[].class);

    /** The value of {@code arrayBaseOffset(Object[].class)} */
    public static final int ARRAY_OBJECT_BASE_OFFSET
            = theUnsafe.arrayBaseOffset(Object[].class);

    /**
     * 可以获取数组的转换因子，也就是数组中元素的增量地址。
     * 将arrayBaseOffset与arrayIndexScale配合使用， 可以定位数组中每个元素在内存中的位置
     *
     * 报告存储中寻址元素的比例因子分配给定的数组类。
     * 但是，“窄”类型的数组通常不能与#getByte（Object，int）之类的访问器一起正常工作，
     * 因此会报告此类的比例因子为零。
     * Report the scale factor for addressing elements in the storage
     * allocation of a given array class.  However, arrays of "narrow" types
     * will generally not work properly with accessors like {@link
     * #getByte(Object, int)}, so the scale factor for such classes is reported
     * as zero.
     *
     * @see #arrayBaseOffset
     * @see #getInt(Object, long)
     * @see #putInt(Object, long, int)
     */
    public native int arrayIndexScale(Class<?> arrayClass);

    /** The value of {@code arrayIndexScale(boolean[].class)} */
    public static final int ARRAY_BOOLEAN_INDEX_SCALE
            = theUnsafe.arrayIndexScale(boolean[].class);

    /** The value of {@code arrayIndexScale(byte[].class)} */
    public static final int ARRAY_BYTE_INDEX_SCALE
            = theUnsafe.arrayIndexScale(byte[].class);

    /** The value of {@code arrayIndexScale(short[].class)} */
    public static final int ARRAY_SHORT_INDEX_SCALE
            = theUnsafe.arrayIndexScale(short[].class);

    /** The value of {@code arrayIndexScale(char[].class)} */
    public static final int ARRAY_CHAR_INDEX_SCALE
            = theUnsafe.arrayIndexScale(char[].class);

    /** The value of {@code arrayIndexScale(int[].class)} */
    public static final int ARRAY_INT_INDEX_SCALE
            = theUnsafe.arrayIndexScale(int[].class);

    /** The value of {@code arrayIndexScale(long[].class)} */
    public static final int ARRAY_LONG_INDEX_SCALE
            = theUnsafe.arrayIndexScale(long[].class);

    /** The value of {@code arrayIndexScale(float[].class)} */
    public static final int ARRAY_FLOAT_INDEX_SCALE
            = theUnsafe.arrayIndexScale(float[].class);

    /** The value of {@code arrayIndexScale(double[].class)} */
    public static final int ARRAY_DOUBLE_INDEX_SCALE
            = theUnsafe.arrayIndexScale(double[].class);

    /** The value of {@code arrayIndexScale(Object[].class)} */
    public static final int ARRAY_OBJECT_INDEX_SCALE
            = theUnsafe.arrayIndexScale(Object[].class);

    /**
     * 报告通过#putAddress存储的本机指针的大小（以字节为单位）。此值将为4或8.
     * 请注意大小确定其他原始类型（存储在本机存储器块中）完全靠他们的信息内容。
     * Report the size in bytes of a native pointer, as stored via {@link
     * #putAddress}.  This value will be either 4 or 8.  Note that the sizes of
     * other primitive types (as stored in native memory blocks) is determined
     * fully by their information content.
     */
    public native int addressSize();

    /** The value of {@code addressSize()} */
    public static final int ADDRESS_SIZE = theUnsafe.addressSize();

    /**
     * 获取本机内存的页数，这个值永远都是2的幂次方
     * Report the size in bytes of a native memory page (whatever that is).
     * This value will always be a power of two.
     */
    public native int pageSize();


    /// random trusted operations from JNI:

    /**
     * 告诉虚拟机定义了一个没有安全检查的类，默认情况下这个类加载器和保护域来着调用者类
     * Tell the VM to define a class, without security checks.  By default, the
     * class loader and protection domain come from the caller's class.
     */
    public native Class<?> defineClass(String name, byte[] b, int off, int len,
                                       ClassLoader loader,
                                       ProtectionDomain protectionDomain);

    /**
     * 定义一个类，但不要让类加载器或系统字典知道它。
     *
     * 对于每个CP条目，相应的CP补丁必须为null或具有与其标记匹配的格式：
     *
     *  Integer，Long，Float，Double：来自java.lang的相应包装器对象类型
     *  Utf8：一个字符串（如果用作签名或名称，必须具有合适的语法）
     *  类：任何java.lang.Class对象
     *  字符串：任何对象（不仅仅是java.lang.String）
     *  InterfaceMethodRef：（NYI）一个方法句柄，用于调用该调用站点的参数
     *
     * Define a class but do not make it known to the class loader or system dictionary.
     * <p>
     * For each CP entry, the corresponding CP patch must either be null or have
     * the a format that matches its tag:
     * <ul>
     * <li>Integer, Long, Float, Double: the corresponding wrapper object type from java.lang
     * <li>Utf8: a string (must have suitable syntax if used as signature or name)
     * <li>Class: any java.lang.Class object
     * <li>String: any object (not just a java.lang.String)
     * <li>InterfaceMethodRef: (NYI) a method handle to invoke on that call site's arguments
     * </ul>
     * @params hostClass context for linkage, access control, protection domain, and class loader
     * @params data      bytes of a class file
     * @params cpPatches where non-null entries exist, they replace corresponding CP entries in data
     */
    public native Class<?> defineAnonymousClass(Class<?> hostClass, byte[] data, Object[] cpPatches);


    /**
     * 创建一个类的实例，不需要调用它的构造函数、初使化代码、各种JVM安全检查以及其它的一些底层的东西。
     * 即使构造函数是私有，我们也可以通过这个方法创建它的实例,对于单例模式，简直是噩梦，哈哈
     *
     * 分配实例但不运行任何构造函数。
     * 如果尚未进行，则初始化该类。
     * Allocate an instance but do not run any constructor.
     * Initializes the class if it has not yet been.
     */
    public native Object allocateInstance(Class<?> cls)
            throws InstantiationException;

    /**
     * 锁定对象，必须是没有被锁的
     * Lock the object.  It must get unlocked via {@link #monitorExit}. */
    @Deprecated
    public native void monitorEnter(Object o);

    /**
     * 解锁对象
     * Unlock the object.  It must have been locked via {@link
     * #monitorEnter}.
     */
    @Deprecated
    public native void monitorExit(Object o);

    /**
     * 试图锁定对象，返回true或false是否锁定成功，如果锁定，必须用monitorExit解锁
     *
     * Tries to lock the object.  Returns true or false to indicate
     * whether the lock succeeded.  If it did, the object must be
     * unlocked via {@link #monitorExit}.
     */
    @Deprecated
    public native boolean tryMonitorEnter(Object o);

    /**
     * 引发异常，没有通知
     * Throw the exception without telling the verifier. */
    public native void throwException(Throwable ee);


    /**
     * CAS，
     * 如果对象偏移量上的值=期待值，更新为x,返回true.否则false.
     * 类似的有compareAndSwapInt,compareAndSwapLong,compareAndSwapBoolean,compareAndSwapChar等等。
     *
     * 在obj的offset位置比较object field和期望的值，如果相同则更新。
     * 这个方法的操作应该是原子的，因此提供了一种不可中断的方式更新object field。
     *
     * @param o the object containing the field to modify.包含要修改field的对象
     * @param offset the offset of the object field within <code>obj</code>.
     *                   <code>o</code>中object型field的偏移量
     * @param expected the expected value of the field.
     *                  希望field中存在的值
     * @param x the new value of the field if it equals <code>expect</code>.
     *                   如果期望值expect与field的当前值相同，设置filed的值为这个新值
     *
     * @return true if the field was changed.
     *                如果field的值被更改
     */
    public final native boolean compareAndSwapObject(Object o, long offset,
                                                     Object expected,
                                                     Object x);

    /**
     * 比较obj的offset处内存位置中的值和期望的值，如果相同则原子操作更新。此更新是不可中断的。
     * Atomically update Java variable to <tt>x</tt> if it is currently
     * holding <tt>expected</tt>.
     * @return <tt>true</tt> if successful
     *
     * @param obj 需要更新的对象
     * @param offset obj中整型field的偏移量
     * @param expect 希望field中存在的值
     * @param update 如果期望值expect与field的当前值相同，设置filed的值为这个新值
     * @return 如果field的值被更改返回true
     */
    public native boolean compareAndSwapInt(Object obj, long offset, int expect, int update);

    /**
     * Atomically update Java variable to <tt>x</tt> if it is currently
     * holding <tt>expected</tt>.
     * @return <tt>true</tt> if successful
     */
    public final native boolean compareAndSwapLong(Object o, long offset,
                                                   long expected,
                                                   long x);

    /**
     * 该方法获取对象中offset偏移地址对应的整型field的值,支持volatile load语义。
     * 类似的方法有getIntVolatile，getBooleanVolatile等等
     * Fetches a reference value from a given Java variable, with volatile
     * load semantics. Otherwise identical to {@link #getObject(Object, long)}
     */
    public native Object getObjectVolatile(Object o, long offset);

    /**
     * Stores a reference value into a given Java variable, with
     * volatile store semantics. Otherwise identical to {@link #putObject(Object, long, Object)}
     */
    public native void    putObjectVolatile(Object o, long offset, Object x);

    /***
     * Retrieves the value of the integer field at the specified offset in the
     * supplied object with volatile load semantics.
     * 获取obj对象中offset偏移地址对应的整型field的值,支持volatile load语义。
     *Volatile version of {@link #getInt(Object, long)}
     * @param obj the object containing the field to read.
     *    包含需要去读取的field的对象
     * @param offset the offset of the integer field within <code>obj</code>.
     *       <code>obj</code>中整型field的偏移量
     */
    public native int getIntVolatile(Object obj, long offset);

    /***
     * Sets the value of the integer field at the specified offset in the
     * supplied object to the given value, with volatile store semantics.
     * 设置obj对象中offset偏移地址对应的整型field的值为指定值。支持volatile store语义
     *Volatile version of {@link #putInt(Object, long, int)}
     * @param obj the object containing the field to modify.
     *    包含需要修改field的对象
     * @param offset the offset of the integer field within <code>obj</code>.
     *       <code>obj</code>中整型field的偏移量
     * @param value the new value of the field.
     *       field将被设置的新值
     */
    public native void putIntVolatile(Object obj, long offset, int value);

    /** Volatile version of {@link #getBoolean(Object, long)}  */
    public native boolean getBooleanVolatile(Object o, long offset);

    /** Volatile version of {@link #putBoolean(Object, long, boolean)}  */
    public native void    putBooleanVolatile(Object o, long offset, boolean x);

    /** Volatile version of {@link #getByte(Object, long)}  */
    public native byte    getByteVolatile(Object o, long offset);

    /** Volatile version of {@link #putByte(Object, long, byte)}  */
    public native void    putByteVolatile(Object o, long offset, byte x);

    /** Volatile version of {@link #getShort(Object, long)}  */
    public native short   getShortVolatile(Object o, long offset);

    /** Volatile version of {@link #putShort(Object, long, short)}  */
    public native void    putShortVolatile(Object o, long offset, short x);

    /** Volatile version of {@link #getChar(Object, long)}  */
    public native char    getCharVolatile(Object o, long offset);

    /** Volatile version of {@link #putChar(Object, long, char)}  */
    public native void    putCharVolatile(Object o, long offset, char x);

    /** Volatile version of {@link #getLong(Object, long)}  */
    public native long    getLongVolatile(Object o, long offset);


    /***
     * Sets the value of the long field at the specified offset in the
     * supplied object to the given value, with volatile store semantics.
     * 设置obj对象中offset偏移地址对应的long型field的值为指定值。支持volatile store语义
     *Volatile version of {@link #putLong(Object, long, long)}
     * @param obj the object containing the field to modify.
     *            包含需要修改field的对象
     * @param offset the offset of the long field within <code>obj</code>.
     *               <code>obj</code>中long型field的偏移量
     * @param value the new value of the field.
     *              field将被设置的新值
     * @see #putLong(Object,long,long)
     */
    public native void putLongVolatile(Object obj, long offset, long value);

    /** Volatile version of {@link #getFloat(Object, long)}  */
    public native float   getFloatVolatile(Object o, long offset);

    /** Volatile version of {@link #putFloat(Object, long, float)}  */
    public native void    putFloatVolatile(Object o, long offset, float x);

    /** Volatile version of {@link #getDouble(Object, long)}  */
    public native double  getDoubleVolatile(Object o, long offset);

    /** Volatile version of {@link #putDouble(Object, long, double)}  */
    public native void    putDoubleVolatile(Object o, long offset, double x);

     /***
     * Sets the value of the object field at the specified offset in the
     * supplied object to the given value.  This is an ordered or lazy
     * version of <code>putObjectVolatile(Object,long,Object)</code>, which
     * doesn't guarantee the immediate visibility of the change to other
     * threads.  It is only really useful where the object field is
     * <code>volatile</code>, and is thus expected to change unexpectedly.
     * 设置obj对象中offset偏移地址对应的object型field的值为指定值。这是一个有序或者
     * 有延迟的<code>putObjectVolatile</cdoe>方法，并且不保证值的改变被其他线程立
     * 即看到。只有在field被<code>volatile</code>修饰并且期望被意外修改的时候
     * 使用才有用。
     *
     * @param obj the object containing the field to modify.
     *    包含需要修改field的对象
     * @param offset the offset of the object field within <code>obj</code>.
     *       <code>obj</code>中long型field的偏移量
     * @param value the new value of the field.
     *      field将被设置的新值
     */
    public native void putOrderedObject(Object obj, long offset, Object value);


    /***
     * Sets the value of the integer field at the specified offset in the
     * supplied object to the given value.  This is an ordered or lazy
     * version of <code>putIntVolatile(Object,long,int)</code>, which
     * doesn't guarantee the immediate visibility of the change to other
     * threads.  It is only really useful where the integer field is
     * <code>volatile</code>, and is thus expected to change unexpectedly.
     * 设置obj对象中offset偏移地址对应的整型field的值为指定值。这是一个有序或者
     * 有延迟的<code>putIntVolatile</cdoe>方法，并且不保证值的改变被其他线程立
     * 即看到。只有在field被<code>volatile</code>修饰并且期望被意外修改的时候
     * 使用才有用。
     * Ordered/Lazy version of {@link #putIntVolatile(Object, long, int)}
     * @param obj the object containing the field to modify.
     *    包含需要修改field的对象
     * @param offset the offset of the integer field within <code>obj</code>.
     *       <code>obj</code>中整型field的偏移量
     * @param value the new value of the field.
     *      field将被设置的新值
     * @see #putIntVolatile(Object,long,int)
     */
    public native void putOrderedInt(Object obj, long offset, int value);

    /***
     * Sets the value of the long field at the specified offset in the
     * supplied object to the given value.  This is an ordered or lazy
     * version of <code>putLongVolatile(Object,long,long)</code>, which
     * doesn't guarantee the immediate visibility of the change to other
     * threads.  It is only really useful where the long field is
     * <code>volatile</code>, and is thus expected to change unexpectedly.
     * 设置obj对象中offset偏移地址对应的long型field的值为指定值。这是一个有序或者
     * 有延迟的<code>putLongVolatile</cdoe>方法，并且不保证值的改变被其他线程立
     * 即看到。只有在field被<code>volatile</code>修饰并且期望被意外修改的时候
     * 使用才有用。
     * Ordered/Lazy version of {@link #putLongVolatile(Object, long, long)}
     * @param obj the object containing the field to modify.
     *    包含需要修改field的对象
     * @param offset the offset of the long field within <code>obj</code>.
     *       <code>obj</code>中long型field的偏移量
     * @param value the new value of the field.
     *      field将被设置的新值
     * @see #putLongVolatile(Object,long,long)
     */
    public native void putOrderedLong(Object obj, long offset, long value);

    /**
     * 终止挂起的线程，恢复正常.
     * java.util.concurrent包中挂起操作都是在LockSupport类实现的，也正是使用这两个方法
     *
     * 取消阻止在停放时阻止的给定线程，或者，如果是，则取消阻止
     * 未被阻止，导致后续呼叫停止不到
     * 块。注意：此操作仅仅是因为“不安全”
     * 调用者必须以某种方式确保线程没有
     * 销毁。通常不需要特别的东西来确保这一点
     * 从Java调用时（通常会有一个直播）
     * 引用线程）但这几乎不是自动的
     * 所以从本机代码调用时。
     *
     * 释放被<a href="#park"><code>park</code></a>创建的在一个线程上的阻塞.这个
     * 方法也可以被使用来终止一个先前调用<code>park</code>导致的阻塞.
     * 这个操作操作时不安全的,因此线程必须保证是活的.这是java代码不是native代码。
     * @param thread the thread to unblock.
     *           要解除阻塞的线程
     * Unblock the given thread blocked on <tt>park</tt>, or, if it is
     * not blocked, cause the subsequent call to <tt>park</tt> not to
     * block.  Note: this operation is "unsafe" solely because the
     * caller must somehow ensure that the thread has not been
     * destroyed. Nothing special is usually required to ensure this
     * when called from Java (in which there will ordinarily be a live
     * reference to the thread) but this is not nearly-automatically
     * so when calling from native code.
     * @param thread the thread to unpark.
     *
     */
    public native void unpark(Object thread);

    /**
     * 线程调用该方法，线程将一直阻塞直到超时，或者是中断条件出现。
     * 阻止当前线程，
     * 当平衡返回取消搁置时，或者平衡取消搁置了已经发生，或线程被中断，或者，
     * 如果没有绝对值和时间不为零，给定时间为纳秒已过去，或绝对的，给定的截止时间（以毫秒为单位）
     * 因为大纪元已经过去了，或者是虚假的（即，返回没有“原因”）。
     * 注意：此操作仅在Unsafe类中因为unpark是，所以放置它会很奇怪别处。
     *
     *阻塞一个线程直到<a href="#unpark"><code>unpark</code></a>出现、线程
     * 被中断或者timeout时间到期。如果一个<code>unpark</code>调用已经出现了，
     * 这里只计数。timeout为0表示永不过期.当<code>isAbsolute</code>为true时，
     * timeout是相对于新纪元之后的毫秒。否则这个值就是超时前的纳秒数。这个方法执行时
     * 也可能不合理地返回(没有具体原因)
     *
     * @param isAbsolute true if the timeout is specified in milliseconds from
     *                   the epoch.
     *                   如果为true timeout的值是一个相对于新纪元之后的毫秒数
     * @param time either the number of nanoseconds to wait, or a time in
     *             milliseconds from the epoch to wait for.
     *             可以是一个要等待的纳秒数，或者是一个相对于新纪元之后的毫秒数直到
     *             到达这个时间点
     * Block current thread, returning when a balancing
     * <tt>unpark</tt> occurs, or a balancing <tt>unpark</tt> has
     * already occurred, or the thread is interrupted, or, if not
     * absolute and time is not zero, the given time nanoseconds have
     * elapsed, or if absolute, the given deadline in milliseconds
     * since Epoch has passed, or spuriously (i.e., returning for no
     * "reason"). Note: This operation is in the Unsafe class only
     * because <tt>unpark</tt> is, so it would be strange to place it
     * elsewhere.
     */
    public native void park(boolean isAbsolute, long time);

    /**
     * 获取系统在不同时间系统的负载情况
     * 获取分配的系统运行队列中的平均负载到各个时间段内平均的可用处理器。
     * 此方法检索给定的nelem样本和分配给定loadavg数组的元素。
     * 系统最多施加3个样本，代表最后1分钟，5分钟和15分钟的平均值。
     * Gets the load average in the system run queue assigned
     * to the available processors averaged over various periods of time.
     * This method retrieves the given <tt>nelem</tt> samples and
     * assigns to the elements of the given <tt>loadavg</tt> array.
     * The system imposes a maximum of 3 samples, representing
     * averages over the last 1,  5,  and  15 minutes, respectively.
     *
     * @params loadavg an array of double of size nelems
     * @params nelems the number of samples to be retrieved and
     *         must be 1 to 3.
     *
     * @return the number of samples actually retrieved; or -1
     *         if the load average is unobtainable.
     */
    public native int getLoadAverage(double[] loadavg, int nelems);

    // The following contain CAS-based Java implementations used on
    // platforms not supporting native instructions

    /**
     * Atomically adds the given value to the current value of a field
     * or array element within the given object <code>o</code>
     * at the given <code>offset</code>.
     *
     * @param o object/array to update the field/element in
     * @param offset field/element offset
     * @param delta the value to add
     * @return the previous value
     * @since 1.8
     */
    public final int getAndAddInt(Object o, long offset, int delta) {
        int v;
        do {
            v = getIntVolatile(o, offset);
        } while (!compareAndSwapInt(o, offset, v, v + delta));
        return v;
    }

    /**
     * Atomically adds the given value to the current value of a field
     * or array element within the given object <code>o</code>
     * at the given <code>offset</code>.
     *
     * @param o object/array to update the field/element in
     * @param offset field/element offset
     * @param delta the value to add
     * @return the previous value
     * @since 1.8
     */
    public final long getAndAddLong(Object o, long offset, long delta) {
        long v;
        do {
            v = getLongVolatile(o, offset);
        } while (!compareAndSwapLong(o, offset, v, v + delta));
        return v;
    }

    /**
     * Atomically exchanges the given value with the current value of
     * a field or array element within the given object <code>o</code>
     * at the given <code>offset</code>.
     *
     * @param o object/array to update the field/element in
     * @param offset field/element offset
     * @param newValue new value
     * @return the previous value
     * @since 1.8
     */
    public final int getAndSetInt(Object o, long offset, int newValue) {
        int v;
        do {
            v = getIntVolatile(o, offset);
        } while (!compareAndSwapInt(o, offset, v, newValue));
        return v;
    }

    /**
     * Atomically exchanges the given value with the current value of
     * a field or array element within the given object <code>o</code>
     * at the given <code>offset</code>.
     *
     * @param o object/array to update the field/element in
     * @param offset field/element offset
     * @param newValue new value
     * @return the previous value
     * @since 1.8
     */
    public final long getAndSetLong(Object o, long offset, long newValue) {
        long v;
        do {
            v = getLongVolatile(o, offset);
        } while (!compareAndSwapLong(o, offset, v, newValue));
        return v;
    }

    /**
     * 如果对象内存地址偏移量上的数值不变，更改为新值
     * Atomically exchanges the given reference value with the current
     * reference value of a field or array element within the given
     * object <code>o</code> at the given <code>offset</code>.
     *
     * @param o object/array to update the field/element in
     * @param offset field/element offset
     * @param newValue new value
     * @return the previous value
     * @since 1.8
     */
    public final Object getAndSetObject(Object o, long offset, Object newValue) {
        Object v;
        do {
            //获取对象内存地址偏移量上的数值v
            v = getObjectVolatile(o, offset);
            //如果现在还是v,设置为newValue,否则返回false,！false=true，一直循环直到等于v退出循环返回v.
        } while (!compareAndSwapObject(o, offset, v, newValue));
        return v;
    }


    /**
     * 表示该方法之前的所有load操作在内存屏障之前完成
     * Ensures lack of reordering of loads before the fence
     * with loads or stores after the fence.
     * @since 1.8
     */
    public native void loadFence();

    /**
     * 表示该方法之前的所有store操作在内存屏障之前完成
     * Ensures lack of reordering of stores before the fence
     * with loads or stores after the fence.
     * @since 1.8
     */
    public native void storeFence();

    /**
     * 表示该方法之前的所有load、store操作在内存屏障之前完成
     * Ensures lack of reordering of loads or stores before the fence
     * with loads or stores after the fence.
     * @since 1.8
     */
    public native void fullFence();

    /**
     * Throws IllegalAccessError; for use by the VM.
     * @since 1.8
     */
    private static void throwIllegalAccessError() {
        throw new IllegalAccessError();
    }

}
