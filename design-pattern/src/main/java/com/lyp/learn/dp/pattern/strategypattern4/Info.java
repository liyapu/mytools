package com.lyp.learn.dp.pattern.strategypattern4;

/**
 * @author liyapu
 * @date 2023-03-28 09:39
 * @description 枚举策略
 * effect java P140
 * <p>
 * 我们真正想要的就是每当添加一个枚举常量时，就强和!选择一种加班报酬策略,幸运的是，有一种很好的方法可以实现这一点 。 这种想法就是将加班工资计算移到一个私有的嵌 套枚举中，
 * 将这个策略枚举( strategy enum)的实例传到 PayrollDay 枚举的构造器中。之 后 PayrollDay 枚举将加班工资计算委托给策略枚举，
 * PayrollDay 中就不需要 switch 语句或者特定于常量的方法实现了 。 虽然这种模式没有 switch 语句那么简洁，但更加安 全，也更加灵活 :
 */
public interface Info {

    /**
     * 例如，考虑用一个枚举表示薪资包中的 工作天数 。 这个枚举有一个方法，根据 给定的某工人的基本工资(按小时)以及当天的工作时间 ，来计算他当天的报酬 。
     * 在五个工 作日中，超过正常八小时的工作时间都会产生加班工资 ;在节假日中，所有工作都产生加班 工资 。
     * 利用 switch 语句，很容易通过将多个 case 标签分别应用到两个代码片段中，来完 成这一计算:
     */
// Enum that switches on its value to share code - questionable
    enum PayrollDay {
        //
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
        private static final int MINS_PER_SHIFT = 8 * 60;

        int pay(int minutesWorked, int payRate) {
            int basePay = minutesWorked * payRate;
            int overtimePay;
            switch (this) {
                case SATURDAY:
                case SUNDAY:
                    //Weekend
                    overtimePay = basePay / 2;
                    break;
                default:
                    // Weekday
                    overtimePay = minutesWorked <= MINS_PER_SHIFT ? 0 : (minutesWorked - MINS_PER_SHIFT) * payRate / 2;
            }
            return basePay + overtimePay;
        }
    }
    //不可否认，这段代码十分简洁，但是从维护的角度来看，它非常危险 。 假设将一个元素 添加到该枚举中，或许是一个表示假期天数的特殊值，但是忘记给 switch 语句添加相应的 case。 程序依然可以编译，但 pay方法会悄悄地将节假日的工资计算成正常工作日的工资。
    //为了利用特定于常量的方法实现安全地执行工资计算，你可能必须重复计算每个常量 的加班工资，或者将计算移到两个辅助方法中(一个用来计算工作日，一个用来计算节假 日)，并从每个常量调用相应的辅助方法 。 任何 一 种方法都会产生相当数量的样板代码，这
    //会降低可读性，并增加了出错的概率 。

}












