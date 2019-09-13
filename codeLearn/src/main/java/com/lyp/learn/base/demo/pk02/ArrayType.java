package com.lyp.learn.base.demo.pk02;

public class ArrayType {
    public static void main(String[] args) {
        System.out.println("====================八种基本类型=======================================");
        System.out.println("------byte类型--------");
        byte [] arrayByte = new byte[5];
        System.out.println("直接输出数组: " + arrayByte);
        for(byte b : arrayByte){
            System.out.println(b);
        }
        System.out.println("length = " + arrayByte.length);
        System.out.println("第一个元素:" + arrayByte[0]);
        System.out.println();

        System.out.println("------short类型--------");
        short [] arrayShort = new short[5];
        System.out.println("直接输出数组:" + arrayShort);
        for(short s : arrayShort){
            System.out.println(s);
        }
        System.out.println("length = " + arrayShort.length);
        System.out.println("第一个元素:" + arrayShort[0]);
        System.out.println();

        System.out.println("------int类型--------");
        int [] arrayInt = new int[5];
        System.out.println("直接输出数组:" + arrayInt);
        for(int i : arrayInt){
            System.out.println(i);
        }
        System.out.println("length = " + arrayInt.length);
        System.out.println("第一个元素:" + arrayInt[0]);
        System.out.println();

        System.out.println("------long类型--------");
        long [] arrayLong = new long[5];
        System.out.println("直接输出数组:" + arrayLong);
        for(long l : arrayLong){
            System.out.println(l);
        }
        System.out.println("length = " + arrayLong.length);
        System.out.println("第一个元素:" + arrayLong[0]);
        System.out.println();

        System.out.println("------float类型--------");
        float [] arrayFloat = new float[5];
        System.out.println("直接输出数组:" + arrayFloat);
        for(float f : arrayFloat){
            System.out.println(f);
        }
        System.out.println("length = " + arrayFloat.length);
        System.out.println("第一个元素:" + arrayFloat[0]);
        System.out.println();

        System.out.println("------double类型--------");
        double [] arrayDouble = new double[5];
        System.out.println("直接输出数组:" + arrayDouble);
        for(double d : arrayDouble){
            System.out.println(d);
        }
        System.out.println("length = " + arrayDouble.length);
        System.out.println("第一个元素:" + arrayDouble[0]);
        System.out.println();

        System.out.println("------char类型--------");
        char [] arrayChar = new char[5];
        System.out.println("直接输出数组:" + arrayChar);
        for(char c : arrayChar){
            System.out.println(c);
        }
        System.out.println("length = " + arrayChar.length);
        System.out.println("第一个元素:" + arrayChar[0]);
        System.out.println();

        System.out.println("------boolean类型--------");
        boolean [] arrayBoolean = new boolean[5];
        System.out.println("直接输出数组:" + arrayBoolean);
        for(boolean b : arrayBoolean){
            System.out.println(b);
        }
        System.out.println("length = " + arrayBoolean.length);
        System.out.println("第一个元素:" + arrayBoolean[0]);
        System.out.println();
        System.out.println("========引用类型===========八种基本类型的包装类型=========================");
        System.out.println("------Byte类型--------");
        Byte [] arrayByte2 = new Byte[5];
        System.out.println("直接输出数组: " + arrayByte2);
        for(Byte b : arrayByte2){
            System.out.println(b);
        }
        System.out.println("length = " + arrayByte2.length);
        System.out.println("第一个元素:" + arrayByte2[0]);
        System.out.println();

        System.out.println("------Short类型--------");
        Short [] arrayShort2 = new Short[5];
        System.out.println("直接输出数组:" + arrayShort2);
        for(Short s : arrayShort2){
            System.out.println(s);
        }
        System.out.println("length = " + arrayShort2.length);
        System.out.println("第一个元素:" + arrayShort2[0]);
        System.out.println();

        System.out.println("------Integer类型--------");
        Integer [] arrayInteger = new Integer[5];
        System.out.println("直接输出数组:" + arrayInteger);
        for(Integer i : arrayInteger){
            System.out.println(i);
        }
        System.out.println("length = " + arrayInteger.length);
        System.out.println("第一个元素:" + arrayInteger[0]);
        System.out.println();

        System.out.println("------Long类型--------");
        Long [] arrayLong2 = new Long[5];
        System.out.println("直接输出数组:" + arrayLong2);
        for(Long l : arrayLong2){
            System.out.println(l);
        }
        System.out.println("length = " + arrayLong2.length);
        System.out.println("第一个元素:" + arrayLong2[0]);
        System.out.println();

        System.out.println("------Float类型--------");
        Float [] arrayFloat2 = new Float[5];
        System.out.println("直接输出数组:" + arrayFloat2);
        for(Float f : arrayFloat2){
            System.out.println(f);
        }
        System.out.println("length = " + arrayFloat2.length);
        System.out.println("第一个元素:" + arrayFloat2[0]);
        System.out.println();

        System.out.println("------Double类型--------");
        Double [] arrayDouble2 = new Double[5];
        System.out.println("直接输出数组:" + arrayDouble2);
        for(Double d : arrayDouble2){
            System.out.println(d);
        }
        System.out.println("length = " + arrayDouble2.length);
        System.out.println("第一个元素:" + arrayDouble2[0]);
        System.out.println();

        System.out.println("------Character类型--------");
        Character [] arrayCharacter = new Character[5];
        System.out.println("直接输出数组:" + arrayCharacter);
        for(Character c : arrayCharacter){
            System.out.println(c);
        }
        System.out.println("length = " + arrayCharacter.length);
        System.out.println("第一个元素:" + arrayCharacter[0]);
        System.out.println();

        System.out.println("------Boolean类型--------");
        Boolean [] arrayBoolean2 = new Boolean[5];
        System.out.println("直接输出数组:" + arrayBoolean2);
        for(Boolean b : arrayBoolean2){
            System.out.println(b);
        }
        System.out.println("length = " + arrayBoolean2.length);
        System.out.println("第一个元素:" + arrayBoolean2[0]);
        System.out.println();

        System.out.println("========引用类型=========String类型=========================");
        System.out.println("------String类型--------");
        String [] arrayStr = new String[5];
        System.out.println("直接输出数组:" + arrayStr);
        for(String s : arrayStr){
            System.out.println(s);
        }
        System.out.println("length = " + arrayStr.length);
        System.out.println("第一个元素:" + arrayStr[0]);
        System.out.println();
    }
}
