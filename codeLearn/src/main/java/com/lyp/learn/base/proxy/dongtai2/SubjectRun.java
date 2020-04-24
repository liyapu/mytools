package com.lyp.learn.base.proxy.dongtai2;


import com.google.gson.internal.$Gson$Preconditions;

/**
 * @Author: liyapu
 * @Description: 动态代理
 * @create: 2019-01-13 19:13
 *
 * 1.可以代理下面两种
 * 代理接口
 * 必须有接口的类才可以使用该代理
 * JDK中的动态代理是通过反射类Proxy以及InvocationHandler回调接口实现的，但是，JDK中所要进行动态代理的类必须要实现一个接口，也就是说只能对该类所实现接口中定义的方法进行代理，这在实际编程中具有一定的局限性，而且使用反射的效率也并不是很高。
 *
 *
 * 普通类不可以代理，cglib可以
 *
 * 二、CGLIB 原理
 * CGLIB 原理：动态生成一个要代理类的子类，子类重写要代理的类的所有不是final的方法。在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。它比使用java反射的JDK动态代理要快。
 * CGLIB 底层：使用字节码处理框架ASM，来转换字节码并生成新的类。不鼓励直接使用ASM，因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉。
 *
 * 使用CGLib实现动态代理，完全不受代理类必须实现接口的限制，而且CGLib底层采用ASM字节码生成框架，使用字节码技术生成代理类，比使用Java反射效率要高。唯一需要注意的是，CGLib不能对声明为final的方法进行代理，因为CGLib原理是动态生成被代理类的子类。
 *
 * CGLIB缺点：对于final方法，无法进行代理。
 *
 * CGLIB和Java动态代理的区别
 * 1.Java动态代理只能够对接口进行代理，不能对普通的类进行代理（因为所有生成的代理类的父类为Proxy，Java类继承机制不允许多重继承）；CGLIB能够代理普通类；
 * 2.Java动态代理使用Java原生的反射API进行操作，在生成类上比较高效；CGLIB使用ASM框架直接对字节码进行操作，在类的执行过程中比较高效
 *
 */
public class SubjectRun {
    public static void main(String[] args) {
        //我们要代理的真实对象
        Subject realSubject = new RealSubject();
        Subject subject = SubjectDynamicHandler.createProxyObject(realSubject);

        System.out.println(subject.getClass().getName());
        subject.printMessage();

        // 接收方法的返回值
        Result result = subject.sayHello("world");
        System.out.println(result);
    }

}
