package com.lyp.learn.dp.pattern.strategypattern4;

/**
 * @author liyapu
 * @date 2023-03-28 09:41
 * @description 枚举策略
 */
//The strategy enum pattern
public enum PayrollDay {
    //
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
    SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

    private final PayType payType;

    PayrollDay() {
        //默认
        this(PayType.WEEKDAY);
    }

    PayrollDay(PayType payType) {
        this.payType = payType;
    }

    //The strategy enum type
    private enum PayType {
        //工作日
        WEEKDAY {
            @Override
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked <= MINS_PER_SHIFT ? 0 : (minsWorked - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        //周末
        WEEKEND {
            @Override
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked * payRate / 2;
            }
        };

        private static final int MINS_PER_SHIFT = 8 * 60;

        /**
         * 计算薪资方法入口
         */
        int pay(int minsWorked, int payRate) {
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);
        }

        abstract int overtimePay(int minsWorked, int payRate);

    }

}
