package com.lyp.learn.base.juc.pk01;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeDemo {
    private static Unsafe unsafe;

    public static void main(String[] args) {
        Test test = new Test();
        try {
            test.test();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("------------------");
        //可以通过反射机制获取Unsafe实例
        try {
            //通过反射获取rt.jar下的Unsafe类
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            Integer target = 12;
            //compareAndSwapInt方法的属性分别是：目标对象实例，目标对象属性偏移量，当前预期值，要设的值.
            //compareAndSwapInt方法是通过反射修改对象的值，具体修改对象下面那个值，可以通过偏移量，对象字段的偏移量可以通过objectFieldOffset获取
            System.out.println(unsafe.compareAndSwapInt(target, 12, 11, 10));
            System.out.println(target);

            String[] strings = new String[]{"1", "2", "3"};
            long i = unsafe.arrayBaseOffset(String[].class);
            System.out.println("string[] base offset is :" + i);
            //every index scale
            long scale = unsafe.arrayIndexScale(String[].class);
            System.out.println("string[] index scale is " + scale);
            //print first string in strings[]
            System.out.println("first element is :" + unsafe.getObject(strings, i));
            //set 100 to first string
            unsafe.putObject(strings, i + scale * 0, "100");
            //print first string in strings[] again
            System.out.println("after set ,first element is :" + unsafe.getObject(strings, i + scale * 0));

            //分配一个8byte的内存
            long address = unsafe.allocateMemory(8L);
            //初始化内存填充0
            unsafe.setMemory(address, 8L, (byte) 0);
            //测试输出
            System.out.println("add byte to memory:" + unsafe.getInt(address));
            //设置0-3 4个byte为0x7fffffff
            unsafe.putInt(address, 0x7fffffff);
            //设置4-7 4个byte为0x80000000
            unsafe.putInt(address + 4, 0x80000000);
            //int占用4byte
            System.out.println("add byte to memory:" + unsafe.getInt(address));
            System.out.println("add byte to memory:" + unsafe.getInt(address + 4));

        } catch (Exception e) {
            System.out.println("Get Unsafe instance occur error" + e);
        }

    }
}

class Test {

    private int count = 0;

    public void test() throws NoSuchFieldException, IllegalAccessException {
        // 获取unsafe实例
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);

        // 获取count域的Field
        Field fieldCount = Test.class.getDeclaredField("count");
        fieldCount.setAccessible(true);

        // 计算count的内存偏移量
        long countOffset = (int) unsafe.objectFieldOffset(fieldCount);
        System.out.println("偏移量：" + countOffset);

        // 原子性的更新指定偏移量的值（将count的值修改为3）
        unsafe.compareAndSwapInt(this, countOffset, count, 3);
        // 获取指定偏移量的int值
        System.out.println(unsafe.getInt(this, countOffset));
        unsafe.compareAndSwapInt(this, countOffset, 0, 3);
        System.out.println(unsafe.getInt(this, countOffset));
        unsafe.compareAndSwapInt(this, countOffset, 3, 33);
        System.out.println(unsafe.getInt(this, countOffset));
    }
}
