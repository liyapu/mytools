package com.lyp.learn.cglib.proxy;

import net.sf.cglib.proxy.*;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * Enhancer既能够代理普通的class，也能够代理接口。Enhancer创建一个被代理对象的子类并且拦截所有的方法调用（包括从Object中继承的toString和hashCode方法）
 * Enhancer不能够拦截final方法，例如Object.getClass()方法，这是由于Java final方法语义决定的。
 * 基于同样的道理，Enhancer也不能对fianl类进行代理操作。这也是Hibernate为什么不能持久化final class的原因。
 */
public class EnhancerTest {

    /**
     * 测试基本代理
     *
     * Object为由CGLib动态生成的代理类实例，
     * Method为上文中实体类所调用的被代理的方法引用，
     * Object[]为参数值列表，
     * MethodProxy为生成的代理类对方法的代理引用。
     *
     * 从代理实例的方法调用返回的值。
     *
     * 其中，proxy.invokeSuper(obj,arg) 调用代理类实例上的proxy方法的父类方法（即实体类TargetObject中对应的方法）
     */
    @Test
    public void testHelloWorld(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Sample.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run ....");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run ....");
                return result;
            }
        });
        //首先将被代理类TargetObject设置成父类，
        // 然后设置拦截器TargetInterceptor，
        // 最后执行enhancer.create()动态生成一个代理类，并从Object强制转型成父类型TargetObject
        Sample sample  = (Sample) enhancer.create();
        sample.sayHello();
    }

    /**
     * FixedValue用来对所有拦截的方法返回相同的值，
     * 从输出我们可以看出来，Enhancer对非final方法test()、toString()、hashCode()进行了拦截，没有对getClass进行拦截。
     * 由于hashCode()方法需要返回一个Number，但是我们返回的是一个String，这解释了上面的程序中为什么会抛出异常。
     *
     * Enhancer.setSuperclass用来设置父类型，从toString方法可以看出，使用CGLIB生成的类为被代理类的一个子类，
     * 形如：Sample$$EnhancerByCGLIB$$e3ea9b7
     */
    @Test
    public void testFixedValue(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Sample.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello cglib";
            }
        });
        Sample proxy = (Sample) enhancer.create();
        System.out.println(proxy.printMessage("msggggg"));
        System.out.println(proxy.toString());
        System.out.println(proxy.getClass());
        System.out.println(proxy.hashCode());
    }

    /**
     * 有些时候我们可能只想对特定的方法进行拦截，对其他的方法直接放行，不做任何操作，
     * 使用Enhancer处理这种需求同样很简单,只需要一个CallbackFilter即可
     *
     *  NoOp.INSTANCE：这个NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
     *  FixedValue：表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
     */
    @Test
    public void testCallbackFilter() throws Exception{
        Enhancer enhancer = new Enhancer();
        CallbackHelper callbackHelper = new CallbackHelper(Sample.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class){
                    return new FixedValue() {
                        @Override
                        public Object loadObject() throws Exception {
                            return "Hello cglib";
                        }
                    };
                }else{
                    return NoOp.INSTANCE;
                }
            }
        };
        enhancer.setSuperclass(Sample.class);
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());

        Sample proxy = (Sample) enhancer.create();
        System.out.println(proxy.printMessage(null));
        System.out.println(proxy.toString());
        System.out.println(proxy.hashCode());
    }

}
