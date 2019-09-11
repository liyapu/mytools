package com.lyp.learn.ppt;

public class Apple {
    private String color;
    private Integer weight;
    private String address;

    public Apple() {
    }

    public Apple(String color, Integer weight, String address) {
        this.color = color;
        this.weight = weight;
        this.address = address;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //按重量进行升序排序
    public int compareByWeight(Apple a1 ,Apple a2) {
        return a1.getWeight() - a2.getWeight();
        //return a1.getWeight().compareTo(a2.getWeight());
    }

    //先按颜色排序，颜色相等再按重量升序排序
    public static int compareByColorWeight(Apple a1,Apple a2){
        if(a1.getColor().equals(a2.getColor())){
            return a1.getWeight().compareTo(a2.getWeight());
        }else{
            return a1.getColor().compareTo(a2.getColor());
        }
    }


    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                ", address='" + address + '\'' +
                '}';
    }
}
