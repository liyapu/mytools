package com.lyp.learn.test;

import redis.clients.jedis.Jedis;


/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-07-19 20:06
 */
public class MainTest {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    public static void main(String[] args) {
        int a = 60 * 24 * 3600 * 1000;
        long b = 60 * 24 * 3600 * 1000L;
        System.out.println(a);
        System.out.println(b);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);

    }

    /**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

}
