package com.lyp.learn.dp.pattern.mementopattern2;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) {
        VersionControlSystem vcs = new VersionControlSystem();

        Document document = new Document();
        document.content = "c1";
        document.otherContent = "oc1";
        vcs.add(document.save());


        document.content = "c2";
        document.otherContent = "oc2";
        vcs.add(document.save());

        document.content = "c3";
        document.otherContent = "oc3";
        vcs.add(document.save());


        document.content = "c4";
        document.otherContent = "oc4";
        vcs.add(document.save());

        document.resume(vcs.get(2));
        System.out.println(document);

        document.resume(vcs.get(3));
        System.out.println(document);

        document.resume(vcs.getLastVersion());
        System.out.println(document);



    }
}
