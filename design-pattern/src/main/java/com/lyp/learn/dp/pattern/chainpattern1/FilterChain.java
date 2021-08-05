package com.lyp.learn.dp.pattern.chainpattern1;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤链条
 */
public class FilterChain implements Filter {

    //用 filters 来存储过滤规则
    List<Filter> filters = new ArrayList<>();
    //用于标记规则的引用顺序
    int index = 0;

    //往规则链条中添加规则
    public FilterChain addFilter(Filter filter){
        filters.add(filter);
        //代码的设计技巧:Chain链添加过滤规则结束后返回添加后的Chain，
        // 方便我们下面doFilter函数的操作
        return this;
    }


    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        //index 初始化为0
        //当index 达到filters的长度时，就返回不在执行
        if(index == filters.size()){
            System.out.println("\t\t\t chain doFilter end");
            return;
        }

        //每执行一个过滤规则，index自增1
        Filter f = filters.get(index++);
        //根据索引值获取对应的规律规则对字符串进行处理
        f.doFilter(request,response,chain);
    }

}
