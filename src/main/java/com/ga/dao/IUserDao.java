package com.ga.dao;

import java.util.List;

import com.ga.model.User;

public interface IUserDao {

    public boolean loginUser(String email, String password);

    public boolean registerUser(User user);

    public int getUserIdByEmail(String email);

    public List<User> getUserDetails(int dbUserId);

    public void editUserDetails(User user, Integer userId);

}
