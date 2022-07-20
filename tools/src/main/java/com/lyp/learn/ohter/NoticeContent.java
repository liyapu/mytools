package com.lyp.learn.ohter;

import java.util.List;

/**
 * @author liyapu
 * @date 2022-07-01 20:31
 * @description
 */
public class NoticeContent {

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知文本
     */
    private String noticeText;

    /**
     * 接收人列表（Long型）
     */
    private List<Long> receiverLongList;

    /**
     * 接收人列表（字符串）
     */
    private List<String> receiverStrList;
}
