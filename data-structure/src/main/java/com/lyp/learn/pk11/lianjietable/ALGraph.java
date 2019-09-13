package com.lyp.learn.pk11.lianjietable;

import com.lyp.learn.DataStructure.pk11.GraphKind;

import java.util.Scanner;
import java.util.Stack;

public class ALGraph implements IGraph {

    //图的种类标志
    private GraphKind kind;

    //图的当前顶点数和边数
    private int vexNum,arcNum;

    //顶点数组
    private VNode [] vexs;

    public ALGraph(){
        this(null,0,0,null);
    }

    public ALGraph(GraphKind kind,int vexNum,int arcNum,VNode [] vexs){
        this.kind = kind;
        this.vexNum = vexNum;
        this.arcNum = arcNum;
        this.vexs = vexs;
    }

    public VNode[] getVexs() {
        return vexs;
    }

    public void setVexs(VNode[] vexs) {
        this.vexs = vexs;
    }

    /**
     * 创建图
     */
    @Override
    public void crateGraph() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入图的类型:");
        GraphKind kind = GraphKind.valueOf(scanner.next().trim().toUpperCase());
        switch (kind){
            case DG:
                createDG();
                return;
            case UDG:
                createUDG();
                return;
            case DN:
                createDN();
                return;
            case UDN:
                createUDN();
                return;

        }
    }

    /**
     * 创建有向图
     */
    private void createDG(){

    }

    /**
     * 创建无向图
     */
    private void createUDG(){

    }

    /**
     * 创建有向网
     */
    private void createDN(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请分别输入图的顶点数、图的边数:");
        vexNum = scanner.nextInt();
        arcNum = scanner.nextInt();
        vexs = new VNode[vexNum];
        System.out.println("请分别输入图的各顶点:");
        for(int v = 0; v < vexNum; v++){
            vexs[v] = new VNode(scanner.next());
        }

        System.out.println("请输入各边的顶点及其权值:");
        for(int k = 0; k < arcNum; k++){
            int v = locateVex(scanner.next());//弧尾
            int u = locateVex(scanner.next());//弧头
            int value = scanner.nextInt();
            addArc(v,u,value);
        }
    }

    /**
     * 在位置为v,u的顶点之间，添加一条弧，其权值为value
     * @param v
     * @param u
     * @param value
     */
    private void addArc(int v, int u, int value) {
        ArcNode arc = new ArcNode(u,value);
        arc.setNextArc(vexs[v].getFirstArc());
        vexs[v].setFirstArc(arc);
    }

    /**
     * 创建无向网
     */
    private void createUDN(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入图的顶点数、图的边数");
        vexNum = scanner.nextInt();
        arcNum = scanner.nextInt();
        vexs = new VNode[vexNum];
        System.out.println("请分别输入图的各顶点:");
        for(int v = 0; v < vexNum; v++){   //构造顶点向量
            vexs[v] = new VNode(scanner.next());
        }
        System.out.println("请输入各边的顶点及其权值:");
        for(int k = 0; k < arcNum; k++){
            int v = locateVex(scanner.next());
            int u = locateVex(scanner.next());
            int value = scanner.nextInt();
            addArc(v,u,value);
            addArc(u,v,value);
        }
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
            throw new Exception("第" + v + "个顶点不存在");
        }
        return vexs[v].getData();
    }

    @Override
    public int locateVex(Object vex) {
        for(int v = 0; v < vexNum; v++){
            if(vexs[v].getData().equals(vex)){
                return v;
            }
        }
        return -1;
    }

    @Override
    public int firstAdjVex(int v) throws Exception {
        if(v < 0 || v >= vexNum){
            throw new Exception("第" + v + " 个顶点不存在");
        }
        VNode vex = vexs[v];
        if(vex.getFirstArc() != null){
            return vex.getFirstArc().getAdjVex();
        }else{
            return -1;
        }
    }

    @Override
    public int nextAdjVex(int v, int w) throws Exception {
        if(v < 0 || v >= vexNum){
            throw  new Exception("第" + v + "个顶点不存在");
        }
        VNode vex = vexs[v];
        ArcNode arcvw  = null;
        for(ArcNode arc = vex.getFirstArc(); arc != null; arc = arc.getNextArc()){
            if(arc.getAdjVex() == w){
                arcvw = arc;
                break;
            }
        }

        if(arcvw != null && arcvw.getNextArc() != null){
            return arcvw.getNextArc().getAdjVex();
        }else{
            return -1;
        }

    }

    /**
     * 求各顶点入度的算法
     * @param G
     * @return
     * @throws Exception
     */
    public static int [] findInDegree(ALGraph G) throws Exception{
        int [] indegree = new int [G.getVexNum()];
        for(int i = 0; i < G.getVexNum(); i++){
            for(ArcNode arc = G.getVexs()[i].getFirstArc(); arc != null; arc = arc.getNextArc()){
                ++indegree[arc.getAdjVex()]; //入度增1
            }
        }
        return indegree;
    }


    /**
     * 若G无回路，则输出G的顶点的一个拓扑序列并返回true
     * @param G
     * @return
     * @throws Exception
     */
    public  static boolean topologicialSort(ALGraph G) throws Exception{
        int count = 0;  //输出顶点计数
        int [] indegree = findInDegree(G); //求各顶点入度
        Stack stack = new Stack();  //建零入度顶点栈
        for(int i = 0; i < G.getVexNum(); i++){
            if(indegree[i] == 0){  //入度为0者进栈
                stack.push(i);
            }
        }

        while(!stack.isEmpty()){
            int j = (int)stack.pop();
            System.out.print(G.getVex(j) + " "); //输出v号顶点并计数
            count++;
            for(ArcNode arc = G.getVexs()[j].getFirstArc(); arc != null; arc = arc.getNextArc()){
                int k = arc.getAdjVex(); //对k号顶点的每个邻接点的入度减1
                if(--indegree[k] == 0){ //若入度减为0，则入栈
                    stack.push(k);
                }
            }
        }

        if(count < G.getVexNum()){
            return false; //该有向图有回路
        }else {
            return true;
        }
    }

     //拓扑逆序列顶点栈
     private Stack stack = new Stack();
     //各顶点的最早发生时间和最迟发生时间
    private int [] ve,vl;

    //有向网G采用邻接表存储结构，求各顶点的最早发生时间ve,
    // 若G无回路，则用栈T返回G的一个拓扑序列，且函数返回true,否则为false
    public boolean toplogicalOrder(ALGraph G) throws Exception{
        int count = 0;        //输出顶点计数
        int [] indegree = findInDegree(G); //求各顶点的入度
        Stack s = new Stack();    //建零入度顶点栈s
        for(int i = 0; i < G.getVexNum(); i++){
            if(indegree[i] == 0){  //入度为0者进栈
                s.push(i);
            }
        }

        ve = new int [G.getVexNum()];
        while(!s.isEmpty()){
            int j = (int)s.pop();
            stack.push(j);    //j号顶点入栈并计数
            count++;
            for(ArcNode arc = G.getVexs()[j].getFirstArc(); arc != null; arc = arc.getNextArc()){
                int k = arc.getAdjVex();
                if(--indegree[k] == 0){ //对j号顶点的每个邻接点的入度减1
                    s.push(k);         //若入度减为0，则入栈
                }
                if(ve[j] + arc.getValue() > ve[k]){
                    ve[k] = ve[j] + arc.getValue();
                }
            }
        }

        if(count < G.getVexNum()){
            return false; //该有向图有回路
        }else{
            return true;
        }
    }

    /**
     * G为有向图，输出G的各项关键活动
     * @param G
     * @return
     * @throws Exception
     */
    public boolean criticalPath(ALGraph G) throws Exception{
        if(!toplogicalOrder(G)){
            return false;
        }
        vl = new int[G.getVexNum()];
        for(int i = 0; i < G.getVexNum(); i++){
            //初始化顶点事件的最迟发生时间
            vl[i] = ve[G.getVexNum()-1];
        }

        while(!stack.isEmpty()){  //按拓扑逆序求各顶点的vl值
            int j = (int) stack.pop();
            for(ArcNode arc = G.getVexs()[j].getFirstArc(); arc != null; arc =  arc.getNextArc()){
                int k = arc.getAdjVex();
                int value = arc.getValue();
                if(vl[k] - value < vl[j]){
                    vl[j] = vl[k] - value;
                }
            }
        }

        for(int j = 0; j < G.getVexNum(); j++){
            //求ee,el和关键活动
            for(ArcNode arc = G.getVexs()[j].getFirstArc(); arc != null; arc = arc.getNextArc()){
                int k = arc.getAdjVex();
                int value = arc.getValue();
                int ee = ve[j];
                int el = vl[k] - value;
                char tag = (ee == el) ? '*' : ' ';
                //输出关键活动   * 标记的路径
                System.out.println(G.getVex(j) + " -> " + G.getVex(k) + " "
                        + value + " " + ee + " " + el + " " + tag);
            }
        }
        return true;
    }

















}
