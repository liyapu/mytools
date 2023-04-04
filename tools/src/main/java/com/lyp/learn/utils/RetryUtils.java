package com.lyp.learn.utils;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.google.common.base.Predicate;
import com.lyp.learn.ex.GateWayException;
import com.lyp.learn.ex.ParamException;
import com.lyp.learn.fun.SupportExceptionSupplier;
import java.util.concurrent.Callable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @description: 重试工具类
 * @author: ysc
 * @create: 2021/07/13 15:28
 */
public class RetryUtils {

    private static Logger logger = LogManager.getLogger(RetryUtils.class);


    private static class DefaultRetryListener implements RetryListener {

        @Override
        public <V> void onRetry(Attempt<V> attempt) {
            try {
                if (attempt.getAttemptNumber() > 1) {
                    logger.debug("第" + attempt.getAttemptNumber() + "次重试结束");
                }
            } catch (Exception e) {
                logger.error("监听重试情况代码有异常", e);
            }
        }
    }

    /**
     * 一定要看注释  一定要看注释   一定要看注释
     * 使用此方法时，需要根据T999时间，调整rpc的超时时间
     * 当excetionIfRetry=true，resultPredicate不为空时： 表示抛异常会重试、如果符合重试条件也会重试
     * 至少需要根据excetionIfRetry或者resultPredicate构造一个重试情况 【要么根据抛异常重试、要么根据构造条件重试、要么抛异常和符合构造条件都会进行重试】
     *
     * @param retryTimes      重试次数 【一共执行retryTimes次（包括第一次失败）】 重试次数需要大于1 重试次数需要大于1 重试次数需要大于1
     * @param excetionIfRetry true: 抛异常会重试【抛出runtime异常、checked异常时都会重试，但是抛出error不会重试。】 false:表示抛出异常不重试
     *                        如果抛异常不需要重试那么就传false，否则传true
     * @param resultPredicate 根据条件 判断是否重试，否则传null
     * @param retryFunction   重试的方法
     */
    public static <R> R call(int retryTimes, boolean excetionIfRetry, Predicate<R> resultPredicate,
        SupportExceptionSupplier<R> retryFunction) {
        Retryer retryer = buildRetryer(retryTimes, excetionIfRetry, resultPredicate, new DefaultRetryListener());
        return getResult(retryer, retryFunction);
    }

    /**
     * 构造重试对象 Retryer
     *
     * @param retryTimes      重试次数 【一共执行retryTimes次（包括第一次失败）】
     * @param excetionIfRetry true: 抛异常会重试【抛出runtime异常、checked异常时都会重试，但是抛出error不会重试。】 false:表示抛出异常不重试
     *                        如果抛异常不需要重试那么就传false，否则传true
     * @param resultPredicate 根据条件 判断是否重试
     * @param listener        监听重试方法执行情况
     */
    private static <T> Retryer<T> buildRetryer(int retryTimes, boolean excetionIfRetry, Predicate<T> resultPredicate,
        RetryListener listener) {
        if (retryTimes < 2) {
            throw new ParamException("retryTimes需要大于1");
        }
        if (excetionIfRetry == false && resultPredicate == null) {
            throw new ParamException("至少需要根据excetionIfRetry或者resultPredicate构造一个重试情况，请看方法注释");
        }
        RetryerBuilder retryerBuilder =
            RetryerBuilder.<T>newBuilder().withStopStrategy(StopStrategies.stopAfterAttempt(retryTimes));
        if (excetionIfRetry) {
            //【抛出runtime异常、checked异常时都会重试，但是抛出error不会重试。】
            retryerBuilder.retryIfException();
        }
        if (resultPredicate != null) {
            retryerBuilder.retryIfResult(resultPredicate);
        }
        if (listener != null) {
            retryerBuilder.withRetryListener(listener);
        }
        return retryerBuilder.build();
    }

    /**
     * 获取结果
     *
     * @param retryer       重试对象
     * @param retryFunction 需要重试的方法
     */
    private static <R> R getResult(Retryer retryer, SupportExceptionSupplier<R> retryFunction) {
        try {
            //重试逻辑
            try {
                return (R) retryer.call(new Callable() {
                    @Override
                    public R call() throws Exception {
                        logger.debug("执行业务方法");
                        return retryFunction.get();
                    }
                });
            } catch (RetryException e) {
                return (R) e.getLastFailedAttempt().get();
            }
        } catch (ParamException | GateWayException e) {
            throw e;
        } catch (Exception e) {
            throw new GateWayException(e.getMessage());
        }
    }
}
