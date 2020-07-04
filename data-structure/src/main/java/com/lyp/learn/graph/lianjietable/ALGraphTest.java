package com.lyp.learn.graph.lianjietable;


import com.lyp.learn.graph.GraphKind;

public class ALGraphTest {
    public static void main(String[] args) {
        try {
            System.out.println("-------ALGraph------");

            ArcNode v01 = new ArcNode(1,6);
            ArcNode v02 = new ArcNode(2,4,v01);
            ArcNode v03 = new ArcNode(3,5,v02);
            VNode v0 = new VNode("v0",v03);

            ArcNode v14 = new ArcNode(4,1);
            VNode v1 = new VNode("v1",v14);

            ArcNode v24 = new ArcNode(4,1);
            VNode v2 = new VNode("v2",v24);

            ArcNode v35 = new ArcNode(5,2);
            VNode v3 = new VNode("v3",v35);

            ArcNode v46 = new ArcNode(6,9);
            ArcNode v47 = new ArcNode(7,7,v46);
            VNode v4 = new VNode("v4",v47);

            ArcNode v57 = new ArcNode(7,4);
            VNode v5 = new VNode("v5",v57);

            ArcNode v68 = new ArcNode(8,2);
            VNode v6 = new VNode("v6",v68);

            ArcNode v78 = new ArcNode(8,4);
            VNode v7 = new VNode("v7",v78);

            VNode v8 = new VNode("v8");

            VNode [] vexs = {v0,v1,v2,v3,v4,v5,v6,v7,v8};

            ALGraph g1 = new ALGraph(GraphKind.DG,9,11,vexs);

            g1.criticalPath(g1);








        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
