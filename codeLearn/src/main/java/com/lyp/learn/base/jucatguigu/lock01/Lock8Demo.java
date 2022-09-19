package com.lyp.learn.base.jucatguigu.lock01;

import com.lyp.learn.base.executors.pk01.TimeUnit;
import org.junit.Test;

/**
 * @author liyapu
 * @date 2022-09-18 15:09
 * @description
 */
class Phone {

    public synchronized void sendEmail1() {
        System.out.println("-----sendEmail---1---");
    }

    public synchronized void sendSms1() {
        System.out.println("-----sendSms---1---");
    }


    public synchronized void sendEmail2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----sendEmail---2---");
    }

    public synchronized void sendSms2() {
        System.out.println("-----sendSms---2---");
    }

    public static synchronized void sendEmail3() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----sendEmail---3---");
    }

    public static synchronized void sendSms3() {
        System.out.println("-----sendSms---3---");
    }

    public void hello1() {
        System.out.println("----hello----1---");
    }
}

/**
 * 题目：谈谈你对多线程的理解，8锁案例
 * 口诀： 线程  操作  资源类
 * 8锁案例
 * <p>
 * 【强制】高并发时，同步调用应该去考量锁的性能损耗。能用无锁数据结构，就不要用锁；能锁区块，就不要锁整个方法体；能用对象锁，就不要用类锁。
 * 说明：尽可能使加锁的代码块工作量可能的小，避免在锁代码块中调用RPC方法
 * <p>
 * 总结：
 * 作用于实例方法，当前实例加锁，进入同步代码前要获得当前实例的锁
 * 作用于代码块，对括号里配置的对象加锁
 * 作用于静态方法，当前类加锁，进去同步代码前要获得当前类对象的锁
 */
public class Lock8Demo {

    /**
     * 1. 标准访问有 a,b两个线程，请问先打印邮件还是短信？
     * 一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了。
     * 其它的线程都只能等待。换句话说，某一个时刻内，只能有唯一的一个线程去访问这些synchronized方法。
     * 锁的是当前对象this,被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法
     * <p>
     * Phone p = [new Phone();] 锁
     */
    @Test
    public void test01() throws InterruptedException {
        final Phone phone = new Phone();
        new Thread(() -> {
            phone.sendEmail1();
        }, "a").start();

        TimeUnit.MILLISECONDS.sleep(200);

        new Thread(() -> {
            phone.sendSms1();
        }, "b").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2. sendEmail2 添加暂停3s，请问先打印邮件还是短信？
     */
    @Test
    public void test02() throws InterruptedException {
        final Phone phone = new Phone();
        new Thread(() -> {
            phone.sendEmail2();
        }, "a").start();

        TimeUnit.MILLISECONDS.sleep(200);

        new Thread(() -> {
            phone.sendSms2();
        }, "b").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 3.添加一个 普通的hello方法，请问先哪个？
     */
    @Test
    public void test03() throws InterruptedException {
        final Phone phone = new Phone();
        new Thread(() -> {
            phone.sendEmail2();
        }, "a").start();

        TimeUnit.MILLISECONDS.sleep(200);

        new Thread(() -> {
            phone.hello1();
        }, "b").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 4.两部手机，请问先哪个？
     */
    @Test
    public void test04() throws InterruptedException {
        final Phone phone1 = new Phone();
        final Phone phone2 = new Phone();
        new Thread(() -> {
            phone1.sendEmail2();
        }, "a").start();

        TimeUnit.MILLISECONDS.sleep(200);

        new Thread(() -> {
            phone2.sendSms2();
        }, "b").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 5.访问静态方法，请问先哪个？
     * [Phone] phone = new Phone();  类锁
     * 都换成静态同步方法后，情况又发生变化
     * 三种 synchronized 锁的内容有一些差别：
     * 对于普通同步方法，锁的是当前实例对象，通常指this,具体的一部部手机，所有的普通同步方法用的都是同一把锁-->实例对象本身。
     * 对于静态同步方法，锁的是当前类的Class对象，如Phone.class 唯一的一个模板
     * 对于同步方法块，锁的是 synchronized 括号内的对象
     */
    @Test
    public void test05() throws InterruptedException {
        final Phone phone = new Phone();
        new Thread(() -> {
            //phone.sendEmail3();
            Phone.sendEmail3();
        }, "a").start();

        TimeUnit.MILLISECONDS.sleep(200);

        new Thread(() -> {
            //phone.sendSms3();
            Phone.sendSms3();
        }, "b").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 7.有1个静态同步方法，有1个普通版同步方法，有1部手机，请问先哪个？
     * <p>
     * 当一个线程试图访问同步代码时它首先必须得到锁，正常退出或抛出异常时必须释放锁。
     * <p>
     * 所有的普通同步方法用的都是同一把锁--实例对象本身，就是 new出来的具体实例对象，本类this
     * 也就是说如果一个实例对象的普通同步方法获取锁后，该实例对象的其它普通同步方法必须等待获取锁的方法释放锁后才能获取锁。
     * <p>
     * 所有的静态同步方法用的也是同一把锁--类对象本身，就是我们说过的唯一模板class
     * <p>
     * 具体实例对象this和唯一模板class,这两把锁是两个不同的对象，所以静态同步方法与普通同步方法之间是不会有竞态条件的，
     * 但是一旦一个静态同步方法获取锁后，其它的静态同步方法都必须等待该方法释放锁后才能获取锁
     */
    @Test
    public void test07() throws InterruptedException {
        final Phone phone1 = new Phone();
        new Thread(() -> {
            phone1.sendEmail3();
        }, "a").start();

        TimeUnit.MILLISECONDS.sleep(200);

        new Thread(() -> {
            phone1.sendSms1();
        }, "b").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

