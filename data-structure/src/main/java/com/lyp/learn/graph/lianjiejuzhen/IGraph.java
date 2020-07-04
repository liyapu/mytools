package com.lyp.learn.graph.lianjiejuzhen;

/**
 * 图的抽象接口
 */
public interface IGraph {

    /**
     * 创建一个图
     */
    void crateGraph();

    /**
     * 返回图中的顶点数
     * @return
     */
    int getVexNum();

    /**
     * 返回图中的边数
     * @return
     */
    int getArcNum();

    /**
     * 给定顶点的位置v,返回其对应的顶点值
     * @param v [0,vexNum) vexNum 为顶点数
     * @return
     */
    Object getVex(int v) throws Exception;

    /**
     * 给定顶点的值vex，返回其在图中的位置
     * 如果图中不包含此顶点，则返回-1
     * @param vex
     * @return
     */
    int locateVex(Object vex);

    /**
     * 返回v的第一个邻接点
     * 若v没有邻接点，则返回-1
     * @param v [0,vexNum)
     * @return
     */
    int firstAdjVex(int v) throws Exception;

    /**
     * 返回v相对于w的下一个邻接点
     * 若w是v的最后一个邻接点，则返回-1
     * @param v   0<= v
     * @param w   w < vexNum
     * @return
     */
    int nextAdjVex(int v, int w) throws Exception;


    /**
     * 图的广度优先搜索算法
     * @param G
     */
    void BFSTraverse(IGraph G) throws Exception;

    /**
     * 图的深度优先搜索算法
     * @param G
     */
    void DFSTraverse(IGraph G) throws Exception;
















}
