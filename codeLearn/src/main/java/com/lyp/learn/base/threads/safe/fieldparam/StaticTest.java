package com.lyp.learn.base.threads.safe.fieldparam;

/**
 * https://www.cnblogs.com/dreamroute/p/4037050.html
 *
 * java线程安全问题之静态变量、实例变量、局部变量
 *  java多线程编程中，存在很多线程安全问题，至于什么是线程安全呢，给出一个通俗易懂的概念还是蛮难的，如同《java并发编程实践》中所说：
 *
 * 写道
 * 给线程安全下定义比较困难。存在很多种定义，如：“一个类在可以被多个线程安全调用时就是线程安全的”。
 *  此处不赘述了，首先给出静态变量、实例变量、局部变量在多线程环境下的线程安全问题结论，然后用示例验证，请大家擦亮眼睛，有错必究，否则误人子弟！
 *
 *
 * 静态变量：线程非安全。
 *
 * 静态变量即类变量，位于方法区，为所有对象共享，共享一份内存，一旦静态变量被修改，其他对象均对修改可见，故线程非安全。
 *
 *
 * 实例变量：单例模式（只有一个对象实例存在）线程非安全，非单例线程安全。
 *
 * 实例变量为对象实例私有，在虚拟机的堆中分配，若在系统中只存在一个此对象的实例，在多线程环境下，“犹如”静态变量那样，被某个线程修改后，其他线程对修改均可见，故线程非安全；如果每个线程执行都是在不同的对象中，那对象与对象之间的实例变量的修改将互不影响，故线程安全。
 *
 * 局部变量：线程安全。
 *
 * 每个线程执行时将会把局部变量放在各自栈帧的工作内存中，线程间不共享，故不存在线程安全问题。
 */

/**
 * 静态变量线程安全问题模拟：
 * 线程安全问题模拟执行
 *  ------------------------------
 *       线程1      |    线程2
 *  ------------------------------
 *   static_i = 4;  | 等待
 *   static_i = 10; | 等待
 *    等待          | static_i = 4;
 *   static_i * 2;  | 等待
 *  -----------------------------
 */
public class StaticTest implements Runnable {

    //静态变量
    private static int static_i;

    @Override
    public void run() {
        static_i = 4;
        System.out.println(Thread.currentThread().getName() + " 获取 static_i = " + static_i);
        static_i = 10;
        System.out.println(Thread.currentThread().getName() + " 获取 static_i * 2 = " + (static_i*2));
    }

    public static void main(String[] args) {
        StaticTest st = new StaticTest();
        int threadCount = 300;
        //启动尽量多的线程才能很容易的模拟问题
        for(int i = 0; i <= threadCount; i++){
//            new Thread(st).start();
            //st可以换成new StaticTest(),保证每个线程都在不同的对象中执行，结果一样
            new Thread(new StaticTest()).start();

        }

        /**
         * 根据代码注释中模拟的情况，当线程1执行了static_i = 4;  static_i = 10; 后，
         * 线程2获得执行权，static_i = 4;
         * 然后当线程1获得执行权执行static_i * 2;  必然输出结果4*2=8，
         * 按照这个模拟，我们可能会在控制台看到输出为8的结果。
         */
    }
}
