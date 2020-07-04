package com.lyp.learn.graph.lianjiejuzhen;


import com.lyp.learn.graph.GraphKind;

public class MGraphTest {

    public static final int INFINITY = Integer.MAX_VALUE;

    public static void main(String[] args) {
        try {

            Object [] vexs = {"A","B","C","D","E","F","G"};
            int [] [] arcs = {
                    {INFINITY,1,INFINITY,1,INFINITY,INFINITY,INFINITY},
                    {1,INFINITY,1,INFINITY,INFINITY,INFINITY,INFINITY},
                    {INFINITY,1,INFINITY,1,INFINITY,INFINITY,INFINITY},
                    {1,INFINITY,1,INFINITY,INFINITY,INFINITY,INFINITY},
                    {INFINITY,INFINITY,INFINITY,INFINITY,INFINITY,1,INFINITY},
                    {INFINITY,INFINITY,INFINITY,INFINITY,1,INFINITY,1},
                    {INFINITY,INFINITY,INFINITY,INFINITY,INFINITY,1,INFINITY},
            };

            MGraph g = new MGraph(GraphKind.UDG,7,6,vexs,arcs);
            MGraph.CC_BFS(g);

            System.out.println("-------普里姆算法求最小生成树------");
            Object [] vexs2 = {"v0","v1","v2","v3","v4","v5"};
            int [][] arcs2 = {
                    {INFINITY,7,1,5,INFINITY,INFINITY},
                    {7,INFINITY,6,INFINITY,3,INFINITY},
                    {1,6,INFINITY,7,6,4},
                    {5,INFINITY,7,INFINITY,INFINITY,2},
                    {INFINITY,3,6,INFINITY,INFINITY,7},
                    {INFINITY,INFINITY,4,2,7,INFINITY}
            };
            MGraph g2 = new MGraph(GraphKind.UDG,6,10,vexs2,arcs2);
            Object [] [] tree2 = g2.PRIM(g2,"v1");
            for(int i = 0; i < tree2.length; i++){
                System.out.println(tree2[i][0] + " - " + tree2[i][1]);
            }

            System.out.println();
            System.out.println("求某个顶点到其余顶点的最短路径\n" +
                    " 戴克斯特拉算法(Dijkstra)");
            g2.shortesPathDIJ(g2,0);
            boolean [][] P = g2.getP();
            int [] D = g2.getD();
            for(int i = 0; i < D.length; i++){
                System.out.println("v0 -->" + vexs2[i] + "  :" + D[i]);
            }

            System.out.println();
            System.out.println("求每一对顶点之间的最短路径\n" + "弗洛伊德算法(Floyd)");
            Object [] vexs4 = {"A","B","C","D"};
            int [][] arcs4 = {
                    {0,15,3,INFINITY},
                    {10,0,2,INFINITY},
                    {INFINITY,INFINITY,0,2},
                    {INFINITY,8,4,0}
            };

            MGraph g4 = new MGraph(GraphKind.UDG,4,7,vexs4,arcs4);
            g4.floyd(g4);
            display(g4.getDD());
            findPlace(g4,g4.getDD());

            System.out.println("--------MGraphTest-------");
            MGraph mGraph = new MGraph();
           // mGraph.crateGraph();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出各村的最短路径长度
     * @param D
     */
    public static void display(int [] [] D){
        System.out.println("各村之间的最短路径长度为:");
        for(int v = 0; v < D.length; v++){
            for(int w = 0; w < D.length; w++){
                System.out.print(D[v][w] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 求出到其它各顶点最短路径长度之和最小的顶点，并输出最大路径信息
     * @param G
     * @param D
     * @throws Exception
     */
    public static void findPlace(MGraph G,int [][] D) throws Exception{
        int min = INFINITY;
        int sum = 0; //用于记录一个顶点到其它顶点的最短路径长度的和
        int u = -1;
        for(int v = 0; v < D.length; v++){
            sum = 0;
            for(int w = 0; w < D.length; w++){
                sum += D[v][w];  //求一点都其它顶点的最短长度之和
            }
            if(min > sum){
                min = sum;
                u = v;
            }
        }
        System.out.println("俱乐部应该设在 " + G.getVex(u) + " 村，其到各村的路径长度依次为: ");
        for(int i = 0; i < D.length; i++){
            System.out.print(D[u][i] + "\t");
        }
        System.out.println();
    }








}
