package com.ga.dao;

import java.sql.SQLException;
import java.util.List;

import com.ga.model.User;

public interface IUserDao {
    
    public boolean loginUser(String email, String password) throws SQLException;
    
    public boolean registerUser(User user) throws SQLException;
    
    public boolean addUser(User user) throws SQLException;
    
    public int getUserIdByEmail(String email) throws SQLException;
    
    public List<User> getUserDetails(int dbUserId) throws SQLException;
    
    public void editUserDetails(User user, Integer userId) throws SQLException;

}
