package com.lyp.learn.base.threads.pk03;

/**
 *  Thread类中interrupt（）、interrupted（）和isInterrupted（）方法详解
 * https://blog.csdn.net/qq_39682377/article/details/81449451
 *
 */
public class InterruptTest4 {
    public static void main(String[] args) {
        NumPrintThread npt = new NumPrintThread();
        npt.start();
    }

}

class NumPrintThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if(i == 5){
                //interrupt()
                //其作用是中断此线程（此线程不一定是当前线程，而是指调用该方法的Thread实例所代表的线程），
                // 但实际上只是给线程设置一个中断标志，线程仍会继续运行。
                this.interrupt();


                // isInterrupted
                // 作用是只测试此线程是否被中断 ，不清除中断状态。
                //从结果可以看出调用interrupt（）方法后，线程仍在继续运行，并未停止，
                // 但已经给线程设置了中断标志，两个isInterrupted（）方法都会输出true，也说明isInterrupted（）方法并不会清除中断状态
                System.out.println(this.isInterrupted());
                System.out.println(this.isInterrupted());
                System.out.println();


                // 作用是测试当前线程是否被中断（检查中断标志），返回一个boolean
                // 并清除中断状态，
                // 第二次再调用时中断状态已经被清除，将返回一个false。
//                this.interrupted();
                System.out.println(this.interrupted());
                System.out.println(this.interrupted());
//                System.out.println(Thread.currentThread().interrupted());
//                System.out.println(Thread.currentThread().interrupted());
                System.out.println();
                //interrupted（）方法测试的是当前线程是否被中断，当前线程！！！当前线程！！！这里当前线程是main线程，
                // 而thread.interrupt(）中断的是thread线程，这里的此线程就是thread线程。
                // 所以当前线程main从未被中断过，尽管interrupted（）方法是以thread.interrupted（）的形式被调用，
//                System.out.println(this.isInterrupted());
//                System.out.println(this.isInterrupted());

                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println(Thread.currentThread().isInterrupted());
            }
            System.out.println(i);

            /**
             * 最后总结，关于这三个方法，
             *    interrupt（）是给线程设置中断标志；
             *    interrupted（）是检测中断并清除中断状态；
             *    isInterrupted（）只检测中断。
             *    还有重要的一点就是interrupted（）作用于当前线程，
             *    interrupt（）和isInterrupted（）作用于此线程，即代码中调用此方法的实例所代表的线程
             */
        }

    }
}
