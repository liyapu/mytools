package com.lyp.learn.pk08.huffman;

import java.util.Arrays;

public class HuffmanTreeTest {
    /**
     * 打印二维数组
     * @param hmCodeArr
     */
    private static void printArray(int[][] hmCodeArr) {
        for(int i = 0 ; i < hmCodeArr.length; i++){
            for(int j = 0; j < hmCodeArr[i].length; j++){
                if(hmCodeArr[i][j] == -1){
                    for(int k = j; k < hmCodeArr[i].length; k++){
                        System.out.print(hmCodeArr[i][k] + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) {
        int [] weight = {6,30,8,9,15,24,4,12};
        //int [] weight = {23,11,5,3,29,14,7,8};
        HuffmanTree huffmanTree = new HuffmanTree();

        System.out.println("=======" + Arrays.toString(weight)+ "============");
        int [][] hmCodeArr = huffmanTree.huffmanCoding(weight);
        printArray(hmCodeArr);
        System.out.println("---------------");

        int [][] hmCodeArr2 = huffmanTree.huffmanCoding2(weight);
        printArray(hmCodeArr2);

    }

}
