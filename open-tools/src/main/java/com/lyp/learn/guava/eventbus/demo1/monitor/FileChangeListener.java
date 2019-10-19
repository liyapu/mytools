package com.lyp.learn.guava.eventbus.demo1.monitor;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.lyp.learn.guava.eventbus.demo1.events.FileChangerEvent;

public class FileChangeListener {

    public FileChangeListener() {
    }

    @Subscribe
    public void onChange(FileChangerEvent event){
        System.out.println("DirectoryChangeListener onChange path:" + event.getPath() + ", kine :" + event.getKind());
    }
}
