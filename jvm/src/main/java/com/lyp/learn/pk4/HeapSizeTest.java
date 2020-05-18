package com.lyp.learn.pk4;

/**
 *     -Xms  用来设置堆空间(年轻代+老年代)的初始内存大小
 *           -X 是jvm的运行参数
 *           ms 是 memory start
 *     -Xmx  用来设置堆空间(年轻代+老年代)的最大内存大小
 *
 *     -Xms50M -Xmx50M
 *
 *     VMOptions : 配置如下，可以看到打印值，证明生效了
 *          -Xms500m  -Xmx3g
 *          -Xms500m  -Xmx3g -XX:+PrintGCDetails
 *
 *          -Xmx:maxMemory = 2863661056 字节
 *          -Xmx:maxMemory = 2731 MB
 *
 *         -Xms:totalMemory = 502792192 字节
 *         -Xms:totalMemory = 479 MB
 *
 *
 *
 *   -Xmx是最大堆大小，默认值是物理内存的1/4(<1GB)，
 *   -Xmx20m 设置 maxMemory 为 20 M，分配空间不能超过最大堆内存大小，否则会抛出 OutOfMemory 异常
 *
 *
 *   -Xms是初始堆大小，默认值是物理内存的1/64(<1GB),
 *   -Xms20m 设置 total Memory 为 20 M ,totalMemory 就是初始化堆大小，
 *   它的意思是一开始限定堆大小为多少，如果不够则可以扩充，但必须小于最大堆大小
 *
 *   首先设置 -Xmx20m -Xms5m ,最大堆空间20m,初始大小5m
 *
 *
 *   -Xmn：设置 新生代 大小
 *   新生代大小设置为 2M，-Xmn2m -Xms20m -Xmx20m -XX:+PrintGCDetails
 *   当新生代设置的比较小时，GC会发生的较为频繁，提前进入下一次GC
 *
 *   -SurvivorRatio：设置新生代中 Eden space 和 Survivor space 的大小
 *
 *
 *   https://www.jianshu.com/p/bcdae3c80c1d
 *   Java启动参数共分为三类；
 *
 * 其一是标准参数（-），所有的JVM实现都必须实现这些参数的功能，而且向后兼容；
 * 其二是非标准参数（-X），默认jvm实现这些参数的功能，但是并不保证所有jvm实现都满足，且不保证向后兼容；
 * 其三是非Stable参数（-XX），此类参数各个jvm实现会有所不同，将来可能会随时取消，需要慎重使用；
 * -Xms10m
 * 设置堆最小值为10M
 *
 * -Xmx10m
 * 设置堆最大值为10M
 *
 * -Xmn10m
 * 设置年轻代大小为10M
 *
 * -Xss设置每个线程的堆栈大小
 *
 * -XX:NewSize和-XX:MaxNewSize
 * 用于设置年轻代的大小，建议设为整个堆大小的1/3或者1/4,两个值设为一样大。
 *
 * -XX:SurvivorRatio=8
 * 年轻代中Eden区与两个Survivor区的比值。注意Survivor区有两个。如：8，表示Eden：Survivor=8：1，一个Survivor区占整个年轻代的1/10
 *
 * -XX:NewRatio=3
 * 设置年轻代(EC+S0C+S1C)和年老代(OC)的比值。如:为3，表示年轻代与年老代比值为1：3，年轻代占整个年轻代年老代和的1/4（jdk1.8，默认2）
 *
 * -XX:InitialTenuringThreshol和-XX:MaxTenuringThreshold
 * 用于设置晋升到老年代的对象年龄的最小值和最大值，每个对象在坚持过一次Minor GC之后，年龄就加1。
 *
 * -XX:+PrintGCDetails
 * 打印GC的具体信息
 *
 * -XX:+PrintTenuringDistribution
 * 这个参数用于显示每次Minor GC时Survivor区中各个年龄段的对象的大小。
 *
 * -XX:+PrintCommandLineFlags
 * 打印传递给虚拟机的显式和隐式参数（在log日志中第一行就输出启动参数）
 *
 * -XX:+HeapDumpOnOutOfMemoryError
 * OOM时导出堆到文件
 *
 * -XX:HeapDumpPath=d:/a.dump
 * dump出来的数据存放在D盘下a.dump中
 *
 * -XX:OnOutOfMemoryError=D:/printstack.bat %p
 * %p代表的是当前java进程的pid ，当发生内存溢出的时候执行printstack.bat这个脚本，通常可以用作线上内存溢出的报警机制，比如发短信或者邮件通知
 *
 * -XX:loggc:D:/a.log
 * 将jvm的日志存储到指定文件
 *
 * -XX:PretenureSizeThreshold=3M
 * 对象的大小大于3M时将直接进入老年代
 *
 * -XX:-HandlePromotionFailure
 * 在发送Minor GC之前，虚拟机会先检查老年代最大可用的连续空间是否大于新生代对象总空间，如果这个条件成立，那么Minor GC可用确保是安全的，如果不成立，则虚拟机会查看HandlePromotionFailure设置值是否允许担保失败，如果允许，会继续检查之前历次Minor GC中晋升到老年代的平均对象大小是否<老年代最大可用的连续空间，如果小于，将尝试进行一次Minor GC，尽管这次GC是有风险的，如果大于或者HandlePromotionFailure设置不允许冒险，那么这时要进行一次Full GC
 *
 * -verbose
 * -verbose:class 输出jvm载入类的相关信息，当jvm报告说找不到类或者类冲突时可此进行诊断。
 * -verbose:gc 输出每次GC的相关情况。
 * -verbose:jni 输出native方法调用的相关情况，一般用于诊断jni调用错误信息。
 *
 * -XX:+UseTLAB
 * 默认是开启的，TLAB内存空间非常小，默认占整个Eden区的1%。
 * 个人建议：不要关闭！！！
 *
 * -XX:+TLABSize
 * 自调整TLABRefillWasteFraction 阀值
 * 个人建议：不要调整，采用虚拟机默认调整！！！
 *
 * -XX:TLABRefillWasteFraction
 * 设置维护进入TLAB空间单个对象大小，比例值，默认1/64，对象大于该值会去Eden区创建。
 *
 * -XX:TLABWasteTargetPercent
 * 设置TLAB空间所占用Eden空间的百分比大小（打破1%）
 *
 * -XX:+PrintTLAB
 * 打印TLAB信息
 *
 * -XX:+UseBiasedLocking
 * 启用偏向锁
 *
 * -XX:BiasedLockingStartupDelay = 0
 * 配合-XX:+UseBiasedLocking参数使用，一般启动偏向锁默认是应用程序启动几秒钟之后才激活，设置为0则是关闭延迟
 *
 * -XX:PreBlockSpin=10
 * 控制多线程锁升级到轻量级锁时，其中自旋锁优化的自旋次数
 *
 * CMS常用参数
 * -XX:+UseConcMarkSweepGC
 * 开启并发标记清除（CMS）收集器
 *
 * -XX:+UseCMSInitiatingOccupancyOnly
 * 始终基于设定的阈值，不根据运行情况进行调整（陷阱）。
 * 用-XX+UseCMSInitiatingOccupancyOnly标志来命令JVM不基于运行时收集的数据来启动CMS垃圾收集周期。而是，当该标志被开启时，JVM通过CMSInitiatingOccupancyFraction的值进行每一次CMS收集。然而，请记住大多数情况下，JVM比我们自己能作出更好的垃圾收集决策。因此，只有当我们充足的理由(比如测试)并且对应用程序产生的对象的生命周期有深刻的认知时，才应该使用该标志。
 * eg： 如果没有 -XX:+UseCMSInitiatingOccupancyOnly 这个参数, 只有第一次会使用CMSInitiatingPermOccupancyFraction=68 这个值。JVM后面的情况会自动调整。
 * 如果设置了 -XX:+UseCMSInitiatingOccupancyOnly 这个参数，就会完全参照-XX:CMSInitiatingOccupancyFraction=XX 设定的值进行回收，摒弃了JVM自身的优化。
 *
 * -XX:CMSInitiatingOccupancyFraction=68
 * 使用CMS收集器的情况下，老年代使用了指定阈值的内存时，出发Old GC，默认是开启的，TLAB内存空间非常小，默认占整个Eden区的1%。
 * 默认为68，即当年老代的空间使用率达到68%时，会执行一次CMS回收。
 * （陷阱） 如果内存使用率增长过快，CMS执行Old GC回收♻️过程中，已经出现了内存不足的情况，此时CMS回收♻️就会失败（会报错：Concurrent Mode Failure），虚拟机会做降级：将启动SerialOld串行收集器进行垃圾回收，如果发生这种场景就会出现STW，直到垃圾回收结束🔚。这样就会造成应用程序停顿时间拉长。
 * 针对陷阱的建议：
 * -如果内存增长缓慢：建议将-XX:CMSInitiatingOccupancyFraction设置可以调高些，降低触发CMS回收♻️频率。
 * -如果内存增长很快：建议将-XX:CMSInitiatingOccupancyFraction设置值较低些，可以提前进行CMS GC回收♻️。
 *
 * G1常用参数
 * -XX:+UseG1GC
 * 开启G1回收器
 *
 * ‐XX:G1HeapRegionSize=2
 * 指定分区大小(1MB~32MB，且必须是2的幂)
 * Region大小：总堆的大小/2048(JVM最多可以2048个Region)
 *
 * -XX:InitiatingHeapOccupancyPercen=45
 * 默认值：45% ，当老年代占用整个堆空间45%的时候尝试触发新生代+老年代一起回收的混合回收阶段。
 *
 */
public class HeapSizeTest {

    private static final int M = 1024 * 1024;


    public static void main(String[] args) {
        //获取处理器数,逻辑上的 比如 4核8线程 的返回 8
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        // 返回 Java 虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        //返回 Java 虚拟机的内存初始总大小
        long totalMemory = Runtime.getRuntime().totalMemory();

        System.out.println("availableProcessors = " + availableProcessors);

        System.out.println("-Xmx:maxMemory = " + maxMemory + " 字节");
        System.out.println("-Xmx:maxMemory = " + (maxMemory/1024/1024) + " MB");
        System.out.println();

        System.out.println("-Xms:totalMemory = " + totalMemory + " 字节");
        System.out.println("-Xms:totalMemory = " + (totalMemory/1024/1024) + " MB");

        System.out.println();
        System.out.println("maxMemory:" + ( Runtime.getRuntime().maxMemory()/M) + "M");
        System.out.println("freeMemory:" + (Runtime.getRuntime().freeMemory()/M) + "M");
        System.out.println("totalMemory:" + (Runtime.getRuntime().totalMemory()/M) + "M");


        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
















