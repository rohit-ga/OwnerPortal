package com.ga.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ga.dao.IUserDao;
import com.ga.model.User;
import com.ga.util.DatabaseConnection;

public class UserDaoImpl implements IUserDao {

    static Connection connection;

    static {
        try {
            connection = DatabaseConnection.doConnection();
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(User user) throws SQLException {

        try {
            PreparedStatement pst = connection.prepareStatement("select email from user where email=?");
            pst.setString(1, user.getUserEmail());

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return false;
            } else {
                return addUser(user);
            }
        } catch (SQLException e) {
            System.err.println("Error::" + e.getMessage());
            // return addUser(user);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return false;

    }

    public boolean addUser(User user) throws SQLException {
        try {
            PreparedStatement pst = connection.prepareStatement("insert into user values (?,?,?,?,?,?,?)");
            pst.setInt(1, 0);
            pst.setString(2, user.getUserFirstName());
            pst.setString(3, user.getUserLastName());
            pst.setString(4, user.getUserGender());
            pst.setLong(5, user.getUserContact());
            pst.setString(6, user.getUserEmail());
            pst.setString(7, user.getUserPassword());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error::" + e.getMessage());

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return true;
    }

    public boolean loginUser(String email, String password) throws SQLException {

        try {
            PreparedStatement pst = connection
                    .prepareStatement("select email,password from user where email=? and password=?");
            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error::" + e.getMessage());
            // return false;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return true;
    }

    public int getUserIdByEmail(String email) throws SQLException {

        int dbUserId = 0;
        try {
            PreparedStatement pst = connection.prepareStatement("select user_id from user where email = ?");
            pst.setString(1, email);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dbUserId = rs.getInt("user_id");
            }
        } catch (SQLException e) {
            System.err.println("Error::" + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return dbUserId;
    }

    public List<User> getUserDetails(int dbUserId) throws SQLException {

        List<User> userDetails = new ArrayList<User>();
        try {
            PreparedStatement pst = connection.prepareStatement("select * from user where user_id = ?");
            pst.setInt(1, dbUserId);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User dbUser = new User();
                dbUser.setUserId(rs.getInt("user_id"));
                dbUser.setUserFirstName(rs.getString("first_name"));
                dbUser.setUserLastName(rs.getString("last_name"));
                dbUser.setUserGender(rs.getString("gender"));
                dbUser.setUserContact(rs.getLong("contact"));
                dbUser.setUserEmail(rs.getString("email"));
                dbUser.setUserPassword(rs.getString("password"));
                userDetails.add(dbUser);
            }
        } catch (SQLException e) {
            System.err.println("Error::" + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return userDetails;
    }

    public void editUserDetails(User user, Integer userId) throws SQLException {

        try {
            PreparedStatement pst = connection
                    .prepareStatement("update user set first_name=?,last_name=?, gender=?, contact=?,email=? where user_id=?");
            pst.setString(1, user.getUserFirstName());
            pst.setString(2, user.getUserLastName());
            pst.setString(3, user.getUserGender());
            pst.setLong(4, user.getUserContact());
            pst.setString(5, user.getUserEmail());
            pst.setInt(6, userId);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error::" + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}