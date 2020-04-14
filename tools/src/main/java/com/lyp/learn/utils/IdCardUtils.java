package com.lyp.learn.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author zanxus
 * @version 1.0.0
 * @date 2018-05-21 12:03
 * @description
 */
public class IdCardUtils {

    private static Logger logger = LoggerFactory.getLogger(IdCardUtils.class);

    /**
     * 省，直辖市代码表： { 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
     * 21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
     * 33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
     * 42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
     * 51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
     * 63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
     */
    protected static Map<String, String> areaCodes = Maps.newHashMap();

    static {
        areaCodes.put("11", "北京");
        areaCodes.put("12", "天津");
        areaCodes.put("13", "河北");
        areaCodes.put("14", "山西");
        areaCodes.put("15", "内蒙古");
        areaCodes.put("21", "辽宁");
        areaCodes.put("22", "吉林");
        areaCodes.put("23", "黑龙江");
        areaCodes.put("31", "上海");
        areaCodes.put("32", "江苏");
        areaCodes.put("33", "浙江");
        areaCodes.put("34", "安徽");
        areaCodes.put("35", "福建");
        areaCodes.put("36", "江西");
        areaCodes.put("37", "山东");
        areaCodes.put("41", "河南");
        areaCodes.put("42", "湖北");
        areaCodes.put("43", "湖南");
        areaCodes.put("44", "广东");
        areaCodes.put("45", "广西");
        areaCodes.put("46", "海南");
        areaCodes.put("50", "重庆");
        areaCodes.put("51", "四川");
        areaCodes.put("52", "贵州");
        areaCodes.put("53", "云南");
        areaCodes.put("54", "西藏");
        areaCodes.put("61", "陕西");
        areaCodes.put("62", "甘肃");
        areaCodes.put("63", "青海");
        areaCodes.put("64", "宁夏");
        areaCodes.put("65", "新疆");
        areaCodes.put("71", "台湾");
        areaCodes.put("81", "香港");
        areaCodes.put("82", "澳门");
        areaCodes.put("91", "国外");
    }

