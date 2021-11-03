package com.lyp.learn.utils;

import com.lyp.learn.ex.GateWayException;
import com.lyp.learn.ex.ParamException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.apache.commons.collections.CollectionUtils;

public class ValidateUtil {

    private static int SUCCESS_200 = 200;
    private static int SUCCESS_0 = 0;

    /**
     * ValidatorFactory跟validator都是线程安全对象
     */
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> boolean validate(T t, Class<?>... classes) {
        Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validate(t, classes);
        List<ConstraintViolation<T>> constraintViolationList = new ArrayList<>(constraintViolations);
        if (!CollectionUtils.isEmpty(constraintViolationList)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : constraintViolationList) {
                stringBuilder.append(constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()).append("\n");
            }
            throw new ParamException(stringBuilder.toString());
        }
        return true;
    }

    /**
     * 校验response
     *
     * @param code code
     * @param msg  msg
     * @return
     */
    public static void checkResponse(int code, String msg) {
        if (code != SUCCESS_0) {
            throw new GateWayException(msg);
        }
    }

    /**
     * 校验response 200表示成功
     *
     * @param code code
     * @param msg  msg
     * @return
     */
    public static void checkResponse200(int code, String msg) {
        if (code != SUCCESS_200) {
            throw new GateWayException(msg);
        }
    }

    /**
     * 校验时间区间是否合法
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param value     值
     * @param unit      值类型
     * @return {@link boolean}
     */
    public static boolean checkTimeInterval(long startTime, long endTime, int value, TimeUnit unit) {
        long duration = unit.toMillis(value);
        if (endTime - startTime > duration) {
            throw new ParamException("时间区间不合法");
        }
        return true;
    }


    public static void checkRequestTime(long startTime, long endTime){
        if (startTime<=0 || endTime<=0){
            throw new ParamException("查询时间不能为空");
        }
        if (endTime<startTime){
            throw new ParamException("查询开始时间不能大于结束时间");
        }
    }


}
