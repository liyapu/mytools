package com.lyp.learn.base.threads.pk05;



public class PrintABC4 {

    //控制单个打印的次数  for循环使用
    private static Integer MAX = 10;
    //控制线程切换的状态位
    private static volatile Integer state = 1;
    //临界区使用的锁
    private static Object obj = new Object();

    public static void main(String[] args) {

        new Thread(() ->{
            for (int i = 1; i <= MAX; ) {
                //同步块，保证下面的代码不会被切换，原子性保证
                synchronized (obj){
                    if(state == 1){
                        System.out.print("A");
                        //自己打印了，才让i++控制循环，保证打印的遍数不会少
                        i++;
                        //让下一个打印
                        state = 2;
                        //自己打印完之后，修改条件了，条件已经不符合自己了，通知他人
                        obj.notifyAll();
                    }else{
                        try {
                            //不符合自己的条件，就等待
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(() ->{
            for (int i = 1; i <= MAX;) {
                synchronized (obj){
                    if(state == 2){
                        System.out.print("B");
                        i++;
                        state = 3;
                        obj.notifyAll();
                    }else{
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(() ->{
            for (int i = 1; i <= MAX; ) {
                synchronized (obj){
                    if(state == 3){
                        System.out.println("C");
                        i++;
                        state = 1;
                        obj.notifyAll();
                    }else{
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
