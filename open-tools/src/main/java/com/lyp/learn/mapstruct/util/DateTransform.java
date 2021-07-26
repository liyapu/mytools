package com.lyp.learn.mapstruct.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author liyapu
 * @date 2021-07-26 15:46
 * @desc
 */
public class DateTransform {

    public static LocalDateTime strToDate(String str){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(str,df);
    }

}
