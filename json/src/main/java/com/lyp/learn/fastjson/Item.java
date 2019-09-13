package com.lyp.learn.fastjson;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Item {
    private Integer sum;
    private Integer total;

    private int min;
    private int max;

    private String address;
    private String description;

    private boolean girl;
    private boolean student;

    private Boolean high;
    private Boolean thin;

    private Color color11;
    private Color color22;

    List<User> userList11;
    List<User> userList22;

    Set<User> setUser11;
    Set<User> setUser22;


    Map<String,String> map11;
    Map<String,String> map22;


    public Item() {
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isGirl() {
        return girl;
    }

    public void setGirl(boolean girl) {
        this.girl = girl;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public Boolean getHigh() {
        return high;
    }

    public void setHigh(Boolean high) {
        this.high = high;
    }

    public Boolean getThin() {
        return thin;
    }

    public void setThin(Boolean thin) {
        this.thin = thin;
    }

    public Color getColor11() {
        return color11;
    }

    public void setColor11(Color color11) {
        this.color11 = color11;
    }

    public Color getColor22() {
        return color22;
    }

    public void setColor22(Color color22) {
        this.color22 = color22;
    }

    public List<User> getUserList11() {
        return userList11;
    }

    public void setUserList11(List<User> userList11) {
        this.userList11 = userList11;
    }

    public List<User> getUserList22() {
        return userList22;
    }

    public void setUserList22(List<User> userList22) {
        this.userList22 = userList22;
    }

    public Map<String, String> getMap11() {
        return map11;
    }

    public void setMap11(Map<String, String> map11) {
        this.map11 = map11;
    }

    public Map<String, String> getMap22() {
        return map22;
    }

    public void setMap22(Map<String, String> map22) {
        this.map22 = map22;
    }

    public Set<User> getSetUser11() {
        return setUser11;
    }

    public void setSetUser11(Set<User> setUser11) {
        this.setUser11 = setUser11;
    }

    public Set<User> getSetUser22() {
        return setUser22;
    }

    public void setSetUser22(Set<User> setUser22) {
        this.setUser22 = setUser22;
    }

    @Override
    public String toString() {
        return "Item{" +
                "sum=" + sum +
                ", total=" + total +
                ", min=" + min +
                ", max=" + max +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", girl=" + girl +
                ", student=" + student +
                ", high=" + high +
                ", thin=" + thin +
                ", color11=" + color11 +
                ", color22=" + color22 +
                ", userList11=" + userList11 +
                ", userList22=" + userList22 +
                ", map11=" + map11 +
                ", map22=" + map22 +
                ", setUser11=" + setUser11 +
                ", setUser22=" + setUser22 +
                '}';
    }
}
