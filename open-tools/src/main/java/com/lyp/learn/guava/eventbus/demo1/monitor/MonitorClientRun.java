package com.lyp.learn.guava.eventbus.demo1.monitor;

import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 使用Run执行程序时直接终止并出现上述提示信息Process finished with exit code -1073741819 (0xC0000005)
 *
 * 使用debug执行程序正常
 */
public class MonitorClientRun {
    public static void main(String[] args) throws Exception {
        EventBus eventBus = new EventBus();

        eventBus.register(new FileChangeListener());

        String targetDir = "D:\\temp";
        TargetMonitor monitor = new DirectoryTargetMonitor(eventBus,targetDir);

        //monitor start 之后，就是一个死循环，要启动其它线程，去stop
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(() -> {
            try {
                monitor.stopMonitor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 2, TimeUnit.MINUTES);
        //关闭任务
        scheduledExecutorService.shutdown();

        monitor.startMonitor();
    }
}
