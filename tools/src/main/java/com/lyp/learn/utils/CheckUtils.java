package com.lyp.learn.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Date: 14-3-25
 * Time: 下午8:02
 */
public class CheckUtils {

    /**
     * 验证手机号(1开头的11位数字)
     * @param mobile
     * @return
     */
    public static boolean checkMobile(String mobile) {
        if (StringUtils.isNotBlank(mobile)) {
            return mobile.matches("^1\\d{10}$");
        }
        return false;
    }

    /**
     * 验证密码(6位以上)
     * @param password
     * @return
     */
    public static boolean checkPassword(String password) {
        if (null != password && password.length() > 5) {
            return true;
        }
        return false;
    }

    /**
     * 验证酷划客户端的版本号
     * @param version
     * @return
     */
    public static boolean checkAppVersion(String version) {
        return null != version && version.matches("^\\d+(.\\d+){1,3}$");
    }
    
    
//    public static int checkOS(String os) {
//
//        int iType = 0;
//        if (os==null) {
//        	iType = ForOS.BOTH.getType();
//        	return iType;
//        }
//        if (os.trim().equalsIgnoreCase("ios")||os.trim().equals("0"))
//        	iType = ForOS.IOS.getType();
//        else if (os.trim().equalsIgnoreCase("android")||os.trim().equals("1"))
//        	iType = ForOS.ANDROID.getType();
//
//        return iType;
//    }
}
