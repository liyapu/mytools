package com.lyp.learn.spock

import spock.lang.Specification

/**
 * @author liyapu* @date 2021-09-18 20:37
 * @desc
 */
class CalculatorTest extends Specification {
    def calculator  = new Calculator();

    def setupSpec() {
        System.out.println(">>>>>>>>>>>> setupSpec----第一个方法开始前执行--")

    }
    def cleanupSpec() {
        System.out.println(">>>>>>>>>>>> cleanupSpec----最后一个方法执行完后执行--")
    }

    void setup() {
        System.out.println("-------setup----每个方法前执行--")
    }

    void cleanup() {
        System.out.println("-------cleanup---每个方法后执行--")
        System.out.println()
    }

    def "Add"() {
        expect:
        calculator.add(10,8) == 18;
    }

    def "Minus"() {
        expect:
        calculator.minus(10,8) == 2;
    }
}
