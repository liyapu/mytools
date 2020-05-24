package com.lyp.learn.pk8;

/**
 *  -Xms50m -Xmx50m -XX:+PrintGCDetails
 */
public class LocalVarGC {

    public void localGC1() {
        // 10MB
        byte[] buffer = new byte[10 * 1024 * 1024];
        System.gc();
    }

    public void localGC2() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        buffer = null;
        System.gc();
    }

    public void localGC3() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];

        }

        System.gc();
    }

    public void localGC4() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];

        }
        // 看字节码
        // buffer 和 value 可以共用一个槽位，故而可以回收
        int value = 10;
        System.gc();
    }

    public void localGC5() {
        localGC1();
        System.gc();
    }

    public static void main(String[] args) {
        LocalVarGC localVarGC = new LocalVarGC();
//        localVarGC.localGC1();

//        localVarGC.localGC2();  //回收了

//        localVarGC.localGC3();

//        localVarGC.localGC4(); // 回收了

        localVarGC.localGC5(); // 回收了
    }

}
