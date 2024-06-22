package com.huynhngoctai.myapplication;

import android.util.Log;

import java.sql.DriverManager;
import java.sql.Connection;


public class DbClass {
    protected static String db ="admin_db";
    protected static String ip ="103.131.74.22";
    protected static String prt ="3306";
    protected static String us ="raspberry";
    protected static String pw ="admin6789@";

    public Connection Connect(){
        Connection conn = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String conStr = "jdbc:mariadb://"+ip+":"+prt+"/"+db;
            conn= DriverManager.getConnection(conStr,us,pw);
            Log.e("SUCCESS", "Succes Connect Database");
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage() + "1");
        }

        return conn;
    }
}