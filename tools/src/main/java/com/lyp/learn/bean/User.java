package com.lyp.learn.bean;


import lombok.Data;
import org.springframework.cglib.core.DebuggingClassWriter;

@Data
public class User {
    private Integer id;
    private String name;
    private int age;
    private String telephone;
    private String address;
    //身高
    private Integer height;
    //体重
    private int weight;

    public User(){
        //关于如何查看生成的class文件，在代码里加入
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/liyapu/myGitRepository/mytools/tools/src/main/java/com/lyp/learn/bean");

    }

    public User(Integer id,String name,int age,String telephone){
        this.id = id;
        this.name = name;
        this.age = age;
        this.telephone = telephone;
        //关于如何查看生成的class文件，在代码里加入
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/liyapu/myGitRepository/mytools/tools/src/main/java/com/lyp/learn/bean");
    }

    public User(String name,int age){
        this.name = name;
        this.age = age;
        //关于如何查看生成的class文件，在代码里加入
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/liyapu/myGitRepository/mytools/tools/src/main/java/com/lyp/learn/bean");
    }


}
