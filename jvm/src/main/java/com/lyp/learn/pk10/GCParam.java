package com.lyp.learn.pk10;

/**
 *  -XX:+PrintCommandLineFlags
 *    查看命令行相关参数(包含使用的垃圾收集器)
 *
 *   使用命令行指令
 *   jinfo -flag 相关垃圾回收器参数 进程id
 *
 *
 *   使用 Serial串行回收器
 *    -XX:+UseSerialGC
 *   在HotSpot虚拟机中，使用 -XX:+UseSerialGC 参数可以指定年轻代和老年代都使用串行收集器
 *   等价于 新生代用 Serial GC ,且老年代使用 Serial Old GC
 *
 *   -XX:+UseParNewGC  表明新生代使用 ParNew GC
 *
 *   使用 Parallel Scavenge 回收器
 *    -XX:+UseParallelGC  表明新生代使用 parallel GC
 *    -XX:+UseParallelOldGC 表明老年代使用 parallel Old GC
 *    UseParallelGC  和 UseParallelOldGC 这两个参数只需要设置一个，相互激活对方
 *    也是 java 8 默认的垃圾回收器
 *
 *
 *   使用 cms 垃圾回收器
 *    -XX:+UseConcMarkSweepGC  表明老年代使用 CMS GC，同时，年轻代会触发对 ParNew 的使用
 *
 *
 *  使用 G1 回收器
 *   -XX:+UseG1GC
 *   也是 java9 默认的垃圾回收器
 */
public class GCParam {
    public static void main(String[] args) {
        System.out.println("打酱油的.......");
    }
}













