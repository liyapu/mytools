package com.lyp.learn.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.*;

public class SerializeCustomerDemo {
    public static void main(String[] args) {


        /**
         * PropertyFilter 根据PropertyName和PropertyValue来判断是否序列化
         */
        PropertyFilter myPropertyFilter = new PropertyFilter() {
            @Override
            public boolean apply(Object object, String name, Object value) {
//                System.out.println("----------------object=" + object);
//                System.out.println("----------------name=" + name);
//                System.out.println("----------------value=" + value);
//                System.out.println();
                // 属性是id并且大于等于100时进行序列化
                if ("id".equals(name)) {
                    long id = ((Long) value).longValue();
                    return id >= 100;
                }
                return false;
            }
        };

        User user = new User(8L, "张八", 18, "北京市");
        // 序列化的时候传入filter
        String jsonString = JSON.toJSONString(user, myPropertyFilter);
        System.out.println("序列化,id=8：" + jsonString);

        User user2 = new User(888L, "李发发", 666, "河南省");
        String jsonString2 = JSON.toJSONString(user2, myPropertyFilter);
        System.out.println("序列化,id=888：" + jsonString2);

        /**
         * NameFilter 修改Key，如果需要修改Key,process返回值则可
         */
        NameFilter myNameFilter = new NameFilter() {
            @Override
            public String process(Object object, String name, Object value) {
//                System.out.println("----------------object=" + object);
//                System.out.println("----------------name=" + name);
//                System.out.println("----------------value=" + value);
                if("id".equals(name)){
                    return name + "$$$$$$$";
                }
                return name;
            }
        };
        System.out.println();
        System.out.println(JSON.toJSONString(user,myNameFilter));

        /**
         * ValueFilter 修改Value
         */
        ValueFilter myValueFilter = new ValueFilter() {
            @Override
            public Object process(Object object, String name, Object value) {
                if("address".equals(name)){
                    String newValue = "中国-" +  value ;
                    return newValue;
                }
                return value;
            }
        };
        System.out.println();
        System.out.println(JSON.toJSONString(user,myValueFilter));

        /**
         * BeforeFilter 序列化时在最前添加内容
         */
        BeforeFilter myBeforeFilter = new BeforeFilter() {
            @Override
            public void writeBefore(Object object) {
                User userTemp = (User)object;
                userTemp.setAddress("中国地理-" + userTemp.getAddress());
                userTemp.setName("China-" + userTemp.getName());
            }
        };
        System.out.println();
        System.out.println(JSON.toJSONString(user,myBeforeFilter));

        /**
         * AfterFilter 序列化时在最后添加内容
         */
        AfterFilter myAfterFilter = new AfterFilter() {
            @Override
            public void writeAfter(Object object) {
                User userTemp = (User) object;
                userTemp.setAddress("老家-" + userTemp.getAddress());
                userTemp.setAge(10);
            }
        };
        System.out.println();
        User user3 = new User(999L, "钱久久", 9999, "久久省市");
        System.out.println(JSON.toJSONString(user3,myAfterFilter));
        System.out.println(user3);
    }
}
