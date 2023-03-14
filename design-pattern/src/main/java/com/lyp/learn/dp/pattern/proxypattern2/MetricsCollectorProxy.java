package com.lyp.learn.dp.pattern.proxypattern2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author liyapu
 * @date 2023-03-11 21:48
 * @description 刚刚的代码实现还是有点问题。一方面，我们需要在代理类中，将原始类中的所有的方法，都重新实现一遍，并且为每个方法都附加相似的代码逻辑。
 * 另一方面，如果要添加的附加功能的类有不止一个，我们需要针对每个类都创建一个代理类。
 * <p>
 * 如果有50个要添加附加功能的原始类，那我们就要创建50个对应的代理类。这会导致项目中类的个数成倍增加，增加了代码维护成本。
 * 并且，每个代理类中的代码都有点像模板式的“重复”代码，也增加了不必要的开发成本。那这个问题怎么解决呢？
 * <p>
 * 我们可以使用动态代理来解决这个问题。所谓动态代理（Dynamic Proxy），就是我们不事先为每个原始类编写代理类，
 * 而是在运行的时候，动态地创建原始类对应的代理类，然后在系统中用代理类替换掉原始类。那如何实现动态代理呢？
 * <p>
 * 如果你熟悉的是Java语言，实现动态代理就是件很简单的事情。因为Java语言本身就已经提供了动态代理的语法
 * （实际上，动态代理底层依赖的就是Java的反射语法）。
 * 我们来看一下，如何用Java的动态代理来实现刚刚的功能。具体的代码如下所示。
 * 其中，MetricsCollectorProxy作为一个动态代理类，动态地给每个需要收集接口请求信息的类创建代理类。
 */
public class MetricsCollectorProxy {

    private MetricsCollector metricsCollector;

    public MetricsCollectorProxy() {
        this.metricsCollector = new MetricsCollector();
    }

    public Object createProxy(Object proxiedObject) {
        Class[] interfaces = proxiedObject.getClass().getInterfaces();
        DynamicProxyHandler handler = new DynamicProxyHandler(proxiedObject);
        return Proxy.newProxyInstance(proxiedObject.getClass().getClassLoader(), interfaces, handler);
    }

    private class DynamicProxyHandler implements InvocationHandler {

        private Object proxiedObject;

        public DynamicProxyHandler(Object proxiedObject) {
            this.proxiedObject = proxiedObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long startTimestamp = System.currentTimeMillis();
            Object result = method.invoke(proxiedObject, args);
            long endTimeStamp = System.currentTimeMillis();
            long responseTime = endTimeStamp - startTimestamp;
            String apiName = proxiedObject.getClass().getName() + ":" + method.getName();
            RequestInfo requestInfo = new RequestInfo(apiName, responseTime, startTimestamp);
            metricsCollector.recordRequest(requestInfo);
            return result;
        }
    }
}
