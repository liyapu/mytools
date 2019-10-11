package com.lyp.learn.guava.io;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

/**
 * 自己模拟实现 Base64 编码
 */
public class MyBase {

    private static final String BASE_64_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final char[] BASE_B4_DICT = BASE_64_STR.toCharArray();

    private MyBase() {

    }

    /**
     * Base 64 编码
     */
    public static String encode(String plain){
        Preconditions.checkNotNull(plain);
        StringBuilder result = new StringBuilder();

        StringBuilder binaryResult = new StringBuilder();
        for(int i = 0; i < plain.length(); i++){
            String s = Integer.toBinaryString(plain.charAt(i));
            binaryResult.append(Strings.padStart(s,8,'0'));
        }
        List<String> stringList = Splitter.fixedLength(6).omitEmptyStrings().trimResults().splitToList(binaryResult);
        for(String str : stringList){
            String equalStr = "";
            int length = str.length();
            if(length < 6){
                if(length % 6 == 2){
                    equalStr = "==";
                    str = str.concat("0000");
                }else if(length % 6 == 4){
                    equalStr = "=";
                    str = str.concat("00");
                }
            }
            int index = Integer.parseInt(str,2);
            result.append(BASE_B4_DICT[index]);
            if(!equalStr.isEmpty()){
                result.append(equalStr);
            }
        }
        return  result.toString();
    }

    public static String decode(String encrypt){
        Preconditions.checkNotNull(encrypt);
        StringBuilder result = new StringBuilder();
        StringBuilder binaryResult = new StringBuilder();
        int times = 0;
        for(int i = 0; i < encrypt.length(); i++){
            char c = encrypt.charAt(i);
            if(Objects.equals(c,'=')){
                times++;
            }
        }
//        System.out.println(times);
        if(times >= 1){
            encrypt = encrypt.replaceAll("=","");
        }

        for(int i = 0; i < encrypt.length(); i++){
            int index = findIndex(encrypt.charAt(i));
            String s = Strings.padStart(Integer.toBinaryString(index),6,'0');
            if(i == encrypt.length() - 1) {
                if (times == 1) {
                    s = s.substring(0, 4);
                } else if (times == 2) {
                    s = s.substring(0, 2);
                }
            }
//            System.out.println(s);
            binaryResult.append(s);
        }
        List<String> stringList = Splitter.fixedLength(8).omitEmptyStrings().trimResults().splitToList(binaryResult);
        for(String str : stringList){
//            System.out.println(str);
            result.append((char)Integer.parseInt(str,2));
        }
        return result.toString();
    }

    private static int findIndex(char c){
        for(int i = 0; i < BASE_B4_DICT.length; i++){
            if(Objects.equals(c,BASE_B4_DICT[i])){
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test(){
        System.out.println(encode("hello"));
        System.out.println(decode("aGVsbG8="));
    }
}
