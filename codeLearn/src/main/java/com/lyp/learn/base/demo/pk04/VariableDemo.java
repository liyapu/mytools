package com.lyp.learn.base.demo.pk04;

public class VariableDemo {

    private static int staticCount = 10;
    private static final int STATIC_NUM = 10;

    private String name = "张三";
    private boolean isSuceess;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printMsg(String msg){
        String localName = "中国 ";
        String tempStr = localName + msg;
        System.out.println(tempStr);
    }

    public static void main(String[] args) {
        //访问类变量
        System.out.println(VariableDemo.staticCount);
        System.out.println(VariableDemo.STATIC_NUM);
        VariableDemo.staticCount++;
        System.out.println(VariableDemo.staticCount);
        System.out.println();

        VariableDemo vd = new VariableDemo();
        //访问类变量(一般不这样用)
        System.out.println(vd.staticCount);
        vd.staticCount++;
        System.out.println(vd.staticCount);
        System.out.println(vd.STATIC_NUM);

        //访问示例变量
        System.out.println(vd.name);
        System.out.println(vd.isSuceess);

        //访问实例方法
        vd.printMsg("好厉害!");
    }
}
