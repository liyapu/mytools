package com.lyp.learn.enums;

/**
 * @Description 线程池配置名称
 */
public enum ThreadPoolNames {
    //定时任务线程池
    schedule("schedule", "定时线程池"),
    aaaa("aa", "查计划"),

    ;
    private String threadPoolName;
    private String desc;
    ThreadPoolNames(String threadPoolName, String desc){
        this.threadPoolName = threadPoolName;
        this.desc = desc;
    }

    public String getThreadPoolName() {
        return threadPoolName;
    }

    public void setThreadPoolName(String threadPoolName) {
        this.threadPoolName = threadPoolName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
