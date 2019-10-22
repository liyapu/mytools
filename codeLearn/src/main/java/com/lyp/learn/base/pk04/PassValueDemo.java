package com.lyp.learn.base.pk04;

public class PassValueDemo {

    public void changeNum(int num){
        System.out.println("changeNum start num  =  " + num);
        num = 100;
        System.out.println("changeNum end   num  =  " + num);
    }

    public void changePassUser(PassUser passUser){
        System.out.println("changePassUser start passUser =  " + passUser);
        passUser.setName("newName");
        System.out.println("changePassUser end   passUser =  " + passUser);
    }

    public static void changeSb1(StringBuffer strBuf) {
        System.out.println("changeSb1 start strBuf =  " + strBuf);
        strBuf.append("World  6666666");
        System.out.println("changeSb1 end   strBuf =  " + strBuf);
    }

    public static void changeSb2(StringBuffer strBuf) {
        System.out.println("changeSb2 start strBuf =  " + strBuf);
        strBuf = new StringBuffer("Hi ");
        strBuf.append("World  88888888");
        System.out.println("changeSb2 end   strBuf =  " + strBuf);
    }


    public static void main(String[] args) {
        PassValueDemo pvd = new PassValueDemo();
        System.out.println("----------值传递-------------");
        int num = 10;
        System.out.println("main start num = " + num);
        pvd.changeNum(num);
        System.out.println("main end num = " + num);
        System.out.println();

        System.out.println("---------引用传递--------------");
        PassUser pu = new PassUser();
        pu.setName("oldName");
        System.out.println("main start pu  =  " + pu);
        pvd.changePassUser(pu);
        System.out.println("main end pu  =  " + pu);
        System.out.println();

        System.out.println("---------StringBuffer demo1------------------");

        StringBuffer sb1 = new StringBuffer("Hello ");
        System.out.println("main start sb1  =  " + sb1);
        changeSb1(sb1);
        System.out.println("main end   sb1  =  " + sb1);
        System.out.println();

        System.out.println("---------StringBuffer demo2------------------");
        StringBuffer sb2 = new StringBuffer("Hello ");
        System.out.println("main start sb2  =  " + sb2);
        changeSb2(sb2);
        System.out.println("main end   sb2  =  " + sb2);



    }

}

class PassUser{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PassUser{" +
                "name='" + name + '\'' +
                '}';
    }
}
