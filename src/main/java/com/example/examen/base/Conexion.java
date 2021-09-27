package com.example.examen.base;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection getConnection() throws SQLException {
        DriverManager.deregisterDriver(new Driver() {
        });
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","te..zaomi123");
    }
    public static void main(String[] args) {
        try{
            Connection con = Conexion.getConnection();
            System.out.println("Conexión exitosa");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}