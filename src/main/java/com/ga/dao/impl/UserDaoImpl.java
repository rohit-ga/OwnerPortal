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

    public boolean registerUser(User user) {
        // registration for new user
        PreparedStatement pst;
        ResultSet rs;
        Connection connection1 = null;
        try {
            connection1 = DatabaseConnection.doConnection();
            pst = connection1.prepareStatement("select email from user where email=?");
            pst.setString(1, user.getUserEmail());

            rs = pst.executeQuery();

            if (rs.next()) {
                // User already registred
                return false;
            } else {
                pst = connection1.prepareStatement("insert into user values (?,?,?,?,?,?,?)");
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
        } catch (SQLException e) {
            System.err.println("Error from registerUser() catch block : " + e.getMessage());
            return false;
        } finally {
            if (connection1 != null) {
                try {
                    connection1.close();
                } catch (SQLException e) {
                    System.err.println("Error from registerUser() finally !! : " + e.getMessage());
                }
            }
        }

    }

    public boolean loginUser(String email, String password) {
        // for login purpose
        PreparedStatement pst;
        ResultSet rs;
        Connection connection1 = null;

        try {
            connection1 = DatabaseConnection.doConnection();

            pst = connection1.prepareStatement("select email,password from user where email=? and password=?");
            pst.setString(1, email);
            pst.setString(2, password);

            rs = pst.executeQuery();

            if (rs.next()) {
                // login credentials are correct
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error from loginUser() catch : " + e.getMessage());
            return true;
        } finally {
            if (connection1 != null) {
                try {
                    connection1.close();
                } catch (SQLException e) {
                    System.err.println("Error from loginUser() finally : " + e.getMessage());
                }
            }
        }
    }

    public int getUserIdByEmail(String email) {
        // getting userId by email
        Connection connection = null;
        PreparedStatement pst;
        ResultSet rs;
        int dbUserId = 0;

        try {
            connection = DatabaseConnection.doConnection();
            pst = connection.prepareStatement("select user_id from user where email = ?");
            pst.setString(1, email);

            rs = pst.executeQuery();

            while (rs.next()) {
                dbUserId = rs.getInt("user_id");
            }

        } catch (SQLException e) {
            System.err.println("Error from getUserIdByEmail() catch : " + e.getMessage());
            return dbUserId;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error from getUserIdByEmail() finally : " + e.getMessage());
                }
            }
        }
        return dbUserId;
    }

    public List<User> getUserDetails(int dbUserId) {
        // getting user's details
        Connection connection = null;
        PreparedStatement pst;
        ResultSet rs;
        List<User> userDetails = new ArrayList<User>();
        try {
            connection = DatabaseConnection.doConnection();
            pst = connection.prepareStatement("select * from user where user_id = ?");
            pst.setInt(1, dbUserId);

            rs = pst.executeQuery();
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
                return userDetails;
            }
        } catch (SQLException e) {
            System.err.println("Error from getUserDetails() catch : " + e.getMessage());
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error from getUserDetails finally : " + e.getMessage());
                }
            }
        }
        return userDetails;
    }

    public void editUserDetails(User user, Integer userId) {
        // editing user's details
        Connection connection = null;
        PreparedStatement pst;

        try {
            connection = DatabaseConnection.doConnection();
            pst = connection
                    .prepareStatement("update user set first_name=?,last_name=?, gender=?, contact=?,email=? where user_id=?");
            pst.setString(1, user.getUserFirstName());
            pst.setString(2, user.getUserLastName());
            pst.setString(3, user.getUserGender());
            pst.setLong(4, user.getUserContact());
            pst.setString(5, user.getUserEmail());
            pst.setInt(6, userId);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error from editUserDetails() catch : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error from editUserDetails() finally : " + e.getMessage());
                }
            }
        }
    }
}