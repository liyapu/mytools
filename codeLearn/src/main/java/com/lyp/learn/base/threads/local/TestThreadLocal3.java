package com.lyp.learn.base.threads.local;



public class TestThreadLocal3 {


    public static void main(String[] args) {
        /*
        由前面我们知道对于一个Thread来说只有持有一个ThreadLocalMap，所以ABC对应同一个ThreadLocalMap对象。
        为了管理ABC，于是将他们存储在一个数组的不同位置，而这个数组就是上面提到的Entry型的数组table。

         */
        //在某一线程声明了ABC三种类型的ThreadLocal
        ThreadLocal<String> sThreadLocalA = new ThreadLocal<>();
        ThreadLocal<String> sThreadLocalB = new ThreadLocal<>();
        ThreadLocal<String> sThreadLocalC = new ThreadLocal<>();

        sThreadLocalA.set("aaaaaa");
        sThreadLocalB.set("bbb");
        sThreadLocalC.set("ccccc");

        System.out.println(sThreadLocalA.get());
        System.out.println(sThreadLocalB.get());
        System.out.println(sThreadLocalC.get());
    }

}
