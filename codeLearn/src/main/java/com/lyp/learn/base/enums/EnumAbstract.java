package com.lyp.learn.base.enums;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-21 20:58
 */
public enum EnumAbstract {
    FIRST{
        @Override
        public String getInfo() {
            return "111";
        }
    },
    SECOND{
        @Override
        public String getInfo() {
            return "22222";
        }
    },
    THREE{
        @Override
        public String getInfo() {
            return "3333";
        }
    };

    /**
     * 定义抽象方法
     */
    public abstract String getInfo();
}
