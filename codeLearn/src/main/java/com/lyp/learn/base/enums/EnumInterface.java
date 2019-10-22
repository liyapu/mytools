package com.lyp.learn.base.enums;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 07:32
 *
 * 由于Java单继承的原因，enum类并不能再继承其它类，但并不妨碍它实现接口，因此enum类同样是可以实现多接口的
 */
interface Cat{
    void eat();
}

interface Sport{
    void run();
}
public enum EnumInterface implements Cat,Sport{
    FIRST,
    SECOND;

    @Override
    public void eat() {
        System.out.println("EnumInterface ... eat....");
    }

    @Override
    public void run() {
        System.out.println("EnumInterface ... run....");
    }
}
