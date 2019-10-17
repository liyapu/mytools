package com.lyp.learn.dp.pattern.prototype2;

public class Mail implements Cloneable {
    //收件人
    private String receiver;
    //邮件标题
    private String subject;
    //邮件内容
    private String content;
    //邮件尾部，一般加上版权信息
    private String tail;

    public Mail(){

    }

    public Mail(String subject,String content){
        this.subject = subject;
        this.content = content;
    }

    //克隆方法
    @Override
    public Mail clone(){
        Mail mail = null;
        try {
            mail = (Mail) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return mail;
    }


    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }
}
