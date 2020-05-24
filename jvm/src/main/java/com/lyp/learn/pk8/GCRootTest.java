package com.lyp.learn.pk8;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author: liyapu
 * @description:
 * @date 2020-05-23 12:13
 */
public class GCRootTest {
    public static void main(String[] args) {
        List<Object> mumList = new ArrayList< >();

        Date birth = new Date();
        for (int i = 0; i < 100; i++) {
            mumList.add(String.valueOf(i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("数据添加完毕，请 dump1 文件");
        new Scanner(System.in).next();
        mumList = null;
        birth = null;

        System.out.println("numList、birth 已置空，请dump2,然后对比两个文件");
        new Scanner(System.in).next();

        System.out.println("结束");
    }
}
