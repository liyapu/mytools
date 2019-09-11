package com.lyp.learn.dp.pattern.factory2;


public class EasyFactory {

    // 简单工厂，根据字符串创建相应的对象
    public static Operation createOperation(String name) throws Exception {
        Operation operationObj = null;
        switch (name){
            case "+":
                operationObj = new Add();
                break;
            case "-":
                operationObj = new Sub();
                break;
            case "*":
                operationObj = new Mul();
                break;
            case "/":
                operationObj = new Div();
                break;
             default:
                 throw new Exception("运算类型错误");
        }

        return operationObj;
    }
}
