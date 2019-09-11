package com.lyp.learn.dp.pattern.commanpattern;

/**
 * 传递命令对象Invoker：
 * 小爱同学
 */
public class XiaoAi {

    //持有命令对象
    private Command command;

    public XiaoAi(Command command){
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * 执行命令
     */
    public void doCommand(){
        command.execute();
    }
}
