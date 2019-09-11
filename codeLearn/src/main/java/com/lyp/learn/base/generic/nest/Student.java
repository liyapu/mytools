package com.lyp.learn.base.generic.nest;


public class Student<T> {
    private T score;

    public Student(){

    }
    public Student(T score){
        this.score = score;
    }

    public T getScore() {
        return score;
    }

    public void setScore(T score) {
        this.score = score;
    }
}
