package com.lyp.learn.dp.pattern.singlepattern3;

/**
 *  枚举实现单例
 *
 *  代码相当简洁，我们也可以像常规类一样编写enum类，为其添加变量和方法，访问方式也更简单，
 *  使用SingletonEnum.INSTANCE进行访问，这样也就避免调用getInstance方法，更重要的是使用枚举单例的写法，我们完全不用考虑序列化和反射的问题。
 *  枚举序列化是由jvm保证的，每一个枚举类型和定义的枚举变量在JVM中都是唯一的，在枚举类型的序列化和反序列化上，Java做了特殊的规定：
 *  在序列化时Java仅仅是将枚举对象的name属性输出到结果中，反序列化的时候则是通过java.lang.Enum的valueOf方法来根据名字查找枚举对象。
 *  同时，编译器是不允许任何对这种序列化机制的定制的并禁用了writeObject、readObject、readObjectNoData、writeReplace和readResolve等方法，
 *  从而保证了枚举实例的唯一性
 *
 * 枚举实现的单例可轻松地解决两个问题：
 * 1.线程安全问题。因为Java虚拟机在加载枚举类的时候，会使用ClassLoader的loadClass方法，这个方法使用了同步代码块来保证线程安全。
 * 2.避免反序列化破坏单例。因为枚举的反序列化并不通过反射实现。
 *
 *  关于单例，我们总是应该记住：线程安全，延迟加载，序列化与反序列化安全，反射安全是很重重要的
 */
public enum SingletonEnum {
    SINGLETON;

    private String name;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public static SingletonEnum getInstance(){
        return SINGLETON;
    }
}
