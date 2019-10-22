package com.lyp.learn.base.pk09;

class MyThreadd extends  Thread{
    public MyThreadd(String name){
        super(name);
    }

    @Override
    public void run(){
        synchronized(this){
            try {
                for(int i = 0; i <= 5; i++){
                    Thread.sleep(100);//休眠100ms
                    System.out.println(Thread.currentThread().getName() + " loop " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class Demo1_2 {
    public static void main(String[] args) {
        Thread t1 = new MyThreadd("t1");  // 新建“线程t1”
        Thread t2 = new MyThreadd("t2");  // 新建“线程t2”
        t1.start();                          // 启动“线程t1”
        t2.start();                          // 启动“线程t2”
    }
}
