package com.lyp.learn.guava.eventbus.demo1.events;

public class Response {
    private String orderNo;
    private String message;

    public Response(String orderNo, String message) {
        this.orderNo = orderNo;
        this.message = message;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "orderNo='" + orderNo + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
