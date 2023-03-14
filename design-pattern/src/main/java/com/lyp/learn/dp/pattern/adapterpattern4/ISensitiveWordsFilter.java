package com.lyp.learn.dp.pattern.adapterpattern4;

/**
 * @author liyapu
 * @date 2023-03-13 09:38
 * @description
 */
public interface ISensitiveWordsFilter { // 统一接口定义

    String filter(String text);
}
