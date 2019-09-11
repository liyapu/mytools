package com.lyp.learn.dp.pattern.factory5;

public class EasyFactory {
    // 数据库名称
    private static String db = "mysql";
    //private static String db = "oracle";

    public static IUser createUser(){
        IUser iUser = null;
        switch (db){
            case "mysql":
                iUser = new MysqlUser();
                break;
            case "oracle":
                iUser = new OracleUser();
                break;
        }
        return iUser;
    }


    public static ILogin createLogin(){
        ILogin iLogin = null;
        switch (db){
            case "mysql":
                iLogin = new MysqlLogin();
                break;
            case "oracle":
                iLogin = new OracleLogin();
                break;
        }
        return iLogin;
    }
}
