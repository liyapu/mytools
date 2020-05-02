package com.lyp.learn.pk1;

/**
 * @author: liyapu
 * @description:
 * @date 2020-04-30 20:30
 *
 *
 *
 */
public class JpsDemo {
    public static void main(String[] args) {
        int a = 10;
        int b= 20;
        int c = a + b;
        System.out.println("c is = " + c);

        try {
            // 睡眠6s,可以在命令行执行 jps  查看当前所有java进程pid的命令
            /*
                liyapudeMacBook-Pro:java12 liyapu$ jps
                4034 RemoteMavenServer36
                8548 Launcher
                8549 AppMainV2   可以看到此进程在下面结束了
                8550 Jps
                283
                liyapudeMacBook-Pro:java12 liyapu$ jps
                4034 RemoteMavenServer36
                8548 Launcher
                8553 Jps
                283
             */
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("hello jps");
    }

}
