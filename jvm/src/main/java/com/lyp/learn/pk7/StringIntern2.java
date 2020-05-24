package com.lyp.learn.pk7;

/**
 *  使用 intern() 测试空间上的执行效率
 *
 *  结论:
 *     对于程序中大量存在的字符串，尤其其中存在很多重复的字符串时，使用 intern()可以节省内存空间
 */
public class StringIntern2 {

    static int max = 10000000;
    static String [] arr = new String[max];
    public static void main(String[] args) {
        Integer[] data =new Integer[]{1,2,3,4,5,6,7,8,9,10};

        long start = System.currentTimeMillis();

        for (int i = 0; i < max; i++) {
//            arr[i] = new String(String.valueOf(data[i%data.length]));
            arr[i] = new String(String.valueOf(data[i%data.length]).intern());
        }
        long end = System.currentTimeMillis();
        System.out.println("花费时间:: " + (end - start) + " ms");

        //此时，可以看 jvisualvm 的 抽样器 内存
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}













