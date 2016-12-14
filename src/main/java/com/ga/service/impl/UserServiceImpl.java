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

    
}
