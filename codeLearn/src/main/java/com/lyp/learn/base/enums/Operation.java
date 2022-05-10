package com.lyp.learn.base.enums;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 13:42
 * <p>
 * ​ 在枚举类型中声明一个抽象的apply方法，并在特定于常量的类主体中，
 * 用具体的方法覆盖每个常量的抽象apply方法。这种方法被称为特定于常量的方法实现
 * <p>
 * 枚举计算类
 */
public enum Operation {
    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public abstract double apply(double x, double y);

    @Override
    public String toString() {
        return symbol;
    }


    public static void main(String[] args) {
        double x = 4;
        double y = 2;
        for (Operation operation : Operation.values()) {
            System.out.printf("%f %s %f = %f%n", x, operation, y, operation.apply(x, y));
        }

        System.out.println();
        System.out.println(PLUS.name());
        System.out.println(PLUS.symbol);
        System.out.println(PLUS.ordinal());
    }
}

