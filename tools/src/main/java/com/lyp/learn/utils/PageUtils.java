package com.lyp.learn.utils;

import com.lyp.learn.bean.Page;
import com.lyp.learn.ex.ParamException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description 翻页Utils
 */
public class PageUtils {

    public final static Integer LIMIT_5 = 5;
    /**
     * limit 10
     */
    public final static Integer LIMIT_10 = 10;
    /**
     * limit 20
     */
    public final static Integer LIMIT_20 = 20;
    /**
     * limit 50
     */
    public final static Integer LIMIT_50 = 50;
    /**
     * limit 100
     */
    public final static Integer LIMIT_100 = 100;

    /**
     * limit 200
     */
    public final static Integer LIMIT_200 = 200;

    /**
     * limit 300
     */
    public final static Integer LIMIT_300 = 300;

    /**
     * limit 500
     */
    public final static Integer LIMIT_500 = 500;

    /**
     * limit 9999
     */
    public final static Integer LIMIT_9999 = 9999;

    /**
     * offset 0
     */
    public final static Integer OFFSET_0 = 0;
    /**
     * 获取offset
     * @param page 页码
     * @param limit limit
     * @return {@link Integer}
     */
    public static Integer getOffset(Integer page,Integer limit){
        return (page - 1)*limit;
    }
    /**
     * 内存分页工具
     *
     * @param data 元数据
     * @param offset 分页信息
     * @param limit 分页信息
     * @param <E>
     * @return 分页后数据
     */
    public static <E> List<E> memoryPaging(List<E> data, Integer offset, Integer limit) {
        int pageSize = 0;
        int startIndex = 0;
        int endIndex = 0;
        if(offset == null || limit == null){
            endIndex = data.size();
        } else {
            pageSize =limit;
            startIndex = offset;
            endIndex = offset + pageSize;
        }
        if(endIndex > data.size()){
            endIndex = data.size();
        }
        if(startIndex >= endIndex){
            return new ArrayList<>();
        }
        return data.subList(startIndex,endIndex);
    }

    /*
     * 校验配置limit
     * @param paging 分页信息
     * @param limit 最大limit
     * @return
     */
    public static void checkLimit(Page paging, Integer limit) {
        if (Objects.isNull(paging)) {
            throw new ParamException("分页参数不能为空");
        }
        if (paging.getLimit()<1){
            throw new ParamException("分页参数设置过小，最小为1");
        }
        if (paging.getLimit() > limit) {
            throw new ParamException("分页参数设置过大，最大允许" + limit);
        }
        if (paging.getOffset() < 0) {
            throw new ParamException("分页参数异常");
        }
    }
}
