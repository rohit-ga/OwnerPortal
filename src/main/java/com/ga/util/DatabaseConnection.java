package com.ga.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

        public static Connection doConnection() throws SQLException {
            Connection con = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/);
                return con;
            } catch (ClassNotFoundException e) {
                System.out.println("Connection Error is:- " + e.getException());
                e.printStackTrace();
            }
            return null;
        }
}
