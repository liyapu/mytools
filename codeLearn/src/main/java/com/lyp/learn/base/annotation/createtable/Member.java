package com.lyp.learn.base.annotation.createtable;

/**
 * @Author: liyapu
 * @Description: 数据库member表对应的bean
 * @create: 2018-11-08 22:33
 */

@DBTable(name="member")
public class Member {

    //主键ID
    @SQLInteger(name = "id", constraint = @Constraints(primaryKey = true))
    private Integer id;

    @SQLString(name = "name" , value = 30)
    private String name;

    @SQLInteger(name = "age")
    private int age;

    //个人描述
    @SQLString(name = "description" ,value = 150 , constraint = @Constraints(allowNull = true))
    private String description;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
