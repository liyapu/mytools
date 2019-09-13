package com.lyp.learn.base.reflect;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-01-08 13:51
 */
public class Student extends Person implements InterfaceA,InterfaceB{
    public  String school;
    public String stuName = "恰同学少年";
    public Integer height;
    public int weight;

    protected  Integer score;

    Integer startHour;
    int endHour;

    private String  levelName;
    private Integer level;

    public Student() {
    }

    public Student(String school, String stuName, Integer height, int weight, Integer score, Integer startHour, int endHour, String levelName, Integer level) {
        this.school = school;
        this.stuName = stuName;
        this.height = height;
        this.weight = weight;
        this.score = score;
        this.startHour = startHour;
        this.endHour = endHour;
        this.levelName = levelName;
        this.level = level;
    }

    public Student(String name,int age){
        this.name = name;
        this.age = age;
    }

    public Student(String name, String father, int age, String address, String hobby, int money, int gender, String school, String stuName, Integer height, int weight, Integer score, Integer startHour, int endHour, String levelName, Integer level) {
        super(name, father, age, address, hobby, money, gender);
        this.school = school;
        this.stuName = stuName;
        this.height = height;
        this.weight = weight;
        this.score = score;
        this.startHour = startHour;
        this.endHour = endHour;
        this.levelName = levelName;
        this.level = level;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }


    @Override
    public String printMessage() {
        return "Student class print message";
    }

    public String getStudentScore(int score){
        return "Student score is :" + score;
    }

    public String getStudentNameScope(String name,Integer score){
        return "Student name is :" + name + ",score ;" + score;
    }

    public static String studentStaticInfo(){
        return "student static info";
    }
    public void studentLevelInfo(){

    }

    protected  String getSchoolName(String schoolName){
        return "Student schoolName is :" + schoolName;
    }

    private String getStudentInfo(){
        return "Student into";
    }


}
