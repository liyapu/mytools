package com.lyp.learn.dp.pattern.factory1;

/**
 * 定义一个操作接口：
 */
public interface Operation {

    //定义一个方法，获取两数的结果
    double getResult(double number1, double number2) throws Exception;
}

//定义具体的操作类

/**
 * 加法实现类
 */
class Add implements Operation{

    @Override
    public double getResult(double number1, double number2) throws Exception {
        return number1 + number2;
    }
}


/**
 * 减法实现类
 */
class Sub implements Operation{

    @Override
    public double getResult(double number1, double number2) throws Exception {
        return number1 - number2;
    }
}

/**
 * 乘法实现类
 */
class Mul implements Operation{

    @Override
    public double getResult(double number1, double number2) throws Exception {
        return number1 * number2;
    }
}

/**
 * 除法实现类
 */
class Div implements Operation{

    @Override
    public double getResult(double number1, double number2) throws Exception {
        if(number2 == 0){
            throw new Exception("number2 不能为0");
        }
        return number1/number2;
    }
}
