package com.lyp.learn.pk9;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 *
 */
public class WeakReferenceTest {
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
        //创建对象，建立弱引用
        WeakReference<User> userSoftRef = new WeakReference<>(new User(1,"hello"));

        //从弱引用中重新获取对象
        System.out.println(userSoftRef.get());

        // 不管当前内存空间是否足够，都会回收弱引用对象
        System.gc();
        System.out.println("after gc");
        //垃圾回收之后获得弱引用对象
        System.out.println(userSoftRef.get());



    }
}











