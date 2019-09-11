package com.lyp.learn.dp.pattern.prototype2;

import java.util.Random;

public class Client {
    private static int MAX_COUNT = 5;

    public static void main(String[] args) {
        Mail mail = new Mail("大促销","商城大减价促销，欢迎选购，欢迎光临.");
        mail.setTail("版权信息归：商城所有");

        int i = 1;
        while (i <= MAX_COUNT){
            Mail cloneMail = mail.clone();
            cloneMail.setReceiver(getReceiverAddress());
            sendMail(cloneMail);
            i++;
        }
    }

    /**
     * 获取收件人地址
     * @return
     */
    public static String getReceiverAddress(){
        String str = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i <= 8; i++){
            sb.append(str.charAt(random.nextInt(str.length())));
        }
        return sb.append("@.qq.com").toString();
    }

    /**
     * 发送邮件
     * @param mail
     */
    public static void sendMail(Mail mail){
        System.out.println("标题: " + mail.getSubject() + " ,收件人: " + mail.getReceiver() + " , 发送成功!");
    }
}
