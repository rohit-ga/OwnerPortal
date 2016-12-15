package com.ga.dao;

import java.sql.SQLException;

import com.ga.model.User;

public interface IUserDao {
    
    public boolean loginUser(String email, String password) throws SQLException;
    
    public boolean registerUser(User user) throws SQLException;
    
    public boolean addUser(User user) throws SQLException;

}
