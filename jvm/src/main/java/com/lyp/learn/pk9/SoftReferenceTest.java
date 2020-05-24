package com.lyp.learn.pk9;

import org.apache.poi.ss.formula.functions.T;

import java.lang.ref.SoftReference;

/**
 *  -Xms10m -Xmx10m
 */
public class SoftReferenceTest {
    public static class User{
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        //创建对象，建立软引用
//        SoftReference<User> userSoftRef = new SoftReference<>(new User(1,"hello"));
        //上面的一行代码，等价于如下的三行代码
        User u1 = new User(2,"world");
        SoftReference<User> userSoftRef = new SoftReference<>(u1);
        u1 = null; // 取消强引用


        //从软引用中重新获得强引用对象
        System.out.println(userSoftRef.get());

        System.gc();
        System.out.println("after gc");
        //垃圾回收之后获得软引用对象
        System.out.println(userSoftRef.get());

        try {
            //让系统认为内存资源紧张,此对象无法放下，会回收软引用的可达对象
            byte[] buffe= new byte[9* 1024 * 1024];
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            //再次用软引用中获取数据
            //在报OOM之前，垃圾回收器会回收软引用的可达对象
            System.out.println(userSoftRef.get());
            System.out.println("---------");
        }


    }
}