    private static List<String> cityCode = Lists.newArrayList("11", "12", "13", "14", "15", "21", "22",
            "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
            "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63",
            "64", "65", "71", "81", "82", "91");
    // 每位加权因子
    private static int power[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    // 第18位校检码
    private static String verifyCode[] = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

    /**
     * 验证所有的身份证的合法性
     *
     * @param idcard
     * @return
     */
    public static boolean isValidIdCard(String idcard) {
        if (idcard.length() == 15) {
            idcard = convertIdCardTo18Bit(idcard);
        }
        return isValid18Idcard(idcard);
    }


    /**
     * <p>
     * 判断18位身份证的合法性
     * </p>
     * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * <p>
     * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
     * </p>
     * <p>
     * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码；
     * 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码；
     * 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
     * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
     * </p>
     * <p>
     * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4
     * 2 1 6 3 7 9 10 5 8 4 2
     * </p>
     * <p>
     * 2.将这17位数字和系数相乘的结果相加。
     * </p>
     * <p>
     * 3.用加出来和除以11，看余数是多少？
     * </p>
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3
     * 2。
     * <p>
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     * </p>
     *
     * @param idcard
     * @return
     */
    private static boolean isValid18Idcard(String idcard) {
        // 非18位为假
        if (!is18Idcard(idcard)) {
            return false;
        }
        // 获取前17位
        String idcard17 = idcard.substring(0, 17);
        // 获取第18位
        String idcard18Code = idcard.substring(17, 18);
        char pre17Chars[];
        String checkCode;
        // 是否都为数字
        if (NumberUtils.isDigits(idcard17)) {
            pre17Chars = idcard17.toCharArray();
        } else {
            return false;
        }
        if (pre17Chars != null) {
            String provinceId = idcard.substring(0, 2);
            String birthday = idcard.substring(6, 14);
            int year = Integer.parseInt(idcard.substring(6, 10));
            int month = Integer.parseInt(idcard.substring(10, 12));
            int day = Integer.parseInt(idcard.substring(12, 14));
            // 判断是否为合法的省份
            if (!cityCode.contains(provinceId)) {
                return false;
            }
            // 该身份证生出日期在当前日期之后时为假
            Date birthdate = null;
            try {
                birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (birthdate == null || new Date().before(birthdate)) {
                return false;
            }
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(birthdate);
            // 判断是否为合法的月份
            if (month < 1 || month > 12) {
                return false;
            }
            // 判断是否为合法的日期
            boolean dayValid = false;
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dayValid = (day >= 1 && day <= 31);
                    break;
                case 2: // 公历的2月非闰年有28天,闰年的2月是29天。
                    if (calendar.isLeapYear(calendar.get(Calendar.YEAR))) {
                        dayValid = (day >= 1 && day <= 29);
                    } else {
                        dayValid = (day >= 1 && day <= 28);
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dayValid = (day >= 1 && day <= 30);
                    break;
            }
            if (!dayValid) {
                return false;
            }
            if (!isCheckCodeValid(idcard18Code, pre17Chars)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCheckCodeValid(String idCard18Code, char[] preChars) {
        int bit[] = converCharToInt(preChars);
        int sum17 = getPowerSum(bit);
        // 将和值与11取模得到余数进行校验码判断
        String checkCode = getCheckCodeBySum(sum17);
        if (null == checkCode) {
            return false;
        }
        // 将身份证的第18位与算出来的校码进行匹配，不相等就为假
        if (!idCard18Code.equalsIgnoreCase(checkCode)) {
            return false;
        }
        return true;
    }

    /**
     * 将15位的身份证转成18位身份证
     *
     * @param idcard
     * @return
     */
    public static String convertIdCardTo18Bit(String idcard) {
        String idcard17 = null;
        if (idcard.length() == 18) {
            return idcard;
        }
        // 非15位身份证
        if (!is15Idcard(idcard)) {
            return null;
        }
        if (NumberUtils.isDigits(idcard)) {
            // 获取出生年月日
            String birthday = idcard.substring(6, 12);
            idcard17 = idcard.substring(0, 6) + "19" + birthday + idcard.substring(12);
            char c[] = idcard17.toCharArray();
            String checkCode;
            if (null != c) {
                int bit[] = converCharToInt(c);
                int sum17;
                sum17 = getPowerSum(bit);
                // 获取和值与11取模得到余数进行校验码
                checkCode = getCheckCodeBySum(sum17);
                // 获取不到校验位
                if (null == checkCode) {
                    return null;
                }
                // 将前17位与第18位校验码拼接
                idcard17 += checkCode;
            }
        } else { // 身份证包含数字
            return null;
        }
        return idcard17;
    }

    /**
     * 15位和18位身份证号码的基本数字和位数验校
     *
     * @param idcard
     * @return
     */
    public static boolean isIdcard(String idcard) {
        return StringUtils.isBlank(idcard) ? false : Pattern.matches(
                "(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)", idcard);
    }

    /**
     * 15位身份证号码的基本数字和位数验校
     *
     * @param idcard
     * @return
     */
    public static boolean is15Idcard(String idcard) {
        return StringUtils.isBlank(idcard) ? false : Pattern.matches(
                "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$",
                idcard);
    }

    /**
     * 18位身份证号码的基本数字和位数验校
     *
     * @param idcard
     * @return
     */
    public static boolean is18Idcard(String idcard) {
        return Pattern
                .matches(
                        "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$",
                        idcard);
    }


    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param bit
     * @return
     */
    private static int getPowerSum(int[] bit) {
        int sum = 0;
        if (power.length != bit.length) {
            return sum;
        }
        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     *
     * @param sum17
     * @return 校验位
     */
    private static String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
        }
        return checkCode;
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param c
     * @return
     * @throws NumberFormatException
     */
    private static int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }


    public static class Extractor {

        private String idCard;

        private String province;

        private String city;

        private String region;

        private Integer year;

        private Integer month;

        private Integer day;

        private String gender;

        private Date birthday;

        private Integer age;

        public Extractor(String idCard) {
            if (is15Idcard(idCard)) {
                this.idCard = convertIdCardTo18Bit(StringUtils.upperCase(idCard));
            } else {
                this.idCard = StringUtils.upperCase(idCard);
            }
        }

        public String getProvince() {
            if (province == null) {
                String provinceId = idCard.substring(0, 2);
                if (areaCodes.containsKey(provinceId)) {
                    this.province = areaCodes.get(provinceId);
                }
            }
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getRegion() {
            return region;
        }

        public Integer getYear() {
            return year;
        }

        public Integer getMonth() {
            return month;
        }

        public Integer getDay() {
            return day;
        }

        public String getGender() {
            if (this.gender == null) {
                String genderBit = idCard.substring(16, 17);
                if (Integer.parseInt(genderBit) % 2 == 1) {
                    this.gender = "男";
                } else {
                    this.gender = "女";
                }
            }
            return this.gender;
        }

        public Date getBirthday() {
            if (birthday == null) {
                String birthdayStr = idCard.substring(6, 14);
                try {
                    Date birthday = new SimpleDateFormat("yyyyMMdd").parse(birthdayStr);
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(birthday);
                    this.year = calendar.get(Calendar.YEAR);
                    this.month = calendar.get(Calendar.MONTH) + 1;
                    this.day = calendar.get(Calendar.DAY_OF_MONTH);
                    this.birthday = birthday;
                } catch (ParseException e) {
                    logger.info("parse birthday error with idCard:{}", idCard, e);
                }
            }
            return this.birthday;
        }

        public Integer getAge() {
            if (age == null) {
                Date birthday = getBirthday();
                LocalDate birthDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate curDate = LocalDate.now();
                int age = Period.between(birthDate, curDate).getYears();
                this.age = age;
            }
            return this.age;
        }

    }

}
