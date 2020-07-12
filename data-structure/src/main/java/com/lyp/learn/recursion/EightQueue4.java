package com.lyp.learn.recursion;


/**
 * @author: liyapu
 * @description:
 * @date 2020-07-05 13:37
 */
public class EightQueue4 {


    int queen[] = new int[8];
    int count = 0;

    public static void main(String args[]) {
        EightQueue4 eq = new EightQueue4();
        eq.eightQueen(0);
        System.out.println("总共有 " + eq.count + " 摆放方法");
    }


    private void eightQueen(int currentColumn) {
        //这个for循环的目的是尝试讲皇后放在当前列的每一行
        for (int row = 0; row < 8; row++) {
            //判断当前列的row行是否能放置皇后
            if (isLegal(row, currentColumn)) {
                //放置皇后
                queen[currentColumn] = row;
                if (currentColumn != 7) {
                    //摆放下一列的皇后
                    eightQueen(currentColumn + 1);
                } else {
                    //递归结束，此时row要++了
                    count++;
                }
            }
        }//end for
    }


    /**
     * 判断当（currentRow,currentColumn)是否可以放置皇后
     *
     * @param currentColumn
     * @return
     */
    public boolean isLegal(int currentRow, int currentColumn) {
        //遍历前面几列
        for (int preColumn = 0; preColumn < currentColumn; preColumn++) {
            int row = queen[preColumn];
            //说明在子preColumn的低currentRow已经有了皇后
            if (row == currentRow) {
                return false;
            }

            //行与行的差值
            int rowDiff = Math.abs(row - currentRow);

            //列于列的差值
            int columnDiff = Math.abs(currentColumn - preColumn);
            //说明斜线上有皇后
            if (rowDiff == columnDiff) {
                return false;
            }
        }//end for

        //说明（currentRow,currentColumn)可以摆放。
        return true;
    }

}



