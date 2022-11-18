package com.lyp.learn.dict.bean;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoiReturnTypeBO {

    /**
     *
     */
    private Integer returnType;

    /**
     *
     */
    private String returnTypeName;

    /**
     * 隐藏的客户端渠道
     * null或者空: 表示所有渠道都显示
     * [3]:表示隐藏商家PC端
     */
    private List<Integer> hiddenClientChannelList;

    /**
     * 隐藏的创建单据类型
     * null或者空: 表示所有单据类型都显示
     * [2]:表示创建销毁单时隐藏
     */
    private List<Integer> hiddenCreateBillTypeList;

    /**
     * 隐藏的操作类型
     * null或者空: 表示所有操作类型都显示
     * [1]:表示查询操作
     */
    private List<Integer> hiddenOperationTypeList;

}