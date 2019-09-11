package com.lyp.learn.ppt;


@FunctionalInterface
public interface FunctionCreateDemo {

    /**
     * 函数式接口的  抽象方法
     *
     * @param message
     */
    void sayMessage(String message);

    /**
     * 函数式接口里是可以包含默认方法，
     * 因为默认方法不是抽象方法，其有一个默认实现，所以是符合函数式接口的定义的；
     */
    default void doSomeMoreWork1() {
        System.out.println("doSomeMoreWork1");
    }

    default void doSomeMoreWork2() {
        // Method body
        System.out.println("doSomeMoreWork2");
    }

    /**
     * 函数式接口里是可以包含静态方法，
     * 因为静态方法不能是抽象方法，是一个已经实现了的方法，所以是符合函数式接口的定义的；
     */
    static void printHello() {
        System.out.println("Hello");
    }

    static void printName() {
        System.out.println("name");
    }


    /**
     * 函数式接口里是可以包含Object里的public方法，
     * 这些方法对于函数式接口来说，不被当成是抽象方法（虽然它们是抽象方法）；
     * 因为任何一个函数式接口的实现，默认都继承了Object类，
     * 包含了来自java.lang.Object里对这些抽象方法的实现；
     *
     * @param obj
     * @return
     */
    @Override
    boolean equals(Object obj);

    @Override
    public String toString();

}
