package com.lyp.learn.dp.pattern.mementopattern2;

import java.util.LinkedList;

/**
 * 版本控制系统
 */
public class VersionControlSystem {
    //所有的备份
    LinkedList<BackUp> lists = new LinkedList<>();

    int nextVersion = 1;

    //添加备份
    public void add(BackUp backUp){
        backUp.version = nextVersion++;
        lists.add(backUp);
    }

    //获取版本
    public BackUp get(int version){
        for(BackUp backUp : lists){
            if(backUp.version == version){
                return backUp;
            }
        }
        return null;
    }

    public BackUp getLastVersion(){
        return lists.getLast();
    }

}
