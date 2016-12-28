package com.ga.service;

import java.util.List;

import com.ga.model.User;

public interface IUserService {

    public boolean registerUser(User user);
    
    public boolean loginUser(String email, String password);
    
    public int getUserIdByEmail(String email);
    
    public List<User> getUserDetails(int dbUserId);
    
    public void editUserDetails(User user, Integer userId);
}
