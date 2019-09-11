package com.lyp.learn.dp.rule;

/**
 * 司机接口是一个抽象的概念，是对司机这一类事物的抽象，只要是司机，都有一个共同的行为，即开车.
 */
interface IDriver{

    /**
     * 司机开车方法
     * 依赖于 ICar 接口，并不依赖于具体的car
     */
     void drive(ICar car);
}

/**
 * 司机类，实现IDriver接口
 */
class Driver implements IDriver{

    @Override
    public void drive(ICar car) {
        car.run();
    }
}


/**
 * 汽车接口
 */
interface ICar{
    //汽车都能跑
    void run();
}

/**
 * 奔驰车类实现ICar接口
 */
class Benz implements ICar{

    @Override
    public void run() {
        System.out.println("奔驰车行驶....");
    }
}


/**
 * 宝马车实现ICar接口
 */
class BMW implements ICar{

    @Override
    public void run() {
        System.out.println("宝马车行驶....");
    }
}

/**
 * 在应用中，抽象不依赖于细节，即抽象(ICar)不依赖Benz 和 BMW 两个实现类(细节)。
 * 因此在高层次的模块代码中应用都是抽象的。
 */

public class TestDIP {
    public static void main(String[] args) {
        IDriver tom = new Driver();

        ICar benz = new Benz();
        tom.drive(benz);

        ICar bmw = new BMW();
        tom.drive(bmw);
    }
}
