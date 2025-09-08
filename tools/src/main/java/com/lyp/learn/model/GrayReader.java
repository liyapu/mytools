//package com.lyp.learn.model;
//
//import com.dianping.cat.Cat;
//import com.dianping.cat.message.Transaction;
//import com.google.common.base.Preconditions;
//import com.google.common.collect.Maps;
//import com.sankuai.mallscmplanning.gray.exception.GrayReaderException;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Map;
//import java.util.Random;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutorService;
//
///**
// * 灰度读
// * @param <T>
// */
//public class GrayReader<T> {
//
//    private static final Logger log = LoggerFactory.getLogger(GrayReader.class);
//
//    private final String grayReaderName;
//
//    private Callable<T> oldReader;
//
//    private Callable<T> newReader;
//
//    private AsyncComparator<T> asyncComparator;
//
//    private final ExecutorService threadPool;
//
//    private final GrayConfig grayConfig;
//
//    public GrayReader(String grayReaderName, ExecutorService threadPool, GrayConfig grayConfig) {
//        Preconditions.checkArgument(StringUtils.isNotBlank(grayReaderName), "grayReaderName can't be blank!");
//        Preconditions.checkNotNull(threadPool, "threadPool can't be null!");
//        Preconditions.checkNotNull(grayConfig, "grayConfig can't be null!");
//        grayConfig.check();
//        this.grayReaderName = grayReaderName;
//        this.threadPool = threadPool;
//        this.grayConfig = grayConfig;
//    }
//
//    public GrayReader<T> oldReader(Callable<T> oldReader) {
//        this.oldReader = oldReader;
//        return this;
//    }
//
//    public GrayReader<T> newReader(Callable<T> newReader) {
//        this.newReader = newReader;
//        return this;
//    }
//
//    public GrayReader<T> asyncComparator(AsyncComparator<T> asyncComparator) {
//        this.asyncComparator = asyncComparator;
//        return this;
//    }
//
//    public T read() {
//        Preconditions.checkNotNull(oldReader, "oldReader can not be null");
//        Preconditions.checkNotNull(newReader, "newReader can not be null");
//        Transaction transaction = Cat.newTransaction("GrayReader", grayReaderName);
//        try {
//            T result = switcher(grayConfig);
//            transaction.setSuccessStatus();
//            return result;
//        } catch (Exception e) {
//            transaction.setStatus(e);
//            logError(e, "read");
//            throw new GrayReaderException(e.getMessage(), e);
//        } finally {
//            transaction.complete();
//        }
//    }
//
//    private T switcher(GrayConfig grayConfig) {
//        T returnValue;
//        GrayReaderPhaseEnum phaseEnum = grayConfig.retrieveReaderPhaseEnum();
//        switch (phaseEnum) {
//            case ONLY_OLD: {
//                returnValue = callOldMethod();
//                break;
//            }
//            case BOTH_OLD_AND_NEW_SELECT_OLD: {
//                returnValue = callOldMethod();
//                compare(true, returnValue, grayConfig);
//                break;
//            }
//            case BOTH_OLD_AND_NEW_SELECT_NEW:
//                returnValue = callNewMethod();
//                compare(false, returnValue, grayConfig);
//                break;
//            case ONLY_NEW: {
//                returnValue = callNewMethod();
//                break;
//            } default: {
//                throw new IllegalArgumentException(String.format("invalid reader phase : %d", phaseEnum.getCode()));
//            }
//        }
//        return returnValue;
//    }
//
//    private T callOldMethod() {
//        return callMethod("OldMethod", oldReader);
//    }
//
//    private T callMethod(String name, Callable<T> reader) {
//        Transaction transaction = Cat.newTransaction("GrayReader", name + "." + grayReaderName);
//        T result;
//        try {
//            result = reader.call();
//            transaction.setSuccessStatus();
//        } catch (Exception e) {
//            transaction.setStatus(e);
//            throw new GrayReaderException(e.getMessage(), e);
//        } finally {
//            transaction.complete();
//        }
//        return result;
//    }
//
//    private T callNewMethod() {
//        return callMethod("NewMethod", newReader);
//    }
//
//    private void compare(final boolean oldFirstCaller, final T returnValue, GrayConfig grayConfig) {
//        // 流量控制
//        boolean dotIt = randomDotIt(grayConfig.getPercentage());
//        if (!dotIt) {
//            return;
//        }
//        // dot it
//        try {
//            this.threadPool.execute(() -> {
//                try {
//                    if (oldFirstCaller) {
//                        T newValue = callNewMethod();
//                        callCompare(returnValue, newValue);
//                    } else {
//                        T oldValue = callOldMethod();
//                        callCompare(oldValue, returnValue);
//                    }
//                } catch (Exception e) {
//                    logError(e, "GrayReaderCompare");
//                }
//            });
//        } catch (Exception e) {
//            logError(e, "compare");
//        }
//    }
//
//    private boolean randomDotIt(double percentage) {
//        if (percentage <= 0) {
//            return false;
//        }
//
//        if (percentage >= 100) {
//            return true;
//        }
//
//        percentage = percentage / 100.0;
//        Random random = new Random();
//        return random.nextDouble() < percentage;
//    }
//
//    private void callCompare(T oldValue, T newValue) {
//        Transaction transaction = Cat.newTransaction("GrayReader", "Compare." + grayReaderName);
//        try {
//            boolean result = asyncComparator.compare(oldValue, newValue);
//            if (!result) {
//                // 比对不通过，执行对应的活动
//                try {
//                    asyncComparator.actionWhenCompareFail(oldValue, newValue);
//                } catch (Exception e) {
//                    logError(e, "actionWhenCompareFail");
//                }
//            }
//            // 比对结果打点
//            Map<String, String> tags = Maps.newHashMap();
//            tags.put("isEqual", String.valueOf(result));
//            Cat.logMetricForCount(grayReaderName, tags);
//            transaction.setSuccessStatus();
//        } catch (Exception ex) {
//            transaction.setStatus(ex);
//            logError(ex, "callCompare");
//        } finally {
//            transaction.complete();
//        }
//    }
//
//    private void logError(Throwable e, String name) {
//        StringBuilder errorMsg = new StringBuilder(1000);
//        errorMsg.append("GrayReader ").append(name).append(" Exception, ")
//                .append("grayReaderName:").append(grayReaderName).append(", ")
//                .append("errorMsg:").append(e.getMessage());
//        if (e instanceof GrayReaderException && e.getCause() != null && e.getCause() instanceof Exception) {
//            e = e.getCause();
//        }
//        log.error(errorMsg.toString(), e);
//    }
//}
