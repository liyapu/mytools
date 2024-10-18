package com.lyp.learn.utils.wrapper;

import com.lyp.learn.ex.GateWayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @Description: 远程方法调用封装类
 */
@Component
@Slf4j
public class RemoteCallWrapper {
    private static final int MAX_TRY_TIMES = 3;

    /**
     * 调用远程方法callable，如果失败发起重试，最多重试三次；三次重试失败后抛出异常并记录cat埋点和error日志；不记录请求和响应的info日志。
     *
     * @param request  请求
     * @param callable 调用方法
     * @param title    日志title
     * @param catType  cat埋点的type
     * @param <T>      请求类型
     * @param <R>      响应类型
     * @return 返回callable返回的结果
     */
    public <T, R> R retryCall(T request, RemoteCallable<T, R> callable, String title, String catType) {
        return retryCall(request, callable, title, catType, GsonUtil::toJsonStr, GsonUtil::toJsonStr, null);
    }

    /**
     * 调用远程方法callable，如果失败发起重试，最多重试三次；三次重试失败后抛出异常并记录cat埋点和error日志；不记录请求和响应的info日志。
     *
     * @param request  请求
     * @param callable 调用方法
     * @param title    日志title
     * @param catType  cat埋点的type
     * @param <T>      请求类型
     * @param <R>      响应类型
     * @return 返回callable返回的结果
     */
    public <T, R> R retryCall(T request, RemoteCallable<T, R> callable, String title, String catType, VerifyCode<R> verifyResp) {
        return retryCall(request, callable, title, catType, GsonUtil::toJsonStr, GsonUtil::toJsonStr, verifyResp);
    }

    /**
     * 调用远程方法callable，如果失败发起重试，最多重试三次；三次重试失败后抛出异常并记录cat埋点和error日志。
     *
     * @param request        请求
     * @param callable       调用方法
     * @param title          日志title
     * @param catType        cat埋点的type
     * @param reqLogSimpler  请求日志简化器，为null时不记录请求，可实现对请求简化后打印。
     * @param respLogSimpler 响应日志简化器，为null时不记录响应，可实现对响应简化后打印。
     * @param <T>            请求类型
     * @param <R>            响应类型
     * @return 返回callable返回的结果
     */
    public <T, R> R retryCall(T request, RemoteCallable<T, R> callable, String title, String catType,
                              Function<T, String> reqLogSimpler, Function<R, String> respLogSimpler) {
        return retryCall(request, callable, title, catType, reqLogSimpler, respLogSimpler, null);
    }

    /**
     * 调用远程方法callable，如果失败发起重试，最多重试三次；三次重试失败后抛出异常并记录cat埋点和error日志。
     *
     * @param request        请求
     * @param callable       调用方法
     * @param title          日志title
     * @param catType        cat埋点的type
     * @param reqLogSimpler  请求日志简化器，为null时不记录请求，可实现对请求简化后打印。
     * @param respLogSimpler 响应日志简化器，为null时不记录响应，可实现对响应简化后打印。
     * @param verifyResp     校验resp code
     * @param <T>            请求类型
     * @param <R>            响应类型
     * @return 返回callable返回的结果
     */
    public <T, R> R retryCall(T request, RemoteCallable<T, R> callable, String title, String catType,
                              Function<T, String> reqLogSimpler, Function<R, String> respLogSimpler, VerifyCode<R> verifyResp) {
        int tryTimes = MAX_TRY_TIMES;
        Exception exception = null;
        while (tryTimes-- > 0) {
            try {
                R response = callable.call(request);
                log(request, response, title, reqLogSimpler, respLogSimpler);
                // 错误码校验
                if (verifyResp == null || verifyResp.verify(response)) {
                    return response;
                } else {
//                    Cat.logEvent(catType, catType + "_errorCode");
                    log.error(title + " remote call response error code, req:{}, resp:{}", GsonUtil.toJsonStr(request),
                            GsonUtil.toJsonStr(response));
                }
            } catch (Exception ex) {
                exception = ex;
//                Cat.logEvent(catType, catType + "_fail");
                log.error(title + " remote call thrown exception, req:{}", GsonUtil.toJsonStr(request), ex);
            }
        }

//        Cat.logEvent(catType, catType + "_lastFail");
        log.error(title + " remote call last fail, req:{}", GsonUtil.toJsonStr(request));
        throw new GateWayException(title + " remote call last fail", exception);
    }

    private <T, R> void log(T request, R response, String title, Function<T, String> reqLogSimpler, Function<R, String> respLogSimpler) {
        if (reqLogSimpler != null && respLogSimpler != null) {
            log.info(title + " remote call success, req:{}, resp:{}.", reqLogSimpler.apply(request), respLogSimpler.apply(response));
        } else if (reqLogSimpler != null) {
            log.info(title + " remote call success, req:{}.", reqLogSimpler.apply(request));
        } else if (respLogSimpler != null) {
            log.info(title + " remote call success, resp:{}.", respLogSimpler.apply(response));
        }
    }

}
