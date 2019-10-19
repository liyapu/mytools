package com.lyp.learn.guava.eventbus.demo1.monitor;

import com.google.common.eventbus.EventBus;
import com.lyp.learn.guava.eventbus.demo1.events.FileChangerEvent;

import java.nio.file.*;

public class DirectoryTargetMonitor implements TargetMonitor {

    private final EventBus eventBus;
    private Path path;
    private WatchService watchService;
    private volatile boolean start = false;

    public DirectoryTargetMonitor(EventBus eventBus, String directoryPath) {
        this.eventBus = eventBus;
        this.path = Paths.get(directoryPath);
    }

    @Override
    public void startMonitor() throws Exception {
        watchService = FileSystems.getDefault().newWatchService();
        this.path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_DELETE);
        System.out.println("the directory [" + path + "] is monitor ....");

        this.start = true;
        while (start){
            System.out.println("-----"+ start);
            WatchKey watchKey = null;
            try {
                watchKey = watchService.take();
                watchKey.pollEvents().forEach(event ->{
                    WatchEvent.Kind<?> kind = event.kind();
                    Path relativePath = (Path) event.context();
                    Path absolutePath = path.resolve(relativePath);
                    eventBus.post(new FileChangerEvent(absolutePath,kind));
                });
            }catch (Exception e){
                start = false;
            }finally {
                if(watchKey != null){
                    //必须重置，不然只能收到一次事件
                    watchKey.reset();
                }
            }
        }
    }

    @Override
    public void stopMonitor() throws Exception {
        System.out.println("the directory[" + path + "] will be strop....");
        Thread.currentThread().interrupt();
        this.start = false;
        this.watchService.close();
        System.out.println("the directory[" + path + "] will be strop done");
    }
}
