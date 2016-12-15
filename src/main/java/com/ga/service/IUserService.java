package com.ga.service;

import java.sql.SQLException;

import com.ga.model.User;

public interface IUserService {

    public boolean registerUser(User user) throws SQLException;
    
    public boolean loginUser(String email, String password) throws SQLException;
    
    public int getUserIdByEmail(String email) throws SQLException;
}
