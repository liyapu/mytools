package com.lyp.learn.pk1;

/**
 *   java8 相关文档
 *   https://docs.oracle.com/javase/8/index.html
 *   jvm 规范
 *   https://docs.oracle.com/javase/specs/index.html
 *
 *   jdk JDK Tool Specifications jar包命令解释
 *   https://docs.oracle.com/en/java/javase/14/
 *   https://docs.oracle.com/en/java/javase/14/docs/specs/man/java.html
 *
 *
 *   查看字节码
 *          先执行此类
 *          然后会生成编译的 target 目录
 *          进入到此目录 mytools/jvm/target/classes/com/lyp/learn/pk1
 *          执行 javap -v ClassDemo.class
 *
 *          若是使用文本编辑器，直接打开 ClassDemo.class，可以看到 此文件的 二进制形式的 十六进制
 *          类似于下面的：
 *                cafe babe 0000 0037 0037 0a00 0200 0307
 *                0004 0c00 0500 0601 0010 6a61 7661 2f6c
 *                616e 672f 4f62 6a65 6374 0100 063c 696e
 *                6974 3e01 0003 2829 5609 0008 0009 0700
 *                0a0c 000b 000c 0100 106a 6176 612f 6c61
 *                魔数是用来区分文件类型的一种标志，一般都是用文件的前几个字节来表示,
 *                比如cafe babe 表示的是class文件，那么有人会问，文件类型可以通过文件名后缀来判断啊？
 *                是的，但是文件名是可以修改的（包括后缀），那么为了保证文件的安全性，讲文件类型写在文件内部来保证不被篡改。
 *                从java的字节码文件类型我们看到，CAFE BABE翻译过来是咖啡宝贝之意，然后再看看java图标。
 *
 *
 *         版本号含主版本号和次版本号，都是各占2个字节
 *         0000  主版本是 0
 *         0037  次版本号 55
 *
 *         常量池是Class文件中的资源仓库，在接下来的内容中我们会发现很多地方会涉及，如Class Name，Interfaces等。
 *         常量池中主要存储2大类常量：字面量和符号引用。
 *              字面量 如文本字符串，java中声明为final的常量值等等，
 *              而符号引用如类和接口的全局限定名，字段的名称和描述符，方法的名称和描述符
 *
 *         为什么需要类和接口的全局限定名呢？
 *         系统引用类或者接口的时候不是通过内存地址进行操作吗？
 *         这里大家仔细想想，java虚拟机在没有将类加载到内存的时候根本都没有分配内存地址，也就不存在对内存的操作，
 *         所以java虚拟机首先需要将类加载到虚拟机中，那么这个过程设计对类的定位
 *         （需要加载A包下的B类，不能加载到别的包下面的别的类中），
 *         所以需要通过全局限定名来判别唯一性。这就是为什么叫做全局，限定的意思，也就是唯一性。
 *
 *
 *         Access_Flag 访问标志
 *         访问标志信息包括该Class文件是类还是接口，是否被定义成public，是否是abstract，如果是类，是否被声明成final。
 *         通过上面的源代码，我们知道该文件是类并且是public
 *
 *
 *
 *
 *
 */
public class ClassDemo {
    public static void main(String[] args) {
        int a = 10;
        int b= 20;
        int c = a + b;
        System.out.println("c is = " + c);
    }
}
