package com.lyp.learn.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created on 2015/3/9.
 */
public class AppVersionUtil {

    /**
     * 判断appVersion1 == appVersion2
     * @param appVersion1
     * @param appVersion2
     * @return
     * @throws Exception
     */
    public static boolean equal(String appVersion1, String appVersion2) {
        try {
            return compareVersion(appVersion1, appVersion2) == 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断appVersion1 > appVersion2
     * @param appVersion1
     * @param appVersion2
     * @return
     * @throws Exception
     */
    public static boolean greater(String appVersion1, String appVersion2) {
        try {
            return compareVersion(appVersion1, appVersion2) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断appVersion1 < appVersion2
     * @param appVersion1
     * @param appVersion2
     * @return
     * @throws Exception
     */
    public static boolean less(String appVersion1, String appVersion2) {
        try {
            return compareVersion(appVersion1, appVersion2) == -1;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断appVersion1 >= appVersion2
     * @param appVersion1
     * @param appVersion2
     * @return
     * @throws Exception
     */
    public static boolean greaterOrEqual(String appVersion1, String appVersion2) {
        try {
            return compareVersion(appVersion1, appVersion2) >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断appVersion1 <= appVersion2
     * @param appVersion1
     * @param appVersion2
     * @return
     * @throws Exception
     */
    public static boolean lessOrEqual(String appVersion1, String appVersion2) {
        try {
            return compareVersion(appVersion1, appVersion2) <= 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 比较app的版本号appVersion1和appVersion2的大小
     * @param appVersion1
     * @param appVersion2
     * @return  0 : appVersion1 = appVersion2,
     *         -1 : appVersion1 < appVersion2,
     *          1 : appVersion1 > appVersion2
     * @throws Exception
     */
    public static int compareVersion(String appVersion1, String appVersion2) throws Exception {

        if (StringUtils.isEmpty(appVersion1)) {
            if (StringUtils.isEmpty(appVersion2)) {
                return 0;
            }
            return -1;
        }

        if (StringUtils.isEmpty(appVersion2)) {
            return 1;
        }

        String[] arr1 = StringUtils.split(appVersion1, ".");
        String[] arr2 = StringUtils.split(appVersion2, ".");

        int minLength = Math.min(arr1.length, arr2.length);

        for (int i = 0; i < minLength; ++i) {
            int value1 = Integer.parseInt(arr1[i]);
            int value2 = Integer.parseInt(arr2[i]);
            if (value1 < value2) {
                return -1;
            } else if (value1 > value2) {
                return 1;
            }
        }

        if (arr1.length > arr2.length) {
            return 1;
        } else if (arr1.length < arr2.length) {
            return -1;
        } else {
            return 0;
        }
    }

}
