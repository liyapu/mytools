package com.lyp.learn.pk8;

/**
 *  测试Object类中的 finalize()方法，即对象的 finalization机制
 */
public class CanReliveObj {

    //类变量，属于 GC Root
    public static CanReliveObj obj;

    // 此方法只能被调用一次
    @Override
    protected void finalize() throws Throwable{
        super.finalize();;
        System.out.println("调用当前类重写的finalize");
        // 当前待回收的对象在 finalize()方法中与引用链上的一个对象obj建立了联系
        obj = this;
    }

    public static void main(String[] args) {
        try {
            obj = new CanReliveObj();
            //对象第一次成功拯救自己
            obj  = null;
            System.gc();//调用垃圾回收器
            System.out.println("第1次 gc");
            //因为 Finalizer线程优先级很低，暂停2s
            Thread.sleep(2000);
            if (obj == null) {
                System.out.println("obj is dead");
            }else{
                System.out.println("obj is still alive");
            }

            System.out.println("第2次 gc");
            //下面这段代码和上面完全相同，但是这次自救却失败了，finalize方法只会被调用1次
            obj  = null;
            System.gc();//调用垃圾回收器
            //因为 Finalizer线程优先级很低，暂停2s
            Thread.sleep(2000);
            if (obj == null) {
                System.out.println("obj is dead");
            }else{
                System.out.println("obj is still alive");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
