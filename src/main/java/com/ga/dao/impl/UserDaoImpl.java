package com.ga.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ga.dao.IUserDao;
import com.ga.model.User;
import com.ga.util.DatabaseConnection;

public class UserDaoImpl implements IUserDao {

    static Connection connection;

    static {
        try {
            connection = DatabaseConnection.doConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(User user) throws SQLException {

        PreparedStatement pst = connection.prepareStatement("select email from user where email=?");
        pst.setString(1, user.getUserEmail());

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return false;
        } else {
            return addUser(user);
        }
    }

    private boolean addUser(User user) throws SQLException {

        PreparedStatement pst = connection.prepareStatement("insert into user values (?,?,?,?,?,?,?)");
        pst.setInt(1, 0);
        pst.setString(2, user.getUserFirstName());
        pst.setString(3, user.getUserLastName());
        pst.setString(4, user.getUserGender());
        pst.setLong(5, user.getUserContact());
        pst.setString(6, user.getUserEmail());
        pst.setString(7, user.getUserPassword());
        pst.executeUpdate();
        return true;
    }
}