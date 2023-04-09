package com.lyp.learn.base.classs.loader;

import java.io.File;
import java.net.URL;
import java.security.Provider;
import java.util.Arrays;
import jdk.nashorn.internal.codegen.DumpBytecode;
import org.apache.commons.lang3.StringUtils;
import sun.security.util.CurveDB;

/**
 * @author liyapu
 * @date 2023-04-09 14:18
 * @description 类加载器加载的类
 * 类的加载器及加载过程 原文链接：https://blog.csdn.net/weixin_50020236/article/details/124098698
 *
 * 启动类加载器（ 引导类加载器，Bootstrap ClassLoader ）
 * 1. 这个类加载使用C/C++语言实现的，嵌套在JVM内部
 * 2. 它用来加载Java的核心库（JAVA_HOME / jre / lib / rt.jar、resources.jar 或 sun.boot.class.path 路径下的内容），用于提供JVM自身需要的类
 * 3. 并不继承自java.lang.ClassLoader，没有父加载器
 * 4. 加载扩展类和应用程序类加载器，并作为他们的父类加载器（当他俩的爹）
 * 5. 出于安全考虑，Bootstrap启动类加载器只加载包名为java、javax、sun等开头的类
 *
 * 扩展类加载器（Extension ClassLoader）
 * 1. Java语言编写，由sun.misc.Launcher$ExtClassLoader实现
 * 2. 派生于ClassLoader类
 * 3. 父类加载器为启动类加载器
 * 4. 从java.ext.dirs系统属性所指定的目录中加载类库，或从JDK的安装目录的 jre / lib / ext子目录（扩展目录）下加载类库。如果用户创建的 JAR 放在此目录下，也会自动由扩展类加载器加载
 *
 * 应用程序类加载器（系统类加载器，AppClassLoader）
 * 1. Java语言编写，由sun.misc.LaunchersAppClassLoader实现
 * 2. 派生于ClassLoader类
 * 3. 父类加载器为扩展类加载器
 * 4. 它负责加载环境变量 classpath 或 系统属性java.class.path指定路径下的类库
 * 5. 该类加载是程序中默认的类加载器，一般来说，Java应用的类都是由它来完成加载的
 * 6. 通过classLoader.getSystemclassLoader( )方法可以获取到该类加载器
 *
 *
 * 为什么要设计双亲委派机制？
 * 沙箱安全机制：自己写的java.lang.String.class类不会被加载，这样便可以防止核心API库被随意篡改
 * 避免类的重复加载：当父亲已经加载了该类时，就没有必要子ClassLoader再加载一次，保证被加载类的唯一性
 *
 *
 * 类的加载(Load)→类的连接(Link)→类的初始化(Initialize)
 *
 * 加载：类经过javac.exe编译的.class字节码文件读入内存（将静态数据转换成堆中方法区的运行时数据结构），并为之创建一个java.lang.Class对象作为方法区中类数据的访问入口(引用的地址)，需要访问和使用类数据只能通过这个Class对象；此过程由类的加载器完成；
 * 类加载器(class loader)用来加载 Java 类到 Java 虚拟机中。一般来说，Java 类的虚拟机使用 Java 方式如下：Java 源程序(.java 文件)在经过 Java 编译器编译之后就被转换成 Java 字节代码(.class 文件)。类加载器负责读取 Java 字节代码，并转换成 java.lang.Class类的一个实例。每个这样的实例用来表示一个 Java 类。通过此实例的 newInstance()方法就可以创建出该类的一个对象。实际的情况可能更加复杂，比如 Java 字节代码可能是通过工具动态生成的，也可能是通过网络下载的。
 *
 * 链接：将java类的二进制代码合并到JVM的运行状态中的过程；
 * a.验证：确保加载的类符合JVM规范；
 * b.准备：正式为类变量(static)分配内存并设置变量默认初始值（非任何显示赋值），这些内存都在方法区中分配；
 * c.解析：虚拟机常量池内的符号引用（常量名）替换为直接引用（地址）的过程
 *
 * 初始化：JVM负责对类进行初始化；
 *
 * 执行类构造器()方法的： 此方法是由编译期自动收集类中所有类变量的赋值动作和静态代码块中的语句合并产生的(类构造器是构造类信息的，并非new对象构造器)
 * 如其父类为进行初始化，则初始化操作从先从父类进行；
 * 虚拟机会保证一个类的()方法在多线程环境中被正确加锁和同步；
 */
public class ClassLoaderLoadClassDemo {

    public static void main(String[] args) {
        System.out.println("**********启动类加载器**************");
        //获取BootstrapClassLoader能够加载的api的路径
        URL[] urLs = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL element : urLs) {
            System.out.println(element.toExternalForm());
        }
        //从上面的路径中随意选择一个类,来看看他的类加载器是什么:引导类加载器
        ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println(classLoader); //null

        System.out.println("***********扩展类加载器*************");
        printStr(System.getProperty("java.ext.dirs"), String.valueOf(File.pathSeparatorChar));

        System.out.println("***********应用程序类加载器*************");
        printStr(System.getProperty("java.class.path"), String.valueOf(File.pathSeparatorChar));

        System.out.println("-----------");
        //从上面的路径中随意选择一个类,来看看他的类加载器是什么:扩展类加载器
        ClassLoader classLoader1 = CurveDB.class.getClassLoader();
        System.out.println(classLoader1);//sun.misc.Launcher$ExtClassLoader@5cad8086ext

        ClassLoader classLoader2 = DumpBytecode.class.getClassLoader();
        System.out.println(classLoader2);//sun.misc.Launcher$ExtClassLoader@4fca772d
    }

    private static void printStr(String path, String pathSeparatorChar) {
        if (StringUtils.isBlank(path)) {
            return;
        }
        Arrays.stream(path.split(pathSeparatorChar))
            .forEach(System.out::println);
        System.out.println();
    }
}
