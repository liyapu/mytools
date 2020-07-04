package com.lyp.learn.graph.lianjiejuzhen;

import com.lyp.learn.graph.GraphKind;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 将图 G 的邻接矩阵存储在一个二维数组中
 */
public class MGraph implements IGraph {

    //用int的最大值，表示无穷大
    private final static int INFINITY = Integer.MAX_VALUE;

    //图的种类标志
    private GraphKind kind;

    //图的当前顶点数和边数
    private int vexNum,arcNum;

    //顶点
    private Object [] vexs;

    private int [][] arcs; //邻接矩阵

    public MGraph(){
        this(null,0,0,null,null);
    }

    public MGraph(GraphKind kind,int vexNum,int arcNum,Object [] vexs,int [][] arcs){
        this.kind = kind;
        this.vexNum = vexNum;
        this.arcNum = arcNum;
        this.vexs = vexs;
        this.arcs = arcs;
    }

    /**
     * 创建图
     */
    @Override
    public void crateGraph() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入图的类型: " );
        GraphKind kind = GraphKind.valueOf(scanner.next().trim().toUpperCase());
        switch (kind){
            case UDG:
                createUDG();
                return;
            case DG:
                createDG();
                return;
            case UDN:
                createUDN();
                return;
            case DN:
                createDN();
                return;
        }
    }

    /**
     * 创建无向图
     */
    private void createUDG(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请分别输入图的顶点数、图的边数:");
        vexNum = scanner.nextInt();
        arcNum = scanner.nextInt();
        vexs = new Object[vexNum];
        System.out.println("请分别输入图的各个顶点:");
        for(int v = 0; v < vexNum; v++){
            vexs[v] = scanner.next(); //构造顶点向量
        }
        arcs = new int[vexNum][vexNum];

        //初始化邻接矩阵
        for(int v = 0; v < vexNum; v++){
            for(int u = 0; u < vexNum; u++){
                arcs[v][u] = 0;
            }
        }
        System.out.println("请输入各个边的两个顶点:");
        for(int k = 0; k < arcNum; k++){
            int v = locateVex(scanner.next());
            int u = locateVex(scanner.next());
            arcs[v][u] = arcs[u][v] = 1;
        }
        printGraph();
    }

    private void printGraph(){
        System.out.print("顶点数组 vexs : " );
        for(int v = 0; v < vexs.length; v++){
            System.out.print(vexs[v] + " ");
        }
        System.out.println();
        for(int i = 0; i < arcs.length; i++){
            for(int j = 0; j < arcs[i].length; j++){
                System.out.print(arcs[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 创建有向图
     */
    private void createDG(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请分别输入图的顶点数、图的边数:");
        vexNum = scanner.nextInt();
        arcNum = scanner.nextInt();
        vexs = new Object[vexNum];
        System.out.println("请分别输入图的各个顶点:");
        for(int v = 0; v < vexNum; v++){
            vexs[v] = scanner.next(); //构造顶点向量
        }
        arcs = new int[vexNum][vexNum];

        //初始化邻接矩阵
        for(int v = 0; v < vexNum; v++){
            for(int u = 0; u < vexNum; u++){
                arcs[v][u] = 0;
            }
        }
        System.out.println("请输入各个边的两个顶点:");
        for(int k = 0; k < arcNum; k++){
            int v = locateVex(scanner.next());
            int u = locateVex(scanner.next());
            arcs[v][u] = 1;
        }
        printGraph();
    }

    /**
     * 创建无向网
     */
    private void createUDN(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请分别输入图的顶点数、图的边数:");
        vexNum = scanner.nextInt();
        arcNum = scanner.nextInt();
        vexs = new Object[vexNum];
        System.out.println("请分别输入图的各个顶点:");
        for(int v = 0; v < vexNum; v++){
            vexs[v] = scanner.next(); //构造顶点向量
        }
        arcs = new int[vexNum][vexNum];

        //初始化邻接矩阵
        for(int v = 0; v < vexNum; v++){
            for(int u = 0; u < vexNum; u++){
                arcs[v][u] = INFINITY;
            }
        }
        System.out.println("请输入各个边的两个顶点及其权值:");
        for(int k = 0; k < arcNum; k++){
            int v = locateVex(scanner.next());
            int u = locateVex(scanner.next());
            arcs[v][u] = arcs[u][v] = scanner.nextInt();
        }
        printGraph();
    }


    /**
     * 创建有向网
     */
    private void createDN(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请分别输入图的顶点数、图的边数:");
        vexNum = scanner.nextInt();
        arcNum = scanner.nextInt();
        vexs = new Object[vexNum];
        System.out.println("请分别输入图的各个顶点:");
        for(int v = 0; v < vexNum; v++){
            vexs[v] = scanner.next(); //构造顶点向量
        }
        arcs = new int[vexNum][vexNum];

        //初始化邻接矩阵
        for(int v = 0; v < vexNum; v++){
            for(int u = 0; u < vexNum; u++){
                arcs[v][u] = INFINITY;
            }
        }
        System.out.println("请输入各个边的两个顶点及其权值:");
        for(int k = 0; k < arcNum; k++){
            int v = locateVex(scanner.next());
            int u = locateVex(scanner.next());
            arcs[v][u] = scanner.nextInt();
        }
        printGraph();
    }


    @Override
    public int getVexNum() {
        return vexNum;
    }

    @Override
    public int getArcNum() {
        return arcNum;
    }

    @Override
    public Object getVex(int v) throws Exception {
        if(v < 0 || v >= vexNum){
            throw new Exception("第" + v + " 个顶点不存在");
        }
        return vexs[v];
    }

    @Override
    public int locateVex(Object vex) {
        for(int v = 0; v < vexNum; v++){
            if(vexs[v].equals(vex)){
                return v;
            }
        }
        return -1;
    }

    @Override
    public int firstAdjVex(int v) throws Exception {
        if(v < 0 || v > vexNum){
            throw new Exception("第" + v + "个顶点不存在");
        }
         for(int j = 0; j < vexNum; j++){
            if(arcs[v][j] != 0 && arcs[v][j] < INFINITY){
                return j;
            }
        }
        return -1;
    }

    @Override
    public int nextAdjVex(int v, int w) throws Exception {
        if(v < 0 || w >= vexNum){
            throw  new Exception("第" + v + "顶点，相对于 " + w + " 的邻接点，参数不合法");
        }
        for(int j = w + 1; j < vexNum; j++){
            if(arcs[v][j] != 0 && arcs[v][j] < INFINITY){
                return j;
            }
        }
        return -1;
    }


    public int[][] getArcs() {
        return arcs;
    }

    public void setArcs(int[][] arcs) {
        this.arcs = arcs;
    }

    /**
     * 图的广度优先搜索算法
     */
    //访问标志数组
    private static boolean [] visited;
    @Override
    public void BFSTraverse(IGraph G) throws Exception {
        int length = G.getVexNum();
        visited = new boolean[length];
        for(int v = 0; v < length; v++){
            visited[v] = false;
        }

        for(int v = 0; v < length; v++){
            if(!visited[v]){
                BFS(G,v);
            }
        }
    }

    private void BFS(IGraph g, int v) throws Exception {
        visited[v] = true;
        System.out.print(g.getVex(v).toString() + " ");
        Queue queue = new LinkedList();  //辅助队列queue
        queue.offer(v);
        while(!queue.isEmpty()){
            int u = (int)queue.poll(); // 对头元素出队并赋值给u;
            for(int w = g.firstAdjVex(u); w >= 0; w = g.nextAdjVex(u,w)){
                if(!visited[w]){  // w为u的尚未访问的邻接顶点
                    visited[w] = true;
                    System.out.print(g.getVex(w).toString() + " ");
                    queue.offer(w);
                }
            }
        }
    }

    /**
     * 图的深度优先搜索算法
     * @param G
     * @throws Exception
     */
    @Override
    public void DFSTraverse(IGraph G) throws Exception {
        int length = G.getVexNum();
        visited = new boolean[length];
        for(int v = 0; v < length; v++){
            visited[v] = false;
        }

        for (int v = 0; v < length; v++) {
            if(!visited[v]){
                DFS(G,v);
            }
        }
    }

    //从第v个顶点出发递归地深度优先遍历图g
    private void DFS(IGraph g, int v) throws Exception {
        visited[v] = true;
        System.out.print(g.getVex(v).toString() + " ");
        for(int w = g.firstAdjVex(v); w >= 0; w = g.nextAdjVex(v,w)){
            if(!visited[w]){ //对v的尚未访问的邻接顶点w递归调用DFS
                DFS(g,w);
            }
        }
    }


    /**
     * 应用广度优先搜索算法
     * 确定无向图的连通分量
     * @param G
     * @throws Exception
     */
    public static void CC_BFS(IGraph G) throws Exception{
        int length = G.getVexNum();
        boolean [] visited = new boolean[length]; //访问标志数组
        for(int v = 0; v < length; v++){
            visited[v] = false;
        }

        Queue queue = new LinkedList(); //辅助队列queue,
        Queue ccQueue = new LinkedList();//辅助队列ccQueue,用于记录连通分量的顶点
        int i = 0;       //用于记数连通分量的个数
        for(int v = 0; v < length; v++){
            ccQueue.clear();   //队列清空
            if(!visited[v]){
                visited[v] = true;
                ccQueue.offer(G.getVex(v));
                queue.offer(v);
                while(!queue.isEmpty()){
                    int u = (int)queue.poll();//对头元素出队并赋值给u
                    for(int w = G.firstAdjVex(v); w >= 0; w = G.nextAdjVex(u,w)){
                        if(!visited[w]){ //w为u的尚未访问的邻接顶点
                            visited[w] = true;
                            ccQueue.offer(G.getVex(w));
                            queue.offer(w);

                        }
                    }
                }

                System.out.println("图的第 " + (++i) + " 个连通分量为 : ");
                while(!ccQueue.isEmpty()){
                    System.out.print(ccQueue.poll().toString() + " ");
                }
                System.out.println();
            }
        }
    }

    /**
     * 普里姆算法
     * 从第u个顶点出发构造网G的最小生成树T
     * 返回由生成树边组成的二维数组
     * @param G
     * @param u
     * @return
     * @throws Exception
     */
    public Object [] [] PRIM(MGraph G,Object u) throws Exception{
        int length = G.getVexNum();
        Object [] [] tree = new Object[length-1][2];
        int count = 0;
        CloseEdge [] closeEdges = new CloseEdge[length];
        int k = G.locateVex(u);
        for(int j = 0; j < length;j++){  //辅助数组初始化
            if(j != k){
                closeEdges[j] = new CloseEdge(u,G.getArcs()[k][j]);
            }
        }

        closeEdges[k] = new CloseEdge(u,0); //初始，U = {u}
        for(int i = 1; i < length; i++){ //选择其余length-1个顶点
            k = getMinNum(closeEdges); //求出T的下一个点：第k个顶点
            tree[count][0] = closeEdges[k].getAdjVex();//生成树的边放入数组中
            tree[count][1] = G.getVex(k);
            count++;
            closeEdges[k].lowCost = 0; //第k个顶点并入U集
            for(int j = 0; j < length; j++){//新顶点并入U后重新选择最小边
                if(G.getArcs()[k][j] < closeEdges[j].lowCost){
                    closeEdges[j] = new CloseEdge(G.getVex(k),G.getArcs()[k][j]);
                }
            }
        }
        return tree;
    }

    /**
     * 在 closeEdges中选出lowCost最小且不为0的顶点
     */
    private int getMinNum(CloseEdge[] closeEdges) {
        int min = Integer.MAX_VALUE;
        int v = -1;
        for(int i = 0; i < closeEdges.length; i++){
            if(closeEdges[i].lowCost != 0 && closeEdges[i].lowCost < min){
                min = closeEdges[i].lowCost;
                v = i;
            }
        }
        return v;
    }


    /**
     * 求某个顶点到其余顶点的最短路径
     * 迪杰斯特拉算法(Dijkstra)
     */
    //v0到其余顶点的最短路径,
    // 若P[v][w]为true,则w是从v0到到v当前求得最短路径上的顶点
    private boolean [][] P;
    private int [] D;

    public boolean[][] getP() {
        return P;
    }

    public void setP(boolean[][] p) {
        P = p;
    }

    public int[] getD() {
        return D;
    }

    public void setD(int[] d) {
        D = d;
    }

    /**
     * 用 Dijkstra算法求有向网G的v0顶点到其余顶点v的最短路径P[v]及其路径长度D[v]
     */
    public void shortesPathDIJ(MGraph G,int v0){
        int vexNum = G.getVexNum();
        P = new boolean[vexNum][vexNum];
        D = new int[vexNum];
        //finish[v]为true当且仅当v属于S，即已经求得从v0到v的最短路径
        boolean [] finish = new boolean[vexNum];
        for(int v = 0; v < vexNum; v++){
            finish[v] = false;
            D[v] = G.getArcs()[v0][v];
            for(int w = 0; w < vexNum; w++){
                P[v][w] = false;  //设空路径
            }
            if(D[v] < INFINITY){
                P[v][v0] = true;
                P[v][v] = true;
            }
        }

        D[v0] = 0; //初始弧，v0顶点属于S集
        int v = -1;
        //开始主循环，每次求得v0到某个v顶点的最短路径，并加v到S集
        for(int i = 1; i < vexNum; i++){ //其余 vexNum - 1 个顶点
            int min = INFINITY;         //当前所知离v0顶点的最近距离
            for(int w = 0; w < vexNum; w++){
                if(!finish[w]){
                    if(D[w] < min){
                        v = w;
                        min = D[w];
                    }
                }
            }
            finish[v] = true;  //离v0顶点最近的v加入S集
            for(int w = 0; w < vexNum; w++){
                if(!finish[w] && G.getArcs()[v][w] < INFINITY && (min + G.getArcs()[v][w] < D[w])){
                    D[w] = min + G.getArcs()[v][w];
                    System.arraycopy(P[v],0,P[w],0,P[v].length);
                    P[w][w] = true;
                }
            }

        }

        //test
        System.out.println();
        System.out.println();
    }

    /**
     * 求每一对顶点之间的最短路径
     * 弗洛伊德算法(Floyd)
     */
    //顶点v和w之间的最短路径PP[v][w]，若PP[v][w][u] = true,
    // 则u是从v到w最短路径上的顶点
    private boolean [][][] PP;
    //顶点v和w之间最短路径的带权长度D[v][w]
    private int [][] DD;

    public boolean[][][] getPP() {
        return PP;
    }

    public void setPP(boolean[][][] PP) {
        this.PP = PP;
    }

    public int[][] getDD() {
        return DD;
    }

    public void setDD(int[][] DD) {
        this.DD = DD;
    }

    /**
     * 用floyd算法求有向图G中各对顶点v和w之间的最短路径pp[v][w]及其路径长度D[v][w]
     * @param G
     */
    public void floyd(MGraph G){
        int vexNum = G.getVexNum();
        PP = new boolean[vexNum][vexNum][vexNum];
        DD = new int[vexNum][vexNum];
        for(int v = 0; v < vexNum; v++){ //各对结点之间初始化已知路径及其距离
            for(int w = 0; w < vexNum; w++){
                DD[v][w] = G.getArcs()[v][w];
                for(int u = 0; u < vexNum; u++){
                    PP[v][w][u] = false;
                }
                if(DD[v][w] < INFINITY){ //从v到w有直接路径
                    PP[v][w][v] = true;
                    PP[v][w][w] = true;
                }
            }
        }

        for(int u = 0; u < vexNum; u++){
            for(int v = 0; v < vexNum; v++){
                for(int w = 0; w < vexNum; w++){
                    //从v经u到w的一条路径最短
                    if(DD[v][u] < INFINITY && DD[u][w] < INFINITY && DD[v][u] + DD[u][w] < DD[v][w]){
                        DD[v][w] = DD[v][u] + DD[u][w];
                        for(int i = 0; i < vexNum; i++){
                            PP[v][w][i] = PP[v][u][i] || PP[u][w][i];
                        }
                    }
                }
            }
        }
    }

}
