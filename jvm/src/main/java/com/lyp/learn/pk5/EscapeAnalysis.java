package com.lyp.learn.pk5;

/**
 *  逃逸分析
 *
 *    如何快速的判断是否发生了逃逸？
 *       大家就看 new 的对象实体是否有可能在方法外被调用
 */
public class EscapeAnalysis {

    public EscapeAnalysis obj;

    /**
     *  方法返回 EscapeAnalysis 对象，发生逃逸
     */
    public EscapeAnalysis getInstance(){
        return  obj == null ? new EscapeAnalysis() : obj;
    }

    /**
     * 为成员属性赋值，发生逃逸
     *
     *  思考: 如果当前的 obj 引用声明为 static 的？
     *       任然会发生逃逸
     */
    public void setObj(){
        this.obj = new EscapeAnalysis();
    }

    /**
     * 对象的作用域仅在当前方法中有效，没有发生逃逸
     */
    public void useEscapeAnalysis(){
        EscapeAnalysis ea = new EscapeAnalysis();
    }

    /**
     * 引用成员变量的值，发生逃逸
     */
    public void useEscapeAnalysisInstance(){
        EscapeAnalysis ea = getInstance();
    }
}
