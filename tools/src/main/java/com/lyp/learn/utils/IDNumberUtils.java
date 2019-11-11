package com.lyp.learn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-04-28 17:30
 */
public class IDNumberUtils {
    /**
     * <pre>
     * 省、直辖市代码表：
     *     11 : 北京  12 : 天津  13 : 河北   14 : 山西  15 : 内蒙古
     *     21 : 辽宁  22 : 吉林  23 : 黑龙江 31 : 上海  32 : 江苏
     *     33 : 浙江  34 : 安徽  35 : 福建   36 : 江西  37 : 山东
     *     41 : 河南  42 : 湖北  43 : 湖南   44 : 广东  45 : 广西  46 : 海南
     *     50 : 重庆  51 : 四川  52 : 贵州   53 : 云南  54 : 西藏
     *     61 : 陕西  62 : 甘肃  63 : 青海   64 : 宁夏  65 : 新疆
     *     71 : 台湾
     *     81 : 香港  82 : 澳门
     *     91 : 国外
     * </pre>
     */
    final static String CITY_CODE[] = {"11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91"};

    /**
     * 效验码
     */
    final static char[] PARITYBIT = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    /**
     * 加权因子
     * Math.pow(2,  i - 1) % 11
     */
    final static int[] POWER = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * 身份证验证
     *
     * @param id 号码内容
     * @return 是否有效
     */
    public final static boolean isValid(String id) {
        if (id == null)
            return false;

        int len = id.length();
        if (len != 15 && len != 18)
            return false;

        //校验区位码
        if (!validCityCode(id.substring(0, 2)))
            return false;

        //校验生日
        if (!validDate(id))
            return false;

        if (len == 15)
            return true;

        //校验位数
        return validParityBit(id);

    }

    private static boolean validParityBit(String id) {
        char[] cs = id.toUpperCase().toCharArray();
        int power = 0;
        for (int i = 0; i < cs.length; i++) {
            //最后一位可以是X
            if (i == cs.length - 1 && cs[i] == 'X')
                break;

            // 非数字
            if (cs[i] < '0' || cs[i] > '9')
                return false;

            // 加权求和
            if (i < cs.length - 1) {
                power += (cs[i] - '0') * POWER[i];
            }
        }
        return PARITYBIT[power % 11] == cs[cs.length - 1];
    }

    private static boolean validDate(String id) {
        try {
            String birth = id.length() == 15 ? "19" + id.substring(6, 12) : id.substring(6, 14);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date birthDate = sdf.parse(birth);
            if (!birth.equals(sdf.format(birthDate)))
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static boolean validCityCode(String cityCode) {
        for (String code : CITY_CODE) {
            if (code.equals(cityCode))
                return true;
        }
        return false;
    }

    /**
     * 将15位的身份证转成18位身份证
     *
     * @param id
     * @return
     */
    final public static String id15To18(String id) {
        if (id == null || id.length() != 15)
            return null;

        if (!isValid(id))
            return null;

        String id17 = id.substring(0, 6) + "19" + id.substring(6);

        int power = 0;
        char[] cs = id17.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            power += (cs[i] - '0') * POWER[i];
        }

        // 将前17位与第18位校验码拼接
        return id17 + String.valueOf(PARITYBIT[power % 11]);
    }
}
