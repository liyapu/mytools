package com.lyp.learn.dp.pattern.singlepattern2;

/**
 * 饿汉式单例模式
 * 第一次使用时，才进行初始化
 *
 * 这种编写方式被称为“双重检查锁”，主要在getInstance方法中，进行两次null检查。这样可以极大提升并发度，进而提升性能。
 * 毕竟在单例中new的情况非常少，绝大多数都是可以并行的读操作，因此在加锁前多进行一次null检查就可以减少绝大多数的加锁操作，也就提高了执行效率。
 *
 * 但是必须注意的是volatile关键字，该关键字有两层语义。
 * 第一层语义是可见性，可见性是指在一个线程中对该变量的修改会马上由工作内存（Work Memory）写回主内存（Main Memory），所以其它线程会马上读取到已修改的值，
 * 关于工作内存和主内存可简单理解为高速缓存（直接与CPU打交道）和主存（日常所说的内存条），注意工作内存是线程独享的，主存是线程共享的。
 * 第二层语义是禁止指令重排序优化，我们写的代码（特别是多线程代码），由于编译器优化，在实际执行的时候可能与我们编写的顺序不同。
 * 编译器只保证程序执行结果与源代码相同，却不保证实际指令的顺序与源代码相同，这在单线程并没什么问题，然而一旦引入多线程环境，这种乱序就可能导致严重问题。
 * volatile关键字就可以从语义上解决这个问题，
 *
 * 值得关注的是volatile的禁止指令重排序优化功能在Java 1.5后才得以实现，因此1.5前的版本仍然是不安全的，
 * 即使使用了volatile关键字。或许我们可以利用静态内部类来实现更安全的机制
 *
 * 序列化可能会破坏单例模式，比较每次反序列化一个序列化的对象实例时都会创建一个新的实例
 */
public class Singleton {
    //私有静态属性，开始为null
    private static volatile Singleton instance = null;

    //私有构造方法，保证外界无法直接实例化
    private Singleton(){
    }

    //静态方法，通过此方法获取实例，double check 双重检查锁
    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
