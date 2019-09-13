package com.lyp.learn.base.demo.pk04;

public class Apple extends Fruit {

    private String address;

    public Apple(){
        super();
    }

    public Apple(String address){
        this.address = address;
    }

    public Apple(String color,int weight,String address){
        super(color,weight);
        this.address = address;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Apple{" +
                super.toString() +
                "address='" + address + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Apple a1 = new Apple();
        System.out.println(a1);

        Apple a2 = new Apple("商丘");
        System.out.println(a2);

        Apple a3 = new Apple("红色",50,"郑州");
        System.out.println(a3);
    }
}
