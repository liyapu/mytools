//package com.lyp.learn.common;
//
//
//import java.util.function.Predicate;
//import java.util.function.Supplier;
//
///**
// * ZebraUtil
// *
// * @author at 2023/12/14 14:41
// */
//@SuppressWarnings("unused")
//public final class ZebraUtil {
//
//    private ZebraUtil() {
//    }
//
//    /**
//     * 强制查主库
//     *
//     * @param query 具体施行逻辑
//     * @param <T>   结果泛型
//     * @return Option
//     */
//    public static <T> Option<T> forceMaster(Supplier<T> query) {
//        ZebraForceMasterHelper.forceMasterInLocalContext();
//        try {
//            return Option.ofNullable(query).map(Supplier::get);
//        } finally {
//            ZebraForceMasterHelper.clearLocalContext();
//        }
//    }
//
//    /**
//     * 先查询从库，检查从库数据，不符合要求时，则重新查询主库
//     *
//     * @param funcQuery           查询方法
//     * @param testNeedForceMaster true：重查主库，false：返回从库数据
//     * @param <T>                 结果类型
//     * @return 最终查询结果
//     */
//    public static <T> Option<T> forceMasterIfNeed(Supplier<T> funcQuery, Predicate<T> testNeedForceMaster) {
//        Assert.notNull(funcQuery, "funcQuery不能为null");
//
//        T firstRes = funcQuery.get();
//
//        if (testNeedForceMaster.test(firstRes)) {
//            ZebraForceMasterHelper.forceMasterInLocalContext();
//            try {
//                return Option.ofNullable(funcQuery.get());
//            } finally {
//                ZebraForceMasterHelper.clearLocalContext();
//            }
//        } else {
//            return Option.ofNullable(firstRes);
//        }
//    }
//
//    /**
//     * 参考控制是否需要强制查询主库
//     *
//     * @param funcQuery       查询方法
//     * @param needForceMaster true：强制查主库，false：返回从库数据
//     * @param <T>             结果类型
//     * @return 最终查询结果
//     */
//    public static <T> Option<T> forceMasterWrapper(Supplier<T> funcQuery, Boolean needForceMaster) {
//        Assert.notNull(funcQuery, "funcQuery不能为null");
//
//        if (Option.ofNullable(needForceMaster).orElse(false)) {
//            ZebraForceMasterHelper.forceMasterInLocalContext();
//            try {
//                return Option.ofNullable(funcQuery.get());
//            } finally {
//                ZebraForceMasterHelper.clearLocalContext();
//            }
//        } else {
//            return Option.ofNullable(funcQuery.get());
//        }
//    }
//}
