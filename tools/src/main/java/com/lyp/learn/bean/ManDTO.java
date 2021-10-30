package com.lyp.learn.bean;

import java.util.List;

/**
 * @author liyapu
 * @date 2021-10-30 20:23
 * @desc
 */
public class ManDTO {

    private List<Man> manList;

    private int total;

    public List<Man> getManList() {
        return manList;
    }

    public void setManList(List<Man> manList) {
        this.manList = manList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
