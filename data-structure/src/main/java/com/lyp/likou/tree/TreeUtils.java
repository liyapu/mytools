package com.lyp.likou.tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-09 12:28
 */
public class TreeUtils {

    public static TreeNode buildTree() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);

        t1.left = t2;
        t1.right = t3;

        t2.left = t4;
        t2.right = t5;

        t3.left = t6;
        t3.right = t7;

        t4.left = t8;
        t4.right = t9;

        return t1;
    }

    public static TreeNode buildTree(Integer... arrs) {
        Map<Integer, TreeNode> map = new HashMap<>();
        for (int i = 0; i < arrs.length; i++) {
            map.put(i, new TreeNode(arrs[i]));
        }

        for (int i = 0; i < arrs.length; i++) {
            TreeNode root = map.get(i);
            TreeNode left = map.get(2 * i + 1);
            TreeNode right = map.get(2 * i + 2);
            if (root == null) break;
            root.left = left;
            root.right = right;
        }
        return map.get(0);
    }

    public static void printTree(TreeNode root) {
        int times = 10;
        pt(root, 10);
        pt(root.left, 10 - 1);
        pt(root.right, 10 - 1);
    }

    public static void pt(TreeNode root, Integer times) {
        for (int i = 0; i < times; i++) {
            System.out.print("\t");
        }
        System.out.println(root == null ? "null" : root.val);

    }

    public static void main(String[] args) {
        TreeNode root = buildTree(1, 2, 3, 4, 5, 6, 7, 8, 9);
        pirnt(root);
    }


    public static void pirnt(TreeNode root) {
        // 找到左边的最大偏移量
        int maxLeftOffset = findMaxOffset(root, 0, true);
        int maxRightOffset = findMaxOffset(root, 0, false);
        int offset = Math.max(maxLeftOffset, maxRightOffset);
        // 计算最大偏移量
        Map<Integer, PrintLine> lineMap = new HashMap();
        calculateLines(root, offset, lineMap, 0, true);
        Iterator<Integer> lineNumbers = lineMap.keySet().iterator();
        int maxLine = 0;
        while (lineNumbers.hasNext()) {
            int lineNumber = lineNumbers.next();
            if (lineNumber > maxLine) {
                maxLine = lineNumber;
            }
        }
        for (int i = 0; i <= maxLine; i++) {
            PrintLine line = lineMap.get(i);
            if (line != null) {
                System.out.println(line.getLineString());
            }
        }

    }

    private static void calculateLines(TreeNode parent, int offset, Map<Integer, PrintLine> lineMap, int level,
                                       boolean right) {
        if (parent == null) {
            return;
        }
        int nameoffset = parent.toString().length() / 2;
        PrintLine line = lineMap.get(level);
        if (line == null) {
            line = new PrintLine();
            lineMap.put(level, line);
        }
        line.putString(right ? offset : (offset - nameoffset), parent.toString());
        // 判断有没有下一级
        if (parent.getLeft() == null && parent.getRight() == null) {
            return;
        }
        // 如果有，添加分割线即/\
        PrintLine separateLine = lineMap.get(level + 1);
        if (separateLine == null) {
            separateLine = new PrintLine();
            lineMap.put(level + 1, separateLine);
        }
        if (parent.getLeft() != null) {
            separateLine.putString(offset - 1, "/");
            calculateLines(parent.getLeft(), offset - nameoffset - 1, lineMap, level + 2, false);
        }
        if (parent.getRight() != null) {
            separateLine.putString(offset + nameoffset + 1, "\\");
            calculateLines(parent.getRight(), offset + nameoffset + 1, lineMap, level + 2, true);
        }

    }

    /**
     * 需要打印的某一行
     *
     * @author zhuguohui
     *
     */
    private static class PrintLine {
        /**
         * 记录了offset和String的map
         */
        Map<Integer, String> printItemsMap = new HashMap<>();
        int maxOffset = 0;

        public void putString(int offset, String info) {
            printItemsMap.put(offset, info);
            if (offset > maxOffset) {
                maxOffset = offset;
            }
        }

        public String getLineString() {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i <= maxOffset; i++) {
                String info = printItemsMap.get(i);
                if (info == null) {
                    buffer.append(" ");
                } else {
                    buffer.append(info);
                    i += info.length();
                }
            }
            return buffer.toString();
        }

    }

    private static int findMaxOffset(TreeNode parent, int offset, boolean findLeft) {
        if (parent != null) {
            offset += parent.toString().length();
        }
        if (findLeft && parent.getLeft() != null) {
            offset += 1;
            return findMaxOffset(parent.getLeft(), offset, findLeft);
        }
        if (!findLeft && parent.getRight() != null) {
            return findMaxOffset(parent.getRight(), offset, findLeft);
        }
        return offset;
    }

//    public interface TreeNode {
//
//        String getPrintInfo();
//
//        TreeNode getLeft();
//
//        TreeNode getRight();
//    }

}
