package com.lyp.learn.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;

import java.lang.reflect.Type;

class MyExtraTypeProvider implements ExtraProcessor, ExtraTypeProvider {
    public void processExtra(Object object, String key, Object value) {
        VO vo = (VO) object;
        vo.getAttributes().put(key, value);
    }

    public Type getExtraType(Object object, String key) {
        if ("value".equals(key)) {
            return int.class;
        }
        return null;
    }
};

public class ExtraTypeProviderDemo {
    public static void main(String[] args) {
        MyExtraTypeProvider myExtraTypeProvider = new MyExtraTypeProvider();

        VO vo = JSON.parseObject("{\"id\":123,\"address\":\"商丘\",\"adId\":\"668\",\"value\":\"123456\"}", VO.class, myExtraTypeProvider);
        System.out.println(vo.getId());
        System.out.println(vo.getAddress());
        System.out.println(vo.getAddress().getClass().getName());
        System.out.println(vo.getAttributes().get("value"));
        // value本应该是字符串类型的，通过getExtraType的处理变成Integer类型了
        System.out.println(vo.getAttributes().get("value").getClass().getName());
        System.out.println(vo.getAttributes().get("adId"));
        // value本应该是字符串类型的，通过getExtraType的处理变成Integer类型了
        System.out.println(vo.getAttributes().get("adId").getClass().getName());

    }
}
