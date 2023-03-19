package com.lyp.learn.dp.pattern.composite4;

/**
 * @author liyapu
 * @date 2023-03-18 20:52
 * @description
 */
public class Employee extends HumanResource {

    public Employee(long id, double salary) {
        super(id);
        this.salary = salary;
    }

    @Override
    public double calculateSalary() {
        return salary;
    }
}
