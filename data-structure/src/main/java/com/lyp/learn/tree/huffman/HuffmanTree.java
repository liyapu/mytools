package com.lyp.learn.tree.huffman;

/**
 * 哈夫曼树
 *
 */
public class HuffmanTree {

    public int [] [] huffmanCoding(int [] w){
        int n = w.length;        //字符个数，w存放n个字符的权值
        int m = n * 2 - 1;       //哈夫曼树的结点数
        HuffmanNode [] hn = new HuffmanNode[m];
        int i = 0;
        for(i = 0; i < n; i++){
            hn[i] = new HuffmanNode(w[i]);    //构造n个具有权值的结点
        }

        for(i = n; i < m; i++){               //构造哈夫曼树
            HuffmanNode min1 = selectMin(hn,i-1);
            min1.setJoin(true);
            HuffmanNode min2 = selectMin(hn,i-1);
            min2.setJoin(true);

            //构造min1和min2的父节点，并修改其父结点的权值
            hn[i] = new HuffmanNode();
            min1.setParent(hn[i]);
            min2.setParent(hn[i]);
            hn[i].setLchild(min1);
            hn[i].setRchild(min2);
            hn[i].setWeight(min1.getWeight()+min2.getWeight());
        }

        //从叶子到根逆向求每个字符的哈夫曼编码
        int [] [] huffCode = new int[n][n];
        for(int j = 0; j < n; j++){
            int start = n - 1;   //编码的开始位置，初始化为数组的结尾
            for(HuffmanNode current = hn[j],parent = current.getParent();
                parent != null; current = parent, parent = parent.getParent()){

                if(parent.getLchild().equals(current)){ //左孩子编码为0
                    huffCode[j][start--] = 0;
                }else{
                    huffCode[j][start--] = 1; //右孩子编码为1
                }
            }
            huffCode[j][start] = -1; //编码的开始标志为 -1，编码是 -1 之后的0,1序列
        }
        return huffCode;
    }

    /**
     * 在 hn[0, i -1] 选择不在哈夫曼树总且weight最小的结点
     * @param hn
     * @param end
     * @return
     */
    public HuffmanNode selectMin(HuffmanNode [] hn,int end){
        HuffmanNode min = hn[end];
        for(int i = 0; i <= end; i++){
            HuffmanNode h = hn[i];
            if(!h.isJoin() && h.getWeight() < min.getWeight()){
                min = h;
            }
        }
        return min;
    }


    /**
     * 构造哈夫曼树
     * @param w
     * @return
     */
    public int [] [] huffmanCoding2(int [] w){
        //权值数组长度
        int weightLength = w.length;
        //总的结点个数长度
        int length = weightLength * 2 - 1;

        //哈夫曼树的数组存储结点
        HuffmanNode [] hmnArr = new HuffmanNode[length];

        int i = 0;
        for(; i < weightLength; i++){
            hmnArr[i] = new HuffmanNode(w[i]);
        }

        for(i = weightLength; i < length; i++){
            HuffmanNode min1 = findMinNode2(hmnArr,i - 1);
            HuffmanNode min2 = findMinNode2(hmnArr,i - 1);

            hmnArr[i] = new HuffmanNode(min1.getWeight() + min2.getWeight());
            hmnArr[i].setLchild(min1);
            hmnArr[i].setRchild(min2);
            min1.setParent(hmnArr[i]);
            min2.setParent(hmnArr[i]);
        }

        int [] [] hmCodeArr = new int [weightLength][weightLength];
        for(i = 0; i < weightLength; i++){
            int start = weightLength - 1;
            for(HuffmanNode currNode = hmnArr[i], parent = currNode.getParent();
                parent != null; currNode = parent,parent = parent.getParent()){

                if(parent.getLchild().equals(currNode)){
                    hmCodeArr[i][start--] = 0;
                }else{
                    hmCodeArr[i][start--] = 1;
                }
            }
            hmCodeArr[i][start] = -1;
        }

        return hmCodeArr;
    }

    /**
     * 查找指定数组长度中的未使用的最下结点
     * @param arr
     * @param len
     * @return
     */
    private HuffmanNode findMinNode2(HuffmanNode [] arr, int len){
        //假设最后一个结点最小
        HuffmanNode minNode = arr[len];
        for(int i = 0; i <= len; i++){
            if(!arr[i].isJoin() && arr[i].getWeight() < minNode.getWeight()){
                minNode = arr[i];
            }
        }
        minNode.setJoin(true);
        return minNode;
    }
}
