package com.lyp.learn.cglib;

/**
 * CGLIB代码包结构
 * 1.core
 *   ClassGenerator（接口） & AbstractClassGenerator（实现类）
 *   作为cglib代码中 最核心的调度者 ，封装了类创建的主要流程，并支持一些缓存操作、命名策略（NamingPolicy）、代码生成策略（GeneratorStrategy）等。
 *
 *  其中，protected Object create(Object key) 作为模版方法，定义了类的生成过程。同时将变化点进行封装，供继承类自主实现。
 *
 *  GeneratorStrategy（接口） & DefaultGeneratorStrategy（默认实现类）
 *         控制ClassGenerator生成class的字节码。
 *         为继承类预留了两个抽象方法，可以对生成的字节码进行操作。
 *
 *  NamingPolicy（接口） & DefaultNamingPolicy（默认实现类）
 *         用于控制生成类的命名规则。
 *        一般的命名规则：
 *         被代理类名 + $$ + CGLIB核心处理类 + "ByCGLIB" + $$ + key的hashCode。
 *         示例：FastSource<span>$$</span>FastClassByCGLIB<span>$$</span>e1a36bab.class。
 *
 *  KeyFactory
 *         每个生成类都会在cglib的缓存中存在唯一的key与之对应，这个key就通过KeyFactory进行生成。
 *
 *  DebuggingClassWriter
 *        被DefaultGeneratorStrategy调用,将生成类转为字节码输出。
 *        将生成的字节码写入到文件中(debugLocation)。
 *
 *  ClassEmitter & CodeEmitter
 *       封装了ASM的实现，提供对类和方法的字节码操作。
 * 工具类
 *      EmitUtils：封装了一些字节码操作的基本函数。
 *      ReflectUtils ：封装JDK中的反射操作。
 *
 * 2.beans
 *      BeanCopier：用于两个bean之间，同名属性间的拷贝。
 *      BulkBean：用于两个bean之间，自定义get&set方法间的拷贝。
 *      BeanMap：针对POJO Bean与Map对象间的拷贝。
 *      BeanGenerator：根据Map<String,Class>properties的属性定义，动态生成POJO Bean类。
 *      ImmutableBean:同样用于两个bean间的属性拷贝，但生成的bean不允许调用set方法，也就是说，生成的对象是不可变的。
 *
 * 3.reflect
 *     FastClass & FastMethod
 *     FastClass机制就是对一个类的方法建立索引，通过索引来直接调用相应的方法.
 *
 * 4.proxy
 *   Enhancer: 用于生成动态代理类
 *
 */
public interface info {
}
