package com.lyp.learn.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;

public class ParseProcessDemo {
    public static void main(String[] args) {

        /**
         * 使用ExtraProcessor 处理多余字段
         */
        ExtraProcessor myExtraProcessor = new ExtraProcessor() {
            @Override
            public void processExtra(Object object, String key, Object value) {
                System.out.println("----------------object=" + object);
                System.out.println(key + " : " + value);
                System.out.println();
                VO vo = (VO) object;
                //解析的多余字段存放到 attributes 集合里
                vo.getAttributes().put(key,value);
            }
        };

        VO vo = JSON.parseObject("{\"id\":123,\"address\":\"商丘\",\"name\":\"张三\",\"nickName\":\"小张\",}", VO.class, myExtraProcessor);
        System.out.println(vo.getId());
        System.out.println(vo.getAddress());
        System.out.println(vo.getAttributes().get("name"));
        System.out.println(vo.getAttributes().get("nickName"));


    }
}
