package com.ga.service.impl;

import java.util.List;

import com.ga.dao.impl.UserDaoImpl;
import com.ga.model.User;
import com.ga.service.IUserService;

public class UserServiceImpl implements IUserService {

    UserDaoImpl userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    public boolean registerUser(User user) {
        return userDao.registerUser(user);
    }

    public boolean loginUser(String email, String password) {
        return userDao.loginUser(email, password);
    }

    public int getUserIdByEmail(String email) {
        return userDao.getUserIdByEmail(email);
    }

    public List<User> getUserDetails(int dbUserId) {
        return userDao.getUserDetails(dbUserId);
    }

    public void editUserDetails(User user, Integer userId) {
        userDao.editUserDetails(user, userId);
    }
}
