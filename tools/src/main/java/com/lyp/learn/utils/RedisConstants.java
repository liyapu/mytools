package com.lyp.learn.utils;

/**
 * Created by Joey
 * Desc: Redis 返回结果常量
 * Date: 2016/9/10
 * Version: 1.0
 */
public class RedisConstants {
    //****************************用户缓存信息的Key前缀或后缀*********************************

    //**************************** 活动缓存信息的Key前缀或后缀*********************************
    public static final String EVERYDAY_RED_BAG_ACTIVITY_LIMIT_TIME = "br.erba.lt.";//天天拆红包活动限制时间倒计时
    public static final String EVERYDAY_RED_BAG_ACTIVITY_DAILY_TIMES = "br.erba.dt."; //天天拆红包活动每天拆的次数
    public static final String EVERYDAY_REB_BAG_ACTIVITY_DAILY_SHARE_TIMES = "br.erba.dst."; //天天拆红包活动每天某次分享加金币的次数
    public static final String EVERYDAY_RED_BAG_ACTIVITY_STATUS = "br.erba.s."; //用户天天拆红包的状态
    public static final String EVERYDAY_RED_BAG_ACTIVITY_DISCIPLE = "br.erba.d.";//天天拆红包活动徒弟列表
    public static final String EVERYDAY_RED_BAG_ACTIVITY_VALID_DISCIPLE = "br.erba.vd.";//天天拆红包活动的有效徒弟

    public static final String CLIENT_UPDATE_TIMES = "client.update.times.";//客户端更新次数


    //**************************** redis缓存辅助常量值*********************************
    public static final String OK = "OK";
    public static final String LOCK = "lock";
    public static final String BROWSER = "br";

    //秒级单位
    public static final int EXPIRED_HALF_YEAR = 180 * 24 * 3600;//半年有效期
    public static final int EXPIRED_THREE_MONTH = 3 * 30 * 24 * 3600;//3月有效期
    public static final int EXPIRED_TWO_MONTH = 2 * 30 * 24 * 3600;//3月有效期
    public static final int EXPIRED_ONE_MONTH = 31 * 24 * 3600;//1月有效期
    public static final int EXPIRED_HALF_MONTH = 15 * 24 * 3600;//半月有效期
    public static final int EXPIRED_ONE_WEEK = 7 * 24 * 3600;//1周有效期
    public static final int EXPIRED_THREE_DAY = 3 * 24 * 3600;//3天有效期
    public static final int EXPIRED_TWO_DAY = 2 * 24 * 3600;//2天有效期
    public static final int EXPIRED_ONE_DAY = 24 * 3600;//1天有效期
    public static final int EXPIRED_ONE_HOUR = 3600;//1小时有效期
    public static final int EXPIRED_TEN_MINUTE = 10 * 60;//10分钟有效期

    //毫秒级单位
    public static final int EXPIRED_TEN_SECOND_OF_MILLISECOND = 10 * 1000;//10秒有效期
}
