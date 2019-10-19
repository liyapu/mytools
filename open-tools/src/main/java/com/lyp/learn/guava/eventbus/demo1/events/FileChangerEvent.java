package com.lyp.learn.guava.eventbus.demo1.events;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;

public class FileChangerEvent {
    private Path path;
    private WatchEvent.Kind<?> kind;

    public FileChangerEvent(Path path, WatchEvent.Kind<?> kind) {
        this.path = path;
        this.kind = kind;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public WatchEvent.Kind<?> getKind() {
        return kind;
    }

    public void setKind(WatchEvent.Kind<?> kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "FileChangerEvent{" +
                "path=" + path +
                ", kind=" + kind +
                '}';
    }
}
