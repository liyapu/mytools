package com.lyp.learn.base.enums.year;

import com.beust.jcommander.internal.Lists;

public class YearEnumTest {
    public static void main(String[] args) {
        YearEnum.SUMMER.say();
        YearEnum.SUMMER.show(new Object(), Lists.newArrayList(new Object()));
    }
}