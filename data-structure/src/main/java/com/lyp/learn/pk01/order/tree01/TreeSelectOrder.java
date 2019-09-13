package com.lyp.learn.pk01.order.tree01;

/**
 * 树形选择排序
 */
public class TreeSelectOrder {

    public static void printArray(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                System.out.print(",");
            }
            System.out.print(array[i]);
        }
        System.out.println();
    }

    /**
     * 树形选择排序 ：
     * 对于直接选择排序来说，主要是进行n-1趟元素的比较，每趟比较n-2次，
     * 每趟比较取出一个最小值(也可以是最大值)，逐步使列表有序。
     * 但是第一趟的比较是可以为后续的比较提供信息的，使后续的比较次数大大减少，
     * 而后续的比较又可以为更后续的比较提供信息，这样就减少了比较的次数，减少了时间复杂度。
     *
     * 实现原理：
     * 第一步，首先对n个记录进行两两比较，得到较小的n/2个数再依次比较，依次类推
     * 直到得到一个最小值,这是一个构造完全二叉树的过程，根节点即为最小元素，叶子节点为列表元素。
     * 构造的此树的存储结构可以用数组表示方法，数组长度为2n-1。填充此树，比如
     * 列表元素为：49    38     65    97   76    13    27   49
     *
     * 构造的树为：                     13
     *                     38               13
     *                38       65       13       27
     *              49  38   65  97   76  13   27  49
     * 13为根结点位最小值，列表元素为叶子节点
     *
     * 第二步，移走最小元素，此时可重新为数组a的第一个位置赋值为此最小值，
     * 之后如果找出次小值则可以为第二个位置赋值，......
     *
     * 第三步，找出次小值，找出最小值在叶子节点的位置，从该节点开始，和其兄弟节点
     * 进行比较，修改从叶子节点到根节点的元素值，比较完毕后，根节点为次小值。
     * 第三步比较是利用了第一次比较提供的信息，因为第一步已经得到了两两比较的
     * 较小值，只要拿第一次与最小值比较的元素(即最小值的兄弟节点)与它们比较即可得最小值。
     * 即拿上述例子的76与27比较，然后27与38比较得到次小值27。
     * 重复第二和第三步，排序完成。
     *
     * PS:这里把移出去的叶子节点都要重设为最大值，可对此方法进行稍微改动
     * 可传一个最大值进来，这里是整型所以用了Integer.MAX_VALUE
     */
    public static int[] treeSelectSort(int[] array) {
        int len = array.length;
        int treeSize = 2 * len - 1;  //完全二叉树的节点数
        int low = 0;
        int[] tree = new int[treeSize];    //临时的树存储空间
        //由后向前填充此树，索引从0开始
        for (int i = len - 1, j = 0; i >= 0; --i, j++) {
            //填充叶子节点
            tree[treeSize - 1 - j] = array[i];
        }
        for (int i = treeSize - 1; i > 0; i -= 2) {
            //填充非终端节点
            tree[(i - 1) / 2] = tree[i - 1] < tree[i] ? tree[i - 1] : tree[i];
        }                  //不断移走最小节点
        int minIndex;
        while (low < len) {
            int min = tree[0];    //最小值
            array[low++] =  min;
            minIndex = treeSize - 1;
            //找到最小值的索引
            while (tree[minIndex] != min) {
                minIndex--;
            }
            tree[minIndex] = Integer.MAX_VALUE;  //设置一个最大值标志
            //找到其兄弟节点
            //此循环找到剩下最小者，放到tree[0]位置，上面会把tree[0]位置的放入到array中
            while (minIndex > 0) {      //如果其还有父节点
                if (minIndex % 2 == 0) {   //如果是右节点
                    tree[(minIndex - 1) / 2] = tree[minIndex - 1] < tree[minIndex] ? tree[minIndex - 1] : tree[minIndex];
                    minIndex = (minIndex - 1) / 2;
                } else {                   //如果是左节点
                    tree[minIndex / 2] = tree[minIndex] < tree[minIndex + 1] ? tree[minIndex] : tree[minIndex + 1];
                    minIndex = minIndex / 2;
                }
            }
        }

        return array;
    }


    private static int[] treeSelectSort2(int[] array) {
        int len = array.length;
        int treeSize = 2 * len - 1;  //完全二叉树的节点数
        int low = 0;
        Object[] tree = new Object[treeSize];    //临时的树存储空间
        //由后向前填充此树，索引从0开始
        for (int i = len - 1, j = 0; i >= 0; --i, j++) {
            //填充叶子节点
            tree[treeSize - 1 - j] = array[i];
        }
        for (int i = treeSize - 1; i > 0; i -= 2) {
            //填充非终端节点
            tree[(i - 1) / 2] = ((Comparable) tree[i - 1]).compareTo(tree[i]) < 0 ? tree[i - 1] : tree[i];
        }                  //不断移走最小节点
        int minIndex;
        while (low < len) {
            Object min = tree[0];    //最小值
            array[low++] = (int) min;
            minIndex = treeSize - 1;
            //找到最小值的索引
            while (((Comparable) tree[minIndex]).compareTo(min) != 0) {
                minIndex--;
            }
            tree[minIndex] = Integer.MAX_VALUE;  //设置一个最大值标志
            //找到其兄弟节点
            while (minIndex > 0) {      //如果其还有父节点
                if (minIndex % 2 == 0) {   //如果是右节点
                    tree[(minIndex - 1) / 2] = ((Comparable) tree[minIndex - 1]).compareTo(tree[minIndex]) < 0 ?
                            tree[minIndex - 1] : tree[minIndex];
                    minIndex = (minIndex - 1) / 2;
                } else {                   //如果是左节点
                    tree[minIndex / 2] = ((Comparable) tree[minIndex]).compareTo(tree[minIndex + 1]) < 0 ?
                            tree[minIndex] : tree[minIndex + 1];
                    minIndex = minIndex / 2;
                }
            }
        }

        return array;
    }


    public static void main(String[] args) {
        int[] array1 = new int[]{49,38,65,97,76,13,27,49};
        //int[] array1 = new int[]{4,1,5,6,2};
        printArray(array1);
        array1 = treeSelectSort(array1);
        printArray(array1);

        System.out.println();

        int[] array2 = new int[]{3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        printArray(array2);
        array2 = treeSelectSort2(array2);
        printArray(array2);
    }
}
