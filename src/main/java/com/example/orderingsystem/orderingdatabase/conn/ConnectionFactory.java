package com.example.orderingsystem.orderingdatabase.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    static ConnectionFactory INSTANCE = null;


    private ConnectionFactory (){}

    public static ConnectionFactory getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new ConnectionFactory();
        return INSTANCE;
    }

    public static Connection getConnection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/orderingdb";
        String username = "root";
        String password = "root";
        return DriverManager.getConnection(url, username, password);
    }

}
