package com.ga.service.impl;

import java.sql.SQLException;

import com.ga.dao.impl.UserDaoImpl;
import com.ga.model.User;
import com.ga.service.IUserService;

public class UserServiceImpl implements IUserService {

    UserDaoImpl userDao = new UserDaoImpl();
    
    public boolean registerUser(User user) throws SQLException {

        return userDao.registerUser(user);
    }

    public boolean loginUser(String email, String password) throws SQLException {
        
        return userDao.loginUser(email,password);
    }

    public int getUserIdByEmail(String email) throws SQLException {
        
        return userDao.getUserIdByEmail(email);
    }

    
}
