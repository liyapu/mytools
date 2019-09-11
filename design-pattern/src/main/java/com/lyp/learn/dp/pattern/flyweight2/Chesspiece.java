package com.lyp.learn.dp.pattern.flyweight2;

/**
 * 棋子接口
 *
 */
public interface Chesspiece {

    /**
     * 落子方法
     * 对棋子的坐标进行定位
     * @param x
     * @param y
     */
    void put(int x, int y);
}
