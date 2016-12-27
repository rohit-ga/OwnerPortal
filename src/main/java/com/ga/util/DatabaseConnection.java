package com.ga.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection dbConnection;

    private DatabaseConnection() {
    }

    public static DatabaseConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DatabaseConnection();
        }
        return dbConnection;
    }

    public static Connection doConnection() throws SQLException, InstantiationException, IllegalAccessException {
        
        Connection con = null;
     
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost/");
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("Connection Error is:- " + e.getException());
            e.printStackTrace();
        }
        return null;
    }
}