package com.lyp.learn.dp.pattern.adapterpattern4;

/**
 * @author liyapu
 * @date 2023-03-13 09:38
 * @description
 */
public class ASensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {

    private ASensitiveWordsFilter aFilter;

    @Override
    public String filter(String text) {
        String maskedText = aFilter.filterSexyWords(text);
        maskedText = aFilter.filterPoliticalWords(maskedText);
        return maskedText;
    }

    //...省略BSensitiveWordsFilterAdaptor、CSensitiveWordsFilterAdaptor...

}