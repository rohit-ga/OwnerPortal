package com.ga.service.impl;

import java.sql.SQLException;
import java.util.List;

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

    public List<User> getUserDetails(int dbUserId) throws SQLException {
        
        return userDao.getUserDetails(dbUserId);
    }

    public void editUserDetails(User user, Integer userId) throws SQLException {

        userDao.editUserDetails(user, userId);
    }

    
}
