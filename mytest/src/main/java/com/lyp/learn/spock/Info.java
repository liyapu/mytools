package com.lyp.learn.spock;

/**
 * @author liyapu
 * @date 2021-09-18 19:24
 * @desc
 *
 *  spock 单元测试
 *     学习一下 groovy语言，可以写出更优美的单元测试
 *
 *  一、介绍文章
 *     Axb的自我修养  http://blog.2baxb.me/archives/1398
 *  二、概念
 *      Specification
 *        在Spock中，待测系统(system under test; SUT) 的行为是由规格(specification) 所定义的。
 *        在使用Spock框架编写测试时，测试类需要继承自Specification类。
 *
 *  二、注意点
 *     a. 新建单元测试
 *        在 类名 上点击 Create Test ，然后选择 Spock 进行测试
 *     b. 执行单测
 *        在 测试类 上，选择 执行，不要在 def 方法上选择执行，否则报错(TestEngine with ID 'junit-vintage' failed to discover tests)
 *
 *
 */
public interface Info {
}
