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

    public static Connection doConnection() {

        Connection con = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        return con;
    }
}