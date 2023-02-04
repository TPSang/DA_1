/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.tienich;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class conect {
    public static void main(String[] args, String sever, String user, String db) throws SQLException {
        sever = "DESKTOP-L3D2584\\SAMCOM";
        user = "sa";
        String password = "123456";
        db = "DUAN_1_QLSPA1";
        String port = "1433";
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(user);
        ds.setPassword(password);
        ds.setPortNumber(1433);
        ds.setServerName(sever);
        try(Connection conn = ds.getConnection()){
            System.out.println("ket noi thanh cong");}
    }
}
