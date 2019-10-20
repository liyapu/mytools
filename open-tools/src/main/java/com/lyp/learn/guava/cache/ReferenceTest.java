package com.lyp.learn.guava.cache;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 在此类运行的 VM Options 添加如下参数
 * -Xms64M -Xmx64M -XX:+PrintGCDetails
 */
public class ReferenceTest {

    public static void main(String[] args) {
//        List<Ref> container = new ArrayList<>();
//        int index = 0;
//        // 1.强引用
//        for(;;){
//            index++;
//            Ref ref  = new Ref(index);
//            container.add(ref);
//            System.out.println("the " + index + " ref will be add container");
//
//            try {
//                TimeUnit.MILLISECONDS.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        List<SoftReference<Ref>> container = new ArrayList<>();
//        int index = 0;
//        // 1.软引用
//        for(;;){
//            index++;
//            SoftReference<Ref> ref  = new SoftReference<>(new Ref(index));
//            container.add(ref);
//            System.out.println("the " + index + " ref will be add container");
//
//            try {
//                TimeUnit.MILLISECONDS.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        List<WeakReference<Ref>> container = new ArrayList<>();
//        int index = 0;
//        // 1.weak引用
//        for(;;){
//            index++;
//            WeakReference<Ref> ref  = new WeakReference<>(new Ref(index));
//            container.add(ref);
//            System.out.println("the " + index + " ref will be add container");
//
//            try {
//                TimeUnit.MILLISECONDS.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        List<PhantomReference<Ref>> container = new ArrayList<>();
        int index = 0;
        // 1.phantom 幻影引用
        for(;;){
            index++;
            PhantomReference<Ref> ref  =new PhantomReference<>(new Ref(index),new ReferenceQueue<>());
            container.add(ref);
            System.out.println("the " + index + " ref will be add container");

            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Ref{
        private int index;
        //1M
        private byte[] data = new byte[1024 * 1024];
        public Ref(int index){
            this.index = index;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("the index [" + index + "] will be gc");
        }
    }
}
