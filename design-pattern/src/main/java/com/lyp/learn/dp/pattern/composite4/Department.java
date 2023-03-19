package com.lyp.learn.dp.pattern.composite4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyapu
 * @date 2023-03-18 20:53
 * @description
 */
public class Department extends HumanResource {

    private List<HumanResource> subNodes = new ArrayList<>();

    public Department(long id) {
        super(id);
    }

    @Override
    public double calculateSalary() {
        double totalSalary = 0;
        for (HumanResource hr : subNodes) {
            totalSalary += hr.calculateSalary();
        }
        this.salary = totalSalary;
        return totalSalary;
    }

    public void addSubNode(HumanResource hr) {
        subNodes.add(hr);
    }
}
