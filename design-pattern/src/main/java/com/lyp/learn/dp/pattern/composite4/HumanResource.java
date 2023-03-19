package com.lyp.learn.dp.pattern.composite4;

/**
 * @author liyapu
 * @date 2023-03-18 20:52
 * @description
 */
public abstract class HumanResource {

    protected long id;
    protected double salary;

    public HumanResource(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public abstract double calculateSalary();
}
